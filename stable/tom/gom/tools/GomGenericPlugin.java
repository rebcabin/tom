/* Generated by TOM (version 2.4alpha): Do not edit this file *//*
 * Gom
 * 
 * Copyright (c) 2005-2006, INRIA
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
 * Antoine Reilles    e-mail: Antoine.Reilles@loria.fr
 **/

package tom.gom.tools;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import tom.platform.OptionManager;
import tom.platform.Plugin;
import tom.platform.StatusHandler;
import tom.platform.adt.platformoption.types.PlatformOptionList;

import tom.gom.Gom;
import tom.gom.GomStreamManager;
import tom.gom.tools.GomEnvironment;

public abstract class GomGenericPlugin implements Plugin {

  public GomGenericPlugin(String name) {
    pluginName = name;
  }

  /* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file *//* Generated by TOM (version 2.4alpha): Do not edit this file */   /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ /* Generated by TOM (version 2.4alpha): Do not edit this file */ private static boolean tom_terms_equal_PlatformOption(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static boolean tom_terms_equal_PlatformOptionList(Object t1, Object t2) {  return  t1.equals(t2)  ;}private static  tom.platform.adt.platformoption.types.PlatformOptionList  tom_empty_list_concPlatformOption() { return  tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption.make() ; }private static  tom.platform.adt.platformoption.types.PlatformOptionList  tom_cons_list_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOption  e,  tom.platform.adt.platformoption.types.PlatformOptionList  l) { return  tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make(e,l) ; }private static  tom.platform.adt.platformoption.types.PlatformOption  tom_get_head_concPlatformOption_PlatformOptionList( tom.platform.adt.platformoption.types.PlatformOptionList  l) {  return  l.getHeadconcPlatformOption()  ;}private static  tom.platform.adt.platformoption.types.PlatformOptionList  tom_get_tail_concPlatformOption_PlatformOptionList( tom.platform.adt.platformoption.types.PlatformOptionList  l) {  return  l.getTailconcPlatformOption()  ;}private static boolean tom_is_empty_concPlatformOption_PlatformOptionList( tom.platform.adt.platformoption.types.PlatformOptionList  l) {  return  l.isEmptyconcPlatformOption()  ;}private static  tom.platform.adt.platformoption.types.PlatformOptionList  tom_append_list_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOptionList  l1,  tom.platform.adt.platformoption.types.PlatformOptionList  l2) {    if(tom_is_empty_concPlatformOption_PlatformOptionList(l1)) {     return l2;    } else if(tom_is_empty_concPlatformOption_PlatformOptionList(l2)) {     return l1;    } else if(tom_is_empty_concPlatformOption_PlatformOptionList(( tom.platform.adt.platformoption.types.PlatformOptionList )tom_get_tail_concPlatformOption_PlatformOptionList(l1))) {     return ( tom.platform.adt.platformoption.types.PlatformOptionList )tom_cons_list_concPlatformOption(( tom.platform.adt.platformoption.types.PlatformOption )tom_get_head_concPlatformOption_PlatformOptionList(l1),l2);    } else {      return ( tom.platform.adt.platformoption.types.PlatformOptionList )tom_cons_list_concPlatformOption(( tom.platform.adt.platformoption.types.PlatformOption )tom_get_head_concPlatformOption_PlatformOptionList(l1),tom_append_list_concPlatformOption(( tom.platform.adt.platformoption.types.PlatformOptionList )tom_get_tail_concPlatformOption_PlatformOptionList(l1),l2));    }   }  private static  tom.platform.adt.platformoption.types.PlatformOptionList  tom_get_slice_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOptionList  begin,  tom.platform.adt.platformoption.types.PlatformOptionList  end) {    if(tom_terms_equal_PlatformOptionList(begin,end)) {      return ( tom.platform.adt.platformoption.types.PlatformOptionList )tom_empty_list_concPlatformOption();    } else {      return ( tom.platform.adt.platformoption.types.PlatformOptionList )tom_cons_list_concPlatformOption(( tom.platform.adt.platformoption.types.PlatformOption )tom_get_head_concPlatformOption_PlatformOptionList(begin),( tom.platform.adt.platformoption.types.PlatformOptionList )tom_get_slice_concPlatformOption(( tom.platform.adt.platformoption.types.PlatformOptionList )tom_get_tail_concPlatformOption_PlatformOptionList(begin),end));    }   }   

  /** The name of the plugin. */
  private String pluginName;

  /** the status handler */
  private StatusHandler statusHandler;

  /** the option manager */
  private OptionManager optionManager;
  
  /** The streamanager */
  protected GomStreamManager streamManager;

  /**
   * An accessor method, so that the plugin can see its logger.
   *
   * @return the plugin's logger
   */
  protected Logger getLogger() {
    return Logger.getLogger(getClass().getName());
  }

  /**
   * An accessor method, so that the plugin can see the unique Status Handler.
   *
   * @return the common to all Gom plugins statusHandler
   */
  protected StatusHandler getStatusHandler() {
    if(statusHandler == null) {
      findStatusHandler();
    }
    return statusHandler;
  }

  protected GomEnvironment environment() {
    return GomEnvironment.getInstance();
  }

  public GomStreamManager getStreamManager() {
    return streamManager;
  }

  public void setStreamManager(GomStreamManager m) {
    streamManager = m;
  }

  /**
   * From Plugin interface 
   * @param term the input Object
   */
  public abstract void setArgs(Object[] arg);

  /**
   * From Plugin interface
   * The run() method is not implemented in GomGenericPlugin.
   * The plugin should implement its own run() method itself.
   */
  public abstract void run();

  /**
   * From Plugin interface
   * @return the Object "term"
   */
  public abstract Object[] getArgs();

  /**
   * From Plugin interface
   * The setOptionManager save the reference to the OM.
   */
  public void setOptionManager(OptionManager optionManager) {
    this.optionManager = optionManager;
  }

  /**
   * From OptionOwner interface
   * Returns an empty PlatformOptionList. By default, the plugin is considered
   * to declare no options.
   *
   * @return an empty PlatformOptionList
   */
  public PlatformOptionList getDeclaredOptionList() {
    return tom_empty_list_concPlatformOption();
  }

  /**
   * From OptionOwner interface
   * Returns an empty PlatformOptionList. By default, the plugin is considered
   * to have no prerequisites.
   *
   * @return an empty PlatformOptionList
   */
  public PlatformOptionList getRequiredOptionList() {
    return tom_empty_list_concPlatformOption();
  }

  /**
   * From OptionOwner interface
   * By default, no further work is done. Sometimes though, a plugin might need
   * to do more work
   * (for instance if altering the value entails a change in another).
   *
   * @param optionName the option's name
   * @param optionValue the option's value
   */
  public void optionChanged(String optionName, Object optionValue) {
  }

  private void findStatusHandler() {
    Handler[] handlers = Logger.getLogger(Gom.LOGRADICAL).getHandlers();
    for(int i=0;i<handlers.length;i++) {
      if(handlers[i] instanceof StatusHandler) {
        statusHandler = (StatusHandler)handlers[i];
        break;
      }
    }
  }

  public OptionManager getOptionManager() {
    return optionManager;
  }

  public void setOptionValue(String name, Object value) {
    optionManager.setOptionValue(name, value);
  }

  protected Object getOptionValue(String name) {
    return optionManager.getOptionValue(name);
  }

  /**
   * Returns the value of a boolean option.
   * 
   * @param optionName the name of the option whose value is seeked
   * @return a boolean that is the option's value
   */
  public boolean getOptionBooleanValue(String optionName) {
    return ((Boolean)getOptionValue(optionName)).booleanValue();
  }

  /**
   * Returns the value of an integer option.
   * 
   * @param optionName the name of the option whose value is seeked
   * @return an int that is the option's value
   */
  public int getOptionIntegerValue(String optionName) {
    return ((Integer)getOptionValue(optionName)).intValue();
  }

  /**
   * Returns the value of a string option.
   * 
   * @param optionName the name of the option whose value is seeked
   * @return a String that is the option's value
   */
  public String getOptionStringValue(String optionName) {
    return (String) getOptionValue(optionName);
  }

  public String getArgumentArrayString(Object[] arg) {
    String argString = "[";
    for(int i=0;i<arg.length;i++) {
      if (arg[i] == null) continue;
      argString += arg[i].getClass().getName();
      if(i < arg.length-1) {
        argString += ",";
      }
    }
    return argString+"]";
  }
}
