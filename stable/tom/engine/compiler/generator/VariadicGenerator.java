/* Generated by TOM (version 2.6alpha): Do not edit this file *//*
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
package tom.engine.compiler.generator;

import tom.engine.adt.tominstruction.types.*;
import tom.engine.adt.tomexpression.types.*;
import tom.engine.adt.tomexpression.types.expression.*;
import tom.engine.adt.tomname.types.*;
import tom.engine.adt.tomname.types.tomname.*;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomtype.types.*;
import tom.engine.adt.tomterm.types.tomterm.*;
import tom.library.sl.*;
import tom.engine.tools.SymbolTable;
import tom.engine.exception.TomRuntimeException;
import tom.engine.adt.tomsignature.types.*;
import tom.engine.TomBase;
import tom.engine.compiler.*;
import tom.engine.compiler.Compiler;

/**
 * Variadic Generator
 */
public class VariadicGenerator implements IBaseGenerator {

  /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */  /* Generated by TOM (version 2.6alpha): Do not edit this file */  /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */   private static   tom.library.sl.Strategy  tom_append_list_Sequence( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 == null )) {       return l2;     } else if(( l2 == null )) {       return l1;     } else if(( (l1 instanceof tom.library.sl.Sequence) )) {       if(( ((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )) == null )) {         return ( (l2==null)?((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1):new tom.library.sl.Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1),l2) );       } else {         return ( (tom_append_list_Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),l2)==null)?((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1):new tom.library.sl.Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1),tom_append_list_Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),l2)) );       }     } else {       return ( (l2==null)?l1:new tom.library.sl.Sequence(l1,l2) );     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else {       return ( (( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)==null)?((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin):new tom.library.sl.Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)) );     }   }    /* Generated by TOM (version 2.6alpha): Do not edit this file */ /* Generated by TOM (version 2.6alpha): Do not edit this file */private static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ),( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )==null)?v:new tom.library.sl.Sequence(v,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )) )) ) ); }   
	

  public Expression generate(Expression expression) throws VisitFailure {
    return (Expression)tom_make_TopDown(tom_make_Generator()).visit(expression);
  }

  // If we find ConstraintToExpression it means that this constraint was not processed	
  private static class Generator extends  tom.engine.adt.tomsignature.TomSignatureBasicStrategy  {public Generator() { super(( new tom.library.sl.Identity() ));}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() { return 1; }public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}public  tom.engine.adt.tomexpression.types.Expression  visit_Expression( tom.engine.adt.tomexpression.types.Expression  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{if ( (tom__arg instanceof tom.engine.adt.tomexpression.types.Expression) ) {{  tom.engine.adt.tomexpression.types.Expression  tomMatch160NameNumberfreshSubject_1=(( tom.engine.adt.tomexpression.types.Expression )tom__arg);if ( (tomMatch160NameNumberfreshSubject_1 instanceof tom.engine.adt.tomexpression.types.expression.ConstraintToExpression) ) {{  tom.engine.adt.tomconstraint.types.Constraint  tomMatch160NameNumber_freshVar_0= tomMatch160NameNumberfreshSubject_1.getcons() ;if ( (tomMatch160NameNumber_freshVar_0 instanceof tom.engine.adt.tomconstraint.types.constraint.MatchConstraint) ) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch160NameNumber_freshVar_1= tomMatch160NameNumber_freshVar_0.getpattern() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch160NameNumber_freshVar_2= tomMatch160NameNumber_freshVar_0.getsubject() ;{ boolean tomMatch160NameNumber_freshVar_7= false ;if ( (tomMatch160NameNumber_freshVar_1 instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) {tomMatch160NameNumber_freshVar_7= true ;} else {if ( (tomMatch160NameNumber_freshVar_1 instanceof tom.engine.adt.tomterm.types.tomterm.UnamedVariableStar) ) {tomMatch160NameNumber_freshVar_7= true ;}}if ((tomMatch160NameNumber_freshVar_7 ==  true )) {{  tom.engine.adt.tomterm.types.TomTerm  tom_v=tomMatch160NameNumber_freshVar_1;if ( (tomMatch160NameNumber_freshVar_2 instanceof tom.engine.adt.tomterm.types.tomterm.VariableHeadList) ) {{  tom.engine.adt.tomname.types.TomName  tomMatch160NameNumber_freshVar_3= tomMatch160NameNumber_freshVar_2.getOpname() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch160NameNumber_freshVar_4= tomMatch160NameNumber_freshVar_2.getBegin() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch160NameNumber_freshVar_5= tomMatch160NameNumber_freshVar_2.getEnd() ;{  tom.engine.adt.tomname.types.TomName  tom_opName=tomMatch160NameNumber_freshVar_3;{  tom.engine.adt.tomterm.types.TomTerm  tom_begin=tomMatch160NameNumber_freshVar_4;if ( (tomMatch160NameNumber_freshVar_5 instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) {{  tom.engine.adt.tomtype.types.TomType  tomMatch160NameNumber_freshVar_6= tomMatch160NameNumber_freshVar_5.getAstType() ;{  tom.engine.adt.tomtype.types.TomType  tom_type=tomMatch160NameNumber_freshVar_6;{  tom.engine.adt.tomterm.types.TomTerm  tom_end=tomMatch160NameNumber_freshVar_5;if ( true ) {












        Expression doWhileTest =  tom.engine.adt.tomexpression.types.expression.Negation.make( tom.engine.adt.tomexpression.types.expression.EqualTerm.make(tom_type, tom_end, tom_begin) ) ;
        Expression testEmpty = ConstraintGenerator.genIsEmptyList(tom_opName,tom_end);
        Expression endExpression =  tom.engine.adt.tomexpression.types.expression.IfExpression.make(testEmpty,  tom.engine.adt.tomexpression.types.expression.EqualTerm.make(tom_type, tom_end, tom_begin) ,  tom.engine.adt.tomexpression.types.expression.EqualTerm.make(tom_type, tom_end,  tom.engine.adt.tomterm.types.tomterm.ListTail.make(tom_opName, tom_end) ) ) ;
        // if we have a varStar, we generate its declaration also
        if (tom_v.isVariableStar()) {
          Expression varDeclaration =  tom.engine.adt.tomexpression.types.expression.ConstraintToExpression.make( tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(tom_v,  tom.engine.adt.tomterm.types.tomterm.ExpressionToTomTerm.make( tom.engine.adt.tomexpression.types.expression.GetSliceList.make(tom_opName, tom_begin, tom_end,  tom.engine.adt.tomterm.types.tomterm.BuildEmptyList.make(tom_opName) ) ) ) ) ;
          return  tom.engine.adt.tomexpression.types.expression.And.make( tom.engine.adt.tomexpression.types.expression.DoWhileExpression.make(endExpression, doWhileTest) , varDeclaration) ;
        }
        return  tom.engine.adt.tomexpression.types.expression.DoWhileExpression.make(endExpression, doWhileTest) ;		        		      
      }}}}}}}}}}}}}}}}}}}}}if ( (tom__arg instanceof tom.engine.adt.tomexpression.types.Expression) ) {{  tom.engine.adt.tomexpression.types.Expression  tomMatch160NameNumberfreshSubject_1=(( tom.engine.adt.tomexpression.types.Expression )tom__arg);if ( (tomMatch160NameNumberfreshSubject_1 instanceof tom.engine.adt.tomexpression.types.expression.ConstraintToExpression) ) {{  tom.engine.adt.tomconstraint.types.Constraint  tomMatch160NameNumber_freshVar_8= tomMatch160NameNumberfreshSubject_1.getcons() ;if ( (tomMatch160NameNumber_freshVar_8 instanceof tom.engine.adt.tomconstraint.types.constraint.MatchConstraint) ) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch160NameNumber_freshVar_9= tomMatch160NameNumber_freshVar_8.getpattern() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch160NameNumber_freshVar_10= tomMatch160NameNumber_freshVar_8.getsubject() ;if ( (tomMatch160NameNumber_freshVar_9 instanceof tom.engine.adt.tomterm.types.tomterm.ExpressionToTomTerm) ) {{  tom.engine.adt.tomexpression.types.Expression  tomMatch160NameNumber_freshVar_11= tomMatch160NameNumber_freshVar_9.getAstExpression() ;{  tom.engine.adt.tomexpression.types.Expression  tom_getHead=tomMatch160NameNumber_freshVar_11;{  tom.engine.adt.tomterm.types.TomTerm  tom_e=tomMatch160NameNumber_freshVar_9;{  tom.engine.adt.tomterm.types.TomTerm  tom_t=tomMatch160NameNumber_freshVar_10;if ( true ) {{if ( (tom_getHead instanceof tom.engine.adt.tomexpression.types.Expression) ) {{  tom.engine.adt.tomexpression.types.Expression  tomMatch161NameNumberfreshSubject_1=(( tom.engine.adt.tomexpression.types.Expression )tom_getHead);if ( (tomMatch161NameNumberfreshSubject_1 instanceof tom.engine.adt.tomexpression.types.expression.GetHead) ) {{  tom.engine.adt.tomtype.types.TomType  tomMatch161NameNumber_freshVar_0= tomMatch161NameNumberfreshSubject_1.getCodomain() ;if ( true ) {




            return  tom.engine.adt.tomexpression.types.expression.EqualTerm.make(tomMatch161NameNumber_freshVar_0, tom_e, tom_t) ;
          }}}}}if ( (tom_getHead instanceof tom.engine.adt.tomexpression.types.Expression) ) {{  tom.engine.adt.tomexpression.types.Expression  tomMatch161NameNumberfreshSubject_1=(( tom.engine.adt.tomexpression.types.Expression )tom_getHead);if ( (tomMatch161NameNumberfreshSubject_1 instanceof tom.engine.adt.tomexpression.types.expression.Conditional) ) {{  tom.engine.adt.tomexpression.types.Expression  tomMatch161NameNumber_freshVar_1= tomMatch161NameNumberfreshSubject_1.getThen() ;if ( (tomMatch161NameNumber_freshVar_1 instanceof tom.engine.adt.tomexpression.types.expression.GetHead) ) {{  tom.engine.adt.tomtype.types.TomType  tomMatch161NameNumber_freshVar_2= tomMatch161NameNumber_freshVar_1.getCodomain() ;if ( true ) {

            return  tom.engine.adt.tomexpression.types.expression.EqualTerm.make(tomMatch161NameNumber_freshVar_2, tom_e, tom_t) ;
          }}}}}}}}

      }}}}}}}}}}}}}}return super.visit_Expression(tom__arg,introspector); }public  tom.engine.adt.tomterm.types.TomTerm  visit_TomTerm( tom.engine.adt.tomterm.types.TomTerm  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{if ( (tom__arg instanceof tom.engine.adt.tomterm.types.TomTerm) ) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch162NameNumberfreshSubject_1=(( tom.engine.adt.tomterm.types.TomTerm )tom__arg);if ( (tomMatch162NameNumberfreshSubject_1 instanceof tom.engine.adt.tomterm.types.tomterm.ListHead) ) {{  tom.engine.adt.tomname.types.TomName  tomMatch162NameNumber_freshVar_0= tomMatch162NameNumberfreshSubject_1.getOpname() ;{  tom.engine.adt.tomtype.types.TomType  tomMatch162NameNumber_freshVar_1= tomMatch162NameNumberfreshSubject_1.getCodomain() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch162NameNumber_freshVar_2= tomMatch162NameNumberfreshSubject_1.getVariable() ;if ( true ) {




        return  tom.engine.adt.tomterm.types.tomterm.ExpressionToTomTerm.make(genGetHead(tomMatch162NameNumber_freshVar_0,tomMatch162NameNumber_freshVar_1,tomMatch162NameNumber_freshVar_2)) ;
      }}}}}}}if ( (tom__arg instanceof tom.engine.adt.tomterm.types.TomTerm) ) {{  tom.engine.adt.tomterm.types.TomTerm  tomMatch162NameNumberfreshSubject_1=(( tom.engine.adt.tomterm.types.TomTerm )tom__arg);if ( (tomMatch162NameNumberfreshSubject_1 instanceof tom.engine.adt.tomterm.types.tomterm.ListTail) ) {{  tom.engine.adt.tomname.types.TomName  tomMatch162NameNumber_freshVar_3= tomMatch162NameNumberfreshSubject_1.getOpname() ;{  tom.engine.adt.tomterm.types.TomTerm  tomMatch162NameNumber_freshVar_4= tomMatch162NameNumberfreshSubject_1.getVariable() ;if ( true ) {


        return  tom.engine.adt.tomterm.types.tomterm.ExpressionToTomTerm.make(genGetTail(tomMatch162NameNumber_freshVar_3,tomMatch162NameNumber_freshVar_4)) ;
      }}}}}}}return super.visit_TomTerm(tom__arg,introspector); }}private static  tom.library.sl.Strategy  tom_make_Generator() { return new Generator(); }

 // end strategy	
  
  /**
   * return the head of the list
   * when domain=codomain, the test is extended to:
   *   is_fsym_f(t)?get_head(t):t 
   *   the element itself is returned when it is not a list operator
   *   this occurs because the last element of a loop may not be a list
   */ 
  private static Expression genGetHead(TomName opName, TomType type, TomTerm var) {
    TomSymbol tomSymbol = Compiler.getSymbolTable().getSymbolFromName(((Name)opName).getString());
    TomType domain = TomBase.getSymbolDomain(tomSymbol).getHeadconcTomType();
    TomType codomain = TomBase.getSymbolCodomain(tomSymbol);
    if(domain==codomain) {
      return  tom.engine.adt.tomexpression.types.expression.Conditional.make( tom.engine.adt.tomexpression.types.expression.IsFsym.make(opName, var) ,  tom.engine.adt.tomexpression.types.expression.GetHead.make(opName, type, var) ,  tom.engine.adt.tomexpression.types.expression.TomTermToExpression.make(var) ) ;
    }
    return  tom.engine.adt.tomexpression.types.expression.GetHead.make(opName, type, var) ;
  }

  /**
   * return the tail of the list
   * when domain=codomain, the test is extended to:
   *   is_fsym_f(t)?get_tail(t):make_empty() 
   *   the neutral element is returned when it is not a list operator
   *   this occurs because the last element of a loop may not be a list
   */ 
  private static Expression genGetTail(TomName opName, TomTerm var) {
    TomSymbol tomSymbol = Compiler.getSymbolTable().getSymbolFromName(((Name)opName).getString());
    TomType domain = TomBase.getSymbolDomain(tomSymbol).getHeadconcTomType();
    TomType codomain = TomBase.getSymbolCodomain(tomSymbol);
    if(domain==codomain) {
      return  tom.engine.adt.tomexpression.types.expression.Conditional.make( tom.engine.adt.tomexpression.types.expression.IsFsym.make(opName, var) ,  tom.engine.adt.tomexpression.types.expression.GetTail.make(opName, var) ,  tom.engine.adt.tomexpression.types.expression.TomTermToExpression.make( tom.engine.adt.tomterm.types.tomterm.BuildEmptyList.make(opName) ) ) ;
    }
    return  tom.engine.adt.tomexpression.types.expression.GetTail.make(opName, var) ;
  }
}
