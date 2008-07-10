/*
 * Gom
 *
 * Copyright (c) 2007-2008, INRIA
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
    if (packagePrefix.length() > 0) {
      packagePrefix.deleteCharAt(packagePrefix.length()-1);
      if (null != streamManager) {
        streamManager.associatePackagePath($moduleName.text,packagePrefix.toString());
      }
    }
  }
  -> ^(GomModuleName $moduleName)
  ;

imports :
  IMPORTS (importedModuleName)* -> ^(Imports ^(ConcImportedModule (importedModuleName)*))
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

syntax :
  ABSTRACT SYNTAX (gr+=production | gr+=hookConstruct | gr+=typedecl | gr+=atomdecl)*
  -> ^(ConcGrammar ^(Grammar ^(ConcProduction ($gr)*)))
  ;

production
@init {
String startLine = ""+input.LT(1).getLine();
} :
  ID fieldlist ARROW type -> ^(Production ID fieldlist type ^(Origin ID[startLine]))
  ;

atomdecl : 
  ATOM atom=ID -> ^(AtomDecl ID[atom])
  ;

typedecl :
    typename=ID  EQUALS alts=alternatives[typename]
      -> ^(SortType ^(GomType ^(ExpressionType) $typename) ^(ConcAtom) $alts)
  |  ptypename=ID BINDS b=atoms EQUALS palts=pattern_alternatives[ptypename]
      -> ^(SortType ^(GomType ^(PatternType) $ptypename) $b $palts)
  ;

atoms : 
  atom=ID (atom=ID)* -> ^(ConcAtom ($atom)+)
  ;

alternatives[Token typename] :
  (ALT)? opdecl[typename] (ALT opdecl[typename])* (SEMI)?
  -> ^(ConcProduction (opdecl)+)
  ;

pattern_alternatives[Token typename] :
  (ALT)? pattern_opdecl[typename] (ALT pattern_opdecl[typename])* (SEMI)?
  -> ^(ConcProduction (pattern_opdecl)+)
  ;

opdecl[Token type] :
  ID fieldlist
  -> ^(Production ID fieldlist ^(GomType ^(ExpressionType) ID[type])
      ^(Origin ID[""+input.LT(1).getLine()]))
  ;

pattern_opdecl[Token type] :
  ID pattern_fieldlist
  -> ^(Production ID pattern_fieldlist ^(GomType ^(PatternType) ID[type])
      ^(Origin ID[""+input.LT(1).getLine()]))
  ;

fieldlist :
  LPAREN (field (COMMA field)* )? RPAREN -> ^(ConcField (field)*) ;

pattern_fieldlist :
  LPAREN (field (COMMA pattern_field)* )? RPAREN -> ^(ConcField (pattern_field)*) ;

type:
  ID -> ^(GomType ^(ExpressionType) ID)
  ;

pattern_type:
  ID -> ^(GomType ^(PatternType) ID)
  ;

field:
    type STAR -> ^(StarredField type)
  | LDIPLE pattern_type RDIPLE STAR -> ^(StarredField pattern_type)
  | ID COLON type -> ^(NamedField ^(None) ID type)
  | ID COLON LDIPLE pattern_type RDIPLE -> ^(NamedField ^(None) ID pattern_type)
  ;

pattern_field:
  | INNER ID COLON type -> ^(NamedField ^(Inner) ID type)
  | OUTER ID COLON type -> ^(NamedField ^(Outer) ID type)
  | ID COLON pattern_type -> ^(NamedField ^(None) ID pattern_type)
  ;

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


MODULE   : 'module';
IMPORTS  : 'imports';
PUBLIC   : 'public';
PRIVATE  : 'private';
SORTS    : 'sorts';
ABSTRACT : 'abstract';
SYNTAX   : 'syntax';
SORT     : 'sort';
OPERATOR : 'operator';
ATOM     : 'atom';
INNER    : 'inner';
OUTER    : 'outer';
BINDS    : 'binds';

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
LDIPLE   : '<';
RDIPLE   : '>';

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
