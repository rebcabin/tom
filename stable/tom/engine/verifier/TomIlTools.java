/*
 *
 * TOM - To One Matching Compiler
 *
 * Copyright (c) 2000-2009, INRIA
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
 * Pierre-Etienne Moreau  e-mail: Pierre-Etienne.Moreau@loria.fr
 * Antoine Reilles        e-mail: Antoine.Reilles@loria.fr
 *
 **/

package tom.engine.verifier;

import tom.engine.*;
import java.util.*;

import tom.engine.TomBase;
import tom.engine.exception.TomRuntimeException;

import tom.engine.adt.tomsignature.*;
import tom.engine.adt.tomconstraint.types.*;
import tom.engine.adt.tomdeclaration.types.*;
import tom.engine.adt.tomexpression.types.*;
import tom.engine.adt.tominstruction.types.*;
import tom.engine.adt.tomname.types.*;
import tom.engine.adt.tomoption.types.*;
import tom.engine.adt.tomsignature.types.*;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomslot.types.*;
import tom.engine.adt.tomtype.types.*;
import tom.engine.tools.ASTFactory;
import tom.library.sl.*;

import tom.engine.adt.zenon.types.*;

import tom.engine.tools.SymbolTable;
import tom.engine.exception.TomRuntimeException;

public class TomIlTools {

