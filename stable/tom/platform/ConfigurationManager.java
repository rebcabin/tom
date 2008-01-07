/* Generated by TOM (version 2.6alpha): Do not edit this file *//*
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

import tom.engine.TomMessage;

import tom.library.adt.tnode.*;
import tom.library.adt.tnode.types.*;
import tom.library.xml.*;
import tom.platform.adt.platformoption.*;
import tom.platform.adt.platformoption.types.*;

/**
 * This class is a wrapper for the platform XML configuration files.
 * It extracts the plugins information and create an ordered list of
 * of instances. Extracts the Option Management information and based
 * on it create and initialize the corresponding OptionManager.
 * The instantiation of a Configuration is not sufficient since it need to
 * be initialized with an execution commandLine.
 *
 */
public class ConfigurationManager {
  
  /** Used to analyse xml configuration file*/
  /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */  /* Generated by TOM (version 2.6alpha): Do not edit this file */ /* Generated by TOM (version 2.6alpha): Do not edit this file */ /* Generated by TOM (version 2.6alpha): Do not edit this file */ /* Generated by TOM (version 2.6alpha): Do not edit this file */    private static   tom.library.adt.tnode.types.TNodeList  tom_append_list_concTNode( tom.library.adt.tnode.types.TNodeList l1,  tom.library.adt.tnode.types.TNodeList  l2) {     if( l1.isEmptyconcTNode() ) {       return l2;     } else if( l2.isEmptyconcTNode() ) {       return l1;     } else if(  l1.getTailconcTNode() .isEmptyconcTNode() ) {       return  tom.library.adt.tnode.types.tnodelist.ConsconcTNode.make( l1.getHeadconcTNode() ,l2) ;     } else {       return  tom.library.adt.tnode.types.tnodelist.ConsconcTNode.make( l1.getHeadconcTNode() ,tom_append_list_concTNode( l1.getTailconcTNode() ,l2)) ;     }   }   private static   tom.library.adt.tnode.types.TNodeList  tom_get_slice_concTNode( tom.library.adt.tnode.types.TNodeList  begin,  tom.library.adt.tnode.types.TNodeList  end, tom.library.adt.tnode.types.TNodeList  tail) {     if( begin.equals(end) ) {       return tail;     } else {       return  tom.library.adt.tnode.types.tnodelist.ConsconcTNode.make( begin.getHeadconcTNode() ,( tom.library.adt.tnode.types.TNodeList )tom_get_slice_concTNode( begin.getTailconcTNode() ,end,tail)) ;     }   }    /* Generated by TOM (version 2.6alpha): Do not edit this file */ 


  
  /** configuration file name */
  private String xmlConfigurationFileName;

  /** The plugins instance list*/
  private List pluginsList;

  /** The OptionManager */
  private OptionManager optionManager;
  
  private static Logger logger = Logger.getLogger("tom.platform.ConfigurationManager");
  /**
   * Basic Constructor
   * constructing a configurationManager that needs to be initialized
   */
  public ConfigurationManager(String xmlConfigurationFileName) {
    this.xmlConfigurationFileName = xmlConfigurationFileName;
    this.pluginsList = new ArrayList();
  }
  
  /**
   * initialize analyse the XML file and extract plugins and option management
   *
   * @return  an error code :
   * <ul>
   * <li>0 if no error was encountered</li>
   * <li>1 if something went wrong</li>
   * </ul>
   */
  public int initialize(String[] commandLine) {    
    XmlTools xtools = new XmlTools();
    TNode configurationNode = xtools.convertXMLToTNode(xmlConfigurationFileName);
    if(configurationNode == null) {
      getLogger().log(Level.SEVERE, PluginPlatformMessage.configFileNotXML.getMessage(), xmlConfigurationFileName);
      return 1;
    }
    if(createPlugins(configurationNode.getDocElem())==1) {
      return 1;
    }    
    if(createOptionManager(configurationNode.getDocElem()) == 1) {     
      if( ((Boolean)optionManager.getOptionValue("optimize2")).booleanValue()
          && !(optionManager.getInputToCompileList().size() == 1 && "-".equals((String)optionManager.getInputToCompileList().get(0))) ) {        
        logger.log(Level.WARNING, TomMessage.optimizerModifiesLineNumbers.getMessage());
      }
      return 1;
    }
    return optionManager.initialize(this, commandLine);
  }

  /** Accessor method */
  public List getPluginsList() {
    return pluginsList;
  }

