
























package tom.engine.backend;



import java.io.IOException;



import tom.engine.TomBase;
import tom.engine.exception.TomRuntimeException;



import tom.engine.adt.tomsignature.*;
import tom.engine.adt.code.types.*;
import tom.engine.adt.tomterm.types.*;
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



import tom.engine.tools.OutputCode;
import tom.engine.tools.SymbolTable;
import tom.engine.tools.ASTFactory;
import tom.platform.OptionManager;



public class PythonGenerator extends GenericGenerator {

  
  
  


  public PythonGenerator(OutputCode output, OptionManager optionManager,
                                SymbolTable symbolTable) {
    super(output, optionManager, symbolTable);
  }

  

  protected void buildAssign(int deep, BQTerm var, OptionList optionList, Expression exp, String moduleName) throws IOException {
    
    generateBQTerm(deep,var,moduleName);
    output.write("=");
    generateExpression(deep,exp,moduleName);
    output.write(";\n");
  } 

  protected void buildComment(int deep, String text) throws IOException {
    
  }
 
  protected void buildDoWhile(int deep, Instruction succes, Expression exp, String moduleName) throws IOException {
    generateInstruction(deep,succes,moduleName);
    buildWhileDo(deep,exp,succes,moduleName);
  }

  protected void buildExpEqualTerm(int deep, TomType type, BQTerm exp1, TomTerm exp2, String moduleName) throws IOException {
    if(getSymbolTable(moduleName).isBooleanType(TomBase.getTomType(type))) {
      output.write("(");
      generateBQTerm(deep,exp1,moduleName);
      output.write(" == ");
      generateTomTerm(deep,exp2,moduleName);
      output.write(")");
    } else {
      output.write("tom_equal_term_" + TomBase.getTomType(type) + "(");
      generateBQTerm(deep,exp1,moduleName);
      output.write(", ");
      generateTomTerm(deep,exp2,moduleName);
      output.write(")");
    }
  }

  protected void buildExpEqualBQTerm(int deep, TomType type, BQTerm exp1, BQTerm exp2, String moduleName) throws IOException {
    if(getSymbolTable(moduleName).isBooleanType(TomBase.getTomType(type))) {
      output.write("(");
      generateBQTerm(deep,exp1,moduleName);
      output.write(" == ");
      generateBQTerm(deep,exp2,moduleName);
      output.write(")");
    } else {
      output.write("tom_equal_term_" + TomBase.getTomType(type) + "(");
      generateBQTerm(deep,exp1,moduleName);
      output.write(", ");
      generateBQTerm(deep,exp2,moduleName);
      output.write(")");
    }
  }

  protected void buildExpConditional(int deep, Expression cond,Expression exp1, Expression exp2, String moduleName) throws IOException {
    output.write("((");
    generateExpression(deep,exp1,moduleName);
    output.write(") if (");
    generateExpression(deep,cond,moduleName);
    output.write(") else (");
    generateExpression(deep,exp2,moduleName);
    output.write("))");
  }

  protected void buildExpAnd(int deep, Expression exp1, Expression exp2, String moduleName) throws IOException {
    output.write(" ( ");
    generateExpression(deep,exp1,moduleName);
    output.write(" and ");
    generateExpression(deep,exp2,moduleName);
    output.write(" ) ");
  }

  protected void buildExpOr(int deep, Expression exp1, Expression exp2, String moduleName) throws IOException {
    output.write(" ( ");
    generateExpression(deep,exp1,moduleName);
    output.write(" or ");
    generateExpression(deep,exp2,moduleName);
    output.write(" ) ");
  }

  protected void buildExpCast(int deep, TargetLanguageType tlType, Expression exp, String moduleName) throws IOException {
    generateExpression(deep,exp,moduleName);
  }

  protected void buildExpNegation(int deep, Expression exp, String moduleName) throws IOException {
    output.write("not (");
    generateExpression(deep,exp,moduleName);
    output.write(")");
  }

  protected void buildIf(int deep, Expression exp, Instruction succes, String moduleName) throws IOException {
    output.write(deep,"if "); 
    generateExpression(deep,exp, moduleName); 
    output.writeln(":\n");
    generateInstruction(deep+1,succes, moduleName);
    output.write(deep,"\n# end if\n"); 
  }

