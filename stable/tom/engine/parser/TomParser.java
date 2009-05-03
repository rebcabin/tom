// $ANTLR 2.7.7 (20060906): "TomLanguage.g" -> "TomParser.java"$

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
 *
 **/

package tom.engine.parser;


import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;

import java.io.StringReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import tom.engine.TomBase;
import tom.engine.TomMessage;
import tom.engine.exception.TomException;

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

import tom.engine.tools.SymbolTable;
import tom.engine.tools.ASTFactory;
import tom.engine.xml.Constants;
import tom.platform.OptionManager;
import tom.platform.PlatformLogRecord;
import aterm.*;
import antlr.TokenStreamSelector;

public class TomParser extends antlr.LLkParser       implements TomParserTokenTypes
 {

    //--------------------------
    private static boolean tom_equal_term_char(char t1, char t2) {return  t1==t2 ;}private static boolean tom_is_sort_char(char t) {return  true ;} private static boolean tom_equal_term_String(String t1, String t2) {return  t1.equals(t2) ;}private static boolean tom_is_sort_String(String t) {return  t instanceof String ;} private static boolean tom_equal_term_int(int t1, int t2) {return  t1==t2 ;}private static boolean tom_is_sort_int(int t) {return  true ;} private static boolean tom_equal_term_Instruction(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_Instruction(Object t) {return  (t instanceof tom.engine.adt.tominstruction.types.Instruction) ;}private static boolean tom_equal_term_InstructionList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_InstructionList(Object t) {return  (t instanceof tom.engine.adt.tominstruction.types.InstructionList) ;}private static boolean tom_equal_term_ConstraintInstruction(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_ConstraintInstruction(Object t) {return  (t instanceof tom.engine.adt.tominstruction.types.ConstraintInstruction) ;}private static boolean tom_equal_term_ConstraintInstructionList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_ConstraintInstructionList(Object t) {return  (t instanceof tom.engine.adt.tominstruction.types.ConstraintInstructionList) ;}private static boolean tom_equal_term_TomTypeList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomTypeList(Object t) {return  (t instanceof tom.engine.adt.tomtype.types.TomTypeList) ;}private static boolean tom_equal_term_TomType(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomType(Object t) {return  (t instanceof tom.engine.adt.tomtype.types.TomType) ;}private static boolean tom_equal_term_TomSymbolList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomSymbolList(Object t) {return  (t instanceof tom.engine.adt.tomsignature.types.TomSymbolList) ;}private static boolean tom_equal_term_TomVisit(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomVisit(Object t) {return  (t instanceof tom.engine.adt.tomsignature.types.TomVisit) ;}private static boolean tom_equal_term_TomStructureTable(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomStructureTable(Object t) {return  (t instanceof tom.engine.adt.tomsignature.types.TomStructureTable) ;}private static boolean tom_equal_term_TextPosition(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TextPosition(Object t) {return  (t instanceof tom.engine.adt.tomsignature.types.TextPosition) ;}private static boolean tom_equal_term_TomEntry(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomEntry(Object t) {return  (t instanceof tom.engine.adt.tomsignature.types.TomEntry) ;}private static boolean tom_equal_term_TomEntryList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomEntryList(Object t) {return  (t instanceof tom.engine.adt.tomsignature.types.TomEntryList) ;}private static boolean tom_equal_term_TomVisitList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomVisitList(Object t) {return  (t instanceof tom.engine.adt.tomsignature.types.TomVisitList) ;}private static boolean tom_equal_term_TargetLanguage(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TargetLanguage(Object t) {return  (t instanceof tom.engine.adt.tomsignature.types.TargetLanguage) ;}private static boolean tom_equal_term_TomSymbolTable(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomSymbolTable(Object t) {return  (t instanceof tom.engine.adt.tomsignature.types.TomSymbolTable) ;}private static boolean tom_equal_term_TomSymbol(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomSymbol(Object t) {return  (t instanceof tom.engine.adt.tomsignature.types.TomSymbol) ;}private static boolean tom_equal_term_KeyEntry(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_KeyEntry(Object t) {return  (t instanceof tom.engine.adt.tomsignature.types.KeyEntry) ;}private static boolean tom_equal_term_ElementaryTheory(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_ElementaryTheory(Object t) {return  (t instanceof tom.engine.adt.theory.types.ElementaryTheory) ;}private static boolean tom_equal_term_Theory(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_Theory(Object t) {return  (t instanceof tom.engine.adt.theory.types.Theory) ;}private static boolean tom_equal_term_DeclarationList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_DeclarationList(Object t) {return  (t instanceof tom.engine.adt.tomdeclaration.types.DeclarationList) ;}private static boolean tom_equal_term_Declaration(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_Declaration(Object t) {return  (t instanceof tom.engine.adt.tomdeclaration.types.Declaration) ;}private static boolean tom_equal_term_TomNumber(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomNumber(Object t) {return  (t instanceof tom.engine.adt.tomname.types.TomNumber) ;}private static boolean tom_equal_term_TomNumberList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomNumberList(Object t) {return  (t instanceof tom.engine.adt.tomname.types.TomNumberList) ;}private static boolean tom_equal_term_TomNameList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomNameList(Object t) {return  (t instanceof tom.engine.adt.tomname.types.TomNameList) ;}private static boolean tom_equal_term_TomName(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomName(Object t) {return  (t instanceof tom.engine.adt.tomname.types.TomName) ;}private static boolean tom_equal_term_Expression(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_Expression(Object t) {return  (t instanceof tom.engine.adt.tomexpression.types.Expression) ;}private static boolean tom_equal_term_TomList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomList(Object t) {return  (t instanceof tom.engine.adt.tomterm.types.TomList) ;}private static boolean tom_equal_term_TomTerm(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_TomTerm(Object t) {return  (t instanceof tom.engine.adt.tomterm.types.TomTerm) ;}private static boolean tom_equal_term_Option(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_Option(Object t) {return  (t instanceof tom.engine.adt.tomoption.types.Option) ;}private static boolean tom_equal_term_OptionList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_OptionList(Object t) {return  (t instanceof tom.engine.adt.tomoption.types.OptionList) ;}private static boolean tom_equal_term_NumericConstraintType(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_NumericConstraintType(Object t) {return  (t instanceof tom.engine.adt.tomconstraint.types.NumericConstraintType) ;}private static boolean tom_equal_term_Constraint(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_Constraint(Object t) {return  (t instanceof tom.engine.adt.tomconstraint.types.Constraint) ;}private static boolean tom_equal_term_ConstraintList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_ConstraintList(Object t) {return  (t instanceof tom.engine.adt.tomconstraint.types.ConstraintList) ;}private static boolean tom_equal_term_PairNameDecl(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_PairNameDecl(Object t) {return  (t instanceof tom.engine.adt.tomslot.types.PairNameDecl) ;}private static boolean tom_equal_term_PairNameDeclList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_PairNameDeclList(Object t) {return  (t instanceof tom.engine.adt.tomslot.types.PairNameDeclList) ;}private static boolean tom_equal_term_SlotList(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_SlotList(Object t) {return  (t instanceof tom.engine.adt.tomslot.types.SlotList) ;}private static boolean tom_equal_term_Slot(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_Slot(Object t) {return  (t instanceof tom.engine.adt.tomslot.types.Slot) ;}private static  tom.engine.adt.tominstruction.types.Instruction  tom_make_ExpressionToInstruction( tom.engine.adt.tomexpression.types.Expression  t0) { return  tom.engine.adt.tominstruction.types.instruction.ExpressionToInstruction.make(t0) ;}private static  tom.engine.adt.tominstruction.types.Instruction  tom_make_TargetLanguageToInstruction( tom.engine.adt.tomsignature.types.TargetLanguage  t0) { return  tom.engine.adt.tominstruction.types.instruction.TargetLanguageToInstruction.make(t0) ;}private static  tom.engine.adt.tominstruction.types.Instruction  tom_make_Return( tom.engine.adt.tomterm.types.TomTerm  t0) { return  tom.engine.adt.tominstruction.types.instruction.Return.make(t0) ;}private static  tom.engine.adt.tominstruction.types.Instruction  tom_make_AbstractBlock( tom.engine.adt.tominstruction.types.InstructionList  t0) { return  tom.engine.adt.tominstruction.types.instruction.AbstractBlock.make(t0) ;}private static  tom.engine.adt.tominstruction.types.Instruction  tom_make_Match( tom.engine.adt.tominstruction.types.ConstraintInstructionList  t0,  tom.engine.adt.tomoption.types.OptionList  t1) { return  tom.engine.adt.tominstruction.types.instruction.Match.make(t0, t1) ;}private static  tom.engine.adt.tominstruction.types.Instruction  tom_make_RawAction( tom.engine.adt.tominstruction.types.Instruction  t0) { return  tom.engine.adt.tominstruction.types.instruction.RawAction.make(t0) ;}private static  tom.engine.adt.tominstruction.types.ConstraintInstruction  tom_make_ConstraintInstruction( tom.engine.adt.tomconstraint.types.Constraint  t0,  tom.engine.adt.tominstruction.types.Instruction  t1,  tom.engine.adt.tomoption.types.OptionList  t2) { return  tom.engine.adt.tominstruction.types.constraintinstruction.ConstraintInstruction.make(t0, t1, t2) ;}private static  tom.engine.adt.tomtype.types.TomType  tom_make_Type( tom.engine.adt.tomtype.types.TomType  t0,  tom.engine.adt.tomtype.types.TomType  t1) { return  tom.engine.adt.tomtype.types.tomtype.Type.make(t0, t1) ;}private static  tom.engine.adt.tomtype.types.TomType  tom_make_TomTypeAlone( String  t0) { return  tom.engine.adt.tomtype.types.tomtype.TomTypeAlone.make(t0) ;}private static  tom.engine.adt.tomtype.types.TomType  tom_make_ASTTomType( String  t0) { return  tom.engine.adt.tomtype.types.tomtype.ASTTomType.make(t0) ;}private static  tom.engine.adt.tomtype.types.TomType  tom_make_TLType( tom.engine.adt.tomsignature.types.TargetLanguage  t0) { return  tom.engine.adt.tomtype.types.tomtype.TLType.make(t0) ;}private static  tom.engine.adt.tomtype.types.TomType  tom_make_EmptyType() { return  tom.engine.adt.tomtype.types.tomtype.EmptyType.make() ;}private static  tom.engine.adt.tomsignature.types.TomVisit  tom_make_VisitTerm( tom.engine.adt.tomtype.types.TomType  t0,  tom.engine.adt.tominstruction.types.ConstraintInstructionList  t1,  tom.engine.adt.tomoption.types.OptionList  t2) { return  tom.engine.adt.tomsignature.types.tomvisit.VisitTerm.make(t0, t1, t2) ;}private static  tom.engine.adt.tomsignature.types.TargetLanguage  tom_make_ITL( String  t0) { return  tom.engine.adt.tomsignature.types.targetlanguage.ITL.make(t0) ;}private static  tom.engine.adt.theory.types.ElementaryTheory  tom_make_TrueAU() { return  tom.engine.adt.theory.types.elementarytheory.TrueAU.make() ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_make_TypeTermDecl( tom.engine.adt.tomname.types.TomName  t0,  tom.engine.adt.tomdeclaration.types.DeclarationList  t1,  tom.engine.adt.tomoption.types.Option  t2) { return  tom.engine.adt.tomdeclaration.types.declaration.TypeTermDecl.make(t0, t1, t2) ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_make_GetImplementationDecl( tom.engine.adt.tomterm.types.TomTerm  t0,  tom.engine.adt.tominstruction.types.Instruction  t1,  tom.engine.adt.tomoption.types.Option  t2) { return  tom.engine.adt.tomdeclaration.types.declaration.GetImplementationDecl.make(t0, t1, t2) ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_make_IsFsymDecl( tom.engine.adt.tomname.types.TomName  t0,  tom.engine.adt.tomterm.types.TomTerm  t1,  tom.engine.adt.tomexpression.types.Expression  t2,  tom.engine.adt.tomoption.types.Option  t3) { return  tom.engine.adt.tomdeclaration.types.declaration.IsFsymDecl.make(t0, t1, t2, t3) ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_make_GetSlotDecl( tom.engine.adt.tomname.types.TomName  t0,  tom.engine.adt.tomname.types.TomName  t1,  tom.engine.adt.tomterm.types.TomTerm  t2,  tom.engine.adt.tomexpression.types.Expression  t3,  tom.engine.adt.tomoption.types.Option  t4) { return  tom.engine.adt.tomdeclaration.types.declaration.GetSlotDecl.make(t0, t1, t2, t3, t4) ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_make_EqualTermDecl( tom.engine.adt.tomterm.types.TomTerm  t0,  tom.engine.adt.tomterm.types.TomTerm  t1,  tom.engine.adt.tomexpression.types.Expression  t2,  tom.engine.adt.tomoption.types.Option  t3) { return  tom.engine.adt.tomdeclaration.types.declaration.EqualTermDecl.make(t0, t1, t2, t3) ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_make_IsSortDecl( tom.engine.adt.tomterm.types.TomTerm  t0,  tom.engine.adt.tomexpression.types.Expression  t1,  tom.engine.adt.tomoption.types.Option  t2) { return  tom.engine.adt.tomdeclaration.types.declaration.IsSortDecl.make(t0, t1, t2) ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_make_GetHeadDecl( tom.engine.adt.tomname.types.TomName  t0,  tom.engine.adt.tomtype.types.TomType  t1,  tom.engine.adt.tomterm.types.TomTerm  t2,  tom.engine.adt.tomexpression.types.Expression  t3,  tom.engine.adt.tomoption.types.Option  t4) { return  tom.engine.adt.tomdeclaration.types.declaration.GetHeadDecl.make(t0, t1, t2, t3, t4) ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_make_GetTailDecl( tom.engine.adt.tomname.types.TomName  t0,  tom.engine.adt.tomterm.types.TomTerm  t1,  tom.engine.adt.tomexpression.types.Expression  t2,  tom.engine.adt.tomoption.types.Option  t3) { return  tom.engine.adt.tomdeclaration.types.declaration.GetTailDecl.make(t0, t1, t2, t3) ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_make_IsEmptyDecl( tom.engine.adt.tomname.types.TomName  t0,  tom.engine.adt.tomterm.types.TomTerm  t1,  tom.engine.adt.tomexpression.types.Expression  t2,  tom.engine.adt.tomoption.types.Option  t3) { return  tom.engine.adt.tomdeclaration.types.declaration.IsEmptyDecl.make(t0, t1, t2, t3) ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_make_MakeEmptyList( tom.engine.adt.tomname.types.TomName  t0,  tom.engine.adt.tominstruction.types.Instruction  t1,  tom.engine.adt.tomoption.types.Option  t2) { return  tom.engine.adt.tomdeclaration.types.declaration.MakeEmptyList.make(t0, t1, t2) ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_make_MakeAddList( tom.engine.adt.tomname.types.TomName  t0,  tom.engine.adt.tomterm.types.TomTerm  t1,  tom.engine.adt.tomterm.types.TomTerm  t2,  tom.engine.adt.tominstruction.types.Instruction  t3,  tom.engine.adt.tomoption.types.Option  t4) { return  tom.engine.adt.tomdeclaration.types.declaration.MakeAddList.make(t0, t1, t2, t3, t4) ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_make_GetElementDecl( tom.engine.adt.tomname.types.TomName  t0,  tom.engine.adt.tomterm.types.TomTerm  t1,  tom.engine.adt.tomterm.types.TomTerm  t2,  tom.engine.adt.tomexpression.types.Expression  t3,  tom.engine.adt.tomoption.types.Option  t4) { return  tom.engine.adt.tomdeclaration.types.declaration.GetElementDecl.make(t0, t1, t2, t3, t4) ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_make_GetSizeDecl( tom.engine.adt.tomname.types.TomName  t0,  tom.engine.adt.tomterm.types.TomTerm  t1,  tom.engine.adt.tomexpression.types.Expression  t2,  tom.engine.adt.tomoption.types.Option  t3) { return  tom.engine.adt.tomdeclaration.types.declaration.GetSizeDecl.make(t0, t1, t2, t3) ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_make_MakeEmptyArray( tom.engine.adt.tomname.types.TomName  t0,  tom.engine.adt.tomterm.types.TomTerm  t1,  tom.engine.adt.tominstruction.types.Instruction  t2,  tom.engine.adt.tomoption.types.Option  t3) { return  tom.engine.adt.tomdeclaration.types.declaration.MakeEmptyArray.make(t0, t1, t2, t3) ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_make_MakeAddArray( tom.engine.adt.tomname.types.TomName  t0,  tom.engine.adt.tomterm.types.TomTerm  t1,  tom.engine.adt.tomterm.types.TomTerm  t2,  tom.engine.adt.tominstruction.types.Instruction  t3,  tom.engine.adt.tomoption.types.Option  t4) { return  tom.engine.adt.tomdeclaration.types.declaration.MakeAddArray.make(t0, t1, t2, t3, t4) ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_make_MakeDecl( tom.engine.adt.tomname.types.TomName  t0,  tom.engine.adt.tomtype.types.TomType  t1,  tom.engine.adt.tomterm.types.TomList  t2,  tom.engine.adt.tominstruction.types.Instruction  t3,  tom.engine.adt.tomoption.types.Option  t4) { return  tom.engine.adt.tomdeclaration.types.declaration.MakeDecl.make(t0, t1, t2, t3, t4) ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_make_Strategy( tom.engine.adt.tomname.types.TomName  t0,  tom.engine.adt.tomterm.types.TomTerm  t1,  tom.engine.adt.tomsignature.types.TomVisitList  t2,  tom.engine.adt.tomoption.types.Option  t3) { return  tom.engine.adt.tomdeclaration.types.declaration.Strategy.make(t0, t1, t2, t3) ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_make_SymbolDecl( tom.engine.adt.tomname.types.TomName  t0) { return  tom.engine.adt.tomdeclaration.types.declaration.SymbolDecl.make(t0) ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_make_ListSymbolDecl( tom.engine.adt.tomname.types.TomName  t0) { return  tom.engine.adt.tomdeclaration.types.declaration.ListSymbolDecl.make(t0) ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_make_ArraySymbolDecl( tom.engine.adt.tomname.types.TomName  t0) { return  tom.engine.adt.tomdeclaration.types.declaration.ArraySymbolDecl.make(t0) ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_make_EmptyDeclaration() { return  tom.engine.adt.tomdeclaration.types.declaration.EmptyDeclaration.make() ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_make_AbstractDecl( tom.engine.adt.tomdeclaration.types.DeclarationList  t0) { return  tom.engine.adt.tomdeclaration.types.declaration.AbstractDecl.make(t0) ;}private static  tom.engine.adt.tomname.types.TomName  tom_make_Name( String  t0) { return  tom.engine.adt.tomname.types.tomname.Name.make(t0) ;}private static  tom.engine.adt.tomname.types.TomName  tom_make_EmptyName() { return  tom.engine.adt.tomname.types.tomname.EmptyName.make() ;}private static  tom.engine.adt.tomname.types.TomName  tom_make_AntiName( tom.engine.adt.tomname.types.TomName  t0) { return  tom.engine.adt.tomname.types.tomname.AntiName.make(t0) ;}private static  tom.engine.adt.tomexpression.types.Expression  tom_make_Code( String  t0) { return  tom.engine.adt.tomexpression.types.expression.Code.make(t0) ;}private static boolean tom_is_fun_sym_TermAppl( tom.engine.adt.tomterm.types.TomTerm  t) {return  (t instanceof tom.engine.adt.tomterm.types.tomterm.TermAppl) ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_make_TermAppl( tom.engine.adt.tomoption.types.OptionList  t0,  tom.engine.adt.tomname.types.TomNameList  t1,  tom.engine.adt.tomterm.types.TomList  t2,  tom.engine.adt.tomconstraint.types.ConstraintList  t3) { return  tom.engine.adt.tomterm.types.tomterm.TermAppl.make(t0, t1, t2, t3) ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_TermAppl_Option( tom.engine.adt.tomterm.types.TomTerm  t) {return  t.getOption() ;}private static  tom.engine.adt.tomname.types.TomNameList  tom_get_slot_TermAppl_NameList( tom.engine.adt.tomterm.types.TomTerm  t) {return  t.getNameList() ;}private static  tom.engine.adt.tomterm.types.TomList  tom_get_slot_TermAppl_Args( tom.engine.adt.tomterm.types.TomTerm  t) {return  t.getArgs() ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slot_TermAppl_Constraints( tom.engine.adt.tomterm.types.TomTerm  t) {return  t.getConstraints() ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_make_RecordAppl( tom.engine.adt.tomoption.types.OptionList  t0,  tom.engine.adt.tomname.types.TomNameList  t1,  tom.engine.adt.tomslot.types.SlotList  t2,  tom.engine.adt.tomconstraint.types.ConstraintList  t3) { return  tom.engine.adt.tomterm.types.tomterm.RecordAppl.make(t0, t1, t2, t3) ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_make_XMLAppl( tom.engine.adt.tomoption.types.OptionList  t0,  tom.engine.adt.tomname.types.TomNameList  t1,  tom.engine.adt.tomterm.types.TomList  t2,  tom.engine.adt.tomterm.types.TomList  t3,  tom.engine.adt.tomconstraint.types.ConstraintList  t4) { return  tom.engine.adt.tomterm.types.tomterm.XMLAppl.make(t0, t1, t2, t3, t4) ;}private static boolean tom_is_fun_sym_Variable( tom.engine.adt.tomterm.types.TomTerm  t) {return  (t instanceof tom.engine.adt.tomterm.types.tomterm.Variable) ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_make_Variable( tom.engine.adt.tomoption.types.OptionList  t0,  tom.engine.adt.tomname.types.TomName  t1,  tom.engine.adt.tomtype.types.TomType  t2,  tom.engine.adt.tomconstraint.types.ConstraintList  t3) { return  tom.engine.adt.tomterm.types.tomterm.Variable.make(t0, t1, t2, t3) ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_slot_Variable_Option( tom.engine.adt.tomterm.types.TomTerm  t) {return  t.getOption() ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_Variable_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {return  t.getAstName() ;}private static  tom.engine.adt.tomtype.types.TomType  tom_get_slot_Variable_AstType( tom.engine.adt.tomterm.types.TomTerm  t) {return  t.getAstType() ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slot_Variable_Constraints( tom.engine.adt.tomterm.types.TomTerm  t) {return  t.getConstraints() ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_make_VariableStar( tom.engine.adt.tomoption.types.OptionList  t0,  tom.engine.adt.tomname.types.TomName  t1,  tom.engine.adt.tomtype.types.TomType  t2,  tom.engine.adt.tomconstraint.types.ConstraintList  t3) { return  tom.engine.adt.tomterm.types.tomterm.VariableStar.make(t0, t1, t2, t3) ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_make_UnamedVariable( tom.engine.adt.tomoption.types.OptionList  t0,  tom.engine.adt.tomtype.types.TomType  t1,  tom.engine.adt.tomconstraint.types.ConstraintList  t2) { return  tom.engine.adt.tomterm.types.tomterm.UnamedVariable.make(t0, t1, t2) ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_make_UnamedVariableStar( tom.engine.adt.tomoption.types.OptionList  t0,  tom.engine.adt.tomtype.types.TomType  t1,  tom.engine.adt.tomconstraint.types.ConstraintList  t2) { return  tom.engine.adt.tomterm.types.tomterm.UnamedVariableStar.make(t0, t1, t2) ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_make_TargetLanguageToTomTerm( tom.engine.adt.tomsignature.types.TargetLanguage  t0) { return  tom.engine.adt.tomterm.types.tomterm.TargetLanguageToTomTerm.make(t0) ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_make_TomTypeToTomTerm( tom.engine.adt.tomtype.types.TomType  t0) { return  tom.engine.adt.tomterm.types.tomterm.TomTypeToTomTerm.make(t0) ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_make_AntiTerm( tom.engine.adt.tomterm.types.TomTerm  t0) { return  tom.engine.adt.tomterm.types.tomterm.AntiTerm.make(t0) ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_make_BuildReducedTerm( tom.engine.adt.tomterm.types.TomTerm  t0,  tom.engine.adt.tomtype.types.TomType  t1) { return  tom.engine.adt.tomterm.types.tomterm.BuildReducedTerm.make(t0, t1) ;}private static  tom.engine.adt.tomoption.types.Option  tom_make_DeclarationToOption( tom.engine.adt.tomdeclaration.types.Declaration  t0) { return  tom.engine.adt.tomoption.types.option.DeclarationToOption.make(t0) ;}private static  tom.engine.adt.tomoption.types.Option  tom_make_OriginTracking( tom.engine.adt.tomname.types.TomName  t0,  int  t1,  String  t2) { return  tom.engine.adt.tomoption.types.option.OriginTracking.make(t0, t1, t2) ;}private static  tom.engine.adt.tomoption.types.Option  tom_make_OriginalText( tom.engine.adt.tomname.types.TomName  t0) { return  tom.engine.adt.tomoption.types.option.OriginalText.make(t0) ;}private static  tom.engine.adt.tomoption.types.Option  tom_make_Constant() { return  tom.engine.adt.tomoption.types.option.Constant.make() ;}private static  tom.engine.adt.tomoption.types.Option  tom_make_MatchingTheory( tom.engine.adt.theory.types.Theory  t0) { return  tom.engine.adt.tomoption.types.option.MatchingTheory.make(t0) ;}private static  tom.engine.adt.tomoption.types.Option  tom_make_Label( tom.engine.adt.tomname.types.TomName  t0) { return  tom.engine.adt.tomoption.types.option.Label.make(t0) ;}private static  tom.engine.adt.tomoption.types.Option  tom_make_ModuleName( String  t0) { return  tom.engine.adt.tomoption.types.option.ModuleName.make(t0) ;}private static  tom.engine.adt.tomoption.types.Option  tom_make_ImplicitXMLAttribut() { return  tom.engine.adt.tomoption.types.option.ImplicitXMLAttribut.make() ;}private static  tom.engine.adt.tomoption.types.Option  tom_make_ImplicitXMLChild() { return  tom.engine.adt.tomoption.types.option.ImplicitXMLChild.make() ;}private static  tom.engine.adt.tomconstraint.types.NumericConstraintType  tom_make_NumLessThan() { return  tom.engine.adt.tomconstraint.types.numericconstrainttype.NumLessThan.make() ;}private static  tom.engine.adt.tomconstraint.types.NumericConstraintType  tom_make_NumLessOrEqualThan() { return  tom.engine.adt.tomconstraint.types.numericconstrainttype.NumLessOrEqualThan.make() ;}private static  tom.engine.adt.tomconstraint.types.NumericConstraintType  tom_make_NumGreaterThan() { return  tom.engine.adt.tomconstraint.types.numericconstrainttype.NumGreaterThan.make() ;}private static  tom.engine.adt.tomconstraint.types.NumericConstraintType  tom_make_NumGreaterOrEqualThan() { return  tom.engine.adt.tomconstraint.types.numericconstrainttype.NumGreaterOrEqualThan.make() ;}private static  tom.engine.adt.tomconstraint.types.NumericConstraintType  tom_make_NumDifferent() { return  tom.engine.adt.tomconstraint.types.numericconstrainttype.NumDifferent.make() ;}private static  tom.engine.adt.tomconstraint.types.NumericConstraintType  tom_make_NumEqual() { return  tom.engine.adt.tomconstraint.types.numericconstrainttype.NumEqual.make() ;}private static  tom.engine.adt.tomconstraint.types.Constraint  tom_make_TrueConstraint() { return  tom.engine.adt.tomconstraint.types.constraint.TrueConstraint.make() ;}private static  tom.engine.adt.tomconstraint.types.Constraint  tom_make_MatchConstraint( tom.engine.adt.tomterm.types.TomTerm  t0,  tom.engine.adt.tomterm.types.TomTerm  t1) { return  tom.engine.adt.tomconstraint.types.constraint.MatchConstraint.make(t0, t1) ;}private static  tom.engine.adt.tomconstraint.types.Constraint  tom_make_NumericConstraint( tom.engine.adt.tomterm.types.TomTerm  t0,  tom.engine.adt.tomterm.types.TomTerm  t1,  tom.engine.adt.tomconstraint.types.NumericConstraintType  t2) { return  tom.engine.adt.tomconstraint.types.constraint.NumericConstraint.make(t0, t1, t2) ;}private static boolean tom_is_fun_sym_PairNameDecl( tom.engine.adt.tomslot.types.PairNameDecl  t) {return  (t instanceof tom.engine.adt.tomslot.types.pairnamedecl.PairNameDecl) ;}private static  tom.engine.adt.tomslot.types.PairNameDecl  tom_make_PairNameDecl( tom.engine.adt.tomname.types.TomName  t0,  tom.engine.adt.tomdeclaration.types.Declaration  t1) { return  tom.engine.adt.tomslot.types.pairnamedecl.PairNameDecl.make(t0, t1) ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_PairNameDecl_SlotName( tom.engine.adt.tomslot.types.PairNameDecl  t) {return  t.getSlotName() ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_get_slot_PairNameDecl_SlotDecl( tom.engine.adt.tomslot.types.PairNameDecl  t) {return  t.getSlotDecl() ;}private static  tom.engine.adt.tomslot.types.Slot  tom_make_PairSlotAppl( tom.engine.adt.tomname.types.TomName  t0,  tom.engine.adt.tomterm.types.TomTerm  t1) { return  tom.engine.adt.tomslot.types.slot.PairSlotAppl.make(t0, t1) ;}private static boolean tom_is_fun_sym_concTomType( tom.engine.adt.tomtype.types.TomTypeList  t) {return  ((t instanceof tom.engine.adt.tomtype.types.tomtypelist.ConsconcTomType) || (t instanceof tom.engine.adt.tomtype.types.tomtypelist.EmptyconcTomType)) ;}private static  tom.engine.adt.tomtype.types.TomTypeList  tom_empty_list_concTomType() { return  tom.engine.adt.tomtype.types.tomtypelist.EmptyconcTomType.make() ;}private static  tom.engine.adt.tomtype.types.TomTypeList  tom_cons_list_concTomType( tom.engine.adt.tomtype.types.TomType  e,  tom.engine.adt.tomtype.types.TomTypeList  l) { return  tom.engine.adt.tomtype.types.tomtypelist.ConsconcTomType.make(e,l) ;}private static  tom.engine.adt.tomtype.types.TomType  tom_get_head_concTomType_TomTypeList( tom.engine.adt.tomtype.types.TomTypeList  l) {return  l.getHeadconcTomType() ;}private static  tom.engine.adt.tomtype.types.TomTypeList  tom_get_tail_concTomType_TomTypeList( tom.engine.adt.tomtype.types.TomTypeList  l) {return  l.getTailconcTomType() ;}private static boolean tom_is_empty_concTomType_TomTypeList( tom.engine.adt.tomtype.types.TomTypeList  l) {return  l.isEmptyconcTomType() ;}   private static   tom.engine.adt.tomtype.types.TomTypeList  tom_append_list_concTomType( tom.engine.adt.tomtype.types.TomTypeList l1,  tom.engine.adt.tomtype.types.TomTypeList  l2) {     if( l1.isEmptyconcTomType() ) {       return l2;     } else if( l2.isEmptyconcTomType() ) {       return l1;     } else if(  l1.getTailconcTomType() .isEmptyconcTomType() ) {       return  tom.engine.adt.tomtype.types.tomtypelist.ConsconcTomType.make( l1.getHeadconcTomType() ,l2) ;     } else {       return  tom.engine.adt.tomtype.types.tomtypelist.ConsconcTomType.make( l1.getHeadconcTomType() ,tom_append_list_concTomType( l1.getTailconcTomType() ,l2)) ;     }   }   private static   tom.engine.adt.tomtype.types.TomTypeList  tom_get_slice_concTomType( tom.engine.adt.tomtype.types.TomTypeList  begin,  tom.engine.adt.tomtype.types.TomTypeList  end, tom.engine.adt.tomtype.types.TomTypeList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTomType()  ||  (end==tom_empty_list_concTomType()) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomtype.types.tomtypelist.ConsconcTomType.make( begin.getHeadconcTomType() ,( tom.engine.adt.tomtype.types.TomTypeList )tom_get_slice_concTomType( begin.getTailconcTomType() ,end,tail)) ;   }   private static boolean tom_is_fun_sym_concTomVisit( tom.engine.adt.tomsignature.types.TomVisitList  t) {return  ((t instanceof tom.engine.adt.tomsignature.types.tomvisitlist.ConsconcTomVisit) || (t instanceof tom.engine.adt.tomsignature.types.tomvisitlist.EmptyconcTomVisit)) ;}private static  tom.engine.adt.tomsignature.types.TomVisitList  tom_empty_list_concTomVisit() { return  tom.engine.adt.tomsignature.types.tomvisitlist.EmptyconcTomVisit.make() ;}private static  tom.engine.adt.tomsignature.types.TomVisitList  tom_cons_list_concTomVisit( tom.engine.adt.tomsignature.types.TomVisit  e,  tom.engine.adt.tomsignature.types.TomVisitList  l) { return  tom.engine.adt.tomsignature.types.tomvisitlist.ConsconcTomVisit.make(e,l) ;}private static  tom.engine.adt.tomsignature.types.TomVisit  tom_get_head_concTomVisit_TomVisitList( tom.engine.adt.tomsignature.types.TomVisitList  l) {return  l.getHeadconcTomVisit() ;}private static  tom.engine.adt.tomsignature.types.TomVisitList  tom_get_tail_concTomVisit_TomVisitList( tom.engine.adt.tomsignature.types.TomVisitList  l) {return  l.getTailconcTomVisit() ;}private static boolean tom_is_empty_concTomVisit_TomVisitList( tom.engine.adt.tomsignature.types.TomVisitList  l) {return  l.isEmptyconcTomVisit() ;}   private static   tom.engine.adt.tomsignature.types.TomVisitList  tom_append_list_concTomVisit( tom.engine.adt.tomsignature.types.TomVisitList l1,  tom.engine.adt.tomsignature.types.TomVisitList  l2) {     if( l1.isEmptyconcTomVisit() ) {       return l2;     } else if( l2.isEmptyconcTomVisit() ) {       return l1;     } else if(  l1.getTailconcTomVisit() .isEmptyconcTomVisit() ) {       return  tom.engine.adt.tomsignature.types.tomvisitlist.ConsconcTomVisit.make( l1.getHeadconcTomVisit() ,l2) ;     } else {       return  tom.engine.adt.tomsignature.types.tomvisitlist.ConsconcTomVisit.make( l1.getHeadconcTomVisit() ,tom_append_list_concTomVisit( l1.getTailconcTomVisit() ,l2)) ;     }   }   private static   tom.engine.adt.tomsignature.types.TomVisitList  tom_get_slice_concTomVisit( tom.engine.adt.tomsignature.types.TomVisitList  begin,  tom.engine.adt.tomsignature.types.TomVisitList  end, tom.engine.adt.tomsignature.types.TomVisitList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTomVisit()  ||  (end==tom_empty_list_concTomVisit()) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomsignature.types.tomvisitlist.ConsconcTomVisit.make( begin.getHeadconcTomVisit() ,( tom.engine.adt.tomsignature.types.TomVisitList )tom_get_slice_concTomVisit( begin.getTailconcTomVisit() ,end,tail)) ;   }   private static boolean tom_is_fun_sym_concElementaryTheory( tom.engine.adt.theory.types.Theory  t) {return  ((t instanceof tom.engine.adt.theory.types.theory.ConsconcElementaryTheory) || (t instanceof tom.engine.adt.theory.types.theory.EmptyconcElementaryTheory)) ;}private static  tom.engine.adt.theory.types.Theory  tom_empty_list_concElementaryTheory() { return  tom.engine.adt.theory.types.theory.EmptyconcElementaryTheory.make() ;}private static  tom.engine.adt.theory.types.Theory  tom_cons_list_concElementaryTheory( tom.engine.adt.theory.types.ElementaryTheory  e,  tom.engine.adt.theory.types.Theory  l) { return  tom.engine.adt.theory.types.theory.ConsconcElementaryTheory.make(e,l) ;}private static  tom.engine.adt.theory.types.ElementaryTheory  tom_get_head_concElementaryTheory_Theory( tom.engine.adt.theory.types.Theory  l) {return  l.getHeadconcElementaryTheory() ;}private static  tom.engine.adt.theory.types.Theory  tom_get_tail_concElementaryTheory_Theory( tom.engine.adt.theory.types.Theory  l) {return  l.getTailconcElementaryTheory() ;}private static boolean tom_is_empty_concElementaryTheory_Theory( tom.engine.adt.theory.types.Theory  l) {return  l.isEmptyconcElementaryTheory() ;}   private static   tom.engine.adt.theory.types.Theory  tom_append_list_concElementaryTheory( tom.engine.adt.theory.types.Theory l1,  tom.engine.adt.theory.types.Theory  l2) {     if( l1.isEmptyconcElementaryTheory() ) {       return l2;     } else if( l2.isEmptyconcElementaryTheory() ) {       return l1;     } else if(  l1.getTailconcElementaryTheory() .isEmptyconcElementaryTheory() ) {       return  tom.engine.adt.theory.types.theory.ConsconcElementaryTheory.make( l1.getHeadconcElementaryTheory() ,l2) ;     } else {       return  tom.engine.adt.theory.types.theory.ConsconcElementaryTheory.make( l1.getHeadconcElementaryTheory() ,tom_append_list_concElementaryTheory( l1.getTailconcElementaryTheory() ,l2)) ;     }   }   private static   tom.engine.adt.theory.types.Theory  tom_get_slice_concElementaryTheory( tom.engine.adt.theory.types.Theory  begin,  tom.engine.adt.theory.types.Theory  end, tom.engine.adt.theory.types.Theory  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcElementaryTheory()  ||  (end==tom_empty_list_concElementaryTheory()) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.theory.types.theory.ConsconcElementaryTheory.make( begin.getHeadconcElementaryTheory() ,( tom.engine.adt.theory.types.Theory )tom_get_slice_concElementaryTheory( begin.getTailconcElementaryTheory() ,end,tail)) ;   }   private static boolean tom_is_fun_sym_concDeclaration( tom.engine.adt.tomdeclaration.types.DeclarationList  t) {return  ((t instanceof tom.engine.adt.tomdeclaration.types.declarationlist.ConsconcDeclaration) || (t instanceof tom.engine.adt.tomdeclaration.types.declarationlist.EmptyconcDeclaration)) ;}private static  tom.engine.adt.tomdeclaration.types.DeclarationList  tom_empty_list_concDeclaration() { return  tom.engine.adt.tomdeclaration.types.declarationlist.EmptyconcDeclaration.make() ;}private static  tom.engine.adt.tomdeclaration.types.DeclarationList  tom_cons_list_concDeclaration( tom.engine.adt.tomdeclaration.types.Declaration  e,  tom.engine.adt.tomdeclaration.types.DeclarationList  l) { return  tom.engine.adt.tomdeclaration.types.declarationlist.ConsconcDeclaration.make(e,l) ;}private static  tom.engine.adt.tomdeclaration.types.Declaration  tom_get_head_concDeclaration_DeclarationList( tom.engine.adt.tomdeclaration.types.DeclarationList  l) {return  l.getHeadconcDeclaration() ;}private static  tom.engine.adt.tomdeclaration.types.DeclarationList  tom_get_tail_concDeclaration_DeclarationList( tom.engine.adt.tomdeclaration.types.DeclarationList  l) {return  l.getTailconcDeclaration() ;}private static boolean tom_is_empty_concDeclaration_DeclarationList( tom.engine.adt.tomdeclaration.types.DeclarationList  l) {return  l.isEmptyconcDeclaration() ;}   private static   tom.engine.adt.tomdeclaration.types.DeclarationList  tom_append_list_concDeclaration( tom.engine.adt.tomdeclaration.types.DeclarationList l1,  tom.engine.adt.tomdeclaration.types.DeclarationList  l2) {     if( l1.isEmptyconcDeclaration() ) {       return l2;     } else if( l2.isEmptyconcDeclaration() ) {       return l1;     } else if(  l1.getTailconcDeclaration() .isEmptyconcDeclaration() ) {       return  tom.engine.adt.tomdeclaration.types.declarationlist.ConsconcDeclaration.make( l1.getHeadconcDeclaration() ,l2) ;     } else {       return  tom.engine.adt.tomdeclaration.types.declarationlist.ConsconcDeclaration.make( l1.getHeadconcDeclaration() ,tom_append_list_concDeclaration( l1.getTailconcDeclaration() ,l2)) ;     }   }   private static   tom.engine.adt.tomdeclaration.types.DeclarationList  tom_get_slice_concDeclaration( tom.engine.adt.tomdeclaration.types.DeclarationList  begin,  tom.engine.adt.tomdeclaration.types.DeclarationList  end, tom.engine.adt.tomdeclaration.types.DeclarationList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcDeclaration()  ||  (end==tom_empty_list_concDeclaration()) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomdeclaration.types.declarationlist.ConsconcDeclaration.make( begin.getHeadconcDeclaration() ,( tom.engine.adt.tomdeclaration.types.DeclarationList )tom_get_slice_concDeclaration( begin.getTailconcDeclaration() ,end,tail)) ;   }   private static boolean tom_is_fun_sym_concTomName( tom.engine.adt.tomname.types.TomNameList  t) {return  ((t instanceof tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName) || (t instanceof tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName)) ;}private static  tom.engine.adt.tomname.types.TomNameList  tom_empty_list_concTomName() { return  tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName.make() ;}private static  tom.engine.adt.tomname.types.TomNameList  tom_cons_list_concTomName( tom.engine.adt.tomname.types.TomName  e,  tom.engine.adt.tomname.types.TomNameList  l) { return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make(e,l) ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_head_concTomName_TomNameList( tom.engine.adt.tomname.types.TomNameList  l) {return  l.getHeadconcTomName() ;}private static  tom.engine.adt.tomname.types.TomNameList  tom_get_tail_concTomName_TomNameList( tom.engine.adt.tomname.types.TomNameList  l) {return  l.getTailconcTomName() ;}private static boolean tom_is_empty_concTomName_TomNameList( tom.engine.adt.tomname.types.TomNameList  l) {return  l.isEmptyconcTomName() ;}   private static   tom.engine.adt.tomname.types.TomNameList  tom_append_list_concTomName( tom.engine.adt.tomname.types.TomNameList l1,  tom.engine.adt.tomname.types.TomNameList  l2) {     if( l1.isEmptyconcTomName() ) {       return l2;     } else if( l2.isEmptyconcTomName() ) {       return l1;     } else if(  l1.getTailconcTomName() .isEmptyconcTomName() ) {       return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( l1.getHeadconcTomName() ,l2) ;     } else {       return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( l1.getHeadconcTomName() ,tom_append_list_concTomName( l1.getTailconcTomName() ,l2)) ;     }   }   private static   tom.engine.adt.tomname.types.TomNameList  tom_get_slice_concTomName( tom.engine.adt.tomname.types.TomNameList  begin,  tom.engine.adt.tomname.types.TomNameList  end, tom.engine.adt.tomname.types.TomNameList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTomName()  ||  (end==tom_empty_list_concTomName()) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( begin.getHeadconcTomName() ,( tom.engine.adt.tomname.types.TomNameList )tom_get_slice_concTomName( begin.getTailconcTomName() ,end,tail)) ;   }   private static boolean tom_is_fun_sym_concTomTerm( tom.engine.adt.tomterm.types.TomList  t) {return  ((t instanceof tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm) || (t instanceof tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm)) ;}private static  tom.engine.adt.tomterm.types.TomList  tom_empty_list_concTomTerm() { return  tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() ;}private static  tom.engine.adt.tomterm.types.TomList  tom_cons_list_concTomTerm( tom.engine.adt.tomterm.types.TomTerm  e,  tom.engine.adt.tomterm.types.TomList  l) { return  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make(e,l) ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_head_concTomTerm_TomList( tom.engine.adt.tomterm.types.TomList  l) {return  l.getHeadconcTomTerm() ;}private static  tom.engine.adt.tomterm.types.TomList  tom_get_tail_concTomTerm_TomList( tom.engine.adt.tomterm.types.TomList  l) {return  l.getTailconcTomTerm() ;}private static boolean tom_is_empty_concTomTerm_TomList( tom.engine.adt.tomterm.types.TomList  l) {return  l.isEmptyconcTomTerm() ;}   private static   tom.engine.adt.tomterm.types.TomList  tom_append_list_concTomTerm( tom.engine.adt.tomterm.types.TomList l1,  tom.engine.adt.tomterm.types.TomList  l2) {     if( l1.isEmptyconcTomTerm() ) {       return l2;     } else if( l2.isEmptyconcTomTerm() ) {       return l1;     } else if(  l1.getTailconcTomTerm() .isEmptyconcTomTerm() ) {       return  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( l1.getHeadconcTomTerm() ,l2) ;     } else {       return  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( l1.getHeadconcTomTerm() ,tom_append_list_concTomTerm( l1.getTailconcTomTerm() ,l2)) ;     }   }   private static   tom.engine.adt.tomterm.types.TomList  tom_get_slice_concTomTerm( tom.engine.adt.tomterm.types.TomList  begin,  tom.engine.adt.tomterm.types.TomList  end, tom.engine.adt.tomterm.types.TomList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTomTerm()  ||  (end==tom_empty_list_concTomTerm()) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( begin.getHeadconcTomTerm() ,( tom.engine.adt.tomterm.types.TomList )tom_get_slice_concTomTerm( begin.getTailconcTomTerm() ,end,tail)) ;   }   private static boolean tom_is_fun_sym_concOption( tom.engine.adt.tomoption.types.OptionList  t) {return  ((t instanceof tom.engine.adt.tomoption.types.optionlist.ConsconcOption) || (t instanceof tom.engine.adt.tomoption.types.optionlist.EmptyconcOption)) ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_empty_list_concOption() { return  tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_cons_list_concOption( tom.engine.adt.tomoption.types.Option  e,  tom.engine.adt.tomoption.types.OptionList  l) { return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make(e,l) ;}private static  tom.engine.adt.tomoption.types.Option  tom_get_head_concOption_OptionList( tom.engine.adt.tomoption.types.OptionList  l) {return  l.getHeadconcOption() ;}private static  tom.engine.adt.tomoption.types.OptionList  tom_get_tail_concOption_OptionList( tom.engine.adt.tomoption.types.OptionList  l) {return  l.getTailconcOption() ;}private static boolean tom_is_empty_concOption_OptionList( tom.engine.adt.tomoption.types.OptionList  l) {return  l.isEmptyconcOption() ;}   private static   tom.engine.adt.tomoption.types.OptionList  tom_append_list_concOption( tom.engine.adt.tomoption.types.OptionList l1,  tom.engine.adt.tomoption.types.OptionList  l2) {     if( l1.isEmptyconcOption() ) {       return l2;     } else if( l2.isEmptyconcOption() ) {       return l1;     } else if(  l1.getTailconcOption() .isEmptyconcOption() ) {       return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( l1.getHeadconcOption() ,l2) ;     } else {       return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( l1.getHeadconcOption() ,tom_append_list_concOption( l1.getTailconcOption() ,l2)) ;     }   }   private static   tom.engine.adt.tomoption.types.OptionList  tom_get_slice_concOption( tom.engine.adt.tomoption.types.OptionList  begin,  tom.engine.adt.tomoption.types.OptionList  end, tom.engine.adt.tomoption.types.OptionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcOption()  ||  (end==tom_empty_list_concOption()) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( begin.getHeadconcOption() ,( tom.engine.adt.tomoption.types.OptionList )tom_get_slice_concOption( begin.getTailconcOption() ,end,tail)) ;   }   private static boolean tom_is_fun_sym_AndConstraint( tom.engine.adt.tomconstraint.types.Constraint  t) {return  ((t instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (t instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) ;}private static  tom.engine.adt.tomconstraint.types.Constraint  tom_empty_list_AndConstraint() { return  tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ;}private static  tom.engine.adt.tomconstraint.types.Constraint  tom_cons_list_AndConstraint( tom.engine.adt.tomconstraint.types.Constraint  e,  tom.engine.adt.tomconstraint.types.Constraint  l) { return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make(e,l) ;}private static  tom.engine.adt.tomconstraint.types.Constraint  tom_get_head_AndConstraint_Constraint( tom.engine.adt.tomconstraint.types.Constraint  l) {return  l.getHeadAndConstraint() ;}private static  tom.engine.adt.tomconstraint.types.Constraint  tom_get_tail_AndConstraint_Constraint( tom.engine.adt.tomconstraint.types.Constraint  l) {return  l.getTailAndConstraint() ;}private static boolean tom_is_empty_AndConstraint_Constraint( tom.engine.adt.tomconstraint.types.Constraint  l) {return  l.isEmptyAndConstraint() ;}   private static   tom.engine.adt.tomconstraint.types.Constraint  tom_append_list_AndConstraint( tom.engine.adt.tomconstraint.types.Constraint  l1,  tom.engine.adt.tomconstraint.types.Constraint  l2) {     if( l1.isEmptyAndConstraint() ) {       return l2;     } else if( l2.isEmptyAndConstraint() ) {       return l1;     } else if( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) ) {       if( (( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getTailAndConstraint() :tom_empty_list_AndConstraint()).isEmptyAndConstraint() ) {         return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getHeadAndConstraint() :l1),l2) ;       } else {         return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getHeadAndConstraint() :l1),tom_append_list_AndConstraint((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? l1.getTailAndConstraint() :tom_empty_list_AndConstraint()),l2)) ;       }     } else {       return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make(l1,l2) ;     }   }   private static   tom.engine.adt.tomconstraint.types.Constraint  tom_get_slice_AndConstraint( tom.engine.adt.tomconstraint.types.Constraint  begin,  tom.engine.adt.tomconstraint.types.Constraint  end, tom.engine.adt.tomconstraint.types.Constraint  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyAndConstraint()  ||  (end==tom_empty_list_AndConstraint()) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? begin.getHeadAndConstraint() :begin),( tom.engine.adt.tomconstraint.types.Constraint )tom_get_slice_AndConstraint((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? begin.getTailAndConstraint() :tom_empty_list_AndConstraint()),end,tail)) ;   }   private static boolean tom_is_fun_sym_OrConstraint( tom.engine.adt.tomconstraint.types.Constraint  t) {return  ((t instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint) || (t instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint)) ;}private static  tom.engine.adt.tomconstraint.types.Constraint  tom_empty_list_OrConstraint() { return  tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint.make() ;}private static  tom.engine.adt.tomconstraint.types.Constraint  tom_cons_list_OrConstraint( tom.engine.adt.tomconstraint.types.Constraint  e,  tom.engine.adt.tomconstraint.types.Constraint  l) { return  tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint.make(e,l) ;}private static  tom.engine.adt.tomconstraint.types.Constraint  tom_get_head_OrConstraint_Constraint( tom.engine.adt.tomconstraint.types.Constraint  l) {return  l.getHeadOrConstraint() ;}private static  tom.engine.adt.tomconstraint.types.Constraint  tom_get_tail_OrConstraint_Constraint( tom.engine.adt.tomconstraint.types.Constraint  l) {return  l.getTailOrConstraint() ;}private static boolean tom_is_empty_OrConstraint_Constraint( tom.engine.adt.tomconstraint.types.Constraint  l) {return  l.isEmptyOrConstraint() ;}   private static   tom.engine.adt.tomconstraint.types.Constraint  tom_append_list_OrConstraint( tom.engine.adt.tomconstraint.types.Constraint  l1,  tom.engine.adt.tomconstraint.types.Constraint  l2) {     if( l1.isEmptyOrConstraint() ) {       return l2;     } else if( l2.isEmptyOrConstraint() ) {       return l1;     } else if( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint)) ) {       if( (( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint)) )? l1.getTailOrConstraint() :tom_empty_list_OrConstraint()).isEmptyOrConstraint() ) {         return  tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint.make((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint)) )? l1.getHeadOrConstraint() :l1),l2) ;       } else {         return  tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint.make((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint)) )? l1.getHeadOrConstraint() :l1),tom_append_list_OrConstraint((( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint)) )? l1.getTailOrConstraint() :tom_empty_list_OrConstraint()),l2)) ;       }     } else {       return  tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint.make(l1,l2) ;     }   }   private static   tom.engine.adt.tomconstraint.types.Constraint  tom_get_slice_OrConstraint( tom.engine.adt.tomconstraint.types.Constraint  begin,  tom.engine.adt.tomconstraint.types.Constraint  end, tom.engine.adt.tomconstraint.types.Constraint  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyOrConstraint()  ||  (end==tom_empty_list_OrConstraint()) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint.make((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint)) )? begin.getHeadOrConstraint() :begin),( tom.engine.adt.tomconstraint.types.Constraint )tom_get_slice_OrConstraint((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint)) )? begin.getTailOrConstraint() :tom_empty_list_OrConstraint()),end,tail)) ;   }   private static boolean tom_is_fun_sym_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList  t) {return  ((t instanceof tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint) || (t instanceof tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint)) ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_empty_list_concConstraint() { return  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_cons_list_concConstraint( tom.engine.adt.tomconstraint.types.Constraint  e,  tom.engine.adt.tomconstraint.types.ConstraintList  l) { return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make(e,l) ;}private static  tom.engine.adt.tomconstraint.types.Constraint  tom_get_head_concConstraint_ConstraintList( tom.engine.adt.tomconstraint.types.ConstraintList  l) {return  l.getHeadconcConstraint() ;}private static  tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_tail_concConstraint_ConstraintList( tom.engine.adt.tomconstraint.types.ConstraintList  l) {return  l.getTailconcConstraint() ;}private static boolean tom_is_empty_concConstraint_ConstraintList( tom.engine.adt.tomconstraint.types.ConstraintList  l) {return  l.isEmptyconcConstraint() ;}   private static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_append_list_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList l1,  tom.engine.adt.tomconstraint.types.ConstraintList  l2) {     if( l1.isEmptyconcConstraint() ) {       return l2;     } else if( l2.isEmptyconcConstraint() ) {       return l1;     } else if(  l1.getTailconcConstraint() .isEmptyconcConstraint() ) {       return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,l2) ;     } else {       return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,tom_append_list_concConstraint( l1.getTailconcConstraint() ,l2)) ;     }   }   private static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slice_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList  begin,  tom.engine.adt.tomconstraint.types.ConstraintList  end, tom.engine.adt.tomconstraint.types.ConstraintList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcConstraint()  ||  (end==tom_empty_list_concConstraint()) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( begin.getHeadconcConstraint() ,( tom.engine.adt.tomconstraint.types.ConstraintList )tom_get_slice_concConstraint( begin.getTailconcConstraint() ,end,tail)) ;   }   private static boolean tom_is_fun_sym_concPairNameDecl( tom.engine.adt.tomslot.types.PairNameDeclList  t) {return  ((t instanceof tom.engine.adt.tomslot.types.pairnamedecllist.ConsconcPairNameDecl) || (t instanceof tom.engine.adt.tomslot.types.pairnamedecllist.EmptyconcPairNameDecl)) ;}private static  tom.engine.adt.tomslot.types.PairNameDeclList  tom_empty_list_concPairNameDecl() { return  tom.engine.adt.tomslot.types.pairnamedecllist.EmptyconcPairNameDecl.make() ;}private static  tom.engine.adt.tomslot.types.PairNameDeclList  tom_cons_list_concPairNameDecl( tom.engine.adt.tomslot.types.PairNameDecl  e,  tom.engine.adt.tomslot.types.PairNameDeclList  l) { return  tom.engine.adt.tomslot.types.pairnamedecllist.ConsconcPairNameDecl.make(e,l) ;}private static  tom.engine.adt.tomslot.types.PairNameDecl  tom_get_head_concPairNameDecl_PairNameDeclList( tom.engine.adt.tomslot.types.PairNameDeclList  l) {return  l.getHeadconcPairNameDecl() ;}private static  tom.engine.adt.tomslot.types.PairNameDeclList  tom_get_tail_concPairNameDecl_PairNameDeclList( tom.engine.adt.tomslot.types.PairNameDeclList  l) {return  l.getTailconcPairNameDecl() ;}private static boolean tom_is_empty_concPairNameDecl_PairNameDeclList( tom.engine.adt.tomslot.types.PairNameDeclList  l) {return  l.isEmptyconcPairNameDecl() ;}   private static   tom.engine.adt.tomslot.types.PairNameDeclList  tom_append_list_concPairNameDecl( tom.engine.adt.tomslot.types.PairNameDeclList l1,  tom.engine.adt.tomslot.types.PairNameDeclList  l2) {     if( l1.isEmptyconcPairNameDecl() ) {       return l2;     } else if( l2.isEmptyconcPairNameDecl() ) {       return l1;     } else if(  l1.getTailconcPairNameDecl() .isEmptyconcPairNameDecl() ) {       return  tom.engine.adt.tomslot.types.pairnamedecllist.ConsconcPairNameDecl.make( l1.getHeadconcPairNameDecl() ,l2) ;     } else {       return  tom.engine.adt.tomslot.types.pairnamedecllist.ConsconcPairNameDecl.make( l1.getHeadconcPairNameDecl() ,tom_append_list_concPairNameDecl( l1.getTailconcPairNameDecl() ,l2)) ;     }   }   private static   tom.engine.adt.tomslot.types.PairNameDeclList  tom_get_slice_concPairNameDecl( tom.engine.adt.tomslot.types.PairNameDeclList  begin,  tom.engine.adt.tomslot.types.PairNameDeclList  end, tom.engine.adt.tomslot.types.PairNameDeclList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcPairNameDecl()  ||  (end==tom_empty_list_concPairNameDecl()) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomslot.types.pairnamedecllist.ConsconcPairNameDecl.make( begin.getHeadconcPairNameDecl() ,( tom.engine.adt.tomslot.types.PairNameDeclList )tom_get_slice_concPairNameDecl( begin.getTailconcPairNameDecl() ,end,tail)) ;   }    
    //--------------------------
        
    public String currentFile(){
        return targetparser.getCurrentFile();
    }

    // the default-mode parser
    private HostParser targetparser;
    protected BackQuoteParser bqparser;
    private TomLexer tomlexer;

    //store information for the OriginalText contained in the OptionList
    private StringBuilder text = new StringBuilder();
    
    private int lastLine; 

    private SymbolTable symbolTable;

    public TomParser(ParserSharedInputState state, HostParser target,
                     OptionManager optionManager){
        this(state);
        this.targetparser = target;
        this.bqparser = new BackQuoteParser(state,this);
        this.tomlexer = (TomLexer) selector().getStream("tomlexer");
        this.symbolTable = target.getSymbolTable();
    }
    
    private void putType(String name, TomType type) {
        symbolTable.putType(name,type);
    }

    private void putSymbol(String name, TomSymbol symbol) {
        symbolTable.putSymbol(name,symbol);
    }
    
    private int getLine(){
        return tomlexer.getLine();
    }

    public void updatePosition(int i, int j){
        targetparser.updatePosition(i,j);
    }
    
    public void addTargetCode(Token t){
        targetparser.addTargetCode(t);
    }

    private void setLastLine(int line){
        lastLine = line;
    }

    private void clearText(){
        text.delete(0,text.length());
    }

    protected TokenStreamSelector selector(){
        return targetparser.getSelector();
    }
    
    private Logger getLogger() {
      return Logger.getLogger(getClass().getName());
    }


protected TomParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public TomParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected TomParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public TomParser(TokenStream lexer) {
  this(lexer,1);
}

public TomParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
}

	public final Token  constant() throws RecognitionException, TokenStreamException {
		Token result;
		
		Token  t1 = null;
		Token  t2 = null;
		Token  t3 = null;
		Token  t4 = null;
		Token  t5 = null;
		Token  t6 = null;
		
		result = null;
		
		
		{
		switch ( LA(1)) {
		case NUM_INT:
		{
			t1 = LT(1);
			match(NUM_INT);
			if ( inputState.guessing==0 ) {
				result = t1;
			}
			break;
		}
		case CHARACTER:
		{
			t2 = LT(1);
			match(CHARACTER);
			if ( inputState.guessing==0 ) {
				result = t2;
			}
			break;
		}
		case STRING:
		{
			t3 = LT(1);
			match(STRING);
			if ( inputState.guessing==0 ) {
				result = t3;
			}
			break;
		}
		case NUM_FLOAT:
		{
			t4 = LT(1);
			match(NUM_FLOAT);
			if ( inputState.guessing==0 ) {
				result = t4;
			}
			break;
		}
		case NUM_LONG:
		{
			t5 = LT(1);
			match(NUM_LONG);
			if ( inputState.guessing==0 ) {
				result = t5;
			}
			break;
		}
		case NUM_DOUBLE:
		{
			t6 = LT(1);
			match(NUM_DOUBLE);
			if ( inputState.guessing==0 ) {
				result = t6;
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		return result;
	}
	
	public final Instruction  matchConstruct(
		Option ot
	) throws RecognitionException, TokenStreamException, TomException {
		Instruction result;
		
		Token  t1 = null;
		Token  t2 = null;
		
		result = null;
		OptionList optionList = tom_cons_list_concOption(ot,tom_cons_list_concOption(tom_make_ModuleName(TomBase.DEFAULT_MODULE_NAME),tom_empty_list_concOption()));
		List<TomTerm> argumentList = new LinkedList<TomTerm>();
		List<ConstraintInstruction> constraintInstructionList = new LinkedList<ConstraintInstruction>();
		TomList subjectList = null;
		TomType patternType = SymbolTable.TYPE_UNKNOWN;
		
		
		{
		switch ( LA(1)) {
		case LPAREN:
		{
			match(LPAREN);
			matchArguments(argumentList);
			match(RPAREN);
			match(LBRACE);
			if ( inputState.guessing==0 ) {
				subjectList = ASTFactory.makeList(argumentList);
			}
			{
			_loop6:
			do {
				if ((_tokenSet_0.member(LA(1)))) {
					patternInstruction(subjectList,constraintInstructionList,patternType);
				}
				else {
					break _loop6;
				}
				
			} while (true);
			}
			t1 = LT(1);
			match(RBRACE);
			if ( inputState.guessing==0 ) {
				
				result = tom_make_Match(ASTFactory.makeConstraintInstructionList(constraintInstructionList),optionList);
				// update for new target block...
				updatePosition(t1.getLine(),t1.getColumn());
				// Match is finished : pop the tomlexer and return in the target parser.  
				selector().pop(); 
				
			}
			break;
		}
		case LBRACE:
		{
			match(LBRACE);
			if ( inputState.guessing==0 ) {
				subjectList = ASTFactory.makeList(argumentList);
			}
			{
			_loop8:
			do {
				if ((_tokenSet_0.member(LA(1)))) {
					constraintInstruction(constraintInstructionList,patternType);
				}
				else {
					break _loop8;
				}
				
			} while (true);
			}
			t2 = LT(1);
			match(RBRACE);
			if ( inputState.guessing==0 ) {
				
				result = tom_make_Match(ASTFactory.makeConstraintInstructionList(constraintInstructionList),optionList);
				// update for new target block...
				updatePosition(t2.getLine(),t2.getColumn());
				// Match is finished : pop the tomlexer and return in the target parser.  
				selector().pop(); 
				
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		return result;
	}
	
	public final void matchArguments(
		List<TomTerm> list
	) throws RecognitionException, TokenStreamException, TomException {
		
		
		{
		matchArgument(list);
		{
		_loop12:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				matchArgument(list);
			}
			else {
				break _loop12;
			}
			
		} while (true);
		}
		}
	}
	
	public final void patternInstruction(
		TomList subjectList, List<ConstraintInstruction> list, TomType rhsType
	) throws RecognitionException, TokenStreamException, TomException {
		
		Token  label = null;
		
		List<Option> optionListLinked = new LinkedList<Option>();
		List<TomTerm> matchPatternList = new LinkedList<TomTerm>();
		
		Constraint constraint = tom_make_TrueConstraint();    
		Constraint constr = null;
		OptionList optionList = null;
		Option option = null;
		
		boolean isAnd = false;
		
		clearText();
		
		
		{
		{
		boolean synPredMatched20 = false;
		if (((LA(1)==ALL_ID))) {
			int _m20 = mark();
			synPredMatched20 = true;
			inputState.guessing++;
			try {
				{
				match(ALL_ID);
				match(COLON);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched20 = false;
			}
			rewind(_m20);
inputState.guessing--;
		}
		if ( synPredMatched20 ) {
			label = LT(1);
			match(ALL_ID);
			match(COLON);
		}
		else if ((_tokenSet_0.member(LA(1)))) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		option=matchPattern(matchPatternList,true);
		if ( inputState.guessing==0 ) {
			
			if(matchPatternList.size() != subjectList.length()) {                       
			getLogger().log(new PlatformLogRecord(Level.SEVERE, TomMessage.badMatchNumberArgument,
			new Object[]{new Integer(subjectList.length()), new Integer(matchPatternList.size())},
			currentFile(), getLine()));
			return;
			}
			
			int counter = 0;
			{{if (tom_is_sort_TomList(subjectList)) {if (tom_is_fun_sym_concTomTerm((( tom.engine.adt.tomterm.types.TomList )subjectList))) { tom.engine.adt.tomterm.types.TomList  tomMatch2NameNumber_end_4=(( tom.engine.adt.tomterm.types.TomList )subjectList);do {{if (!(tom_is_empty_concTomTerm_TomList(tomMatch2NameNumber_end_4))) {
			
			constraint = tom_cons_list_AndConstraint(constraint,tom_cons_list_AndConstraint(tom_make_MatchConstraint(matchPatternList.get(counter),tom_get_head_concTomTerm_TomList(tomMatch2NameNumber_end_4)),tom_empty_list_AndConstraint()));
			counter++;
			}if (tom_is_empty_concTomTerm_TomList(tomMatch2NameNumber_end_4)) {tomMatch2NameNumber_end_4=(( tom.engine.adt.tomterm.types.TomList )subjectList);} else {tomMatch2NameNumber_end_4=tom_get_tail_concTomTerm_TomList(tomMatch2NameNumber_end_4);}}} while(!(tom_equal_term_TomList(tomMatch2NameNumber_end_4, (( tom.engine.adt.tomterm.types.TomList )subjectList))));}}}}
			
			
			optionList = tom_cons_list_concOption(option,tom_cons_list_concOption(tom_make_OriginalText(tom_make_Name(text.toString())),tom_empty_list_concOption()));
			
			matchPatternList.clear();
			clearText();
			
		}
		{
		switch ( LA(1)) {
		case AND_CONNECTOR:
		case OR_CONNECTOR:
		{
			{
			switch ( LA(1)) {
			case AND_CONNECTOR:
			{
				match(AND_CONNECTOR);
				if ( inputState.guessing==0 ) {
					isAnd = true;
				}
				break;
			}
			case OR_CONNECTOR:
			{
				match(OR_CONNECTOR);
				if ( inputState.guessing==0 ) {
					isAnd = false;
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			constr=matchOrConstraint(optionListLinked);
			if ( inputState.guessing==0 ) {
				
				constraint = isAnd ? tom_cons_list_AndConstraint(constraint,tom_cons_list_AndConstraint(constr,tom_empty_list_AndConstraint())) : tom_cons_list_OrConstraint(constraint,tom_cons_list_OrConstraint(constr,tom_empty_list_OrConstraint()));
				
			}
			break;
		}
		case ARROW:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		arrowAndAction(list,optionList,optionListLinked,label,rhsType,constraint);
		}
	}
	
	public final void constraintInstruction(
		List<ConstraintInstruction> list, TomType rhsType
	) throws RecognitionException, TokenStreamException, TomException {
		
		
		List<Option> optionListLinked = new LinkedList<Option>();
		Constraint constraint = tom_make_TrueConstraint();
		clearText();
		
		
		{
		constraint=matchOrConstraint(optionListLinked);
		arrowAndAction(list,null,optionListLinked,null,rhsType,constraint);
		}
	}
	
	public final void matchArgument(
		List<TomTerm> list
	) throws RecognitionException, TokenStreamException, TomException {
		
		
		TomTerm subject1 = null;
		TomTerm subject2 = null;
		TomType tomType = null;
		
		String s1 = null;
		String s2 = null;
		
		
		subject1=plainTerm(null,null,0);
		if ( inputState.guessing==0 ) {
			s1 = text.toString();text.delete(0, text.length());
		}
		{
		switch ( LA(1)) {
		case BACKQUOTE:
		{
			match(BACKQUOTE);
			if ( inputState.guessing==0 ) {
				text.delete(0, text.length());
			}
			break;
		}
		case NUM_INT:
		case CHARACTER:
		case STRING:
		case NUM_FLOAT:
		case NUM_LONG:
		case NUM_DOUBLE:
		case LPAREN:
		case RPAREN:
		case COMMA:
		case ALL_ID:
		case AND_CONNECTOR:
		case OR_CONNECTOR:
		case ARROW:
		case XML_START:
		case ANTI_SYM:
		case XML_TEXT:
		case XML_COMMENT:
		case XML_PROC:
		case LBRACKET:
		case UNDERSCORE:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		switch ( LA(1)) {
		case NUM_INT:
		case CHARACTER:
		case STRING:
		case NUM_FLOAT:
		case NUM_LONG:
		case NUM_DOUBLE:
		case LPAREN:
		case ALL_ID:
		case XML_START:
		case ANTI_SYM:
		case XML_TEXT:
		case XML_COMMENT:
		case XML_PROC:
		case LBRACKET:
		case UNDERSCORE:
		{
			subject2=plainTerm(null,null,0);
			if ( inputState.guessing==0 ) {
				s2 = text.toString();
			}
			break;
		}
		case RPAREN:
		case COMMA:
		case AND_CONNECTOR:
		case OR_CONNECTOR:
		case ARROW:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			
			if(subject2==null) {
			// System.out.println("matchArgument = " + subject1);
			list.add(subject1);        
			} else {
			if(subject1.isVariable()) {
			String type = subject1.getAstName().getString();
			{{if (tom_is_sort_TomTerm(subject2)) {if (tom_is_fun_sym_Variable((( tom.engine.adt.tomterm.types.TomTerm )subject2))) { tom.engine.adt.tomname.types.TomName  tom_name=tom_get_slot_Variable_AstName((( tom.engine.adt.tomterm.types.TomTerm )subject2));
			
			Option ot = tom_make_OriginTracking(tom_name,lastLine,currentFile());
			list.add(tom_make_Variable(tom_cons_list_concOption(ot,tom_empty_list_concOption()),tom_name,tom_make_TomTypeAlone(type),tom_empty_list_concConstraint()));  
			return;
			}}}{if (tom_is_sort_TomTerm(subject2)) {if (tom_is_fun_sym_TermAppl((( tom.engine.adt.tomterm.types.TomTerm )subject2))) {
			
			list.add(tom_make_BuildReducedTerm((( tom.engine.adt.tomterm.types.TomTerm )subject2),tom_make_TomTypeAlone(type)));
			return;
			}}}}
			
			}  
			throw new TomException(TomMessage.invalidMatchSubject, new Object[]{subject1, subject2});
			}
			
		}
	}
	
	public final TomTerm  plainTerm(
		TomName astLabeledName, TomName astAnnotedName, int line
	) throws RecognitionException, TokenStreamException, TomException {
		TomTerm result;
		
		Token  a = null;
		
		List<Option> optionList = new LinkedList<Option>();
		List<Option> secondOptionList = new LinkedList<Option>();
		List list = new LinkedList();
		List<Constraint> constraintList = new LinkedList<Constraint>(); 
		result = null;
		boolean anti = false;
		
		
		{
		_loop85:
		do {
			if ((LA(1)==ANTI_SYM)) {
				a = LT(1);
				match(ANTI_SYM);
				if ( inputState.guessing==0 ) {
					anti = !anti;
				}
			}
			else {
				break _loop85;
			}
			
		} while (true);
		}
		{
		if (((_tokenSet_1.member(LA(1))))&&(LA(1) != LPAREN  || ( LA(1) == LPAREN && ( LA(3) == ALTERNATIVE || LA(4) == ALTERNATIVE) ) )) {
			result=simplePlainTerm(astLabeledName, astAnnotedName, line, list,secondOptionList,optionList,constraintList,anti);
		}
		else if ((LA(1)==LPAREN||LA(1)==LBRACKET)) {
			result=implicitNotationPlainTerm(astLabeledName, astAnnotedName, line, list,secondOptionList,optionList,constraintList,anti);
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		if ( inputState.guessing==0 ) {
			return result;
		}
		return result;
	}
	
	public final Option  matchPattern(
		List<TomTerm> list, boolean allowImplicit
	) throws RecognitionException, TokenStreamException, TomException {
		Option result;
		
		
		result = null;
		TomTerm term = null;
		
		
		{
		term=annotatedTerm(allowImplicit);
		if ( inputState.guessing==0 ) {
			
			list.add(term);
			result = tom_make_OriginTracking(tom_make_Name("Pattern"),lastLine,currentFile());
			
		}
		{
		_loop50:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				if ( inputState.guessing==0 ) {
					text.append('\n');
				}
				term=annotatedTerm(allowImplicit);
				if ( inputState.guessing==0 ) {
					list.add(term);
				}
			}
			else {
				break _loop50;
			}
			
		} while (true);
		}
		}
		return result;
	}
	
	public final Constraint  matchOrConstraint(
		List<Option> optionListLinked
	) throws RecognitionException, TokenStreamException, TomException {
		Constraint result;
		
		
		result = null;  
		Constraint constr = null;
		
		
		result=matchAndConstraint(optionListLinked);
		{
		_loop36:
		do {
			if ((LA(1)==OR_CONNECTOR)) {
				match(OR_CONNECTOR);
				constr=matchAndConstraint(optionListLinked);
				if ( inputState.guessing==0 ) {
					
					result = tom_cons_list_OrConstraint(result,tom_cons_list_OrConstraint(constr,tom_empty_list_OrConstraint()));
					
				}
			}
			else {
				break _loop36;
			}
			
		} while (true);
		}
		return result;
	}
	
	public final void arrowAndAction(
		List<ConstraintInstruction> list, OptionList optionList, List<Option> optionListLinked, Token label, TomType rhsType, Constraint constraint
	) throws RecognitionException, TokenStreamException, TomException {
		
		Token  t = null;
		
		List blockList = new LinkedList();
		TomTerm rhsTerm = null;
		
		
		match(ARROW);
		if ( inputState.guessing==0 ) {
			
			optionList = tom_empty_list_concOption();
			for(Option op:optionListLinked) {
			optionList = tom_append_list_concOption(optionList,tom_cons_list_concOption(op,tom_empty_list_concOption()));
			}
			optionList = tom_append_list_concOption(optionList,tom_cons_list_concOption(tom_make_OriginalText(tom_make_Name(text.toString())),tom_empty_list_concOption()));
			if(label != null) {
			optionList = tom_cons_list_concOption(tom_make_Label(tom_make_Name(label.getText())),tom_append_list_concOption(optionList,tom_empty_list_concOption()));
			}
			
		}
		t = LT(1);
		match(LBRACE);
		if ( inputState.guessing==0 ) {
			
			// update for new target block
			updatePosition(t.getLine(),t.getColumn());
			// actions in target language : call the target lexer and
			// call the target parser
			selector().push("targetlexer");
			TargetLanguage tlCode = targetparser.targetLanguage(blockList);
			// target parser finished : pop the target lexer
			selector().pop();
			blockList.add(tlCode);
			list.add(tom_make_ConstraintInstruction(constraint,tom_make_RawAction(tom_make_AbstractBlock(ASTFactory.makeInstructionList(blockList))),optionList)
			
			
			
			);
			
		}
	}
	
	public final void visitInstruction(
		TomList subjectList, List<ConstraintInstruction> list, TomType rhsType
	) throws RecognitionException, TokenStreamException, TomException {
		
		Token  label = null;
		Token  t = null;
		
		List<Option> optionListLinked = new LinkedList<Option>();
		List<TomTerm> matchPatternList = new LinkedList<TomTerm>();
		
		Constraint constraint = tom_make_TrueConstraint();    
		Constraint constr = null;
		OptionList optionList = null;
		Option option = null;
		
		boolean isAnd = false;
		
		List blockList = new LinkedList();
		TomTerm rhsTerm = null;
		
		clearText();
		
		
		{
		{
		boolean synPredMatched27 = false;
		if (((LA(1)==ALL_ID))) {
			int _m27 = mark();
			synPredMatched27 = true;
			inputState.guessing++;
			try {
				{
				match(ALL_ID);
				match(COLON);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched27 = false;
			}
			rewind(_m27);
inputState.guessing--;
		}
		if ( synPredMatched27 ) {
			label = LT(1);
			match(ALL_ID);
			match(COLON);
		}
		else if ((_tokenSet_0.member(LA(1)))) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		option=matchPattern(matchPatternList,true);
		if ( inputState.guessing==0 ) {
			
			if(matchPatternList.size() != subjectList.length()) {                       
			getLogger().log(new PlatformLogRecord(Level.SEVERE, TomMessage.badMatchNumberArgument,
			new Object[]{new Integer(subjectList.length()), new Integer(matchPatternList.size())},
			currentFile(), getLine()));
			return;
			}
			
			int counter = 0;
			{{if (tom_is_sort_TomList(subjectList)) {if (tom_is_fun_sym_concTomTerm((( tom.engine.adt.tomterm.types.TomList )subjectList))) { tom.engine.adt.tomterm.types.TomList  tomMatch3NameNumber_end_4=(( tom.engine.adt.tomterm.types.TomList )subjectList);do {{if (!(tom_is_empty_concTomTerm_TomList(tomMatch3NameNumber_end_4))) {
			
			constraint = tom_cons_list_AndConstraint(constraint,tom_cons_list_AndConstraint(tom_make_MatchConstraint(matchPatternList.get(counter),tom_get_head_concTomTerm_TomList(tomMatch3NameNumber_end_4)),tom_empty_list_AndConstraint()));
			counter++;
			}if (tom_is_empty_concTomTerm_TomList(tomMatch3NameNumber_end_4)) {tomMatch3NameNumber_end_4=(( tom.engine.adt.tomterm.types.TomList )subjectList);} else {tomMatch3NameNumber_end_4=tom_get_tail_concTomTerm_TomList(tomMatch3NameNumber_end_4);}}} while(!(tom_equal_term_TomList(tomMatch3NameNumber_end_4, (( tom.engine.adt.tomterm.types.TomList )subjectList))));}}}}
			
			
			optionList = tom_cons_list_concOption(option,tom_cons_list_concOption(tom_make_OriginalText(tom_make_Name(text.toString())),tom_empty_list_concOption()));
			
			matchPatternList.clear();
			clearText();
			
		}
		{
		switch ( LA(1)) {
		case AND_CONNECTOR:
		case OR_CONNECTOR:
		{
			{
			switch ( LA(1)) {
			case AND_CONNECTOR:
			{
				match(AND_CONNECTOR);
				if ( inputState.guessing==0 ) {
					isAnd = true;
				}
				break;
			}
			case OR_CONNECTOR:
			{
				match(OR_CONNECTOR);
				if ( inputState.guessing==0 ) {
					isAnd = false;
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			constr=matchOrConstraint(optionListLinked);
			if ( inputState.guessing==0 ) {
				
				constraint = isAnd ? tom_cons_list_AndConstraint(constraint,tom_cons_list_AndConstraint(constr,tom_empty_list_AndConstraint())) : tom_cons_list_OrConstraint(constraint,tom_cons_list_OrConstraint(constr,tom_empty_list_OrConstraint()));
				
			}
			break;
		}
		case ARROW:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(ARROW);
		if ( inputState.guessing==0 ) {
			
			optionList = tom_empty_list_concOption();
			for(Option op:optionListLinked) {
			optionList = tom_append_list_concOption(optionList,tom_cons_list_concOption(op,tom_empty_list_concOption()));
			}
			optionList = tom_append_list_concOption(optionList,tom_cons_list_concOption(tom_make_OriginalText(tom_make_Name(text.toString())),tom_empty_list_concOption()));
			if (label != null) {
			optionList = tom_cons_list_concOption(tom_make_Label(tom_make_Name(label.getText())),tom_append_list_concOption(optionList,tom_empty_list_concOption()));
			}
			
			
		}
		{
		switch ( LA(1)) {
		case LBRACE:
		{
			t = LT(1);
			match(LBRACE);
			if ( inputState.guessing==0 ) {
				
				// update for new target block
				updatePosition(t.getLine(),t.getColumn());
				// actions in target language : call the target lexer and
				// call the target parser
				selector().push("targetlexer");
				TargetLanguage tlCode = targetparser.targetLanguage(blockList);
				// target parser finished : pop the target lexer
				selector().pop();
				blockList.add(tlCode);
				list.add(tom_make_ConstraintInstruction(constraint,tom_make_RawAction(tom_make_AbstractBlock(ASTFactory.makeInstructionList(blockList))),optionList)
				
				
				
				);
				
			}
			break;
		}
		case NUM_INT:
		case CHARACTER:
		case STRING:
		case NUM_FLOAT:
		case NUM_LONG:
		case NUM_DOUBLE:
		case LPAREN:
		case ALL_ID:
		case XML_START:
		case ANTI_SYM:
		case XML_TEXT:
		case XML_COMMENT:
		case XML_PROC:
		case LBRACKET:
		case UNDERSCORE:
		{
			rhsTerm=plainTerm(null,null,0);
			if ( inputState.guessing==0 ) {
				
				// case where the rhs of a rule is an algebraic term
				list.add(tom_make_ConstraintInstruction(constraint,tom_make_Return(tom_make_BuildReducedTerm(rhsTerm,rhsType)),optionList)
				
				
				
				);
				
				
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		}
	}
	
	public final Constraint  matchAndConstraint(
		List<Option> optionListLinked
	) throws RecognitionException, TokenStreamException, TomException {
		Constraint result;
		
		
		result = null;  
		Constraint constr = null;
		
		
		result=matchParanthesedConstraint(optionListLinked);
		{
		_loop39:
		do {
			if ((LA(1)==AND_CONNECTOR)) {
				match(AND_CONNECTOR);
				constr=matchParanthesedConstraint(optionListLinked);
				if ( inputState.guessing==0 ) {
					
					result = tom_cons_list_AndConstraint(result,tom_cons_list_AndConstraint(constr,tom_empty_list_AndConstraint()));
					
				}
			}
			else {
				break _loop39;
			}
			
		} while (true);
		}
		return result;
	}
	
	public final Constraint  matchParanthesedConstraint(
		List<Option> optionListLinked
	) throws RecognitionException, TokenStreamException, TomException {
		Constraint result;
		
		
		result = null; 
		List<TomTerm> matchPatternList = new LinkedList<TomTerm>();
		
		
		boolean synPredMatched42 = false;
		if (((_tokenSet_0.member(LA(1))))) {
			int _m42 = mark();
			synPredMatched42 = true;
			inputState.guessing++;
			try {
				{
				matchPattern(matchPatternList,true);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched42 = false;
			}
			rewind(_m42);
inputState.guessing--;
		}
		if ( synPredMatched42 ) {
			result=matchConstraint(optionListLinked);
		}
		else if ((LA(1)==LPAREN)) {
			match(LPAREN);
			result=matchOrConstraint(optionListLinked);
			match(RPAREN);
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		return result;
	}
	
	public final Constraint  matchConstraint(
		List<Option> optionListLinked
	) throws RecognitionException, TokenStreamException, TomException {
		Constraint result;
		
		
		List<TomTerm> matchPatternList = new LinkedList<TomTerm>();
		List<TomTerm> matchSubjectList = new LinkedList<TomTerm>();
		Option option = null;
		result = null;
		int consType = -1;
		
		
		{
		option=matchPattern(matchPatternList,true);
		consType=constraintType();
		matchArgument(matchSubjectList);
		if ( inputState.guessing==0 ) {
			
			optionListLinked.add(option);
			TomTerm left  = (TomTerm)matchPatternList.get(0);
			TomTerm right = (TomTerm)matchSubjectList.get(0);
			switch(consType) {
			case MATCH_CONSTRAINT : {
			return tom_make_MatchConstraint(left,right);           
			}
			case /*LESS_CONSTRAINT*/XML_START : {         
			return tom_make_NumericConstraint(left,right,tom_make_NumLessThan());           
			}
			case LESSOREQUAL_CONSTRAINT : {         
			return tom_make_NumericConstraint(left,right,tom_make_NumLessOrEqualThan());           
			}
			case /*GREATER_CONSTRAINT*/XML_CLOSE : {         
			return tom_make_NumericConstraint(left,right,tom_make_NumGreaterThan());           
			}
			case GREATEROREQUAL_CONSTRAINT : {         
			return tom_make_NumericConstraint(left,right,tom_make_NumGreaterOrEqualThan());           
			}
			case DIFFERENT_CONSTRAINT : {         
			return tom_make_NumericConstraint(left,right,tom_make_NumDifferent());           
			}
			case DOUBLEEQ : {         
			return tom_make_NumericConstraint(left,right,tom_make_NumEqual());           
			}      
			} 
			// should never reach this statement because of the parsing error that should occur before
			throw new TomException(TomMessage.invalidConstraintType);
			
		}
		}
		return result;
	}
	
	public final int  constraintType() throws RecognitionException, TokenStreamException {
		int result;
		
		
		result = -1;
		
		
		{
		switch ( LA(1)) {
		case MATCH_CONSTRAINT:
		{
			match(MATCH_CONSTRAINT);
			if ( inputState.guessing==0 ) {
				result = MATCH_CONSTRAINT;
			}
			break;
		}
		case XML_START:
		{
			match(XML_START);
			if ( inputState.guessing==0 ) {
				result = /*LESS_CONSTRAINT;*/XML_START;
			}
			break;
		}
		case LESSOREQUAL_CONSTRAINT:
		{
			match(LESSOREQUAL_CONSTRAINT);
			if ( inputState.guessing==0 ) {
				result = LESSOREQUAL_CONSTRAINT;
			}
			break;
		}
		case XML_CLOSE:
		{
			match(XML_CLOSE);
			if ( inputState.guessing==0 ) {
				result = /*GREATER_CONSTRAINT*/XML_CLOSE;
			}
			break;
		}
		case GREATEROREQUAL_CONSTRAINT:
		{
			match(GREATEROREQUAL_CONSTRAINT);
			if ( inputState.guessing==0 ) {
				result = GREATEROREQUAL_CONSTRAINT;
			}
			break;
		}
		case DOUBLEEQ:
		{
			match(DOUBLEEQ);
			if ( inputState.guessing==0 ) {
				result = DOUBLEEQ;
			}
			break;
		}
		case DIFFERENT_CONSTRAINT:
		{
			match(DIFFERENT_CONSTRAINT);
			if ( inputState.guessing==0 ) {
				result = DIFFERENT_CONSTRAINT;
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		return result;
	}
	
	public final TomTerm  annotatedTerm(
		boolean allowImplicit
	) throws RecognitionException, TokenStreamException, TomException {
		TomTerm result;
		
		Token  lname = null;
		Token  name = null;
		Token  a = null;
		
		result = null;
		TomName labeledName = null;
		TomName annotatedName = null;
		int line = 0;
		boolean anti = false;
		
		
		{
		{
		boolean synPredMatched71 = false;
		if (((LA(1)==ALL_ID))) {
			int _m71 = mark();
			synPredMatched71 = true;
			inputState.guessing++;
			try {
				{
				match(ALL_ID);
				match(COLON);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched71 = false;
			}
			rewind(_m71);
inputState.guessing--;
		}
		if ( synPredMatched71 ) {
			lname = LT(1);
			match(ALL_ID);
			match(COLON);
			if ( inputState.guessing==0 ) {
				
				text.append(lname.getText());
				text.append(':');
				labeledName = tom_make_Name(lname.getText());
				line = lname.getLine();
				
			}
		}
		else if ((_tokenSet_0.member(LA(1)))) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		{
		boolean synPredMatched74 = false;
		if (((LA(1)==ALL_ID))) {
			int _m74 = mark();
			synPredMatched74 = true;
			inputState.guessing++;
			try {
				{
				match(ALL_ID);
				match(AT);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched74 = false;
			}
			rewind(_m74);
inputState.guessing--;
		}
		if ( synPredMatched74 ) {
			name = LT(1);
			match(ALL_ID);
			match(AT);
			if ( inputState.guessing==0 ) {
				
				text.append(name.getText());
				text.append('@');
				annotatedName = tom_make_Name(name.getText());
				line = name.getLine();
				
			}
		}
		else if ((_tokenSet_0.member(LA(1)))) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		{
		if (((_tokenSet_0.member(LA(1))))&&(allowImplicit)) {
			result=plainTerm(labeledName,annotatedName,line);
		}
		else if (((_tokenSet_2.member(LA(1))))&&(!allowImplicit)) {
			{
			_loop77:
			do {
				if ((LA(1)==ANTI_SYM)) {
					a = LT(1);
					match(ANTI_SYM);
					if ( inputState.guessing==0 ) {
						anti = !anti;
					}
				}
				else {
					break _loop77;
				}
				
			} while (true);
			}
			result=simplePlainTerm(labeledName,annotatedName,line, new LinkedList(), new LinkedList<Option>(), new LinkedList<Option>(), new LinkedList<Constraint>(), anti);
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		}
		return result;
	}
	
	public final Declaration  strategyConstruct(
		Option orgTrack
	) throws RecognitionException, TokenStreamException, TomException {
		Declaration result;
		
		Token  name = null;
		Token  firstSlot1 = null;
		Token  colon = null;
		Token  secondSlot1 = null;
		Token  firstSlot2 = null;
		Token  colon2 = null;
		Token  secondSlot2 = null;
		Token  t = null;
		
		result = null;
		TomTerm extendsTerm = null;
		List<TomVisit> visitList = new LinkedList<TomVisit>();
		TomVisitList astVisitList = tom_empty_list_concTomVisit();
		TomName orgText = null;
		TomTypeList types = tom_empty_list_concTomType();
		List<Option> options = new LinkedList<Option>();
		List<TomName> slotNameList = new LinkedList<TomName>();
		List<PairNameDecl> pairNameDeclList = new LinkedList<PairNameDecl>();
		String stringSlotName = null;
		String stringTypeArg = null;
		
		clearText();
		
		
		{
		name = LT(1);
		match(ALL_ID);
		if ( inputState.guessing==0 ) {
			
			Option ot = tom_make_OriginTracking(tom_make_Name(name.getText()),name.getLine(),currentFile());
			options.add(ot);
			if(symbolTable.getSymbolFromName(name.getText()) != null) {
			throw new TomException(TomMessage.invalidStrategyName, new Object[]{name.getText()});
			}
			
		}
		{
		match(LPAREN);
		{
		switch ( LA(1)) {
		case ALL_ID:
		{
			firstSlot1 = LT(1);
			match(ALL_ID);
			{
			switch ( LA(1)) {
			case COLON:
			{
				colon = LT(1);
				match(COLON);
				break;
			}
			case ALL_ID:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			secondSlot1 = LT(1);
			match(ALL_ID);
			if ( inputState.guessing==0 ) {
				
				if(colon != null) {
				stringSlotName = firstSlot1.getText(); 
				stringTypeArg = secondSlot1.getText(); 
				} else {
				stringSlotName = secondSlot1.getText(); 
				stringTypeArg = firstSlot1.getText(); 
				}
				TomName astName = tom_make_Name(stringSlotName);
				slotNameList.add(astName); 
				
				TomType strategyType = tom_make_TomTypeAlone("Strategy");
				
				// Define get<slot> method.
				Option slotOption = tom_make_OriginTracking(tom_make_Name(stringSlotName),firstSlot1.getLine(),currentFile());
				String varname = "t";
				TomTerm slotVar = tom_make_Variable(tom_cons_list_concOption(slotOption,tom_empty_list_concOption()),tom_make_Name(varname),strategyType,tom_empty_list_concConstraint());
				String code = ASTFactory.abstractCode("((" + name.getText() + ")$"+varname+").get" + stringSlotName + "()",varname);
				Declaration slotDecl = tom_make_GetSlotDecl(tom_make_Name(name.getText()),tom_make_Name(stringSlotName),slotVar,tom_make_Code(code),slotOption);
				
				pairNameDeclList.add(tom_make_PairNameDecl(astName,slotDecl)); 
				types = tom_append_list_concTomType(types,tom_cons_list_concTomType(tom_make_TomTypeAlone(stringTypeArg),tom_empty_list_concTomType()));
				
			}
			{
			_loop58:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					firstSlot2 = LT(1);
					match(ALL_ID);
					{
					switch ( LA(1)) {
					case COLON:
					{
						colon2 = LT(1);
						match(COLON);
						break;
					}
					case ALL_ID:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					secondSlot2 = LT(1);
					match(ALL_ID);
					if ( inputState.guessing==0 ) {
						
						if(colon != null) {
						stringSlotName = firstSlot2.getText(); 
						stringTypeArg = secondSlot2.getText(); 
						} else {
						stringSlotName = secondSlot2.getText(); 
						stringTypeArg = firstSlot2.getText(); 
						}
						TomName astName = ASTFactory.makeName(stringSlotName);
						if(slotNameList.indexOf(astName) != -1) {
						getLogger().log(new PlatformLogRecord(Level.SEVERE, TomMessage.repeatedSlotName,
						new Object[]{stringSlotName},
						currentFile(), getLine()));
						}
						slotNameList.add(astName); 
						
						TomType strategyType = tom_make_TomTypeAlone("Strategy");
						// Define get<slot> method.
						Option slotOption = tom_make_OriginTracking(tom_make_Name(stringSlotName),firstSlot2.getLine(),currentFile());
						String varname = "t";
						TomTerm slotVar = tom_make_Variable(tom_cons_list_concOption(slotOption,tom_empty_list_concOption()),tom_make_Name(varname),strategyType,tom_empty_list_concConstraint());
						String code = ASTFactory.abstractCode("((" + name.getText() + ")$"+varname+").get" + stringSlotName + "()",varname);
						Declaration slotDecl = tom_make_GetSlotDecl(tom_make_Name(name.getText()),tom_make_Name(stringSlotName),slotVar,tom_make_Code(code),slotOption);
						
						pairNameDeclList.add(tom_make_PairNameDecl(tom_make_Name(stringSlotName),slotDecl)); 
						types = tom_append_list_concTomType(types,tom_cons_list_concTomType(tom_make_TomTypeAlone(stringTypeArg),tom_empty_list_concTomType()));
						
					}
				}
				else {
					break _loop58;
				}
				
			} while (true);
			}
			break;
		}
		case RPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(RPAREN);
		}
		match(EXTENDS);
		{
		switch ( LA(1)) {
		case BACKQUOTE:
		{
			match(BACKQUOTE);
			break;
		}
		case NUM_INT:
		case CHARACTER:
		case STRING:
		case NUM_FLOAT:
		case NUM_LONG:
		case NUM_DOUBLE:
		case LPAREN:
		case ALL_ID:
		case XML_START:
		case ANTI_SYM:
		case XML_TEXT:
		case XML_COMMENT:
		case XML_PROC:
		case LBRACKET:
		case UNDERSCORE:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		extendsTerm=plainTerm(null,null,0);
		match(LBRACE);
		strategyVisitList(visitList);
		if ( inputState.guessing==0 ) {
			astVisitList = ASTFactory.makeTomVisitList(visitList);
		}
		t = LT(1);
		match(RBRACE);
		if ( inputState.guessing==0 ) {
			
			//initialize arrayList with argument names
							 TomList makeArgs = tom_empty_list_concTomTerm();
			int index = 0;
			TomTypeList makeTypes = types;//keep a copy of types
							 String makeTlCode = "new " + name.getText() + "(";
			while(!makeTypes.isEmptyconcTomType()) {
								 String argName = "t"+index;
			if (index>0) {//if many parameters
			makeTlCode = makeTlCode.concat(",");
			}
								 makeTlCode += argName;
			
			TomTerm arg = tom_make_Variable(tom_empty_list_concOption(),tom_make_Name(argName),makeTypes.getHeadconcTomType(),tom_empty_list_concConstraint());
			makeArgs = tom_append_list_concTomTerm(makeArgs,tom_cons_list_concTomTerm(arg,tom_empty_list_concTomTerm()));
			
								 makeTypes = makeTypes.getTailconcTomType();
			index++;
			}
							 makeTlCode += ")";
			
			TomType strategyType = tom_make_TomTypeAlone("Strategy");
							 Option makeOption = tom_make_OriginTracking(tom_make_Name(name.getText()),t.getLine(),currentFile());
							 Declaration makeDecl = tom_make_MakeDecl(tom_make_Name(name.getText()),strategyType,makeArgs,tom_make_TargetLanguageToInstruction(tom_make_ITL(makeTlCode)),makeOption);
			options.add(tom_make_DeclarationToOption(makeDecl));
			
			// Define the is_fsym method.
			Option fsymOption = tom_make_OriginTracking(tom_make_Name(name.getText()),t.getLine(),currentFile());
			String varname = "t";
			TomTerm fsymVar = tom_make_Variable(tom_cons_list_concOption(fsymOption,tom_empty_list_concOption()),tom_make_Name(varname),strategyType,tom_empty_list_concConstraint());
			String code = ASTFactory.abstractCode("($"+varname+" instanceof " + name.getText() + ")",varname);
			Declaration fsymDecl = tom_make_IsFsymDecl(tom_make_Name(name.getText()),fsymVar,tom_make_Code(code),fsymOption);
			options.add(tom_make_DeclarationToOption(fsymDecl));
			
			TomSymbol astSymbol = ASTFactory.makeSymbol(name.getText(), strategyType, types, ASTFactory.makePairNameDeclList(pairNameDeclList), options);
			putSymbol(name.getText(),astSymbol);
			// update for new target block...
			updatePosition(t.getLine(),t.getColumn());
			
			result = tom_make_AbstractDecl(tom_cons_list_concDeclaration(tom_make_Strategy(tom_make_Name(name.getText()),tom_make_BuildReducedTerm(extendsTerm,strategyType),astVisitList,orgTrack),tom_cons_list_concDeclaration(tom_make_SymbolDecl(tom_make_Name(name.getText())),tom_empty_list_concDeclaration())));
			
			// %strat finished: go back in target parser.
			selector().pop();
			
		}
		}
		return result;
	}
	
	public final void strategyVisitList(
		List<TomVisit> list
	) throws RecognitionException, TokenStreamException, TomException {
		
		
		{
		_loop62:
		do {
			if ((LA(1)==ALL_ID)) {
				strategyVisit(list);
			}
			else {
				break _loop62;
			}
			
		} while (true);
		}
	}
	
	public final void strategyVisit(
		List<TomVisit> list
	) throws RecognitionException, TokenStreamException, TomException {
		
		Token  visit = null;
		Token  type = null;
		
		List<ConstraintInstruction> constraintInstructionList = new LinkedList<ConstraintInstruction>();
		TomType vType = null;
		TomList subjectList = tom_empty_list_concTomTerm();
		
		clearText();
		
		
		{
		visit = LT(1);
		match(ALL_ID);
		if ( inputState.guessing==0 ) {
			
			if (!"visit".equals(visit.getText())) {
			throw new TomException(TomMessage.malformedStrategy,
			new Object[]{currentFile(), new Integer(getLine()),
			"strat","visit",visit.getText()});
			}
			
		}
		type = LT(1);
		match(ALL_ID);
		match(LBRACE);
		if ( inputState.guessing==0 ) {
			
			vType = tom_make_TomTypeAlone(type.getText());
			subjectList = tom_cons_list_concTomTerm(tom_make_TomTypeToTomTerm(vType),tom_empty_list_concTomTerm());
			
		}
		{
		_loop66:
		do {
			if ((_tokenSet_0.member(LA(1)))) {
				visitInstruction(subjectList,constraintInstructionList,vType);
			}
			else {
				break _loop66;
			}
			
		} while (true);
		}
		match(RBRACE);
		}
		if ( inputState.guessing==0 ) {
			
			List<Option> optionList = new LinkedList<Option>();
			optionList.add(tom_make_OriginTracking(tom_make_Name(type.getText()),type.getLine(),currentFile()));
			OptionList options = ASTFactory.makeOptionList(optionList);
			list.add(tom_make_VisitTerm(vType,ASTFactory.makeConstraintInstructionList(constraintInstructionList),options));
			
		}
	}
	
	public final TomTerm  simplePlainTerm(
		TomName astLabeledName, TomName astAnnotedName, int line, List list,
                 List<Option> secondOptionList, List<Option> optionList,
                 List<Constraint> constraintList, boolean anti
	) throws RecognitionException, TokenStreamException, TomException {
		TomTerm result;
		
		Token  qm = null;
		
		result = null;   
		TomNameList nameList = tom_empty_list_concTomName();
		TomName name = null;
		boolean implicit = false;
		
		if(astLabeledName != null) {
		constraintList.add(ASTFactory.makeStorePosition(astLabeledName, line, currentFile()));
		}
		if(astAnnotedName != null) {
		constraintList.add(ASTFactory.makeAssignTo(astAnnotedName, line, currentFile()));
		}    
		
		
		{
		if ((_tokenSet_3.member(LA(1)))) {
			result=xmlTerm(optionList, constraintList);
			if ( inputState.guessing==0 ) {
				
				if(anti) { result = tom_make_AntiTerm(result); }
				
			}
		}
		else {
			boolean synPredMatched81 = false;
			if ((((LA(1)==ALL_ID||LA(1)==UNDERSCORE))&&(!anti))) {
				int _m81 = mark();
				synPredMatched81 = true;
				inputState.guessing++;
				try {
					{
					variableStar(null,null);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched81 = false;
				}
				rewind(_m81);
inputState.guessing--;
			}
			if ( synPredMatched81 ) {
				result=variableStar(optionList,constraintList);
			}
			else if (((LA(1)==UNDERSCORE))&&(!anti)) {
				result=unamedVariable(optionList,constraintList);
			}
			else if (((LA(1)==ALL_ID))&&(LA(2) != LPAREN && LA(2) != LBRACKET && LA(2) != QMARK)) {
				name=headSymbol(optionList);
				if ( inputState.guessing==0 ) {
					
					result = tom_make_Variable(ASTFactory.makeOptionList(optionList),name,SymbolTable.TYPE_UNKNOWN,ASTFactory.makeConstraintList(constraintList))
					;
					if(anti) { result = tom_make_AntiTerm(result); }
					
				}
			}
			else if ((((LA(1) >= NUM_INT && LA(1) <= NUM_DOUBLE)))&&(LA(2) != LPAREN && LA(2) != LBRACKET && LA(2) != QMARK)) {
				nameList=headConstantList(optionList);
				if ( inputState.guessing==0 ) {
					
					optionList.add(tom_make_Constant());
					result = tom_make_TermAppl(ASTFactory.makeOptionList(optionList),nameList,ASTFactory.makeList(list),ASTFactory.makeConstraintList(constraintList))
					
					
					
					;
					if(anti) { result = tom_make_AntiTerm(result); }
					
				}
			}
			else if ((LA(1)==ALL_ID)) {
				name=headSymbol(optionList);
				{
				switch ( LA(1)) {
				case QMARK:
				{
					qm = LT(1);
					match(QMARK);
					break;
				}
				case LPAREN:
				case LBRACKET:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				if ( inputState.guessing==0 ) {
					
					if(qm!=null) {
					//name = `Name(name.getString() + "__qm__"); 
					name = tom_make_Name(name.getString()); 
					optionList.add(tom_make_MatchingTheory(tom_cons_list_concElementaryTheory(tom_make_TrueAU(),tom_empty_list_concElementaryTheory())));
					}
					nameList = tom_append_list_concTomName(nameList,tom_cons_list_concTomName(name,tom_empty_list_concTomName()));
					
				}
				implicit=args(list,secondOptionList);
				if ( inputState.guessing==0 ) {
					
					if(implicit) {
					result = tom_make_RecordAppl(ASTFactory.makeOptionList(optionList),nameList,ASTFactory.makeSlotList(list),ASTFactory.makeConstraintList(constraintList)
					)
					
					
					
					
					;
					} else {
					result = tom_make_TermAppl(ASTFactory.makeOptionList(optionList),nameList,ASTFactory.makeList(list),ASTFactory.makeConstraintList(constraintList)
					)
					
					
					
					
					;
					}
					if(anti) { result = tom_make_AntiTerm(result); }
					
				}
			}
			else if (((LA(1)==LPAREN))&&(LA(3) == ALTERNATIVE || LA(4) == ALTERNATIVE)) {
				nameList=headSymbolList(optionList);
				implicit=args(list, secondOptionList);
				if ( inputState.guessing==0 ) {
					
					if(implicit) {
					result = tom_make_RecordAppl(ASTFactory.makeOptionList(optionList),nameList,ASTFactory.makeSlotList(list),ASTFactory.makeConstraintList(constraintList)
					)
					
					
					
					
					;
					} else {
					result = tom_make_TermAppl(ASTFactory.makeOptionList(optionList),nameList,ASTFactory.makeList(list),ASTFactory.makeConstraintList(constraintList)
					)
					
					
					
					
					;
					}
					if(anti) { result = tom_make_AntiTerm(result); }
					
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			return result;
		}
		
	public final TomTerm  xmlTerm(
		List<Option> optionList, List<Constraint> constraintList
	) throws RecognitionException, TokenStreamException, TomException {
		TomTerm result;
		
		Token  t = null;
		
		result = null;
		TomTerm arg1, arg2;
		List<Slot> pairSlotList = new LinkedList<Slot>();
		List attributeList = new LinkedList();
		List childs = new LinkedList();
		String keyword = "";
		boolean implicit;
		TomNameList nameList, closingNameList;
		OptionList option = null;
		ConstraintList constraint;  
		
		
		{
		switch ( LA(1)) {
		case XML_START:
		{
			match(XML_START);
			if ( inputState.guessing==0 ) {
				text.append("<");
			}
			nameList=xmlNameList(optionList, true);
			implicit=xmlAttributeList(attributeList);
			if ( inputState.guessing==0 ) {
				
				if(implicit) { optionList.add(tom_make_ImplicitXMLAttribut()); }
				
			}
			{
			switch ( LA(1)) {
			case XML_CLOSE_SINGLETON:
			{
				match(XML_CLOSE_SINGLETON);
				if ( inputState.guessing==0 ) {
					
					text.append("\\>");
					option =  ASTFactory.makeOptionList(optionList);
					
				}
				break;
			}
			case XML_CLOSE:
			{
				match(XML_CLOSE);
				if ( inputState.guessing==0 ) {
					text.append(">");
				}
				implicit=xmlChilds(childs);
				match(XML_START_ENDING);
				if ( inputState.guessing==0 ) {
					text.append("</");
				}
				closingNameList=xmlNameList(optionList, false);
				t = LT(1);
				match(XML_CLOSE);
				if ( inputState.guessing==0 ) {
					text.append(">");
				}
				if ( inputState.guessing==0 ) {
					
					if(!nameList.equals(closingNameList)) {
					StringBuilder found = new StringBuilder();
					StringBuilder expected = new StringBuilder();
					while(!nameList.isEmptyconcTomName()) {
					expected.append("|"+nameList.getHeadconcTomName().getString());
					nameList = nameList.getTailconcTomName();
					}
					while(!closingNameList.isEmptyconcTomName()) {
					found.append("|"+closingNameList.getHeadconcTomName().getString());
					closingNameList = closingNameList.getTailconcTomName();
					}
					// TODO find the orgTrack of the match
					throw new TomException(TomMessage.malformedXMLTerm,
					new Object[]{currentFile(), new Integer(getLine()), 
					"match", expected.substring(1), found.substring(1)});
					}
					if(implicit) {
					// Special case when XMLChilds() is reduced to a singleton
					// when XMLChilds() is reduced to a singleton
					// Appl(...,Name(""),args)
					if(ASTFactory.isExplicitTermList(childs)) {
					childs = ASTFactory.metaEncodeExplicitTermList(symbolTable, (TomTerm)childs.get(0));
					} else {
					optionList.add(tom_make_ImplicitXMLChild());
					}
					}
					option = ASTFactory.makeOptionList(optionList);    
					
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				
				result = tom_make_XMLAppl(option,nameList,ASTFactory.makeList(attributeList),ASTFactory.makeList(childs),ASTFactory.makeConstraintList(constraintList))
				
				
				
				
				;
				
			}
			break;
		}
		case XML_TEXT:
		{
			match(XML_TEXT);
			match(LPAREN);
			arg1=annotatedTerm(true);
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				
				keyword = Constants.TEXT_NODE;
				pairSlotList.add(tom_make_PairSlotAppl(tom_make_Name(Constants.SLOT_DATA),arg1));
				
				optionList.add(tom_make_OriginTracking(tom_make_Name(keyword),getLine(),currentFile()));
				option = ASTFactory.makeOptionList(optionList);
				constraint = ASTFactory.makeConstraintList(constraintList);
				nameList = tom_cons_list_concTomName(tom_make_Name(keyword),tom_empty_list_concTomName());
				result = tom_make_RecordAppl(option,nameList,ASTFactory.makeSlotList(pairSlotList),constraint)
				
				
				;
				
			}
			break;
		}
		case XML_COMMENT:
		{
			match(XML_COMMENT);
			match(LPAREN);
			arg1=termStringIdentifier(null);
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				
				keyword = Constants.COMMENT_NODE;
				pairSlotList.add(tom_make_PairSlotAppl(tom_make_Name(Constants.SLOT_DATA),arg1));
				
				optionList.add(tom_make_OriginTracking(tom_make_Name(keyword),getLine(),currentFile()));
				option = ASTFactory.makeOptionList(optionList);
				constraint = ASTFactory.makeConstraintList(constraintList);
				nameList = tom_cons_list_concTomName(tom_make_Name(keyword),tom_empty_list_concTomName());
				result = tom_make_RecordAppl(option,nameList,ASTFactory.makeSlotList(pairSlotList),constraint)
				
				
				;
				
			}
			break;
		}
		case XML_PROC:
		{
			match(XML_PROC);
			match(LPAREN);
			arg1=termStringIdentifier(null);
			match(COMMA);
			arg2=termStringIdentifier(null);
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				
				keyword = Constants.PROCESSING_INSTRUCTION_NODE;
				pairSlotList.add(tom_make_PairSlotAppl(tom_make_Name(Constants.SLOT_TARGET),arg1));
				pairSlotList.add(tom_make_PairSlotAppl(tom_make_Name(Constants.SLOT_DATA),arg2));
				
				optionList.add(tom_make_OriginTracking(tom_make_Name(keyword),getLine(),currentFile()));
				option = ASTFactory.makeOptionList(optionList);
				constraint = ASTFactory.makeConstraintList(constraintList);
				nameList = tom_cons_list_concTomName(tom_make_Name(keyword),tom_empty_list_concTomName());
				result = tom_make_RecordAppl(option,nameList,ASTFactory.makeSlotList(pairSlotList),constraint)
				
				
				;
				
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		return result;
	}
	
	public final TomTerm  variableStar(
		List<Option> optionList, List<Constraint> constraintList
	) throws RecognitionException, TokenStreamException {
		TomTerm result;
		
		Token  name1 = null;
		Token  name2 = null;
		Token  t = null;
		
		result = null; 
		String name = null;
		int line = 0;
		OptionList options = null;
		ConstraintList constraints = null;
		
		
		{
		{
		switch ( LA(1)) {
		case ALL_ID:
		{
			name1 = LT(1);
			match(ALL_ID);
			if ( inputState.guessing==0 ) {
				
				name = name1.getText();
				line = name1.getLine();
				
			}
			break;
		}
		case UNDERSCORE:
		{
			name2 = LT(1);
			match(UNDERSCORE);
			if ( inputState.guessing==0 ) {
				
				name = name2.getText();
				line = name2.getLine();
				
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		t = LT(1);
		match(STAR);
		if ( inputState.guessing==0 ) {
			
			text.append(name);
			text.append(t.getText());
			
			// setting line number for origin tracking
			// in %rule construct
			setLastLine(t.getLine());
			
			optionList.add(tom_make_OriginTracking(tom_make_Name(name),line,currentFile()));
			options = ASTFactory.makeOptionList(optionList);
			constraints = ASTFactory.makeConstraintList(constraintList);
			if(name1 == null) {
			result = tom_make_UnamedVariableStar(options,SymbolTable.TYPE_UNKNOWN,constraints)
			
			
			
			;
			} else {
			result = tom_make_VariableStar(options,tom_make_Name(name),SymbolTable.TYPE_UNKNOWN,constraints)
			
			
			
			
			;
			}
			
		}
		}
		return result;
	}
	
	public final TomTerm  unamedVariable(
		List<Option> optionList, List<Constraint> constraintList
	) throws RecognitionException, TokenStreamException {
		TomTerm result;
		
		Token  t = null;
		
		result = null;
		OptionList options = null;
		ConstraintList constraints = null;
		
		
		{
		t = LT(1);
		match(UNDERSCORE);
		if ( inputState.guessing==0 ) {
			
			text.append(t.getText());
			setLastLine(t.getLine());
			
			optionList.add(tom_make_OriginTracking(tom_make_Name(t.getText()),t.getLine(),currentFile()));
			options = ASTFactory.makeOptionList(optionList);
			constraints = ASTFactory.makeConstraintList(constraintList);
			result = tom_make_UnamedVariable(options,SymbolTable.TYPE_UNKNOWN,constraints);
			
		}
		}
		return result;
	}
	
	public final TomName  headSymbol(
		List<Option> optionList
	) throws RecognitionException, TokenStreamException {
		TomName result;
		
		Token  i = null;
		
		//String buf="";
		// TomName name = null;
		result = null; 
		
		
		{
		i = LT(1);
		match(ALL_ID);
		if ( inputState.guessing==0 ) {
			
			String name = i.getText();
					int line = i.getLine();
					text.append(name);
					setLastLine(line);
					result = tom_make_Name(name);
					optionList.add(tom_make_OriginTracking(result,line,currentFile()));
				
		}
		}
		return result;
	}
	
	public final TomNameList  headConstantList(
		List<Option> optionList
	) throws RecognitionException, TokenStreamException {
		TomNameList result;
		
		
		result = tom_empty_list_concTomName();
		TomName name = null;
		
		
		name=headConstant(optionList);
		if ( inputState.guessing==0 ) {
			result = tom_append_list_concTomName(result,tom_cons_list_concTomName(name,tom_empty_list_concTomName()));
		}
		{
		_loop161:
		do {
			if ((LA(1)==ALTERNATIVE)) {
				match(ALTERNATIVE);
				if ( inputState.guessing==0 ) {
					text.append('|');
				}
				name=headConstant(optionList);
				if ( inputState.guessing==0 ) {
					result = tom_append_list_concTomName(result,tom_cons_list_concTomName(name,tom_empty_list_concTomName()));
				}
			}
			else {
				break _loop161;
			}
			
		} while (true);
		}
		return result;
	}
	
	public final boolean  args(
		List list, List<Option> optionList
	) throws RecognitionException, TokenStreamException, TomException {
		boolean result;
		
		Token  t1 = null;
		Token  t2 = null;
		Token  t3 = null;
		Token  t4 = null;
		
		result = false;
		
		
		{
		switch ( LA(1)) {
		case LPAREN:
		{
			t1 = LT(1);
			match(LPAREN);
			if ( inputState.guessing==0 ) {
				text.append('(');
			}
			{
			switch ( LA(1)) {
			case NUM_INT:
			case CHARACTER:
			case STRING:
			case NUM_FLOAT:
			case NUM_LONG:
			case NUM_DOUBLE:
			case LPAREN:
			case ALL_ID:
			case XML_START:
			case ANTI_SYM:
			case XML_TEXT:
			case XML_COMMENT:
			case XML_PROC:
			case LBRACKET:
			case UNDERSCORE:
			{
				termList(list);
				break;
			}
			case RPAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			t2 = LT(1);
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				
				// setting line number for origin tracking
				// in %rule construct
				setLastLine(t2.getLine());
				
				text.append(t2.getText());
				
				result = false;
				optionList.add(tom_make_OriginTracking(tom_make_Name(""),t1.getLine(),currentFile()));
				
			}
			break;
		}
		case LBRACKET:
		{
			t3 = LT(1);
			match(LBRACKET);
			if ( inputState.guessing==0 ) {
				text.append('[');
			}
			{
			switch ( LA(1)) {
			case ALL_ID:
			{
				pairList(list);
				break;
			}
			case RBRACKET:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			t4 = LT(1);
			match(RBRACKET);
			if ( inputState.guessing==0 ) {
				
				// setting line number for origin tracking
				// in %rule construct
				setLastLine(t4.getLine());
				text.append(t4.getText());
				
				result = true;
				optionList.add(tom_make_OriginTracking(tom_make_Name(""),t3.getLine(),currentFile()));
				
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		return result;
	}
	
	public final TomNameList  headSymbolList(
		List<Option> optionList
	) throws RecognitionException, TokenStreamException {
		TomNameList result;
		
		Token  t = null;
		
		result = tom_empty_list_concTomName();
		TomName name = null;
		
		
		{
		match(LPAREN);
		if ( inputState.guessing==0 ) {
			text.append('(');
		}
		name=headSymbolOrConstant(optionList);
		if ( inputState.guessing==0 ) {
			
				result = tom_append_list_concTomName(result,tom_cons_list_concTomName(name,tom_empty_list_concTomName()));            	
			
		}
		match(ALTERNATIVE);
		if ( inputState.guessing==0 ) {
			text.append('|');
		}
		name=headSymbolOrConstant(optionList);
		if ( inputState.guessing==0 ) {
			
				result = tom_append_list_concTomName(result,tom_cons_list_concTomName(name,tom_empty_list_concTomName()));                
			
		}
		{
		_loop154:
		do {
			if ((LA(1)==ALTERNATIVE)) {
				match(ALTERNATIVE);
				if ( inputState.guessing==0 ) {
					text.append('|');
				}
				name=headSymbolOrConstant(optionList);
				if ( inputState.guessing==0 ) {
					
						result = tom_append_list_concTomName(result,tom_cons_list_concTomName(name,tom_empty_list_concTomName()));                    
					
				}
			}
			else {
				break _loop154;
			}
			
		} while (true);
		}
		t = LT(1);
		match(RPAREN);
		if ( inputState.guessing==0 ) {
			
			text.append(t.getText());
			setLastLine(t.getLine());
			
		}
		}
		return result;
	}
	
	public final TomTerm  implicitNotationPlainTerm(
		TomName astLabeledName, TomName astAnnotedName, int line, 
                          List list, List<Option> secondOptionList,
                          List<Option> optionList, List<Constraint> constraintList,
                          boolean anti
	) throws RecognitionException, TokenStreamException, TomException {
		TomTerm result;
		
		
		TomNameList nameList = null;
		result = null;
		
		if(astLabeledName != null) {
		constraintList.add(ASTFactory.makeStorePosition(astLabeledName, line, currentFile()));
		}
		if(astAnnotedName != null) {
		constraintList.add(ASTFactory.makeAssignTo(astAnnotedName, line, currentFile()));
		}
		
		
		
		args(list,secondOptionList);
		if ( inputState.guessing==0 ) {
			
			nameList = tom_cons_list_concTomName(tom_make_Name(""),tom_empty_list_concTomName());
			optionList.addAll(secondOptionList);
			result = tom_make_TermAppl(ASTFactory.makeOptionList(optionList),nameList,ASTFactory.makeList(list),ASTFactory.makeConstraintList(constraintList)
			)
			
			
			
			
			;
			if(anti) { result = tom_make_AntiTerm(result); }
			
		}
		return result;
	}
	
	public final TomNameList  xmlNameList(
		List<Option> optionList, boolean needOrgTrack
	) throws RecognitionException, TokenStreamException, TomException {
		TomNameList result;
		
		Token  a = null;
		Token  name = null;
		Token  name2 = null;
		Token  b = null;
		Token  name3 = null;
		Token  c = null;
		Token  name4 = null;
		
		result = tom_empty_list_concTomName();
		StringBuilder XMLName = new StringBuilder("");
		int decLine = 0;
		boolean anti = false;
		
		
		{
		switch ( LA(1)) {
		case ALL_ID:
		case ANTI_SYM:
		{
			{
			_loop116:
			do {
				if ((LA(1)==ANTI_SYM)) {
					a = LT(1);
					match(ANTI_SYM);
					if ( inputState.guessing==0 ) {
						anti = !anti;
					}
				}
				else {
					break _loop116;
				}
				
			} while (true);
			}
			name = LT(1);
			match(ALL_ID);
			if ( inputState.guessing==0 ) {
				
				text.append(name.getText());
				XMLName.append(name.getText());
				decLine = name.getLine();                
				if (anti) { 
					result =  tom_cons_list_concTomName(tom_make_AntiName(tom_make_Name(name.getText())),tom_empty_list_concTomName());
				}else{
					               	result = tom_cons_list_concTomName(tom_make_Name(name.getText()),tom_empty_list_concTomName());
				}
				
			}
			break;
		}
		case UNDERSCORE:
		{
			name2 = LT(1);
			match(UNDERSCORE);
			if ( inputState.guessing==0 ) {
				
				text.append(name2.getText());
				XMLName.append(name2.getText());
				decLine = name2.getLine();
				result = tom_cons_list_concTomName(tom_make_Name(name2.getText()),tom_empty_list_concTomName());
				
			}
			break;
		}
		case LPAREN:
		{
			match(LPAREN);
			{
			_loop118:
			do {
				if ((LA(1)==ANTI_SYM)) {
					b = LT(1);
					match(ANTI_SYM);
					if ( inputState.guessing==0 ) {
						anti = !anti;
					}
				}
				else {
					break _loop118;
				}
				
			} while (true);
			}
			name3 = LT(1);
			match(ALL_ID);
			if ( inputState.guessing==0 ) {
				
				text.append(name3.getText());
				XMLName.append(name3.getText());
				decLine = name3.getLine();
				if (anti) { 
					result =  tom_cons_list_concTomName(tom_make_AntiName(tom_make_Name(name3.getText())),tom_empty_list_concTomName());
				}else{
					result = tom_cons_list_concTomName(tom_make_Name(name3.getText()),tom_empty_list_concTomName());
				}
				
				
			}
			{
			_loop122:
			do {
				if ((LA(1)==ALTERNATIVE)) {
					match(ALTERNATIVE);
					{
					_loop121:
					do {
						if ((LA(1)==ANTI_SYM)) {
							c = LT(1);
							match(ANTI_SYM);
							if ( inputState.guessing==0 ) {
								anti = !anti;
							}
						}
						else {
							break _loop121;
						}
						
					} while (true);
					}
					name4 = LT(1);
					match(ALL_ID);
					if ( inputState.guessing==0 ) {
						
						text.append("|"+name4.getText());
						XMLName.append("|"+name4.getText());
						if (anti) { 
							result = tom_append_list_concTomName(result,tom_cons_list_concTomName(tom_make_AntiName(tom_make_Name(name4.getText())),tom_empty_list_concTomName()));
						}else{
							result = tom_append_list_concTomName(result,tom_cons_list_concTomName(tom_make_Name(name4.getText()),tom_empty_list_concTomName()));
						}
						
					}
				}
				else {
					break _loop122;
				}
				
			} while (true);
			}
			match(RPAREN);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			
			if(needOrgTrack) {
			optionList.add(tom_make_OriginTracking(tom_make_Name(XMLName.toString()),decLine,currentFile()));
			}
			
		}
		return result;
	}
	
	public final boolean  xmlAttributeList(
		List<TomTerm> list
	) throws RecognitionException, TokenStreamException, TomException {
		boolean result;
		
		
		result = false;
		TomTerm term;
		
		
		{
		switch ( LA(1)) {
		case LBRACKET:
		{
			match(LBRACKET);
			if ( inputState.guessing==0 ) {
				text.append("[");
			}
			{
			switch ( LA(1)) {
			case ALL_ID:
			case UNDERSCORE:
			{
				term=xmlAttribute();
				if ( inputState.guessing==0 ) {
					list.add(term);
				}
				{
				_loop95:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						if ( inputState.guessing==0 ) {
							text.append("(");
						}
						term=xmlAttribute();
						if ( inputState.guessing==0 ) {
							list.add(term);
						}
					}
					else {
						break _loop95;
					}
					
				} while (true);
				}
				break;
			}
			case RBRACKET:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(RBRACKET);
			if ( inputState.guessing==0 ) {
				
				text.append("]");
				result = true;
				
			}
			break;
		}
		case LPAREN:
		{
			match(LPAREN);
			if ( inputState.guessing==0 ) {
				text.append("(");
			}
			{
			switch ( LA(1)) {
			case ALL_ID:
			case UNDERSCORE:
			{
				term=xmlAttribute();
				if ( inputState.guessing==0 ) {
					list.add(term);
				}
				{
				_loop98:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						if ( inputState.guessing==0 ) {
							text.append(",");
						}
						term=xmlAttribute();
						if ( inputState.guessing==0 ) {
							list.add(term);
						}
					}
					else {
						break _loop98;
					}
					
				} while (true);
				}
				break;
			}
			case RPAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				
				text.append(")");
				result = false;
				
			}
			break;
		}
		case ALL_ID:
		case XML_CLOSE:
		case XML_CLOSE_SINGLETON:
		case UNDERSCORE:
		{
			{
			_loop100:
			do {
				if (((LA(1)==ALL_ID||LA(1)==UNDERSCORE))&&(LA(1) != XML_CLOSE)) {
					term=xmlAttribute();
					if ( inputState.guessing==0 ) {
						list.add(term);
					}
				}
				else {
					break _loop100;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				result = true;
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		return result;
	}
	
	public final boolean  xmlChilds(
		List list
	) throws RecognitionException, TokenStreamException, TomException {
		boolean result;
		
		
		result = false;
		List childs = new LinkedList();
		Iterator it;
		
		
		{
		if (((LA(1)==LBRACKET))&&(LA(1) == LBRACKET)) {
			result=implicitTermList(childs);
		}
		else if ((_tokenSet_4.member(LA(1)))) {
			result=xmlTermList(childs);
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		if ( inputState.guessing==0 ) {
			
			it = childs.iterator();
			while(it.hasNext()) {
			list.add(ASTFactory.metaEncodeXMLAppl(symbolTable,(TomTerm)it.next()));
			}
			
		}
		return result;
	}
	
	public final TomTerm  termStringIdentifier(
		List<Option> options
	) throws RecognitionException, TokenStreamException, TomException {
		TomTerm result;
		
		Token  nameID = null;
		Token  nameString = null;
		
		result = null;
		List<Option> optionList = (options==null)?new LinkedList<Option>():options;
		OptionList option = null;
		TomNameList nameList = null;
		
		
		{
		switch ( LA(1)) {
		case ALL_ID:
		{
			nameID = LT(1);
			match(ALL_ID);
			if ( inputState.guessing==0 ) {
				
				text.append(nameID.getText());
				optionList.add(tom_make_OriginTracking(tom_make_Name(nameID.getText()),nameID.getLine(),currentFile()));
				option = ASTFactory.makeOptionList(optionList);
				result = tom_make_Variable(option,tom_make_Name(nameID.getText()),SymbolTable.TYPE_UNKNOWN,tom_empty_list_concConstraint());
				
			}
			break;
		}
		case STRING:
		{
			nameString = LT(1);
			match(STRING);
			if ( inputState.guessing==0 ) {
				
				text.append(nameString.getText());
				optionList.add(tom_make_OriginTracking(tom_make_Name(nameString.getText()),nameString.getLine(),currentFile()));
				option = ASTFactory.makeOptionList(optionList);
				ASTFactory.makeStringSymbol(symbolTable,nameString.getText(),optionList);
				nameList = tom_cons_list_concTomName(tom_make_Name(nameString.getText()),tom_empty_list_concTomName());
				result = tom_make_TermAppl(option,nameList,tom_empty_list_concTomTerm(),tom_empty_list_concConstraint());
				
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		return result;
	}
	
	public final TomTerm  xmlAttribute() throws RecognitionException, TokenStreamException, TomException {
		TomTerm result;
		
		Token  id = null;
		Token  anno2 = null;
		Token  a = null;
		Token  anno1 = null;
		Token  e = null;
		Token  anno3 = null;
		Token  b = null;
		
		result = null;
		List<Slot> slotList = new LinkedList<Slot>();
		TomTerm term = null;
		TomTerm termName = null;
		String name;
		OptionList option = null;
		ConstraintList constraint;
		List<Option> optionList = new LinkedList<Option>();
		List<Constraint> constraintList = new LinkedList<Constraint>();
		List anno1ConstraintList = new LinkedList();
		List anno2ConstraintList = new LinkedList();
		List<Option> optionListAnno2 = new LinkedList<Option>();
		TomNameList nameList;
		boolean varStar = false;
		boolean anti = false;
		
		
		{
		if (((LA(1)==ALL_ID||LA(1)==UNDERSCORE))&&(LA(2) == STAR)) {
			result=variableStar(optionList,constraintList);
			if ( inputState.guessing==0 ) {
				varStar = true;
			}
		}
		else if (((LA(1)==ALL_ID))&&(LA(2) == EQUAL)) {
			id = LT(1);
			match(ALL_ID);
			match(EQUAL);
			if ( inputState.guessing==0 ) {
				text.append(id.getText()+"=");
			}
			{
			if (((LA(1)==ALL_ID))&&(LA(2) == AT)) {
				anno2 = LT(1);
				match(ALL_ID);
				match(AT);
				if ( inputState.guessing==0 ) {
					
					text.append(anno2.getText()+"@");
					anno2ConstraintList.add(ASTFactory.makeAssignTo(tom_make_Name(anno2.getText()), getLine(), currentFile()));
					
				}
			}
			else if ((_tokenSet_5.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			_loop105:
			do {
				if ((LA(1)==ANTI_SYM)) {
					a = LT(1);
					match(ANTI_SYM);
					if ( inputState.guessing==0 ) {
						anti = !anti;
					}
				}
				else {
					break _loop105;
				}
				
			} while (true);
			}
			term=unamedVariableOrTermStringIdentifier(optionListAnno2,anno2ConstraintList);
			if ( inputState.guessing==0 ) {
				
				name = ASTFactory.encodeXMLString(symbolTable,id.getText());
				nameList = tom_cons_list_concTomName(tom_make_Name(name),tom_empty_list_concTomName());
				termName = tom_make_TermAppl(tom_empty_list_concOption(),nameList,tom_empty_list_concTomTerm(),tom_empty_list_concConstraint());                
				
			}
		}
		else if ((LA(1)==ALL_ID||LA(1)==UNDERSCORE)) {
			{
			switch ( LA(1)) {
			case ALL_ID:
			{
				anno1 = LT(1);
				match(ALL_ID);
				match(AT);
				if ( inputState.guessing==0 ) {
					
					text.append(anno1.getText()+"@");
					anno1ConstraintList.add(ASTFactory.makeAssignTo(tom_make_Name(anno1.getText()), getLine(), currentFile()));
					
				}
				break;
			}
			case UNDERSCORE:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			termName=unamedVariable(optionList,anno1ConstraintList);
			e = LT(1);
			match(EQUAL);
			if ( inputState.guessing==0 ) {
				text.append("=");
			}
			{
			if (((LA(1)==ALL_ID))&&(LA(2) == AT)) {
				anno3 = LT(1);
				match(ALL_ID);
				match(AT);
				if ( inputState.guessing==0 ) {
					
					text.append(anno3.getText()+"@");
					anno2ConstraintList.add(ASTFactory.makeAssignTo(tom_make_Name(anno3.getText()), getLine(), currentFile()));
					
				}
			}
			else if ((_tokenSet_5.member(LA(1)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			_loop109:
			do {
				if ((LA(1)==ANTI_SYM)) {
					b = LT(1);
					match(ANTI_SYM);
					if ( inputState.guessing==0 ) {
						anti = !anti;
					}
				}
				else {
					break _loop109;
				}
				
			} while (true);
			}
			term=unamedVariableOrTermStringIdentifier(optionListAnno2,anno2ConstraintList);
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		if ( inputState.guessing==0 ) {
			
			if (!varStar) {
				
				if (anti){
					term = tom_make_AntiTerm(term);
				}
				
			slotList.add(tom_make_PairSlotAppl(tom_make_Name(Constants.SLOT_NAME),termName));
			// we add the specif value : _
			slotList.add(tom_make_PairSlotAppl(tom_make_Name(Constants.SLOT_SPECIFIED),tom_make_UnamedVariable(tom_empty_list_concOption(),SymbolTable.TYPE_UNKNOWN,tom_empty_list_concConstraint())));
			// no longer necessary ot metaEncode Strings in attributes
			slotList.add(tom_make_PairSlotAppl(tom_make_Name(Constants.SLOT_VALUE),term));
			optionList.add(tom_make_OriginTracking(tom_make_Name(Constants.ATTRIBUTE_NODE),getLine(),currentFile()));
			option = ASTFactory.makeOptionList(optionList);            
			constraint = ASTFactory.makeConstraintList(constraintList);
			
			nameList = tom_cons_list_concTomName(tom_make_Name(Constants.ATTRIBUTE_NODE),tom_empty_list_concTomName());
			result = tom_make_RecordAppl(option,nameList,ASTFactory.makeSlotList(slotList),constraint)
			
			
			;
			}   
			
		}
		return result;
	}
	
	public final TomTerm  unamedVariableOrTermStringIdentifier(
		List<Option> options, List<Constraint> constraintList
	) throws RecognitionException, TokenStreamException, TomException {
		TomTerm result;
		
		Token  nameID = null;
		Token  nameString = null;
		
		result = null;
		List<Option> optionList = (options==null)?new LinkedList<Option>():options;
		OptionList option = null;
		TomNameList nameList = null;
		ConstraintList constraints = null;
		
		
		{
		switch ( LA(1)) {
		case UNDERSCORE:
		{
			result=unamedVariable(optionList, constraintList);
			break;
		}
		case ALL_ID:
		{
			nameID = LT(1);
			match(ALL_ID);
			if ( inputState.guessing==0 ) {
				
				text.append(nameID.getText());
				optionList.add(tom_make_OriginTracking(tom_make_Name(nameID.getText()),nameID.getLine(),currentFile()));
				option = ASTFactory.makeOptionList(optionList);
				constraints = ASTFactory.makeConstraintList(constraintList);
				result = tom_make_Variable(option,tom_make_Name(nameID.getText()),SymbolTable.TYPE_UNKNOWN,constraints);
				
			}
			break;
		}
		case STRING:
		{
			nameString = LT(1);
			match(STRING);
			if ( inputState.guessing==0 ) {
				
				text.append(nameString.getText());
				optionList.add(tom_make_OriginTracking(tom_make_Name(nameString.getText()),nameString.getLine(),currentFile()));
				option = ASTFactory.makeOptionList(optionList);
				ASTFactory.makeStringSymbol(symbolTable,nameString.getText(),optionList);
				nameList = tom_cons_list_concTomName(tom_make_Name(nameString.getText()),tom_empty_list_concTomName());
				constraints = ASTFactory.makeConstraintList(constraintList);
				result = tom_make_TermAppl(option,nameList,tom_empty_list_concTomTerm(),constraints);
				
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		return result;
	}
	
	public final boolean  xmlTermList(
		List<TomTerm> list
	) throws RecognitionException, TokenStreamException, TomException {
		boolean result;
		
		
		result = false;
		TomTerm term;
		
		
		{
		_loop112:
		do {
			if ((_tokenSet_0.member(LA(1)))) {
				term=annotatedTerm(true);
				if ( inputState.guessing==0 ) {
					list.add(term);
				}
			}
			else {
				break _loop112;
			}
			
		} while (true);
		}
		if ( inputState.guessing==0 ) {
			result = true;
		}
		return result;
	}
	
	public final boolean  implicitTermList(
		List<TomTerm> list
	) throws RecognitionException, TokenStreamException, TomException {
		boolean result;
		
		
		result = false;
		TomTerm term;
		
		
		{
		match(LBRACKET);
		if ( inputState.guessing==0 ) {
			text.append("[");
		}
		{
		switch ( LA(1)) {
		case NUM_INT:
		case CHARACTER:
		case STRING:
		case NUM_FLOAT:
		case NUM_LONG:
		case NUM_DOUBLE:
		case LPAREN:
		case ALL_ID:
		case XML_START:
		case ANTI_SYM:
		case XML_TEXT:
		case XML_COMMENT:
		case XML_PROC:
		case LBRACKET:
		case UNDERSCORE:
		{
			term=annotatedTerm(true);
			if ( inputState.guessing==0 ) {
				list.add(term);
			}
			{
			_loop131:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					if ( inputState.guessing==0 ) {
						text.append(",");
					}
					term=annotatedTerm(true);
					if ( inputState.guessing==0 ) {
						list.add(term);
					}
				}
				else {
					break _loop131;
				}
				
			} while (true);
			}
			break;
		}
		case RBRACKET:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(RBRACKET);
		if ( inputState.guessing==0 ) {
			
			text.append("]");
			result=true;
			
		}
		}
		return result;
	}
	
	public final void termList(
		List<TomTerm> list
	) throws RecognitionException, TokenStreamException, TomException {
		
		
		TomTerm term = null;
		
		
		{
		term=annotatedTerm(true);
		if ( inputState.guessing==0 ) {
			list.add(term);
		}
		{
		_loop141:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				if ( inputState.guessing==0 ) {
					text.append(',');
				}
				term=annotatedTerm(true);
				if ( inputState.guessing==0 ) {
					list.add(term);
				}
			}
			else {
				break _loop141;
			}
			
		} while (true);
		}
		}
	}
	
	public final void pairList(
		List<Slot> list
	) throws RecognitionException, TokenStreamException, TomException {
		
		Token  name = null;
		Token  name2 = null;
		
		TomTerm term = null;
		
		
		{
		name = LT(1);
		match(ALL_ID);
		match(EQUAL);
		if ( inputState.guessing==0 ) {
			
			text.append(name.getText());
			text.append('=');
			
		}
		term=annotatedTerm(true);
		if ( inputState.guessing==0 ) {
			list.add(tom_make_PairSlotAppl(tom_make_Name(name.getText()),term));
		}
		{
		_loop145:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				if ( inputState.guessing==0 ) {
					text.append(',');
				}
				name2 = LT(1);
				match(ALL_ID);
				match(EQUAL);
				if ( inputState.guessing==0 ) {
					
					text.append(name2.getText());
					text.append('=');
					
				}
				term=annotatedTerm(true);
				if ( inputState.guessing==0 ) {
					list.add(tom_make_PairSlotAppl(tom_make_Name(name2.getText()),term));
				}
			}
			else {
				break _loop145;
			}
			
		} while (true);
		}
		}
	}
	
	public final TomName  headSymbolOrConstant(
		List<Option> optionList
	) throws RecognitionException, TokenStreamException {
		TomName result;
		
		
		result = null;
		
		
		{
		switch ( LA(1)) {
		case ALL_ID:
		{
			result=headSymbol(optionList);
			break;
		}
		case NUM_INT:
		case CHARACTER:
		case STRING:
		case NUM_FLOAT:
		case NUM_LONG:
		case NUM_DOUBLE:
		{
			result=headConstant(optionList);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		return result;
	}
	
	public final TomName  headConstant(
		List<Option> optionList
	) throws RecognitionException, TokenStreamException {
		TomName result;
		
		
		result = null;
		Token t;
		
		
		t=constant();
		if ( inputState.guessing==0 ) {
			
				String name = t.getText();        
				int line = t.getLine();
				text.append(name);
				setLastLine(line);
				result = tom_make_Name(name);
				optionList.add(tom_make_OriginTracking(result,line,currentFile()));
			
				switch(t.getType()) {
					case NUM_INT:
						ASTFactory.makeIntegerSymbol(symbolTable,name,optionList);
						break;
					case NUM_LONG:
						ASTFactory.makeLongSymbol(symbolTable,name,optionList);
						break;
					case CHARACTER:
						ASTFactory.makeCharSymbol(symbolTable,name,optionList);
						break;
					case NUM_DOUBLE:
						ASTFactory.makeDoubleSymbol(symbolTable,name,optionList);
						break;
					case STRING:
						ASTFactory.makeStringSymbol(symbolTable,name,optionList);
						break;
					default:
				}
			
		}
		return result;
	}
	
	public final Declaration  operator() throws RecognitionException, TokenStreamException, TomException {
		Declaration result;
		
		Token  type = null;
		Token  name = null;
		Token  slotName = null;
		Token  typeArg = null;
		Token  slotName2 = null;
		Token  typeArg2 = null;
		Token  t = null;
		
		result=null;
		Option ot = null;
		TomTypeList types = tom_empty_list_concTomType();
		List<Option> options = new LinkedList<Option>();
		List<TomName> slotNameList = new LinkedList<TomName>();
		List<PairNameDecl> pairNameDeclList = new LinkedList<PairNameDecl>();
		TomName astName = null;
		String stringSlotName = null;
		Declaration attribute;
		
		
		type = LT(1);
		match(ALL_ID);
		name = LT(1);
		match(ALL_ID);
		if ( inputState.guessing==0 ) {
			
			ot = tom_make_OriginTracking(tom_make_Name(name.getText()),name.getLine(),currentFile());
			options.add(ot);
			
		}
		{
		match(LPAREN);
		{
		switch ( LA(1)) {
		case ALL_ID:
		{
			slotName = LT(1);
			match(ALL_ID);
			match(COLON);
			typeArg = LT(1);
			match(ALL_ID);
			if ( inputState.guessing==0 ) {
				
				stringSlotName = slotName.getText(); 
				astName = tom_make_Name(stringSlotName);
				slotNameList.add(astName); 
				pairNameDeclList.add(tom_make_PairNameDecl(astName,tom_make_EmptyDeclaration())); 
				types = tom_append_list_concTomType(types,tom_cons_list_concTomType(tom_make_TomTypeAlone(typeArg.getText()),tom_empty_list_concTomType()));
				
			}
			{
			_loop167:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					slotName2 = LT(1);
					match(ALL_ID);
					match(COLON);
					typeArg2 = LT(1);
					match(ALL_ID);
					if ( inputState.guessing==0 ) {
						
						stringSlotName = slotName2.getText(); 
						astName = ASTFactory.makeName(stringSlotName);
						if(slotNameList.indexOf(astName) != -1) {
						getLogger().log(new PlatformLogRecord(Level.SEVERE, TomMessage.repeatedSlotName,
						new Object[]{stringSlotName},
						currentFile(), getLine()));
						}
						slotNameList.add(astName); 
						pairNameDeclList.add(tom_make_PairNameDecl(tom_make_Name(stringSlotName),tom_make_EmptyDeclaration())); 
						types = tom_append_list_concTomType(types,tom_cons_list_concTomType(tom_make_TomTypeAlone(typeArg2.getText()),tom_empty_list_concTomType()));
						
					}
				}
				else {
					break _loop167;
				}
				
			} while (true);
			}
			break;
		}
		case RPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(RPAREN);
		}
		{
		match(LBRACE);
		if ( inputState.guessing==0 ) {
			
			astName = tom_make_Name(name.getText());
			
		}
		{
		_loop170:
		do {
			switch ( LA(1)) {
			case MAKE:
			{
				attribute=keywordMake(name.getText(),tom_make_TomTypeAlone(type.getText()),types);
				if ( inputState.guessing==0 ) {
					options.add(tom_make_DeclarationToOption(attribute));
				}
				break;
			}
			case GET_SLOT:
			{
				attribute=keywordGetSlot(astName,type.getText());
				if ( inputState.guessing==0 ) {
					
					TomName sName = attribute.getSlotName();
					/*
					* ensure that sName appears in slotNameList, only once
					* ensure that sName has not already been generated
					*/
					//System.out.println("slotNameList = " + slotNameList);
					//System.out.println("sName      = " + sName);
					
					TomMessage msg = null;
					int index = slotNameList.indexOf(sName);
					if(index == -1) {
					msg = TomMessage.errorIncompatibleSlotDecl;
					} else {
					PairNameDecl pair = pairNameDeclList.get(index);
					{{if (tom_is_sort_PairNameDecl(pair)) {if (tom_is_fun_sym_PairNameDecl((( tom.engine.adt.tomslot.types.PairNameDecl )pair))) {
					
					if(tom_get_slot_PairNameDecl_SlotDecl((( tom.engine.adt.tomslot.types.PairNameDecl )pair))!= tom_make_EmptyDeclaration()) {
					msg = TomMessage.errorTwoSameSlotDecl;
					}
					}}}}
					
					}
					if(msg != null) {
					getLogger().log(new PlatformLogRecord(Level.SEVERE, msg,
					new Object[]{currentFile(), new Integer(attribute.getOrgTrack().getLine()),
					"%op "+type.getText(), new Integer(ot.getLine()), sName.getString()} ,
					currentFile(), getLine()));
					} else {
					pairNameDeclList.set(index,tom_make_PairNameDecl(sName,attribute));
					}
					
				}
				break;
			}
			case IS_FSYM:
			{
				attribute=keywordIsFsym(astName,type.getText());
				if ( inputState.guessing==0 ) {
					options.add(tom_make_DeclarationToOption(attribute));
				}
				break;
			}
			default:
			{
				break _loop170;
			}
			}
		} while (true);
		}
		t = LT(1);
		match(RBRACE);
		}
		if ( inputState.guessing==0 ) {
			
			
			//System.out.println("pairNameDeclList = " + pairNameDeclList);
			
			TomSymbol astSymbol = ASTFactory.makeSymbol(name.getText(), tom_make_TomTypeAlone(type.getText()), types, ASTFactory.makePairNameDeclList(pairNameDeclList), options);
			putSymbol(name.getText(),astSymbol);
			result = tom_make_SymbolDecl(astName);
			updatePosition(t.getLine(),t.getColumn());
			selector().pop(); 
			
		}
		return result;
	}
	
	public final Declaration  keywordMake(
		String opname, TomType returnType, TomTypeList types
	) throws RecognitionException, TokenStreamException, TomException {
		Declaration result;
		
		Token  t = null;
		Token  nameArg = null;
		Token  nameArg2 = null;
		Token  l = null;
		
		result = null;
		Option ot = null;
		TomList args = tom_empty_list_concTomTerm();
		ArrayList<String> varnameList = new ArrayList<String>();
		int index = 0;
		TomType type;
		int nbTypes = types.length();
		
		
		{
		t = LT(1);
		match(MAKE);
		if ( inputState.guessing==0 ) {
			ot = tom_make_OriginTracking(tom_make_Name(t.getText()),t.getLine(),currentFile());
		}
		{
		switch ( LA(1)) {
		case LPAREN:
		{
			match(LPAREN);
			{
			switch ( LA(1)) {
			case ALL_ID:
			{
				nameArg = LT(1);
				match(ALL_ID);
				if ( inputState.guessing==0 ) {
					
					if( !(nbTypes > 0) ) {
					type = tom_make_EmptyType();
					} else {
					type = TomBase.elementAt(types,index++);
					}
					Option info1 = tom_make_OriginTracking(tom_make_Name(nameArg.getText()),nameArg.getLine(),currentFile());  
					OptionList option1 = tom_cons_list_concOption(info1,tom_empty_list_concOption());
					
					args = tom_append_list_concTomTerm(args,tom_cons_list_concTomTerm(tom_make_Variable(option1,tom_make_Name(nameArg.getText()),type,tom_empty_list_concConstraint()
					),tom_empty_list_concTomTerm()))
					
					
					
					;
					varnameList.add(nameArg.getText());
					
				}
				{
				_loop208:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						nameArg2 = LT(1);
						match(ALL_ID);
						if ( inputState.guessing==0 ) {
							
							if( index >= nbTypes ) {
							type = tom_make_EmptyType();
							} else {
							type = TomBase.elementAt(types,index++);
							}
							Option info2 = tom_make_OriginTracking(tom_make_Name(nameArg2.getText()),nameArg2.getLine(),currentFile());
							OptionList option2 = tom_cons_list_concOption(info2,tom_empty_list_concOption());
							
							args = tom_append_list_concTomTerm(args,tom_cons_list_concTomTerm(tom_make_Variable(option2,tom_make_Name(nameArg2.getText()),type,tom_empty_list_concConstraint()
							),tom_empty_list_concTomTerm()))
							
							
							
							;
							varnameList.add(nameArg2.getText());
							
						}
					}
					else {
						break _loop208;
					}
					
				} while (true);
				}
				break;
			}
			case RPAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(RPAREN);
			break;
		}
		case LBRACE:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		l = LT(1);
		match(LBRACE);
		if ( inputState.guessing==0 ) {
			
			updatePosition(t.getLine(),t.getColumn());
			selector().push("targetlexer");
			List<TomTerm> blockList = new LinkedList<TomTerm>();
			TargetLanguage tlCode = targetparser.targetLanguage(blockList);
			selector().pop();
			blockList.add(tom_make_TargetLanguageToTomTerm(tlCode));
			if(blockList.size()==1) {
			String[] vars = new String[varnameList.size()];
			String code = ASTFactory.abstractCode(tlCode.getCode(),varnameList.toArray(vars));
			result = tom_make_MakeDecl(tom_make_Name(opname),returnType,args,tom_make_ExpressionToInstruction(tom_make_Code(code)),ot);
			} else {
			result = tom_make_MakeDecl(tom_make_Name(opname),returnType,args,tom_make_AbstractBlock(ASTFactory.makeInstructionList(blockList)),ot);
			}
			
		}
		}
		return result;
	}
	
	public final Declaration  keywordGetSlot(
		TomName astName, String type
	) throws RecognitionException, TokenStreamException, TomException {
		Declaration result;
		
		Token  t = null;
		Token  slotName = null;
		Token  name = null;
		
		result = null;
		Option ot = null;
		
		
		{
		t = LT(1);
		match(GET_SLOT);
		if ( inputState.guessing==0 ) {
			ot = tom_make_OriginTracking(tom_make_Name(t.getText()),t.getLine(),currentFile());
		}
		match(LPAREN);
		slotName = LT(1);
		match(ALL_ID);
		match(COMMA);
		name = LT(1);
		match(ALL_ID);
		match(RPAREN);
		if ( inputState.guessing==0 ) {
			
			Option info = tom_make_OriginTracking(tom_make_Name(name.getText()),name.getLine(),currentFile());
			OptionList option = tom_cons_list_concOption(info,tom_empty_list_concOption());
			
			selector().push("targetlexer");
			TargetLanguage tlCode = targetparser.goalLanguage(new LinkedList<TomTerm>());
			selector().pop(); 
			String code = ASTFactory.abstractCode(tlCode.getCode(),name.getText());
			result = tom_make_GetSlotDecl(astName,tom_make_Name(slotName.getText()),tom_make_Variable(option,tom_make_Name(name.getText()),tom_make_TomTypeAlone(type),tom_empty_list_concConstraint()),tom_make_Code(code),ot)
			
			
			;
			
		}
		}
		return result;
	}
	
	public final Declaration  keywordIsFsym(
		TomName astName, String typeString
	) throws RecognitionException, TokenStreamException, TomException {
		Declaration result;
		
		Token  t = null;
		Token  name = null;
		
		result = null;
		Option ot = null;
		
		
		t = LT(1);
		match(IS_FSYM);
		if ( inputState.guessing==0 ) {
			ot = tom_make_OriginTracking(tom_make_Name(t.getText()),t.getLine(),currentFile());
		}
		match(LPAREN);
		name = LT(1);
		match(ALL_ID);
		match(RPAREN);
		if ( inputState.guessing==0 ) {
			
			Option info = tom_make_OriginTracking(tom_make_Name(name.getText()),name.getLine(),currentFile());
			OptionList option = tom_cons_list_concOption(info,tom_empty_list_concOption());
			
			selector().push("targetlexer");
			TargetLanguage tlCode = targetparser.goalLanguage(new LinkedList<TomTerm>());
			selector().pop();
			
			String code = ASTFactory.abstractCode(tlCode.getCode(),name.getText());
			result = tom_make_IsFsymDecl(astName,tom_make_Variable(option,tom_make_Name(name.getText()),tom_make_TomTypeAlone(typeString),tom_empty_list_concConstraint()),tom_make_Code(code),ot)
			
			;
			
		}
		return result;
	}
	
	public final Declaration  operatorList() throws RecognitionException, TokenStreamException, TomException {
		Declaration result;
		
		Token  type = null;
		Token  name = null;
		Token  qm = null;
		Token  typeArg = null;
		Token  t = null;
		
		result = null;
		TomTypeList types = tom_empty_list_concTomType();
		List<Option> options = new LinkedList<Option>();
		Declaration attribute = null;
		String opName = "";
		
		
		type = LT(1);
		match(ALL_ID);
		name = LT(1);
		match(ALL_ID);
		{
		switch ( LA(1)) {
		case QMARK:
		{
			qm = LT(1);
			match(QMARK);
			break;
		}
		case LPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			
				  opName = name.getText(); // + ((qm!=null)?"__qm__":"");
				  Option ot = tom_make_OriginTracking(tom_make_Name(opName),name.getLine(),currentFile());
				  options.add(ot);
			
		}
		match(LPAREN);
		typeArg = LT(1);
		match(ALL_ID);
		match(STAR);
		match(RPAREN);
		if ( inputState.guessing==0 ) {
			
			types = tom_append_list_concTomType(types,tom_cons_list_concTomType(tom_make_TomTypeAlone(typeArg.getText()),tom_empty_list_concTomType()));
			
		}
		match(LBRACE);
		{
		_loop174:
		do {
			switch ( LA(1)) {
			case MAKE_EMPTY:
			{
				attribute=keywordMakeEmptyList(opName);
				if ( inputState.guessing==0 ) {
					options.add(tom_make_DeclarationToOption(attribute));
				}
				break;
			}
			case MAKE_INSERT:
			{
				attribute=keywordMakeAddList(opName,type.getText(),typeArg.getText());
				if ( inputState.guessing==0 ) {
					options.add(tom_make_DeclarationToOption(attribute));
				}
				break;
			}
			case IS_FSYM:
			{
				attribute=keywordIsFsym(tom_make_Name(opName), type.getText());
				if ( inputState.guessing==0 ) {
					options.add(tom_make_DeclarationToOption(attribute));
				}
				break;
			}
			case GET_HEAD:
			{
				attribute=keywordGetHead(tom_make_Name(opName), type.getText());
				if ( inputState.guessing==0 ) {
					options.add(tom_make_DeclarationToOption(attribute));
				}
				break;
			}
			case GET_TAIL:
			{
				attribute=keywordGetTail(tom_make_Name(opName), type.getText());
				if ( inputState.guessing==0 ) {
					options.add(tom_make_DeclarationToOption(attribute));
				}
				break;
			}
			case IS_EMPTY:
			{
				attribute=keywordIsEmpty(tom_make_Name(opName), type.getText());
				if ( inputState.guessing==0 ) {
					options.add(tom_make_DeclarationToOption(attribute));
				}
				break;
			}
			default:
			{
				break _loop174;
			}
			}
		} while (true);
		}
		t = LT(1);
		match(RBRACE);
		if ( inputState.guessing==0 ) {
			
			PairNameDeclList pairNameDeclList = tom_cons_list_concPairNameDecl(tom_make_PairNameDecl(tom_make_EmptyName(),tom_make_EmptyDeclaration()),tom_empty_list_concPairNameDecl());
			TomSymbol astSymbol = ASTFactory.makeSymbol(opName, tom_make_TomTypeAlone(type.getText()), types, pairNameDeclList, options);
			putSymbol(opName,astSymbol);
			result = tom_make_ListSymbolDecl(tom_make_Name(opName));
			updatePosition(t.getLine(),t.getColumn());
			selector().pop(); 
			
		}
		return result;
	}
	
	public final Declaration  keywordMakeEmptyList(
		String name
	) throws RecognitionException, TokenStreamException, TomException {
		Declaration result;
		
		Token  t = null;
		
		result = null;
		Option ot = null;
		
		
		t = LT(1);
		match(MAKE_EMPTY);
		{
		switch ( LA(1)) {
		case LPAREN:
		{
			match(LPAREN);
			match(RPAREN);
			break;
		}
		case LBRACE:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			ot = tom_make_OriginTracking(tom_make_Name(t.getText()),t.getLine(),currentFile());
		}
		match(LBRACE);
		if ( inputState.guessing==0 ) {
			
			selector().push("targetlexer");
			List blockList = new LinkedList();
			TargetLanguage tlCode = targetparser.targetLanguage(blockList);
			selector().pop();
			blockList.add(tlCode);
			if(blockList.size()==1) {
			result = tom_make_MakeEmptyList(tom_make_Name(name),tom_make_ExpressionToInstruction(tom_make_Code(tlCode.getCode())),ot);
			} else {
			result = tom_make_MakeEmptyList(tom_make_Name(name),tom_make_AbstractBlock(ASTFactory.makeInstructionList(blockList)),ot);
			}
			
		}
		return result;
	}
	
	public final Declaration  keywordMakeAddList(
		String name, String listType, String elementType
	) throws RecognitionException, TokenStreamException, TomException {
		Declaration result;
		
		Token  t = null;
		Token  elementName = null;
		Token  listName = null;
		
		result = null;
		Option ot = null;
		
		
		t = LT(1);
		match(MAKE_INSERT);
		if ( inputState.guessing==0 ) {
			ot = tom_make_OriginTracking(tom_make_Name(t.getText()),t.getLine(),currentFile());
		}
		match(LPAREN);
		elementName = LT(1);
		match(ALL_ID);
		match(COMMA);
		listName = LT(1);
		match(ALL_ID);
		match(RPAREN);
		match(LBRACE);
		if ( inputState.guessing==0 ) {
			
			Option listInfo = tom_make_OriginTracking(tom_make_Name(listName.getText()),listName.getLine(),currentFile());  
			Option elementInfo = tom_make_OriginTracking(tom_make_Name(elementName.getText()),elementName.getLine(),currentFile());
			OptionList listOption = tom_cons_list_concOption(listInfo,tom_empty_list_concOption());
			OptionList elementOption = tom_cons_list_concOption(elementInfo,tom_empty_list_concOption());
			
			selector().push("targetlexer");
			List blockList = new LinkedList();
			TargetLanguage tlCode = targetparser.targetLanguage(blockList);
			selector().pop();
			blockList.add(tlCode);
			if(blockList.size()==1) {
			String code = ASTFactory.abstractCode(tlCode.getCode(),elementName.getText(),listName.getText());
			result = tom_make_MakeAddList(tom_make_Name(name),tom_make_Variable(elementOption,tom_make_Name(elementName.getText()),tom_make_TomTypeAlone(elementType),tom_empty_list_concConstraint()),tom_make_Variable(listOption,tom_make_Name(listName.getText()),tom_make_TomTypeAlone(listType),tom_empty_list_concConstraint()),tom_make_ExpressionToInstruction(tom_make_Code(code)),ot)
			
			
			;
			} else {
			result = tom_make_MakeAddList(tom_make_Name(name),tom_make_Variable(elementOption,tom_make_Name(elementName.getText()),tom_make_TomTypeAlone(elementType),tom_empty_list_concConstraint()),tom_make_Variable(listOption,tom_make_Name(listName.getText()),tom_make_TomTypeAlone(listType),tom_empty_list_concConstraint()),tom_make_AbstractBlock(ASTFactory.makeInstructionList(blockList)),ot)
			
			
			;
			}
			
		}
		return result;
	}
	
	public final Declaration  keywordGetHead(
		TomName opname, String type
	) throws RecognitionException, TokenStreamException, TomException {
		Declaration result;
		
		Token  t = null;
		Token  name = null;
		
		result = null;
		Option ot = null;
		
		
		{
		t = LT(1);
		match(GET_HEAD);
		if ( inputState.guessing==0 ) {
			ot = tom_make_OriginTracking(tom_make_Name(t.getText()),t.getLine(),currentFile());
		}
		match(LPAREN);
		name = LT(1);
		match(ALL_ID);
		match(RPAREN);
		if ( inputState.guessing==0 ) {
			
			Option info = tom_make_OriginTracking(tom_make_Name(name.getText()),name.getLine(),currentFile());
			OptionList option = tom_cons_list_concOption(info,tom_empty_list_concOption());
			
			selector().push("targetlexer");
			TargetLanguage tlCode = targetparser.goalLanguage(new LinkedList<TomTerm>());
			selector().pop();  
			
			result = tom_make_GetHeadDecl(opname,symbolTable.getUniversalType(),tom_make_Variable(option,tom_make_Name(name.getText()),tom_make_TomTypeAlone(type),tom_empty_list_concConstraint()),tom_make_Code(ASTFactory.abstractCode(tlCode.getCode(),name.getText())),ot)
			
			
			
			;
			
		}
		}
		return result;
	}
	
	public final Declaration  keywordGetTail(
		TomName opname, String type
	) throws RecognitionException, TokenStreamException, TomException {
		Declaration result;
		
		Token  t = null;
		Token  name = null;
		
		result = null;
		Option ot = null;
		
		
		{
		t = LT(1);
		match(GET_TAIL);
		if ( inputState.guessing==0 ) {
			ot = tom_make_OriginTracking(tom_make_Name(t.getText()),t.getLine(),currentFile());
		}
		match(LPAREN);
		name = LT(1);
		match(ALL_ID);
		match(RPAREN);
		if ( inputState.guessing==0 ) {
			
			Option info = tom_make_OriginTracking(tom_make_Name(name.getText()),name.getLine(),currentFile());
			OptionList option = tom_cons_list_concOption(info,tom_empty_list_concOption());
			
			selector().push("targetlexer");
			TargetLanguage tlCode = targetparser.goalLanguage(new LinkedList<TomTerm>());
			selector().pop();  
			
			result = tom_make_GetTailDecl(opname,tom_make_Variable(option,tom_make_Name(name.getText()),tom_make_TomTypeAlone(type),tom_empty_list_concConstraint()),tom_make_Code(ASTFactory.abstractCode(tlCode.getCode(),name.getText())),ot)
			
			
			;
			
		}
		}
		return result;
	}
	
	public final Declaration  keywordIsEmpty(
		TomName opname, String type
	) throws RecognitionException, TokenStreamException, TomException {
		Declaration result;
		
		Token  t = null;
		Token  name = null;
		
		result = null;
		Option ot = null;
		
		
		{
		t = LT(1);
		match(IS_EMPTY);
		if ( inputState.guessing==0 ) {
			ot = tom_make_OriginTracking(tom_make_Name(t.getText()),t.getLine(),currentFile());
		}
		match(LPAREN);
		name = LT(1);
		match(ALL_ID);
		match(RPAREN);
		if ( inputState.guessing==0 ) {
			
			Option info = tom_make_OriginTracking(tom_make_Name(name.getText()),name.getLine(),currentFile());
			OptionList option = tom_cons_list_concOption(info,tom_empty_list_concOption());
			
			selector().push("targetlexer");
			TargetLanguage  tlCode = targetparser.goalLanguage(new LinkedList<TomTerm>());
			selector().pop(); 
			
			result = tom_make_IsEmptyDecl(opname,tom_make_Variable(option,tom_make_Name(name.getText()),tom_make_TomTypeAlone(type),tom_empty_list_concConstraint()),tom_make_Code(ASTFactory.abstractCode(tlCode.getCode(),name.getText())),ot)
			
			
			; 
			
		}
		}
		return result;
	}
	
	public final Declaration  operatorArray() throws RecognitionException, TokenStreamException, TomException {
		Declaration result;
		
		Token  type = null;
		Token  name = null;
		Token  qm = null;
		Token  typeArg = null;
		Token  t = null;
		
		result = null;
		TomTypeList types = tom_empty_list_concTomType();
		List<Option> options = new LinkedList<Option>();
		Declaration attribute = null;
		String opName = "";
		
		
		type = LT(1);
		match(ALL_ID);
		name = LT(1);
		match(ALL_ID);
		{
		switch ( LA(1)) {
		case QMARK:
		{
			qm = LT(1);
			match(QMARK);
			break;
		}
		case LPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			
				  opName = name.getText(); // + ((qm!=null)?"__qm__":"");
				  Option ot = tom_make_OriginTracking(tom_make_Name(opName),name.getLine(),currentFile());
				  options.add(ot);
			
		}
		match(LPAREN);
		typeArg = LT(1);
		match(ALL_ID);
		match(STAR);
		match(RPAREN);
		if ( inputState.guessing==0 ) {
			
			types = tom_append_list_concTomType(types,tom_cons_list_concTomType(tom_make_TomTypeAlone(typeArg.getText()),tom_empty_list_concTomType()));
			
		}
		match(LBRACE);
		{
		_loop178:
		do {
			switch ( LA(1)) {
			case MAKE_EMPTY:
			{
				attribute=keywordMakeEmptyArray(opName,type.getText());
				if ( inputState.guessing==0 ) {
					options.add(tom_make_DeclarationToOption(attribute));
				}
				break;
			}
			case MAKE_APPEND:
			{
				attribute=keywordMakeAddArray(opName,type.getText(),typeArg.getText());
				if ( inputState.guessing==0 ) {
					options.add(tom_make_DeclarationToOption(attribute));
				}
				break;
			}
			case IS_FSYM:
			{
				attribute=keywordIsFsym(tom_make_Name(opName),type.getText());
				if ( inputState.guessing==0 ) {
					options.add(tom_make_DeclarationToOption(attribute));
				}
				break;
			}
			case GET_ELEMENT:
			{
				attribute=keywordGetElement(tom_make_Name(opName), type.getText());
				if ( inputState.guessing==0 ) {
					options.add(tom_make_DeclarationToOption(attribute));
				}
				break;
			}
			case GET_SIZE:
			{
				attribute=keywordGetSize(tom_make_Name(opName), type.getText());
				if ( inputState.guessing==0 ) {
					options.add(tom_make_DeclarationToOption(attribute));
				}
				break;
			}
			default:
			{
				break _loop178;
			}
			}
		} while (true);
		}
		t = LT(1);
		match(RBRACE);
		if ( inputState.guessing==0 ) {
			
			PairNameDeclList pairNameDeclList = tom_cons_list_concPairNameDecl(tom_make_PairNameDecl(tom_make_EmptyName(),tom_make_EmptyDeclaration()),tom_empty_list_concPairNameDecl());
			TomSymbol astSymbol = ASTFactory.makeSymbol(opName, tom_make_TomTypeAlone(type.getText()), types, pairNameDeclList, options);
			putSymbol(opName,astSymbol);
			
			result = tom_make_ArraySymbolDecl(tom_make_Name(opName));
			
			updatePosition(t.getLine(),t.getColumn());
			
			selector().pop(); 
			
		}
		return result;
	}
	
	public final Declaration  keywordMakeEmptyArray(
		String name, String listType
	) throws RecognitionException, TokenStreamException, TomException {
		Declaration result;
		
		Token  t = null;
		Token  listName = null;
		
		result = null;
		Option ot = null;
		
		
		t = LT(1);
		match(MAKE_EMPTY);
		if ( inputState.guessing==0 ) {
			ot = tom_make_OriginTracking(tom_make_Name(t.getText()),t.getLine(),currentFile());
		}
		match(LPAREN);
		listName = LT(1);
		match(ALL_ID);
		match(RPAREN);
		match(LBRACE);
		if ( inputState.guessing==0 ) {
			
			Option listInfo = tom_make_OriginTracking(tom_make_Name(listName.getText()),listName.getLine(),currentFile());  
			OptionList listOption = tom_cons_list_concOption(listInfo,tom_empty_list_concOption());
			
			selector().push("targetlexer");
			List blockList = new LinkedList();
			TargetLanguage tlCode = targetparser.targetLanguage(blockList);
			selector().pop();
			blockList.add(tlCode);
			if(blockList.size()==1) {
			String code = ASTFactory.abstractCode(tlCode.getCode(),listName.getText());
			result = tom_make_MakeEmptyArray(tom_make_Name(name),tom_make_Variable(listOption,tom_make_Name(listName.getText()),tom_make_TomTypeAlone(listType),tom_empty_list_concConstraint()),tom_make_ExpressionToInstruction(tom_make_Code(code)),ot)
			;
			} else {
			result = tom_make_MakeEmptyArray(tom_make_Name(name),tom_make_Variable(listOption,tom_make_Name(listName.getText()),tom_make_TomTypeAlone(listType),tom_empty_list_concConstraint()),tom_make_AbstractBlock(ASTFactory.makeInstructionList(blockList)),ot)
			
			;
			}
			
		}
		return result;
	}
	
	public final Declaration  keywordMakeAddArray(
		String name, String listType, String elementType
	) throws RecognitionException, TokenStreamException, TomException {
		Declaration result;
		
		Token  t = null;
		Token  elementName = null;
		Token  listName = null;
		
		result = null;
		Option ot = null;
		
		
		t = LT(1);
		match(MAKE_APPEND);
		if ( inputState.guessing==0 ) {
			ot = tom_make_OriginTracking(tom_make_Name(t.getText()),t.getLine(),currentFile());
		}
		match(LPAREN);
		elementName = LT(1);
		match(ALL_ID);
		match(COMMA);
		listName = LT(1);
		match(ALL_ID);
		match(RPAREN);
		match(LBRACE);
		if ( inputState.guessing==0 ) {
			
			selector().push("targetlexer");
			List blockList = new LinkedList();
			TargetLanguage tlCode = targetparser.targetLanguage(blockList);
			selector().pop();
			blockList.add(tlCode);
			
			Option listInfo = tom_make_OriginTracking(tom_make_Name(listName.getText()),listName.getLine(),currentFile());  
			Option elementInfo = tom_make_OriginTracking(tom_make_Name(elementName.getText()),elementName.getLine(),currentFile());
			OptionList listOption = tom_cons_list_concOption(listInfo,tom_empty_list_concOption());
			OptionList elementOption = tom_cons_list_concOption(elementInfo,tom_empty_list_concOption());
			if(blockList.size()==1) {
			String code = ASTFactory.abstractCode(tlCode.getCode(),elementName.getText(),listName.getText());
			result = tom_make_MakeAddArray(tom_make_Name(name),tom_make_Variable(elementOption,tom_make_Name(elementName.getText()),tom_make_TomTypeAlone(elementType),tom_empty_list_concConstraint()),tom_make_Variable(listOption,tom_make_Name(listName.getText()),tom_make_TomTypeAlone(listType),tom_empty_list_concConstraint()),tom_make_ExpressionToInstruction(tom_make_Code(code)),ot)
			
			
			;
			} else {
			result = tom_make_MakeAddArray(tom_make_Name(name),tom_make_Variable(elementOption,tom_make_Name(elementName.getText()),tom_make_TomTypeAlone(elementType),tom_empty_list_concConstraint()),tom_make_Variable(listOption,tom_make_Name(listName.getText()),tom_make_TomTypeAlone(listType),tom_empty_list_concConstraint()),tom_make_AbstractBlock(ASTFactory.makeInstructionList(blockList)),ot)
			
			
			;
			}
			
		}
		return result;
	}
	
	public final Declaration  keywordGetElement(
		TomName opname, String type
	) throws RecognitionException, TokenStreamException, TomException {
		Declaration result;
		
		Token  t = null;
		Token  name1 = null;
		Token  name2 = null;
		
		result = null;
		Option ot = null;
		
		
		{
		t = LT(1);
		match(GET_ELEMENT);
		if ( inputState.guessing==0 ) {
			ot = tom_make_OriginTracking(tom_make_Name(t.getText()),t.getLine(),currentFile());
		}
		match(LPAREN);
		name1 = LT(1);
		match(ALL_ID);
		match(COMMA);
		name2 = LT(1);
		match(ALL_ID);
		match(RPAREN);
		if ( inputState.guessing==0 ) {
			
			Option info1 = tom_make_OriginTracking(tom_make_Name(name1.getText()),name1.getLine(),currentFile());
			Option info2 = tom_make_OriginTracking(tom_make_Name(name2.getText()),name2.getLine(),currentFile());
			OptionList option1 = tom_cons_list_concOption(info1,tom_empty_list_concOption());
			OptionList option2 = tom_cons_list_concOption(info2,tom_empty_list_concOption());
			
			selector().push("targetlexer");
			TargetLanguage tlCode = targetparser.goalLanguage(new LinkedList<TomTerm>());
			selector().pop();  
			
			result = tom_make_GetElementDecl(opname,tom_make_Variable(option1,tom_make_Name(name1.getText()),tom_make_TomTypeAlone(type),tom_empty_list_concConstraint()),tom_make_Variable(option2,tom_make_Name(name2.getText()),tom_make_TomTypeAlone("int"),tom_empty_list_concConstraint()),tom_make_Code(ASTFactory.abstractCode(tlCode.getCode(),name1.getText(),name2.getText())),ot)
			
			
			;
			
		}
		}
		return result;
	}
	
	public final Declaration  keywordGetSize(
		TomName opname, String type
	) throws RecognitionException, TokenStreamException, TomException {
		Declaration result;
		
		Token  t = null;
		Token  name = null;
		
		result = null;
		Option ot = null;
		
		
		{
		t = LT(1);
		match(GET_SIZE);
		if ( inputState.guessing==0 ) {
			ot = tom_make_OriginTracking(tom_make_Name(t.getText()),t.getLine(),currentFile());
		}
		match(LPAREN);
		name = LT(1);
		match(ALL_ID);
		match(RPAREN);
		if ( inputState.guessing==0 ) {
			
			Option info = tom_make_OriginTracking(tom_make_Name(name.getText()),name.getLine(),currentFile());
			OptionList option = tom_cons_list_concOption(info,tom_empty_list_concOption());
			
			selector().push("targetlexer");
			TargetLanguage tlCode = targetparser.goalLanguage(new LinkedList<TomTerm>());
			selector().pop();  
			
			result = tom_make_GetSizeDecl(opname,tom_make_Variable(option,tom_make_Name(name.getText()),tom_make_TomTypeAlone(type),tom_empty_list_concConstraint()),tom_make_Code(ASTFactory.abstractCode(tlCode.getCode(),name.getText())),ot)
			
			;
			
		}
		}
		return result;
	}
	
	public final Declaration  typeTerm() throws RecognitionException, TokenStreamException, TomException {
		Declaration result;
		
		Token  type = null;
		Token  t = null;
		
		result = null;
		Option ot = null;
		Declaration attribute = null;
		TargetLanguage implement = null;
		DeclarationList declarationList = tom_empty_list_concDeclaration();
		String s;
		
		
		{
		type = LT(1);
		match(ALL_ID);
		if ( inputState.guessing==0 ) {
			
			ot = tom_make_OriginTracking(tom_make_Name(type.getText()),type.getLine(),currentFile());
			
		}
		match(LBRACE);
		implement=keywordImplement();
		{
		_loop182:
		do {
			switch ( LA(1)) {
			case EQUALS:
			{
				attribute=keywordEquals(type.getText());
				if ( inputState.guessing==0 ) {
					declarationList = tom_cons_list_concDeclaration(attribute,tom_append_list_concDeclaration(declarationList,tom_empty_list_concDeclaration()));
				}
				break;
			}
			case IS_SORT:
			{
				attribute=keywordIsSort(type.getText());
				if ( inputState.guessing==0 ) {
					declarationList = tom_cons_list_concDeclaration(attribute,tom_append_list_concDeclaration(declarationList,tom_empty_list_concDeclaration()));
				}
				break;
			}
			case GET_IMPLEMENTATION:
			{
				attribute=keywordGetImplementation(type.getText());
				if ( inputState.guessing==0 ) {
					declarationList = tom_cons_list_concDeclaration(attribute,tom_append_list_concDeclaration(declarationList,tom_empty_list_concDeclaration()));
				}
				break;
			}
			default:
			{
				break _loop182;
			}
			}
		} while (true);
		}
		t = LT(1);
		match(RBRACE);
		}
		if ( inputState.guessing==0 ) {
			
			TomType astType = tom_make_Type(tom_make_ASTTomType(type.getText()),tom_make_TLType(implement));
			putType(type.getText(), astType); 
			result = tom_make_TypeTermDecl(tom_make_Name(type.getText()),declarationList,ot);
			updatePosition(t.getLine(),t.getColumn());
			selector().pop();
			
		}
		return result;
	}
	
	public final TargetLanguage  keywordImplement() throws RecognitionException, TokenStreamException, TomException {
		TargetLanguage tlCode;
		
		
		tlCode = null;
		
		
		{
		match(IMPLEMENT);
		if ( inputState.guessing==0 ) {
			
			selector().push("targetlexer");
			tlCode = targetparser.goalLanguage(new LinkedList<TomTerm>());
			selector().pop();
			
		}
		}
		return tlCode;
	}
	
	public final Declaration  keywordEquals(
		String type
	) throws RecognitionException, TokenStreamException, TomException {
		Declaration result;
		
		Token  t = null;
		Token  name1 = null;
		Token  name2 = null;
		
		result = null;
		Option ot = null;
		
		
		{
		t = LT(1);
		match(EQUALS);
		if ( inputState.guessing==0 ) {
			ot = tom_make_OriginTracking(tom_make_Name(t.getText()),t.getLine(),currentFile());
		}
		match(LPAREN);
		name1 = LT(1);
		match(ALL_ID);
		match(COMMA);
		name2 = LT(1);
		match(ALL_ID);
		match(RPAREN);
		if ( inputState.guessing==0 ) {
			
			Option info1 = tom_make_OriginTracking(tom_make_Name(name1.getText()),name1.getLine(),currentFile());
			Option info2 = tom_make_OriginTracking(tom_make_Name(name2.getText()),name2.getLine(),currentFile());
			OptionList option1 = tom_cons_list_concOption(info1,tom_empty_list_concOption());
			OptionList option2 = tom_cons_list_concOption(info2,tom_empty_list_concOption());
			
			selector().push("targetlexer");
			TargetLanguage tlCode = targetparser.goalLanguage(new LinkedList<TomTerm>());
			selector().pop();  
			String code = ASTFactory.abstractCode(tlCode.getCode(),name1.getText(),name2.getText());
			result = tom_make_EqualTermDecl(tom_make_Variable(option1,tom_make_Name(name1.getText()),tom_make_TomTypeAlone(type),tom_empty_list_concConstraint()),tom_make_Variable(option2,tom_make_Name(name2.getText()),tom_make_TomTypeAlone(type),tom_empty_list_concConstraint()),tom_make_Code(code),ot)
			
			
			;
			
		}
		}
		return result;
	}
	
	public final Declaration  keywordIsSort(
		String type
	) throws RecognitionException, TokenStreamException, TomException {
		Declaration result;
		
		Token  t = null;
		Token  name = null;
		
		result = null;
		Option ot = null;
		
		
		{
		t = LT(1);
		match(IS_SORT);
		if ( inputState.guessing==0 ) {
			ot = tom_make_OriginTracking(tom_make_Name(t.getText()),t.getLine(),currentFile());
		}
		match(LPAREN);
		name = LT(1);
		match(ALL_ID);
		match(RPAREN);
		if ( inputState.guessing==0 ) {
			
			Option info = tom_make_OriginTracking(tom_make_Name(name.getText()),name.getLine(),currentFile());
			OptionList option = tom_cons_list_concOption(info,tom_empty_list_concOption());
			
			selector().push("targetlexer");
			TargetLanguage tlCode = targetparser.goalLanguage(new LinkedList<TomTerm>());
			selector().pop();  
			
			String code = ASTFactory.abstractCode(tlCode.getCode(),name.getText());
			result = tom_make_IsSortDecl(tom_make_Variable(option,tom_make_Name(name.getText()),tom_make_TomTypeAlone(type),tom_empty_list_concConstraint()),tom_make_Code(code),ot)
			
			;
			
		}
		}
		return result;
	}
	
	public final Declaration  keywordGetImplementation(
		String typeString
	) throws RecognitionException, TokenStreamException, TomException {
		Declaration result;
		
		Token  t = null;
		Token  name = null;
		
		result = null;
		Option ot = null;
		
		
		t = LT(1);
		match(GET_IMPLEMENTATION);
		if ( inputState.guessing==0 ) {
			ot = tom_make_OriginTracking(tom_make_Name(t.getText()),t.getLine(),currentFile());
		}
		match(LPAREN);
		name = LT(1);
		match(ALL_ID);
		match(RPAREN);
		if ( inputState.guessing==0 ) {
			
			Option info = tom_make_OriginTracking(tom_make_Name(name.getText()),name.getLine(),currentFile());
			OptionList option = tom_cons_list_concOption(info,tom_empty_list_concOption());
			
			selector().push("targetlexer");
			TargetLanguage tlCode = targetparser.goalLanguage(new LinkedList<TomTerm>());
			selector().pop();
			
			result = tom_make_GetImplementationDecl(tom_make_Variable(option,tom_make_Name(name.getText()),tom_make_TomTypeAlone(typeString),tom_empty_list_concConstraint()),tom_make_Return(tom_make_TargetLanguageToTomTerm(tlCode)),ot)
			;
			
		}
		return result;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"NUM_INT",
		"CHARACTER",
		"STRING",
		"NUM_FLOAT",
		"NUM_LONG",
		"NUM_DOUBLE",
		"LPAREN",
		"RPAREN",
		"LBRACE",
		"RBRACE",
		"COMMA",
		"BACKQUOTE",
		"ALL_ID",
		"COLON",
		"AND_CONNECTOR",
		"OR_CONNECTOR",
		"ARROW",
		"MATCH_CONSTRAINT",
		"XML_START",
		"LESSOREQUAL_CONSTRAINT",
		"XML_CLOSE",
		"GREATEROREQUAL_CONSTRAINT",
		"DOUBLEEQ",
		"DIFFERENT_CONSTRAINT",
		"\"extends\"",
		"AT",
		"ANTI_SYM",
		"QMARK",
		"XML_CLOSE_SINGLETON",
		"XML_START_ENDING",
		"XML_TEXT",
		"XML_COMMENT",
		"XML_PROC",
		"LBRACKET",
		"RBRACKET",
		"EQUAL",
		"UNDERSCORE",
		"ALTERNATIVE",
		"STAR",
		"\"implement\"",
		"\"equals\"",
		"\"is_sort\"",
		"\"get_head\"",
		"\"get_tail\"",
		"\"is_empty\"",
		"\"get_element\"",
		"\"get_size\"",
		"\"is_fsym\"",
		"\"get_implementation\"",
		"\"get_slot\"",
		"\"make\"",
		"\"make_empty\"",
		"\"make_insert\"",
		"\"make_append\"",
		"\"where\"",
		"\"if\"",
		"\"when\"",
		"DOULEARROW",
		"AFFECT",
		"DOUBLE_QUOTE",
		"WS",
		"SLCOMMENT",
		"ML_COMMENT",
		"LESS_CONSTRAINT",
		"CONSTRAINT_GROUP_START",
		"CONSTRAINT_GROUP_END",
		"ESC",
		"HEX_DIGIT",
		"LETTER",
		"DIGIT",
		"ID",
		"ID_MINUS",
		"MINUS",
		"PLUS",
		"QUOTE",
		"EXPONENT",
		"DOT",
		"FLOAT_SUFFIX"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 1358287669232L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 1219774973936L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 1220848715760L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 120263278592L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 1366877603824L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 1100585435200L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	
	}
