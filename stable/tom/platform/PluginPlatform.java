/* Generated by TOM (version 2.3rc3): Do not edit this file *//*
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

import java.util.*;
import java.util.logging.*;
import aterm.*;
import aterm.pure.*;
import tom.library.adt.tnode.*;

/**
 * The PluginPlatform manages plugins defined in an xml configuration file.
 * (which plugins are used and how they are ordered) with the intermediate
 * of a ConfigurationManager objet
 * Its main role is to run the plugins in the specified order and make some 
 * error management.
 *
 */
public class PluginPlatform {

  /** Used to analyse xml configuration file */
  /* Generated by TOM (version 2.3rc3): Do not edit this file *//* Generated by TOM (version 2.3rc3): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  /* Generated by TOM (version 2.3rc3): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  private static  char  tom_set_stamp_char( char  c) {  return  c  ;}private static void tom_check_stamp_char( char  c) { ;}private static boolean tom_terms_equal_char( char  t1,  char  t2) {  return  (t1==t2)  ;}private static  Character  tom_set_stamp_Character( Character  c) {  return  c  ;}private static void tom_check_stamp_Character( Character  c) { ;}private static boolean tom_terms_equal_Character(Object t1, Object t2) {  return  (t1.equals(t2))  ;}private static boolean tom_is_fun_sym_Char( Character  t) {  return  (t!= null) && (t instanceof Character)  ;}private static  Character  tom_make_Char( char  c) { return  new Character(c) ; }private static  char  tom_get_slot_Char_c( Character  t) {  return  t.charValue()  ;} private static  String  tom_set_stamp_String( String  s) {  return  s  ;}private static void tom_check_stamp_String( String  s) { ;}private static boolean tom_terms_equal_String( String  t1,  String  t2) {  return  (t1.equals(t2))  ;}private static boolean tom_is_fun_sym_concString( String  t) {  return  (t!= null) && (t instanceof String)  ;}private static  String  tom_empty_list_concString() { return  "" ; }private static  String  tom_cons_list_concString( char  c,  String  s) { return  (c+s) ; }private static  char  tom_get_head_concString_String( String  s) {  return  s.charAt(0)  ;}private static  String  tom_get_tail_concString_String( String  s) {  return  s.substring(1)  ;}private static boolean tom_is_empty_concString_String( String  s) {  return  (s.length()==0)  ;}private static  String  tom_append_list_concString( String  l1,  String  l2) {    if(tom_is_empty_concString_String(l1)) {     return l2;    } else if(tom_is_empty_concString_String(l2)) {     return l1;    } else if(tom_is_empty_concString_String(( String )tom_get_tail_concString_String(l1))) {     return ( String )tom_cons_list_concString(( char )tom_get_head_concString_String(l1),l2);    } else {      return ( String )tom_cons_list_concString(( char )tom_get_head_concString_String(l1),tom_append_list_concString(( String )tom_get_tail_concString_String(l1),l2));    }   }  private static  String  tom_get_slice_concString( String  begin,  String  end) {    if(tom_terms_equal_String(begin,end)) {      return ( String )tom_empty_list_concString();    } else {      return ( String )tom_cons_list_concString(( char )tom_get_head_concString_String(begin),( String )tom_get_slice_concString(( String )tom_get_tail_concString_String(begin),end));    }   }    /* Generated by TOM (version 2.3rc3): Do not edit this file *//*  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  */ private static boolean tom_terms_equal_int( int  t1,  int  t2) {  return  (t1==t2)  ;} /* Generated by TOM (version 2.3rc3): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  private static boolean tom_terms_equal_double( double  t1,  double  t2) {  return  (t1==t2)  ;} /* Generated by TOM (version 2.3rc3): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  private static boolean tom_terms_equal_ATerm(Object t1, Object t2) {  return  t1 == t2 ;} /* Generated by TOM (version 2.3rc3): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  private static boolean tom_terms_equal_ATermList(Object l1, Object l2) {  return  l1==l2  ;} private static  tom.library.adt.tnode.types.TNodeList tom_get_implementation_TNodeList( tom.library.adt.tnode.types.TNodeList t) {  return t ;}private static  tom.library.adt.tnode.types.TNodeList tom_set_stamp_TNodeList( tom.library.adt.tnode.types.TNodeList t) {  return (tom.library.adt.tnode.types.TNodeList)t.setAnnotation(aterm.pure.SingletonFactory.getInstance().makeList(),aterm.pure.SingletonFactory.getInstance().makeList()) ;}private static void tom_check_stamp_TNodeList( tom.library.adt.tnode.types.TNodeList t) { if(t.getAnnotation(aterm.pure.SingletonFactory.getInstance().makeList()) == aterm.pure.SingletonFactory.getInstance().makeList())  return; else throw new RuntimeException("bad stamp");}private static boolean tom_terms_equal_TNodeList(Object t1, Object t2) {  return t1.equals(t2) ;}private static boolean tom_is_fun_sym_concTNode( tom.library.adt.tnode.types.TNodeList t) {  return (t!= null) && t.isSortTNodeList() ;}private static  tom.library.adt.tnode.types.TNodeList tom_empty_list_concTNode() { return tom.library.adt.tnode.TNodeFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeTNodeList(); }private static  tom.library.adt.tnode.types.TNodeList tom_cons_list_concTNode( tom.library.adt.tnode.types.TNode e,  tom.library.adt.tnode.types.TNodeList l) { return tom.library.adt.tnode.TNodeFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeTNodeList(e,l); }private static  tom.library.adt.tnode.types.TNode tom_get_head_concTNode_TNodeList( tom.library.adt.tnode.types.TNodeList l) {  return l.getHead() ;}private static  tom.library.adt.tnode.types.TNodeList tom_get_tail_concTNode_TNodeList( tom.library.adt.tnode.types.TNodeList l) {  return l.getTail() ;}private static boolean tom_is_empty_concTNode_TNodeList( tom.library.adt.tnode.types.TNodeList l) {  return l.isEmpty() ;}private static  tom.library.adt.tnode.types.TNodeList tom_append_list_concTNode( tom.library.adt.tnode.types.TNodeList l1,  tom.library.adt.tnode.types.TNodeList l2) {    if(tom_is_empty_concTNode_TNodeList(l1)) {     return l2;    } else if(tom_is_empty_concTNode_TNodeList(l2)) {     return l1;    } else if(tom_is_empty_concTNode_TNodeList(( tom.library.adt.tnode.types.TNodeList)tom_get_tail_concTNode_TNodeList(l1))) {     return ( tom.library.adt.tnode.types.TNodeList)tom_cons_list_concTNode(( tom.library.adt.tnode.types.TNode)tom_get_head_concTNode_TNodeList(l1),l2);    } else {      return ( tom.library.adt.tnode.types.TNodeList)tom_cons_list_concTNode(( tom.library.adt.tnode.types.TNode)tom_get_head_concTNode_TNodeList(l1),tom_append_list_concTNode(( tom.library.adt.tnode.types.TNodeList)tom_get_tail_concTNode_TNodeList(l1),l2));    }   }  private static  tom.library.adt.tnode.types.TNodeList tom_get_slice_concTNode( tom.library.adt.tnode.types.TNodeList begin,  tom.library.adt.tnode.types.TNodeList end) {    if(tom_terms_equal_TNodeList(begin,end)) {      return ( tom.library.adt.tnode.types.TNodeList)tom_empty_list_concTNode();    } else {      return ( tom.library.adt.tnode.types.TNodeList)tom_cons_list_concTNode(( tom.library.adt.tnode.types.TNode)tom_get_head_concTNode_TNodeList(begin),( tom.library.adt.tnode.types.TNodeList)tom_get_slice_concTNode(( tom.library.adt.tnode.types.TNodeList)tom_get_tail_concTNode_TNodeList(begin),end));    }   }  private static boolean tom_is_fun_sym_emptyTNodeList( tom.library.adt.tnode.types.TNodeList t) {  return  (t!= null) && t.isEmpty() ;}private static  tom.library.adt.tnode.types.TNodeList tom_make_emptyTNodeList() { return tom.library.adt.tnode.TNodeFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeTNodeList(); }private static boolean tom_is_fun_sym_manyTNodeList( tom.library.adt.tnode.types.TNodeList t) {  return  (t!= null) && t.isMany() ;}private static  tom.library.adt.tnode.types.TNodeList tom_make_manyTNodeList( tom.library.adt.tnode.types.TNode e,  tom.library.adt.tnode.types.TNodeList l) { return tom.library.adt.tnode.TNodeFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeTNodeList(e,l); }private static  tom.library.adt.tnode.types.TNode tom_get_slot_manyTNodeList_head( tom.library.adt.tnode.types.TNodeList t) {  return  t.getHead() ;}private static  tom.library.adt.tnode.types.TNodeList tom_get_slot_manyTNodeList_tail( tom.library.adt.tnode.types.TNodeList t) {  return  t.getTail() ;}private static  tom.library.adt.tnode.types.TNode tom_get_implementation_TNode( tom.library.adt.tnode.types.TNode t) {  return t ;}private static  tom.library.adt.tnode.types.TNode tom_set_stamp_TNode( tom.library.adt.tnode.types.TNode t) {  return (tom.library.adt.tnode.types.TNode)t.setAnnotation(aterm.pure.SingletonFactory.getInstance().makeList(),aterm.pure.SingletonFactory.getInstance().makeList()) ;}private static void tom_check_stamp_TNode( tom.library.adt.tnode.types.TNode t) { if(t.getAnnotation(aterm.pure.SingletonFactory.getInstance().makeList()) == aterm.pure.SingletonFactory.getInstance().makeList())  return; else throw new RuntimeException("bad stamp");}private static boolean tom_terms_equal_TNode(Object t1, Object t2) {  return t1.equals(t2) ;}private static boolean tom_is_fun_sym_NotationNode( tom.library.adt.tnode.types.TNode t) {  return  (t!= null) && t.isNotationNode() ;}private static  tom.library.adt.tnode.types.TNode tom_make_NotationNode( String  t0,  String  t1) { return  tom.library.adt.tnode.TNodeFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeTNode_NotationNode(t0, t1); }private static  String  tom_get_slot_NotationNode_publicId( tom.library.adt.tnode.types.TNode t) {  return  t.getPublicId() ;}private static  String  tom_get_slot_NotationNode_systemId( tom.library.adt.tnode.types.TNode t) {  return  t.getSystemId() ;}private static boolean tom_is_fun_sym_EntityNode( tom.library.adt.tnode.types.TNode t) {  return  (t!= null) && t.isEntityNode() ;}private static  tom.library.adt.tnode.types.TNode tom_make_EntityNode( String  t0,  String  t1,  String  t2) { return  tom.library.adt.tnode.TNodeFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeTNode_EntityNode(t0, t1, t2); }private static  String  tom_get_slot_EntityNode_notationName( tom.library.adt.tnode.types.TNode t) {  return  t.getNotationName() ;}private static  String  tom_get_slot_EntityNode_publicId( tom.library.adt.tnode.types.TNode t) {  return  t.getPublicId() ;}private static  String  tom_get_slot_EntityNode_systemId( tom.library.adt.tnode.types.TNode t) {  return  t.getSystemId() ;}private static boolean tom_is_fun_sym_EntityReferenceNode( tom.library.adt.tnode.types.TNode t) {  return  (t!= null) && t.isEntityReferenceNode() ;}private static  tom.library.adt.tnode.types.TNode tom_make_EntityReferenceNode( String  t0,  tom.library.adt.tnode.types.TNodeList t1) { return  tom.library.adt.tnode.TNodeFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeTNode_EntityReferenceNode(t0, t1); }private static  String  tom_get_slot_EntityReferenceNode_name( tom.library.adt.tnode.types.TNode t) {  return  t.getName() ;}private static  tom.library.adt.tnode.types.TNodeList tom_get_slot_EntityReferenceNode_childList( tom.library.adt.tnode.types.TNode t) {  return  t.getChildList() ;}private static boolean tom_is_fun_sym_DocumentTypeNode( tom.library.adt.tnode.types.TNode t) {  return  (t!= null) && t.isDocumentTypeNode() ;}private static  tom.library.adt.tnode.types.TNode tom_make_DocumentTypeNode( String  t0,  String  t1,  String  t2,  String  t3,  tom.library.adt.tnode.types.TNodeList t4,  tom.library.adt.tnode.types.TNodeList t5) { return  tom.library.adt.tnode.TNodeFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeTNode_DocumentTypeNode(t0, t1, t2, t3, t4, t5); }private static  String  tom_get_slot_DocumentTypeNode_name( tom.library.adt.tnode.types.TNode t) {  return  t.getName() ;}private static  String  tom_get_slot_DocumentTypeNode_publicId( tom.library.adt.tnode.types.TNode t) {  return  t.getPublicId() ;}private static  String  tom_get_slot_DocumentTypeNode_systemId( tom.library.adt.tnode.types.TNode t) {  return  t.getSystemId() ;}private static  String  tom_get_slot_DocumentTypeNode_internalSubset( tom.library.adt.tnode.types.TNode t) {  return  t.getInternalSubset() ;}private static  tom.library.adt.tnode.types.TNodeList tom_get_slot_DocumentTypeNode_entities( tom.library.adt.tnode.types.TNode t) {  return  t.getEntities() ;}private static  tom.library.adt.tnode.types.TNodeList tom_get_slot_DocumentTypeNode_notations( tom.library.adt.tnode.types.TNode t) {  return  t.getNotations() ;}private static boolean tom_is_fun_sym_AttributeNode( tom.library.adt.tnode.types.TNode t) {  return  (t!= null) && t.isAttributeNode() ;}private static  tom.library.adt.tnode.types.TNode tom_make_AttributeNode( String  t0,  String  t1,  String  t2) { return  tom.library.adt.tnode.TNodeFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeTNode_AttributeNode(t0, t1, t2); }private static  String  tom_get_slot_AttributeNode_name( tom.library.adt.tnode.types.TNode t) {  return  t.getName() ;}private static  String  tom_get_slot_AttributeNode_specified( tom.library.adt.tnode.types.TNode t) {  return  t.getSpecified() ;}private static  String  tom_get_slot_AttributeNode_value( tom.library.adt.tnode.types.TNode t) {  return  t.getValue() ;}private static boolean tom_is_fun_sym_ElementNode( tom.library.adt.tnode.types.TNode t) {  return  (t!= null) && t.isElementNode() ;}private static  tom.library.adt.tnode.types.TNode tom_make_ElementNode( String  t0,  tom.library.adt.tnode.types.TNodeList t1,  tom.library.adt.tnode.types.TNodeList t2) { return  tom.library.adt.tnode.TNodeFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeTNode_ElementNode(t0, t1, t2); }private static  String  tom_get_slot_ElementNode_name( tom.library.adt.tnode.types.TNode t) {  return  t.getName() ;}private static  tom.library.adt.tnode.types.TNodeList tom_get_slot_ElementNode_attrList( tom.library.adt.tnode.types.TNode t) {  return  t.getAttrList() ;}private static  tom.library.adt.tnode.types.TNodeList tom_get_slot_ElementNode_childList( tom.library.adt.tnode.types.TNode t) {  return  t.getChildList() ;}private static boolean tom_is_fun_sym_DocumentNode( tom.library.adt.tnode.types.TNode t) {  return  (t!= null) && t.isDocumentNode() ;}private static  tom.library.adt.tnode.types.TNode tom_make_DocumentNode( tom.library.adt.tnode.types.TNode t0,  tom.library.adt.tnode.types.TNode t1) { return  tom.library.adt.tnode.TNodeFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeTNode_DocumentNode(t0, t1); }private static  tom.library.adt.tnode.types.TNode tom_get_slot_DocumentNode_docType( tom.library.adt.tnode.types.TNode t) {  return  t.getDocType() ;}private static  tom.library.adt.tnode.types.TNode tom_get_slot_DocumentNode_docElem( tom.library.adt.tnode.types.TNode t) {  return  t.getDocElem() ;}private static boolean tom_is_fun_sym_CDATASectionNode( tom.library.adt.tnode.types.TNode t) {  return  (t!= null) && t.isCDATASectionNode() ;}private static  tom.library.adt.tnode.types.TNode tom_make_CDATASectionNode( String  t0) { return  tom.library.adt.tnode.TNodeFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeTNode_CDATASectionNode(t0); }private static  String  tom_get_slot_CDATASectionNode_data( tom.library.adt.tnode.types.TNode t) {  return  t.getData() ;}private static boolean tom_is_fun_sym_TextNode( tom.library.adt.tnode.types.TNode t) {  return  (t!= null) && t.isTextNode() ;}private static  tom.library.adt.tnode.types.TNode tom_make_TextNode( String  t0) { return  tom.library.adt.tnode.TNodeFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeTNode_TextNode(t0); }private static  String  tom_get_slot_TextNode_data( tom.library.adt.tnode.types.TNode t) {  return  t.getData() ;}private static boolean tom_is_fun_sym_ProcessingInstructionNode( tom.library.adt.tnode.types.TNode t) {  return  (t!= null) && t.isProcessingInstructionNode() ;}private static  tom.library.adt.tnode.types.TNode tom_make_ProcessingInstructionNode( String  t0,  String  t1) { return  tom.library.adt.tnode.TNodeFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeTNode_ProcessingInstructionNode(t0, t1); }private static  String  tom_get_slot_ProcessingInstructionNode_target( tom.library.adt.tnode.types.TNode t) {  return  t.getTarget() ;}private static  String  tom_get_slot_ProcessingInstructionNode_data( tom.library.adt.tnode.types.TNode t) {  return  t.getData() ;}private static boolean tom_is_fun_sym_CommentNode( tom.library.adt.tnode.types.TNode t) {  return  (t!= null) && t.isCommentNode() ;}private static  tom.library.adt.tnode.types.TNode tom_make_CommentNode( String  t0) { return  tom.library.adt.tnode.TNodeFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeTNode_CommentNode(t0); }private static  String  tom_get_slot_CommentNode_data( tom.library.adt.tnode.types.TNode t) {  return  t.getData() ;}private static boolean tom_is_fun_sym_EmptyNode( tom.library.adt.tnode.types.TNode t) {  return  (t!= null) && t.isEmptyNode() ;}private static  tom.library.adt.tnode.types.TNode tom_make_EmptyNode() { return  tom.library.adt.tnode.TNodeFactory.getInstance(aterm.pure.SingletonFactory.getInstance()).makeTNode_EmptyNode(); }  

  /** The List of reference to plugins. */
  private List pluginsList;
    
