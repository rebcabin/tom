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
* Pierre-Etienne Moreau  e-mail: Pierre-Etienne.Moreau@loria.fr
*
**/

package tom.engine;

import java.util.*;
import java.util.logging.Logger;

import aterm.*;

import tom.engine.tools.*;

import tom.engine.adt.tomsignature.*;
import tom.engine.adt.tomconstraint.types.*;
import tom.engine.adt.tomdeclaration.types.*;
import tom.engine.adt.tomexpression.types.*;
import tom.engine.adt.tominstruction.types.*;
import tom.engine.adt.tomname.types.*;
import tom.engine.adt.tomname.types.tomname.*;
import tom.engine.adt.tomoption.types.*;
import tom.engine.adt.tomsignature.types.*;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomslot.types.*;
import tom.engine.adt.tomtype.types.*;
import tom.engine.adt.theory.types.*;
import tom.engine.adt.code.types.*;

import tom.engine.exception.TomRuntimeException;
import tom.engine.TomMessage;

import tom.platform.adt.platformoption.*;

import tom.library.sl.*;
import tom.library.sl.VisitFailure;


/**
* Provides access to the TomSignatureFactory and helper methods.
*/
public final class TomBase {



  private static   tom.engine.adt.tomsignature.types.TomSymbolList  tom_append_list_concTomSymbol( tom.engine.adt.tomsignature.types.TomSymbolList l1,  tom.engine.adt.tomsignature.types.TomSymbolList  l2) {
    if( l1.isEmptyconcTomSymbol() ) {
      return l2;
    } else if( l2.isEmptyconcTomSymbol() ) {
      return l1;
    } else if(  l1.getTailconcTomSymbol() .isEmptyconcTomSymbol() ) {
      return  tom.engine.adt.tomsignature.types.tomsymbollist.ConsconcTomSymbol.make( l1.getHeadconcTomSymbol() ,l2) ;
    } else {
      return  tom.engine.adt.tomsignature.types.tomsymbollist.ConsconcTomSymbol.make( l1.getHeadconcTomSymbol() ,tom_append_list_concTomSymbol( l1.getTailconcTomSymbol() ,l2)) ;
    }
  }
  private static   tom.engine.adt.tomsignature.types.TomSymbolList  tom_get_slice_concTomSymbol( tom.engine.adt.tomsignature.types.TomSymbolList  begin,  tom.engine.adt.tomsignature.types.TomSymbolList  end, tom.engine.adt.tomsignature.types.TomSymbolList  tail) {
    if( (begin==end) ) {
      return tail;
    } else if( (end==tail)  && ( end.isEmptyconcTomSymbol()  ||  (end== tom.engine.adt.tomsignature.types.tomsymbollist.EmptyconcTomSymbol.make() ) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.engine.adt.tomsignature.types.tomsymbollist.ConsconcTomSymbol.make( begin.getHeadconcTomSymbol() ,( tom.engine.adt.tomsignature.types.TomSymbolList )tom_get_slice_concTomSymbol( begin.getTailconcTomSymbol() ,end,tail)) ;
  }
  
  private static   tom.engine.adt.tomterm.types.TomList  tom_append_list_concTomTerm( tom.engine.adt.tomterm.types.TomList l1,  tom.engine.adt.tomterm.types.TomList  l2) {
    if( l1.isEmptyconcTomTerm() ) {
      return l2;
    } else if( l2.isEmptyconcTomTerm() ) {
      return l1;
    } else if(  l1.getTailconcTomTerm() .isEmptyconcTomTerm() ) {
      return  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( l1.getHeadconcTomTerm() ,l2) ;
    } else {
      return  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( l1.getHeadconcTomTerm() ,tom_append_list_concTomTerm( l1.getTailconcTomTerm() ,l2)) ;
    }
  }
  private static   tom.engine.adt.tomterm.types.TomList  tom_get_slice_concTomTerm( tom.engine.adt.tomterm.types.TomList  begin,  tom.engine.adt.tomterm.types.TomList  end, tom.engine.adt.tomterm.types.TomList  tail) {
    if( (begin==end) ) {
      return tail;
    } else if( (end==tail)  && ( end.isEmptyconcTomTerm()  ||  (end== tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() ) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( begin.getHeadconcTomTerm() ,( tom.engine.adt.tomterm.types.TomList )tom_get_slice_concTomTerm( begin.getTailconcTomTerm() ,end,tail)) ;
  }
  
  private static   tom.engine.adt.tomtype.types.TomTypeList  tom_append_list_concTomType( tom.engine.adt.tomtype.types.TomTypeList l1,  tom.engine.adt.tomtype.types.TomTypeList  l2) {
    if( l1.isEmptyconcTomType() ) {
      return l2;
    } else if( l2.isEmptyconcTomType() ) {
      return l1;
    } else if(  l1.getTailconcTomType() .isEmptyconcTomType() ) {
      return  tom.engine.adt.tomtype.types.tomtypelist.ConsconcTomType.make( l1.getHeadconcTomType() ,l2) ;
    } else {
      return  tom.engine.adt.tomtype.types.tomtypelist.ConsconcTomType.make( l1.getHeadconcTomType() ,tom_append_list_concTomType( l1.getTailconcTomType() ,l2)) ;
    }
  }
  private static   tom.engine.adt.tomtype.types.TomTypeList  tom_get_slice_concTomType( tom.engine.adt.tomtype.types.TomTypeList  begin,  tom.engine.adt.tomtype.types.TomTypeList  end, tom.engine.adt.tomtype.types.TomTypeList  tail) {
    if( (begin==end) ) {
      return tail;
    } else if( (end==tail)  && ( end.isEmptyconcTomType()  ||  (end== tom.engine.adt.tomtype.types.tomtypelist.EmptyconcTomType.make() ) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.engine.adt.tomtype.types.tomtypelist.ConsconcTomType.make( begin.getHeadconcTomType() ,( tom.engine.adt.tomtype.types.TomTypeList )tom_get_slice_concTomType( begin.getTailconcTomType() ,end,tail)) ;
  }
  
  private static   tom.engine.adt.code.types.BQTermList  tom_append_list_concBQTerm( tom.engine.adt.code.types.BQTermList l1,  tom.engine.adt.code.types.BQTermList  l2) {
    if( l1.isEmptyconcBQTerm() ) {
      return l2;
    } else if( l2.isEmptyconcBQTerm() ) {
      return l1;
    } else if(  l1.getTailconcBQTerm() .isEmptyconcBQTerm() ) {
      return  tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make( l1.getHeadconcBQTerm() ,l2) ;
    } else {
      return  tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make( l1.getHeadconcBQTerm() ,tom_append_list_concBQTerm( l1.getTailconcBQTerm() ,l2)) ;
    }
  }
  private static   tom.engine.adt.code.types.BQTermList  tom_get_slice_concBQTerm( tom.engine.adt.code.types.BQTermList  begin,  tom.engine.adt.code.types.BQTermList  end, tom.engine.adt.code.types.BQTermList  tail) {
    if( (begin==end) ) {
      return tail;
    } else if( (end==tail)  && ( end.isEmptyconcBQTerm()  ||  (end== tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() ) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make( begin.getHeadconcBQTerm() ,( tom.engine.adt.code.types.BQTermList )tom_get_slice_concBQTerm( begin.getTailconcBQTerm() ,end,tail)) ;
  }
  
  private static   tom.engine.adt.tomname.types.TomNameList  tom_append_list_concTomName( tom.engine.adt.tomname.types.TomNameList l1,  tom.engine.adt.tomname.types.TomNameList  l2) {
    if( l1.isEmptyconcTomName() ) {
      return l2;
    } else if( l2.isEmptyconcTomName() ) {
      return l1;
    } else if(  l1.getTailconcTomName() .isEmptyconcTomName() ) {
      return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( l1.getHeadconcTomName() ,l2) ;
    } else {
      return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( l1.getHeadconcTomName() ,tom_append_list_concTomName( l1.getTailconcTomName() ,l2)) ;
    }
  }
  private static   tom.engine.adt.tomname.types.TomNameList  tom_get_slice_concTomName( tom.engine.adt.tomname.types.TomNameList  begin,  tom.engine.adt.tomname.types.TomNameList  end, tom.engine.adt.tomname.types.TomNameList  tail) {
    if( (begin==end) ) {
      return tail;
    } else if( (end==tail)  && ( end.isEmptyconcTomName()  ||  (end== tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName.make() ) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( begin.getHeadconcTomName() ,( tom.engine.adt.tomname.types.TomNameList )tom_get_slice_concTomName( begin.getTailconcTomName() ,end,tail)) ;
  }
  
  private static   tom.engine.adt.tomname.types.TomNumberList  tom_append_list_concTomNumber( tom.engine.adt.tomname.types.TomNumberList l1,  tom.engine.adt.tomname.types.TomNumberList  l2) {
    if( l1.isEmptyconcTomNumber() ) {
      return l2;
    } else if( l2.isEmptyconcTomNumber() ) {
      return l1;
    } else if(  l1.getTailconcTomNumber() .isEmptyconcTomNumber() ) {
      return  tom.engine.adt.tomname.types.tomnumberlist.ConsconcTomNumber.make( l1.getHeadconcTomNumber() ,l2) ;
    } else {
      return  tom.engine.adt.tomname.types.tomnumberlist.ConsconcTomNumber.make( l1.getHeadconcTomNumber() ,tom_append_list_concTomNumber( l1.getTailconcTomNumber() ,l2)) ;
    }
  }
  private static   tom.engine.adt.tomname.types.TomNumberList  tom_get_slice_concTomNumber( tom.engine.adt.tomname.types.TomNumberList  begin,  tom.engine.adt.tomname.types.TomNumberList  end, tom.engine.adt.tomname.types.TomNumberList  tail) {
    if( (begin==end) ) {
      return tail;
    } else if( (end==tail)  && ( end.isEmptyconcTomNumber()  ||  (end== tom.engine.adt.tomname.types.tomnumberlist.EmptyconcTomNumber.make() ) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.engine.adt.tomname.types.tomnumberlist.ConsconcTomNumber.make( begin.getHeadconcTomNumber() ,( tom.engine.adt.tomname.types.TomNumberList )tom_get_slice_concTomNumber( begin.getTailconcTomNumber() ,end,tail)) ;
  }
  
  private static   tom.engine.adt.tomslot.types.SlotList  tom_append_list_concSlot( tom.engine.adt.tomslot.types.SlotList l1,  tom.engine.adt.tomslot.types.SlotList  l2) {
    if( l1.isEmptyconcSlot() ) {
      return l2;
    } else if( l2.isEmptyconcSlot() ) {
      return l1;
    } else if(  l1.getTailconcSlot() .isEmptyconcSlot() ) {
      return  tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( l1.getHeadconcSlot() ,l2) ;
    } else {
      return  tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( l1.getHeadconcSlot() ,tom_append_list_concSlot( l1.getTailconcSlot() ,l2)) ;
    }
  }
  private static   tom.engine.adt.tomslot.types.SlotList  tom_get_slice_concSlot( tom.engine.adt.tomslot.types.SlotList  begin,  tom.engine.adt.tomslot.types.SlotList  end, tom.engine.adt.tomslot.types.SlotList  tail) {
    if( (begin==end) ) {
      return tail;
    } else if( (end==tail)  && ( end.isEmptyconcSlot()  ||  (end== tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() ) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( begin.getHeadconcSlot() ,( tom.engine.adt.tomslot.types.SlotList )tom_get_slice_concSlot( begin.getTailconcSlot() ,end,tail)) ;
  }
  
  private static   tom.engine.adt.tomslot.types.PairNameDeclList  tom_append_list_concPairNameDecl( tom.engine.adt.tomslot.types.PairNameDeclList l1,  tom.engine.adt.tomslot.types.PairNameDeclList  l2) {
    if( l1.isEmptyconcPairNameDecl() ) {
      return l2;
    } else if( l2.isEmptyconcPairNameDecl() ) {
      return l1;
    } else if(  l1.getTailconcPairNameDecl() .isEmptyconcPairNameDecl() ) {
      return  tom.engine.adt.tomslot.types.pairnamedecllist.ConsconcPairNameDecl.make( l1.getHeadconcPairNameDecl() ,l2) ;
    } else {
      return  tom.engine.adt.tomslot.types.pairnamedecllist.ConsconcPairNameDecl.make( l1.getHeadconcPairNameDecl() ,tom_append_list_concPairNameDecl( l1.getTailconcPairNameDecl() ,l2)) ;
    }
  }
  private static   tom.engine.adt.tomslot.types.PairNameDeclList  tom_get_slice_concPairNameDecl( tom.engine.adt.tomslot.types.PairNameDeclList  begin,  tom.engine.adt.tomslot.types.PairNameDeclList  end, tom.engine.adt.tomslot.types.PairNameDeclList  tail) {
    if( (begin==end) ) {
      return tail;
    } else if( (end==tail)  && ( end.isEmptyconcPairNameDecl()  ||  (end== tom.engine.adt.tomslot.types.pairnamedecllist.EmptyconcPairNameDecl.make() ) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.engine.adt.tomslot.types.pairnamedecllist.ConsconcPairNameDecl.make( begin.getHeadconcPairNameDecl() ,( tom.engine.adt.tomslot.types.PairNameDeclList )tom_get_slice_concPairNameDecl( begin.getTailconcPairNameDecl() ,end,tail)) ;
  }
  
  private static   tom.engine.adt.tomoption.types.OptionList  tom_append_list_concOption( tom.engine.adt.tomoption.types.OptionList l1,  tom.engine.adt.tomoption.types.OptionList  l2) {
    if( l1.isEmptyconcOption() ) {
      return l2;
    } else if( l2.isEmptyconcOption() ) {
      return l1;
    } else if(  l1.getTailconcOption() .isEmptyconcOption() ) {
      return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( l1.getHeadconcOption() ,l2) ;
    } else {
      return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( l1.getHeadconcOption() ,tom_append_list_concOption( l1.getTailconcOption() ,l2)) ;
    }
  }
  private static   tom.engine.adt.tomoption.types.OptionList  tom_get_slice_concOption( tom.engine.adt.tomoption.types.OptionList  begin,  tom.engine.adt.tomoption.types.OptionList  end, tom.engine.adt.tomoption.types.OptionList  tail) {
    if( (begin==end) ) {
      return tail;
    } else if( (end==tail)  && ( end.isEmptyconcOption()  ||  (end== tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() ) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( begin.getHeadconcOption() ,( tom.engine.adt.tomoption.types.OptionList )tom_get_slice_concOption( begin.getTailconcOption() ,end,tail)) ;
  }
  
  private static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_append_list_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList l1,  tom.engine.adt.tomconstraint.types.ConstraintList  l2) {
    if( l1.isEmptyconcConstraint() ) {
      return l2;
    } else if( l2.isEmptyconcConstraint() ) {
      return l1;
    } else if(  l1.getTailconcConstraint() .isEmptyconcConstraint() ) {
      return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,l2) ;
    } else {
      return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,tom_append_list_concConstraint( l1.getTailconcConstraint() ,l2)) ;
    }
  }
  private static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slice_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList  begin,  tom.engine.adt.tomconstraint.types.ConstraintList  end, tom.engine.adt.tomconstraint.types.ConstraintList  tail) {
    if( (begin==end) ) {
      return tail;
    } else if( (end==tail)  && ( end.isEmptyconcConstraint()  ||  (end== tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( begin.getHeadconcConstraint() ,( tom.engine.adt.tomconstraint.types.ConstraintList )tom_get_slice_concConstraint( begin.getTailconcConstraint() ,end,tail)) ;
  }
  
  private static   tom.engine.adt.theory.types.Theory  tom_append_list_concElementaryTheory( tom.engine.adt.theory.types.Theory l1,  tom.engine.adt.theory.types.Theory  l2) {
    if( l1.isEmptyconcElementaryTheory() ) {
      return l2;
    } else if( l2.isEmptyconcElementaryTheory() ) {
      return l1;
    } else if(  l1.getTailconcElementaryTheory() .isEmptyconcElementaryTheory() ) {
      return  tom.engine.adt.theory.types.theory.ConsconcElementaryTheory.make( l1.getHeadconcElementaryTheory() ,l2) ;
    } else {
      return  tom.engine.adt.theory.types.theory.ConsconcElementaryTheory.make( l1.getHeadconcElementaryTheory() ,tom_append_list_concElementaryTheory( l1.getTailconcElementaryTheory() ,l2)) ;
    }
  }
  private static   tom.engine.adt.theory.types.Theory  tom_get_slice_concElementaryTheory( tom.engine.adt.theory.types.Theory  begin,  tom.engine.adt.theory.types.Theory  end, tom.engine.adt.theory.types.Theory  tail) {
    if( (begin==end) ) {
      return tail;
    } else if( (end==tail)  && ( end.isEmptyconcElementaryTheory()  ||  (end== tom.engine.adt.theory.types.theory.EmptyconcElementaryTheory.make() ) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.engine.adt.theory.types.theory.ConsconcElementaryTheory.make( begin.getHeadconcElementaryTheory() ,( tom.engine.adt.theory.types.Theory )tom_get_slice_concElementaryTheory( begin.getTailconcElementaryTheory() ,end,tail)) ;
  }
  
  private static   tom.library.sl.Strategy  tom_append_list_Sequence( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {
    if(( l1 == null )) {
      return l2;
    } else if(( l2 == null )) {
      return l1;
    } else if(( (l1 instanceof tom.library.sl.Sequence) )) {
      if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ) == null )) {
        return ( (l2==null)?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):new tom.library.sl.Sequence(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ),l2) );
      } else {
        return ( (tom_append_list_Sequence(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ),l2)==null)?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):new tom.library.sl.Sequence(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ),tom_append_list_Sequence(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ),l2)) );
      }
    } else {
      return ( (l2==null)?l1:new tom.library.sl.Sequence(l1,l2) );
    }
  }
  private static   tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {
    if( (begin.equals(end)) ) {
      return tail;
    } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals(( null ))) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return ( (( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)==null)?((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin):new tom.library.sl.Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)) );
  }
  
  private static   tom.library.sl.Strategy  tom_append_list_Choice( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {
    if(( l1 ==null )) {
      return l2;
    } else if(( l2 ==null )) {
      return l1;
    } else if(( (l1 instanceof tom.library.sl.Choice) )) {
      if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ) ==null )) {
        return ( (l2==null)?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ):new tom.library.sl.Choice(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ),l2) );
      } else {
        return ( (tom_append_list_Choice(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ),l2)==null)?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ):new tom.library.sl.Choice(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ),tom_append_list_Choice(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ),l2)) );
      }
    } else {
      return ( (l2==null)?l1:new tom.library.sl.Choice(l1,l2) );
    }
  }
  private static   tom.library.sl.Strategy  tom_get_slice_Choice( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {
    if( (begin.equals(end)) ) {
      return tail;
    } else if( (end.equals(tail))  && (( end ==null ) ||  (end.equals(( null ))) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return ( (( tom.library.sl.Strategy )tom_get_slice_Choice(((( (begin instanceof tom.library.sl.Choice) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.THEN) ):( null )),end,tail)==null)?((( (begin instanceof tom.library.sl.Choice) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.FIRST) ):begin):new tom.library.sl.Choice(((( (begin instanceof tom.library.sl.Choice) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Choice(((( (begin instanceof tom.library.sl.Choice) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.THEN) ):( null )),end,tail)) );
  }
  
  private static   tom.library.sl.Strategy  tom_append_list_SequenceId( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {
    if(( l1 == null )) {
      return l2;
    } else if(( l2 == null )) {
      return l1;
    } else if(( (l1 instanceof tom.library.sl.SequenceId) )) {
      if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.THEN) ) == null )) {
        return ( (l2==null)?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.FIRST) ):new tom.library.sl.SequenceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.FIRST) ),l2) );
      } else {
        return ( (tom_append_list_SequenceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.THEN) ),l2)==null)?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.FIRST) ):new tom.library.sl.SequenceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.FIRST) ),tom_append_list_SequenceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.THEN) ),l2)) );
      }
    } else {
      return ( (l2==null)?l1:new tom.library.sl.SequenceId(l1,l2) );
    }
  }
  private static   tom.library.sl.Strategy  tom_get_slice_SequenceId( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {
    if( (begin.equals(end)) ) {
      return tail;
    } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals(( null ))) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return ( (( tom.library.sl.Strategy )tom_get_slice_SequenceId(((( (begin instanceof tom.library.sl.SequenceId) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.SequenceId.THEN) ):( null )),end,tail)==null)?((( (begin instanceof tom.library.sl.SequenceId) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.SequenceId.FIRST) ):begin):new tom.library.sl.SequenceId(((( (begin instanceof tom.library.sl.SequenceId) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.SequenceId.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_SequenceId(((( (begin instanceof tom.library.sl.SequenceId) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.SequenceId.THEN) ):( null )),end,tail)) );
  }
  
  private static   tom.library.sl.Strategy  tom_append_list_ChoiceId( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {
    if(( l1 ==null )) {
      return l2;
    } else if(( l2 ==null )) {
      return l1;
    } else if(( (l1 instanceof tom.library.sl.ChoiceId) )) {
      if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.THEN) ) ==null )) {
        return ( (l2==null)?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ):new tom.library.sl.ChoiceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ),l2) );
      } else {
        return ( (tom_append_list_ChoiceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.THEN) ),l2)==null)?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ):new tom.library.sl.ChoiceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ),tom_append_list_ChoiceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.THEN) ),l2)) );
      }
    } else {
      return ( (l2==null)?l1:new tom.library.sl.ChoiceId(l1,l2) );
    }
  }
  private static   tom.library.sl.Strategy  tom_get_slice_ChoiceId( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {
    if( (begin.equals(end)) ) {
      return tail;
    } else if( (end.equals(tail))  && (( end ==null ) ||  (end.equals(( null ))) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return ( (( tom.library.sl.Strategy )tom_get_slice_ChoiceId(((( (begin instanceof tom.library.sl.ChoiceId) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.THEN) ):( null )),end,tail)==null)?((( (begin instanceof tom.library.sl.ChoiceId) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.FIRST) ):begin):new tom.library.sl.ChoiceId(((( (begin instanceof tom.library.sl.ChoiceId) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_ChoiceId(((( (begin instanceof tom.library.sl.ChoiceId) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.THEN) ):( null )),end,tail)) );
  }
  private static  tom.library.sl.Strategy  tom_make_AUCtl( tom.library.sl.Strategy  s1,  tom.library.sl.Strategy  s2) { 
return ( 
( new tom.library.sl.Mu(( new tom.library.sl.MuVar("x") ),( (( (( null )==null)?( (( (( null )==null)?( new tom.library.sl.One(( new tom.library.sl.Identity() )) ):new tom.library.sl.Sequence(( new tom.library.sl.One(( new tom.library.sl.Identity() )) ),( null )) )==null)?( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ),( null )) )==null)?s1:new tom.library.sl.Sequence(s1,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ),( null )) )) ):new tom.library.sl.Sequence(( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ),( null )) )==null)?s1:new tom.library.sl.Sequence(s1,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ),( null )) )) ),( (( null )==null)?( new tom.library.sl.One(( new tom.library.sl.Identity() )) ):new tom.library.sl.Sequence(( new tom.library.sl.One(( new tom.library.sl.Identity() )) ),( null )) )) ):new tom.library.sl.Choice(( (( (( null )==null)?( new tom.library.sl.One(( new tom.library.sl.Identity() )) ):new tom.library.sl.Sequence(( new tom.library.sl.One(( new tom.library.sl.Identity() )) ),( null )) )==null)?( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ),( null )) )==null)?s1:new tom.library.sl.Sequence(s1,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ),( null )) )) ):new tom.library.sl.Sequence(( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ),( null )) )==null)?s1:new tom.library.sl.Sequence(s1,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ),( null )) )) ),( (( null )==null)?( new tom.library.sl.One(( new tom.library.sl.Identity() )) ):new tom.library.sl.Sequence(( new tom.library.sl.One(( new tom.library.sl.Identity() )) ),( null )) )) ),( null )) )==null)?s2:new tom.library.sl.Choice(s2,( (( null )==null)?( (( (( null )==null)?( new tom.library.sl.One(( new tom.library.sl.Identity() )) ):new tom.library.sl.Sequence(( new tom.library.sl.One(( new tom.library.sl.Identity() )) ),( null )) )==null)?( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ),( null )) )==null)?s1:new tom.library.sl.Sequence(s1,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ),( null )) )) ):new tom.library.sl.Sequence(( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ),( null )) )==null)?s1:new tom.library.sl.Sequence(s1,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ),( null )) )) ),( (( null )==null)?( new tom.library.sl.One(( new tom.library.sl.Identity() )) ):new tom.library.sl.Sequence(( new tom.library.sl.One(( new tom.library.sl.Identity() )) ),( null )) )) ):new tom.library.sl.Choice(( (( (( null )==null)?( new tom.library.sl.One(( new tom.library.sl.Identity() )) ):new tom.library.sl.Sequence(( new tom.library.sl.One(( new tom.library.sl.Identity() )) ),( null )) )==null)?( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ),( null )) )==null)?s1:new tom.library.sl.Sequence(s1,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ),( null )) )) ):new tom.library.sl.Sequence(( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ),( null )) )==null)?s1:new tom.library.sl.Sequence(s1,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ),( null )) )) ),( (( null )==null)?( new tom.library.sl.One(( new tom.library.sl.Identity() )) ):new tom.library.sl.Sequence(( new tom.library.sl.One(( new tom.library.sl.Identity() )) ),( null )) )) ),( null )) )) )) ))

