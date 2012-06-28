/*
* generated by Xtext
*/

package tom.services;

import com.google.inject.Singleton;
import com.google.inject.Inject;

import org.eclipse.xtext.*;
import org.eclipse.xtext.service.GrammarProvider;
import org.eclipse.xtext.service.AbstractElementFinder.*;


@Singleton
public class TomDslGrammarAccess extends AbstractGrammarElementFinder {
	
	
	public class TomFileElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "TomFile");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final Assignment cOpsAssignment_0 = (Assignment)cAlternatives.eContents().get(0);
		private final RuleCall cOpsArrayOperationParserRuleCall_0_0 = (RuleCall)cOpsAssignment_0.eContents().get(0);
		private final Assignment cTermsAssignment_1 = (Assignment)cAlternatives.eContents().get(1);
		private final RuleCall cTermsTypeTermParserRuleCall_1_0 = (RuleCall)cTermsAssignment_1.eContents().get(0);
		private final Assignment cIncAssignment_2 = (Assignment)cAlternatives.eContents().get(2);
		private final RuleCall cIncIncludeParserRuleCall_2_0 = (RuleCall)cIncAssignment_2.eContents().get(0);
		private final Assignment cLocalsAssignment_3 = (Assignment)cAlternatives.eContents().get(3);
		private final RuleCall cLocalsLocalParserRuleCall_3_0 = (RuleCall)cLocalsAssignment_3.eContents().get(0);
		
		//TomFile:
		//	(ops+=ArrayOperation | terms+=TypeTerm | inc+=Include | locals=Local)*;
		public ParserRule getRule() { return rule; }

		//(ops+=ArrayOperation | terms+=TypeTerm | inc+=Include | locals=Local)*
		public Alternatives getAlternatives() { return cAlternatives; }

		//ops+=ArrayOperation
		public Assignment getOpsAssignment_0() { return cOpsAssignment_0; }

		//ArrayOperation
		public RuleCall getOpsArrayOperationParserRuleCall_0_0() { return cOpsArrayOperationParserRuleCall_0_0; }

		//terms+=TypeTerm
		public Assignment getTermsAssignment_1() { return cTermsAssignment_1; }

		//TypeTerm
		public RuleCall getTermsTypeTermParserRuleCall_1_0() { return cTermsTypeTermParserRuleCall_1_0; }

		//inc+=Include
		public Assignment getIncAssignment_2() { return cIncAssignment_2; }

		//Include
		public RuleCall getIncIncludeParserRuleCall_2_0() { return cIncIncludeParserRuleCall_2_0; }

		//locals=Local
		public Assignment getLocalsAssignment_3() { return cLocalsAssignment_3; }

		//Local
		public RuleCall getLocalsLocalParserRuleCall_3_0() { return cLocalsLocalParserRuleCall_3_0; }
	}

	public class ArrayOperationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "ArrayOperation");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cOperationParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cOperationArrayParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		
		//ArrayOperation:
		//	Operation | OperationArray;
		public ParserRule getRule() { return rule; }

		//Operation | OperationArray
		public Alternatives getAlternatives() { return cAlternatives; }

		//Operation
		public RuleCall getOperationParserRuleCall_0() { return cOperationParserRuleCall_0; }

		//OperationArray
		public RuleCall getOperationArrayParserRuleCall_1() { return cOperationArrayParserRuleCall_1; }
	}

	public class IncludeElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Include");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cIncludeKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final RuleCall cFIRST_LEVEL_LBRACKETTerminalRuleCall_1 = (RuleCall)cGroup.eContents().get(1);
		private final Assignment cPathAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final Alternatives cPathAlternatives_2_0 = (Alternatives)cPathAssignment_2.eContents().get(0);
		private final RuleCall cPathIDTerminalRuleCall_2_0_0 = (RuleCall)cPathAlternatives_2_0.eContents().get(0);
		private final Keyword cPathSolidusKeyword_2_0_1 = (Keyword)cPathAlternatives_2_0.eContents().get(1);
		private final Keyword cPathFullStopKeyword_2_0_2 = (Keyword)cPathAlternatives_2_0.eContents().get(2);
		private final RuleCall cFIRST_LEVEL_RBRACKETTerminalRuleCall_3 = (RuleCall)cGroup.eContents().get(3);
		
		//Include:
		//	"%include" FIRST_LEVEL_LBRACKET path+=(ID | "/" | ".")+ FIRST_LEVEL_RBRACKET;
		public ParserRule getRule() { return rule; }

		//"%include" FIRST_LEVEL_LBRACKET path+=(ID | "/" | ".")+ FIRST_LEVEL_RBRACKET
		public Group getGroup() { return cGroup; }

		//"%include"
		public Keyword getIncludeKeyword_0() { return cIncludeKeyword_0; }

		//FIRST_LEVEL_LBRACKET
		public RuleCall getFIRST_LEVEL_LBRACKETTerminalRuleCall_1() { return cFIRST_LEVEL_LBRACKETTerminalRuleCall_1; }

		//path+=(ID | "/" | ".")+
		public Assignment getPathAssignment_2() { return cPathAssignment_2; }

		//ID | "/" | "."
		public Alternatives getPathAlternatives_2_0() { return cPathAlternatives_2_0; }

		//ID
		public RuleCall getPathIDTerminalRuleCall_2_0_0() { return cPathIDTerminalRuleCall_2_0_0; }

		//"/"
		public Keyword getPathSolidusKeyword_2_0_1() { return cPathSolidusKeyword_2_0_1; }

		//"."
		public Keyword getPathFullStopKeyword_2_0_2() { return cPathFullStopKeyword_2_0_2; }

		//FIRST_LEVEL_RBRACKET
		public RuleCall getFIRST_LEVEL_RBRACKETTerminalRuleCall_3() { return cFIRST_LEVEL_RBRACKETTerminalRuleCall_3; }
	}

	public class LocalElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Local");
		private final RuleCall cJAVAMETHODTerminalRuleCall = (RuleCall)rule.eContents().get(1);
		
		//Local:
		//	JAVAMETHOD;
		public ParserRule getRule() { return rule; }

		//JAVAMETHOD
		public RuleCall getJAVAMETHODTerminalRuleCall() { return cJAVAMETHODTerminalRuleCall; }
	}

	public class OperationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Operation");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cOpKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cTermAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cTermIDTerminalRuleCall_1_0 = (RuleCall)cTermAssignment_1.eContents().get(0);
		private final Assignment cNameAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cNameIDTerminalRuleCall_2_0 = (RuleCall)cNameAssignment_2.eContents().get(0);
		private final Keyword cLeftParenthesisKeyword_3 = (Keyword)cGroup.eContents().get(3);
		private final Group cGroup_4 = (Group)cGroup.eContents().get(4);
		private final Assignment cArgAssignment_4_0 = (Assignment)cGroup_4.eContents().get(0);
		private final RuleCall cArgARGParserRuleCall_4_0_0 = (RuleCall)cArgAssignment_4_0.eContents().get(0);
		private final Group cGroup_4_1 = (Group)cGroup_4.eContents().get(1);
		private final Keyword cCommaKeyword_4_1_0 = (Keyword)cGroup_4_1.eContents().get(0);
		private final Assignment cArgAssignment_4_1_1 = (Assignment)cGroup_4_1.eContents().get(1);
		private final RuleCall cArgARGParserRuleCall_4_1_1_0 = (RuleCall)cArgAssignment_4_1_1.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_5 = (Keyword)cGroup.eContents().get(5);
		private final RuleCall cFIRST_LEVEL_LBRACKETTerminalRuleCall_6 = (RuleCall)cGroup.eContents().get(6);
		private final Keyword cIs_fsymKeyword_7 = (Keyword)cGroup.eContents().get(7);
		private final RuleCall cParIDParserRuleCall_8 = (RuleCall)cGroup.eContents().get(8);
		private final Assignment cFsymAssignment_9 = (Assignment)cGroup.eContents().get(9);
		private final RuleCall cFsymJavaBodyParserRuleCall_9_0 = (RuleCall)cFsymAssignment_9.eContents().get(0);
		private final Group cGroup_10 = (Group)cGroup.eContents().get(10);
		private final Keyword cGet_slotKeyword_10_0 = (Keyword)cGroup_10.eContents().get(0);
		private final RuleCall cParIDListParserRuleCall_10_1 = (RuleCall)cGroup_10.eContents().get(1);
		private final Assignment cSlotAssignment_10_2 = (Assignment)cGroup_10.eContents().get(2);
		private final RuleCall cSlotJavaBodyParserRuleCall_10_2_0 = (RuleCall)cSlotAssignment_10_2.eContents().get(0);
		private final Keyword cMakeKeyword_11 = (Keyword)cGroup.eContents().get(11);
		private final RuleCall cParIDListParserRuleCall_12 = (RuleCall)cGroup.eContents().get(12);
		private final Assignment cMakeAssignment_13 = (Assignment)cGroup.eContents().get(13);
		private final RuleCall cMakeJavaBodyParserRuleCall_13_0 = (RuleCall)cMakeAssignment_13.eContents().get(0);
		private final RuleCall cFIRST_LEVEL_RBRACKETTerminalRuleCall_14 = (RuleCall)cGroup.eContents().get(14);
		
		//Operation:
		//	"%op" term=ID name=ID "(" (arg+=ARG ("," arg+=ARG)*)? ")" FIRST_LEVEL_LBRACKET "is_fsym" ParID fsym=JavaBody
		//	("get_slot" ParIDList slot+=JavaBody)* "make" ParIDList make=JavaBody FIRST_LEVEL_RBRACKET;
		public ParserRule getRule() { return rule; }

		//"%op" term=ID name=ID "(" (arg+=ARG ("," arg+=ARG)*)? ")" FIRST_LEVEL_LBRACKET "is_fsym" ParID fsym=JavaBody ("get_slot"
		//ParIDList slot+=JavaBody)* "make" ParIDList make=JavaBody FIRST_LEVEL_RBRACKET
		public Group getGroup() { return cGroup; }

		//"%op"
		public Keyword getOpKeyword_0() { return cOpKeyword_0; }

		//term=ID
		public Assignment getTermAssignment_1() { return cTermAssignment_1; }

		//ID
		public RuleCall getTermIDTerminalRuleCall_1_0() { return cTermIDTerminalRuleCall_1_0; }

		//name=ID
		public Assignment getNameAssignment_2() { return cNameAssignment_2; }

		//ID
		public RuleCall getNameIDTerminalRuleCall_2_0() { return cNameIDTerminalRuleCall_2_0; }

		//"("
		public Keyword getLeftParenthesisKeyword_3() { return cLeftParenthesisKeyword_3; }

		//(arg+=ARG ("," arg+=ARG)*)?
		public Group getGroup_4() { return cGroup_4; }

		//arg+=ARG
		public Assignment getArgAssignment_4_0() { return cArgAssignment_4_0; }

		//ARG
		public RuleCall getArgARGParserRuleCall_4_0_0() { return cArgARGParserRuleCall_4_0_0; }

		//("," arg+=ARG)*
		public Group getGroup_4_1() { return cGroup_4_1; }

		//","
		public Keyword getCommaKeyword_4_1_0() { return cCommaKeyword_4_1_0; }

		//arg+=ARG
		public Assignment getArgAssignment_4_1_1() { return cArgAssignment_4_1_1; }

		//ARG
		public RuleCall getArgARGParserRuleCall_4_1_1_0() { return cArgARGParserRuleCall_4_1_1_0; }

		//")"
		public Keyword getRightParenthesisKeyword_5() { return cRightParenthesisKeyword_5; }

		//FIRST_LEVEL_LBRACKET
		public RuleCall getFIRST_LEVEL_LBRACKETTerminalRuleCall_6() { return cFIRST_LEVEL_LBRACKETTerminalRuleCall_6; }

		//"is_fsym"
		public Keyword getIs_fsymKeyword_7() { return cIs_fsymKeyword_7; }

		//ParID
		public RuleCall getParIDParserRuleCall_8() { return cParIDParserRuleCall_8; }

		//fsym=JavaBody
		public Assignment getFsymAssignment_9() { return cFsymAssignment_9; }

		//JavaBody
		public RuleCall getFsymJavaBodyParserRuleCall_9_0() { return cFsymJavaBodyParserRuleCall_9_0; }

		//("get_slot" ParIDList slot+=JavaBody)*
		public Group getGroup_10() { return cGroup_10; }

		//"get_slot"
		public Keyword getGet_slotKeyword_10_0() { return cGet_slotKeyword_10_0; }

		//ParIDList
		public RuleCall getParIDListParserRuleCall_10_1() { return cParIDListParserRuleCall_10_1; }

		//slot+=JavaBody
		public Assignment getSlotAssignment_10_2() { return cSlotAssignment_10_2; }

		//JavaBody
		public RuleCall getSlotJavaBodyParserRuleCall_10_2_0() { return cSlotJavaBodyParserRuleCall_10_2_0; }

		//"make"
		public Keyword getMakeKeyword_11() { return cMakeKeyword_11; }

		//ParIDList
		public RuleCall getParIDListParserRuleCall_12() { return cParIDListParserRuleCall_12; }

		//make=JavaBody
		public Assignment getMakeAssignment_13() { return cMakeAssignment_13; }

		//JavaBody
		public RuleCall getMakeJavaBodyParserRuleCall_13_0() { return cMakeJavaBodyParserRuleCall_13_0; }

		//FIRST_LEVEL_RBRACKET
		public RuleCall getFIRST_LEVEL_RBRACKETTerminalRuleCall_14() { return cFIRST_LEVEL_RBRACKETTerminalRuleCall_14; }
	}

	public class OperationArrayElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "OperationArray");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cOparrayKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cTermAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cTermIDTerminalRuleCall_1_0 = (RuleCall)cTermAssignment_1.eContents().get(0);
		private final Assignment cNameAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cNameIDTerminalRuleCall_2_0 = (RuleCall)cNameAssignment_2.eContents().get(0);
		private final Keyword cLeftParenthesisKeyword_3 = (Keyword)cGroup.eContents().get(3);
		private final RuleCall cIDTerminalRuleCall_4 = (RuleCall)cGroup.eContents().get(4);
		private final Keyword cAsteriskKeyword_5 = (Keyword)cGroup.eContents().get(5);
		private final Keyword cRightParenthesisKeyword_6 = (Keyword)cGroup.eContents().get(6);
		private final RuleCall cFIRST_LEVEL_LBRACKETTerminalRuleCall_7 = (RuleCall)cGroup.eContents().get(7);
		private final Alternatives cAlternatives_8 = (Alternatives)cGroup.eContents().get(8);
		private final Group cGroup_8_0 = (Group)cAlternatives_8.eContents().get(0);
		private final Keyword cIs_fsymKeyword_8_0_0 = (Keyword)cGroup_8_0.eContents().get(0);
		private final RuleCall cParIDParserRuleCall_8_0_1 = (RuleCall)cGroup_8_0.eContents().get(1);
		private final Assignment cFsymAssignment_8_0_2 = (Assignment)cGroup_8_0.eContents().get(2);
		private final RuleCall cFsymJavaBodyParserRuleCall_8_0_2_0 = (RuleCall)cFsymAssignment_8_0_2.eContents().get(0);
		private final Group cGroup_8_1 = (Group)cAlternatives_8.eContents().get(1);
		private final Keyword cGet_sizeKeyword_8_1_0 = (Keyword)cGroup_8_1.eContents().get(0);
		private final RuleCall cParIDParserRuleCall_8_1_1 = (RuleCall)cGroup_8_1.eContents().get(1);
		private final Assignment cSizeAssignment_8_1_2 = (Assignment)cGroup_8_1.eContents().get(2);
		private final RuleCall cSizeJavaBodyParserRuleCall_8_1_2_0 = (RuleCall)cSizeAssignment_8_1_2.eContents().get(0);
		private final Group cGroup_8_2 = (Group)cAlternatives_8.eContents().get(2);
		private final Keyword cGet_elementKeyword_8_2_0 = (Keyword)cGroup_8_2.eContents().get(0);
		private final RuleCall cParIDListParserRuleCall_8_2_1 = (RuleCall)cGroup_8_2.eContents().get(1);
		private final Assignment cElementAssignment_8_2_2 = (Assignment)cGroup_8_2.eContents().get(2);
		private final RuleCall cElementJavaBodyParserRuleCall_8_2_2_0 = (RuleCall)cElementAssignment_8_2_2.eContents().get(0);
		private final Group cGroup_8_3 = (Group)cAlternatives_8.eContents().get(3);
		private final Keyword cMake_emptyKeyword_8_3_0 = (Keyword)cGroup_8_3.eContents().get(0);
		private final RuleCall cParIDParserRuleCall_8_3_1 = (RuleCall)cGroup_8_3.eContents().get(1);
		private final Assignment cEmptyAssignment_8_3_2 = (Assignment)cGroup_8_3.eContents().get(2);
		private final RuleCall cEmptyJavaBodyParserRuleCall_8_3_2_0 = (RuleCall)cEmptyAssignment_8_3_2.eContents().get(0);
		private final Group cGroup_8_4 = (Group)cAlternatives_8.eContents().get(4);
		private final Keyword cMake_appendKeyword_8_4_0 = (Keyword)cGroup_8_4.eContents().get(0);
		private final RuleCall cParIDListParserRuleCall_8_4_1 = (RuleCall)cGroup_8_4.eContents().get(1);
		private final Assignment cAppendAssignment_8_4_2 = (Assignment)cGroup_8_4.eContents().get(2);
		private final RuleCall cAppendJavaBodyParserRuleCall_8_4_2_0 = (RuleCall)cAppendAssignment_8_4_2.eContents().get(0);
		private final RuleCall cFIRST_LEVEL_RBRACKETTerminalRuleCall_9 = (RuleCall)cGroup.eContents().get(9);
		
		//OperationArray:
		//	"%oparray" term=ID name=ID "(" ID "*" ")" FIRST_LEVEL_LBRACKET ("is_fsym" ParID fsym=JavaBody | "get_size" ParID
		//	size=JavaBody | "get_element" ParIDList element=JavaBody | "make_empty" ParID empty=JavaBody | "make_append" ParIDList
		//	append=JavaBody)+ FIRST_LEVEL_RBRACKET;
		public ParserRule getRule() { return rule; }

		//"%oparray" term=ID name=ID "(" ID "*" ")" FIRST_LEVEL_LBRACKET ("is_fsym" ParID fsym=JavaBody | "get_size" ParID
		//size=JavaBody | "get_element" ParIDList element=JavaBody | "make_empty" ParID empty=JavaBody | "make_append" ParIDList
		//append=JavaBody)+ FIRST_LEVEL_RBRACKET
		public Group getGroup() { return cGroup; }

		//"%oparray"
		public Keyword getOparrayKeyword_0() { return cOparrayKeyword_0; }

		//term=ID
		public Assignment getTermAssignment_1() { return cTermAssignment_1; }

		//ID
		public RuleCall getTermIDTerminalRuleCall_1_0() { return cTermIDTerminalRuleCall_1_0; }

		//name=ID
		public Assignment getNameAssignment_2() { return cNameAssignment_2; }

		//ID
		public RuleCall getNameIDTerminalRuleCall_2_0() { return cNameIDTerminalRuleCall_2_0; }

		//"("
		public Keyword getLeftParenthesisKeyword_3() { return cLeftParenthesisKeyword_3; }

		//ID
		public RuleCall getIDTerminalRuleCall_4() { return cIDTerminalRuleCall_4; }

		//"*"
		public Keyword getAsteriskKeyword_5() { return cAsteriskKeyword_5; }

		//")"
		public Keyword getRightParenthesisKeyword_6() { return cRightParenthesisKeyword_6; }

		//FIRST_LEVEL_LBRACKET
		public RuleCall getFIRST_LEVEL_LBRACKETTerminalRuleCall_7() { return cFIRST_LEVEL_LBRACKETTerminalRuleCall_7; }

		//("is_fsym" ParID fsym=JavaBody | "get_size" ParID size=JavaBody | "get_element" ParIDList element=JavaBody |
		//"make_empty" ParID empty=JavaBody | "make_append" ParIDList append=JavaBody)+
		public Alternatives getAlternatives_8() { return cAlternatives_8; }

		//"is_fsym" ParID fsym=JavaBody
		public Group getGroup_8_0() { return cGroup_8_0; }

		//"is_fsym"
		public Keyword getIs_fsymKeyword_8_0_0() { return cIs_fsymKeyword_8_0_0; }

		//ParID
		public RuleCall getParIDParserRuleCall_8_0_1() { return cParIDParserRuleCall_8_0_1; }

		//fsym=JavaBody
		public Assignment getFsymAssignment_8_0_2() { return cFsymAssignment_8_0_2; }

		//JavaBody
		public RuleCall getFsymJavaBodyParserRuleCall_8_0_2_0() { return cFsymJavaBodyParserRuleCall_8_0_2_0; }

		//"get_size" ParID size=JavaBody
		public Group getGroup_8_1() { return cGroup_8_1; }

		//"get_size"
		public Keyword getGet_sizeKeyword_8_1_0() { return cGet_sizeKeyword_8_1_0; }

		//ParID
		public RuleCall getParIDParserRuleCall_8_1_1() { return cParIDParserRuleCall_8_1_1; }

		//size=JavaBody
		public Assignment getSizeAssignment_8_1_2() { return cSizeAssignment_8_1_2; }

		//JavaBody
		public RuleCall getSizeJavaBodyParserRuleCall_8_1_2_0() { return cSizeJavaBodyParserRuleCall_8_1_2_0; }

		//"get_element" ParIDList element=JavaBody
		public Group getGroup_8_2() { return cGroup_8_2; }

		//"get_element"
		public Keyword getGet_elementKeyword_8_2_0() { return cGet_elementKeyword_8_2_0; }

		//ParIDList
		public RuleCall getParIDListParserRuleCall_8_2_1() { return cParIDListParserRuleCall_8_2_1; }

		//element=JavaBody
		public Assignment getElementAssignment_8_2_2() { return cElementAssignment_8_2_2; }

		//JavaBody
		public RuleCall getElementJavaBodyParserRuleCall_8_2_2_0() { return cElementJavaBodyParserRuleCall_8_2_2_0; }

		//"make_empty" ParID empty=JavaBody
		public Group getGroup_8_3() { return cGroup_8_3; }

		//"make_empty"
		public Keyword getMake_emptyKeyword_8_3_0() { return cMake_emptyKeyword_8_3_0; }

		//ParID
		public RuleCall getParIDParserRuleCall_8_3_1() { return cParIDParserRuleCall_8_3_1; }

		//empty=JavaBody
		public Assignment getEmptyAssignment_8_3_2() { return cEmptyAssignment_8_3_2; }

		//JavaBody
		public RuleCall getEmptyJavaBodyParserRuleCall_8_3_2_0() { return cEmptyJavaBodyParserRuleCall_8_3_2_0; }

		//"make_append" ParIDList append=JavaBody
		public Group getGroup_8_4() { return cGroup_8_4; }

		//"make_append"
		public Keyword getMake_appendKeyword_8_4_0() { return cMake_appendKeyword_8_4_0; }

		//ParIDList
		public RuleCall getParIDListParserRuleCall_8_4_1() { return cParIDListParserRuleCall_8_4_1; }

		//append=JavaBody
		public Assignment getAppendAssignment_8_4_2() { return cAppendAssignment_8_4_2; }

		//JavaBody
		public RuleCall getAppendJavaBodyParserRuleCall_8_4_2_0() { return cAppendJavaBodyParserRuleCall_8_4_2_0; }

		//FIRST_LEVEL_RBRACKET
		public RuleCall getFIRST_LEVEL_RBRACKETTerminalRuleCall_9() { return cFIRST_LEVEL_RBRACKETTerminalRuleCall_9; }
	}

	public class JavaBodyElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "JavaBody");
		private final Assignment cBodyAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cBodyBRCKTSTMTTerminalRuleCall_0 = (RuleCall)cBodyAssignment.eContents().get(0);
		
		////FSYMNode:
		////	( 'is_fsym' ParID fsym=JavaBody)
		////;
		//JavaBody:
		//	body=BRCKTSTMT;
		public ParserRule getRule() { return rule; }

		//body=BRCKTSTMT
		public Assignment getBodyAssignment() { return cBodyAssignment; }

		//BRCKTSTMT
		public RuleCall getBodyBRCKTSTMTTerminalRuleCall_0() { return cBodyBRCKTSTMTTerminalRuleCall_0; }
	}

	public class ParIDElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "ParID");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cLeftParenthesisKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final RuleCall cIDTerminalRuleCall_1 = (RuleCall)cGroup.eContents().get(1);
		private final Keyword cRightParenthesisKeyword_2 = (Keyword)cGroup.eContents().get(2);
		
		//ParID:
		//	"(" ID? ")";
		public ParserRule getRule() { return rule; }

		//"(" ID? ")"
		public Group getGroup() { return cGroup; }

		//"("
		public Keyword getLeftParenthesisKeyword_0() { return cLeftParenthesisKeyword_0; }

		//ID?
		public RuleCall getIDTerminalRuleCall_1() { return cIDTerminalRuleCall_1; }

		//")"
		public Keyword getRightParenthesisKeyword_2() { return cRightParenthesisKeyword_2; }
	}

	public class ParIDListElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "ParIDList");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cLeftParenthesisKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final RuleCall cIDTerminalRuleCall_1_0 = (RuleCall)cGroup_1.eContents().get(0);
		private final Group cGroup_1_1 = (Group)cGroup_1.eContents().get(1);
		private final Keyword cCommaKeyword_1_1_0 = (Keyword)cGroup_1_1.eContents().get(0);
		private final RuleCall cIDTerminalRuleCall_1_1_1 = (RuleCall)cGroup_1_1.eContents().get(1);
		private final Keyword cRightParenthesisKeyword_2 = (Keyword)cGroup.eContents().get(2);
		
		//ParIDList:
		//	"(" (ID ("," ID)*)? ")";
		public ParserRule getRule() { return rule; }

		//"(" (ID ("," ID)*)? ")"
		public Group getGroup() { return cGroup; }

		//"("
		public Keyword getLeftParenthesisKeyword_0() { return cLeftParenthesisKeyword_0; }

		//(ID ("," ID)*)?
		public Group getGroup_1() { return cGroup_1; }

		//ID
		public RuleCall getIDTerminalRuleCall_1_0() { return cIDTerminalRuleCall_1_0; }

		//("," ID)*
		public Group getGroup_1_1() { return cGroup_1_1; }

		//","
		public Keyword getCommaKeyword_1_1_0() { return cCommaKeyword_1_1_0; }

		//ID
		public RuleCall getIDTerminalRuleCall_1_1_1() { return cIDTerminalRuleCall_1_1_1; }

		//")"
		public Keyword getRightParenthesisKeyword_2() { return cRightParenthesisKeyword_2; }
	}

	public class ARGElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "ARG");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cNameAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cNameIDTerminalRuleCall_0_0 = (RuleCall)cNameAssignment_0.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Keyword cColonKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final Assignment cTypeAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final RuleCall cTypeIDTerminalRuleCall_1_1_0 = (RuleCall)cTypeAssignment_1_1.eContents().get(0);
		
		//ARG:
		//	name=ID (":" type=ID)?;
		public ParserRule getRule() { return rule; }

		//name=ID (":" type=ID)?
		public Group getGroup() { return cGroup; }

		//name=ID
		public Assignment getNameAssignment_0() { return cNameAssignment_0; }

		//ID
		public RuleCall getNameIDTerminalRuleCall_0_0() { return cNameIDTerminalRuleCall_0_0; }

		//(":" type=ID)?
		public Group getGroup_1() { return cGroup_1; }

		//":"
		public Keyword getColonKeyword_1_0() { return cColonKeyword_1_0; }

		//type=ID
		public Assignment getTypeAssignment_1_1() { return cTypeAssignment_1_1; }

		//ID
		public RuleCall getTypeIDTerminalRuleCall_1_1_0() { return cTypeIDTerminalRuleCall_1_1_0; }
	}

	public class TypeTermElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "TypeTerm");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cTypetermKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final RuleCall cFIRST_LEVEL_LBRACKETTerminalRuleCall_2 = (RuleCall)cGroup.eContents().get(2);
		private final Keyword cImplementKeyword_3 = (Keyword)cGroup.eContents().get(3);
		private final Assignment cImplementAssignment_4 = (Assignment)cGroup.eContents().get(4);
		private final RuleCall cImplementJavaBodyParserRuleCall_4_0 = (RuleCall)cImplementAssignment_4.eContents().get(0);
		private final Keyword cIs_sortKeyword_5 = (Keyword)cGroup.eContents().get(5);
		private final RuleCall cParIDParserRuleCall_6 = (RuleCall)cGroup.eContents().get(6);
		private final Assignment cSortAssignment_7 = (Assignment)cGroup.eContents().get(7);
		private final RuleCall cSortJavaBodyParserRuleCall_7_0 = (RuleCall)cSortAssignment_7.eContents().get(0);
		private final Keyword cEqualsKeyword_8 = (Keyword)cGroup.eContents().get(8);
		private final Keyword cLeftParenthesisKeyword_9 = (Keyword)cGroup.eContents().get(9);
		private final RuleCall cIDTerminalRuleCall_10 = (RuleCall)cGroup.eContents().get(10);
		private final Keyword cCommaKeyword_11 = (Keyword)cGroup.eContents().get(11);
		private final RuleCall cIDTerminalRuleCall_12 = (RuleCall)cGroup.eContents().get(12);
		private final Keyword cRightParenthesisKeyword_13 = (Keyword)cGroup.eContents().get(13);
		private final Assignment cEqualsAssignment_14 = (Assignment)cGroup.eContents().get(14);
		private final RuleCall cEqualsJavaBodyParserRuleCall_14_0 = (RuleCall)cEqualsAssignment_14.eContents().get(0);
		private final RuleCall cFIRST_LEVEL_RBRACKETTerminalRuleCall_15 = (RuleCall)cGroup.eContents().get(15);
		
		//TypeTerm:
		//	"%typeterm" name=ID FIRST_LEVEL_LBRACKET "implement" implement=JavaBody "is_sort" ParID sort=JavaBody "equals" "(" ID
		//	"," ID ")" equals=JavaBody FIRST_LEVEL_RBRACKET;
		public ParserRule getRule() { return rule; }

		//"%typeterm" name=ID FIRST_LEVEL_LBRACKET "implement" implement=JavaBody "is_sort" ParID sort=JavaBody "equals" "(" ID
		//"," ID ")" equals=JavaBody FIRST_LEVEL_RBRACKET
		public Group getGroup() { return cGroup; }

		//"%typeterm"
		public Keyword getTypetermKeyword_0() { return cTypetermKeyword_0; }

		//name=ID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//ID
		public RuleCall getNameIDTerminalRuleCall_1_0() { return cNameIDTerminalRuleCall_1_0; }

		//FIRST_LEVEL_LBRACKET
		public RuleCall getFIRST_LEVEL_LBRACKETTerminalRuleCall_2() { return cFIRST_LEVEL_LBRACKETTerminalRuleCall_2; }

		//"implement"
		public Keyword getImplementKeyword_3() { return cImplementKeyword_3; }

		//implement=JavaBody
		public Assignment getImplementAssignment_4() { return cImplementAssignment_4; }

		//JavaBody
		public RuleCall getImplementJavaBodyParserRuleCall_4_0() { return cImplementJavaBodyParserRuleCall_4_0; }

		//"is_sort"
		public Keyword getIs_sortKeyword_5() { return cIs_sortKeyword_5; }

		//ParID
		public RuleCall getParIDParserRuleCall_6() { return cParIDParserRuleCall_6; }

		//sort=JavaBody
		public Assignment getSortAssignment_7() { return cSortAssignment_7; }

		//JavaBody
		public RuleCall getSortJavaBodyParserRuleCall_7_0() { return cSortJavaBodyParserRuleCall_7_0; }

		//"equals"
		public Keyword getEqualsKeyword_8() { return cEqualsKeyword_8; }

		//"("
		public Keyword getLeftParenthesisKeyword_9() { return cLeftParenthesisKeyword_9; }

		//ID
		public RuleCall getIDTerminalRuleCall_10() { return cIDTerminalRuleCall_10; }

		//","
		public Keyword getCommaKeyword_11() { return cCommaKeyword_11; }

		//ID
		public RuleCall getIDTerminalRuleCall_12() { return cIDTerminalRuleCall_12; }

		//")"
		public Keyword getRightParenthesisKeyword_13() { return cRightParenthesisKeyword_13; }

		//equals=JavaBody
		public Assignment getEqualsAssignment_14() { return cEqualsAssignment_14; }

		//JavaBody
		public RuleCall getEqualsJavaBodyParserRuleCall_14_0() { return cEqualsJavaBodyParserRuleCall_14_0; }

		//FIRST_LEVEL_RBRACKET
		public RuleCall getFIRST_LEVEL_RBRACKETTerminalRuleCall_15() { return cFIRST_LEVEL_RBRACKETTerminalRuleCall_15; }
	}
	
	
	private TomFileElements pTomFile;
	private ArrayOperationElements pArrayOperation;
	private IncludeElements pInclude;
	private LocalElements pLocal;
	private OperationElements pOperation;
	private OperationArrayElements pOperationArray;
	private JavaBodyElements pJavaBody;
	private ParIDElements pParID;
	private ParIDListElements pParIDList;
	private ARGElements pARG;
	private TypeTermElements pTypeTerm;
	private TerminalRule tFIRST_LEVEL_LBRACKET;
	private TerminalRule tFIRST_LEVEL_RBRACKET;
	private TerminalRule tBRCKTSTMT;
	private TerminalRule tJAVAMETHOD;
	private TerminalRule tID;
	private TerminalRule tML_COMMENT;
	private TerminalRule tSL_COMMENT;
	private TerminalRule tWS;
	private TerminalRule tANY_OTHER;
	
	private final GrammarProvider grammarProvider;

	@Inject
	public TomDslGrammarAccess(GrammarProvider grammarProvider) {
		this.grammarProvider = grammarProvider;
	}
	
	public Grammar getGrammar() {	
		return grammarProvider.getGrammar(this);
	}
	

	
	//TomFile:
	//	(ops+=ArrayOperation | terms+=TypeTerm | inc+=Include | locals=Local)*;
	public TomFileElements getTomFileAccess() {
		return (pTomFile != null) ? pTomFile : (pTomFile = new TomFileElements());
	}
	
	public ParserRule getTomFileRule() {
		return getTomFileAccess().getRule();
	}

	//ArrayOperation:
	//	Operation | OperationArray;
	public ArrayOperationElements getArrayOperationAccess() {
		return (pArrayOperation != null) ? pArrayOperation : (pArrayOperation = new ArrayOperationElements());
	}
	
	public ParserRule getArrayOperationRule() {
		return getArrayOperationAccess().getRule();
	}

	//Include:
	//	"%include" FIRST_LEVEL_LBRACKET path+=(ID | "/" | ".")+ FIRST_LEVEL_RBRACKET;
	public IncludeElements getIncludeAccess() {
		return (pInclude != null) ? pInclude : (pInclude = new IncludeElements());
	}
	
	public ParserRule getIncludeRule() {
		return getIncludeAccess().getRule();
	}

	//Local:
	//	JAVAMETHOD;
	public LocalElements getLocalAccess() {
		return (pLocal != null) ? pLocal : (pLocal = new LocalElements());
	}
	
	public ParserRule getLocalRule() {
		return getLocalAccess().getRule();
	}

	//Operation:
	//	"%op" term=ID name=ID "(" (arg+=ARG ("," arg+=ARG)*)? ")" FIRST_LEVEL_LBRACKET "is_fsym" ParID fsym=JavaBody
	//	("get_slot" ParIDList slot+=JavaBody)* "make" ParIDList make=JavaBody FIRST_LEVEL_RBRACKET;
	public OperationElements getOperationAccess() {
		return (pOperation != null) ? pOperation : (pOperation = new OperationElements());
	}
	
	public ParserRule getOperationRule() {
		return getOperationAccess().getRule();
	}

	//OperationArray:
	//	"%oparray" term=ID name=ID "(" ID "*" ")" FIRST_LEVEL_LBRACKET ("is_fsym" ParID fsym=JavaBody | "get_size" ParID
	//	size=JavaBody | "get_element" ParIDList element=JavaBody | "make_empty" ParID empty=JavaBody | "make_append" ParIDList
	//	append=JavaBody)+ FIRST_LEVEL_RBRACKET;
	public OperationArrayElements getOperationArrayAccess() {
		return (pOperationArray != null) ? pOperationArray : (pOperationArray = new OperationArrayElements());
	}
	
	public ParserRule getOperationArrayRule() {
		return getOperationArrayAccess().getRule();
	}

	////FSYMNode:
	////	( 'is_fsym' ParID fsym=JavaBody)
	////;
	//JavaBody:
	//	body=BRCKTSTMT;
	public JavaBodyElements getJavaBodyAccess() {
		return (pJavaBody != null) ? pJavaBody : (pJavaBody = new JavaBodyElements());
	}
	
	public ParserRule getJavaBodyRule() {
		return getJavaBodyAccess().getRule();
	}

	//ParID:
	//	"(" ID? ")";
	public ParIDElements getParIDAccess() {
		return (pParID != null) ? pParID : (pParID = new ParIDElements());
	}
	
	public ParserRule getParIDRule() {
		return getParIDAccess().getRule();
	}

	//ParIDList:
	//	"(" (ID ("," ID)*)? ")";
	public ParIDListElements getParIDListAccess() {
		return (pParIDList != null) ? pParIDList : (pParIDList = new ParIDListElements());
	}
	
	public ParserRule getParIDListRule() {
		return getParIDListAccess().getRule();
	}

	//ARG:
	//	name=ID (":" type=ID)?;
	public ARGElements getARGAccess() {
		return (pARG != null) ? pARG : (pARG = new ARGElements());
	}
	
	public ParserRule getARGRule() {
		return getARGAccess().getRule();
	}

	//TypeTerm:
	//	"%typeterm" name=ID FIRST_LEVEL_LBRACKET "implement" implement=JavaBody "is_sort" ParID sort=JavaBody "equals" "(" ID
	//	"," ID ")" equals=JavaBody FIRST_LEVEL_RBRACKET;
	public TypeTermElements getTypeTermAccess() {
		return (pTypeTerm != null) ? pTypeTerm : (pTypeTerm = new TypeTermElements());
	}
	
	public ParserRule getTypeTermRule() {
		return getTypeTermAccess().getRule();
	}

	//terminal FIRST_LEVEL_LBRACKET:
	//	"{";
	public TerminalRule getFIRST_LEVEL_LBRACKETRule() {
		return (tFIRST_LEVEL_LBRACKET != null) ? tFIRST_LEVEL_LBRACKET : (tFIRST_LEVEL_LBRACKET = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "FIRST_LEVEL_LBRACKET"));
	} 

	//terminal FIRST_LEVEL_RBRACKET:
	//	"}";
	public TerminalRule getFIRST_LEVEL_RBRACKETRule() {
		return (tFIRST_LEVEL_RBRACKET != null) ? tFIRST_LEVEL_RBRACKET : (tFIRST_LEVEL_RBRACKET = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "FIRST_LEVEL_RBRACKET"));
	} 

	//terminal BRCKTSTMT:
	//	"{"->"}";
	public TerminalRule getBRCKTSTMTRule() {
		return (tBRCKTSTMT != null) ? tBRCKTSTMT : (tBRCKTSTMT = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "BRCKTSTMT"));
	} 

	//terminal JAVAMETHOD:
	//	"private"->"("->")"->"{"->"}";
	public TerminalRule getJAVAMETHODRule() {
		return (tJAVAMETHOD != null) ? tJAVAMETHOD : (tJAVAMETHOD = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "JAVAMETHOD"));
	} 

	//terminal ID:
	//	"^"? ("a".."z" | "A".."Z" | "_") ("a".."z" | "A".."Z" | "_" | "0".."9")*;
	public TerminalRule getIDRule() {
		return (tID != null) ? tID : (tID = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "ID"));
	} 

	//terminal ML_COMMENT:
	//	"/ *"->"* /";
	public TerminalRule getML_COMMENTRule() {
		return (tML_COMMENT != null) ? tML_COMMENT : (tML_COMMENT = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "ML_COMMENT"));
	} 

	//terminal SL_COMMENT:
	//	"//" !("\n" | "\r")* ("\r"? "\n")?;
	public TerminalRule getSL_COMMENTRule() {
		return (tSL_COMMENT != null) ? tSL_COMMENT : (tSL_COMMENT = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "SL_COMMENT"));
	} 

	//terminal WS:
	//	(" " | "\t" | "\r" | "\n")+;
	public TerminalRule getWSRule() {
		return (tWS != null) ? tWS : (tWS = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "WS"));
	} 

	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return (tANY_OTHER != null) ? tANY_OTHER : (tANY_OTHER = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "ANY_OTHER"));
	} 
}
