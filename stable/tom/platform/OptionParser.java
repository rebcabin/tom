/* Generated by TOM (version 2.4alpha): Do not edit this file *//*
 * 
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2006, INRIA
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

package tom.platform;

import java.io.*;

import aterm.*;
import aterm.pure.*;

import tom.library.adt.tnode.*;
import tom.library.adt.tnode.types.*;
import tom.platform.adt.platformoption.*;
import tom.platform.adt.platformoption.types.*;
import tom.library.xml.*;


/**
 * Helper class to parse OptionOwner options.
 * The options have to comply with the following this DTD
 *
 * <PRE><CODE>
 * < !ELEMENT options (boolean*,integer*,string*) >
 *
 * < !ELEMENT boolean EMPTY >
 * < !ATTLIST boolean
 *   name CDATA #REQUIRED
 *   altName CDATA ""
 *   description CDATA ""
 *   value (true|false) #REQUIRED >
 *
 * < !ELEMENT integer EMPTY >
 * < !ATTLIST integer
 *   name CDATA #REQUIRED
 *   altName CDATA ""
 *   description CDATA ""
 *   value CDATA #REQUIRED
 *   attrName CDATA #REQUIRED >
 *
 * < !ELEMENT string EMPTY >
 * < !ATTLIST string
 *   name CDATA #REQUIRED
 *   altName CDATA ""
 *   description CDATA ""
 *   value CDATA #REQUIRED
 *   attrName CDATA #REQUIRED >
 * </CODE></PRE>
 */
public class OptionParser {
  
