/*
*
* TOM - To One Matching Compiler
* 
* Copyright (c) 2000-2011, INPL, INRIA
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
import tom.engine.adt.tomsignature.types.*;
import tom.engine.adt.tomoption.types.*;
import tom.engine.adt.tomtype.types.*;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomname.types.*;
import tom.engine.adt.tomterm.types.tomterm.*;
import tom.engine.adt.code.types.*;
import tom.library.sl.*;
import tom.engine.adt.tomslot.types.*;
import tom.engine.compiler.*;
import tom.engine.TomBase;
import tom.engine.exception.TomRuntimeException;
import java.util.ArrayList;
import tom.engine.compiler.Compiler;
import tom.engine.tools.TomConstraintPrettyPrinter;
import tom.engine.tools.ASTFactory;

/**
* AC propagator
*/
public class ACPropagator implements IBasePropagator {

//--------------------------------------------------------	


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
  
  private static   tom.engine.adt.tomconstraint.types.Constraint  tom_append_list_AndConstraint( tom.engine.adt.tomconstraint.types.Constraint  l1,  tom.engine.adt.tomconstraint.types.Constraint  l2) {
    if( l1.isEmptyAndConstraint() ) {
      return l2;
    } else if( l2.isEmptyAndConstraint() ) {
      return l1;
    } else if( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) ) {
      if(  l1.getTailAndConstraint() .isEmptyAndConstraint() ) {
        return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( l1.getHeadAndConstraint() ,l2) ;
      } else {
        return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( l1.getHeadAndConstraint() ,tom_append_list_AndConstraint( l1.getTailAndConstraint() ,l2)) ;
      }
    } else {
      return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make(l1,l2) ;
    }
  }
  private static   tom.engine.adt.tomconstraint.types.Constraint  tom_get_slice_AndConstraint( tom.engine.adt.tomconstraint.types.Constraint  begin,  tom.engine.adt.tomconstraint.types.Constraint  end, tom.engine.adt.tomconstraint.types.Constraint  tail) {
    if( (begin==end) ) {
      return tail;
    } else if( (end==tail)  && ( end.isEmptyAndConstraint()  ||  (end== tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? begin.getHeadAndConstraint() :begin),( tom.engine.adt.tomconstraint.types.Constraint )tom_get_slice_AndConstraint((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? begin.getTailAndConstraint() : tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ),end,tail)) ;
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
    } else if(( l1 instanceof tom.library.sl.Sequence )) {
      if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ) == null )) {
        return  tom.library.sl.Sequence.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ),l2) ;
      } else {
        return  tom.library.sl.Sequence.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ),tom_append_list_Sequence(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ),l2)) ;
      }
    } else {
      return  tom.library.sl.Sequence.make(l1,l2) ;
    }
  }
  private static   tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {
    if( (begin.equals(end)) ) {
      return tail;
    } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals( null )) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.library.sl.Sequence.make(((( begin instanceof tom.library.sl.Sequence ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(((( begin instanceof tom.library.sl.Sequence ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ): null ),end,tail)) ;
  }
  
  private static   tom.library.sl.Strategy  tom_append_list_Choice( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {
    if(( l1 ==null )) {
      return l2;
    } else if(( l2 ==null )) {
      return l1;
    } else if(( l1 instanceof tom.library.sl.Choice )) {
      if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ) ==null )) {
        return  tom.library.sl.Choice.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ),l2) ;
      } else {
        return  tom.library.sl.Choice.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ),tom_append_list_Choice(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ),l2)) ;
      }
    } else {
      return  tom.library.sl.Choice.make(l1,l2) ;
    }
  }
  private static   tom.library.sl.Strategy  tom_get_slice_Choice( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {
    if( (begin.equals(end)) ) {
      return tail;
    } else if( (end.equals(tail))  && (( end ==null ) ||  (end.equals( null )) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.library.sl.Choice.make(((( begin instanceof tom.library.sl.Choice ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Choice(((( begin instanceof tom.library.sl.Choice ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.THEN) ): null ),end,tail)) ;
  }
  
  private static   tom.library.sl.Strategy  tom_append_list_SequenceId( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {
    if(( l1 == null )) {
      return l2;
    } else if(( l2 == null )) {
      return l1;
    } else if(( l1 instanceof tom.library.sl.SequenceId )) {
      if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.THEN) ) == null )) {
        return  tom.library.sl.SequenceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.FIRST) ),l2) ;
      } else {
        return  tom.library.sl.SequenceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.FIRST) ),tom_append_list_SequenceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.THEN) ),l2)) ;
      }
    } else {
      return  tom.library.sl.SequenceId.make(l1,l2) ;
    }
  }
  private static   tom.library.sl.Strategy  tom_get_slice_SequenceId( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {
    if( (begin.equals(end)) ) {
      return tail;
    } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals( null )) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.library.sl.SequenceId.make(((( begin instanceof tom.library.sl.SequenceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.SequenceId.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_SequenceId(((( begin instanceof tom.library.sl.SequenceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.SequenceId.THEN) ): null ),end,tail)) ;
  }
  
  private static   tom.library.sl.Strategy  tom_append_list_ChoiceId( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {
    if(( l1 ==null )) {
      return l2;
    } else if(( l2 ==null )) {
      return l1;
    } else if(( l1 instanceof tom.library.sl.ChoiceId )) {
      if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.THEN) ) ==null )) {
        return  tom.library.sl.ChoiceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ),l2) ;
      } else {
        return  tom.library.sl.ChoiceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ),tom_append_list_ChoiceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.THEN) ),l2)) ;
      }
    } else {
      return  tom.library.sl.ChoiceId.make(l1,l2) ;
    }
  }
  private static   tom.library.sl.Strategy  tom_get_slice_ChoiceId( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {
    if( (begin.equals(end)) ) {
      return tail;
    } else if( (end.equals(tail))  && (( end ==null ) ||  (end.equals( null )) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.library.sl.ChoiceId.make(((( begin instanceof tom.library.sl.ChoiceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_ChoiceId(((( begin instanceof tom.library.sl.ChoiceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.THEN) ): null ),end,tail)) ;
  }
  private static  tom.library.sl.Strategy  tom_make_AUCtl( tom.library.sl.Strategy  s1,  tom.library.sl.Strategy  s2) { 
return ( 
( new tom.library.sl.Mu(( new tom.library.sl.MuVar("x") ), tom.library.sl.Choice.make(s2, tom.library.sl.Choice.make( tom.library.sl.Sequence.make( tom.library.sl.Sequence.make(s1, tom.library.sl.Sequence.make(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ), null ) ) , tom.library.sl.Sequence.make(( new tom.library.sl.One(( new tom.library.sl.Identity() )) ), null ) ) , null ) ) ) ))

;
}
private static  tom.library.sl.Strategy  tom_make_EUCtl( tom.library.sl.Strategy  s1,  tom.library.sl.Strategy  s2) { 
return ( 
( new tom.library.sl.Mu(( new tom.library.sl.MuVar("x") ), tom.library.sl.Choice.make(s2, tom.library.sl.Choice.make( tom.library.sl.Sequence.make(s1, tom.library.sl.Sequence.make(( new tom.library.sl.One(( new tom.library.sl.MuVar("x") )) ), null ) ) , null ) ) ) ))

;
}
private static  tom.library.sl.Strategy  tom_make_Try( tom.library.sl.Strategy  s) { 
return ( 
 tom.library.sl.Choice.make(s, tom.library.sl.Choice.make(( new tom.library.sl.Identity() ), null ) ) )

;
}
private static  tom.library.sl.Strategy  tom_make_Repeat( tom.library.sl.Strategy  s) { 
return ( 
( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.Choice.make( tom.library.sl.Sequence.make(s, tom.library.sl.Sequence.make(( new tom.library.sl.MuVar("_x") ), null ) ) , tom.library.sl.Choice.make(( new tom.library.sl.Identity() ), null ) ) ) ))

;
}
private static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { 
return ( 
( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.Sequence.make(v, tom.library.sl.Sequence.make(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ), null ) ) ) ))

;
}
private static  tom.library.sl.Strategy  tom_make_OnceTopDown( tom.library.sl.Strategy  v) { 
return ( 
( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.Choice.make(v, tom.library.sl.Choice.make(( new tom.library.sl.One(( new tom.library.sl.MuVar("_x") )) ), null ) ) ) ))

;
}
private static  tom.library.sl.Strategy  tom_make_RepeatId( tom.library.sl.Strategy  v) { 
return ( 
( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.SequenceId.make(v, tom.library.sl.SequenceId.make(( new tom.library.sl.MuVar("_x") ), null ) ) ) ))

;
}
private static  tom.library.sl.Strategy  tom_make_OnceTopDownId( tom.library.sl.Strategy  v) { 
return ( 
( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.ChoiceId.make(v, tom.library.sl.ChoiceId.make(( new tom.library.sl.OneId(( new tom.library.sl.MuVar("_x") )) ), null ) ) ) ))

