/* Generated By:JavaCC: Do not edit this line. TomParserConstants.java */
/*
  
    TOM - To One Matching Compiler

    Copyright (C) 2000-2003  LORIA (CNRS, INPL, INRIA, UHP, U-Nancy 2)
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

package jtom.parser;

public interface TomParserConstants {

  int EOF = 0;
  int MATCH = 1;
  int MATCHXML = 2;
  int RULE = 3;
  int MAKE_TERM = 4;
  int BACKQUOTE_TERM = 5;
  int BACKQUOTE_XML = 6;
  int VARIABLE = 7;
  int TYPE = 8;
  int TYPETERM = 9;
  int TYPEINT = 10;
  int TYPESTRING = 11;
  int TYPELIST = 12;
  int TYPEARRAY = 13;
  int INCLUDE = 14;
  int OPERATOR = 15;
  int OPERATOR_LIST = 16;
  int OPERATOR_ARRAY = 17;
  int SINGLE_LINE_COMMENT = 26;
  int FORMAL_COMMENT = 27;
  int MULTI_LINE_COMMENT = 28;
  int STRING_LITERAL = 30;
  int LBRACE = 31;
  int RBRACE = 32;
  int OTHER = 33;
  int TOM_SINGLE_LINE_COMMENT = 42;
  int TOM_FORMAL_COMMENT = 43;
  int TOM_MULTI_LINE_COMMENT = 44;
  int TOM_ARROW = 46;
  int TOM_DOUBLE_ARROW = 47;
  int TOM_ALTERNATIVE = 48;
  int TOM_COMMA = 49;
  int TOM_COLON = 50;
  int TOM_EQUAL = 51;
  int TOM_AT = 52;
  int TOM_DOT = 53;
  int TOM_LPAREN = 54;
  int TOM_RPAREN = 55;
  int TOM_LBRACKET = 56;
  int TOM_RBRACKET = 57;
  int TOM_LBRACE = 58;
  int TOM_RBRACE = 59;
  int TOM_UNDERSCORE = 60;
  int TOM_MINUS = 61;
  int TOM_STAR = 62;
  int TOM_SHARP = 63;
  int TOM_AND = 64;
  int TOM_WHERE = 65;
  int TOM_IF = 66;
  int TOM_MAKE = 67;
  int TOM_MAKE_EMPTY = 68;
  int TOM_MAKE_INSERT = 69;
  int TOM_MAKE_APPEND = 70;
  int TOM_IMPLEMENT = 71;
  int TOM_FSYM = 72;
  int TOM_GET_SLOT = 73;
  int TOM_IS_FSYM = 74;
  int TOM_GET_SUBTERM = 75;
  int TOM_GET_FUN_SYM = 76;
  int TOM_CMP_FUN_SYM = 77;
  int TOM_EQUALS = 78;
  int TOM_GET_HEAD = 79;
  int TOM_GET_TAIL = 80;
  int TOM_IS_EMPTY = 81;
  int TOM_GET_ELEMENT = 82;
  int TOM_GET_SIZE = 83;
  int TOM_INTEGER = 84;
  int TOM_IDENTIFIER = 85;
  int TOM_LETTER = 86;
  int TOM_DIGIT = 87;
  int TOM_STRING = 88;

  int DEFAULT = 0;
  int IN_SINGLE_LINE_COMMENT = 1;
  int IN_FORMAL_COMMENT = 2;
  int IN_MULTI_LINE_COMMENT = 3;
  int TOM = 4;
  int TOM_IN_SINGLE_LINE_COMMENT = 5;
  int TOM_IN_FORMAL_COMMENT = 6;
  int TOM_IN_MULTI_LINE_COMMENT = 7;

  String[] tokenImage = {
    "<EOF>",
    "\"%match\"",
    "\"%matchXML\"",
    "\"%rule\"",
    "\"%make\"",
    "\"`\"",
    "\"``\"",
    "\"%variable\"",
    "\"%type\"",
    "\"%typeterm\"",
    "\"%typeint\"",
    "\"%typestring\"",
    "\"%typelist\"",
    "\"%typearray\"",
    "\"%include\"",
    "\"%op\"",
    "\"%oplist\"",
    "\"%oparray\"",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\f\"",
    "\"//\"",
    "<token of kind 24>",
    "\"/*\"",
    "<SINGLE_LINE_COMMENT>",
    "\"*/\"",
    "\"*/\"",
    "<token of kind 29>",
    "<STRING_LITERAL>",
    "\"{\"",
    "\"}\"",
    "<OTHER>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\f\"",
    "\"//\"",
    "<token of kind 40>",
    "\"/*\"",
    "<TOM_SINGLE_LINE_COMMENT>",
    "\"*/\"",
    "\"*/\"",
    "<token of kind 45>",
    "\"->\"",
    "\"=>\"",
    "\"|\"",
    "\",\"",
    "\":\"",
    "\"=\"",
    "\"@\"",
    "\".\"",
    "\"(\"",
    "\")\"",
    "\"[\"",
    "\"]\"",
    "\"{\"",
    "\"}\"",
    "\"_\"",
    "\"-\"",
    "\"*\"",
    "\"#\"",
    "\"&\"",
    "\"where\"",
    "\"if\"",
    "\"make\"",
    "\"make_empty\"",
    "\"make_insert\"",
    "\"make_append\"",
    "\"implement\"",
    "\"fsym\"",
    "\"get_slot\"",
    "\"is_fsym\"",
    "\"get_subterm\"",
    "\"get_fun_sym\"",
    "\"cmp_fun_sym\"",
    "\"equals\"",
    "\"get_head\"",
    "\"get_tail\"",
    "\"is_empty\"",
    "\"get_element\"",
    "\"get_size\"",
    "<TOM_INTEGER>",
    "<TOM_IDENTIFIER>",
    "<TOM_LETTER>",
    "<TOM_DIGIT>",
    "<TOM_STRING>",
  };

}
