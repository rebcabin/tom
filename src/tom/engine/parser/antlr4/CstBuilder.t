/*
 *
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2016, Universite de Lorraine, Inria
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

package tom.engine.parser.antlr4;

import java.util.logging.Logger;
import java.util.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

//import tom.engine.adt.code.types.*;
import tom.engine.adt.cst.types.*;

import tom.engine.TomBase;
import tom.engine.TomMessage;
import tom.engine.exception.TomRuntimeException;

import tom.engine.tools.SymbolTable;
import tom.engine.tools.ASTFactory;

//import tom.library.sl.*;

/*
 * CST builder
 * traverse the ANTLR tree and generate a Gom Cst_Program, of sort CstProgram
 */
public class CstBuilder extends TomIslandParserBaseListener {
  %include { ../../adt/cst/CST.tom }

  private static Logger logger = Logger.getLogger("tom.engine.typer.CstConverter");
  private Logger getLogger() {
    return Logger.getLogger(getClass().getName());
  }

  ParseTreeProperty<Object> values = new ParseTreeProperty<Object>();
  private void setValue(ParseTree node, Object value) { values.put(node, value); } 
  public Object getValue(ParseTree node) { return values.get(node); }
  public void setStringValue(ParseTree node, String value) { setValue(node, value); } 
  public String getStringValue(ParseTree node) { return (String) getValue(node); }

  ParseTreeProperty<Object> values2 = new ParseTreeProperty<Object>();
  private void setValue2(ParseTree node, Object value) { values2.put(node, value); } 
  public Object getValue2(ParseTree node) { return values2.get(node); }

  private void setValue(String debug, ParseTree node, Object value) { 
    values.put(node, value);
    //System.out.println(debug + ": " + value);
  } 