;
}

//--------------------------------------------------------




private Compiler compiler;  
private ConstraintPropagator constraintPropagator; 

public Compiler getCompiler() {
return this.compiler;
}

public ACPropagator(Compiler compiler, ConstraintPropagator constraintPropagator) {
this.compiler = compiler;
this.constraintPropagator = constraintPropagator;
}

public Constraint propagate(Constraint constraint) throws VisitFailure {
Constraint result = constraint;
result = 
tom_make_RepeatId(tom_make_TopDown(tom_make_RemoveNonVariableStar(this))).visitLight(result);		
result = 
tom_make_RepeatId(tom_make_TopDown(tom_make_RemoveNonLinearVariableStar(this))).visitLight(result);		
result = 
tom_make_RepeatId(tom_make_TopDown(tom_make_PerformAbstraction(this))).visitLight(result);		
result = 
tom_make_TopDown(tom_make_CleanSingleVariable()).visitLight(result);		
//Constraint res = `TopDownWhenConstraint(ACMatching(this)).visitLight(constraint);		
if(result!=constraint) {
/*System.out.println("after propagate:");*/
System.out.println(TomConstraintPrettyPrinter.prettyPrint(result));
System.out.println();
}
return result;
}	

/**
* remove a term which is not a VariableStar
* f(t,...) <<ac s -> (X1*,t,X2*) <<a s /\ f(...) <<ac f(X1*,X2*)
*/

