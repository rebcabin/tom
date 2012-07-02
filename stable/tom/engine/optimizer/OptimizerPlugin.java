/*
 *
 * TOM - To One Matching Compiler
 *
 * Copyright (c) 2000-2012, INPL, INRIA
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

package tom.engine.optimizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import tom.engine.TomBase;

import tom.engine.adt.tomsignature.*;
import tom.engine.adt.tomconstraint.types.*;
import tom.engine.adt.tomdeclaration.types.*;
import tom.engine.adt.tomexpression.types.*;
import tom.engine.adt.tominstruction.types.*;
import tom.engine.adt.tomname.types.*;
import tom.engine.adt.tomoption.types.*;
import tom.engine.adt.tomoption.types.option.OriginTracking;
import tom.engine.adt.tomsignature.types.*;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomslot.types.*;
import tom.engine.adt.tomtype.types.*;
import tom.engine.adt.code.types.*;

import tom.engine.exception.TomRuntimeException;
import tom.engine.TomMessage;
import tom.engine.tools.TomGenericPlugin;
import tom.engine.tools.PILFactory;
import tom.engine.tools.Tools;
import tom.platform.OptionParser;
import tom.platform.adt.platformoption.types.PlatformOptionList;

import tom.library.sl.*;

/**
 * The TomOptimizer plugin.
 */
public class OptimizerPlugin extends TomGenericPlugin {

