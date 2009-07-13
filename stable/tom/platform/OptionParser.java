/*
 * 
 * TOM - To One Matching Compiler
 * 
 * Copyright (c) 2000-2009, INRIA
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
  
       private static   tom.library.adt.tnode.types.TNodeList  tom_append_list_concTNode( tom.library.adt.tnode.types.TNodeList l1,  tom.library.adt.tnode.types.TNodeList  l2) {     if( l1.isEmptyconcTNode() ) {       return l2;     } else if( l2.isEmptyconcTNode() ) {       return l1;     } else if(  l1.getTailconcTNode() .isEmptyconcTNode() ) {       return  tom.library.adt.tnode.types.tnodelist.ConsconcTNode.make( l1.getHeadconcTNode() ,l2) ;     } else {       return  tom.library.adt.tnode.types.tnodelist.ConsconcTNode.make( l1.getHeadconcTNode() ,tom_append_list_concTNode( l1.getTailconcTNode() ,l2)) ;     }   }   private static   tom.library.adt.tnode.types.TNodeList  tom_get_slice_concTNode( tom.library.adt.tnode.types.TNodeList  begin,  tom.library.adt.tnode.types.TNodeList  end, tom.library.adt.tnode.types.TNodeList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTNode()  ||  (end== tom.library.adt.tnode.types.tnodelist.EmptyconcTNode.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.library.adt.tnode.types.tnodelist.ConsconcTNode.make( begin.getHeadconcTNode() ,( tom.library.adt.tnode.types.TNodeList )tom_get_slice_concTNode( begin.getTailconcTNode() ,end,tail)) ;   }        private static   tom.platform.adt.platformoption.types.PlatformOptionList  tom_append_list_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOptionList l1,  tom.platform.adt.platformoption.types.PlatformOptionList  l2) {     if( l1.isEmptyconcPlatformOption() ) {       return l2;     } else if( l2.isEmptyconcPlatformOption() ) {       return l1;     } else if(  l1.getTailconcPlatformOption() .isEmptyconcPlatformOption() ) {       return  tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make( l1.getHeadconcPlatformOption() ,l2) ;     } else {       return  tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make( l1.getHeadconcPlatformOption() ,tom_append_list_concPlatformOption( l1.getTailconcPlatformOption() ,l2)) ;     }   }   private static   tom.platform.adt.platformoption.types.PlatformOptionList  tom_get_slice_concPlatformOption( tom.platform.adt.platformoption.types.PlatformOptionList  begin,  tom.platform.adt.platformoption.types.PlatformOptionList  end, tom.platform.adt.platformoption.types.PlatformOptionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcPlatformOption()  ||  (end== tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make( begin.getHeadconcPlatformOption() ,( tom.platform.adt.platformoption.types.PlatformOptionList )tom_get_slice_concPlatformOption( begin.getTailconcPlatformOption() ,end,tail)) ;   }    


  
  /**
   * An XMLTools for doing the stuff
   */
    // non static XmlTools
  //private static XmlTools xtools = new XmlTools();
  
  /**
   * @return a PlatformOptionList extracted from the a String
   */
  public static PlatformOptionList xmlToOptionList(String xmlString) {
    InputStream stream = new ByteArrayInputStream(xmlString.getBytes());
    // non static XmlTools
    XmlTools xtools = new XmlTools();
    TNode node = xtools.convertXMLToTNode(stream);
    return xmlNodeToOptionList(node.getDocElem());
  }
  
  /**
   * @return a PlatformOptionList extracted from a TNode
   */
  public static PlatformOptionList xmlNodeToOptionList(TNode optionsNode) {
    PlatformOptionList list =  tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption.make() ;
    {{if ( (optionsNode instanceof tom.library.adt.tnode.types.TNode) ) {if ( ((( tom.library.adt.tnode.types.TNode )optionsNode) instanceof tom.library.adt.tnode.types.tnode.ElementNode) ) { tom.library.adt.tnode.types.TNodeList  tomMatch638NameNumber_freshVar_3= (( tom.library.adt.tnode.types.TNode )optionsNode).getChildList() ;if ( "options".equals( (( tom.library.adt.tnode.types.TNode )optionsNode).getName() ) ) {if ( (( (( tom.library.adt.tnode.types.TNode )optionsNode).getAttrList()  instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || ( (( tom.library.adt.tnode.types.TNode )optionsNode).getAttrList()  instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {if ( ((tomMatch638NameNumber_freshVar_3 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch638NameNumber_freshVar_3 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) { tom.library.adt.tnode.types.TNodeList  tomMatch638NameNumber_end_11=tomMatch638NameNumber_freshVar_3;do {{if (!( tomMatch638NameNumber_end_11.isEmptyconcTNode() )) { tom.library.adt.tnode.types.TNode  tom_option= tomMatch638NameNumber_end_11.getHeadconcTNode() ;{{if ( (tom_option instanceof tom.library.adt.tnode.types.TNode) ) {if ( ((( tom.library.adt.tnode.types.TNode )tom_option) instanceof tom.library.adt.tnode.types.tnode.ElementNode) ) { tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_freshVar_2= (( tom.library.adt.tnode.types.TNode )tom_option).getAttrList() ; tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_freshVar_3= (( tom.library.adt.tnode.types.TNode )tom_option).getChildList() ;if ( "boolean".equals( (( tom.library.adt.tnode.types.TNode )tom_option).getName() ) ) {if ( ((tomMatch639NameNumber_freshVar_2 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch639NameNumber_freshVar_2 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) { tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_end_9=tomMatch639NameNumber_freshVar_2;do {{if (!( tomMatch639NameNumber_end_9.isEmptyconcTNode() )) { tom.library.adt.tnode.types.TNode  tomMatch639NameNumber_freshVar_28= tomMatch639NameNumber_end_9.getHeadconcTNode() ;if ( (tomMatch639NameNumber_freshVar_28 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {if ( "altName".equals( tomMatch639NameNumber_freshVar_28.getName() ) ) { tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_freshVar_10= tomMatch639NameNumber_end_9.getTailconcTNode() ; tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_end_13=tomMatch639NameNumber_freshVar_10;do {{if (!( tomMatch639NameNumber_end_13.isEmptyconcTNode() )) { tom.library.adt.tnode.types.TNode  tomMatch639NameNumber_freshVar_33= tomMatch639NameNumber_end_13.getHeadconcTNode() ;if ( (tomMatch639NameNumber_freshVar_33 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {if ( "description".equals( tomMatch639NameNumber_freshVar_33.getName() ) ) { tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_freshVar_14= tomMatch639NameNumber_end_13.getTailconcTNode() ; tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_end_17=tomMatch639NameNumber_freshVar_14;do {{if (!( tomMatch639NameNumber_end_17.isEmptyconcTNode() )) { tom.library.adt.tnode.types.TNode  tomMatch639NameNumber_freshVar_38= tomMatch639NameNumber_end_17.getHeadconcTNode() ;if ( (tomMatch639NameNumber_freshVar_38 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {if ( "name".equals( tomMatch639NameNumber_freshVar_38.getName() ) ) { tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_freshVar_18= tomMatch639NameNumber_end_17.getTailconcTNode() ; tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_end_21=tomMatch639NameNumber_freshVar_18;do {{if (!( tomMatch639NameNumber_end_21.isEmptyconcTNode() )) { tom.library.adt.tnode.types.TNode  tomMatch639NameNumber_freshVar_43= tomMatch639NameNumber_end_21.getHeadconcTNode() ;if ( (tomMatch639NameNumber_freshVar_43 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {if ( "value".equals( tomMatch639NameNumber_freshVar_43.getName() ) ) {if ( ((tomMatch639NameNumber_freshVar_3 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch639NameNumber_freshVar_3 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {if ( tomMatch639NameNumber_freshVar_3.isEmptyconcTNode() ) {


 
            PlatformBoolean bool = Boolean.valueOf( tomMatch639NameNumber_freshVar_43.getValue() ).booleanValue()? tom.platform.adt.platformoption.types.platformboolean.True.make() : tom.platform.adt.platformoption.types.platformboolean.False.make() ;
            list = tom_append_list_concPlatformOption(list, tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make( tom.platform.adt.platformoption.types.platformoption.PluginOption.make( tomMatch639NameNumber_freshVar_38.getValue() ,  tomMatch639NameNumber_freshVar_28.getValue() ,  tomMatch639NameNumber_freshVar_33.getValue() ,  tom.platform.adt.platformoption.types.platformvalue.BooleanValue.make(bool) , "") , tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption.make() ) ); 
          }}}}}if ( tomMatch639NameNumber_end_21.isEmptyconcTNode() ) {tomMatch639NameNumber_end_21=tomMatch639NameNumber_freshVar_18;} else {tomMatch639NameNumber_end_21= tomMatch639NameNumber_end_21.getTailconcTNode() ;}}} while(!( (tomMatch639NameNumber_end_21==tomMatch639NameNumber_freshVar_18) ));}}}if ( tomMatch639NameNumber_end_17.isEmptyconcTNode() ) {tomMatch639NameNumber_end_17=tomMatch639NameNumber_freshVar_14;} else {tomMatch639NameNumber_end_17= tomMatch639NameNumber_end_17.getTailconcTNode() ;}}} while(!( (tomMatch639NameNumber_end_17==tomMatch639NameNumber_freshVar_14) ));}}}if ( tomMatch639NameNumber_end_13.isEmptyconcTNode() ) {tomMatch639NameNumber_end_13=tomMatch639NameNumber_freshVar_10;} else {tomMatch639NameNumber_end_13= tomMatch639NameNumber_end_13.getTailconcTNode() ;}}} while(!( (tomMatch639NameNumber_end_13==tomMatch639NameNumber_freshVar_10) ));}}}if ( tomMatch639NameNumber_end_9.isEmptyconcTNode() ) {tomMatch639NameNumber_end_9=tomMatch639NameNumber_freshVar_2;} else {tomMatch639NameNumber_end_9= tomMatch639NameNumber_end_9.getTailconcTNode() ;}}} while(!( (tomMatch639NameNumber_end_9==tomMatch639NameNumber_freshVar_2) ));}}}}}{if ( (tom_option instanceof tom.library.adt.tnode.types.TNode) ) {if ( ((( tom.library.adt.tnode.types.TNode )tom_option) instanceof tom.library.adt.tnode.types.tnode.ElementNode) ) { tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_freshVar_47= (( tom.library.adt.tnode.types.TNode )tom_option).getAttrList() ; tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_freshVar_48= (( tom.library.adt.tnode.types.TNode )tom_option).getChildList() ;if ( "integer".equals( (( tom.library.adt.tnode.types.TNode )tom_option).getName() ) ) {if ( ((tomMatch639NameNumber_freshVar_47 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch639NameNumber_freshVar_47 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) { tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_end_54=tomMatch639NameNumber_freshVar_47;do {{if (!( tomMatch639NameNumber_end_54.isEmptyconcTNode() )) { tom.library.adt.tnode.types.TNode  tomMatch639NameNumber_freshVar_77= tomMatch639NameNumber_end_54.getHeadconcTNode() ;if ( (tomMatch639NameNumber_freshVar_77 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {if ( "altName".equals( tomMatch639NameNumber_freshVar_77.getName() ) ) { tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_freshVar_55= tomMatch639NameNumber_end_54.getTailconcTNode() ; tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_end_58=tomMatch639NameNumber_freshVar_55;do {{if (!( tomMatch639NameNumber_end_58.isEmptyconcTNode() )) { tom.library.adt.tnode.types.TNode  tomMatch639NameNumber_freshVar_82= tomMatch639NameNumber_end_58.getHeadconcTNode() ;if ( (tomMatch639NameNumber_freshVar_82 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {if ( "attrName".equals( tomMatch639NameNumber_freshVar_82.getName() ) ) { tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_freshVar_59= tomMatch639NameNumber_end_58.getTailconcTNode() ; tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_end_62=tomMatch639NameNumber_freshVar_59;do {{if (!( tomMatch639NameNumber_end_62.isEmptyconcTNode() )) { tom.library.adt.tnode.types.TNode  tomMatch639NameNumber_freshVar_87= tomMatch639NameNumber_end_62.getHeadconcTNode() ;if ( (tomMatch639NameNumber_freshVar_87 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {if ( "description".equals( tomMatch639NameNumber_freshVar_87.getName() ) ) { tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_freshVar_63= tomMatch639NameNumber_end_62.getTailconcTNode() ; tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_end_66=tomMatch639NameNumber_freshVar_63;do {{if (!( tomMatch639NameNumber_end_66.isEmptyconcTNode() )) { tom.library.adt.tnode.types.TNode  tomMatch639NameNumber_freshVar_92= tomMatch639NameNumber_end_66.getHeadconcTNode() ;if ( (tomMatch639NameNumber_freshVar_92 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {if ( "name".equals( tomMatch639NameNumber_freshVar_92.getName() ) ) { tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_freshVar_67= tomMatch639NameNumber_end_66.getTailconcTNode() ; tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_end_70=tomMatch639NameNumber_freshVar_67;do {{if (!( tomMatch639NameNumber_end_70.isEmptyconcTNode() )) { tom.library.adt.tnode.types.TNode  tomMatch639NameNumber_freshVar_97= tomMatch639NameNumber_end_70.getHeadconcTNode() ;if ( (tomMatch639NameNumber_freshVar_97 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {if ( "value".equals( tomMatch639NameNumber_freshVar_97.getName() ) ) {if ( ((tomMatch639NameNumber_freshVar_48 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch639NameNumber_freshVar_48 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {if ( tomMatch639NameNumber_freshVar_48.isEmptyconcTNode() ) {

            list = tom_append_list_concPlatformOption(list, tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make( tom.platform.adt.platformoption.types.platformoption.PluginOption.make( tomMatch639NameNumber_freshVar_92.getValue() ,  tomMatch639NameNumber_freshVar_77.getValue() ,  tomMatch639NameNumber_freshVar_87.getValue() ,  tom.platform.adt.platformoption.types.platformvalue.IntegerValue.make(Integer.parseInt( tomMatch639NameNumber_freshVar_97.getValue() )) ,  tomMatch639NameNumber_freshVar_82.getValue() ) , tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption.make() ) );
          }}}}}if ( tomMatch639NameNumber_end_70.isEmptyconcTNode() ) {tomMatch639NameNumber_end_70=tomMatch639NameNumber_freshVar_67;} else {tomMatch639NameNumber_end_70= tomMatch639NameNumber_end_70.getTailconcTNode() ;}}} while(!( (tomMatch639NameNumber_end_70==tomMatch639NameNumber_freshVar_67) ));}}}if ( tomMatch639NameNumber_end_66.isEmptyconcTNode() ) {tomMatch639NameNumber_end_66=tomMatch639NameNumber_freshVar_63;} else {tomMatch639NameNumber_end_66= tomMatch639NameNumber_end_66.getTailconcTNode() ;}}} while(!( (tomMatch639NameNumber_end_66==tomMatch639NameNumber_freshVar_63) ));}}}if ( tomMatch639NameNumber_end_62.isEmptyconcTNode() ) {tomMatch639NameNumber_end_62=tomMatch639NameNumber_freshVar_59;} else {tomMatch639NameNumber_end_62= tomMatch639NameNumber_end_62.getTailconcTNode() ;}}} while(!( (tomMatch639NameNumber_end_62==tomMatch639NameNumber_freshVar_59) ));}}}if ( tomMatch639NameNumber_end_58.isEmptyconcTNode() ) {tomMatch639NameNumber_end_58=tomMatch639NameNumber_freshVar_55;} else {tomMatch639NameNumber_end_58= tomMatch639NameNumber_end_58.getTailconcTNode() ;}}} while(!( (tomMatch639NameNumber_end_58==tomMatch639NameNumber_freshVar_55) ));}}}if ( tomMatch639NameNumber_end_54.isEmptyconcTNode() ) {tomMatch639NameNumber_end_54=tomMatch639NameNumber_freshVar_47;} else {tomMatch639NameNumber_end_54= tomMatch639NameNumber_end_54.getTailconcTNode() ;}}} while(!( (tomMatch639NameNumber_end_54==tomMatch639NameNumber_freshVar_47) ));}}}}}{if ( (tom_option instanceof tom.library.adt.tnode.types.TNode) ) {if ( ((( tom.library.adt.tnode.types.TNode )tom_option) instanceof tom.library.adt.tnode.types.tnode.ElementNode) ) { tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_freshVar_101= (( tom.library.adt.tnode.types.TNode )tom_option).getAttrList() ; tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_freshVar_102= (( tom.library.adt.tnode.types.TNode )tom_option).getChildList() ;if ( "string".equals( (( tom.library.adt.tnode.types.TNode )tom_option).getName() ) ) {if ( ((tomMatch639NameNumber_freshVar_101 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch639NameNumber_freshVar_101 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) { tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_end_108=tomMatch639NameNumber_freshVar_101;do {{if (!( tomMatch639NameNumber_end_108.isEmptyconcTNode() )) { tom.library.adt.tnode.types.TNode  tomMatch639NameNumber_freshVar_131= tomMatch639NameNumber_end_108.getHeadconcTNode() ;if ( (tomMatch639NameNumber_freshVar_131 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {if ( "altName".equals( tomMatch639NameNumber_freshVar_131.getName() ) ) { tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_freshVar_109= tomMatch639NameNumber_end_108.getTailconcTNode() ; tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_end_112=tomMatch639NameNumber_freshVar_109;do {{if (!( tomMatch639NameNumber_end_112.isEmptyconcTNode() )) { tom.library.adt.tnode.types.TNode  tomMatch639NameNumber_freshVar_136= tomMatch639NameNumber_end_112.getHeadconcTNode() ;if ( (tomMatch639NameNumber_freshVar_136 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {if ( "attrName".equals( tomMatch639NameNumber_freshVar_136.getName() ) ) { tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_freshVar_113= tomMatch639NameNumber_end_112.getTailconcTNode() ; tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_end_116=tomMatch639NameNumber_freshVar_113;do {{if (!( tomMatch639NameNumber_end_116.isEmptyconcTNode() )) { tom.library.adt.tnode.types.TNode  tomMatch639NameNumber_freshVar_141= tomMatch639NameNumber_end_116.getHeadconcTNode() ;if ( (tomMatch639NameNumber_freshVar_141 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {if ( "description".equals( tomMatch639NameNumber_freshVar_141.getName() ) ) { tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_freshVar_117= tomMatch639NameNumber_end_116.getTailconcTNode() ; tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_end_120=tomMatch639NameNumber_freshVar_117;do {{if (!( tomMatch639NameNumber_end_120.isEmptyconcTNode() )) { tom.library.adt.tnode.types.TNode  tomMatch639NameNumber_freshVar_146= tomMatch639NameNumber_end_120.getHeadconcTNode() ;if ( (tomMatch639NameNumber_freshVar_146 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {if ( "name".equals( tomMatch639NameNumber_freshVar_146.getName() ) ) { tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_freshVar_121= tomMatch639NameNumber_end_120.getTailconcTNode() ; tom.library.adt.tnode.types.TNodeList  tomMatch639NameNumber_end_124=tomMatch639NameNumber_freshVar_121;do {{if (!( tomMatch639NameNumber_end_124.isEmptyconcTNode() )) { tom.library.adt.tnode.types.TNode  tomMatch639NameNumber_freshVar_151= tomMatch639NameNumber_end_124.getHeadconcTNode() ;if ( (tomMatch639NameNumber_freshVar_151 instanceof tom.library.adt.tnode.types.tnode.AttributeNode) ) {if ( "value".equals( tomMatch639NameNumber_freshVar_151.getName() ) ) {if ( ((tomMatch639NameNumber_freshVar_102 instanceof tom.library.adt.tnode.types.tnodelist.ConsconcTNode) || (tomMatch639NameNumber_freshVar_102 instanceof tom.library.adt.tnode.types.tnodelist.EmptyconcTNode)) ) {if ( tomMatch639NameNumber_freshVar_102.isEmptyconcTNode() ) {

            list = tom_append_list_concPlatformOption(list, tom.platform.adt.platformoption.types.platformoptionlist.ConsconcPlatformOption.make( tom.platform.adt.platformoption.types.platformoption.PluginOption.make( tomMatch639NameNumber_freshVar_146.getValue() ,  tomMatch639NameNumber_freshVar_131.getValue() ,  tomMatch639NameNumber_freshVar_141.getValue() ,  tom.platform.adt.platformoption.types.platformvalue.StringValue.make( tomMatch639NameNumber_freshVar_151.getValue() ) ,  tomMatch639NameNumber_freshVar_136.getValue() ) , tom.platform.adt.platformoption.types.platformoptionlist.EmptyconcPlatformOption.make() ) );
          }}}}}if ( tomMatch639NameNumber_end_124.isEmptyconcTNode() ) {tomMatch639NameNumber_end_124=tomMatch639NameNumber_freshVar_121;} else {tomMatch639NameNumber_end_124= tomMatch639NameNumber_end_124.getTailconcTNode() ;}}} while(!( (tomMatch639NameNumber_end_124==tomMatch639NameNumber_freshVar_121) ));}}}if ( tomMatch639NameNumber_end_120.isEmptyconcTNode() ) {tomMatch639NameNumber_end_120=tomMatch639NameNumber_freshVar_117;} else {tomMatch639NameNumber_end_120= tomMatch639NameNumber_end_120.getTailconcTNode() ;}}} while(!( (tomMatch639NameNumber_end_120==tomMatch639NameNumber_freshVar_117) ));}}}if ( tomMatch639NameNumber_end_116.isEmptyconcTNode() ) {tomMatch639NameNumber_end_116=tomMatch639NameNumber_freshVar_113;} else {tomMatch639NameNumber_end_116= tomMatch639NameNumber_end_116.getTailconcTNode() ;}}} while(!( (tomMatch639NameNumber_end_116==tomMatch639NameNumber_freshVar_113) ));}}}if ( tomMatch639NameNumber_end_112.isEmptyconcTNode() ) {tomMatch639NameNumber_end_112=tomMatch639NameNumber_freshVar_109;} else {tomMatch639NameNumber_end_112= tomMatch639NameNumber_end_112.getTailconcTNode() ;}}} while(!( (tomMatch639NameNumber_end_112==tomMatch639NameNumber_freshVar_109) ));}}}if ( tomMatch639NameNumber_end_108.isEmptyconcTNode() ) {tomMatch639NameNumber_end_108=tomMatch639NameNumber_freshVar_101;} else {tomMatch639NameNumber_end_108= tomMatch639NameNumber_end_108.getTailconcTNode() ;}}} while(!( (tomMatch639NameNumber_end_108==tomMatch639NameNumber_freshVar_101) ));}}}}}}

      }if ( tomMatch638NameNumber_end_11.isEmptyconcTNode() ) {tomMatch638NameNumber_end_11=tomMatch638NameNumber_freshVar_3;} else {tomMatch638NameNumber_end_11= tomMatch638NameNumber_end_11.getTailconcTNode() ;}}} while(!( (tomMatch638NameNumber_end_11==tomMatch638NameNumber_freshVar_3) ));}}}}}}}

    return list;
  }

}
