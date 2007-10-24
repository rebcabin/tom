/*
 *
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2007, INRIA
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

import tom.engine.adt.tomconstraint.types.*;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomtype.types.*;
import tom.engine.adt.tomname.types.*;
import tom.library.sl.*;
import tom.engine.adt.tomslot.types.*;
import tom.engine.tools.SymbolTable;
import tom.engine.compiler.*;
import tom.engine.TomBase;
import java.util.*;
import tom.engine.exception.TomRuntimeException;

/**
 * Syntactic propagator
 */
public class SyntacticPropagator implements IBasePropagator {

//--------------------------------------------------------
  %include { ../../adt/tomsignature/TomSignature.tom }
  %include { ../../../library/mapping/java/sl.tom}	
//--------------------------------------------------------
  
  // contains variables that were already replaced (for optimizing reasons)
  private static ArrayList replacedVariables = null; 

  public Constraint propagate(Constraint constraint) throws VisitFailure {   
    replacedVariables = new ArrayList(); 
    return  (Constraint)`TopDown(SyntacticPatternMatching()).visitLight(constraint);
  }	

  %strategy SyntacticPatternMatching() extends `Identity() {
    visit Constraint {
      /**
       * Decompose
       * 
       * f1(t1,...,tn) = g 
       * -> f1 = SymbolOf(g) /\ freshVar1=subterm1_f(g) /\ ... /\ freshVarn=subterm1_f(g) 
       *                /\ t1=freshVar1 /\ ... /\ tn=freshVarn
       * 
       * if f has multiple names (from f1|f2): 
       * (f1|f2)(t1,...,tn) = g 
       * -> ( (f1 = SymbolOf(g) /\ freshVar1=subterm1_f1(g) /\ ... /\ freshVarn=subtermn_f1(g)) 
       *       \/ (f2 = SymbolOf(g) /\ freshVar1=subterm1_f2(g) /\ ... /\ freshVarn=subtermn_f2(g)) ) 
       *  /\ t1=freshVar1 /\ ... /\ tn=freshVarn
       * 
       * we can decompose only if 'g' != SymbolOf
       * if the symbol was annotated, annotations are detached:
       *        a@...b@f(...) << t -> f(...) << t /\ a << t /\ ... /\ b << t
       */
      m@MatchConstraint(RecordAppl(options,nameList@(firstName@Name(tomName),_*),slots,_),g@!SymbolOf[]) -> {
        // if this a list or array, nothing to do
        if(!TomBase.isSyntacticOperator(
            ConstraintCompiler.getSymbolTable().getSymbolFromName(`tomName))) { return `m; }
        
        Constraint lastPart = `AndConstraint();
        ArrayList<TomTerm> freshVarList = new ArrayList<TomTerm>();
        // we build the last part only once, and we store the fresh variables we generate
        %match(slots) {
          concSlot(_*,PairSlotAppl(slotName,appl),_*) -> {
            TomTerm freshVar = ConstraintCompiler.getFreshVariable(ConstraintCompiler.getSlotType(`firstName,`slotName));
            // store the fresh variable
            freshVarList.add(freshVar);
            // build the last part
            lastPart = `AndConstraint(lastPart*,MatchConstraint(appl,freshVar));              
          }
        }
        // take each symbol and build the disjunction
        Constraint l = `OrConstraintDisjunction();
        %match(nameList) {
          concTomName(_*,name,_*) -> {
            // the 'and' conjunction for each name
            Constraint andForName = `AndConstraint();
            // add condition for symbolOf
            andForName = `AndConstraint(MatchConstraint(RecordAppl(options,concTomName(name),concSlot(),concConstraint()),SymbolOf(g)));
            int counter = 0;          
            // for each slot
            %match(slots) {
              concSlot(_*,PairSlotAppl(slotName,_),_*) -> {                                          
                TomTerm freshVar = freshVarList.get(counter);          
                andForName = `AndConstraint(andForName*,MatchConstraint(freshVar,Subterm(name,slotName,g)));
                counter++;
              }
            }// match slots
            l = `OrConstraintDisjunction(l*,andForName);
          }
        }
        return `AndConstraint(l*,lastPart*,ConstraintPropagator.performDetach(m));
      }      
    }
  }// end %strategy
}