public static class RemoveNonVariableStar extends tom.library.sl.AbstractStrategyBasic {
private  ACPropagator  acp;
public RemoveNonVariableStar( ACPropagator  acp) {
super(( new tom.library.sl.Identity() ));
this.acp=acp;
}
public  ACPropagator  getacp() {
return acp;
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
public <T> T visitLight(T v, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
if ( (v instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {
return ((T)visit_Constraint((( tom.engine.adt.tomconstraint.types.Constraint )v),introspector));
}
if (!(( null  == environment))) {
return ((T)any.visit(environment,introspector));
} else {
return any.visitLight(v,introspector);
}

}
@SuppressWarnings("unchecked")
public  tom.engine.adt.tomconstraint.types.Constraint  _visit_Constraint( tom.engine.adt.tomconstraint.types.Constraint  arg, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
if (!(( null  == environment))) {
return (( tom.engine.adt.tomconstraint.types.Constraint )any.visit(environment,introspector));
} else {
return any.visitLight(arg,introspector);
}
}
@SuppressWarnings("unchecked")
public  tom.engine.adt.tomconstraint.types.Constraint  visit_Constraint( tom.engine.adt.tomconstraint.types.Constraint  tom__arg, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
{
{
if ( (tom__arg instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {
if ( ((( tom.engine.adt.tomconstraint.types.Constraint )tom__arg) instanceof tom.engine.adt.tomconstraint.types.constraint.MatchConstraint) ) {
 tom.engine.adt.tomterm.types.TomTerm  tomMatch207_1= (( tom.engine.adt.tomconstraint.types.Constraint )tom__arg).getPattern() ;
if ( (tomMatch207_1 instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {
 tom.engine.adt.tomoption.types.OptionList  tomMatch207_5= tomMatch207_1.getOptions() ;
 tom.engine.adt.tomname.types.TomNameList  tomMatch207_6= tomMatch207_1.getNameList() ;
if ( ((tomMatch207_5 instanceof tom.engine.adt.tomoption.types.optionlist.ConsconcOption) || (tomMatch207_5 instanceof tom.engine.adt.tomoption.types.optionlist.EmptyconcOption)) ) {
 tom.engine.adt.tomoption.types.OptionList  tomMatch207__end__12=tomMatch207_5;
do {
{
if (!( tomMatch207__end__12.isEmptyconcOption() )) {
 tom.engine.adt.tomoption.types.Option  tomMatch207_18= tomMatch207__end__12.getHeadconcOption() ;
if ( (tomMatch207_18 instanceof tom.engine.adt.tomoption.types.option.MatchingTheory) ) {
 tom.engine.adt.theory.types.Theory  tomMatch207_17= tomMatch207_18.getTheory() ;
if ( ((tomMatch207_17 instanceof tom.engine.adt.theory.types.theory.ConsconcElementaryTheory) || (tomMatch207_17 instanceof tom.engine.adt.theory.types.theory.EmptyconcElementaryTheory)) ) {
 tom.engine.adt.theory.types.Theory  tomMatch207__end__24=tomMatch207_17;
do {
{
if (!( tomMatch207__end__24.isEmptyconcElementaryTheory() )) {
if ( ( tomMatch207__end__24.getHeadconcElementaryTheory()  instanceof tom.engine.adt.theory.types.elementarytheory.AC) ) {
if ( ((tomMatch207_6 instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || (tomMatch207_6 instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {
 tom.engine.adt.tomname.types.TomNameList  tom_namelist=tomMatch207_6;
if (!( tomMatch207_6.isEmptyconcTomName() )) {
 tom.engine.adt.tomname.types.TomName  tomMatch207_20= tomMatch207_6.getHeadconcTomName() ;
if ( (tomMatch207_20 instanceof tom.engine.adt.tomname.types.tomname.Name) ) {
 String  tom_tomName= tomMatch207_20.getString() ;
if (  tomMatch207_6.getTailconcTomName() .isEmptyconcTomName() ) {
 tom.engine.adt.tomslot.types.SlotList  tom_slots= tomMatch207_1.getSlots() ;
 tom.engine.adt.tomtype.types.TomType  tom_aType= (( tom.engine.adt.tomconstraint.types.Constraint )tom__arg).getAstType() ;

OptionList optWithoutAC = 
tom_append_list_concOption(tom_get_slice_concOption(tomMatch207_5,tomMatch207__end__12, tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() ), tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( tom.engine.adt.tomoption.types.option.MatchingTheory.make(tom_append_list_concElementaryTheory(tom_get_slice_concElementaryTheory(tomMatch207_17,tomMatch207__end__24, tom.engine.adt.theory.types.theory.EmptyconcElementaryTheory.make() ),tom_append_list_concElementaryTheory( tomMatch207__end__24.getTailconcElementaryTheory() , tom.engine.adt.theory.types.theory.EmptyconcElementaryTheory.make() ))) ,tom_append_list_concOption( tomMatch207__end__12.getTailconcOption() , tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() )) );


{
{
if ( (tom_slots instanceof tom.engine.adt.tomslot.types.SlotList) ) {
if ( (((( tom.engine.adt.tomslot.types.SlotList )tom_slots) instanceof tom.engine.adt.tomslot.types.slotlist.ConsconcSlot) || ((( tom.engine.adt.tomslot.types.SlotList )tom_slots) instanceof tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot)) ) {
 tom.engine.adt.tomslot.types.SlotList  tomMatch208__end__4=(( tom.engine.adt.tomslot.types.SlotList )tom_slots);
do {
{
if (!( tomMatch208__end__4.isEmptyconcSlot() )) {
 tom.engine.adt.tomslot.types.Slot  tomMatch208_9= tomMatch208__end__4.getHeadconcSlot() ;
if ( (tomMatch208_9 instanceof tom.engine.adt.tomslot.types.slot.PairSlotAppl) ) {
 tom.engine.adt.tomname.types.TomName  tom_slotname= tomMatch208_9.getSlotName() ;
boolean tomMatch208_11= false ;
if ( ( tomMatch208_9.getAppl()  instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) {
tomMatch208_11= true ;
}
if (!(tomMatch208_11)) {

System.out.println("case F(t,...): " + 
tom_slots);
/*System.out.println("slot: " + `slot);*/

//generate f(X1*, slot, X2*) << s and modify s <- f(X1*,X2*)
BQTerm X1 = acp.getCompiler().getFreshVariableStar(
tom_aType);			
BQTerm X2 = acp.getCompiler().getFreshVariableStar(
tom_aType);
BQTerm X3 = acp.getCompiler().getFreshVariableStar(
tom_aType);
Constraint c1 = 
 tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make( tom.engine.adt.tomterm.types.tomterm.RecordAppl.make(optWithoutAC, tom_namelist,  tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( tom.engine.adt.tomslot.types.slot.PairSlotAppl.make(tom_slotname, TomBase.convertFromBQVarToVar(X1)) , tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( tomMatch208__end__4.getHeadconcSlot() , tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( tom.engine.adt.tomslot.types.slot.PairSlotAppl.make(tom_slotname, TomBase.convertFromBQVarToVar(X2)) , tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() ) ) ) ,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) ,  (( tom.engine.adt.tomconstraint.types.Constraint )tom__arg).getSubject() , tom_aType) ;

//generate f(...) << f(X1*,X2*)
TomSymbol tomSymbol = acp.getCompiler().getSymbolTable().getSymbolFromName(
tom_tomName);
BQTerm newSubject = null;
if(TomBase.isListOperator(tomSymbol)) {
newSubject = ASTFactory.buildList(
 tom.engine.adt.tomname.types.tomname.Name.make(tom_tomName) ,
 tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make(X1, tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make(X2, tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() ) ) ,acp.getCompiler().getSymbolTable());
} else if(TomBase.isArrayOperator(tomSymbol)) {
newSubject = ASTFactory.buildArray(
 tom.engine.adt.tomname.types.tomname.Name.make(tom_tomName) ,
 tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make(X1, tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make(X2, tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() ) ) ,acp.getCompiler().getSymbolTable());
}
Constraint c2 =

 tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make( tom.engine.adt.tomterm.types.tomterm.RecordAppl.make(tomMatch207_5, tom_namelist, tom_append_list_concSlot(tom_get_slice_concSlot((( tom.engine.adt.tomslot.types.SlotList )tom_slots),tomMatch208__end__4, tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() ),tom_append_list_concSlot( tomMatch208__end__4.getTailconcSlot() , tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() )),  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) , newSubject, tom_aType) ;

Constraint result = 
 tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make(c1, tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make(c2, tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ) ) ;
/*System.out.println("result: " + result);*/
System.out.println(TomConstraintPrettyPrinter.prettyPrint(result));
return result;


}

}
}
if ( tomMatch208__end__4.isEmptyconcSlot() ) {
tomMatch208__end__4=(( tom.engine.adt.tomslot.types.SlotList )tom_slots);
} else {
tomMatch208__end__4= tomMatch208__end__4.getTailconcSlot() ;
}

}
} while(!( (tomMatch208__end__4==(( tom.engine.adt.tomslot.types.SlotList )tom_slots)) ));
}
}

}

}



}
}
}
}
}
}
if ( tomMatch207__end__24.isEmptyconcElementaryTheory() ) {
tomMatch207__end__24=tomMatch207_17;
} else {
tomMatch207__end__24= tomMatch207__end__24.getTailconcElementaryTheory() ;
}

}
} while(!( (tomMatch207__end__24==tomMatch207_17) ));
}
}
}
if ( tomMatch207__end__12.isEmptyconcOption() ) {
tomMatch207__end__12=tomMatch207_5;
} else {
tomMatch207__end__12= tomMatch207__end__12.getTailconcOption() ;
}

}
} while(!( (tomMatch207__end__12==tomMatch207_5) ));
}
}
}
}

}

}
return _visit_Constraint(tom__arg,introspector);

}
}
private static  tom.library.sl.Strategy  tom_make_RemoveNonVariableStar( ACPropagator  t0) { 
return new RemoveNonVariableStar(t0);
}


/**
* use abstraction to compile non-linear variables
*
* at this stage, we only have Variable en VariableStar
* f(X1,X2^a2,...,Xn^an) <<ac s -> f(Z,Xn^an) <<ac s ^ f(X1,X2^a2,...,Xn-1^an-1) <<ac Z
*/

public static class RemoveNonLinearVariableStar extends tom.library.sl.AbstractStrategyBasic {
private  ACPropagator  acp;
public RemoveNonLinearVariableStar( ACPropagator  acp) {
super(( new tom.library.sl.Identity() ));
this.acp=acp;
}
public  ACPropagator  getacp() {
return acp;
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
public <T> T visitLight(T v, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
if ( (v instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {
return ((T)visit_Constraint((( tom.engine.adt.tomconstraint.types.Constraint )v),introspector));
}
if (!(( null  == environment))) {
return ((T)any.visit(environment,introspector));
} else {
return any.visitLight(v,introspector);
}

}
@SuppressWarnings("unchecked")
public  tom.engine.adt.tomconstraint.types.Constraint  _visit_Constraint( tom.engine.adt.tomconstraint.types.Constraint  arg, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
if (!(( null  == environment))) {
return (( tom.engine.adt.tomconstraint.types.Constraint )any.visit(environment,introspector));
} else {
return any.visitLight(arg,introspector);
}
}
@SuppressWarnings("unchecked")
public  tom.engine.adt.tomconstraint.types.Constraint  visit_Constraint( tom.engine.adt.tomconstraint.types.Constraint  tom__arg, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
{
{
if ( (tom__arg instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {
if ( ((( tom.engine.adt.tomconstraint.types.Constraint )tom__arg) instanceof tom.engine.adt.tomconstraint.types.constraint.MatchConstraint) ) {
 tom.engine.adt.tomterm.types.TomTerm  tomMatch209_1= (( tom.engine.adt.tomconstraint.types.Constraint )tom__arg).getPattern() ;
if ( (tomMatch209_1 instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {
 tom.engine.adt.tomoption.types.OptionList  tomMatch209_5= tomMatch209_1.getOptions() ;
 tom.engine.adt.tomname.types.TomNameList  tomMatch209_6= tomMatch209_1.getNameList() ;
if ( ((tomMatch209_5 instanceof tom.engine.adt.tomoption.types.optionlist.ConsconcOption) || (tomMatch209_5 instanceof tom.engine.adt.tomoption.types.optionlist.EmptyconcOption)) ) {
 tom.engine.adt.tomoption.types.OptionList  tom_optWithAC=tomMatch209_5;
 tom.engine.adt.tomoption.types.OptionList  tomMatch209__end__12=tomMatch209_5;
do {
{
if (!( tomMatch209__end__12.isEmptyconcOption() )) {
 tom.engine.adt.tomoption.types.Option  tomMatch209_18= tomMatch209__end__12.getHeadconcOption() ;
if ( (tomMatch209_18 instanceof tom.engine.adt.tomoption.types.option.MatchingTheory) ) {
 tom.engine.adt.theory.types.Theory  tomMatch209_17= tomMatch209_18.getTheory() ;
if ( ((tomMatch209_17 instanceof tom.engine.adt.theory.types.theory.ConsconcElementaryTheory) || (tomMatch209_17 instanceof tom.engine.adt.theory.types.theory.EmptyconcElementaryTheory)) ) {
 tom.engine.adt.theory.types.Theory  tomMatch209__end__23=tomMatch209_17;
do {
{
if (!( tomMatch209__end__23.isEmptyconcElementaryTheory() )) {
if ( ( tomMatch209__end__23.getHeadconcElementaryTheory()  instanceof tom.engine.adt.theory.types.elementarytheory.AC) ) {
if ( ((tomMatch209_6 instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || (tomMatch209_6 instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {
 tom.engine.adt.tomname.types.TomNameList  tom_namelist=tomMatch209_6;
if (!( tomMatch209_6.isEmptyconcTomName() )) {
if ( ( tomMatch209_6.getHeadconcTomName()  instanceof tom.engine.adt.tomname.types.tomname.Name) ) {
if (  tomMatch209_6.getTailconcTomName() .isEmptyconcTomName() ) {
 tom.engine.adt.tomslot.types.SlotList  tom_slots= tomMatch209_1.getSlots() ;
 tom.engine.adt.tomtype.types.TomType  tom_aType= (( tom.engine.adt.tomconstraint.types.Constraint )tom__arg).getAstType() ;

if(
tom_slots.length() > 2) {

{
{
if ( (tom_slots instanceof tom.engine.adt.tomslot.types.SlotList) ) {
if ( (((( tom.engine.adt.tomslot.types.SlotList )tom_slots) instanceof tom.engine.adt.tomslot.types.slotlist.ConsconcSlot) || ((( tom.engine.adt.tomslot.types.SlotList )tom_slots) instanceof tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot)) ) {
 tom.engine.adt.tomslot.types.SlotList  tomMatch210__end__4=(( tom.engine.adt.tomslot.types.SlotList )tom_slots);
do {
{
if (!( tomMatch210__end__4.isEmptyconcSlot() )) {
 tom.engine.adt.tomslot.types.Slot  tomMatch210_13= tomMatch210__end__4.getHeadconcSlot() ;
if ( (tomMatch210_13 instanceof tom.engine.adt.tomslot.types.slot.PairSlotAppl) ) {
 tom.engine.adt.tomterm.types.TomTerm  tomMatch210_12= tomMatch210_13.getAppl() ;
 tom.engine.adt.tomname.types.TomName  tom_slotname= tomMatch210_13.getSlotName() ;
if ( (tomMatch210_12 instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) {
 tom.engine.adt.tomname.types.TomName  tom_Xname= tomMatch210_12.getAstName() ;
 tom.engine.adt.tomslot.types.SlotList  tomMatch210_5= tomMatch210__end__4.getTailconcSlot() ;
 tom.engine.adt.tomslot.types.SlotList  tomMatch210__end__8=tomMatch210_5;
do {
{
if (!( tomMatch210__end__8.isEmptyconcSlot() )) {
 tom.engine.adt.tomslot.types.Slot  tomMatch210_18= tomMatch210__end__8.getHeadconcSlot() ;
if ( (tomMatch210_18 instanceof tom.engine.adt.tomslot.types.slot.PairSlotAppl) ) {
 tom.engine.adt.tomterm.types.TomTerm  tomMatch210_17= tomMatch210_18.getAppl() ;
if ( (tom_slotname== tomMatch210_18.getSlotName() ) ) {
if ( (tomMatch210_17 instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) {
if ( (tom_Xname== tomMatch210_17.getAstName() ) ) {

//System.out.println("remove non linear X*: " + `Xn);
//System.out.println(`slots);

SlotList slotXn = 
 tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() ;
SlotList slotContext = 
 tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() ;
for(Slot s:
tom_slots.getCollectionconcSlot()) {
//System.out.println("slot: " + `s);

{
{
if ( (s instanceof tom.engine.adt.tomslot.types.Slot) ) {
if ( ((( tom.engine.adt.tomslot.types.Slot )s) instanceof tom.engine.adt.tomslot.types.slot.PairSlotAppl) ) {
 tom.engine.adt.tomterm.types.TomTerm  tomMatch211_2= (( tom.engine.adt.tomslot.types.Slot )s).getAppl() ;
if ( (tomMatch211_2 instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) {

if(
tom_slotname== 
 (( tom.engine.adt.tomslot.types.Slot )s).getSlotName() && 
tom_Xname==
 tomMatch211_2.getAstName() ) {
// remove all occurences of Xn
slotXn = 
 tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make(s,tom_append_list_concSlot(slotXn, tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() )) ;
} else {
// rebuild C1,C2,C3 without Xn
slotContext = 
tom_append_list_concSlot(slotContext, tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make(s, tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() ) );
}


}
}
}

}

}

}

//generate: f(Z,Xn) <<ac s 
TomType listType = acp.getCompiler().getTermTypeFromTerm(
tomMatch209_1);
BQTerm Z = acp.getCompiler().getFreshVariableStar(listType);				
Constraint c1 = 

 tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make( tom.engine.adt.tomterm.types.tomterm.RecordAppl.make(tom_optWithAC, tom_namelist,  tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( tom.engine.adt.tomslot.types.slot.PairSlotAppl.make(tom_slotname, TomBase.convertFromBQVarToVar(Z)) ,tom_append_list_concSlot(slotXn, tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() )) ,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) ,  (( tom.engine.adt.tomconstraint.types.Constraint )tom__arg).getSubject() , tom_aType) ;
//generate: f(slotContext) <<ac Z
Constraint c2 = 
 tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make( tom.engine.adt.tomterm.types.tomterm.RecordAppl.make(tom_optWithAC, tom_namelist, slotContext,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) , Z, tom_aType) ;
Constraint result = 
 tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make(c1, tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make(c2, tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ) ) ;

if(
slotContext.length() > 1) {
System.out.println("remove non linear X*: " + 
 tomMatch210__end__4.getHeadconcSlot() );
System.out.println("result: ");
System.out.println(TomConstraintPrettyPrinter.prettyPrint(result));
return result;
}


}
}
}
}
}
if ( tomMatch210__end__8.isEmptyconcSlot() ) {
tomMatch210__end__8=tomMatch210_5;
} else {
tomMatch210__end__8= tomMatch210__end__8.getTailconcSlot() ;
}

}
} while(!( (tomMatch210__end__8==tomMatch210_5) ));
}
}
}
if ( tomMatch210__end__4.isEmptyconcSlot() ) {
tomMatch210__end__4=(( tom.engine.adt.tomslot.types.SlotList )tom_slots);
} else {
tomMatch210__end__4= tomMatch210__end__4.getTailconcSlot() ;
}

}
} while(!( (tomMatch210__end__4==(( tom.engine.adt.tomslot.types.SlotList )tom_slots)) ));
}
}

}

}

}


}
}
}
}
}
}
if ( tomMatch209__end__23.isEmptyconcElementaryTheory() ) {
tomMatch209__end__23=tomMatch209_17;
} else {
tomMatch209__end__23= tomMatch209__end__23.getTailconcElementaryTheory() ;
}

}
} while(!( (tomMatch209__end__23==tomMatch209_17) ));
}
}
}
if ( tomMatch209__end__12.isEmptyconcOption() ) {
tomMatch209__end__12=tomMatch209_5;
} else {
tomMatch209__end__12= tomMatch209__end__12.getTailconcOption() ;
}

}
} while(!( (tomMatch209__end__12==tomMatch209_5) ));
}
}
}
}

}

}
return _visit_Constraint(tom__arg,introspector);

}
}
private static  tom.library.sl.Strategy  tom_make_RemoveNonLinearVariableStar( ACPropagator  t0) { 
return new RemoveNonLinearVariableStar(t0);
}


/**
* use abstraction to compile  
* 
* f(Z*,...) <<ac s -> (Z*,X1*) <<ac s ^ f(...) <<ac X1* IF Z* linear 
*/

public static class PerformAbstraction extends tom.library.sl.AbstractStrategyBasic {
private  ACPropagator  acp;
public PerformAbstraction( ACPropagator  acp) {
super(( new tom.library.sl.Identity() ));
this.acp=acp;
}
public  ACPropagator  getacp() {
return acp;
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
public <T> T visitLight(T v, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
if ( (v instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {
return ((T)visit_Constraint((( tom.engine.adt.tomconstraint.types.Constraint )v),introspector));
}
if (!(( null  == environment))) {
return ((T)any.visit(environment,introspector));
} else {
return any.visitLight(v,introspector);
}

}
@SuppressWarnings("unchecked")
public  tom.engine.adt.tomconstraint.types.Constraint  _visit_Constraint( tom.engine.adt.tomconstraint.types.Constraint  arg, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
if (!(( null  == environment))) {
return (( tom.engine.adt.tomconstraint.types.Constraint )any.visit(environment,introspector));
} else {
return any.visitLight(arg,introspector);
}
}
@SuppressWarnings("unchecked")
public  tom.engine.adt.tomconstraint.types.Constraint  visit_Constraint( tom.engine.adt.tomconstraint.types.Constraint  tom__arg, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
{
{
if ( (tom__arg instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {
if ( ((( tom.engine.adt.tomconstraint.types.Constraint )tom__arg) instanceof tom.engine.adt.tomconstraint.types.constraint.MatchConstraint) ) {
 tom.engine.adt.tomterm.types.TomTerm  tomMatch212_1= (( tom.engine.adt.tomconstraint.types.Constraint )tom__arg).getPattern() ;
if ( (tomMatch212_1 instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {
 tom.engine.adt.tomoption.types.OptionList  tomMatch212_5= tomMatch212_1.getOptions() ;
 tom.engine.adt.tomname.types.TomNameList  tomMatch212_6= tomMatch212_1.getNameList() ;
if ( ((tomMatch212_5 instanceof tom.engine.adt.tomoption.types.optionlist.ConsconcOption) || (tomMatch212_5 instanceof tom.engine.adt.tomoption.types.optionlist.EmptyconcOption)) ) {
 tom.engine.adt.tomoption.types.OptionList  tom_optWithAC=tomMatch212_5;
 tom.engine.adt.tomoption.types.OptionList  tomMatch212__end__12=tomMatch212_5;
do {
{
if (!( tomMatch212__end__12.isEmptyconcOption() )) {
 tom.engine.adt.tomoption.types.Option  tomMatch212_18= tomMatch212__end__12.getHeadconcOption() ;
if ( (tomMatch212_18 instanceof tom.engine.adt.tomoption.types.option.MatchingTheory) ) {
 tom.engine.adt.theory.types.Theory  tomMatch212_17= tomMatch212_18.getTheory() ;
if ( ((tomMatch212_17 instanceof tom.engine.adt.theory.types.theory.ConsconcElementaryTheory) || (tomMatch212_17 instanceof tom.engine.adt.theory.types.theory.EmptyconcElementaryTheory)) ) {
 tom.engine.adt.theory.types.Theory  tomMatch212__end__23=tomMatch212_17;
do {
{
if (!( tomMatch212__end__23.isEmptyconcElementaryTheory() )) {
if ( ( tomMatch212__end__23.getHeadconcElementaryTheory()  instanceof tom.engine.adt.theory.types.elementarytheory.AC) ) {
if ( ((tomMatch212_6 instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || (tomMatch212_6 instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {
 tom.engine.adt.tomname.types.TomNameList  tom_namelist=tomMatch212_6;
if (!( tomMatch212_6.isEmptyconcTomName() )) {
if ( ( tomMatch212_6.getHeadconcTomName()  instanceof tom.engine.adt.tomname.types.tomname.Name) ) {
if (  tomMatch212_6.getTailconcTomName() .isEmptyconcTomName() ) {
 tom.engine.adt.tomslot.types.SlotList  tom_slots= tomMatch212_1.getSlots() ;
 tom.engine.adt.tomtype.types.TomType  tom_aType= (( tom.engine.adt.tomconstraint.types.Constraint )tom__arg).getAstType() ;

if(
tom_slots.length() > 2) {

{
{
if ( (tom_slots instanceof tom.engine.adt.tomslot.types.SlotList) ) {
if ( (((( tom.engine.adt.tomslot.types.SlotList )tom_slots) instanceof tom.engine.adt.tomslot.types.slotlist.ConsconcSlot) || ((( tom.engine.adt.tomslot.types.SlotList )tom_slots) instanceof tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot)) ) {
 tom.engine.adt.tomslot.types.SlotList  tomMatch213__end__5=(( tom.engine.adt.tomslot.types.SlotList )tom_slots);
do {
{
 tom.engine.adt.tomslot.types.SlotList  tom_C1=tom_get_slice_concSlot((( tom.engine.adt.tomslot.types.SlotList )tom_slots),tomMatch213__end__5, tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() );
if (!( tomMatch213__end__5.isEmptyconcSlot() )) {
 tom.engine.adt.tomslot.types.Slot  tomMatch213_10= tomMatch213__end__5.getHeadconcSlot() ;
if ( (tomMatch213_10 instanceof tom.engine.adt.tomslot.types.slot.PairSlotAppl) ) {
 tom.engine.adt.tomname.types.TomName  tom_slotname= tomMatch213_10.getSlotName() ;
if ( ( tomMatch213_10.getAppl()  instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) {
 tom.engine.adt.tomslot.types.SlotList  tom_C2= tomMatch213__end__5.getTailconcSlot() ;
Object tomMatch213_1=tom_append_list_concSlot(tom_C1,tom_append_list_concSlot(tom_C2, tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() ));
if ( (tomMatch213_1 instanceof tom.engine.adt.tomslot.types.SlotList) ) {
boolean tomMatch213_23= false ;
if ( (((( tom.engine.adt.tomslot.types.SlotList )tomMatch213_1) instanceof tom.engine.adt.tomslot.types.slotlist.ConsconcSlot) || ((( tom.engine.adt.tomslot.types.SlotList )tomMatch213_1) instanceof tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot)) ) {
 tom.engine.adt.tomslot.types.SlotList  tomMatch213__end__15=(( tom.engine.adt.tomslot.types.SlotList )tomMatch213_1);
do {
{
if (!( tomMatch213__end__15.isEmptyconcSlot() )) {
 tom.engine.adt.tomslot.types.Slot  tomMatch213_20= tomMatch213__end__15.getHeadconcSlot() ;
if ( (tomMatch213_20 instanceof tom.engine.adt.tomslot.types.slot.PairSlotAppl) ) {
if ( (tom_slotname== tomMatch213_20.getSlotName() ) ) {
if ( ( tomMatch213_20.getAppl()  instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) {
tomMatch213_23= true ;
}
}
}
}
if ( tomMatch213__end__15.isEmptyconcSlot() ) {
tomMatch213__end__15=(( tom.engine.adt.tomslot.types.SlotList )tomMatch213_1);
} else {
tomMatch213__end__15= tomMatch213__end__15.getTailconcSlot() ;
}

}
} while(!( (tomMatch213__end__15==(( tom.engine.adt.tomslot.types.SlotList )tomMatch213_1)) ));
}
if (!(tomMatch213_23)) {

System.out.println("case F(Z*,...): " + 
tom_slots);
//generate: f(Z,X1) <<ac s 
TomType listType = acp.getCompiler().getTermTypeFromTerm(
tomMatch212_1);
BQTerm X1 = acp.getCompiler().getFreshVariableStar(listType);				
Constraint c1 = 

 tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make( tom.engine.adt.tomterm.types.tomterm.RecordAppl.make(tom_optWithAC, tom_namelist,  tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( tomMatch213__end__5.getHeadconcSlot() , tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( tom.engine.adt.tomslot.types.slot.PairSlotAppl.make(tom_slotname, TomBase.convertFromBQVarToVar(X1)) , tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() ) ) ,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) ,  (( tom.engine.adt.tomconstraint.types.Constraint )tom__arg).getSubject() , tom_aType) ;
//generate: f(...) <<ac X1
Constraint c2 = 
 tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make( tom.engine.adt.tomterm.types.tomterm.RecordAppl.make(tom_optWithAC, tom_namelist, tom_append_list_concSlot(tom_C1,tom_append_list_concSlot(tom_C2, tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() )),  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) , X1, tom_aType) ;
Constraint result = 
 tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make(c1, tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make(c2, tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ) ) ;

/*System.out.println("result: " + result);*/
System.out.println(TomConstraintPrettyPrinter.prettyPrint(result));
return result;


}

}
}
}
}
if ( tomMatch213__end__5.isEmptyconcSlot() ) {
tomMatch213__end__5=(( tom.engine.adt.tomslot.types.SlotList )tom_slots);
} else {
tomMatch213__end__5= tomMatch213__end__5.getTailconcSlot() ;
}

}
} while(!( (tomMatch213__end__5==(( tom.engine.adt.tomslot.types.SlotList )tom_slots)) ));
}
}

}

}

}


}
}
}
}
}
}
if ( tomMatch212__end__23.isEmptyconcElementaryTheory() ) {
tomMatch212__end__23=tomMatch212_17;
} else {
tomMatch212__end__23= tomMatch212__end__23.getTailconcElementaryTheory() ;
}

}
} while(!( (tomMatch212__end__23==tomMatch212_17) ));
}
}
}
if ( tomMatch212__end__12.isEmptyconcOption() ) {
tomMatch212__end__12=tomMatch212_5;
} else {
tomMatch212__end__12= tomMatch212__end__12.getTailconcOption() ;
}

}
} while(!( (tomMatch212__end__12==tomMatch212_5) ));
}
}
}
}

}

}
return _visit_Constraint(tom__arg,introspector);

}
}
private static  tom.library.sl.Strategy  tom_make_PerformAbstraction( ACPropagator  t0) { 
return new PerformAbstraction(t0);
}


/**
* transform AC matching into A matching when the pattern is reduced to 
* an empty-list or a single variable
* f()   <<ac s => f() <<a s
* f(X*) <<ac s => f(X*) <<a s
*/

public static class CleanSingleVariable extends tom.library.sl.AbstractStrategyBasic {
public CleanSingleVariable() {
super(( new tom.library.sl.Identity() ));
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
public <T> T visitLight(T v, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
if ( (v instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {
return ((T)visit_Constraint((( tom.engine.adt.tomconstraint.types.Constraint )v),introspector));
}
if (!(( null  == environment))) {
return ((T)any.visit(environment,introspector));
} else {
return any.visitLight(v,introspector);
}

}
@SuppressWarnings("unchecked")
public  tom.engine.adt.tomconstraint.types.Constraint  _visit_Constraint( tom.engine.adt.tomconstraint.types.Constraint  arg, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
if (!(( null  == environment))) {
return (( tom.engine.adt.tomconstraint.types.Constraint )any.visit(environment,introspector));
} else {
return any.visitLight(arg,introspector);
}
}
@SuppressWarnings("unchecked")
public  tom.engine.adt.tomconstraint.types.Constraint  visit_Constraint( tom.engine.adt.tomconstraint.types.Constraint  tom__arg, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
{
{
if ( (tom__arg instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {
if ( ((( tom.engine.adt.tomconstraint.types.Constraint )tom__arg) instanceof tom.engine.adt.tomconstraint.types.constraint.MatchConstraint) ) {
 tom.engine.adt.tomterm.types.TomTerm  tomMatch214_3= (( tom.engine.adt.tomconstraint.types.Constraint )tom__arg).getPattern() ;
if ( (tomMatch214_3 instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) {
 tom.engine.adt.tomoption.types.OptionList  tomMatch214_7= tomMatch214_3.getOptions() ;
 tom.engine.adt.tomname.types.TomNameList  tomMatch214_8= tomMatch214_3.getNameList() ;
if ( ((tomMatch214_7 instanceof tom.engine.adt.tomoption.types.optionlist.ConsconcOption) || (tomMatch214_7 instanceof tom.engine.adt.tomoption.types.optionlist.EmptyconcOption)) ) {
 tom.engine.adt.tomoption.types.OptionList  tomMatch214__end__14=tomMatch214_7;
do {
{
 tom.engine.adt.tomoption.types.OptionList  tom_T1=tom_get_slice_concOption(tomMatch214_7,tomMatch214__end__14, tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() );
if (!( tomMatch214__end__14.isEmptyconcOption() )) {
 tom.engine.adt.tomoption.types.Option  tomMatch214_23= tomMatch214__end__14.getHeadconcOption() ;
if ( (tomMatch214_23 instanceof tom.engine.adt.tomoption.types.option.MatchingTheory) ) {
 tom.engine.adt.theory.types.Theory  tomMatch214_22= tomMatch214_23.getTheory() ;
if ( ((tomMatch214_22 instanceof tom.engine.adt.theory.types.theory.ConsconcElementaryTheory) || (tomMatch214_22 instanceof tom.engine.adt.theory.types.theory.EmptyconcElementaryTheory)) ) {
 tom.engine.adt.theory.types.Theory  tomMatch214__end__31=tomMatch214_22;
do {
{
 tom.engine.adt.theory.types.Theory  tom_T2=tom_get_slice_concElementaryTheory(tomMatch214_22,tomMatch214__end__31, tom.engine.adt.theory.types.theory.EmptyconcElementaryTheory.make() );
if (!( tomMatch214__end__31.isEmptyconcElementaryTheory() )) {
if ( ( tomMatch214__end__31.getHeadconcElementaryTheory()  instanceof tom.engine.adt.theory.types.elementarytheory.AC) ) {
 tom.engine.adt.theory.types.Theory  tom_T3= tomMatch214__end__31.getTailconcElementaryTheory() ;
 tom.engine.adt.tomoption.types.OptionList  tom_T4= tomMatch214__end__14.getTailconcOption() ;
if ( ((tomMatch214_8 instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || (tomMatch214_8 instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {
 tom.engine.adt.tomname.types.TomNameList  tom_namelist=tomMatch214_8;
if (!( tomMatch214_8.isEmptyconcTomName() )) {
if ( ( tomMatch214_8.getHeadconcTomName()  instanceof tom.engine.adt.tomname.types.tomname.Name) ) {
if (  tomMatch214_8.getTailconcTomName() .isEmptyconcTomName() ) {
 tom.engine.adt.tomslot.types.SlotList  tom_slots= tomMatch214_3.getSlots() ;
 tom.engine.adt.code.types.BQTerm  tom_subject= (( tom.engine.adt.tomconstraint.types.Constraint )tom__arg).getSubject() ;
 tom.engine.adt.tomtype.types.TomType  tom_aType= (( tom.engine.adt.tomconstraint.types.Constraint )tom__arg).getAstType() ;
if ( (tom_slots instanceof tom.engine.adt.tomslot.types.SlotList) ) {
if ( (((( tom.engine.adt.tomslot.types.SlotList )tom_slots) instanceof tom.engine.adt.tomslot.types.slotlist.ConsconcSlot) || ((( tom.engine.adt.tomslot.types.SlotList )tom_slots) instanceof tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot)) ) {
if ( (( tom.engine.adt.tomslot.types.SlotList )tom_slots).isEmptyconcSlot() ) {

/*System.out.println("case f(X*) <<ac s => f(X*) <<a s");*/
/*System.out.println("case f()   <<ac s => f()   <<a s: " + `slots);*/
OptionList optWithoutAC = 
tom_append_list_concOption(tom_T1, tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( tom.engine.adt.tomoption.types.option.MatchingTheory.make(tom_append_list_concElementaryTheory(tom_T2,tom_append_list_concElementaryTheory(tom_T3, tom.engine.adt.theory.types.theory.EmptyconcElementaryTheory.make() ))) ,tom_append_list_concOption(tom_T4, tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() )) );
Constraint result = 
 tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make( tom.engine.adt.tomterm.types.tomterm.RecordAppl.make(optWithoutAC, tom_namelist, tom_slots,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) , tom_subject, tom_aType) ;
System.out.println(TomConstraintPrettyPrinter.prettyPrint(result));
return result;


}
}
}
if ( (tom_slots instanceof tom.engine.adt.tomslot.types.SlotList) ) {
if ( (((( tom.engine.adt.tomslot.types.SlotList )tom_slots) instanceof tom.engine.adt.tomslot.types.slotlist.ConsconcSlot) || ((( tom.engine.adt.tomslot.types.SlotList )tom_slots) instanceof tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot)) ) {
if (!( (( tom.engine.adt.tomslot.types.SlotList )tom_slots).isEmptyconcSlot() )) {
 tom.engine.adt.tomslot.types.Slot  tomMatch214_26= (( tom.engine.adt.tomslot.types.SlotList )tom_slots).getHeadconcSlot() ;
if ( (tomMatch214_26 instanceof tom.engine.adt.tomslot.types.slot.PairSlotAppl) ) {
if ( ( tomMatch214_26.getAppl()  instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) {
if (  (( tom.engine.adt.tomslot.types.SlotList )tom_slots).getTailconcSlot() .isEmptyconcSlot() ) {

/*System.out.println("case f(X*) <<ac s => f(X*) <<a s");*/
/*System.out.println("case f()   <<ac s => f()   <<a s: " + `slots);*/
OptionList optWithoutAC = 
tom_append_list_concOption(tom_T1, tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( tom.engine.adt.tomoption.types.option.MatchingTheory.make(tom_append_list_concElementaryTheory(tom_T2,tom_append_list_concElementaryTheory(tom_T3, tom.engine.adt.theory.types.theory.EmptyconcElementaryTheory.make() ))) ,tom_append_list_concOption(tom_T4, tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() )) );
Constraint result = 
 tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make( tom.engine.adt.tomterm.types.tomterm.RecordAppl.make(optWithoutAC, tom_namelist, tom_slots,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) , tom_subject, tom_aType) ;
System.out.println(TomConstraintPrettyPrinter.prettyPrint(result));
return result;


}
}
}
}
}
}

}
}
}
}
}
}
if ( tomMatch214__end__31.isEmptyconcElementaryTheory() ) {
tomMatch214__end__31=tomMatch214_22;
} else {
tomMatch214__end__31= tomMatch214__end__31.getTailconcElementaryTheory() ;
}

}
} while(!( (tomMatch214__end__31==tomMatch214_22) ));
}
}
}
if ( tomMatch214__end__14.isEmptyconcOption() ) {
tomMatch214__end__14=tomMatch214_7;
} else {
tomMatch214__end__14= tomMatch214__end__14.getTailconcOption() ;
}

}
} while(!( (tomMatch214__end__14==tomMatch214_7) ));
}
}
}
}

}

}
return _visit_Constraint(tom__arg,introspector);

}
}
private static  tom.library.sl.Strategy  tom_make_CleanSingleVariable() { 
return new CleanSingleVariable();
}


}
