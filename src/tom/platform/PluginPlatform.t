/*
 * 
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2007, INRIA
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
 * Pierre-Etienne Moreau  e-mail: Pierre-Etienne.Moreau@loria.fr
 *
 **/

package tom.platform;

import java.util.*;
import java.util.logging.*;
import aterm.*;
import aterm.pure.*;
import tom.library.adt.tnode.*;

/**
 * The PluginPlatform manages plugins defined in an xml configuration file.
 * (which plugins are used and how they are ordered) with the intermediate
 * of a ConfigurationManager objet
 * Its main role is to run the plugins in the specified order and make some
 * error management.
 *
 */
public class PluginPlatform extends PluginPlatformBase {

  /** Used to analyse xml configuration file */
  %include{ adt/tnode/TNode.tom }

  /** The List of reference to plugins. */
  private List pluginsList;

  /** The status handler */
  private StatusHandler statusHandler;

  /** The test handler */
  private TestHandler testHandler;

  /** List of input arg */
  private List inputToCompileList;

  /** List of generated object cleared before each run */
  private List lastGeneratedObjects;

  /** Radical of the logger */
  private String loggerRadical;
  
  /**
   * The current file name to process - this is used in the status handler 
   * in order to have the file name when it was not passed at logging 
   * 
   * (generally, this information mostly serves for the eclipse plugin) 
   */
  private static String currentFileName = null; 

  /** Class Pluginplatform constructor */
  public PluginPlatform(ConfigurationManager confManager, String loggerRadical) {
    super(loggerRadical);  
    statusHandler = new StatusHandler();
    this.loggerRadical = loggerRadical;
    Logger.getLogger(loggerRadical).addHandler(this.statusHandler);
    pluginsList = confManager.getPluginsList();
    inputToCompileList = confManager.getOptionManager().getInputToCompileList();
  }

  /**
   * The main method which runs the PluginPlatform.
   *
   * @return an error code :
   * <ul>
   * <li>0 if no error was encountered</li>
   * <li>1 if something went wrong</li>
   * </ul>
   */
  public int run() {
    try {
      boolean globalSuccess = true;
      int globalNbOfErrors = 0;
      int globalNbOfWarnings = 0;
      // intialize run instances
      lastGeneratedObjects = new ArrayList();
      // for each input we call the sequence of plug-ins
      for(int i=0; i < inputToCompileList.size(); i++) {
        Object input = inputToCompileList.get(i);
        Object[] pluginArg = new Object[]{ input };
        Object initArgument = input;
        boolean success = true;
        statusHandler.clear();

        if(this.testHandler!=null) Logger.getLogger(loggerRadical).removeHandler(this.testHandler);
        if(input instanceof String) {
          String inputString = (String)input;
          testHandler = new TestHandler(inputString);
          if(!testHandler.hasError()) {
            Logger.getLogger(loggerRadical).addHandler(this.testHandler);
          }
        }

        getLogger().log(Level.FINER, PluginPlatformMessage.nowCompiling.getMessage(), input);
        
        // runs the plugins
        Iterator it = pluginsList.iterator();
        while(it.hasNext()) {
          /*
           * very strangely, the object pointed by statusHandler changes, and therefore 
           * it is no longer associated to the logger; therefore, we add it again as a handler  
           */
          Handler[] handlers = Logger.getLogger(loggerRadical).getHandlers();
          boolean foundHdl = false;
          for (int k = 0; k < handlers.length ; k++) {
            if (handlers[k].equals(statusHandler)) {
              foundHdl = true;
              break;
            }
          }
          if (!foundHdl){
            Logger.getLogger(loggerRadical).addHandler(this.statusHandler);  
          }
          Plugin plugin = (Plugin)it.next();
          currentFileName = (String)input;
          plugin.setArgs(pluginArg);
          if(statusHandler.hasError()) {
            getLogger().log(Level.INFO, PluginPlatformMessage.settingArgError.getMessage());
            success = false;
            globalSuccess = false;
            globalNbOfErrors += statusHandler.nbOfErrors();
            globalNbOfWarnings += statusHandler.nbOfWarnings();
            break;
          }
          plugin.run();
          if(statusHandler.hasError()) {
            getLogger().log(Level.INFO, PluginPlatformMessage.processingError.getMessage(),
                new Object[]{plugin.getClass().getName(), initArgument});
            success = false;
            globalSuccess = false;
            globalNbOfErrors += statusHandler.nbOfErrors();
            globalNbOfWarnings += statusHandler.nbOfWarnings();
            break;
          }
          pluginArg = plugin.getArgs();
        }

        if(success) {
          // save the first element of last plugin getArg response
          // this shall correspond to a generated file name
          lastGeneratedObjects.add(pluginArg[0]);
          globalNbOfWarnings += statusHandler.nbOfWarnings();
        }
      }

      if(!globalSuccess) {
        getLogger().log(Level.INFO, PluginPlatformMessage.runErrorMessage.getMessage(),
            new Integer(globalNbOfErrors));
        return 1;
      } else if(globalNbOfWarnings>0) {
        getLogger().log(Level.INFO, PluginPlatformMessage.runWarningMessage.getMessage(),
            new Integer(globalNbOfWarnings));
        return 0;
      }
    } catch(PlatformException e) {
      getLogger().log(Level.SEVERE, PluginPlatformMessage.platformStopped.getMessage());
      return 1;
    }
    return 0;
  }

  /**
   * An accessor method
   * @return the status handler.
   */
  public StatusHandler getStatusHandler() {
    return statusHandler;
  }

  /**
   * An accessor method
   * @return the test handler.
   */
  public TestHandler getTestHandler() {
    return testHandler;
  }

  /** return the list of last generated objects */
  public List getLastGeneratedObjects() {
    return lastGeneratedObjects;
  }

  public RuntimeAlert getAlertForInput(String filePath) {
    return statusHandler.getAlertForInput(filePath);
  }

  /** logger accessor in case of logging needs*/
  private Logger getLogger() {
    return Logger.getLogger(getClass().getName());
  }

  public static String getCurrentFileName(){
    return currentFileName;
  }
}
