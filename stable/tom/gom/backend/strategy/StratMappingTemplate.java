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

package tom.gom.backend.strategy;

import tom.gom.GomStreamManager;
import tom.gom.tools.GomEnvironment;
import tom.gom.backend.MappingTemplateClass;
import java.io.*;
import tom.gom.adt.objects.types.*;
import tom.gom.tools.error.GomRuntimeException;

public class StratMappingTemplate extends MappingTemplateClass {
  GomClassList operatorClasses;

  /* Generated by TOM (version 2.6rc2): Do not edit this file *//* Generated by TOM (version 2.6rc2): Do not edit this file *//* Generated by TOM (version 2.6rc2): Do not edit this file */  /* Generated by TOM (version 2.6rc2): Do not edit this file */    private static   tom.gom.adt.objects.types.GomClassList  tom_append_list_ConcGomClass( tom.gom.adt.objects.types.GomClassList l1,  tom.gom.adt.objects.types.GomClassList  l2) {     if( l1.isEmptyConcGomClass() ) {       return l2;     } else if( l2.isEmptyConcGomClass() ) {       return l1;     } else if(  l1.getTailConcGomClass() .isEmptyConcGomClass() ) {       return  tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass.make( l1.getHeadConcGomClass() ,l2) ;     } else {       return  tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass.make( l1.getHeadConcGomClass() ,tom_append_list_ConcGomClass( l1.getTailConcGomClass() ,l2)) ;     }   }   private static   tom.gom.adt.objects.types.GomClassList  tom_get_slice_ConcGomClass( tom.gom.adt.objects.types.GomClassList  begin,  tom.gom.adt.objects.types.GomClassList  end, tom.gom.adt.objects.types.GomClassList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyConcGomClass()  ||  (end== tom.gom.adt.objects.types.gomclasslist.EmptyConcGomClass.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass.make( begin.getHeadConcGomClass() ,( tom.gom.adt.objects.types.GomClassList )tom_get_slice_ConcGomClass( begin.getTailConcGomClass() ,end,tail)) ;   }    

  public StratMappingTemplate(GomClass gomClass) {
    super(gomClass);
    {{ Object tomMatch425NameNumber_freshVar_0=gomClass;if ( (tomMatch425NameNumber_freshVar_0 instanceof tom.gom.adt.objects.types.GomClass) ) {{  tom.gom.adt.objects.types.GomClass  tomMatch425NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClass )tomMatch425NameNumber_freshVar_0);{  tom.gom.adt.objects.types.GomClass  tomMatch425NameNumber_freshVar_2=tomMatch425NameNumberfreshSubject_1;if ( (tomMatch425NameNumber_freshVar_2 instanceof tom.gom.adt.objects.types.gomclass.TomMapping) ) {{  tom.gom.adt.objects.types.GomClassList  tomMatch425NameNumber_freshVar_1= tomMatch425NameNumber_freshVar_2.getOperatorClasses() ;if ( true ) {

        this.operatorClasses = tomMatch425NameNumber_freshVar_1;
        return;
      }}}}}}}}