  protected void buildIfWithFailure(int deep, Expression exp, Instruction succes, Instruction failure, String moduleName) throws IOException {
    output.write(deep,"if "); 
    generateExpression(deep,exp,moduleName); 
    output.writeln(":\n");
    generateInstruction(deep+1,succes,moduleName);
    output.writeln(deep,"else:\n");
    generateInstruction(deep+1,failure,moduleName);
    output.write(deep,"\n# end if\n"); 
  }

  protected void buildInstructionSequence(int deep, InstructionList instructionList, String moduleName) throws IOException {
    generateInstructionList(deep, instructionList, moduleName);
  }

  protected void buildLet(int deep, BQTerm var, OptionList optionList, TargetLanguageType tlType, 
      Expression exp, Instruction body, String moduleName) throws IOException {
    buildAssign(deep,var,optionList,exp,moduleName);
    generateInstruction(deep,body,moduleName);
  }
  
  protected void buildLetRef(int deep, BQTerm var, OptionList optionList, TargetLanguageType tlType, 
      Expression exp, Instruction body, String moduleName) throws IOException {
    buildLet(deep,var,optionList,tlType,exp,body, moduleName);
  }

  protected void buildReturn(int deep, BQTerm exp, String moduleName) throws IOException {
    output.write(deep,"return ");
    generateBQTerm(deep,exp,moduleName);
  }

  
  protected void buildUnamedBlock(int deep, InstructionList instList, String moduleName) throws IOException {
    generateInstructionList(deep+1,instList, moduleName);
  }

  protected void buildWhileDo(int deep, Expression exp, Instruction succes, String moduleName) throws IOException {
    output.write(deep,"while ");
    generateExpression(deep,exp,moduleName);
    output.writeln(":\n");
    generateInstruction(deep+1,succes,moduleName);
    output.write(deep,"\n# end while\n"); 
  }

  protected void genDeclInstr(String returnType,
                         String declName,
                         String suffix,
                         String args[],
                         Instruction instr,
                         int deep, String moduleName) throws IOException {
    StringBuilder s = new StringBuilder();
    if(nodeclMode) {
      return;
    }
    s.append("def " + declName + "_" + suffix + "(");
    for(int i=0 ; i<args.length ; ) {
      s.append(args[i+1]);
      i+=2;
      if(i<args.length) {
        s.append(", ");
      }
    } 

    s.append("): ");
    output.write(deep,s);
    generateInstruction(deep+1,instr,moduleName);
    output.writeln("\n# end def " + declName + "_" + suffix + "\n");
  }


  protected void genDeclList(String name, String moduleName) throws IOException {
    TomSymbol tomSymbol = getSymbolTable(moduleName).getSymbolFromName(name);
    TomType listType = TomBase.getSymbolCodomain(tomSymbol);
    TomType eltType = TomBase.getSymbolDomain(tomSymbol).getHeadconcTomType();

    String s = "";
    if(nodeclMode) {
      return;
    }

    String tomType = TomBase.getTomType(listType);
    String glType = TomBase.getTLType(listType);
    

    
    
    String is_empty = "tom_is_empty_" + name + "_" + tomType;
    String equal_term = "tom_equal_term_" + tomType;
    String make_insert = "tom_cons_list_" + name;
    String make_empty = "tom_empty_list_" + name;
    String get_head = "tom_get_head_" + name + "_" + tomType;
    String get_tail = "tom_get_tail_" + name + "_" + tomType;
    String get_slice = "tom_get_slice_" + name;

    s+= "def tom_append_list_" + name +  "(l1, l2):\n";
    s+= "   if " + is_empty + "(l1):\n";
    s+= "     return l2\n";  
    s+= "   elif " + is_empty + "(l2):\n";
    s+= "     return l1\n";  
    s+= "   elif " + is_empty + "(" + get_tail + "(l1)): \n";  
    s+= "     return " + make_insert + "(" + get_head + "(l1),l2)\n";
    s+= "   else:\n";  
    s+= "     return " + make_insert + "(" + get_head + "(l1),tom_append_list_" + name +  "(" + get_tail + "(l1),l2));\n";
    s+= "# end if\n";
    s+= "# end def tom_append_list_" + name;
    s+= "\n";
    
    s+= "def tom_get_slice_" + name + "(begin, end,tail):\n"; 
    s+= "   if " + equal_term + "(begin,end):\n";
    s+= "      return tail\n";
    s+= "   else:\n";
    s+= "      return " +  make_insert + "(" + get_head + "(begin)," + 
      get_slice + "(" + get_tail + "(begin),end,tail));\n";
    s+= "# end if\n";
    s+= "# end def tom_get_slice_" + name;
    s+= "\n";
    
    
    output.write(s);
  }