  /* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file */ private static boolean tom_terms_equal_String( String  t1,  String  t2) {  return  (t1.equals(t2))  ;}  /* Generated by TOM (version 2.4alpha): Do not edit this file */private static boolean tom_terms_equal_int( int  t1,  int  t2) {  return  (t1==t2)  ;} /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ private static boolean tom_terms_equal_TNode(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_TNodeList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_fun_sym_ElementNode( tom.library.adt.tnode.types.TNode  t) {  return  t instanceof tom.library.adt.tnode.types.tnode.ElementNode  ;}private static  String  tom_get_slot_ElementNode_Name( tom.library.adt.tnode.types.TNode  t) {  return  t.getName()  ;}private static  tom.library.adt.tnode.types.TNodeList  tom_get_slot_ElementNode_AttrList( tom.library.adt.tnode.types.TNode  t) {  return  t.getAttrList()  ;}private static  tom.library.adt.tnode.types.TNodeList  tom_get_slot_ElementNode_ChildList( tom.library.adt.tnode.types.TNode  t) {  return  t.getChildList()  ;}private static boolean tom_is_fun_sym_AttributeNode( tom.library.adt.tnode.types.TNode  t) {  return  t instanceof tom.library.adt.tnode.types.tnode.AttributeNode  ;}private static  String  tom_get_slot_AttributeNode_Name( tom.library.adt.tnode.types.TNode  t) {  return  t.getName()  ;}private static  String  tom_get_slot_AttributeNode_Specified( tom.library.adt.tnode.types.TNode  t) {  return  t.getSpecified()  ;}private static  String  tom_get_slot_AttributeNode_Value( tom.library.adt.tnode.types.TNode  t) {  return  t.getValue()  ;}private static boolean tom_is_fun_sym_concTNode( tom.library.adt.tnode.types.TNodeList  t) {  return  t instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode || t instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode  ;}private static  tom.library.adt.tnode.types.TNodeList  tom_empty_list_concTNode() { return  tom.library.adt.tnode.types.tnodelist.EmptyconcTNode.make() ; }private static  tom.library.adt.tnode.types.TNodeList  tom_cons_list_concTNode( tom.library.adt.tnode.types.TNode  e,  tom.library.adt.tnode.types.TNodeList  l) { return  tom.library.adt.tnode.types.tnodelist.ConsconcTNode.make(e,l) ; }private static  tom.library.adt.tnode.types.TNode  tom_get_head_concTNode_TNodeList( tom.library.adt.tnode.types.TNodeList  l) {  return  l.getHeadconcTNode()  ;}private static  tom.library.adt.tnode.types.TNodeList  tom_get_tail_concTNode_TNodeList( tom.library.adt.tnode.types.TNodeList  l) {  return  l.getTailconcTNode()  ;}private static boolean tom_is_empty_concTNode_TNodeList( tom.library.adt.tnode.types.TNodeList  l) {  return  l.isEmptyconcTNode()  ;}private static  tom.library.adt.tnode.types.TNodeList  tom_append_list_concTNode( tom.library.adt.tnode.types.TNodeList  l1,  tom.library.adt.tnode.types.TNodeList  l2) {    if(tom_is_empty_concTNode_TNodeList(l1)) {     return l2;    } else if(tom_is_empty_concTNode_TNodeList(l2)) {     return l1;    } else if(tom_is_empty_concTNode_TNodeList(( tom.library.adt.tnode.types.TNodeList )tom_get_tail_concTNode_TNodeList(l1))) {     return ( tom.library.adt.tnode.types.TNodeList )tom_cons_list_concTNode(( tom.library.adt.tnode.types.TNode )tom_get_head_concTNode_TNodeList(l1),l2);    } else {      return ( tom.library.adt.tnode.types.TNodeList )tom_cons_list_concTNode(( tom.library.adt.tnode.types.TNode )tom_get_head_concTNode_TNodeList(l1),tom_append_list_concTNode(( tom.library.adt.tnode.types.TNodeList )tom_get_tail_concTNode_TNodeList(l1),l2));    }   }  private static  tom.library.adt.tnode.types.TNodeList  tom_get_slice_concTNode( tom.library.adt.tnode.types.TNodeList  begin,  tom.library.adt.tnode.types.TNodeList  end) {    if(tom_terms_equal_TNodeList(begin,end)) {      return ( tom.library.adt.tnode.types.TNodeList )tom_empty_list_concTNode();    } else {      return ( tom.library.adt.tnode.types.TNodeList )tom_cons_list_concTNode(( tom.library.adt.tnode.types.TNode )tom_get_head_concTNode_TNodeList(begin),( tom.library.adt.tnode.types.TNodeList )tom_get_slice_concTNode(( tom.library.adt.tnode.types.TNodeList )tom_get_tail_concTNode_TNodeList(begin),end));    }   }   /* Generated by TOM (version 2.4alpha): Do not edit this file */private static boolean tom_terms_equal_PlatformValue(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_PlatformOptionList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_PlatformBoolean(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_PlatformOption(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static  tom.platform.adt.platformoption.types.PlatformValue  tom_make_BooleanValue( tom.platform.adt.platformoption.types.PlatformBoolean  t0) { return  tom.platform.adt.platformoption.types.platformvalue.BooleanValue.make(t0); }private static  tom.platform.adt.platformoption.types.PlatformValue  tom_make_StringValue( String  t0) { return  tom.platform.adt.platformoption.types.platformvalue.StringValue.make(t0); }private static  tom.platform.adt.platformoption.types.PlatformValue  tom_make_IntegerValue( int  t0) { return  tom.platform.adt.platformoption.types.platformvalue.IntegerValue.make(t0); }private static  tom.platform.adt.platformoption.types.PlatformBoolean  tom_make_True() { return  tom.platform.adt.platformoption.types.platformboolean.True.make(); }private static  tom.platform.adt.platformoption.types.PlatformBoolean  tom_make_False() { return  tom.platform.adt.platformoption.types.platformboolean.False.make(); }private static  tom.platform.adt.platformoption.types.PlatformOption  tom_make_PluginOption( String  t0,  String  t1,  String  t2,  tom.platform.adt.platformoption.types.PlatformValue  t3,  String  t4) { return  tom.platform.adt.platformoption.types.platformoption.PluginOption.make(t0, t1, t2, t3, t4); }private static boolean tom_is_fun_sym_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOptionList  t) {  return  t instanceof tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption || t instanceof tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption  ;}private static  tom.platform.adt.platformoption.types.PlatformOptionList  tom_empty_list_concPlatformOption() { return  tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption.make() ; }private static  tom.platform.adt.platformoption.types.PlatformOptionList  tom_cons_list_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOption  e,  tom.platform.adt.platformoption.types.PlatformOptionList  l) { return  tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make(e,l) ; }private static  tom.platform.adt.platformoption.types.PlatformOption  tom_get_head_concPlatformOption_PlatformOptionList( tom.platform.adt.platformoption.types.PlatformOptionList  l) {  return  l.getHeadconcPlatformOption()  ;}private static  tom.platform.adt.platformoption.types.PlatformOptionList  tom_get_tail_concPlatformOption_PlatformOptionList( tom.platform.adt.platformoption.types.PlatformOptionList  l) {  return  l.getTailconcPlatformOption()  ;}private static boolean tom_is_empty_concPlatformOption_PlatformOptionList( tom.platform.adt.platformoption.types.PlatformOptionList  l) {  return  l.isEmptyconcPlatformOption()  ;}private static  tom.platform.adt.platformoption.types.PlatformOptionList  tom_append_list_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOptionList  l1,  tom.platform.adt.platformoption.types.PlatformOptionList  l2) {    if(tom_is_empty_concPlatformOption_PlatformOptionList(l1)) {     return l2;    } else if(tom_is_empty_concPlatformOption_PlatformOptionList(l2)) {     return l1;    } else if(tom_is_empty_concPlatformOption_PlatformOptionList(( tom.platform.adt.platformoption.types.PlatformOptionList )tom_get_tail_concPlatformOption_PlatformOptionList(l1))) {     return ( tom.platform.adt.platformoption.types.PlatformOptionList )tom_cons_list_concPlatformOption(( tom.platform.adt.platformoption.types.PlatformOption )tom_get_head_concPlatformOption_PlatformOptionList(l1),l2);    } else {      return ( tom.platform.adt.platformoption.types.PlatformOptionList )tom_cons_list_concPlatformOption(( tom.platform.adt.platformoption.types.PlatformOption )tom_get_head_concPlatformOption_PlatformOptionList(l1),tom_append_list_concPlatformOption(( tom.platform.adt.platformoption.types.PlatformOptionList )tom_get_tail_concPlatformOption_PlatformOptionList(l1),l2));    }   }  private static  tom.platform.adt.platformoption.types.PlatformOptionList  tom_get_slice_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOptionList  begin,  tom.platform.adt.platformoption.types.PlatformOptionList  end) {    if(tom_terms_equal_PlatformOptionList(begin,end)) {      return ( tom.platform.adt.platformoption.types.PlatformOptionList )tom_empty_list_concPlatformOption();    } else {      return ( tom.platform.adt.platformoption.types.PlatformOptionList )tom_cons_list_concPlatformOption(( tom.platform.adt.platformoption.types.PlatformOption )tom_get_head_concPlatformOption_PlatformOptionList(begin),( tom.platform.adt.platformoption.types.PlatformOptionList )tom_get_slice_concPlatformOption(( tom.platform.adt.platformoption.types.PlatformOptionList )tom_get_tail_concPlatformOption_PlatformOptionList(begin),end));    }   }   


  
  /**
   * An XMLTools for doing the stuff
   */
  private static XmlTools xtools = new XmlTools();
  
