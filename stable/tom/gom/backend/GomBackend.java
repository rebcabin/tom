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

package tom.gom.backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import tom.gom.GomMessage;
import tom.gom.tools.GomEnvironment;
import tom.gom.tools.error.GomRuntimeException;

import tom.gom.adt.objects.*;
import tom.gom.adt.objects.types.*;

import tom.library.strategy.mutraveler.MuTraveler;
import jjtraveler.reflective.VisitableVisitor;
import jjtraveler.VisitFailure;

public class GomBackend {
  TemplateFactory templatefactory;
  private File tomHomePath;

  /* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file */ private static boolean tom_terms_equal_String( String  t1,  String  t2) {  return  (t1.equals(t2))  ;}  /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ private static boolean tom_terms_equal_SlotFieldList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_GomClass(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ClassName(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_GomClassList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_ClassNameList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_HookList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_fun_sym_VisitableFwdClass( tom.gom.adt.objects.types.GomClass  t) {  return  (t!=null) && t.isVisitableFwdClass()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VisitableFwdClass_className( tom.gom.adt.objects.types.GomClass  t) {  return  t.getclassName()  ;}private static  tom.gom.adt.objects.types.GomClass  tom_get_slot_VisitableFwdClass_fwd( tom.gom.adt.objects.types.GomClass  t) {  return  t.getfwd()  ;}private static boolean tom_is_fun_sym_AbstractTypeClass( tom.gom.adt.objects.types.GomClass  t) {  return  (t!=null) && t.isAbstractTypeClass()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_AbstractTypeClass_className( tom.gom.adt.objects.types.GomClass  t) {  return  t.getclassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_AbstractTypeClass_visitor( tom.gom.adt.objects.types.GomClass  t) {  return  t.getvisitor()  ;}private static  tom.gom.adt.objects.types.ClassNameList  tom_get_slot_AbstractTypeClass_sortList( tom.gom.adt.objects.types.GomClass  t) {  return  t.getsortList()  ;}private static boolean tom_is_fun_sym_SortClass( tom.gom.adt.objects.types.GomClass  t) {  return  (t!=null) && t.isSortClass()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_className( tom.gom.adt.objects.types.GomClass  t) {  return  t.getclassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_abstractType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getabstractType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_visitor( tom.gom.adt.objects.types.GomClass  t) {  return  t.getvisitor()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_SortClass_forward( tom.gom.adt.objects.types.GomClass  t) {  return  t.getforward()  ;}private static  tom.gom.adt.objects.types.ClassNameList  tom_get_slot_SortClass_operators( tom.gom.adt.objects.types.GomClass  t) {  return  t.getoperators()  ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_get_slot_SortClass_slots( tom.gom.adt.objects.types.GomClass  t) {  return  t.getslots()  ;}private static boolean tom_is_fun_sym_OperatorClass( tom.gom.adt.objects.types.GomClass  t) {  return  (t!=null) && t.isOperatorClass()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_className( tom.gom.adt.objects.types.GomClass  t) {  return  t.getclassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_abstractType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getabstractType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_extendsType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getextendsType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_mapping( tom.gom.adt.objects.types.GomClass  t) {  return  t.getmapping()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_sortName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getsortName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_OperatorClass_visitor( tom.gom.adt.objects.types.GomClass  t) {  return  t.getvisitor()  ;}private static  tom.gom.adt.objects.types.SlotFieldList  tom_get_slot_OperatorClass_slots( tom.gom.adt.objects.types.GomClass  t) {  return  t.getslots()  ;}private static  tom.gom.adt.objects.types.HookList  tom_get_slot_OperatorClass_hooks( tom.gom.adt.objects.types.GomClass  t) {  return  t.gethooks()  ;}private static boolean tom_is_fun_sym_VariadicOperatorClass( tom.gom.adt.objects.types.GomClass  t) {  return  (t!=null) && t.isVariadicOperatorClass()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_className( tom.gom.adt.objects.types.GomClass  t) {  return  t.getclassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_abstractType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getabstractType()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VariadicOperatorClass_sortName( tom.gom.adt.objects.types.GomClass  t) {  return  t.getsortName()  ;}private static  tom.gom.adt.objects.types.GomClass  tom_get_slot_VariadicOperatorClass_empty( tom.gom.adt.objects.types.GomClass  t) {  return  t.getempty()  ;}private static  tom.gom.adt.objects.types.GomClass  tom_get_slot_VariadicOperatorClass_cons( tom.gom.adt.objects.types.GomClass  t) {  return  t.getcons()  ;}private static boolean tom_is_fun_sym_VisitorClass( tom.gom.adt.objects.types.GomClass  t) {  return  (t!=null) && t.isVisitorClass()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_VisitorClass_className( tom.gom.adt.objects.types.GomClass  t) {  return  t.getclassName()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_slot_VisitorClass_sortClasses( tom.gom.adt.objects.types.GomClass  t) {  return  t.getsortClasses()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_slot_VisitorClass_operatorClasses( tom.gom.adt.objects.types.GomClass  t) {  return  t.getoperatorClasses()  ;}private static boolean tom_is_fun_sym_FwdClass( tom.gom.adt.objects.types.GomClass  t) {  return  (t!=null) && t.isFwdClass()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_FwdClass_className( tom.gom.adt.objects.types.GomClass  t) {  return  t.getclassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_FwdClass_visitor( tom.gom.adt.objects.types.GomClass  t) {  return  t.getvisitor()  ;}private static  tom.gom.adt.objects.types.ClassNameList  tom_get_slot_FwdClass_importedVisitors( tom.gom.adt.objects.types.GomClass  t) {  return  t.getimportedVisitors()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_FwdClass_abstractType( tom.gom.adt.objects.types.GomClass  t) {  return  t.getabstractType()  ;}private static  tom.gom.adt.objects.types.ClassNameList  tom_get_slot_FwdClass_importedAbstractTypes( tom.gom.adt.objects.types.GomClass  t) {  return  t.getimportedAbstractTypes()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_slot_FwdClass_sortClasses( tom.gom.adt.objects.types.GomClass  t) {  return  t.getsortClasses()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_slot_FwdClass_operatorClasses( tom.gom.adt.objects.types.GomClass  t) {  return  t.getoperatorClasses()  ;}private static boolean tom_is_fun_sym_TomMapping( tom.gom.adt.objects.types.GomClass  t) {  return  (t!=null) && t.isTomMapping()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_TomMapping_className( tom.gom.adt.objects.types.GomClass  t) {  return  t.getclassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_get_slot_TomMapping_basicStrategy( tom.gom.adt.objects.types.GomClass  t) {  return  t.getbasicStrategy()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_slot_TomMapping_sortClasses( tom.gom.adt.objects.types.GomClass  t) {  return  t.getsortClasses()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_slot_TomMapping_operatorClasses( tom.gom.adt.objects.types.GomClass  t) {  return  t.getoperatorClasses()  ;}private static boolean tom_is_fun_sym_ClassName( tom.gom.adt.objects.types.ClassName  t) {  return  (t!=null) && t.isClassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_make_ClassName( String  t0,  String  t1) { return  tom.gom.adt.objects.types.classname.ClassName.make(t0, t1); }private static  String  tom_get_slot_ClassName_pkg( tom.gom.adt.objects.types.ClassName  t) {  return  t.getpkg()  ;}private static  String  tom_get_slot_ClassName_name( tom.gom.adt.objects.types.ClassName  t) {  return  t.getname()  ;}private static boolean tom_is_fun_sym_concGomClass( tom.gom.adt.objects.types.GomClassList  t) {  return  t instanceof tom.gom.adt.objects.types.gomclasslist.ConsconcGomClass || t instanceof tom.gom.adt.objects.types.gomclasslist.EmptyconcGomClass  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_empty_list_concGomClass() { return  tom.gom.adt.objects.types.gomclasslist.EmptyconcGomClass.make() ; }private static  tom.gom.adt.objects.types.GomClassList  tom_cons_list_concGomClass( tom.gom.adt.objects.types.GomClass  e,  tom.gom.adt.objects.types.GomClassList  l) { return  tom.gom.adt.objects.types.gomclasslist.ConsconcGomClass.make(e,l) ; }private static  tom.gom.adt.objects.types.GomClass  tom_get_head_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) {  return  l.getHeadconcGomClass()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_get_tail_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) {  return  l.getTailconcGomClass()  ;}private static boolean tom_is_empty_concGomClass_GomClassList( tom.gom.adt.objects.types.GomClassList  l) {  return  l.isEmptyconcGomClass()  ;}private static  tom.gom.adt.objects.types.GomClassList  tom_append_list_concGomClass( tom.gom.adt.objects.types.GomClassList  l1,  tom.gom.adt.objects.types.GomClassList  l2) {    if(tom_is_empty_concGomClass_GomClassList(l1)) {     return l2;    } else if(tom_is_empty_concGomClass_GomClassList(l2)) {     return l1;    } else if(tom_is_empty_concGomClass_GomClassList(( tom.gom.adt.objects.types.GomClassList )tom_get_tail_concGomClass_GomClassList(l1))) {     return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(( tom.gom.adt.objects.types.GomClass )tom_get_head_concGomClass_GomClassList(l1),l2);    } else {      return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(( tom.gom.adt.objects.types.GomClass )tom_get_head_concGomClass_GomClassList(l1),tom_append_list_concGomClass(( tom.gom.adt.objects.types.GomClassList )tom_get_tail_concGomClass_GomClassList(l1),l2));    }   }  private static  tom.gom.adt.objects.types.GomClassList  tom_get_slice_concGomClass( tom.gom.adt.objects.types.GomClassList  begin,  tom.gom.adt.objects.types.GomClassList  end) {    if(tom_terms_equal_GomClassList(begin,end)) {      return ( tom.gom.adt.objects.types.GomClassList )tom_empty_list_concGomClass();    } else {      return ( tom.gom.adt.objects.types.GomClassList )tom_cons_list_concGomClass(( tom.gom.adt.objects.types.GomClass )tom_get_head_concGomClass_GomClassList(begin),( tom.gom.adt.objects.types.GomClassList )tom_get_slice_concGomClass(( tom.gom.adt.objects.types.GomClassList )tom_get_tail_concGomClass_GomClassList(begin),end));    }   }   /* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file */  


  GomBackend(TemplateFactory templatefactory, File tomHomePath) {
    this.templatefactory = templatefactory;
    this.tomHomePath = tomHomePath;
  }

  private GomEnvironment environment() {
    return GomEnvironment.getInstance();
  }

  private Map mappingForMappingName = new HashMap();

  public int generate(GomClassList classList) {
    int errno = 0;
    // populate the mappingForMappingName Map
     if(classList instanceof  tom.gom.adt.objects.types.GomClassList ) { { tom.gom.adt.objects.types.GomClassList  tom_match1_1=(( tom.gom.adt.objects.types.GomClassList )classList); if ( ( tom_is_fun_sym_concGomClass(tom_match1_1) ||  false  ) ) { { tom.gom.adt.objects.types.GomClassList  tom_match1_1_list1=tom_match1_1; { tom.gom.adt.objects.types.GomClassList  tom_match1_1_begin1=tom_match1_1_list1; { tom.gom.adt.objects.types.GomClassList  tom_match1_1_end1=tom_match1_1_list1; { while (!(tom_is_empty_concGomClass_GomClassList(tom_match1_1_end1))) {tom_match1_1_list1=tom_match1_1_end1; { { tom.gom.adt.objects.types.GomClass  tom_match1_1_2=tom_get_head_concGomClass_GomClassList(tom_match1_1_list1);tom_match1_1_list1=tom_get_tail_concGomClass_GomClassList(tom_match1_1_list1); if ( ( tom_is_fun_sym_TomMapping(tom_match1_1_2) ||  false  ) ) { { tom.gom.adt.objects.types.GomClass  tom_mapping=tom_match1_1_2; { tom.gom.adt.objects.types.ClassName  tom_match1_1_2_className=tom_get_slot_TomMapping_className(tom_match1_1_2); { tom.gom.adt.objects.types.ClassName  tom_mappingName=tom_match1_1_2_className; if ( true ) {

        mappingForMappingName.put(tom_mappingName,tom_mapping);
       } } } } } }tom_match1_1_end1=tom_get_tail_concGomClass_GomClassList(tom_match1_1_end1); } }tom_match1_1_list1=tom_match1_1_begin1; } } } } } } }

    // generate a class for each element of the list
    while (!classList.isEmptyconcGomClass()) {
      GomClass gomclass = classList.getHeadconcGomClass();
      classList = classList.getTailconcGomClass();
      errno += generateClass(gomclass);
    }
    return 1;
  }

  /*
   * Create template classes for the different classes to generate
   */
  public int generateClass(GomClass gomclass) {
     if(gomclass instanceof  tom.gom.adt.objects.types.GomClass ) { { tom.gom.adt.objects.types.GomClass  tom_match2_1=(( tom.gom.adt.objects.types.GomClass )gomclass); if ( ( tom_is_fun_sym_TomMapping(tom_match2_1) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match2_1_className=tom_get_slot_TomMapping_className(tom_match2_1); { tom.gom.adt.objects.types.ClassName  tom_match2_1_basicStrategy=tom_get_slot_TomMapping_basicStrategy(tom_match2_1); { tom.gom.adt.objects.types.GomClassList  tom_match2_1_sortClasses=tom_get_slot_TomMapping_sortClasses(tom_match2_1); { tom.gom.adt.objects.types.GomClassList  tom_match2_1_operatorClasses=tom_get_slot_TomMapping_operatorClasses(tom_match2_1); if ( ( tom_is_fun_sym_ClassName(tom_match2_1_className) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_className=tom_match2_1_className; { String  tom_match2_1_className_pkg=tom_get_slot_ClassName_pkg(tom_match2_1_className); { String  tom_match2_1_className_name=tom_get_slot_ClassName_name(tom_match2_1_className); { String  tom_pkg=tom_match2_1_className_pkg; { String  tom_name=tom_match2_1_className_name; { tom.gom.adt.objects.types.ClassName  tom_basicStrategy=tom_match2_1_basicStrategy; { tom.gom.adt.objects.types.GomClassList  tom_sortClasses=tom_match2_1_sortClasses; { tom.gom.adt.objects.types.GomClassList  tom_ops=tom_match2_1_operatorClasses; if ( true ) {

        TemplateClass mapping = templatefactory.makeTomMappingTemplate(tom_className,tom_basicStrategy,tom_sortClasses,tom_ops);
        mapping.generateFile();

        TemplateClass stratMapping = 
          new tom.gom.backend.strategy.StratMappingTemplate(tom_make_ClassName(tom_pkg,"_"+tom_name),tom_ops);
        stratMapping.generateFile();
        return 1;
       } } } } } } } } } } } } } } } if ( ( tom_is_fun_sym_FwdClass(tom_match2_1) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match2_1_className=tom_get_slot_FwdClass_className(tom_match2_1); { tom.gom.adt.objects.types.ClassName  tom_match2_1_visitor=tom_get_slot_FwdClass_visitor(tom_match2_1); { tom.gom.adt.objects.types.ClassNameList  tom_match2_1_importedVisitors=tom_get_slot_FwdClass_importedVisitors(tom_match2_1); { tom.gom.adt.objects.types.ClassName  tom_match2_1_abstractType=tom_get_slot_FwdClass_abstractType(tom_match2_1); { tom.gom.adt.objects.types.ClassNameList  tom_match2_1_importedAbstractTypes=tom_get_slot_FwdClass_importedAbstractTypes(tom_match2_1); { tom.gom.adt.objects.types.GomClassList  tom_match2_1_sortClasses=tom_get_slot_FwdClass_sortClasses(tom_match2_1); { tom.gom.adt.objects.types.GomClassList  tom_match2_1_operatorClasses=tom_get_slot_FwdClass_operatorClasses(tom_match2_1); { tom.gom.adt.objects.types.ClassName  tom_className=tom_match2_1_className; { tom.gom.adt.objects.types.ClassName  tom_visitorClass=tom_match2_1_visitor; { tom.gom.adt.objects.types.ClassNameList  tom_importedVisitors=tom_match2_1_importedVisitors; { tom.gom.adt.objects.types.ClassName  tom_abstractType=tom_match2_1_abstractType; { tom.gom.adt.objects.types.ClassNameList  tom_imported=tom_match2_1_importedAbstractTypes; { tom.gom.adt.objects.types.GomClassList  tom_sortClasses=tom_match2_1_sortClasses; { tom.gom.adt.objects.types.GomClassList  tom_ops=tom_match2_1_operatorClasses; if ( true ) {







        TemplateClass fwd = templatefactory.makeForwardTemplate(tom_className,tom_visitorClass,tom_importedVisitors,tom_abstractType,tom_imported,tom_sortClasses,tom_ops);
        fwd.generateFile();
        return 1;
       } } } } } } } } } } } } } } } } if ( ( tom_is_fun_sym_VisitableFwdClass(tom_match2_1) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match2_1_className=tom_get_slot_VisitableFwdClass_className(tom_match2_1); { tom.gom.adt.objects.types.GomClass  tom_match2_1_fwd=tom_get_slot_VisitableFwdClass_fwd(tom_match2_1); { tom.gom.adt.objects.types.ClassName  tom_className=tom_match2_1_className; if ( ( tom_is_fun_sym_FwdClass(tom_match2_1_fwd) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match2_1_fwd_className=tom_get_slot_FwdClass_className(tom_match2_1_fwd); { tom.gom.adt.objects.types.ClassName  tom_fwdClass=tom_match2_1_fwd_className; if ( true ) {

        TemplateClass visitablefwd = templatefactory.makeVisitableForwardTemplate(tom_className,tom_fwdClass);
        visitablefwd.generateFile();
        return 1;
       } } } } } } } } if ( ( tom_is_fun_sym_VisitorClass(tom_match2_1) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match2_1_className=tom_get_slot_VisitorClass_className(tom_match2_1); { tom.gom.adt.objects.types.GomClassList  tom_match2_1_sortClasses=tom_get_slot_VisitorClass_sortClasses(tom_match2_1); { tom.gom.adt.objects.types.GomClassList  tom_match2_1_operatorClasses=tom_get_slot_VisitorClass_operatorClasses(tom_match2_1); { tom.gom.adt.objects.types.ClassName  tom_className=tom_match2_1_className; { tom.gom.adt.objects.types.GomClassList  tom_sortClasses=tom_match2_1_sortClasses; { tom.gom.adt.objects.types.GomClassList  tom_ops=tom_match2_1_operatorClasses; if ( true ) {

        TemplateClass visitor = templatefactory.makeVisitorTemplate(tom_className,tom_sortClasses,tom_ops);
        visitor.generateFile();
        return 1;
       } } } } } } } } if ( ( tom_is_fun_sym_AbstractTypeClass(tom_match2_1) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match2_1_className=tom_get_slot_AbstractTypeClass_className(tom_match2_1); { tom.gom.adt.objects.types.ClassName  tom_match2_1_visitor=tom_get_slot_AbstractTypeClass_visitor(tom_match2_1); { tom.gom.adt.objects.types.ClassNameList  tom_match2_1_sortList=tom_get_slot_AbstractTypeClass_sortList(tom_match2_1); { tom.gom.adt.objects.types.ClassName  tom_className=tom_match2_1_className; { tom.gom.adt.objects.types.ClassName  tom_visitorName=tom_match2_1_visitor; { tom.gom.adt.objects.types.ClassNameList  tom_sortList=tom_match2_1_sortList; if ( true ) {



        TemplateClass abstracttype = templatefactory.makeAbstractTypeTemplate(tom_className,tom_visitorName,tom_sortList);
        abstracttype.generateFile();
        return 1;
       } } } } } } } } if ( ( tom_is_fun_sym_SortClass(tom_match2_1) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match2_1_className=tom_get_slot_SortClass_className(tom_match2_1); { tom.gom.adt.objects.types.ClassName  tom_match2_1_abstractType=tom_get_slot_SortClass_abstractType(tom_match2_1); { tom.gom.adt.objects.types.ClassName  tom_match2_1_visitor=tom_get_slot_SortClass_visitor(tom_match2_1); { tom.gom.adt.objects.types.ClassNameList  tom_match2_1_operators=tom_get_slot_SortClass_operators(tom_match2_1); { tom.gom.adt.objects.types.SlotFieldList  tom_match2_1_slots=tom_get_slot_SortClass_slots(tom_match2_1); { tom.gom.adt.objects.types.ClassName  tom_className=tom_match2_1_className; { tom.gom.adt.objects.types.ClassName  tom_abstracttype=tom_match2_1_abstractType; { tom.gom.adt.objects.types.ClassName  tom_visitorName=tom_match2_1_visitor; { tom.gom.adt.objects.types.ClassNameList  tom_ops=tom_match2_1_operators; { tom.gom.adt.objects.types.SlotFieldList  tom_slots=tom_match2_1_slots; if ( true ) {





        TemplateClass sort = templatefactory.makeSortTemplate(tom_className,tom_abstracttype,tom_visitorName,tom_ops,tom_slots);
        sort.generateFile();
        return 1;
       } } } } } } } } } } } } if ( ( tom_is_fun_sym_OperatorClass(tom_match2_1) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match2_1_className=tom_get_slot_OperatorClass_className(tom_match2_1); { tom.gom.adt.objects.types.ClassName  tom_match2_1_abstractType=tom_get_slot_OperatorClass_abstractType(tom_match2_1); { tom.gom.adt.objects.types.ClassName  tom_match2_1_extendsType=tom_get_slot_OperatorClass_extendsType(tom_match2_1); { tom.gom.adt.objects.types.ClassName  tom_match2_1_mapping=tom_get_slot_OperatorClass_mapping(tom_match2_1); { tom.gom.adt.objects.types.ClassName  tom_match2_1_sortName=tom_get_slot_OperatorClass_sortName(tom_match2_1); { tom.gom.adt.objects.types.ClassName  tom_match2_1_visitor=tom_get_slot_OperatorClass_visitor(tom_match2_1); { tom.gom.adt.objects.types.SlotFieldList  tom_match2_1_slots=tom_get_slot_OperatorClass_slots(tom_match2_1); { tom.gom.adt.objects.types.HookList  tom_match2_1_hooks=tom_get_slot_OperatorClass_hooks(tom_match2_1); { tom.gom.adt.objects.types.ClassName  tom_className=tom_match2_1_className; { tom.gom.adt.objects.types.ClassName  tom_abstracttype=tom_match2_1_abstractType; { tom.gom.adt.objects.types.ClassName  tom_extendstype=tom_match2_1_extendsType; { tom.gom.adt.objects.types.ClassName  tom_mapping=tom_match2_1_mapping; { tom.gom.adt.objects.types.ClassName  tom_sort=tom_match2_1_sortName; { tom.gom.adt.objects.types.ClassName  tom_visitorName=tom_match2_1_visitor; { tom.gom.adt.objects.types.SlotFieldList  tom_slots=tom_match2_1_slots; { tom.gom.adt.objects.types.HookList  tom_hooks=tom_match2_1_hooks; if ( true ) {








        GomClass mappingClass = (GomClass)mappingForMappingName.get(tom_mapping);
        TemplateClass operator = templatefactory.makeOperatorTemplate(
            tomHomePath,
            tom_className,
            tom_abstracttype,
            tom_extendstype,
            tom_sort,
            tom_visitorName,
            tom_slots,
            tom_hooks,
            getMappingTemplate(mappingClass));
        operator.generateFile();

        TemplateClass isOpStrat = new tom.gom.backend.strategy.IsOpTemplate(tom_className);
        isOpStrat.generateFile();

        TemplateClass sOpStrat = new tom.gom.backend.strategy.SOpTemplate(tom_className,tom_slots);
        sOpStrat.generateFile();

        TemplateClass makeOpStrat = new tom.gom.backend.strategy.MakeOpTemplate(tom_className,tom_slots);
        makeOpStrat.generateFile();
        return 1;
       } } } } } } } } } } } } } } } } } } if ( ( tom_is_fun_sym_VariadicOperatorClass(tom_match2_1) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match2_1_className=tom_get_slot_VariadicOperatorClass_className(tom_match2_1); { tom.gom.adt.objects.types.ClassName  tom_match2_1_abstractType=tom_get_slot_VariadicOperatorClass_abstractType(tom_match2_1); { tom.gom.adt.objects.types.ClassName  tom_match2_1_sortName=tom_get_slot_VariadicOperatorClass_sortName(tom_match2_1); { tom.gom.adt.objects.types.GomClass  tom_match2_1_empty=tom_get_slot_VariadicOperatorClass_empty(tom_match2_1); { tom.gom.adt.objects.types.GomClass  tom_match2_1_cons=tom_get_slot_VariadicOperatorClass_cons(tom_match2_1); { tom.gom.adt.objects.types.ClassName  tom_className=tom_match2_1_className; { tom.gom.adt.objects.types.ClassName  tom_abstracttype=tom_match2_1_abstractType; { tom.gom.adt.objects.types.ClassName  tom_sort=tom_match2_1_sortName; { tom.gom.adt.objects.types.GomClass  tom_empty=tom_match2_1_empty; { tom.gom.adt.objects.types.GomClass  tom_cons=tom_match2_1_cons; if ( true ) {





        TemplateClass operator = templatefactory.makeVariadicOperatorTemplate(tom_className,tom_abstracttype,tom_sort,tom_empty,tom_cons);
        operator.generateFile();
        return 1;
       } } } } } } } } } } } } } }

    throw new GomRuntimeException("Trying to generate code for a strange class: "+gomclass);
  }

  public TemplateClass getMappingTemplate(GomClass mapping) {
    TemplateClass mappingTemplate = null;
     if(mapping instanceof  tom.gom.adt.objects.types.GomClass ) { { tom.gom.adt.objects.types.GomClass  tom_match3_1=(( tom.gom.adt.objects.types.GomClass )mapping); if ( ( tom_is_fun_sym_TomMapping(tom_match3_1) ||  false  ) ) { { tom.gom.adt.objects.types.ClassName  tom_match3_1_className=tom_get_slot_TomMapping_className(tom_match3_1); { tom.gom.adt.objects.types.ClassName  tom_match3_1_basicStrategy=tom_get_slot_TomMapping_basicStrategy(tom_match3_1); { tom.gom.adt.objects.types.GomClassList  tom_match3_1_sortClasses=tom_get_slot_TomMapping_sortClasses(tom_match3_1); { tom.gom.adt.objects.types.GomClassList  tom_match3_1_operatorClasses=tom_get_slot_TomMapping_operatorClasses(tom_match3_1); { tom.gom.adt.objects.types.ClassName  tom_mappingName=tom_match3_1_className; { tom.gom.adt.objects.types.ClassName  tom_basicStrategy=tom_match3_1_basicStrategy; { tom.gom.adt.objects.types.GomClassList  tom_sortClasses=tom_match3_1_sortClasses; { tom.gom.adt.objects.types.GomClassList  tom_ops=tom_match3_1_operatorClasses; if ( true ) {




        mappingTemplate = templatefactory.makeTomMappingTemplate(tom_mappingName,tom_basicStrategy,tom_sortClasses,tom_ops);
       } } } } } } } } } } } }

    return mappingTemplate;
  }
}
