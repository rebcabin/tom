
























package tom.engine.compiler;



import java.util.*;



import java.lang.reflect.*;



import tom.engine.TomBase;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomterm.types.tomterm.*;
import tom.engine.adt.tomname.types.*;
import tom.engine.adt.tomname.types.tomname.*;
import tom.engine.adt.tominstruction.types.*;
import tom.engine.adt.tomconstraint.types.*;
import tom.engine.adt.tomconstraint.types.constraint.*;
import tom.engine.adt.tomexpression.types.*;
import tom.engine.tools.SymbolTable;
import tom.engine.compiler.*;
import tom.engine.compiler.generator.*;
import tom.engine.exception.TomRuntimeException;
import tom.engine.adt.tomsignature.types.*;
import tom.engine.adt.tomtype.types.*;
import tom.engine.adt.code.types.*;
import tom.library.sl.*;







public class ConstraintGenerator {


     private static   tom.engine.adt.tomexpression.types.Expression  tom_append_list_OrExpressionDisjunction( tom.engine.adt.tomexpression.types.Expression  l1,  tom.engine.adt.tomexpression.types.Expression  l2) {     if( l1.isEmptyOrExpressionDisjunction() ) {       return l2;     } else if( l2.isEmptyOrExpressionDisjunction() ) {       return l1;     } else if( ((l1 instanceof tom.engine.adt.tomexpression.types.expression.ConsOrExpressionDisjunction) || (l1 instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction)) ) {       if(  l1.getTailOrExpressionDisjunction() .isEmptyOrExpressionDisjunction() ) {         return  tom.engine.adt.tomexpression.types.expression.ConsOrExpressionDisjunction.make( l1.getHeadOrExpressionDisjunction() ,l2) ;       } else {         return  tom.engine.adt.tomexpression.types.expression.ConsOrExpressionDisjunction.make( l1.getHeadOrExpressionDisjunction() ,tom_append_list_OrExpressionDisjunction( l1.getTailOrExpressionDisjunction() ,l2)) ;       }     } else {       return  tom.engine.adt.tomexpression.types.expression.ConsOrExpressionDisjunction.make(l1,l2) ;     }   }   private static   tom.engine.adt.tomexpression.types.Expression  tom_get_slice_OrExpressionDisjunction( tom.engine.adt.tomexpression.types.Expression  begin,  tom.engine.adt.tomexpression.types.Expression  end, tom.engine.adt.tomexpression.types.Expression  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyOrExpressionDisjunction()  ||  (end== tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomexpression.types.expression.ConsOrExpressionDisjunction.make((( ((begin instanceof tom.engine.adt.tomexpression.types.expression.ConsOrExpressionDisjunction) || (begin instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction)) )? begin.getHeadOrExpressionDisjunction() :begin),( tom.engine.adt.tomexpression.types.Expression )tom_get_slice_OrExpressionDisjunction((( ((begin instanceof tom.engine.adt.tomexpression.types.expression.ConsOrExpressionDisjunction) || (begin instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction)) )? begin.getTailOrExpressionDisjunction() : tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction.make() ),end,tail)) ;   }      private static   tom.engine.adt.tomexpression.types.Expression  tom_append_list_OrConnector( tom.engine.adt.tomexpression.types.Expression  l1,  tom.engine.adt.tomexpression.types.Expression  l2) {     if( l1.isEmptyOrConnector() ) {       return l2;     } else if( l2.isEmptyOrConnector() ) {       return l1;     } else if( ((l1 instanceof tom.engine.adt.tomexpression.types.expression.ConsOrConnector) || (l1 instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrConnector)) ) {       if(  l1.getTailOrConnector() .isEmptyOrConnector() ) {         return  tom.engine.adt.tomexpression.types.expression.ConsOrConnector.make( l1.getHeadOrConnector() ,l2) ;       } else {         return  tom.engine.adt.tomexpression.types.expression.ConsOrConnector.make( l1.getHeadOrConnector() ,tom_append_list_OrConnector( l1.getTailOrConnector() ,l2)) ;       }     } else {       return  tom.engine.adt.tomexpression.types.expression.ConsOrConnector.make(l1,l2) ;     }   }   private static   tom.engine.adt.tomexpression.types.Expression  tom_get_slice_OrConnector( tom.engine.adt.tomexpression.types.Expression  begin,  tom.engine.adt.tomexpression.types.Expression  end, tom.engine.adt.tomexpression.types.Expression  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyOrConnector()  ||  (end== tom.engine.adt.tomexpression.types.expression.EmptyOrConnector.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomexpression.types.expression.ConsOrConnector.make((( ((begin instanceof tom.engine.adt.tomexpression.types.expression.ConsOrConnector) || (begin instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrConnector)) )? begin.getHeadOrConnector() :begin),( tom.engine.adt.tomexpression.types.Expression )tom_get_slice_OrConnector((( ((begin instanceof tom.engine.adt.tomexpression.types.expression.ConsOrConnector) || (begin instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrConnector)) )? begin.getTailOrConnector() : tom.engine.adt.tomexpression.types.expression.EmptyOrConnector.make() ),end,tail)) ;   }      private static   tom.engine.adt.code.types.BQTermList  tom_append_list_concBQTerm( tom.engine.adt.code.types.BQTermList l1,  tom.engine.adt.code.types.BQTermList  l2) {     if( l1.isEmptyconcBQTerm() ) {       return l2;     } else if( l2.isEmptyconcBQTerm() ) {       return l1;     } else if(  l1.getTailconcBQTerm() .isEmptyconcBQTerm() ) {       return  tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make( l1.getHeadconcBQTerm() ,l2) ;     } else {       return  tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make( l1.getHeadconcBQTerm() ,tom_append_list_concBQTerm( l1.getTailconcBQTerm() ,l2)) ;     }   }   private static   tom.engine.adt.code.types.BQTermList  tom_get_slice_concBQTerm( tom.engine.adt.code.types.BQTermList  begin,  tom.engine.adt.code.types.BQTermList  end, tom.engine.adt.code.types.BQTermList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcBQTerm()  ||  (end== tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make( begin.getHeadconcBQTerm() ,( tom.engine.adt.code.types.BQTermList )tom_get_slice_concBQTerm( begin.getTailconcBQTerm() ,end,tail)) ;   }      private static   tom.engine.adt.tominstruction.types.InstructionList  tom_append_list_concInstruction( tom.engine.adt.tominstruction.types.InstructionList l1,  tom.engine.adt.tominstruction.types.InstructionList  l2) {     if( l1.isEmptyconcInstruction() ) {       return l2;     } else if( l2.isEmptyconcInstruction() ) {       return l1;     } else if(  l1.getTailconcInstruction() .isEmptyconcInstruction() ) {       return  tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( l1.getHeadconcInstruction() ,l2) ;     } else {       return  tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( l1.getHeadconcInstruction() ,tom_append_list_concInstruction( l1.getTailconcInstruction() ,l2)) ;     }   }   private static   tom.engine.adt.tominstruction.types.InstructionList  tom_get_slice_concInstruction( tom.engine.adt.tominstruction.types.InstructionList  begin,  tom.engine.adt.tominstruction.types.InstructionList  end, tom.engine.adt.tominstruction.types.InstructionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcInstruction()  ||  (end== tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( begin.getHeadconcInstruction() ,( tom.engine.adt.tominstruction.types.InstructionList )tom_get_slice_concInstruction( begin.getTailconcInstruction() ,end,tail)) ;   }      private static   tom.library.sl.Strategy  tom_append_list_Sequence( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 == null )) {       return l2;     } else if(( l2 == null )) {       return l1;     } else if(( l1 instanceof tom.library.sl.Sequence )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ) == null )) {         return  tom.library.sl.Sequence.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ),l2) ;       } else {         return  tom.library.sl.Sequence.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ),tom_append_list_Sequence(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ),l2)) ;       }     } else {       return  tom.library.sl.Sequence.make(l1,l2) ;     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals( null )) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.sl.Sequence.make(((( begin instanceof tom.library.sl.Sequence ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(((( begin instanceof tom.library.sl.Sequence ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ): null ),end,tail)) ;   }      private static   tom.library.sl.Strategy  tom_append_list_Choice( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 ==null )) {       return l2;     } else if(( l2 ==null )) {       return l1;     } else if(( l1 instanceof tom.library.sl.Choice )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ) ==null )) {         return  tom.library.sl.Choice.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ),l2) ;       } else {         return  tom.library.sl.Choice.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ),tom_append_list_Choice(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ),l2)) ;       }     } else {       return  tom.library.sl.Choice.make(l1,l2) ;     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Choice( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end ==null ) ||  (end.equals( null )) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.sl.Choice.make(((( begin instanceof tom.library.sl.Choice ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Choice(((( begin instanceof tom.library.sl.Choice ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.THEN) ): null ),end,tail)) ;   }      private static   tom.library.sl.Strategy  tom_append_list_SequenceId( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 == null )) {       return l2;     } else if(( l2 == null )) {       return l1;     } else if(( l1 instanceof tom.library.sl.SequenceId )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.THEN) ) == null )) {         return  tom.library.sl.SequenceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.FIRST) ),l2) ;       } else {         return  tom.library.sl.SequenceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.FIRST) ),tom_append_list_SequenceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.THEN) ),l2)) ;       }     } else {       return  tom.library.sl.SequenceId.make(l1,l2) ;     }   }   private static   tom.library.sl.Strategy  tom_get_slice_SequenceId( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals( null )) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.sl.SequenceId.make(((( begin instanceof tom.library.sl.SequenceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.SequenceId.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_SequenceId(((( begin instanceof tom.library.sl.SequenceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.SequenceId.THEN) ): null ),end,tail)) ;   }      private static   tom.library.sl.Strategy  tom_append_list_ChoiceId( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 ==null )) {       return l2;     } else if(( l2 ==null )) {       return l1;     } else if(( l1 instanceof tom.library.sl.ChoiceId )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.THEN) ) ==null )) {         return  tom.library.sl.ChoiceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ),l2) ;       } else {         return  tom.library.sl.ChoiceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ),tom_append_list_ChoiceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.THEN) ),l2)) ;       }     } else {       return  tom.library.sl.ChoiceId.make(l1,l2) ;     }   }   private static   tom.library.sl.Strategy  tom_get_slice_ChoiceId( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end ==null ) ||  (end.equals( null )) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.sl.ChoiceId.make(((( begin instanceof tom.library.sl.ChoiceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_ChoiceId(((( begin instanceof tom.library.sl.ChoiceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.THEN) ): null ),end,tail)) ;   }   private static  tom.library.sl.Strategy  tom_make_AUCtl( tom.library.sl.Strategy  s1,  tom.library.sl.Strategy  s2) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("x") ), tom.library.sl.Choice.make(s2, tom.library.sl.Choice.make( tom.library.sl.Sequence.make( tom.library.sl.Sequence.make(s1, tom.library.sl.Sequence.make(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ), null ) ) , tom.library.sl.Sequence.make(( new tom.library.sl.One(( new tom.library.sl.Identity() )) ), null ) ) , null ) ) ) ) );}private static  tom.library.sl.Strategy  tom_make_EUCtl( tom.library.sl.Strategy  s1,  tom.library.sl.Strategy  s2) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("x") ), tom.library.sl.Choice.make(s2, tom.library.sl.Choice.make( tom.library.sl.Sequence.make(s1, tom.library.sl.Sequence.make(( new tom.library.sl.One(( new tom.library.sl.MuVar("x") )) ), null ) ) , null ) ) ) ));}private static  tom.library.sl.Strategy  tom_make_Try( tom.library.sl.Strategy  s) { return (  tom.library.sl.Choice.make(s, tom.library.sl.Choice.make(( new tom.library.sl.Identity() ), null ) )  );}private static  tom.library.sl.Strategy  tom_make_Repeat( tom.library.sl.Strategy  s) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.Choice.make( tom.library.sl.Sequence.make(s, tom.library.sl.Sequence.make(( new tom.library.sl.MuVar("_x") ), null ) ) , tom.library.sl.Choice.make(( new tom.library.sl.Identity() ), null ) ) ) ) );}private static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.Sequence.make(v, tom.library.sl.Sequence.make(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ), null ) ) ) ) );}private static  tom.library.sl.Strategy  tom_make_TopDownCollect( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ),tom_make_Try( tom.library.sl.Sequence.make(v, tom.library.sl.Sequence.make(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ), null ) ) )) ) );}private static  tom.library.sl.Strategy  tom_make_OnceTopDown( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.Choice.make(v, tom.library.sl.Choice.make(( new tom.library.sl.One(( new tom.library.sl.MuVar("_x") )) ), null ) ) ) ) );}private static  tom.library.sl.Strategy  tom_make_RepeatId( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.SequenceId.make(v, tom.library.sl.SequenceId.make(( new tom.library.sl.MuVar("_x") ), null ) ) ) ) );}private static  tom.library.sl.Strategy  tom_make_OnceTopDownId( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.ChoiceId.make(v, tom.library.sl.ChoiceId.make(( new tom.library.sl.OneId(( new tom.library.sl.MuVar("_x") )) ), null ) ) ) ) );}














  private Compiler compiler;

  public ConstraintGenerator(Compiler myCompiler) {
    this.compiler = myCompiler; 
  } 

  public Compiler getCompiler() {
    return this.compiler;
  }
 
  private static final String generatorsPackage = "tom.engine.compiler.generator.";
  
  private static final String[] generatorsNames = {"ACGenerator", "SyntacticGenerator","VariadicGenerator","ArrayGenerator"};
  
  public static final String computeLengthFuncName = "__computeLength";
  public static final String multiplicityFuncName = "__getMultiplicities";
  public static final String getTermForMultiplicityFuncName = "__getTermForMult";  

  public Instruction performGenerations(Expression expression, Instruction action) 
       throws ClassNotFoundException,InstantiationException,IllegalAccessException,VisitFailure,InvocationTargetException,NoSuchMethodException{
    
    int genCounter = 0;
    int genNb = generatorsNames.length;
    
    Expression result = null;
    
    IBaseGenerator[] gen = new IBaseGenerator[genNb];
    Class[] classTab = new Class[]{Class.forName("tom.engine.compiler.Compiler"), Class.forName("tom.engine.compiler.ConstraintGenerator")};
    for(int i=0 ; i < genNb ; i++) {
      Class myClass = Class.forName(generatorsPackage + generatorsNames[i]);
      java.lang.reflect.Constructor constructor = myClass.getConstructor(classTab);
      gen[i] = (IBaseGenerator)constructor.newInstance(this.getCompiler(),this);
    }
    
    
    mainLoop: while(true) {
      for(int i=0 ; i < genNb ; i++) {
        result = gen[i].generate(expression);
        
        genCounter = (result == expression) ? (genCounter + 1) : 0;        				
        
        
        if (genCounter == genNb) { break mainLoop; }
        
        expression = result; 
      }
    } 
    return buildInstructionFromExpression(result,action);
  }
  
  
  private Instruction buildInstructionFromExpression(Expression expression, Instruction action)
      throws VisitFailure {		
    
    expression = tom_make_TopDown( new ReplaceSubterms(this) ).visitLight(expression);
    
    return generateAutomata(expression,action);    
  }

  
  private Instruction generateAutomata(Expression expression, Instruction action) throws VisitFailure {
    { /* unamed block */{ /* unamed block */if ( (expression instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )expression) instanceof tom.engine.adt.tomexpression.types.expression.And) ) {

        Instruction subInstruction = generateAutomata( (( tom.engine.adt.tomexpression.types.Expression )expression).getArg2() ,action);
        return generateAutomata( (( tom.engine.adt.tomexpression.types.Expression )expression).getArg1() ,subInstruction);
      }}}{ /* unamed block */if ( (expression instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( (((( tom.engine.adt.tomexpression.types.Expression )expression) instanceof tom.engine.adt.tomexpression.types.expression.ConsOrConnector) || ((( tom.engine.adt.tomexpression.types.Expression )expression) instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrConnector)) ) {
        
        return buildConstraintDisjunction((( tom.engine.adt.tomexpression.types.Expression )expression),action);
      }}}{ /* unamed block */if ( (expression instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )expression) instanceof tom.engine.adt.tomexpression.types.expression.ConstraintToExpression) ) { tom.engine.adt.tomconstraint.types.Constraint  tomMatch190_9= (( tom.engine.adt.tomexpression.types.Expression )expression).getcons() ;if ( ((( tom.engine.adt.tomconstraint.types.Constraint )tomMatch190_9) instanceof tom.engine.adt.tomconstraint.types.constraint.MatchConstraint) ) { tom.engine.adt.tomterm.types.TomTerm  tomMatch190_12= tomMatch190_9.getPattern() ;boolean tomMatch190_19= false ; tom.engine.adt.tomterm.types.TomTerm  tomMatch190_17= null ; tom.engine.adt.tomterm.types.TomTerm  tomMatch190_18= null ;if ( ((( tom.engine.adt.tomterm.types.TomTerm )tomMatch190_12) instanceof tom.engine.adt.tomterm.types.tomterm.Variable) ) {{ /* unamed block */tomMatch190_19= true ;tomMatch190_17=tomMatch190_12;}} else {if ( ((( tom.engine.adt.tomterm.types.TomTerm )tomMatch190_12) instanceof tom.engine.adt.tomterm.types.tomterm.VariableStar) ) {{ /* unamed block */tomMatch190_19= true ;tomMatch190_18=tomMatch190_12;}}}if (tomMatch190_19) { tom.engine.adt.tomterm.types.TomTerm  tom___v=tomMatch190_12; tom.engine.adt.code.types.BQTerm  tom___t= tomMatch190_9.getSubject() ;


        SymbolTable symbolTable = getCompiler().getSymbolTable();

        
        
        TomType patternType = TomBase.getTermType(tom___v,symbolTable);
        TomType subjectType = TomBase.getTermType(tom___t,symbolTable);

        
        

        
        
        
        
        
        
        
        


        if(TomBase.getTomType(patternType) != TomBase.getTomType(subjectType)) { /* unamed block */
          return  tom.engine.adt.tominstruction.types.instruction.LetRef.make(TomBase.convertFromVarToBQVar(tom___v),  tom.engine.adt.tomexpression.types.expression.Cast.make(patternType,  tom.engine.adt.tomexpression.types.expression.BQTermToExpression.make(tom___t) ) , action) ;
        } else { /* unamed block */
          return  tom.engine.adt.tominstruction.types.instruction.LetRef.make(TomBase.convertFromVarToBQVar(tom___v),  tom.engine.adt.tomexpression.types.expression.BQTermToExpression.make(tom___t) , action) ;
        }}}}}}{ /* unamed block */if ( (expression instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )expression) instanceof tom.engine.adt.tomexpression.types.expression.ConstraintToExpression) ) { tom.engine.adt.tomconstraint.types.Constraint  tomMatch190_21= (( tom.engine.adt.tomexpression.types.Expression )expression).getcons() ;if ( ((( tom.engine.adt.tomconstraint.types.Constraint )tomMatch190_21) instanceof tom.engine.adt.tomconstraint.types.constraint.NumericConstraint) ) {




        return buildNumericCondition(tomMatch190_21,action);
      }}}}{ /* unamed block */if ( (expression instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )expression) instanceof tom.engine.adt.tomexpression.types.expression.DoWhileExpression) ) {


        Instruction subInstruction = generateAutomata( (( tom.engine.adt.tomexpression.types.Expression )expression).getEndExpression() , tom.engine.adt.tominstruction.types.instruction.Nop.make() );
        return  tom.engine.adt.tominstruction.types.instruction.DoWhile.make( tom.engine.adt.tominstruction.types.instruction.UnamedBlock.make( tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(action, tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(subInstruction, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) ) ) ,  (( tom.engine.adt.tomexpression.types.Expression )expression).getLoopCondition() ) ;
      }}}{ /* unamed block */if ( (expression instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )expression) instanceof tom.engine.adt.tomexpression.types.expression.TomInstructionToExpression) ) {


        return  (( tom.engine.adt.tomexpression.types.Expression )expression).getInstruction() ;
      }}}{ /* unamed block */if ( (expression instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( (((( tom.engine.adt.tomexpression.types.Expression )expression) instanceof tom.engine.adt.tomexpression.types.expression.ConsOrExpressionDisjunction) || ((( tom.engine.adt.tomexpression.types.Expression )expression) instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction)) ) {


        return buildExpressionDisjunction((( tom.engine.adt.tomexpression.types.Expression )expression),action);
      }}}{ /* unamed block */if ( (expression instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )expression) instanceof tom.engine.adt.tomexpression.types.expression.AntiMatchExpression) ) {


        return buildAntiMatchInstruction( (( tom.engine.adt.tomexpression.types.Expression )expression).getExpression() ,action);
      }}}{ /* unamed block */if ( (expression instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )expression) instanceof tom.engine.adt.tomexpression.types.expression.ACMatchLoop) ) {


        return buildACMatchLoop( (( tom.engine.adt.tomexpression.types.Expression )expression).getSymbolName() , (( tom.engine.adt.tomexpression.types.Expression )expression).getVariableX() , (( tom.engine.adt.tomexpression.types.Expression )expression).getVariableY() , (( tom.engine.adt.tomexpression.types.Expression )expression).getMultiplicityY() , (( tom.engine.adt.tomexpression.types.Expression )expression).getSubject() ,action);
      }}}{ /* unamed block */if ( (expression instanceof tom.engine.adt.tomexpression.types.Expression) ) {


        return  tom.engine.adt.tominstruction.types.instruction.If.make((( tom.engine.adt.tomexpression.types.Expression )expression), action,  tom.engine.adt.tominstruction.types.instruction.Nop.make() ) ;
      }}}

    throw new TomRuntimeException("ConstraintGenerator.generateAutomata - strange expression:" + expression);
  }

  
  public static class ReplaceSubterms extends tom.library.sl.AbstractStrategyBasic {private  ConstraintGenerator  cg;public ReplaceSubterms( ConstraintGenerator  cg) {super(( new tom.library.sl.Identity() ));this.cg=cg;}public  ConstraintGenerator  getcg() {return cg;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChildren = new tom.library.sl.Visitable[getChildCount()];stratChildren[0] = super.getChildAt(0);return stratChildren;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.code.types.BQTerm) ) {return ((T)visit_BQTerm((( tom.engine.adt.code.types.BQTerm )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.code.types.BQTerm  _visit_BQTerm( tom.engine.adt.code.types.BQTerm  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.code.types.BQTerm )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.code.types.BQTerm  visit_BQTerm( tom.engine.adt.code.types.BQTerm  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{ /* unamed block */{ /* unamed block */if ( (tom__arg instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )tom__arg) instanceof tom.engine.adt.code.types.bqterm.Subterm) ) { tom.engine.adt.tomname.types.TomName  tomMatch191_1= (( tom.engine.adt.code.types.BQTerm )tom__arg).getAstName() ;if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch191_1) instanceof tom.engine.adt.tomname.types.tomname.Name) ) { tom.engine.adt.tomname.types.TomName  tom___slotName= (( tom.engine.adt.code.types.BQTerm )tom__arg).getSlotName() ;


        TomSymbol tomSymbol = cg.getCompiler().getSymbolTable().getSymbolFromName( tomMatch191_1.getString() );
        TomType subtermType = TomBase.getSlotType(tomSymbol, tom___slotName);	        	
        return  tom.engine.adt.code.types.bqterm.ExpressionToBQTerm.make( tom.engine.adt.tomexpression.types.expression.GetSlot.make(subtermType, tomMatch191_1, tom___slotName.getString(),  (( tom.engine.adt.code.types.BQTerm )tom__arg).getGroundTerm() ) ) ;
      }}}}}return _visit_BQTerm(tom__arg,introspector);}}




  
  private Instruction buildConstraintDisjunction(Expression orConnector, Instruction action) throws VisitFailure {    
    { /* unamed block */{ /* unamed block */if ( (orConnector instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( (((( tom.engine.adt.tomexpression.types.Expression )orConnector) instanceof tom.engine.adt.tomexpression.types.expression.ConsOrConnector) || ((( tom.engine.adt.tomexpression.types.Expression )orConnector) instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrConnector)) ) {if (!( (  (( tom.engine.adt.tomexpression.types.Expression )orConnector).isEmptyOrConnector()  ||  ((( tom.engine.adt.tomexpression.types.Expression )orConnector)== tom.engine.adt.tomexpression.types.expression.EmptyOrConnector.make() )  ) )) {
        
        return  tom.engine.adt.tominstruction.types.instruction.AbstractBlock.make( tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(generateAutomata((( (((( tom.engine.adt.tomexpression.types.Expression )orConnector) instanceof tom.engine.adt.tomexpression.types.expression.ConsOrConnector) || ((( tom.engine.adt.tomexpression.types.Expression )orConnector) instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrConnector)) )?( (( tom.engine.adt.tomexpression.types.Expression )orConnector).getHeadOrConnector() ):((( tom.engine.adt.tomexpression.types.Expression )orConnector))),action), tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(buildConstraintDisjunction((( (((( tom.engine.adt.tomexpression.types.Expression )orConnector) instanceof tom.engine.adt.tomexpression.types.expression.ConsOrConnector) || ((( tom.engine.adt.tomexpression.types.Expression )orConnector) instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrConnector)) )?( (( tom.engine.adt.tomexpression.types.Expression )orConnector).getTailOrConnector() ):( tom.engine.adt.tomexpression.types.expression.EmptyOrConnector.make() )),action), tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) ) ) 


;
      }}}}}

    return  tom.engine.adt.tominstruction.types.instruction.Nop.make() ;
  }

  
  private Instruction buildExpressionDisjunction(Expression orDisjunction,Instruction action)
         throws VisitFailure {     
    BQTerm flag = getCompiler().getFreshVariable(getCompiler().getSymbolTable().getBooleanType());
    Instruction assignFlagTrue =  tom.engine.adt.tominstruction.types.instruction.Assign.make(flag,  tom.engine.adt.tomexpression.types.expression.TrueTL.make() ) ;
    Collection<BQTerm> freshVarList = new HashSet<BQTerm>();
    
    tom_make_TopDown( new CollectVar(freshVarList) ).visitLight(orDisjunction);    
    Instruction instruction = buildDisjunctionIfElse(orDisjunction,assignFlagTrue);
    
    instruction =  tom.engine.adt.tominstruction.types.instruction.AbstractBlock.make( tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(instruction, tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( tom.engine.adt.tominstruction.types.instruction.If.make( tom.engine.adt.tomexpression.types.expression.BQTermToExpression.make(flag) , action,  tom.engine.adt.tominstruction.types.instruction.Nop.make() ) , tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) ) ) ;    
    
    for(BQTerm var:freshVarList) {
      instruction =  tom.engine.adt.tominstruction.types.instruction.LetRef.make(var,  tom.engine.adt.tomexpression.types.expression.Bottom.make(var.getAstType()) , instruction) ;
    }
    
    return  tom.engine.adt.tominstruction.types.instruction.LetRef.make(flag,  tom.engine.adt.tomexpression.types.expression.FalseTL.make() , instruction) ;
  }

  private Instruction buildDisjunctionIfElse(Expression orDisjunction,Instruction assignFlagTrue)
      throws VisitFailure {    
    { /* unamed block */{ /* unamed block */if ( (orDisjunction instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( (((( tom.engine.adt.tomexpression.types.Expression )orDisjunction) instanceof tom.engine.adt.tomexpression.types.expression.ConsOrExpressionDisjunction) || ((( tom.engine.adt.tomexpression.types.Expression )orDisjunction) instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction)) ) {if ( (  (( tom.engine.adt.tomexpression.types.Expression )orDisjunction).isEmptyOrExpressionDisjunction()  ||  ((( tom.engine.adt.tomexpression.types.Expression )orDisjunction)== tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction.make() )  ) ) {

        return  tom.engine.adt.tominstruction.types.instruction.Nop.make() ;
      }}}}{ /* unamed block */if ( (orDisjunction instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( (((( tom.engine.adt.tomexpression.types.Expression )orDisjunction) instanceof tom.engine.adt.tomexpression.types.expression.ConsOrExpressionDisjunction) || ((( tom.engine.adt.tomexpression.types.Expression )orDisjunction) instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction)) ) {if (!( (  (( tom.engine.adt.tomexpression.types.Expression )orDisjunction).isEmptyOrExpressionDisjunction()  ||  ((( tom.engine.adt.tomexpression.types.Expression )orDisjunction)== tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction.make() )  ) )) { tom.engine.adt.tomexpression.types.Expression  tomMatch193_8=(( (((( tom.engine.adt.tomexpression.types.Expression )orDisjunction) instanceof tom.engine.adt.tomexpression.types.expression.ConsOrExpressionDisjunction) || ((( tom.engine.adt.tomexpression.types.Expression )orDisjunction) instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction)) )?( (( tom.engine.adt.tomexpression.types.Expression )orDisjunction).getHeadOrExpressionDisjunction() ):((( tom.engine.adt.tomexpression.types.Expression )orDisjunction)));if ( ((( tom.engine.adt.tomexpression.types.Expression )tomMatch193_8) instanceof tom.engine.adt.tomexpression.types.expression.And) ) { tom.engine.adt.tomexpression.types.Expression  tomMatch193_6= tomMatch193_8.getArg1() ;if ( ((( tom.engine.adt.tomexpression.types.Expression )tomMatch193_6) instanceof tom.engine.adt.tomexpression.types.expression.IsSort) ) {

        return
           tom.engine.adt.tominstruction.types.instruction.If.make(tomMatch193_6, buildDisjunctionIfElse( tom.engine.adt.tomexpression.types.expression.ConsOrExpressionDisjunction.make( tomMatch193_8.getArg2() ,tom_append_list_OrExpressionDisjunction((( (((( tom.engine.adt.tomexpression.types.Expression )orDisjunction) instanceof tom.engine.adt.tomexpression.types.expression.ConsOrExpressionDisjunction) || ((( tom.engine.adt.tomexpression.types.Expression )orDisjunction) instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction)) )?( (( tom.engine.adt.tomexpression.types.Expression )orDisjunction).getTailOrExpressionDisjunction() ):( tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction.make() )), tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction.make() )) ,assignFlagTrue),  tom.engine.adt.tominstruction.types.instruction.Nop.make() ) ;
      }}}}}}{ /* unamed block */if ( (orDisjunction instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( (((( tom.engine.adt.tomexpression.types.Expression )orDisjunction) instanceof tom.engine.adt.tomexpression.types.expression.ConsOrExpressionDisjunction) || ((( tom.engine.adt.tomexpression.types.Expression )orDisjunction) instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction)) ) {if (!( (  (( tom.engine.adt.tomexpression.types.Expression )orDisjunction).isEmptyOrExpressionDisjunction()  ||  ((( tom.engine.adt.tomexpression.types.Expression )orDisjunction)== tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction.make() )  ) )) { tom.engine.adt.tomexpression.types.Expression  tomMatch193_18=(( (((( tom.engine.adt.tomexpression.types.Expression )orDisjunction) instanceof tom.engine.adt.tomexpression.types.expression.ConsOrExpressionDisjunction) || ((( tom.engine.adt.tomexpression.types.Expression )orDisjunction) instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction)) )?( (( tom.engine.adt.tomexpression.types.Expression )orDisjunction).getHeadOrExpressionDisjunction() ):((( tom.engine.adt.tomexpression.types.Expression )orDisjunction)));if ( ((( tom.engine.adt.tomexpression.types.Expression )tomMatch193_18) instanceof tom.engine.adt.tomexpression.types.expression.And) ) {

        
        
        Instruction subtest = buildDisjunctionIfElse(tom_append_list_OrExpressionDisjunction((( (((( tom.engine.adt.tomexpression.types.Expression )orDisjunction) instanceof tom.engine.adt.tomexpression.types.expression.ConsOrExpressionDisjunction) || ((( tom.engine.adt.tomexpression.types.Expression )orDisjunction) instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction)) )?( (( tom.engine.adt.tomexpression.types.Expression )orDisjunction).getTailOrExpressionDisjunction() ):( tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction.make() )), tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction.make() ),assignFlagTrue);
        return  tom.engine.adt.tominstruction.types.instruction.If.make( tomMatch193_18.getArg1() ,  tom.engine.adt.tominstruction.types.instruction.UnamedBlock.make( tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(assignFlagTrue, tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(generateAutomata( tomMatch193_18.getArg2() , tom.engine.adt.tominstruction.types.instruction.Nop.make() ), tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) ) ) , subtest) ;
      }}}}}{ /* unamed block */if ( (orDisjunction instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( (((( tom.engine.adt.tomexpression.types.Expression )orDisjunction) instanceof tom.engine.adt.tomexpression.types.expression.ConsOrExpressionDisjunction) || ((( tom.engine.adt.tomexpression.types.Expression )orDisjunction) instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction)) ) {if (!( (  (( tom.engine.adt.tomexpression.types.Expression )orDisjunction).isEmptyOrExpressionDisjunction()  ||  ((( tom.engine.adt.tomexpression.types.Expression )orDisjunction)== tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction.make() )  ) )) {
        
        Instruction subtest = buildDisjunctionIfElse(tom_append_list_OrExpressionDisjunction((( (((( tom.engine.adt.tomexpression.types.Expression )orDisjunction) instanceof tom.engine.adt.tomexpression.types.expression.ConsOrExpressionDisjunction) || ((( tom.engine.adt.tomexpression.types.Expression )orDisjunction) instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction)) )?( (( tom.engine.adt.tomexpression.types.Expression )orDisjunction).getTailOrExpressionDisjunction() ):( tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction.make() )), tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction.make() ),assignFlagTrue);
        return  tom.engine.adt.tominstruction.types.instruction.If.make((( (((( tom.engine.adt.tomexpression.types.Expression )orDisjunction) instanceof tom.engine.adt.tomexpression.types.expression.ConsOrExpressionDisjunction) || ((( tom.engine.adt.tomexpression.types.Expression )orDisjunction) instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrExpressionDisjunction)) )?( (( tom.engine.adt.tomexpression.types.Expression )orDisjunction).getHeadOrExpressionDisjunction() ):((( tom.engine.adt.tomexpression.types.Expression )orDisjunction))), assignFlagTrue, subtest) ;
      }}}}}

    throw new TomRuntimeException("ConstraintGenerator.buildDisjunctionIfElse - strange expression:" + orDisjunction);
  }

  
  private Instruction buildAntiMatchInstruction(Expression expression, Instruction action)
      throws VisitFailure {
    BQTerm flag = getCompiler().getFreshVariable(getCompiler().getSymbolTable().getBooleanType());
    Instruction assignFlagTrue =  tom.engine.adt.tominstruction.types.instruction.Assign.make(flag,  tom.engine.adt.tomexpression.types.expression.TrueTL.make() ) ;
    Instruction automata = generateAutomata(expression, assignFlagTrue);    
    
    Instruction result =  tom.engine.adt.tominstruction.types.instruction.AbstractBlock.make( tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(automata, tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( tom.engine.adt.tominstruction.types.instruction.If.make( tom.engine.adt.tomexpression.types.expression.Negation.make( tom.engine.adt.tomexpression.types.expression.BQTermToExpression.make(flag) ) , action,  tom.engine.adt.tominstruction.types.instruction.Nop.make() ) , tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) ) ) 
;
    return  tom.engine.adt.tominstruction.types.instruction.LetRef.make(flag,  tom.engine.adt.tomexpression.types.expression.FalseTL.make() , result) ;
  }

  
  public static class CollectVar extends tom.library.sl.AbstractStrategyBasic {private  java.util.Collection<BQTerm>  varList;public CollectVar( java.util.Collection<BQTerm>  varList) {super(( new tom.library.sl.Identity() ));this.varList=varList;}public  java.util.Collection<BQTerm>  getvarList() {return varList;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChildren = new tom.library.sl.Visitable[getChildCount()];stratChildren[0] = super.getChildAt(0);return stratChildren;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {return ((T)visit_Constraint((( tom.engine.adt.tomconstraint.types.Constraint )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tomconstraint.types.Constraint  _visit_Constraint( tom.engine.adt.tomconstraint.types.Constraint  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.tomconstraint.types.Constraint )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tomconstraint.types.Constraint  visit_Constraint( tom.engine.adt.tomconstraint.types.Constraint  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{ /* unamed block */{ /* unamed block */if ( (tom__arg instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {if ( ((( tom.engine.adt.tomconstraint.types.Constraint )tom__arg) instanceof tom.engine.adt.tomconstraint.types.constraint.MatchConstraint) ) { tom.engine.adt.tomterm.types.TomTerm  tomMatch194_1= (( tom.engine.adt.tomconstraint.types.Constraint )tom__arg).getPattern() ;if ( ((( tom.engine.adt.tomterm.types.TomTerm )tomMatch194_1) instanceof tom.engine.adt.tomterm.types.tomterm.Variable) ) { tom.engine.adt.tomterm.types.TomTerm  tom___v=tomMatch194_1;


        if(!varList.contains(tom___v)) { /* unamed block */ varList.add(TomBase.convertFromVarToBQVar(tom___v)); }}}}}}return _visit_Constraint(tom__arg,introspector);}}public static class CollectFreeVar extends tom.library.sl.AbstractStrategyBasic {private  java.util.Collection<BQTerm>  varList;public CollectFreeVar( java.util.Collection<BQTerm>  varList) {super(( new tom.library.sl.Identity() ));this.varList=varList;}public  java.util.Collection<BQTerm>  getvarList() {return varList;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChildren = new tom.library.sl.Visitable[getChildCount()];stratChildren[0] = super.getChildAt(0);return stratChildren;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tomexpression.types.Expression) ) {return ((T)visit_Expression((( tom.engine.adt.tomexpression.types.Expression )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tomexpression.types.Expression  _visit_Expression( tom.engine.adt.tomexpression.types.Expression  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.tomexpression.types.Expression )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tomexpression.types.Expression  visit_Expression( tom.engine.adt.tomexpression.types.Expression  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{ /* unamed block */{ /* unamed block */if ( (tom__arg instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )tom__arg) instanceof tom.engine.adt.tomexpression.types.expression.ConstraintToExpression) ) { tom.engine.adt.tomconstraint.types.Constraint  tomMatch195_1= (( tom.engine.adt.tomexpression.types.Expression )tom__arg).getcons() ;if ( ((( tom.engine.adt.tomconstraint.types.Constraint )tomMatch195_1) instanceof tom.engine.adt.tomconstraint.types.constraint.MatchConstraint) ) { tom.engine.adt.tomterm.types.TomTerm  tomMatch195_4= tomMatch195_1.getPattern() ;if ( ((( tom.engine.adt.tomterm.types.TomTerm )tomMatch195_4) instanceof tom.engine.adt.tomterm.types.tomterm.Variable) ) { tom.engine.adt.tomterm.types.TomTerm  tom___v=tomMatch195_4;










        if(!varList.contains(tom___v)) { /* unamed block */ varList.add(TomBase.convertFromVarToBQVar(tom___v)); }     
        throw new VisitFailure();
      }}}}}{ /* unamed block */if ( (tom__arg instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )tom__arg) instanceof tom.engine.adt.tomexpression.types.expression.AntiMatchExpression) ) {
        
        throw new VisitFailure();
      }}}}return _visit_Expression(tom__arg,introspector);}}


  
   
  public Expression genIsEmptyList(TomName opName, BQTerm var) {
    TomSymbol tomSymbol = getCompiler().getSymbolTable().getSymbolFromName(opName.getString());
    TomType domain = TomBase.getSymbolDomain(tomSymbol).getHeadconcTomType();
    TomType codomain = TomBase.getSymbolCodomain(tomSymbol);
    if(domain==codomain) {
      
      return  tom.engine.adt.tomexpression.types.expression.Or.make( tom.engine.adt.tomexpression.types.expression.IsEmptyList.make(opName, var) ,  tom.engine.adt.tomexpression.types.expression.EqualBQTerm.make(codomain, var,  tom.engine.adt.code.types.bqterm.BuildEmptyList.make(opName) ) ) ;
    }
    return  tom.engine.adt.tomexpression.types.expression.IsEmptyList.make(opName, var) ;
  }
  
  private Instruction buildNumericCondition(Constraint c, Instruction action) {
    { /* unamed block */{ /* unamed block */if ( (c instanceof tom.engine.adt.tomconstraint.types.Constraint) ) {if ( ((( tom.engine.adt.tomconstraint.types.Constraint )c) instanceof tom.engine.adt.tomconstraint.types.constraint.NumericConstraint) ) { tom.engine.adt.code.types.BQTerm  tom___left= (( tom.engine.adt.tomconstraint.types.Constraint )c).getLeft() ; tom.engine.adt.code.types.BQTerm  tom___right= (( tom.engine.adt.tomconstraint.types.Constraint )c).getRight() ; tom.engine.adt.tomconstraint.types.NumericConstraintType  tom___type= (( tom.engine.adt.tomconstraint.types.Constraint )c).getType() ;
        
        Expression leftExpr =  tom.engine.adt.tomexpression.types.expression.BQTermToExpression.make(tom___left) ;
        Expression rightExpr =  tom.engine.adt.tomexpression.types.expression.BQTermToExpression.make(tom___right) ;
        { /* unamed block */{ /* unamed block */if ( (tom___type instanceof tom.engine.adt.tomconstraint.types.NumericConstraintType) ) {if ( ((( tom.engine.adt.tomconstraint.types.NumericConstraintType )tom___type) instanceof tom.engine.adt.tomconstraint.types.numericconstrainttype.NumLessThan) ) {
 return  tom.engine.adt.tominstruction.types.instruction.If.make( tom.engine.adt.tomexpression.types.expression.LessThan.make(leftExpr, rightExpr) , action,  tom.engine.adt.tominstruction.types.instruction.Nop.make() ) ;}}}{ /* unamed block */if ( (tom___type instanceof tom.engine.adt.tomconstraint.types.NumericConstraintType) ) {if ( ((( tom.engine.adt.tomconstraint.types.NumericConstraintType )tom___type) instanceof tom.engine.adt.tomconstraint.types.numericconstrainttype.NumLessOrEqualThan) ) {
 return  tom.engine.adt.tominstruction.types.instruction.If.make( tom.engine.adt.tomexpression.types.expression.LessOrEqualThan.make(leftExpr, rightExpr) , action,  tom.engine.adt.tominstruction.types.instruction.Nop.make() ) ;}}}{ /* unamed block */if ( (tom___type instanceof tom.engine.adt.tomconstraint.types.NumericConstraintType) ) {if ( ((( tom.engine.adt.tomconstraint.types.NumericConstraintType )tom___type) instanceof tom.engine.adt.tomconstraint.types.numericconstrainttype.NumGreaterThan) ) {
 return  tom.engine.adt.tominstruction.types.instruction.If.make( tom.engine.adt.tomexpression.types.expression.GreaterThan.make(leftExpr, rightExpr) , action,  tom.engine.adt.tominstruction.types.instruction.Nop.make() ) ;}}}{ /* unamed block */if ( (tom___type instanceof tom.engine.adt.tomconstraint.types.NumericConstraintType) ) {if ( ((( tom.engine.adt.tomconstraint.types.NumericConstraintType )tom___type) instanceof tom.engine.adt.tomconstraint.types.numericconstrainttype.NumGreaterOrEqualThan) ) {
 return  tom.engine.adt.tominstruction.types.instruction.If.make( tom.engine.adt.tomexpression.types.expression.GreaterOrEqualThan.make(leftExpr, rightExpr) , action,  tom.engine.adt.tominstruction.types.instruction.Nop.make() ) ;}}}{ /* unamed block */if ( (tom___type instanceof tom.engine.adt.tomconstraint.types.NumericConstraintType) ) {if ( ((( tom.engine.adt.tomconstraint.types.NumericConstraintType )tom___type) instanceof tom.engine.adt.tomconstraint.types.numericconstrainttype.NumEqual) ) {
 TomType tomType = getCompiler().getTermTypeFromTerm(tom___left);
                                         return  tom.engine.adt.tominstruction.types.instruction.If.make( tom.engine.adt.tomexpression.types.expression.EqualBQTerm.make(tomType, tom___right, tom___left) , action,  tom.engine.adt.tominstruction.types.instruction.Nop.make() ) ; }}}{ /* unamed block */if ( (tom___type instanceof tom.engine.adt.tomconstraint.types.NumericConstraintType) ) {if ( ((( tom.engine.adt.tomconstraint.types.NumericConstraintType )tom___type) instanceof tom.engine.adt.tomconstraint.types.numericconstrainttype.NumDifferent) ) {
 TomType tomType = getCompiler().getTermTypeFromTerm(tom___left);
                                         return  tom.engine.adt.tominstruction.types.instruction.If.make( tom.engine.adt.tomexpression.types.expression.Negation.make( tom.engine.adt.tomexpression.types.expression.EqualBQTerm.make(tomType, tom___right, tom___left) ) , action,  tom.engine.adt.tominstruction.types.instruction.Nop.make() ) ; }}}}}}}}



    
    throw new TomRuntimeException("Untreated numeric constraint: " + c);
  }


  
  private Instruction buildConstraintDisjunctionWithoutCopy(Expression orConnector, Instruction action) throws VisitFailure {    
    BQTerm flag = getCompiler().getFreshVariable(getCompiler().getSymbolTable().getBooleanType());
    Instruction assignFlagTrue =  tom.engine.adt.tominstruction.types.instruction.Assign.make(flag,  tom.engine.adt.tomexpression.types.expression.TrueTL.make() ) ;
    TomType intType = getCompiler().getSymbolTable().getIntType();
    BQTerm counter = getCompiler().getFreshVariable(intType);    
    
    Instruction instruction = buildTestsInConstraintDisjuction(0,assignFlagTrue,counter,intType,orConnector);    
    
    instruction =  tom.engine.adt.tominstruction.types.instruction.AbstractBlock.make( tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(instruction, tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( tom.engine.adt.tominstruction.types.instruction.If.make( tom.engine.adt.tomexpression.types.expression.EqualTerm.make(getCompiler().getSymbolTable().getBooleanType(), flag,  tom.engine.adt.tomterm.types.tomterm.TruePattern.make() ) , action,  tom.engine.adt.tominstruction.types.instruction.Nop.make() ) , tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) ) ) 
;
    
    Instruction counterIncrement =  tom.engine.adt.tominstruction.types.instruction.Assign.make(counter,  tom.engine.adt.tomexpression.types.expression.AddOne.make(counter) ) ;
    
    instruction =  tom.engine.adt.tominstruction.types.instruction.LetRef.make(flag,  tom.engine.adt.tomexpression.types.expression.FalseTL.make() ,  tom.engine.adt.tominstruction.types.instruction.AbstractBlock.make( tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(instruction, tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(counterIncrement, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) ) ) ) ;      
    instruction =  tom.engine.adt.tominstruction.types.instruction.DoWhile.make(instruction,  tom.engine.adt.tomexpression.types.expression.LessThan.make( tom.engine.adt.tomexpression.types.expression.BQTermToExpression.make(counter) ,  tom.engine.adt.tomexpression.types.expression.Integer.make(orConnector.length()) ) ) ;    
    
    Collection<BQTerm> freshVarList = new HashSet<BQTerm>();
    
    tom_make_TopDownCollect( new CollectFreeVar(freshVarList) ).visitLight(orConnector);
    for(BQTerm var:freshVarList) {
      instruction =  tom.engine.adt.tominstruction.types.instruction.LetRef.make(var,  tom.engine.adt.tomexpression.types.expression.Bottom.make(var.getAstType()) , instruction) ;
    }
    
    return  tom.engine.adt.tominstruction.types.instruction.LetRef.make(counter,  tom.engine.adt.tomexpression.types.expression.Integer.make(0) , instruction) ;
  }

  
  private Instruction buildTestsInConstraintDisjuction(int cnt, Instruction assignFlagTrue, 
      BQTerm counter, TomType intType, Expression orConnector) throws VisitFailure {
    { /* unamed block */{ /* unamed block */if ( (orConnector instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( (((( tom.engine.adt.tomexpression.types.Expression )orConnector) instanceof tom.engine.adt.tomexpression.types.expression.ConsOrConnector) || ((( tom.engine.adt.tomexpression.types.Expression )orConnector) instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrConnector)) ) {if (!( (  (( tom.engine.adt.tomexpression.types.Expression )orConnector).isEmptyOrConnector()  ||  ((( tom.engine.adt.tomexpression.types.Expression )orConnector)== tom.engine.adt.tomexpression.types.expression.EmptyOrConnector.make() )  ) )) {
        
        return  tom.engine.adt.tominstruction.types.instruction.If.make( tom.engine.adt.tomexpression.types.expression.And.make( tom.engine.adt.tomexpression.types.expression.GreaterOrEqualThan.make( tom.engine.adt.tomexpression.types.expression.BQTermToExpression.make(counter) ,  tom.engine.adt.tomexpression.types.expression.Integer.make(cnt) ) ,  tom.engine.adt.tomexpression.types.expression.LessOrEqualThan.make( tom.engine.adt.tomexpression.types.expression.BQTermToExpression.make(counter) ,  tom.engine.adt.tomexpression.types.expression.Integer.make(cnt) ) ) , generateAutomata((( (((( tom.engine.adt.tomexpression.types.Expression )orConnector) instanceof tom.engine.adt.tomexpression.types.expression.ConsOrConnector) || ((( tom.engine.adt.tomexpression.types.Expression )orConnector) instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrConnector)) )?( (( tom.engine.adt.tomexpression.types.Expression )orConnector).getHeadOrConnector() ):((( tom.engine.adt.tomexpression.types.Expression )orConnector))),assignFlagTrue), buildTestsInConstraintDisjuction(cnt,assignFlagTrue,counter,intType,tom_append_list_OrConnector((( (((( tom.engine.adt.tomexpression.types.Expression )orConnector) instanceof tom.engine.adt.tomexpression.types.expression.ConsOrConnector) || ((( tom.engine.adt.tomexpression.types.Expression )orConnector) instanceof tom.engine.adt.tomexpression.types.expression.EmptyOrConnector)) )?( (( tom.engine.adt.tomexpression.types.Expression )orConnector).getTailOrConnector() ):( tom.engine.adt.tomexpression.types.expression.EmptyOrConnector.make() )), tom.engine.adt.tomexpression.types.expression.EmptyOrConnector.make() ))) 


;        
      }}}}}

    return  tom.engine.adt.tominstruction.types.instruction.Nop.make() ;
  }

  
  private Instruction buildACMatchLoop(String symbolName, TomTerm var_x, TomTerm var_y, int mult_y, BQTerm subject, Instruction action) {
    

    SymbolTable symbolTable = getCompiler().getSymbolTable();
    TomType intType = symbolTable.getIntType();
    TomType intArrayType = symbolTable.getIntArrayType();
    
    
    
    TomName intArrayName =  tom.engine.adt.tomname.types.tomname.Name.make(symbolTable.getIntArrayOp()) ;

    BQTerm alpha = getCompiler().getFreshVariable("alpha",intArrayType);
    BQTerm tempSol = getCompiler().getFreshVariable("tempSol",intArrayType);
    BQTerm position = getCompiler().getFreshVariable("position",intType);
    BQTerm length = getCompiler().getFreshVariable("length",intType);                
    BQTerm multiplicity = getCompiler().getFreshVariable("multiplicity",intType);                

    BQTermList getTermArgs =  tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make(tempSol, tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make(alpha, tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make(subject, tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() ) ) ) ;        
    TomType subtermType = getCompiler().getTermTypeFromTerm(var_x);
    TomType booleanType = symbolTable.getBooleanType();
    
    Expression whileCond =  tom.engine.adt.tomexpression.types.expression.BQTermToExpression.make( tom.engine.adt.code.types.bqterm.FunctionCall.make( tom.engine.adt.tomname.types.tomname.Name.make("next_minimal_extract") , booleanType,  tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make(multiplicity, tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make(length, tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make(alpha, tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make(tempSol, tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() ) ) ) ) ) ) ;

    Instruction instruction =  tom.engine.adt.tominstruction.types.instruction.DoWhile.make( tom.engine.adt.tominstruction.types.instruction.LetRef.make(TomBase.convertFromVarToBQVar(var_x),  tom.engine.adt.tomexpression.types.expression.BQTermToExpression.make( tom.engine.adt.code.types.bqterm.FunctionCall.make( tom.engine.adt.tomname.types.tomname.Name.make(ConstraintGenerator.getTermForMultiplicityFuncName+"_"+symbolName) , subtermType, tom_append_list_concBQTerm(getTermArgs, tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make( tom.engine.adt.code.types.bqterm.ExpressionToBQTerm.make( tom.engine.adt.tomexpression.types.expression.FalseTL.make() ) , tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() ) )) ) ,  tom.engine.adt.tominstruction.types.instruction.LetRef.make(TomBase.convertFromVarToBQVar(var_y),  tom.engine.adt.tomexpression.types.expression.BQTermToExpression.make( tom.engine.adt.code.types.bqterm.FunctionCall.make( tom.engine.adt.tomname.types.tomname.Name.make(ConstraintGenerator.getTermForMultiplicityFuncName+"_"+symbolName) , subtermType, tom_append_list_concBQTerm(getTermArgs, tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make( tom.engine.adt.code.types.bqterm.ExpressionToBQTerm.make( tom.engine.adt.tomexpression.types.expression.TrueTL.make() ) , tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() ) )) ) , action) ) , whileCond) 






;

    instruction =  tom.engine.adt.tominstruction.types.instruction.LetRef.make(multiplicity,  tom.engine.adt.tomexpression.types.expression.Integer.make(mult_y) , instruction) ;
    instruction =  tom.engine.adt.tominstruction.types.instruction.LetRef.make(position,  tom.engine.adt.tomexpression.types.expression.SubstractOne.make(length) , instruction) ;
    instruction =  tom.engine.adt.tominstruction.types.instruction.LetRef.make(tempSol,  tom.engine.adt.tomexpression.types.expression.BQTermToExpression.make( tom.engine.adt.code.types.bqterm.BuildEmptyArray.make(intArrayName, length) ) , instruction) ;
    instruction =  tom.engine.adt.tominstruction.types.instruction.LetRef.make(length,  tom.engine.adt.tomexpression.types.expression.GetSize.make(intArrayName, alpha) , instruction) ;
    instruction =  tom.engine.adt.tominstruction.types.instruction.LetRef.make(alpha,  tom.engine.adt.tomexpression.types.expression.BQTermToExpression.make( tom.engine.adt.code.types.bqterm.FunctionCall.make( tom.engine.adt.tomname.types.tomname.Name.make(ConstraintGenerator.multiplicityFuncName+"_"+symbolName) , intArrayType,  tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make(subject, tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() ) ) ) , instruction) 

;

    return instruction;
  }



}
