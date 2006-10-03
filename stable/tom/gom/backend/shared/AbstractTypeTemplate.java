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

import tom.gom.backend.TemplateHookedClass;
import tom.gom.adt.objects.types.*;

public class AbstractTypeTemplate extends TemplateHookedClass {
  ClassName visitor;
  ClassNameList sortList;

  public AbstractTypeTemplate(ClassName className,
                              ClassName visitor,
                              ClassNameList sortList,
                              HookList hooks) {
    super(className,hooks);
    this.visitor = visitor;
    this.sortList = sortList;
  }

  public void generate(java.io.Writer writer) throws java.io.IOException {
    writer.write(
"\npackage "/* Generated by TOM (version 2.4rc2): Do not edit this file */+getPackage()+";\n"/* Generated by TOM (version 2.4rc2): Do not edit this file */+generateImport()+"\n\npublic abstract class "/* Generated by TOM (version 2.4rc2): Do not edit this file */+className()+" implements shared.SharedObjectWithID, jjtraveler.Visitable, Comparable "/* Generated by TOM (version 2.4rc2): Do not edit this file */+generateInterface()+" {\n\n"/* Generated by TOM (version 2.4rc2): Do not edit this file */+generateBlock()+"\n\n  private int uniqueID;\n\n  public abstract aterm.ATerm toATerm();\n\n  public abstract String symbolName();\n\n  public String toString() {\n    java.lang.StringBuffer buffer = new java.lang.StringBuffer();\n    toStringBuffer(buffer);\n    return buffer.toString();\n  }\n\n  public abstract void toStringBuffer(java.lang.StringBuffer buffer);\n\n  public abstract int compareTo(Object o);\n\n  public abstract int compareToLPO(Object o);\n\n  public int getUniqueIdentifier() {\n    return uniqueID;\n  }\n\n  public void setUniqueIdentifier(int uniqueID) {\n    this.uniqueID = uniqueID;\n  }\n\n  abstract public "/* Generated by TOM (version 2.4rc2): Do not edit this file */+className()+" accept("/* Generated by TOM (version 2.4rc2): Do not edit this file */+fullClassName(visitor)+" v) throws jjtraveler.VisitFailure;\n}\n"



































);
  }

}
