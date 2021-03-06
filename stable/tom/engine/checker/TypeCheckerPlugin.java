


























package tom.engine.checker;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



import tom.engine.TomBase;
import tom.engine.TomMessage;
import tom.engine.tools.TomGenericPlugin;
import tom.engine.exception.TomRuntimeException;
import tom.platform.PlatformLogRecord;



import tom.library.sl.*;
import tom.platform.adt.platformoption.types.PlatformOptionList;



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
import tom.engine.adt.code.types.*;






public class TypeCheckerPlugin extends TomGenericPlugin {

     private static   tom.engine.adt.tomsignature.types.TomVisitList  tom_append_list_concTomVisit( tom.engine.adt.tomsignature.types.TomVisitList l1,  tom.engine.adt.tomsignature.types.TomVisitList  l2) {     if( l1.isEmptyconcTomVisit() ) {       return l2;     } else if( l2.isEmptyconcTomVisit() ) {       return l1;     } else if(  l1.getTailconcTomVisit() .isEmptyconcTomVisit() ) {       return  tom.engine.adt.tomsignature.types.tomvisitlist.ConsconcTomVisit.make( l1.getHeadconcTomVisit() ,l2) ;     } else {       return  tom.engine.adt.tomsignature.types.tomvisitlist.ConsconcTomVisit.make( l1.getHeadconcTomVisit() ,tom_append_list_concTomVisit( l1.getTailconcTomVisit() ,l2)) ;     }   }   private static   tom.engine.adt.tomsignature.types.TomVisitList  tom_get_slice_concTomVisit( tom.engine.adt.tomsignature.types.TomVisitList  begin,  tom.engine.adt.tomsignature.types.TomVisitList  end, tom.engine.adt.tomsignature.types.TomVisitList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTomVisit()  ||  (end== tom.engine.adt.tomsignature.types.tomvisitlist.EmptyconcTomVisit.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomsignature.types.tomvisitlist.ConsconcTomVisit.make( begin.getHeadconcTomVisit() ,( tom.engine.adt.tomsignature.types.TomVisitList )tom_get_slice_concTomVisit( begin.getTailconcTomVisit() ,end,tail)) ;   }      private static   tom.engine.adt.tomtype.types.TypeOptionList  tom_append_list_concTypeOption( tom.engine.adt.tomtype.types.TypeOptionList l1,  tom.engine.adt.tomtype.types.TypeOptionList  l2) {     if( l1.isEmptyconcTypeOption() ) {       return l2;     } else if( l2.isEmptyconcTypeOption() ) {       return l1;     } else if(  l1.getTailconcTypeOption() .isEmptyconcTypeOption() ) {       return  tom.engine.adt.tomtype.types.typeoptionlist.ConsconcTypeOption.make( l1.getHeadconcTypeOption() ,l2) ;     } else {       return  tom.engine.adt.tomtype.types.typeoptionlist.ConsconcTypeOption.make( l1.getHeadconcTypeOption() ,tom_append_list_concTypeOption( l1.getTailconcTypeOption() ,l2)) ;     }   }   private static   tom.engine.adt.tomtype.types.TypeOptionList  tom_get_slice_concTypeOption( tom.engine.adt.tomtype.types.TypeOptionList  begin,  tom.engine.adt.tomtype.types.TypeOptionList  end, tom.engine.adt.tomtype.types.TypeOptionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTypeOption()  ||  (end== tom.engine.adt.tomtype.types.typeoptionlist.EmptyconcTypeOption.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomtype.types.typeoptionlist.ConsconcTypeOption.make( begin.getHeadconcTypeOption() ,( tom.engine.adt.tomtype.types.TypeOptionList )tom_get_slice_concTypeOption( begin.getTailconcTypeOption() ,end,tail)) ;   }      private static   tom.engine.adt.tominstruction.types.ConstraintInstructionList  tom_append_list_concConstraintInstruction( tom.engine.adt.tominstruction.types.ConstraintInstructionList l1,  tom.engine.adt.tominstruction.types.ConstraintInstructionList  l2) {     if( l1.isEmptyconcConstraintInstruction() ) {       return l2;     } else if( l2.isEmptyconcConstraintInstruction() ) {       return l1;     } else if(  l1.getTailconcConstraintInstruction() .isEmptyconcConstraintInstruction() ) {       return  tom.engine.adt.tominstruction.types.constraintinstructionlist.ConsconcConstraintInstruction.make( l1.getHeadconcConstraintInstruction() ,l2) ;     } else {       return  tom.engine.adt.tominstruction.types.constraintinstructionlist.ConsconcConstraintInstruction.make( l1.getHeadconcConstraintInstruction() ,tom_append_list_concConstraintInstruction( l1.getTailconcConstraintInstruction() ,l2)) ;     }   }   private static   tom.engine.adt.tominstruction.types.ConstraintInstructionList  tom_get_slice_concConstraintInstruction( tom.engine.adt.tominstruction.types.ConstraintInstructionList  begin,  tom.engine.adt.tominstruction.types.ConstraintInstructionList  end, tom.engine.adt.tominstruction.types.ConstraintInstructionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcConstraintInstruction()  ||  (end== tom.engine.adt.tominstruction.types.constraintinstructionlist.EmptyconcConstraintInstruction.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tominstruction.types.constraintinstructionlist.ConsconcConstraintInstruction.make( begin.getHeadconcConstraintInstruction() ,( tom.engine.adt.tominstruction.types.ConstraintInstructionList )tom_get_slice_concConstraintInstruction( begin.getTailconcConstraintInstruction() ,end,tail)) ;   }      private static   tom.engine.adt.tomname.types.TomNameList  tom_append_list_concTomName( tom.engine.adt.tomname.types.TomNameList l1,  tom.engine.adt.tomname.types.TomNameList  l2) {     if( l1.isEmptyconcTomName() ) {       return l2;     } else if( l2.isEmptyconcTomName() ) {       return l1;     } else if(  l1.getTailconcTomName() .isEmptyconcTomName() ) {       return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( l1.getHeadconcTomName() ,l2) ;     } else {       return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( l1.getHeadconcTomName() ,tom_append_list_concTomName( l1.getTailconcTomName() ,l2)) ;     }   }   private static   tom.engine.adt.tomname.types.TomNameList  tom_get_slice_concTomName( tom.engine.adt.tomname.types.TomNameList  begin,  tom.engine.adt.tomname.types.TomNameList  end, tom.engine.adt.tomname.types.TomNameList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTomName()  ||  (end== tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( begin.getHeadconcTomName() ,( tom.engine.adt.tomname.types.TomNameList )tom_get_slice_concTomName( begin.getTailconcTomName() ,end,tail)) ;   }      private static   tom.engine.adt.tomoption.types.OptionList  tom_append_list_concOption( tom.engine.adt.tomoption.types.OptionList l1,  tom.engine.adt.tomoption.types.OptionList  l2) {     if( l1.isEmptyconcOption() ) {       return l2;     } else if( l2.isEmptyconcOption() ) {       return l1;     } else if(  l1.getTailconcOption() .isEmptyconcOption() ) {       return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( l1.getHeadconcOption() ,l2) ;     } else {       return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( l1.getHeadconcOption() ,tom_append_list_concOption( l1.getTailconcOption() ,l2)) ;     }   }   private static   tom.engine.adt.tomoption.types.OptionList  tom_get_slice_concOption( tom.engine.adt.tomoption.types.OptionList  begin,  tom.engine.adt.tomoption.types.OptionList  end, tom.engine.adt.tomoption.types.OptionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcOption()  ||  (end== tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( begin.getHeadconcOption() ,( tom.engine.adt.tomoption.types.OptionList )tom_get_slice_concOption( begin.getTailconcOption() ,end,tail)) ;   }      private static   tom.platform.adt.platformoption.types.PlatformOptionList  tom_append_list_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOptionList l1,  tom.platform.adt.platformoption.types.PlatformOptionList  l2) {     if( l1.isEmptyconcPlatformOption() ) {       return l2;     } else if( l2.isEmptyconcPlatformOption() ) {       return l1;     } else if(  l1.getTailconcPlatformOption() .isEmptyconcPlatformOption() ) {       return  tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make( l1.getHeadconcPlatformOption() ,l2) ;     } else {       return  tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make( l1.getHeadconcPlatformOption() ,tom_append_list_concPlatformOption( l1.getTailconcPlatformOption() ,l2)) ;     }   }   private static   tom.platform.adt.platformoption.types.PlatformOptionList  tom_get_slice_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOptionList  begin,  tom.platform.adt.platformoption.types.PlatformOptionList  end, tom.platform.adt.platformoption.types.PlatformOptionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcPlatformOption()  ||  (end== tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make( begin.getHeadconcPlatformOption() ,( tom.platform.adt.platformoption.types.PlatformOptionList )tom_get_slice_concPlatformOption( begin.getTailconcPlatformOption() ,end,tail)) ;   }      private static   tom.library.sl.Strategy  tom_append_list_Sequence( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 == null )) {       return l2;     } else if(( l2 == null )) {       return l1;     } else if(( l1 instanceof tom.library.sl.Sequence )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ) == null )) {         return  tom.library.sl.Sequence.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ),l2) ;       } else {         return  tom.library.sl.Sequence.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ),tom_append_list_Sequence(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ),l2)) ;       }     } else {       return  tom.library.sl.Sequence.make(l1,l2) ;     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals( null )) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.sl.Sequence.make(((( begin instanceof tom.library.sl.Sequence ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(((( begin instanceof tom.library.sl.Sequence ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ): null ),end,tail)) ;   }      private static   tom.library.sl.Strategy  tom_append_list_Choice( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 ==null )) {       return l2;     } else if(( l2 ==null )) {       return l1;     } else if(( l1 instanceof tom.library.sl.Choice )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ) ==null )) {         return  tom.library.sl.Choice.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ),l2) ;       } else {         return  tom.library.sl.Choice.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ),tom_append_list_Choice(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ),l2)) ;       }     } else {       return  tom.library.sl.Choice.make(l1,l2) ;     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Choice( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end ==null ) ||  (end.equals( null )) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.sl.Choice.make(((( begin instanceof tom.library.sl.Choice ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Choice(((( begin instanceof tom.library.sl.Choice ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.THEN) ): null ),end,tail)) ;   }      private static   tom.library.sl.Strategy  tom_append_list_SequenceId( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 == null )) {       return l2;     } else if(( l2 == null )) {       return l1;     } else if(( l1 instanceof tom.library.sl.SequenceId )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.THEN) ) == null )) {         return  tom.library.sl.SequenceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.FIRST) ),l2) ;       } else {         return  tom.library.sl.SequenceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.FIRST) ),tom_append_list_SequenceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.THEN) ),l2)) ;       }     } else {       return  tom.library.sl.SequenceId.make(l1,l2) ;     }   }   private static   tom.library.sl.Strategy  tom_get_slice_SequenceId( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals( null )) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.sl.SequenceId.make(((( begin instanceof tom.library.sl.SequenceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.SequenceId.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_SequenceId(((( begin instanceof tom.library.sl.SequenceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.SequenceId.THEN) ): null ),end,tail)) ;   }      private static   tom.library.sl.Strategy  tom_append_list_ChoiceId( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 ==null )) {       return l2;     } else if(( l2 ==null )) {       return l1;     } else if(( l1 instanceof tom.library.sl.ChoiceId )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.THEN) ) ==null )) {         return  tom.library.sl.ChoiceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ),l2) ;       } else {         return  tom.library.sl.ChoiceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ),tom_append_list_ChoiceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.THEN) ),l2)) ;       }     } else {       return  tom.library.sl.ChoiceId.make(l1,l2) ;     }   }   private static   tom.library.sl.Strategy  tom_get_slice_ChoiceId( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end ==null ) ||  (end.equals( null )) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.sl.ChoiceId.make(((( begin instanceof tom.library.sl.ChoiceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_ChoiceId(((( begin instanceof tom.library.sl.ChoiceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.THEN) ): null ),end,tail)) ;   }   private static  tom.library.sl.Strategy  tom_make_AUCtl( tom.library.sl.Strategy  s1,  tom.library.sl.Strategy  s2) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("x") ), tom.library.sl.Choice.make(s2, tom.library.sl.Choice.make( tom.library.sl.Sequence.make( tom.library.sl.Sequence.make(s1, tom.library.sl.Sequence.make(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ), null ) ) , tom.library.sl.Sequence.make(( new tom.library.sl.One(( new tom.library.sl.Identity() )) ), null ) ) , null ) ) ) ) );}private static  tom.library.sl.Strategy  tom_make_EUCtl( tom.library.sl.Strategy  s1,  tom.library.sl.Strategy  s2) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("x") ), tom.library.sl.Choice.make(s2, tom.library.sl.Choice.make( tom.library.sl.Sequence.make(s1, tom.library.sl.Sequence.make(( new tom.library.sl.One(( new tom.library.sl.MuVar("x") )) ), null ) ) , null ) ) ) ));}private static  tom.library.sl.Strategy  tom_make_Try( tom.library.sl.Strategy  s) { return (  tom.library.sl.Choice.make(s, tom.library.sl.Choice.make(( new tom.library.sl.Identity() ), null ) )  );}private static  tom.library.sl.Strategy  tom_make_Repeat( tom.library.sl.Strategy  s) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.Choice.make( tom.library.sl.Sequence.make(s, tom.library.sl.Sequence.make(( new tom.library.sl.MuVar("_x") ), null ) ) , tom.library.sl.Choice.make(( new tom.library.sl.Identity() ), null ) ) ) ) );}private static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.Sequence.make(v, tom.library.sl.Sequence.make(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ), null ) ) ) ) );}private static  tom.library.sl.Strategy  tom_make_TopDownCollect( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ),tom_make_Try( tom.library.sl.Sequence.make(v, tom.library.sl.Sequence.make(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ), null ) ) )) ) );}private static  tom.library.sl.Strategy  tom_make_OnceTopDown( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.Choice.make(v, tom.library.sl.Choice.make(( new tom.library.sl.One(( new tom.library.sl.MuVar("_x") )) ), null ) ) ) ) );}private static  tom.library.sl.Strategy  tom_make_RepeatId( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.SequenceId.make(v, tom.library.sl.SequenceId.make(( new tom.library.sl.MuVar("_x") ), null ) ) ) ) );}private static  tom.library.sl.Strategy  tom_make_OnceTopDownId( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.ChoiceId.make(v, tom.library.sl.ChoiceId.make(( new tom.library.sl.OneId(( new tom.library.sl.MuVar("_x") )) ), null ) ) ) ) );}





  
  protected final static int TERM_APPL               = 0;
  protected final static int UNAMED_APPL             = 1;
  protected final static int APPL_DISJUNCTION        = 2;
  protected final static int RECORD_APPL             = 3;
  protected final static int RECORD_APPL_DISJUNCTION = 4;
  protected final static int VARIABLE_STAR           = 6;
  protected final static int UNAMED_VARIABLE_STAR    = 7;
  protected final static int UNAMED_VARIABLE         = 8;
  protected final static int VARIABLE                = 9;

  protected Option currentTomStructureOrgTrack;

  
  public static final PlatformOptionList PLATFORM_OPTIONS =
     tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make( tom.platform.adt.platformoption.types.platformoption.PluginOption.make("noTypeCheck", "", "Do not perform type checking",  tom.platform.adt.platformoption.types.platformvalue.BooleanValue.make( tom.platform.adt.platformoption.types.platformboolean.False.make() ) , "") , tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption.make() ) 

