/*
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
package tom.gom.backend;

import tom.gom.GomStreamManager;
import tom.gom.tools.GomEnvironment;
import tom.gom.adt.objects.*;
import tom.gom.adt.objects.types.*;
import tom.gom.tools.error.GomRuntimeException;
import java.io.*;

public abstract class TemplateClass {
  protected GomClass gomClass;
  protected ClassName className;

  public TemplateClass(GomClass gomClass) {
    this.gomClass = gomClass;
    this.className = gomClass.getClassName();
  }

  %include { ../adt/objects/Objects.tom}

  public abstract void generate(Writer writer) throws java.io.IOException;

  public String className() {
    return className(this.className);
  }

  public String className(ClassName clsName) {
    %match(ClassName clsName) {
      ClassName[Name=name] -> {
        return `name;
      }
    }
    throw new GomRuntimeException(
        "TemplateClass:className got a strange ClassName "+clsName);
  }

  public String fullClassName() {
    return fullClassName(this.className);
  }

  public static String fullClassName(ClassName clsName) {
    %match(ClassName clsName) {
      ClassName[Pkg=pkgPrefix,Name=name] -> {
        if(`pkgPrefix.length()==0) {
          return `name;
        } else {
          return `pkgPrefix+"."+`name;
        }
      }
    }
    throw new GomRuntimeException(
        "TemplateClass:fullClassName got a strange ClassName "+clsName);
  }

  public String getPackage() {
    return getPackage(this.className);
  }

  public String getPackage(ClassName clsName) {
    %match(ClassName clsName) {
      ClassName[Pkg=pkg] -> {
        return `pkg;
      }
    }
    throw new GomRuntimeException(
        "TemplateClass:getPackage got a strange ClassName "+clsName);
  }

  public String hasMethod(SlotField slot) {
    %match(SlotField slot) {
      SlotField[Name=name] -> {
        return "has"+`name;
      }
    }
    throw new GomRuntimeException(
        "TemplateClass:hasMethod got a strange SlotField "+slot);
  }

  public String getMethod(SlotField slot) {
    %match(SlotField slot) {
      SlotField[Name=name] -> {
        return "get"+`name;
      }
    }
    throw new GomRuntimeException(
        "TemplateClass:getMethod got a strange SlotField "+slot);
  }

  public String setMethod(SlotField slot) {
    %match(SlotField slot) {
      SlotField[Name=name] -> {
        return "set"+`name;
      }
    }
    throw new GomRuntimeException(
        "TemplateClass:setMethod got a strange SlotField "+slot);
  }

  public String index(SlotField slot) {
    %match(SlotField slot) {
      SlotField[Name=name] -> {
        return "index_"+`name;
      }
    }
    throw new GomRuntimeException(
        "TemplateClass:index got a strange SlotField "+slot);
  }

  public String slotDomain(SlotField slot) {
    %match(SlotField slot) {
      SlotField[Domain=domain] -> {
        return fullClassName(`domain);
      }
    }
    throw new GomRuntimeException(
        "TemplateClass:slotDomain got a strange SlotField "+slot);
  }

  private String fieldName(String fieldName) {
    return "_"+fieldName;
  }

  public String classFieldName(ClassName clsName) {
    %match(ClassName clsName) {
      ClassName[Name=name] -> {
        return `name.toLowerCase();
      }
    }
    throw new GomRuntimeException(
        "TemplateClass:classFieldName got a strange ClassName "+clsName);
  }

  public void toStringSlotField(StringBuilder res, SlotField slot,
                                String element, String buffer) {
    %match(SlotField slot) {
      SlotField[Domain=domain] -> {
        if(!GomEnvironment.getInstance().isBuiltinClass(`domain)) {
          res.append(%[@element@.toStringBuilder(@buffer@);
]%);
        } else {
          if (`domain.equals(`ClassName("","int"))
              || `domain.equals(`ClassName("","double"))
              || `domain.equals(`ClassName("","float"))) {
            res.append(%[@buffer@.append(@element@);
]%);
          } else if (`domain.equals(`ClassName("","long"))) {
            res.append(%[@buffer@.append(@element@);
            @buffer@.append("l");
]%);
          } else if (`domain.equals(`ClassName("","char"))) {
            res.append(%[@buffer@.append(((int)@element@-(int)'0'));
]%);
          } else if (`domain.equals(`ClassName("","boolean"))) {
            res.append(%[@buffer@.append(@element@?1:0);
]%);
          } else if (`domain.equals(`ClassName("","String"))) {
            String atchar = "@";
            res.append(%[@buffer@.append('"');
            for (int i = 0; i < @element@.length(); i++) {
              char c = @element@.charAt(i);
              switch (c) {
                case '\n':
                  @buffer@.append('\\');
                  @buffer@.append('n');
                  break;
                case '\t':
                  @buffer@.append('\\');
                  @buffer@.append('t');
                  break;
                case '\b':
                  @buffer@.append('\\');
                  @buffer@.append('b');
                  break;
                case '\r':
                  @buffer@.append('\\');
                  @buffer@.append('r');
                  break;
                case '\f':
                  @buffer@.append('\\');
                  @buffer@.append('f');
                  break;
                case '\\':
                  @buffer@.append('\\');
                  @buffer@.append('\\');
                  break;
                case '\'':
                  @buffer@.append('\\');
                  @buffer@.append('\'');
                  break;
                case '\"':
                  @buffer@.append('\\');
                  @buffer@.append('\"');
                  break;
                case '!':
                case '@atchar@':
                case '#':
                case '$':
                case '%':
                case '^':
                case '&':
                case '*':
                case '(':
                case ')':
                case '-':
                case '_':
                case '+':
                case '=':
                case '|':
                case '~':
                case '{':
                case '}':
                case '[':
                case ']':
                case ';':
                case ':':
                case '<':
                case '>':
                case ',':
                case '.':
                case '?':
                case ' ':
                case '/':
                  @buffer@.append(c);
                  break;

                default:
                  if (java.lang.Character.isLetterOrDigit(c)) {
                    @buffer@.append(c);
                  } else {
                    @buffer@.append('\\');
                    @buffer@.append((char) ('0' + c / 64));
                    c = (char) (c % 64);
                    @buffer@.append((char) ('0' + c / 8));
                    c = (char) (c % 8);
                    @buffer@.append((char) ('0' + c));
                  }
              }
            }
            @buffer@.append('"');
]%);
          } else if (`domain.equals(`ClassName("aterm","ATerm")) ||`domain.equals(`ClassName("aterm","ATermList"))) {
            res.append(%[@buffer@.append(@element@.toString());
]%);
          } else {
            throw new GomRuntimeException("Builtin " + `domain + " not supported");
          }
        }
      }
    }
  }

  public void toATermSlotField(StringBuilder res, SlotField slot) {
    %match(SlotField slot) {
      SlotField[Domain=domain] -> {
        if(!GomEnvironment.getInstance().isBuiltinClass(`domain)) {
          res.append(getMethod(slot));
          res.append("().toATerm()");
        } else {
          if (`domain.equals(`ClassName("","int"))) {
            res.append("(aterm.ATerm) atermFactory.makeInt(");
            res.append(getMethod(slot));
            res.append("())");
          } else if (`domain.equals(`ClassName("","boolean"))) {
            res.append("(aterm.ATerm) atermFactory.makeInt(");
            res.append(getMethod(slot));
            res.append("()?1:0)");
          } else if (`domain.equals(`ClassName("","long"))) {
            res.append("(aterm.ATerm) atermFactory.makeLong(");
            res.append(getMethod(slot));
            res.append("())");
          } else if (`domain.equals(`ClassName("","double"))) {
            res.append("(aterm.ATerm) atermFactory.makeReal(");
            res.append(getMethod(slot));
            res.append("())");
          } else if (`domain.equals(`ClassName("","float"))) {
            res.append("(aterm.ATerm) atermFactory.makeReal(");
            res.append(getMethod(slot));
            res.append("())");
          } else if (`domain.equals(`ClassName("","char"))) {
            res.append("(aterm.ATerm) atermFactory.makeInt(((int)");
            res.append(getMethod(slot));
            res.append("()-(int)'0'))");
          } else if (`domain.equals(`ClassName("","String"))) {
            res.append("(aterm.ATerm) atermFactory.makeAppl(");
            res.append("atermFactory.makeAFun(");
            res.append(getMethod(slot));
            res.append("() ,0 , true))");
          } else if (`domain.equals(`ClassName("aterm","ATerm")) ||`domain.equals(`ClassName("aterm","ATermList"))){
            res.append(getMethod(slot));
            res.append("()");
          } else {
            throw new GomRuntimeException("Builtin " + `domain + " not supported");
          }
        }
      }
    }
  }

  public void fromATermSlotField(StringBuilder buffer, SlotField slot, String appl) {
    %match(SlotField slot) {
      SlotField[Domain=domain] -> {
        if(!GomEnvironment.getInstance().isBuiltinClass(`domain)) {
          buffer.append(fullClassName(`domain));
          buffer.append(".fromTerm(");
          buffer.append(appl);
          buffer.append(")");
        } else {
          if (`domain.equals(`ClassName("","int"))) {
            buffer.append("((aterm.ATermInt)").append(appl).append(").getInt()");
          } else  if (`domain.equals(`ClassName("","float"))) {
            buffer.append("(float) ((aterm.ATermReal)").append(appl).append(").getReal()");
          } else  if (`domain.equals(`ClassName("","boolean"))) {
            buffer.append("(((aterm.ATermInt)").append(appl).append(").getInt()==0?false:true)");
          } else  if (`domain.equals(`ClassName("","long"))) {
            buffer.append("((aterm.ATermLong)").append(appl).append(").getLong()");
          } else  if (`domain.equals(`ClassName("","double"))) {
            buffer.append("((aterm.ATermReal)").append(appl).append(").getReal()");
          } else  if (`domain.equals(`ClassName("","char"))) {
            buffer.append("(char) (((aterm.ATermInt)").append(appl).append(").getInt()+(int)'0')");
          } else if (`domain.equals(`ClassName("","String"))) {
            buffer.append("(String) ((aterm.ATermAppl)").append(appl).append(").getAFun().getName()");
          } else if (`domain.equals(`ClassName("aterm","ATerm")) || `domain.equals(`ClassName("aterm","ATermList")) ){
            buffer.append(appl);
          } else {
            throw new GomRuntimeException("Builtin " + `domain + " not supported");
          }
        }
      }
    }
  }

  protected String primitiveToReferenceType(String classname) {
    %match(classname) {
      "byte" -> { return "java.lang.Byte"; }
      "short" -> { return "java.lang.Short"; }
      "int" -> { return "java.lang.Integer"; }
      "long" -> { return "java.lang.Long"; }
      "float" -> { return "java.lang.Float"; }
      "double" -> { return "java.lang.Double"; }
      "boolean" -> { return "java.lang.Boolean"; }
      "char" -> { return "java.lang.Character"; }
    }
    return classname;
  }

  protected String fileName() {
    return fullClassName().replace('.',File.separatorChar)+".java";
  }

  protected File fileToGenerate() {
    GomStreamManager stream = GomEnvironment.getInstance().getStreamManager();
    File output = new File(stream.getDestDir(),fileName());
    return output;
  }

  public int generateFile() {
    try {
       File output = fileToGenerate();
       // make sure the directory exists
       output.getParentFile().mkdirs();
       Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)));
       generate(writer);
       writer.flush();
       writer.close();
    } catch(Exception e) {
      e.printStackTrace();
      return 1;
    }
    return 0;
  }

  public String visitMethod(ClassName sortName) {
    return "visit_"+className(sortName);
  }

  public String isOperatorMethod(ClassName opName) {
    return "is"+className(opName);
  }

  public String getCollectionMethod(ClassName opName) {
    return "getCollection"+className(opName);
  }

  protected void slotDecl(java.io.Writer writer, SlotFieldList slotList)
                        throws java.io.IOException {
    int index = 0;
    while(!slotList.isEmptyconcSlotField()) {
      SlotField slot = slotList.getHeadconcSlotField();
      slotList = slotList.getTailconcSlotField();
      if (index>0) { writer.write(", "); }
      %match(SlotField slot) {
        SlotField[Name=slotName,Domain=ClassName[Name=domainName]] -> {
          writer.write(`slotName);
          writer.write(":");
          writer.write(`domainName);
          index++;
        }
      }
    }
  }

  protected void slotArgs(java.io.Writer writer, SlotFieldList slotList)
                        throws java.io.IOException {
    int index = 0;
    while(!slotList.isEmptyconcSlotField()) {
      SlotField slot = slotList.getHeadconcSlotField();
      slotList = slotList.getTailconcSlotField();
      if (index>0) { writer.write(", "); }
      /* Warning: do not write the 'index' alone, this is not a valid variable
         name */
      writer.write("t"+index);
      index++;
    }
  }

  protected void slotArgsWithDollar(java.io.Writer writer, SlotFieldList slotList)
                        throws java.io.IOException {
    int index = 0;
    while(!slotList.isEmptyconcSlotField()) {
      SlotField slot = slotList.getHeadconcSlotField();
      slotList = slotList.getTailconcSlotField();
      if (index>0) { writer.write(", "); }
      /* Warning: do not write the 'index' alone, this is not a valid variable
         name */
      writer.write("$t"+index);
      index++;
    }
  }

  public void generateTomMapping(Writer writer, ClassName basicStrategy)
      throws java.io.IOException {
    return;
  }

}
