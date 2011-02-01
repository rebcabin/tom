/*
 *
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2010, INPL, INRIA
 * Nancy, France.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA
 * 
 * Radu Kopetz e-mail: Radu.Kopetz@loria.fr
 * Pierre-Etienne Moreau  e-mail: Pierre-Etienne.Moreau@loria.fr
 *
 **/
package tom.engine.compiler.propagator;

import tom.engine.adt.tomsignature.types.*;
import tom.engine.adt.tomconstraint.types.*;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomtype.types.*;
import tom.engine.adt.tomname.types.*;
import tom.engine.adt.code.types.*;
import tom.library.sl.*;
import tom.engine.adt.tomslot.types.*;
import tom.engine.tools.SymbolTable;
import tom.engine.tools.ASTFactory;
import tom.engine.compiler.*;
import tom.engine.TomBase;
import java.util.*;
import tom.engine.exception.TomRuntimeException;
import tom.engine.compiler.Compiler;

/**
 * Syntactic propagator
 */
public class SyntacticPropagator implements IBasePropagator {

//--------------------------------------------------------
  %include { ../../adt/tomsignature/TomSignature.tom }
  %include { ../../../library/mapping/java/sl.tom }	
  %include { constraintstrategies.tom }	
//--------------------------------------------------------
  
  %typeterm SyntacticPropagator {
    implement { SyntacticPropagator }
    is_sort(t) { ($t instanceof SyntacticPropagator) }
  }

  private Compiler compiler; 
  private ConstraintPropagator constraintPropagator;
 
  public SyntacticPropagator(Compiler myCompiler, ConstraintPropagator myConstraintPropagator) {
    this.compiler = myCompiler;
    this.constraintPropagator = myConstraintPropagator;
  }

  public Compiler getCompiler() {
    return this.compiler;
  }
 
  public ConstraintPropagator getConstraintPropagator() {
    return this.constraintPropagator;
  }
 

  public Constraint propagate(Constraint constraint) throws VisitFailure {   
    return  `TopDownWhenConstraint(SyntacticPatternMatching(this)).visitLight(constraint);
  }	

  %strategy SyntacticPatternMatching(sp:SyntacticPropagator) extends `Identity() {
    visit Constraint {
      /**
       * Decompose
       * 
       * f1(t1,...,tn) = g 
       * -> freshSubject = g /\ f1 = SymbolOf(freshSubject) /\ freshVar1=subterm1_f(freshSubject) /\ ... /\ freshVarn=subterm1_f(freshSubject) 
       *                /\ t1=freshVar1 /\ ... /\ tn=freshVarn
       * 
       * if f has multiple names (from f1|f2): 
       * (f1|f2)(t1,...,tn) = g 
       * -> freshSubject = g /\ ( (f1 = SymbolOf(freshSubject) /\ freshVar1=subterm1_f1(freshSubject) /\ ... /\ freshVarn=subtermn_f1(freshSubject)) 
       *       \/ (f2 = SymbolOf(freshSubject) /\ freshVar1=subterm1_f2(freshSubject) /\ ... /\ freshVarn=subtermn_f2(freshSubject)) ) 
       *  /\ t1=freshVar1 /\ ... /\ tn=freshVarn
       * 
       * if the symbol was annotated, annotations are detached:
       *        a@...b@f(...) << t -> f(...) << t /\ a << t /\ ... /\ b << t
       */
      m@MatchConstraint[Pattern=RecordAppl(options,nameList@concTomName(firstName@Name(tomName),_*),slots,_),Subject=g@!SymbolOf[],AstType=aType] -> {
        // if this a list or array, nothing to do
        if(!TomBase.isSyntacticOperator(
            sp.getCompiler().getSymbolTable().getSymbolFromName(`tomName))) {
          return `m; 
        }
       
        System.out.println("m = " + `m);
        System.out.println("aType = " + `aType);
        List<Constraint> lastPart = new ArrayList<Constraint>();
        ArrayList<BQTerm> freshVarList = new ArrayList<BQTerm>();
        // we build the last part only once, and we store the fresh variables we generate
        %match(slots) {
          concSlot(_*,PairSlotAppl(slotName,appl),_*) -> {
            TomType slotType =
              sp.getCompiler().getSlotType(`firstName,`slotName);
            System.out.println("slotType = " + slotType);
            System.out.println("slotName= " + `slotName);
            BQTerm freshVar = sp.getCompiler().getFreshVariable(slotType);
            // store the fresh variable
            freshVarList.add(freshVar);
            // build the last part
            lastPart.add(`MatchConstraint(appl,freshVar,slotType));
            System.out.println("Match#1= " + `MatchConstraint(appl,freshVar,slotType));
          }
        }
        // introduce an intermediate variable (with another type) to handle subtyping
        TomSymbol symbol =
          sp.getCompiler().getSymbolTable().getSymbolFromName(`tomName);
        TomType pType = TomBase.getSymbolCodomain(symbol);
        //DEBUG System.out.println("*** " + symbol);
        BQTerm freshSubject =
          sp.getCompiler().getFreshVariable(pType);

        // take each symbol and build the disjunction (OrConstraintDisjunction)
        Constraint l = `OrConstraintDisjunction();
        %match(nameList) {
          concTomName(_*,name,_*) -> {
            // the 'and' conjunction for each name
            List<Constraint> andForName = new ArrayList<Constraint>();
            // add condition for symbolOf
            andForName.add(`MatchConstraint(RecordAppl(options,concTomName(name),concSlot(),concConstraint()),SymbolOf(freshSubject),pType));
            int counter = 0;

            BQTerm freshCastedSubject = sp.getCompiler().getFreshVariable(pType);
            TomTerm var = TomBase.convertFromBQVarToVar(freshCastedSubject);
            //DEBUG System.out.println("*** " + var);
            //DEBUG System.out.println("+++ " + pType);
            //DEBUG System.out.println("--- " + freshSubject);
            andForName.add(`MatchConstraint(var,freshSubject,pType));

            // for each slot
            %match(slots) {
              concSlot(_*,PairSlotAppl(slotName,_),_*) -> {                                          
                BQTerm freshVar = freshVarList.get(counter);
                TomType slotType =
                  sp.getCompiler().getSlotType(`name,`slotName); 
                andForName.add(`MatchConstraint(TomBase.convertFromBQVarToVar(freshVar),Subterm(name,slotName,freshCastedSubject),slotType));
                System.out.println("Match#2= " + `MatchConstraint(TomBase.convertFromBQVarToVar(freshVar),Subterm(name,slotName,freshCastedSubject),slotType));
                counter++;
                //DEBUG System.out.println("Type slot = " + slotType);
                //DEBUG System.out.println("--- aType =  " + `aType);
              }
            }// match slots
            l = `OrConstraintDisjunction(l*,ASTFactory.makeAndConstraint(andForName));
          }
        }
        lastPart.add(0,l);
        lastPart.add(0,`MatchConstraint(TomBase.convertFromBQVarToVar(freshSubject),g,pType));
        System.out.println("Match = " +
            `MatchConstraint(TomBase.convertFromBQVarToVar(freshSubject),g,pType));
        System.out.println("In Syntactic Propagator: lastPart = " + lastPart);
        lastPart.add(sp.getConstraintPropagator().performDetach(`m));
        return ASTFactory.makeAndConstraint(lastPart);
      }      
    }
  }// end %strategy
}