  /** The status handler */
  private StatusHandler statusHandler;

  /** The test handler */
  private TestHandler testHandler;

  /** List of input arg */
  private List inputToCompileList;

  /** List of generated object cleared before each run */
  private List lastGeneratedObjects;

  /** Radical of the logger */  
  private String loggerRadical;

  /** Class Pluginplatform constructor */
  public PluginPlatform(ConfigurationManager confManager, String loggerRadical) {
    statusHandler = new StatusHandler();
    this.loggerRadical = loggerRadical;
    Logger.getLogger(loggerRadical).addHandler(this.statusHandler);
    pluginsList = confManager.getPluginsList();
    inputToCompileList = confManager.getOptionManager().getInputToCompileList();
  }
  
  /**
   * The main method which runs the PluginPlatform.
   * 
   * @return an error code :
   * <ul>
   * <li>0 if no error was encountered</li>
   * <li>1 if something went wrong</li>
   * </ul>
   */
  public int run() {
    boolean globalSuccess = true;
    int globalNbOfErrors = 0;
    int globalNbOfWarnings = 0;
    // intialize run instances
    lastGeneratedObjects = new ArrayList();
    // for each input we call the sequence of plug-ins
    for(int i=0; i < inputToCompileList.size(); i++) {
      Object input = inputToCompileList.get(i);
      Object[] pluginArg = new Object[]{input};
      Object initArgument = input;
      boolean success = true;
      statusHandler.clear();
      
      if(this.testHandler!=null) Logger.getLogger(loggerRadical).removeHandler(this.testHandler);     
      if(input instanceof String && ((String)input).endsWith(".t")) {
        String inputWithoutSuffix = ((String)input).substring(0, ((String)input).length() - ".t".length());
        testHandler = new TestHandler(inputWithoutSuffix);
        if(!testHandler.hasError()){
          Logger.getLogger(loggerRadical).addHandler(this.testHandler);
          }
      }
      
      getLogger().log(Level.FINER, PluginPlatformMessage.nowCompiling.getMessage(), input);
      // runs the plugins
      Iterator it = pluginsList.iterator();
      while(it.hasNext()) {
        Plugin plugin = (Plugin)it.next();
        plugin.setArgs(pluginArg);
        if(statusHandler.hasError()) {
          getLogger().log(Level.SEVERE, PluginPlatformMessage.settingArgError.getMessage());
          success = false;
          globalSuccess = false;
          globalNbOfErrors += statusHandler.nbOfErrors();
          globalNbOfWarnings += statusHandler.nbOfWarnings();
          break;
        }
        plugin.run();
        if(statusHandler.hasError()) {
          getLogger().log(Level.SEVERE, PluginPlatformMessage.processingError.getMessage(),
                          new Object[]{plugin.getClass().getName(), initArgument});
          success = false;
          globalSuccess = false;
          globalNbOfErrors += statusHandler.nbOfErrors();
          globalNbOfWarnings += statusHandler.nbOfWarnings();
          break;
        }
        pluginArg = plugin.getArgs();
      }

      if(success) {
        // save the first element of last plugin getArg response
        // this shall correspond to a generated file name
        lastGeneratedObjects.add(pluginArg[0]);
        globalNbOfWarnings += statusHandler.nbOfWarnings();
      }
    }

    if(!globalSuccess) {
      // this is the highest possible level > will be printed no matter what 
      getLogger().log(Level.SEVERE, PluginPlatformMessage.runErrorMessage.getMessage(),
                      new Integer(globalNbOfErrors));
      return 1;
    } else if(globalNbOfWarnings>0) {
      getLogger().log(Level.INFO, PluginPlatformMessage.runWarningMessage.getMessage(),
                      new Integer(globalNbOfWarnings));
      return 0;
    }
    return 0;
  }

  /**
   * An accessor method
   * @return the status handler.
   */
  public StatusHandler getStatusHandler() {
    return statusHandler;
  }

  /**
   * An accessor method
   * @return the test handler.
   */
  public TestHandler getTestHandler() {
    return testHandler;
  }

  /** return the list of last generated objects */
  public List getLastGeneratedObjects() {
    return lastGeneratedObjects;
  }
  
  public RuntimeAlert getAlertForInput(String filePath) {
    return statusHandler.getAlertForInput(filePath);
  }

  /** logger accessor in case of logging needs*/
  private Logger getLogger() {
    return Logger.getLogger(getClass().getName());
  }

}
