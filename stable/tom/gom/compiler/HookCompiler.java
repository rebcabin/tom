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

package tom.gom.compiler;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import tom.gom.GomMessage;
import tom.gom.tools.GomEnvironment;
import tom.gom.adt.gom.*;
import tom.gom.adt.gom.types.*;
import tom.gom.tools.error.GomRuntimeException;

import tom.gom.adt.objects.*;
import tom.gom.adt.objects.types.*;

import tom.library.sl.*;

public class HookCompiler {

  /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */  /* Generated by TOM (version 2.6alpha): Do not edit this file */    private static   tom.gom.adt.objects.types.SlotFieldList  tom_append_list_concSlotField( tom.gom.adt.objects.types.SlotFieldList l1,  tom.gom.adt.objects.types.SlotFieldList  l2) {     if( l1.isEmptyconcSlotField() ) {       return l2;     } else if( l2.isEmptyconcSlotField() ) {       return l1;     } else if(  l1.getTailconcSlotField() .isEmptyconcSlotField() ) {       return  tom.gom.adt.objects.types.slotfieldlist.ConsconcSlotField.make( l1.getHeadconcSlotField() ,l2) ;     } else {       return  tom.gom.adt.objects.types.slotfieldlist.ConsconcSlotField.make( l1.getHeadconcSlotField() ,tom_append_list_concSlotField( l1.getTailconcSlotField() ,l2)) ;     }   }   private static   tom.gom.adt.objects.types.SlotFieldList  tom_get_slice_concSlotField( tom.gom.adt.objects.types.SlotFieldList  begin,  tom.gom.adt.objects.types.SlotFieldList  end, tom.gom.adt.objects.types.SlotFieldList  tail) {     if( begin.equals(end) ) {       return tail;     } else {       return  tom.gom.adt.objects.types.slotfieldlist.ConsconcSlotField.make( begin.getHeadconcSlotField() ,( tom.gom.adt.objects.types.SlotFieldList )tom_get_slice_concSlotField( begin.getTailconcSlotField() ,end,tail)) ;     }   }      private static   tom.gom.adt.objects.types.HookList  tom_append_list_concHook( tom.gom.adt.objects.types.HookList l1,  tom.gom.adt.objects.types.HookList  l2) {     if( l1.isEmptyconcHook() ) {       return l2;     } else if( l2.isEmptyconcHook() ) {       return l1;     } else if(  l1.getTailconcHook() .isEmptyconcHook() ) {       return  tom.gom.adt.objects.types.hooklist.ConsconcHook.make( l1.getHeadconcHook() ,l2) ;     } else {       return  tom.gom.adt.objects.types.hooklist.ConsconcHook.make( l1.getHeadconcHook() ,tom_append_list_concHook( l1.getTailconcHook() ,l2)) ;     }   }   private static   tom.gom.adt.objects.types.HookList  tom_get_slice_concHook( tom.gom.adt.objects.types.HookList  begin,  tom.gom.adt.objects.types.HookList  end, tom.gom.adt.objects.types.HookList  tail) {     if( begin.equals(end) ) {       return tail;     } else {       return  tom.gom.adt.objects.types.hooklist.ConsconcHook.make( begin.getHeadconcHook() ,( tom.gom.adt.objects.types.HookList )tom_get_slice_concHook( begin.getTailconcHook() ,end,tail)) ;     }   }      private static   tom.gom.adt.gom.types.HookDeclList  tom_append_list_concHookDecl( tom.gom.adt.gom.types.HookDeclList l1,  tom.gom.adt.gom.types.HookDeclList  l2) {     if( l1.isEmptyconcHookDecl() ) {       return l2;     } else if( l2.isEmptyconcHookDecl() ) {       return l1;     } else if(  l1.getTailconcHookDecl() .isEmptyconcHookDecl() ) {       return  tom.gom.adt.gom.types.hookdecllist.ConsconcHookDecl.make( l1.getHeadconcHookDecl() ,l2) ;     } else {       return  tom.gom.adt.gom.types.hookdecllist.ConsconcHookDecl.make( l1.getHeadconcHookDecl() ,tom_append_list_concHookDecl( l1.getTailconcHookDecl() ,l2)) ;     }   }   private static   tom.gom.adt.gom.types.HookDeclList  tom_get_slice_concHookDecl( tom.gom.adt.gom.types.HookDeclList  begin,  tom.gom.adt.gom.types.HookDeclList  end, tom.gom.adt.gom.types.HookDeclList  tail) {     if( begin.equals(end) ) {       return tail;     } else {       return  tom.gom.adt.gom.types.hookdecllist.ConsconcHookDecl.make( begin.getHeadconcHookDecl() ,( tom.gom.adt.gom.types.HookDeclList )tom_get_slice_concHookDecl( begin.getTailconcHookDecl() ,end,tail)) ;     }   }      private static   tom.gom.adt.gom.types.SlotList  tom_append_list_concSlot( tom.gom.adt.gom.types.SlotList l1,  tom.gom.adt.gom.types.SlotList  l2) {     if( l1.isEmptyconcSlot() ) {       return l2;     } else if( l2.isEmptyconcSlot() ) {       return l1;     } else if(  l1.getTailconcSlot() .isEmptyconcSlot() ) {       return  tom.gom.adt.gom.types.slotlist.ConsconcSlot.make( l1.getHeadconcSlot() ,l2) ;     } else {       return  tom.gom.adt.gom.types.slotlist.ConsconcSlot.make( l1.getHeadconcSlot() ,tom_append_list_concSlot( l1.getTailconcSlot() ,l2)) ;     }   }   private static   tom.gom.adt.gom.types.SlotList  tom_get_slice_concSlot( tom.gom.adt.gom.types.SlotList  begin,  tom.gom.adt.gom.types.SlotList  end, tom.gom.adt.gom.types.SlotList  tail) {     if( begin.equals(end) ) {       return tail;     } else {       return  tom.gom.adt.gom.types.slotlist.ConsconcSlot.make( begin.getHeadconcSlot() ,( tom.gom.adt.gom.types.SlotList )tom_get_slice_concSlot( begin.getTailconcSlot() ,end,tail)) ;     }   }    /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */   private static   tom.library.sl.Strategy  tom_append_list_Sequence( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 == null )) {       return l2;     } else if(( l2 == null )) {       return l1;     } else if(( (l1 instanceof tom.library.sl.Sequence) )) {       if(( ((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )) == null )) {         return ( (l2==null)?((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1):new tom.library.sl.Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1),l2) );       } else {         return ( (tom_append_list_Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),l2)==null)?((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1):new tom.library.sl.Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1),tom_append_list_Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),l2)) );       }     } else {       return ( (l2==null)?l1:new tom.library.sl.Sequence(l1,l2) );     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else {       return ( (( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)==null)?((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin):new tom.library.sl.Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)) );     }   }    /* Generated by TOM (version 2.6alpha): Do not edit this file */ /* Generated by TOM (version 2.6alpha): Do not edit this file */private static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ),( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )==null)?v:new tom.library.sl.Sequence(v,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )) )) ) ); }   


  private GomEnvironment environment() {
    return GomEnvironment.getInstance();
  }

  private static Map sortClassNameForSortDecl;
  HookCompiler(Map sortClassNameForSortDecl) {
    this.sortClassNameForSortDecl = sortClassNameForSortDecl;
  }
  /**
    * Process the hooks, and attach them to the correct classes.
    */
  public GomClassList compile(
      HookDeclList declList,
      GomClassList classes,
      Map declToClassName) {
    /* for each hook, find the class, and attach the hook */
    {if ( (declList instanceof tom.gom.adt.gom.types.HookDeclList) ) {{  tom.gom.adt.gom.types.HookDeclList  tomMatch434NameNumberfreshSubject_1=(( tom.gom.adt.gom.types.HookDeclList )declList);if ( ((tomMatch434NameNumberfreshSubject_1 instanceof tom.gom.adt.gom.types.hookdecllist.ConsconcHookDecl) || (tomMatch434NameNumberfreshSubject_1 instanceof tom.gom.adt.gom.types.hookdecllist.EmptyconcHookDecl)) ) {{  tom.gom.adt.gom.types.HookDeclList  tomMatch434NameNumber_freshVar_0=tomMatch434NameNumberfreshSubject_1;{  tom.gom.adt.gom.types.HookDeclList  tomMatch434NameNumber_begin_2=tomMatch434NameNumber_freshVar_0;{  tom.gom.adt.gom.types.HookDeclList  tomMatch434NameNumber_end_3=tomMatch434NameNumber_freshVar_0;do {{{  tom.gom.adt.gom.types.HookDeclList  tomMatch434NameNumber_freshVar_1=tomMatch434NameNumber_end_3;if (!( tomMatch434NameNumber_freshVar_1.isEmptyconcHookDecl() )) {{  tom.gom.adt.gom.types.HookDecl  tom_hook= tomMatch434NameNumber_freshVar_1.getHeadconcHookDecl() ;{  tom.gom.adt.gom.types.HookDeclList  tomMatch434NameNumber_freshVar_4= tomMatch434NameNumber_freshVar_1.getTailconcHookDecl() ;if ( true ) {

        Decl decl = tom_hook.getPointcut();
        {if ( (decl instanceof tom.gom.adt.gom.types.Decl) ) {{  tom.gom.adt.gom.types.Decl  tomMatch435NameNumberfreshSubject_1=(( tom.gom.adt.gom.types.Decl )decl);if ( (tomMatch435NameNumberfreshSubject_1 instanceof tom.gom.adt.gom.types.decl.CutModule) ) {{  tom.gom.adt.gom.types.ModuleDecl  tomMatch435NameNumber_freshVar_0= tomMatch435NameNumberfreshSubject_1.getMDecl() ;if ( true ) {

            ClassName clsName = (ClassName) declToClassName.get(tomMatch435NameNumber_freshVar_0);
            try {
              classes = (GomClassList)
                tom_make_TopDown(tom_make_AttachModuleHook(clsName,tom_hook)).visit(classes);
            } catch (tom.library.sl.VisitFailure e) {
              throw new GomRuntimeException("Unexpected strategy failure!");
            }
          }}}}}if ( (decl instanceof tom.gom.adt.gom.types.Decl) ) {{  tom.gom.adt.gom.types.Decl  tomMatch435NameNumberfreshSubject_1=(( tom.gom.adt.gom.types.Decl )decl);if ( (tomMatch435NameNumberfreshSubject_1 instanceof tom.gom.adt.gom.types.decl.CutSort) ) {{  tom.gom.adt.gom.types.SortDecl  tomMatch435NameNumber_freshVar_1= tomMatch435NameNumberfreshSubject_1.getSort() ;if ( true ) {

            ClassName clsName = (ClassName) declToClassName.get(tomMatch435NameNumber_freshVar_1);
            try {
              classes = (GomClassList)
                tom_make_TopDown(tom_make_AttachSortHook(clsName,tom_hook)).visit(classes);
            } catch (tom.library.sl.VisitFailure e) {
              throw new GomRuntimeException("Unexpected strategy failure!");
            }
          }}}}}if ( (decl instanceof tom.gom.adt.gom.types.Decl) ) {{  tom.gom.adt.gom.types.Decl  tomMatch435NameNumberfreshSubject_1=(( tom.gom.adt.gom.types.Decl )decl);if ( (tomMatch435NameNumberfreshSubject_1 instanceof tom.gom.adt.gom.types.decl.CutOperator) ) {{  tom.gom.adt.gom.types.OperatorDecl  tomMatch435NameNumber_freshVar_2= tomMatch435NameNumberfreshSubject_1.getODecl() ;if ( true ) {

            ClassName clsName = (ClassName) declToClassName.get(tomMatch435NameNumber_freshVar_2);
            try {
              classes = (GomClassList)
                tom_make_TopDown(tom_make_AttachOperatorHook(clsName,tom_hook)).visit(classes);
            } catch (tom.library.sl.VisitFailure e) {
              throw new GomRuntimeException("Unexpected strategy failure!");
            }     
          }}}}}}

      }}}}}if ( tomMatch434NameNumber_end_3.isEmptyconcHookDecl() ) {tomMatch434NameNumber_end_3=tomMatch434NameNumber_begin_2;} else {tomMatch434NameNumber_end_3= tomMatch434NameNumber_end_3.getTailconcHookDecl() ;}}} while(!( tomMatch434NameNumber_end_3.equals(tomMatch434NameNumber_begin_2) ));}}}}}}}

    return classes;
  }

  private static class AttachModuleHook extends  tom.gom.adt.objects.ObjectsBasicStrategy  {private  tom.gom.adt.objects.types.ClassName  cName; private  tom.gom.adt.gom.types.HookDecl  hook; public AttachModuleHook( tom.gom.adt.objects.types.ClassName  cName,  tom.gom.adt.gom.types.HookDecl  hook) { super(( new tom.library.sl.Identity() ));this.cName=cName;this.hook=hook;}public  tom.gom.adt.objects.types.ClassName  getcName() { return cName;}public  tom.gom.adt.gom.types.HookDecl  gethook() { return hook;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() { return 1; }public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}public  tom.gom.adt.objects.types.GomClass  visit_GomClass( tom.gom.adt.objects.types.GomClass  tom__arg) throws tom.library.sl.VisitFailure {{if ( (tom__arg instanceof tom.gom.adt.objects.types.GomClass) ) {{  tom.gom.adt.objects.types.GomClass  tomMatch436NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClass )tom__arg);if ( (tomMatch436NameNumberfreshSubject_1 instanceof tom.gom.adt.objects.types.gomclass.AbstractTypeClass) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch436NameNumber_freshVar_0= tomMatch436NameNumberfreshSubject_1.getClassName() ;{  tom.gom.adt.objects.types.HookList  tomMatch436NameNumber_freshVar_1= tomMatch436NameNumberfreshSubject_1.getHooks() ;if ( true ) {



          if (tomMatch436NameNumber_freshVar_0== cName) {
            return
              tomMatch436NameNumberfreshSubject_1.setHooks( tom.gom.adt.objects.types.hooklist.ConsconcHook.make(makeHooksFromHookDecl(hook),tom_append_list_concHook(tomMatch436NameNumber_freshVar_1, tom.gom.adt.objects.types.hooklist.EmptyconcHook.make() )) );
          }
        }}}}}}}return super.visit_GomClass(tom__arg); }}private static  tom.library.sl.Strategy  tom_make_AttachModuleHook( tom.gom.adt.objects.types.ClassName  t0,  tom.gom.adt.gom.types.HookDecl  t1) { return new AttachModuleHook(t0,t1); }private static class AttachSortHook extends  tom.gom.adt.objects.ObjectsBasicStrategy  {private  tom.gom.adt.objects.types.ClassName  cName; private  tom.gom.adt.gom.types.HookDecl  hook; public AttachSortHook( tom.gom.adt.objects.types.ClassName  cName,  tom.gom.adt.gom.types.HookDecl  hook) { super(( new tom.library.sl.Identity() ));this.cName=cName;this.hook=hook;}public  tom.gom.adt.objects.types.ClassName  getcName() { return cName;}public  tom.gom.adt.gom.types.HookDecl  gethook() { return hook;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() { return 1; }public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}public  tom.gom.adt.objects.types.GomClass  visit_GomClass( tom.gom.adt.objects.types.GomClass  tom__arg) throws tom.library.sl.VisitFailure {{if ( (tom__arg instanceof tom.gom.adt.objects.types.GomClass) ) {{  tom.gom.adt.objects.types.GomClass  tomMatch437NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClass )tom__arg);if ( (tomMatch437NameNumberfreshSubject_1 instanceof tom.gom.adt.objects.types.gomclass.SortClass) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch437NameNumber_freshVar_0= tomMatch437NameNumberfreshSubject_1.getClassName() ;{  tom.gom.adt.objects.types.HookList  tomMatch437NameNumber_freshVar_1= tomMatch437NameNumberfreshSubject_1.getHooks() ;if ( true ) {







          if (tomMatch437NameNumber_freshVar_0== cName) {
            return
              tomMatch437NameNumberfreshSubject_1.setHooks( tom.gom.adt.objects.types.hooklist.ConsconcHook.make(makeHooksFromHookDecl(hook),tom_append_list_concHook(tomMatch437NameNumber_freshVar_1, tom.gom.adt.objects.types.hooklist.EmptyconcHook.make() )) );
          }
        }}}}}}}return super.visit_GomClass(tom__arg); }}private static  tom.library.sl.Strategy  tom_make_AttachSortHook( tom.gom.adt.objects.types.ClassName  t0,  tom.gom.adt.gom.types.HookDecl  t1) { return new AttachSortHook(t0,t1); }private static class AttachOperatorHook extends  tom.gom.adt.objects.ObjectsBasicStrategy  {private  tom.gom.adt.objects.types.ClassName  cName; private  tom.gom.adt.gom.types.HookDecl  hook; public AttachOperatorHook( tom.gom.adt.objects.types.ClassName  cName,  tom.gom.adt.gom.types.HookDecl  hook) { super(( new tom.library.sl.Identity() ));this.cName=cName;this.hook=hook;}public  tom.gom.adt.objects.types.ClassName  getcName() { return cName;}public  tom.gom.adt.gom.types.HookDecl  gethook() { return hook;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() { return 1; }public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}public  tom.gom.adt.objects.types.GomClass  visit_GomClass( tom.gom.adt.objects.types.GomClass  tom__arg) throws tom.library.sl.VisitFailure {{if ( (tom__arg instanceof tom.gom.adt.objects.types.GomClass) ) {{  tom.gom.adt.objects.types.GomClass  tomMatch438NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClass )tom__arg);if ( (tomMatch438NameNumberfreshSubject_1 instanceof tom.gom.adt.objects.types.gomclass.VariadicOperatorClass) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch438NameNumber_freshVar_0= tomMatch438NameNumberfreshSubject_1.getClassName() ;{  tom.gom.adt.objects.types.HookList  tomMatch438NameNumber_freshVar_1= tomMatch438NameNumberfreshSubject_1.getHooks() ;{  tom.gom.adt.objects.types.GomClass  tomMatch438NameNumber_freshVar_2= tomMatch438NameNumberfreshSubject_1.getEmpty() ;{  tom.gom.adt.objects.types.GomClass  tomMatch438NameNumber_freshVar_3= tomMatch438NameNumberfreshSubject_1.getCons() ;{  tom.gom.adt.objects.types.HookList  tom_oldHooks=tomMatch438NameNumber_freshVar_1;{  tom.gom.adt.objects.types.GomClass  tom_emptyClass=tomMatch438NameNumber_freshVar_2;{  tom.gom.adt.objects.types.GomClass  tom_consClass=tomMatch438NameNumber_freshVar_3;{  tom.gom.adt.objects.types.GomClass  tom_obj=tomMatch438NameNumberfreshSubject_1;if ( true ) {








            if (tomMatch438NameNumber_freshVar_0== cName) {
              /* We may want to attach the hook to the cons or empty */
              if (hook.isMakeHookDecl()) {
                if (hook.getSlotArgs() !=  tom.gom.adt.gom.types.slotlist.EmptyconcSlot.make() ) {
                  HookList oldConsHooks = tom_consClass.getHooks();
                  GomClass newCons =
                    tom_consClass.setHooks(
                         tom.gom.adt.objects.types.hooklist.ConsconcHook.make(makeHooksFromHookDecl(hook),tom_append_list_concHook(oldConsHooks, tom.gom.adt.objects.types.hooklist.EmptyconcHook.make() )) );
                  return tom_obj.setCons(newCons);
                } else if (hook.getSlotArgs() ==  tom.gom.adt.gom.types.slotlist.EmptyconcSlot.make() ) {
                  HookList oldEmptyHooks = tom_emptyClass.getHooks();
                  GomClass newEmpty =
                    tom_emptyClass.setHooks(
                         tom.gom.adt.objects.types.hooklist.ConsconcHook.make(makeHooksFromHookDecl(hook),tom_append_list_concHook(oldEmptyHooks, tom.gom.adt.objects.types.hooklist.EmptyconcHook.make() )) );
                  return tom_obj.setEmpty(newEmpty);
                }
              } else if (hook.isImportHookDecl()) {
                /* We will want to attach the hook directly to the 3 classes */
                /* in case we use these imports for the corresponding Make hooks */
                HookList oldConsHooks = tom_consClass.getHooks();
                GomClass newCons =
                  tom_consClass.setHooks(
                       tom.gom.adt.objects.types.hooklist.ConsconcHook.make(makeHooksFromHookDecl(hook),tom_append_list_concHook(oldConsHooks, tom.gom.adt.objects.types.hooklist.EmptyconcHook.make() )) );
                HookList oldEmptyHooks = tom_emptyClass.getHooks();
                GomClass newEmpty =
                  tom_emptyClass.setHooks(
                       tom.gom.adt.objects.types.hooklist.ConsconcHook.make(makeHooksFromHookDecl(hook),tom_append_list_concHook(oldEmptyHooks, tom.gom.adt.objects.types.hooklist.EmptyconcHook.make() )) );
                GomClass newobj = tom_obj.setEmpty(newEmpty);
                newobj = newobj.setCons(newCons);
                return newobj.setHooks( tom.gom.adt.objects.types.hooklist.ConsconcHook.make(makeHooksFromHookDecl(hook),tom_append_list_concHook(tom_oldHooks, tom.gom.adt.objects.types.hooklist.EmptyconcHook.make() )) );
              } else {                
                return
                  tom_obj.setHooks( tom.gom.adt.objects.types.hooklist.ConsconcHook.make(makeHooksFromHookDecl(hook),tom_append_list_concHook(tom_oldHooks, tom.gom.adt.objects.types.hooklist.EmptyconcHook.make() )) );
              }
            }
          }}}}}}}}}}}}if ( (tom__arg instanceof tom.gom.adt.objects.types.GomClass) ) {{  tom.gom.adt.objects.types.GomClass  tomMatch438NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClass )tom__arg);if ( (tomMatch438NameNumberfreshSubject_1 instanceof tom.gom.adt.objects.types.gomclass.OperatorClass) ) {{  tom.gom.adt.objects.types.ClassName  tomMatch438NameNumber_freshVar_4= tomMatch438NameNumberfreshSubject_1.getClassName() ;{  tom.gom.adt.objects.types.HookList  tomMatch438NameNumber_freshVar_5= tomMatch438NameNumberfreshSubject_1.getHooks() ;if ( true ) {

          if (tomMatch438NameNumber_freshVar_4== cName) {
            return
              tomMatch438NameNumberfreshSubject_1.setHooks( tom.gom.adt.objects.types.hooklist.ConsconcHook.make(makeHooksFromHookDecl(hook),tom_append_list_concHook(tomMatch438NameNumber_freshVar_5, tom.gom.adt.objects.types.hooklist.EmptyconcHook.make() )) );
          }
        }}}}}}}return super.visit_GomClass(tom__arg); }}private static  tom.library.sl.Strategy  tom_make_AttachOperatorHook( tom.gom.adt.objects.types.ClassName  t0,  tom.gom.adt.gom.types.HookDecl  t1) { return new AttachOperatorHook(t0,t1); }



  private static Hook makeHooksFromHookDecl(HookDecl hookDecl) {
    {if ( (hookDecl instanceof tom.gom.adt.gom.types.HookDecl) ) {{  tom.gom.adt.gom.types.HookDecl  tomMatch439NameNumberfreshSubject_1=(( tom.gom.adt.gom.types.HookDecl )hookDecl);if ( (tomMatch439NameNumberfreshSubject_1 instanceof tom.gom.adt.gom.types.hookdecl.MakeHookDecl) ) {{  tom.gom.adt.gom.types.SlotList  tomMatch439NameNumber_freshVar_0= tomMatch439NameNumberfreshSubject_1.getSlotArgs() ;{  tom.gom.adt.code.types.Code  tomMatch439NameNumber_freshVar_1= tomMatch439NameNumberfreshSubject_1.getCode() ;if ( true ) {

        SlotFieldList newArgs = makeSlotFieldListFromSlotList(tomMatch439NameNumber_freshVar_0);
        return  tom.gom.adt.objects.types.hook.MakeHook.make(newArgs, tomMatch439NameNumber_freshVar_1) ;
      }}}}}}if ( (hookDecl instanceof tom.gom.adt.gom.types.HookDecl) ) {{  tom.gom.adt.gom.types.HookDecl  tomMatch439NameNumberfreshSubject_1=(( tom.gom.adt.gom.types.HookDecl )hookDecl);if ( (tomMatch439NameNumberfreshSubject_1 instanceof tom.gom.adt.gom.types.hookdecl.BlockHookDecl) ) {{  tom.gom.adt.code.types.Code  tomMatch439NameNumber_freshVar_2= tomMatch439NameNumberfreshSubject_1.getCode() ;if ( true ) {

        return  tom.gom.adt.objects.types.hook.BlockHook.make(tomMatch439NameNumber_freshVar_2) ;
      }}}}}if ( (hookDecl instanceof tom.gom.adt.gom.types.HookDecl) ) {{  tom.gom.adt.gom.types.HookDecl  tomMatch439NameNumberfreshSubject_1=(( tom.gom.adt.gom.types.HookDecl )hookDecl);if ( (tomMatch439NameNumberfreshSubject_1 instanceof tom.gom.adt.gom.types.hookdecl.InterfaceHookDecl) ) {{  tom.gom.adt.code.types.Code  tomMatch439NameNumber_freshVar_3= tomMatch439NameNumberfreshSubject_1.getCode() ;if ( true ) {

        return  tom.gom.adt.objects.types.hook.InterfaceHook.make(tomMatch439NameNumber_freshVar_3) ;
      }}}}}if ( (hookDecl instanceof tom.gom.adt.gom.types.HookDecl) ) {{  tom.gom.adt.gom.types.HookDecl  tomMatch439NameNumberfreshSubject_1=(( tom.gom.adt.gom.types.HookDecl )hookDecl);if ( (tomMatch439NameNumberfreshSubject_1 instanceof tom.gom.adt.gom.types.hookdecl.ImportHookDecl) ) {{  tom.gom.adt.code.types.Code  tomMatch439NameNumber_freshVar_4= tomMatch439NameNumberfreshSubject_1.getCode() ;if ( true ) {

        return  tom.gom.adt.objects.types.hook.ImportHook.make(tomMatch439NameNumber_freshVar_4) ;
      }}}}}if ( (hookDecl instanceof tom.gom.adt.gom.types.HookDecl) ) {{  tom.gom.adt.gom.types.HookDecl  tomMatch439NameNumberfreshSubject_1=(( tom.gom.adt.gom.types.HookDecl )hookDecl);if ( (tomMatch439NameNumberfreshSubject_1 instanceof tom.gom.adt.gom.types.hookdecl.MappingHookDecl) ) {{  tom.gom.adt.code.types.Code  tomMatch439NameNumber_freshVar_5= tomMatch439NameNumberfreshSubject_1.getCode() ;if ( true ) {

        return  tom.gom.adt.objects.types.hook.MappingHook.make(tomMatch439NameNumber_freshVar_5) ;
      }}}}}}

    throw new GomRuntimeException(
        "Hook declaration " + hookDecl+ " not processed");
  }

  private static SlotFieldList makeSlotFieldListFromSlotList(SlotList args) {
    SlotFieldList newArgs =  tom.gom.adt.objects.types.slotfieldlist.EmptyconcSlotField.make() ;
    while(!args.isEmptyconcSlot()) {
      Slot arg = args.getHeadconcSlot();
      args = args.getTailconcSlot();
      {if ( (arg instanceof tom.gom.adt.gom.types.Slot) ) {{  tom.gom.adt.gom.types.Slot  tomMatch440NameNumberfreshSubject_1=(( tom.gom.adt.gom.types.Slot )arg);if ( (tomMatch440NameNumberfreshSubject_1 instanceof tom.gom.adt.gom.types.slot.Slot) ) {{  String  tomMatch440NameNumber_freshVar_0= tomMatch440NameNumberfreshSubject_1.getName() ;{  tom.gom.adt.gom.types.SortDecl  tomMatch440NameNumber_freshVar_1= tomMatch440NameNumberfreshSubject_1.getSort() ;if ( true ) {

          ClassName slotClassName = (ClassName)
            sortClassNameForSortDecl.get(tomMatch440NameNumber_freshVar_1);
          newArgs = tom_append_list_concSlotField(newArgs, tom.gom.adt.objects.types.slotfieldlist.ConsconcSlotField.make( tom.gom.adt.objects.types.slotfield.SlotField.make(tomMatch440NameNumber_freshVar_0, slotClassName) , tom.gom.adt.objects.types.slotfieldlist.EmptyconcSlotField.make() ) );
        }}}}}}}

    }
    return newArgs;
  }
}
