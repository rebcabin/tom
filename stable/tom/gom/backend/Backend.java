/* Generated by TOM (version 2.6rc2): Do not edit this file *//*
 * Gom
 *
 * Copyright (c) 2006-2008, INRIA
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
import tom.gom.tools.error.GomRuntimeException;

import tom.gom.adt.objects.*;
import tom.gom.adt.objects.types.*;

public class Backend {
  TemplateFactory templatefactory;
  private File tomHomePath;
  private List importList = null;
  private boolean strategySupport = true;
  private boolean multithread = false;

  /* Generated by TOM (version 2.6rc2): Do not edit this file *//* Generated by TOM (version 2.6rc2): Do not edit this file *//* Generated by TOM (version 2.6rc2): Do not edit this file */  /* Generated by TOM (version 2.6rc2): Do not edit this file */    private static   tom.gom.adt.objects.types.GomClassList  tom_append_list_ConcGomClass( tom.gom.adt.objects.types.GomClassList l1,  tom.gom.adt.objects.types.GomClassList  l2) {     if( l1.isEmptyConcGomClass() ) {       return l2;     } else if( l2.isEmptyConcGomClass() ) {       return l1;     } else if(  l1.getTailConcGomClass() .isEmptyConcGomClass() ) {       return  tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass.make( l1.getHeadConcGomClass() ,l2) ;     } else {       return  tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass.make( l1.getHeadConcGomClass() ,tom_append_list_ConcGomClass( l1.getTailConcGomClass() ,l2)) ;     }   }   private static   tom.gom.adt.objects.types.GomClassList  tom_get_slice_ConcGomClass( tom.gom.adt.objects.types.GomClassList  begin,  tom.gom.adt.objects.types.GomClassList  end, tom.gom.adt.objects.types.GomClassList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyConcGomClass()  ||  (end== tom.gom.adt.objects.types.gomclasslist.EmptyConcGomClass.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass.make( begin.getHeadConcGomClass() ,( tom.gom.adt.objects.types.GomClassList )tom_get_slice_ConcGomClass( begin.getTailConcGomClass() ,end,tail)) ;   }    /* Generated by TOM (version 2.6rc2): Do not edit this file *//* Generated by TOM (version 2.6rc2): Do not edit this file */ /* Generated by TOM (version 2.6rc2): Do not edit this file */ /* Generated by TOM (version 2.6rc2): Do not edit this file */   


  Backend(TemplateFactory templatefactory,
          File tomHomePath,
          boolean strategySupport,
          boolean multithread,
          List importList) {
    this.templatefactory = templatefactory;
    this.tomHomePath = tomHomePath;
    this.strategySupport = strategySupport;
    this.multithread = multithread;
    this.importList = importList;
  }

  public int generate(GomClassList classList) {
    int errno = 0;
    Set mappingSet = new HashSet();
    Map generators =
      new HashMap();
    // prepare stuff for the mappings
    {{ Object tomMatch351NameNumber_freshVar_0=classList;if ( (tomMatch351NameNumber_freshVar_0 instanceof tom.gom.adt.objects.types.GomClassList) ) {{  tom.gom.adt.objects.types.GomClassList  tomMatch351NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClassList )tomMatch351NameNumber_freshVar_0);{  tom.gom.adt.objects.types.GomClassList  tomMatch351NameNumber_freshVar_1=tomMatch351NameNumberfreshSubject_1;if ( ((tomMatch351NameNumber_freshVar_1 instanceof tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass) || (tomMatch351NameNumber_freshVar_1 instanceof tom.gom.adt.objects.types.gomclasslist.EmptyConcGomClass)) ) {{  tom.gom.adt.objects.types.GomClassList  tomMatch351NameNumber_begin_3=tomMatch351NameNumber_freshVar_1;{  tom.gom.adt.objects.types.GomClassList  tomMatch351NameNumber_end_4=tomMatch351NameNumber_freshVar_1;do {{{  tom.gom.adt.objects.types.GomClassList  tomMatch351NameNumber_freshVar_2=tomMatch351NameNumber_end_4;if (!( tomMatch351NameNumber_freshVar_2.isEmptyConcGomClass() )) {{  tom.gom.adt.objects.types.GomClass  tomMatch351NameNumber_freshVar_8= tomMatch351NameNumber_freshVar_2.getHeadConcGomClass() ;if ( (tomMatch351NameNumber_freshVar_8 instanceof tom.gom.adt.objects.types.gomclass.TomMapping) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch351NameNumber_freshVar_7= tomMatch351NameNumber_freshVar_8.getClassName() ;{  tom.gom.adt.objects.types.ClassName  tomMatch351NameNumber_freshVar_11=tomMatch351NameNumber_freshVar_7;if ( (tomMatch351NameNumber_freshVar_11 instanceof tom.gom.adt.objects.types.classname.ClassName) ) {{  String  tomMatch351NameNumber_freshVar_9= tomMatch351NameNumber_freshVar_11.getPkg() ;{  String  tomMatch351NameNumber_freshVar_10= tomMatch351NameNumber_freshVar_11.getName() ;{  tom.gom.adt.objects.types.GomClass  tom_gomclass= tomMatch351NameNumber_freshVar_2.getHeadConcGomClass() ;{  tom.gom.adt.objects.types.GomClassList  tomMatch351NameNumber_freshVar_5= tomMatch351NameNumber_freshVar_2.getTailConcGomClass() ;if ( true ) {



        ClassName smappingclass =  tom.gom.adt.objects.types.classname.ClassName.make(tomMatch351NameNumber_freshVar_9, "_"+tomMatch351NameNumber_freshVar_10) ;
        GomClass nGomClass =
          tom_gomclass.setClassName(smappingclass);
        TemplateClass stratMapping =
          new tom.gom.backend.strategy.StratMappingTemplate(nGomClass);
        generators.put(smappingclass,stratMapping);

        TemplateClass mapping = null;
        if(strategySupport) {
          mapping =
            templatefactory.makeTomMappingTemplate(tom_gomclass,stratMapping);
        } else {
          mapping =
            templatefactory.makeTomMappingTemplate(tom_gomclass,null);
        }
        mappingSet.add(mapping);
        generators.put(tomMatch351NameNumber_freshVar_7,mapping);
      }}}}}}}}}}}}if ( tomMatch351NameNumber_end_4.isEmptyConcGomClass() ) {tomMatch351NameNumber_end_4=tomMatch351NameNumber_begin_3;} else {tomMatch351NameNumber_end_4= tomMatch351NameNumber_end_4.getTailConcGomClass() ;}}} while(!( (tomMatch351NameNumber_end_4==tomMatch351NameNumber_begin_3) ));}}}}}}}}

    // generate a class for each element of the list
    while (!classList.isEmptyConcGomClass()) {
      GomClass gomclass = classList.getHeadConcGomClass();
      classList = classList.getTailConcGomClass();
      errno += generateClass(gomclass,generators);
    }
    /* The mappings may need to access generators */
    Iterator it = mappingSet.iterator();
    while (it.hasNext()) {
      ((MappingTemplateClass)it.next()).addTemplates(generators);
    }
    it = generators.keySet().iterator();
    while (it.hasNext()) {
      ((TemplateClass)generators.get(it.next())).generateFile();
    }

    return 1;
  }

  /*
   * Create template classes for the different classes to generate
   */
  public int generateClass(
      GomClass gomclass,
      Map generators) {
    {{ Object tomMatch352NameNumber_freshVar_0=gomclass;if ( (tomMatch352NameNumber_freshVar_0 instanceof tom.gom.adt.objects.types.GomClass) ) {{  tom.gom.adt.objects.types.GomClass  tomMatch352NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClass )tomMatch352NameNumber_freshVar_0);{  tom.gom.adt.objects.types.GomClass  tomMatch352NameNumber_freshVar_2=tomMatch352NameNumberfreshSubject_1;if ( (tomMatch352NameNumber_freshVar_2 instanceof tom.gom.adt.objects.types.gomclass.TomMapping) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch352NameNumber_freshVar_1= tomMatch352NameNumber_freshVar_2.getClassName() ;if ( true ) {

        /* It was processed by the caller: check it is already in generators */
        if (!generators.containsKey(tomMatch352NameNumber_freshVar_1)) {
          throw new GomRuntimeException(
              "Mapping should be processed before generateClass is called");
        }
        return 1;
      }}}}}}}{ Object tomMatch352NameNumber_freshVar_3=gomclass;if ( (tomMatch352NameNumber_freshVar_3 instanceof tom.gom.adt.objects.types.GomClass) ) {{  tom.gom.adt.objects.types.GomClass  tomMatch352NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClass )tomMatch352NameNumber_freshVar_3);{  tom.gom.adt.objects.types.GomClass  tomMatch352NameNumber_freshVar_6=tomMatch352NameNumberfreshSubject_1;if ( (tomMatch352NameNumber_freshVar_6 instanceof tom.gom.adt.objects.types.gomclass.AbstractTypeClass) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch352NameNumber_freshVar_4= tomMatch352NameNumber_freshVar_6.getClassName() ;{  tom.gom.adt.objects.types.ClassName  tomMatch352NameNumber_freshVar_5= tomMatch352NameNumber_freshVar_6.getMapping() ;if ( true ) {

        TemplateClass abstracttype =
          templatefactory.makeAbstractTypeTemplate(
              tomHomePath,
              importList,
              gomclass,
              (TemplateClass)generators.get(tomMatch352NameNumber_freshVar_5));
        generators.put(tomMatch352NameNumber_freshVar_4,abstracttype);
        return 1;
      }}}}}}}}{ Object tomMatch352NameNumber_freshVar_7=gomclass;if ( (tomMatch352NameNumber_freshVar_7 instanceof tom.gom.adt.objects.types.GomClass) ) {{  tom.gom.adt.objects.types.GomClass  tomMatch352NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClass )tomMatch352NameNumber_freshVar_7);{  tom.gom.adt.objects.types.GomClass  tomMatch352NameNumber_freshVar_10=tomMatch352NameNumberfreshSubject_1;if ( (tomMatch352NameNumber_freshVar_10 instanceof tom.gom.adt.objects.types.gomclass.SortClass) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch352NameNumber_freshVar_8= tomMatch352NameNumber_freshVar_10.getClassName() ;{  tom.gom.adt.objects.types.ClassName  tomMatch352NameNumber_freshVar_9= tomMatch352NameNumber_freshVar_10.getMapping() ;if ( true ) {

        TemplateClass sort =
          templatefactory.makeSortTemplate(
              tomHomePath,
              importList,
              gomclass,
              (TemplateClass)generators.get(tomMatch352NameNumber_freshVar_9));
        generators.put(tomMatch352NameNumber_freshVar_8,sort);
        return 1;
      }}}}}}}}{ Object tomMatch352NameNumber_freshVar_11=gomclass;if ( (tomMatch352NameNumber_freshVar_11 instanceof tom.gom.adt.objects.types.GomClass) ) {{  tom.gom.adt.objects.types.GomClass  tomMatch352NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClass )tomMatch352NameNumber_freshVar_11);{  tom.gom.adt.objects.types.GomClass  tomMatch352NameNumber_freshVar_14=tomMatch352NameNumberfreshSubject_1;if ( (tomMatch352NameNumber_freshVar_14 instanceof tom.gom.adt.objects.types.gomclass.OperatorClass) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch352NameNumber_freshVar_12= tomMatch352NameNumber_freshVar_14.getClassName() ;{  tom.gom.adt.objects.types.ClassName  tomMatch352NameNumber_freshVar_13= tomMatch352NameNumber_freshVar_14.getMapping() ;if ( true ) {

        TemplateClass operator = templatefactory.makeOperatorTemplate(
            tomHomePath,
            importList,
            gomclass,
            (TemplateClass)generators.get(tomMatch352NameNumber_freshVar_13),
            multithread);
        generators.put(tomMatch352NameNumber_freshVar_12,operator);

        TemplateClass sOpStrat =
          new tom.gom.backend.strategy.SOpTemplate(gomclass);
        sOpStrat.generateFile();

        TemplateClass isOpStrat =
          new tom.gom.backend.strategy.IsOpTemplate(gomclass);
        isOpStrat.generateFile();


        TemplateClass makeOpStrat = new tom.gom.backend.strategy.MakeOpTemplate(gomclass);
        makeOpStrat.generateFile();
       return 1;
      }}}}}}}}{ Object tomMatch352NameNumber_freshVar_15=gomclass;if ( (tomMatch352NameNumber_freshVar_15 instanceof tom.gom.adt.objects.types.GomClass) ) {{  tom.gom.adt.objects.types.GomClass  tomMatch352NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClass )tomMatch352NameNumber_freshVar_15);{  tom.gom.adt.objects.types.GomClass  tomMatch352NameNumber_freshVar_20=tomMatch352NameNumberfreshSubject_1;if ( (tomMatch352NameNumber_freshVar_20 instanceof tom.gom.adt.objects.types.gomclass.VariadicOperatorClass) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch352NameNumber_freshVar_16= tomMatch352NameNumber_freshVar_20.getClassName() ;{  tom.gom.adt.objects.types.ClassName  tomMatch352NameNumber_freshVar_17= tomMatch352NameNumber_freshVar_20.getMapping() ;{  tom.gom.adt.objects.types.GomClass  tomMatch352NameNumber_freshVar_18= tomMatch352NameNumber_freshVar_20.getEmpty() ;{  tom.gom.adt.objects.types.GomClass  tomMatch352NameNumber_freshVar_19= tomMatch352NameNumber_freshVar_20.getCons() ;if ( true ) {




        TemplateClass operator =
          templatefactory.makeVariadicOperatorTemplate(
              tomHomePath,
              importList,
              gomclass,
              (TemplateClass)generators.get(tomMatch352NameNumber_freshVar_17));
        generators.put(tomMatch352NameNumber_freshVar_16,operator);
        /* Generate files for cons and empty */
        int ret = 1;
        ret+=generateClass(tomMatch352NameNumber_freshVar_18,generators);
        ret+=generateClass(tomMatch352NameNumber_freshVar_19,generators);

        return ret;
      }}}}}}}}}}}

    throw new GomRuntimeException("Trying to generate code for a strange class: "+gomclass);
  }
}
