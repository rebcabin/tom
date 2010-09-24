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

import tom.engine.compiler.propagator.*;  

import tom.engine.adt.tomconstraint.types.*;
import tom.engine.adt.tomsignature.types.*;
import tom.engine.adt.tomtype.types.*;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomname.types.*;
import tom.engine.adt.code.types.*;
import tom.engine.adt.tomterm.types.tomterm.*;
import tom.library.sl.*;
import tom.engine.adt.tomslot.types.*;
import tom.engine.compiler.*;
import tom.engine.exception.TomRuntimeException;
import tom.engine.TomBase;
import tom.engine.compiler.Compiler;

/**
 * Array propagator
 */
public class ArrayPropagator implements IBasePropagator {

//--------------------------------------------------------	
  %include { ../../adt/tomsignature/TomSignature.tom }	
  %include { ../../../library/mapping/java/sl.tom }
  %include { constraintstrategies.tom }	
//--------------------------------------------------------

  %typeterm ArrayPropagator {
    implement { ArrayPropagator }
    is_sort(t) { ($t instanceof ArrayPropagator) }
  }

  %typeterm GeneralPurposePropagator {
    implement { GeneralPurposePropagator }
    is_sort(t) { ($t instanceof GeneralPurposePropagator) }
  }

  private Compiler compiler;
  private ConstraintPropagator constraintPropagator; // only present for compatibility 
  private GeneralPurposePropagator generalPurposePropagator;  
 
  public ArrayPropagator(Compiler myCompiler, ConstraintPropagator myConstraintPropagator) {
    this.compiler = myCompiler;
    this.constraintPropagator = myConstraintPropagator; // only present for compatibility 
    this.generalPurposePropagator = new GeneralPurposePropagator(this.compiler, this.constraintPropagator);
  }

  public Compiler getCompiler() {
    return this.compiler;
  }
 
  public GeneralPurposePropagator getGeneralPurposePropagator() {
    return this.generalPurposePropagator;
  }
 
  public ConstraintPropagator getConstraintPropagator() {
    return this.constraintPropagator;
  }
 
