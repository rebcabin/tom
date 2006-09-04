/* Generated by TOM (version 2.4alpha): Do not edit this file *//*
 * Gom
 *
 * Copyright (C) 2006 INRIA
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
 * Antoine Reilles  e-mail: Antoine.Reilles@loria.fr
 *
 **/

package tom.gom.backend.strategy;

import tom.gom.GomStreamManager;
import tom.gom.tools.GomEnvironment;
import tom.gom.backend.TemplateClass;
import java.io.*;
import tom.gom.adt.objects.types.*;

public class StratMappingTemplate extends TemplateClass {
  GomClassList operatorClasses;

  /* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file */   /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ private static boolean tom_terms_equal_SlotFieldList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_GomClass(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ClassName(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_GomClassList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_HookList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_fun_sym_OperatorClass( tom.gom.adt.objects.types.GomClass  t) {  return  (t!=null) && t.isOperatorClass()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_className( tom.gom.adt.objects.types.GomClass  t) {  return  t.getclassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_abstractType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getabstractType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_extendsType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getextendsType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_mapping( tom.gom.adt.objects.types.GomClass  t) {  return  t.getmapping()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_sortName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getsortName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_visitor( tom.gom.adt.objects.types.GomClass  t) {  return  t.getvisitor()  ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_get_slot_OperatorClass_slots( tom.gom.adt.objects.types.GomClass  t) {  return  t.getslots()  ;}private static  tom.gom.adt.objects.types.HookList  tom_get_slot_OperatorClass_hooks( tom.gom.adt.objects.types.GomClass  t) {  return  t.gethooks()  ;}private static boolean tom_is_fun_sym_VariadicOperatorClass( tom.gom.adt.objects.types.GomClass  t) {  return  (t!=null) && t.isVariadicOperatorClass()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_className( tom.gom.adt.objects.types.GomClass  t) {  return  t.getclassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_abstractType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getabstractType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_sortName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getsortName()  ;}private static  tom.gom.adt.objects.types.GomClass  tom_get_slot_VariadicOperatorClass_empty( tom.gom.adt.objects.types.GomClass  t) {  return  t.getempty()  ;}private static  tom.gom.adt.objects.types.GomClass  tom_get_slot_VariadicOperatorClass_cons( tom.gom.adt.objects.types.GomClass  t) {  return  t.getcons()  ;}private static boolean tom_is_fun_sym_concGomClass( tom.gom.adt.objects.types.GomClassList  t) {  return  t instanceof tom.gom.adt.objects.types.gomclasslist.ConsconcGomClass || t instanceof tom.gom.adt.objects.types.gomclasslist.EmptyconcGomClass  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_empty_list_concGomClass() { return  tom.gom.adt.objects.types.gomclasslist.EmptyconcGomClass.make() ; }private static  tom.gom.adt.objects.types.GomClassList  tom_cons_list_concGomClass( tom.gom.adt.objects.types.GomClass  e,  tom.gom.adt.objects.types.GomClassList  l) { return  tom.gom.adt.objects.types.gomclasslist.ConsconcGomClass.make(e,l) ; }private static  tom.gom.adt.objects.types.GomClass  tom_get_head_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) {  return  l.getHeadconcGomClass()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_tail_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) {  return  l.getTailconcGomClass()  ;}private static boolean tom_is_empty_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) {  return  l.isEmptyconcGomClass()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_append_list_concGomClass( tom.gom.adt.objects.types.GomClassList  l1,  tom.gom.adt.objects.types.GomClassList  l2) {    if(tom_is_empty_concGomClass_GomClassList(l1)) {     return l2;    } else if(tom_is_empty_concGomClass_GomClassList(l2)) {     return l1;    } else if(tom_is_empty_concGomClass_GomClassList(( tom.gom.adt.objects.types.GomClassList )tom_get_tail_concGomClass_GomClassList(l1))) {     return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(( tom.gom.adt.objects.types.GomClass )tom_get_head_concGomClass_GomClassList(l1),l2);    } else {      return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(( tom.gom.adt.objects.types.GomClass )tom_get_head_concGomClass_GomClassList(l1),tom_append_list_concGomClass(( tom.gom.adt.objects.types.GomClassList )tom_get_tail_concGomClass_GomClassList(l1),l2));    }   }  private static  tom.gom.adt.objects.types.GomClassList  tom_get_slice_concGomClass( tom.gom.adt.objects.types.GomClassList  begin,  tom.gom.adt.objects.types.GomClassList  end) {    if(tom_terms_equal_GomClassList(begin,end)) {      return ( tom.gom.adt.objects.types.GomClassList )tom_empty_list_concGomClass();    } else {      return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(( tom.gom.adt.objects.types.GomClass )tom_get_head_concGomClass_GomClassList(begin),( tom.gom.adt.objects.types.GomClassList )tom_get_slice_concGomClass(( tom.gom.adt.objects.types.GomClassList )tom_get_tail_concGomClass_GomClassList(begin),end));    }   }   

  public StratMappingTemplate(ClassName className, GomClassList operatorClasses) {
    super(className);
    this.operatorClasses = operatorClasses;
  }

  public String generate() {
    String out = "";
    out += "\n%include { mustrategy.tom }\n"

;
    /* XXX: i could introduce an interface providing generateMapping() */
     if(operatorClasses instanceof  tom.gom.adt.objects.types.GomClassList ) { { tom.gom.adt.objects.types.GomClassList  tom_match1_1=(( tom.gom.adt.objects.types.GomClassList )operatorClasses); if ( ( tom_is_fun_sym_concGomClass(tom_match1_1) ||  false  ) ) { { tom.gom.adt.objects.types.GomClassList  tom_match1_1_list1=tom_match1_1; { tom.gom.adt.objects.types.GomClassList  tom_match1_1_begin1=tom_match1_1_list1; { tom.gom.adt.objects.types.GomClassList  tom_match1_1_end1=tom_match1_1_list1; { while (!(tom_is_empty_concGomClass_GomClassList(tom_match1_1_end1))) {tom_match1_1_list1=tom_match1_1_end1; { { tom.gom.adt.objects.types.GomClass  tom_match1_1_2=tom_get_head_concGomClass_GomClassList(tom_match1_1_list1);tom_match1_1_list1=tom_get_tail_concGomClass_GomClassList(tom_match1_1_list1); if ( ( tom_is_fun_sym_OperatorClass(tom_match1_1_2) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match1_1_2_className=tom_get_slot_OperatorClass_className(tom_match1_1_2); { tom.gom.adt.objects.types.SlotFieldList  tom_match1_1_2_slots=tom_get_slot_OperatorClass_slots(tom_match1_1_2); { tom.gom.adt.objects.types.ClassName  tom_opName=tom_match1_1_2_className; { tom.gom.adt.objects.types.SlotFieldList  tom_slotList=tom_match1_1_2_slots; if ( true ) {




      out +=
        (new tom.gom.backend.strategy.IsOpTemplate(tom_opName)).generateMapping();

      out +=
        (new tom.gom.backend.strategy.SOpTemplate(tom_opName,tom_slotList)).generateMapping();
      out +=
        (new tom.gom.backend.strategy.MakeOpTemplate(tom_opName,tom_slotList)).generateMapping();
       } } } } } } }tom_match1_1_end1=tom_get_tail_concGomClass_GomClassList(tom_match1_1_end1); } }tom_match1_1_list1=tom_match1_1_begin1; } } } } } if ( ( tom_is_fun_sym_concGomClass(tom_match1_1) ||  false  ) ) { { tom.gom.adt.objects.types.GomClassList  tom_match1_1_list1=tom_match1_1; { tom.gom.adt.objects.types.GomClassList  tom_match1_1_begin1=tom_match1_1_list1; { tom.gom.adt.objects.types.GomClassList  tom_match1_1_end1=tom_match1_1_list1; { while (!(tom_is_empty_concGomClass_GomClassList(tom_match1_1_end1))) {tom_match1_1_list1=tom_match1_1_end1; { { tom.gom.adt.objects.types.GomClass  tom_match1_1_2=tom_get_head_concGomClass_GomClassList(tom_match1_1_list1);tom_match1_1_list1=tom_get_tail_concGomClass_GomClassList(tom_match1_1_list1); if ( ( tom_is_fun_sym_VariadicOperatorClass(tom_match1_1_2) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match1_1_2_className=tom_get_slot_VariadicOperatorClass_className(tom_match1_1_2); { tom.gom.adt.objects.types.GomClass  tom_match1_1_2_empty=tom_get_slot_VariadicOperatorClass_empty(tom_match1_1_2); { tom.gom.adt.objects.types.GomClass  tom_match1_1_2_cons=tom_get_slot_VariadicOperatorClass_cons(tom_match1_1_2); { tom.gom.adt.objects.types.ClassName  tom_vopName=tom_match1_1_2_className; if ( ( tom_is_fun_sym_OperatorClass(tom_match1_1_2_empty) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match1_1_2_empty_className=tom_get_slot_OperatorClass_className(tom_match1_1_2_empty); { tom.gom.adt.objects.types.ClassName  tom_empty=tom_match1_1_2_empty_className; if ( ( tom_is_fun_sym_OperatorClass(tom_match1_1_2_cons) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match1_1_2_cons_className=tom_get_slot_OperatorClass_className(tom_match1_1_2_cons); { tom.gom.adt.objects.types.ClassName  tom_cons=tom_match1_1_2_cons_className; if ( true ) {





        out += "\n%op Strategy _"/* Generated by TOM (version 2.4alpha): Do not edit this file */+className(tom_vopName)+"(sub:Strategy) {\n  is_fsym(t) { false }\n  make(sub)  { `mu(MuVar(\"x\"),Choice(_"/* Generated by TOM (version 2.4alpha): Do not edit this file */+className(tom_cons)+"(sub,MuVar(\"x\")),_"/* Generated by TOM (version 2.4alpha): Do not edit this file */+className(tom_empty)+"())) }\n}\n"




;
       } } } } } } } } } } } } }tom_match1_1_end1=tom_get_tail_concGomClass_GomClassList(tom_match1_1_end1); } }tom_match1_1_list1=tom_match1_1_begin1; } } } } } } }

    return out;
  }

  protected String fileName() {
    return fullClassName().replace('.',File.separatorChar)+".tom";
  }

}
