/*
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

import java.io.*;
import java.util.logging.*;
import tom.gom.backend.TemplateHookedClass;
import tom.gom.tools.GomEnvironment;
import tom.gom.tools.error.GomRuntimeException;
import tom.gom.adt.objects.types.*;

public class VariadicOperatorTemplate extends TemplateHookedClass {
  ClassName abstractType;
  ClassName sortName;
  GomClass empty;
  GomClass cons;

  %include { ../../adt/objects/Objects.tom}

  public VariadicOperatorTemplate(ClassName className,
                                  ClassName abstractType,
                                  ClassName sortName,
                                  GomClass empty,
                                  GomClass cons,
                                  HookList hooks){
    super(className,hooks);
    this.abstractType = abstractType;
    this.sortName = sortName;
    this.empty = empty;
    this.cons = cons;
  }

  public void generate(java.io.Writer writer) throws java.io.IOException {

    writer.write(%[
package @getPackage()@;
@generateImport()@
public abstract class @className()@ extends @fullClassName(sortName)@ @generateInterface()@{
@generateBlock()@
]%);
generateBody(writer);
writer.write(%[
}
]%);
  }

  protected String generateInterface() {
    String interfaces = super.generateInterface();
    if (! interfaces.equals("")) return "implements "+interfaces.substring(1);
    else return interfaces;
  }


  private void generateBody(java.io.Writer writer) throws java.io.IOException {
    writer.write(%[
  public int length() {
    int count = 0;
    if(this instanceof @fullClassName(cons.getClassName())@) {
      @fullClassName(sortName)@ tl = ((@fullClassName(cons.getClassName())@)this).getTail@className()@();
      if (tl instanceof @className()@) {
        return 1+((@className()@)tl).length();
      } else {
        return 2;
      }
    } else {
      return 0;
    }
  }

 public @fullClassName(cons.getSlots().getHeadconcSlotField().getDomain())@[] toArray() {
    @fullClassName(cons.getSlots().getHeadconcSlotField().getDomain())@[] array;
    if(this instanceof @fullClassName(cons.getClassName())@) {
      @fullClassName(cons.getSlots().getHeadconcSlotField().getDomain())@ h = ((@fullClassName(cons.getClassName())@)this).getHead@className()@();
      @fullClassName(sortName)@ tl = ((@fullClassName(cons.getClassName())@)this).getTail@className()@();
      if (tl instanceof @className()@) {
        @fullClassName(cons.getSlots().getHeadconcSlotField().getDomain())@[] tailArray =((@className()@)tl).toArray();
        array = new @fullClassName(cons.getSlots().getHeadconcSlotField().getDomain())@[1+tailArray.length];
        array[0]=h;     
        for(int i =0;i<tailArray.length;i++){
          array[i+1]=tailArray[i];
        }
      } else {
        array = new @fullClassName(cons.getSlots().getHeadconcSlotField().getDomain())@[1];
        array[0]=h;  
      }
    } else {
        array = new @fullClassName(cons.getSlots().getHeadconcSlotField().getDomain())@[0];
    }
    return array;
  }

  public @fullClassName(sortName)@ reverse() {
    if(this instanceof @fullClassName(cons.getClassName())@) {
      @fullClassName(sortName)@ cur = this;
      @fullClassName(sortName)@ rev = @fullClassName(empty.getClassName())@.make();
      while(cur instanceof @fullClassName(cons.getClassName())@) {
        rev = @fullClassName(cons.getClassName())@.make(((@fullClassName(cons.getClassName())@)cur).getHead@className()@(),rev);
        cur = ((@fullClassName(cons.getClassName())@)cur).getTail@className()@();
      }
      return rev;
    } else {
      return this;
    }
  }
]%);
  }

  /** the class logger instance*/
  private Logger getLogger() {
    return Logger.getLogger(getClass().getName());
  }
}
