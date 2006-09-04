/* Generated by TOM (version 2.4alpha): Do not edit this file *//*
 *   
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2006, INRIA
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
 * Julien Guyon
 *
 **/

package tom.engine.checker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import tom.engine.TomMessage;
import tom.platform.OptionParser;

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

import tom.platform.adt.platformoption.types.PlatformOptionList;
import aterm.ATerm;

import tom.library.strategy.mutraveler.MuTraveler;
import jjtraveler.reflective.VisitableVisitor;
import jjtraveler.VisitFailure;

/**
 * The TomTypeChecker plugin.
 */
public class TomTypeChecker extends TomChecker {

  /* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file */ private static boolean tom_terms_equal_String( String  t1,  String  t2) {  return  (t1.equals(t2))  ;}  /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ private static boolean tom_terms_equal_Instruction(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_InstructionList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomForwardType(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomType(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomVisit(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomRuleList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomVisitList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TargetLanguage(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_Declaration(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomName(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomNameList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_PatternInstructionList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomTerm(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TomList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_Option(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_OptionList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ConstraintList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_fun_sym_TypedEqualityCondition( tom.engine.adt.tominstruction.types.Instruction  t) {  return  (t!=null) && t.isTypedEqualityCondition()  ;}private static  tom.engine.adt.tomtype.types.TomType  tom_get_slot_TypedEqualityCondition_TomType( tom.engine.adt.tominstruction.types.Instruction  t) {  return  t.getTomType()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_TypedEqualityCondition_Lhs( tom.engine.adt.tominstruction.types.Instruction  t) {  return  t.getLhs()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_TypedEqualityCondition_Rhs( tom.engine.adt.tominstruction.types.Instruction  t) {  return  t.getRhs()  ;}private static boolean tom_is_fun_sym_MatchingCondition( tom.engine.adt.tominstruction.types.Instruction  t) {  return  (t!=null) && t.isMatchingCondition()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_MatchingCondition_Lhs( tom.engine.adt.tominstruction.types.Instruction  t) {  return  t.getLhs()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_MatchingCondition_Rhs( tom.engine.adt.tominstruction.types.Instruction  t) {  return  t.getRhs()  ;}private static boolean tom_is_fun_sym_Match( tom.engine.adt.tominstruction.types.Instruction  t) {  return  (t!=null) && t.isMatch()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_Match_SubjectList( tom.engine.adt.tominstruction.types.Instruction  t) {  return  t.getSubjectList()  ;}private static  tom.engine.adt.tomterm.types.PatternInstructionList  tom_get_slot_Match_AstPatternInstructionList( tom.engine.adt.tominstruction.types.Instruction  t) {  return  t.getAstPatternInstructionList()  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_Match_Option( tom.engine.adt.tominstruction.types.Instruction  t) {  return  t.getOption()  ;}private static  tom.engine.adt.tomtype.types.TomForwardType  tom_make_EmptyForward() { return  tom.engine.adt.tomtype.types.tomforwardtype.EmptyForward.make(); }private static boolean tom_is_fun_sym_TLType( tom.engine.adt.tomtype.types.TomType  t) {  return  (t!=null) && t.isTLType()  ;}private static  tom.engine.adt.tomsignature.types.TargetLanguage  tom_get_slot_TLType_Tl( tom.engine.adt.tomtype.types.TomType  t) {  return  t.getTl()  ;}private static boolean tom_is_fun_sym_ASTTomType( tom.engine.adt.tomtype.types.TomType  t) {  return  (t!=null) && t.isASTTomType()  ;}private static  String  tom_get_slot_ASTTomType_String( tom.engine.adt.tomtype.types.TomType  t) {  return  t.getString()  ;}private static boolean tom_is_fun_sym_TomTypeAlone( tom.engine.adt.tomtype.types.TomType  t) {  return  (t!=null) && t.isTomTypeAlone()  ;}private static  String  tom_get_slot_TomTypeAlone_String( tom.engine.adt.tomtype.types.TomType  t) {  return  t.getString()  ;}private static boolean tom_is_fun_sym_Type( tom.engine.adt.tomtype.types.TomType  t) {  return  (t!=null) && t.isType()  ;}private static  tom.engine.adt.tomtype.types.TomType  tom_get_slot_Type_TomType( tom.engine.adt.tomtype.types.TomType  t) {  return  t.getTomType()  ;}private static  tom.engine.adt.tomtype.types.TomType  tom_get_slot_Type_TlType( tom.engine.adt.tomtype.types.TomType  t) {  return  t.getTlType()  ;}private static boolean tom_is_fun_sym_VisitTerm( tom.engine.adt.tomsignature.types.TomVisit  t) {  return  (t!=null) && t.isVisitTerm()  ;}private static  tom.engine.adt.tomtype.types.TomType  tom_get_slot_VisitTerm_VNode( tom.engine.adt.tomsignature.types.TomVisit  t) {  return  t.getVNode()  ;}private static  tom.engine.adt.tomterm.types.PatternInstructionList  tom_get_slot_VisitTerm_AstPatternInstructionList( tom.engine.adt.tomsignature.types.TomVisit  t) {  return  t.getAstPatternInstructionList()  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_VisitTerm_Option( tom.engine.adt.tomsignature.types.TomVisit  t) {  return  t.getOption()  ;}private static boolean tom_is_fun_sym_RuleSet( tom.engine.adt.tomdeclaration.types.Declaration  t) {  return  (t!=null) && t.isRuleSet()  ;}private static  tom.engine.adt.tomsignature.types.TomRuleList  tom_get_slot_RuleSet_RuleList( tom.engine.adt.tomdeclaration.types.Declaration  t) {  return  t.getRuleList()  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_RuleSet_Option( tom.engine.adt.tomdeclaration.types.Declaration  t) {  return  t.getOption()  ;}private static boolean tom_is_fun_sym_Strategy( tom.engine.adt.tomdeclaration.types.Declaration  t) {  return  (t!=null) && t.isStrategy()  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_Strategy_SName( tom.engine.adt.tomdeclaration.types.Declaration  t) {  return  t.getSName()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_Strategy_ExtendsTerm( tom.engine.adt.tomdeclaration.types.Declaration  t) {  return  t.getExtendsTerm()  ;}private static  tom.engine.adt.tomsignature.types.TomVisitList  tom_get_slot_Strategy_VisitList( tom.engine.adt.tomdeclaration.types.Declaration  t) {  return  t.getVisitList()  ;}private static  tom.engine.adt.tomoption.types.Option  tom_get_slot_Strategy_OrgTrack( tom.engine.adt.tomdeclaration.types.Declaration  t) {  return  t.getOrgTrack()  ;}private static boolean tom_is_fun_sym_TermAppl( tom.engine.adt.tomterm.types.TomTerm  t) {  return  (t!=null) && t.isTermAppl()  ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_TermAppl_Option( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getOption()  ;}private static  tom.engine.adt.tomname.types.TomNameList  tom_get_slot_TermAppl_NameList( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getNameList()  ;}private static  tom.engine.adt.tomterm.types.TomList  tom_get_slot_TermAppl_Args( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getArgs()  ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slot_TermAppl_Constraints( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getConstraints()  ;}private static boolean tom_is_fun_sym_concInstruction( tom.engine.adt.tominstruction.types.InstructionList  t) {  return  t instanceof tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction || t instanceof tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction  ;}private static  tom.engine.adt.tominstruction.types.InstructionList  tom_empty_list_concInstruction() { return  tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ; }private static  tom.engine.adt.tominstruction.types.InstructionList  tom_cons_list_concInstruction( tom.engine.adt.tominstruction.types.Instruction  e,  tom.engine.adt.tominstruction.types.InstructionList  l) { return  tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(e,l) ; }private static  tom.engine.adt.tominstruction.types.Instruction  tom_get_head_concInstruction_InstructionList( tom.engine.adt.tominstruction.types.InstructionList  l) {  return  l.getHeadconcInstruction()  ;}private static  tom.engine.adt.tominstruction.types.InstructionList  tom_get_tail_concInstruction_InstructionList( tom.engine.adt.tominstruction.types.InstructionList  l) {  return  l.getTailconcInstruction()  ;}private static boolean tom_is_empty_concInstruction_InstructionList( tom.engine.adt.tominstruction.types.InstructionList  l) {  return  l.isEmptyconcInstruction()  ;}private static  tom.engine.adt.tominstruction.types.InstructionList  tom_append_list_concInstruction( tom.engine.adt.tominstruction.types.InstructionList  l1,  tom.engine.adt.tominstruction.types.InstructionList  l2) {    if(tom_is_empty_concInstruction_InstructionList(l1)) {     return l2;    } else if(tom_is_empty_concInstruction_InstructionList(l2)) {     return l1;    } else if(tom_is_empty_concInstruction_InstructionList(( tom.engine.adt.tominstruction.types.InstructionList )tom_get_tail_concInstruction_InstructionList(l1))) {     return ( tom.engine.adt.tominstruction.types.InstructionList )tom_cons_list_concInstruction(( tom.engine.adt.tominstruction.types.Instruction )tom_get_head_concInstruction_InstructionList(l1),l2);    } else {      return ( tom.engine.adt.tominstruction.types.InstructionList )tom_cons_list_concInstruction(( tom.engine.adt.tominstruction.types.Instruction )tom_get_head_concInstruction_InstructionList(l1),tom_append_list_concInstruction(( tom.engine.adt.tominstruction.types.InstructionList )tom_get_tail_concInstruction_InstructionList(l1),l2));    }   }  private static  tom.engine.adt.tominstruction.types.InstructionList  tom_get_slice_concInstruction( tom.engine.adt.tominstruction.types.InstructionList  begin,  tom.engine.adt.tominstruction.types.InstructionList  end) {    if(tom_terms_equal_InstructionList(begin,end)) {      return ( tom.engine.adt.tominstruction.types.InstructionList )tom_empty_list_concInstruction();    } else {      return ( tom.engine.adt.tominstruction.types.InstructionList )tom_cons_list_concInstruction(( tom.engine.adt.tominstruction.types.Instruction )tom_get_head_concInstruction_InstructionList(begin),( tom.engine.adt.tominstruction.types.InstructionList )tom_get_slice_concInstruction(( tom.engine.adt.tominstruction.types.InstructionList )tom_get_tail_concInstruction_InstructionList(begin),end));    }   }   /* Generated by TOM (version 2.4alpha): Do not edit this file */private static boolean tom_terms_equal_Strategy(Object t1, Object t2) {  return t1.equals(t2) ;}private static  jjtraveler.reflective.VisitableVisitor  tom_make_mu( jjtraveler.reflective.VisitableVisitor  var,  jjtraveler.reflective.VisitableVisitor  v) { return  new tom.library.strategy.mutraveler.Mu(var,v) ; }/* Generated by TOM (version 2.4alpha): Do not edit this file */private static  jjtraveler.reflective.VisitableVisitor  tom_make_Identity() { return  new tom.library.strategy.mutraveler.Identity() ; }private static  jjtraveler.reflective.VisitableVisitor  tom_make_Fail() { return  new tom.library.strategy.mutraveler.Fail() ; }private static  jjtraveler.reflective.VisitableVisitor  tom_make_Sequence( jjtraveler.reflective.VisitableVisitor  first,  jjtraveler.reflective.VisitableVisitor  then) { return  new tom.library.strategy.mutraveler.Sequence(first,then) ; }private static  jjtraveler.reflective.VisitableVisitor  tom_make_Choice( jjtraveler.reflective.VisitableVisitor  first,  jjtraveler.reflective.VisitableVisitor  then) { return  new tom.library.strategy.mutraveler.Choice(first,then) ; }private static  jjtraveler.reflective.VisitableVisitor  tom_make_All( jjtraveler.reflective.VisitableVisitor  v) { return  new tom.library.strategy.mutraveler.All(v) ; }private static  jjtraveler.reflective.VisitableVisitor  tom_make_MuVar( String  name) { return  new tom.library.strategy.mutraveler.MuVar(name) ; }private static  jjtraveler.reflective.VisitableVisitor  tom_make_Try( jjtraveler.reflective.VisitableVisitor  v) { return tom_make_Choice(v,tom_make_Identity()) ; }private static  jjtraveler.reflective.VisitableVisitor  tom_make_TopDown( jjtraveler.reflective.VisitableVisitor  v) { return tom_make_mu(tom_make_MuVar("x"),tom_make_Sequence(v,tom_make_All(tom_make_MuVar("x")))) ; }  


  /** the declared options string */
  public static final String DECLARED_OPTIONS = "<options><boolean name='noTypeCheck' altName='' description='Do not perform type checking' value='false'/></options>";

  /**
   * inherited from OptionOwner interface (plugin) 
   */
  public PlatformOptionList getDeclaredOptionList() {
    return OptionParser.xmlToOptionList(TomTypeChecker.DECLARED_OPTIONS);
  }

  /** Constructor */
  public TomTypeChecker() {
    super("TomTypeChecker");
  }
  
  public void run() {
    if(isActivated()) {
      strictType = !getOptionBooleanValue("lazyType");
      long startChrono = System.currentTimeMillis();
      try {
        // clean up internals
        reinit();
        // perform analyse
        try {
          MuTraveler.init(tom_make_mu(tom_make_MuVar("x"),tom_make_Try(tom_make_Sequence(tom_make_checkTypeInference(this),tom_make_All(tom_make_MuVar("x")))))).visit((TomTerm)getWorkingTerm());
        } catch(jjtraveler.VisitFailure e) {
          System.out.println("strategy failed");
        }
        // verbose
        getLogger().log( Level.INFO, TomMessage.tomTypeCheckingPhase.getMessage(),
                         new Integer((int)(System.currentTimeMillis()-startChrono)) );
      } catch (Exception e) {
        getLogger().log( Level.SEVERE, TomMessage.exceptionMessage.getMessage(),
                         new Object[]{getClass().getName(), getStreamManager().getInputFileName(),e.getMessage()} );
        e.printStackTrace();
      }
    } else {
      // type checker desactivated    
      getLogger().log(Level.INFO, TomMessage.typeCheckerInactivated.getMessage());
    }
  }
  
  private boolean isActivated() {
	  return !getOptionBooleanValue("noTypeCheck");
  }
  
  /**
   * Main type checking entry point:
   * We check all Match and RuleSet instructions
   */
   private static class checkTypeInference  extends  tom.engine.adt.tomsignature.TomSignatureBasicStrategy   { private  TomTypeChecker  ttc;  public checkTypeInference(  TomTypeChecker  ttc ) { super(tom_make_Identity() ); this.ttc=ttc; } public  TomTypeChecker  getttc() { return ttc;} public int getChildCount() { return 1; } public jjtraveler.Visitable getChildAt(int i) { switch (i) { case 0: return super.getChildAt(0); default: throw new IndexOutOfBoundsException(); }} public jjtraveler.Visitable setChildAt(int i, jjtraveler.Visitable child) { switch (i) { case 0: return super.setChildAt(0, child); default: throw new IndexOutOfBoundsException(); }} public  tom.engine.adt.tominstruction.types.Instruction  visit_Instruction(  tom.engine.adt.tominstruction.types.Instruction  tom__arg )  throws jjtraveler.VisitFailure { if(tom__arg instanceof  tom.engine.adt.tominstruction.types.Instruction ) { { tom.engine.adt.tominstruction.types.Instruction  tom_match1_1=(( tom.engine.adt.tominstruction.types.Instruction )tom__arg); if ( ( tom_is_fun_sym_Match(tom_match1_1) ||  false  ) ) { { tom.engine.adt.tomterm.types.TomTerm  tom_match1_1_SubjectList=tom_get_slot_Match_SubjectList(tom_match1_1); { tom.engine.adt.tomterm.types.PatternInstructionList  tom_match1_1_AstPatternInstructionList=tom_get_slot_Match_AstPatternInstructionList(tom_match1_1); { tom.engine.adt.tomoption.types.OptionList  tom_match1_1_Option=tom_get_slot_Match_Option(tom_match1_1); { tom.engine.adt.tomterm.types.PatternInstructionList  tom_patternInstructionList=tom_match1_1_AstPatternInstructionList; { tom.engine.adt.tomoption.types.OptionList  tom_oplist=tom_match1_1_Option; if ( true ) {



  
			  ttc.currentTomStructureOrgTrack = findOriginTracking(tom_oplist);
			  ttc.verifyMatchVariable(tom_patternInstructionList);
			  tom_make_Fail().visit(null);
		   } } } } } } } } } return super.visit_Instruction(tom__arg) ;  } public  tom.engine.adt.tomdeclaration.types.Declaration  visit_Declaration(  tom.engine.adt.tomdeclaration.types.Declaration  tom__arg )  throws jjtraveler.VisitFailure { if(tom__arg instanceof  tom.engine.adt.tomdeclaration.types.Declaration ) { { tom.engine.adt.tomdeclaration.types.Declaration  tom_match2_1=(( tom.engine.adt.tomdeclaration.types.Declaration )tom__arg); if ( ( tom_is_fun_sym_Strategy(tom_match2_1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomName  tom_match2_1_SName=tom_get_slot_Strategy_SName(tom_match2_1); { tom.engine.adt.tomterm.types.TomTerm  tom_match2_1_ExtendsTerm=tom_get_slot_Strategy_ExtendsTerm(tom_match2_1); { tom.engine.adt.tomsignature.types.TomVisitList  tom_match2_1_VisitList=tom_get_slot_Strategy_VisitList(tom_match2_1); { tom.engine.adt.tomoption.types.Option  tom_match2_1_OrgTrack=tom_get_slot_Strategy_OrgTrack(tom_match2_1); { tom.engine.adt.tomsignature.types.TomVisitList  tom_visitList=tom_match2_1_VisitList; { tom.engine.adt.tomoption.types.Option  tom_orgTrack=tom_match2_1_OrgTrack; if ( true ) {



			  ttc.currentTomStructureOrgTrack = tom_orgTrack;
			  ttc.verifyStrategyVariable(tom_visitList);
			  tom_make_Fail().visit(null);
       } } } } } } } } if ( ( tom_is_fun_sym_RuleSet(tom_match2_1) ||  false  ) ) { { tom.engine.adt.tomsignature.types.TomRuleList  tom_match2_1_RuleList=tom_get_slot_RuleSet_RuleList(tom_match2_1); { tom.engine.adt.tomoption.types.OptionList  tom_match2_1_Option=tom_get_slot_RuleSet_Option(tom_match2_1); { tom.engine.adt.tomsignature.types.TomRuleList  tom_list=tom_match2_1_RuleList; { tom.engine.adt.tomoption.types.OptionList  tom_optionList=tom_match2_1_Option; if ( true ) {

			  ttc.currentTomStructureOrgTrack = findOriginTracking(tom_optionList);
			  ttc.verifyRuleVariable(tom_list);
			  tom_make_Fail().visit(null);
		   } } } } } } } } return super.visit_Declaration(tom__arg) ;  } }private static  jjtraveler.reflective.VisitableVisitor  tom_make_checkTypeInference( TomTypeChecker  t0) { return new checkTypeInference(t0); }

 //checkTypeInference

  /* 
   * Collect unknown (not in symbol table) appls without ()
   */
   private static class collectUnknownAppls  extends  tom.engine.adt.tomsignature.TomSignatureBasicStrategy   { private  TomTypeChecker  ttc;  public collectUnknownAppls(  TomTypeChecker  ttc ) { super(tom_make_Identity() ); this.ttc=ttc; } public  TomTypeChecker  getttc() { return ttc;} public int getChildCount() { return 1; } public jjtraveler.Visitable getChildAt(int i) { switch (i) { case 0: return super.getChildAt(0); default: throw new IndexOutOfBoundsException(); }} public jjtraveler.Visitable setChildAt(int i, jjtraveler.Visitable child) { switch (i) { case 0: return super.setChildAt(0, child); default: throw new IndexOutOfBoundsException(); }} public  tom.engine.adt.tomterm.types.TomTerm  visit_TomTerm(  tom.engine.adt.tomterm.types.TomTerm  tom__arg )  throws jjtraveler.VisitFailure { if(tom__arg instanceof  tom.engine.adt.tomterm.types.TomTerm ) { { tom.engine.adt.tomterm.types.TomTerm  tom_match3_1=(( tom.engine.adt.tomterm.types.TomTerm )tom__arg); if ( ( tom_is_fun_sym_TermAppl(tom_match3_1) ||  false  ) ) { { tom.engine.adt.tomterm.types.TomTerm  tom_app=tom_match3_1; if ( true ) {


				if(ttc.symbolTable().getSymbolFromName(ttc.getName(tom_app))==null) {
					ttc.messageError(findOriginTrackingFileName(tom_app.getOption()),
							findOriginTrackingLine(tom_app.getOption()),
							TomMessage.unknownVariableInWhen,
							new Object[]{ttc.getName(tom_app)});
				}
				// else, it's actually app()
				// else, it's a unknown (ie : java) function
			 } } } } } return super.visit_TomTerm(tom__arg) ;  } }private static  jjtraveler.reflective.VisitableVisitor  tom_make_collectUnknownAppls( TomTypeChecker  t0) { return new collectUnknownAppls(t0); }


  
  private void verifyMatchVariable(PatternInstructionList patternInstructionList) {
    while(!patternInstructionList.isEmptyconcPatternInstruction()) {
      PatternInstruction pa = patternInstructionList.getHeadconcPatternInstruction();
      Pattern pattern = pa.getPattern();
        // collect variables
      ArrayList variableList = new ArrayList();
      collectVariable(variableList, pattern);
      verifyVariableTypeListCoherence(variableList);
      // verify variables in WHEN instruction
      // collect unknown variables
      try {
	      MuTraveler.init(tom_make_TopDown(tom_make_collectUnknownAppls(this))).visit(pattern.getGuards());
      } catch(jjtraveler.VisitFailure e) {
	      System.out.println("strategy failed");
      }

      patternInstructionList = patternInstructionList.getTailconcPatternInstruction();
    }
  } //verifyMatchVariable
  
  private void verifyStrategyVariable(TomVisitList list) {
    TomForwardType visitorFwd = null;
    TomForwardType currentVisitorFwd = null;
    while(!list.isEmptyconcTomVisit()) {
      TomVisit visit = list.getHeadconcTomVisit();
       if(visit instanceof  tom.engine.adt.tomsignature.types.TomVisit ) { { tom.engine.adt.tomsignature.types.TomVisit  tom_match4_1=(( tom.engine.adt.tomsignature.types.TomVisit )visit); if ( ( tom_is_fun_sym_VisitTerm(tom_match4_1) ||  false  ) ) { { tom.engine.adt.tomtype.types.TomType  tom_match4_1_VNode=tom_get_slot_VisitTerm_VNode(tom_match4_1); { tom.engine.adt.tomterm.types.PatternInstructionList  tom_match4_1_AstPatternInstructionList=tom_get_slot_VisitTerm_AstPatternInstructionList(tom_match4_1); { tom.engine.adt.tomoption.types.OptionList  tom_match4_1_Option=tom_get_slot_VisitTerm_Option(tom_match4_1); { tom.engine.adt.tomtype.types.TomType  tom_visitType=tom_match4_1_VNode; { tom.engine.adt.tomterm.types.PatternInstructionList  tom_patternInstructionList=tom_match4_1_AstPatternInstructionList; { tom.engine.adt.tomoption.types.OptionList  tom_options=tom_match4_1_Option; if ( true ) {

          String fileName =findOriginTrackingFileName(tom_options);
           if(tom_visitType instanceof  tom.engine.adt.tomtype.types.TomType ) { { tom.engine.adt.tomtype.types.TomType  tom_match5_1=(( tom.engine.adt.tomtype.types.TomType )tom_visitType); if ( ( tom_is_fun_sym_TomTypeAlone(tom_match5_1) ||  false  ) ) { { String  tom_match5_1_String=tom_get_slot_TomTypeAlone_String(tom_match5_1); { String  tom_strVisitType=tom_match5_1_String; if ( true ) {

              messageError(fileName,
                  findOriginTrackingLine(tom_options),
                  TomMessage.unknownVisitedType,
                  new Object[]{(tom_strVisitType)});
             } } } } if ( ( tom_is_fun_sym_Type(tom_match5_1) ||  false  ) ) { { tom.engine.adt.tomtype.types.TomType  tom_match5_1_TomType=tom_get_slot_Type_TomType(tom_match5_1); { tom.engine.adt.tomtype.types.TomType  tom_match5_1_TlType=tom_get_slot_Type_TlType(tom_match5_1); if ( ( tom_is_fun_sym_ASTTomType(tom_match5_1_TomType) ||  false  ) ) { { String  tom_match5_1_TomType_String=tom_get_slot_ASTTomType_String(tom_match5_1_TomType); { String  tom_ASTVisitType=tom_match5_1_TomType_String; if ( ( tom_is_fun_sym_TLType(tom_match5_1_TlType) ||  false  ) ) { { tom.engine.adt.tomsignature.types.TargetLanguage  tom_match5_1_TlType_Tl=tom_get_slot_TLType_Tl(tom_match5_1_TlType); { tom.engine.adt.tomsignature.types.TargetLanguage  tom_TLVisitType=tom_match5_1_TlType_Tl; if ( true ) {

              //check that all visitType have same visitorFwd

              currentVisitorFwd = symbolTable().getForwardType(tom_ASTVisitType);

              //noVisitorFwd defined for visitType
              if (currentVisitorFwd == null || currentVisitorFwd == tom_make_EmptyForward()){ 
                messageError(fileName,tom_TLVisitType.getStart().getLine(),
                    TomMessage.noVisitorForward,
                    new Object[]{(tom_ASTVisitType)});
              } else if (visitorFwd == null) {
                //first visit 
                visitorFwd = currentVisitorFwd;
              } else {
                //check if current visitor equals to previous visitor
                if (currentVisitorFwd != visitorFwd){ 
                  messageError(fileName,tom_TLVisitType.getStart().getLine(),
                      TomMessage.differentVisitorForward,
                      new Object[]{visitorFwd.getString(),currentVisitorFwd.getString()});
                }
              }
              verifyMatchVariable(tom_patternInstructionList);
             } } } } } } } } } } } }

         } } } } } } } } } }

      // next visit
      list = list.getTailconcTomVisit();
    }
  }

/**
   * The notion of conditional rewrite rule can be generalised with a sequence of conditions
   * as in lhs -> rhs where P1:=C1 ... where Pn:=Cn if Qj==Dj 
   * (i) Var(Pi) inter (var(lhs) U var(P1) U ... U var(Pi-1)) = empty
   * => introduced variables in Pi are "fresh"
   * (ii) var(ci) included in (var(lhs) U var(P1) U ... U var(Pi-1))
   * no new fresh variables in Ci
   * (iii) Var(rhs) included in (var(lhs) U U(var(Pi)))
   * => no new fresh variables in r
   * (iv) the condition Qj==Dj shall never lead to the declaration of a new variable
   */
  private void verifyRuleVariable(TomRuleList list) {
    while(!list.isEmptyconcTomRule()) {
      TomRule rewriteRule = list.getHeadconcTomRule();
      TomTerm ruleLhs = rewriteRule.getLhs();
      TomTerm ruleRhs = rewriteRule.getRhs();
      InstructionList condList = rewriteRule.getCondList();
      Option orgTrack = findOriginTracking(rewriteRule.getOption());
           
      // the accumulator for defined variables
      Hashtable variableTable = new Hashtable();
      // collect lhs variable 
      ArrayList freshLhsVariableList = new ArrayList();
      collectVariable(freshLhsVariableList, ruleLhs);

      // fill the table with found variables in lhs
      if(!appendToTable(variableTable, freshLhsVariableList)) {
        // there are already some coherence issues: same name but not same type
        break;
      }
      
       if(condList instanceof  tom.engine.adt.tominstruction.types.InstructionList ) { { tom.engine.adt.tominstruction.types.InstructionList  tom_match6_1=(( tom.engine.adt.tominstruction.types.InstructionList )condList); if ( ( tom_is_fun_sym_concInstruction(tom_match6_1) ||  false  ) ) { { tom.engine.adt.tominstruction.types.InstructionList  tom_match6_1_list1=tom_match6_1; { tom.engine.adt.tominstruction.types.InstructionList  tom_match6_1_begin1=tom_match6_1_list1; { tom.engine.adt.tominstruction.types.InstructionList  tom_match6_1_end1=tom_match6_1_list1; { while (!(tom_is_empty_concInstruction_InstructionList(tom_match6_1_end1))) {tom_match6_1_list1=tom_match6_1_end1; { { tom.engine.adt.tominstruction.types.Instruction  tom_cond=tom_get_head_concInstruction_InstructionList(tom_match6_1_list1);tom_match6_1_list1=tom_get_tail_concInstruction_InstructionList(tom_match6_1_list1); if ( true ) {

          Instruction condition = tom_cond;
           if(condition instanceof  tom.engine.adt.tominstruction.types.Instruction ) { { tom.engine.adt.tominstruction.types.Instruction  tom_match7_1=(( tom.engine.adt.tominstruction.types.Instruction )condition); if ( ( tom_is_fun_sym_MatchingCondition(tom_match7_1) ||  false  ) ) { { tom.engine.adt.tomterm.types.TomTerm  tom_match7_1_Lhs=tom_get_slot_MatchingCondition_Lhs(tom_match7_1); { tom.engine.adt.tomterm.types.TomTerm  tom_match7_1_Rhs=tom_get_slot_MatchingCondition_Rhs(tom_match7_1); { tom.engine.adt.tomterm.types.TomTerm  tom_lhs=tom_match7_1_Lhs; { tom.engine.adt.tomterm.types.TomTerm  tom_p=tom_lhs; { tom.engine.adt.tomterm.types.TomTerm  tom_rhs=tom_match7_1_Rhs; { tom.engine.adt.tomterm.types.TomTerm  tom_c=tom_rhs; if ( true ) {

              // (i)
              ArrayList pVar = new ArrayList();
              collectVariable(pVar, tom_p);
              if(!areAllFreshVariableTest(pVar, variableTable)) {
                // at least one no fresh variable
                break;
              }
              // (ii)
              ArrayList cVar = new ArrayList();
              collectVariable(cVar, tom_c);
              if(!areAllExistingVariableTest(cVar, variableTable, TomMessage.declaredVariableIssueInWhere)) {
                // there is a fresh variable
                break;
              }
              
              // fill the table
              if(!appendToTable(variableTable, pVar)) {
                // there are some coherence issues: same name but not same type
                break;
              }
             } } } } } } } } if ( ( tom_is_fun_sym_TypedEqualityCondition(tom_match7_1) ||  false  ) ) { { tom.engine.adt.tomtype.types.TomType  tom_match7_1_TomType=tom_get_slot_TypedEqualityCondition_TomType(tom_match7_1); { tom.engine.adt.tomterm.types.TomTerm  tom_match7_1_Lhs=tom_get_slot_TypedEqualityCondition_Lhs(tom_match7_1); { tom.engine.adt.tomterm.types.TomTerm  tom_match7_1_Rhs=tom_get_slot_TypedEqualityCondition_Rhs(tom_match7_1); { tom.engine.adt.tomterm.types.TomTerm  tom_lhs=tom_match7_1_Lhs; { tom.engine.adt.tomterm.types.TomTerm  tom_p=tom_lhs; { tom.engine.adt.tomterm.types.TomTerm  tom_rhs=tom_match7_1_Rhs; { tom.engine.adt.tomterm.types.TomTerm  tom_c=tom_rhs; if ( true ) {

               // (iv)
              ArrayList pVar = new ArrayList();
              collectVariable(pVar, tom_p);
              if(!areAllExistingVariableTest(pVar, variableTable, TomMessage.declaredVariableIssueInIf)) {
                // there is a fresh variable
                break;
              }
              // (iv)
              ArrayList cVar = new ArrayList();
              collectVariable(cVar, tom_c);
              if(!areAllExistingVariableTest(cVar, variableTable, TomMessage.declaredVariableIssueInIf)) {
                // there is a fresh variable
                break;
              }
              
              // fill the table
              if(!appendToTable(variableTable, pVar)) {
                // there are some coherence issues: same name but not same type
                break;
              }
             } } } } } } } } } } }

         } }tom_match6_1_end1=tom_get_tail_concInstruction_InstructionList(tom_match6_1_end1); } }tom_match6_1_list1=tom_match6_1_begin1; } } } } } } }

      
      // (iii)
      ArrayList variableRhs = new ArrayList();
      collectVariable(variableRhs, ruleRhs);
      areAllExistingVariableTest(variableRhs, variableTable, TomMessage.unknownRuleRhsVariable);
      
      // next rewrite rule
      list = list.getTailconcTomRule();
    }
  } //verifyRuleVariable
  
  private void verifyVariableTypeListCoherence(ArrayList list) {
    // compute multiplicities
    //System.out.println("list = " + list);
    HashMap map = new HashMap();
    Iterator it = list.iterator();
    while(it.hasNext()) {
      TomTerm variable = (TomTerm)it.next();
      TomName name = variable.getAstName();
      
      if(map.containsKey(name)) {
        TomTerm var = (TomTerm)map.get(name);
        TomType type1 = var.getAstType();
        TomType type2 = variable.getAstType();
        if(!(type1==type2)) {
          messageError(findOriginTrackingFileName(variable.getOption()),
              findOriginTrackingLine(variable.getOption()),
                       TomMessage.incoherentVariable,
                       new Object[]{name.getString(), type1.getTomType().getString(), type2.getTomType().getString()});
        }
      } else {
        map.put(name, variable);
      }
    }
  }  //verifyVariableTypeListCoherence  

  /**
   * Append variables to table 
   * if variable name already exists, it ensures it is coherent with the previous found type
   * return true if OK else false
   */
  private boolean appendToTable(Hashtable variableTable, List freshLhsVariableList) {
    Iterator it = freshLhsVariableList.iterator();
    while(it.hasNext()) {
      TomTerm variable = (TomTerm)it.next();
      TomName nameVar = variable.getAstName();
      TomType typeVar = variable.getAstType();
        
      if(variableTable.containsKey(nameVar)) {
        // this is an issue
        //TomTerm var = (TomTerm)variableTable.get(nameVar);
        //TomType type = var.getAstType();
        TomType type = (TomType)variableTable.get(nameVar);
        if(!(type==typeVar)) {
          messageError(findOriginTrackingFileName(variable.getOption()),
              findOriginTrackingLine(variable.getOption()),
                       TomMessage.incoherentVariable,
                       new Object[]{nameVar.getString(), type.getTomType().getString(), typeVar.getTomType().getString()});
          return false;
        }
      } else {
        // append to table
        variableTable.put(nameVar, typeVar);
        //System.out.println("Adding "+nameVar+" "+typeVar);
      }
    }
    return true;
  }
  
  /** (i) condition */
  private boolean areAllFreshVariableTest(ArrayList pVar, Hashtable variableTable) {
    Iterator it = pVar.iterator();
    while(it.hasNext()) {
      TomTerm variable = (TomTerm)it.next();
      TomName name = variable.getAstName();
      if(variableTable.containsKey(name)) {
        messageError(findOriginTrackingFileName(variable.getOption()),
            findOriginTrackingLine(variable.getOption()),
                     TomMessage.freshVariableIssue,
                     new Object[]{name.getString()});
         
        return false;
      }
    }
    return true;
  }

  /** (ii) condition and (iii) at the end when varaibleTable is full */
  private boolean areAllExistingVariableTest(ArrayList cVar, Hashtable variableTable, TomMessage message) {
    Iterator it = cVar.iterator();
    while(it.hasNext()) {
      TomTerm variable = (TomTerm)it.next();
      TomName name = variable.getAstName();
      if(!variableTable.containsKey(name)) {
        messageError(findOriginTrackingFileName(variable.getOption()),
            findOriginTrackingLine(variable.getOption()),
                     message,
                     new Object[]{name.getString()});             
        return false;
      }
    }
    return true;
  }
  
} // class TomTypeChecker
