/* Generated by TOM (version 3.0alpha): Do not edit this file *//*
 *   
 * TOM - To One Matching Compiler
 * 
 * Copyright (C) 2000-2004 INRIA
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

package jtom.backend;

import java.io.IOException;

import jtom.adt.tomsignature.types.*;

import jtom.tools.OutputCode;
import jtom.exception.TomRuntimeException;

public class TomEiffelGenerator extends TomImperativeGenerator {
  
  public TomEiffelGenerator(OutputCode output) {
    super(output);
  }

// ------------------------------------------------------------
  /* Generated by TOM (version 3.0alpha): Do not edit this file *//* Generated by TOM (version 3.0alpha): Do not edit this file *//*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  /* Generated by TOM (version 3.0alpha): Do not edit this file *//*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/     /*  * old definition of String %typeterm String {   implement           { String }   get_fun_sym(t)      { t }   cmp_fun_sym(s1,s2)  { s1.equals(s2) }   get_subterm(t, n)   { null }   equals(t1,t2)       { t1.equals(t2) } } */ /* Generated by TOM (version 3.0alpha): Do not edit this file *//*  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  */   /* Generated by TOM (version 3.0alpha): Do not edit this file *//*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/   /* Generated by TOM (version 3.0alpha): Do not edit this file *//*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/   /* Generated by TOM (version 3.0alpha): Do not edit this file *//*  *  * Copyright (c) 2004, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/    
// ------------------------------------------------------------

  protected void buildComment(int deep, String text) throws IOException {
    output.writeln("-- " + text);
    return;
  }

  protected void buildTerm(int deep, String name, TomList argList) throws IOException {
    output.write("tom_make_");
    output.write(name);
    if(argList.isEmpty()) { // strange ?
    } else {
      output.writeOpenBrace();
      while(!argList.isEmpty()) {
        generate(deep,argList.getHead());
        argList = argList.getTail();
        if(!argList.isEmpty()) {
              output.writeComa();
        }
      }
      output.writeCloseBrace();
    }
  }

  protected void buildFunctionBegin(int deep, String tomName, TomList varList) throws IOException {
        TomSymbol tomSymbol = symbolTable().getSymbol(tomName);
        String glType = getTLType(getSymbolCodomain(tomSymbol));
        String name = tomSymbol.getAstName().getString();
        output.write(deep,name + "(");
        while(!varList.isEmpty()) {
          TomTerm localVar = varList.getHead();
          matchBlock: {
             { jtom.adt.tomsignature.types.TomTerm tom_match1_1=(( jtom.adt.tomsignature.types.TomTerm)localVar);{ if(tom_is_fun_sym_Variable(tom_match1_1) ||  false ) { { jtom.adt.tomsignature.types.TomTerm v=tom_match1_1; { jtom.adt.tomsignature.types.TomType tom_match1_1_3=tom_get_slot_Variable_astType(tom_match1_1); { jtom.adt.tomsignature.types.TomType type2=tom_match1_1_3;

                  generate(deep,v);
                  output.write(deep,": " + getTLType(type2));
                break matchBlock;
              }}} }

                System.out.println("MakeFunction: strange term: " + localVar);
                throw new TomRuntimeException("MakeFunction: strange term: " + localVar);
              }}

          }
          varList = varList.getTail();
          if(!varList.isEmpty()) {
              output.write(deep,"; ");
          }
        }
        output.writeln(deep,"): " + glType + " is");
        output.writeln(deep,"local ");
        //out.writeln(deep,"return null;");
  }
  
  protected void buildFunctionEnd(int deep) throws IOException {
      output.writeln(deep,"end;");
  }
  
  protected void buildExpNot(int deep, Expression exp) throws IOException {
    output.write("not ");
    generateExpression(deep,exp);
  }

  protected void buildExpTrue(int deep) throws IOException {
    output.write(" true ");
  }
  
  protected void buildExpFalse(int deep) throws IOException {
    output.write(" false ");
  }

  protected void buildAssignVar(int deep, TomTerm var, OptionList list, Expression exp) throws IOException {
    output.indent(deep);
    generate(deep,var);
      //out.write(" ?= ");
    String assignSign = " := ";
     { jtom.adt.tomsignature.types.Expression tom_match2_1=(( jtom.adt.tomsignature.types.Expression)exp);{ if(tom_is_fun_sym_GetSubterm(tom_match2_1) ||  false ) {

        assignSign = " ?= ";
       }}}

      output.write(assignSign);
    generateExpression(deep,exp);
    output.writeln(";");
    if(((Boolean)getServer().getOptionValue("debug")).booleanValue() && !list.isEmpty()) {
      output.write("jtom.debug.TomDebugger.debugger.addSubstitution(\""+debugKey+"\",\"");
      generate(deep,var);
      output.write("\", ");
      generateExpression(deep,exp);
      output.writeln(");");
    }
  }

  protected void buildExpCast(int deep, TomType tlType, Expression exp) throws IOException {
    generateExpression(deep,exp);
  }

  protected void buildLet(int deep, TomTerm var, OptionList list, TomType tlType, 
                          Expression exp, Instruction body) throws IOException {
    System.out.println("buildLet code not yet implemented");
    throw new TomRuntimeException("buildLet: Eiffel code not yet implemented");
  }

  protected void buildLetRef(int deep, TomTerm var, OptionList optionList,
                             TomType tlType, 
                             Expression exp, Instruction body) throws IOException {
    System.out.println("buildLetRef code not yet implemented");
    throw new TomRuntimeException("buildLetRef: Eiffel code not yet implemented");
  }

  
  protected void buildAssignMatch(int deep, TomTerm var, String type, TomType tlType, Expression exp) throws IOException {
    output.indent(deep);
    generate(deep,var);
    if(symbolTable().isBoolType(type) || symbolTable().isIntType(type) || symbolTable().isDoubleType(type)) {
                  output.write(" := ");
    } else {
        //out.write(" ?= ");
      String assignSign = " := ";
       { jtom.adt.tomsignature.types.Expression tom_match3_1=(( jtom.adt.tomsignature.types.Expression)exp);{ if(tom_is_fun_sym_GetSubterm(tom_match3_1) ||  false ) {

          assignSign = " ?= ";
         }}}

      output.write(assignSign);
    }
    generateExpression(deep,exp);
    output.writeln(";");
    if(((Boolean)getServer().getOptionValue("debug")).booleanValue()) {
      output.write("jtom.debug.TomDebugger.debugger.specifySubject(\""+debugKey+"\",\"");
      generateExpression(deep,exp);
      output.write("\",");
      generateExpression(deep,exp);
      output.writeln(");");
    }
  }

  protected void buildNamedBlock(int deep, String blockName, InstructionList instList) throws IOException {
    System.out.println("NamedBlock: Eiffel code not yet implemented");
    throw new TomRuntimeException("NamedBlock: Eiffel code not yet implemented");
  }

  protected void buildUnamedBlock(int deep, InstructionList instList) throws IOException {
    System.out.println("UnamedBlock: Eiffel code not yet implemented");
    throw new TomRuntimeException("NamedBlock: Eiffel code not yet implemented");
  }

  protected void buildIfThenElse(int deep, Expression exp, TomList succesList) throws IOException {
    output.write(deep,"if "); generateExpression(deep,exp); output.writeln(" then ");
    generateList(deep+1,succesList);
    output.writeln(deep,"end;");
  }

  protected void buildIfThenElseWithFailure(int deep, Expression exp, TomList succesList, TomList failureList) throws IOException {
    output.write(deep,"if "); generateExpression(deep,exp); output.writeln(" then ");
    generateList(deep+1,succesList);
    output.writeln(deep," else ");
    generateList(deep+1,failureList);
    output.writeln(deep,"end;");
  }

  protected void buildExitAction(int deep, TomNumberList numberList) throws IOException {
      System.out.println("ExitAction: Eiffel code not yet implemented");
      throw new TomRuntimeException("ExitAction: Eiffel code not yet implemented");
  }

  protected void buildReturn(int deep, TomTerm exp) throws IOException {
    output.writeln(deep,"if Result = Void then");
    output.write(deep+1,"Result := ");
    generate(deep+1,exp);
    output.writeln(deep+1,";");
    output.writeln(deep,"end;");
  }

    protected void buildExpGetHead(int deep, TomType domain, TomType codomain, TomTerm var) throws IOException {
    output.write("tom_get_head_" + getTomType(domain) + "(");
    generate(deep,var);
    output.write(")");
  }

  protected void buildExpGetElement(int deep, TomType domain, TomType codomain, TomTerm varName, TomTerm varIndex) throws IOException {
    output.write("tom_get_element_" + getTomType(domain) + "(");
    generate(deep,varName);
    output.write(",");
    generate(deep,varIndex);
    output.write(")");
  }

    protected void buildExpGetSubterm(int deep, TomType domain, TomType codomain, TomTerm exp, int number) throws IOException {
    String s = (String)getSubtermMap.get(domain);
    if(s == null) {
      s = "tom_get_subterm_" + getTomType(domain) + "(";
      getSubtermMap.put(domain,s);
    }
    output.write(s);
    generate(deep,exp);
    output.write(", " + number + ")");
  }

  protected void buildGetSubtermDecl(int deep, String name1, String name2, String type1, TomType tlType1, TomType tlType2, TargetLanguage tlCode) throws IOException {
    String args[];
    if(!((Boolean)getServer().getOptionValue("lazyType")).booleanValue()) {
      args = new String[] { getTLCode(tlType1), name1,
                            getTLCode(tlType2), name2 };
    } else {
      args = new String[] { getTLType(getUniversalType()), name1,
                            getTLCode(tlType2), name2 };
    }
    generateTargetLanguage(deep, genDecl(getTLType(getUniversalType()), "tom_get_subterm", type1,
                                         args, tlCode));
  }
  
  protected TargetLanguage genDecl(int deep, String returnType,
                                   String declName,
                                   String suffix,
                                   String args[],
                                   TargetLanguage tlCode) {
    String s = "";
    if(((Boolean)getServer().getOptionValue("noDeclaration")).booleanValue()) { return null; }
    s = declName + "_" + suffix + "(";
    for(int i=0 ; i<args.length ; ) {
      s+= args[i+1] + ": " + args[i];
      i+=2;
      if(i<args.length) {
        s+= "; ";
      }
    } 
    s += "): " + returnType + " is do Result := " + tlCode.getCode() + "end;";
    if(tlCode.isTL())
      return tom_make_TL(s, tlCode.getStart(), tlCode.getEnd());
    else
      return tom_make_ITL(s);
  }

  protected TargetLanguage genDeclMake(int deep, String opname, TomType returnType, 
                                            TomList argList, TargetLanguage tlCode) throws IOException {
      //%variable
    String s = "";
    if(((Boolean)getServer().getOptionValue("noDeclaration")).booleanValue()) { return null; }
    boolean braces = !argList.isEmpty();
    s = "tom_make_" + opname;
    if(braces) {
      s = s + "(";
    }
    while(!argList.isEmpty()) {
      TomTerm arg = argList.getHead();
      matchBlock: {
         { jtom.adt.tomsignature.types.TomTerm tom_match4_1=(( jtom.adt.tomsignature.types.TomTerm)arg);{ if(tom_is_fun_sym_Variable(tom_match4_1) ||  false ) { { jtom.adt.tomsignature.types.TomName tom_match4_1_2=tom_get_slot_Variable_astName(tom_match4_1); { jtom.adt.tomsignature.types.TomType tom_match4_1_3=tom_get_slot_Variable_astType(tom_match4_1); if(tom_is_fun_sym_Name(tom_match4_1_2) ||  false ) { { String  tom_match4_1_2_1=tom_get_slot_Name_string(tom_match4_1_2); { String  name=tom_match4_1_2_1; if(tom_is_fun_sym_Type(tom_match4_1_3) ||  false ) { { jtom.adt.tomsignature.types.TomType tom_match4_1_3_2=tom_get_slot_Type_tlType(tom_match4_1_3); if(tom_is_fun_sym_TLType(tom_match4_1_3_2) ||  false ) { { jtom.adt.tomsignature.types.TomType tlType=tom_match4_1_3_2;

            s += name + ": " + getTLCode(tlType);
            break matchBlock;
          } }} }}} }}} }


            System.out.println("genDeclMake: strange term: " + arg);
            throw new TomRuntimeException("genDeclMake: strange term: " + arg);
          }}

      }
      argList = argList.getTail();
      if(!argList.isEmpty()) {
        s += "; ";
      }
    }
      if(braces) {
        s = s + ")";
      }
      s += ": " + getTLType(returnType) + " is do Result := " + tlCode.getCode() + "end;";
      return tom_make_TL(s, tlCode.getStart(), tlCode.getEnd());
  }

  protected TargetLanguage genDeclList(int deep, String name, TomType listType, TomType eltType) throws IOException {
      //%variable
    String s = "";
    if(((Boolean)getServer().getOptionValue("noDeclaration")).booleanValue()) { return null; }
    System.out.println("genDeclList: Eiffel code not yet implemented");
    return null;
  }


  protected TargetLanguage genDeclArray(int deep, String name, TomType listType, TomType eltType) throws IOException {
    //%variable
    String s = "";
    if(((Boolean)getServer().getOptionValue("noDeclaration")).booleanValue()) { return null; }
    System.out.println("genDeclArray: Eiffel code not yet implemented");
    return null;
  }

  protected TargetLanguage genDecl(String returnType,
                        String declName,
                        String suffix,
                        String args[],
                        TargetLanguage tlCode) {
    String s = "";
    if(((Boolean)getServer().getOptionValue("noDeclaration")).booleanValue()) { return null; }
    s = declName + "_" + suffix + "(";
    for(int i=0 ; i<args.length ; ) {
      s+= args[i+1] + ": " + args[i];
      i+=2;
      if(i<args.length) {
        s+= "; ";
      }
    } 
    s += "): " + returnType + " is do Result := " + tlCode.getCode() + "end;";
    if(tlCode.isTL())
      return tom_make_TL(s, tlCode.getStart(), tlCode.getEnd());
    else
      return tom_make_ITL(s);
  }

}