  /**
   * @return a PlatformOptionList extracted from the a String
   */
  public static PlatformOptionList xmlToOptionList(String xmlString) {
    InputStream stream = new ByteArrayInputStream(xmlString.getBytes());
    TNode node = xtools.convertXMLToTNode(stream);
    return xmlNodeToOptionList(node.getDocElem());
  }
  
  /**
   * @return a PlatformOptionList extracted from a TNode
   */
  public static PlatformOptionList xmlNodeToOptionList(TNode optionsNode) {
    PlatformOptionList list = tom_empty_list_concPlatformOption();
     if(optionsNode instanceof  tom.library.adt.tnode.types.TNode ) { { tom.library.adt.tnode.types.TNode  tom_match1_1=(( tom.library.adt.tnode.types.TNode )optionsNode); if ( ( tom_is_fun_sym_ElementNode(tom_match1_1) ||  false  ) ) { { String  tom_match1_1_Name=tom_get_slot_ElementNode_Name(tom_match1_1); { tom.library.adt.tnode.types.TNodeList  tom_match1_1_AttrList=tom_get_slot_ElementNode_AttrList(tom_match1_1); { tom.library.adt.tnode.types.TNodeList  tom_match1_1_ChildList=tom_get_slot_ElementNode_ChildList(tom_match1_1); if ( ( tom_terms_equal_String("options", tom_match1_1_Name) ||  false  ) ) { if ( ( tom_is_fun_sym_concTNode(tom_match1_1_AttrList) ||  false  ) ) { { tom.library.adt.tnode.types.TNodeList  tom_match1_1_AttrList_list1=tom_match1_1_AttrList; if ( ( tom_is_fun_sym_concTNode(tom_match1_1_ChildList) ||  false  ) ) { { tom.library.adt.tnode.types.TNodeList  tom_match1_1_ChildList_list1=tom_match1_1_ChildList; { tom.library.adt.tnode.types.TNodeList  tom_match1_1_ChildList_begin1=tom_match1_1_ChildList_list1; { tom.library.adt.tnode.types.TNodeList  tom_match1_1_ChildList_end1=tom_match1_1_ChildList_list1; { while (!(tom_is_empty_concTNode_TNodeList(tom_match1_1_ChildList_end1))) {tom_match1_1_ChildList_list1=tom_match1_1_ChildList_end1; { { tom.library.adt.tnode.types.TNode  tom_option=tom_get_head_concTNode_TNodeList(tom_match1_1_ChildList_list1);tom_match1_1_ChildList_list1=tom_get_tail_concTNode_TNodeList(tom_match1_1_ChildList_list1); if ( true ) { if(tom_option instanceof  tom.library.adt.tnode.types.TNode ) { { tom.library.adt.tnode.types.TNode  tom_match2_1=(( tom.library.adt.tnode.types.TNode )tom_option); if ( ( tom_is_fun_sym_ElementNode(tom_match2_1) ||  false  ) ) { { String  tom_match2_1_Name=tom_get_slot_ElementNode_Name(tom_match2_1); { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList=tom_get_slot_ElementNode_AttrList(tom_match2_1); { tom.library.adt.tnode.types.TNodeList  tom_match2_1_ChildList=tom_get_slot_ElementNode_ChildList(tom_match2_1); if ( ( tom_terms_equal_String("boolean", tom_match2_1_Name) ||  false  ) ) { if ( ( tom_is_fun_sym_concTNode(tom_match2_1_AttrList) ||  false  ) ) { { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_list1=tom_match2_1_AttrList; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_begin1=tom_match2_1_AttrList_list1; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_end1=tom_match2_1_AttrList_list1; { while (!(tom_is_empty_concTNode_TNodeList(tom_match2_1_AttrList_end1))) {tom_match2_1_AttrList_list1=tom_match2_1_AttrList_end1; { { tom.library.adt.tnode.types.TNode  tom_match2_1_AttrList_2=tom_get_head_concTNode_TNodeList(tom_match2_1_AttrList_list1);tom_match2_1_AttrList_list1=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_list1); if ( ( tom_is_fun_sym_AttributeNode(tom_match2_1_AttrList_2) ||  false  ) ) { { String  tom_match2_1_AttrList_2_Name=tom_get_slot_AttributeNode_Name(tom_match2_1_AttrList_2); { String  tom_match2_1_AttrList_2_Specified=tom_get_slot_AttributeNode_Specified(tom_match2_1_AttrList_2); { String  tom_match2_1_AttrList_2_Value=tom_get_slot_AttributeNode_Value(tom_match2_1_AttrList_2); if ( ( tom_terms_equal_String("altName", tom_match2_1_AttrList_2_Name) ||  false  ) ) { { String  tom_an=tom_match2_1_AttrList_2_Value; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_begin3=tom_match2_1_AttrList_list1; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_end3=tom_match2_1_AttrList_list1; { while (!(tom_is_empty_concTNode_TNodeList(tom_match2_1_AttrList_end3))) {tom_match2_1_AttrList_list1=tom_match2_1_AttrList_end3; { { tom.library.adt.tnode.types.TNode  tom_match2_1_AttrList_4=tom_get_head_concTNode_TNodeList(tom_match2_1_AttrList_list1);tom_match2_1_AttrList_list1=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_list1); if ( ( tom_is_fun_sym_AttributeNode(tom_match2_1_AttrList_4) ||  false  ) ) { { String  tom_match2_1_AttrList_4_Name=tom_get_slot_AttributeNode_Name(tom_match2_1_AttrList_4); { String  tom_match2_1_AttrList_4_Specified=tom_get_slot_AttributeNode_Specified(tom_match2_1_AttrList_4); { String  tom_match2_1_AttrList_4_Value=tom_get_slot_AttributeNode_Value(tom_match2_1_AttrList_4); if ( ( tom_terms_equal_String("description", tom_match2_1_AttrList_4_Name) ||  false  ) ) { { String  tom_d=tom_match2_1_AttrList_4_Value; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_begin5=tom_match2_1_AttrList_list1; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_end5=tom_match2_1_AttrList_list1; { while (!(tom_is_empty_concTNode_TNodeList(tom_match2_1_AttrList_end5))) {tom_match2_1_AttrList_list1=tom_match2_1_AttrList_end5; { { tom.library.adt.tnode.types.TNode  tom_match2_1_AttrList_6=tom_get_head_concTNode_TNodeList(tom_match2_1_AttrList_list1);tom_match2_1_AttrList_list1=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_list1); if ( ( tom_is_fun_sym_AttributeNode(tom_match2_1_AttrList_6) ||  false  ) ) { { String  tom_match2_1_AttrList_6_Name=tom_get_slot_AttributeNode_Name(tom_match2_1_AttrList_6); { String  tom_match2_1_AttrList_6_Specified=tom_get_slot_AttributeNode_Specified(tom_match2_1_AttrList_6); { String  tom_match2_1_AttrList_6_Value=tom_get_slot_AttributeNode_Value(tom_match2_1_AttrList_6); if ( ( tom_terms_equal_String("name", tom_match2_1_AttrList_6_Name) ||  false  ) ) { { String  tom_n=tom_match2_1_AttrList_6_Value; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_begin7=tom_match2_1_AttrList_list1; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_end7=tom_match2_1_AttrList_list1; { while (!(tom_is_empty_concTNode_TNodeList(tom_match2_1_AttrList_end7))) {tom_match2_1_AttrList_list1=tom_match2_1_AttrList_end7; { { tom.library.adt.tnode.types.TNode  tom_match2_1_AttrList_8=tom_get_head_concTNode_TNodeList(tom_match2_1_AttrList_list1);tom_match2_1_AttrList_list1=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_list1); if ( ( tom_is_fun_sym_AttributeNode(tom_match2_1_AttrList_8) ||  false  ) ) { { String  tom_match2_1_AttrList_8_Name=tom_get_slot_AttributeNode_Name(tom_match2_1_AttrList_8); { String  tom_match2_1_AttrList_8_Specified=tom_get_slot_AttributeNode_Specified(tom_match2_1_AttrList_8); { String  tom_match2_1_AttrList_8_Value=tom_get_slot_AttributeNode_Value(tom_match2_1_AttrList_8); if ( ( tom_terms_equal_String("value", tom_match2_1_AttrList_8_Name) ||  false  ) ) { { String  tom_v=tom_match2_1_AttrList_8_Value; if ( ( tom_is_fun_sym_concTNode(tom_match2_1_ChildList) ||  false  ) ) { { tom.library.adt.tnode.types.TNodeList  tom_match2_1_ChildList_list1=tom_match2_1_ChildList; if (tom_is_empty_concTNode_TNodeList(tom_match2_1_ChildList_list1)) { if ( true ) {


 
            PlatformBoolean bool = Boolean.valueOf(tom_v).booleanValue()?tom_make_True():tom_make_False();
            list = tom_append_list_concPlatformOption(list,tom_cons_list_concPlatformOption(tom_make_PluginOption(tom_n,tom_an,tom_d,tom_make_BooleanValue(bool),""),tom_empty_list_concPlatformOption())); 
           } } } } } } } } } } }tom_match2_1_AttrList_end7=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_end7); } }tom_match2_1_AttrList_list1=tom_match2_1_AttrList_begin7; } } } } } } } } } }tom_match2_1_AttrList_end5=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_end5); } }tom_match2_1_AttrList_list1=tom_match2_1_AttrList_begin5; } } } } } } } } } }tom_match2_1_AttrList_end3=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_end3); } }tom_match2_1_AttrList_list1=tom_match2_1_AttrList_begin3; } } } } } } } } } }tom_match2_1_AttrList_end1=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_end1); } }tom_match2_1_AttrList_list1=tom_match2_1_AttrList_begin1; } } } } } } } } } } if ( ( tom_is_fun_sym_ElementNode(tom_match2_1) ||  false  ) ) { { String  tom_match2_1_Name=tom_get_slot_ElementNode_Name(tom_match2_1); { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList=tom_get_slot_ElementNode_AttrList(tom_match2_1); { tom.library.adt.tnode.types.TNodeList  tom_match2_1_ChildList=tom_get_slot_ElementNode_ChildList(tom_match2_1); if ( ( tom_terms_equal_String("integer", tom_match2_1_Name) ||  false  ) ) { if ( ( tom_is_fun_sym_concTNode(tom_match2_1_AttrList) ||  false  ) ) { { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_list1=tom_match2_1_AttrList; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_begin1=tom_match2_1_AttrList_list1; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_end1=tom_match2_1_AttrList_list1; { while (!(tom_is_empty_concTNode_TNodeList(tom_match2_1_AttrList_end1))) {tom_match2_1_AttrList_list1=tom_match2_1_AttrList_end1; { { tom.library.adt.tnode.types.TNode  tom_match2_1_AttrList_2=tom_get_head_concTNode_TNodeList(tom_match2_1_AttrList_list1);tom_match2_1_AttrList_list1=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_list1); if ( ( tom_is_fun_sym_AttributeNode(tom_match2_1_AttrList_2) ||  false  ) ) { { String  tom_match2_1_AttrList_2_Name=tom_get_slot_AttributeNode_Name(tom_match2_1_AttrList_2); { String  tom_match2_1_AttrList_2_Specified=tom_get_slot_AttributeNode_Specified(tom_match2_1_AttrList_2); { String  tom_match2_1_AttrList_2_Value=tom_get_slot_AttributeNode_Value(tom_match2_1_AttrList_2); if ( ( tom_terms_equal_String("altName", tom_match2_1_AttrList_2_Name) ||  false  ) ) { { String  tom_an=tom_match2_1_AttrList_2_Value; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_begin3=tom_match2_1_AttrList_list1; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_end3=tom_match2_1_AttrList_list1; { while (!(tom_is_empty_concTNode_TNodeList(tom_match2_1_AttrList_end3))) {tom_match2_1_AttrList_list1=tom_match2_1_AttrList_end3; { { tom.library.adt.tnode.types.TNode  tom_match2_1_AttrList_4=tom_get_head_concTNode_TNodeList(tom_match2_1_AttrList_list1);tom_match2_1_AttrList_list1=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_list1); if ( ( tom_is_fun_sym_AttributeNode(tom_match2_1_AttrList_4) ||  false  ) ) { { String  tom_match2_1_AttrList_4_Name=tom_get_slot_AttributeNode_Name(tom_match2_1_AttrList_4); { String  tom_match2_1_AttrList_4_Specified=tom_get_slot_AttributeNode_Specified(tom_match2_1_AttrList_4); { String  tom_match2_1_AttrList_4_Value=tom_get_slot_AttributeNode_Value(tom_match2_1_AttrList_4); if ( ( tom_terms_equal_String("attrName", tom_match2_1_AttrList_4_Name) ||  false  ) ) { { String  tom_at=tom_match2_1_AttrList_4_Value; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_begin5=tom_match2_1_AttrList_list1; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_end5=tom_match2_1_AttrList_list1; { while (!(tom_is_empty_concTNode_TNodeList(tom_match2_1_AttrList_end5))) {tom_match2_1_AttrList_list1=tom_match2_1_AttrList_end5; { { tom.library.adt.tnode.types.TNode  tom_match2_1_AttrList_6=tom_get_head_concTNode_TNodeList(tom_match2_1_AttrList_list1);tom_match2_1_AttrList_list1=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_list1); if ( ( tom_is_fun_sym_AttributeNode(tom_match2_1_AttrList_6) ||  false  ) ) { { String  tom_match2_1_AttrList_6_Name=tom_get_slot_AttributeNode_Name(tom_match2_1_AttrList_6); { String  tom_match2_1_AttrList_6_Specified=tom_get_slot_AttributeNode_Specified(tom_match2_1_AttrList_6); { String  tom_match2_1_AttrList_6_Value=tom_get_slot_AttributeNode_Value(tom_match2_1_AttrList_6); if ( ( tom_terms_equal_String("description", tom_match2_1_AttrList_6_Name) ||  false  ) ) { { String  tom_d=tom_match2_1_AttrList_6_Value; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_begin7=tom_match2_1_AttrList_list1; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_end7=tom_match2_1_AttrList_list1; { while (!(tom_is_empty_concTNode_TNodeList(tom_match2_1_AttrList_end7))) {tom_match2_1_AttrList_list1=tom_match2_1_AttrList_end7; { { tom.library.adt.tnode.types.TNode  tom_match2_1_AttrList_8=tom_get_head_concTNode_TNodeList(tom_match2_1_AttrList_list1);tom_match2_1_AttrList_list1=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_list1); if ( ( tom_is_fun_sym_AttributeNode(tom_match2_1_AttrList_8) ||  false  ) ) { { String  tom_match2_1_AttrList_8_Name=tom_get_slot_AttributeNode_Name(tom_match2_1_AttrList_8); { String  tom_match2_1_AttrList_8_Specified=tom_get_slot_AttributeNode_Specified(tom_match2_1_AttrList_8); { String  tom_match2_1_AttrList_8_Value=tom_get_slot_AttributeNode_Value(tom_match2_1_AttrList_8); if ( ( tom_terms_equal_String("name", tom_match2_1_AttrList_8_Name) ||  false  ) ) { { String  tom_n=tom_match2_1_AttrList_8_Value; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_begin9=tom_match2_1_AttrList_list1; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_end9=tom_match2_1_AttrList_list1; { while (!(tom_is_empty_concTNode_TNodeList(tom_match2_1_AttrList_end9))) {tom_match2_1_AttrList_list1=tom_match2_1_AttrList_end9; { { tom.library.adt.tnode.types.TNode  tom_match2_1_AttrList_10=tom_get_head_concTNode_TNodeList(tom_match2_1_AttrList_list1);tom_match2_1_AttrList_list1=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_list1); if ( ( tom_is_fun_sym_AttributeNode(tom_match2_1_AttrList_10) ||  false  ) ) { { String  tom_match2_1_AttrList_10_Name=tom_get_slot_AttributeNode_Name(tom_match2_1_AttrList_10); { String  tom_match2_1_AttrList_10_Specified=tom_get_slot_AttributeNode_Specified(tom_match2_1_AttrList_10); { String  tom_match2_1_AttrList_10_Value=tom_get_slot_AttributeNode_Value(tom_match2_1_AttrList_10); if ( ( tom_terms_equal_String("value", tom_match2_1_AttrList_10_Name) ||  false  ) ) { { String  tom_v=tom_match2_1_AttrList_10_Value; if ( ( tom_is_fun_sym_concTNode(tom_match2_1_ChildList) ||  false  ) ) { { tom.library.adt.tnode.types.TNodeList  tom_match2_1_ChildList_list1=tom_match2_1_ChildList; if (tom_is_empty_concTNode_TNodeList(tom_match2_1_ChildList_list1)) { if ( true ) {

            list = tom_append_list_concPlatformOption(list,tom_cons_list_concPlatformOption(tom_make_PluginOption(tom_n,tom_an,tom_d,tom_make_IntegerValue(Integer.parseInt(tom_v)),tom_at),tom_empty_list_concPlatformOption()));
           } } } } } } } } } } }tom_match2_1_AttrList_end9=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_end9); } }tom_match2_1_AttrList_list1=tom_match2_1_AttrList_begin9; } } } } } } } } } }tom_match2_1_AttrList_end7=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_end7); } }tom_match2_1_AttrList_list1=tom_match2_1_AttrList_begin7; } } } } } } } } } }tom_match2_1_AttrList_end5=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_end5); } }tom_match2_1_AttrList_list1=tom_match2_1_AttrList_begin5; } } } } } } } } } }tom_match2_1_AttrList_end3=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_end3); } }tom_match2_1_AttrList_list1=tom_match2_1_AttrList_begin3; } } } } } } } } } }tom_match2_1_AttrList_end1=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_end1); } }tom_match2_1_AttrList_list1=tom_match2_1_AttrList_begin1; } } } } } } } } } } if ( ( tom_is_fun_sym_ElementNode(tom_match2_1) ||  false  ) ) { { String  tom_match2_1_Name=tom_get_slot_ElementNode_Name(tom_match2_1); { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList=tom_get_slot_ElementNode_AttrList(tom_match2_1); { tom.library.adt.tnode.types.TNodeList  tom_match2_1_ChildList=tom_get_slot_ElementNode_ChildList(tom_match2_1); if ( ( tom_terms_equal_String("string", tom_match2_1_Name) ||  false  ) ) { if ( ( tom_is_fun_sym_concTNode(tom_match2_1_AttrList) ||  false  ) ) { { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_list1=tom_match2_1_AttrList; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_begin1=tom_match2_1_AttrList_list1; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_end1=tom_match2_1_AttrList_list1; { while (!(tom_is_empty_concTNode_TNodeList(tom_match2_1_AttrList_end1))) {tom_match2_1_AttrList_list1=tom_match2_1_AttrList_end1; { { tom.library.adt.tnode.types.TNode  tom_match2_1_AttrList_2=tom_get_head_concTNode_TNodeList(tom_match2_1_AttrList_list1);tom_match2_1_AttrList_list1=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_list1); if ( ( tom_is_fun_sym_AttributeNode(tom_match2_1_AttrList_2) ||  false  ) ) { { String  tom_match2_1_AttrList_2_Name=tom_get_slot_AttributeNode_Name(tom_match2_1_AttrList_2); { String  tom_match2_1_AttrList_2_Specified=tom_get_slot_AttributeNode_Specified(tom_match2_1_AttrList_2); { String  tom_match2_1_AttrList_2_Value=tom_get_slot_AttributeNode_Value(tom_match2_1_AttrList_2); if ( ( tom_terms_equal_String("altName", tom_match2_1_AttrList_2_Name) ||  false  ) ) { { String  tom_an=tom_match2_1_AttrList_2_Value; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_begin3=tom_match2_1_AttrList_list1; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_end3=tom_match2_1_AttrList_list1; { while (!(tom_is_empty_concTNode_TNodeList(tom_match2_1_AttrList_end3))) {tom_match2_1_AttrList_list1=tom_match2_1_AttrList_end3; { { tom.library.adt.tnode.types.TNode  tom_match2_1_AttrList_4=tom_get_head_concTNode_TNodeList(tom_match2_1_AttrList_list1);tom_match2_1_AttrList_list1=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_list1); if ( ( tom_is_fun_sym_AttributeNode(tom_match2_1_AttrList_4) ||  false  ) ) { { String  tom_match2_1_AttrList_4_Name=tom_get_slot_AttributeNode_Name(tom_match2_1_AttrList_4); { String  tom_match2_1_AttrList_4_Specified=tom_get_slot_AttributeNode_Specified(tom_match2_1_AttrList_4); { String  tom_match2_1_AttrList_4_Value=tom_get_slot_AttributeNode_Value(tom_match2_1_AttrList_4); if ( ( tom_terms_equal_String("attrName", tom_match2_1_AttrList_4_Name) ||  false  ) ) { { String  tom_at=tom_match2_1_AttrList_4_Value; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_begin5=tom_match2_1_AttrList_list1; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_end5=tom_match2_1_AttrList_list1; { while (!(tom_is_empty_concTNode_TNodeList(tom_match2_1_AttrList_end5))) {tom_match2_1_AttrList_list1=tom_match2_1_AttrList_end5; { { tom.library.adt.tnode.types.TNode  tom_match2_1_AttrList_6=tom_get_head_concTNode_TNodeList(tom_match2_1_AttrList_list1);tom_match2_1_AttrList_list1=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_list1); if ( ( tom_is_fun_sym_AttributeNode(tom_match2_1_AttrList_6) ||  false  ) ) { { String  tom_match2_1_AttrList_6_Name=tom_get_slot_AttributeNode_Name(tom_match2_1_AttrList_6); { String  tom_match2_1_AttrList_6_Specified=tom_get_slot_AttributeNode_Specified(tom_match2_1_AttrList_6); { String  tom_match2_1_AttrList_6_Value=tom_get_slot_AttributeNode_Value(tom_match2_1_AttrList_6); if ( ( tom_terms_equal_String("description", tom_match2_1_AttrList_6_Name) ||  false  ) ) { { String  tom_d=tom_match2_1_AttrList_6_Value; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_begin7=tom_match2_1_AttrList_list1; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_end7=tom_match2_1_AttrList_list1; { while (!(tom_is_empty_concTNode_TNodeList(tom_match2_1_AttrList_end7))) {tom_match2_1_AttrList_list1=tom_match2_1_AttrList_end7; { { tom.library.adt.tnode.types.TNode  tom_match2_1_AttrList_8=tom_get_head_concTNode_TNodeList(tom_match2_1_AttrList_list1);tom_match2_1_AttrList_list1=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_list1); if ( ( tom_is_fun_sym_AttributeNode(tom_match2_1_AttrList_8) ||  false  ) ) { { String  tom_match2_1_AttrList_8_Name=tom_get_slot_AttributeNode_Name(tom_match2_1_AttrList_8); { String  tom_match2_1_AttrList_8_Specified=tom_get_slot_AttributeNode_Specified(tom_match2_1_AttrList_8); { String  tom_match2_1_AttrList_8_Value=tom_get_slot_AttributeNode_Value(tom_match2_1_AttrList_8); if ( ( tom_terms_equal_String("name", tom_match2_1_AttrList_8_Name) ||  false  ) ) { { String  tom_n=tom_match2_1_AttrList_8_Value; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_begin9=tom_match2_1_AttrList_list1; { tom.library.adt.tnode.types.TNodeList  tom_match2_1_AttrList_end9=tom_match2_1_AttrList_list1; { while (!(tom_is_empty_concTNode_TNodeList(tom_match2_1_AttrList_end9))) {tom_match2_1_AttrList_list1=tom_match2_1_AttrList_end9; { { tom.library.adt.tnode.types.TNode  tom_match2_1_AttrList_10=tom_get_head_concTNode_TNodeList(tom_match2_1_AttrList_list1);tom_match2_1_AttrList_list1=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_list1); if ( ( tom_is_fun_sym_AttributeNode(tom_match2_1_AttrList_10) ||  false  ) ) { { String  tom_match2_1_AttrList_10_Name=tom_get_slot_AttributeNode_Name(tom_match2_1_AttrList_10); { String  tom_match2_1_AttrList_10_Specified=tom_get_slot_AttributeNode_Specified(tom_match2_1_AttrList_10); { String  tom_match2_1_AttrList_10_Value=tom_get_slot_AttributeNode_Value(tom_match2_1_AttrList_10); if ( ( tom_terms_equal_String("value", tom_match2_1_AttrList_10_Name) ||  false  ) ) { { String  tom_v=tom_match2_1_AttrList_10_Value; if ( ( tom_is_fun_sym_concTNode(tom_match2_1_ChildList) ||  false  ) ) { { tom.library.adt.tnode.types.TNodeList  tom_match2_1_ChildList_list1=tom_match2_1_ChildList; if (tom_is_empty_concTNode_TNodeList(tom_match2_1_ChildList_list1)) { if ( true ) {

            list = tom_append_list_concPlatformOption(list,tom_cons_list_concPlatformOption(tom_make_PluginOption(tom_n,tom_an,tom_d,tom_make_StringValue(tom_v),tom_at),tom_empty_list_concPlatformOption()));
           } } } } } } } } } } }tom_match2_1_AttrList_end9=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_end9); } }tom_match2_1_AttrList_list1=tom_match2_1_AttrList_begin9; } } } } } } } } } }tom_match2_1_AttrList_end7=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_end7); } }tom_match2_1_AttrList_list1=tom_match2_1_AttrList_begin7; } } } } } } } } } }tom_match2_1_AttrList_end5=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_end5); } }tom_match2_1_AttrList_list1=tom_match2_1_AttrList_begin5; } } } } } } } } } }tom_match2_1_AttrList_end3=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_end3); } }tom_match2_1_AttrList_list1=tom_match2_1_AttrList_begin3; } } } } } } } } } }tom_match2_1_AttrList_end1=tom_get_tail_concTNode_TNodeList(tom_match2_1_AttrList_end1); } }tom_match2_1_AttrList_list1=tom_match2_1_AttrList_begin1; } } } } } } } } } } } }

       } }tom_match1_1_ChildList_end1=tom_get_tail_concTNode_TNodeList(tom_match1_1_ChildList_end1); } }tom_match1_1_ChildList_list1=tom_match1_1_ChildList_begin1; } } } } } } } } } } } } } }

    return list;
  }

}