  public Constraint propagate(Constraint constraint) throws VisitFailure {
    return `TopDownWhenConstraint(ArrayPatternMatching(this)).visitLight(constraint);		
  }	

  %strategy ArrayPatternMatching(ap:ArrayPropagator) extends Identity() {
    visit Constraint {
      /**
       * Detach sublists
       * 
       * Make sure that the sublists in a list are replaced by star variables - this is only happening 
       * when the lists and the sublists have the same name
       * 
       * conc(X*,conc(some_pattern),Y*) << t -> conc(X*,Z*,Y*) << t /\ conc(some_pattern) << Z*  
       * 
       */ 
      m@MatchConstraint[Pattern=pattern@RecordAppl[NameList=concTomName(Name(tomName)),Slots=!concSlot()]] -> {
        if(TomBase.hasTheory(`pattern,`AC())) {
          return `m;
        }
        // if this is not an array, nothing to do
        if(!TomBase.isArrayOperator(ap.getCompiler().getSymbolTable().
            getSymbolFromName(`tomName))) { return `m; }
        Constraint detachedConstr = ap.getGeneralPurposePropagator().detachSublists(`m);
        // if something changed
        if (detachedConstr != `m) { return detachedConstr; }
      }      
      
      /*
         array[t1,X*,t2,Y*] = g -> freshSubj = g /\ array=SymbolOf(freshSubj) /\ fresh_index = 0 
         /\ HasElement(fresh_index,freshSubj)  /\ t1=GetElement(fresh_index,freshSubj) /\ fresh_index1 = fresh_index + 1 
         /\ begin1 = fresh_index1  /\ end1 = fresh_index1 /\ X* = VariableHeadArray(begin1,end1) /\ fresh_index2 = end1
         /\ HasElement(fresh_index2,freshSubj) /\ t2=GetElement(fresh_index2,freshSubj)/\ fresh_index3 = fresh_index2 + 1  
         /\ begin2 = fresh_index3  /\ end2 = fresh_index3 /\ Y* = VariableHeadArray(begin2,end2) /\ fresh_index4 = end2
      */
      m@MatchConstraint[Pattern=pattern@RecordAppl(options,nameList@concTomName(name@Name(tomName),_*),slots,_),Subject=g@!SymbolOf[],AstType=aType] -> {      
        if(TomBase.hasTheory(`pattern,`AC())) {
          return `m;
        }
            // if this is not an array, nothing to do
            if(!TomBase.isArrayOperator(ap.getCompiler().getSymbolTable().
                getSymbolFromName(`tomName))) {return `m;}        
            // declare fresh variable            
            TomType termType = ap.getCompiler().getTermTypeFromTerm(`g);            
            BQTerm freshVariable = ap.getCompiler().getFreshVariableStar(termType);
            // g should be only a variable
            Constraint freshVarDeclaration =
              `MatchConstraint(TomBase.convertFromBQVarToVar(freshVariable),g,aType);
            
            // declare fresh index = 0            
            BQTerm freshIndex = ap.getFreshIndex();				
            Constraint freshIndexDeclaration =
              `MatchConstraint(TomBase.convertFromBQVarToVar(freshIndex),
                  ExpressionToBQTerm(Integer(0)),aType);
            Constraint l = `AndConstraint();
    match:  %match(slots) {
              concSlot() -> {
                l = `AndConstraint(l*,EmptyArrayConstraint(name,freshVariable,freshIndex));
              }
              concSlot(_*,PairSlotAppl[Appl=appl],X*) -> {
                BQTerm newFreshIndex = ap.getFreshIndex();                
          mAppl:%match(appl){
                  // if we have a variable star
                  VariableStar[] -> {
                    // if it is the last element               
                    if(`X.length() == 0) {
                      // we should only assign it, without generating a loop
                      l = `AndConstraint(l*,MatchConstraint(appl,ExpressionToBQTerm(
                            GetSliceArray(name,freshVariable,freshIndex,ExpressionToBQTerm(GetSize(name,freshVariable)))),aType));
                    } else {
                      BQTerm beginIndex = ap.getBeginIndex();
                      BQTerm endIndex = ap.getEndIndex();
                      l = `AndConstraint(l*,
                          MatchConstraint(TomBase.convertFromBQVarToVar(beginIndex),freshIndex,aType),
                          MatchConstraint(TomBase.convertFromBQVarToVar(endIndex),freshIndex,aType),
                          MatchConstraint(appl,VariableHeadArray(name,freshVariable,beginIndex,endIndex),aType),
                          MatchConstraint(TomBase.convertFromBQVarToVar(newFreshIndex),endIndex,aType));     
                    }
                    break mAppl;
                  }
                  _ -> {                    
                    l = `AndConstraint(l*,                      
                        Negate(EmptyArrayConstraint(name,freshVariable,freshIndex)),                      
                        MatchConstraint(appl,ExpressionToBQTerm(GetElement(name,ap.getCompiler().getTermTypeFromTerm(appl),freshVariable,freshIndex)),aType),
                        MatchConstraint(TomBase.convertFromBQVarToVar(newFreshIndex),ExpressionToBQTerm(AddOne(freshIndex)),aType));
                    // for the last element, we should also check that the list ends
                    if(`X.length() == 0) {                  
                      l = `AndConstraint(l*, EmptyArrayConstraint(name,freshVariable,newFreshIndex));
                    }
                  }
                }// end match
                freshIndex = newFreshIndex;
              }
            }// end match                        
            // add head equality condition + fresh var declaration + detached constraints
            l =
              `AndConstraint(freshVarDeclaration,MatchConstraint(RecordAppl(options,nameList,concSlot(),concConstraint()),SymbolOf(freshVariable),aType),
                freshIndexDeclaration,ap.getConstraintPropagator().performDetach(m),l*);
            return l;
        }
    }
  }// end %strategy

  private BQTerm getBeginIndex() {
    return getCompiler().getBeginVariableStar(getCompiler().getSymbolTable().getIntType());
  }

  private BQTerm getEndIndex() {
    return getCompiler().getEndVariableStar(getCompiler().getSymbolTable().getIntType());
  }

  private BQTerm getFreshIndex() {
    return getCompiler().getFreshVariableStar(getCompiler().getSymbolTable().getIntType());    
  }
}
