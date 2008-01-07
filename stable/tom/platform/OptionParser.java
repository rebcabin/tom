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

import java.io.*;

import aterm.*;
import aterm.pure.*;

import tom.library.adt.tnode.*;
import tom.library.adt.tnode.types.*;
import tom.platform.adt.platformoption.*;
import tom.platform.adt.platformoption.types.*;
import tom.library.xml.*;

/**
 * Helper class to parse OptionOwner options.
 * The options have to comply with the following this DTD
 *
 * <PRE><CODE>
 * < !ELEMENT options (boolean*,integer*,string*) >
 *
 * < !ELEMENT boolean EMPTY >
 * < !ATTLIST boolean
 *   name CDATA #REQUIRED
 *   altName CDATA ""
 *   description CDATA ""
 *   value (true|false) #REQUIRED >
 *
 * < !ELEMENT integer EMPTY >
 * < !ATTLIST integer
 *   name CDATA #REQUIRED
 *   altName CDATA ""
 *   description CDATA ""
 *   value CDATA #REQUIRED
 *   attrName CDATA #REQUIRED >
 *
 * < !ELEMENT string EMPTY >
 * < !ATTLIST string
 *   name CDATA #REQUIRED
 *   altName CDATA ""
 *   description CDATA ""
 *   value CDATA #REQUIRED
 *   attrName CDATA #REQUIRED >
 * </CODE></PRE>
 */
public class OptionParser {
  
