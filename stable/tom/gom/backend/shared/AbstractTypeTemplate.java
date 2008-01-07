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

import java.io.*;
import java.util.*;

import tom.gom.backend.TemplateClass;
import tom.gom.backend.TemplateHookedClass;
import tom.gom.adt.objects.types.*;
import tom.gom.tools.error.GomRuntimeException;
import tom.platform.OptionManager;

public class AbstractTypeTemplate extends TemplateHookedClass {
  ClassNameList sortList;

  /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */  /* Generated by TOM (version 2.6alpha): Do not edit this file */  

  public AbstractTypeTemplate(File tomHomePath,
                              OptionManager manager,
                              List importList,
                              GomClass gomClass,
                              TemplateClass mapping) {
    super(gomClass,manager,tomHomePath,importList,mapping);
    {if ( (gomClass instanceof tom.gom.adt.objects.types.GomClass) ) {{  tom.gom.adt.objects.types.GomClass  tomMatch378NameNumberfreshSubject_1=(( tom.gom.adt.objects.types.GomClass )gomClass);if ( (tomMatch378NameNumberfreshSubject_1 instanceof tom.gom.adt.objects.types.gomclass.AbstractTypeClass) ) {{  tom.gom.adt.objects.types.ClassNameList  tomMatch378NameNumber_freshVar_0= tomMatch378NameNumberfreshSubject_1.getSortList() ;if ( true ) {

        this.sortList = tomMatch378NameNumber_freshVar_0;
        return;
      }}}}}}

    throw new GomRuntimeException(
        "Bad argument for AbstractTypeTemplate: " + gomClass);
  }

  public void generate(java.io.Writer writer) throws java.io.IOException {
    
    writer.write(
"\npackage "/* Generated by TOM (version 2.6alpha): Do not edit this file */+getPackage()+";\n"/* Generated by TOM (version 2.6alpha): Do not edit this file */+generateImport()+"\n\npublic abstract class "/* Generated by TOM (version 2.6alpha): Do not edit this file */+className()+" implements shared.SharedObjectWithID, tom.library.sl.Visitable, Comparable "/* Generated by TOM (version 2.6alpha): Do not edit this file */+generateInterface()+" {\n"




);

    if (! hooks.isEmptyConcHook()) {
      mapping.generate(writer); 
    }
    writer.write(
"\n"/* Generated by TOM (version 2.6alpha): Do not edit this file */+generateBlock()+"\n\n  private int uniqueID;\n\n  protected static final shared.SharedObjectFactory factory = shared.SingletonSharedObjectFactory.getInstance();\n  protected static final aterm.ATermFactory atermFactory = aterm.pure.SingletonFactory.getInstance();\n\n  public abstract aterm.ATerm toATerm();\n\n  public abstract String symbolName();\n\n  @Override\n  public String toString() {\n    java.lang.StringBuilder buffer = new java.lang.StringBuilder();\n    toStringBuilder(buffer);\n    return buffer.toString();\n  }\n\n  public abstract void toStringBuilder(java.lang.StringBuilder buffer);\n\n  public abstract int compareTo(Object o);\n\n  public abstract int compareToLPO(Object o);\n\n  public int getUniqueIdentifier() {\n    return uniqueID;\n  }\n\n  public void setUniqueIdentifier(int uniqueID) {\n    this.uniqueID = uniqueID;\n  }\n\n}\n"

































);
 }

}
