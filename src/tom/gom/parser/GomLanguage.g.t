/*
 * Gom
 *
 * Copyright (c) 2007, INRIA
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
 * Antoine Reilles    e-mail: Antoine.Reilles@loria.fr
 *
 **/
grammar GomLanguage;

options {
  output=AST;
  ASTLabelType=GomTree;
}

tokens {
  %include { gom/GomTokenList.txt }
}

@header {
package tom.gom.parser;
import tom.gom.adt.gom.GomTree;
import tom.gom.GomStreamManager;
}

@lexer::header {
package tom.gom.parser;
import tom.gom.adt.gom.GomTree;
}

@members {
  private GomStreamManager streamManager;
  public GomLanguageParser(TokenStream input, GomStreamManager streamManager) {
    super(input);
    this.streamManager = streamManager;
  }
}

module :
  MODULE modulename (imps=imports)? section
  -> {imps!=null}? ^(GomModule modulename ^(ConcSection imports section))
  -> ^(GomModule modulename ^(ConcSection section))
  ;

modulename
@init {
  StringBuilder packagePrefix = new StringBuilder("");
} :
  (mod=ID DOT { packagePrefix.append($mod.text+"."); })*
  moduleName=ID
  {
    packagePrefix.deleteCharAt(packagePrefix.length()-1);
    if (null != streamManager) {
      streamManager.associatePackagePath($moduleName.text,packagePrefix.toString());
    }
  }
  -> ^(GomModuleName $moduleName)
  ;

imports :
  IMPORTS (importedModuleName)* -> ^(Imports (importedModuleName)*)
  ;
importedModuleName :
  ID -> ^(Import ^(GomModuleName ID))
  ;

section :
  (PUBLIC)? adtgrammar -> ^(Public adtgrammar)
  ;

adtgrammar :
  (gr+=sortdef | gr+=syntax)+ -> $gr
  ;

sortdef :
  SORTS (type)* -> ^(ConcGrammar ^(Sorts (type)*))
  ;

type :
  ID -> ^(GomType ID);

syntax :
  ABSTRACT SYNTAX (gr+=production | gr+=hookConstruct | gr+=typedecl)*
  -> ^(ConcGrammar ^(Grammar ($gr)*))
  ;

production
@init {
String startLine = ""+input.LT(1).getLine();
} :
  ID fieldlist ARROW type -> ^(Production ID fieldlist type ^(Origin ID[startLine]))
  ;

typedecl :
  typename=ID EQUALS alts=alternatives[typename] -> $alts
  ;

alternatives[Token typename] :
  (ALT)? opdecl[typename] (ALT opdecl[typename])* (SEMI)?
  -> ^(ConcProduction (opdecl)+)
  ;

opdecl[Token type] :
  ID fieldlist
  -> ^(Production ID fieldlist ^(GomType ID[type])
      ^(Origin ID[""+input.LT(1).getLine()]))
  ;

fieldlist :
  LPAREN (field (COMMA field)* )? RPAREN -> ^(ConcField (field)*) ;

arglist:
  (LPAREN (arg (COMMA arg)* )? RPAREN)? 
  -> ^(ConcArg (arg)*)
  ;

arg : ID -> ^(Arg ID);

hookConstruct :
  (hscope=hookScope)? pointCut=ID COLON hookType=ID arglist LBRACE
  -> {hscope!=null}? ^(Hook $hscope $pointCut ^(HookKind $hookType) arglist LBRACE
                       ^(Origin ID[""+input.LT(1).getLine()]))
  -> ^(Hook ^(KindOperator) $pointCut ^(HookKind $hookType) arglist LBRACE
      ^(Origin ID[""+input.LT(1).getLine()]))
  /* The LBRACE should contain the code */
  ;

hookScope :
  SORT -> ^(KindSort)
  | MODULE -> ^(KindModule)
  | OPERATOR -> ^(KindOperator)
  ;

field :
  type STAR -> ^(StarredField type)
  | ID COLON type -> ^(NamedField ID type)
  ;

MODULE   : 'module';
IMPORTS  : 'imports';
PUBLIC   : 'public';
PRIVATE  : 'private';
SORTS    : 'sorts';
ABSTRACT : 'abstract';
SYNTAX   : 'syntax';
SORT     : 'sort';
OPERATOR : 'operator';

ARROW    : '->';
COLON    : ':';
COMMA    : ',';
DOT      : '.';
LPAREN   : '(';
RPAREN   : ')';
STAR     : '*';
EQUALS   : '=';
ALT      : '|';
SEMI     : ';;';

LBRACE: '{'
  {
    SimpleBlockLexer lex = new SimpleBlockLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lex);
    SimpleBlockParser parser = new SimpleBlockParser(tokens);
    parser.block();
  }
  ;

RBRACE: '}';

WS : ( ' '
       | '\t'
       | ( '\r\n' // DOS
           | '\n'   // Unix
           | '\r'   // Macintosh
           )
       )
  {$channel=HIDDEN;}
  ;

SLCOMMENT :
  '//' (~('\n'|'\r'))* ('\n'|'\r'('\n')?)?
  {$channel=HIDDEN;}
  ;

MLCOMMENT :
  '/*' .* '*/' {$channel=HIDDEN;}
  ;

ID : ('a'..'z' | 'A'..'Z')
     ('a'..'z' | 'A'..'Z' | '0'..'9' | '_' | '-')* ;
