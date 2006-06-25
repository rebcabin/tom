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

public class IsOpTemplate extends TemplateClass {
  ClassName operator;

  /* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file */ private static boolean tom_terms_equal_String( String  t1,  String  t2) {  return  (t1.equals(t2))  ;}  /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ private static boolean tom_terms_equal_ClassName(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_fun_sym_ClassName( tom.gom.adt.objects.types.ClassName  t) {  return  (t!=null) && t.isClassName()  ;}private static  tom.gom.adt.objects.types.ClassName  tom_make_ClassName( String  t0,  String  t1) { return  tom.gom.adt.objects.types.classname.ClassName.make(t0, t1); }private static  String  tom_get_slot_ClassName_pkg( tom.gom.adt.objects.types.ClassName  t) {  return  t.getpkg()  ;}private static  String  tom_get_slot_ClassName_name( tom.gom.adt.objects.types.ClassName  t) {  return  t.getname()  ;} 

  /*
   * The argument is an operator class, and this template generatees the
   * assotiated isOp strategy
   */
  public IsOpTemplate(ClassName className) {
    super(className);
     if(className instanceof  tom.gom.adt.objects.types.ClassName ) { { tom.gom.adt.objects.types.ClassName  tom_match1_1=(( tom.gom.adt.objects.types.ClassName )className); if (tom_is_fun_sym_ClassName(tom_match1_1) ||  false ) { { String  tom_match1_1_pkg=tom_get_slot_ClassName_pkg(tom_match1_1); { String  tom_match1_1_name=tom_get_slot_ClassName_name(tom_match1_1); { String  tom_pkg=tom_match1_1_pkg; { String  tom_name=tom_match1_1_name; if ( true ) {

        String newpkg = tom_pkg.replaceFirst(".types.",".strategy.");
        String newname = "Is_"+tom_name;
        this.className = tom_make_ClassName(newpkg,newname);
       } } } } } } } }

    this.operator = className;
  }

  public String generate() {

    String classBody = "\npackage "/* Generated by TOM (version 2.4alpha): Do not edit this file */+getPackage()+";\n\nimport jjtraveler.Visitable;\nimport jjtraveler.VisitFailure;\n\npublic class "/* Generated by TOM (version 2.4alpha): Do not edit this file */+className()+" extends tom.library.strategy.mutraveler.Fail {\n  private static final String msg = \"Not an "/* Generated by TOM (version 2.4alpha): Do not edit this file */+className(operator)+"\";\n\n  public Visitable visit(Visitable any) throws VisitFailure {\n    if(any instanceof "/* Generated by TOM (version 2.4alpha): Do not edit this file */+fullClassName(operator)+") {\n      return any;\n    } else {\n      throw new VisitFailure(msg);\n    }\n  }\n}\n"
















;

    return classBody;
  }

  public String generateMapping() {
    return "\n%op Strategy "/* Generated by TOM (version 2.4alpha): Do not edit this file */+className()+"() {\n  is_fsym(t) { (t!=null) && t instanceof ("/* Generated by TOM (version 2.4alpha): Do not edit this file */+fullClassName()+")}\n  make() { new "/* Generated by TOM (version 2.4alpha): Do not edit this file */+fullClassName()+"() }\n}\n"




;
  }

  /** the class logger instance*/
  private Logger getLogger() {
    return Logger.getLogger(getClass().getName());
  }
}