;
}
private static  tom.library.sl.Strategy  tom_make_EUCtl( tom.library.sl.Strategy  s1,  tom.library.sl.Strategy  s2) { 
return ( 
( new tom.library.sl.Mu(( new tom.library.sl.MuVar("x") ),( (( (( null )==null)?( (( (( null )==null)?( new tom.library.sl.One(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.One(( new tom.library.sl.MuVar("x") )) ),( null )) )==null)?s1:new tom.library.sl.Sequence(s1,( (( null )==null)?( new tom.library.sl.One(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.One(( new tom.library.sl.MuVar("x") )) ),( null )) )) ):new tom.library.sl.Choice(( (( (( null )==null)?( new tom.library.sl.One(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.One(( new tom.library.sl.MuVar("x") )) ),( null )) )==null)?s1:new tom.library.sl.Sequence(s1,( (( null )==null)?( new tom.library.sl.One(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.One(( new tom.library.sl.MuVar("x") )) ),( null )) )) ),( null )) )==null)?s2:new tom.library.sl.Choice(s2,( (( null )==null)?( (( (( null )==null)?( new tom.library.sl.One(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.One(( new tom.library.sl.MuVar("x") )) ),( null )) )==null)?s1:new tom.library.sl.Sequence(s1,( (( null )==null)?( new tom.library.sl.One(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.One(( new tom.library.sl.MuVar("x") )) ),( null )) )) ):new tom.library.sl.Choice(( (( (( null )==null)?( new tom.library.sl.One(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.One(( new tom.library.sl.MuVar("x") )) ),( null )) )==null)?s1:new tom.library.sl.Sequence(s1,( (( null )==null)?( new tom.library.sl.One(( new tom.library.sl.MuVar("x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.One(( new tom.library.sl.MuVar("x") )) ),( null )) )) ),( null )) )) )) ))

;
}
private static  tom.library.sl.Strategy  tom_make_Try( tom.library.sl.Strategy  s) { 
return ( 
( (( (( null )==null)?( new tom.library.sl.Identity() ):new tom.library.sl.Choice(( new tom.library.sl.Identity() ),( null )) )==null)?s:new tom.library.sl.Choice(s,( (( null )==null)?( new tom.library.sl.Identity() ):new tom.library.sl.Choice(( new tom.library.sl.Identity() ),( null )) )) ))

;
}
private static  tom.library.sl.Strategy  tom_make_Repeat( tom.library.sl.Strategy  s) { 
return ( 
( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ),( (( (( null )==null)?( new tom.library.sl.Identity() ):new tom.library.sl.Choice(( new tom.library.sl.Identity() ),( null )) )==null)?( (( (( null )==null)?( new tom.library.sl.MuVar("_x") ):new tom.library.sl.Sequence(( new tom.library.sl.MuVar("_x") ),( null )) )==null)?s:new tom.library.sl.Sequence(s,( (( null )==null)?( new tom.library.sl.MuVar("_x") ):new tom.library.sl.Sequence(( new tom.library.sl.MuVar("_x") ),( null )) )) ):new tom.library.sl.Choice(( (( (( null )==null)?( new tom.library.sl.MuVar("_x") ):new tom.library.sl.Sequence(( new tom.library.sl.MuVar("_x") ),( null )) )==null)?s:new tom.library.sl.Sequence(s,( (( null )==null)?( new tom.library.sl.MuVar("_x") ):new tom.library.sl.Sequence(( new tom.library.sl.MuVar("_x") ),( null )) )) ),( (( null )==null)?( new tom.library.sl.Identity() ):new tom.library.sl.Choice(( new tom.library.sl.Identity() ),( null )) )) )) ))

;
}
private static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { 
return ( 
( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ),( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )==null)?v:new tom.library.sl.Sequence(v,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )) )) ))

;
}
private static  tom.library.sl.Strategy  tom_make_TopDownCollect( tom.library.sl.Strategy  v) { 
return ( 
( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ),tom_make_Try(( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )==null)?v:new tom.library.sl.Sequence(v,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )) ))) ))

;
}
private static  tom.library.sl.Strategy  tom_make_OnceTopDown( tom.library.sl.Strategy  v) { 
return ( 
( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ),( (( (( null )==null)?( new tom.library.sl.One(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Choice(( new tom.library.sl.One(( new tom.library.sl.MuVar("_x") )) ),( null )) )==null)?v:new tom.library.sl.Choice(v,( (( null )==null)?( new tom.library.sl.One(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Choice(( new tom.library.sl.One(( new tom.library.sl.MuVar("_x") )) ),( null )) )) )) ))

;
}
private static  tom.library.sl.Strategy  tom_make_RepeatId( tom.library.sl.Strategy  v) { 
return ( 
( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ),( (( (( null )==null)?( new tom.library.sl.MuVar("_x") ):new tom.library.sl.SequenceId(( new tom.library.sl.MuVar("_x") ),( null )) )==null)?v:new tom.library.sl.SequenceId(v,( (( null )==null)?( new tom.library.sl.MuVar("_x") ):new tom.library.sl.SequenceId(( new tom.library.sl.MuVar("_x") ),( null )) )) )) ))

;
}
private static  tom.library.sl.Strategy  tom_make_OnceTopDownId( tom.library.sl.Strategy  v) { 
return ( 
( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ),( (( (( null )==null)?( new tom.library.sl.OneId(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.ChoiceId(( new tom.library.sl.OneId(( new tom.library.sl.MuVar("_x") )) ),( null )) )==null)?v:new tom.library.sl.ChoiceId(v,( (( null )==null)?( new tom.library.sl.OneId(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.ChoiceId(( new tom.library.sl.OneId(( new tom.library.sl.MuVar("_x") )) ),( null )) )) )) ))

;
}


public final static String DEFAULT_MODULE_NAME = "default";
//size of cache
private final static int LRUCACHE_SIZE = 5000;

private static Logger logger = Logger.getLogger("tom.engine.TomBase");

/** shortcut */

/**
* Returns the name of a <code>TomType</code>
*/
public static String getTomType(TomType type) {

{
{
if ( (type instanceof tom.engine.adt.tomtype.types.TomType) ) {
boolean tomMatch18_3= false ;
 String  tomMatch18_1= "" ;
if ( ((( tom.engine.adt.tomtype.types.TomType )type) instanceof tom.engine.adt.tomtype.types.tomtype.Type) ) {
{
tomMatch18_3= true ;
tomMatch18_1= (( tom.engine.adt.tomtype.types.TomType )type).getTomType() ;

}
} else {
if ( ((( tom.engine.adt.tomtype.types.TomType )type) instanceof tom.engine.adt.tomtype.types.tomtype.TypeWithSymbol) ) {
{
tomMatch18_3= true ;
tomMatch18_1= (( tom.engine.adt.tomtype.types.TomType )type).getTomType() ;

}
}
}
if (tomMatch18_3) {
return 
tomMatch18_1; 

}

}

}
{
if ( (type instanceof tom.engine.adt.tomtype.types.TomType) ) {
if ( ((( tom.engine.adt.tomtype.types.TomType )type) instanceof tom.engine.adt.tomtype.types.tomtype.EmptyType) ) {
return null; 
}
}
if ( (type instanceof tom.engine.adt.tomtype.types.TomType) ) {
if ( ((( tom.engine.adt.tomtype.types.TomType )type) instanceof tom.engine.adt.tomtype.types.tomtype.TypeVar) ) {
return null; 
}
}

}


}

throw new TomRuntimeException("getTomType error on term: " + type);
}

/**
* Returns the implementation-type of a <code>TomType</code>
*/
public static String getTLType(TomType type) {

{
{
if ( (type instanceof tom.engine.adt.tomtype.types.TomType) ) {
if ( ((( tom.engine.adt.tomtype.types.TomType )type) instanceof tom.engine.adt.tomtype.types.tomtype.Type) ) {
return getTLCode(
 (( tom.engine.adt.tomtype.types.TomType )type).getTlType() ); 

}
}

}

}

throw new TomRuntimeException("getTLType error on term: " + type);
}

/**
* Returns the implementation-type of a <code>TLType</code>
*/
public static String getTLCode(TargetLanguageType type) {

{
{
if ( (type instanceof tom.engine.adt.tomtype.types.TargetLanguageType) ) {
if ( ((( tom.engine.adt.tomtype.types.TargetLanguageType )type) instanceof tom.engine.adt.tomtype.types.targetlanguagetype.TLType) ) {
return 
 (( tom.engine.adt.tomtype.types.TargetLanguageType )type).getString() ; 

}
}

}

}

throw new TomRuntimeException("getTLCode error on term: " + type);
}

/**
* Returns the codomain of a given symbol
*/
public static TomType getSymbolCodomain(TomSymbol symbol) {
if(symbol!=null) {
return symbol.getTypesToType().getCodomain();
} else {
return 
 tom.engine.adt.tomtype.types.tomtype.EmptyType.make() ;
}
}

/**
* Returns the domain of a given symbol
*/
public static TomTypeList getSymbolDomain(TomSymbol symbol) {
if(symbol!=null) {
return symbol.getTypesToType().getDomain();
} else {
return 
 tom.engine.adt.tomtype.types.tomtypelist.EmptyconcTomType.make() ;
}
}

private static LRUCache<TomNumberList,String> tomNumberListToStringMap =
new LRUCache<TomNumberList,String>(LRUCACHE_SIZE);
public static String tomNumberListToString(TomNumberList numberList) {
String result = tomNumberListToStringMap.get(numberList);
if(result == null) {
TomNumberList key = numberList;
StringBuilder buf = new StringBuilder(30);
while(!numberList.isEmptyconcTomNumber()) {
TomNumber number = numberList.getHeadconcTomNumber();
numberList = numberList.getTailconcTomNumber();

{
{
if ( (number instanceof tom.engine.adt.tomname.types.TomNumber) ) {
if ( ((( tom.engine.adt.tomname.types.TomNumber )number) instanceof tom.engine.adt.tomname.types.tomnumber.Position) ) {

buf.append("Position");
buf.append(Integer.toString(
 (( tom.engine.adt.tomname.types.TomNumber )number).getInteger() ));


}
}

}
{
if ( (number instanceof tom.engine.adt.tomname.types.TomNumber) ) {
if ( ((( tom.engine.adt.tomname.types.TomNumber )number) instanceof tom.engine.adt.tomname.types.tomnumber.MatchNumber) ) {

buf.append("Match");
buf.append(Integer.toString(
 (( tom.engine.adt.tomname.types.TomNumber )number).getInteger() ));


}
}

}
{
if ( (number instanceof tom.engine.adt.tomname.types.TomNumber) ) {
if ( ((( tom.engine.adt.tomname.types.TomNumber )number) instanceof tom.engine.adt.tomname.types.tomnumber.PatternNumber) ) {

buf.append("Pattern");
buf.append(Integer.toString(
 (( tom.engine.adt.tomname.types.TomNumber )number).getInteger() ));


}
}

}
{
if ( (number instanceof tom.engine.adt.tomname.types.TomNumber) ) {
if ( ((( tom.engine.adt.tomname.types.TomNumber )number) instanceof tom.engine.adt.tomname.types.tomnumber.ListNumber) ) {

buf.append("List");
buf.append(Integer.toString(
 (( tom.engine.adt.tomname.types.TomNumber )number).getInteger() ));


}
}

}
{
if ( (number instanceof tom.engine.adt.tomname.types.TomNumber) ) {
if ( ((( tom.engine.adt.tomname.types.TomNumber )number) instanceof tom.engine.adt.tomname.types.tomnumber.IndexNumber) ) {

buf.append("Index");
buf.append(Integer.toString(
 (( tom.engine.adt.tomname.types.TomNumber )number).getInteger() ));


}
}

}
{
if ( (number instanceof tom.engine.adt.tomname.types.TomNumber) ) {
if ( ((( tom.engine.adt.tomname.types.TomNumber )number) instanceof tom.engine.adt.tomname.types.tomnumber.Begin) ) {

buf.append("Begin");
buf.append(Integer.toString(
 (( tom.engine.adt.tomname.types.TomNumber )number).getInteger() ));


}
}

}
{
if ( (number instanceof tom.engine.adt.tomname.types.TomNumber) ) {
if ( ((( tom.engine.adt.tomname.types.TomNumber )number) instanceof tom.engine.adt.tomname.types.tomnumber.End) ) {

buf.append("End");
buf.append(Integer.toString(
 (( tom.engine.adt.tomname.types.TomNumber )number).getInteger() ));


}
}

}
{
if ( (number instanceof tom.engine.adt.tomname.types.TomNumber) ) {
if ( ((( tom.engine.adt.tomname.types.TomNumber )number) instanceof tom.engine.adt.tomname.types.tomnumber.Save) ) {

buf.append("Save");
buf.append(Integer.toString(
 (( tom.engine.adt.tomname.types.TomNumber )number).getInteger() ));


}
}

}
{
if ( (number instanceof tom.engine.adt.tomname.types.TomNumber) ) {
if ( ((( tom.engine.adt.tomname.types.TomNumber )number) instanceof tom.engine.adt.tomname.types.tomnumber.AbsVar) ) {

buf.append("AbsVar");
buf.append(Integer.toString(
 (( tom.engine.adt.tomname.types.TomNumber )number).getInteger() ));


}
}

}
{
if ( (number instanceof tom.engine.adt.tomname.types.TomNumber) ) {
if ( ((( tom.engine.adt.tomname.types.TomNumber )number) instanceof tom.engine.adt.tomname.types.tomnumber.RenamedVar) ) {
 tom.engine.adt.tomname.types.TomName  tom_tomName= (( tom.engine.adt.tomname.types.TomNumber )number).getAstName() ;

String identifier = "Empty";

{
{
if ( (tom_tomName instanceof tom.engine.adt.tomname.types.TomName) ) {
if ( ((( tom.engine.adt.tomname.types.TomName )tom_tomName) instanceof tom.engine.adt.tomname.types.tomname.Name) ) {

identifier = 
 (( tom.engine.adt.tomname.types.TomName )tom_tomName).getString() ;


}
}

}
{
if ( (tom_tomName instanceof tom.engine.adt.tomname.types.TomName) ) {
if ( ((( tom.engine.adt.tomname.types.TomName )tom_tomName) instanceof tom.engine.adt.tomname.types.tomname.PositionName) ) {

identifier = tomNumberListToString(
 (( tom.engine.adt.tomname.types.TomName )tom_tomName).getNumberList() );


}
}

}


}

buf.append("RenamedVar");
buf.append(identifier);


}
}

}
{
if ( (number instanceof tom.engine.adt.tomname.types.TomNumber) ) {
if ( ((( tom.engine.adt.tomname.types.TomNumber )number) instanceof tom.engine.adt.tomname.types.tomnumber.NameNumber) ) {
 tom.engine.adt.tomname.types.TomName  tom_tomName= (( tom.engine.adt.tomname.types.TomNumber )number).getAstName() ;

String identifier = "Empty";

{
{
if ( (tom_tomName instanceof tom.engine.adt.tomname.types.TomName) ) {
if ( ((( tom.engine.adt.tomname.types.TomName )tom_tomName) instanceof tom.engine.adt.tomname.types.tomname.Name) ) {

identifier = 
 (( tom.engine.adt.tomname.types.TomName )tom_tomName).getString() ;


}
}

}
{
if ( (tom_tomName instanceof tom.engine.adt.tomname.types.TomName) ) {
if ( ((( tom.engine.adt.tomname.types.TomName )tom_tomName) instanceof tom.engine.adt.tomname.types.tomname.PositionName) ) {

identifier = tomNumberListToString(
 (( tom.engine.adt.tomname.types.TomName )tom_tomName).getNumberList() );


}
}

}


}

//buf.append("NameNumber");
buf.append(identifier);


}
}

}


}

}
result = buf.toString();
tomNumberListToStringMap.put(key,result);
}
return result;
}

/**
* Returns <code>true</code> if the symbol corresponds to a %oplist
*/
public static boolean isListOperator(TomSymbol symbol) {
if(symbol==null) {
return false;
}

{
{
if ( (symbol instanceof tom.engine.adt.tomsignature.types.TomSymbol) ) {
if ( ((( tom.engine.adt.tomsignature.types.TomSymbol )symbol) instanceof tom.engine.adt.tomsignature.types.tomsymbol.Symbol) ) {

OptionList optionList = 
 (( tom.engine.adt.tomsignature.types.TomSymbol )symbol).getOptions() ;
while(!optionList.isEmptyconcOption()) {
Option opt = optionList.getHeadconcOption();

{
{
if ( (opt instanceof tom.engine.adt.tomoption.types.Option) ) {
if ( ((( tom.engine.adt.tomoption.types.Option )opt) instanceof tom.engine.adt.tomoption.types.option.DeclarationToOption) ) {
if ( ( (( tom.engine.adt.tomoption.types.Option )opt).getAstDeclaration()  instanceof tom.engine.adt.tomdeclaration.types.declaration.MakeEmptyList) ) {
return true; 
}
}
}

}
{
if ( (opt instanceof tom.engine.adt.tomoption.types.Option) ) {
if ( ((( tom.engine.adt.tomoption.types.Option )opt) instanceof tom.engine.adt.tomoption.types.option.DeclarationToOption) ) {
if ( ( (( tom.engine.adt.tomoption.types.Option )opt).getAstDeclaration()  instanceof tom.engine.adt.tomdeclaration.types.declaration.MakeAddList) ) {
return true; 
}
}
}

}


}

optionList = optionList.getTailconcOption();
}
return false;


}
}

}

}

throw new TomRuntimeException("isListOperator -- strange case: '" + symbol + "'");
}

/**
* Returns <code>true</code> if the symbol corresponds to a %oparray
*/
public static boolean isArrayOperator(TomSymbol symbol) {
if(symbol==null) {
return false;
}

{
{
if ( (symbol instanceof tom.engine.adt.tomsignature.types.TomSymbol) ) {
if ( ((( tom.engine.adt.tomsignature.types.TomSymbol )symbol) instanceof tom.engine.adt.tomsignature.types.tomsymbol.Symbol) ) {

OptionList optionList = 
 (( tom.engine.adt.tomsignature.types.TomSymbol )symbol).getOptions() ;
while(!optionList.isEmptyconcOption()) {
Option opt = optionList.getHeadconcOption();

{
{
if ( (opt instanceof tom.engine.adt.tomoption.types.Option) ) {
if ( ((( tom.engine.adt.tomoption.types.Option )opt) instanceof tom.engine.adt.tomoption.types.option.DeclarationToOption) ) {
if ( ( (( tom.engine.adt.tomoption.types.Option )opt).getAstDeclaration()  instanceof tom.engine.adt.tomdeclaration.types.declaration.MakeEmptyArray) ) {
return true; 
}
}
}

}
{
if ( (opt instanceof tom.engine.adt.tomoption.types.Option) ) {
if ( ((( tom.engine.adt.tomoption.types.Option )opt) instanceof tom.engine.adt.tomoption.types.option.DeclarationToOption) ) {
if ( ( (( tom.engine.adt.tomoption.types.Option )opt).getAstDeclaration()  instanceof tom.engine.adt.tomdeclaration.types.declaration.MakeAddArray) ) {
return true; 
}
}
}

}


}

optionList = optionList.getTailconcOption();
}
return false;


}
}

}

}

throw new TomRuntimeException("isArrayOperator -- strange case: '" + symbol + "'");
}

/**
* Returns <code>true</code> if the symbol corresponds to a %op
*/
public static boolean isSyntacticOperator(TomSymbol subject) {
return (!(isListOperator(subject) || isArrayOperator(subject)));
}

// ------------------------------------------------------------
/**
* Collects the variables that appears in a term
* @param collection the bag which collect the results
* @param subject the term to traverse
*/
public static void collectVariable(Collection<TomTerm> collection, 
tom.library.sl.Visitable subject, boolean considerBQVars) {
try {
//TODO: replace TopDownCollect by continuations

tom_make_TopDownCollect(tom_make_collectVariable(collection,considerBQVars)).visitLight(
subject);
} catch(VisitFailure e) {
throw new TomRuntimeException("Should not be there");
}
}


public static class collectVariable extends tom.library.sl.AbstractStrategyBasic {
private  java.util.Collection  collection;
private  boolean  considerBQVars;
public collectVariable( java.util.Collection  collection,  boolean  considerBQVars) {
super(( new tom.library.sl.Identity() ));
this.collection=collection;
this.considerBQVars=considerBQVars;
}
public  java.util.Collection  getcollection() {
return collection;
}
public  boolean  getconsiderBQVars() {
return considerBQVars;
}
public tom.library.sl.Visitable[] getChildren() {
tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];
stratChilds[0] = super.getChildAt(0);
return stratChilds;}
public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {
super.setChildAt(0, children[0]);
return this;
}
public int getChildCount() {
return 1;
}
public tom.library.sl.Visitable getChildAt(int index) {
switch (index) {
case 0: return super.getChildAt(0);
default: throw new IndexOutOfBoundsException();
}
}
public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {
switch (index) {
case 0: return super.setChildAt(0, child);
default: throw new IndexOutOfBoundsException();
}
}
@SuppressWarnings("unchecked")
public  tom.engine.adt.code.types.BQTerm  visit_BQTerm( tom.engine.adt.code.types.BQTerm  tom__arg, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
{
{
if ( (tom__arg instanceof tom.engine.adt.code.types.BQTerm) ) {
if ( ((( tom.engine.adt.code.types.BQTerm )tom__arg) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {

if (considerBQVars) { collection.add(convertFromBQVarToVar(
(( tom.engine.adt.code.types.BQTerm )tom__arg))); } 


}
}

}

}
return _visit_BQTerm(tom__arg,introspector);

}
@SuppressWarnings("unchecked")
public  tom.engine.adt.tomterm.types.TomTerm  visit_TomTerm( tom.engine.adt.tomterm.types.TomTerm  tom__arg, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
{
{
if ( (tom__arg instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
boolean tomMatch29_3= false ;
 tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch29_1= null ;
if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom__arg) instanceof tom.engine.adt.tomterm.types.tomterm.Variable) ) {
{
tomMatch29_3= true ;
tomMatch29_1= (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getConstraints() ;

}
} else {
if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom__arg) instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) {
{
tomMatch29_3= true ;
tomMatch29_1= (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getConstraints() ;

}
}
}
if (tomMatch29_3) {
 tom.engine.adt.tomterm.types.TomTerm  tom_v=(( tom.engine.adt.tomterm.types.TomTerm )tom__arg);

collection.add(
tom_v);
TomTerm annotedVariable = getAliasToVariable(
tomMatch29_1);
if(annotedVariable!=null) {
collection.add(annotedVariable);
}

( new tom.library.sl.Fail() ).visitLight(
tom_v);


}

}

}
{
if ( (tom__arg instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
if ( ((( tom.engine.adt.tomterm.types.TomTerm )tom__arg) instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {

collectVariable(collection,
 (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getSlots() ,considerBQVars);
TomTerm annotedVariable = getAliasToVariable(
 (( tom.engine.adt.tomterm.types.TomTerm )tom__arg).getConstraints() );
if(annotedVariable!=null) {
collection.add(annotedVariable);
}

( new tom.library.sl.Fail() ).visitLight(
(( tom.engine.adt.tomterm.types.TomTerm )tom__arg));


}
}

}


}
return _visit_TomTerm(tom__arg,introspector);

}
@SuppressWarnings("unchecked")
public  tom.engine.adt.code.types.BQTerm  _visit_BQTerm( tom.engine.adt.code.types.BQTerm  arg, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
if (!(  null ==environment )) {
return (( tom.engine.adt.code.types.BQTerm )any.visit(environment,introspector));
} else {
return any.visitLight(arg,introspector);
}
}
@SuppressWarnings("unchecked")
public  tom.engine.adt.tomterm.types.TomTerm  _visit_TomTerm( tom.engine.adt.tomterm.types.TomTerm  arg, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
if (!(  null ==environment )) {
return (( tom.engine.adt.tomterm.types.TomTerm )any.visit(environment,introspector));
} else {
return any.visitLight(arg,introspector);
}
}
@SuppressWarnings("unchecked")
public <T> T visitLight(T v, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
if ( (v instanceof tom.engine.adt.code.types.BQTerm) ) {
return ((T)visit_BQTerm((( tom.engine.adt.code.types.BQTerm )v),introspector));
}
if ( (v instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
return ((T)visit_TomTerm((( tom.engine.adt.tomterm.types.TomTerm )v),introspector));
}
if (!(  null ==environment )) {
return ((T)any.visit(environment,introspector));
} else {
return any.visitLight(v,introspector);
}

}
}
private static  tom.library.sl.Strategy  tom_make_collectVariable( java.util.Collection  t0,  boolean  t1) { 
return new collectVariable(t0,t1);
}


/**
* Returns a Map which associates an integer to each variable name
*/
public static Map<TomName,Integer> collectMultiplicity(tom.library.sl.Visitable subject) {
// collect variables
Collection<TomTerm> variableList = new HashSet<TomTerm>();
collectVariable(variableList,
subject,true);
// compute multiplicities
HashMap<TomName,Integer> multiplicityMap = new HashMap<TomName,Integer>();
for(TomTerm variable:variableList) {
TomName name = variable.getAstName();
if(multiplicityMap.containsKey(name)) {
int value = multiplicityMap.get(name);
multiplicityMap.put(name, 1+value);
} else {
multiplicityMap.put(name, 1);
}
}
return multiplicityMap;
}

private static TomTerm getAliasToVariable(ConstraintList constraintList) {

{
{
if ( (constraintList instanceof tom.engine.adt.tomconstraint.types.ConstraintList) ) {
if ( (((( tom.engine.adt.tomconstraint.types.ConstraintList )constraintList) instanceof tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint) || ((( tom.engine.adt.tomconstraint.types.ConstraintList )constraintList) instanceof tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint)) ) {
 tom.engine.adt.tomconstraint.types.ConstraintList  tomMatch30__end__4=(( tom.engine.adt.tomconstraint.types.ConstraintList )constraintList);
do {
{
if (!( tomMatch30__end__4.isEmptyconcConstraint() )) {
 tom.engine.adt.tomconstraint.types.Constraint  tomMatch30_8= tomMatch30__end__4.getHeadconcConstraint() ;
if ( (tomMatch30_8 instanceof tom.engine.adt.tomconstraint.types.constraint.AliasTo) ) {
 tom.engine.adt.tomterm.types.TomTerm  tomMatch30_7= tomMatch30_8.getVar() ;
if ( (tomMatch30_7 instanceof tom.engine.adt.tomterm.types.tomterm.Variable) ) {
return 
tomMatch30_7; 

}
}
}
if ( tomMatch30__end__4.isEmptyconcConstraint() ) {
tomMatch30__end__4=(( tom.engine.adt.tomconstraint.types.ConstraintList )constraintList);
} else {
tomMatch30__end__4= tomMatch30__end__4.getTailconcConstraint() ;
}

}
} while(!( (tomMatch30__end__4==(( tom.engine.adt.tomconstraint.types.ConstraintList )constraintList)) ));
}
}

}

}

return null;
}

public static boolean hasTheory(TomTerm term, ElementaryTheory elementaryTheory) {
return hasTheory(getTheory(term),elementaryTheory);
}

public static boolean hasTheory(Theory theory, ElementaryTheory elementaryTheory) {

{
{
if ( (theory instanceof tom.engine.adt.theory.types.Theory) ) {
if ( (((( tom.engine.adt.theory.types.Theory )theory) instanceof tom.engine.adt.theory.types.theory.ConsconcElementaryTheory) || ((( tom.engine.adt.theory.types.Theory )theory) instanceof tom.engine.adt.theory.types.theory.EmptyconcElementaryTheory)) ) {
 tom.engine.adt.theory.types.Theory  tomMatch31__end__4=(( tom.engine.adt.theory.types.Theory )theory);
do {
{
if (!( tomMatch31__end__4.isEmptyconcElementaryTheory() )) {
if(
 tomMatch31__end__4.getHeadconcElementaryTheory() ==elementaryTheory) { return true; } 

}
if ( tomMatch31__end__4.isEmptyconcElementaryTheory() ) {
tomMatch31__end__4=(( tom.engine.adt.theory.types.Theory )theory);
} else {
tomMatch31__end__4= tomMatch31__end__4.getTailconcElementaryTheory() ;
}

}
} while(!( (tomMatch31__end__4==(( tom.engine.adt.theory.types.Theory )theory)) ));
}
}

}

}

return false;
}

public static Theory getTheory(TomTerm term) {

{
{
if ( (term instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
if ( ((( tom.engine.adt.tomterm.types.TomTerm )term) instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {
 tom.engine.adt.tomoption.types.OptionList  tomMatch32_1= (( tom.engine.adt.tomterm.types.TomTerm )term).getOptions() ;
if ( ((tomMatch32_1 instanceof tom.engine.adt.tomoption.types.optionlist.ConsconcOption) || (tomMatch32_1 instanceof tom.engine.adt.tomoption.types.optionlist.EmptyconcOption)) ) {
 tom.engine.adt.tomoption.types.OptionList  tomMatch32__end__6=tomMatch32_1;
do {
{
if (!( tomMatch32__end__6.isEmptyconcOption() )) {
 tom.engine.adt.tomoption.types.Option  tomMatch32_10= tomMatch32__end__6.getHeadconcOption() ;
if ( (tomMatch32_10 instanceof tom.engine.adt.tomoption.types.option.MatchingTheory) ) {
return 
 tomMatch32_10.getTheory() ; 

}
}
if ( tomMatch32__end__6.isEmptyconcOption() ) {
tomMatch32__end__6=tomMatch32_1;
} else {
tomMatch32__end__6= tomMatch32__end__6.getTailconcOption() ;
}

}
} while(!( (tomMatch32__end__6==tomMatch32_1) ));
}
}
}

}

}

return 
 tom.engine.adt.theory.types.theory.ConsconcElementaryTheory.make( tom.engine.adt.theory.types.elementarytheory.Syntactic.make() , tom.engine.adt.theory.types.theory.EmptyconcElementaryTheory.make() ) ;
}

public static Theory getTheory(OptionList optionList) {

{
{
if ( (optionList instanceof tom.engine.adt.tomoption.types.OptionList) ) {
if ( (((( tom.engine.adt.tomoption.types.OptionList )optionList) instanceof tom.engine.adt.tomoption.types.optionlist.ConsconcOption) || ((( tom.engine.adt.tomoption.types.OptionList )optionList) instanceof tom.engine.adt.tomoption.types.optionlist.EmptyconcOption)) ) {
 tom.engine.adt.tomoption.types.OptionList  tomMatch33__end__4=(( tom.engine.adt.tomoption.types.OptionList )optionList);
do {
{
if (!( tomMatch33__end__4.isEmptyconcOption() )) {
 tom.engine.adt.tomoption.types.Option  tomMatch33_8= tomMatch33__end__4.getHeadconcOption() ;
if ( (tomMatch33_8 instanceof tom.engine.adt.tomoption.types.option.MatchingTheory) ) {
return 
 tomMatch33_8.getTheory() ; 

}
}
if ( tomMatch33__end__4.isEmptyconcOption() ) {
tomMatch33__end__4=(( tom.engine.adt.tomoption.types.OptionList )optionList);
} else {
tomMatch33__end__4= tomMatch33__end__4.getTailconcOption() ;
}

}
} while(!( (tomMatch33__end__4==(( tom.engine.adt.tomoption.types.OptionList )optionList)) ));
}
}

}

}

return 
 tom.engine.adt.theory.types.theory.ConsconcElementaryTheory.make( tom.engine.adt.theory.types.elementarytheory.Syntactic.make() , tom.engine.adt.theory.types.theory.EmptyconcElementaryTheory.make() ) ;
}

public static Declaration getIsFsymDecl(OptionList optionList) {

{
{
if ( (optionList instanceof tom.engine.adt.tomoption.types.OptionList) ) {
if ( (((( tom.engine.adt.tomoption.types.OptionList )optionList) instanceof tom.engine.adt.tomoption.types.optionlist.ConsconcOption) || ((( tom.engine.adt.tomoption.types.OptionList )optionList) instanceof tom.engine.adt.tomoption.types.optionlist.EmptyconcOption)) ) {
 tom.engine.adt.tomoption.types.OptionList  tomMatch34__end__4=(( tom.engine.adt.tomoption.types.OptionList )optionList);
do {
{
if (!( tomMatch34__end__4.isEmptyconcOption() )) {
 tom.engine.adt.tomoption.types.Option  tomMatch34_8= tomMatch34__end__4.getHeadconcOption() ;
if ( (tomMatch34_8 instanceof tom.engine.adt.tomoption.types.option.DeclarationToOption) ) {
 tom.engine.adt.tomdeclaration.types.Declaration  tomMatch34_7= tomMatch34_8.getAstDeclaration() ;
if ( (tomMatch34_7 instanceof tom.engine.adt.tomdeclaration.types.declaration.IsFsymDecl) ) {
return 
tomMatch34_7; 

}
}
}
if ( tomMatch34__end__4.isEmptyconcOption() ) {
tomMatch34__end__4=(( tom.engine.adt.tomoption.types.OptionList )optionList);
} else {
tomMatch34__end__4= tomMatch34__end__4.getTailconcOption() ;
}

}
} while(!( (tomMatch34__end__4==(( tom.engine.adt.tomoption.types.OptionList )optionList)) ));
}
}

}

}

return null;
}

public static boolean hasIsFsymDecl(TomSymbol tomSymbol) {

{
{
if ( (tomSymbol instanceof tom.engine.adt.tomsignature.types.TomSymbol) ) {
if ( ((( tom.engine.adt.tomsignature.types.TomSymbol )tomSymbol) instanceof tom.engine.adt.tomsignature.types.tomsymbol.Symbol) ) {
 tom.engine.adt.tomoption.types.OptionList  tomMatch35_1= (( tom.engine.adt.tomsignature.types.TomSymbol )tomSymbol).getOptions() ;
if ( ((tomMatch35_1 instanceof tom.engine.adt.tomoption.types.optionlist.ConsconcOption) || (tomMatch35_1 instanceof tom.engine.adt.tomoption.types.optionlist.EmptyconcOption)) ) {
 tom.engine.adt.tomoption.types.OptionList  tomMatch35__end__6=tomMatch35_1;
do {
{
if (!( tomMatch35__end__6.isEmptyconcOption() )) {
 tom.engine.adt.tomoption.types.Option  tomMatch35_10= tomMatch35__end__6.getHeadconcOption() ;
if ( (tomMatch35_10 instanceof tom.engine.adt.tomoption.types.option.DeclarationToOption) ) {
if ( ( tomMatch35_10.getAstDeclaration()  instanceof tom.engine.adt.tomdeclaration.types.declaration.IsFsymDecl) ) {

return true;

}
}
}
if ( tomMatch35__end__6.isEmptyconcOption() ) {
tomMatch35__end__6=tomMatch35_1;
} else {
tomMatch35__end__6= tomMatch35__end__6.getTailconcOption() ;
}

}
} while(!( (tomMatch35__end__6==tomMatch35_1) ));
}
}
}

}

}

return false;
}

public static String getModuleName(OptionList optionList) {

{
{
if ( (optionList instanceof tom.engine.adt.tomoption.types.OptionList) ) {
if ( (((( tom.engine.adt.tomoption.types.OptionList )optionList) instanceof tom.engine.adt.tomoption.types.optionlist.ConsconcOption) || ((( tom.engine.adt.tomoption.types.OptionList )optionList) instanceof tom.engine.adt.tomoption.types.optionlist.EmptyconcOption)) ) {
 tom.engine.adt.tomoption.types.OptionList  tomMatch36__end__4=(( tom.engine.adt.tomoption.types.OptionList )optionList);
do {
{
if (!( tomMatch36__end__4.isEmptyconcOption() )) {
 tom.engine.adt.tomoption.types.Option  tomMatch36_8= tomMatch36__end__4.getHeadconcOption() ;
if ( (tomMatch36_8 instanceof tom.engine.adt.tomoption.types.option.ModuleName) ) {
return 
 tomMatch36_8.getString() ; 

}
}
if ( tomMatch36__end__4.isEmptyconcOption() ) {
tomMatch36__end__4=(( tom.engine.adt.tomoption.types.OptionList )optionList);
} else {
tomMatch36__end__4= tomMatch36__end__4.getTailconcOption() ;
}

}
} while(!( (tomMatch36__end__4==(( tom.engine.adt.tomoption.types.OptionList )optionList)) ));
}
}

}

}

return null;
}

public static boolean hasConstant(OptionList optionList) {

{
{
if ( (optionList instanceof tom.engine.adt.tomoption.types.OptionList) ) {
if ( (((( tom.engine.adt.tomoption.types.OptionList )optionList) instanceof tom.engine.adt.tomoption.types.optionlist.ConsconcOption) || ((( tom.engine.adt.tomoption.types.OptionList )optionList) instanceof tom.engine.adt.tomoption.types.optionlist.EmptyconcOption)) ) {
 tom.engine.adt.tomoption.types.OptionList  tomMatch37__end__4=(( tom.engine.adt.tomoption.types.OptionList )optionList);
do {
{
if (!( tomMatch37__end__4.isEmptyconcOption() )) {
if ( ( tomMatch37__end__4.getHeadconcOption()  instanceof tom.engine.adt.tomoption.types.option.Constant) ) {
return true; 
}
}
if ( tomMatch37__end__4.isEmptyconcOption() ) {
tomMatch37__end__4=(( tom.engine.adt.tomoption.types.OptionList )optionList);
} else {
tomMatch37__end__4= tomMatch37__end__4.getTailconcOption() ;
}

}
} while(!( (tomMatch37__end__4==(( tom.engine.adt.tomoption.types.OptionList )optionList)) ));
}
}

}

}

return false;
}

public static boolean hasDefinedSymbol(OptionList optionList) {

{
{
if ( (optionList instanceof tom.engine.adt.tomoption.types.OptionList) ) {
if ( (((( tom.engine.adt.tomoption.types.OptionList )optionList) instanceof tom.engine.adt.tomoption.types.optionlist.ConsconcOption) || ((( tom.engine.adt.tomoption.types.OptionList )optionList) instanceof tom.engine.adt.tomoption.types.optionlist.EmptyconcOption)) ) {
 tom.engine.adt.tomoption.types.OptionList  tomMatch38__end__4=(( tom.engine.adt.tomoption.types.OptionList )optionList);
do {
{
if (!( tomMatch38__end__4.isEmptyconcOption() )) {
if ( ( tomMatch38__end__4.getHeadconcOption()  instanceof tom.engine.adt.tomoption.types.option.DefinedSymbol) ) {
return true; 
}
}
if ( tomMatch38__end__4.isEmptyconcOption() ) {
tomMatch38__end__4=(( tom.engine.adt.tomoption.types.OptionList )optionList);
} else {
tomMatch38__end__4= tomMatch38__end__4.getTailconcOption() ;
}

}
} while(!( (tomMatch38__end__4==(( tom.engine.adt.tomoption.types.OptionList )optionList)) ));
}
}

}

}

return false;
}

public static boolean hasImplicitXMLAttribut(OptionList optionList) {

{
{
if ( (optionList instanceof tom.engine.adt.tomoption.types.OptionList) ) {
if ( (((( tom.engine.adt.tomoption.types.OptionList )optionList) instanceof tom.engine.adt.tomoption.types.optionlist.ConsconcOption) || ((( tom.engine.adt.tomoption.types.OptionList )optionList) instanceof tom.engine.adt.tomoption.types.optionlist.EmptyconcOption)) ) {
 tom.engine.adt.tomoption.types.OptionList  tomMatch39__end__4=(( tom.engine.adt.tomoption.types.OptionList )optionList);
do {
{
if (!( tomMatch39__end__4.isEmptyconcOption() )) {
if ( ( tomMatch39__end__4.getHeadconcOption()  instanceof tom.engine.adt.tomoption.types.option.ImplicitXMLAttribut) ) {
return true; 
}
}
if ( tomMatch39__end__4.isEmptyconcOption() ) {
tomMatch39__end__4=(( tom.engine.adt.tomoption.types.OptionList )optionList);
} else {
tomMatch39__end__4= tomMatch39__end__4.getTailconcOption() ;
}

}
} while(!( (tomMatch39__end__4==(( tom.engine.adt.tomoption.types.OptionList )optionList)) ));
}
}

}

}

return false;
}

public static boolean hasImplicitXMLChild(OptionList optionList) {

{
{
if ( (optionList instanceof tom.engine.adt.tomoption.types.OptionList) ) {
if ( (((( tom.engine.adt.tomoption.types.OptionList )optionList) instanceof tom.engine.adt.tomoption.types.optionlist.ConsconcOption) || ((( tom.engine.adt.tomoption.types.OptionList )optionList) instanceof tom.engine.adt.tomoption.types.optionlist.EmptyconcOption)) ) {
 tom.engine.adt.tomoption.types.OptionList  tomMatch40__end__4=(( tom.engine.adt.tomoption.types.OptionList )optionList);
do {
{
if (!( tomMatch40__end__4.isEmptyconcOption() )) {
if ( ( tomMatch40__end__4.getHeadconcOption()  instanceof tom.engine.adt.tomoption.types.option.ImplicitXMLChild) ) {
return true; 
}
}
if ( tomMatch40__end__4.isEmptyconcOption() ) {
tomMatch40__end__4=(( tom.engine.adt.tomoption.types.OptionList )optionList);
} else {
tomMatch40__end__4= tomMatch40__end__4.getTailconcOption() ;
}

}
} while(!( (tomMatch40__end__4==(( tom.engine.adt.tomoption.types.OptionList )optionList)) ));
}
}

}

}

return false;
}

public static TomName getSlotName(TomSymbol symbol, int number) {
PairNameDeclList pairNameDeclList = symbol.getPairNameDeclList();
for(int index = 0; !pairNameDeclList.isEmptyconcPairNameDecl() && index<number ; index++) {
pairNameDeclList = pairNameDeclList.getTailconcPairNameDecl();
}
if(pairNameDeclList.isEmptyconcPairNameDecl()) {
System.out.println("getSlotName: bad index error");
throw new TomRuntimeException("getSlotName: bad index error");
}
PairNameDecl pairNameDecl = pairNameDeclList.getHeadconcPairNameDecl();

Declaration decl = pairNameDecl.getSlotDecl();

{
{
if ( (decl instanceof tom.engine.adt.tomdeclaration.types.Declaration) ) {
if ( ((( tom.engine.adt.tomdeclaration.types.Declaration )decl) instanceof tom.engine.adt.tomdeclaration.types.declaration.GetSlotDecl) ) {
return 
 (( tom.engine.adt.tomdeclaration.types.Declaration )decl).getSlotName() ; 

}
}

}

}


return pairNameDecl.getSlotName();
}

public static int getSlotIndex(TomSymbol tomSymbol, TomName slotName) {
int index = 0;
PairNameDeclList pairNameDeclList = tomSymbol.getPairNameDeclList();
ArrayList<String> nameList = new ArrayList<String>();
while(!pairNameDeclList.isEmptyconcPairNameDecl()) {
TomName name = pairNameDeclList.getHeadconcPairNameDecl().getSlotName();
// System.out.println("index = " + index + " name = " + name);
if(slotName.equals(name)) {
return index;
}
nameList.add(name.getString());
pairNameDeclList = pairNameDeclList.getTailconcPairNameDecl();
index++;
}
TomMessage.error(logger, null, 0, TomMessage.badSlotName, 
slotName.getString(), tomSymbol, nameList.toArray());
/*    throw new TomRuntimeException("getSlotIndex: bad slotName error. Found '"
+ slotName.getString() + "' but expected one of the following names: "
+ Arrays.toString(nameList.toArray()) + ".");*/
return -1;
}

public static TomType elementAt(TomTypeList l, int index) {
if (0 > index || index > l.length()) {
throw new IllegalArgumentException("illegal list index: " + index);
}
for (int i = 0; i < index; i++) {
l = l.getTailconcTomType();
}
return l.getHeadconcTomType();
}

public static TomType getSlotType(TomSymbol symbol, TomName slotName) {

{
{
if ( (symbol instanceof tom.engine.adt.tomsignature.types.TomSymbol) ) {
if ( ((( tom.engine.adt.tomsignature.types.TomSymbol )symbol) instanceof tom.engine.adt.tomsignature.types.tomsymbol.Symbol) ) {
 tom.engine.adt.tomtype.types.TomType  tomMatch42_1= (( tom.engine.adt.tomsignature.types.TomSymbol )symbol).getTypesToType() ;
if ( (tomMatch42_1 instanceof tom.engine.adt.tomtype.types.tomtype.TypesToType) ) {

int index = getSlotIndex(symbol,slotName);
return elementAt(
 tomMatch42_1.getDomain() ,index);


}
}
}

}

}

return null;
//throw new TomRuntimeException("getSlotType: bad slotName error: " + slotName);
}

public static boolean isDefinedSymbol(TomSymbol subject) {
/*This test seems to be useless
if(subject==null) {
System.out.println("isDefinedSymbol: subject == null");
return false;
}*/

{
{
if ( (subject instanceof tom.engine.adt.tomsignature.types.TomSymbol) ) {
if ( ((( tom.engine.adt.tomsignature.types.TomSymbol )subject) instanceof tom.engine.adt.tomsignature.types.tomsymbol.Symbol) ) {

return hasDefinedSymbol(
 (( tom.engine.adt.tomsignature.types.TomSymbol )subject).getOptions() );


}
}

}

}

return false;
}

public static boolean isDefinedGetSlot(TomSymbol symbol, TomName slotName) {
if(symbol==null) {
//System.out.println("isDefinedSymbol: symbol == null");
return false;
}

{
{
if ( (symbol instanceof tom.engine.adt.tomsignature.types.TomSymbol) ) {
if ( ((( tom.engine.adt.tomsignature.types.TomSymbol )symbol) instanceof tom.engine.adt.tomsignature.types.tomsymbol.Symbol) ) {
 tom.engine.adt.tomslot.types.PairNameDeclList  tomMatch44_1= (( tom.engine.adt.tomsignature.types.TomSymbol )symbol).getPairNameDeclList() ;
if ( ((tomMatch44_1 instanceof tom.engine.adt.tomslot.types.pairnamedecllist.ConsconcPairNameDecl) || (tomMatch44_1 instanceof tom.engine.adt.tomslot.types.pairnamedecllist.EmptyconcPairNameDecl)) ) {
 tom.engine.adt.tomslot.types.PairNameDeclList  tomMatch44__end__6=tomMatch44_1;
do {
{
if (!( tomMatch44__end__6.isEmptyconcPairNameDecl() )) {
 tom.engine.adt.tomslot.types.PairNameDecl  tomMatch44_11= tomMatch44__end__6.getHeadconcPairNameDecl() ;
if ( (tomMatch44_11 instanceof tom.engine.adt.tomslot.types.pairnamedecl.PairNameDecl) ) {

if(
 tomMatch44_11.getSlotName() ==slotName && 
 tomMatch44_11.getSlotDecl() !=
 tom.engine.adt.tomdeclaration.types.declaration.EmptyDeclaration.make() ) {
return true;
}


}
}
if ( tomMatch44__end__6.isEmptyconcPairNameDecl() ) {
tomMatch44__end__6=tomMatch44_1;
} else {
tomMatch44__end__6= tomMatch44__end__6.getTailconcPairNameDecl() ;
}

}
} while(!( (tomMatch44__end__6==tomMatch44_1) ));
}
}
}

}

}

return false;
}


/**
* Return the option containing OriginTracking information
*/
public static Option findOriginTracking(OptionList optionList) {
if(optionList.isEmptyconcOption()) {
return 
 tom.engine.adt.tomoption.types.option.noOption.make() ;
}
while(!optionList.isEmptyconcOption()) {
Option subject = optionList.getHeadconcOption();

{
{
if ( (subject instanceof tom.engine.adt.tomoption.types.Option) ) {
if ( ((( tom.engine.adt.tomoption.types.Option )subject) instanceof tom.engine.adt.tomoption.types.option.OriginTracking) ) {

return 
(( tom.engine.adt.tomoption.types.Option )subject);


}
}

}

}

optionList = optionList.getTailconcOption();
}
System.out.println("findOriginTracking:  not found" + optionList);
throw new TomRuntimeException("findOriginTracking:  not found" + optionList);
}

public static TomSymbol getSymbolFromName(String tomName, SymbolTable symbolTable) {
return symbolTable.getSymbolFromName(tomName);
}

public static TomSymbol getSymbolFromType(TomType tomType, SymbolTable symbolTable) {
if ( SymbolTable.TYPE_UNKNOWN == tomType) { return null; }

TomSymbolList list = symbolTable.getSymbolFromType(tomType);
TomSymbolList filteredList = 
 tom.engine.adt.tomsignature.types.tomsymbollist.EmptyconcTomSymbol.make() ;
// Not necessary since checker ensure the uniqueness of the symbol
while(!list.isEmptyconcTomSymbol()) {
TomSymbol head = list.getHeadconcTomSymbol();
if(isArrayOperator(head) || isListOperator(head)) {
filteredList = 
 tom.engine.adt.tomsignature.types.tomsymbollist.ConsconcTomSymbol.make(head,tom_append_list_concTomSymbol(filteredList, tom.engine.adt.tomsignature.types.tomsymbollist.EmptyconcTomSymbol.make() )) ;
}
list = list.getTailconcTomSymbol();
}
if(filteredList.isEmptyconcTomSymbol()) {
return null;
} else {
return filteredList.getHeadconcTomSymbol();
}
}

public static TomType getTermType(TomTerm t, SymbolTable symbolTable) {

{
{
if ( (t instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
boolean tomMatch46_6= false ;
 tom.engine.adt.tomname.types.TomNameList  tomMatch46_1= null ;
if ( ((( tom.engine.adt.tomterm.types.TomTerm )t) instanceof tom.engine.adt.tomterm.types.tomterm.TermAppl) ) {
{
tomMatch46_6= true ;
tomMatch46_1= (( tom.engine.adt.tomterm.types.TomTerm )t).getNameList() ;

}
} else {
if ( ((( tom.engine.adt.tomterm.types.TomTerm )t) instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {
{
tomMatch46_6= true ;
tomMatch46_1= (( tom.engine.adt.tomterm.types.TomTerm )t).getNameList() ;

}
}
}
if (tomMatch46_6) {
if ( ((tomMatch46_1 instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || (tomMatch46_1 instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {
if (!( tomMatch46_1.isEmptyconcTomName() )) {
 tom.engine.adt.tomname.types.TomName  tom_headName= tomMatch46_1.getHeadconcTomName() ;

String tomName = null;
if(
(tom_headName)instanceof AntiName) {
tomName = ((AntiName)
tom_headName).getName().getString();
} else {
tomName = ((TomName)
tom_headName).getString();
}
TomSymbol tomSymbol = symbolTable.getSymbolFromName(tomName);
if(tomSymbol!=null) {
return tomSymbol.getTypesToType().getCodomain();
} else {
return 
 tom.engine.adt.tomtype.types.tomtype.EmptyType.make() ;
}


}
}
}

}

}
{
if ( (t instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
boolean tomMatch46_10= false ;
 tom.engine.adt.tomtype.types.TomType  tomMatch46_8= null ;
if ( ((( tom.engine.adt.tomterm.types.TomTerm )t) instanceof tom.engine.adt.tomterm.types.tomterm.Variable) ) {
{
tomMatch46_10= true ;
tomMatch46_8= (( tom.engine.adt.tomterm.types.TomTerm )t).getAstType() ;

}
} else {
if ( ((( tom.engine.adt.tomterm.types.TomTerm )t) instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) {
{
tomMatch46_10= true ;
tomMatch46_8= (( tom.engine.adt.tomterm.types.TomTerm )t).getAstType() ;

}
}
}
if (tomMatch46_10) {

return 
tomMatch46_8;


}

}

}
{
if ( (t instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
if ( ((( tom.engine.adt.tomterm.types.TomTerm )t) instanceof tom.engine.adt.tomterm.types.tomterm.AntiTerm) ) {
return getTermType(
 (( tom.engine.adt.tomterm.types.TomTerm )t).getTomTerm() ,symbolTable);

}
}

}


}

//System.out.println("getTermType error on term: " + t);
//throw new TomRuntimeException("getTermType error on term: " + t);
return 
 tom.engine.adt.tomtype.types.tomtype.EmptyType.make() ;
}

public static TomType getTermType(BQTerm t, SymbolTable symbolTable) {

{
{
if ( (t instanceof tom.engine.adt.code.types.BQTerm) ) {
boolean tomMatch47_3= false ;
 tom.engine.adt.tomtype.types.TomType  tomMatch47_1= null ;
if ( ((( tom.engine.adt.code.types.BQTerm )t) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {
{
tomMatch47_3= true ;
tomMatch47_1= (( tom.engine.adt.code.types.BQTerm )t).getAstType() ;

}
} else {
if ( ((( tom.engine.adt.code.types.BQTerm )t) instanceof tom.engine.adt.code.types.bqterm.BQVariableStar) ) {
{
tomMatch47_3= true ;
tomMatch47_1= (( tom.engine.adt.code.types.BQTerm )t).getAstType() ;

}
}
}
if (tomMatch47_3) {

return 
tomMatch47_1;


}

}

}
{
if ( (t instanceof tom.engine.adt.code.types.BQTerm) ) {
if ( ((( tom.engine.adt.code.types.BQTerm )t) instanceof tom.engine.adt.code.types.bqterm.FunctionCall) ) {
return 
 (( tom.engine.adt.code.types.BQTerm )t).getAstType() ; 

}
}

}
{
if ( (t instanceof tom.engine.adt.code.types.BQTerm) ) {
if ( ((( tom.engine.adt.code.types.BQTerm )t) instanceof tom.engine.adt.code.types.bqterm.ExpressionToBQTerm) ) {
return getTermType(
 (( tom.engine.adt.code.types.BQTerm )t).getExp() ,symbolTable); 

}
}

}
{
if ( (t instanceof tom.engine.adt.code.types.BQTerm) ) {
if ( ((( tom.engine.adt.code.types.BQTerm )t) instanceof tom.engine.adt.code.types.bqterm.ListHead) ) {
return 
 (( tom.engine.adt.code.types.BQTerm )t).getCodomain() ; 

}
}

}
{
if ( (t instanceof tom.engine.adt.code.types.BQTerm) ) {
if ( ((( tom.engine.adt.code.types.BQTerm )t) instanceof tom.engine.adt.code.types.bqterm.ListTail) ) {
return getTermType(
 (( tom.engine.adt.code.types.BQTerm )t).getVariable() , symbolTable); 

}
}

}
{
if ( (t instanceof tom.engine.adt.code.types.BQTerm) ) {
if ( ((( tom.engine.adt.code.types.BQTerm )t) instanceof tom.engine.adt.code.types.bqterm.Subterm) ) {
 tom.engine.adt.tomname.types.TomName  tomMatch47_17= (( tom.engine.adt.code.types.BQTerm )t).getAstName() ;
if ( (tomMatch47_17 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {

TomSymbol tomSymbol = symbolTable.getSymbolFromName(
 tomMatch47_17.getString() );
return getSlotType(tomSymbol, 
 (( tom.engine.adt.code.types.BQTerm )t).getSlotName() );


}
}
}

}


}

//System.out.println("getTermType error on term: " + t);
//throw new TomRuntimeException("getTermType error on term: " + t);
return 
 tom.engine.adt.tomtype.types.tomtype.EmptyType.make() ;
}

public static TomSymbol getSymbolFromTerm(TomTerm t, SymbolTable symbolTable) {

{
{
if ( (t instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
boolean tomMatch48_6= false ;
 tom.engine.adt.tomname.types.TomNameList  tomMatch48_1= null ;
if ( ((( tom.engine.adt.tomterm.types.TomTerm )t) instanceof tom.engine.adt.tomterm.types.tomterm.TermAppl) ) {
{
tomMatch48_6= true ;
tomMatch48_1= (( tom.engine.adt.tomterm.types.TomTerm )t).getNameList() ;

}
} else {
if ( ((( tom.engine.adt.tomterm.types.TomTerm )t) instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {
{
tomMatch48_6= true ;
tomMatch48_1= (( tom.engine.adt.tomterm.types.TomTerm )t).getNameList() ;

}
}
}
if (tomMatch48_6) {
if ( ((tomMatch48_1 instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || (tomMatch48_1 instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {
if (!( tomMatch48_1.isEmptyconcTomName() )) {
 tom.engine.adt.tomname.types.TomName  tom_headName= tomMatch48_1.getHeadconcTomName() ;

String tomName = null;
if(
(tom_headName)instanceof AntiName) {
tomName = ((AntiName)
tom_headName).getName().getString();
} else {
tomName = ((TomName)
tom_headName).getString();
}
return symbolTable.getSymbolFromName(tomName);


}
}
}

}

}
{
if ( (t instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
boolean tomMatch48_12= false ;
 tom.engine.adt.tomname.types.TomName  tomMatch48_8= null ;
if ( ((( tom.engine.adt.tomterm.types.TomTerm )t) instanceof tom.engine.adt.tomterm.types.tomterm.Variable) ) {
{
tomMatch48_12= true ;
tomMatch48_8= (( tom.engine.adt.tomterm.types.TomTerm )t).getAstName() ;

}
} else {
if ( ((( tom.engine.adt.tomterm.types.TomTerm )t) instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) {
{
tomMatch48_12= true ;
tomMatch48_8= (( tom.engine.adt.tomterm.types.TomTerm )t).getAstName() ;

}
}
}
if (tomMatch48_12) {
if ( (tomMatch48_8 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {

return symbolTable.getSymbolFromName(
 tomMatch48_8.getString() );


}
}

}

}
{
if ( (t instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
if ( ((( tom.engine.adt.tomterm.types.TomTerm )t) instanceof tom.engine.adt.tomterm.types.tomterm.AntiTerm) ) {
return getSymbolFromTerm(
 (( tom.engine.adt.tomterm.types.TomTerm )t).getTomTerm() ,symbolTable);

}
}

}


}

return null;
}

public static TomSymbol getSymbolFromTerm(BQTerm t, SymbolTable symbolTable) {

{
{
if ( (t instanceof tom.engine.adt.code.types.BQTerm) ) {
boolean tomMatch49_5= false ;
 tom.engine.adt.tomname.types.TomName  tomMatch49_1= null ;
if ( ((( tom.engine.adt.code.types.BQTerm )t) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {
{
tomMatch49_5= true ;
tomMatch49_1= (( tom.engine.adt.code.types.BQTerm )t).getAstName() ;

}
} else {
if ( ((( tom.engine.adt.code.types.BQTerm )t) instanceof tom.engine.adt.code.types.bqterm.BQVariableStar) ) {
{
tomMatch49_5= true ;
tomMatch49_1= (( tom.engine.adt.code.types.BQTerm )t).getAstName() ;

}
}
}
if (tomMatch49_5) {
if ( (tomMatch49_1 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {

return symbolTable.getSymbolFromName(
 tomMatch49_1.getString() );


}
}

}

}
{
if ( (t instanceof tom.engine.adt.code.types.BQTerm) ) {
if ( ((( tom.engine.adt.code.types.BQTerm )t) instanceof tom.engine.adt.code.types.bqterm.FunctionCall) ) {
 tom.engine.adt.tomname.types.TomName  tomMatch49_7= (( tom.engine.adt.code.types.BQTerm )t).getAstName() ;
if ( (tomMatch49_7 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {
return symbolTable.getSymbolFromName(
 tomMatch49_7.getString() ); 

}
}
}

}


}

return null;
}

public static TomType getTermType(Expression t, SymbolTable symbolTable) {

{
{
if ( (t instanceof tom.engine.adt.tomexpression.types.Expression) ) {
boolean tomMatch50_3= false ;
 tom.engine.adt.tomtype.types.TomType  tomMatch50_1= null ;
if ( ((( tom.engine.adt.tomexpression.types.Expression )t) instanceof tom.engine.adt.tomexpression.types.expression.GetHead) ) {
{
tomMatch50_3= true ;
tomMatch50_1= (( tom.engine.adt.tomexpression.types.Expression )t).getCodomain() ;

}
} else {
if ( ((( tom.engine.adt.tomexpression.types.Expression )t) instanceof tom.engine.adt.tomexpression.types.expression.GetSlot) ) {
{
tomMatch50_3= true ;
tomMatch50_1= (( tom.engine.adt.tomexpression.types.Expression )t).getCodomain() ;

}
} else {
if ( ((( tom.engine.adt.tomexpression.types.Expression )t) instanceof tom.engine.adt.tomexpression.types.expression.GetElement) ) {
{
tomMatch50_3= true ;
tomMatch50_1= (( tom.engine.adt.tomexpression.types.Expression )t).getCodomain() ;

}
}
}
}
if (tomMatch50_3) {
return 
tomMatch50_1; 

}

}

}
{
if ( (t instanceof tom.engine.adt.tomexpression.types.Expression) ) {
if ( ((( tom.engine.adt.tomexpression.types.Expression )t) instanceof tom.engine.adt.tomexpression.types.expression.BQTermToExpression) ) {
return getTermType(
 (( tom.engine.adt.tomexpression.types.Expression )t).getAstTerm() , symbolTable); 

}
}

}
{
if ( (t instanceof tom.engine.adt.tomexpression.types.Expression) ) {
if ( ((( tom.engine.adt.tomexpression.types.Expression )t) instanceof tom.engine.adt.tomexpression.types.expression.GetTail) ) {
return getTermType(
 (( tom.engine.adt.tomexpression.types.Expression )t).getVariable() , symbolTable); 

}
}

}
{
if ( (t instanceof tom.engine.adt.tomexpression.types.Expression) ) {
if ( ((( tom.engine.adt.tomexpression.types.Expression )t) instanceof tom.engine.adt.tomexpression.types.expression.GetSliceList) ) {
return getTermType(
 (( tom.engine.adt.tomexpression.types.Expression )t).getVariableBeginAST() , symbolTable); 

}
}

}
{
if ( (t instanceof tom.engine.adt.tomexpression.types.Expression) ) {
if ( ((( tom.engine.adt.tomexpression.types.Expression )t) instanceof tom.engine.adt.tomexpression.types.expression.GetSliceArray) ) {
return getTermType(
 (( tom.engine.adt.tomexpression.types.Expression )t).getSubjectListName() , symbolTable); 

}
}

}
{
if ( (t instanceof tom.engine.adt.tomexpression.types.Expression) ) {
if ( ((( tom.engine.adt.tomexpression.types.Expression )t) instanceof tom.engine.adt.tomexpression.types.expression.Cast) ) {
return 
 (( tom.engine.adt.tomexpression.types.Expression )t).getAstType() ; 

}
}

}


}

System.out.println("getTermType error on term: " + t);
throw new TomRuntimeException("getTermType error on term: " + t);
}

public static SlotList tomListToSlotList(TomList tomList) {
return tomListToSlotList(tomList,1);
}

public static SlotList tomListToSlotList(TomList tomList, int index) {

{
{
if ( (tomList instanceof tom.engine.adt.tomterm.types.TomList) ) {
if ( (((( tom.engine.adt.tomterm.types.TomList )tomList) instanceof tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm) || ((( tom.engine.adt.tomterm.types.TomList )tomList) instanceof tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm)) ) {
if ( (( tom.engine.adt.tomterm.types.TomList )tomList).isEmptyconcTomTerm() ) {
return 
 tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() ; 

}
}
}

}
{
if ( (tomList instanceof tom.engine.adt.tomterm.types.TomList) ) {
if ( (((( tom.engine.adt.tomterm.types.TomList )tomList) instanceof tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm) || ((( tom.engine.adt.tomterm.types.TomList )tomList) instanceof tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm)) ) {
if (!( (( tom.engine.adt.tomterm.types.TomList )tomList).isEmptyconcTomTerm() )) {

TomName slotName = 
 tom.engine.adt.tomname.types.tomname.PositionName.make( tom.engine.adt.tomname.types.tomnumberlist.ConsconcTomNumber.make( tom.engine.adt.tomname.types.tomnumber.Position.make(index) , tom.engine.adt.tomname.types.tomnumberlist.EmptyconcTomNumber.make() ) ) ;
SlotList sl = tomListToSlotList(
 (( tom.engine.adt.tomterm.types.TomList )tomList).getTailconcTomTerm() ,index+1);
return 
 tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( tom.engine.adt.tomslot.types.slot.PairSlotAppl.make(slotName,  (( tom.engine.adt.tomterm.types.TomList )tomList).getHeadconcTomTerm() ) ,tom_append_list_concSlot(sl, tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() )) ;


}
}
}

}


}

throw new TomRuntimeException("tomListToSlotList: " + tomList);
}

public static SlotList mergeTomListWithSlotList(TomList tomList, SlotList slotList) {

{
{
if ( (tomList instanceof tom.engine.adt.tomterm.types.TomList) ) {
if ( (((( tom.engine.adt.tomterm.types.TomList )tomList) instanceof tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm) || ((( tom.engine.adt.tomterm.types.TomList )tomList) instanceof tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm)) ) {
if ( (( tom.engine.adt.tomterm.types.TomList )tomList).isEmptyconcTomTerm() ) {
if ( (slotList instanceof tom.engine.adt.tomslot.types.SlotList) ) {
if ( (((( tom.engine.adt.tomslot.types.SlotList )slotList) instanceof tom.engine.adt.tomslot.types.slotlist.ConsconcSlot) || ((( tom.engine.adt.tomslot.types.SlotList )slotList) instanceof tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot)) ) {
if ( (( tom.engine.adt.tomslot.types.SlotList )slotList).isEmptyconcSlot() ) {

return 
 tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() ;


}
}
}
}
}
}

}
{
if ( (tomList instanceof tom.engine.adt.tomterm.types.TomList) ) {
if ( (((( tom.engine.adt.tomterm.types.TomList )tomList) instanceof tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm) || ((( tom.engine.adt.tomterm.types.TomList )tomList) instanceof tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm)) ) {
if (!( (( tom.engine.adt.tomterm.types.TomList )tomList).isEmptyconcTomTerm() )) {
if ( (slotList instanceof tom.engine.adt.tomslot.types.SlotList) ) {
if ( (((( tom.engine.adt.tomslot.types.SlotList )slotList) instanceof tom.engine.adt.tomslot.types.slotlist.ConsconcSlot) || ((( tom.engine.adt.tomslot.types.SlotList )slotList) instanceof tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot)) ) {
if (!( (( tom.engine.adt.tomslot.types.SlotList )slotList).isEmptyconcSlot() )) {
 tom.engine.adt.tomslot.types.Slot  tomMatch52_13= (( tom.engine.adt.tomslot.types.SlotList )slotList).getHeadconcSlot() ;
if ( (tomMatch52_13 instanceof tom.engine.adt.tomslot.types.slot.PairSlotAppl) ) {

SlotList sl = mergeTomListWithSlotList(
 (( tom.engine.adt.tomterm.types.TomList )tomList).getTailconcTomTerm() ,
 (( tom.engine.adt.tomslot.types.SlotList )slotList).getTailconcSlot() );
return 
 tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( tom.engine.adt.tomslot.types.slot.PairSlotAppl.make( tomMatch52_13.getSlotName() ,  (( tom.engine.adt.tomterm.types.TomList )tomList).getHeadconcTomTerm() ) ,tom_append_list_concSlot(sl, tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() )) ;


}
}
}
}
}
}
}

}


}

throw new TomRuntimeException("mergeTomListWithSlotList: " + tomList + " and " + slotList);
}

public static BQTermList slotListToBQTermList(SlotList tomList) {

{
{
if ( (tomList instanceof tom.engine.adt.tomslot.types.SlotList) ) {
if ( (((( tom.engine.adt.tomslot.types.SlotList )tomList) instanceof tom.engine.adt.tomslot.types.slotlist.ConsconcSlot) || ((( tom.engine.adt.tomslot.types.SlotList )tomList) instanceof tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot)) ) {
if ( (( tom.engine.adt.tomslot.types.SlotList )tomList).isEmptyconcSlot() ) {
return 
 tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() ; 

}
}
}

}
{
if ( (tomList instanceof tom.engine.adt.tomslot.types.SlotList) ) {
if ( (((( tom.engine.adt.tomslot.types.SlotList )tomList) instanceof tom.engine.adt.tomslot.types.slotlist.ConsconcSlot) || ((( tom.engine.adt.tomslot.types.SlotList )tomList) instanceof tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot)) ) {
if (!( (( tom.engine.adt.tomslot.types.SlotList )tomList).isEmptyconcSlot() )) {
 tom.engine.adt.tomslot.types.Slot  tomMatch53_7= (( tom.engine.adt.tomslot.types.SlotList )tomList).getHeadconcSlot() ;
if ( (tomMatch53_7 instanceof tom.engine.adt.tomslot.types.slot.PairSlotAppl) ) {

BQTermList tl = slotListToBQTermList(
 (( tom.engine.adt.tomslot.types.SlotList )tomList).getTailconcSlot() );
return 
 tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make(convertFromVarToBQVar( tomMatch53_7.getAppl() ),tom_append_list_concBQTerm(tl, tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() )) ;


}
}
}
}

}


}

throw new TomRuntimeException("slotListToTomList: " + tomList);
}

public static int getArity(TomSymbol symbol) {
if (isListOperator(symbol) || isArrayOperator(symbol)) {
return 2;
} else {
return ((Collection) symbol.getPairNameDeclList()).size();
}
}

/**
* builds a BQVariable from a TomTerm Variable
*/
public static BQTerm convertFromVarToBQVar(TomTerm variable) {

{
{
if ( (variable instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
if ( ((( tom.engine.adt.tomterm.types.TomTerm )variable) instanceof tom.engine.adt.tomterm.types.tomterm.Variable) ) {

return 
 tom.engine.adt.code.types.bqterm.BQVariable.make( (( tom.engine.adt.tomterm.types.TomTerm )variable).getOptions() ,  (( tom.engine.adt.tomterm.types.TomTerm )variable).getAstName() ,  (( tom.engine.adt.tomterm.types.TomTerm )variable).getAstType() ) ;


}
}

}
{
if ( (variable instanceof tom.engine.adt.tomterm.types.TomTerm) ) {
if ( ((( tom.engine.adt.tomterm.types.TomTerm )variable) instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) {

return 
 tom.engine.adt.code.types.bqterm.BQVariableStar.make( (( tom.engine.adt.tomterm.types.TomTerm )variable).getOptions() ,  (( tom.engine.adt.tomterm.types.TomTerm )variable).getAstName() ,  (( tom.engine.adt.tomterm.types.TomTerm )variable).getAstType() ) ;


}
}

}


}

throw new TomRuntimeException("cannot convert into a bq variable the term "+variable);
}

/**
* builds a Variable from a BQVariable
*/
public static TomTerm convertFromBQVarToVar(BQTerm variable) {

{
{
if ( (variable instanceof tom.engine.adt.code.types.BQTerm) ) {
if ( ((( tom.engine.adt.code.types.BQTerm )variable) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {

return 
 tom.engine.adt.tomterm.types.tomterm.Variable.make( (( tom.engine.adt.code.types.BQTerm )variable).getOptions() ,  (( tom.engine.adt.code.types.BQTerm )variable).getAstName() ,  (( tom.engine.adt.code.types.BQTerm )variable).getAstType() ,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) ;


}
}

}
{
if ( (variable instanceof tom.engine.adt.code.types.BQTerm) ) {
if ( ((( tom.engine.adt.code.types.BQTerm )variable) instanceof tom.engine.adt.code.types.bqterm.BQVariableStar) ) {

return 
 tom.engine.adt.tomterm.types.tomterm.VariableStar.make( (( tom.engine.adt.code.types.BQTerm )variable).getOptions() ,  (( tom.engine.adt.code.types.BQTerm )variable).getAstName() ,  (( tom.engine.adt.code.types.BQTerm )variable).getAstType() ,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) ;


}
}

}


}

throw new TomRuntimeException("cannot convert into a variable the term "+variable);
}

} // class TomBase
