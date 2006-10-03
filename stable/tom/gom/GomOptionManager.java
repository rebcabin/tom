/* Generated by TOM (version 2.4rc2): Do not edit this file *//*
 * Gom
 *
 * Copyright (c) 2000-2006, INRIA
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
 * Antoine Reilles       e-mail: Antoine.Reilles@loria.fr
 *
 **/

package tom.gom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import tom.platform.ConfigurationManager;
import tom.platform.OptionManager;
import tom.platform.OptionOwner;
import tom.platform.adt.platformoption.types.*;
import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermList;

public class GomOptionManager implements OptionManager, OptionOwner {

  /* Generated by TOM (version 2.4rc2): Do not edit this file *//* Generated by TOM (version 2.4rc2): Do not edit this file *//* Generated by TOM (version 2.4rc2): Do not edit this file */ private static boolean tom_terms_equal_String(String t1, String t2) {  return  (t1.equals(t2))  ;}  /* Generated by TOM (version 2.4rc2): Do not edit this file */private static boolean tom_terms_equal_int(int t1, int t2) {  return  (t1==t2)  ;} private static boolean tom_terms_equal_PlatformValue(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_PlatformOptionList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_PlatformBoolean(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_PlatformOption(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_is_fun_sym_BooleanValue( tom.platform.adt.platformoption.types.PlatformValue  t) {  return  t instanceof tom.platform.adt.platformoption.types.platformvalue.BooleanValue  ;}private static  tom.platform.adt.platformoption.types.PlatformValue  tom_make_BooleanValue( tom.platform.adt.platformoption.types.PlatformBoolean  t0) { return  tom.platform.adt.platformoption.types.platformvalue.BooleanValue.make(t0); }private static  tom.platform.adt.platformoption.types.PlatformBoolean  tom_get_slot_BooleanValue_BooleanValue( tom.platform.adt.platformoption.types.PlatformValue  t) {  return  t.getBooleanValue()  ;}private static boolean tom_is_fun_sym_StringValue( tom.platform.adt.platformoption.types.PlatformValue  t) {  return  t instanceof tom.platform.adt.platformoption.types.platformvalue.StringValue  ;}private static  tom.platform.adt.platformoption.types.PlatformValue  tom_make_StringValue( String  t0) { return  tom.platform.adt.platformoption.types.platformvalue.StringValue.make(t0); }private static  String  tom_get_slot_StringValue_StringValue( tom.platform.adt.platformoption.types.PlatformValue  t) {  return  t.getStringValue()  ;}private static boolean tom_is_fun_sym_IntegerValue( tom.platform.adt.platformoption.types.PlatformValue  t) {  return  t instanceof tom.platform.adt.platformoption.types.platformvalue.IntegerValue  ;}private static  tom.platform.adt.platformoption.types.PlatformValue  tom_make_IntegerValue( int  t0) { return  tom.platform.adt.platformoption.types.platformvalue.IntegerValue.make(t0); }private static  int  tom_get_slot_IntegerValue_IntegerValue( tom.platform.adt.platformoption.types.PlatformValue  t) {  return  t.getIntegerValue()  ;}private static boolean tom_is_fun_sym_True( tom.platform.adt.platformoption.types.PlatformBoolean  t) {  return  t instanceof tom.platform.adt.platformoption.types.platformboolean.True  ;}private static  tom.platform.adt.platformoption.types.PlatformBoolean  tom_make_True() { return  tom.platform.adt.platformoption.types.platformboolean.True.make(); }private static boolean tom_is_fun_sym_False( tom.platform.adt.platformoption.types.PlatformBoolean  t) {  return  t instanceof tom.platform.adt.platformoption.types.platformboolean.False  ;}private static  tom.platform.adt.platformoption.types.PlatformBoolean  tom_make_False() { return  tom.platform.adt.platformoption.types.platformboolean.False.make(); }private static boolean tom_is_fun_sym_PluginOption( tom.platform.adt.platformoption.types.PlatformOption  t) {  return  t instanceof tom.platform.adt.platformoption.types.platformoption.PluginOption  ;}private static  String  tom_get_slot_PluginOption_Name( tom.platform.adt.platformoption.types.PlatformOption  t) {  return  t.getName()  ;}private static  String  tom_get_slot_PluginOption_AltName( tom.platform.adt.platformoption.types.PlatformOption  t) {  return  t.getAltName()  ;}private static  String  tom_get_slot_PluginOption_Description( tom.platform.adt.platformoption.types.PlatformOption  t) {  return  t.getDescription()  ;}private static  tom.platform.adt.platformoption.types.PlatformValue  tom_get_slot_PluginOption_Value( tom.platform.adt.platformoption.types.PlatformOption  t) {  return  t.getValue()  ;}private static  String  tom_get_slot_PluginOption_AttrName( tom.platform.adt.platformoption.types.PlatformOption  t) {  return  t.getAttrName()  ;}private static boolean tom_is_fun_sym_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOptionList  t) {  return  t instanceof tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption || t instanceof tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption  ;}private static  tom.platform.adt.platformoption.types.PlatformOptionList  tom_empty_list_concPlatformOption() { return  tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption.make() ; }private static  tom.platform.adt.platformoption.types.PlatformOptionList  tom_cons_list_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOption  e,  tom.platform.adt.platformoption.types.PlatformOptionList  l) { return  tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make(e,l) ; }private static  tom.platform.adt.platformoption.types.PlatformOption  tom_get_head_concPlatformOption_PlatformOptionList( tom.platform.adt.platformoption.types.PlatformOptionList  l) {  return  l.getHeadconcPlatformOption()  ;}private static  tom.platform.adt.platformoption.types.PlatformOptionList  tom_get_tail_concPlatformOption_PlatformOptionList( tom.platform.adt.platformoption.types.PlatformOptionList  l) {  return  l.getTailconcPlatformOption()  ;}private static boolean tom_is_empty_concPlatformOption_PlatformOptionList( tom.platform.adt.platformoption.types.PlatformOptionList  l) {  return  l.isEmptyconcPlatformOption()  ;}private static  tom.platform.adt.platformoption.types.PlatformOptionList  tom_append_list_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOptionList  l1,  tom.platform.adt.platformoption.types.PlatformOptionList  l2) {    if(tom_is_empty_concPlatformOption_PlatformOptionList(l1)) {     return l2;    } else if(tom_is_empty_concPlatformOption_PlatformOptionList(l2)) {     return l1;    } else if(tom_is_empty_concPlatformOption_PlatformOptionList(( tom.platform.adt.platformoption.types.PlatformOptionList )tom_get_tail_concPlatformOption_PlatformOptionList(l1))) {     return ( tom.platform.adt.platformoption.types.PlatformOptionList )tom_cons_list_concPlatformOption(( tom.platform.adt.platformoption.types.PlatformOption )tom_get_head_concPlatformOption_PlatformOptionList(l1),l2);    } else {      return ( tom.platform.adt.platformoption.types.PlatformOptionList )tom_cons_list_concPlatformOption(( tom.platform.adt.platformoption.types.PlatformOption )tom_get_head_concPlatformOption_PlatformOptionList(l1),tom_append_list_concPlatformOption(( tom.platform.adt.platformoption.types.PlatformOptionList )tom_get_tail_concPlatformOption_PlatformOptionList(l1),l2));    }   }  private static  tom.platform.adt.platformoption.types.PlatformOptionList  tom_get_slice_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOptionList  begin,  tom.platform.adt.platformoption.types.PlatformOptionList  end) {    if(tom_terms_equal_PlatformOptionList(begin,end)) {      return ( tom.platform.adt.platformoption.types.PlatformOptionList )tom_empty_list_concPlatformOption();    } else {      return ( tom.platform.adt.platformoption.types.PlatformOptionList )tom_cons_list_concPlatformOption(( tom.platform.adt.platformoption.types.PlatformOption )tom_get_head_concPlatformOption_PlatformOptionList(begin),( tom.platform.adt.platformoption.types.PlatformOptionList )tom_get_slice_concPlatformOption(( tom.platform.adt.platformoption.types.PlatformOptionList )tom_get_tail_concPlatformOption_PlatformOptionList(begin),end));    }   }   

  /** The global options */
  private PlatformOptionList globalOptions;

  /**  map the name of an option to the plugin which defines this option */
  private Map mapNameToOptionOwner;

  /** map the name of an option to the option itself */
  private Map mapNameToOption;

  /** map a shortname of an option to its full name */
  private Map mapShortNameToName;

  /** the list of input files extract from the commandLine */
  private List inputFileList;

  /**
   * Basic Constructor
   * @return a configurationManager that needs to be initialized
   */
  public GomOptionManager() {
    mapNameToOptionOwner = new HashMap();
    mapNameToOption = new HashMap();
    mapShortNameToName = new HashMap();
    inputFileList = new ArrayList();
    globalOptions = tom_empty_list_concPlatformOption();
  }

  /**
   * initialize does everything needed
   *
   * @return  an error code :
   * <ul>
   * <li>0 if no error was encountered</li>
   * <li>1 if something went wrong</li>
   * </ul>
   */
  public int initialize(ConfigurationManager confManager, String[] commandLine) {
    List pluginList = confManager.getPluginsList();
    List optionOwnerList = new ArrayList(pluginList);
    optionOwnerList.add(this);
    collectOptions(optionOwnerList, pluginList);
    this.inputFileList = processArguments(commandLine);
    if(this.inputFileList == null) {
      return 1;
    }
    return checkAllOptionsDepedencies(optionOwnerList);
  }

  /**
   * Inherited from OptionManager interface
   */
  public void setGlobalOptionList(PlatformOptionList globalOptions) {
    this.globalOptions = globalOptions;
  }

  /** Accessor Method */
  public List getInputToCompileList() {
    return inputFileList;
  }

  /**
   * An option has changed
   *
   * @param optionName the option's name
   * @param optionValue the option's desired value
   */
  public void optionChanged(String optionName, Object optionValue) {
    String canonicalOptionName = getCanonicalName(optionName);
    if(canonicalOptionName.equals("verbose")) {
      if( ((Boolean)optionValue).booleanValue() ) {
        Gom.changeLogLevel(Level.INFO);
      }
    } else if(canonicalOptionName.equals("wall")) {
      if( ((Boolean)optionValue).booleanValue() ) {
        Gom.changeLogLevel(Level.WARNING);
      }
    } else if(canonicalOptionName.equals("debug")) {
      if( ((Boolean)optionValue).booleanValue() ) {
        Gom.changeLogLevel(Level.FINE);
      }
    } else if(canonicalOptionName.equals("verbosedebug")) {
      if( ((Boolean)optionValue).booleanValue() ) {
        Gom.changeLogLevel(Level.FINER);
      }
    }
  }

  /**
   * Sets an option to the desired value.
   */
  public void setOptionValue(String optionName, Object optionValue) {
    PlatformBoolean bool = null;
    if(optionValue instanceof Boolean) {
      bool = ((Boolean)optionValue).booleanValue()?tom_make_True():tom_make_False();
      setOptionPlatformValue(optionName, tom_make_BooleanValue(bool));
    } else if(optionValue instanceof Integer) {
      Integer v = (Integer) optionValue;
      setOptionPlatformValue(optionName, tom_make_IntegerValue(v.intValue()));
    } else if(optionValue instanceof String) {
      String v = (String) optionValue;
      setOptionPlatformValue(optionName, tom_make_StringValue(v));
    } else {
      throw new RuntimeException("unknown optionValue type: " + optionValue);
    }
    // alert the owner of the change
    OptionOwner owner = getOptionOwnerFromName(optionName);
    owner.optionChanged(optionName, optionValue);
  }

  /**
   * Returns the value of an option. Returns an Object which is a Boolean,
   * a String or an Integer depending on what the option type is.
   *
   * @param optionName the name of the option whose value is seeked
   * @return an Object containing the option's value
   */
  public Object getOptionValue(String name) {
    PlatformOption option = getOptionFromName(name);
    if(option != null) {
       if(option instanceof  tom.platform.adt.platformoption.types.PlatformOption ) { { tom.platform.adt.platformoption.types.PlatformOption  tom_match1_1=(( tom.platform.adt.platformoption.types.PlatformOption )option); if ( ( tom_is_fun_sym_PluginOption(tom_match1_1) ||  false  ) ) { { tom.platform.adt.platformoption.types.PlatformValue  tom_match1_1_Value=tom_get_slot_PluginOption_Value(tom_match1_1); if ( ( tom_is_fun_sym_BooleanValue(tom_match1_1_Value) ||  false  ) ) { { tom.platform.adt.platformoption.types.PlatformBoolean  tom_match1_1_Value_BooleanValue=tom_get_slot_BooleanValue_BooleanValue(tom_match1_1_Value); if ( ( tom_is_fun_sym_True(tom_match1_1_Value_BooleanValue) ||  false  ) ) { {boolean tom_match1_tom_anti_constraints_status= true ; if ((tom_match1_tom_anti_constraints_status ==  true )) { if ( true ) {
 return Boolean.valueOf(true);  } } } } } } } } if ( ( tom_is_fun_sym_PluginOption(tom_match1_1) ||  false  ) ) { { tom.platform.adt.platformoption.types.PlatformValue  tom_match1_1_Value=tom_get_slot_PluginOption_Value(tom_match1_1); if ( ( tom_is_fun_sym_BooleanValue(tom_match1_1_Value) ||  false  ) ) { { tom.platform.adt.platformoption.types.PlatformBoolean  tom_match1_1_Value_BooleanValue=tom_get_slot_BooleanValue_BooleanValue(tom_match1_1_Value); if ( ( tom_is_fun_sym_False(tom_match1_1_Value_BooleanValue) ||  false  ) ) { {boolean tom_match1_tom_anti_constraints_status= true ; if ((tom_match1_tom_anti_constraints_status ==  true )) { if ( true ) {
 return Boolean.valueOf(false);  } } } } } } } } if ( ( tom_is_fun_sym_PluginOption(tom_match1_1) ||  false  ) ) { { tom.platform.adt.platformoption.types.PlatformValue  tom_match1_1_Value=tom_get_slot_PluginOption_Value(tom_match1_1); if ( ( tom_is_fun_sym_IntegerValue(tom_match1_1_Value) ||  false  ) ) { { int  tom_match1_1_Value_IntegerValue=tom_get_slot_IntegerValue_IntegerValue(tom_match1_1_Value); { int  tom_value=tom_match1_1_Value_IntegerValue; {boolean tom_match1_tom_anti_constraints_status= true ; if ((tom_match1_tom_anti_constraints_status ==  true )) { if ( true ) {
 return new Integer(tom_value);  } } } } } } } } if ( ( tom_is_fun_sym_PluginOption(tom_match1_1) ||  false  ) ) { { tom.platform.adt.platformoption.types.PlatformValue  tom_match1_1_Value=tom_get_slot_PluginOption_Value(tom_match1_1); if ( ( tom_is_fun_sym_StringValue(tom_match1_1_Value) ||  false  ) ) { { String  tom_match1_1_Value_StringValue=tom_get_slot_StringValue_StringValue(tom_match1_1_Value); { String  tom_value=tom_match1_1_Value_StringValue; {boolean tom_match1_tom_anti_constraints_status= true ; if ((tom_match1_tom_anti_constraints_status ==  true )) { if ( true ) {
 return tom_value;  } } } } } } } } } }

    } else {
      getLogger().log(Level.SEVERE,GomMessage.optionNotFound.getMessage(),name);
      throw new RuntimeException();
    }
    return null;
  }

  /**
   * From OptionOwner Interface
   * @return the global options
   */
  public PlatformOptionList getDeclaredOptionList() {
    return globalOptions;
  }

  /**
   * From OptionOwner Interface
   * @return the prerequisites
   */
  public PlatformOptionList getRequiredOptionList() {
    PlatformOptionList prerequisites = tom_empty_list_concPlatformOption();
    // for now, there is no incompatibilities or requirements on options
    return prerequisites;
  }

  public void setOptionManager(OptionManager om) {}

  /**
   * collects the options/services provided by each plugin
   */
  private void collectOptions(List optionOwnerList, List plugins) {
    Iterator owners = optionOwnerList.iterator();
    while(owners.hasNext()) {
      OptionOwner owner = (OptionOwner)owners.next();
      PlatformOptionList list = owner.getDeclaredOptionList();
      owner.setOptionManager((OptionManager)this);
      while(!list.isEmptyconcPlatformOption()) {
        PlatformOption option = list.getHeadconcPlatformOption();
         if(option instanceof  tom.platform.adt.platformoption.types.PlatformOption ) { { tom.platform.adt.platformoption.types.PlatformOption  tom_match2_1=(( tom.platform.adt.platformoption.types.PlatformOption )option); if ( ( tom_is_fun_sym_PluginOption(tom_match2_1) ||  false  ) ) { { String  tom_match2_1_Name=tom_get_slot_PluginOption_Name(tom_match2_1); { String  tom_match2_1_AltName=tom_get_slot_PluginOption_AltName(tom_match2_1); { String  tom_name=tom_match2_1_Name; { String  tom_altName=tom_match2_1_AltName; {boolean tom_match2_tom_anti_constraints_status= true ; if ((tom_match2_tom_anti_constraints_status ==  true )) { if ( true ) {

            setOptionOwnerFromName(tom_name, owner);
            setOptionFromName(tom_name, option);
            if(tom_altName.length() > 0) {
              mapShortNameToName.put(tom_altName,tom_name);
            }
           } } } } } } } } } }

        list = list.getTailconcPlatformOption();
      }
    }
  }

  /**
   * Checks if every plugin's needs are fulfilled
   */
  private int checkAllOptionsDepedencies(List optionOwnerList) {
    Iterator owners = optionOwnerList.iterator();
    while(owners.hasNext()) {
      OptionOwner plugin = (OptionOwner)owners.next();
      if(!checkOptionDependency(plugin.getRequiredOptionList())) {
        getLogger().log(Level.SEVERE, GomMessage.prerequisitesIssue.getMessage(), plugin.getClass().getName());
        return 1;
      }
    }
    return 0;
  }

  private String getCanonicalName(String name) {
    if(mapShortNameToName.containsKey(name)) {
      return (String)mapShortNameToName.get(name);
    }
    return name;
  }

  private PlatformOption getOptionFromName(String name) {
    PlatformOption option = (PlatformOption)mapNameToOption.get(getCanonicalName(name));
    if(option == null) {
      getLogger().log(Level.WARNING,GomMessage.optionNotFound.getMessage(),getCanonicalName(name));
    }
    return option;
  }

  private PlatformOption setOptionFromName(String name, PlatformOption option) {
    return (PlatformOption)mapNameToOption.put(getCanonicalName(name),option);
  }

  private OptionOwner getOptionOwnerFromName(String name) {
    OptionOwner plugin = (OptionOwner)mapNameToOptionOwner.get(getCanonicalName(name));
    if(plugin == null) {
      getLogger().log(Level.WARNING,GomMessage.optionNotFound.getMessage(),getCanonicalName(name));
    }
    return plugin;
  }

  private void setOptionOwnerFromName(String name, OptionOwner plugin) {
    mapNameToOptionOwner.put(getCanonicalName(name),plugin);
  }

  private void setOptionPlatformValue(String name, PlatformValue value) {
    PlatformOption option = getOptionFromName(name);
    if(option != null) {
      PlatformOption newOption = option.setValue(value);
      Object replaced = setOptionFromName(name, newOption);
      getLogger().log(Level.FINER,GomMessage.setValue.getMessage(),new Object[]{name,value,replaced});
    } else {
      throw new RuntimeException();
    }
  }

  /**
   * Self-explanatory. Displays an help message indicating how to use the compiler.
   */
  private void displayHelp() {
    String beginning = "usage :"
      + "\n\tgom [options] file [... file_n]"
      + "\noptions :";
    StringBuffer buffer = new StringBuffer(beginning);

    buffer.append("\n\t-X <file>:\tDefines an alternate XML configuration file\n");

    for(Iterator it = mapNameToOption.values().iterator(); it.hasNext() ; ) {
      PlatformOption h = (PlatformOption)it.next();
       if(h instanceof  tom.platform.adt.platformoption.types.PlatformOption ) { { tom.platform.adt.platformoption.types.PlatformOption  tom_match3_1=(( tom.platform.adt.platformoption.types.PlatformOption )h); if ( ( tom_is_fun_sym_PluginOption(tom_match3_1) ||  false  ) ) { { String  tom_match3_1_Name=tom_get_slot_PluginOption_Name(tom_match3_1); { String  tom_match3_1_AltName=tom_get_slot_PluginOption_AltName(tom_match3_1); { String  tom_match3_1_Description=tom_get_slot_PluginOption_Description(tom_match3_1); { String  tom_match3_1_AttrName=tom_get_slot_PluginOption_AttrName(tom_match3_1); { String  tom_name=tom_match3_1_Name; { String  tom_altName=tom_match3_1_AltName; { String  tom_description=tom_match3_1_Description; { String  tom_attrName=tom_match3_1_AttrName; {boolean tom_match3_tom_anti_constraints_status= true ; if ((tom_match3_tom_anti_constraints_status ==  true )) { if ( true ) {

          buffer.append("\t--" + tom_name);
          if(tom_attrName.length() > 0) {
            buffer.append(" <" + tom_attrName+ ">");
          }
          if(tom_altName.length() > 0) {
            buffer.append(" | -" + tom_altName);
          }
          buffer.append(":\t" + tom_description);
          buffer.append("\n");
         } } } } } } } } } } } } } }

    }

    System.out.println(buffer.toString());
  }

  /**
   * Self-explanatory. Displays the current version of the Gom compiler.
   */
  public void displayVersion() {
    System.out.println("Gom " + Gom.VERSION + "\n\n"
                       + "Copyright (c) 2000-2006, INRIA, Nancy, France.\n");
  }

  /**
   * Checks if all the options a plugin needs are here.
   *
   * @param list a list of options that must be found with the right value
   * @return true if every option was found with the right value
   */
  private boolean checkOptionDependency(PlatformOptionList requiredOptions) {
     if(requiredOptions instanceof  tom.platform.adt.platformoption.types.PlatformOptionList ) { { tom.platform.adt.platformoption.types.PlatformOptionList  tom_match4_1=(( tom.platform.adt.platformoption.types.PlatformOptionList )requiredOptions); if ( ( tom_is_fun_sym_concPlatformOption(tom_match4_1) ||  false  ) ) { { tom.platform.adt.platformoption.types.PlatformOptionList  tom_match4_1_list1=tom_match4_1; if (tom_is_empty_concPlatformOption_PlatformOptionList(tom_match4_1_list1)) { {boolean tom_match4_tom_anti_constraints_status= true ; if ((tom_match4_tom_anti_constraints_status ==  true )) { if ( true ) {

        return true;
       } } } } } } if ( ( tom_is_fun_sym_concPlatformOption(tom_match4_1) ||  false  ) ) { { tom.platform.adt.platformoption.types.PlatformOptionList  tom_match4_1_list1=tom_match4_1; if (!(tom_is_empty_concPlatformOption_PlatformOptionList(tom_match4_1_list1))) { { tom.platform.adt.platformoption.types.PlatformOption  tom_match4_1_1=tom_get_head_concPlatformOption_PlatformOptionList(tom_match4_1_list1);tom_match4_1_list1=tom_get_tail_concPlatformOption_PlatformOptionList(tom_match4_1_list1); if ( ( tom_is_fun_sym_PluginOption(tom_match4_1_1) ||  false  ) ) { { String  tom_match4_1_1_Name=tom_get_slot_PluginOption_Name(tom_match4_1_1); { tom.platform.adt.platformoption.types.PlatformValue  tom_match4_1_1_Value=tom_get_slot_PluginOption_Value(tom_match4_1_1); { String  tom_name=tom_match4_1_1_Name; { tom.platform.adt.platformoption.types.PlatformValue  tom_value=tom_match4_1_1_Value; { tom.platform.adt.platformoption.types.PlatformOptionList  tom_tail=tom_match4_1_list1; {boolean tom_match4_tom_anti_constraints_status= true ; if ((tom_match4_tom_anti_constraints_status ==  true )) { if ( true ) {


        PlatformOption option = getOptionFromName(tom_name);
        if(option !=null) {
          PlatformValue localValue = option.getValue();
          if(tom_value!= localValue) {
            getLogger().log(Level.SEVERE, GomMessage.incorrectOptionValue.getMessage(), new Object[]{tom_name,tom_value,getOptionValue(tom_name)});
            return false;
          } else {
            return checkOptionDependency(tom_tail);
          }
        } else {
          getLogger().log(Level.SEVERE, GomMessage.incorrectOptionValue.getMessage(), new Object[]{tom_name,tom_value,getOptionValue(tom_name)});
          return false;
        }
       } } } } } } } } } } } } } } }

    return false;
  }

  /** logger accessor in case of logging needs*/
  private Logger getLogger() {
    return Logger.getLogger(getClass().getName());
  }

    /**
   * This method takes the arguments given by the user and deduces the options
   * to set, then sets them.
   *
   * @param argumentList
   * @return an array containing the name of the input files
   */
  private List processArguments(String[] argumentList) {
    List inputFiles = new ArrayList();
    StringBuffer imports = new StringBuffer();
    boolean outputEncountered = false;
    boolean destdirEncountered = false;
    int i = 0;
    String argument="";
    try {
      for(; i < argumentList.length; i++) {
        argument = argumentList[i];
        getLogger().log(Level.FINER, "GomOptionManager: processing argument "+i+" \""+argument+"\"");
        if(!argument.startsWith("-") || (argument.equals("-")) ) {
          // input file name, should never start with '-' (except for System.in)
          inputFiles.add(argument);
        } else { // s does start with '-', thus is -or at least should be- an option
          argument = argument.substring(1); // crops the '-'
          if(argument.startsWith("-")) { // if there's another one
            argument = argument.substring(1); // crops the second '-'
          }

          if(argument.equals("X")) {
            // if we're here, the PluginPlatform has already handled the "-X" option
            // and all errors that might occur
            // just skip it,along with its argument
            i++;
            continue;
          }

          if(argument.equals("help") || argument.equals("h")) {
            displayHelp();
            return null;
          }
          if(argument.equals("version") || argument.equals("V")) {
            displayVersion();
            return null;
          }
          if(argument.equals("import") || argument.equals("I")) {
            i++;
            imports.append(argumentList[i] + ":");
          }
          if(argument.equals("destdir") || argument.equals("d")) {
            if(destdirEncountered) {
              getLogger().log(Level.SEVERE, GomMessage.destdirTwice.getMessage());
              return null;
            } else {
              destdirEncountered = true;
            }
          }

          OptionOwner plugin = getOptionOwnerFromName(argument);
          PlatformOption option = getOptionFromName(argument);

          if(option == null || plugin == null) {// option not found
            getLogger().log(Level.SEVERE, GomMessage.invalidOption.getMessage(), argument);
            displayHelp();
            return null;
          } else {
             if(option instanceof  tom.platform.adt.platformoption.types.PlatformOption ) { { tom.platform.adt.platformoption.types.PlatformOption  tom_match5_1=(( tom.platform.adt.platformoption.types.PlatformOption )option); if ( ( tom_is_fun_sym_PluginOption(tom_match5_1) ||  false  ) ) { { tom.platform.adt.platformoption.types.PlatformValue  tom_match5_1_Value=tom_get_slot_PluginOption_Value(tom_match5_1); if ( ( tom_is_fun_sym_BooleanValue(tom_match5_1_Value) ||  false  ) ) { {boolean tom_match5_tom_anti_constraints_status= true ; if ((tom_match5_tom_anti_constraints_status ==  true )) { if ( true ) {

                setOptionValue(argument, Boolean.TRUE);
               } } } } } } if ( ( tom_is_fun_sym_PluginOption(tom_match5_1) ||  false  ) ) { { tom.platform.adt.platformoption.types.PlatformValue  tom_match5_1_Value=tom_get_slot_PluginOption_Value(tom_match5_1); if ( ( tom_is_fun_sym_IntegerValue(tom_match5_1_Value) ||  false  ) ) { {boolean tom_match5_tom_anti_constraints_status= true ; if ((tom_match5_tom_anti_constraints_status ==  true )) { if ( true ) {


                String t = argumentList[++i];
                setOptionValue(argument, new Integer(t));
               } } } } } } if ( ( tom_is_fun_sym_PluginOption(tom_match5_1) ||  false  ) ) { { tom.platform.adt.platformoption.types.PlatformValue  tom_match5_1_Value=tom_get_slot_PluginOption_Value(tom_match5_1); if ( ( tom_is_fun_sym_StringValue(tom_match5_1_Value) ||  false  ) ) { {boolean tom_match5_tom_anti_constraints_status= true ; if ((tom_match5_tom_anti_constraints_status ==  true )) { if ( true ) {


                if ( !( argument.equals("import") || argument.equals("I") ) ) {
                  // "import" is handled in the end
                  String t = argumentList[++i];
                  setOptionValue(argument, t);
                }
               } } } } } } } }

          }
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      getLogger().log(Level.SEVERE, GomMessage.incompleteOption.getMessage(), argument);
      displayHelp();
      return null;
    }

    setOptionValue("import",imports.toString());

    if(inputFiles.isEmpty()) {
      getLogger().log(Level.SEVERE, GomMessage.noFileToCompile.getMessage());
      displayHelp();
      return null;
    }
    return inputFiles;
  }
}
