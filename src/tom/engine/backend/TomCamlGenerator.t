/*
  
TOM - To One Matching Compiler

Copyright (C) 2000-2003 INRIA
Nancy, France.

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA

Pierre-Etienne Moreau	e-mail: Pierre-Etienne.Moreau@loria.fr

*/

package jtom.backend;
 
import aterm.*;

import java.io.IOException;

import jtom.adt.tomsignature.types.*;
import jtom.tools.TomTaskInput;
import jtom.tools.OutputCode;
import jtom.exception.TomRuntimeException;
import jtom.TomEnvironment;

public class TomCamlGenerator extends TomImperativeGenerator {

	protected String modifier = "";
  public TomCamlGenerator(TomEnvironment environment, OutputCode output, TomTaskInput input) {
		super(environment, output, input);
  }

// ------------------------------------------------------------
  %include { ../../adt/TomSignature.tom }
// ------------------------------------------------------------

	/*
	 * the method implementations are here for caml 
	 */
  protected void buildLet(int deep, TomTerm var, OptionList list, String type, TomType tlType, 
                          Expression exp, Instruction body) throws IOException {

    output.indent(deep);
    output.writeln("(");
    //buildDeclaration(deep,var,type,tlType);
		output.write("let ");
    generate(deep,var);
    output.write(" = ");
    generateExpression(deep,exp);
    output.writeln(" in (");

//     if(debugMode && !list.isEmpty()) {
//       output.write("jtom.debug.TomDebugger.debugger.addSubstitution(\""+debugKey+"\",\"");
//       generate(deep,var);
//       output.write("\", ");
//       generate(deep,var); // generateExpression(out,deep,exp);
//       output.writeln(");");
//     }
    generateInstruction(deep,body);
    output.writeln(" ) ");
    output.writeln(" ) ");
  }

	protected void buildIfThenElse(int deep, Expression exp, TomList succesList) throws IOException {
		output.write(deep,"if "); 
		generateExpression(deep,exp); 
		output.writeln(" then");
		generateList(deep+1,succesList);
		output.writeln(deep,"(* else () *) ");
  }

  protected void buildIfThenElseWithFailure(int deep, Expression exp, TomList succesList, TomList failureList) throws IOException {
		output.write(deep,"if "); 
		generateExpression(deep,exp); 
		output.writeln(" then ");
		generateList(deep+1,succesList);
		output.writeln(deep," else ");
		generateList(deep+1,failureList);
		output.writeln(deep," (* endif *)");
  }

	protected TargetLanguage genDecl(String returnType,
																	 String declName,
																	 String suffix,
																	 String args[],
																	 TargetLanguage tlCode) {
    String s = "";
    if(!genDecl) { return null; }
		s =  "let " + declName + "_" + suffix + "(";
		for(int i=0 ; i<args.length ; ) {
			// the first argument is the type, second the name 
			s+= args[i+1];
			i+=2;
			if(i<args.length) {
				s+= ", ";
			}
		} 
		s += ") = " + tlCode.getCode() + " ";
    if(tlCode.isTL())
      return `TL(s, tlCode.getStart(), tlCode.getEnd());
    else
      return `ITL(s);
		}

  protected TargetLanguage genDeclMake(String opname, TomType returnType, 
                                            TomList argList, TargetLanguage tlCode) {
		//%variable
    String s = "";
    if(!genDecl) { return null; }
      
		s = "let tom_make_" + opname + "(";
		while(!argList.isEmpty()) {
			TomTerm arg = argList.getHead();
			matchBlock: {
				%match(TomTerm arg) {
// 					Variable(option,Name(name), Type(ASTTomType(type),tlType@TLType[])) -> {
// 					in caml, we are not interested in the type of arguments
					Variable(option,Name(name), _) -> {
						s += name;
						break matchBlock;
					}
            
					_ -> {
						System.out.println("genDeclMake: strange term: " + arg);
						throw new TomRuntimeException(new Throwable("genDeclMake: strange term: " + arg));
					}
				}
			}
			argList = argList.getTail();
			if(!argList.isEmpty()) {
				s += ", ";
			}
		}
		s += ") = ";
// 		the debug mode will not work as it for caml
		if (debugMode) {
			s += "\n"+getTLType(returnType)+ " debugVar = " + tlCode.getCode() +";\n";
			s += "jtom.debug.TomDebugger.debugger.termCreation(debugVar);\n";
			s += "return  debugVar;\n}";
		} else {
			s += tlCode.getCode() + " ";
		}
    return `TL(s, tlCode.getStart(), tlCode.getEnd());
  }

	protected void buildDeclaration(int deep, TomTerm var, String type, TomType tlType) throws IOException {
		output.write(deep,"let ");
		generate(deep,var);
    System.out.println("buildDeclaration : this is a deprecated code");
		output.writeln(" = ref None");
	}


  protected void buildExpTrue(int deep) throws IOException {
		output.write(" true ");
	}
  
  protected void buildExpFalse(int deep) throws IOException {
		output.write(" false ");
  }

  protected void buildNamedBlock(int deep, String blockName, TomList instList) throws IOException {
		// no named blocks in caml : ignore the name
    output.writeln("(");
    generateList(deep+1,instList);
    output.writeln(")");
  }

  protected void buildExitAction(int deep, TomNumberList numberList) throws IOException {
		System.out.println(" Deprecated intermediate code : break is evil");
  }


} // class TomCamlGenerator
