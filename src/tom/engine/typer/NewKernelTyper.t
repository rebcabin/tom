/*
 *
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2010, INPL, INRIA
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
 * Claudia Tavares  e-mail: Claudia.Tavares@loria.fr
 * Jean-Christophe Bach e-mail: Jeanchristophe.Bach@loria.fr
 *
 **/



package tom.engine.typer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import java.lang.Class;
import java.lang.reflect.*;

import tom.engine.adt.tomsignature.types.*;
import tom.engine.adt.tomconstraint.types.*;
import tom.engine.adt.tomdeclaration.types.*;
import tom.engine.adt.tomexpression.types.*;
import tom.engine.adt.tominstruction.types.*;
import tom.engine.adt.tomname.types.*;
import tom.engine.adt.tomoption.types.*;
import tom.engine.adt.tomslot.types.*;
import tom.engine.adt.tomtype.types.*;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.code.types.*;
import tom.engine.adt.typeconstraints.types.*;

import tom.engine.TomBase;
import tom.engine.TomMessage;
import tom.engine.exception.TomRuntimeException;

import tom.engine.tools.SymbolTable;
import tom.engine.tools.ASTFactory;

import tom.library.sl.*;

public class NewKernelTyper {
  %include { ../../library/mapping/java/sl.tom}
  %include { ../adt/tomsignature/TomSignature.tom }

  private static Logger logger = Logger.getLogger("tom.engine.typer.NewKernelTyper");

  %typeterm NewKernelTyper {
    implement { NewKernelTyper }
    is_sort(t) { ($t instanceof NewKernelTyper) }
  }

  private int freshTypeVarCounter = 0;
  /*
   * pem: why use a state variable here ?
   */
  // List for variables of pattern (match constraints)
  private TomList varPatternList;
  // List for variables of subject and of numeric constraints
  private BQTermList varList;
  /*
   * pem: why use a state variable here ?
   */
  // List for type constraints (for fresh type variables)
  private TypeConstraintList typeConstraints;
  // Set of pairs (freshVar,groundVar)
  private HashMap<TomType,TomType> substitutions;

  private SymbolTable symbolTable;

  private String currentInputFileName;

  protected void setSymbolTable(SymbolTable symbolTable) {
    this.symbolTable = symbolTable;
  }

  protected SymbolTable getSymbolTable() {
    return symbolTable;
  }

  protected void setCurrentInputFileName(String currentInputFileName) {
    this.currentInputFileName = currentInputFileName;
  }

  protected String getCurrentInputFileName() {
    return currentInputFileName;
  }
  protected TomType getCodomain(TomSymbol tSymbol) {
    return TomBase.getSymbolCodomain(tSymbol);
  }

  protected TomSymbol getSymbolFromTerm(TomTerm tTerm) {
    return TomBase.getSymbolFromTerm(tTerm, symbolTable);
   }

  protected TomSymbol getSymbolFromTerm(BQTerm bqTerm) {
    return TomBase.getSymbolFromTerm(bqTerm,symbolTable);
  }

  protected TomSymbol getSymbolFromName(String tName) {
    return TomBase.getSymbolFromName(tName, symbolTable);
   }