  /** Accessor method */
  public OptionManager  getOptionManager() {
    return optionManager;
  }
  
  /** 
   * Initialize the plugins list based on information extracted
   * from the XML conf file converted in TNode
   *
   * @return  an error code :
   * <ul>
   * <li>0 if no error was encountered</li>
   * <li>1 if something went wrong</li>
   * </ul>
   */
  private int createPlugins(TNode configurationNode) {
    List pluginsClassList = extractClassPaths(configurationNode);
    // if empty list this means there is a problem somewhere
    if(pluginsClassList.isEmpty()) {
      getLogger().log(Level.SEVERE, PluginPlatformMessage.noPluginFound.getMessage(), xmlConfigurationFileName);
      pluginsList = null;
      return 1;
    }
    // creates an instance of each plugin
    Iterator classPathIt = pluginsClassList.iterator();
    while(classPathIt.hasNext()) {
      String pluginClass = (String)classPathIt.next();
      try { 
        Object pluginInstance = Class.forName(pluginClass).newInstance();
        if(pluginInstance instanceof Plugin) {
          pluginsList.add(pluginInstance);
        } else {
          getLogger().log(Level.SEVERE, PluginPlatformMessage.classNotAPlugin.getMessage(), pluginClass);
          pluginsList = null;
          return 1;
        }
      } catch(ClassNotFoundException cnfe) {
        getLogger().log(Level.WARNING, PluginPlatformMessage.classNotFound.getMessage(), pluginClass);
        return 1;
      } catch(Exception e) {
        // adds the error message. this is too cryptic otherwise
        e.printStackTrace();
        getLogger().log(Level.SEVERE, PluginPlatformMessage.instantiationError.getMessage(), pluginClass);
        pluginsList = null;
        return 1;
      }
    }
    return 0;
  }
  
