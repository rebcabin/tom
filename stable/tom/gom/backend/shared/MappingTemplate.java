/* Generated by TOM (version 2.6alpha): Do not edit this file *//*
 * Gom
 *
 * Copyright (C) 2006-2007, INRIA
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
import tom.gom.backend.MappingTemplateClass;
import java.io.*;
import tom.gom.adt.objects.types.*;
import tom.gom.tools.error.GomRuntimeException;

public class MappingTemplate extends MappingTemplateClass {
  ClassName basicStrategy;
  GomClassList sortClasses;
  GomClassList operatorClasses;
  TemplateClass strategyMapping;

  /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */  /* Generated by TOM (version 2.6alpha): Do not edit this file */    private static   tom.gom.adt.objects.types.GomClassList  tom_append_list_ConcGomClass( tom.gom.adt.objects.types.GomClassList l1,  tom.gom.adt.objects.types.GomClassList  l2) {     if( l1.isEmptyConcGomClass() ) {       return l2;     } else if( l2.isEmptyConcGomClass() ) {       return l1;     } else if(  l1.getTailConcGomClass() .isEmptyConcGomClass() ) {       return  tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass.make( l1.getHeadConcGomClass() ,l2) ;     } else {       return  tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass.make( l1.getHeadConcGomClass() ,tom_append_list_ConcGomClass( l1.getTailConcGomClass() ,l2)) ;     }   }   private static   tom.gom.adt.objects.types.GomClassList  tom_get_slice_ConcGomClass( tom.gom.adt.objects.types.GomClassList  begin,  tom.gom.adt.objects.types.GomClassList  end, tom.gom.adt.objects.types.GomClassList  tail) {     if( begin.equals(end) ) {       return tail;     } else {       return  tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass.make( begin.getHeadConcGomClass() ,( tom.gom.adt.objects.types.GomClassList )tom_get_slice_ConcGomClass( begin.getTailConcGomClass() ,end,tail)) ;     }   }    

  public MappingTemplate(GomClass gomClass, TemplateClass strategyMapping) {
    super(gomClass);
    {if ( (gomClass instanceof tom.gom.adt.objects.types.GomClass) ) {{  tom.gom.adt.objects.types.GomClass  tomMatch369NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClass )gomClass);if ( (tomMatch369NameNumberfreshSubject_1 instanceof tom.gom.adt.objects.types.gomclass.TomMapping) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch369NameNumber_freshVar_0= tomMatch369NameNumberfreshSubject_1.getBasicStrategy() ;{  tom.gom.adt.objects.types.GomClassList  tomMatch369NameNumber_freshVar_1= tomMatch369NameNumberfreshSubject_1.getSortClasses() ;{  tom.gom.adt.objects.types.GomClassList  tomMatch369NameNumber_freshVar_2= tomMatch369NameNumberfreshSubject_1.getOperatorClasses() ;if ( true ) {



        this.basicStrategy = tomMatch369NameNumber_freshVar_0;
        this.sortClasses = tomMatch369NameNumber_freshVar_1;
        this.operatorClasses = tomMatch369NameNumber_freshVar_2;
        this.strategyMapping = strategyMapping;
        return;
      }}}}}}}}

    throw new GomRuntimeException(
        "Wrong argument for MappingTemplate: " + gomClass);
  }

  public void generate(java.io.Writer writer) throws java.io.IOException {
    if(GomEnvironment.getInstance().isBuiltinSort("boolean")) {
      writer.write("\n%include { boolean.tom }\n"

);
    }
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
    if (GomEnvironment.getInstance().isBuiltinSort("double")) {
      writer.write("\n%include { double.tom }\n"

);
    }
    if (GomEnvironment.getInstance().isBuiltinSort("long")) {
      writer.write("\n%include { long.tom }\n"

);
    }
    if (GomEnvironment.getInstance().isBuiltinSort("float")) {
      writer.write("\n%include { float.tom }\n"

);
    }
    if (GomEnvironment.getInstance().isBuiltinSort("ATerm")) {
      writer.write("\n%include { aterm.tom }\n"

);
    }
    if (GomEnvironment.getInstance().isBuiltinSort("ATermList")) {
      writer.write("\n%include { aterm.tom }\n"

);
    }

    // generate a %typeterm for each class
    {if ( (sortClasses instanceof tom.gom.adt.objects.types.GomClassList) ) {{  tom.gom.adt.objects.types.GomClassList  tomMatch370NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClassList )sortClasses);if ( ((tomMatch370NameNumberfreshSubject_1 instanceof tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass) || (tomMatch370NameNumberfreshSubject_1 instanceof tom.gom.adt.objects.types.gomclasslist.EmptyConcGomClass)) ) {{  tom.gom.adt.objects.types.GomClassList  tomMatch370NameNumber_freshVar_0=tomMatch370NameNumberfreshSubject_1;{  tom.gom.adt.objects.types.GomClassList  tomMatch370NameNumber_begin_2=tomMatch370NameNumber_freshVar_0;{  tom.gom.adt.objects.types.GomClassList  tomMatch370NameNumber_end_3=tomMatch370NameNumber_freshVar_0;do {{{  tom.gom.adt.objects.types.GomClassList  tomMatch370NameNumber_freshVar_1=tomMatch370NameNumber_end_3;if (!( tomMatch370NameNumber_freshVar_1.isEmptyConcGomClass() )) {if ( ( tomMatch370NameNumber_freshVar_1.getHeadConcGomClass()  instanceof tom.gom.adt.objects.types.gomclass.SortClass) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch370NameNumber_freshVar_6=  tomMatch370NameNumber_freshVar_1.getHeadConcGomClass() .getClassName() ;{  tom.gom.adt.objects.types.GomClassList  tomMatch370NameNumber_freshVar_4= tomMatch370NameNumber_freshVar_1.getTailConcGomClass() ;if ( true ) {



        ((TemplateClass) templates.get(tomMatch370NameNumber_freshVar_6))
          .generateTomMapping(writer,basicStrategy);
      }}}}}}if ( tomMatch370NameNumber_end_3.isEmptyConcGomClass() ) {tomMatch370NameNumber_end_3=tomMatch370NameNumber_begin_2;} else {tomMatch370NameNumber_end_3= tomMatch370NameNumber_end_3.getTailConcGomClass() ;}}} while(!( tomMatch370NameNumber_end_3.equals(tomMatch370NameNumber_begin_2) ));}}}}}}}


    // generate a %op for each operator
    {if ( (operatorClasses instanceof tom.gom.adt.objects.types.GomClassList) ) {{  tom.gom.adt.objects.types.GomClassList  tomMatch371NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClassList )operatorClasses);if ( ((tomMatch371NameNumberfreshSubject_1 instanceof tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass) || (tomMatch371NameNumberfreshSubject_1 instanceof tom.gom.adt.objects.types.gomclasslist.EmptyConcGomClass)) ) {{  tom.gom.adt.objects.types.GomClassList  tomMatch371NameNumber_freshVar_0=tomMatch371NameNumberfreshSubject_1;{  tom.gom.adt.objects.types.GomClassList  tomMatch371NameNumber_begin_2=tomMatch371NameNumber_freshVar_0;{  tom.gom.adt.objects.types.GomClassList  tomMatch371NameNumber_end_3=tomMatch371NameNumber_freshVar_0;do {{{  tom.gom.adt.objects.types.GomClassList  tomMatch371NameNumber_freshVar_1=tomMatch371NameNumber_end_3;if (!( tomMatch371NameNumber_freshVar_1.isEmptyConcGomClass() )) {if ( ( tomMatch371NameNumber_freshVar_1.getHeadConcGomClass()  instanceof tom.gom.adt.objects.types.gomclass.OperatorClass) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch371NameNumber_freshVar_6=  tomMatch371NameNumber_freshVar_1.getHeadConcGomClass() .getClassName() ;{  tom.gom.adt.objects.types.GomClassList  tomMatch371NameNumber_freshVar_4= tomMatch371NameNumber_freshVar_1.getTailConcGomClass() ;if ( true ) {



        ((TemplateClass) templates.get(tomMatch371NameNumber_freshVar_6))
          .generateTomMapping(writer,basicStrategy);
      }}}}}}if ( tomMatch371NameNumber_end_3.isEmptyConcGomClass() ) {tomMatch371NameNumber_end_3=tomMatch371NameNumber_begin_2;} else {tomMatch371NameNumber_end_3= tomMatch371NameNumber_end_3.getTailConcGomClass() ;}}} while(!( tomMatch371NameNumber_end_3.equals(tomMatch371NameNumber_begin_2) ));}}}}}}}


    // generate a %oplist for each variadic operator
    {if ( (operatorClasses instanceof tom.gom.adt.objects.types.GomClassList) ) {{  tom.gom.adt.objects.types.GomClassList  tomMatch372NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClassList )operatorClasses);if ( ((tomMatch372NameNumberfreshSubject_1 instanceof tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass) || (tomMatch372NameNumberfreshSubject_1 instanceof tom.gom.adt.objects.types.gomclasslist.EmptyConcGomClass)) ) {{  tom.gom.adt.objects.types.GomClassList  tomMatch372NameNumber_freshVar_0=tomMatch372NameNumberfreshSubject_1;{  tom.gom.adt.objects.types.GomClassList  tomMatch372NameNumber_begin_2=tomMatch372NameNumber_freshVar_0;{  tom.gom.adt.objects.types.GomClassList  tomMatch372NameNumber_end_3=tomMatch372NameNumber_freshVar_0;do {{{  tom.gom.adt.objects.types.GomClassList  tomMatch372NameNumber_freshVar_1=tomMatch372NameNumber_end_3;if (!( tomMatch372NameNumber_freshVar_1.isEmptyConcGomClass() )) {if ( ( tomMatch372NameNumber_freshVar_1.getHeadConcGomClass()  instanceof tom.gom.adt.objects.types.gomclass.VariadicOperatorClass) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch372NameNumber_freshVar_6=  tomMatch372NameNumber_freshVar_1.getHeadConcGomClass() .getClassName() ;{  tom.gom.adt.objects.types.GomClassList  tomMatch372NameNumber_freshVar_4= tomMatch372NameNumber_freshVar_1.getTailConcGomClass() ;if ( true ) {



        ((TemplateClass) templates.get(tomMatch372NameNumber_freshVar_6))
          .generateTomMapping(writer,basicStrategy);
      }}}}}}if ( tomMatch372NameNumber_end_3.isEmptyConcGomClass() ) {tomMatch372NameNumber_end_3=tomMatch372NameNumber_begin_2;} else {tomMatch372NameNumber_end_3= tomMatch372NameNumber_end_3.getTailConcGomClass() ;}}} while(!( tomMatch372NameNumber_end_3.equals(tomMatch372NameNumber_begin_2) ));}}}}}}}

    /* Include the strategy mapping if needed */
    if (strategyMapping != null) {
      strategyMapping.generateTomMapping(writer,basicStrategy);
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
      GomEnvironment.getInstance()
        .setLastGeneratedMapping(output.getCanonicalPath());
    } catch(Exception e) {
      e.printStackTrace();
    }
    return output;
  }
}