  /*
   * start : (island | water)*? ;
   */
  public void exitStart(TomIslandParser.StartContext ctx) {
    CstBlockList bl = `ConcCstBlock();
    for(int i = 0 ; i<ctx.getChildCount() ; i++) {
      ParseTree child = ctx.getChild(i);
      if(child instanceof TomIslandParser.IslandContext) {
        bl = `ConcCstBlock(bl*,(CstBlock)getValue(child));
      } else if(child instanceof TomIslandParser.WaterContext) {
        ParserRuleContext prc = (ParserRuleContext)child;
        CstOption ot = extractOption(prc.getStart());
        bl = `ConcCstBlock(bl*,HOSTBLOCK(ConcCstOption(ot), getStringValue(child)));
      }
    }
    //System.out.println("exitStart bl1: " + bl);
    //bl = mergeHOSTBLOCK(bl);
    //System.out.println("exitStart bl2: " + bl);
    setValue("exitStart",ctx, `Cst_Program(bl));
  }

  /*
   * island 
   *   : matchStatement
   *   | strategyStatement
   *   | includeStatement
   *   | typeterm
   *   | operator
   *   | oplist
   *   | oparray
   *   | bqcomposite
   *   ;
   */
  public void exitIsland(TomIslandParser.IslandContext ctx) {
    ParseTree child = ctx.getChild(0);
    setValue("exitIsland",ctx,getValue(child));
  }

  /*
   * water
   *   : .
   *   ;
   */
  public void exitWater(TomIslandParser.WaterContext ctx) {
    setStringValue(ctx,ctx.getText());
  }

  /*
   * waterexceptparen 
   *   :
   *   ~(LPAREN|RPAREN)+? 
   *   ;
   */
  public void exitWaterexceptparen(TomIslandParser.WaterexceptparenContext ctx) {
    setStringValue(ctx,ctx.getText());
  }

  /*
   * matchStatement
   *   : MATCH LPAREN (bqterm (COMMA bqterm)*)? RPAREN LBRACE actionRule* RBRACE 
   *   ;
   */
  public void exitMatchStatement(TomIslandParser.MatchStatementContext ctx) {
    CstOptionList optionList = `ConcCstOption(extractOption(ctx.getStart()));
    CstBQTermList subjectList = buildCstBQTermList(ctx.bqterm());
    CstConstraintActionList constraintActionList = buildCstConstraintActionList(ctx.actionRule());
    CstBlock res = `Cst_MatchConstruct(optionList,subjectList,constraintActionList);
    setValue("exitMatchStatement", ctx,res);
  }

  /*
   * strategyStatement
   *   : STRATEGY ID LPAREN slotList? RPAREN EXTENDS bqterm LBRACE visit* RBRACE
   *   ;
   */
  public void exitStrategyStatement(TomIslandParser.StrategyStatementContext ctx) {
    CstOptionList optionList = `ConcCstOption(extractOption(ctx.getStart()));
    CstName name = `Cst_Name(ctx.ID().getText());
    CstSlotList argumentList = `ConcCstSlot();
    // if there are arguments
    if(ctx.slotList() != null) {
      argumentList = (CstSlotList) getValue(ctx.slotList());
    }
    CstVisitList visitList = buildCstVisitList(ctx.visit());

    CstBlock res = `Cst_StrategyConstruct(optionList,name,argumentList,(CstBQTerm)getValue(ctx.bqterm()),visitList);
    setValue("exitStrategy", ctx,res);
  }

  /*
   * includeStatement
   *   : INCLUDE LBRACE ID ((SLASH|BACKSLASH) ID)*  RBRACE 
   *   ;
   */
  public void exitIncludeStatement(TomIslandParser.IncludeStatementContext ctx) {
    CstOptionList optionList = `ConcCstOption(extractOption(ctx.getStart()));
    String filename = "";
    for(int i = 2 ; i<ctx.getChildCount()-1 ; i++) {
      // skip %include {, and the last }
      ParseTree child = ctx.getChild(i);
      filename += child.getText();
    }
    setValue("exitIncludeStatement", ctx,`Cst_IncludeConstruct(optionList,filename));
  }

  /*
   * visit
   *   : VISIT ID LBRACE actionRule* RBRACE
   *   ;
   */
  public void exitVisit(TomIslandParser.VisitContext ctx) {
    CstOptionList optionList = `ConcCstOption(extractOption(ctx.getStart()));
    CstConstraintActionList l = buildCstConstraintActionList(ctx.actionRule());
    CstVisit res = `Cst_VisitTerm( Cst_Type(ctx.ID().getText()), l, optionList);
    setValue("exitVisit", ctx,res);
  }

  /*
   * actionRule
   *   : patternlist ((AND | OR) constraint)? ARROW block
   *   | patternlist ((AND | OR) constraint)? ARROW bqterm
   *   | c=constraint ARROW block
   *   | c=constraint ARROW bqterm
   *   ;
   */
  public void exitActionRule(TomIslandParser.ActionRuleContext ctx) {
    CstConstraintAction res = null;
    CstOptionList optionList = `ConcCstOption(extractOption(ctx.getStart()));
    CstBlockList action = null;
    if(ctx.block() != null) {
      action = (CstBlockList) getValue(ctx.block());
    } else if(ctx.bqterm() != null) {
      action = `ConcCstBlock(Cst_BQTermToBlock((CstBQTerm)getValue(ctx.bqterm())));
    }
    CstConstraint constraint = `Cst_AndConstraint();
    if(ctx.c != null) {
      constraint = (CstConstraint)getValue(ctx.c);
    } else {
      for(CstPattern p:((CstPatternList)getValue(ctx.patternlist())).getCollectionConcCstPattern()) {
        constraint = `Cst_AndConstraint(constraint, Cst_MatchArgumentConstraint(p));
      }
      if(ctx.AND() != null) {
        constraint = `Cst_AndConstraint(constraint,(CstConstraint)getValue(ctx.constraint()));
      } else if(ctx.OR() != null) {
        constraint = `Cst_OrConstraint(constraint,(CstConstraint)getValue(ctx.constraint()));
      }
    }

    res = `Cst_ConstraintAction(constraint,action,optionList);
    setValue("exitActionRule", ctx,res);
  }

  /*
   * block 
   *   : LBRACE (island | block | water)*? RBRACE
   *   ;
   */
  public void exitBlock(TomIslandParser.BlockContext ctx) {
    CstBlockList bl = `ConcCstBlock();

    for(int i = 0 ; i<ctx.getChildCount() ; i++) {
      ParseTree child = ctx.getChild(i);

      if(child instanceof TomIslandParser.IslandContext) {
        bl = `ConcCstBlock(bl*,(CstBlock)getValue(child));
      } else if(child instanceof TomIslandParser.BlockContext) {
        CstBlockList cbl = (CstBlockList)getValue(child);
        //System.out.println("exitBlock cbl: " + cbl);
        bl = `ConcCstBlock(bl*,cbl*);
      } else if(child instanceof TomIslandParser.WaterContext) {
        ParserRuleContext prc = (ParserRuleContext)child;
        CstOption ot = extractOption(prc.getStart());
        bl = `ConcCstBlock(bl*,HOSTBLOCK(ConcCstOption(ot), getStringValue(child)));
      }
    }
    //System.out.println("exitBlock bl1: " + bl);
    //bl = mergeHOSTBLOCK(bl);
    //System.out.println("exitBlock bl2: " + bl);
    //System.out.println("exitBlock: " + bl);
    setValue(ctx,bl);
  }

  /*
   * slotList
   *   : slot (COMMA slot)*
   *   ;
   */
  public void exitSlotList(TomIslandParser.SlotListContext ctx) {
    CstSlotList res = buildCstSlotList(ctx.slot());
    setValue("exitSlotList", ctx,res);
  }

  /*
   * slot
   *   : id1=ID COLON? id2=ID
   *   ;
   */
  public void exitSlot(TomIslandParser.SlotContext ctx) {
    CstSlot res = null;
    if(ctx.COLON() != null) {
      res = `Cst_Slot(Cst_Name(ctx.id1.getText()), Cst_Type(ctx.id2.getText()));
    } else {
      res = `Cst_Slot(Cst_Name(ctx.id2.getText()), Cst_Type(ctx.id1.getText()));
    }
    setValue("exitSlot",ctx,res);
  }

  /*
   * patternlist
   *   : pattern (COMMA pattern)* 
   *   ;
   */
  public void exitPatternlist(TomIslandParser.PatternlistContext ctx) {
    CstPatternList res = buildCstPatternList(ctx.pattern());
    setValue("exitPatternList", ctx,res);
  }

  /*
   * constraint
   *   : constraint AND constraint
   *   | constraint OR constraint
   *   | pattern MATCH_SYMBOL bqterm
   *   | term GREATERTHAN term
   *   | term GREATEROREQ term
   *   | term LOWERTHAN term
   *   | term LOWEROREQ term
   *   | term DOUBLEEQ term
   *   | term DIFFERENT term
   *   | LPAREN c=constraint RPAREN
   *   ;
   */
  public void exitConstraint(TomIslandParser.ConstraintContext ctx) {
    CstConstraint res = null;
    if(ctx.AND() != null) {
      res = `Cst_AndConstraint((CstConstraint)getValue(ctx.constraint(0)),(CstConstraint)getValue(ctx.constraint(1)));
    } else if(ctx.OR() != null) {
      res = `Cst_OrConstraint((CstConstraint)getValue(ctx.constraint(0)),(CstConstraint)getValue(ctx.constraint(1)));
    } else if(ctx.MATCH_SYMBOL() != null) {
      res = `Cst_MatchTermConstraint((CstPattern)getValue(ctx.pattern()),(CstBQTerm)getValue(ctx.bqterm()),
          (CstType)getValue2(ctx.bqterm()));
    } else if(ctx.LPAREN() != null && ctx.RPAREN() != null) {
      res = (CstConstraint)getValue(ctx.c);
    } else {
      CstTerm lhs = (CstTerm)getValue(ctx.term(0));
      CstTerm rhs = (CstTerm)getValue(ctx.term(1));
      if(ctx.GREATERTHAN() != null) { res = `Cst_NumGreaterThan(lhs,rhs); }
      else if(ctx.GREATEROREQ() != null) { res = `Cst_NumGreaterOrEqualTo(lhs,rhs); }
      else if(ctx.LOWERTHAN() != null) { res = `Cst_NumLessThan(lhs,rhs); }
      else if(ctx.LOWEROREQ() != null) { res = `Cst_NumLessOrEqualTo(lhs,rhs); }
      else if(ctx.DOUBLEEQ() != null) { res = `Cst_NumEqualTo(lhs,rhs); }
      else if(ctx.DIFFERENT() != null) { res = `Cst_NumDifferent(lhs,rhs); }

    }

    setValue("exitConstraint",ctx,res);
  }

  /*
   * term
   *   : var=ID STAR?
   *   | fsym=ID LPAREN (term (COMMA term)*)? RPAREN 
   *   ;
   */
  public void exitTerm(TomIslandParser.TermContext ctx) {
    CstTerm res = null;
    if(ctx.var != null && ctx.STAR() == null) {
      res = `Cst_TermVariable(ctx.var.getText());
    } if(ctx.var != null && ctx.STAR() != null) {
      res = `Cst_TermVariableStar(ctx.var.getText());
    } if(ctx.fsym != null) {
      CstTermList args = buildCstTermList(ctx.term());
      res = `Cst_TermAppl(ctx.fsym.getText(),args);
    }
    setValue("exitTerm",ctx,res);
  }

  /*
   * bqterm
   *   : codomain=ID? BQUOTE? fsym=ID LPAREN (bqterm (COMMA bqterm)*)? RPAREN 
   *   | codomain=ID? BQUOTE? var=ID STAR?
   *   | constant
   *   ;
   */
  public void exitBqterm(TomIslandParser.BqtermContext ctx) {
    CstBQTerm res = null;
    CstOptionList optionList = `ConcCstOption(extractOption(ctx.getStart()));
    CstType type = (ctx.codomain != null)?`Cst_Type(ctx.codomain.getText()):`Cst_TypeUnknown();

    if(ctx.fsym != null) {
      CstBQTermList args = buildCstBQTermList(ctx.bqterm());
      res = `Cst_BQAppl(optionList,ctx.fsym.getText(),args);
    } if(ctx.var != null && ctx.STAR() == null) {
      res = `Cst_BQVar(optionList,ctx.var.getText(),type);
    } if(ctx.var != null && ctx.STAR() != null) {
      res = `Cst_BQVarStar(optionList,ctx.var.getText(),type);
    } if(ctx.constant() != null) {
      CstSymbol cst = (CstSymbol) getValue(ctx.constant());
      res = `Cst_BQConstant(optionList,cst.getvalue());
    }

    setValue2(ctx,type);
    setValue("exitBqterm",ctx,res);
  }

  /*
   * bqcomposite
   *   : BQUOTE composite
   *   ;
   */
  public void exitBqcomposite(TomIslandParser.BqcompositeContext ctx) {
    setValue("exitBqcomposite",ctx,`Cst_BQTermToBlock((CstBQTerm)getValue(ctx.composite())));
  }

  /*
   * composite
   *   : fsym=ID LPAREN composite* RPAREN
   *   | LPAREN composite* RPAREN
   *   | var=ID STAR?
   *   | constant
   *   | waterexceptparen
   *   ;
   */
  public void exitComposite(TomIslandParser.CompositeContext ctx) {
    CstOptionList optionList = `ConcCstOption(extractOption(ctx.getStart()));
    CstBQTerm res = null;
    CstType type = `Cst_TypeUnknown();

    if(ctx.fsym != null) {
      CstBQTermList args = `ConcCstBQTerm();
      /*
         if(ctx.compositeplus() != null) {
      // retrieve list of elements separated by COMMA
      CstBQTermList list = ((CstBQTerm)getValue(ctx.compositeplus())).getlist();
      for(CstBQTerm elt:list.getCollectionConcCstBQTerm()) {
      System.out.println("elt: " + elt);
      }
      }
       */

      CstBQTermList accu = `ConcCstBQTerm();
      for(ParserRuleContext e:ctx.composite()) {
        CstBQTerm bq = (CstBQTerm)getValue(e);
        if(bq.isCst_ITL() && bq.getcode() == ",") {
          // put all elements of accu as a subterm
          CstBQTerm newComposite = `Cst_BQComposite(ConcCstOption(),accu);
          //CstBQTerm newComposite = flattenComposite(`Cst_BQComposite(ConcCstOption(),accu));
          //newComposite = mergeITL(newComposite);
          args = `ConcCstBQTerm(args*,newComposite);
          accu = `ConcCstBQTerm();
        } else {
          // retrieve elements separated by COMMA
          accu = `ConcCstBQTerm(accu*,bq);
        }
      }
      // flush the last accu
      %match(accu) {
        ConcCstBQTerm(bq) -> {
          // single element
          args = `ConcCstBQTerm(args*,bq);
        }

        ConcCstBQTerm(_,_,_*) -> {
          // multiple elements: build a composite
          //CstBQTerm newComposite = flattenComposite(`Cst_BQComposite(ConcCstOption(),accu));
          CstBQTerm newComposite = `Cst_BQComposite(ConcCstOption(),accu);
          //newComposite = mergeITL(newComposite);
          args = `ConcCstBQTerm(args*,newComposite);
        }
      }

      res = `Cst_BQAppl(optionList,ctx.fsym.getText(),args);
    } else if(ctx.LPAREN() != null && ctx.RPAREN() != null) {
      CstOptionList optionList1 = `ConcCstOption(extractOption(ctx.LPAREN().getSymbol()));
      CstOptionList optionList2 = `ConcCstOption(extractOption(ctx.RPAREN().getSymbol()));
      CstBQTermList args = buildCstBQTermList(ctx.composite());
      res = `Cst_BQComposite(optionList, ConcCstBQTerm(
            Cst_ITL(optionList1,ctx.LPAREN().getText()),
            args*,
            Cst_ITL(optionList2,ctx.RPAREN().getText())
            ));

      //res = mergeITL(res);
    } else if(ctx.var != null && ctx.STAR() == null) {
      res = `Cst_BQVar(optionList,ctx.var.getText(),type);
    } else if(ctx.var != null && ctx.STAR() != null) {
      res = `Cst_BQVarStar(optionList,ctx.var.getText(),type);
    } else if(ctx.constant() != null) {
      CstSymbol cst = (CstSymbol) getValue(ctx.constant());
      res = `Cst_BQConstant(optionList,cst.getvalue());
    } else if (ctx.waterexceptparen() != null) {
      //System.out.println("composite water");
      res = `Cst_ITL(optionList, getStringValue(ctx.waterexceptparen()));
    }

    setValue("exitComposite",ctx,res);
  }

  /*
   * pattern
   *   : ID AT pattern 
   *   | ANTI pattern
   *   | fsymbol explicitArgs
   *   | fsymbol implicitArgs
   *   | var=ID STAR?
   *   | UNDERSCORE STAR?
   *   | constant STAR?
   *   ;
   */
  public void exitPattern(TomIslandParser.PatternContext ctx) {
    CstPattern res = null;
    if(ctx.AT() != null) {
      res = `Cst_AnnotatedPattern((CstPattern)getValue(ctx.pattern()), ctx.ID().getText());
    } if(ctx.ANTI() != null) {
      res = `Cst_Anti((CstPattern)getValue(ctx.pattern()));
    } if(ctx.explicitArgs() != null) {
      res = `Cst_Appl((CstSymbolList)getValue(ctx.fsymbol()), (CstPatternList)getValue(ctx.explicitArgs()));
    } if(ctx.implicitArgs() != null) {
      res = `Cst_RecordAppl((CstSymbolList)getValue(ctx.fsymbol()), (CstPairPatternList)getValue(ctx.implicitArgs()));
    } if(ctx.var != null && ctx.STAR() == null) {
      res = `Cst_Variable(ctx.var.getText());
    } if(ctx.var != null && ctx.STAR() != null) {
      res = `Cst_VariableStar(ctx.var.getText());
    } if(ctx.UNDERSCORE() != null && ctx.STAR() == null) {
      res = `Cst_UnamedVariable();
    } if(ctx.UNDERSCORE() != null && ctx.STAR() != null) {
      res = `Cst_UnamedVariableStar();
    } if(ctx.constant() != null && ctx.STAR() == null) {
      CstSymbol cst = (CstSymbol) getValue(ctx.constant());
      res = `Cst_Constant(cst);
    } if(ctx.constant() != null && ctx.STAR() != null) {
      CstSymbol cst = (CstSymbol) getValue(ctx.constant());
      res = `Cst_ConstantStar(cst);
    }
    setValue("exitPattern",ctx,res);
  }

  /*
   * fsymbol 
   *   : headSymbol
   *   | LPAREN headSymbol (PIPE headSymbol)* RPAREN
   *   ;
   */
  public void exitFsymbol(TomIslandParser.FsymbolContext ctx) {
    CstSymbolList res = buildCstSymbolList(ctx.headSymbol());
    setValue("exitFsymbol",ctx,res);
  }

  /*
   * headSymbol
   *   : ID QMARK?
   *   | ID DQMARK?
   *   | constant
   *   ;
   */
  public void exitHeadSymbol(TomIslandParser.HeadSymbolContext ctx) {
    CstSymbol res = null;
    if(ctx.QMARK() != null) {
      res = `Cst_Symbol(ctx.ID().getText(), Cst_TheoryAU());
    } else if(ctx.DQMARK() != null) {
      res = `Cst_Symbol(ctx.ID().getText(), Cst_TheoryAC());
    } else if(ctx.ID() != null) {
      res = `Cst_Symbol(ctx.ID().getText(), Cst_TheoryDEFAULT());
    } else if(ctx.constant() != null) {
      res = (CstSymbol) getValue(ctx.constant());
    } 
    setValue("exitHeadSymbol",ctx,res);
  }

  /*
   * constant
   *   : INTEGER
   *   | LONG
   *   | CHAR
   *   | DOUBLE
   *   | STRING
   *   ;
   */
  public void exitConstant(TomIslandParser.ConstantContext ctx) {
    CstSymbol res = null;
    if(ctx.INTEGER() != null) {
      res = `Cst_SymbolInt(ctx.INTEGER().getText());
    } else if(ctx.LONG() != null) {
      res = `Cst_SymbolLong(ctx.LONG().getText());
    } else if(ctx.CHAR() != null) {
      res = `Cst_SymbolChar(ctx.CHAR().getText());
    } else if(ctx.DOUBLE() != null) {
      res = `Cst_SymbolDouble(ctx.DOUBLE().getText());
    } else if(ctx.STRING() != null) {
      res = `Cst_SymbolString(ctx.STRING().getText());
    }
    setValue("exitConstant",ctx,res);
  }

  /*
   * explicitArgs
   *   : LPAREN (pattern (COMMA pattern)*)? RPAREN
   *   ;
   */
  public void exitExplicitArgs(TomIslandParser.ExplicitArgsContext ctx) {
    int n = ctx.pattern().size();
    CstPatternList res = `ConcCstPattern();
    for(int i=0 ; i<n ; i++) {
      res = `ConcCstPattern(res*, (CstPattern)getValue(ctx.pattern(i)));
    }
    setValue("exitExplicitArgs",ctx,res);
  }

  /*
   * implicitArgs
   *   : LSQUAREBR (ID EQUAL pattern (COMMA ID EQUAL pattern)*)? RSQUAREBR 
   *   ;
   */
  public void exitImplicitArgs(TomIslandParser.ImplicitArgsContext ctx) {
    int n = ctx.ID().size();
    CstPairPatternList res = `ConcCstPairPattern();
    for(int i=0 ; i<n ; i++) {
      res = `ConcCstPairPattern(res*, Cst_PairPattern(ctx.ID(i).getText(), (CstPattern)getValue(ctx.pattern(i))));
    }
    setValue("exitImplicitArgs",ctx,res);
  }

  /*
   * typeterm
   *   : TYPETERM type=ID (EXTENDS supertype=ID)? LBRACE 
   *     implement isSort? equalsTerm?
   *     RBRACE
   *   ;
   */
  public void exitTypeterm(TomIslandParser.TypetermContext ctx) {
    CstOptionList optionList = `ConcCstOption(extractOption(ctx.getStart()));
    CstType typeName = `Cst_Type(ctx.type.getText());
    CstType extendsTypeName = `Cst_TypeUnknown();
    if(ctx.supertype != null) {
      extendsTypeName = `Cst_Type(ctx.supertype.getText());
    }
    CstOperatorList operatorList = `ConcCstOperator();
    operatorList = addCstOperator(operatorList, ctx.implement());
    operatorList = addCstOperator(operatorList, ctx.isSort());
    operatorList = addCstOperator(operatorList, ctx.equalsTerm());
    setValue("exitTypeterm", ctx,
        `Cst_TypetermConstruct(optionList,typeName,extendsTypeName,operatorList));
  }

  /*
   * operator
   *   : OP codomain=ID opname=ID LPAREN slotList? RPAREN LBRACE 
   *     (isFsym | make | getSlot | getDefault)*
   *     RBRACE
   *   ;
   */
  public void exitOperator(TomIslandParser.OperatorContext ctx) {
    CstOptionList optionList = `ConcCstOption(extractOption(ctx.getStart()));
    CstType codomain = `Cst_Type(ctx.codomain.getText());
    CstName ctorName = `Cst_Name(ctx.opname.getText());
    // fill arguments
    CstSlotList argumentList = `ConcCstSlot();
    if(ctx.slotList() != null) {
      argumentList = (CstSlotList) getValue(ctx.slotList());
    }
    // fill constructors
    CstOperatorList operatorList = `ConcCstOperator();
    operatorList = addCstOperator(operatorList, ctx.isFsym());
    operatorList = addCstOperator(operatorList, ctx.make());
    operatorList = addCstOperator(operatorList, ctx.getSlot());
    operatorList = addCstOperator(operatorList, ctx.getDefault());
    setValue("exitOperator", ctx,
        `Cst_OpConstruct(optionList,codomain,ctorName,argumentList,operatorList));
  }

  /*
   * oplist
   *   : OPLIST codomain=ID opname=ID LPAREN domain=ID STAR RPAREN LBRACE 
   *     (isFsym | makeEmptyList | makeInsertList | getHead | getTail | isEmptyList)*
   *     RBRACE
   *   ;
   */
  public void exitOplist(TomIslandParser.OplistContext ctx) {
    CstOptionList optionList = `ConcCstOption(extractOption(ctx.getStart()));
    CstType codomain = `Cst_Type(ctx.codomain.getText());
    CstName ctorName = `Cst_Name(ctx.opname.getText());
    CstType domain = `Cst_Type(ctx.domain.getText());
    // fill constructors
    CstOperatorList operatorList = `ConcCstOperator();
    operatorList = addCstOperator(operatorList, ctx.isFsym());
    operatorList = addCstOperator(operatorList, ctx.makeEmptyList());
    operatorList = addCstOperator(operatorList, ctx.makeInsertList());
    operatorList = addCstOperator(operatorList, ctx.getHead());
    operatorList = addCstOperator(operatorList, ctx.getTail());
    operatorList = addCstOperator(operatorList, ctx.isEmptyList());
    setValue("exitOpList", ctx,
        `Cst_OpListConstruct(optionList,codomain,ctorName,domain,operatorList));
  }

  /*
   * oparray
   *   : OPARRAY codomain=ID opname=ID LPAREN domain=ID STAR RPAREN LBRACE 
   *     (isFsym | makeEmptyArray | makeAppendArray | getElement | getSize)*
   *     RBRACE
   *   ;
   */
  public void exitOparray(TomIslandParser.OparrayContext ctx) {
    CstOptionList optionList = `ConcCstOption(extractOption(ctx.getStart()));
    CstType codomain = `Cst_Type(ctx.codomain.getText());
    CstName ctorName = `Cst_Name(ctx.opname.getText());
    CstType domain = `Cst_Type(ctx.domain.getText());
    // fill constructors
    CstOperatorList operatorList = `ConcCstOperator();
    operatorList = addCstOperator(operatorList, ctx.isFsym());
    operatorList = addCstOperator(operatorList, ctx.makeEmptyArray());
    operatorList = addCstOperator(operatorList, ctx.makeAppendArray());
    operatorList = addCstOperator(operatorList, ctx.getElement());
    operatorList = addCstOperator(operatorList, ctx.getSize());
    setValue("exitOpArray", ctx,
        `Cst_OpArrayConstruct(optionList,codomain,ctorName,domain,operatorList));
  }

  /*
   * implement
   *   : IMPLEMENT block
   *   ;
   */
  public void exitImplement(TomIslandParser.ImplementContext ctx) {
    setValue("exitImplement", ctx,
        `Cst_Implement((CstBlockList) getValue(ctx.block())));
  }

  /*
   * equalsTerm
   *   : EQUALS LPAREN id1=ID COMMA id2=ID RPAREN block
   *   ;
   */
  public void exitEqualsTerm(TomIslandParser.EqualsTermContext ctx) {
    setValue("exitEquals", ctx,
        `Cst_Equals(Cst_Name(ctx.id1.getText()), Cst_Name(ctx.id2.getText()), (CstBlockList) getValue(ctx.block())));
  }

  /*
   * isSort
   *   : IS_SORT LPAREN ID RPAREN block
   *   ;
   */
  public void exitIsSort(TomIslandParser.IsSortContext ctx) {
    setValue("exitIsSort", ctx,
        `Cst_IsSort(Cst_Name(ctx.ID().getText()), (CstBlockList) getValue(ctx.block())));
  }

  /*
   * isFsym
   *   : IS_FSYM LPAREN ID RPAREN block
   *   ;
   */
  public void exitIsFsym(TomIslandParser.IsFsymContext ctx) {
    setValue("exitIsFsym", ctx,
        `Cst_IsFsym(Cst_Name(ctx.ID().getText()), (CstBlockList) getValue(ctx.block())));
  }

  /*
   * make
   *   : MAKE LPAREN (ID (COMMA ID)*)? RPAREN block
   *   ;
   */
  public void exitMake(TomIslandParser.MakeContext ctx) {
    CstNameList nameList = `ConcCstName();
    for(TerminalNode e:ctx.ID()) {
      nameList = `ConcCstName(nameList*, Cst_Name(e.getText()));
    }
    setValue("exitMake", ctx,
        `Cst_Make(nameList,(CstBlockList) getValue(ctx.block())));
  }

  /*
   * makeEmptyList
   *   : MAKE_EMPTY LPAREN RPAREN block
   *   ;
   */
  public void exitMakeEmptyList(TomIslandParser.MakeEmptyListContext ctx) {
    setValue("exitMakeEmptyList", ctx,
        `Cst_MakeEmptyList((CstBlockList) getValue(ctx.block())));
  }

  /*
   * makeEmptyArray
   *   : MAKE_EMPTY LPAREN ID RPAREN block
   *   ;
   */
  public void exitMakeEmptyArray(TomIslandParser.MakeEmptyArrayContext ctx) {
    setValue("exitMakeEmptyArray", ctx,
        `Cst_MakeEmptyArray(Cst_Name(ctx.ID().getText()), (CstBlockList) getValue(ctx.block())));
  }

  /*
   * makeAppendArray
   *   : MAKE_APPEND LPAREN id1=ID COMMA id2=ID RPAREN block
   *   ;
   */
  public void exitMakeAppendArray(TomIslandParser.MakeAppendArrayContext ctx) {
    setValue("exitMakeAppendArray", ctx,
        `Cst_MakeAppend(Cst_Name(ctx.id1.getText()), Cst_Name(ctx.id2.getText()), (CstBlockList) getValue(ctx.block())));
  }

  /*
   * makeInsertList
   *   : MAKE_INSERT LPAREN id1=ID COMMA id2=ID RPAREN block
   *   ;
   */
  public void exitMakeInsertList(TomIslandParser.MakeInsertListContext ctx) {
    setValue("exitMakeInsertList", ctx,
        `Cst_MakeInsert(Cst_Name(ctx.id1.getText()), Cst_Name(ctx.id2.getText()), (CstBlockList) getValue(ctx.block())));
  }

  /*
   * getSlot
   *   : GET_SLOT LPAREN id1=ID COMMA id2=ID RPAREN block
   *   ;
   */
  public void exitGetSlot(TomIslandParser.GetSlotContext ctx) {
    setValue("exitGetSlot", ctx,
        `Cst_GetSlot(Cst_Name(ctx.id1.getText()), Cst_Name(ctx.id2.getText()), (CstBlockList) getValue(ctx.block())));
  }

  /*
   * getHead
   *   : GET_HEAD LPAREN ID RPAREN block
   *   ;
   */
  public void exitGetHead(TomIslandParser.GetHeadContext ctx) {
    setValue("exitGetHead", ctx,
        `Cst_GetHead(Cst_Name(ctx.ID().getText()), (CstBlockList) getValue(ctx.block())));
  }

  /*
   * getTail
   *   : GET_TAIL LPAREN ID RPAREN block
   *   ;
   */
  public void exitGetTail(TomIslandParser.GetTailContext ctx) {
    setValue("exitGetTail", ctx,
        `Cst_GetTail(Cst_Name(ctx.ID().getText()), (CstBlockList) getValue(ctx.block())));
  }

  /*
   * getElement
   *   : GET_ELEMENT LPAREN id1=ID COMMA id2=ID RPAREN block
   *   ;
   */
  public void exitGetElement(TomIslandParser.GetElementContext ctx) {
    setValue("exitGetElement", ctx,
        `Cst_GetElement(Cst_Name(ctx.id1.getText()), Cst_Name(ctx.id2.getText()), (CstBlockList) getValue(ctx.block())));
  }

  /*
   * isEmptyList
   *   : IS_EMPTY LPAREN ID RPAREN block
   *   ;
   */
  public void exitIsEmptyList(TomIslandParser.IsEmptyListContext ctx) {
    setValue("exitIsEmptyList", ctx,
        `Cst_IsEmpty(Cst_Name(ctx.ID().getText()), (CstBlockList) getValue(ctx.block())));
  }

  /*
   * getSize
   *   : GET_SIZE LPAREN ID RPAREN block
   *   ;
   */
  public void exitGetSize(TomIslandParser.GetSizeContext ctx) {
    setValue("exitGetSize", ctx,
        `Cst_GetSize(Cst_Name(ctx.ID().getText()), (CstBlockList) getValue(ctx.block())));
  }

  /*
   * getDefault
   *   : GET_DEFAULT LPAREN ID RPAREN block
   *   ;
   */
  public void exitGetDefault(TomIslandParser.GetDefaultContext ctx) {
    setValue("exitGetDefault", ctx,
        `Cst_GetDefault(Cst_Name(ctx.ID().getText()), (CstBlockList) getValue(ctx.block())));
  }

  /*
   * End of grammar
   */

  private static CstOption extractOption(Token t) {
    String newline = System.getProperty("line.separator");
    String lines[] = t.getText().split(newline);

    int firstCharLine = t.getLine();
    int firstCharColumn = t.getCharPositionInLine()+1;
    int lastCharLine = firstCharLine+lines.length-1;
    int lastCharColumn;
    if(lines.length==1) {
      lastCharColumn = firstCharColumn + lines[0].length();
    } else {
      lastCharColumn = lines[lines.length-1].length();
    }

    return `Cst_OriginTracking(t.getInputStream().getSourceName(), firstCharLine, firstCharColumn, lastCharLine, lastCharColumn);  
  }


  public CstOperatorList addCstOperator(CstOperatorList operatorList, ParserRuleContext ctx) {
    if(ctx != null) {
      operatorList = `ConcCstOperator(operatorList*, (CstOperator)getValue(ctx));
    }
    return operatorList;
  }

  public CstOperatorList addCstOperator(CstOperatorList operatorList, List<? extends ParserRuleContext> ctxList) {
    for(ParserRuleContext e:ctxList) {
      operatorList = addCstOperator(operatorList, e);
    }
    return operatorList;
  }

  /*
   * convert list of context into CstList 
   */
  private CstBQTermList buildCstBQTermList(List<? extends ParserRuleContext> ctx) {
    CstBQTermList res = `ConcCstBQTerm();
    if(ctx != null) {
      for(ParserRuleContext e:ctx) {
        res = `ConcCstBQTerm(res*, (CstBQTerm)getValue(e));
      }
    }
    return res;
  }

  private CstConstraintActionList buildCstConstraintActionList(List<? extends ParserRuleContext> ctx) {
    CstConstraintActionList res = `ConcCstConstraintAction();
    if(ctx != null) {
      for(ParserRuleContext e:ctx) {
        res = `ConcCstConstraintAction(res*, (CstConstraintAction)getValue(e));
      }
    }
    return res;
  }

  private CstVisitList buildCstVisitList(List<? extends ParserRuleContext> ctx) {
    CstVisitList res = `ConcCstVisit();
    if(ctx != null) {
      for(ParserRuleContext e:ctx) {
        res = `ConcCstVisit(res*, (CstVisit)getValue(e));
      }
    }
    return res;
  }

  private CstSlotList buildCstSlotList(List<? extends ParserRuleContext> ctx) {
    CstSlotList res = `ConcCstSlot();
    if(ctx != null) {
      for(ParserRuleContext e:ctx) {
        res = `ConcCstSlot(res*, (CstSlot)getValue(e));
      }
    }
    return res;
  }

  private CstPatternList buildCstPatternList(List<? extends ParserRuleContext> ctx) {
    CstPatternList res = `ConcCstPattern();
    if(ctx != null) {
      for(ParserRuleContext e:ctx) {
        res = `ConcCstPattern(res*, (CstPattern)getValue(e));
      }
    }
    return res;
  }

  private CstTermList buildCstTermList(List<? extends ParserRuleContext> ctx) {
    CstTermList res = `ConcCstTerm();
    if(ctx != null) {
      for(ParserRuleContext e:ctx) {
        res = `ConcCstTerm(res*, (CstTerm)getValue(e));
      }
    }
    return res;
  }

  private CstSymbolList buildCstSymbolList(List<? extends ParserRuleContext> ctx) {
    CstSymbolList res = `ConcCstSymbol();
    if(ctx != null) {
      for(ParserRuleContext e:ctx) {
        res = `ConcCstSymbol(res*, (CstSymbol)getValue(e));
      }
    }
    return res;
  }

}

