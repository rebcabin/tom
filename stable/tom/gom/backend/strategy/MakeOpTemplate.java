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

import java.io.*;
import java.util.logging.*;
import tom.gom.backend.TemplateClass;
import tom.gom.tools.GomEnvironment;
import tom.gom.tools.error.GomRuntimeException;
import tom.gom.adt.objects.types.*;

public class MakeOpTemplate extends TemplateClass {
  ClassName operator;
  SlotFieldList slotList;

  /* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file */ private static boolean tom_terms_equal_String( String  t1,  String  t2) {  return  (t1.equals(t2))  ;}  /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ private static boolean tom_terms_equal_SlotField(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_SlotFieldList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ClassName(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_fun_sym_SlotField( tom.gom.adt.objects.types.SlotField  t) {  return  (t!=null) && t.isSlotField()  ;}private static  String  tom_get_slot_SlotField_name( tom.gom.adt.objects.types.SlotField  t) {  return  t.getname()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SlotField_domain( tom.gom.adt.objects.types.SlotField  t) {  return  t.getdomain()  ;}private static boolean tom_is_fun_sym_ClassName( tom.gom.adt.objects.types.ClassName  t) {  return  (t!=null) && t.isClassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_make_ClassName( String  t0,  String  t1) { return  tom.gom.adt.objects.types.classname.ClassName.make(t0, t1); }private static  String  tom_get_slot_ClassName_pkg( tom.gom.adt.objects.types.ClassName  t) {  return  t.getpkg()  ;}private static  String  tom_get_slot_ClassName_name( tom.gom.adt.objects.types.ClassName  t) {  return  t.getname()  ;}private static boolean tom_is_fun_sym_concSlotField( tom.gom.adt.objects.types.SlotFieldList  t) {  return  t instanceof tom.gom.adt.objects.types.slotfieldlist.ConsconcSlotField || t instanceof tom.gom.adt.objects.types.slotfieldlist.EmptyconcSlotField  ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_empty_list_concSlotField() { return  tom.gom.adt.objects.types.slotfieldlist.EmptyconcSlotField.make() ; }private static  tom.gom.adt.objects.types.SlotFieldList  tom_cons_list_concSlotField( tom.gom.adt.objects.types.SlotField  e,  tom.gom.adt.objects.types.SlotFieldList  l) { return  tom.gom.adt.objects.types.slotfieldlist.ConsconcSlotField.make(e,l) ; }private static  tom.gom.adt.objects.types.SlotField  tom_get_head_concSlotField_SlotFieldList( tom.gom.adt.objects.types.SlotFieldList  l) {  return  l.getHeadconcSlotField()  ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_get_tail_concSlotField_SlotFieldList( tom.gom.adt.objects.types.SlotFieldList  l) {  return  l.getTailconcSlotField()  ;}private static boolean tom_is_empty_concSlotField_SlotFieldList( tom.gom.adt.objects.types.SlotFieldList  l) {  return  l.isEmptyconcSlotField()  ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_append_list_concSlotField( tom.gom.adt.objects.types.SlotFieldList  l1,  tom.gom.adt.objects.types.SlotFieldList  l2) {    if(tom_is_empty_concSlotField_SlotFieldList(l1)) {     return l2;    } else if(tom_is_empty_concSlotField_SlotFieldList(l2)) {     return l1;    } else if(tom_is_empty_concSlotField_SlotFieldList(( tom.gom.adt.objects.types.SlotFieldList )tom_get_tail_concSlotField_SlotFieldList(l1))) {     return ( tom.gom.adt.objects.types.SlotFieldList )tom_cons_list_concSlotField(( tom.gom.adt.objects.types.SlotField )tom_get_head_concSlotField_SlotFieldList(l1),l2);    } else {      return ( tom.gom.adt.objects.types.SlotFieldList )tom_cons_list_concSlotField(( tom.gom.adt.objects.types.SlotField )tom_get_head_concSlotField_SlotFieldList(l1),tom_append_list_concSlotField(( tom.gom.adt.objects.types.SlotFieldList )tom_get_tail_concSlotField_SlotFieldList(l1),l2));    }   }  private static  tom.gom.adt.objects.types.SlotFieldList  tom_get_slice_concSlotField( tom.gom.adt.objects.types.SlotFieldList  begin,  tom.gom.adt.objects.types.SlotFieldList  end) {    if(tom_terms_equal_SlotFieldList(begin,end)) {      return ( tom.gom.adt.objects.types.SlotFieldList )tom_empty_list_concSlotField();    } else {      return ( tom.gom.adt.objects.types.SlotFieldList )tom_cons_list_concSlotField(( tom.gom.adt.objects.types.SlotField )tom_get_head_concSlotField_SlotFieldList(begin),( tom.gom.adt.objects.types.SlotFieldList )tom_get_slice_concSlotField(( tom.gom.adt.objects.types.SlotFieldList )tom_get_tail_concSlotField_SlotFieldList(begin),end));    }   }   

  /**
   * The argument is an operator class, and this template generates the
   * assotiated MakeOp strategy
   */
  public MakeOpTemplate(ClassName className,
                        SlotFieldList slots) {
    super(className);
     if(className instanceof  tom.gom.adt.objects.types.ClassName ) { { tom.gom.adt.objects.types.ClassName  tom_match1_1=(( tom.gom.adt.objects.types.ClassName )className); if ( ( tom_is_fun_sym_ClassName(tom_match1_1) ||  false  ) ) { { String  tom_match1_1_pkg=tom_get_slot_ClassName_pkg(tom_match1_1); { String  tom_match1_1_name=tom_get_slot_ClassName_name(tom_match1_1); { String  tom_pkg=tom_match1_1_pkg; { String  tom_name=tom_match1_1_name; if ( true ) {

        String newpkg = tom_pkg.replaceFirst(".types.",".strategy.");
        String newname = "Make_"+tom_name;
        this.className = tom_make_ClassName(newpkg,newname);
       } } } } } } } }

    this.operator = className;
    this.slotList = slots;
  }

  public String generate() {

    String classBody = "\npackage "/* Generated by TOM (version 2.4alpha): Do not edit this file */+getPackage()+";\n\npublic class "/* Generated by TOM (version 2.4alpha): Do not edit this file */+className()+" implements tom.library.strategy.mutraveler.MuStrategy {\n  /* Do not manage an internal position, since the arguments is not really\n   * used\n   */\n  public void setPosition(tom.library.strategy.mutraveler.Position pos) { ; /* who cares */ }\n\n  public tom.library.strategy.mutraveler.Position getPosition() {\n    throw new RuntimeException(\"Construction strategies have no position\");\n  }\n\n  public boolean hasPosition() { return false; }\n\n"/* Generated by TOM (version 2.4alpha): Do not edit this file */+generateMembers()+"\n\n  public int getChildCount() {\n    return "/* Generated by TOM (version 2.4alpha): Do not edit this file */+nonBuiltinChildCount()+";\n  }\n  public jjtraveler.Visitable getChildAt(int index) {\n    switch(index) {\n"/* Generated by TOM (version 2.4alpha): Do not edit this file */+nonBuiltinsGetCases()+"\n      default: throw new IndexOutOfBoundsException();\n    }\n  }\n  public jjtraveler.Visitable setChildAt(int index, jjtraveler.Visitable child) {\n    switch(index) {\n"/* Generated by TOM (version 2.4alpha): Do not edit this file */+nonBuiltinMakeCases("child")+"\n      default: throw new IndexOutOfBoundsException();\n    }\n  }\n  /*\n   * Apply the strategy, and returns the subject in case of VisitFailure\n   */\n  public jjtraveler.Visitable apply(jjtraveler.Visitable any) {\n    try {\n      return tom.library.strategy.mutraveler.MuTraveler.init(this).visit(any);\n    } catch (jjtraveler.VisitFailure f) {\n      return any;\n    }\n  }\n\n  public tom.library.strategy.mutraveler.MuStrategy accept(tom.library.strategy.mutraveler.reflective.StrategyVisitorFwd v) throws jjtraveler.VisitFailure {\n    return v.visit_Strategy(this);\n  }\n\n  public "/* Generated by TOM (version 2.4alpha): Do not edit this file */+className()+"("/* Generated by TOM (version 2.4alpha): Do not edit this file */+childListWithType(slotList)+") {\n"/* Generated by TOM (version 2.4alpha): Do not edit this file */+generateMembersInit()+"\n  }\n\n  /**\n    * Builds a new "/* Generated by TOM (version 2.4alpha): Do not edit this file */+className(operator)+"\n    * If one of the sub-strategies application fails, throw a VisitFailure\n    */\n  public jjtraveler.Visitable visit(jjtraveler.Visitable any) throws jjtraveler.VisitFailure {\n"/* Generated by TOM (version 2.4alpha): Do not edit this file */+computeNewChilds(slotList,"any")+"\n    return "/* Generated by TOM (version 2.4alpha): Do not edit this file */+fullClassName(operator)+".make("/* Generated by TOM (version 2.4alpha): Do not edit this file */+genMakeArguments(slotList)+");\n  }\n}\n"



























































;

    return classBody;
  }

  public String generateMapping() {
    return "\n%op Strategy "/* Generated by TOM (version 2.4alpha): Do not edit this file */+className()+"("/* Generated by TOM (version 2.4alpha): Do not edit this file */+genStratArgs(slotList,"arg")+") {\n  is_fsym(t) { (t!=null) && t instanceof ("/* Generated by TOM (version 2.4alpha): Do not edit this file */+fullClassName()+")}\n"/* Generated by TOM (version 2.4alpha): Do not edit this file */+genGetSlot(slotList,"arg")+"\n  make("/* Generated by TOM (version 2.4alpha): Do not edit this file */+genMakeArguments(slotList)+") { new "/* Generated by TOM (version 2.4alpha): Do not edit this file */+fullClassName()+"("/* Generated by TOM (version 2.4alpha): Do not edit this file */+genMakeArguments(slotList)+") }\n}\n"





;
  }

  private String genGetSlot(SlotFieldList slots, String arg) {
    String out = "";
    while(!slots.isEmptyconcSlotField()) {
      SlotField head = slots.getHeadconcSlotField();
      slots = slots.getTailconcSlotField();
       if(head instanceof  tom.gom.adt.objects.types.SlotField ) { { tom.gom.adt.objects.types.SlotField  tom_match2_1=(( tom.gom.adt.objects.types.SlotField )head); if ( ( tom_is_fun_sym_SlotField(tom_match2_1) ||  false  ) ) { { String  tom_match2_1_name=tom_get_slot_SlotField_name(tom_match2_1); { tom.gom.adt.objects.types.ClassName  tom_match2_1_domain=tom_get_slot_SlotField_domain(tom_match2_1); { String  tom_name=tom_match2_1_name; if ( true ) {

          out += "\n  get_slot("/* Generated by TOM (version 2.4alpha): Do not edit this file */+fieldName(tom_name)+", t) { "/* Generated by TOM (version 2.4alpha): Do not edit this file */+fieldName(tom_name)+" }"
;
         } } } } } } }

    }
    return out;
  }
  private String genStratArgs(SlotFieldList slots,String arg) {
    String args = "";
    int i = 0;
    while(!slots.isEmptyconcSlotField()) {
      SlotField head = slots.getHeadconcSlotField();
      slots = slots.getTailconcSlotField();

       if(head instanceof  tom.gom.adt.objects.types.SlotField ) { { tom.gom.adt.objects.types.SlotField  tom_match3_1=(( tom.gom.adt.objects.types.SlotField )head); if ( ( tom_is_fun_sym_SlotField(tom_match3_1) ||  false  ) ) { { String  tom_match3_1_name=tom_get_slot_SlotField_name(tom_match3_1); { tom.gom.adt.objects.types.ClassName  tom_match3_1_domain=tom_get_slot_SlotField_domain(tom_match3_1); { String  tom_name=tom_match3_1_name; { tom.gom.adt.objects.types.ClassName  tom_domain=tom_match3_1_domain; if ( true ) {

          if (!GomEnvironment.getInstance().isBuiltinClass(tom_domain)) {
            args += (i==0?"":", ")+fieldName(tom_name)+":Strategy";
          } else {
            args += (i==0?"":", ")+fieldName(tom_name)+":"+fullClassName(tom_domain);
          }
         } } } } } } } }

      i++;
    }
    return args;
  }

  private String genNonBuiltin() {
    String out = "";
     if(slotList instanceof  tom.gom.adt.objects.types.SlotFieldList ) { { tom.gom.adt.objects.types.SlotFieldList  tom_match4_1=(( tom.gom.adt.objects.types.SlotFieldList )slotList); if ( ( tom_is_fun_sym_concSlotField(tom_match4_1) ||  false  ) ) { { tom.gom.adt.objects.types.SlotFieldList  tom_match4_1_list1=tom_match4_1; { tom.gom.adt.objects.types.SlotFieldList  tom_match4_1_begin1=tom_match4_1_list1; { tom.gom.adt.objects.types.SlotFieldList  tom_match4_1_end1=tom_match4_1_list1; { while (!(tom_is_empty_concSlotField_SlotFieldList(tom_match4_1_end1))) {tom_match4_1_list1=tom_match4_1_end1; { { tom.gom.adt.objects.types.SlotField  tom_match4_1_2=tom_get_head_concSlotField_SlotFieldList(tom_match4_1_list1);tom_match4_1_list1=tom_get_tail_concSlotField_SlotFieldList(tom_match4_1_list1); if ( ( tom_is_fun_sym_SlotField(tom_match4_1_2) ||  false  ) ) { { String  tom_match4_1_2_name=tom_get_slot_SlotField_name(tom_match4_1_2); { tom.gom.adt.objects.types.ClassName  tom_match4_1_2_domain=tom_get_slot_SlotField_domain(tom_match4_1_2); { tom.gom.adt.objects.types.ClassName  tom_domain=tom_match4_1_2_domain; if ( true ) {

        if (!GomEnvironment.getInstance().isBuiltinClass(tom_domain)) {
          out += "true, ";
        } else {
          out += "false, ";
        }
       } } } } } }tom_match4_1_end1=tom_get_tail_concSlotField_SlotFieldList(tom_match4_1_end1); } }tom_match4_1_list1=tom_match4_1_begin1; } } } } } } }

    if (out.length()!=0) {
      return out.substring(0,out.length()-2);
    } else {
      return out;
    }
  }

  private int nonBuiltinChildCount() {
    int count = 0;
     if(slotList instanceof  tom.gom.adt.objects.types.SlotFieldList ) { { tom.gom.adt.objects.types.SlotFieldList  tom_match5_1=(( tom.gom.adt.objects.types.SlotFieldList )slotList); if ( ( tom_is_fun_sym_concSlotField(tom_match5_1) ||  false  ) ) { { tom.gom.adt.objects.types.SlotFieldList  tom_match5_1_list1=tom_match5_1; { tom.gom.adt.objects.types.SlotFieldList  tom_match5_1_begin1=tom_match5_1_list1; { tom.gom.adt.objects.types.SlotFieldList  tom_match5_1_end1=tom_match5_1_list1; { while (!(tom_is_empty_concSlotField_SlotFieldList(tom_match5_1_end1))) {tom_match5_1_list1=tom_match5_1_end1; { { tom.gom.adt.objects.types.SlotField  tom_match5_1_2=tom_get_head_concSlotField_SlotFieldList(tom_match5_1_list1);tom_match5_1_list1=tom_get_tail_concSlotField_SlotFieldList(tom_match5_1_list1); if ( ( tom_is_fun_sym_SlotField(tom_match5_1_2) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match5_1_2_domain=tom_get_slot_SlotField_domain(tom_match5_1_2); { tom.gom.adt.objects.types.ClassName  tom_domain=tom_match5_1_2_domain; if ( true ) {

        if (!GomEnvironment.getInstance().isBuiltinClass(tom_domain)) {
          count++;
        }
       } } } } }tom_match5_1_end1=tom_get_tail_concSlotField_SlotFieldList(tom_match5_1_end1); } }tom_match5_1_list1=tom_match5_1_begin1; } } } } } } }

    return count;
  }

  /**
    * Store a strategy for each non builtin child, the builtin value otherwise
    */
  private String generateMembers() {
    String res="";
     if(slotList instanceof  tom.gom.adt.objects.types.SlotFieldList ) { { tom.gom.adt.objects.types.SlotFieldList  tom_match6_1=(( tom.gom.adt.objects.types.SlotFieldList )slotList); if ( ( tom_is_fun_sym_concSlotField(tom_match6_1) ||  false  ) ) { { tom.gom.adt.objects.types.SlotFieldList  tom_match6_1_list1=tom_match6_1; { tom.gom.adt.objects.types.SlotFieldList  tom_match6_1_begin1=tom_match6_1_list1; { tom.gom.adt.objects.types.SlotFieldList  tom_match6_1_end1=tom_match6_1_list1; { while (!(tom_is_empty_concSlotField_SlotFieldList(tom_match6_1_end1))) {tom_match6_1_list1=tom_match6_1_end1; { { tom.gom.adt.objects.types.SlotField  tom_match6_1_2=tom_get_head_concSlotField_SlotFieldList(tom_match6_1_list1);tom_match6_1_list1=tom_get_tail_concSlotField_SlotFieldList(tom_match6_1_list1); if ( ( tom_is_fun_sym_SlotField(tom_match6_1_2) ||  false  ) ) { { String  tom_match6_1_2_name=tom_get_slot_SlotField_name(tom_match6_1_2); { tom.gom.adt.objects.types.ClassName  tom_match6_1_2_domain=tom_get_slot_SlotField_domain(tom_match6_1_2); { String  tom_fieldName=tom_match6_1_2_name; { tom.gom.adt.objects.types.ClassName  tom_domain=tom_match6_1_2_domain; if ( true ) {

        if (!GomEnvironment.getInstance().isBuiltinClass(tom_domain)) {
          res += "  private jjtraveler.reflective.VisitableVisitor "+fieldName(tom_fieldName)+";\n";
        } else {
          res += "  private "+fullClassName(tom_domain)+" "+fieldName(tom_fieldName)+";\n";
        }
       } } } } } } }tom_match6_1_end1=tom_get_tail_concSlotField_SlotFieldList(tom_match6_1_end1); } }tom_match6_1_list1=tom_match6_1_begin1; } } } } } } }

    return res;
  }

  /**
    * Generate "case: " instructions for each non builtin child
    * XXX: this code in duplicated from OperatorTemplate, need to be factorized
    */
  private String nonBuiltinsGetCases() {
    String res = "";
    int index = 0;
     if(slotList instanceof  tom.gom.adt.objects.types.SlotFieldList ) { { tom.gom.adt.objects.types.SlotFieldList  tom_match7_1=(( tom.gom.adt.objects.types.SlotFieldList )slotList); if ( ( tom_is_fun_sym_concSlotField(tom_match7_1) ||  false  ) ) { { tom.gom.adt.objects.types.SlotFieldList  tom_match7_1_list1=tom_match7_1; { tom.gom.adt.objects.types.SlotFieldList  tom_match7_1_begin1=tom_match7_1_list1; { tom.gom.adt.objects.types.SlotFieldList  tom_match7_1_end1=tom_match7_1_list1; { while (!(tom_is_empty_concSlotField_SlotFieldList(tom_match7_1_end1))) {tom_match7_1_list1=tom_match7_1_end1; { { tom.gom.adt.objects.types.SlotField  tom_match7_1_2=tom_get_head_concSlotField_SlotFieldList(tom_match7_1_list1);tom_match7_1_list1=tom_get_tail_concSlotField_SlotFieldList(tom_match7_1_list1); if ( ( tom_is_fun_sym_SlotField(tom_match7_1_2) ||  false  ) ) { { String  tom_match7_1_2_name=tom_get_slot_SlotField_name(tom_match7_1_2); { tom.gom.adt.objects.types.ClassName  tom_match7_1_2_domain=tom_get_slot_SlotField_domain(tom_match7_1_2); { String  tom_fieldName=tom_match7_1_2_name; { tom.gom.adt.objects.types.ClassName  tom_domain=tom_match7_1_2_domain; if ( true ) {

        if (!GomEnvironment.getInstance().isBuiltinClass(tom_domain)) {
          res += "      case "+index+": return "+fieldName(tom_fieldName)+";\n";
          index++;
        }
       } } } } } } }tom_match7_1_end1=tom_get_tail_concSlotField_SlotFieldList(tom_match7_1_end1); } }tom_match7_1_list1=tom_match7_1_begin1; } } } } } } }

    return res;
  }

  private String nonBuiltinMakeCases(String argName) {
    String res = "";
    int index = 0;
     if(slotList instanceof  tom.gom.adt.objects.types.SlotFieldList ) { { tom.gom.adt.objects.types.SlotFieldList  tom_match8_1=(( tom.gom.adt.objects.types.SlotFieldList )slotList); if ( ( tom_is_fun_sym_concSlotField(tom_match8_1) ||  false  ) ) { { tom.gom.adt.objects.types.SlotFieldList  tom_match8_1_list1=tom_match8_1; { tom.gom.adt.objects.types.SlotFieldList  tom_match8_1_begin1=tom_match8_1_list1; { tom.gom.adt.objects.types.SlotFieldList  tom_match8_1_end1=tom_match8_1_list1; { while (!(tom_is_empty_concSlotField_SlotFieldList(tom_match8_1_end1))) {tom_match8_1_list1=tom_match8_1_end1; { { tom.gom.adt.objects.types.SlotField  tom_match8_1_2=tom_get_head_concSlotField_SlotFieldList(tom_match8_1_list1);tom_match8_1_list1=tom_get_tail_concSlotField_SlotFieldList(tom_match8_1_list1); if ( ( tom_is_fun_sym_SlotField(tom_match8_1_2) ||  false  ) ) { { String  tom_match8_1_2_name=tom_get_slot_SlotField_name(tom_match8_1_2); { tom.gom.adt.objects.types.ClassName  tom_match8_1_2_domain=tom_get_slot_SlotField_domain(tom_match8_1_2); { String  tom_fieldName=tom_match8_1_2_name; { tom.gom.adt.objects.types.ClassName  tom_domain=tom_match8_1_2_domain; if ( true ) {

        if (!GomEnvironment.getInstance().isBuiltinClass(tom_domain)) {
          res += "      case "/* Generated by TOM (version 2.4alpha): Do not edit this file */+index+": "/* Generated by TOM (version 2.4alpha): Do not edit this file */+fieldName(tom_fieldName)+" = (jjtraveler.reflective.VisitableVisitor) "/* Generated by TOM (version 2.4alpha): Do not edit this file */+argName+"; return this;\n"
;
          index++;
        }
       } } } } } } }tom_match8_1_end1=tom_get_tail_concSlotField_SlotFieldList(tom_match8_1_end1); } }tom_match8_1_list1=tom_match8_1_begin1; } } } } } } }

    return res;
  }
  
  /**
    * Generate the child list to be used as function parameter declaration
    * Each non builtin child has type VisitableVisitor
    */
  private String childListWithType(SlotFieldList slots) {
    String res = "";
    while(!slots.isEmptyconcSlotField()) {
      SlotField head = slots.getHeadconcSlotField();
      slots = slots.getTailconcSlotField();
       if(head instanceof  tom.gom.adt.objects.types.SlotField ) { { tom.gom.adt.objects.types.SlotField  tom_match9_1=(( tom.gom.adt.objects.types.SlotField )head); if ( ( tom_is_fun_sym_SlotField(tom_match9_1) ||  false  ) ) { { String  tom_match9_1_name=tom_get_slot_SlotField_name(tom_match9_1); { tom.gom.adt.objects.types.ClassName  tom_match9_1_domain=tom_get_slot_SlotField_domain(tom_match9_1); { String  tom_name=tom_match9_1_name; { tom.gom.adt.objects.types.ClassName  tom_domain=tom_match9_1_domain; if ( true ) {

          if (!res.equals("")) {
            res+= ", ";
          }
          if (!GomEnvironment.getInstance().isBuiltinClass(tom_domain)) {
            res+= "jjtraveler.reflective.VisitableVisitor "+fieldName(tom_name);
          } else {
            res+= fullClassName(tom_domain) + " "+fieldName(tom_name);
          }
         } } } } } } } }

    }
    return res;
  }

  /**
   * Generate code to initialize all members of the strategy
   */
  private String computeNewChilds(SlotFieldList slots, String argName) {
    String res = "";
     if(slots instanceof  tom.gom.adt.objects.types.SlotFieldList ) { { tom.gom.adt.objects.types.SlotFieldList  tom_match10_1=(( tom.gom.adt.objects.types.SlotFieldList )slots); if ( ( tom_is_fun_sym_concSlotField(tom_match10_1) ||  false  ) ) { { tom.gom.adt.objects.types.SlotFieldList  tom_match10_1_list1=tom_match10_1; { tom.gom.adt.objects.types.SlotFieldList  tom_match10_1_begin1=tom_match10_1_list1; { tom.gom.adt.objects.types.SlotFieldList  tom_match10_1_end1=tom_match10_1_list1; { while (!(tom_is_empty_concSlotField_SlotFieldList(tom_match10_1_end1))) {tom_match10_1_list1=tom_match10_1_end1; { { tom.gom.adt.objects.types.SlotField  tom_match10_1_2=tom_get_head_concSlotField_SlotFieldList(tom_match10_1_list1);tom_match10_1_list1=tom_get_tail_concSlotField_SlotFieldList(tom_match10_1_list1); if ( ( tom_is_fun_sym_SlotField(tom_match10_1_2) ||  false  ) ) { { String  tom_match10_1_2_name=tom_get_slot_SlotField_name(tom_match10_1_2); { tom.gom.adt.objects.types.ClassName  tom_match10_1_2_domain=tom_get_slot_SlotField_domain(tom_match10_1_2); { String  tom_fieldName=tom_match10_1_2_name; { tom.gom.adt.objects.types.ClassName  tom_domain=tom_match10_1_2_domain; if ( true ) {

        if (!GomEnvironment.getInstance().isBuiltinClass(tom_domain)) {
          res += "\n    "/* Generated by TOM (version 2.4alpha): Do not edit this file */+fullClassName(tom_domain)+" new"/* Generated by TOM (version 2.4alpha): Do not edit this file */+fieldName(tom_fieldName)+" = ("/* Generated by TOM (version 2.4alpha): Do not edit this file */+fullClassName(tom_domain)+") "/* Generated by TOM (version 2.4alpha): Do not edit this file */+fieldName(tom_fieldName)+".visit("/* Generated by TOM (version 2.4alpha): Do not edit this file */+argName+");\n"

;
        }
       } } } } } } }tom_match10_1_end1=tom_get_tail_concSlotField_SlotFieldList(tom_match10_1_end1); } }tom_match10_1_list1=tom_match10_1_begin1; } } } } } } }

    return res;
  }

  /**
    * Generate the computation of all new childs for the target
    */
  private String generateMembersInit() {
    String res = "";
     if(slotList instanceof  tom.gom.adt.objects.types.SlotFieldList ) { { tom.gom.adt.objects.types.SlotFieldList  tom_match11_1=(( tom.gom.adt.objects.types.SlotFieldList )slotList); if ( ( tom_is_fun_sym_concSlotField(tom_match11_1) ||  false  ) ) { { tom.gom.adt.objects.types.SlotFieldList  tom_match11_1_list1=tom_match11_1; { tom.gom.adt.objects.types.SlotFieldList  tom_match11_1_begin1=tom_match11_1_list1; { tom.gom.adt.objects.types.SlotFieldList  tom_match11_1_end1=tom_match11_1_list1; { while (!(tom_is_empty_concSlotField_SlotFieldList(tom_match11_1_end1))) {tom_match11_1_list1=tom_match11_1_end1; { { tom.gom.adt.objects.types.SlotField  tom_match11_1_2=tom_get_head_concSlotField_SlotFieldList(tom_match11_1_list1);tom_match11_1_list1=tom_get_tail_concSlotField_SlotFieldList(tom_match11_1_list1); if ( ( tom_is_fun_sym_SlotField(tom_match11_1_2) ||  false  ) ) { { String  tom_match11_1_2_name=tom_get_slot_SlotField_name(tom_match11_1_2); { String  tom_name=tom_match11_1_2_name; if ( true ) {

        res += "    this."+fieldName(tom_name)+" = "+fieldName(tom_name)+";\n";
       } } } } }tom_match11_1_end1=tom_get_tail_concSlotField_SlotFieldList(tom_match11_1_end1); } }tom_match11_1_list1=tom_match11_1_begin1; } } } } } } }

    return res;
  }

  /**
    * Generate the argument list for the operator construction, using the
    * values computed by computeNewChilds
    */
  private String genMakeArguments(SlotFieldList slots) {
    String res = "";
    while(!slots.isEmptyconcSlotField()) {
      SlotField head = slots.getHeadconcSlotField();
      slots = slots.getTailconcSlotField();
       if(head instanceof  tom.gom.adt.objects.types.SlotField ) { { tom.gom.adt.objects.types.SlotField  tom_match12_1=(( tom.gom.adt.objects.types.SlotField )head); if ( ( tom_is_fun_sym_SlotField(tom_match12_1) ||  false  ) ) { { String  tom_match12_1_name=tom_get_slot_SlotField_name(tom_match12_1); { tom.gom.adt.objects.types.ClassName  tom_match12_1_domain=tom_get_slot_SlotField_domain(tom_match12_1); { String  tom_name=tom_match12_1_name; { tom.gom.adt.objects.types.ClassName  tom_domain=tom_match12_1_domain; if ( true ) {

          if (!res.equals("")) {
            res+= ", ";
          }
          if (!GomEnvironment.getInstance().isBuiltinClass(tom_domain)) {
            res+= " new"+fieldName(tom_name);
          } else {
            res+= " "+fieldName(tom_name);
          }
         } } } } } } } }

    }
    return res;
  }
  private String fieldName(String fieldName) {
    return "_"+fieldName;
  }

  /** the class logger instance*/
  private Logger getLogger() {
    return Logger.getLogger(getClass().getName());
  }
}