      private static   String  tom_append_list_concString( String l1,  String  l2) {     if( l1.length()==0 ) {       return l2;     } else if( l2.length()==0 ) {       return l1;     } else if(  l1.substring(1) .length()==0 ) {       return   l1.charAt(0) +l2 ;     } else {       return   l1.charAt(0) +tom_append_list_concString( l1.substring(1) ,l2) ;     }   }   private static   String  tom_get_slice_concString( String  begin,  String  end, String  tail) {     if( begin.equals(end) ) {       return tail;     } else if( end.equals(tail)  && ( end.length()==0  ||  end.equals( "" ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return   begin.charAt(0) +( String )tom_get_slice_concString( begin.substring(1) ,end,tail) ;   }        private static   tom.engine.adt.tominstruction.types.InstructionList  tom_append_list_concInstruction( tom.engine.adt.tominstruction.types.InstructionList l1,  tom.engine.adt.tominstruction.types.InstructionList  l2) {     if( l1.isEmptyconcInstruction() ) {       return l2;     } else if( l2.isEmptyconcInstruction() ) {       return l1;     } else if(  l1.getTailconcInstruction() .isEmptyconcInstruction() ) {       return  tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( l1.getHeadconcInstruction() ,l2) ;     } else {       return  tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( l1.getHeadconcInstruction() ,tom_append_list_concInstruction( l1.getTailconcInstruction() ,l2)) ;     }   }   private static   tom.engine.adt.tominstruction.types.InstructionList  tom_get_slice_concInstruction( tom.engine.adt.tominstruction.types.InstructionList  begin,  tom.engine.adt.tominstruction.types.InstructionList  end, tom.engine.adt.tominstruction.types.InstructionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcInstruction()  ||  (end== tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( begin.getHeadconcInstruction() ,( tom.engine.adt.tominstruction.types.InstructionList )tom_get_slice_concInstruction( begin.getTailconcInstruction() ,end,tail)) ;   }            private static   tom.library.sl.Strategy  tom_append_list_Sequence( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 == null )) {       return l2;     } else if(( l2 == null )) {       return l1;     } else if(( l1 instanceof tom.library.sl.Sequence )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ) == null )) {         return  tom.library.sl.Sequence.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ),l2) ;       } else {         return  tom.library.sl.Sequence.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ),tom_append_list_Sequence(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ),l2)) ;       }     } else {       return  tom.library.sl.Sequence.make(l1,l2) ;     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals( null )) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.sl.Sequence.make(((( begin instanceof tom.library.sl.Sequence ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(((( begin instanceof tom.library.sl.Sequence ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ): null ),end,tail)) ;   }      private static   tom.library.sl.Strategy  tom_append_list_Choice( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 ==null )) {       return l2;     } else if(( l2 ==null )) {       return l1;     } else if(( l1 instanceof tom.library.sl.Choice )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ) ==null )) {         return  tom.library.sl.Choice.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ),l2) ;       } else {         return  tom.library.sl.Choice.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ),tom_append_list_Choice(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ),l2)) ;       }     } else {       return  tom.library.sl.Choice.make(l1,l2) ;     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Choice( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end ==null ) ||  (end.equals( null )) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.sl.Choice.make(((( begin instanceof tom.library.sl.Choice ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Choice(((( begin instanceof tom.library.sl.Choice ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.THEN) ): null ),end,tail)) ;   }      private static   tom.library.sl.Strategy  tom_append_list_SequenceId( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 == null )) {       return l2;     } else if(( l2 == null )) {       return l1;     } else if(( l1 instanceof tom.library.sl.SequenceId )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.THEN) ) == null )) {         return  tom.library.sl.SequenceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.FIRST) ),l2) ;       } else {         return  tom.library.sl.SequenceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.FIRST) ),tom_append_list_SequenceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.THEN) ),l2)) ;       }     } else {       return  tom.library.sl.SequenceId.make(l1,l2) ;     }   }   private static   tom.library.sl.Strategy  tom_get_slice_SequenceId( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals( null )) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.sl.SequenceId.make(((( begin instanceof tom.library.sl.SequenceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.SequenceId.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_SequenceId(((( begin instanceof tom.library.sl.SequenceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.SequenceId.THEN) ): null ),end,tail)) ;   }      private static   tom.library.sl.Strategy  tom_append_list_ChoiceId( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 ==null )) {       return l2;     } else if(( l2 ==null )) {       return l1;     } else if(( l1 instanceof tom.library.sl.ChoiceId )) {       if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.THEN) ) ==null )) {         return  tom.library.sl.ChoiceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ),l2) ;       } else {         return  tom.library.sl.ChoiceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ),tom_append_list_ChoiceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.THEN) ),l2)) ;       }     } else {       return  tom.library.sl.ChoiceId.make(l1,l2) ;     }   }   private static   tom.library.sl.Strategy  tom_get_slice_ChoiceId( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else if( (end.equals(tail))  && (( end ==null ) ||  (end.equals( null )) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.sl.ChoiceId.make(((( begin instanceof tom.library.sl.ChoiceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_ChoiceId(((( begin instanceof tom.library.sl.ChoiceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.THEN) ): null ),end,tail)) ;   }      private static  tom.library.sl.Strategy  tom_make_AUCtl( tom.library.sl.Strategy  s1,  tom.library.sl.Strategy  s2) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("x") ), tom.library.sl.Choice.make(s2, tom.library.sl.Choice.make( tom.library.sl.Sequence.make( tom.library.sl.Sequence.make(s1, tom.library.sl.Sequence.make(( new tom.library.sl.All(( new tom.library.sl.MuVar("x") )) ), null ) ) , tom.library.sl.Sequence.make(( new tom.library.sl.One(( new tom.library.sl.Identity() )) ), null ) ) , null ) ) ) ) );}private static  tom.library.sl.Strategy  tom_make_EUCtl( tom.library.sl.Strategy  s1,  tom.library.sl.Strategy  s2) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("x") ), tom.library.sl.Choice.make(s2, tom.library.sl.Choice.make( tom.library.sl.Sequence.make(s1, tom.library.sl.Sequence.make(( new tom.library.sl.One(( new tom.library.sl.MuVar("x") )) ), null ) ) , null ) ) ) ));} private static  tom.library.sl.Strategy  tom_make_Try( tom.library.sl.Strategy  s) { return (  tom.library.sl.Choice.make(s, tom.library.sl.Choice.make(( new tom.library.sl.Identity() ), null ) )  );}private static  tom.library.sl.Strategy  tom_make_Repeat( tom.library.sl.Strategy  s) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.Choice.make( tom.library.sl.Sequence.make(s, tom.library.sl.Sequence.make(( new tom.library.sl.MuVar("_x") ), null ) ) , tom.library.sl.Choice.make(( new tom.library.sl.Identity() ), null ) ) ) ) );}private static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.Sequence.make(v, tom.library.sl.Sequence.make(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ), null ) ) ) ) );}private static  tom.library.sl.Strategy  tom_make_TopDownCollect( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ),tom_make_Try( tom.library.sl.Sequence.make(v, tom.library.sl.Sequence.make(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ), null ) ) )) ) );}private static  tom.library.sl.Strategy  tom_make_OnceTopDown( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.Choice.make(v, tom.library.sl.Choice.make(( new tom.library.sl.One(( new tom.library.sl.MuVar("_x") )) ), null ) ) ) ) );}private static  tom.library.sl.Strategy  tom_make_RepeatId( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.SequenceId.make(v, tom.library.sl.SequenceId.make(( new tom.library.sl.MuVar("_x") ), null ) ) ) ) );}private static  tom.library.sl.Strategy  tom_make_OnceTopDownId( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.ChoiceId.make(v, tom.library.sl.ChoiceId.make(( new tom.library.sl.OneId(( new tom.library.sl.MuVar("_x") )) ), null ) ) ) ) );}private static  tom.library.sl.Strategy  tom_make_InnermostId( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ), tom.library.sl.Sequence.make(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ), tom.library.sl.Sequence.make( tom.library.sl.SequenceId.make(v, tom.library.sl.SequenceId.make(( new tom.library.sl.MuVar("_x") ), null ) ) , null ) ) ) ) );}       @SuppressWarnings("unchecked") private static java.util.ArrayList concArrayListAppend(Object o, java.util.ArrayList l) {   java.util.ArrayList res = (java.util.ArrayList)l.clone();   res.add(o);   return res; } 












  /** some output suffixes */
  private static final String OPTIMIZED_SUFFIX = ".tfix.optimized";

  /** the declared options string*/
  private static final String DECLARED_OPTIONS =
    "<options>" +
    "<boolean name='optimize' altName='O' description='Optimize generated code: perform inlining' value='true'/>" +
    "<boolean name='optimize2' altName='O2' description='Optimize generated code: discrimination tree' value='false'/>" +
    "<boolean name='prettyPIL' altName='pil' description='PrettyPrint IL' value='false'/>" +
    "</options>";

  public PlatformOptionList getDeclaredOptionList() {
    return OptionParser.xmlToOptionList(OptimizerPlugin.DECLARED_OPTIONS);
  }

  public void optionChanged(String optionName, Object optionValue) {
    if(optionName.equals("optimize2") && ((Boolean)optionValue).booleanValue() ) {
      setOptionValue("pretty", Boolean.TRUE);
    }
  }

  // this static field is necessary for %strategy instructions that generate static code
  private static PILFactory factory = new PILFactory();
  private static Logger logger = Logger.getLogger("tom.engine.optimizer.OptimizerPlugin");

  /** Constructor */
  public OptimizerPlugin() {
    super("OptimizerPlugin");
  }

  public void run(Map informationTracker) {
    //System.out.println("(debug) I'm in the Tom optimizer : TSM"+getStreamManager().toString());
    if(getOptionBooleanValue("optimize") || getOptionBooleanValue("optimize2")) {
      /* Initialize strategies */
      long startChrono = System.currentTimeMillis();
      boolean intermediate = getOptionBooleanValue("intermediate");
      try {
       Code renamedTerm = (Code)getWorkingTerm();
        if(getOptionBooleanValue("optimize2")) {
          Strategy optStrategy2 =  tom.library.sl.Sequence.make(tom_make_InnermostId( tom.library.sl.ChoiceId.make(tom_make_NormExpr(this), tom.library.sl.ChoiceId.make(tom_make_NopElimAndFlatten(this), null ) ) ), tom.library.sl.Sequence.make(tom_make_InnermostId( tom.library.sl.ChoiceId.make( tom.library.sl.Sequence.make(( new tom.library.sl.BuiltinRepeatId(tom_make_IfSwapping(this)) ), tom.library.sl.Sequence.make(( new tom.library.sl.BuiltinRepeatId( tom.library.sl.SequenceId.make( tom.library.sl.ChoiceId.make(tom_make_BlockFusion(this), tom.library.sl.ChoiceId.make(tom_make_IfFusion(this), null ) ) , tom.library.sl.SequenceId.make(tom_make_OnceTopDownId(tom_make_NopElimAndFlatten(this)), null ) ) ) ), null ) ) , tom.library.sl.ChoiceId.make( tom.library.sl.SequenceId.make(tom_make_InterBlock(this), tom.library.sl.SequenceId.make(tom_make_OnceTopDownId(( new tom.library.sl.BuiltinRepeatId(tom_make_NopElimAndFlatten(this)) )), null ) ) , null ) ) 
                )
              , null ) ) 







;
          renamedTerm = optStrategy2.visitLight(renamedTerm);
          renamedTerm = ( new tom.library.sl.BuiltinBottomUp(tom_make_Inline( tom.engine.adt.tomconstraint.types.constraint.TrueConstraint.make() ,this)) ).visit(renamedTerm);
          renamedTerm = optStrategy2.visitLight(renamedTerm);
          //System.out.println("opt renamedTerm = " + renamedTerm);
        } else if(getOptionBooleanValue("optimize")) {
          Strategy optStrategy =  tom.library.sl.Sequence.make(tom_make_InnermostId( tom.library.sl.ChoiceId.make(tom_make_NormExpr(this), tom.library.sl.ChoiceId.make(tom_make_NopElimAndFlatten(this), null ) ) ), tom.library.sl.Sequence.make(( new tom.library.sl.BuiltinBottomUp(tom_make_Inline( tom.engine.adt.tomconstraint.types.constraint.TrueConstraint.make() ,this)) ), null ) ) 

;

          renamedTerm = optStrategy.visit(renamedTerm);
        }
        setWorkingTerm(renamedTerm);

        // verbose
        TomMessage.info(logger, getStreamManager().getInputFileName(), 0,
            TomMessage.tomOptimizationPhase,
            Integer.valueOf((int)(System.currentTimeMillis()-startChrono)));
      } catch (Exception e) {
        TomMessage.error(logger, getStreamManager().getInputFileName(), 0,
            TomMessage.exceptionMessage, e.getMessage());
        e.printStackTrace();
         return;
      }
      if(intermediate) {
        Tools.generateOutput(getStreamManager().getOutputFileName() + OPTIMIZED_SUFFIX,
            (Code)getWorkingTerm() );
      }
    } else {
      // not active plugin
      TomMessage.info(logger, getStreamManager().getInputFileName(), 0,
          TomMessage.optimizerNotActive);
    }
    if(getOptionBooleanValue("prettyPIL")) {
      System.out.println(factory.prettyPrintCompiledMatch(factory.remove((Code)getWorkingTerm())));
    }
  }

  private final static String PREFIX = "tom_";
  private static String extractRealName(String name) {
    if(name.startsWith(PREFIX)) {
      return name.substring(PREFIX.length());
    }
    return name;
  }

  public static class Inline extends tom.library.sl.AbstractStrategyBasic {private  tom.engine.adt.tomconstraint.types.Constraint  context;private  OptimizerPlugin  optimizer;public Inline( tom.engine.adt.tomconstraint.types.Constraint  context,  OptimizerPlugin  optimizer) {super(( new tom.library.sl.Identity() ));this.context=context;this.optimizer=optimizer;}public  tom.engine.adt.tomconstraint.types.Constraint  getcontext() {return context;}public  OptimizerPlugin  getoptimizer() {return optimizer;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tominstruction.types.Instruction) ) {return ((T)visit_Instruction((( tom.engine.adt.tominstruction.types.Instruction )v),introspector));}if ( (v instanceof tom.engine.adt.code.types.BQTerm) ) {return ((T)visit_BQTerm((( tom.engine.adt.code.types.BQTerm )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.code.types.BQTerm  _visit_BQTerm( tom.engine.adt.code.types.BQTerm  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.code.types.BQTerm )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  _visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.tominstruction.types.Instruction )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (((Object)tom__arg) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.Let) ) { tom.engine.adt.code.types.BQTerm  tomMatch252_3= (( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)).getVariable() ;boolean tomMatch252_48= false ; tom.engine.adt.tomname.types.TomName  tomMatch252_8= null ; tom.engine.adt.code.types.BQTerm  tomMatch252_11= null ; tom.engine.adt.code.types.BQTerm  tomMatch252_10= null ;if ( (tomMatch252_3 instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch252_3) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {{tomMatch252_48= true ;tomMatch252_10=tomMatch252_3;tomMatch252_8= tomMatch252_10.getAstName() ;}} else {if ( (tomMatch252_3 instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch252_3) instanceof tom.engine.adt.code.types.bqterm.BQVariableStar) ) {{tomMatch252_48= true ;tomMatch252_11=tomMatch252_3;tomMatch252_8= tomMatch252_11.getAstName() ;}}}}}if (tomMatch252_48) { tom.engine.adt.tomname.types.TomName  tom_name=tomMatch252_8; tom.engine.adt.tomexpression.types.Expression  tom_exp= (( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)).getSource() ; tom.engine.adt.tominstruction.types.Instruction  tom_body= (( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)).getAstInstruction() ;boolean tomMatch252_47= false ;if ( (tomMatch252_8 instanceof tom.engine.adt.tomname.types.TomName) ) {if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch252_8) instanceof tom.engine.adt.tomname.types.tomname.Name) ) { String  tomMatch252_27= tomMatch252_8.getString() ;if ( tomMatch252_27!= null && tomMatch252_27 instanceof String ) {if (!( tomMatch252_27.length()==0 )) { char  tomMatch252_37= tomMatch252_27.charAt(0) ;if ( true ) {if ( 't'==tomMatch252_37 ) { String  tomMatch252_31= tomMatch252_27.substring(1) ;if (!( tomMatch252_31.length()==0 )) { char  tomMatch252_39= tomMatch252_31.charAt(0) ;if ( true ) {if ( 'o'==tomMatch252_39 ) { String  tomMatch252_32= tomMatch252_31.substring(1) ;if (!( tomMatch252_32.length()==0 )) { char  tomMatch252_41= tomMatch252_32.charAt(0) ;if ( true ) {if ( 'm'==tomMatch252_41 ) { String  tomMatch252_33= tomMatch252_32.substring(1) ;if (!( tomMatch252_33.length()==0 )) { char  tomMatch252_43= tomMatch252_33.charAt(0) ;if ( true ) {if ( '_'==tomMatch252_43 ) {if ( (tom_name==tomMatch252_8) ) {tomMatch252_47= true ;}}}}}}}}}}}}}}}}if (!(tomMatch252_47)) {if ( (((Object)tom_exp) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )((Object)tom_exp)) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )(( tom.engine.adt.tomexpression.types.Expression )((Object)tom_exp))) instanceof tom.engine.adt.tomexpression.types.expression.BQTermToExpression) ) { tom.engine.adt.code.types.BQTerm  tomMatch252_12= (( tom.engine.adt.tomexpression.types.Expression )((Object)tom_exp)).getAstTerm() ;boolean tomMatch252_45= false ; tom.engine.adt.code.types.BQTerm  tomMatch252_17= null ; tom.engine.adt.code.types.BQTerm  tomMatch252_16= null ;if ( (tomMatch252_12 instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch252_12) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {{tomMatch252_45= true ;tomMatch252_16=tomMatch252_12;}} else {if ( (tomMatch252_12 instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch252_12) instanceof tom.engine.adt.code.types.bqterm.BQVariableStar) ) {{tomMatch252_45= true ;tomMatch252_17=tomMatch252_12;}}}}}if (tomMatch252_45) {















          return tom_make_TopDown(tom_make_replaceVariableByExpression(tom_name,tom_exp)).visitLight(tom_body);
      }}}}if ( (((Object)tom_exp) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )((Object)tom_exp)) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )(( tom.engine.adt.tomexpression.types.Expression )((Object)tom_exp))) instanceof tom.engine.adt.tomexpression.types.expression.Cast) ) { tom.engine.adt.tomexpression.types.Expression  tomMatch252_18= (( tom.engine.adt.tomexpression.types.Expression )((Object)tom_exp)).getSource() ;if ( (tomMatch252_18 instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )tomMatch252_18) instanceof tom.engine.adt.tomexpression.types.expression.BQTermToExpression) ) { tom.engine.adt.code.types.BQTerm  tomMatch252_21= tomMatch252_18.getAstTerm() ;boolean tomMatch252_46= false ; tom.engine.adt.code.types.BQTerm  tomMatch252_26= null ; tom.engine.adt.code.types.BQTerm  tomMatch252_25= null ;if ( (tomMatch252_21 instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch252_21) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {{tomMatch252_46= true ;tomMatch252_25=tomMatch252_21;}} else {if ( (tomMatch252_21 instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch252_21) instanceof tom.engine.adt.code.types.bqterm.BQVariableStar) ) {{tomMatch252_46= true ;tomMatch252_26=tomMatch252_21;}}}}}if (tomMatch252_46) {           return tom_make_TopDown(tom_make_replaceVariableByExpression(tom_name,tom_exp)).visitLight(tom_body);       }}}}}}}}}}}}{if ( (((Object)tom__arg) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.Let) ) { tom.engine.adt.code.types.BQTerm  tomMatch252_50= (( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)).getVariable() ;boolean tomMatch252_59= false ; tom.engine.adt.code.types.BQTerm  tomMatch252_57= null ; tom.engine.adt.code.types.BQTerm  tomMatch252_58= null ; tom.engine.adt.tomname.types.TomName  tomMatch252_55= null ;if ( (tomMatch252_50 instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch252_50) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {{tomMatch252_59= true ;tomMatch252_57=tomMatch252_50;tomMatch252_55= tomMatch252_57.getAstName() ;}} else {if ( (tomMatch252_50 instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch252_50) instanceof tom.engine.adt.code.types.bqterm.BQVariableStar) ) {{tomMatch252_59= true ;tomMatch252_58=tomMatch252_50;tomMatch252_55= tomMatch252_58.getAstName() ;}}}}}if (tomMatch252_59) { tom.engine.adt.tomname.types.TomName  tom_name=tomMatch252_55; tom.engine.adt.tomexpression.types.Expression  tom_exp= (( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)).getSource() ;


        String varName = ""; // real name of the variable (i.e. without the tom_ prefix)
        {{if ( (((Object)tom_name) instanceof tom.engine.adt.tomname.types.TomName) ) {if ( ((( tom.engine.adt.tomname.types.TomName )((Object)tom_name)) instanceof tom.engine.adt.tomname.types.TomName) ) {if ( ((( tom.engine.adt.tomname.types.TomName )(( tom.engine.adt.tomname.types.TomName )((Object)tom_name))) instanceof tom.engine.adt.tomname.types.tomname.Name) ) {
 varName = extractRealName( (( tom.engine.adt.tomname.types.TomName )((Object)tom_name)).getString() ); }}}}}


        //System.out.println("varName = " + varName);
        //System.out.println("body = " + `body);

        // count the occurence of name
        InfoVariable infoBody = new InfoVariable();
        infoBody.setAssignment(tom_exp,getPosition());
        getEnvironment().down(3); // 3 = body
        tom_make_computeOccurencesLet(tom_name,infoBody).visit(getEnvironment());
        getEnvironment().up();

        if(infoBody.modifiedAssignmentVariables) {
          /* do nothing */
          if(varName.length() > 0) {
            TomMessage.info(logger,null,0,TomMessage.cannotInline,0,varName);
          }
        } else {
          int mult = infoBody.readCount;
          //System.out.println(`name + " --> " + mult);
          if(mult == 0) { // name is not used
            // suppress the Let
            if(varName.length() > 0) {
              // varName is not empty when the variable is a user defined variable (with a name)
              // and not a variable generated by the compiler itself
              // TODO: check variable occurence in RawAction
              InfoVariable infoContext = new InfoVariable();
              tom_make_computeOccurencesLet(tom_name,infoContext).visit(context);
              if(infoContext.readCount<=1 && !varName.startsWith("_")) {
                // variables introduced by renaming starts with a '_'
                // verify linearity in case of variables from the pattern
                // warning to indicate that this var is unused in the rhs
                Option orgTrack = TomBase.findOriginTracking(tomMatch252_50.getOptions());
                TomMessage.warning(logger,(orgTrack instanceof OriginTracking)?orgTrack.getFileName():"unknown file", 
                  (orgTrack instanceof OriginTracking)?orgTrack.getLine():-1,
                  TomMessage.unusedVariable,varName);
                TomMessage.info(logger,null,0,TomMessage.remove,mult,varName);
              }
            }
            return  (( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)).getAstInstruction() ;
          } else if(mult == 1) {
            //test if variables contained in the exp to assign have not been
            //modified between the last assignment and the read
            if(varName.length() > 0) {
              TomMessage.info(logger,
                  optimizer.getStreamManager().getInputFileName(), 0,
                  TomMessage.inline, mult, varName);
            }
            Position current = getPosition();
            getEnvironment().goToPosition(infoBody.usePosition);
            getEnvironment().setSubject( tom.engine.adt.code.types.bqterm.ExpressionToBQTerm.make(tom_exp) );
            getEnvironment().goToPosition(current);
            Instruction newlet = (Instruction) getEnvironment().getSubject();
            // return only the body
            return (Instruction) newlet.getChildAt(2);
          } else {
            /* do nothing */
            if(varName.length() > 0) {
              TomMessage.info(logger,optimizer.getStreamManager().getInputFileName(),0,TomMessage.doNothing,mult,varName);
            }
          }
        }
      }}}}}{if ( (((Object)tom__arg) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.LetRef) ) { tom.engine.adt.code.types.BQTerm  tomMatch252_61= (( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)).getVariable() ;boolean tomMatch252_72= false ; tom.engine.adt.code.types.BQTerm  tomMatch252_69= null ; tom.engine.adt.code.types.BQTerm  tomMatch252_68= null ; tom.engine.adt.tomname.types.TomName  tomMatch252_66= null ;if ( (tomMatch252_61 instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch252_61) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {{tomMatch252_72= true ;tomMatch252_68=tomMatch252_61;tomMatch252_66= tomMatch252_68.getAstName() ;}} else {if ( (tomMatch252_61 instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch252_61) instanceof tom.engine.adt.code.types.bqterm.BQVariableStar) ) {{tomMatch252_72= true ;tomMatch252_69=tomMatch252_61;tomMatch252_66= tomMatch252_69.getAstName() ;}}}}}if (tomMatch252_72) {if ( (tomMatch252_66 instanceof tom.engine.adt.tomname.types.TomName) ) {if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch252_66) instanceof tom.engine.adt.tomname.types.tomname.Name) ) { tom.engine.adt.tomname.types.TomName  tom_name=tomMatch252_66;






        /*
         * do not optimize Variable(TomNumber...) because LetRef X*=GetTail(X*) in ...
         * is not correctly handled
         * we must check that X notin exp
         */
        String varName = "";
        {{if ( (((Object)tom_name) instanceof tom.engine.adt.tomname.types.TomName) ) {if ( ((( tom.engine.adt.tomname.types.TomName )((Object)tom_name)) instanceof tom.engine.adt.tomname.types.TomName) ) {if ( ((( tom.engine.adt.tomname.types.TomName )(( tom.engine.adt.tomname.types.TomName )((Object)tom_name))) instanceof tom.engine.adt.tomname.types.tomname.Name) ) {
 varName = extractRealName( (( tom.engine.adt.tomname.types.TomName )((Object)tom_name)).getString() ); }}}}}


        InfoVariable info = new InfoVariable();
        info.setAssignment( (( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)).getSource() ,getPosition());
        getEnvironment().down(3);
        //computeOccurencesLetRef on the body
        tom_make_computeOccurencesLetRef(tom_name,info).visit(getEnvironment());
        getEnvironment().up();
        int mult = info.readCount;
        Position readPos = info.usePosition;

        if(info.assignment==null) {
          if(varName.length() > 0) {
            TomMessage.info(logger,null,0,TomMessage.cannotInline,0,varName);
          }
        } else {
          //System.out.println(`name + " --> " + mult);
          // 0 -> unused variable
          // suppress the letref and all the corresponding letassigns in the body
          if(mult == 0) {
            // why this test?
            if(varName.length() > 0) {
              // TODO: check variable occurence in RawAction
              info = new InfoVariable();
              tom_make_computeOccurencesLetRef(tom_name,info).visit(context);
              if(info.readCount<=1 && !varName.startsWith("_")) {
                // verify linearity in case of variables from the pattern
                // warning to indicate that this var is unused in the rhs
                Option orgTrack = TomBase.findOriginTracking(tomMatch252_61.getOptions());
                TomMessage.warning(logger,orgTrack.getFileName(), orgTrack.getLine(),
                    TomMessage.unusedVariable,varName);
                TomMessage.info(logger,null,0,TomMessage.remove,mult,varName);
              }
            }
            return tom_make_CleanAssign(tom_name).visitLight( (( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)).getAstInstruction() );
          } else if(mult == 1) {
            //test if variables contained in the exp to assign have not been
            //modified between the last assignment and the use
            //test if the last assignment is not in a conditional sub-block
            //relatively to the variable use
            Position src = info.usePosition;
            Position dest = info.assignmentPosition;

            // find the positive part of src-dest
            Position positivePart = (Position) dest.sub(src).getCanonicalPath();
            while(positivePart.length()>0 && positivePart.getHead()<0) {
              positivePart = (Position) positivePart.getTail();
            }
            // find the common ancestor of src and dest
            Position commonAncestor = (Position) dest.add(positivePart.inverse()).getCanonicalPath();
            Position current = getPosition();
            getEnvironment().goToPosition(commonAncestor);
            try {
              //this strategy fails if from common ancestor and along the path
              //positivePart there is an instruction If, WhileDo or DoWhile
              positivePart.getOmegaPath(( new tom.library.sl.Not( tom.library.sl.Choice.make( new tom.engine.adt.tominstruction.strategy.instruction.Is_If(), tom.library.sl.Choice.make( new tom.engine.adt.tominstruction.strategy.instruction.Is_DoWhile(), tom.library.sl.Choice.make( new tom.engine.adt.tominstruction.strategy.instruction.Is_WhileDo(), null ) ) ) ) )).visit(getEnvironment());
              if(varName.length() > 0) {
                TomMessage.info(logger,
                    optimizer.getStreamManager().getInputFileName(), 0,
                    TomMessage.inline, mult, varName);
              }
              getEnvironment().goToPosition(readPos);
              BQTerm value =  tom.engine.adt.code.types.bqterm.ExpressionToBQTerm.make(info.assignment) ;
              getEnvironment().setSubject(value);
              getEnvironment().goToPosition(current);
              tom_make_CleanAssign(tom_name).visit(getEnvironment());
              Instruction newletref = (Instruction) getEnvironment().getSubject();
              // return only the body
              //System.out.println("inlineletref");
              return (Instruction) newletref.getChildAt(2);
            } catch(VisitFailure e) {
              getEnvironment().goToPosition(current);
              if(varName.length() > 0) {
                TomMessage.info(logger,
                    optimizer.getStreamManager().getInputFileName(), 0,
                    TomMessage.noInline, mult, varName);
              }
            }
          } else {
            /* do nothing: traversal() */
            if(varName.length() > 0) {
              TomMessage.info(logger,
                  optimizer.getStreamManager().getInputFileName(), 0,
                  TomMessage.doNothing, mult, varName);
            }
          }
        }
      }}}}}}}}return _visit_Instruction(tom__arg,introspector);}@SuppressWarnings("unchecked")public  tom.engine.adt.code.types.BQTerm  visit_BQTerm( tom.engine.adt.code.types.BQTerm  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (((Object)tom__arg) instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )((Object)tom__arg)) instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )(( tom.engine.adt.code.types.BQTerm )((Object)tom__arg))) instanceof tom.engine.adt.code.types.bqterm.BuildAppendList) ) { tom.engine.adt.code.types.BQTerm  tomMatch255_2= (( tom.engine.adt.code.types.BQTerm )((Object)tom__arg)).getHeadTerm() ; tom.engine.adt.tomname.types.TomName  tom_name= (( tom.engine.adt.code.types.BQTerm )((Object)tom__arg)).getAstName() ;if ( (tomMatch255_2 instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch255_2) instanceof tom.engine.adt.code.types.bqterm.ExpressionToBQTerm) ) { tom.engine.adt.tomexpression.types.Expression  tomMatch255_6= tomMatch255_2.getExp() ;if ( (tomMatch255_6 instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )tomMatch255_6) instanceof tom.engine.adt.tomexpression.types.expression.GetSliceList) ) {if ( (tom_name== tomMatch255_6.getAstName() ) ) {         return  tom.engine.adt.code.types.bqterm.ExpressionToBQTerm.make( tom.engine.adt.tomexpression.types.expression.GetSliceList.make(tom_name,  tomMatch255_6.getVariableBeginAST() ,  tomMatch255_6.getVariableEndAST() ,  tom.engine.adt.code.types.bqterm.BuildAppendList.make(tom_name,  tomMatch255_6.getTail() ,  (( tom.engine.adt.code.types.BQTerm )((Object)tom__arg)).getTailTerm() ) ) ) ;       }}}}}}}}}}return _visit_BQTerm(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_Inline( tom.engine.adt.tomconstraint.types.Constraint  t0,  OptimizerPlugin  t1) { return new Inline(t0,t1);}






  private static class InfoVariable {

    private HashSet<TomName> assignmentVariables = new HashSet<TomName>();
    public int readCount=0;
    public boolean modifiedAssignmentVariables=false; // true when exp depends on a variable which is modified by Assign
    public Position usePosition; // position where is used the variable
    /*
     * for LetRef
     */
    private Position assignmentPosition;
    private Expression assignment;

    public InfoVariable() {}

    public void setAssignment(Expression newAssignment,Position newAssignmentPosition) {
      assignment = newAssignment;
      assignmentPosition = newAssignmentPosition;
      assignmentVariables.clear();
      try {
        tom_make_TopDownCollect(tom_make_CollectVariable(assignmentVariables)).visitLight(newAssignment);
      } catch(VisitFailure e) {
        throw new TomRuntimeException("Error during collecting variables in "+newAssignment);
      }
    }

    public Set getAssignmentVariables() {
      return assignmentVariables;
    }

    public void clear() {
      assignment = null;
      assignmentPosition = null;
      assignmentVariables.clear();
    }

  }

  public static class CollectVariable extends tom.library.sl.AbstractStrategyBasic {private  java.util.HashSet<TomName>  set;public CollectVariable( java.util.HashSet<TomName>  set) {super(( new tom.library.sl.Identity() ));this.set=set;}public  java.util.HashSet<TomName>  getset() {return set;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.code.types.BQTerm) ) {return ((T)visit_BQTerm((( tom.engine.adt.code.types.BQTerm )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.code.types.BQTerm  _visit_BQTerm( tom.engine.adt.code.types.BQTerm  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.code.types.BQTerm )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.code.types.BQTerm  visit_BQTerm( tom.engine.adt.code.types.BQTerm  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (((Object)tom__arg) instanceof tom.engine.adt.code.types.BQTerm) ) {boolean tomMatch256_5= false ; tom.engine.adt.tomname.types.TomName  tomMatch256_1= null ; tom.engine.adt.code.types.BQTerm  tomMatch256_4= null ; tom.engine.adt.code.types.BQTerm  tomMatch256_3= null ;if ( ((( tom.engine.adt.code.types.BQTerm )((Object)tom__arg)) instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )(( tom.engine.adt.code.types.BQTerm )((Object)tom__arg))) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {{tomMatch256_5= true ;tomMatch256_3=(( tom.engine.adt.code.types.BQTerm )((Object)tom__arg));tomMatch256_1= tomMatch256_3.getAstName() ;}} else {if ( ((( tom.engine.adt.code.types.BQTerm )((Object)tom__arg)) instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )(( tom.engine.adt.code.types.BQTerm )((Object)tom__arg))) instanceof tom.engine.adt.code.types.bqterm.BQVariableStar) ) {{tomMatch256_5= true ;tomMatch256_4=(( tom.engine.adt.code.types.BQTerm )((Object)tom__arg));tomMatch256_1= tomMatch256_4.getAstName() ;}}}}}if (tomMatch256_5) {


        set.add(tomMatch256_1);
        //stop to visit this branch (like "return false" with traversal)
        throw new VisitFailure();
      }}}}return _visit_BQTerm(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_CollectVariable( java.util.HashSet<TomName>  t0) { return new CollectVariable(t0);}



  /* strategies for Let inlining (using cps) */
  // comp = AssignCase(RawActionCase(comp,BaseCase(all(comp),fail())),fail())
  private static  tom.library.sl.Strategy  tom_make_computeOccurencesLet( tom.engine.adt.tomname.types.TomName  variableName,  InfoVariable  info) { return tom_make_Try(( new tom.library.sl.Mu(( new tom.library.sl.MuVar("comp") ),tom_make_computeOccurenceLet_AssignCase(tom_make_computeOccurenceLet_RawActionCase(( new tom.library.sl.MuVar("comp") ),tom_make_computeOccurenceLet_BaseCase(( new tom.library.sl.All(( new tom.library.sl.MuVar("comp") )) ),variableName,info),variableName,info),info)
            ) )
          )













    ;}


  /*
   * check if a variable appearing in an expression (in Let x <- exp ...)
   * is modified (by applying Assign on the variables contained in exp)
   */
  public static class computeOccurenceLet_AssignCase extends tom.library.sl.AbstractStrategyBasic {private  tom.library.sl.Strategy  defaultCase;private  InfoVariable  info;public computeOccurenceLet_AssignCase( tom.library.sl.Strategy  defaultCase,  InfoVariable  info) {super(defaultCase);this.defaultCase=defaultCase;this.info=info;}public  tom.library.sl.Strategy  getdefaultCase() {return defaultCase;}public  InfoVariable  getinfo() {return info;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);stratChilds[1] = getdefaultCase();return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);defaultCase = ( tom.library.sl.Strategy ) children[1];return this;}public int getChildCount() {return 2;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);case 1: return getdefaultCase();default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);case 1: defaultCase = ( tom.library.sl.Strategy )child; return this;default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tominstruction.types.Instruction) ) {return ((T)visit_Instruction((( tom.engine.adt.tominstruction.types.Instruction )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  _visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.tominstruction.types.Instruction )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (((Object)tom__arg) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {boolean tomMatch257_8= false ; tom.engine.adt.code.types.BQTerm  tomMatch257_1= null ; tom.engine.adt.tominstruction.types.Instruction  tomMatch257_3= null ; tom.engine.adt.tominstruction.types.Instruction  tomMatch257_4= null ;if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.Assign) ) {{tomMatch257_8= true ;tomMatch257_3=(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg));tomMatch257_1= tomMatch257_3.getVariable() ;}} else {if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.AssignArray) ) {{tomMatch257_8= true ;tomMatch257_4=(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg));tomMatch257_1= tomMatch257_4.getVariable() ;}}}}}if (tomMatch257_8) {if ( (tomMatch257_1 instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch257_1) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {


        if(info.getAssignmentVariables().contains( tomMatch257_1.getAstName() )) {
          info.modifiedAssignmentVariables=true;
          throw new VisitFailure();
        }
      }}}}}}return _visit_Instruction(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_computeOccurenceLet_AssignCase( tom.library.sl.Strategy  t0,  InfoVariable  t1) { return new computeOccurenceLet_AssignCase(t0,t1);}public static class computeOccurenceLet_RawActionCase extends tom.library.sl.AbstractStrategyBasic {private  tom.library.sl.Strategy  goOnCase;private  tom.library.sl.Strategy  cutCase;private  tom.engine.adt.tomname.types.TomName  variableName;private  InfoVariable  info;public computeOccurenceLet_RawActionCase( tom.library.sl.Strategy  goOnCase,  tom.library.sl.Strategy  cutCase,  tom.engine.adt.tomname.types.TomName  variableName,  InfoVariable  info) {super(cutCase);this.goOnCase=goOnCase;this.cutCase=cutCase;this.variableName=variableName;this.info=info;}public  tom.library.sl.Strategy  getgoOnCase() {return goOnCase;}public  tom.library.sl.Strategy  getcutCase() {return cutCase;}public  tom.engine.adt.tomname.types.TomName  getvariableName() {return variableName;}public  InfoVariable  getinfo() {return info;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);stratChilds[1] = getgoOnCase();stratChilds[2] = getcutCase();return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);goOnCase = ( tom.library.sl.Strategy ) children[1];cutCase = ( tom.library.sl.Strategy ) children[2];return this;}public int getChildCount() {return 3;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);case 1: return getgoOnCase();case 2: return getcutCase();default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);case 1: goOnCase = ( tom.library.sl.Strategy )child; return this;case 2: cutCase = ( tom.library.sl.Strategy )child; return this;default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tominstruction.types.Instruction) ) {return ((T)visit_Instruction((( tom.engine.adt.tominstruction.types.Instruction )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  _visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.tominstruction.types.Instruction )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (((Object)tom__arg) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.RawAction) ) {







        /* recursive call of the current strategy on the first child */
        Environment current = getEnvironment();
        current.down(1);
        try {
          goOnCase.visit(current);
          current.up();
          return (Instruction) current.getSubject();
        } catch (VisitFailure e) {
          current.upLocal();
          throw new VisitFailure();
        }
      }}}}{if ( (((Object)tom__arg) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {boolean tomMatch258_11= false ; tom.engine.adt.tominstruction.types.Instruction  tomMatch258_6= null ; tom.engine.adt.tominstruction.types.Instruction  tomMatch258_7= null ; tom.engine.adt.code.types.BQTerm  tomMatch258_4= null ;if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.Assign) ) {{tomMatch258_11= true ;tomMatch258_6=(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg));tomMatch258_4= tomMatch258_6.getVariable() ;}} else {if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.AssignArray) ) {{tomMatch258_11= true ;tomMatch258_7=(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg));tomMatch258_4= tomMatch258_7.getVariable() ;}}}}}if (tomMatch258_11) {if ( (tomMatch258_4 instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch258_4) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {



        if(variableName.equals( tomMatch258_4.getAstName() )) {
          throw new TomRuntimeException("OptimizerPlugin: Assignment cannot be done for the variable "+variableName+" declared in a let");
        }
      }}}}}}return _visit_Instruction(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_computeOccurenceLet_RawActionCase( tom.library.sl.Strategy  t0,  tom.library.sl.Strategy  t1,  tom.engine.adt.tomname.types.TomName  t2,  InfoVariable  t3) { return new computeOccurenceLet_RawActionCase(t0,t1,t2,t3);}



  /*
   * when variableName is encountered, its position is stored
   * if it appears more than once, the computation is stopped because there is no possible inlining
   */
  public static class computeOccurenceLet_BaseCase extends tom.library.sl.AbstractStrategyBasic {private  tom.library.sl.Strategy  defaultCase;private  tom.engine.adt.tomname.types.TomName  variableName;private  InfoVariable  info;public computeOccurenceLet_BaseCase( tom.library.sl.Strategy  defaultCase,  tom.engine.adt.tomname.types.TomName  variableName,  InfoVariable  info) {super(defaultCase);this.defaultCase=defaultCase;this.variableName=variableName;this.info=info;}public  tom.library.sl.Strategy  getdefaultCase() {return defaultCase;}public  tom.engine.adt.tomname.types.TomName  getvariableName() {return variableName;}public  InfoVariable  getinfo() {return info;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);stratChilds[1] = getdefaultCase();return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);defaultCase = ( tom.library.sl.Strategy ) children[1];return this;}public int getChildCount() {return 2;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);case 1: return getdefaultCase();default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);case 1: defaultCase = ( tom.library.sl.Strategy )child; return this;default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.code.types.BQTerm) ) {return ((T)visit_BQTerm((( tom.engine.adt.code.types.BQTerm )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.code.types.BQTerm  _visit_BQTerm( tom.engine.adt.code.types.BQTerm  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.code.types.BQTerm )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.code.types.BQTerm  visit_BQTerm( tom.engine.adt.code.types.BQTerm  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (((Object)tom__arg) instanceof tom.engine.adt.code.types.BQTerm) ) {boolean tomMatch259_5= false ; tom.engine.adt.tomname.types.TomName  tomMatch259_1= null ; tom.engine.adt.code.types.BQTerm  tomMatch259_4= null ; tom.engine.adt.code.types.BQTerm  tomMatch259_3= null ;if ( ((( tom.engine.adt.code.types.BQTerm )((Object)tom__arg)) instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )(( tom.engine.adt.code.types.BQTerm )((Object)tom__arg))) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {{tomMatch259_5= true ;tomMatch259_3=(( tom.engine.adt.code.types.BQTerm )((Object)tom__arg));tomMatch259_1= tomMatch259_3.getAstName() ;}} else {if ( ((( tom.engine.adt.code.types.BQTerm )((Object)tom__arg)) instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )(( tom.engine.adt.code.types.BQTerm )((Object)tom__arg))) instanceof tom.engine.adt.code.types.bqterm.BQVariableStar) ) {{tomMatch259_5= true ;tomMatch259_4=(( tom.engine.adt.code.types.BQTerm )((Object)tom__arg));tomMatch259_1= tomMatch259_4.getAstName() ;}}}}}if (tomMatch259_5) {



        if(variableName == tomMatch259_1) {
          info.usePosition = getPosition();
          info.readCount++;
          if(info.readCount==2) { throw new VisitFailure(); }
        }
      }}}}return _visit_BQTerm(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_computeOccurenceLet_BaseCase( tom.library.sl.Strategy  t0,  tom.engine.adt.tomname.types.TomName  t1,  InfoVariable  t2) { return new computeOccurenceLet_BaseCase(t0,t1,t2);}public static class Print extends tom.library.sl.AbstractStrategyBasic {private  String  name;public Print( String  name) {super(( new tom.library.sl.Identity() ));this.name=name;}public  String  getname() {return name;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tominstruction.types.Instruction) ) {return ((T)visit_Instruction((( tom.engine.adt.tominstruction.types.Instruction )v),introspector));}if ( (v instanceof tom.engine.adt.tominstruction.types.InstructionList) ) {return ((T)visit_InstructionList((( tom.engine.adt.tominstruction.types.InstructionList )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.InstructionList  _visit_InstructionList( tom.engine.adt.tominstruction.types.InstructionList  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.tominstruction.types.InstructionList )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  _visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.tominstruction.types.Instruction )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.InstructionList  visit_InstructionList( tom.engine.adt.tominstruction.types.InstructionList  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (((Object)tom__arg) instanceof tom.engine.adt.tominstruction.types.InstructionList) ) {











        System.out.println(name + " instlist: " + (( tom.engine.adt.tominstruction.types.InstructionList )((Object)tom__arg)));
      }}}return _visit_InstructionList(tom__arg,introspector);}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (((Object)tom__arg) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {         System.out.println(name + " inst: " + (( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)));       }}}return _visit_Instruction(tom__arg,introspector);}}



  /* strategies for LetRef inlining (using cps) */
  // comp = special( comp, Basecase(comp,fail()) )
  private static  tom.library.sl.Strategy  tom_make_computeOccurencesLetRef( tom.engine.adt.tomname.types.TomName  variableName,  InfoVariable  info) { return 
 (
        tom_make_Try(( new tom.library.sl.Mu(( new tom.library.sl.MuVar("comp") ),tom_make_computeOccurencesLetRef_CutCase(( new tom.library.sl.MuVar("comp") ),tom_make_computeOccurencesLetRef_BaseCase(( new tom.library.sl.All(( new tom.library.sl.MuVar("comp") )) ),variableName,info),variableName,info)
            ) ))










        ) ;}


  //cases where failure is used to cut branches
  public static class computeOccurencesLetRef_CutCase extends tom.library.sl.AbstractStrategyBasic {private  tom.library.sl.Strategy  goOnCase;private  tom.library.sl.Strategy  defaultCase;private  tom.engine.adt.tomname.types.TomName  variableName;private  InfoVariable  info;public computeOccurencesLetRef_CutCase( tom.library.sl.Strategy  goOnCase,  tom.library.sl.Strategy  defaultCase,  tom.engine.adt.tomname.types.TomName  variableName,  InfoVariable  info) {super(defaultCase);this.goOnCase=goOnCase;this.defaultCase=defaultCase;this.variableName=variableName;this.info=info;}public  tom.library.sl.Strategy  getgoOnCase() {return goOnCase;}public  tom.library.sl.Strategy  getdefaultCase() {return defaultCase;}public  tom.engine.adt.tomname.types.TomName  getvariableName() {return variableName;}public  InfoVariable  getinfo() {return info;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);stratChilds[1] = getgoOnCase();stratChilds[2] = getdefaultCase();return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);goOnCase = ( tom.library.sl.Strategy ) children[1];defaultCase = ( tom.library.sl.Strategy ) children[2];return this;}public int getChildCount() {return 3;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);case 1: return getgoOnCase();case 2: return getdefaultCase();default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);case 1: goOnCase = ( tom.library.sl.Strategy )child; return this;case 2: defaultCase = ( tom.library.sl.Strategy )child; return this;default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tominstruction.types.Instruction) ) {return ((T)visit_Instruction((( tom.engine.adt.tominstruction.types.Instruction )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  _visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.tominstruction.types.Instruction )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (((Object)tom__arg) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.RawAction) ) {


        /* recursive call of the current strategy on the first child */
        Environment current = getEnvironment();
        current.down(1);
        try {
          goOnCase.visit(current);
          current.up();
          return (Instruction) current.getSubject();
        } catch (VisitFailure e) {
          current.upLocal();
          throw new VisitFailure();
        }
      }}}}{if ( (((Object)tom__arg) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {boolean tomMatch262_12= false ; tom.engine.adt.tominstruction.types.Instruction  tomMatch262_8= null ; tom.engine.adt.tominstruction.types.Instruction  tomMatch262_7= null ; tom.engine.adt.tomexpression.types.Expression  tomMatch262_5= null ; tom.engine.adt.code.types.BQTerm  tomMatch262_4= null ;if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.Assign) ) {{tomMatch262_12= true ;tomMatch262_7=(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg));tomMatch262_4= tomMatch262_7.getVariable() ;tomMatch262_5= tomMatch262_7.getSource() ;}} else {if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.AssignArray) ) {{tomMatch262_12= true ;tomMatch262_8=(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg));tomMatch262_4= tomMatch262_8.getVariable() ;tomMatch262_5= tomMatch262_8.getSource() ;}}}}}if (tomMatch262_12) {if ( (tomMatch262_4 instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch262_4) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) { tom.engine.adt.tomname.types.TomName  tom_name= tomMatch262_4.getAstName() ;


        if(variableName == tom_name) {
          info.setAssignment(tomMatch262_5,getPosition());
        } else {
          if(info.getAssignmentVariables().contains(tom_name)) {
            info.clear();
         }
        }
        /* recursive call of the current strategy on src */
        Environment current = getEnvironment();
        try {
          current.down(2);
          goOnCase.visit(current);
          current.up();
          return (Instruction) current.getSubject();
        } catch (VisitFailure e) {
          current.upLocal();
          throw new VisitFailure();
        }
      }}}}}}return _visit_Instruction(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_computeOccurencesLetRef_CutCase( tom.library.sl.Strategy  t0,  tom.library.sl.Strategy  t1,  tom.engine.adt.tomname.types.TomName  t2,  InfoVariable  t3) { return new computeOccurencesLetRef_CutCase(t0,t1,t2,t3);}public static class computeOccurencesLetRef_BaseCase extends tom.library.sl.AbstractStrategyBasic {private  tom.library.sl.Strategy  defaultCase;private  tom.engine.adt.tomname.types.TomName  variableName;private  InfoVariable  info;public computeOccurencesLetRef_BaseCase( tom.library.sl.Strategy  defaultCase,  tom.engine.adt.tomname.types.TomName  variableName,  InfoVariable  info) {super(defaultCase);this.defaultCase=defaultCase;this.variableName=variableName;this.info=info;}public  tom.library.sl.Strategy  getdefaultCase() {return defaultCase;}public  tom.engine.adt.tomname.types.TomName  getvariableName() {return variableName;}public  InfoVariable  getinfo() {return info;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);stratChilds[1] = getdefaultCase();return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);defaultCase = ( tom.library.sl.Strategy ) children[1];return this;}public int getChildCount() {return 2;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);case 1: return getdefaultCase();default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);case 1: defaultCase = ( tom.library.sl.Strategy )child; return this;default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.code.types.BQTerm) ) {return ((T)visit_BQTerm((( tom.engine.adt.code.types.BQTerm )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.code.types.BQTerm  _visit_BQTerm( tom.engine.adt.code.types.BQTerm  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.code.types.BQTerm )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.code.types.BQTerm  visit_BQTerm( tom.engine.adt.code.types.BQTerm  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (((Object)tom__arg) instanceof tom.engine.adt.code.types.BQTerm) ) {boolean tomMatch263_5= false ; tom.engine.adt.code.types.BQTerm  tomMatch263_4= null ; tom.engine.adt.code.types.BQTerm  tomMatch263_3= null ; tom.engine.adt.tomname.types.TomName  tomMatch263_1= null ;if ( ((( tom.engine.adt.code.types.BQTerm )((Object)tom__arg)) instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )(( tom.engine.adt.code.types.BQTerm )((Object)tom__arg))) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {{tomMatch263_5= true ;tomMatch263_3=(( tom.engine.adt.code.types.BQTerm )((Object)tom__arg));tomMatch263_1= tomMatch263_3.getAstName() ;}} else {if ( ((( tom.engine.adt.code.types.BQTerm )((Object)tom__arg)) instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )(( tom.engine.adt.code.types.BQTerm )((Object)tom__arg))) instanceof tom.engine.adt.code.types.bqterm.BQVariableStar) ) {{tomMatch263_5= true ;tomMatch263_4=(( tom.engine.adt.code.types.BQTerm )((Object)tom__arg));tomMatch263_1= tomMatch263_4.getAstName() ;}}}}}if (tomMatch263_5) {







        if(variableName == tomMatch263_1) {
          info.readCount++;
          info.usePosition = getPosition();
          if(info.readCount==2) {
            throw new VisitFailure();
          }
        }
      }}}}return _visit_BQTerm(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_computeOccurencesLetRef_BaseCase( tom.library.sl.Strategy  t0,  tom.engine.adt.tomname.types.TomName  t1,  InfoVariable  t2) { return new computeOccurencesLetRef_BaseCase(t0,t1,t2);}



  /*
   * rename variable1 into variable2
   */
  private static  tom.library.sl.Strategy  tom_make_renameVariable( tom.engine.adt.tomname.types.TomName  variable1,  tom.engine.adt.tomname.types.TomName  variable2) { return 
 (tom_make_TopDown(tom_make_renameVariableOnce(variable1,variable2)) ) ;}public static class renameVariableOnce extends tom.library.sl.AbstractStrategyBasic {private  tom.engine.adt.tomname.types.TomName  variable1;private  tom.engine.adt.tomname.types.TomName  variable2;public renameVariableOnce( tom.engine.adt.tomname.types.TomName  variable1,  tom.engine.adt.tomname.types.TomName  variable2) {super(( new tom.library.sl.Identity() ));this.variable1=variable1;this.variable2=variable2;}public  tom.engine.adt.tomname.types.TomName  getvariable1() {return variable1;}public  tom.engine.adt.tomname.types.TomName  getvariable2() {return variable2;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.code.types.BQTerm) ) {return ((T)visit_BQTerm((( tom.engine.adt.code.types.BQTerm )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.code.types.BQTerm  _visit_BQTerm( tom.engine.adt.code.types.BQTerm  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.code.types.BQTerm )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.code.types.BQTerm  visit_BQTerm( tom.engine.adt.code.types.BQTerm  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (((Object)tom__arg) instanceof tom.engine.adt.code.types.BQTerm) ) {boolean tomMatch264_5= false ; tom.engine.adt.code.types.BQTerm  tomMatch264_4= null ; tom.engine.adt.code.types.BQTerm  tomMatch264_3= null ; tom.engine.adt.tomname.types.TomName  tomMatch264_1= null ;if ( ((( tom.engine.adt.code.types.BQTerm )((Object)tom__arg)) instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )(( tom.engine.adt.code.types.BQTerm )((Object)tom__arg))) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {{tomMatch264_5= true ;tomMatch264_3=(( tom.engine.adt.code.types.BQTerm )((Object)tom__arg));tomMatch264_1= tomMatch264_3.getAstName() ;}} else {if ( ((( tom.engine.adt.code.types.BQTerm )((Object)tom__arg)) instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )(( tom.engine.adt.code.types.BQTerm )((Object)tom__arg))) instanceof tom.engine.adt.code.types.bqterm.BQVariableStar) ) {{tomMatch264_5= true ;tomMatch264_4=(( tom.engine.adt.code.types.BQTerm )((Object)tom__arg));tomMatch264_1= tomMatch264_4.getAstName() ;}}}}}if (tomMatch264_5) {





        if(variable1 == tomMatch264_1) {
          return (( tom.engine.adt.code.types.BQTerm )((Object)tom__arg)).setAstName(variable2);
        }
      }}}}return _visit_BQTerm(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_renameVariableOnce( tom.engine.adt.tomname.types.TomName  t0,  tom.engine.adt.tomname.types.TomName  t1) { return new renameVariableOnce(t0,t1);}public static class replaceVariableByExpression extends tom.library.sl.AbstractStrategyBasic {private  tom.engine.adt.tomname.types.TomName  variable;private  tom.engine.adt.tomexpression.types.Expression  exp;public replaceVariableByExpression( tom.engine.adt.tomname.types.TomName  variable,  tom.engine.adt.tomexpression.types.Expression  exp) {super(( new tom.library.sl.Identity() ));this.variable=variable;this.exp=exp;}public  tom.engine.adt.tomname.types.TomName  getvariable() {return variable;}public  tom.engine.adt.tomexpression.types.Expression  getexp() {return exp;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.code.types.BQTerm) ) {return ((T)visit_BQTerm((( tom.engine.adt.code.types.BQTerm )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.code.types.BQTerm  _visit_BQTerm( tom.engine.adt.code.types.BQTerm  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.code.types.BQTerm )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.code.types.BQTerm  visit_BQTerm( tom.engine.adt.code.types.BQTerm  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (((Object)tom__arg) instanceof tom.engine.adt.code.types.BQTerm) ) {boolean tomMatch265_5= false ; tom.engine.adt.code.types.BQTerm  tomMatch265_3= null ; tom.engine.adt.code.types.BQTerm  tomMatch265_4= null ; tom.engine.adt.tomname.types.TomName  tomMatch265_1= null ;if ( ((( tom.engine.adt.code.types.BQTerm )((Object)tom__arg)) instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )(( tom.engine.adt.code.types.BQTerm )((Object)tom__arg))) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {{tomMatch265_5= true ;tomMatch265_3=(( tom.engine.adt.code.types.BQTerm )((Object)tom__arg));tomMatch265_1= tomMatch265_3.getAstName() ;}} else {if ( ((( tom.engine.adt.code.types.BQTerm )((Object)tom__arg)) instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )(( tom.engine.adt.code.types.BQTerm )((Object)tom__arg))) instanceof tom.engine.adt.code.types.bqterm.BQVariableStar) ) {{tomMatch265_5= true ;tomMatch265_4=(( tom.engine.adt.code.types.BQTerm )((Object)tom__arg));tomMatch265_1= tomMatch265_4.getAstName() ;}}}}}if (tomMatch265_5) {






        if(variable == tomMatch265_1) {
          return  tom.engine.adt.code.types.bqterm.ExpressionToBQTerm.make(exp) ;
        }
      }}}}return _visit_BQTerm(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_replaceVariableByExpression( tom.engine.adt.tomname.types.TomName  t0,  tom.engine.adt.tomexpression.types.Expression  t1) { return new replaceVariableByExpression(t0,t1);}private static  tom.library.sl.Strategy  tom_make_CleanAssign( tom.engine.adt.tomname.types.TomName  varname) { return 




 (tom_make_TopDown(tom_make_CleanAssignOnce(varname))) ;}public static class CleanAssignOnce extends tom.library.sl.AbstractStrategyBasic {private  tom.engine.adt.tomname.types.TomName  varname;public CleanAssignOnce( tom.engine.adt.tomname.types.TomName  varname) {super(( new tom.library.sl.Identity() ));this.varname=varname;}public  tom.engine.adt.tomname.types.TomName  getvarname() {return varname;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tominstruction.types.Instruction) ) {return ((T)visit_Instruction((( tom.engine.adt.tominstruction.types.Instruction )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  _visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.tominstruction.types.Instruction )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (((Object)tom__arg) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {boolean tomMatch266_10= false ; tom.engine.adt.tominstruction.types.Instruction  tomMatch266_3= null ; tom.engine.adt.code.types.BQTerm  tomMatch266_1= null ; tom.engine.adt.tominstruction.types.Instruction  tomMatch266_4= null ;if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.Assign) ) {{tomMatch266_10= true ;tomMatch266_3=(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg));tomMatch266_1= tomMatch266_3.getVariable() ;}} else {if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.AssignArray) ) {{tomMatch266_10= true ;tomMatch266_4=(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg));tomMatch266_1= tomMatch266_4.getVariable() ;}}}}}if (tomMatch266_10) {boolean tomMatch266_9= false ; tom.engine.adt.tomname.types.TomName  tomMatch266_5= null ; tom.engine.adt.code.types.BQTerm  tomMatch266_7= null ; tom.engine.adt.code.types.BQTerm  tomMatch266_8= null ;if ( (tomMatch266_1 instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch266_1) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {{tomMatch266_9= true ;tomMatch266_7=tomMatch266_1;tomMatch266_5= tomMatch266_7.getAstName() ;}} else {if ( (tomMatch266_1 instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch266_1) instanceof tom.engine.adt.code.types.bqterm.BQVariableStar) ) {{tomMatch266_9= true ;tomMatch266_8=tomMatch266_1;tomMatch266_5= tomMatch266_8.getAstName() ;}}}}}if (tomMatch266_9) {





        if(tomMatch266_5.equals(varname)) { return  tom.engine.adt.tominstruction.types.instruction.Nop.make() ; }
      }}}}}return _visit_Instruction(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_CleanAssignOnce( tom.engine.adt.tomname.types.TomName  t0) { return new CleanAssignOnce(t0);}



  private static boolean compare(tom.library.sl.Visitable term1, tom.library.sl.Visitable term2) {
    return factory.remove(term1)==factory.remove(term2);
  }

  public static class NopElimAndFlatten extends tom.library.sl.AbstractStrategyBasic {private  OptimizerPlugin  optimizer;public NopElimAndFlatten( OptimizerPlugin  optimizer) {super(( new tom.library.sl.Identity() ));this.optimizer=optimizer;}public  OptimizerPlugin  getoptimizer() {return optimizer;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tominstruction.types.Instruction) ) {return ((T)visit_Instruction((( tom.engine.adt.tominstruction.types.Instruction )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  _visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.tominstruction.types.Instruction )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (((Object)tom__arg) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.AbstractBlock) ) { tom.engine.adt.tominstruction.types.InstructionList  tomMatch267_1= (( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)).getInstList() ;if ( (((( tom.engine.adt.tominstruction.types.InstructionList )tomMatch267_1) instanceof tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction) || ((( tom.engine.adt.tominstruction.types.InstructionList )tomMatch267_1) instanceof tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction)) ) { tom.engine.adt.tominstruction.types.InstructionList  tomMatch267__end__7=tomMatch267_1;do {{if (!( tomMatch267__end__7.isEmptyconcInstruction() )) { tom.engine.adt.tominstruction.types.Instruction  tomMatch267_11= tomMatch267__end__7.getHeadconcInstruction() ;if ( (tomMatch267_11 instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tomMatch267_11) instanceof tom.engine.adt.tominstruction.types.instruction.AbstractBlock) ) {



        TomMessage.info(logger,optimizer.getStreamManager().getInputFileName(),0,TomMessage.tomOptimizationType,"flatten");
        return  tom.engine.adt.tominstruction.types.instruction.AbstractBlock.make(tom_append_list_concInstruction(tom_get_slice_concInstruction(tomMatch267_1,tomMatch267__end__7, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ),tom_append_list_concInstruction( tomMatch267_11.getInstList() ,tom_append_list_concInstruction( tomMatch267__end__7.getTailconcInstruction() , tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() )))) ;
      }}}if ( tomMatch267__end__7.isEmptyconcInstruction() ) {tomMatch267__end__7=tomMatch267_1;} else {tomMatch267__end__7= tomMatch267__end__7.getTailconcInstruction() ;}}} while(!( (tomMatch267__end__7==tomMatch267_1) ));}}}}}{if ( (((Object)tom__arg) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.AbstractBlock) ) { tom.engine.adt.tominstruction.types.InstructionList  tomMatch267_14= (( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)).getInstList() ;if ( (((( tom.engine.adt.tominstruction.types.InstructionList )tomMatch267_14) instanceof tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction) || ((( tom.engine.adt.tominstruction.types.InstructionList )tomMatch267_14) instanceof tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction)) ) { tom.engine.adt.tominstruction.types.InstructionList  tomMatch267__end__20=tomMatch267_14;do {{if (!( tomMatch267__end__20.isEmptyconcInstruction() )) { tom.engine.adt.tominstruction.types.Instruction  tomMatch267_23= tomMatch267__end__20.getHeadconcInstruction() ;if ( (tomMatch267_23 instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tomMatch267_23) instanceof tom.engine.adt.tominstruction.types.instruction.Nop) ) {


        TomMessage.info(logger,optimizer.getStreamManager().getInputFileName(),0,TomMessage.tomOptimizationType,"nop-elim");
        return  tom.engine.adt.tominstruction.types.instruction.AbstractBlock.make(tom_append_list_concInstruction(tom_get_slice_concInstruction(tomMatch267_14,tomMatch267__end__20, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ),tom_append_list_concInstruction( tomMatch267__end__20.getTailconcInstruction() , tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ))) ;
      }}}if ( tomMatch267__end__20.isEmptyconcInstruction() ) {tomMatch267__end__20=tomMatch267_14;} else {tomMatch267__end__20= tomMatch267__end__20.getTailconcInstruction() ;}}} while(!( (tomMatch267__end__20==tomMatch267_14) ));}}}}}{if ( (((Object)tom__arg) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.AbstractBlock) ) { tom.engine.adt.tominstruction.types.InstructionList  tomMatch267_26= (( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)).getInstList() ;if ( (((( tom.engine.adt.tominstruction.types.InstructionList )tomMatch267_26) instanceof tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction) || ((( tom.engine.adt.tominstruction.types.InstructionList )tomMatch267_26) instanceof tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction)) ) {if ( tomMatch267_26.isEmptyconcInstruction() ) {


        TomMessage.info(logger,optimizer.getStreamManager().getInputFileName(),0,TomMessage.tomOptimizationType,"abstractblock-elim1");
        return  tom.engine.adt.tominstruction.types.instruction.Nop.make() ;
      }}}}}}{if ( (((Object)tom__arg) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.AbstractBlock) ) { tom.engine.adt.tominstruction.types.InstructionList  tomMatch267_31= (( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)).getInstList() ;if ( (((( tom.engine.adt.tominstruction.types.InstructionList )tomMatch267_31) instanceof tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction) || ((( tom.engine.adt.tominstruction.types.InstructionList )tomMatch267_31) instanceof tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction)) ) {if (!( tomMatch267_31.isEmptyconcInstruction() )) {if (  tomMatch267_31.getTailconcInstruction() .isEmptyconcInstruction() ) {


        TomMessage.info(logger,optimizer.getStreamManager().getInputFileName(),0,TomMessage.tomOptimizationType,"abstractblock-elim2");
        return  tomMatch267_31.getHeadconcInstruction() ;
      }}}}}}}{if ( (((Object)tom__arg) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.If) ) { tom.engine.adt.tominstruction.types.Instruction  tomMatch267_37= (( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)).getSuccesInst() ; tom.engine.adt.tominstruction.types.Instruction  tomMatch267_38= (( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)).getFailureInst() ;if ( (tomMatch267_37 instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tomMatch267_37) instanceof tom.engine.adt.tominstruction.types.instruction.Nop) ) {if ( (tomMatch267_38 instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tomMatch267_38) instanceof tom.engine.adt.tominstruction.types.instruction.Nop) ) {


        TomMessage.info(logger,optimizer.getStreamManager().getInputFileName(),0,TomMessage.tomOptimizationType,"ifnopnop-elim");
        return  tom.engine.adt.tominstruction.types.instruction.Nop.make() ;
      }}}}}}}}{if ( (((Object)tom__arg) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.If) ) { tom.engine.adt.tomexpression.types.Expression  tomMatch267_46= (( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)).getCondition() ;if ( (tomMatch267_46 instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )tomMatch267_46) instanceof tom.engine.adt.tomexpression.types.expression.TrueTL) ) {


        TomMessage.info(logger,optimizer.getStreamManager().getInputFileName(),0,TomMessage.tomOptimizationType,"iftrue-elim");
        return  (( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)).getSuccesInst() ;
      }}}}}}{if ( (((Object)tom__arg) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.If) ) { tom.engine.adt.tomexpression.types.Expression  tomMatch267_53= (( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)).getCondition() ;if ( (tomMatch267_53 instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )tomMatch267_53) instanceof tom.engine.adt.tomexpression.types.expression.FalseTL) ) {


        TomMessage.info(logger,optimizer.getStreamManager().getInputFileName(),0,TomMessage.tomOptimizationType,"iffalse-elim");
        return  (( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)).getFailureInst() ;
      }}}}}}}return _visit_Instruction(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_NopElimAndFlatten( OptimizerPlugin  t0) { return new NopElimAndFlatten(t0);}




  /*
   * two expressions are incompatible when they cannot be true a the same time
   */
  private boolean incompatible(Expression c1, Expression c2) {
    try {
      Expression res = tom_make_InnermostId(tom_make_NormExpr(this)).visitLight( tom.engine.adt.tomexpression.types.expression.And.make(c2, c2) );
      return res == tom.engine.adt.tomexpression.types.expression.FalseTL.make() ;
    } catch(VisitFailure e) {
      return false;
    }
  }

  public static class IfSwapping extends tom.library.sl.AbstractStrategyBasic {private  OptimizerPlugin  optimizer;public IfSwapping( OptimizerPlugin  optimizer) {super(( new tom.library.sl.Identity() ));this.optimizer=optimizer;}public  OptimizerPlugin  getoptimizer() {return optimizer;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tominstruction.types.Instruction) ) {return ((T)visit_Instruction((( tom.engine.adt.tominstruction.types.Instruction )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  _visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.tominstruction.types.Instruction )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (((Object)tom__arg) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.AbstractBlock) ) { tom.engine.adt.tominstruction.types.InstructionList  tomMatch268_1= (( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)).getInstList() ;if ( (((( tom.engine.adt.tominstruction.types.InstructionList )tomMatch268_1) instanceof tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction) || ((( tom.engine.adt.tominstruction.types.InstructionList )tomMatch268_1) instanceof tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction)) ) { tom.engine.adt.tominstruction.types.InstructionList  tomMatch268__end__7=tomMatch268_1;do {{if (!( tomMatch268__end__7.isEmptyconcInstruction() )) { tom.engine.adt.tominstruction.types.Instruction  tomMatch268_14= tomMatch268__end__7.getHeadconcInstruction() ;if ( (tomMatch268_14 instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tomMatch268_14) instanceof tom.engine.adt.tominstruction.types.instruction.If) ) { tom.engine.adt.tominstruction.types.Instruction  tomMatch268_13= tomMatch268_14.getFailureInst() ; tom.engine.adt.tomexpression.types.Expression  tom_cond1= tomMatch268_14.getCondition() ;if ( (tomMatch268_13 instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tomMatch268_13) instanceof tom.engine.adt.tominstruction.types.instruction.Nop) ) { tom.engine.adt.tominstruction.types.InstructionList  tomMatch268_8= tomMatch268__end__7.getTailconcInstruction() ;if (!( tomMatch268_8.isEmptyconcInstruction() )) { tom.engine.adt.tominstruction.types.Instruction  tomMatch268_21= tomMatch268_8.getHeadconcInstruction() ;if ( (tomMatch268_21 instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tomMatch268_21) instanceof tom.engine.adt.tominstruction.types.instruction.If) ) { tom.engine.adt.tominstruction.types.Instruction  tomMatch268_20= tomMatch268_21.getFailureInst() ; tom.engine.adt.tomexpression.types.Expression  tom_cond2= tomMatch268_21.getCondition() ;if ( (tomMatch268_20 instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tomMatch268_20) instanceof tom.engine.adt.tominstruction.types.instruction.Nop) ) {


        String s1 = factory.prettyPrint(factory.remove(tom_cond1));
        String s2 = factory.prettyPrint(factory.remove(tom_cond2));
        if(s1.compareTo(s2) < 0) {
          /* swap two incompatible conditions */
          if(optimizer.incompatible(tom_cond1,tom_cond2)) {
            TomMessage.info(logger,optimizer.getStreamManager().getInputFileName(),0,TomMessage.tomOptimizationType,"if-swapping");
            return  tom.engine.adt.tominstruction.types.instruction.AbstractBlock.make(tom_append_list_concInstruction(tom_get_slice_concInstruction(tomMatch268_1,tomMatch268__end__7, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ), tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( tomMatch268_8.getHeadconcInstruction() , tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( tomMatch268__end__7.getHeadconcInstruction() ,tom_append_list_concInstruction( tomMatch268_8.getTailconcInstruction() , tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() )) ) )) ;
          }
        }
      }}}}}}}}}}if ( tomMatch268__end__7.isEmptyconcInstruction() ) {tomMatch268__end__7=tomMatch268_1;} else {tomMatch268__end__7= tomMatch268__end__7.getTailconcInstruction() ;}}} while(!( (tomMatch268__end__7==tomMatch268_1) ));}}}}}}return _visit_Instruction(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_IfSwapping( OptimizerPlugin  t0) { return new IfSwapping(t0);}public static class BlockFusion extends tom.library.sl.AbstractStrategyBasic {private  OptimizerPlugin  optimizer;public BlockFusion( OptimizerPlugin  optimizer) {super(( new tom.library.sl.Identity() ));this.optimizer=optimizer;}public  OptimizerPlugin  getoptimizer() {return optimizer;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tominstruction.types.Instruction) ) {return ((T)visit_Instruction((( tom.engine.adt.tominstruction.types.Instruction )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  _visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.tominstruction.types.Instruction )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (((Object)tom__arg) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.AbstractBlock) ) { tom.engine.adt.tominstruction.types.InstructionList  tomMatch269_1= (( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)).getInstList() ;if ( (((( tom.engine.adt.tominstruction.types.InstructionList )tomMatch269_1) instanceof tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction) || ((( tom.engine.adt.tominstruction.types.InstructionList )tomMatch269_1) instanceof tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction)) ) { tom.engine.adt.tominstruction.types.InstructionList  tomMatch269__end__7=tomMatch269_1;do {{ tom.engine.adt.tominstruction.types.InstructionList  tom_X1=tom_get_slice_concInstruction(tomMatch269_1,tomMatch269__end__7, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() );if (!( tomMatch269__end__7.isEmptyconcInstruction() )) { tom.engine.adt.tominstruction.types.Instruction  tomMatch269_14= tomMatch269__end__7.getHeadconcInstruction() ;if ( (tomMatch269_14 instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tomMatch269_14) instanceof tom.engine.adt.tominstruction.types.instruction.Let) ) { tom.engine.adt.code.types.BQTerm  tomMatch269_11= tomMatch269_14.getVariable() ;boolean tomMatch269_30= false ; tom.engine.adt.code.types.BQTerm  tomMatch269_18= null ; tom.engine.adt.code.types.BQTerm  tomMatch269_19= null ; tom.engine.adt.tomname.types.TomName  tomMatch269_16= null ;if ( (tomMatch269_11 instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch269_11) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {{tomMatch269_30= true ;tomMatch269_18=tomMatch269_11;tomMatch269_16= tomMatch269_18.getAstName() ;}} else {if ( (tomMatch269_11 instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch269_11) instanceof tom.engine.adt.code.types.bqterm.BQVariableStar) ) {{tomMatch269_30= true ;tomMatch269_19=tomMatch269_11;tomMatch269_16= tomMatch269_19.getAstName() ;}}}}}if (tomMatch269_30) { tom.engine.adt.tomname.types.TomName  tom_name1=tomMatch269_16; tom.engine.adt.code.types.BQTerm  tom_var1=tomMatch269_11; tom.engine.adt.tomexpression.types.Expression  tom_term1= tomMatch269_14.getSource() ; tom.engine.adt.tominstruction.types.Instruction  tom_body1= tomMatch269_14.getAstInstruction() ; tom.engine.adt.tominstruction.types.InstructionList  tomMatch269_8= tomMatch269__end__7.getTailconcInstruction() ;if (!( tomMatch269_8.isEmptyconcInstruction() )) { tom.engine.adt.tominstruction.types.Instruction  tomMatch269_23= tomMatch269_8.getHeadconcInstruction() ;if ( (tomMatch269_23 instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tomMatch269_23) instanceof tom.engine.adt.tominstruction.types.instruction.Let) ) { tom.engine.adt.code.types.BQTerm  tomMatch269_20= tomMatch269_23.getVariable() ;boolean tomMatch269_29= false ; tom.engine.adt.tomname.types.TomName  tomMatch269_25= null ; tom.engine.adt.code.types.BQTerm  tomMatch269_27= null ; tom.engine.adt.code.types.BQTerm  tomMatch269_28= null ;if ( (tomMatch269_20 instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch269_20) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {{tomMatch269_29= true ;tomMatch269_27=tomMatch269_20;tomMatch269_25= tomMatch269_27.getAstName() ;}} else {if ( (tomMatch269_20 instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch269_20) instanceof tom.engine.adt.code.types.bqterm.BQVariableStar) ) {{tomMatch269_29= true ;tomMatch269_28=tomMatch269_20;tomMatch269_25= tomMatch269_28.getAstName() ;}}}}}if (tomMatch269_29) { tom.engine.adt.tominstruction.types.Instruction  tom_body2= tomMatch269_23.getAstInstruction() ; tom.engine.adt.tominstruction.types.InstructionList  tom_X2= tomMatch269_8.getTailconcInstruction() ; tom.engine.adt.tominstruction.types.Instruction  tom_block=(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg));









        /* Fusion de 2 blocs Let contigus instanciant deux variables egales */
        if(compare(tom_term1, tomMatch269_23.getSource() )) {
          if(compare(tom_var1,tomMatch269_20)) {
            TomMessage.info(logger,optimizer.getStreamManager().getInputFileName(),0,TomMessage.tomOptimizationType,"block-fusion1");
            return (tom_block.setInstList(tom_append_list_concInstruction(tom_X1, tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( tom.engine.adt.tominstruction.types.instruction.Let.make(tom_var1, tom_term1,  tom.engine.adt.tominstruction.types.instruction.AbstractBlock.make( tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(tom_body1, tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(tom_body2, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) ) ) ) ,tom_append_list_concInstruction(tom_X2, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() )) )));
          } else {
            InfoVariable info = new InfoVariable();
            tom_make_computeOccurencesLet(tom_name1,info).visit(tom_body2);
            int mult = info.readCount;
            if(mult==0) {
              TomMessage.info(logger,optimizer.getStreamManager().getInputFileName(),0,TomMessage.tomOptimizationType,"block-fusion2");
              Instruction newBody2 =  tom_make_renameVariable(tomMatch269_25,tom_name1).visitLight(tom_body2);
              return (tom_block.setInstList(tom_append_list_concInstruction(tom_X1, tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( tom.engine.adt.tominstruction.types.instruction.Let.make(tom_var1, tom_term1,  tom.engine.adt.tominstruction.types.instruction.AbstractBlock.make( tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(tom_body1, tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(newBody2, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) ) ) ) ,tom_append_list_concInstruction(tom_X2, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() )) )));
            }
          }
        }
      }}}}}}}}if ( tomMatch269__end__7.isEmptyconcInstruction() ) {tomMatch269__end__7=tomMatch269_1;} else {tomMatch269__end__7= tomMatch269__end__7.getTailconcInstruction() ;}}} while(!( (tomMatch269__end__7==tomMatch269_1) ));}}}}}}return _visit_Instruction(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_BlockFusion( OptimizerPlugin  t0) { return new BlockFusion(t0);}public static class IfFusion extends tom.library.sl.AbstractStrategyBasic {private  OptimizerPlugin  optimizer;public IfFusion( OptimizerPlugin  optimizer) {super(( new tom.library.sl.Identity() ));this.optimizer=optimizer;}public  OptimizerPlugin  getoptimizer() {return optimizer;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tominstruction.types.Instruction) ) {return ((T)visit_Instruction((( tom.engine.adt.tominstruction.types.Instruction )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  _visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.tominstruction.types.Instruction )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (((Object)tom__arg) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {boolean tomMatch270_22= false ; tom.engine.adt.tominstruction.types.Instruction  tomMatch270_4= null ; tom.engine.adt.tominstruction.types.InstructionList  tomMatch270_1= null ; tom.engine.adt.tominstruction.types.Instruction  tomMatch270_3= null ;if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.UnamedBlock) ) {{tomMatch270_22= true ;tomMatch270_3=(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg));tomMatch270_1= tomMatch270_3.getInstList() ;}} else {if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.AbstractBlock) ) {{tomMatch270_22= true ;tomMatch270_4=(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg));tomMatch270_1= tomMatch270_4.getInstList() ;}}}}}if (tomMatch270_22) {if ( (((( tom.engine.adt.tominstruction.types.InstructionList )tomMatch270_1) instanceof tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction) || ((( tom.engine.adt.tominstruction.types.InstructionList )tomMatch270_1) instanceof tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction)) ) { tom.engine.adt.tominstruction.types.InstructionList  tomMatch270__end__8=tomMatch270_1;do {{ tom.engine.adt.tominstruction.types.InstructionList  tom_X1=tom_get_slice_concInstruction(tomMatch270_1,tomMatch270__end__8, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() );if (!( tomMatch270__end__8.isEmptyconcInstruction() )) { tom.engine.adt.tominstruction.types.Instruction  tomMatch270_15= tomMatch270__end__8.getHeadconcInstruction() ;if ( (tomMatch270_15 instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tomMatch270_15) instanceof tom.engine.adt.tominstruction.types.instruction.If) ) { tom.engine.adt.tomexpression.types.Expression  tom_cond1= tomMatch270_15.getCondition() ; tom.engine.adt.tominstruction.types.Instruction  tom_success1= tomMatch270_15.getSuccesInst() ; tom.engine.adt.tominstruction.types.Instruction  tom_failure1= tomMatch270_15.getFailureInst() ; tom.engine.adt.tominstruction.types.InstructionList  tomMatch270_9= tomMatch270__end__8.getTailconcInstruction() ;if (!( tomMatch270_9.isEmptyconcInstruction() )) { tom.engine.adt.tominstruction.types.Instruction  tomMatch270_20= tomMatch270_9.getHeadconcInstruction() ;if ( (tomMatch270_20 instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tomMatch270_20) instanceof tom.engine.adt.tominstruction.types.instruction.If) ) { tom.engine.adt.tominstruction.types.Instruction  tom_success2= tomMatch270_20.getSuccesInst() ; tom.engine.adt.tominstruction.types.Instruction  tom_failure2= tomMatch270_20.getFailureInst() ; tom.engine.adt.tominstruction.types.InstructionList  tom_X2= tomMatch270_9.getTailconcInstruction() ; tom.engine.adt.tominstruction.types.Instruction  tom_block=(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg));









        Expression c1 = factory.remove(tom_cond1);
        Expression c2 = factory.remove( tomMatch270_20.getCondition() );
        //System.out.println("c1 = " + c1);
        //System.out.println("c2 = " + c2);
        {{if ( (((Object)c1) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( (((Object)c2) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )((Object)c1))==(( tom.engine.adt.tomexpression.types.Expression )((Object)c2))) ) {

            /* Merge 2 blocks whose conditions are equals */
            if(tom_failure1.isNop() && tom_failure2.isNop()) {
              TomMessage.info(logger,optimizer.getStreamManager().getInputFileName(),0,TomMessage.tomOptimizationType,"if-fusion1");
              Instruction res = (tom_block.setInstList(tom_append_list_concInstruction(tom_X1, tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( tom.engine.adt.tominstruction.types.instruction.If.make(tom_cond1,  tom.engine.adt.tominstruction.types.instruction.AbstractBlock.make( tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(tom_success1, tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(tom_success2, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) ) ) ,  tom.engine.adt.tominstruction.types.instruction.Nop.make() ) ,tom_append_list_concInstruction(tom_X2, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() )) )));
              //System.out.println(res);

              return res;
            } else {
              TomMessage.info(logger,optimizer.getStreamManager().getInputFileName(),0,TomMessage.tomOptimizationType,"if-fusion2");
              return (tom_block.setInstList(tom_append_list_concInstruction(tom_X1, tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( tom.engine.adt.tominstruction.types.instruction.If.make(tom_cond1,  tom.engine.adt.tominstruction.types.instruction.AbstractBlock.make( tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(tom_success1, tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(tom_success2, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) ) ) ,  tom.engine.adt.tominstruction.types.instruction.AbstractBlock.make( tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(tom_failure1, tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(tom_failure2, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) ) ) ) ,tom_append_list_concInstruction(tom_X2, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() )) )));
            }
          }}}}{if ( (((Object)c1) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )((Object)c1)) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )(( tom.engine.adt.tomexpression.types.Expression )((Object)c1))) instanceof tom.engine.adt.tomexpression.types.expression.Negation) ) {if ( (((Object)c2) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ( (( tom.engine.adt.tomexpression.types.Expression )((Object)c1)).getArg() ==(( tom.engine.adt.tomexpression.types.Expression )((Object)c2))) ) {


            /* Merge 2 blocks whose conditions are the negation of the other */
            TomMessage.info(logger,optimizer.getStreamManager().getInputFileName(),0,TomMessage.tomOptimizationType,"if-fusion-not");
            return (tom_block.setInstList(tom_append_list_concInstruction(tom_X1, tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( tom.engine.adt.tominstruction.types.instruction.If.make(tom_cond1,  tom.engine.adt.tominstruction.types.instruction.AbstractBlock.make( tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(tom_success1, tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(tom_failure2, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) ) ) ,  tom.engine.adt.tominstruction.types.instruction.AbstractBlock.make( tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(tom_failure1, tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(tom_success2, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) ) ) ) ,tom_append_list_concInstruction(tom_X2, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() )) )));
          }}}}}}}

      }}}}}}if ( tomMatch270__end__8.isEmptyconcInstruction() ) {tomMatch270__end__8=tomMatch270_1;} else {tomMatch270__end__8= tomMatch270__end__8.getTailconcInstruction() ;}}} while(!( (tomMatch270__end__8==tomMatch270_1) ));}}}}}return _visit_Instruction(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_IfFusion( OptimizerPlugin  t0) { return new IfFusion(t0);}public static class InterBlock extends tom.library.sl.AbstractStrategyBasic {private  OptimizerPlugin  optimizer;public InterBlock( OptimizerPlugin  optimizer) {super(( new tom.library.sl.Identity() ));this.optimizer=optimizer;}public  OptimizerPlugin  getoptimizer() {return optimizer;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tominstruction.types.Instruction) ) {return ((T)visit_Instruction((( tom.engine.adt.tominstruction.types.Instruction )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  _visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.tominstruction.types.Instruction )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tominstruction.types.Instruction  visit_Instruction( tom.engine.adt.tominstruction.types.Instruction  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (((Object)tom__arg) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)) instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )(( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg))) instanceof tom.engine.adt.tominstruction.types.instruction.AbstractBlock) ) { tom.engine.adt.tominstruction.types.InstructionList  tomMatch272_1= (( tom.engine.adt.tominstruction.types.Instruction )((Object)tom__arg)).getInstList() ;if ( (((( tom.engine.adt.tominstruction.types.InstructionList )tomMatch272_1) instanceof tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction) || ((( tom.engine.adt.tominstruction.types.InstructionList )tomMatch272_1) instanceof tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction)) ) { tom.engine.adt.tominstruction.types.InstructionList  tomMatch272__end__7=tomMatch272_1;do {{if (!( tomMatch272__end__7.isEmptyconcInstruction() )) { tom.engine.adt.tominstruction.types.Instruction  tomMatch272_14= tomMatch272__end__7.getHeadconcInstruction() ;if ( (tomMatch272_14 instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tomMatch272_14) instanceof tom.engine.adt.tominstruction.types.instruction.If) ) { tom.engine.adt.tomexpression.types.Expression  tom_cond1= tomMatch272_14.getCondition() ; tom.engine.adt.tominstruction.types.InstructionList  tomMatch272_8= tomMatch272__end__7.getTailconcInstruction() ;if (!( tomMatch272_8.isEmptyconcInstruction() )) { tom.engine.adt.tominstruction.types.Instruction  tomMatch272_19= tomMatch272_8.getHeadconcInstruction() ;if ( (tomMatch272_19 instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tomMatch272_19) instanceof tom.engine.adt.tominstruction.types.instruction.If) ) { tom.engine.adt.tominstruction.types.Instruction  tomMatch272_18= tomMatch272_19.getFailureInst() ; tom.engine.adt.tomexpression.types.Expression  tom_cond2= tomMatch272_19.getCondition() ;if ( (tomMatch272_18 instanceof tom.engine.adt.tominstruction.types.Instruction) ) {if ( ((( tom.engine.adt.tominstruction.types.Instruction )tomMatch272_18) instanceof tom.engine.adt.tominstruction.types.instruction.Nop) ) {










        if(optimizer.incompatible(tom_cond1,tom_cond2)) {
          TomMessage.info(logger,optimizer.getStreamManager().getInputFileName(),0,TomMessage.tomOptimizationType,"inter-block");
          return  tom.engine.adt.tominstruction.types.instruction.AbstractBlock.make(tom_append_list_concInstruction(tom_get_slice_concInstruction(tomMatch272_1,tomMatch272__end__7, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ), tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( tom.engine.adt.tominstruction.types.instruction.If.make(tom_cond1,  tomMatch272_14.getSuccesInst() ,  tom.engine.adt.tominstruction.types.instruction.AbstractBlock.make( tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( tomMatch272_14.getFailureInst() , tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( tom.engine.adt.tominstruction.types.instruction.If.make(tom_cond2,  tomMatch272_19.getSuccesInst() ,  tom.engine.adt.tominstruction.types.instruction.Nop.make() ) , tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) ) ) ) ,tom_append_list_concInstruction( tomMatch272_8.getTailconcInstruction() , tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() )) )) ;
        }
      }}}}}}}}if ( tomMatch272__end__7.isEmptyconcInstruction() ) {tomMatch272__end__7=tomMatch272_1;} else {tomMatch272__end__7= tomMatch272__end__7.getTailconcInstruction() ;}}} while(!( (tomMatch272__end__7==tomMatch272_1) ));}}}}}}return _visit_Instruction(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_InterBlock( OptimizerPlugin  t0) { return new InterBlock(t0);}public static class NormExpr extends tom.library.sl.AbstractStrategyBasic {private  OptimizerPlugin  optimizer;public NormExpr( OptimizerPlugin  optimizer) {super(( new tom.library.sl.Identity() ));this.optimizer=optimizer;}public  OptimizerPlugin  getoptimizer() {return optimizer;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() {return 1;}public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}@SuppressWarnings("unchecked")public <T> T visitLight(T v, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if ( (v instanceof tom.engine.adt.tomexpression.types.Expression) ) {return ((T)visit_Expression((( tom.engine.adt.tomexpression.types.Expression )v),introspector));}if (!(( null  == environment))) {return ((T)any.visit(environment,introspector));} else {return any.visitLight(v,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tomexpression.types.Expression  _visit_Expression( tom.engine.adt.tomexpression.types.Expression  arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {if (!(( null  == environment))) {return (( tom.engine.adt.tomexpression.types.Expression )any.visit(environment,introspector));} else {return any.visitLight(arg,introspector);}}@SuppressWarnings("unchecked")public  tom.engine.adt.tomexpression.types.Expression  visit_Expression( tom.engine.adt.tomexpression.types.Expression  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{{if ( (((Object)tom__arg) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )(( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg))) instanceof tom.engine.adt.tomexpression.types.expression.Or) ) { tom.engine.adt.tomexpression.types.Expression  tomMatch273_2= (( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)).getArg2() ;if ( (tomMatch273_2 instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )tomMatch273_2) instanceof tom.engine.adt.tomexpression.types.expression.TrueTL) ) {return  tom.engine.adt.tomexpression.types.expression.TrueTL.make() ;}}}}}}{if ( (((Object)tom__arg) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )(( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg))) instanceof tom.engine.adt.tomexpression.types.expression.Or) ) { tom.engine.adt.tomexpression.types.Expression  tomMatch273_8= (( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)).getArg1() ;if ( (tomMatch273_8 instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )tomMatch273_8) instanceof tom.engine.adt.tomexpression.types.expression.TrueTL) ) {return  tom.engine.adt.tomexpression.types.expression.TrueTL.make() ;}}}}}}{if ( (((Object)tom__arg) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )(( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg))) instanceof tom.engine.adt.tomexpression.types.expression.Or) ) { tom.engine.adt.tomexpression.types.Expression  tomMatch273_16= (( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)).getArg2() ;if ( (tomMatch273_16 instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )tomMatch273_16) instanceof tom.engine.adt.tomexpression.types.expression.FalseTL) ) {return  (( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)).getArg1() ;}}}}}}{if ( (((Object)tom__arg) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )(( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg))) instanceof tom.engine.adt.tomexpression.types.expression.Or) ) { tom.engine.adt.tomexpression.types.Expression  tomMatch273_22= (( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)).getArg1() ;if ( (tomMatch273_22 instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )tomMatch273_22) instanceof tom.engine.adt.tomexpression.types.expression.FalseTL) ) {return  (( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)).getArg2() ;}}}}}}{if ( (((Object)tom__arg) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )(( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg))) instanceof tom.engine.adt.tomexpression.types.expression.And) ) { tom.engine.adt.tomexpression.types.Expression  tomMatch273_29= (( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)).getArg1() ;if ( (tomMatch273_29 instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )tomMatch273_29) instanceof tom.engine.adt.tomexpression.types.expression.TrueTL) ) {return  (( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)).getArg2() ;}}}}}}{if ( (((Object)tom__arg) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )(( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg))) instanceof tom.engine.adt.tomexpression.types.expression.And) ) { tom.engine.adt.tomexpression.types.Expression  tomMatch273_37= (( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)).getArg2() ;if ( (tomMatch273_37 instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )tomMatch273_37) instanceof tom.engine.adt.tomexpression.types.expression.TrueTL) ) {return  (( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)).getArg1() ;}}}}}}{if ( (((Object)tom__arg) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )(( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg))) instanceof tom.engine.adt.tomexpression.types.expression.And) ) { tom.engine.adt.tomexpression.types.Expression  tomMatch273_43= (( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)).getArg1() ;if ( (tomMatch273_43 instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )tomMatch273_43) instanceof tom.engine.adt.tomexpression.types.expression.FalseTL) ) {return  tom.engine.adt.tomexpression.types.expression.FalseTL.make() ;}}}}}}{if ( (((Object)tom__arg) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )(( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg))) instanceof tom.engine.adt.tomexpression.types.expression.And) ) { tom.engine.adt.tomexpression.types.Expression  tomMatch273_50= (( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)).getArg1() ;if ( (tomMatch273_50 instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )tomMatch273_50) instanceof tom.engine.adt.tomexpression.types.expression.TrueTL) ) {return  tom.engine.adt.tomexpression.types.expression.FalseTL.make() ;}}}}}}{if ( (((Object)tom__arg) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )(( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg))) instanceof tom.engine.adt.tomexpression.types.expression.EqualTerm) ) {















        //System.out.println("kid1 = " + `kid1);
        //System.out.println("kid2 = " + `kid2);
        if(compare( (( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)).getKid1() , (( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)).getKid2() )) {
          return  tom.engine.adt.tomexpression.types.expression.TrueTL.make() ;
        } else {
          return (( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg));
        }
      }}}}{if ( (((Object)tom__arg) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)) instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )(( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg))) instanceof tom.engine.adt.tomexpression.types.expression.And) ) { tom.engine.adt.tomexpression.types.Expression  tomMatch273_63= (( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)).getArg1() ; tom.engine.adt.tomexpression.types.Expression  tomMatch273_64= (( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg)).getArg2() ;if ( (tomMatch273_63 instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )tomMatch273_63) instanceof tom.engine.adt.tomexpression.types.expression.IsFsym) ) { tom.engine.adt.tomname.types.TomName  tom_name1= tomMatch273_63.getAstName() ; tom.engine.adt.code.types.BQTerm  tom_term= tomMatch273_63.getVariable() ;if ( (tomMatch273_64 instanceof tom.engine.adt.tomexpression.types.Expression) ) {if ( ((( tom.engine.adt.tomexpression.types.Expression )tomMatch273_64) instanceof tom.engine.adt.tomexpression.types.expression.IsFsym) ) {if ( (tom_term== tomMatch273_64.getVariable() ) ) {


        if(tom_name1== tomMatch273_64.getAstName() ) {
          return  tom.engine.adt.tomexpression.types.expression.IsFsym.make(tom_name1, tom_term) ;
        }
        /*
         * may be true for list operator with domain=codomain
         * two if_sym(f)==is_fsym(g) may be true due to mapping
         */
        TomSymbol tomSymbol = optimizer.getSymbolTable().getSymbolFromName(tom_name1.getString());
        if(TomBase.isListOperator(tomSymbol) || TomBase.isArrayOperator(tomSymbol)) {
          TomType domain = TomBase.getSymbolDomain(tomSymbol).getHeadconcTomType();
          TomType codomain = TomBase.getSymbolCodomain(tomSymbol);
          if(domain!=codomain) {
            return  tom.engine.adt.tomexpression.types.expression.FalseTL.make() ;
          }
        } else {
          return  tom.engine.adt.tomexpression.types.expression.FalseTL.make() ;
        }
        return (( tom.engine.adt.tomexpression.types.Expression )((Object)tom__arg));
      }}}}}}}}}}return _visit_Expression(tom__arg,introspector);}}private static  tom.library.sl.Strategy  tom_make_NormExpr( OptimizerPlugin  t0) { return new NormExpr(t0);}



}