  /* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file *//* Generated by TOM (version 2.6alpha): Do not edit this file */  /* Generated by TOM (version 2.6alpha): Do not edit this file */ /* Generated by TOM (version 2.6alpha): Do not edit this file */ /* Generated by TOM (version 2.6alpha): Do not edit this file */ /* Generated by TOM (version 2.6alpha): Do not edit this file */    private static   tom.library.adt.tnode.types.TNodeList  tom_append_list_concTNode( tom.library.adt.tnode.types.TNodeList l1,  tom.library.adt.tnode.types.TNodeList  l2) {     if( l1.isEmptyconcTNode() ) {       return l2;     } else if( l2.isEmptyconcTNode() ) {       return l1;     } else if(  l1.getTailconcTNode() .isEmptyconcTNode() ) {       return  tom.library.adt.tnode.types.tnodelist.ConsconcTNode.make( l1.getHeadconcTNode() ,l2) ;     } else {       return  tom.library.adt.tnode.types.tnodelist.ConsconcTNode.make( l1.getHeadconcTNode() ,tom_append_list_concTNode( l1.getTailconcTNode() ,l2)) ;     }   }   private static   tom.library.adt.tnode.types.TNodeList  tom_get_slice_concTNode( tom.library.adt.tnode.types.TNodeList  begin,  tom.library.adt.tnode.types.TNodeList  end, tom.library.adt.tnode.types.TNodeList  tail) {     if( begin.equals(end) ) {       return tail;     } else {       return  tom.library.adt.tnode.types.tnodelist.ConsconcTNode.make( begin.getHeadconcTNode() ,( tom.library.adt.tnode.types.TNodeList )tom_get_slice_concTNode( begin.getTailconcTNode() ,end,tail)) ;     }   }    /* Generated by TOM (version 2.6alpha): Do not edit this file */   private static   tom.platform.adt.platformoption.types.PlatformOptionList  tom_append_list_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOptionList l1,  tom.platform.adt.platformoption.types.PlatformOptionList  l2) {     if( l1.isEmptyconcPlatformOption() ) {       return l2;     } else if( l2.isEmptyconcPlatformOption() ) {       return l1;     } else if(  l1.getTailconcPlatformOption() .isEmptyconcPlatformOption() ) {       return  tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make( l1.getHeadconcPlatformOption() ,l2) ;     } else {       return  tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make( l1.getHeadconcPlatformOption() ,tom_append_list_concPlatformOption( l1.getTailconcPlatformOption() ,l2)) ;     }   }   private static   tom.platform.adt.platformoption.types.PlatformOptionList  tom_get_slice_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOptionList  begin,  tom.platform.adt.platformoption.types.PlatformOptionList  end, tom.platform.adt.platformoption.types.PlatformOptionList  tail) {     if( begin.equals(end) ) {       return tail;     } else {       return  tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make( begin.getHeadconcPlatformOption() ,( tom.platform.adt.platformoption.types.PlatformOptionList )tom_get_slice_concPlatformOption( begin.getTailconcPlatformOption() ,end,tail)) ;     }   }    


  
  /**
   * An XMLTools for doing the stuff
   */
  private static XmlTools xtools = new XmlTools();
  
  /**
   * @return a PlatformOptionList extracted from the a String
   */
  public static PlatformOptionList xmlToOptionList(String xmlString) {
    InputStream stream = new ByteArrayInputStream(xmlString.getBytes());
    TNode node = xtools.convertXMLToTNode(stream);
    return xmlNodeToOptionList(node.getDocElem());
  }
  
  /**
   * @return a PlatformOptionList extracted from a TNode
   */
  public static PlatformOptionList xmlNodeToOptionList(TNode optionsNode) {
    PlatformOptionList list =  tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption.make() ;
    {if ( (optionsNode instanceof tom.library.adt.tnode.types.TNode) ) {{  tom.library.adt.tnode.types.TNode  tomMatch575NameNumberfreshSubject_1=(( tom.library.adt.tnode.types.TNode )optionsNode);if ( (tomMatch575NameNumberfreshSubject_1 instanceof tom.library.adt.tnode.types.tnode.ElementNode) ) {{  String  tomMatch575NameNumber_freshVar_0= tomMatch575NameNumberfreshSubject_1.getName() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_1= tomMatch575NameNumberfreshSubject_1.getAttrList() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_2= tomMatch575NameNumberfreshSubject_1.getChildList() ;if ( "options".equals(tomMatch575NameNumber_freshVar_0) ) {if ( ((tomMatch575NameNumber_freshVar_1 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch575NameNumber_freshVar_1 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_3=tomMatch575NameNumber_freshVar_1;if ( ((tomMatch575NameNumber_freshVar_2 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch575NameNumber_freshVar_2 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_5=tomMatch575NameNumber_freshVar_2;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_begin_7=tomMatch575NameNumber_freshVar_5;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_end_8=tomMatch575NameNumber_freshVar_5;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_6=tomMatch575NameNumber_end_8;if (!( tomMatch575NameNumber_freshVar_6.isEmptyconcTNode() )) {{  tom.library.adt.tnode.types.TNode  tom_option= tomMatch575NameNumber_freshVar_6.getHeadconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch575NameNumber_freshVar_9= tomMatch575NameNumber_freshVar_6.getTailconcTNode() ;if ( true ) {{if ( (tom_option instanceof tom.library.adt.tnode.types.TNode) ) {{  tom.library.adt.tnode.types.TNode  tomMatch576NameNumberfreshSubject_1=(( tom.library.adt.tnode.types.TNode )tom_option);if ( (tomMatch576NameNumberfreshSubject_1 instanceof tom.library.adt.tnode.types.tnode.ElementNode) ) {{  String  tomMatch576NameNumber_freshVar_0= tomMatch576NameNumberfreshSubject_1.getName() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_1= tomMatch576NameNumberfreshSubject_1.getAttrList() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_2= tomMatch576NameNumberfreshSubject_1.getChildList() ;if ( "boolean".equals(tomMatch576NameNumber_freshVar_0) ) {if ( ((tomMatch576NameNumber_freshVar_1 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch576NameNumber_freshVar_1 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_3=tomMatch576NameNumber_freshVar_1;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_begin_5=tomMatch576NameNumber_freshVar_3;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_end_6=tomMatch576NameNumber_freshVar_3;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_4=tomMatch576NameNumber_end_6;if (!( tomMatch576NameNumber_freshVar_4.isEmptyconcTNode() )) {if ( ( tomMatch576NameNumber_freshVar_4.getHeadconcTNode()  instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch576NameNumber_freshVar_22=  tomMatch576NameNumber_freshVar_4.getHeadconcTNode() .getName() ;{  String  tomMatch576NameNumber_freshVar_23=  tomMatch576NameNumber_freshVar_4.getHeadconcTNode() .getSpecified() ;{  String  tomMatch576NameNumber_freshVar_24=  tomMatch576NameNumber_freshVar_4.getHeadconcTNode() .getValue() ;if ( "altName".equals(tomMatch576NameNumber_freshVar_22) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_7= tomMatch576NameNumber_freshVar_4.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_begin_9=tomMatch576NameNumber_freshVar_7;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_end_10=tomMatch576NameNumber_freshVar_7;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_8=tomMatch576NameNumber_end_10;if (!( tomMatch576NameNumber_freshVar_8.isEmptyconcTNode() )) {if ( ( tomMatch576NameNumber_freshVar_8.getHeadconcTNode()  instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch576NameNumber_freshVar_25=  tomMatch576NameNumber_freshVar_8.getHeadconcTNode() .getName() ;{  String  tomMatch576NameNumber_freshVar_26=  tomMatch576NameNumber_freshVar_8.getHeadconcTNode() .getSpecified() ;{  String  tomMatch576NameNumber_freshVar_27=  tomMatch576NameNumber_freshVar_8.getHeadconcTNode() .getValue() ;if ( "description".equals(tomMatch576NameNumber_freshVar_25) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_11= tomMatch576NameNumber_freshVar_8.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_begin_13=tomMatch576NameNumber_freshVar_11;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_end_14=tomMatch576NameNumber_freshVar_11;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_12=tomMatch576NameNumber_end_14;if (!( tomMatch576NameNumber_freshVar_12.isEmptyconcTNode() )) {if ( ( tomMatch576NameNumber_freshVar_12.getHeadconcTNode()  instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch576NameNumber_freshVar_28=  tomMatch576NameNumber_freshVar_12.getHeadconcTNode() .getName() ;{  String  tomMatch576NameNumber_freshVar_29=  tomMatch576NameNumber_freshVar_12.getHeadconcTNode() .getSpecified() ;{  String  tomMatch576NameNumber_freshVar_30=  tomMatch576NameNumber_freshVar_12.getHeadconcTNode() .getValue() ;if ( "name".equals(tomMatch576NameNumber_freshVar_28) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_15= tomMatch576NameNumber_freshVar_12.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_begin_17=tomMatch576NameNumber_freshVar_15;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_end_18=tomMatch576NameNumber_freshVar_15;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_16=tomMatch576NameNumber_end_18;if (!( tomMatch576NameNumber_freshVar_16.isEmptyconcTNode() )) {if ( ( tomMatch576NameNumber_freshVar_16.getHeadconcTNode()  instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch576NameNumber_freshVar_31=  tomMatch576NameNumber_freshVar_16.getHeadconcTNode() .getName() ;{  String  tomMatch576NameNumber_freshVar_32=  tomMatch576NameNumber_freshVar_16.getHeadconcTNode() .getSpecified() ;{  String  tomMatch576NameNumber_freshVar_33=  tomMatch576NameNumber_freshVar_16.getHeadconcTNode() .getValue() ;if ( "value".equals(tomMatch576NameNumber_freshVar_31) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_19= tomMatch576NameNumber_freshVar_16.getTailconcTNode() ;if ( ((tomMatch576NameNumber_freshVar_2 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch576NameNumber_freshVar_2 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_21=tomMatch576NameNumber_freshVar_2;if ( tomMatch576NameNumber_freshVar_21.isEmptyconcTNode() ) {if ( true ) {


 
            PlatformBoolean bool = Boolean.valueOf(tomMatch576NameNumber_freshVar_33).booleanValue()? tom.platform.adt.platformoption.types.platformboolean.True.make() : tom.platform.adt.platformoption.types.platformboolean.False.make() ;
            list = tom_append_list_concPlatformOption(list, tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make( tom.platform.adt.platformoption.types.platformoption.PluginOption.make(tomMatch576NameNumber_freshVar_30, tomMatch576NameNumber_freshVar_24, tomMatch576NameNumber_freshVar_27,  tom.platform.adt.platformoption.types.platformvalue.BooleanValue.make(bool) , "") , tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption.make() ) ); 
          }}}}}}}}}}}}if ( tomMatch576NameNumber_end_18.isEmptyconcTNode() ) {tomMatch576NameNumber_end_18=tomMatch576NameNumber_begin_17;} else {tomMatch576NameNumber_end_18= tomMatch576NameNumber_end_18.getTailconcTNode() ;}}} while(!( tomMatch576NameNumber_end_18.equals(tomMatch576NameNumber_begin_17) ));}}}}}}}}}}if ( tomMatch576NameNumber_end_14.isEmptyconcTNode() ) {tomMatch576NameNumber_end_14=tomMatch576NameNumber_begin_13;} else {tomMatch576NameNumber_end_14= tomMatch576NameNumber_end_14.getTailconcTNode() ;}}} while(!( tomMatch576NameNumber_end_14.equals(tomMatch576NameNumber_begin_13) ));}}}}}}}}}}if ( tomMatch576NameNumber_end_10.isEmptyconcTNode() ) {tomMatch576NameNumber_end_10=tomMatch576NameNumber_begin_9;} else {tomMatch576NameNumber_end_10= tomMatch576NameNumber_end_10.getTailconcTNode() ;}}} while(!( tomMatch576NameNumber_end_10.equals(tomMatch576NameNumber_begin_9) ));}}}}}}}}}}if ( tomMatch576NameNumber_end_6.isEmptyconcTNode() ) {tomMatch576NameNumber_end_6=tomMatch576NameNumber_begin_5;} else {tomMatch576NameNumber_end_6= tomMatch576NameNumber_end_6.getTailconcTNode() ;}}} while(!( tomMatch576NameNumber_end_6.equals(tomMatch576NameNumber_begin_5) ));}}}}}}}}}}}if ( (tom_option instanceof tom.library.adt.tnode.types.TNode) ) {{  tom.library.adt.tnode.types.TNode  tomMatch576NameNumberfreshSubject_1=(( tom.library.adt.tnode.types.TNode )tom_option);if ( (tomMatch576NameNumberfreshSubject_1 instanceof tom.library.adt.tnode.types.tnode.ElementNode) ) {{  String  tomMatch576NameNumber_freshVar_34= tomMatch576NameNumberfreshSubject_1.getName() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_35= tomMatch576NameNumberfreshSubject_1.getAttrList() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_36= tomMatch576NameNumberfreshSubject_1.getChildList() ;if ( "integer".equals(tomMatch576NameNumber_freshVar_34) ) {if ( ((tomMatch576NameNumber_freshVar_35 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch576NameNumber_freshVar_35 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_37=tomMatch576NameNumber_freshVar_35;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_begin_39=tomMatch576NameNumber_freshVar_37;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_end_40=tomMatch576NameNumber_freshVar_37;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_38=tomMatch576NameNumber_end_40;if (!( tomMatch576NameNumber_freshVar_38.isEmptyconcTNode() )) {if ( ( tomMatch576NameNumber_freshVar_38.getHeadconcTNode()  instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch576NameNumber_freshVar_60=  tomMatch576NameNumber_freshVar_38.getHeadconcTNode() .getName() ;{  String  tomMatch576NameNumber_freshVar_61=  tomMatch576NameNumber_freshVar_38.getHeadconcTNode() .getSpecified() ;{  String  tomMatch576NameNumber_freshVar_62=  tomMatch576NameNumber_freshVar_38.getHeadconcTNode() .getValue() ;if ( "altName".equals(tomMatch576NameNumber_freshVar_60) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_41= tomMatch576NameNumber_freshVar_38.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_begin_43=tomMatch576NameNumber_freshVar_41;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_end_44=tomMatch576NameNumber_freshVar_41;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_42=tomMatch576NameNumber_end_44;if (!( tomMatch576NameNumber_freshVar_42.isEmptyconcTNode() )) {if ( ( tomMatch576NameNumber_freshVar_42.getHeadconcTNode()  instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch576NameNumber_freshVar_63=  tomMatch576NameNumber_freshVar_42.getHeadconcTNode() .getName() ;{  String  tomMatch576NameNumber_freshVar_64=  tomMatch576NameNumber_freshVar_42.getHeadconcTNode() .getSpecified() ;{  String  tomMatch576NameNumber_freshVar_65=  tomMatch576NameNumber_freshVar_42.getHeadconcTNode() .getValue() ;if ( "attrName".equals(tomMatch576NameNumber_freshVar_63) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_45= tomMatch576NameNumber_freshVar_42.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_begin_47=tomMatch576NameNumber_freshVar_45;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_end_48=tomMatch576NameNumber_freshVar_45;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_46=tomMatch576NameNumber_end_48;if (!( tomMatch576NameNumber_freshVar_46.isEmptyconcTNode() )) {if ( ( tomMatch576NameNumber_freshVar_46.getHeadconcTNode()  instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch576NameNumber_freshVar_66=  tomMatch576NameNumber_freshVar_46.getHeadconcTNode() .getName() ;{  String  tomMatch576NameNumber_freshVar_67=  tomMatch576NameNumber_freshVar_46.getHeadconcTNode() .getSpecified() ;{  String  tomMatch576NameNumber_freshVar_68=  tomMatch576NameNumber_freshVar_46.getHeadconcTNode() .getValue() ;if ( "description".equals(tomMatch576NameNumber_freshVar_66) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_49= tomMatch576NameNumber_freshVar_46.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_begin_51=tomMatch576NameNumber_freshVar_49;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_end_52=tomMatch576NameNumber_freshVar_49;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_50=tomMatch576NameNumber_end_52;if (!( tomMatch576NameNumber_freshVar_50.isEmptyconcTNode() )) {if ( ( tomMatch576NameNumber_freshVar_50.getHeadconcTNode()  instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch576NameNumber_freshVar_69=  tomMatch576NameNumber_freshVar_50.getHeadconcTNode() .getName() ;{  String  tomMatch576NameNumber_freshVar_70=  tomMatch576NameNumber_freshVar_50.getHeadconcTNode() .getSpecified() ;{  String  tomMatch576NameNumber_freshVar_71=  tomMatch576NameNumber_freshVar_50.getHeadconcTNode() .getValue() ;if ( "name".equals(tomMatch576NameNumber_freshVar_69) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_53= tomMatch576NameNumber_freshVar_50.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_begin_55=tomMatch576NameNumber_freshVar_53;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_end_56=tomMatch576NameNumber_freshVar_53;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_54=tomMatch576NameNumber_end_56;if (!( tomMatch576NameNumber_freshVar_54.isEmptyconcTNode() )) {if ( ( tomMatch576NameNumber_freshVar_54.getHeadconcTNode()  instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch576NameNumber_freshVar_72=  tomMatch576NameNumber_freshVar_54.getHeadconcTNode() .getName() ;{  String  tomMatch576NameNumber_freshVar_73=  tomMatch576NameNumber_freshVar_54.getHeadconcTNode() .getSpecified() ;{  String  tomMatch576NameNumber_freshVar_74=  tomMatch576NameNumber_freshVar_54.getHeadconcTNode() .getValue() ;if ( "value".equals(tomMatch576NameNumber_freshVar_72) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_57= tomMatch576NameNumber_freshVar_54.getTailconcTNode() ;if ( ((tomMatch576NameNumber_freshVar_36 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch576NameNumber_freshVar_36 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_59=tomMatch576NameNumber_freshVar_36;if ( tomMatch576NameNumber_freshVar_59.isEmptyconcTNode() ) {if ( true ) {

            list = tom_append_list_concPlatformOption(list, tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make( tom.platform.adt.platformoption.types.platformoption.PluginOption.make(tomMatch576NameNumber_freshVar_71, tomMatch576NameNumber_freshVar_62, tomMatch576NameNumber_freshVar_68,  tom.platform.adt.platformoption.types.platformvalue.IntegerValue.make(Integer.parseInt(tomMatch576NameNumber_freshVar_74)) , tomMatch576NameNumber_freshVar_65) , tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption.make() ) );
          }}}}}}}}}}}}if ( tomMatch576NameNumber_end_56.isEmptyconcTNode() ) {tomMatch576NameNumber_end_56=tomMatch576NameNumber_begin_55;} else {tomMatch576NameNumber_end_56= tomMatch576NameNumber_end_56.getTailconcTNode() ;}}} while(!( tomMatch576NameNumber_end_56.equals(tomMatch576NameNumber_begin_55) ));}}}}}}}}}}if ( tomMatch576NameNumber_end_52.isEmptyconcTNode() ) {tomMatch576NameNumber_end_52=tomMatch576NameNumber_begin_51;} else {tomMatch576NameNumber_end_52= tomMatch576NameNumber_end_52.getTailconcTNode() ;}}} while(!( tomMatch576NameNumber_end_52.equals(tomMatch576NameNumber_begin_51) ));}}}}}}}}}}if ( tomMatch576NameNumber_end_48.isEmptyconcTNode() ) {tomMatch576NameNumber_end_48=tomMatch576NameNumber_begin_47;} else {tomMatch576NameNumber_end_48= tomMatch576NameNumber_end_48.getTailconcTNode() ;}}} while(!( tomMatch576NameNumber_end_48.equals(tomMatch576NameNumber_begin_47) ));}}}}}}}}}}if ( tomMatch576NameNumber_end_44.isEmptyconcTNode() ) {tomMatch576NameNumber_end_44=tomMatch576NameNumber_begin_43;} else {tomMatch576NameNumber_end_44= tomMatch576NameNumber_end_44.getTailconcTNode() ;}}} while(!( tomMatch576NameNumber_end_44.equals(tomMatch576NameNumber_begin_43) ));}}}}}}}}}}if ( tomMatch576NameNumber_end_40.isEmptyconcTNode() ) {tomMatch576NameNumber_end_40=tomMatch576NameNumber_begin_39;} else {tomMatch576NameNumber_end_40= tomMatch576NameNumber_end_40.getTailconcTNode() ;}}} while(!( tomMatch576NameNumber_end_40.equals(tomMatch576NameNumber_begin_39) ));}}}}}}}}}}}if ( (tom_option instanceof tom.library.adt.tnode.types.TNode) ) {{  tom.library.adt.tnode.types.TNode  tomMatch576NameNumberfreshSubject_1=(( tom.library.adt.tnode.types.TNode )tom_option);if ( (tomMatch576NameNumberfreshSubject_1 instanceof tom.library.adt.tnode.types.tnode.ElementNode) ) {{  String  tomMatch576NameNumber_freshVar_75= tomMatch576NameNumberfreshSubject_1.getName() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_76= tomMatch576NameNumberfreshSubject_1.getAttrList() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_77= tomMatch576NameNumberfreshSubject_1.getChildList() ;if ( "string".equals(tomMatch576NameNumber_freshVar_75) ) {if ( ((tomMatch576NameNumber_freshVar_76 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch576NameNumber_freshVar_76 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_78=tomMatch576NameNumber_freshVar_76;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_begin_80=tomMatch576NameNumber_freshVar_78;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_end_81=tomMatch576NameNumber_freshVar_78;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_79=tomMatch576NameNumber_end_81;if (!( tomMatch576NameNumber_freshVar_79.isEmptyconcTNode() )) {if ( ( tomMatch576NameNumber_freshVar_79.getHeadconcTNode()  instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch576NameNumber_freshVar_101=  tomMatch576NameNumber_freshVar_79.getHeadconcTNode() .getName() ;{  String  tomMatch576NameNumber_freshVar_102=  tomMatch576NameNumber_freshVar_79.getHeadconcTNode() .getSpecified() ;{  String  tomMatch576NameNumber_freshVar_103=  tomMatch576NameNumber_freshVar_79.getHeadconcTNode() .getValue() ;if ( "altName".equals(tomMatch576NameNumber_freshVar_101) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_82= tomMatch576NameNumber_freshVar_79.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_begin_84=tomMatch576NameNumber_freshVar_82;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_end_85=tomMatch576NameNumber_freshVar_82;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_83=tomMatch576NameNumber_end_85;if (!( tomMatch576NameNumber_freshVar_83.isEmptyconcTNode() )) {if ( ( tomMatch576NameNumber_freshVar_83.getHeadconcTNode()  instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch576NameNumber_freshVar_104=  tomMatch576NameNumber_freshVar_83.getHeadconcTNode() .getName() ;{  String  tomMatch576NameNumber_freshVar_105=  tomMatch576NameNumber_freshVar_83.getHeadconcTNode() .getSpecified() ;{  String  tomMatch576NameNumber_freshVar_106=  tomMatch576NameNumber_freshVar_83.getHeadconcTNode() .getValue() ;if ( "attrName".equals(tomMatch576NameNumber_freshVar_104) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_86= tomMatch576NameNumber_freshVar_83.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_begin_88=tomMatch576NameNumber_freshVar_86;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_end_89=tomMatch576NameNumber_freshVar_86;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_87=tomMatch576NameNumber_end_89;if (!( tomMatch576NameNumber_freshVar_87.isEmptyconcTNode() )) {if ( ( tomMatch576NameNumber_freshVar_87.getHeadconcTNode()  instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch576NameNumber_freshVar_107=  tomMatch576NameNumber_freshVar_87.getHeadconcTNode() .getName() ;{  String  tomMatch576NameNumber_freshVar_108=  tomMatch576NameNumber_freshVar_87.getHeadconcTNode() .getSpecified() ;{  String  tomMatch576NameNumber_freshVar_109=  tomMatch576NameNumber_freshVar_87.getHeadconcTNode() .getValue() ;if ( "description".equals(tomMatch576NameNumber_freshVar_107) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_90= tomMatch576NameNumber_freshVar_87.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_begin_92=tomMatch576NameNumber_freshVar_90;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_end_93=tomMatch576NameNumber_freshVar_90;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_91=tomMatch576NameNumber_end_93;if (!( tomMatch576NameNumber_freshVar_91.isEmptyconcTNode() )) {if ( ( tomMatch576NameNumber_freshVar_91.getHeadconcTNode()  instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch576NameNumber_freshVar_110=  tomMatch576NameNumber_freshVar_91.getHeadconcTNode() .getName() ;{  String  tomMatch576NameNumber_freshVar_111=  tomMatch576NameNumber_freshVar_91.getHeadconcTNode() .getSpecified() ;{  String  tomMatch576NameNumber_freshVar_112=  tomMatch576NameNumber_freshVar_91.getHeadconcTNode() .getValue() ;if ( "name".equals(tomMatch576NameNumber_freshVar_110) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_94= tomMatch576NameNumber_freshVar_91.getTailconcTNode() ;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_begin_96=tomMatch576NameNumber_freshVar_94;{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_end_97=tomMatch576NameNumber_freshVar_94;do {{{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_95=tomMatch576NameNumber_end_97;if (!( tomMatch576NameNumber_freshVar_95.isEmptyconcTNode() )) {if ( ( tomMatch576NameNumber_freshVar_95.getHeadconcTNode()  instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {{  String  tomMatch576NameNumber_freshVar_113=  tomMatch576NameNumber_freshVar_95.getHeadconcTNode() .getName() ;{  String  tomMatch576NameNumber_freshVar_114=  tomMatch576NameNumber_freshVar_95.getHeadconcTNode() .getSpecified() ;{  String  tomMatch576NameNumber_freshVar_115=  tomMatch576NameNumber_freshVar_95.getHeadconcTNode() .getValue() ;if ( "value".equals(tomMatch576NameNumber_freshVar_113) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_98= tomMatch576NameNumber_freshVar_95.getTailconcTNode() ;if ( ((tomMatch576NameNumber_freshVar_77 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch576NameNumber_freshVar_77 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {{  tom.library.adt.tnode.types.TNodeList  tomMatch576NameNumber_freshVar_100=tomMatch576NameNumber_freshVar_77;if ( tomMatch576NameNumber_freshVar_100.isEmptyconcTNode() ) {if ( true ) {

            list = tom_append_list_concPlatformOption(list, tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make( tom.platform.adt.platformoption.types.platformoption.PluginOption.make(tomMatch576NameNumber_freshVar_112, tomMatch576NameNumber_freshVar_103, tomMatch576NameNumber_freshVar_109,  tom.platform.adt.platformoption.types.platformvalue.StringValue.make(tomMatch576NameNumber_freshVar_115) , tomMatch576NameNumber_freshVar_106) , tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption.make() ) );
          }}}}}}}}}}}}if ( tomMatch576NameNumber_end_97.isEmptyconcTNode() ) {tomMatch576NameNumber_end_97=tomMatch576NameNumber_begin_96;} else {tomMatch576NameNumber_end_97= tomMatch576NameNumber_end_97.getTailconcTNode() ;}}} while(!( tomMatch576NameNumber_end_97.equals(tomMatch576NameNumber_begin_96) ));}}}}}}}}}}if ( tomMatch576NameNumber_end_93.isEmptyconcTNode() ) {tomMatch576NameNumber_end_93=tomMatch576NameNumber_begin_92;} else {tomMatch576NameNumber_end_93= tomMatch576NameNumber_end_93.getTailconcTNode() ;}}} while(!( tomMatch576NameNumber_end_93.equals(tomMatch576NameNumber_begin_92) ));}}}}}}}}}}if ( tomMatch576NameNumber_end_89.isEmptyconcTNode() ) {tomMatch576NameNumber_end_89=tomMatch576NameNumber_begin_88;} else {tomMatch576NameNumber_end_89= tomMatch576NameNumber_end_89.getTailconcTNode() ;}}} while(!( tomMatch576NameNumber_end_89.equals(tomMatch576NameNumber_begin_88) ));}}}}}}}}}}if ( tomMatch576NameNumber_end_85.isEmptyconcTNode() ) {tomMatch576NameNumber_end_85=tomMatch576NameNumber_begin_84;} else {tomMatch576NameNumber_end_85= tomMatch576NameNumber_end_85.getTailconcTNode() ;}}} while(!( tomMatch576NameNumber_end_85.equals(tomMatch576NameNumber_begin_84) ));}}}}}}}}}}if ( tomMatch576NameNumber_end_81.isEmptyconcTNode() ) {tomMatch576NameNumber_end_81=tomMatch576NameNumber_begin_80;} else {tomMatch576NameNumber_end_81= tomMatch576NameNumber_end_81.getTailconcTNode() ;}}} while(!( tomMatch576NameNumber_end_81.equals(tomMatch576NameNumber_begin_80) ));}}}}}}}}}}}}

      }}}}}if ( tomMatch575NameNumber_end_8.isEmptyconcTNode() ) {tomMatch575NameNumber_end_8=tomMatch575NameNumber_begin_7;} else {tomMatch575NameNumber_end_8= tomMatch575NameNumber_end_8.getTailconcTNode() ;}}} while(!( tomMatch575NameNumber_end_8.equals(tomMatch575NameNumber_begin_7) ));}}}}}}}}}}}}}}

    return list;
  }

}
