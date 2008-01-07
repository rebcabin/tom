/* Generated by TOM (version 2.6alpha): Do not edit this file *//*
 * Copyright (c) 2000-2007, INRIA
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met: 
 *	- Redistributions of source code must retain the above copyright
 *	notice, this list of conditions and the following disclaimer.  
 *	- Redistributions in binary form must reproduce the above copyright
 *	notice, this list of conditions and the following disclaimer in the
 *	documentation and/or other materials provided with the distribution.
 *	- Neither the name of the INRIA nor the names of its
 *	contributors may be used to endorse or promote products derived from
 *	this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package tom.library.bytecode;

import java.util.HashMap;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Label;
import org.objectweb.asm.Type;

import tom.library.adt.bytecode.*;
import tom.library.adt.bytecode.types.*;

public class ToolBox {
  /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */ /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */     private static   tom.library.sl.Strategy  tom_append_list_Sequence( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {     if(( l1 == null )) {       return l2;     } else if(( l2 == null )) {       return l1;     } else if(( (l1 instanceof tom.library.sl.Sequence) )) {       if(( ((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )) == null )) {         return ( (l2==null)?((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1):new tom.library.sl.Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1),l2) );       } else {         return ( (tom_append_list_Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),l2)==null)?((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1):new tom.library.sl.Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ):l1),tom_append_list_Sequence(((( (l1 instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),l2)) );       }     } else {       return ( (l2==null)?l1:new tom.library.sl.Sequence(l1,l2) );     }   }   private static   tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {     if( (begin.equals(end)) ) {       return tail;     } else {       return ( (( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)==null)?((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin):new tom.library.sl.Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(((( (begin instanceof tom.library.sl.Sequence) ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):( null )),end,tail)) );     }   }    /* Generated by TOM (version 2.6alpha): Do not edit this file */ /* Generated by TOM (version 2.6alpha): Do not edit this file */private static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { return ( ( new tom.library.sl.Mu(( new tom.library.sl.MuVar("_x") ),( (( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )==null)?v:new tom.library.sl.Sequence(v,( (( null )==null)?( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ):new tom.library.sl.Sequence(( new tom.library.sl.All(( new tom.library.sl.MuVar("_x") )) ),( null )) )) )) ) ); }   /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */ /* Generated by TOM (version 2.6alpha): Do not edit this file */ /* Generated by TOM (version 2.6alpha): Do not edit this file */    private static   tom.library.adt.bytecode.types.TStringList  tom_append_list_StringList( tom.library.adt.bytecode.types.TStringList l1,  tom.library.adt.bytecode.types.TStringList  l2) {     if( l1.isEmptyStringList() ) {       return l2;     } else if( l2.isEmptyStringList() ) {       return l1;     } else if(  l1.getTailStringList() .isEmptyStringList() ) {       return  tom.library.adt.bytecode.types.tstringlist.ConsStringList.make( l1.getHeadStringList() ,l2) ;     } else {       return  tom.library.adt.bytecode.types.tstringlist.ConsStringList.make( l1.getHeadStringList() ,tom_append_list_StringList( l1.getTailStringList() ,l2)) ;     }   }   private static   tom.library.adt.bytecode.types.TStringList  tom_get_slice_StringList( tom.library.adt.bytecode.types.TStringList  begin,  tom.library.adt.bytecode.types.TStringList  end, tom.library.adt.bytecode.types.TStringList  tail) {     if( begin.equals(end) ) {       return tail;     } else {       return  tom.library.adt.bytecode.types.tstringlist.ConsStringList.make( begin.getHeadStringList() ,( tom.library.adt.bytecode.types.TStringList )tom_get_slice_StringList( begin.getTailStringList() ,end,tail)) ;     }   }      private static   tom.library.adt.bytecode.types.TAccessList  tom_append_list_AccessList( tom.library.adt.bytecode.types.TAccessList l1,  tom.library.adt.bytecode.types.TAccessList  l2) {     if( l1.isEmptyAccessList() ) {       return l2;     } else if( l2.isEmptyAccessList() ) {       return l1;     } else if(  l1.getTailAccessList() .isEmptyAccessList() ) {       return  tom.library.adt.bytecode.types.taccesslist.ConsAccessList.make( l1.getHeadAccessList() ,l2) ;     } else {       return  tom.library.adt.bytecode.types.taccesslist.ConsAccessList.make( l1.getHeadAccessList() ,tom_append_list_AccessList( l1.getTailAccessList() ,l2)) ;     }   }   private static   tom.library.adt.bytecode.types.TAccessList  tom_get_slice_AccessList( tom.library.adt.bytecode.types.TAccessList  begin,  tom.library.adt.bytecode.types.TAccessList  end, tom.library.adt.bytecode.types.TAccessList  tail) {     if( begin.equals(end) ) {       return tail;     } else {       return  tom.library.adt.bytecode.types.taccesslist.ConsAccessList.make( begin.getHeadAccessList() ,( tom.library.adt.bytecode.types.TAccessList )tom_get_slice_AccessList( begin.getTailAccessList() ,end,tail)) ;     }   }      private static   tom.library.adt.bytecode.types.TintList  tom_append_list_intList( tom.library.adt.bytecode.types.TintList l1,  tom.library.adt.bytecode.types.TintList  l2) {     if( l1.isEmptyintList() ) {       return l2;     } else if( l2.isEmptyintList() ) {       return l1;     } else if(  l1.getTailintList() .isEmptyintList() ) {       return  tom.library.adt.bytecode.types.tintlist.ConsintList.make( l1.getHeadintList() ,l2) ;     } else {       return  tom.library.adt.bytecode.types.tintlist.ConsintList.make( l1.getHeadintList() ,tom_append_list_intList( l1.getTailintList() ,l2)) ;     }   }   private static   tom.library.adt.bytecode.types.TintList  tom_get_slice_intList( tom.library.adt.bytecode.types.TintList  begin,  tom.library.adt.bytecode.types.TintList  end, tom.library.adt.bytecode.types.TintList  tail) {     if( begin.equals(end) ) {       return tail;     } else {       return  tom.library.adt.bytecode.types.tintlist.ConsintList.make( begin.getHeadintList() ,( tom.library.adt.bytecode.types.TintList )tom_get_slice_intList( begin.getTailintList() ,end,tail)) ;     }   }      private static   tom.library.adt.bytecode.types.TFieldDescriptorList  tom_append_list_FieldDescriptorList( tom.library.adt.bytecode.types.TFieldDescriptorList l1,  tom.library.adt.bytecode.types.TFieldDescriptorList  l2) {     if( l1.isEmptyFieldDescriptorList() ) {       return l2;     } else if( l2.isEmptyFieldDescriptorList() ) {       return l1;     } else if(  l1.getTailFieldDescriptorList() .isEmptyFieldDescriptorList() ) {       return  tom.library.adt.bytecode.types.tfielddescriptorlist.ConsFieldDescriptorList.make( l1.getHeadFieldDescriptorList() ,l2) ;     } else {       return  tom.library.adt.bytecode.types.tfielddescriptorlist.ConsFieldDescriptorList.make( l1.getHeadFieldDescriptorList() ,tom_append_list_FieldDescriptorList( l1.getTailFieldDescriptorList() ,l2)) ;     }   }   private static   tom.library.adt.bytecode.types.TFieldDescriptorList  tom_get_slice_FieldDescriptorList( tom.library.adt.bytecode.types.TFieldDescriptorList  begin,  tom.library.adt.bytecode.types.TFieldDescriptorList  end, tom.library.adt.bytecode.types.TFieldDescriptorList  tail) {     if( begin.equals(end) ) {       return tail;     } else {       return  tom.library.adt.bytecode.types.tfielddescriptorlist.ConsFieldDescriptorList.make( begin.getHeadFieldDescriptorList() ,( tom.library.adt.bytecode.types.TFieldDescriptorList )tom_get_slice_FieldDescriptorList( begin.getTailFieldDescriptorList() ,end,tail)) ;     }   }    


  private final static int[] accessFlags = {
    Opcodes.ACC_ABSTRACT,
    Opcodes.ACC_ANNOTATION,
    Opcodes.ACC_BRIDGE,
    Opcodes.ACC_DEPRECATED,
    Opcodes.ACC_ENUM,
    Opcodes.ACC_FINAL,
    Opcodes.ACC_INTERFACE,
    Opcodes.ACC_NATIVE,
    Opcodes.ACC_PRIVATE,
    Opcodes.ACC_PROTECTED,
    Opcodes.ACC_PUBLIC,
    Opcodes.ACC_STATIC,
    Opcodes.ACC_STRICT,
    Opcodes.ACC_SUPER,
    Opcodes.ACC_SYNCHRONIZED,
    Opcodes.ACC_SYNTHETIC,
    Opcodes.ACC_TRANSIENT,
    Opcodes.ACC_VARARGS,
    Opcodes.ACC_VOLATILE
  };
  private final static TAccess[] accessObj = {
     tom.library.adt.bytecode.types.taccess.ABSTRACT.make() ,
     tom.library.adt.bytecode.types.taccess.ANNOTATION.make() ,
     tom.library.adt.bytecode.types.taccess.BRIDGE.make() ,
     tom.library.adt.bytecode.types.taccess.DEPRECATED.make() ,
     tom.library.adt.bytecode.types.taccess.ENUM.make() ,
     tom.library.adt.bytecode.types.taccess.FINAL.make() ,
     tom.library.adt.bytecode.types.taccess.INTERFACE.make() ,
     tom.library.adt.bytecode.types.taccess.NATIVE.make() ,
     tom.library.adt.bytecode.types.taccess.PRIVATE.make() ,
     tom.library.adt.bytecode.types.taccess.PROTECTED.make() ,
     tom.library.adt.bytecode.types.taccess.PUBLIC.make() ,
     tom.library.adt.bytecode.types.taccess.STATIC.make() ,
     tom.library.adt.bytecode.types.taccess.STRICT.make() ,
     tom.library.adt.bytecode.types.taccess.SUPER.make() ,
     tom.library.adt.bytecode.types.taccess.SYNCHRONIZED.make() ,
     tom.library.adt.bytecode.types.taccess.SYNTHETIC.make() ,
     tom.library.adt.bytecode.types.taccess.TRANSIENT.make() ,
     tom.library.adt.bytecode.types.taccess.VARARGS.make() ,
     tom.library.adt.bytecode.types.taccess.VOLATILE.make() 
  };

  public static TAccessList buildTAccess(int access) {
    TAccessList list =  tom.library.adt.bytecode.types.taccesslist.EmptyAccessList.make() ;
    
    for(int i = 0; i < accessFlags.length; i++) {
      if((access & accessFlags[i]) != 0)
        list =  tom.library.adt.bytecode.types.taccesslist.ConsAccessList.make(accessObj[i], list) ;
    }

    return list;
  }


  public static int buildAccessValue(TAccessList list){
    int value =0;
    HashMap map = new HashMap();
    for(int i =0;i<accessObj.length;i++){
      map.put(accessObj[i],new Integer(accessFlags[i]));
    }

    {if ( (list instanceof tom.library.adt.bytecode.types.TAccessList) ) {{  tom.library.adt.bytecode.types.TAccessList  tomMatch558NameNumberfreshSubject_1=(( tom.library.adt.bytecode.types.TAccessList )list);if ( ((tomMatch558NameNumberfreshSubject_1 instanceof tom.library.adt.bytecode.types.taccesslist.ConsAccessList) || (tomMatch558NameNumberfreshSubject_1 instanceof tom.library.adt.bytecode.types.taccesslist.EmptyAccessList)) ) {{  tom.library.adt.bytecode.types.TAccessList  tomMatch558NameNumber_freshVar_0=tomMatch558NameNumberfreshSubject_1;{  tom.library.adt.bytecode.types.TAccessList  tomMatch558NameNumber_begin_2=tomMatch558NameNumber_freshVar_0;{  tom.library.adt.bytecode.types.TAccessList  tomMatch558NameNumber_end_3=tomMatch558NameNumber_freshVar_0;do {{{  tom.library.adt.bytecode.types.TAccessList  tomMatch558NameNumber_freshVar_1=tomMatch558NameNumber_end_3;if (!( tomMatch558NameNumber_freshVar_1.isEmptyAccessList() )) {{  tom.library.adt.bytecode.types.TAccessList  tomMatch558NameNumber_freshVar_4= tomMatch558NameNumber_freshVar_1.getTailAccessList() ;if ( true ) {

        value = value | ((Integer)map.get( tomMatch558NameNumber_freshVar_1.getHeadAccessList() )).intValue();
      }}}}if ( tomMatch558NameNumber_end_3.isEmptyAccessList() ) {tomMatch558NameNumber_end_3=tomMatch558NameNumber_begin_2;} else {tomMatch558NameNumber_end_3= tomMatch558NameNumber_end_3.getTailAccessList() ;}}} while(!( tomMatch558NameNumber_end_3.equals(tomMatch558NameNumber_begin_2) ));}}}}}}}

    return value;   
  }

  public static String buildSignature(TSignature signature){
    String sig = null;
    {if ( (signature instanceof tom.library.adt.bytecode.types.TSignature) ) {{  tom.library.adt.bytecode.types.TSignature  tomMatch559NameNumberfreshSubject_1=(( tom.library.adt.bytecode.types.TSignature )signature);if ( (tomMatch559NameNumberfreshSubject_1 instanceof tom.library.adt.bytecode.types.tsignature.Signature) ) {{  String  tomMatch559NameNumber_freshVar_0= tomMatch559NameNumberfreshSubject_1.getsig() ;if ( true ) {
sig=tomMatch559NameNumber_freshVar_0;}}}}}}

    return sig;
  }

  public static TValue buildTValue(Object v) {
    if(v instanceof String)
      return  tom.library.adt.bytecode.types.tvalue.StringValue.make((String)v) ;
    else if(v instanceof Integer)
      return  tom.library.adt.bytecode.types.tvalue.IntValue.make(((Integer)v).intValue()) ;
    else if(v instanceof Long)
      return  tom.library.adt.bytecode.types.tvalue.LongValue.make(((Long)v).longValue()) ;
    else if(v instanceof Float)
      return  tom.library.adt.bytecode.types.tvalue.FloatValue.make(((Float)v).floatValue()) ;
    else if(v instanceof Double)
      return  tom.library.adt.bytecode.types.tvalue.DoubleValue.make(((Double)v).doubleValue()) ;

    return null;
  }

  public static Object buildConstant(TValue value) {
      {if ( (value instanceof tom.library.adt.bytecode.types.TValue) ) {{  tom.library.adt.bytecode.types.TValue  tomMatch560NameNumberfreshSubject_1=(( tom.library.adt.bytecode.types.TValue )value);if ( (tomMatch560NameNumberfreshSubject_1 instanceof tom.library.adt.bytecode.types.tvalue.StringValue) ) {{  String  tomMatch560NameNumber_freshVar_0= tomMatch560NameNumberfreshSubject_1.gets() ;if ( true ) {
 return tomMatch560NameNumber_freshVar_0;}}}}}if ( (value instanceof tom.library.adt.bytecode.types.TValue) ) {{  tom.library.adt.bytecode.types.TValue  tomMatch560NameNumberfreshSubject_1=(( tom.library.adt.bytecode.types.TValue )value);if ( (tomMatch560NameNumberfreshSubject_1 instanceof tom.library.adt.bytecode.types.tvalue.IntValue) ) {{  int  tomMatch560NameNumber_freshVar_1= tomMatch560NameNumberfreshSubject_1.geti() ;if ( true ) {
return new Integer(tomMatch560NameNumber_freshVar_1);}}}}}if ( (value instanceof tom.library.adt.bytecode.types.TValue) ) {{  tom.library.adt.bytecode.types.TValue  tomMatch560NameNumberfreshSubject_1=(( tom.library.adt.bytecode.types.TValue )value);if ( (tomMatch560NameNumberfreshSubject_1 instanceof tom.library.adt.bytecode.types.tvalue.LongValue) ) {{  long  tomMatch560NameNumber_freshVar_2= tomMatch560NameNumberfreshSubject_1.getl() ;if ( true ) {
return new Long(tomMatch560NameNumber_freshVar_2);}}}}}if ( (value instanceof tom.library.adt.bytecode.types.TValue) ) {{  tom.library.adt.bytecode.types.TValue  tomMatch560NameNumberfreshSubject_1=(( tom.library.adt.bytecode.types.TValue )value);if ( (tomMatch560NameNumberfreshSubject_1 instanceof tom.library.adt.bytecode.types.tvalue.FloatValue) ) {{  float  tomMatch560NameNumber_freshVar_3= tomMatch560NameNumberfreshSubject_1.getf() ;if ( true ) {
return new Float(tomMatch560NameNumber_freshVar_3);}}}}}if ( (value instanceof tom.library.adt.bytecode.types.TValue) ) {{  tom.library.adt.bytecode.types.TValue  tomMatch560NameNumberfreshSubject_1=(( tom.library.adt.bytecode.types.TValue )value);if ( (tomMatch560NameNumberfreshSubject_1 instanceof tom.library.adt.bytecode.types.tvalue.DoubleValue) ) {{  double  tomMatch560NameNumber_freshVar_4= tomMatch560NameNumberfreshSubject_1.getd() ;if ( true ) {
return new Double(tomMatch560NameNumber_freshVar_4);}}}}}}

      return null;
  }

  public static TStringList buildTStringList(String[] array) {
    TStringList list =  tom.library.adt.bytecode.types.tstringlist.EmptyStringList.make() ;
    if(array != null) {
      for(int i = array.length - 1; i >= 0; i--)
        list =  tom.library.adt.bytecode.types.tstringlist.ConsStringList.make(array[i], list) ;
    }

    return list;
  }

  public static TintList buildTintList(int[] array) {
    TintList list =  tom.library.adt.bytecode.types.tintlist.EmptyintList.make() ;
    if(array != null) {
      for(int i = array.length - 1; i >= 0; i--) {
        list =  tom.library.adt.bytecode.types.tintlist.ConsintList.make(array[i], list) ;
      }
    }

    return list;
  }

  public static TType buildTType(String type) {
    int t = Type.getType(type).getSort();
    TType ret = null;
    switch(t) {
      case Type.ARRAY:
        ret =  tom.library.adt.bytecode.types.ttype.ARRAY.make() ;
        break;
      case Type.BOOLEAN:
        ret =  tom.library.adt.bytecode.types.ttype.BOOLEAN.make() ;
        break;
      case Type.BYTE:
        ret =  tom.library.adt.bytecode.types.ttype.BYTE.make() ;
        break;
      case Type.CHAR:
        ret =  tom.library.adt.bytecode.types.ttype.CHAR.make() ;
        break;
      case Type.DOUBLE:
        ret =  tom.library.adt.bytecode.types.ttype.DOUBLE.make() ;
        break;
      case Type.FLOAT:
        ret =  tom.library.adt.bytecode.types.ttype.FLOAT.make() ;
        break;
      case Type.INT:
        ret =  tom.library.adt.bytecode.types.ttype.INT.make() ;
        break;
      case Type.LONG:
        ret =  tom.library.adt.bytecode.types.ttype.LONG.make() ;
        break;
      case Type.OBJECT:
        ret =  tom.library.adt.bytecode.types.ttype.OBJECT.make() ;
        break;
      case Type.SHORT:
        ret =  tom.library.adt.bytecode.types.ttype.SHORT.make() ;
        break;
      case Type.VOID:
        ret =  tom.library.adt.bytecode.types.ttype.VOID.make() ;
        break;
    }

    return ret;
  }

  private static class Counter { public int count = 0; }
  public static TFieldDescriptor buildTFieldDescriptor(String desc) {
    Counter count = new Counter();
    TFieldDescriptor fDesc = buildTFieldDescriptorFrom(desc, count);
    if(count.count != desc.length())
      System.err.println("Malformed descriptor : " + desc);
    return fDesc;
  }

  private static TFieldDescriptor buildTFieldDescriptorFrom(String desc, Counter count) {
    TFieldDescriptor fDesc = null;
    switch(desc.charAt(count.count)) {
      case 'L':
        count.count++;
        int j = desc.indexOf(';', count.count);
        if(j == -1)
          System.err.println("Malformed descriptor : " + desc);
        String className = desc.substring(count.count, j);
        count.count += className.length() + 1;
        fDesc =  tom.library.adt.bytecode.types.tfielddescriptor.ObjectType.make(className) ;
        break;
      case '[':
        count.count++;
        fDesc =  tom.library.adt.bytecode.types.tfielddescriptor.ArrayType.make(buildTFieldDescriptorFrom(desc,count)) ;
        break;
      case 'B':
        count.count++;
        fDesc =  tom.library.adt.bytecode.types.tfielddescriptor.B.make() ;
        break;
      case 'C':
        count.count++;
        fDesc =  tom.library.adt.bytecode.types.tfielddescriptor.C.make() ;
        break;
      case 'D':
        count.count++;
        fDesc =  tom.library.adt.bytecode.types.tfielddescriptor.D.make() ;
        break;
      case 'F':
        count.count++;
        fDesc =  tom.library.adt.bytecode.types.tfielddescriptor.F.make() ;
        break;
      case 'I':
        count.count++;
        fDesc =  tom.library.adt.bytecode.types.tfielddescriptor.I.make() ;
        break;
      case 'J':
        count.count++;
        fDesc =  tom.library.adt.bytecode.types.tfielddescriptor.J.make() ;
        break;
      case 'S':
        count.count++;
        fDesc =  tom.library.adt.bytecode.types.tfielddescriptor.S.make() ;
        break;
      case 'Z':
        count.count++;
        fDesc =  tom.library.adt.bytecode.types.tfielddescriptor.Z.make() ;
        break;
    }
    if(fDesc == null)
      System.err.println("Malformed descriptor : " + desc);
    return fDesc;
  }

  public static TReturnDescriptor buildTReturnDescriptor(String desc) {
    if(desc.charAt(0) == 'V' && desc.length() == 1)
      return  tom.library.adt.bytecode.types.treturndescriptor.Void.make() ;
    return  tom.library.adt.bytecode.types.treturndescriptor.ReturnDescriptor.make(buildTFieldDescriptor(desc)) ;
  }

  public static TMethodDescriptor buildTMethodDescriptor(String desc) {
    int endParam = desc.indexOf(')', 1);
    if(desc.charAt(0) != '(' || endParam == -1)
      System.err.println("Malformed descriptor : " + desc);

    TFieldDescriptorList fList =  tom.library.adt.bytecode.types.tfielddescriptorlist.EmptyFieldDescriptorList.make() ;
    Counter count = new Counter();
    count.count++;
    while(count.count < endParam)
      fList = tom_append_list_FieldDescriptorList(fList, tom.library.adt.bytecode.types.tfielddescriptorlist.ConsFieldDescriptorList.make(buildTFieldDescriptorFrom(desc,count), tom.library.adt.bytecode.types.tfielddescriptorlist.EmptyFieldDescriptorList.make() ) );
    if(count.count != endParam)
      System.err.println("Malformed descriptor : " + desc);
    TReturnDescriptor ret = buildTReturnDescriptor(desc.substring(count.count + 1));
    return  tom.library.adt.bytecode.types.tmethoddescriptor.MethodDescriptor.make(fList, ret) ;
  }

  private static class BuildDescriptor extends  tom.library.adt.bytecode.BytecodeBasicStrategy  {private  StringBuilder  sb; public BuildDescriptor( StringBuilder  sb) { super(( new tom.library.sl.Identity() ));this.sb=sb;}public  StringBuilder  getsb() { return sb;}public tom.library.sl.Visitable[] getChildren() {tom.library.sl.Visitable[] stratChilds = new tom.library.sl.Visitable[getChildCount()];stratChilds[0] = super.getChildAt(0);return stratChilds;}public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {super.setChildAt(0, children[0]);return this;}public int getChildCount() { return 1; }public tom.library.sl.Visitable getChildAt(int index) {switch (index) {case 0: return super.getChildAt(0);default: throw new IndexOutOfBoundsException();}}public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {switch (index) {case 0: return super.setChildAt(0, child);default: throw new IndexOutOfBoundsException();}}public  tom.library.adt.bytecode.types.TFieldDescriptor  visit_TFieldDescriptor( tom.library.adt.bytecode.types.TFieldDescriptor  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{if ( (tom__arg instanceof tom.library.adt.bytecode.types.TFieldDescriptor) ) {{  tom.library.adt.bytecode.types.TFieldDescriptor  tomMatch561NameNumberfreshSubject_1=(( tom.library.adt.bytecode.types.TFieldDescriptor )tom__arg);if ( (tomMatch561NameNumberfreshSubject_1 instanceof tom.library.adt.bytecode.types.tfielddescriptor.ObjectType) ) {{  String  tomMatch561NameNumber_freshVar_0= tomMatch561NameNumberfreshSubject_1.getclassName() ;if ( true ) {





 sb.append("L" + tomMatch561NameNumber_freshVar_0+ ";"); }}}}}if ( (tom__arg instanceof tom.library.adt.bytecode.types.TFieldDescriptor) ) {{  tom.library.adt.bytecode.types.TFieldDescriptor  tomMatch561NameNumberfreshSubject_1=(( tom.library.adt.bytecode.types.TFieldDescriptor )tom__arg);if ( (tomMatch561NameNumberfreshSubject_1 instanceof tom.library.adt.bytecode.types.tfielddescriptor.ArrayType) ) {if ( true ) {
 sb.append('['); }}}}if ( (tom__arg instanceof tom.library.adt.bytecode.types.TFieldDescriptor) ) {{  tom.library.adt.bytecode.types.TFieldDescriptor  tomMatch561NameNumberfreshSubject_1=(( tom.library.adt.bytecode.types.TFieldDescriptor )tom__arg);if ( (tomMatch561NameNumberfreshSubject_1 instanceof tom.library.adt.bytecode.types.tfielddescriptor.B) ) {if ( true ) {
 sb.append('B'); }}}}if ( (tom__arg instanceof tom.library.adt.bytecode.types.TFieldDescriptor) ) {{  tom.library.adt.bytecode.types.TFieldDescriptor  tomMatch561NameNumberfreshSubject_1=(( tom.library.adt.bytecode.types.TFieldDescriptor )tom__arg);if ( (tomMatch561NameNumberfreshSubject_1 instanceof tom.library.adt.bytecode.types.tfielddescriptor.C) ) {if ( true ) {
 sb.append('C'); }}}}if ( (tom__arg instanceof tom.library.adt.bytecode.types.TFieldDescriptor) ) {{  tom.library.adt.bytecode.types.TFieldDescriptor  tomMatch561NameNumberfreshSubject_1=(( tom.library.adt.bytecode.types.TFieldDescriptor )tom__arg);if ( (tomMatch561NameNumberfreshSubject_1 instanceof tom.library.adt.bytecode.types.tfielddescriptor.D) ) {if ( true ) {
 sb.append('D'); }}}}if ( (tom__arg instanceof tom.library.adt.bytecode.types.TFieldDescriptor) ) {{  tom.library.adt.bytecode.types.TFieldDescriptor  tomMatch561NameNumberfreshSubject_1=(( tom.library.adt.bytecode.types.TFieldDescriptor )tom__arg);if ( (tomMatch561NameNumberfreshSubject_1 instanceof tom.library.adt.bytecode.types.tfielddescriptor.F) ) {if ( true ) {
 sb.append('F'); }}}}if ( (tom__arg instanceof tom.library.adt.bytecode.types.TFieldDescriptor) ) {{  tom.library.adt.bytecode.types.TFieldDescriptor  tomMatch561NameNumberfreshSubject_1=(( tom.library.adt.bytecode.types.TFieldDescriptor )tom__arg);if ( (tomMatch561NameNumberfreshSubject_1 instanceof tom.library.adt.bytecode.types.tfielddescriptor.I) ) {if ( true ) {
 sb.append('I'); }}}}if ( (tom__arg instanceof tom.library.adt.bytecode.types.TFieldDescriptor) ) {{  tom.library.adt.bytecode.types.TFieldDescriptor  tomMatch561NameNumberfreshSubject_1=(( tom.library.adt.bytecode.types.TFieldDescriptor )tom__arg);if ( (tomMatch561NameNumberfreshSubject_1 instanceof tom.library.adt.bytecode.types.tfielddescriptor.J) ) {if ( true ) {
 sb.append('J'); }}}}if ( (tom__arg instanceof tom.library.adt.bytecode.types.TFieldDescriptor) ) {{  tom.library.adt.bytecode.types.TFieldDescriptor  tomMatch561NameNumberfreshSubject_1=(( tom.library.adt.bytecode.types.TFieldDescriptor )tom__arg);if ( (tomMatch561NameNumberfreshSubject_1 instanceof tom.library.adt.bytecode.types.tfielddescriptor.S) ) {if ( true ) {
 sb.append('S'); }}}}if ( (tom__arg instanceof tom.library.adt.bytecode.types.TFieldDescriptor) ) {{  tom.library.adt.bytecode.types.TFieldDescriptor  tomMatch561NameNumberfreshSubject_1=(( tom.library.adt.bytecode.types.TFieldDescriptor )tom__arg);if ( (tomMatch561NameNumberfreshSubject_1 instanceof tom.library.adt.bytecode.types.tfielddescriptor.Z) ) {if ( true ) {
 sb.append('Z'); }}}}}return super.visit_TFieldDescriptor(tom__arg,introspector); }public  tom.library.adt.bytecode.types.TMethodDescriptor  visit_TMethodDescriptor( tom.library.adt.bytecode.types.TMethodDescriptor  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{if ( (tom__arg instanceof tom.library.adt.bytecode.types.TMethodDescriptor) ) {{  tom.library.adt.bytecode.types.TMethodDescriptor  tomMatch562NameNumberfreshSubject_1=(( tom.library.adt.bytecode.types.TMethodDescriptor )tom__arg);if ( true ) {



 sb.append('('); }}}}return super.visit_TMethodDescriptor(tom__arg,introspector); }public  tom.library.adt.bytecode.types.TReturnDescriptor  visit_TReturnDescriptor( tom.library.adt.bytecode.types.TReturnDescriptor  tom__arg, tom.library.sl.Introspector introspector) throws tom.library.sl.VisitFailure {{if ( (tom__arg instanceof tom.library.adt.bytecode.types.TReturnDescriptor) ) {{  tom.library.adt.bytecode.types.TReturnDescriptor  tomMatch563NameNumberfreshSubject_1=(( tom.library.adt.bytecode.types.TReturnDescriptor )tom__arg);if ( true ) {



 sb.append(')'); }}}if ( (tom__arg instanceof tom.library.adt.bytecode.types.TReturnDescriptor) ) {{  tom.library.adt.bytecode.types.TReturnDescriptor  tomMatch563NameNumberfreshSubject_1=(( tom.library.adt.bytecode.types.TReturnDescriptor )tom__arg);if ( (tomMatch563NameNumberfreshSubject_1 instanceof tom.library.adt.bytecode.types.treturndescriptor.Void) ) {if ( true ) {
 sb.append('V'); }}}}}return super.visit_TReturnDescriptor(tom__arg,introspector); }}private static  tom.library.sl.Strategy  tom_make_BuildDescriptor( StringBuilder  t0) { return new BuildDescriptor(t0); }



  public static String buildDescriptor(TFieldDescriptor desc) {
    StringBuilder sb = new StringBuilder();
    try {
      tom_make_TopDown(tom_make_BuildDescriptor(sb)).visitLight(desc);
    } catch(tom.library.sl.VisitFailure e) { }
    return sb.toString();
  }
  public static String buildDescriptor(TMethodDescriptor desc) {
    StringBuilder sb = new StringBuilder();
    try {
      tom_make_TopDown(tom_make_BuildDescriptor(sb)).visitLight(desc);
    } catch(tom.library.sl.VisitFailure e) { }
    return sb.toString();
  }
}

