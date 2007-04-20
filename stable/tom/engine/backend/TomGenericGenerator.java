/* Generated by TOM (version 2.5alpha): Do not edit this file *//*
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
 * Pierre-Etienne Moreau  e-mail: Pierre-Etienne.Moreau@loria.fr
 *
 **/

package tom.engine.backend;

import java.io.IOException;
import java.util.HashMap;

import tom.engine.TomBase;
import tom.engine.tools.OutputCode;

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
import tom.platform.OptionManager;

public abstract class TomGenericGenerator extends TomAbstractGenerator {

  protected HashMap isFsymMap = new HashMap();
  protected boolean lazyMode;
  protected boolean nodeclMode;
  protected String modifier = "";

  public TomGenericGenerator(OutputCode output, OptionManager optionManager,
                             SymbolTable symbolTable) {
    super(output, optionManager, symbolTable);
    lazyMode = ((Boolean)optionManager.getOptionValue("lazyType")).booleanValue();
    nodeclMode = ((Boolean)optionManager.getOptionValue("noDeclaration")).booleanValue();
  }

  // ------------------------------------------------------------
  /* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file *//* Generated by TOM (version 2.5alpha): Do not edit this file */ private static boolean tom_equal_term_String(String t1, String t2) {  return  (t1.equals(t2))  ;}private static boolean tom_is_sort_String(String t) {  return  t instanceof String  ;}  /* Generated by TOM (version 2.5alpha): Do not edit this file */private static boolean tom_equal_term_int(int t1, int t2) {  return  (t1==t2)  ;} private static boolean tom_equal_term_TomName(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_sort_TomName(Object t) {  return  t instanceof tom.engine.adt.tomname.types.TomName  ;}private static boolean tom_equal_term_Expression(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_sort_Expression(Object t) {  return  t instanceof tom.engine.adt.tomexpression.types.Expression  ;}private static boolean tom_equal_term_TomTerm(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_sort_TomTerm(Object t) {  return  t instanceof tom.engine.adt.tomterm.types.TomTerm  ;}private static boolean tom_is_fun_sym_Name( tom.engine.adt.tomname.types.TomName  t) {  return  t instanceof tom.engine.adt.tomname.types.tomname.Name  ;}private static  String  tom_get_slot_Name_String( tom.engine.adt.tomname.types.TomName  t) {  return  t.getString()  ;}private static boolean tom_is_fun_sym_EmptyName( tom.engine.adt.tomname.types.TomName  t) {  return  t instanceof tom.engine.adt.tomname.types.tomname.EmptyName  ;}private static  tom.engine.adt.tomexpression.types.Expression  tom_make_TrueTL() { return  tom.engine.adt.tomexpression.types.expression.TrueTL.make(); }private static boolean tom_is_fun_sym_BuildEmptyList( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.BuildEmptyList  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_BuildEmptyList_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;}private static boolean tom_is_fun_sym_BuildConsList( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.BuildConsList  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_BuildConsList_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildConsList_HeadTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getHeadTerm()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildConsList_TailTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getTailTerm()  ;}private static boolean tom_is_fun_sym_BuildAppendList( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.BuildAppendList  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_BuildAppendList_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildAppendList_HeadTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getHeadTerm()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildAppendList_TailTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getTailTerm()  ;}private static boolean tom_is_fun_sym_BuildEmptyArray( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.BuildEmptyArray  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_BuildEmptyArray_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;}private static  int  tom_get_slot_BuildEmptyArray_Size( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getSize()  ;}private static boolean tom_is_fun_sym_BuildConsArray( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.BuildConsArray  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_BuildConsArray_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildConsArray_HeadTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getHeadTerm()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildConsArray_TailTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getTailTerm()  ;}private static boolean tom_is_fun_sym_BuildAppendArray( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t instanceof tom.engine.adt.tomterm.types.tomterm.BuildAppendArray  ;}private static  tom.engine.adt.tomname.types.TomName  tom_get_slot_BuildAppendArray_AstName( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getAstName()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildAppendArray_HeadTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getHeadTerm()  ;}private static  tom.engine.adt.tomterm.types.TomTerm  tom_get_slot_BuildAppendArray_TailTerm( tom.engine.adt.tomterm.types.TomTerm  t) {  return  t.getTailTerm()  ;} 
  // ------------------------------------------------------------

  /*
   * Implementation of functions whose definition is
   * independant of the target language
   */

  protected void buildTerm(int deep, String name, TomList argList, String moduleName) throws IOException {
    buildFunctionCall(deep, "tom_make_"+name, argList, moduleName);
  }

  protected void buildSymbolDecl(int deep, String tomName, String moduleName) throws IOException {
    TomSymbol tomSymbol = getSymbolTable(moduleName).getSymbolFromName(tomName);
    OptionList optionList = tomSymbol.getOption();
    PairNameDeclList pairNameDeclList = tomSymbol.getPairNameDeclList();
    // inspect the optionList
    generateOptionList(deep, optionList, moduleName);
    // inspect the slotlist
    generatePairNameDeclList(deep, pairNameDeclList, moduleName);
  }

  protected void buildExpGreaterThan(int deep, Expression exp1, Expression exp2, String moduleName) throws IOException {
    generateExpression(deep,exp1,moduleName);
    output.write(" > ");
    generateExpression(deep,exp2,moduleName);
  }

  protected void buildExpIsEmptyList(int deep, TomName opNameAST, TomType type, TomTerm expList, String moduleName) throws IOException {
     if (tom_is_sort_TomName(opNameAST)) { { tom.engine.adt.tomname.types.TomName  tomMatch1Position1=(( tom.engine.adt.tomname.types.TomName )opNameAST); if ( ( tom_is_fun_sym_EmptyName(tomMatch1Position1) ||  false  ) ) { if ( true ) {
 output.write("tom_is_empty_" + TomBase.getTomType(type) + "(");  } } if ( ( tom_is_fun_sym_Name(tomMatch1Position1) ||  false  ) ) { { String  tom_opName=tom_get_slot_Name_String(tomMatch1Position1); if ( true ) {
 output.write("tom_is_empty_" + tom_opName+ "_" + TomBase.getTomType(type) + "(");  } } } } }

    generate(deep,expList,moduleName);
    output.write(")");
  }

  protected void buildExpIsEmptyArray(int deep, TomName opNameAST, TomType type, TomTerm expIndex, TomTerm expArray, String moduleName) throws IOException {
    generate(deep,expIndex,moduleName);
    output.write(" >= ");
     if (tom_is_sort_TomName(opNameAST)) { { tom.engine.adt.tomname.types.TomName  tomMatch2Position1=(( tom.engine.adt.tomname.types.TomName )opNameAST); if ( ( tom_is_fun_sym_EmptyName(tomMatch2Position1) ||  false  ) ) { if ( true ) {
 output.write("tom_get_size_" + TomBase.getTomType(type) + "(");  } } if ( ( tom_is_fun_sym_Name(tomMatch2Position1) ||  false  ) ) { { String  tom_opName=tom_get_slot_Name_String(tomMatch2Position1); if ( true ) {
 output.write("tom_get_size_" + tom_opName+ "_" + TomBase.getTomType(type) + "(");  } } } } }

    generate(deep,expArray,moduleName);
    output.write(")");
  }

  protected void buildExpIsSort(int deep, TomType type, TomTerm exp1, String moduleName) throws IOException {
    if(getSymbolTable(moduleName).isBuiltinType(TomBase.getTomType(type))) {
      generateExpression(deep,tom_make_TrueTL(),moduleName);
    } else {
      output.write("tom_is_sort_" + TomBase.getTomType(type) + "(");
      generate(deep,exp1,moduleName);
      output.write(")");
    }
  }

  protected void buildExpIsFsym(int deep, String opname, TomTerm exp, String moduleName) throws IOException {
    String s = (String)isFsymMap.get(opname);
    if(s == null) {
      s = "tom_is_fun_sym_" + opname + "(";
      isFsymMap.put(opname,s);
    }
    output.write(s);
    generate(deep,exp,moduleName);
    output.write(")");
  }

  protected void buildExpGetSlot(int deep, String opname, String slotName, TomTerm var, String moduleName) throws IOException {
    //output.write("tom_get_slot_" + opname + "_" + slotName + "(");
    //generate(deep,var);
    //output.write(")");
    output.write("tom_get_slot_");
    output.write(opname);
    output.writeUnderscore();
    output.write(slotName);
    output.writeOpenBrace();
    generate(deep,var,moduleName);
    output.writeCloseBrace();
  }

  protected void buildExpGetTail(int deep, TomName opNameAST, TomType type, TomTerm var, String moduleName) throws IOException {
     if (tom_is_sort_TomName(opNameAST)) { { tom.engine.adt.tomname.types.TomName  tomMatch3Position1=(( tom.engine.adt.tomname.types.TomName )opNameAST); if ( ( tom_is_fun_sym_EmptyName(tomMatch3Position1) ||  false  ) ) { if ( true ) {
 output.write("tom_get_tail_" + TomBase.getTomType(type) + "(");  } } if ( ( tom_is_fun_sym_Name(tomMatch3Position1) ||  false  ) ) { { String  tom_opName=tom_get_slot_Name_String(tomMatch3Position1); if ( true ) {
 output.write("tom_get_tail_" + tom_opName+ "_" + TomBase.getTomType(type) + "(");  } } } } }

    generate(deep,var,moduleName);
    output.write(")");
  }

  protected void buildExpGetSize(int deep, TomName opNameAST, TomType type, TomTerm var, String moduleName) throws IOException {
     if (tom_is_sort_TomName(opNameAST)) { { tom.engine.adt.tomname.types.TomName  tomMatch4Position1=(( tom.engine.adt.tomname.types.TomName )opNameAST); if ( ( tom_is_fun_sym_EmptyName(tomMatch4Position1) ||  false  ) ) { if ( true ) {
 output.write("tom_get_size_" + TomBase.getTomType(type) + "(");  } } if ( ( tom_is_fun_sym_Name(tomMatch4Position1) ||  false  ) ) { { String  tom_opName=tom_get_slot_Name_String(tomMatch4Position1); if ( true ) {
 output.write("tom_get_size_" + tom_opName+ "_" + TomBase.getTomType(type) + "(");  } } } } }

    generate(deep,var,moduleName);
    output.write(")");
  }

  protected void buildExpGetSliceList(int deep, String name, TomTerm varBegin, TomTerm varEnd, TomTerm tail, String moduleName) throws IOException {
    output.write("tom_get_slice_" + name + "(");
    generate(deep,varBegin,moduleName);
    output.write(",");
    generate(deep,varEnd,moduleName);
    output.write(",");
    generate(deep,tail,moduleName);
    output.write(")");
  }
  
  protected void buildExpGetSliceArray(int deep, String name, TomTerm varArray, TomTerm varBegin, TomTerm expEnd, String moduleName) throws IOException {
    output.write("tom_get_slice_" + name + "(");
    generate(deep,varArray,moduleName);
    output.write(",");
    generate(deep,varBegin,moduleName);
    output.write(",");
    generate(deep,expEnd,moduleName);
    output.write(")");
  }

  protected void buildAddOne(int deep, TomTerm var, String moduleName) throws IOException {
    generate(deep,var,moduleName);
    output.write(" + 1");
  }

  protected void buildGetFunctionSymbolDecl(int deep, String type, String name,
                                            TomType tlType, TargetLanguage tlCode, String moduleName) throws IOException {
    String args[];
    if(lazyMode) {
      TomType argType = getUniversalType();
      if(getSymbolTable(moduleName).isBuiltinType(type)) {
        argType = getSymbolTable(moduleName).getBuiltinType(type);
      }
      args = new String[] { TomBase.getTLType(argType), name };
    } else {
      args = new String[] { TomBase.getTLCode(tlType), name };
    }

    TomType returnType = getUniversalType();
    if(getSymbolTable(moduleName).isBuiltinType(type)) {
      returnType = getSymbolTable(moduleName).getBuiltinType(type);
    }
    genDecl(TomBase.getTLType(returnType),"tom_get_fun_sym", type,args,tlCode, moduleName);
  }

  protected void buildGetImplementationDecl(int deep, String type, String typename,
                                            TomType tlType, Instruction instr, String moduleName) throws IOException {
    String argType;
    if(!lazyMode) {
      argType = TomBase.getTLCode(tlType);
    } else {
      argType = TomBase.getTLType(getUniversalType());
    }
    String returnType = argType;

    genDeclInstr(returnType,
            "tom_get_implementation", type,
            new String[] { argType, typename },
            instr,deep,moduleName);
  }

  protected void buildIsFsymDecl(int deep, String tomName, String varname,
                                 TomType tlType, Instruction instr, String moduleName) throws IOException {
    TomSymbol tomSymbol = getSymbolTable(moduleName).getSymbolFromName(tomName);
    String opname = tomSymbol.getAstName().getString();

    TomType returnType = getSymbolTable(moduleName).getBooleanType();
    String argType;
    if(!lazyMode) {
      argType = TomBase.getTLCode(tlType);
    } else {
      argType = TomBase.getTLType(getUniversalType());
    }

    genDeclInstr(TomBase.getTLType(returnType), "tom_is_fun_sym", opname, 
        new String[] { argType, varname }, instr,deep,moduleName);
  }

  protected void buildGetSlotDecl(int deep, String tomName, String varname,
                                  TomType tlType, Instruction instr, TomName slotName, String moduleName) throws IOException {
    TomSymbol tomSymbol = getSymbolTable(moduleName).getSymbolFromName(tomName);
    String opname = tomSymbol.getAstName().getString();
    TomTypeList typesList = tomSymbol.getTypesToType().getDomain();

    int slotIndex = TomBase.getSlotIndex(tomSymbol,slotName);
    TomTypeList l = typesList;
    for(int index = 0; !l.isEmptyconcTomType() && index<slotIndex ; index++) {
      l = l.getTailconcTomType();
    }
    TomType returnType = l.getHeadconcTomType();

    String argType;
    if(!lazyMode) {
      argType = TomBase.getTLCode(tlType);
    } else {
      argType = TomBase.getTLType(getUniversalType());
    }
    genDeclInstr(TomBase.getTLType(returnType),
            "tom_get_slot", opname  + "_" + slotName.getString(),
            new String[] { argType, varname },
            instr,deep,moduleName);
  }

  protected void  buildCompareFunctionSymbolDecl(int deep, String name1, String name2,
                                                 String type1, String type2, TargetLanguage tlCode, String moduleName) throws IOException {
    TomType argType1 = getUniversalType();
    if(getSymbolTable(moduleName).isBuiltinType(type1)) {
      argType1 = getSymbolTable(moduleName).getBuiltinType(type1);
    }
    TomType argType2 = getUniversalType();
    if(getSymbolTable(moduleName).isBuiltinType(type2)) {
      argType2 = getSymbolTable(moduleName).getBuiltinType(type2);
    }

    genDecl(TomBase.getTLType(getSymbolTable(moduleName).getBooleanType()), "tom_cmp_fun_sym", type1,
            new String[] {
              TomBase.getTLType(argType1), name1,
              TomBase.getTLType(argType2), name2
            },
            tlCode, moduleName);
  }

  protected void buildEqualTermDecl(int deep, String name1, String name2,
                                     String type1, String type2, Instruction instr, String moduleName) throws IOException {
    TomType argType1 = getUniversalType();
    if(getSymbolTable(moduleName).isBuiltinType(type1)) {
      argType1 = getSymbolTable(moduleName).getBuiltinType(type1);
    }
    TomType argType2 = getUniversalType();
    if(getSymbolTable(moduleName).isBuiltinType(type2)) {
      argType2 = getSymbolTable(moduleName).getBuiltinType(type2);
    }

    genDeclInstr(TomBase.getTLType(getSymbolTable(moduleName).getBooleanType()), "tom_equal_term", type1,
            new String[] {
              TomBase.getTLType(argType1), name1,
              TomBase.getTLType(argType2), name2
            },
            instr,deep,moduleName);
  }

  protected void buildIsSortDecl(int deep, String name1, String type1, Instruction instr, String moduleName) throws IOException {
    TomType argType1 = getUniversalType();
    if(getSymbolTable(moduleName).isBuiltinType(type1)) {
      argType1 = getSymbolTable(moduleName).getBuiltinType(type1);
    }
    genDeclInstr(TomBase.getTLType(getSymbolTable(moduleName).getBooleanType()), "tom_is_sort", type1,
        new String[] { TomBase.getTLType(argType1), name1 },
        instr,deep,moduleName);
  }

  protected void buildGetHeadDecl(int deep, TomName opNameAST, String varName, String suffix, TomType domain, TomType codomain, Instruction instr, String moduleName)
    throws IOException {
    String returnType = null;
    String argType = null;
    String functionName = "tom_get_head";

     if (tom_is_sort_TomName(opNameAST)) { { tom.engine.adt.tomname.types.TomName  tomMatch5Position1=(( tom.engine.adt.tomname.types.TomName )opNameAST); if ( ( tom_is_fun_sym_Name(tomMatch5Position1) ||  false  ) ) { { String  tom_opName=tom_get_slot_Name_String(tomMatch5Position1); if ( true ) {
 functionName = functionName + "_" + tom_opName;  } } } } }


    if(lazyMode) {
      returnType = TomBase.getTLType(getUniversalType());
      argType = TomBase.getTLType(getUniversalType());
    } else {
       if (tom_is_sort_TomName(opNameAST)) { { tom.engine.adt.tomname.types.TomName  tomMatch6Position1=(( tom.engine.adt.tomname.types.TomName )opNameAST); if ( ( tom_is_fun_sym_EmptyName(tomMatch6Position1) ||  false  ) ) { if ( true ) {

          returnType = TomBase.getTLCode(codomain);
          argType = TomBase.getTLCode(domain);
         } } if ( ( tom_is_fun_sym_Name(tomMatch6Position1) ||  false  ) ) { { String  tom_opName=tom_get_slot_Name_String(tomMatch6Position1); if ( true ) {


          TomSymbol tomSymbol = getSymbolFromName(tom_opName);
          argType = TomBase.getTLType(TomBase.getSymbolCodomain(tomSymbol));
          returnType = TomBase.getTLType(TomBase.getSymbolDomain(tomSymbol).getHeadconcTomType());
         } } } } }

    }
    genDeclInstr(returnType, functionName, suffix,
            new String[] { argType, varName },
            instr,deep,moduleName);
  }

  protected void buildGetTailDecl(int deep, TomName opNameAST, String varName, String type, TomType tlType, Instruction instr, String moduleName)
    throws IOException {
    String returnType = null;
    String argType = null;
    String functionName = "tom_get_tail";

     if (tom_is_sort_TomName(opNameAST)) { { tom.engine.adt.tomname.types.TomName  tomMatch7Position1=(( tom.engine.adt.tomname.types.TomName )opNameAST); if ( ( tom_is_fun_sym_Name(tomMatch7Position1) ||  false  ) ) { { String  tom_opName=tom_get_slot_Name_String(tomMatch7Position1); if ( true ) {
 functionName = functionName + "_" + tom_opName;  } } } } }


    if(lazyMode) {
      returnType = TomBase.getTLType(getUniversalType());
      argType = TomBase.getTLType(getUniversalType());
    } else {
       if (tom_is_sort_TomName(opNameAST)) { { tom.engine.adt.tomname.types.TomName  tomMatch8Position1=(( tom.engine.adt.tomname.types.TomName )opNameAST); if ( ( tom_is_fun_sym_EmptyName(tomMatch8Position1) ||  false  ) ) { if ( true ) {

          returnType = TomBase.getTLCode(tlType);
          argType = returnType;
         } } if ( ( tom_is_fun_sym_Name(tomMatch8Position1) ||  false  ) ) { { String  tom_opName=tom_get_slot_Name_String(tomMatch8Position1); if ( true ) {


          TomSymbol tomSymbol = getSymbolFromName(tom_opName);
          returnType = TomBase.getTLType(TomBase.getSymbolCodomain(tomSymbol));
          argType = returnType;
         } } } } }

    }

    genDeclInstr(returnType, functionName, type,
            new String[] { argType, varName },
            instr,deep,moduleName);
  }

  protected void buildIsEmptyDecl(int deep, TomName opNameAST, String varName, String type,
                                  TomType tlType, Instruction instr, String moduleName) throws IOException {
    String argType = null;
    String functionName = "tom_is_empty";

     if (tom_is_sort_TomName(opNameAST)) { { tom.engine.adt.tomname.types.TomName  tomMatch9Position1=(( tom.engine.adt.tomname.types.TomName )opNameAST); if ( ( tom_is_fun_sym_Name(tomMatch9Position1) ||  false  ) ) { { String  tom_opName=tom_get_slot_Name_String(tomMatch9Position1); if ( true ) {
 functionName = functionName + "_" + tom_opName;  } } } } }

    if(lazyMode) {
      argType = TomBase.getTLType(getUniversalType());
    } else {
       if (tom_is_sort_TomName(opNameAST)) { { tom.engine.adt.tomname.types.TomName  tomMatch10Position1=(( tom.engine.adt.tomname.types.TomName )opNameAST); if ( ( tom_is_fun_sym_EmptyName(tomMatch10Position1) ||  false  ) ) { if ( true ) {

          argType = TomBase.getTLCode(tlType);
         } } if ( ( tom_is_fun_sym_Name(tomMatch10Position1) ||  false  ) ) { { String  tom_opName=tom_get_slot_Name_String(tomMatch10Position1); if ( true ) {


          TomSymbol tomSymbol = getSymbolFromName(tom_opName);
          argType = TomBase.getTLType(TomBase.getSymbolCodomain(tomSymbol));
         } } } } }

    }

    genDeclInstr(TomBase.getTLType(getSymbolTable(moduleName).getBooleanType()),
            functionName, type,
            new String[] { argType, varName },
            instr,deep,moduleName);
  }

  protected void buildGetElementDecl(int deep, TomName opNameAST, String name1, String name2,
                                     String type1, TomType domain, Instruction instr, String moduleName) throws IOException {
    String returnType = null;
    String argType = null;
    String functionName = "tom_get_element";

     if (tom_is_sort_TomName(opNameAST)) { { tom.engine.adt.tomname.types.TomName  tomMatch11Position1=(( tom.engine.adt.tomname.types.TomName )opNameAST); if ( ( tom_is_fun_sym_Name(tomMatch11Position1) ||  false  ) ) { { String  tom_opName=tom_get_slot_Name_String(tomMatch11Position1); if ( true ) {
 functionName = functionName + "_" + tom_opName;  } } } } }


    if(lazyMode) {
      returnType = TomBase.getTLType(getUniversalType());
      argType = TomBase.getTLType(getUniversalType());
    } else {
       if (tom_is_sort_TomName(opNameAST)) { { tom.engine.adt.tomname.types.TomName  tomMatch12Position1=(( tom.engine.adt.tomname.types.TomName )opNameAST); if ( ( tom_is_fun_sym_EmptyName(tomMatch12Position1) ||  false  ) ) { if ( true ) {

          returnType = TomBase.getTLType(getUniversalType());
          argType = TomBase.getTLCode(domain);
         } } if ( ( tom_is_fun_sym_Name(tomMatch12Position1) ||  false  ) ) { { String  tom_opName=tom_get_slot_Name_String(tomMatch12Position1); if ( true ) {


          TomSymbol tomSymbol = getSymbolFromName(tom_opName);
          argType = TomBase.getTLType(TomBase.getSymbolCodomain(tomSymbol));
          returnType = TomBase.getTLType(TomBase.getSymbolDomain(tomSymbol).getHeadconcTomType());
         } } } } }

    }

    genDeclInstr(returnType,
            functionName, type1,
            new String[] {
              argType, name1,
              TomBase.getTLType(getSymbolTable(moduleName).getIntType()), name2
            },
            instr,deep,moduleName);
  }

  protected void buildGetSizeDecl(int deep, TomName opNameAST, String name1, String type,
                                  TomType domain, Instruction instr, String moduleName) throws IOException {
    String argType = null;
    String functionName = "tom_get_size";

     if (tom_is_sort_TomName(opNameAST)) { { tom.engine.adt.tomname.types.TomName  tomMatch13Position1=(( tom.engine.adt.tomname.types.TomName )opNameAST); if ( ( tom_is_fun_sym_Name(tomMatch13Position1) ||  false  ) ) { { String  tom_opName=tom_get_slot_Name_String(tomMatch13Position1); if ( true ) {
 functionName = functionName + "_" + tom_opName;  } } } } }


    if(lazyMode) {
      argType = TomBase.getTLType(getUniversalType());
    } else {
       if (tom_is_sort_TomName(opNameAST)) { { tom.engine.adt.tomname.types.TomName  tomMatch14Position1=(( tom.engine.adt.tomname.types.TomName )opNameAST); if ( ( tom_is_fun_sym_EmptyName(tomMatch14Position1) ||  false  ) ) { if ( true ) {

          argType = TomBase.getTLCode(domain);
         } } if ( ( tom_is_fun_sym_Name(tomMatch14Position1) ||  false  ) ) { { String  tom_opName=tom_get_slot_Name_String(tomMatch14Position1); if ( true ) {


          TomSymbol tomSymbol = getSymbolFromName(tom_opName);
          argType = TomBase.getTLType(TomBase.getSymbolCodomain(tomSymbol));
         } } } } }

    }

    genDeclInstr(TomBase.getTLType(getSymbolTable(moduleName).getIntType()),
            functionName, type,
            new String[] { argType, name1 },
            instr,deep,moduleName);
  }

  /*
   * the method implementations are here common to C, Java, caml and python
   */
  protected void buildExpGetHead(int deep, TomName opNameAST, TomType domain, TomType codomain, TomTerm var, String moduleName) throws IOException {
     if (tom_is_sort_TomName(opNameAST)) { { tom.engine.adt.tomname.types.TomName  tomMatch15Position1=(( tom.engine.adt.tomname.types.TomName )opNameAST); if ( ( tom_is_fun_sym_EmptyName(tomMatch15Position1) ||  false  ) ) { if ( true ) {
 output.write("tom_get_head_" + TomBase.getTomType(domain) + "(");  } } if ( ( tom_is_fun_sym_Name(tomMatch15Position1) ||  false  ) ) { { String  tom_opName=tom_get_slot_Name_String(tomMatch15Position1); if ( true ) {
 output.write("tom_get_head_" + tom_opName+ "_" + TomBase.getTomType(domain) + "(");  } } } } }

    generate(deep,var,moduleName);
    output.write(")");
  }
  
  protected void buildExpGetElement(int deep, TomName opNameAST, TomType domain, TomType codomain, TomTerm varName, TomTerm varIndex, String moduleName) throws IOException {
     if (tom_is_sort_TomName(opNameAST)) { { tom.engine.adt.tomname.types.TomName  tomMatch16Position1=(( tom.engine.adt.tomname.types.TomName )opNameAST); if ( ( tom_is_fun_sym_EmptyName(tomMatch16Position1) ||  false  ) ) { if ( true ) {
 output.write("tom_get_element_" + TomBase.getTomType(domain) + "(");  } } if ( ( tom_is_fun_sym_Name(tomMatch16Position1) ||  false  ) ) { { String  tom_opName=tom_get_slot_Name_String(tomMatch16Position1); if ( true ) {
 output.write("tom_get_element_" + tom_opName+ "_" + TomBase.getTomType(domain) + "(");  } } } } }


    generate(deep,varName,moduleName);
    output.write(",");
    generate(deep,varIndex,moduleName);
    output.write(")");
  }
 
  protected void buildListOrArray(int deep, TomTerm list, String moduleName) throws IOException {
     if (tom_is_sort_TomTerm(list)) { { tom.engine.adt.tomterm.types.TomTerm  tomMatch17Position1=(( tom.engine.adt.tomterm.types.TomTerm )list); if ( ( tom_is_fun_sym_BuildEmptyList(tomMatch17Position1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomName  tomMatch17Position1NameNumberAstName=tom_get_slot_BuildEmptyList_AstName(tomMatch17Position1); if ( ( tom_is_fun_sym_Name(tomMatch17Position1NameNumberAstName) ||  false  ) ) { { String  tom_name=tom_get_slot_Name_String(tomMatch17Position1NameNumberAstName); if ( true ) {

        output.write("tom_empty_list_" + tom_name+ "()");
        return;
       } } } } } if ( ( tom_is_fun_sym_BuildConsList(tomMatch17Position1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomName  tomMatch17Position1NameNumberAstName=tom_get_slot_BuildConsList_AstName(tomMatch17Position1); if ( ( tom_is_fun_sym_Name(tomMatch17Position1NameNumberAstName) ||  false  ) ) { { String  tom_name=tom_get_slot_Name_String(tomMatch17Position1NameNumberAstName); { tom.engine.adt.tomterm.types.TomTerm  tom_headTerm=tom_get_slot_BuildConsList_HeadTerm(tomMatch17Position1); { tom.engine.adt.tomterm.types.TomTerm  tom_tailTerm=tom_get_slot_BuildConsList_TailTerm(tomMatch17Position1); if ( true ) {


        output.write("tom_cons_list_" + tom_name+ "(");
        generate(deep,tom_headTerm,moduleName);
        output.write(",");
        generate(deep,tom_tailTerm,moduleName);
        output.write(")");
        return;
       } } } } } } } if ( ( tom_is_fun_sym_BuildAppendList(tomMatch17Position1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomName  tomMatch17Position1NameNumberAstName=tom_get_slot_BuildAppendList_AstName(tomMatch17Position1); if ( ( tom_is_fun_sym_Name(tomMatch17Position1NameNumberAstName) ||  false  ) ) { { String  tom_name=tom_get_slot_Name_String(tomMatch17Position1NameNumberAstName); { tom.engine.adt.tomterm.types.TomTerm  tom_headTerm=tom_get_slot_BuildAppendList_HeadTerm(tomMatch17Position1); { tom.engine.adt.tomterm.types.TomTerm  tom_tailTerm=tom_get_slot_BuildAppendList_TailTerm(tomMatch17Position1); if ( true ) {


        output.write("tom_append_list_" + tom_name+ "(");
        generate(deep,tom_headTerm,moduleName);
        output.write(",");
        generate(deep,tom_tailTerm,moduleName);
        output.write(")");
        return;
       } } } } } } } if ( ( tom_is_fun_sym_BuildEmptyArray(tomMatch17Position1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomName  tomMatch17Position1NameNumberAstName=tom_get_slot_BuildEmptyArray_AstName(tomMatch17Position1); if ( ( tom_is_fun_sym_Name(tomMatch17Position1NameNumberAstName) ||  false  ) ) { { String  tom_name=tom_get_slot_Name_String(tomMatch17Position1NameNumberAstName); { int  tom_size=tom_get_slot_BuildEmptyArray_Size(tomMatch17Position1); if ( true ) {


        output.write("tom_empty_array_" + tom_name+ "(" + tom_size+ ")");
        return;
       } } } } } } if ( ( tom_is_fun_sym_BuildConsArray(tomMatch17Position1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomName  tomMatch17Position1NameNumberAstName=tom_get_slot_BuildConsArray_AstName(tomMatch17Position1); if ( ( tom_is_fun_sym_Name(tomMatch17Position1NameNumberAstName) ||  false  ) ) { { String  tom_name=tom_get_slot_Name_String(tomMatch17Position1NameNumberAstName); { tom.engine.adt.tomterm.types.TomTerm  tom_headTerm=tom_get_slot_BuildConsArray_HeadTerm(tomMatch17Position1); { tom.engine.adt.tomterm.types.TomTerm  tom_tailTerm=tom_get_slot_BuildConsArray_TailTerm(tomMatch17Position1); if ( true ) {


        output.write("tom_cons_array_" + tom_name+ "(");
        generate(deep,tom_headTerm,moduleName);
        output.write(",");
        generate(deep,tom_tailTerm,moduleName);
        output.write(")");
        return;
       } } } } } } } if ( ( tom_is_fun_sym_BuildAppendArray(tomMatch17Position1) ||  false  ) ) { { tom.engine.adt.tomname.types.TomName  tomMatch17Position1NameNumberAstName=tom_get_slot_BuildAppendArray_AstName(tomMatch17Position1); if ( ( tom_is_fun_sym_Name(tomMatch17Position1NameNumberAstName) ||  false  ) ) { { String  tom_name=tom_get_slot_Name_String(tomMatch17Position1NameNumberAstName); { tom.engine.adt.tomterm.types.TomTerm  tom_headTerm=tom_get_slot_BuildAppendArray_HeadTerm(tomMatch17Position1); { tom.engine.adt.tomterm.types.TomTerm  tom_tailTerm=tom_get_slot_BuildAppendArray_TailTerm(tomMatch17Position1); if ( true ) {


        output.write("tom_append_array_" + tom_name+ "(");
        generate(deep,tom_headTerm,moduleName);
        output.write(",");
        generate(deep,tom_tailTerm,moduleName);
        output.write(")");
        return;
       } } } } } } } } }

  }

  protected void buildFunctionCall(int deep, String name, TomList argList, String moduleName) throws IOException {
    output.write(name);
    output.writeOpenBrace();
    while(!argList.isEmptyconcTomTerm()) {
      generate(deep,argList.getHeadconcTomTerm(),moduleName);
      argList = argList.getTailconcTomTerm();
      if(!argList.isEmptyconcTomTerm()) {
        output.writeComa();
      }
    }
    output.writeCloseBrace();
  }


  protected void genDeclArray(String name, String moduleName) throws IOException {
    if(nodeclMode) {
      return;
    }

    TomSymbol tomSymbol = getSymbolTable(moduleName).getSymbolFromName(name);
    TomType listType = TomBase.getSymbolCodomain(tomSymbol);
    TomType eltType = TomBase.getSymbolDomain(tomSymbol).getHeadconcTomType();

    String s = "";
    String tomType = TomBase.getTomType(listType);
    String glType = TomBase.getTLType(listType);
    //String tlEltType = getTLType(eltType);
    String utype = glType;
    if(lazyMode) {
      utype =  TomBase.getTLType(getUniversalType());
    }
    
    String listCast = "(" + glType + ")";
    String eltCast = "(" + TomBase.getTLType(eltType) + ")";
    String make_empty = listCast + "tom_empty_array_" + name;
    String make_insert = listCast + "tom_cons_array_" + name;
    String get_element = eltCast + "tom_get_element_" + name +"_" + tomType;
    String get_size = "tom_get_size_" + name +"_" + tomType;
    
    s = modifier + utype + " tom_get_slice_" + name +  "(" + utype + " subject, int begin, int end) {\n";
    s+= "   " + glType + " result = " + make_empty + "(end - begin);\n";
    s+= "    while( begin != end ) {\n";
    s+= "      result = " + make_insert + "(" + get_element + "(subject, begin),result);\n";
    s+= "      begin++;\n";
    s+= "     }\n";
    s+= "    return result;\n";
    s+= "  }\n";
    s+= "\n";
    
    s+= modifier + utype + " tom_append_array_" + name +  "(" + utype + " l2, " + utype + " l1) {\n";
    s+= "    int size1 = " + get_size + "(l1);\n";
    s+= "    int size2 = " + get_size + "(l2);\n";
    s+= "    int index;\n";
    s+= "   " + glType + " result = " + make_empty + "(size1+size2);\n";

    s+= "    index=size1;\n";
    s+= "    while(index > 0) {\n";
    s+= "      result = " + make_insert + "(" + get_element + "(l1,(size1-index)),result);\n";
    s+= "      index--;\n";
    s+= "    }\n";

    s+= "    index=size2;\n";
    s+= "    while(index > 0) {\n";
    s+= "      result = " + make_insert + "(" + get_element + "(l2,(size2-index)),result);\n";
    s+= "      index--;\n";
    s+= "    }\n";
   
    s+= "    return result;\n";
    s+= "  }\n";

    //If necessary we remove \n code depending on pretty option
    String res = ASTFactory.makeSingleLineCode(s, prettyMode);
    output.write(res);
  }

} // class TomGenericGenerator