  protected TomSymbol getSymbolFromType(TomType tType) {
    %match(tType) {
       TypeWithSymbol[TomType=tomType, TlType=tlType] -> {
         return TomBase.getSymbolFromType(`Type(tomType,tlType), symbolTable); 
       }
     }
    return TomBase.getSymbolFromType(tType,symbolTable); 
   }

  // TO VERIFY: how to test if a term has an undeclared type? Maybe verifying if
  // a term has type Type(name,EmptyTargetLanguage()), where name is not
  // UNKNOWN_TYPE. So we verify the subjects of %match but, which BQTerm we need to
  // treat?
  
  protected void hasUndeclaredType(BQTerm subject) {
    String fileName = currentInputFileName;
    int line = 0;
    //DEBUG System.out.println("hasUndeclaredType: subject = " + subject);
    %match {
      (BQVariable|BQVariableStar)[Options=oList,AstType=aType] << subject && 
        TypeVar(tomType,_) << aType &&
        (tomType != symbolTable.TYPE_UNKNOWN.getTomType()) -> {
          Option option = TomBase.findOriginTracking(`oList);
          %match(option) {
            !noOption() -> {
              fileName = option.getFileName();
              line = option.getLine();
              TomMessage.error(logger, fileName, 
                  line, TomMessage.unknownSymbol,`tomType);
            }
          }
        }
    }
  }
  

  protected TomType getType(TomTerm tTerm) {
    %match(tTerm) {
      Variable[AstType=aType] -> { return `aType; }
    } 
    throw new TomRuntimeException("getType(TomTerm): should not be here.");
  }

  //TO VERIFY : getAstName and getOption
  protected Info getInfoFromTomTerm(TomTerm tTerm) {
    %match(tTerm) {
      AntiTerm[TomTerm=atomicTerm] -> { return getInfoFromTomTerm(`atomicTerm); }
      (Variable|VariableStar)[Options=optionList,AstName=aName] -> { 
        return `PairNameOptions(aName,optionList); 
      }
      RecordAppl[Options=optionList,NameList=concTomName(aName,_*)] ->  { 
        return `PairNameOptions(aName,optionList); 
      }
    } 
    return `PairNameOptions(Name(""),concOption()); 
  }

  protected Info getInfoFromBQTerm(BQTerm bqTerm) {
    %match(bqTerm) {
      (BQVariable|BQVariableStar|BQAppl)[Options=optionList,AstName=aName] -> { 
        return `PairNameOptions(aName,optionList); 
      }
    } 
    return `PairNameOptions(Name(""),concOption()); 
  }

  protected int getFreshTlTIndex() {
    return freshTypeVarCounter++;
  }

  protected TomType getUnknownFreshTypeVar() {
    TomType tType = symbolTable.TYPE_UNKNOWN;
    %match(tType) {
      Type[TomType=tomType] -> { return `TypeVar(tomType,getFreshTlTIndex()); }
    }
    throw new TomRuntimeException("getUnknownFreshTypeVar: should not be here.");
   }

  /*
     * pem: use if(...==... && typeConstraints.contains(...))
     */
  protected void addConstraint(TypeConstraint tConstraint) {
    %match {
      !concTypeConstraint(_*,typeConstraint,_*) << typeConstraints &&
      typeConstraint << TypeConstraint tConstraint -> {
        %match(typeConstraint) {
          Equation[Type1=!EmptyType(),Type2=!EmptyType()] -> { 
            typeConstraints = `concTypeConstraint(typeConstraints*,tConstraint);
          }
        }
      }
    }
  }

  protected void addTomTerm(TomTerm tTerm) {
    varPatternList = `concTomTerm(tTerm,varPatternList*);
  }

  protected void addTomList(TomList TTList) {
    varPatternList = `concTomTerm(TTList*,varPatternList*);
  }

  protected void addBQTerm(BQTerm bqTerm) {
    varList = `concBQTerm(bqTerm,varList*);
  }

  protected void addBQTermList(BQTermList BQTList) {
    varList = `concBQTerm(BQTList*,varList*);
  }

  /**
   * The method <code>resetVarPatternList</code> empties varPatternList after
   * checking if <code>varList</code> contains
   * a corresponding BQTerm in order to remove it from <code>varList</code. too
   */
  protected void resetVarPatternList() {
    for(TomTerm tTerm: varPatternList.getCollectionconcTomTerm()) {
      %match(tTerm,varList) {
        (Variable|VariableStar)[AstName=aName],concBQTerm(x*,(BQVariable|BQVariableStar)[AstName=aName],y*)
          -> {
            varList = `concBQTerm(x*,y*);
          }
      }
    }
    varPatternList = `concTomTerm();
  }

  /**
   * The method <code>init</code> empties all global lists and hashMaps which means to
   * empty <code>varPatternList</code>, <code>varList</code>,
   * <code>typeConstraints</code> and <code>substitutions</code>
   */
  private void init() {
    varPatternList = `concTomTerm();
    varList = `concBQTerm();
    typeConstraints = `concTypeConstraint();
    substitutions = new HashMap<TomType,TomType>();
  }

  /**
   * The method <code>inferCode</code> starts inference process which takes one
   * <code>%match</code> instruction ("InstructionToCode(Match(...))") at a time
   * <ul>
   *  <li> each "constraintInstructionList" element corresponds to a pair
   *  "condition -> action"
   *  <li> each pair is traversed in order to generate type constraints
   *  <li> the type constraints of "typeConstraints" list are solved at the end
   *        of the current <code>%match</code> instruction generating a mapping (a set of
   *        substitutions for each type variable)
   *  <li> the mapping is applied over the whole <code>%match</code> instruction
   *  <li> all lists and hashMaps are reset
   * </ul>
   * @param code  the tom code to be type inferred
   * @return      the tom typed code
   */
  public Code inferCode(Code code) {
    init();
    //DEBUG System.out.println("\n Original Code = \n" + code + '\n');
    %match(code) {
      Tom(codes@concCode(_,_*)) -> {
        CodeList codeResult = `concCode();
        for(Code headCodeList : `codes.getCollectionconcCode()) {
          %match(headCodeList) {
            // For %match 
            InstructionToCode(instruction) -> {
              try{
                `instruction = inferAllTypes(`instruction,getUnknownFreshTypeVar());
                `headCodeList = `InstructionToCode(instruction);
                typeConstraints = `RepeatId(solveConstraints(this)).visitLight(typeConstraints);
              } catch(tom.library.sl.VisitFailure e) {
                throw new TomRuntimeException("inferCode: failure on " +
                    headCodeList);
              } 
            }
            
            // For %strategy
            DeclarationToCode(declaration) -> {
              try{
                `declaration = inferAllTypes(`declaration,getUnknownFreshTypeVar());
                `headCodeList = `DeclarationToCode(declaration);
                typeConstraints = `RepeatId(solveConstraints(this)).visitLight(typeConstraints);
              } catch(tom.library.sl.VisitFailure e) {
                throw new TomRuntimeException("inferCode: failure on " +
                    headCodeList);
              } 
            }
          }
          `headCodeList = replaceInCode(`headCodeList);
          codeResult = `concCode(codeResult*,headCodeList);
          replaceInSymbolTable();
          init();
        }
        return `Tom(codeResult);
      }
    }
    // If it is a ill-formed code (different from "Tom(...)")
    return code;
  }

  private <T extends tom.library.sl.Visitable> T inferAllTypes(T term, TomType
      contextType) {
    try {
      //DEBUG System.out.println("In inferALLTypes with term = " + term);
      return `TopDownStopOnSuccess(inferTypes(contextType,this)).visitLight(term); 
    } catch(tom.library.sl.VisitFailure e) {
      throw new TomRuntimeException("inferAllTypes: failure on " + term);
    }

  }

  /**
   * The class <code>inferTomTerm</code> is generated from a strategy wich
   * tries to infer types of all variables
   * <p> 
   * It starts by searching for a Instruction
   * <code>Match(constraintInstructionList,option)</code> and calling
   * <code>inferConstraintInstructionList</code> in order to applu rule CT-RULE
   * for each single constraintInstruction
   * <p>
   * It searches for variables and star variables (TomTerms and BQTerms) and
   * applies rule CT-ANTI, CT-VAR, CT-SVAR, CT-FUN,
   * CT-EMPTY, CT-ELEM, CT-MERGE or CT-STAR to a "pattern" (a TomTerm) or a
   * "subject" (a BQTerm) in order to infer its type.
   * <p>
   * CT-ANTI rule:
   * IF found "!(e):A"
   * THEN infers type of e
   * <p>
   * CT-VAR rule (resp. CT-SVAR): 
   * IF found "x:A" (resp. "x*:A") and "x:T" (resp. "x*:T") already exists in SymbolTable 
   * THEN add a type constraint "A = T"
   * <p>
   * CT-FUN rule:
   * IF found "f(e1,...,en):A" and "f:T1,...,Tn->T" exists in SymbolTable
   * THEN infers type of arguments and add a type constraint "A = T" and
   *      calls the TypeVariableList method which adds a type constraint "Ai =
   *      Ti" for each argument, where Ai is a fresh type variable
   * <p>
   * CT-EMPTY rule:
   * IF found "l():AA" and "l:T*->TT" exists in SymbolTable
   * THEN add a type constraint "AA = TT"       
   * <p>
   * CT-ELEM rule:
   * IF found "l(e1,...,en,e):AA" and "l:T*->TT" exists in SymbolTable
   * THEN infers type of both sublist "l(e1,...,en)" and last argument
   *      "e" and adds a type constraint "AA = TT" and calls the
   *      TypeVariableList method which adds a type constraint "A =T"
   *      for the last argument, where A is a fresh type variable and
   *      "e" does not represent a list with head symbol "l"
   * <p>
   * CT-MERGE rule:
   * IF found "l(e1,...,en,e):AA" and "l:T*->TT" exists in SymbolTable
   * THEN infers type of both sublist "l(e1,...,en)" and last argument
   *      "e" and adds a type constraint "AA = TT" and calls the
   *      TypeVariableList method, where "e" represents a list with
   *      head symbol "l"
   * <p>
   * CT-STAR rule:
   * Equals to CT-MERGE but with a star variable "x*" instead of "e"
   * This rule is necessary because it differed from CT-MERGE in the
   * sense of the type of the last argument ("x*" here) is unknown 
   * @param contextType the fresh generated previously and attributed to the term
   * @param nkt an instance of object NewKernelTyper
   */
  %strategy inferTypes(contextType:TomType,nkt:NewKernelTyper) extends Fail() {
    // We put "returns" for each "case" of a visit in order to interrupt the
    // flow of the strategy.
    // e.g. for "f(g(x))" the strategy will be applied over "x" three times (1
    // when visiting "f(g(x))" + 1 when visiting "g(x)" + 1 when visiting "x")
    visit Instruction {
      Match[ConstraintInstructionList=ciList,Options=optionList] -> {
        //DEBUG System.out.println("Instruction with term = " + `match);
        BQTermList BQTList = nkt.varList;
        ConstraintInstructionList newCIList =
          nkt.inferConstraintInstructionList(`ciList);
        nkt.varList = BQTList;
        return `Match(newCIList,optionList);
      }      
    }
    
    visit TomVisit {
      VisitTerm[VNode=vNode,AstConstraintInstructionList=ciList,Options=optionList] -> {
        //DEBUG System.out.println("TomVisit with term = " + `vTerm);
        BQTermList BQTList = nkt.varList;
        ConstraintInstructionList newCIList =
          nkt.inferConstraintInstructionList(`ciList);
        nkt.varList = BQTList;
        return `VisitTerm(vNode,newCIList,optionList);
      }
    }
   
    visit TomTerm {
      //TODO type ConstraintList
      AntiTerm[TomTerm=atomicTerm] -> { nkt.inferAllTypes(`atomicTerm,contextType); }

      var@(Variable|VariableStar)[Options=optionList,AstName=aName,AstType=aType,Constraints=cList] -> {
        //DEBUG System.out.println("InferTypes:TomTerm var = " + `var);
        nkt.checkNonLinearityOfVariables(`var);
        nkt.addConstraint(`Equation(aType,contextType,PairNameOptions(aName,optionList)));  
        //DEBUG System.out.println("InferTypes:TomTerm var -- constraint = " +
        //DEBUG `aType + " = " + contextType);
        %match(cList) {
          // How many "AliasTo" constructors can concConstraint have?
          concConstraint(AliasTo(boundTerm)) -> {
            //DEBUG System.out.println("InferTypes:TomTerm aliasvar -- constraint = " +
            //DEBUG   nkt.getType(`boundTerm) + " = " + `contextType);
            // TODO : boundTerm.getAstType()
            nkt.addConstraint(`Equation(nkt.getType(boundTerm),contextType,nkt.getInfoFromTomTerm(boundTerm))); }
        }
        return `var;
      }

      RecordAppl[Options=optionList,NameList=nList@concTomName(aName@Name(tomName),_*),Slots=sList,Constraints=cList] -> {
        // In case of a String, tomName is "" for ("a","b")
        TomSymbol tSymbol = nkt.getSymbolFromName(`tomName);


        // IF_1
        if (tSymbol == null) {
          //The contextType is used here, so it must be a ground type, not a
          //type variable
          //DEBUG System.out.println("visit contextType = " + contextType);
          tSymbol = nkt.getSymbolFromType(contextType);

          // IF_2
          if (tSymbol != null) {
            // In case of contextType is "TypeVar(name,i)"
            `nList = `concTomName(tSymbol.getAstName());
          } 
        }
        //DEBUG System.out.println("\n Test pour TomTerm-inferTypes in RecordAppl. tSymbol = " + `tSymbol);
        //DEBUG System.out.println("\n Test pour TomTerm-inferTypes in RecordAppl. astName = " +`concTomName(tSymbol.getAstName()));
        //DEBUG System.out.println("\n Test pour TomTerm-inferTypes in RecordAppl.
        //tSymbol = " + tSymbol);

        %match(cList) {
          // How many "AliasTo" constructors can concConstraint have?
          concConstraint(AliasTo(boundTerm)) -> {
            //DEBUG System.out.println("InferTypes:TomTerm aliasrecordappl -- constraint = " +
            //DEBUG     nkt.getType(`boundTerm) + " = " + contextType);
            nkt.addConstraint(`Equation(nkt.getType(boundTerm),contextType,nkt.getInfoFromTomTerm(boundTerm))); }
        }

        TomType codomain = contextType;

        // IF_3
        if (tSymbol == null) {
          //DEBUG System.out.println("tSymbol is still null!");
          tSymbol = `EmptySymbol();
        } else {
          // This code can not be moved to IF_2 because tSymbol may be not "null
          // since the begginning and then does not enter into neither IF_1 nor
          // IF_2
          codomain = nkt.getCodomain(tSymbol);
          //DEBUG System.out.println("\n Test pour TomTerm-inferTypes in RecordAppl. codomain = " + codomain);
          nkt.addConstraint(`Equation(codomain,contextType,PairNameOptions(aName,optionList)));
          //DEBUG System.out.println("InferTypes:TomTerm recordappl -- constraint" + codomain + " = " + contextType);
        }

        SlotList newSList = `concSlot();
        if (!`sList.isEmptyconcSlot()) {
          `newSList = nkt.inferSlotList(`sList,tSymbol,codomain);
        }
        return `RecordAppl(optionList,nList,newSList,cList);
      }
    }

    visit BQTerm {
      bqVar@(BQVariable|BQVariableStar)[Options=optionList,AstName=aName,AstType=aType] -> {
        nkt.checkNonLinearityOfBQVariables(`bqVar);
        nkt.addConstraint(`Equation(aType,contextType,PairNameOptions(aName,optionList)));  
        //DEBUG System.out.println("InferTypes:BQTerm bqVar -- constraint = " +
        //DEBUG `aType + " = " + contextType);
        return `bqVar;
      }

      // Special case : when there is a block "rules" in %gom and "temporary
      // subjects" with name "realMake" are created but corresponding to none of
      // symbols existing in SymbolTable (i.e. having a "null" symbol)
      bqAppl@BQAppl[Options=optionList,AstName=aName@Name("realMake"),Args=concBQTerm(BQVariable[AstName=Name("head")],BQVariable[AstName=Name("tail")])]
        -> {
          TomSymbol tSymbol = nkt.getSymbolFromName("realMake");
          if (tSymbol == null) {
            tSymbol =
              `Symbol(aName,TypesToType(concTomType(contextType),contextType),concPairNameDecl(),optionList);
            nkt.symbolTable.putSymbol("realMake",tSymbol);
          }
          return `bqAppl;
        }

      BQAppl[Options=optionList,AstName=aName@Name(name),Args=bqTList] -> {
        //DEBUG System.out.println("\n Test pour BQTerm-inferTypes in BQAppl. tomName = " + `name);
        TomSymbol tSymbol = nkt.getSymbolFromName(`name);
        //DEBUG System.out.println("\n Test pour BQTerm-inferTypes in BQAppl. tSymbol = "+ tSymbol);
        if (tSymbol == null) {
          //The contextType is used here, so it must be a ground type, not a
          //type variable
          //DEBUG System.out.println("visit contextType = " + contextType);
          tSymbol = nkt.getSymbolFromType(contextType);
          if (tSymbol != null && `name.equals("")) {
            // In case of contextType is "TypeVar(name,i)"
            `aName = tSymbol.getAstName();
          }
        }

        /*
           TomSymbol tSymbol = null;
           if (`name.equals("")) {
           System.out.println("\n Test pour BQTerm-inferTypes in BQAppl without tomName. bqterm = " + `a);
        //The contextType is used here, so it must be a ground type, not a
        //type variable
        //DEBUG System.out.println("visit contextType = " + contextType);
        tSymbol = nkt.getSymbolFromType(contextType);
        if (tSymbol != null) {
        // In case of contextType is "TypeVar(name,i)"
        `aName = tSymbol.getAstName();
        }
        } else {
        // "name" is not "" and "aName" must not be changed
        tSymbol = nkt.getSymbolFromName(`name);
        }
         */

        TomType codomain = contextType;
        if (tSymbol == null) {
          tSymbol = `EmptySymbol();
        } else {
          codomain = nkt.getCodomain(tSymbol);
          nkt.addConstraint(`Equation(codomain,contextType,PairNameOptions(aName,optionList)));
          //DEBUG System.out.println("InferTypes:BQTerm bqappl -- constraint = "
          //DEBUG + `codomain + " = " + contextType);
        }
        
        BQTermList newBQTList = `bqTList;
        if (!`bqTList.isEmptyconcBQTerm()) {
          //DEBUG System.out.println("\n Test pour BQTerm-inferTypes in BQAppl. bqTList = " + `bqTList);
          newBQTList = nkt.inferBQTermList(`bqTList,`tSymbol,codomain);
        }
      
        // TO VERIFY
        %match(tSymbol) {
          EmptySymbol() -> {
            return `FunctionCall(aName,contextType,newBQTList); 
          }
        }
        return `BQAppl(optionList,aName,newBQTList);
      }
    }
  }
    
  /**
   * The method <code>checkNonLinearityOfVariables</code> searches for variables
   * occurring more than once in a condition.
   * <p>
   * For each variable of type
   * "TomTerm" that already exists in varPatternList or in varList, a type
   * constraint is added to <code>typeConstraints</code> to ensure that  both
   * variables have same type (this happens in case of non-linearity).
   * <p>
   * OBS.: we also need to check the varList since a Variable/VariableStar can have
   * occurred in a previous condition as a BQVariable/BQVariableStar, in the
   * case of a composed condition
   * e.g. (x < 10 ) && f(x) << e -> { action }
   * @param var the variable to have the linearity checked
   */
  private void checkNonLinearityOfVariables(TomTerm var) {
    %match {
      // --- Case of Variable
      // If the variable already exists in varPatternList or in varList (in
      // the case of a bad order of the conditions of a
      // conjunction/disjunction
      (Variable|VariableStar)[Options=optionList,AstName=aName,AstType=aType1] << var &&
        (concTomTerm(_*,(Variable|VariableStar)[AstName=aName,AstType=aType2@!aType1],_*)
         << varPatternList ||
         concBQTerm(_*,(BQVariable|BQVariableStar)[AstName=aName,AstType=aType2@!aType1],_*)
         << varList) -> {
          addConstraint(`Equation(aType1,aType2,PairNameOptions(aName,optionList))); }
    }
  }

  /**
   * The method <code>checkNonLinearityOfBQVariables</code> searches for variables
   * occurring more than once in a condition.
   * <p>
   * For each variable of type
   * "BQTerm" that already exists in varPatternList or in varList, a type
   * constraint is added to <code>typeConstraints</code> to ensure that  both
   * variables have same type (this happens in case of non-linearity).
   * <p>
   * OBS.: we also need to check the varPatternList since a BQVariable/BQVariableStar can have
   * occurred in a previous condition as a Variable/VariableStar, in the
   * case of a composed condition or of a inner match
   * e.g. f(x) << e && (x < 10 ) -> { action } 
   * @param bqvar the variable to have the linearity checked
   */
  private void checkNonLinearityOfBQVariables(BQTerm bqvar) {
    %match {
      // --- Case of Variable
      // If the backquote variable already exists in varPatternList 
      // (in the case of a inner match) or in varList
      (BQVariable|BQVariableStar)[Options=optionList,AstName=aName,AstType=aType1] << bqvar &&
        (concBQTerm(_*,(BQVariable|BQVariableStar)[AstName=aName,AstType=aType2@!aType1],_*)
         << varList ||
         concTomTerm(_*,(Variable|VariableStar)[AstName=aName,AstType=aType2@!aType1],_*)
         << varPatternList) -> {
          addConstraint(`Equation(aType1,aType2,PairNameOptions(aName,optionList))); }
    }
  }

  /**
   * The method <code>inferConstraintInstructionList</code> applies rule CT-RULE
   * to a pair "condition -> action" in order to collect all variables occurring
   * in the condition and put them into <code>varPatternList</code> (for those
   * variables occurring in match constraints) and <code>varList</code> (for
   * those variables occurring in numeric constraints) to be able to handle
   * non-linearity.
   * <p>
   * The condition (left-hand side) is traversed and then the action (right-hand
   * side) is traversed in order to generate type constraints.
   * <p>
   * CT-RULE rule:
   * IF found "cond --> (e1,...,en)" where "ei" are backquote terms composing
   * the action
   * THEN infers types of condition (by calling <code>inferConstraint</code>
   * method) and action (by calling <code>inferConstraintList</code> for each
   * match constraint occurring in the action) 
   * @param ciList  the pair "condition -> action" to be type inferred 
   */
  private ConstraintInstructionList inferConstraintInstructionList(ConstraintInstructionList ciList) {
    //DEBUG System.out.println("\n Test pour inferConstraintInstructionList -- ligne 1.");
    %match(ciList) {
      concConstraintInstruction() -> { return `ciList; }
      concConstraintInstruction(headCIList@ConstraintInstruction(constraint,action,optionList),tailCIList*) -> {
        try {
          //DEBUG System.out.println("\n Test pour inferConstraintInstructionList dans un match -- ligne 4.");
          //DEBUG System.out.println("\n Test pour inferConstraintInstructionList dans un match -- ligne 2.");
          //DEBUG System.out.println("\n Test pour inferConstraintInstructionList dans un match -- ligne 3.");
          //DEBUG System.out.println("\n varPatternList = " + `varPatternList);
          //DEBUG System.out.println("\n varList = " + `varList);
          //DEBUG System.out.println("\n Constraints = " + typeConstraints);
          TomList TTList = varPatternList;
          `TopDownCollect(CollectVars(this)).visitLight(`constraint);
          //DEBUG System.out.println("\n varPatternList apres = " + `varPatternList);
          //DEBUG System.out.println("\n varList apres = " + `varList);
          `constraint = inferConstraint(`constraint);
          `action = `inferAllTypes(action,EmptyType());
          varPatternList = TTList;
          //DEBUG System.out.println("\n varList apr?s = " + `varList);
          `tailCIList = inferConstraintInstructionList(`tailCIList);
          return
            `concConstraintInstruction(ConstraintInstruction(constraint,action,optionList),tailCIList*);
        } catch(tom.library.sl.VisitFailure e) {
          throw new TomRuntimeException("inferConstraintInstructionList: failure on " + `headCIList);
        }
      }
    }
    throw new TomRuntimeException("inferConstraintInstructionList: failure on "
        + `ciList);
  }

  /**
   * The class <code>CollectVars</code> is generated from a strategy which
   * collect all variables (Variable, VariableStar, BQVariable, BQVariableStar
   * occurring in a condition.
   * @param nkt an instance of object NewKernelTyper
   */
  %strategy CollectVars(nkt:NewKernelTyper) extends Identity() {
    visit TomTerm {
      var@(Variable|VariableStar)[] -> { nkt.addTomTerm(`var); }
    }

    visit BQTerm {
      bqvar@(BQVariable|BQVariableStar)[] -> { nkt.addBQTerm(`bqvar); }
    }
  }

  /**
   * The method <code>inferConstraint</code> applies rule CT-MATCH, CT-EQ, CT-CONJ,
   * or CT-DISJ to a "condition" in order to infer its type.
   * <p>
   * CT-MATCH rule:
   * IF found "e1 << [T] e2" 
   * THEN infers type of e1 and e2 and add a type constraint "T1 = T2", where
   * "Ti" is a fresh type variable generated to "ei" (by
   * <code>inferBQTerm</code>, a type constraint "T = T2" will be added)
   * <p>
   * CT-EQ rule:
   * IF found "e1 == e2", "e1 <= e2", "e1 < e2", "e1 >= e2" or "e1 > e2"
   * THEN infers type of e1 and e2 and add a type constraint "T1 = T2", where
   * "Ti" is a fresh type variable generated to "ei"  
   * <p>
   * CT-CONJ rule (resp. CT-DISJ):
   * IF found "cond1 && cond2" (resp. "cond1 || cond2")
   * THEN infers type of cond1 and cond2 (by calling
   * <code>inferConstraint</code> for each condition)
   * @param constraint the "condition" to be type inferred 
   */
  private Constraint inferConstraint(Constraint constraint) {
    %match(constraint) {
      MatchConstraint(pattern,subject) -> { 
        //DEBUG System.out.println("inferConstraint l1 -- subject = " + `subject);
        TomType freshType1 = getUnknownFreshTypeVar();
        TomType freshType2 = getUnknownFreshTypeVar();
        //DEBUG System.out.println("inferConstraint: match -- constraint " + freshType1 + " = " + freshType2);
        addConstraint(`Equation(freshType1,freshType2,getInfoFromTomTerm(pattern)));
        `pattern = inferAllTypes(`pattern,freshType1);
        `subject = inferAllTypes(`subject,freshType2);
        hasUndeclaredType(`subject);
        return `MatchConstraint(pattern,subject);
      }

      NumericConstraint(left,right,type) -> {
        TomType freshType1 = getUnknownFreshTypeVar();
        TomType freshType2 = getUnknownFreshTypeVar();
        // It will be useful for subtyping
        // TomType freshType3 = getUnknownFreshTypeVar();
        //addConstraint(`Equation(freshType1,getType(left)));
        //DEBUG System.out.println("inferConstraint l1 - typeConstraints = " + typeConstraints);
        //addConstraint(`Equation(freshType2,getType(right)));
        //DEBUG System.out.println("inferConstraint l2 - typeConstraints = " + typeConstraints);
        //DEBUG System.out.println("inferConstraint: numeric -- constraint " + freshType1 + " = " + freshType2);
        //TO VERIFY : Do all bqTerm have an AstName?? 
        addConstraint(`Equation(freshType1,freshType2,getInfoFromBQTerm(left)));
        `left = inferAllTypes(`left,freshType1);
        `right = inferAllTypes(`right,freshType2);
        return `NumericConstraint(left,right,type);
      }

      AndConstraint(headCList,tailCList*) -> {
        ConstraintList cList = `concConstraint(headCList,tailCList);
        Constraint cArg;
        Constraint newAConstraint = `AndConstraint();
        while(!cList.isEmptyconcConstraint()) {
          cArg = inferConstraint(cList.getHeadconcConstraint());
          newAConstraint = `AndConstraint(newAConstraint,cArg);
          cList = cList.getTailconcConstraint();
        }
        return newAConstraint;
      }

      OrConstraint(headCList,tailCList*) -> {
        ConstraintList cList = `concConstraint(headCList,tailCList);
        Constraint cArg;
        Constraint newOConstraint = `OrConstraint();
        while(!cList.isEmptyconcConstraint()) {
          cArg = inferConstraint(cList.getHeadconcConstraint());
          newOConstraint = `OrConstraint(newOConstraint,cArg);
          cList = cList.getTailconcConstraint();
        }
        return newOConstraint;
      }
    }
    return constraint;
  }

  private SlotList inferSlotList(SlotList sList, TomSymbol tSymbol, TomType
      contextType) {
    %match(sList,tSymbol) {
      concSlot(),Symbol[] -> { return `sList; }
      concSlot(),EmptySymbol() -> { return `sList; }

      concSlot(PairSlotAppl[SlotName=sName,Appl=tTerm],tailSList*),EmptySymbol() -> {
        //DEBUG System.out.println("InferSlotList -- sList =" + sList);
        //DEBUG System.out.println("InferSlotList -- tSymbol =" + tSymbol);
        /*
         * if the top symbol is unknown, the subterms
         * are typed in an empty context
         */
        TomType argType = contextType;
        TomSymbol argSymb = getSymbolFromTerm(`tTerm);
        if(!(TomBase.isListOperator(`argSymb) || TomBase.isArrayOperator(`argSymb))) {
          %match(tTerm) {
            !VariableStar[] -> { 
              //DEBUG System.out.println("InferSlotList CT-ELEM -- tTerm = " + `tTerm);
              argType = getUnknownFreshTypeVar();
            }
          }
        }
        `tTerm = `inferAllTypes(tTerm,argType);
        SlotList newTail = `inferSlotList(tailSList,tSymbol,contextType);
        return `concSlot(PairSlotAppl(sName,tTerm),newTail*);
      }

      concSlot(PairSlotAppl[SlotName=sName,Appl=tTerm],tailSList*),Symbol[AstName=symName,TypesToType=TypesToType(domain@concTomType(headTTList,_*),Type(tomCodomain,tlCodomain))] -> {
        TomType argType = contextType;
        // In case of a list
        if(TomBase.isListOperator(`tSymbol) || TomBase.isArrayOperator(`tSymbol)) {
          TomSymbol argSymb = getSymbolFromTerm(`tTerm);
          TomTerm newTerm = `tTerm;
          if(!(TomBase.isListOperator(`argSymb) || TomBase.isArrayOperator(`argSymb))) {
            %match(tTerm) {
              VariableStar[] -> {
                /**
                 * Continuation of CT-STAR rule (applying to premises):
                 * IF found "l(e1,...,en,x*):AA" and "l:T*->TT" exists in SymbolTable
                 * THEN infers type of both sublist "l(e1,...,en)" and last argument
                 *      "x", where "x" represents a list with
                 *      head symbol "l"
                 */
                argType = `TypeWithSymbol(tomCodomain,tlCodomain,symName);
              }

              !VariableStar[] -> { 
                //DEBUG System.out.println("InferSlotList CT-ELEM -- tTerm = " + `tTerm);
                /**
                 * Continuation of CT-ELEM rule (applying to premises which are
                 * not lists):
                 * IF found "l(e1,...en,e):AA" and "l:T*->TT" exists in SymbolTable
                 * THEN infers type of both sublist "l(e1,...,en)" and last argument
                 *      "e" and adds a type constraint "A = T" for the last
                 *      argument, where "A" is a fresh type variable  and
                 *      "e" does not represent a list with head symbol "l"
                 */
                argType = getUnknownFreshTypeVar();
                //DEBUG System.out.println("inferSlotList: !VariableStar -- constraint "
                //DEBUG     + `headTTList + " = " + argType);

                //TODO create a method getAstName which takes an AstName of a
                //RecordAppl
                addConstraint(`Equation(argType,headTTList,getInfoFromTomTerm(tTerm)));
              }
            }
          } else if (`symName != argSymb.getAstName()) {
            // TODO: improve this code! It is like CT-ELEM
            /*
             * A list with a sublist whose constructor is different
             * e.g. 
             * A = ListA(A*) and B = ListB(A*) | b()
             * ListB(b(),ListA(a()),b())
             */
            argType = getUnknownFreshTypeVar();
            //DEBUG System.out.println("inferSlotList: symName != argSymbName -- constraint "
            //DEBUG     + `headTTList + " = " + argType);
            addConstraint(`Equation(argType,headTTList,getInfoFromTomTerm(tTerm)));
          }

          /**
           * Continuation of CT-MERGE rule (applying to premises):
           * IF found "l(e1,...,en,e):AA" and "l:T*->TT" exists in SymbolTable
           * THEN infers type of both sublist "l(e1,...,en)" and last argument
           *      "e", where "e" represents a list with
           *      head symbol "l"
           */
          `newTerm = `inferAllTypes(newTerm,argType);
          SlotList newTail = `inferSlotList(tailSList,tSymbol,contextType);
          return `concSlot(PairSlotAppl(sName,newTerm),newTail*);
        } else {
          // In case of a function
          TomTerm argTerm;
          TomName argName;
          SlotList newSList = `concSlot();
          TomTypeList symDomain = `domain;
          /**
           * Continuation of CT-FUN rule (applying to premises):
           * IF found "f(e1,...,en):A" and "f:T1,...,Tn->T" exists in SymbolTable
           * THEN infers type of arguments and adds a type constraint "Ai =
           *      Ti" for each argument, where "Ai" is a fresh type variable
           */
          for(Slot arg : `sList.getCollectionconcSlot()) {
            argTerm = arg.getAppl();
            argName = arg.getSlotName();
            //DEBUG System.out.println("InferSlotList CT-FUN -- slotappl in for = " +
            //DEBUG     `argTerm);
            argType = symDomain.getHeadconcTomType();
            addConstraint(`Equation(getUnknownFreshTypeVar(),argType,getInfoFromTomTerm(argTerm)));
            `argTerm = `inferAllTypes(argTerm,argType);
            newSList = `concSlot(newSList*,PairSlotAppl(argName,argTerm));
            symDomain = symDomain.getTailconcTomType();
            //DEBUG System.out.println("InferSlotList CT-FUN -- end of for with slotappl = " + `argTerm);
          }
          return newSList;
        }
      }
    }
    throw new TomRuntimeException("inferSlotList: failure on " + `sList);
  }

  private BQTermList inferBQTermList(BQTermList bqTList, TomSymbol tSymbol, TomType
      contextType) {
    //DEBUG System.out.println("begin of InferBQTermList -- tSymbol'" + tSymbol +
    //DEBUG    "'");
    %match(bqTList,tSymbol) {
      concBQTerm(),Symbol[] -> { return bqTList; }
      concBQTerm(headBQTerm@BQVariable[AstName=Name("head")],tailBQTerm@BQVariable[AstName=Name("tail")]),
        Symbol[AstName=Name("realMake")]
          -> {
            //DEBUG System.out.println("What???? headBQTerm = " + `headBQTerm);
            `headBQTerm = inferAllTypes(`headBQTerm,contextType);
            //DEBUG System.out.println("What???? tailBQTerm = " + `tailBQTerm);
            `tailBQTerm = inferAllTypes(`tailBQTerm,contextType);
            return `concBQTerm(headBQTerm,tailBQTerm);
          }

      concBQTerm(_,_*),EmptySymbol() -> {
        /*
         * if the top symbol is unknown, the subterms
         * are typed in an empty context
         */
        BQTermList newBQTList = `concBQTerm();
        for(BQTerm arg : `bqTList.getCollectionconcBQTerm()) {
          //DEBUG System.out.println("InferBQTermList will call inferAllTypes with = "
          //DEBUG     + `arg);
          arg = `inferAllTypes(arg,EmptyType());
          newBQTList = `concBQTerm(newBQTList*,arg);
        }
        return newBQTList;
      }

      concBQTerm(bqTerm,tailBQTList*),Symbol[AstName=symName,TypesToType=TypesToType(domain@concTomType(headTTList,_*),Type(tomCodomain,tlCodomain))] -> {
        TomType argType = contextType;
        // In case of a list
        if(TomBase.isListOperator(`tSymbol) || TomBase.isArrayOperator(`tSymbol)) {
          TomSymbol argSymb = getSymbolFromTerm(`bqTerm);
          BQTerm newTerm = `bqTerm;
          //DEBUG System.out.println("InferBQTermList -- bqTerm= " + `bqTerm);
          //DEBUG System.out.println("\n\n" + `bqTerm + " is a list? " +
          //DEBUG     TomBase.isListOperator(`argSymb) + " and " +
          //DEBUG     TomBase.isArrayOperator(`argSymb) + '\n');
          if(!(TomBase.isListOperator(`argSymb) || TomBase.isArrayOperator(`argSymb))) {
            %match(bqTerm) {
              BQVariableStar[] -> {
                argType = `TypeWithSymbol(tomCodomain,tlCodomain,symName);
              }

              //TO VERIFY : which constructors must be tested here?
              (BQVariable|BQAppl)[] -> {
                //DEBUG System.out.println("inferBQTermList: bqTerm = " + `bqTerm);
                argType = getUnknownFreshTypeVar();
                //DEBUG System.out.println("inferBQTermList: !BQVariableStar -- constraint "
                //DEBUG     + argType + " = " + `headTTList);
                addConstraint(`Equation(argType,headTTList,getInfoFromBQTerm(bqTerm)));
                //addConstraint(`Equation(getUnknownFreshTypeVar(),argType));
              }

              // We don't know what is into the Composite
              // It can be a BQVariableStar or a list operator or something else
              Composite(_*) -> { argType = getUnknownFreshTypeVar(); }
            }
          } else if (`symName != argSymb.getAstName()) {
            // TODO: improve this code! It is like CT-ELEM
            /*
             * A list with a sublist whose constructor is different
             * e.g. 
             * A = ListA(A*) and B = ListB(A*) | b()
             * ListB(b(),ListA(a()),b())
             */
            argType = getUnknownFreshTypeVar();
            addConstraint(`Equation(argType,headTTList,getInfoFromBQTerm(bqTerm)));
          }
          `newTerm = `inferAllTypes(newTerm,argType);
          BQTermList newTail = `inferBQTermList(tailBQTList,tSymbol,contextType);
          return `concBQTerm(newTerm,newTail*);
        } else {
          // In case of a function
          BQTermList newBQTList = `concBQTerm();
          TomTypeList symDomain = `domain;
          for(BQTerm arg : `bqTList.getCollectionconcBQTerm()) {
            argType = symDomain.getHeadconcTomType();
            addConstraint(`Equation(getUnknownFreshTypeVar(),argType,getInfoFromBQTerm(arg)));
            arg = `inferAllTypes(arg,argType);
            newBQTList = `concBQTerm(newBQTList*,arg);
            symDomain = symDomain.getTailconcTomType();
          }
          return newBQTList;
        }
      }
    }
    throw new TomRuntimeException("inferBQTermList: failure on " + `bqTList);
  }

  /**
   * The class <code>solveConstraints</code> is generated from a strategy wich
   * tries to solve all type constraints collected during the inference
   * <p> 
   * There exists 3 kinds of types : variable types Ai, ground types Ti and
   * ground types Ti^c which are decorated with a given symbol c. Since a type
   * constraints is a pair (type1,type2) representing an equation relation
   * between two types, them the set of all possibilities of arrangement between
   * types is a sequence with repetition. Then, we have 9 possible case (since
   * 3^2 = 9).
   * <p>
   * CASE 1: Equation(T1 = T2) U TCList and Map
   *  a) --> Fail if T1 is different from T2
   *  b) --> Nothing if T1 is equals to T2
   * <p>
   * CASE 2: Equation(T1 = T2^c) U TCList and Map
   *  a) --> Fail if T1 is different from T2
   *  b) --> Nothing if T1 is equals to T2
   * <p>
   * CASE 3: Equation(T1^c = T2) U TCList and Map
   *  a) --> Fail if T1 is different from T2
   *  b) --> Nothing if T1 is equals to T2
   * <p>
   * CASE 4: Equation(T1^a = T2^b) U TCList and Map
   *  a) --> Fail if T1 is different from T2 and/or "a" is different from "b"
   *  b) --> Nothing if T1 is equals to T2
   * <p>
   * CASE 5: Equation(T = A) U TCList and Map 
   *  --> Equation(T = T) U [A/T]TCList and [A/T]Map
   * <p>
   * CASE 6: Equation(T^c = A) U TCList and Map 
   *  --> Equation(T^c = T^c) U [A/T^c]TCList and [A/T^c]Map
   * <p>
   * CASE 7: Equation(A = T) U TCList and Map 
   *  --> Equation(T = T) U [A/T]TCList and [A/T]Map
   * <p>
   * CASE 8: Equation(A = T^c) U TCList and Map 
   *  --> Equation(T^c = T^c) U [A/T^c]TCList and [A/T^c]Map
   * <p>
   * CASE 9: Equation(A1 = A2) U TCList and Map 
   *  --> Nothing 
   */   
  %strategy solveConstraints(nkt:NewKernelTyper) extends Identity() {
    visit TypeConstraintList {
      // CASES 1a and 3a :
      concTypeConstraint(leftTCList*,tc@Equation(t1@(Type|TypeWithSymbol)[TomType=tName1],t2@Type[TomType=tName2@!tName1],_),rightTCList*) && 
        (tName1 != "unknown type") && (tName2 != "unknown type")  -> {
          nkt.printError(`tc);
          return `concTypeConstraint(leftTCList*,rightTCList*);
          //throw new RuntimeException("solveConstraints: failure on " + `t1
          //    + " = " + `t2);
        }

      // CASE 2a :
      concTypeConstraint(leftTCList*,tc@Equation(t1@Type[TomType=tName1],t2@TypeWithSymbol[TomType=tName2@!tName1],_),rightTCList*) &&
        (tName1 != "unknown type") && (tName2 != "unknown type")  -> {
          nkt.printError(`tc);
          return `concTypeConstraint(leftTCList*,rightTCList*);
          //throw new RuntimeException("solveConstraints: failure on " + `t1
          //    + " = " + `t2);
        }

      // CASE 4a :  
      concTypeConstraint(leftTCList*,tc@Equation(tLType1@TypeWithSymbol(_,_,_),tLType2@TypeWithSymbol(_,_,_),_),rightTCList*) &&
        (tLType1 != tLType2)  -> {
          nkt.printError(`tc);
          return `concTypeConstraint(leftTCList*,rightTCList*);
          //throw new RuntimeException("solveConstraints: failure on " + `tLType1
          //    + " = " + `tLType2);
        }

      // CASES 7 and 8 :
      concTypeConstraint(leftTCList*,Equation(typeVar@TypeVar(_,_),type@!typeVar,info),rightTCList*) -> {
        nkt.substitutions.put(`typeVar,`type);
        %match {
          !concTypeConstraint() << leftTCList -> {
            if(nkt.findTypeVars(`typeVar,`leftTCList)) {
              `leftTCList = nkt.applySubstitution(`typeVar,`type,`leftTCList);
            }
          }

          !concTypeConstraint() << rightTCList -> {
            if(nkt.findTypeVars(`typeVar,`rightTCList)) {
              `rightTCList = nkt.applySubstitution(`typeVar,`type,`rightTCList);
            }
          }
        }
        return `concTypeConstraint(leftTCList*,Equation(type,type,info),rightTCList*);
      }

      // CASES 5 and 6 :
      concTypeConstraint(leftTCList*,Equation(groundType@!TypeVar(_,_),typeVar@TypeVar(_,_),info),rightTCList*) -> {
        nkt.substitutions.put(`typeVar,`groundType);
        %match {
          !concTypeConstraint() << leftTCList -> {
            if(nkt.findTypeVars(`typeVar,`leftTCList)) {
              `leftTCList = nkt.applySubstitution(`typeVar,`groundType,`leftTCList);
            }
          }

          !concTypeConstraint() << rightTCList -> {
            if(nkt.findTypeVars(`typeVar,`rightTCList)) {
              `rightTCList = nkt.applySubstitution(`typeVar,`groundType,`rightTCList);
            }
          }
        }
        return
          `concTypeConstraint(leftTCList*,Equation(groundType,groundType,info),rightTCList*);
      }
    }
  }

  private void printError(TypeConstraint tConstraint) {
    String fileName = "";
    int line = 0;
    %match {
      Equation[Type1=tType1,Type2=tType2,Info=info] << tConstraint &&
        (Type|TypeWithSymbol)[TomType=tName1] << tType1 &&
        (Type|TypeWithSymbol)[TomType=tName2] << tType2 &&
        PairNameOptions(Name(termName),optionList) << info
        -> {
          Option option = TomBase.findOriginTracking(`optionList);
          %match(option) {
            !noOption() -> {
              fileName = option.getFileName();
              line = option.getLine();
            }
          }
          TomMessage.error(logger,fileName, line,
              TomMessage.incompatibleTypes,`tName1,`tName2,`termName); 
        }
    }
  }


  private boolean findTypeVars(TomType typeVar, TypeConstraintList
      tcList) {
    //DEBUG System.out.println("\n Test pour findTypeVars -- ligne 1.");
    %match {
      concTypeConstraint(_*,Equation(type1,type2,_),_*) << tcList &&
      (type1 == typeVar || type2 == typeVar) -> {
        return true;
      }
    }
    return false;
  }

  private TypeConstraintList applySubstitution(TomType oldtt, TomType newtt,
      TypeConstraintList tcList) {
    try {
      return (TypeConstraintList)
        `TopDown(replaceTypeConstraints(oldtt,newtt)).visitLight(tcList);
    } catch(tom.library.sl.VisitFailure e) {
      throw new RuntimeException("applySubstitution: should not be here.");
    }
  }

  %strategy replaceTypeConstraints(oldtt:TomType, newtt:TomType) extends Identity() {
    visit TomType {
      typeVar && (oldtt == typeVar) -> {
          return newtt; 
      }
    }
  }
 
  private Code replaceInCode(Code code) {
    Code replacedCode = code;
    try {
      replacedCode =
        `RepeatId(TopDown(replaceFreshTypeVar(this))).visitLight(code);
    } catch(tom.library.sl.VisitFailure e) {
      throw new TomRuntimeException("replaceInCode: failure on " +
          replacedCode);
    }
    return replacedCode;
  }


  private void replaceInSymbolTable() {
    for(String tomName:symbolTable.keySymbolIterable()) {
      //DEBUG System.out.println("replaceInSymboltable() - tomName : " + tomName);
      TomSymbol tSymbol = getSymbolFromName(tomName);
      //DEBUG System.out.println("replaceInSymboltable() - tSymbol before strategy: "
      //DEBUG     + tSymbol);
      try {
        tSymbol = `RepeatId(TopDown(replaceFreshTypeVar(this))).visitLight(tSymbol);
      } catch(tom.library.sl.VisitFailure e) {
        throw new TomRuntimeException("replaceInSymbolTable: failure on " +
            tSymbol);
      }
      //DEBUG System.out.println("replaceInSymboltable() - tSymbol after strategy: "
      //DEBUG     + tSymbol);
      /*
      %match {
        Symbol[AstName=aName@Name(""),TypesToType=TypesToType(concTomType(contextType),contextType),PairNameDeclList=pndList,Options=optionList] << tSymbol -> {
            
        }  
      }*/
      symbolTable.putSymbol(tomName,tSymbol);
    }
  }

  %strategy replaceFreshTypeVar(nkt:NewKernelTyper) extends Identity() {
    visit TomType {
      typeVar@TypeVar(tomType,_) -> {
        if (nkt.substitutions.containsKey(`typeVar)) {
          return nkt.substitutions.get(`typeVar);
        } else {
          //DEBUG System.out.println("\n----- There is no mapping for " + `typeVar +'\n');
          return `Type(tomType,EmptyTargetLanguageType());
        }    
      }
    }
  }

  public void printGeneratedConstraints(TypeConstraintList TCList) {
    %match(TCList) {
      !concTypeConstraint() -> { 
        System.out.print("\n------ Type Constraints : \n {");
        printEachConstraint(TCList);
        System.out.print("}");
      }
    }
  }
  public void printEachConstraint(TypeConstraintList TCList) {
    %match(TCList) {
      concTypeConstraint(Equation(type1,type2,_),tailTCList*) -> {
        printType(`type1);
        System.out.print(" = ");
        printType(`type2);
        if (`tailTCList != `concTypeConstraint()) {
            System.out.print(", "); 
            printEachConstraint(`tailTCList);
        }
      }
    }
  }
    
  public void printType(TomType type) {
    System.out.print(type);
  }
} // NewKernelTyper