;

  
  public TypeCheckerPlugin() {
    super("TypeCheckerPlugin");
  }

  
  public PlatformOptionList getDeclaredOptionList() {
    return PLATFORM_OPTIONS; 
  }

  protected void reinit() {
    currentTomStructureOrgTrack = null;
  }

  public int getClass(TomTerm term) {
    { /* unamed block */{ /* unamed block */if ( (term instanceof tom.engine.adt.tomterm.types.TomTerm) ) {if ( ((( tom.engine.adt.tomterm.types.TomTerm )term) instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) { tom.engine.adt.tomname.types.TomNameList  tomMatch167_1= (( tom.engine.adt.tomterm.types.TomTerm )term).getNameList() ;if ( (((( tom.engine.adt.tomname.types.TomNameList )tomMatch167_1) instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || ((( tom.engine.adt.tomname.types.TomNameList )tomMatch167_1) instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {if (!( tomMatch167_1.isEmptyconcTomName() )) {if ( ((( tom.engine.adt.tomname.types.TomName ) tomMatch167_1.getHeadconcTomName() ) instanceof tom.engine.adt.tomname.types.tomname.Name) ) {if (  tomMatch167_1.getTailconcTomName() .isEmptyconcTomName() ) {

 return RECORD_APPL;}}}}}}}{ /* unamed block */if ( (term instanceof tom.engine.adt.tomterm.types.TomTerm) ) {if ( ((( tom.engine.adt.tomterm.types.TomTerm )term) instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) { tom.engine.adt.tomname.types.TomNameList  tomMatch167_10= (( tom.engine.adt.tomterm.types.TomTerm )term).getNameList() ;if ( (((( tom.engine.adt.tomname.types.TomNameList )tomMatch167_10) instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || ((( tom.engine.adt.tomname.types.TomNameList )tomMatch167_10) instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {if (!( tomMatch167_10.isEmptyconcTomName() )) {if ( ((( tom.engine.adt.tomname.types.TomName ) tomMatch167_10.getHeadconcTomName() ) instanceof tom.engine.adt.tomname.types.tomname.Name) ) {
 return RECORD_APPL_DISJUNCTION;}}}}}}{ /* unamed block */if ( (term instanceof tom.engine.adt.tomterm.types.TomTerm) ) {if ( ((( tom.engine.adt.tomterm.types.TomTerm )term) instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) {
 return VARIABLE_STAR;}}}{ /* unamed block */if ( (term instanceof tom.engine.adt.tomterm.types.TomTerm) ) {if ( ((( tom.engine.adt.tomterm.types.TomTerm )term) instanceof tom.engine.adt.tomterm.types.tomterm.Variable) ) {
 return VARIABLE;}}}}

    throw new TomRuntimeException("Invalid Term");
  }

  public String getName(TomTerm term) {
    String dijunctionName = "";
    { /* unamed block */{ /* unamed block */if ( (term instanceof tom.engine.adt.tomterm.types.TomTerm) ) {if ( ((( tom.engine.adt.tomterm.types.TomTerm )term) instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) { tom.engine.adt.tomname.types.TomNameList  tomMatch168_1= (( tom.engine.adt.tomterm.types.TomTerm )term).getNameList() ;if ( (((( tom.engine.adt.tomname.types.TomNameList )tomMatch168_1) instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || ((( tom.engine.adt.tomname.types.TomNameList )tomMatch168_1) instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ) {if (!( tomMatch168_1.isEmptyconcTomName() )) { tom.engine.adt.tomname.types.TomName  tomMatch168_7= tomMatch168_1.getHeadconcTomName() ;if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch168_7) instanceof tom.engine.adt.tomname.types.tomname.Name) ) {if (  tomMatch168_1.getTailconcTomName() .isEmptyconcTomName() ) {

 return  tomMatch168_7.getString() ;}}}}}}}{ /* unamed block */if ( (term instanceof tom.engine.adt.tomterm.types.TomTerm) ) {if ( ((( tom.engine.adt.tomterm.types.TomTerm )term) instanceof tom.engine.adt.tomterm.types.tomterm.RecordAppl) ) { tom.engine.adt.tomname.types.TomNameList  tom___nameList= (( tom.engine.adt.tomterm.types.TomTerm )term).getNameList() ;

        String head;
        dijunctionName = tom___nameList.getHeadconcTomName().getString();
        while(!tom___nameList.isEmptyconcTomName()) { /* unamed block */
          head = tom___nameList.getHeadconcTomName().getString();
          dijunctionName = ( dijunctionName.compareTo(head) > 0)?dijunctionName:head;
          tom___nameList = tom___nameList.getTailconcTomName();
        }
        return dijunctionName;
      }}}{ /* unamed block */if ( (term instanceof tom.engine.adt.tomterm.types.TomTerm) ) {if ( ((( tom.engine.adt.tomterm.types.TomTerm )term) instanceof tom.engine.adt.tomterm.types.tomterm.Variable) ) { tom.engine.adt.tomname.types.TomName  tomMatch168_14= (( tom.engine.adt.tomterm.types.TomTerm )term).getAstName() ;if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch168_14) instanceof tom.engine.adt.tomname.types.tomname.Name) ) {
 return  tomMatch168_14.getString() ;}}}}{ /* unamed block */if ( (term instanceof tom.engine.adt.tomterm.types.TomTerm) ) {if ( ((( tom.engine.adt.tomterm.types.TomTerm )term) instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) { tom.engine.adt.tomname.types.TomName  tomMatch168_21= (( tom.engine.adt.tomterm.types.TomTerm )term).getAstName() ;if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch168_21) instanceof tom.engine.adt.tomname.types.tomname.Name) ) {
 return  tomMatch168_21.getString() +"*";}}}}{ /* unamed block */if ( (term instanceof tom.engine.adt.tomterm.types.TomTerm) ) {if ( ((( tom.engine.adt.tomterm.types.TomTerm )term) instanceof tom.engine.adt.tomterm.types.tomterm.AntiTerm) ) {
 return getName( (( tom.engine.adt.tomterm.types.TomTerm )term).getTomTerm() ); }}}}

    throw new TomRuntimeException("Invalid Term:" + term);
  }

  
  protected String findOriginTrackingFileName(OptionList optionList) {
    { /* unamed block */{ /* unamed block */if ( (optionList instanceof tom.engine.adt.tomoption.types.OptionList) ) {if ( (((( tom.engine.adt.tomoption.types.OptionList )optionList) instanceof tom.engine.adt.tomoption.types.optionlist.ConsconcOption) || ((( tom.engine.adt.tomoption.types.OptionList )optionList) instanceof tom.engine.adt.tomoption.types.optionlist.EmptyconcOption)) ) { tom.engine.adt.tomoption.types.OptionList  tomMatch169_end_4=(( tom.engine.adt.tomoption.types.OptionList )optionList);do {{ /* unamed block */if (!( tomMatch169_end_4.isEmptyconcOption() )) { tom.engine.adt.tomoption.types.Option  tomMatch169_8= tomMatch169_end_4.getHeadconcOption() ;if ( ((( tom.engine.adt.tomoption.types.Option )tomMatch169_8) instanceof tom.engine.adt.tomoption.types.option.OriginTracking) ) {
 return  tomMatch169_8.getFileName() ; }}if ( tomMatch169_end_4.isEmptyconcOption() ) {tomMatch169_end_4=(( tom.engine.adt.tomoption.types.OptionList )optionList);} else {tomMatch169_end_4= tomMatch169_end_4.getTailconcOption() ;}}} while(!( (tomMatch169_end_4==(( tom.engine.adt.tomoption.types.OptionList )optionList)) ));}}}}

    return "unknown filename";
  }

  protected int findOriginTrackingLine(OptionList optionList) {
    { /* unamed block */{ /* unamed block */if ( (optionList instanceof tom.engine.adt.tomoption.types.OptionList) ) {if ( (((( tom.engine.adt.tomoption.types.OptionList )optionList) instanceof tom.engine.adt.tomoption.types.optionlist.ConsconcOption) || ((( tom.engine.adt.tomoption.types.OptionList )optionList) instanceof tom.engine.adt.tomoption.types.optionlist.EmptyconcOption)) ) { tom.engine.adt.tomoption.types.OptionList  tomMatch170_end_4=(( tom.engine.adt.tomoption.types.OptionList )optionList);do {{ /* unamed block */if (!( tomMatch170_end_4.isEmptyconcOption() )) { tom.engine.adt.tomoption.types.Option  tomMatch170_8= tomMatch170_end_4.getHeadconcOption() ;if ( ((( tom.engine.adt.tomoption.types.Option )tomMatch170_8) instanceof tom.engine.adt.tomoption.types.option.OriginTracking) ) {
 return  tomMatch170_8.getLine() ; }}if ( tomMatch170_end_4.isEmptyconcOption() ) {tomMatch170_end_4=(( tom.engine.adt.tomoption.types.OptionList )optionList);} else {tomMatch170_end_4= tomMatch170_end_4.getTailconcOption() ;}}} while(!( (tomMatch170_end_4==(( tom.engine.adt.tomoption.types.OptionList )optionList)) ));}}}}

    return -1;
  }

  public void run(Map informationTracker) {
    if(isActivated()) {
      long startChrono = System.currentTimeMillis();
      try {
        
        reinit();
        
        try {
          Code subject = (Code) getWorkingTerm();
          tom_make_TopDownCollect( new checkTypeInference(this) ).visitLight(subject);
        } catch(tom.library.sl.VisitFailure e) {
          System.out.println("strategy failed");
        }
        
        TomMessage.info(getLogger(), getStreamManager().getInputFileName(), 0,
            TomMessage.tomTypeCheckingPhase,
            Integer.valueOf((int)(System.currentTimeMillis()-startChrono)));
      } catch (Exception e) {
        TomMessage.error(getLogger(), 
            getStreamManager().getInputFileName(), 0,
            TomMessage.exceptionMessage,
            getClass().getName(), 
            getStreamManager().getInputFileName(),
            e.getMessage() );
        e.printStackTrace();
      }
    } else {
      
      TomMessage.info(getLogger(), getStreamManager().getInputFileName(), 0,
          TomMessage.typeCheckerInactivated);
    }
  }

  private boolean isActivated() {
    return !getOptionBooleanValue("noTypeCheck");
  }

  

  public static class checkTypeInference extends tom.library.sl.AbstractStrategyBasic {private  TypeCheckerPlugin  tcp;public checkTypeInference( TypeCheckerPlugin  tcp) {super(( new tom.library.sl.Identity() ));this.tcp=tcp;}public  TypeCheckerPlugin  gettcp() {return tcp;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChildren = new tom.library.sl.Visitable[getChildCount()];stratChildren[0] = super.getChildAt(0);return stratChildren;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tominstruction.types.Instruction) ) {return ((T)visit_Instruction((( tom.engine.adt.tominstruction.types.Instruction )v),introspector));}if ( (v instanceof tom.engine.adt.tomdeclaration.types.Declaration) ) {return ((T)visit_Declaration((( tom.engine.adt.tomdeclaration.types.Declaration )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tomdeclaration.types.Declaration  _visit_Declaration( tom.engine.adt.tomdeclaration.types.Declaration  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.tomdeclaration.types.Declaration )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  _visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.tominstruction.types.Instruction )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tomdeclaration.types.Declaration  visit_Declaration( tom.engine.adt.tomdeclaration.types.Declaration  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{ /* unamed block */{ /* unamed block */if ( (tom__arg instanceof tom.engine.adt.tomdeclaration.types.Declaration) ) {if ( ((( tom.engine.adt.tomdeclaration.types.Declaration )tom__arg) instanceof tom.engine.adt.tomdeclaration.types.declaration.Strategy) ) {










        tcp.currentTomStructureOrgTrack =  (( tom.engine.adt.tomdeclaration.types.Declaration )tom__arg).getOrgTrack() ;
        tcp.verifyStrategyVariable( (( tom.engine.adt.tomdeclaration.types.Declaration )tom__arg).getVisitList() );
        throw new tom.library.sl.VisitFailure();
      }}}}return _visit_Declaration(tom__arg,introspector);}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{ /* unamed block */{ /* unamed block */if ( (tom__arg instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tom__arg) instanceof tom.engine.adt.tominstruction.types.instruction.Match) ) {           tcp.currentTomStructureOrgTrack = TomBase.findOriginTracking( (( tom.engine.adt.tominstruction.types.Instruction )tom__arg).getOptions() );         tcp.verifyMatchVariable( (( tom.engine.adt.tominstruction.types.Instruction )tom__arg).getConstraintInstructionList() );         throw new tom.library.sl.VisitFailure();       }}}}return _visit_Instruction(tom__arg,introspector);}}




  private void verifyMatchVariable(ConstraintInstructionList constraintInstructionList) throws VisitFailure {
    { /* unamed block */{ /* unamed block */if ( (constraintInstructionList instanceof tom.engine.adt.tominstruction.types.ConstraintInstructionList) ) {if ( (((( tom.engine.adt.tominstruction.types.ConstraintInstructionList )constraintInstructionList) instanceof tom.engine.adt.tominstruction.types.constraintinstructionlist.ConsconcConstraintInstruction) || ((( tom.engine.adt.tominstruction.types.ConstraintInstructionList )constraintInstructionList) instanceof tom.engine.adt.tominstruction.types.constraintinstructionlist.EmptyconcConstraintInstruction)) ) { tom.engine.adt.tominstruction.types.ConstraintInstructionList  tomMatch173_end_4=(( tom.engine.adt.tominstruction.types.ConstraintInstructionList )constraintInstructionList);do {{ /* unamed block */if (!( tomMatch173_end_4.isEmptyconcConstraintInstruction() )) { tom.engine.adt.tominstruction.types.ConstraintInstruction  tomMatch173_9= tomMatch173_end_4.getHeadconcConstraintInstruction() ;if ( ((( tom.engine.adt.tominstruction.types.ConstraintInstruction )tomMatch173_9) instanceof tom.engine.adt.tominstruction.types.constraintinstruction.ConstraintInstruction) ) {

        
        ArrayList<TomTerm> variableList = new ArrayList<TomTerm>();
        TomBase.collectVariable(variableList,  tomMatch173_9.getConstraint() , false); 
        verifyVariableTypeListCoherence(variableList);        

        
        verifyListVariableInAction( tomMatch173_9.getAction() );
      }}if ( tomMatch173_end_4.isEmptyconcConstraintInstruction() ) {tomMatch173_end_4=(( tom.engine.adt.tominstruction.types.ConstraintInstructionList )constraintInstructionList);} else {tomMatch173_end_4= tomMatch173_end_4.getTailconcConstraintInstruction() ;}}} while(!( (tomMatch173_end_4==(( tom.engine.adt.tominstruction.types.ConstraintInstructionList )constraintInstructionList)) ));}}}}
    
  }

  
  private void verifyVariableTypeListCoherence(ArrayList<TomTerm> list) {
    
    HashMap<TomName,TomTerm> map = new HashMap<TomName,TomTerm>();
    for(TomTerm variable:list) {
      TomName name = variable.getAstName();
      if(map.containsKey(name)) {
        TomTerm var = map.get(name);
        TomType type1 = var.getAstType();
        TomType type2 = variable.getAstType();
        
        
        if(!TomBase.getTomType(type1).equals(TomBase.getTomType(type2))) {
          TomMessage.error(getLogger(),
              findOriginTrackingFileName(variable.getOptions()),
              findOriginTrackingLine(variable.getOptions()),
              TomMessage.incoherentVariable,
              name.getString(),
              TomBase.getTomType(type1),
              TomBase.getTomType(type2));
        }
      } else {
        map.put(name,variable);
      }
    }
  }

  
  private void verifyListVariableInAction(Instruction action) {
    try {
      tom_make_TopDown( new checkVariableStar(this) ).visitLight(action);
    } catch(VisitFailure e) {
      System.out.println("strategy failed");
    }
  }

  public static class checkVariableStar extends tom.library.sl.AbstractStrategyBasic {private  TypeCheckerPlugin  tcp;public checkVariableStar( TypeCheckerPlugin  tcp) {super(( new tom.library.sl.Identity() ));this.tcp=tcp;}public  TypeCheckerPlugin  gettcp() {return tcp;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChildren = new tom.library.sl.Visitable[getChildCount()];stratChildren[0] = super.getChildAt(0);return stratChildren;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.code.types.BQTerm) ) {return ((T)visit_BQTerm((( tom.engine.adt.code.types.BQTerm )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.code.types.BQTerm  _visit_BQTerm( tom.engine.adt.code.types.BQTerm  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.code.types.BQTerm )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.code.types.BQTerm  visit_BQTerm( tom.engine.adt.code.types.BQTerm  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{ /* unamed block */{ /* unamed block */if ( (tom__arg instanceof tom.engine.adt.code.types.BQTerm) ) {boolean tomMatch174_33= false ; tom.engine.adt.code.types.BQTerm  tomMatch174_5= null ; tom.engine.adt.code.types.BQTerm  tomMatch174_6= null ; tom.engine.adt.code.types.BQTerm  tomMatch174_3= null ; tom.engine.adt.tomname.types.TomName  tomMatch174_2= null ;if ( ((( tom.engine.adt.code.types.BQTerm )tom__arg) instanceof tom.engine.adt.code.types.bqterm.BuildAppendList) ) {{ /* unamed block */tomMatch174_33= true ;tomMatch174_5=(( tom.engine.adt.code.types.BQTerm )tom__arg);tomMatch174_2= tomMatch174_5.getAstName() ;tomMatch174_3= tomMatch174_5.getHeadTerm() ;}} else {if ( ((( tom.engine.adt.code.types.BQTerm )tom__arg) instanceof tom.engine.adt.code.types.bqterm.BuildAppendArray) ) {{ /* unamed block */tomMatch174_33= true ;tomMatch174_6=(( tom.engine.adt.code.types.BQTerm )tom__arg);tomMatch174_2= tomMatch174_6.getAstName() ;tomMatch174_3= tomMatch174_6.getHeadTerm() ;}}}if (tomMatch174_33) {if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch174_2) instanceof tom.engine.adt.tomname.types.tomname.Name) ) { String  tom___listName= tomMatch174_2.getString() ;if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch174_3) instanceof tom.engine.adt.code.types.bqterm.BQVariableStar) ) { tom.engine.adt.tomname.types.TomName  tomMatch174_11= tomMatch174_3.getAstName() ; tom.engine.adt.tomtype.types.TomType  tomMatch174_12= tomMatch174_3.getAstType() ; tom.engine.adt.tomoption.types.OptionList  tom___optionList= tomMatch174_3.getOptions() ;if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch174_11) instanceof tom.engine.adt.tomname.types.tomname.Name) ) {if ( ((( tom.engine.adt.tomtype.types.TomType )tomMatch174_12) instanceof tom.engine.adt.tomtype.types.tomtype.Type) ) { tom.engine.adt.tomtype.types.TypeOptionList  tom___tOptions= tomMatch174_12.getTypeOptions() ;if ( (tom___tOptions instanceof tom.engine.adt.tomtype.types.TypeOptionList) ) {if ( (((( tom.engine.adt.tomtype.types.TypeOptionList )tom___tOptions) instanceof tom.engine.adt.tomtype.types.typeoptionlist.ConsconcTypeOption) || ((( tom.engine.adt.tomtype.types.TypeOptionList )tom___tOptions) instanceof tom.engine.adt.tomtype.types.typeoptionlist.EmptyconcTypeOption)) ) { tom.engine.adt.tomtype.types.TypeOptionList  tomMatch174_end_24=(( tom.engine.adt.tomtype.types.TypeOptionList )tom___tOptions);do {{ /* unamed block */if (!( tomMatch174_end_24.isEmptyconcTypeOption() )) { tom.engine.adt.tomtype.types.TypeOption  tomMatch174_28= tomMatch174_end_24.getHeadconcTypeOption() ;if ( ((( tom.engine.adt.tomtype.types.TypeOption )tomMatch174_28) instanceof tom.engine.adt.tomtype.types.typeoption.WithSymbol) ) { tom.engine.adt.tomname.types.TomName  tomMatch174_27= tomMatch174_28.getRootSymbolName() ;if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch174_27) instanceof tom.engine.adt.tomname.types.tomname.Name) ) { String  tom___rootName= tomMatch174_27.getString() ;



        
        if(!tom___listName.equals(tom___rootName)) { /* unamed block */
          TomMessage.error(tcp.getLogger(),
              tcp.findOriginTrackingFileName(tom___optionList),
              tcp.findOriginTrackingLine(tom___optionList),
              TomMessage.incoherentVariableStar,
               tomMatch174_11.getString() , tom___rootName, tom___listName);
        }}}}if ( tomMatch174_end_24.isEmptyconcTypeOption() ) {tomMatch174_end_24=(( tom.engine.adt.tomtype.types.TypeOptionList )tom___tOptions);} else {tomMatch174_end_24= tomMatch174_end_24.getTailconcTypeOption() ;}}} while(!( (tomMatch174_end_24==(( tom.engine.adt.tomtype.types.TypeOptionList )tom___tOptions)) ));}}}}}}}}}}return _visit_BQTerm(tom__arg,introspector);}}




  private void verifyStrategyVariable(TomVisitList list) {
    
    if(list.isEmptyconcTomVisit()) {
      TomMessage.error(getLogger(),null,0,TomMessage.emptyStrategy);
    }
        
    { /* unamed block */{ /* unamed block */if ( (list instanceof tom.engine.adt.tomsignature.types.TomVisitList) ) {if ( (((( tom.engine.adt.tomsignature.types.TomVisitList )list) instanceof tom.engine.adt.tomsignature.types.tomvisitlist.ConsconcTomVisit) || ((( tom.engine.adt.tomsignature.types.TomVisitList )list) instanceof tom.engine.adt.tomsignature.types.tomvisitlist.EmptyconcTomVisit)) ) { tom.engine.adt.tomsignature.types.TomVisitList  tomMatch175_end_4=(( tom.engine.adt.tomsignature.types.TomVisitList )list);do {{ /* unamed block */if (!( tomMatch175_end_4.isEmptyconcTomVisit() )) { tom.engine.adt.tomsignature.types.TomVisit  tomMatch175_10= tomMatch175_end_4.getHeadconcTomVisit() ;if ( ((( tom.engine.adt.tomsignature.types.TomVisit )tomMatch175_10) instanceof tom.engine.adt.tomsignature.types.tomvisit.VisitTerm) ) { tom.engine.adt.tomtype.types.TomType  tomMatch175_7= tomMatch175_10.getVNode() ;if ( ((( tom.engine.adt.tomtype.types.TomType )tomMatch175_7) instanceof tom.engine.adt.tomtype.types.tomtype.Type) ) {if ( ((( tom.engine.adt.tomtype.types.TargetLanguageType ) tomMatch175_7.getTlType() ) instanceof tom.engine.adt.tomtype.types.targetlanguagetype.EmptyTargetLanguageType) ) { tom.engine.adt.tomoption.types.OptionList  tom___optionList= tomMatch175_10.getOptions() ;

        TomMessage.error(getLogger(),
            findOriginTrackingFileName(tom___optionList),
            findOriginTrackingLine(tom___optionList),
            TomMessage.unknownVisitedType,
             tomMatch175_7.getTomType() );
      }}}}if ( tomMatch175_end_4.isEmptyconcTomVisit() ) {tomMatch175_end_4=(( tom.engine.adt.tomsignature.types.TomVisitList )list);} else {tomMatch175_end_4= tomMatch175_end_4.getTailconcTomVisit() ;}}} while(!( (tomMatch175_end_4==(( tom.engine.adt.tomsignature.types.TomVisitList )list)) ));}}}}

  }



}