  // ------------------------------------------------------------
        private static   tom.engine.adt.tomname.types.TomNameList  tom_append_list_concTomName( tom.engine.adt.tomname.types.TomNameList l1,  tom.engine.adt.tomname.types.TomNameList  l2) {     if( l1.isEmptyconcTomName() ) {       return l2;     } else if( l2.isEmptyconcTomName() ) {       return l1;     } else if(  l1.getTailconcTomName() .isEmptyconcTomName() ) {       return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( l1.getHeadconcTomName() ,l2) ;     } else {       return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( l1.getHeadconcTomName() ,tom_append_list_concTomName( l1.getTailconcTomName() ,l2)) ;     }   }   private static   tom.engine.adt.tomname.types.TomNameList  tom_get_slice_concTomName( tom.engine.adt.tomname.types.TomNameList  begin,  tom.engine.adt.tomname.types.TomNameList  end, tom.engine.adt.tomname.types.TomNameList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTomName()  ||  (end== tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( begin.getHeadconcTomName() ,( tom.engine.adt.tomname.types.TomNameList )tom_get_slice_concTomName( begin.getTailconcTomName() ,end,tail)) ;   }      private static   tom.engine.adt.tomoption.types.OptionList  tom_append_list_concOption( tom.engine.adt.tomoption.types.OptionList l1,  tom.engine.adt.tomoption.types.OptionList  l2) {     if( l1.isEmptyconcOption() ) {       return l2;     } else if( l2.isEmptyconcOption() ) {       return l1;     } else if(  l1.getTailconcOption() .isEmptyconcOption() ) {       return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( l1.getHeadconcOption() ,l2) ;     } else {       return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( l1.getHeadconcOption() ,tom_append_list_concOption( l1.getTailconcOption() ,l2)) ;     }   }   private static   tom.engine.adt.tomoption.types.OptionList  tom_get_slice_concOption( tom.engine.adt.tomoption.types.OptionList  begin,  tom.engine.adt.tomoption.types.OptionList  end, tom.engine.adt.tomoption.types.OptionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcOption()  ||  (end== tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( begin.getHeadconcOption() ,( tom.engine.adt.tomoption.types.OptionList )tom_get_slice_concOption( begin.getTailconcOption() ,end,tail)) ;   }      private static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_append_list_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList l1,  tom.engine.adt.tomconstraint.types.ConstraintList  l2) {     if( l1.isEmptyconcConstraint() ) {       return l2;     } else if( l2.isEmptyconcConstraint() ) {       return l1;     } else if(  l1.getTailconcConstraint() .isEmptyconcConstraint() ) {       return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,l2) ;     } else {       return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,tom_append_list_concConstraint( l1.getTailconcConstraint() ,l2)) ;     }   }   private static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slice_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList  begin,  tom.engine.adt.tomconstraint.types.ConstraintList  end, tom.engine.adt.tomconstraint.types.ConstraintList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcConstraint()  ||  (end== tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( begin.getHeadconcConstraint() ,( tom.engine.adt.tomconstraint.types.ConstraintList )tom_get_slice_concConstraint( begin.getTailconcConstraint() ,end,tail)) ;   }      private static   tom.engine.adt.tomslot.types.PairNameDeclList  tom_append_list_concPairNameDecl( tom.engine.adt.tomslot.types.PairNameDeclList l1,  tom.engine.adt.tomslot.types.PairNameDeclList  l2) {     if( l1.isEmptyconcPairNameDecl() ) {       return l2;     } else if( l2.isEmptyconcPairNameDecl() ) {       return l1;     } else if(  l1.getTailconcPairNameDecl() .isEmptyconcPairNameDecl() ) {       return  tom.engine.adt.tomslot.types.pairnamedecllist.ConsconcPairNameDecl.make( l1.getHeadconcPairNameDecl() ,l2) ;     } else {       return  tom.engine.adt.tomslot.types.pairnamedecllist.ConsconcPairNameDecl.make( l1.getHeadconcPairNameDecl() ,tom_append_list_concPairNameDecl( l1.getTailconcPairNameDecl() ,l2)) ;     }   }   private static   tom.engine.adt.tomslot.types.PairNameDeclList  tom_get_slice_concPairNameDecl( tom.engine.adt.tomslot.types.PairNameDeclList  begin,  tom.engine.adt.tomslot.types.PairNameDeclList  end, tom.engine.adt.tomslot.types.PairNameDeclList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcPairNameDecl()  ||  (end== tom.engine.adt.tomslot.types.pairnamedecllist.EmptyconcPairNameDecl.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomslot.types.pairnamedecllist.ConsconcPairNameDecl.make( begin.getHeadconcPairNameDecl() ,( tom.engine.adt.tomslot.types.PairNameDeclList )tom_get_slice_concPairNameDecl( begin.getTailconcPairNameDecl() ,end,tail)) ;   }       private static   tom.engine.adt.zenon.types.ZTermList  tom_append_list_concZTerm( tom.engine.adt.zenon.types.ZTermList l1,  tom.engine.adt.zenon.types.ZTermList  l2) {     if( l1.isEmptyconcZTerm() ) {       return l2;     } else if( l2.isEmptyconcZTerm() ) {       return l1;     } else if(  l1.getTailconcZTerm() .isEmptyconcZTerm() ) {       return  tom.engine.adt.zenon.types.ztermlist.ConsconcZTerm.make( l1.getHeadconcZTerm() ,l2) ;     } else {       return  tom.engine.adt.zenon.types.ztermlist.ConsconcZTerm.make( l1.getHeadconcZTerm() ,tom_append_list_concZTerm( l1.getTailconcZTerm() ,l2)) ;     }   }   private static   tom.engine.adt.zenon.types.ZTermList  tom_get_slice_concZTerm( tom.engine.adt.zenon.types.ZTermList  begin,  tom.engine.adt.zenon.types.ZTermList  end, tom.engine.adt.zenon.types.ZTermList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcZTerm()  ||  (end== tom.engine.adt.zenon.types.ztermlist.EmptyconcZTerm.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.zenon.types.ztermlist.ConsconcZTerm.make( begin.getHeadconcZTerm() ,( tom.engine.adt.zenon.types.ZTermList )tom_get_slice_concZTerm( begin.getTailconcZTerm() ,end,tail)) ;   }      private static   tom.engine.adt.zenon.types.ZAxiomList  tom_append_list_zby( tom.engine.adt.zenon.types.ZAxiomList l1,  tom.engine.adt.zenon.types.ZAxiomList  l2) {     if( l1.isEmptyzby() ) {       return l2;     } else if( l2.isEmptyzby() ) {       return l1;     } else if(  l1.getTailzby() .isEmptyzby() ) {       return  tom.engine.adt.zenon.types.zaxiomlist.Conszby.make( l1.getHeadzby() ,l2) ;     } else {       return  tom.engine.adt.zenon.types.zaxiomlist.Conszby.make( l1.getHeadzby() ,tom_append_list_zby( l1.getTailzby() ,l2)) ;     }   }   private static   tom.engine.adt.zenon.types.ZAxiomList  tom_get_slice_zby( tom.engine.adt.zenon.types.ZAxiomList  begin,  tom.engine.adt.zenon.types.ZAxiomList  end, tom.engine.adt.zenon.types.ZAxiomList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyzby()  ||  (end== tom.engine.adt.zenon.types.zaxiomlist.Emptyzby.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.zenon.types.zaxiomlist.Conszby.make( begin.getHeadzby() ,( tom.engine.adt.zenon.types.ZAxiomList )tom_get_slice_zby( begin.getTailzby() ,end,tail)) ;   }         private static   tom.library.sl.Strategy  tom_append_list_Sequence( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 == null )) {       return l2;     } else if(( l2 == null )) {       return l1;     } else if(( (l1 instanceof tom.library.sl.Sequence) )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ) == null )) {         return ( (l2==null)?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):new tom.library.sl.Sequence(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ),l2) );       } else {         return ( (tom_append_list_Sequence(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ),l2)==null)?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):new tom.library.sl.Sequence(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ),tom_append_list_Sequence(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ),l2)) );       }     } else {       return ( (l2==null)?l1:new tom.library.sl.Sequence(l1,l2) );     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals(( null ))) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return ( (( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)==null)?((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin):new tom.library.sl.Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)) );   }       private static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ),( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )==null)?v:new tom.library.sl.Sequence(v,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )) )) ) );}      














  // ------------------------------------------------------------

  private SymbolTable symbolTable;
  private Verifier verifier;


  public TomIlTools(Verifier verifier) {
    super();
    this.verifier = verifier;
    this.symbolTable = verifier.getSymbolTable();
  }

  private SymbolTable getSymbolTable() {
    return symbolTable;
  }

  /**
   * Methods used to translate a pattern and conditions in zenon signature
   */
  public ZExpr constraintToZExpr(ConstraintList constraintList, Map<String,ZTerm> map) {
    // do everything match the empty pattern ?
    ZExpr result =  tom.engine.adt.zenon.types.zexpr.zfalse.make() ;
    while(!constraintList.isEmptyconcConstraint()) {
      Constraint h = constraintList.getHeadconcConstraint();
      result =  tom.engine.adt.zenon.types.zexpr.zor.make(result, constraintToZExpr(h,map)) ;
      constraintList = constraintList.getTailconcConstraint();
    }
    return result;
  }

  public void getZTermSubjectListFromConstraint(Constraint constraint, List<ZTerm> list, Map<String,ZTerm> map) {
    Set<ZTerm> unamedVarSet = new HashSet<ZTerm>();
    try{
      ArrayList<TomTerm> tmpList = new ArrayList<TomTerm>();
      tom_make_TopDown(tom_make_CollectSubjects(tmpList)).visitLight(constraint);
      for(TomTerm t:tmpList){
        list.add(tomTermToZTerm(t,map,unamedVarSet));
      }
    } catch(VisitFailure e) {
      throw new TomRuntimeException("VisiFailure in TomIlTools.getZTermSubjectListFromConstraint: " + e.getMessage());
    }
  }
  
  public static class CollectSubjects extends tom.library.sl.AbstractStrategyBasic {private  java.util.List<TomTerm>  list;public CollectSubjects( java.util.List<TomTerm>  list) {super(( new tom.library.sl.Identity() ));this.list=list;}public  java.util.List<TomTerm>  getlist() {return list;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public  tom.engine.adt.tomconstraint.types.Constraint  visit_Constraint( tom.engine.adt.tomconstraint.types.Constraint  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (tom__arg instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {if ( ((( tom.engine.adt.tomconstraint.types.Constraint )tom__arg) instanceof tom.engine.adt.tomconstraint.types.constraint.MatchConstraint) ) {


        list.add( (( tom.engine.adt.tomconstraint.types.Constraint )tom__arg).getSubject() );
      }}}}return _visit_Constraint(tom__arg,introspector); }@SuppressWarnings("unchecked")public  tom.engine.adt.tomconstraint.types.Constraint  _visit_Constraint( tom.engine.adt.tomconstraint.types.Constraint  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.engine.adt.tomconstraint.types.Constraint )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);} }@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {return ((T)visit_Constraint((( tom.engine.adt.tomconstraint.types.Constraint )v),introspector));}if (!((environment ==  null ))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);} }}private static  tom.library.sl.Strategy  tom_make_CollectSubjects( java.util.List<TomTerm>  t0) { return new CollectSubjects(t0);}



  public ZExpr constraintToZExpr(Constraint constraint, Map<String,ZTerm> map) {
    Set<ZTerm> unamedVariableSet = new HashSet<ZTerm>();
    ArrayList<TomTerm> subjectList = new ArrayList<TomTerm>();
    ArrayList<TomTerm> patternList = new ArrayList<TomTerm>();
    try{
      tom_make_TopDown(tom_make_CollectSubjectsAndPatterns(subjectList,patternList)).visitLight(constraint);
    } catch(VisitFailure e) {
      throw new TomRuntimeException("VisiFailure in TomIlTools.constraintToZExpr: " + e.getMessage());
    }
    ZExpr result = constraintToZExpr(ASTFactory.makeList(subjectList),ASTFactory.makeList(patternList),map,unamedVariableSet);
    // insert existential quantifiers for the unamed variables
    for (ZTerm var : unamedVariableSet) {
      result =  tom.engine.adt.zenon.types.zexpr.zexists.make(var,  tom.engine.adt.zenon.types.ztype.ztype.make("T") , result) ;
    }
    return result;

  }
  
  public static class CollectSubjectsAndPatterns extends tom.library.sl.AbstractStrategyBasic {private  java.util.List<TomTerm>  subjectlist;private  java.util.List<TomTerm>  patternList;public CollectSubjectsAndPatterns( java.util.List<TomTerm>  subjectlist,  java.util.List<TomTerm>  patternList) {super(( new tom.library.sl.Identity() ));this.subjectlist=subjectlist;this.patternList=patternList;}public  java.util.List<TomTerm>  getsubjectlist() {return subjectlist;}public  java.util.List<TomTerm>  getpatternList() {return patternList;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public  tom.engine.adt.tomconstraint.types.Constraint  visit_Constraint( tom.engine.adt.tomconstraint.types.Constraint  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (tom__arg instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {if ( ((( tom.engine.adt.tomconstraint.types.Constraint )tom__arg) instanceof tom.engine.adt.tomconstraint.types.constraint.MatchConstraint) ) {


        subjectlist.add( (( tom.engine.adt.tomconstraint.types.Constraint )tom__arg).getSubject() );
        patternList.add( (( tom.engine.adt.tomconstraint.types.Constraint )tom__arg).getPattern() );
      }}}}return _visit_Constraint(tom__arg,introspector); }@SuppressWarnings("unchecked")public  tom.engine.adt.tomconstraint.types.Constraint  _visit_Constraint( tom.engine.adt.tomconstraint.types.Constraint  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.engine.adt.tomconstraint.types.Constraint )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);} }@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {return ((T)visit_Constraint((( tom.engine.adt.tomconstraint.types.Constraint )v),introspector));}if (!((environment ==  null ))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);} }}private static  tom.library.sl.Strategy  tom_make_CollectSubjectsAndPatterns( java.util.List<TomTerm>  t0,  java.util.List<TomTerm>  t1) { return new CollectSubjectsAndPatterns(t0,t1);}



  public ZExpr constraintToZExpr(TomList subjectList, TomList tomList, Map<String,ZTerm> map, Set<ZTerm> unamedVariableSet) {
    /* for each TomTerm: builds a zeq : pattern = subject */
    ZExpr res =  tom.engine.adt.zenon.types.zexpr.ztrue.make() ;
    while(!tomList.isEmptyconcTomTerm()) {
      TomTerm h = tomList.getHeadconcTomTerm();
      TomTerm subject = subjectList.getHeadconcTomTerm();
      tomList = tomList.getTailconcTomTerm();
      subjectList = subjectList.getTailconcTomTerm();
      res =  tom.engine.adt.zenon.types.zexpr.zand.make(res,  tom.engine.adt.zenon.types.zexpr.zeq.make(tomTermToZTerm(h,map,unamedVariableSet), tomTermToZTerm(subject,map,unamedVariableSet)) ) 
;
    }
    return res;
  }

  public ZTerm tomTermToZTerm(TomTerm tomTerm, Map<String,ZTerm> map, Set<ZTerm> unamedVariableSet) {
    {{if ( (tomTerm instanceof tom.engine.adt.tomterm.types.TomTerm) ) {if ( ((( tom.engine.adt.tomterm.types.TomTerm )tomTerm) instanceof tom.engine.adt.tomterm.types.tomterm.TermAppl) ) { tom.engine.adt.tomname.types.TomNameList  tomMatch288NameNumber_freshVar_1= (( tom.engine.adt.tomterm.types.TomTerm )tomTerm).getNameList() ;if ( ((tomMatch288NameNumber_freshVar_1 instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || (tomMatch288NameNumber_freshVar_1 instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {if (!( tomMatch288NameNumber_freshVar_1.isEmptyconcTomName() )) { tom.engine.adt.tomname.types.TomName  tomMatch288NameNumber_freshVar_8= tomMatch288NameNumber_freshVar_1.getHeadconcTomName() ;if ( (tomMatch288NameNumber_freshVar_8 instanceof tom.engine.adt.tomname.types.tomname.Name) ) { tom.engine.adt.tomterm.types.TomList  tom_childrens= (( tom.engine.adt.tomterm.types.TomTerm )tomTerm).getArgs() ;

        // builds children list
        ZTermList zchild =  tom.engine.adt.zenon.types.ztermlist.EmptyconcZTerm.make() ;
        TomTerm hd = null;
        while (!tom_childrens.isEmptyconcTomTerm()) {
          hd = tom_childrens.getHeadconcTomTerm();
          tom_childrens= tom_childrens.getTailconcTomTerm();
          zchild = tom_append_list_concZTerm(zchild, tom.engine.adt.zenon.types.ztermlist.ConsconcZTerm.make(tomTermToZTerm(hd,map,unamedVariableSet), tom.engine.adt.zenon.types.ztermlist.EmptyconcZTerm.make() ) );
        }
        // issue a warning here: this case is probably impossible
        return  tom.engine.adt.zenon.types.zterm.zappl.make( tom.engine.adt.zenon.types.zsymbol.zsymbol.make( tomMatch288NameNumber_freshVar_8.getString() ) , zchild) ;
      }}}}}}{if ( (tomTerm instanceof tom.engine.adt.tomterm.types.TomTerm) ) {if ( ((( tom.engine.adt.tomterm.types.TomTerm )tomTerm) instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) { tom.engine.adt.tomname.types.TomNameList  tomMatch288NameNumber_freshVar_10= (( tom.engine.adt.tomterm.types.TomTerm )tomTerm).getNameList() ;if ( ((tomMatch288NameNumber_freshVar_10 instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || (tomMatch288NameNumber_freshVar_10 instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {if (!( tomMatch288NameNumber_freshVar_10.isEmptyconcTomName() )) { tom.engine.adt.tomname.types.TomName  tomMatch288NameNumber_freshVar_17= tomMatch288NameNumber_freshVar_10.getHeadconcTomName() ;if ( (tomMatch288NameNumber_freshVar_17 instanceof tom.engine.adt.tomname.types.tomname.Name) ) { String  tom_name= tomMatch288NameNumber_freshVar_17.getString() ; tom.engine.adt.tomslot.types.SlotList  tom_childrens= (( tom.engine.adt.tomterm.types.TomTerm )tomTerm).getSlots() ;

        // builds a map: slotName / TomTerm
        Map<TomName,TomTerm> definedSlotMap = new HashMap<TomName,TomTerm>();
        Slot hd = null;
        while (!tom_childrens.isEmptyconcSlot()) {
          hd = tom_childrens.getHeadconcSlot();
          tom_childrens= tom_childrens.getTailconcSlot();
          definedSlotMap.put(hd.getSlotName(),hd.getAppl());
        }
        // builds children list
        ZTermList zchild =  tom.engine.adt.zenon.types.ztermlist.EmptyconcZTerm.make() ;
        // take care to add unamedVariables for wildcards
        TomSymbol symbol = TomBase.getSymbolFromName(tom_name,getSymbolTable());
        // process all slots from symbol
        {{if ( (symbol instanceof tom.engine.adt.tomsignature.types.TomSymbol) ) {if ( ((( tom.engine.adt.tomsignature.types.TomSymbol )symbol) instanceof tom.engine.adt.tomsignature.types.tomsymbol.Symbol) ) { tom.engine.adt.tomslot.types.PairNameDeclList  tom_slots= (( tom.engine.adt.tomsignature.types.TomSymbol )symbol).getPairNameDeclList() ;

            // process all slots. If the slot is in childrens, use it
            while(!tom_slots.isEmptyconcPairNameDecl()) {
              Declaration decl= tom_slots.getHeadconcPairNameDecl().getSlotDecl();
              tom_slots= tom_slots.getTailconcPairNameDecl();
              {{if ( (decl instanceof tom.engine.adt.tomdeclaration.types.Declaration) ) {if ( ((( tom.engine.adt.tomdeclaration.types.Declaration )decl) instanceof tom.engine.adt.tomdeclaration.types.declaration.GetSlotDecl) ) { tom.engine.adt.tomname.types.TomName  tom_slotName= (( tom.engine.adt.tomdeclaration.types.Declaration )decl).getSlotName() ;

                  if (definedSlotMap.containsKey(tom_slotName)) {
                    zchild = tom_append_list_concZTerm(zchild, tom.engine.adt.zenon.types.ztermlist.ConsconcZTerm.make(tomTermToZTerm(definedSlotMap.get(tom_slotName),map,unamedVariableSet), tom.engine.adt.zenon.types.ztermlist.EmptyconcZTerm.make() ) );
                  }
                  else {
                    // fake an UnamedVariable
                    zchild = tom_append_list_concZTerm(zchild, tom.engine.adt.zenon.types.ztermlist.ConsconcZTerm.make(tomTermToZTerm( tom.engine.adt.tomterm.types.tomterm.UnamedVariable.make( tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() ,  tom.engine.adt.tomtype.types.tomtype.EmptyType.make() ,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) ,map,unamedVariableSet), tom.engine.adt.zenon.types.ztermlist.EmptyconcZTerm.make() ) )

;
                  }
                }}}}

            }
          }}}}

        return  tom.engine.adt.zenon.types.zterm.zappl.make( tom.engine.adt.zenon.types.zsymbol.zsymbol.make(tom_name) , zchild) ;
      }}}}}}{if ( (tomTerm instanceof tom.engine.adt.tomterm.types.TomTerm) ) {if ( ((( tom.engine.adt.tomterm.types.TomTerm )tomTerm) instanceof tom.engine.adt.tomterm.types.tomterm.Variable) ) { tom.engine.adt.tomname.types.TomName  tomMatch288NameNumber_freshVar_19= (( tom.engine.adt.tomterm.types.TomTerm )tomTerm).getAstName() ;if ( (tomMatch288NameNumber_freshVar_19 instanceof tom.engine.adt.tomname.types.tomname.Name) ) { String  tom_name= tomMatch288NameNumber_freshVar_19.getString() ;

        if (map.containsKey(tom_name)) {
          return map.get(tom_name);
        } else {
          System.out.println("tomTermToZTerm 1 Not in map: " + tom_name+ " map: " + map);
          return  tom.engine.adt.zenon.types.zterm.zvar.make(tom_name) ;
        }
      }}}}{if ( (tomTerm instanceof tom.engine.adt.tomterm.types.TomTerm) ) {if ( ((( tom.engine.adt.tomterm.types.TomTerm )tomTerm) instanceof tom.engine.adt.tomterm.types.tomterm.Variable) ) { tom.engine.adt.tomname.types.TomName  tomMatch288NameNumber_freshVar_24= (( tom.engine.adt.tomterm.types.TomTerm )tomTerm).getAstName() ;if ( (tomMatch288NameNumber_freshVar_24 instanceof tom.engine.adt.tomname.types.tomname.PositionName) ) {

        String name = TomBase.tomNumberListToString( tomMatch288NameNumber_freshVar_24.getNumberList() );
        if (map.containsKey(name)) {
          return map.get(name);
        } else {
          System.out.println("tomTermToZTerm 2 Not in map: " + name + " map: " + map);
          return  tom.engine.adt.zenon.types.zterm.zvar.make(name) ;
        }
      }}}}{if ( (tomTerm instanceof tom.engine.adt.tomterm.types.TomTerm) ) {if ( ((( tom.engine.adt.tomterm.types.TomTerm )tomTerm) instanceof tom.engine.adt.tomterm.types.tomterm.UnamedVariable) ) {

        // for unamed variables in a pattern, we generate an existential
        // quantifier for a dummy name
        ZTerm unamedVariable =  tom.engine.adt.zenon.types.zterm.zvar.make(replaceNumbersByString("unamedVariable"+unamedVariableSet.size())) ;
        unamedVariableSet.add(unamedVariable);
        return unamedVariable;
      }}}}

    throw new TomRuntimeException("tomTermToZTerm Strange pattern: " + tomTerm);
  }

  public static class collect_symbols extends tom.library.sl.AbstractStrategyBasic {private  java.util.Collection<String>  store;public collect_symbols( java.util.Collection<String>  store) {super(( new tom.library.sl.Identity() ));this.store=store;}public  java.util.Collection<String>  getstore() {return store;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public  tom.engine.adt.zenon.types.ZSymbol  visit_ZSymbol( tom.engine.adt.zenon.types.ZSymbol  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (tom__arg instanceof tom.engine.adt.zenon.types.ZSymbol) ) {if ( ((( tom.engine.adt.zenon.types.ZSymbol )tom__arg) instanceof tom.engine.adt.zenon.types.zsymbol.zsymbol) ) {


        store.add( (( tom.engine.adt.zenon.types.ZSymbol )tom__arg).getName() );
      }}}}return _visit_ZSymbol(tom__arg,introspector); }@SuppressWarnings("unchecked")public  tom.engine.adt.zenon.types.ZSymbol  _visit_ZSymbol( tom.engine.adt.zenon.types.ZSymbol  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!((environment ==  null ))) {return (( tom.engine.adt.zenon.types.ZSymbol )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);} }@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.zenon.types.ZSymbol) ) {return ((T)visit_ZSymbol((( tom.engine.adt.zenon.types.ZSymbol )v),introspector));}if (!((environment ==  null ))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);} }}private static  tom.library.sl.Strategy  tom_make_collect_symbols( java.util.Collection<String>  t0) { return new collect_symbols(t0);}



  public Collection<String> collectSymbols(ZExpr subject) {
    Collection<String> result = new HashSet<String>();
    try {
      tom_make_TopDown(tom_make_collect_symbols(result)).visitLight(subject);
    } catch (tom.library.sl.VisitFailure e) {
      throw new TomRuntimeException("Strategy collect_symbols failed");
    }
    return result;
  }
  public Collection<String> collectSymbolsFromZSpec(ZSpec subject) {
    Collection<String> result = new HashSet<String>();
    try {
      tom_make_TopDown(tom_make_collect_symbols(result)).visitLight(subject);
    } catch (tom.library.sl.VisitFailure e) {
      throw new TomRuntimeException("Strategy collect_symbols failed");
    }
    return result;
  }

  public ZAxiomList symbolsDefinition(Collection<String> symbolnames) {
    ZAxiomList res =  tom.engine.adt.zenon.types.zaxiomlist.Emptyzby.make() ;
    for (String name : symbolnames) {
      TomSymbol symbol = TomBase.getSymbolFromName(name,getSymbolTable());
      ZTermList list =  tom.engine.adt.zenon.types.ztermlist.EmptyconcZTerm.make() ;
      ZTerm abstractVariable =  tom.engine.adt.zenon.types.zterm.zvar.make("t") ;
      //ZExpr exists = null;
      {{if ( (symbol instanceof tom.engine.adt.tomsignature.types.TomSymbol) ) {if ( ((( tom.engine.adt.tomsignature.types.TomSymbol )symbol) instanceof tom.engine.adt.tomsignature.types.tomsymbol.Symbol) ) { tom.engine.adt.tomslot.types.PairNameDeclList  tom_slots= (( tom.engine.adt.tomsignature.types.TomSymbol )symbol).getPairNameDeclList() ;

          // process all slots
          while(!tom_slots.isEmptyconcPairNameDecl()) {
            Declaration hd= tom_slots.getHeadconcPairNameDecl().getSlotDecl();
            tom_slots= tom_slots.getTailconcPairNameDecl();
            {{if ( (hd instanceof tom.engine.adt.tomdeclaration.types.Declaration) ) {if ( ((( tom.engine.adt.tomdeclaration.types.Declaration )hd) instanceof tom.engine.adt.tomdeclaration.types.declaration.GetSlotDecl) ) { tom.engine.adt.tomname.types.TomName  tomMatch293NameNumber_freshVar_1= (( tom.engine.adt.tomdeclaration.types.Declaration )hd).getSlotName() ;if ( (tomMatch293NameNumber_freshVar_1 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {

                list = tom_append_list_concZTerm(list, tom.engine.adt.zenon.types.ztermlist.ConsconcZTerm.make( tom.engine.adt.zenon.types.zterm.zsl.make(abstractVariable,  tomMatch293NameNumber_freshVar_1.getString() ) , tom.engine.adt.zenon.types.ztermlist.EmptyconcZTerm.make() ) );
              }}}}}

          }
        }}}}


      ZExpr axiom =  tom.engine.adt.zenon.types.zexpr.zforall.make(abstractVariable,  tom.engine.adt.zenon.types.ztype.ztype.make("T") ,  tom.engine.adt.zenon.types.zexpr.zequiv.make( tom.engine.adt.zenon.types.zexpr.zisfsym.make(abstractVariable,  tom.engine.adt.zenon.types.zsymbol.zsymbol.make(name) ) ,  tom.engine.adt.zenon.types.zexpr.zeq.make(abstractVariable,  tom.engine.adt.zenon.types.zterm.zappl.make( tom.engine.adt.zenon.types.zsymbol.zsymbol.make(name) , list) ) ) ) 


;
      res=tom_append_list_zby(res, tom.engine.adt.zenon.types.zaxiomlist.Conszby.make( tom.engine.adt.zenon.types.zaxiom.zaxiom.make("symb_"+replaceNumbersByString(name), axiom) , tom.engine.adt.zenon.types.zaxiomlist.Emptyzby.make() ) );
    }
    return res;
  }

  public ZAxiomList subtermsDefinition(Collection<String> symbolnames) {
    ZAxiomList res =  tom.engine.adt.zenon.types.zaxiomlist.Emptyzby.make() ;
    for (String name : symbolnames) {
      TomSymbol symbol = TomBase.getSymbolFromName(name,getSymbolTable());
      ZTermList list =  tom.engine.adt.zenon.types.ztermlist.EmptyconcZTerm.make() ;
      {{if ( (symbol instanceof tom.engine.adt.tomsignature.types.TomSymbol) ) {if ( ((( tom.engine.adt.tomsignature.types.TomSymbol )symbol) instanceof tom.engine.adt.tomsignature.types.tomsymbol.Symbol) ) { tom.engine.adt.tomslot.types.PairNameDeclList  tom_slots= (( tom.engine.adt.tomsignature.types.TomSymbol )symbol).getPairNameDeclList() ;

          // process all slots
          int slotnumber =tom_slots.length();
          for (int i = 0; i < slotnumber;i++) {
            list = tom_append_list_concZTerm(list, tom.engine.adt.zenon.types.ztermlist.ConsconcZTerm.make( tom.engine.adt.zenon.types.zterm.zvar.make("x"+i) , tom.engine.adt.zenon.types.ztermlist.EmptyconcZTerm.make() ) );
          }
          {{if ( (tom_slots instanceof tom.engine.adt.tomslot.types.PairNameDeclList) ) {if ( (((( tom.engine.adt.tomslot.types.PairNameDeclList )tom_slots) instanceof tom.engine.adt.tomslot.types.pairnamedecllist.ConsconcPairNameDecl) || ((( tom.engine.adt.tomslot.types.PairNameDeclList )tom_slots) instanceof tom.engine.adt.tomslot.types.pairnamedecllist.EmptyconcPairNameDecl)) ) { tom.engine.adt.tomslot.types.PairNameDeclList  tomMatch295NameNumber_end_4=(( tom.engine.adt.tomslot.types.PairNameDeclList )tom_slots);do {{if (!( tomMatch295NameNumber_end_4.isEmptyconcPairNameDecl() )) { tom.engine.adt.tomslot.types.PairNameDecl  tomMatch295NameNumber_freshVar_8= tomMatch295NameNumber_end_4.getHeadconcPairNameDecl() ;if ( (tomMatch295NameNumber_freshVar_8 instanceof tom.engine.adt.tomslot.types.pairnamedecl.PairNameDecl) ) { tom.engine.adt.tomname.types.TomName  tomMatch295NameNumber_freshVar_7= tomMatch295NameNumber_freshVar_8.getSlotName() ;if ( (tomMatch295NameNumber_freshVar_7 instanceof tom.engine.adt.tomname.types.tomname.Name) ) { String  tom_slname= tomMatch295NameNumber_freshVar_7.getString() ;

              int index = tom_get_slice_concPairNameDecl((( tom.engine.adt.tomslot.types.PairNameDeclList )tom_slots),tomMatch295NameNumber_end_4, tom.engine.adt.tomslot.types.pairnamedecllist.EmptyconcPairNameDecl.make() ).length();
              ZExpr axiom =  tom.engine.adt.zenon.types.zexpr.zeq.make( tom.engine.adt.zenon.types.zterm.zvar.make("x"+index) ,  tom.engine.adt.zenon.types.zterm.zsl.make( tom.engine.adt.zenon.types.zterm.zappl.make( tom.engine.adt.zenon.types.zsymbol.zsymbol.make(name) , list) , tom_slname) ) 
;
              for (int j = 0; j < slotnumber;j++) {
                axiom =  tom.engine.adt.zenon.types.zexpr.zforall.make( tom.engine.adt.zenon.types.zterm.zvar.make("x"+j) ,  tom.engine.adt.zenon.types.ztype.ztype.make("T") , axiom) ;
              }
              res=tom_append_list_zby(res, tom.engine.adt.zenon.types.zaxiomlist.Conszby.make( tom.engine.adt.zenon.types.zaxiom.zaxiom.make("st_"+tom_slname+"_"+replaceNumbersByString(name), axiom) , tom.engine.adt.zenon.types.zaxiomlist.Emptyzby.make() ) );
            }}}if ( tomMatch295NameNumber_end_4.isEmptyconcPairNameDecl() ) {tomMatch295NameNumber_end_4=(( tom.engine.adt.tomslot.types.PairNameDeclList )tom_slots);} else {tomMatch295NameNumber_end_4= tomMatch295NameNumber_end_4.getTailconcPairNameDecl() ;}}} while(!( (tomMatch295NameNumber_end_4==(( tom.engine.adt.tomslot.types.PairNameDeclList )tom_slots)) ));}}}}

        }}}}

    }
    return res;
  }

  public List<String> subtermList(String symbolName) {
    List<String> nameList = new LinkedList<String>();

    TomSymbol symbol = TomBase.getSymbolFromName(symbolName,getSymbolTable());

    {{if ( (symbol instanceof tom.engine.adt.tomsignature.types.TomSymbol) ) {if ( ((( tom.engine.adt.tomsignature.types.TomSymbol )symbol) instanceof tom.engine.adt.tomsignature.types.tomsymbol.Symbol) ) { tom.engine.adt.tomslot.types.PairNameDeclList  tom_slots= (( tom.engine.adt.tomsignature.types.TomSymbol )symbol).getPairNameDeclList() ;{{if ( (tom_slots instanceof tom.engine.adt.tomslot.types.PairNameDeclList) ) {if ( (((( tom.engine.adt.tomslot.types.PairNameDeclList )tom_slots) instanceof tom.engine.adt.tomslot.types.pairnamedecllist.ConsconcPairNameDecl) || ((( tom.engine.adt.tomslot.types.PairNameDeclList )tom_slots) instanceof tom.engine.adt.tomslot.types.pairnamedecllist.EmptyconcPairNameDecl)) ) { tom.engine.adt.tomslot.types.PairNameDeclList  tomMatch297NameNumber_end_4=(( tom.engine.adt.tomslot.types.PairNameDeclList )tom_slots);do {{if (!( tomMatch297NameNumber_end_4.isEmptyconcPairNameDecl() )) { tom.engine.adt.tomslot.types.PairNameDecl  tomMatch297NameNumber_freshVar_8= tomMatch297NameNumber_end_4.getHeadconcPairNameDecl() ;if ( (tomMatch297NameNumber_freshVar_8 instanceof tom.engine.adt.tomslot.types.pairnamedecl.PairNameDecl) ) { tom.engine.adt.tomname.types.TomName  tomMatch297NameNumber_freshVar_7= tomMatch297NameNumber_freshVar_8.getSlotName() ;if ( (tomMatch297NameNumber_freshVar_7 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {



            nameList.add( tomMatch297NameNumber_freshVar_7.getString() );
          }}}if ( tomMatch297NameNumber_end_4.isEmptyconcPairNameDecl() ) {tomMatch297NameNumber_end_4=(( tom.engine.adt.tomslot.types.PairNameDeclList )tom_slots);} else {tomMatch297NameNumber_end_4= tomMatch297NameNumber_end_4.getTailconcPairNameDecl() ;}}} while(!( (tomMatch297NameNumber_end_4==(( tom.engine.adt.tomslot.types.PairNameDeclList )tom_slots)) ));}}}}

      }}}}

    return nameList;
  }

  public String replaceNumbersByString(String input) {
    String output = input;
    output = output.replace("0","zero");
    output = output.replace("1","one");
    output = output.replace("2","two");
    output = output.replace("3","three");
    output = output.replace("4","four");
    output = output.replace("5","five");
    output = output.replace("6","six");
    output = output.replace("7","seven");
    output = output.replace("8","eight");
    output = output.replace("9","nine");
    output = output.replace("\\\"","_sd_");
    output = output.replace("True","z_true");
    output = output.replace("False","z_false");
    return output;
  }

}