  protected void genDeclMake(String prefix,String funName, TomType returnType, 
      BQTermList argList, Instruction instr, String moduleName) throws IOException {
    StringBuilder s = new StringBuilder();
    StringBuilder check = new StringBuilder();
    if( nodeclMode) {
      return;
    }

    s.append("def " + prefix+funName + "(");
    while(!argList.isEmptyconcBQTerm()) {
      BQTerm arg = argList.getHeadconcBQTerm();
matchBlock: {
              { /* unamed block */{ /* unamed block */if ( (arg instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )arg) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) { tom.engine.adt.tomname.types.TomName  tomMatch115_1= (( tom.engine.adt.code.types.BQTerm )arg).getAstName() ; tom.engine.adt.tomtype.types.TomType  tomMatch115_2= (( tom.engine.adt.code.types.BQTerm )arg).getAstType() ;if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch115_1) instanceof tom.engine.adt.tomname.types.tomname.Name) ) {if ( ((( tom.engine.adt.tomtype.types.TomType )tomMatch115_2) instanceof tom.engine.adt.tomtype.types.tomtype.Type) ) {if ( ((( tom.engine.adt.tomtype.types.TargetLanguageType ) tomMatch115_2.getTlType() ) instanceof tom.engine.adt.tomtype.types.targetlanguagetype.TLType) ) {

                  s.append( tomMatch115_1.getString() );
                  break matchBlock;
                }}}}}}{ /* unamed block */if ( (arg instanceof tom.engine.adt.code.types.BQTerm) ) {


                  System.out.println("genDeclMake: strange term: " + arg);
                  throw new TomRuntimeException("genDeclMake: strange term: " + arg);
                }}}

            }
            argList = argList.getTailconcBQTerm();
            if(!argList.isEmptyconcBQTerm()) {
              s.append(", ");
            }
    }
    s.append("): ");
    s.append(check);

    output.write(s);
    output.write("return ");
    generateInstruction(0,instr,moduleName);
    output.write("\n # end def " + prefix+funName + "\n");
  }

  
  protected void buildNamedBlock(int deep, String blockName, InstructionList instList, String moduleName) throws IOException {
    
    generateInstructionList(deep+1,instList,moduleName);
    
  }

  protected void buildExpTrue(int deep) throws IOException {
    output.write(" True ");
  }

  protected void buildExpFalse(int deep) throws IOException {
    output.write(" False ");
  }

  protected void buildExpBottom(int deep, TomType type, String moduleName) throws IOException {
    if ((getSymbolTable(moduleName).getIntType() == type)
        || (getSymbolTable(moduleName).getCharType() == type)
        || (getSymbolTable(moduleName).getLongType() == type)
        || (getSymbolTable(moduleName).getFloatType() == type)
        || (getSymbolTable(moduleName).getDoubleType() == type)) {
      output.write(" 0 ");
    } else if (getSymbolTable(moduleName).getBooleanType() == type) {
      output.write(" False ");
    } else if (getSymbolTable(moduleName).getStringType() == type) {
      output.write(" \"\" ");
    } else {
      output.write(" None ");
    }
  }

  protected void buildFunctionDef(int deep, String tomName, BQTermList argList, TomType codomain, TomType throwsType, Instruction instruction, String moduleName) throws IOException {
    buildMethod(deep,tomName,argList,codomain,throwsType,instruction,moduleName,this.modifier);
  }

  protected void buildMethodDef(int deep, String tomName, BQTermList argList, TomType codomain, TomType throwsType, Instruction instruction, String moduleName) throws IOException {
    buildMethod(deep,tomName,argList,codomain,throwsType,instruction,moduleName,"public ");
  }

  private void buildMethod(int deep, String tomName, BQTermList varList, TomType codomain, TomType throwsType, Instruction instruction, String moduleName, String methodModifier) throws IOException {
    output.write(deep, "def " + tomName + "(");
    while(!varList.isEmptyconcBQTerm()) {
      BQTerm localVar = varList.getHeadconcBQTerm();
matchBlock: {
              { /* unamed block */{ /* unamed block */if ( (localVar instanceof tom.engine.adt.code.types.BQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm )localVar) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {

                  
                  generateBQTerm(deep,(( tom.engine.adt.code.types.BQTerm )localVar),moduleName);
                  break matchBlock;
                }}}{ /* unamed block */if ( (localVar instanceof tom.engine.adt.code.types.BQTerm) ) {

                  System.out.println("MakeFunction: strange term: " + localVar);
                  throw new TomRuntimeException("MakeFunction: strange term: " + localVar);
                }}}

            }
            varList = varList.getTailconcBQTerm();
            if(!varList.isEmptyconcBQTerm()) {
              output.write(deep,", ");

            }
    }
    output.writeln(deep,")");

    output.writeln(": ");
    generateInstruction(deep+1,instruction,moduleName);
    output.write(deep, "\n# end def " + tomName + "\n");
  }

  protected void genDecl(String returnType,
      String declName,
      String suffix,
      String args[],
      TargetLanguage tlCode,
      String moduleName) throws IOException {
    StringBuilder s = new StringBuilder();
    if(nodeclMode) {
      return;
    }
    s.append("def " + declName + "_" + suffix + "(");
    for(int i=0 ; i<args.length ; ) {
      s.append(args[i+1]);
      i+=2;
      if(i<args.length) {
        s.append(", ");
      }
    } 
    String returnValue = getSymbolTable(moduleName).isVoidType(returnType)?tlCode.getCode():"return " + tlCode.getCode();
    s.append("):\n " + returnValue + "\n");
    s.append("\n # end def " + declName + "_" + suffix + "\n");
    output.write(s);
  }

  protected void buildAssignArray(int deep, BQTerm var, OptionList optionList, BQTerm index, 
      Expression exp, String moduleName) throws IOException {
    buildAssignArrayVar(deep,var,optionList, index, exp, moduleName);
  }

  protected void buildAssignArrayVar(int deep, BQTerm var, OptionList optionList, BQTerm index, 
      Expression exp, String moduleName) throws IOException {    
    
    generateArray(deep,var,index,moduleName);
    output.write("=");
    generateExpression(deep,exp,moduleName);
    output.write(";\n");
  } 

  
  
  protected String genResolveIsSortCode(String varName, String
      resolveStringName) throws IOException {
    throw new TomRuntimeException("%transformation (ResolveIsSort) not yet supported in Python");
    
  }

  protected String genResolveIsFsymCode(String tomName, String varname) throws IOException {
    throw new TomRuntimeException("%transformation (ResolveIsFsym) not yet supported in Python");
    
  }

  protected String genResolveGetSlotCode(String tomName, String varname, String slotName) throws IOException {
    throw new TomRuntimeException("%transformation (ResolveGetSlot) not yet supported in Python");
    
  }
  
  protected void buildResolveClass(String wName, String tName, String extendsName, String moduleName) throws IOException {
    throw new TomRuntimeException("%transformation (ResolveClass) not yet supported in Python");
  }

  protected void buildResolveInverseLinks(int deep, String fileFrom, String fileTo, TomNameList resolveNameList, String moduleName) throws IOException {
    throw new TomRuntimeException("%transformation (ResolveInverseLinks) not yet supported in Python");
  }

  protected void genResolveDeclMake(String prefix, String funName, TomType returnType, BQTermList argList, String moduleName) throws IOException {
    throw new TomRuntimeException("%transformation (ResolveDeclMake) not yet supported in Python");
  }

  protected String genResolveMakeCode(String funName, BQTermList argList) throws IOException {
    throw new TomRuntimeException("%transformation (ResolveMakeCode) not yet supported in Python");
  }

  protected void buildReferenceClass(int deep, String refname, RefClassTracelinkInstructionList refclassTInstructions, String  moduleName) {
    throw new TomRuntimeException("%transformation (ResolveReferenceClass) not yet supported in Python");
  }

  protected void buildTracelink(int deep, String type, String name, Expression expr, String moduleName) throws IOException {
    throw new TomRuntimeException("%transformation (Tracelink instruction) not  yet supported in Python");
  }

  protected void buildTracelinkPopulateResolve(int deep, String refClassName, TomNameList tracedLinks, BQTerm current, BQTerm link, String moduleName) throws IOException {
    throw new TomRuntimeException("%transformation (TracelinkPopulateResolve instruction) not yet supported in Python");
  }

  
  protected void buildResolve(int deep, BQTerm bqterm, String moduleName) throws IOException {
    throw new TomRuntimeException("%transformation (Resolve2 instruction) not yet supported in Python");
  }



}