  /**
   * Extracts the plugins' class name from the XML configuration file.
   * 
   * @param node the node containing the XML document
   * @return the List of plugins class path
   */
  private List extractClassPaths(TNode node) {
    List res = new ArrayList();
    {if ( (node instanceof tom.library.adt.tnode.types.TNode) ) {{  tom.library.adt.tnode.types.TNode  tomMatch573NameNumberfreshSubject_1=(( tom.library.adt.tnode.types.TNode )node);if ( (tomMatch573NameNumberfreshSubject_1 instanceof tom.library.adt.tnode.types.tnode.ElementNode) ) {{  String  tomMatch573NameNumber_freshVar_0= tomMatch573NameNumberfreshSubject_1.getName() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_freshVar_1= tomMatch573NameNumberfreshSubject_1.getAttrList() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_freshVar_2= tomMatch573NameNumberfreshSubject_1.getChildList() ;if ( "platform".equals(tomMatch573NameNumber_freshVar_0) ) {if ( ((tomMatch573NameNumber_freshVar_1 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch573NameNumber_freshVar_1 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_freshVar_3=tomMatch573NameNumber_freshVar_1;if ( ((tomMatch573NameNumber_freshVar_2 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch573NameNumber_freshVar_2 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_freshVar_5=tomMatch573NameNumber_freshVar_2;{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_begin_7=tomMatch573NameNumber_freshVar_5;{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_end_8=tomMatch573NameNumber_freshVar_5;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_freshVar_6=tomMatch573NameNumber_end_8;if (!( tomMatch573NameNumber_freshVar_6.isEmptyconcTNode() )) {if ( ( tomMatch573NameNumber_freshVar_6.getHeadconcTNode()  instanceof tom.library.adt.tnode.types.tnode.ElementNode) ) {{  String  tomMatch573NameNumber_freshVar_11=  tomMatch573NameNumber_freshVar_6.getHeadconcTNode() .getName() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_freshVar_12=  tomMatch573NameNumber_freshVar_6.getHeadconcTNode() .getAttrList() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_freshVar_13=  tomMatch573NameNumber_freshVar_6.getHeadconcTNode() .getChildList() ;if ( "plugins".equals(tomMatch573NameNumber_freshVar_11) ) {if ( ((tomMatch573NameNumber_freshVar_12 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch573NameNumber_freshVar_12 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_freshVar_14=tomMatch573NameNumber_freshVar_12;if ( ((tomMatch573NameNumber_freshVar_13 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch573NameNumber_freshVar_13 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_freshVar_16=tomMatch573NameNumber_freshVar_13;{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_begin_18=tomMatch573NameNumber_freshVar_16;{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_end_19=tomMatch573NameNumber_freshVar_16;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_freshVar_17=tomMatch573NameNumber_end_19;if (!( tomMatch573NameNumber_freshVar_17.isEmptyconcTNode() )) {if ( ( tomMatch573NameNumber_freshVar_17.getHeadconcTNode()  instanceof tom.library.adt.tnode.types.tnode.ElementNode) ) {{  String  tomMatch573NameNumber_freshVar_22=  tomMatch573NameNumber_freshVar_17.getHeadconcTNode() .getName() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_freshVar_23=  tomMatch573NameNumber_freshVar_17.getHeadconcTNode() .getAttrList() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_freshVar_24=  tomMatch573NameNumber_freshVar_17.getHeadconcTNode() .getChildList() ;if ( "plugin".equals(tomMatch573NameNumber_freshVar_22) ) {if ( ((tomMatch573NameNumber_freshVar_23 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch573NameNumber_freshVar_23 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_freshVar_25=tomMatch573NameNumber_freshVar_23;{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_begin_27=tomMatch573NameNumber_freshVar_25;{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_end_28=tomMatch573NameNumber_freshVar_25;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_freshVar_26=tomMatch573NameNumber_end_28;if (!( tomMatch573NameNumber_freshVar_26.isEmptyconcTNode() )) {if ( ( tomMatch573NameNumber_freshVar_26.getHeadconcTNode()  instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch573NameNumber_freshVar_32=  tomMatch573NameNumber_freshVar_26.getHeadconcTNode() .getName() ;{  String  tomMatch573NameNumber_freshVar_33=  tomMatch573NameNumber_freshVar_26.getHeadconcTNode() .getSpecified() ;{  String  tomMatch573NameNumber_freshVar_34=  tomMatch573NameNumber_freshVar_26.getHeadconcTNode() .getValue() ;if ( "class".equals(tomMatch573NameNumber_freshVar_32) ) {{  String  tom_cp=tomMatch573NameNumber_freshVar_34;{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_freshVar_29= tomMatch573NameNumber_freshVar_26.getTailconcTNode() ;if ( ((tomMatch573NameNumber_freshVar_24 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch573NameNumber_freshVar_24 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_freshVar_31=tomMatch573NameNumber_freshVar_24;if ( tomMatch573NameNumber_freshVar_31.isEmptyconcTNode() ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_freshVar_20= tomMatch573NameNumber_freshVar_17.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch573NameNumber_freshVar_9= tomMatch573NameNumber_freshVar_6.getTailconcTNode() ;if ( true ) {

         res.add(tom_cp);
         getLogger().log(Level.FINER, PluginPlatformMessage.classPathRead.getMessage(), tom_cp);
       }}}}}}}}}}}}}}}if ( tomMatch573NameNumber_end_28.isEmptyconcTNode() ) {tomMatch573NameNumber_end_28=tomMatch573NameNumber_begin_27;} else {tomMatch573NameNumber_end_28= tomMatch573NameNumber_end_28.getTailconcTNode() ;}}} while(!( tomMatch573NameNumber_end_28.equals(tomMatch573NameNumber_begin_27) ));}}}}}}}}}}}if ( tomMatch573NameNumber_end_19.isEmptyconcTNode() ) {tomMatch573NameNumber_end_19=tomMatch573NameNumber_begin_18;} else {tomMatch573NameNumber_end_19= tomMatch573NameNumber_end_19.getTailconcTNode() ;}}} while(!( tomMatch573NameNumber_end_19.equals(tomMatch573NameNumber_begin_18) ));}}}}}}}}}}}}}if ( tomMatch573NameNumber_end_8.isEmptyconcTNode() ) {tomMatch573NameNumber_end_8=tomMatch573NameNumber_begin_7;} else {tomMatch573NameNumber_end_8= tomMatch573NameNumber_end_8.getTailconcTNode() ;}}} while(!( tomMatch573NameNumber_end_8.equals(tomMatch573NameNumber_begin_7) ));}}}}}}}}}}}}}}

    return res;
  }
 
   /**
   * Initialize the option manager based on information extracted
   * from the XML conf file converted in TNode
   * 
   * @param node the node containing the XML file
   * @return  an error code :
   * <ul>
   * <li>0 if no error was encountered</li>
   * <li>1 if something went wrong</li>
   * </ul>
   */
  private int createOptionManager(TNode node) {
    {if ( (node instanceof tom.library.adt.tnode.types.TNode) ) {{  tom.library.adt.tnode.types.TNode  tomMatch574NameNumberfreshSubject_1=(( tom.library.adt.tnode.types.TNode )node);if ( (tomMatch574NameNumberfreshSubject_1 instanceof tom.library.adt.tnode.types.tnode.ElementNode) ) {{  String  tomMatch574NameNumber_freshVar_0= tomMatch574NameNumberfreshSubject_1.getName() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_1= tomMatch574NameNumberfreshSubject_1.getAttrList() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_2= tomMatch574NameNumberfreshSubject_1.getChildList() ;if ( "platform".equals(tomMatch574NameNumber_freshVar_0) ) {if ( ((tomMatch574NameNumber_freshVar_1 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch574NameNumber_freshVar_1 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_3=tomMatch574NameNumber_freshVar_1;if ( ((tomMatch574NameNumber_freshVar_2 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch574NameNumber_freshVar_2 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_5=tomMatch574NameNumber_freshVar_2;{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_begin_7=tomMatch574NameNumber_freshVar_5;{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_end_8=tomMatch574NameNumber_freshVar_5;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_6=tomMatch574NameNumber_end_8;if (!( tomMatch574NameNumber_freshVar_6.isEmptyconcTNode() )) {if ( ( tomMatch574NameNumber_freshVar_6.getHeadconcTNode()  instanceof tom.library.adt.tnode.types.tnode.ElementNode) ) {{  String  tomMatch574NameNumber_freshVar_11=  tomMatch574NameNumber_freshVar_6.getHeadconcTNode() .getName() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_12=  tomMatch574NameNumber_freshVar_6.getHeadconcTNode() .getAttrList() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_13=  tomMatch574NameNumber_freshVar_6.getHeadconcTNode() .getChildList() ;if ( "optionmanager".equals(tomMatch574NameNumber_freshVar_11) ) {if ( ((tomMatch574NameNumber_freshVar_12 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch574NameNumber_freshVar_12 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_14=tomMatch574NameNumber_freshVar_12;{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_begin_16=tomMatch574NameNumber_freshVar_14;{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_end_17=tomMatch574NameNumber_freshVar_14;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_15=tomMatch574NameNumber_end_17;if (!( tomMatch574NameNumber_freshVar_15.isEmptyconcTNode() )) {if ( ( tomMatch574NameNumber_freshVar_15.getHeadconcTNode()  instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch574NameNumber_freshVar_26=  tomMatch574NameNumber_freshVar_15.getHeadconcTNode() .getName() ;{  String  tomMatch574NameNumber_freshVar_27=  tomMatch574NameNumber_freshVar_15.getHeadconcTNode() .getSpecified() ;{  String  tomMatch574NameNumber_freshVar_28=  tomMatch574NameNumber_freshVar_15.getHeadconcTNode() .getValue() ;if ( "class".equals(tomMatch574NameNumber_freshVar_26) ) {{  String  tom_omclass=tomMatch574NameNumber_freshVar_28;{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_18= tomMatch574NameNumber_freshVar_15.getTailconcTNode() ;if ( ((tomMatch574NameNumber_freshVar_13 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch574NameNumber_freshVar_13 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_20=tomMatch574NameNumber_freshVar_13;{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_begin_22=tomMatch574NameNumber_freshVar_20;{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_end_23=tomMatch574NameNumber_freshVar_20;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_21=tomMatch574NameNumber_end_23;if (!( tomMatch574NameNumber_freshVar_21.isEmptyconcTNode() )) {if ( ( tomMatch574NameNumber_freshVar_21.getHeadconcTNode()  instanceof tom.library.adt.tnode.types.tnode.ElementNode) ) {{  String  tomMatch574NameNumber_freshVar_29=  tomMatch574NameNumber_freshVar_21.getHeadconcTNode() .getName() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_30=  tomMatch574NameNumber_freshVar_21.getHeadconcTNode() .getAttrList() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_31=  tomMatch574NameNumber_freshVar_21.getHeadconcTNode() .getChildList() ;if ( "options".equals(tomMatch574NameNumber_freshVar_29) ) {if ( ((tomMatch574NameNumber_freshVar_30 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch574NameNumber_freshVar_30 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_32=tomMatch574NameNumber_freshVar_30;if ( ((tomMatch574NameNumber_freshVar_31 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch574NameNumber_freshVar_31 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_34=tomMatch574NameNumber_freshVar_31;{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_24= tomMatch574NameNumber_freshVar_21.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch574NameNumber_freshVar_9= tomMatch574NameNumber_freshVar_6.getTailconcTNode() ;if ( true ) {

        try {
          Object omInstance = Class.forName(tom_omclass).newInstance();
          if(omInstance instanceof OptionManager) {
            optionManager = (OptionManager)omInstance;
          } else {
            getLogger().log(Level.SEVERE, PluginPlatformMessage.classNotOptionManager.getMessage(), tom_omclass);
            return 1;
          }
        } catch(ClassNotFoundException cnfe) {
          getLogger().log(Level.SEVERE, PluginPlatformMessage.classNotFound.getMessage(), tom_omclass);
          optionManager = null;
          return 1;
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println(e.getMessage());
          getLogger().log(Level.SEVERE, PluginPlatformMessage.instantiationError.getMessage(), tom_omclass);
          optionManager = null;
          return 1;
        }

        TNode optionX =  tom.library.adt.tnode.types.tnode.ElementNode.make("string",  tom.library.adt.tnode.types.tnodelist.ConsconcTNode.make( tom.library.adt.tnode.types.tnode.AttributeNode.make("altName", "true", "X") , tom.library.adt.tnode.types.tnodelist.ConsconcTNode.make( tom.library.adt.tnode.types.tnode.AttributeNode.make("attrName", "true", "file") , tom.library.adt.tnode.types.tnodelist.ConsconcTNode.make( tom.library.adt.tnode.types.tnode.AttributeNode.make("description", "true", "Defines an alternate XML configuration file") , tom.library.adt.tnode.types.tnodelist.ConsconcTNode.make( tom.library.adt.tnode.types.tnode.AttributeNode.make("name", "true", "config") , tom.library.adt.tnode.types.tnodelist.ConsconcTNode.make( tom.library.adt.tnode.types.tnode.AttributeNode.make("value", "true", xmlConfigurationFileName) , tom.library.adt.tnode.types.tnodelist.EmptyconcTNode.make() ) ) ) ) ) ,  tom.library.adt.tnode.types.tnodelist.EmptyconcTNode.make() ) 


;
        TNode opt =  tom.library.adt.tnode.types.tnode.ElementNode.make("options",  tom.library.adt.tnode.types.tnodelist.EmptyconcTNode.make() ,  tom.library.adt.tnode.types.tnodelist.ConsconcTNode.make(optionX,tom_append_list_concTNode(tomMatch574NameNumber_freshVar_34, tom.library.adt.tnode.types.tnodelist.EmptyconcTNode.make() )) ) ;
        PlatformOptionList globalOptions = OptionParser.xmlNodeToOptionList(opt);
        optionManager.setGlobalOptionList(globalOptions);
        return 0;
      }}}}}}}}}}}}}}if ( tomMatch574NameNumber_end_23.isEmptyconcTNode() ) {tomMatch574NameNumber_end_23=tomMatch574NameNumber_begin_22;} else {tomMatch574NameNumber_end_23= tomMatch574NameNumber_end_23.getTailconcTNode() ;}}} while(!( tomMatch574NameNumber_end_23.equals(tomMatch574NameNumber_begin_22) ));}}}}}}}}}}}}}if ( tomMatch574NameNumber_end_17.isEmptyconcTNode() ) {tomMatch574NameNumber_end_17=tomMatch574NameNumber_begin_16;} else {tomMatch574NameNumber_end_17= tomMatch574NameNumber_end_17.getTailconcTNode() ;}}} while(!( tomMatch574NameNumber_end_17.equals(tomMatch574NameNumber_begin_16) ));}}}}}}}}}}}if ( tomMatch574NameNumber_end_8.isEmptyconcTNode() ) {tomMatch574NameNumber_end_8=tomMatch574NameNumber_begin_7;} else {tomMatch574NameNumber_end_8= tomMatch574NameNumber_end_8.getTailconcTNode() ;}}} while(!( tomMatch574NameNumber_end_8.equals(tomMatch574NameNumber_begin_7) ));}}}}}}}}}}}}}}

    return 1;
  }

  /** logger accessor in case of logging needs*/
  private Logger getLogger() {
    return Logger.getLogger(getClass().getName());
  }

}