    throw new GomRuntimeException(
        "Wrong argument for MappingTemplate: " + gomClass);
  }
  
  public void generateTomMapping(java.io.Writer writer) throws java.io.IOException {
    generate(writer);
  }

  public void generate(java.io.Writer writer) throws java.io.IOException {
    {{ Object tomMatch426NameNumber_freshVar_0=operatorClasses;if ( (tomMatch426NameNumber_freshVar_0 instanceof tom.gom.adt.objects.types.GomClassList) ) {{  tom.gom.adt.objects.types.GomClassList  tomMatch426NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClassList )tomMatch426NameNumber_freshVar_0);{  tom.gom.adt.objects.types.GomClassList  tomMatch426NameNumber_freshVar_1=tomMatch426NameNumberfreshSubject_1;if ( ((tomMatch426NameNumber_freshVar_1 instanceof tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass) || (tomMatch426NameNumber_freshVar_1 instanceof tom.gom.adt.objects.types.gomclasslist.EmptyConcGomClass)) ) {{  tom.gom.adt.objects.types.GomClassList  tomMatch426NameNumber_begin_3=tomMatch426NameNumber_freshVar_1;{  tom.gom.adt.objects.types.GomClassList  tomMatch426NameNumber_end_4=tomMatch426NameNumber_freshVar_1;do {{{  tom.gom.adt.objects.types.GomClassList  tomMatch426NameNumber_freshVar_2=tomMatch426NameNumber_end_4;if (!( tomMatch426NameNumber_freshVar_2.isEmptyConcGomClass() )) {{  tom.gom.adt.objects.types.GomClass  tomMatch426NameNumber_freshVar_7= tomMatch426NameNumber_freshVar_2.getHeadConcGomClass() ;if ( (tomMatch426NameNumber_freshVar_7 instanceof tom.gom.adt.objects.types.gomclass.OperatorClass) ) {{  tom.gom.adt.objects.types.GomClass  tom_op= tomMatch426NameNumber_freshVar_2.getHeadConcGomClass() ;{  tom.gom.adt.objects.types.GomClassList  tomMatch426NameNumber_freshVar_5= tomMatch426NameNumber_freshVar_2.getTailConcGomClass() ;if ( true ) {

        writer.write(
            (new tom.gom.backend.strategy.SOpTemplate(tom_op)).generateMapping());
        writer.write(
            (new tom.gom.backend.strategy.IsOpTemplate(tom_op)).generateMapping());
        writer.write(
            (new tom.gom.backend.strategy.MakeOpTemplate(tom_op)).generateMapping());
      }}}}}}}if ( tomMatch426NameNumber_end_4.isEmptyConcGomClass() ) {tomMatch426NameNumber_end_4=tomMatch426NameNumber_begin_3;} else {tomMatch426NameNumber_end_4= tomMatch426NameNumber_end_4.getTailConcGomClass() ;}}} while(!( (tomMatch426NameNumber_end_4==tomMatch426NameNumber_begin_3) ));}}}}}}}{ Object tomMatch426NameNumber_freshVar_8=operatorClasses;if ( (tomMatch426NameNumber_freshVar_8 instanceof tom.gom.adt.objects.types.GomClassList) ) {{  tom.gom.adt.objects.types.GomClassList  tomMatch426NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClassList )tomMatch426NameNumber_freshVar_8);{  tom.gom.adt.objects.types.GomClassList  tomMatch426NameNumber_freshVar_9=tomMatch426NameNumberfreshSubject_1;if ( ((tomMatch426NameNumber_freshVar_9 instanceof tom.gom.adt.objects.types.gomclasslist.ConsConcGomClass) || (tomMatch426NameNumber_freshVar_9 instanceof tom.gom.adt.objects.types.gomclasslist.EmptyConcGomClass)) ) {{  tom.gom.adt.objects.types.GomClassList  tomMatch426NameNumber_begin_11=tomMatch426NameNumber_freshVar_9;{  tom.gom.adt.objects.types.GomClassList  tomMatch426NameNumber_end_12=tomMatch426NameNumber_freshVar_9;do {{{  tom.gom.adt.objects.types.GomClassList  tomMatch426NameNumber_freshVar_10=tomMatch426NameNumber_end_12;if (!( tomMatch426NameNumber_freshVar_10.isEmptyConcGomClass() )) {{  tom.gom.adt.objects.types.GomClass  tomMatch426NameNumber_freshVar_18= tomMatch426NameNumber_freshVar_10.getHeadConcGomClass() ;if ( (tomMatch426NameNumber_freshVar_18 instanceof tom.gom.adt.objects.types.gomclass.VariadicOperatorClass) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch426NameNumber_freshVar_15= tomMatch426NameNumber_freshVar_18.getClassName() ;{  tom.gom.adt.objects.types.GomClass  tomMatch426NameNumber_freshVar_16= tomMatch426NameNumber_freshVar_18.getEmpty() ;{  tom.gom.adt.objects.types.GomClass  tomMatch426NameNumber_freshVar_17= tomMatch426NameNumber_freshVar_18.getCons() ;{  tom.gom.adt.objects.types.ClassName  tom_vopName=tomMatch426NameNumber_freshVar_15;{  tom.gom.adt.objects.types.GomClass  tomMatch426NameNumber_freshVar_20=tomMatch426NameNumber_freshVar_16;if ( (tomMatch426NameNumber_freshVar_20 instanceof tom.gom.adt.objects.types.gomclass.OperatorClass) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch426NameNumber_freshVar_19= tomMatch426NameNumber_freshVar_20.getClassName() ;{  tom.gom.adt.objects.types.GomClass  tomMatch426NameNumber_freshVar_22=tomMatch426NameNumber_freshVar_17;if ( (tomMatch426NameNumber_freshVar_22 instanceof tom.gom.adt.objects.types.gomclass.OperatorClass) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch426NameNumber_freshVar_21= tomMatch426NameNumber_freshVar_22.getClassName() ;{  tom.gom.adt.objects.types.GomClassList  tomMatch426NameNumber_freshVar_13= tomMatch426NameNumber_freshVar_10.getTailConcGomClass() ;if ( true ) {





        writer.write("\n            %op Strategy _"/* Generated by TOM (version 2.6rc2): Do not edit this file */+className(tom_vopName)+"(sub:Strategy) {\n            is_fsym(t) { false }\n            make(sub)  { `mu(MuVar(\"x_"/* Generated by TOM (version 2.6rc2): Do not edit this file */+className(tom_vopName)+"\"),Choice(_"/* Generated by TOM (version 2.6rc2): Do not edit this file */+className(tomMatch426NameNumber_freshVar_21)+"(sub,MuVar(\"x_"/* Generated by TOM (version 2.6rc2): Do not edit this file */+className(tom_vopName)+"\")),_"/* Generated by TOM (version 2.6rc2): Do not edit this file */+className(tomMatch426NameNumber_freshVar_19)+"())) }\n            }\n            "




);
      }}}}}}}}}}}}}}}}if ( tomMatch426NameNumber_end_12.isEmptyConcGomClass() ) {tomMatch426NameNumber_end_12=tomMatch426NameNumber_begin_11;} else {tomMatch426NameNumber_end_12= tomMatch426NameNumber_end_12.getTailConcGomClass() ;}}} while(!( (tomMatch426NameNumber_end_12==tomMatch426NameNumber_begin_11) ));}}}}}}}}

      }

  protected String fileName() {
    return fullClassName().replace('.',File.separatorChar)+".tom";
  }

}
