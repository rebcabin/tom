/* Generated by TOM (version 2.4rc2): Do not edit this file *//*
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

package tom.gom.backend.shared;

import tom.gom.GomStreamManager;
import tom.gom.tools.GomEnvironment;
import tom.gom.backend.TemplateClass;
import java.io.*;
import tom.gom.adt.objects.types.*;

public class MappingTemplate extends TemplateClass {
  ClassName basicStrategy;
  GomClassList sortClasses;
  GomClassList operatorClasses;

  /* Generated by TOM (version 2.4rc2): Do not edit this file *//* Generated by TOM (version 2.4rc2): Do not edit this file *//* Generated by TOM (version 2.4rc2): Do not edit this file */ private static boolean tom_terms_equal_String(String t1, String t2) {  return  (t1.equals(t2))  ;}  private static boolean tom_terms_equal_ClassNameList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_HookList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ClassName(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_GomClass(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_GomClassList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_SlotField(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_SlotFieldList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_fun_sym_ClassName( tom.gom.adt.objects.types.ClassName  t) {  return  t instanceof tom.gom.adt.objects.types.classname.ClassName  ;}private static  String  tom_get_slot_ClassName_Pkg( tom.gom.adt.objects.types.ClassName  t) {  return  t.getPkg()  ;}private static  String  tom_get_slot_ClassName_Name( tom.gom.adt.objects.types.ClassName  t) {  return  t.getName()  ;}private static boolean tom_is_fun_sym_SortClass( tom.gom.adt.objects.types.GomClass  t) {  return  t instanceof tom.gom.adt.objects.types.gomclass.SortClass  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_ClassName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getClassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_AbstractType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getAbstractType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_Visitor( tom.gom.adt.objects.types.GomClass  t) {  return  t.getVisitor()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_Forward( tom.gom.adt.objects.types.GomClass  t) {  return  t.getForward()  ;}private static  tom.gom.adt.objects.types.ClassNameList  tom_get_slot_SortClass_Operators( tom.gom.adt.objects.types.GomClass  t) {  return  t.getOperators()  ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_get_slot_SortClass_Slots( tom.gom.adt.objects.types.GomClass  t) {  return  t.getSlots()  ;}private static  tom.gom.adt.objects.types.HookList  tom_get_slot_SortClass_Hooks( tom.gom.adt.objects.types.GomClass  t) {  return  t.getHooks()  ;}private static boolean tom_is_fun_sym_OperatorClass( tom.gom.adt.objects.types.GomClass  t) {  return  t instanceof tom.gom.adt.objects.types.gomclass.OperatorClass  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_ClassName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getClassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_AbstractType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getAbstractType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_ExtendsType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getExtendsType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_Mapping( tom.gom.adt.objects.types.GomClass  t) {  return  t.getMapping()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_SortName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getSortName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_Visitor( tom.gom.adt.objects.types.GomClass  t) {  return  t.getVisitor()  ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_get_slot_OperatorClass_Slots( tom.gom.adt.objects.types.GomClass  t) {  return  t.getSlots()  ;}private static  tom.gom.adt.objects.types.HookList  tom_get_slot_OperatorClass_Hooks( tom.gom.adt.objects.types.GomClass  t) {  return  t.getHooks()  ;}private static boolean tom_is_fun_sym_VariadicOperatorClass( tom.gom.adt.objects.types.GomClass  t) {  return  t instanceof tom.gom.adt.objects.types.gomclass.VariadicOperatorClass  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_ClassName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getClassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_AbstractType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getAbstractType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_SortName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getSortName()  ;}private static  tom.gom.adt.objects.types.GomClass  tom_get_slot_VariadicOperatorClass_Empty( tom.gom.adt.objects.types.GomClass  t) {  return  t.getEmpty()  ;}private static  tom.gom.adt.objects.types.GomClass  tom_get_slot_VariadicOperatorClass_Cons( tom.gom.adt.objects.types.GomClass  t) {  return  t.getCons()  ;}private static  tom.gom.adt.objects.types.HookList  tom_get_slot_VariadicOperatorClass_Hooks( tom.gom.adt.objects.types.GomClass  t) {  return  t.getHooks()  ;}private static boolean tom_is_fun_sym_SlotField( tom.gom.adt.objects.types.SlotField  t) {  return  t instanceof tom.gom.adt.objects.types.slotfield.SlotField  ;}private static  String  tom_get_slot_SlotField_Name( tom.gom.adt.objects.types.SlotField  t) {  return  t.getName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SlotField_Domain( tom.gom.adt.objects.types.SlotField  t) {  return  t.getDomain()  ;}private static boolean tom_is_fun_sym_concGomClass( tom.gom.adt.objects.types.GomClassList  t) {  return  t instanceof tom.gom.adt.objects.types.gomclasslist.ConsconcGomClass || t instanceof tom.gom.adt.objects.types.gomclasslist.EmptyconcGomClass  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_empty_list_concGomClass() { return  tom.gom.adt.objects.types.gomclasslist.EmptyconcGomClass.make() ; }private static  tom.gom.adt.objects.types.GomClassList  tom_cons_list_concGomClass( tom.gom.adt.objects.types.GomClass  e,  tom.gom.adt.objects.types.GomClassList  l) { return  tom.gom.adt.objects.types.gomclasslist.ConsconcGomClass.make(e,l) ; }private static  tom.gom.adt.objects.types.GomClass  tom_get_head_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) {  return  l.getHeadconcGomClass()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_tail_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) {  return  l.getTailconcGomClass()  ;}private static boolean tom_is_empty_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) {  return  l.isEmptyconcGomClass()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_append_list_concGomClass( tom.gom.adt.objects.types.GomClassList  l1,  tom.gom.adt.objects.types.GomClassList  l2) {    if(tom_is_empty_concGomClass_GomClassList(l1)) {     return l2;    } else if(tom_is_empty_concGomClass_GomClassList(l2)) {     return l1;    } else if(tom_is_empty_concGomClass_GomClassList(( tom.gom.adt.objects.types.GomClassList )tom_get_tail_concGomClass_GomClassList(l1))) {     return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(( tom.gom.adt.objects.types.GomClass )tom_get_head_concGomClass_GomClassList(l1),l2);    } else {      return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(( tom.gom.adt.objects.types.GomClass )tom_get_head_concGomClass_GomClassList(l1),tom_append_list_concGomClass(( tom.gom.adt.objects.types.GomClassList )tom_get_tail_concGomClass_GomClassList(l1),l2));    }   }  private static  tom.gom.adt.objects.types.GomClassList  tom_get_slice_concGomClass( tom.gom.adt.objects.types.GomClassList  begin,  tom.gom.adt.objects.types.GomClassList  end) {    if(tom_terms_equal_GomClassList(begin,end)) {      return ( tom.gom.adt.objects.types.GomClassList )tom_empty_list_concGomClass();    } else {      return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(( tom.gom.adt.objects.types.GomClass )tom_get_head_concGomClass_GomClassList(begin),( tom.gom.adt.objects.types.GomClassList )tom_get_slice_concGomClass(( tom.gom.adt.objects.types.GomClassList )tom_get_tail_concGomClass_GomClassList(begin),end));    }   }  private static boolean tom_is_fun_sym_concSlotField( tom.gom.adt.objects.types.SlotFieldList  t) {  return  t instanceof tom.gom.adt.objects.types.slotfieldlist.ConsconcSlotField || t instanceof tom.gom.adt.objects.types.slotfieldlist.EmptyconcSlotField  ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_empty_list_concSlotField() { return  tom.gom.adt.objects.types.slotfieldlist.EmptyconcSlotField.make() ; }private static  tom.gom.adt.objects.types.SlotFieldList  tom_cons_list_concSlotField( tom.gom.adt.objects.types.SlotField  e,  tom.gom.adt.objects.types.SlotFieldList  l) { return  tom.gom.adt.objects.types.slotfieldlist.ConsconcSlotField.make(e,l) ; }private static  tom.gom.adt.objects.types.SlotField  tom_get_head_concSlotField_SlotFieldList( tom.gom.adt.objects.types.SlotFieldList  l) {  return  l.getHeadconcSlotField()  ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_get_tail_concSlotField_SlotFieldList( tom.gom.adt.objects.types.SlotFieldList  l) {  return  l.getTailconcSlotField()  ;}private static boolean tom_is_empty_concSlotField_SlotFieldList( tom.gom.adt.objects.types.SlotFieldList  l) {  return  l.isEmptyconcSlotField()  ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_append_list_concSlotField( tom.gom.adt.objects.types.SlotFieldList  l1,  tom.gom.adt.objects.types.SlotFieldList  l2) {    if(tom_is_empty_concSlotField_SlotFieldList(l1)) {     return l2;    } else if(tom_is_empty_concSlotField_SlotFieldList(l2)) {     return l1;    } else if(tom_is_empty_concSlotField_SlotFieldList(( tom.gom.adt.objects.types.SlotFieldList )tom_get_tail_concSlotField_SlotFieldList(l1))) {     return ( tom.gom.adt.objects.types.SlotFieldList )tom_cons_list_concSlotField(( tom.gom.adt.objects.types.SlotField )tom_get_head_concSlotField_SlotFieldList(l1),l2);    } else {      return ( tom.gom.adt.objects.types.SlotFieldList )tom_cons_list_concSlotField(( tom.gom.adt.objects.types.SlotField )tom_get_head_concSlotField_SlotFieldList(l1),tom_append_list_concSlotField(( tom.gom.adt.objects.types.SlotFieldList )tom_get_tail_concSlotField_SlotFieldList(l1),l2));    }   }  private static  tom.gom.adt.objects.types.SlotFieldList  tom_get_slice_concSlotField( tom.gom.adt.objects.types.SlotFieldList  begin,  tom.gom.adt.objects.types.SlotFieldList  end) {    if(tom_terms_equal_SlotFieldList(begin,end)) {      return ( tom.gom.adt.objects.types.SlotFieldList )tom_empty_list_concSlotField();    } else {      return ( tom.gom.adt.objects.types.SlotFieldList )tom_cons_list_concSlotField(( tom.gom.adt.objects.types.SlotField )tom_get_head_concSlotField_SlotFieldList(begin),( tom.gom.adt.objects.types.SlotFieldList )tom_get_slice_concSlotField(( tom.gom.adt.objects.types.SlotFieldList )tom_get_tail_concSlotField_SlotFieldList(begin),end));    }   }   

  public MappingTemplate(ClassName className,
                         ClassName basicStrategy,
                         GomClassList sortClasses,
                         GomClassList operatorClasses) {
    super(className);
    this.basicStrategy = basicStrategy;
    this.sortClasses = sortClasses;
    this.operatorClasses = operatorClasses;
  }

  /* We may want to return the stringbuffer itself in the future, or directly write to a Stream */
  public void generate(java.io.Writer writer) throws java.io.IOException {
    if(GomEnvironment.getInstance().isBuiltinSort("String")) {
      writer.write("\n%include { string.tom }\n"

);
    }
    if(GomEnvironment.getInstance().isBuiltinSort("int")) {
      writer.write("\n%include { int.tom }\n"

);
    }
    if(GomEnvironment.getInstance().isBuiltinSort("char")) {
      writer.write("\n%include { char.tom }\n"

);
    }
    if(GomEnvironment.getInstance().isBuiltinSort("double")) {
      writer.write("\n%include { double.tom }\n"

);
    }
    if(GomEnvironment.getInstance().isBuiltinSort("long")) {
      writer.write("\n%include { long.tom }\n"

);
    }
    if(GomEnvironment.getInstance().isBuiltinSort("float")) {
      writer.write("\n%include { float.tom }\n"

);
    }
    if(GomEnvironment.getInstance().isBuiltinSort("ATerm")) {
      writer.write("\n%include { aterm.tom }\n"

);
    }
    if(GomEnvironment.getInstance().isBuiltinSort("ATermList")) {
      writer.write("\n%include { atermlist.tom }\n"

);
    }

    // generate a %typeterm for each class
     if(sortClasses instanceof  tom.gom.adt.objects.types.GomClassList ) { { tom.gom.adt.objects.types.GomClassList  tom_match1_1=(( tom.gom.adt.objects.types.GomClassList )sortClasses); if ( ( tom_is_fun_sym_concGomClass(tom_match1_1) ||  false  ) ) { { tom.gom.adt.objects.types.GomClassList  tom_match1_1_list1=tom_match1_1; { tom.gom.adt.objects.types.GomClassList  tom_match1_1_begin1=tom_match1_1_list1; { tom.gom.adt.objects.types.GomClassList  tom_match1_1_end1=tom_match1_1_list1; { while (!(tom_is_empty_concGomClass_GomClassList(tom_match1_1_end1))) {tom_match1_1_list1=tom_match1_1_end1; { { tom.gom.adt.objects.types.GomClass  tom_match1_1_2=tom_get_head_concGomClass_GomClassList(tom_match1_1_list1);tom_match1_1_list1=tom_get_tail_concGomClass_GomClassList(tom_match1_1_list1); if ( ( tom_is_fun_sym_SortClass(tom_match1_1_2) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match1_1_2_ClassName=tom_get_slot_SortClass_ClassName(tom_match1_1_2); { tom.gom.adt.objects.types.ClassName  tom_sortName=tom_match1_1_2_ClassName; {boolean tom_match1_tom_anti_constraints_status= true ; if ((tom_match1_tom_anti_constraints_status ==  true )) { if ( true ) {



        writer.write("\n%typeterm "/* Generated by TOM (version 2.4rc2): Do not edit this file */+className(tom_sortName)+" {\n  implement { "/* Generated by TOM (version 2.4rc2): Do not edit this file */+fullClassName(tom_sortName)+" }\n  equals(t1,t2) { t1.equals(t2) }\n  visitor_fwd { "/* Generated by TOM (version 2.4rc2): Do not edit this file */+fullClassName(basicStrategy)+" }\n}\n\n"






);
       } } } } } } }tom_match1_1_end1=tom_get_tail_concGomClass_GomClassList(tom_match1_1_end1); } }tom_match1_1_list1=tom_match1_1_begin1; } } } } } } }


    // generate a %op for each operator
     if(operatorClasses instanceof  tom.gom.adt.objects.types.GomClassList ) { { tom.gom.adt.objects.types.GomClassList  tom_match2_1=(( tom.gom.adt.objects.types.GomClassList )operatorClasses); if ( ( tom_is_fun_sym_concGomClass(tom_match2_1) ||  false  ) ) { { tom.gom.adt.objects.types.GomClassList  tom_match2_1_list1=tom_match2_1; { tom.gom.adt.objects.types.GomClassList  tom_match2_1_begin1=tom_match2_1_list1; { tom.gom.adt.objects.types.GomClassList  tom_match2_1_end1=tom_match2_1_list1; { while (!(tom_is_empty_concGomClass_GomClassList(tom_match2_1_end1))) {tom_match2_1_list1=tom_match2_1_end1; { { tom.gom.adt.objects.types.GomClass  tom_match2_1_2=tom_get_head_concGomClass_GomClassList(tom_match2_1_list1);tom_match2_1_list1=tom_get_tail_concGomClass_GomClassList(tom_match2_1_list1); if ( ( tom_is_fun_sym_OperatorClass(tom_match2_1_2) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match2_1_2_ClassName=tom_get_slot_OperatorClass_ClassName(tom_match2_1_2); { tom.gom.adt.objects.types.ClassName  tom_match2_1_2_SortName=tom_get_slot_OperatorClass_SortName(tom_match2_1_2); { tom.gom.adt.objects.types.SlotFieldList  tom_match2_1_2_Slots=tom_get_slot_OperatorClass_Slots(tom_match2_1_2); { tom.gom.adt.objects.types.ClassName  tom_opName=tom_match2_1_2_ClassName; { tom.gom.adt.objects.types.ClassName  tom_sortName=tom_match2_1_2_SortName; { tom.gom.adt.objects.types.SlotFieldList  tom_slotList=tom_match2_1_2_Slots; {boolean tom_match2_tom_anti_constraints_status= true ; if ((tom_match2_tom_anti_constraints_status ==  true )) { if ( true ) {





        writer.write("%op "+className(tom_sortName)+" "+className(tom_opName)+"(");
        slotDecl(writer,tom_slotList);
        writer.write(") {\n");
        //writer.write("  is_fsym(t) { (t!=null) && t."+isOperatorMethod(`opName)+"() }\n");
        writer.write("  is_fsym(t) { t instanceof "+fullClassName(tom_opName)+" }\n");
         if(tom_slotList instanceof  tom.gom.adt.objects.types.SlotFieldList ) { { tom.gom.adt.objects.types.SlotFieldList  tom_match3_1=(( tom.gom.adt.objects.types.SlotFieldList )tom_slotList); if ( ( tom_is_fun_sym_concSlotField(tom_match3_1) ||  false  ) ) { { tom.gom.adt.objects.types.SlotFieldList  tom_match3_1_list1=tom_match3_1; { tom.gom.adt.objects.types.SlotFieldList  tom_match3_1_begin1=tom_match3_1_list1; { tom.gom.adt.objects.types.SlotFieldList  tom_match3_1_end1=tom_match3_1_list1; { while (!(tom_is_empty_concSlotField_SlotFieldList(tom_match3_1_end1))) {tom_match3_1_list1=tom_match3_1_end1; { { tom.gom.adt.objects.types.SlotField  tom_match3_1_2=tom_get_head_concSlotField_SlotFieldList(tom_match3_1_list1);tom_match3_1_list1=tom_get_tail_concSlotField_SlotFieldList(tom_match3_1_list1); if ( ( tom_is_fun_sym_SlotField(tom_match3_1_2) ||  false  ) ) { { String  tom_match3_1_2_Name=tom_get_slot_SlotField_Name(tom_match3_1_2); { String  tom_slotName=tom_match3_1_2_Name; { tom.gom.adt.objects.types.SlotField  tom_slot=tom_match3_1_2; {boolean tom_match3_tom_anti_constraints_status= true ; if ((tom_match3_tom_anti_constraints_status ==  true )) { if ( true ) {

            writer.write("  get_slot("+tom_slotName+", t) ");
            writer.write("{ t."+getMethod(tom_slot)+"() }\n");
           } } } } } } } }tom_match3_1_end1=tom_get_tail_concSlotField_SlotFieldList(tom_match3_1_end1); } }tom_match3_1_list1=tom_match3_1_begin1; } } } } } } }

        writer.write("  make(");
        slotArgs(writer,tom_slotList);
        writer.write(") { ");
        writer.write(fullClassName(tom_opName));
        writer.write(".make(");
        slotArgs(writer,tom_slotList);
        writer.write(")}\n");
        writer.write("}\n");
        writer.write("\n");
       } } } } } } } } } } }tom_match2_1_end1=tom_get_tail_concGomClass_GomClassList(tom_match2_1_end1); } }tom_match2_1_list1=tom_match2_1_begin1; } } } } } } }


    // generate a %oplist for each variadic operator
     if(operatorClasses instanceof  tom.gom.adt.objects.types.GomClassList ) { { tom.gom.adt.objects.types.GomClassList  tom_match4_1=(( tom.gom.adt.objects.types.GomClassList )operatorClasses); if ( ( tom_is_fun_sym_concGomClass(tom_match4_1) ||  false  ) ) { { tom.gom.adt.objects.types.GomClassList  tom_match4_1_list1=tom_match4_1; { tom.gom.adt.objects.types.GomClassList  tom_match4_1_begin1=tom_match4_1_list1; { tom.gom.adt.objects.types.GomClassList  tom_match4_1_end1=tom_match4_1_list1; { while (!(tom_is_empty_concGomClass_GomClassList(tom_match4_1_end1))) {tom_match4_1_list1=tom_match4_1_end1; { { tom.gom.adt.objects.types.GomClass  tom_match4_1_2=tom_get_head_concGomClass_GomClassList(tom_match4_1_list1);tom_match4_1_list1=tom_get_tail_concGomClass_GomClassList(tom_match4_1_list1); if ( ( tom_is_fun_sym_VariadicOperatorClass(tom_match4_1_2) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match4_1_2_ClassName=tom_get_slot_VariadicOperatorClass_ClassName(tom_match4_1_2); { tom.gom.adt.objects.types.ClassName  tom_match4_1_2_SortName=tom_get_slot_VariadicOperatorClass_SortName(tom_match4_1_2); { tom.gom.adt.objects.types.GomClass  tom_match4_1_2_Empty=tom_get_slot_VariadicOperatorClass_Empty(tom_match4_1_2); { tom.gom.adt.objects.types.GomClass  tom_match4_1_2_Cons=tom_get_slot_VariadicOperatorClass_Cons(tom_match4_1_2); { tom.gom.adt.objects.types.ClassName  tom_opName=tom_match4_1_2_ClassName; { tom.gom.adt.objects.types.ClassName  tom_sortName=tom_match4_1_2_SortName; if ( ( tom_is_fun_sym_OperatorClass(tom_match4_1_2_Empty) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match4_1_2_Empty_ClassName=tom_get_slot_OperatorClass_ClassName(tom_match4_1_2_Empty); { tom.gom.adt.objects.types.ClassName  tom_emptyClass=tom_match4_1_2_Empty_ClassName; if ( ( tom_is_fun_sym_OperatorClass(tom_match4_1_2_Cons) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match4_1_2_Cons_ClassName=tom_get_slot_OperatorClass_ClassName(tom_match4_1_2_Cons); { tom.gom.adt.objects.types.SlotFieldList  tom_match4_1_2_Cons_Slots=tom_get_slot_OperatorClass_Slots(tom_match4_1_2_Cons); { tom.gom.adt.objects.types.ClassName  tom_concClass=tom_match4_1_2_Cons_ClassName; if ( ( tom_is_fun_sym_concSlotField(tom_match4_1_2_Cons_Slots) ||  false  ) ) { { tom.gom.adt.objects.types.SlotFieldList  tom_match4_1_2_Cons_Slots_list1=tom_match4_1_2_Cons_Slots; if (!(tom_is_empty_concSlotField_SlotFieldList(tom_match4_1_2_Cons_Slots_list1))) { { tom.gom.adt.objects.types.SlotField  tom_match4_1_2_Cons_Slots_1=tom_get_head_concSlotField_SlotFieldList(tom_match4_1_2_Cons_Slots_list1);tom_match4_1_2_Cons_Slots_list1=tom_get_tail_concSlotField_SlotFieldList(tom_match4_1_2_Cons_Slots_list1); if ( ( tom_is_fun_sym_SlotField(tom_match4_1_2_Cons_Slots_1) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match4_1_2_Cons_Slots_1_Domain=tom_get_slot_SlotField_Domain(tom_match4_1_2_Cons_Slots_1); { tom.gom.adt.objects.types.ClassName  tom_headDomain=tom_match4_1_2_Cons_Slots_1_Domain; { tom.gom.adt.objects.types.SlotField  tom_head=tom_match4_1_2_Cons_Slots_1; if (!(tom_is_empty_concSlotField_SlotFieldList(tom_match4_1_2_Cons_Slots_list1))) { { tom.gom.adt.objects.types.SlotField  tom_tail=tom_get_head_concSlotField_SlotFieldList(tom_match4_1_2_Cons_Slots_list1);tom_match4_1_2_Cons_Slots_list1=tom_get_tail_concSlotField_SlotFieldList(tom_match4_1_2_Cons_Slots_list1); if (tom_is_empty_concSlotField_SlotFieldList(tom_match4_1_2_Cons_Slots_list1)) { {boolean tom_match4_tom_anti_constraints_status= true ; if ((tom_match4_tom_anti_constraints_status ==  true )) { if ( true ) {











        if(tom_sortName== tom_headDomain) { /* handle List = conc(List*) case */
          writer.write("\n%oplist "/* Generated by TOM (version 2.4rc2): Do not edit this file */+className(tom_sortName)+" "/* Generated by TOM (version 2.4rc2): Do not edit this file */+className(tom_opName)+"("/* Generated by TOM (version 2.4rc2): Do not edit this file */+className(tom_headDomain)+"*) {\n  is_fsym(t) { t instanceof "/* Generated by TOM (version 2.4rc2): Do not edit this file */+fullClassName(tom_concClass)+" || t instanceof "/* Generated by TOM (version 2.4rc2): Do not edit this file */+fullClassName(tom_emptyClass)+" }\n  make_empty() { "/* Generated by TOM (version 2.4rc2): Do not edit this file */+fullClassName(tom_emptyClass)+".make() }\n  make_insert(e,l) { "/* Generated by TOM (version 2.4rc2): Do not edit this file */+fullClassName(tom_concClass)+".make(e,l) }\n  get_head(l) { (l."/* Generated by TOM (version 2.4rc2): Do not edit this file */+isOperatorMethod(tom_concClass)+"())?(l."/* Generated by TOM (version 2.4rc2): Do not edit this file */+getMethod(tom_head)+"()):(l) }\n  get_tail(l) { (l."/* Generated by TOM (version 2.4rc2): Do not edit this file */+isOperatorMethod(tom_concClass)+"())?(l."/* Generated by TOM (version 2.4rc2): Do not edit this file */+getMethod(tom_tail)+"()):("/* Generated by TOM (version 2.4rc2): Do not edit this file */+fullClassName(tom_emptyClass)+".make()) }\n  is_empty(l) { l."/* Generated by TOM (version 2.4rc2): Do not edit this file */+isOperatorMethod(tom_emptyClass)+"() }\n}\n"








);
        } else {
          writer.write("\n%oplist "/* Generated by TOM (version 2.4rc2): Do not edit this file */+className(tom_sortName)+" "/* Generated by TOM (version 2.4rc2): Do not edit this file */+className(tom_opName)+"("/* Generated by TOM (version 2.4rc2): Do not edit this file */+className(tom_headDomain)+"*) {\n  is_fsym(t) { t instanceof "/* Generated by TOM (version 2.4rc2): Do not edit this file */+fullClassName(tom_concClass)+" || t instanceof "/* Generated by TOM (version 2.4rc2): Do not edit this file */+fullClassName(tom_emptyClass)+" }\n  make_empty() { "/* Generated by TOM (version 2.4rc2): Do not edit this file */+fullClassName(tom_emptyClass)+".make() }\n  make_insert(e,l) { "/* Generated by TOM (version 2.4rc2): Do not edit this file */+fullClassName(tom_concClass)+".make(e,l) }\n  get_head(l) { l."/* Generated by TOM (version 2.4rc2): Do not edit this file */+getMethod(tom_head)+"() }\n  get_tail(l) { l."/* Generated by TOM (version 2.4rc2): Do not edit this file */+getMethod(tom_tail)+"() }\n  is_empty(l) { l."/* Generated by TOM (version 2.4rc2): Do not edit this file */+isOperatorMethod(tom_emptyClass)+"() }\n}\n"








);

        }
       } } } } } } } } } } } } } } } } } } } } } } } } } } } } }tom_match4_1_end1=tom_get_tail_concGomClass_GomClassList(tom_match4_1_end1); } }tom_match4_1_list1=tom_match4_1_begin1; } } } } } } }

  }

  private void slotDecl(java.io.Writer writer, SlotFieldList slotList) throws java.io.IOException {
    int index = 0;
    while(!slotList.isEmptyconcSlotField()) {
      SlotField slot = slotList.getHeadconcSlotField();
      slotList = slotList.getTailconcSlotField();
      if (index>0) { writer.write(", "); }
       if(slot instanceof  tom.gom.adt.objects.types.SlotField ) { { tom.gom.adt.objects.types.SlotField  tom_match5_1=(( tom.gom.adt.objects.types.SlotField )slot); if ( ( tom_is_fun_sym_SlotField(tom_match5_1) ||  false  ) ) { { String  tom_match5_1_Name=tom_get_slot_SlotField_Name(tom_match5_1); { tom.gom.adt.objects.types.ClassName  tom_match5_1_Domain=tom_get_slot_SlotField_Domain(tom_match5_1); { String  tom_slotName=tom_match5_1_Name; if ( ( tom_is_fun_sym_ClassName(tom_match5_1_Domain) ||  false  ) ) { { String  tom_match5_1_Domain_Name=tom_get_slot_ClassName_Name(tom_match5_1_Domain); { String  tom_domainName=tom_match5_1_Domain_Name; {boolean tom_match5_tom_anti_constraints_status= true ; if ((tom_match5_tom_anti_constraints_status ==  true )) { if ( true ) {

          writer.write(tom_slotName);
          writer.write(":");
          writer.write(tom_domainName);
          index++;
         } } } } } } } } } } } }

    }
  }

  private void slotArgs(java.io.Writer writer, SlotFieldList slotList) throws java.io.IOException {
    int index = 0;
    while(!slotList.isEmptyconcSlotField()) {
      SlotField slot = slotList.getHeadconcSlotField();
      slotList = slotList.getTailconcSlotField();
      if (index>0) { writer.write(", "); }
      // Warning: do not write the 'index' alone, this would be a strange character
      writer.write("t"+index);
      index++;
    }
  }

  protected String fileName() {
    return fullClassName().replace('.',File.separatorChar)+".tom";
  }

  protected File fileToGenerate() {
    GomStreamManager stream = GomEnvironment.getInstance().getStreamManager();
    File output = new File(stream.getDestDir(),fileName());
    // log the generated mapping file name
    try {
      GomEnvironment.getInstance().setLastGeneratedMapping(output.getCanonicalPath());
    } catch(Exception e) {
      e.printStackTrace();
    }
    return output;
  }
}
