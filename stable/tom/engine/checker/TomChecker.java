/* Generated by TOM (version 2.3rc1): Do not edit this file *//*
 *   
 * TOM - To One Matching Compiler
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
 * Pierre-Etienne Moreau  e-mail: Pierre-Etienne.Moreau@loria.fr
 * Julien Guyon
 *
 **/

package tom.engine.checker;

import java.text.MessageFormat;
import java.util.logging.Level;

import tom.engine.TomMessage;
import tom.engine.adt.tomsignature.types.Option;
import tom.engine.adt.tomsignature.types.OptionList;
import tom.engine.adt.tomsignature.types.TomSymbol;
import tom.engine.adt.tomsignature.types.TomTerm;
import tom.engine.adt.tomsignature.types.TomType;
import tom.engine.exception.TomRuntimeException;
import tom.engine.tools.TomGenericPlugin;
import tom.platform.PlatformLogRecord;


abstract public class TomChecker extends TomGenericPlugin {
  
    // ------------------------------------------------------------
  /* Generated by TOM (version 2.3rc1): Do not edit this file *//* Generated by TOM (version 2.3rc1): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/  /* Generated by TOM (version 2.3rc1): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/     /* Generated by TOM (version 2.3rc1): Do not edit this file *//*  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  */  /* Generated by TOM (version 2.3rc1): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/   /* Generated by TOM (version 2.3rc1): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/   /* Generated by TOM (version 2.3rc1): Do not edit this file *//*  *  * Copyright (c) 2004-2006, Pierre-Etienne Moreau  * All rights reserved.  *   * Redistribution and use in source and binary forms, with or without  * modification, are permitted provided that the following conditions are  * met:   *  - Redistributions of source code must retain the above copyright  *  notice, this list of conditions and the following disclaimer.    *  - Redistributions in binary form must reproduce the above copyright  *  notice, this list of conditions and the following disclaimer in the  *  documentation and/or other materials provided with the distribution.  *  - Neither the name of the INRIA nor the names of its  *  contributors may be used to endorse or promote products derived from  *  this software without specific prior written permission.  *   * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT  * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR  * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT  * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,  * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT  * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,  * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY  * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.  *   **/     
    // ------------------------------------------------------------
  
  static protected class TermDescription {
    int termClass, decLine;
    String name ="";
    TomType tomType = null;
    public TermDescription(int termClass, String name, int decLine, TomType tomType) {
      this.termClass = termClass;
      this.decLine = decLine;
      this.name = name;
      this.tomType = tomType;
    }
    public String type() {
      if(tomType != null && !tomType.isEmptyType()) {
        return tomType.getString();
      } else {
        return null;
      }
    }
  }
    // Different kind of structures
  protected final static int TERM_APPL               = 0;
  protected final static int UNAMED_APPL             = 1;
  protected final static int APPL_DISJUNCTION        = 2;
  protected final static int RECORD_APPL             = 3;
  protected final static int RECORD_APPL_DISJUNCTION = 4;
  protected final static int XML_APPL                = 5;
  protected final static int VARIABLE_STAR           = 6;
  protected final static int UNAMED_VARIABLE_STAR    = 7;
  protected final static int PLACE_HOLDER            = 8;
  protected final static int VARIABLE                = 9;
  
  protected boolean strictType = false;
  protected Option currentTomStructureOrgTrack;
    
  public TomChecker(String name) {
    super(name);
  }

  protected void reinit() {
    currentTomStructureOrgTrack = null;
  }
 
  public int getClass(TomTerm term) {
     if(term instanceof  tom.engine.adt.tomsignature.types.TomTerm) { { tom.engine.adt.tomsignature.types.TomTerm tom_match1_1=(( tom.engine.adt.tomsignature.types.TomTerm)term); if (tom_is_fun_sym_TermAppl(tom_match1_1) ||  false ) { { tom.engine.adt.tomsignature.types.NameList tom_match1_1_nameList=tom_get_slot_TermAppl_nameList(tom_match1_1); if (tom_is_fun_sym_concTomName(tom_match1_1_nameList) ||  false ) { { tom.engine.adt.tomsignature.types.NameList tom_match1_1_nameList_list1=tom_match1_1_nameList; if (!(tom_is_empty_concTomName_NameList(tom_match1_1_nameList_list1))) { { tom.engine.adt.tomsignature.types.TomName tom_match1_1_nameList_1=tom_get_head_concTomName_NameList(tom_match1_1_nameList_list1);tom_match1_1_nameList_list1=tom_get_tail_concTomName_NameList(tom_match1_1_nameList_list1); if (tom_is_fun_sym_Name(tom_match1_1_nameList_1) ||  false ) { { String  tom_match1_1_nameList_1_string=tom_get_slot_Name_string(tom_match1_1_nameList_1); if (tom_terms_equal_String("", tom_match1_1_nameList_1_string) ||  false ) { if (tom_is_empty_concTomName_NameList(tom_match1_1_nameList_list1)) { if ( true ) {
 return UNAMED_APPL; } } } } } } } } } } } if (tom_is_fun_sym_TermAppl(tom_match1_1) ||  false ) { { tom.engine.adt.tomsignature.types.NameList tom_match1_1_nameList=tom_get_slot_TermAppl_nameList(tom_match1_1); if (tom_is_fun_sym_concTomName(tom_match1_1_nameList) ||  false ) { { tom.engine.adt.tomsignature.types.NameList tom_match1_1_nameList_list1=tom_match1_1_nameList; if (!(tom_is_empty_concTomName_NameList(tom_match1_1_nameList_list1))) { { tom.engine.adt.tomsignature.types.TomName tom_match1_1_nameList_1=tom_get_head_concTomName_NameList(tom_match1_1_nameList_list1);tom_match1_1_nameList_list1=tom_get_tail_concTomName_NameList(tom_match1_1_nameList_list1); if (tom_is_fun_sym_Name(tom_match1_1_nameList_1) ||  false ) { { String  tom_match1_1_nameList_1_string=tom_get_slot_Name_string(tom_match1_1_nameList_1); if (tom_is_empty_concTomName_NameList(tom_match1_1_nameList_list1)) { if ( true ) {
 return TERM_APPL; } } } } } } } } } } if (tom_is_fun_sym_TermAppl(tom_match1_1) ||  false ) { { tom.engine.adt.tomsignature.types.NameList tom_match1_1_nameList=tom_get_slot_TermAppl_nameList(tom_match1_1); if (tom_is_fun_sym_concTomName(tom_match1_1_nameList) ||  false ) { { tom.engine.adt.tomsignature.types.NameList tom_match1_1_nameList_list1=tom_match1_1_nameList; if (!(tom_is_empty_concTomName_NameList(tom_match1_1_nameList_list1))) { { tom.engine.adt.tomsignature.types.TomName tom_match1_1_nameList_1=tom_get_head_concTomName_NameList(tom_match1_1_nameList_list1);tom_match1_1_nameList_list1=tom_get_tail_concTomName_NameList(tom_match1_1_nameList_list1); if (tom_is_fun_sym_Name(tom_match1_1_nameList_1) ||  false ) { { String  tom_match1_1_nameList_1_string=tom_get_slot_Name_string(tom_match1_1_nameList_1); if ( true ) {
 return APPL_DISJUNCTION; } } } } } } } } } if (tom_is_fun_sym_RecordAppl(tom_match1_1) ||  false ) { { tom.engine.adt.tomsignature.types.NameList tom_match1_1_nameList=tom_get_slot_RecordAppl_nameList(tom_match1_1); if (tom_is_fun_sym_concTomName(tom_match1_1_nameList) ||  false ) { { tom.engine.adt.tomsignature.types.NameList tom_match1_1_nameList_list1=tom_match1_1_nameList; if (!(tom_is_empty_concTomName_NameList(tom_match1_1_nameList_list1))) { { tom.engine.adt.tomsignature.types.TomName tom_match1_1_nameList_1=tom_get_head_concTomName_NameList(tom_match1_1_nameList_list1);tom_match1_1_nameList_list1=tom_get_tail_concTomName_NameList(tom_match1_1_nameList_list1); if (tom_is_fun_sym_Name(tom_match1_1_nameList_1) ||  false ) { { String  tom_match1_1_nameList_1_string=tom_get_slot_Name_string(tom_match1_1_nameList_1); if (tom_is_empty_concTomName_NameList(tom_match1_1_nameList_list1)) { if ( true ) {
 return RECORD_APPL; } } } } } } } } } } if (tom_is_fun_sym_RecordAppl(tom_match1_1) ||  false ) { { tom.engine.adt.tomsignature.types.NameList tom_match1_1_nameList=tom_get_slot_RecordAppl_nameList(tom_match1_1); if (tom_is_fun_sym_concTomName(tom_match1_1_nameList) ||  false ) { { tom.engine.adt.tomsignature.types.NameList tom_match1_1_nameList_list1=tom_match1_1_nameList; if (!(tom_is_empty_concTomName_NameList(tom_match1_1_nameList_list1))) { { tom.engine.adt.tomsignature.types.TomName tom_match1_1_nameList_1=tom_get_head_concTomName_NameList(tom_match1_1_nameList_list1);tom_match1_1_nameList_list1=tom_get_tail_concTomName_NameList(tom_match1_1_nameList_list1); if (tom_is_fun_sym_Name(tom_match1_1_nameList_1) ||  false ) { { String  tom_match1_1_nameList_1_string=tom_get_slot_Name_string(tom_match1_1_nameList_1); if ( true ) {
 return RECORD_APPL_DISJUNCTION; } } } } } } } } } if (tom_is_fun_sym_XMLAppl(tom_match1_1) ||  false ) { if ( true ) {
 return XML_APPL; } } if (tom_is_fun_sym_Placeholder(tom_match1_1) ||  false ) { if ( true ) {
 return PLACE_HOLDER; } } if (tom_is_fun_sym_VariableStar(tom_match1_1) ||  false ) { if ( true ) {
 return VARIABLE_STAR; } } if (tom_is_fun_sym_Variable(tom_match1_1) ||  false ) { if ( true ) {
 return VARIABLE; } } if (tom_is_fun_sym_UnamedVariableStar(tom_match1_1) ||  false ) { if ( true ) {
 return UNAMED_VARIABLE_STAR; } } } }

		throw new TomRuntimeException("Invalid Term");
  }
  
  public String getName(TomTerm term) {
    String dijunctionName = "";
     if(term instanceof  tom.engine.adt.tomsignature.types.TomTerm) { { tom.engine.adt.tomsignature.types.TomTerm tom_match2_1=(( tom.engine.adt.tomsignature.types.TomTerm)term); if (tom_is_fun_sym_TermAppl(tom_match2_1) ||  false ) { { tom.engine.adt.tomsignature.types.NameList tom_match2_1_nameList=tom_get_slot_TermAppl_nameList(tom_match2_1); if (tom_is_fun_sym_concTomName(tom_match2_1_nameList) ||  false ) { { tom.engine.adt.tomsignature.types.NameList tom_match2_1_nameList_list1=tom_match2_1_nameList; if (!(tom_is_empty_concTomName_NameList(tom_match2_1_nameList_list1))) { { tom.engine.adt.tomsignature.types.TomName tom_match2_1_nameList_1=tom_get_head_concTomName_NameList(tom_match2_1_nameList_list1);tom_match2_1_nameList_list1=tom_get_tail_concTomName_NameList(tom_match2_1_nameList_list1); if (tom_is_fun_sym_Name(tom_match2_1_nameList_1) ||  false ) { { String  tom_match2_1_nameList_1_string=tom_get_slot_Name_string(tom_match2_1_nameList_1); if (tom_is_empty_concTomName_NameList(tom_match2_1_nameList_list1)) { if ( true ) {
 return tom_match2_1_nameList_1_string; } } } } } } } } } } if (tom_is_fun_sym_TermAppl(tom_match2_1) ||  false ) { { tom.engine.adt.tomsignature.types.NameList tom_match2_1_nameList=tom_get_slot_TermAppl_nameList(tom_match2_1); { tom.engine.adt.tomsignature.types.NameList tom_nameList=tom_match2_1_nameList; if ( true ) {

        String head;
        dijunctionName = tom_nameList.getHead().getString();
        while(!tom_nameList.isEmpty()) {
          head = tom_nameList.getHead().getString();
          dijunctionName = ( dijunctionName.compareTo(head) > 0)?dijunctionName:head;
          tom_nameList= tom_nameList.getTail();
        }
        return dijunctionName;
       } } } } if (tom_is_fun_sym_RecordAppl(tom_match2_1) ||  false ) { { tom.engine.adt.tomsignature.types.NameList tom_match2_1_nameList=tom_get_slot_RecordAppl_nameList(tom_match2_1); if (tom_is_fun_sym_concTomName(tom_match2_1_nameList) ||  false ) { { tom.engine.adt.tomsignature.types.NameList tom_match2_1_nameList_list1=tom_match2_1_nameList; if (!(tom_is_empty_concTomName_NameList(tom_match2_1_nameList_list1))) { { tom.engine.adt.tomsignature.types.TomName tom_match2_1_nameList_1=tom_get_head_concTomName_NameList(tom_match2_1_nameList_list1);tom_match2_1_nameList_list1=tom_get_tail_concTomName_NameList(tom_match2_1_nameList_list1); if (tom_is_fun_sym_Name(tom_match2_1_nameList_1) ||  false ) { { String  tom_match2_1_nameList_1_string=tom_get_slot_Name_string(tom_match2_1_nameList_1); if (tom_is_empty_concTomName_NameList(tom_match2_1_nameList_list1)) { if ( true ) {
 return tom_match2_1_nameList_1_string; } } } } } } } } } } if (tom_is_fun_sym_RecordAppl(tom_match2_1) ||  false ) { { tom.engine.adt.tomsignature.types.NameList tom_match2_1_nameList=tom_get_slot_RecordAppl_nameList(tom_match2_1); { tom.engine.adt.tomsignature.types.NameList tom_nameList=tom_match2_1_nameList; if ( true ) {

        String head;
        dijunctionName = tom_nameList.getHead().getString();
        while(!tom_nameList.isEmpty()) {
          head = tom_nameList.getHead().getString();
          dijunctionName = ( dijunctionName.compareTo(head) > 0)?dijunctionName:head;
          tom_nameList= tom_nameList.getTail();
        }
        return dijunctionName;
       } } } } if (tom_is_fun_sym_XMLAppl(tom_match2_1) ||  false ) { { tom.engine.adt.tomsignature.types.NameList tom_match2_1_nameList=tom_get_slot_XMLAppl_nameList(tom_match2_1); if (tom_is_fun_sym_concTomName(tom_match2_1_nameList) ||  false ) { { tom.engine.adt.tomsignature.types.NameList tom_match2_1_nameList_list1=tom_match2_1_nameList; if (!(tom_is_empty_concTomName_NameList(tom_match2_1_nameList_list1))) { { tom.engine.adt.tomsignature.types.TomName tom_match2_1_nameList_1=tom_get_head_concTomName_NameList(tom_match2_1_nameList_list1);tom_match2_1_nameList_list1=tom_get_tail_concTomName_NameList(tom_match2_1_nameList_list1); if (tom_is_fun_sym_Name(tom_match2_1_nameList_1) ||  false ) { { String  tom_match2_1_nameList_1_string=tom_get_slot_Name_string(tom_match2_1_nameList_1); if ( true ) {
 return tom_match2_1_nameList_1_string; } } } } } } } } } if (tom_is_fun_sym_XMLAppl(tom_match2_1) ||  false ) { { tom.engine.adt.tomsignature.types.NameList tom_match2_1_nameList=tom_get_slot_XMLAppl_nameList(tom_match2_1); { tom.engine.adt.tomsignature.types.NameList tom_nameList=tom_match2_1_nameList; if ( true ) {

        String head;
        dijunctionName = tom_nameList.getHead().getString();
        while(!tom_nameList.isEmpty()) {
          head = tom_nameList.getHead().getString();
          dijunctionName = ( dijunctionName.compareTo(head) > 0)?dijunctionName:head;
          tom_nameList= tom_nameList.getTail();
        }
        return dijunctionName;
       } } } } if (tom_is_fun_sym_Placeholder(tom_match2_1) ||  false ) { if ( true ) {
 return "_"; } } {boolean tom_bool_match2_1= false ; { tom.engine.adt.tomsignature.types.TomName tom_match2_1_astName= null ; if (tom_is_fun_sym_Variable(tom_match2_1)) {tom_bool_match2_1= true ;tom_match2_1_astName=tom_get_slot_Variable_astName(tom_match2_1); } else { if (tom_is_fun_sym_VariableStar(tom_match2_1)) {tom_bool_match2_1= true ;tom_match2_1_astName=tom_get_slot_VariableStar_astName(tom_match2_1); } } if (tom_bool_match2_1) { if (tom_is_fun_sym_Name(tom_match2_1_astName) ||  false ) { { String  tom_match2_1_astName_string=tom_get_slot_Name_string(tom_match2_1_astName); if ( true ) {
 return tom_match2_1_astName_string+"*"; } } } } } } if (tom_is_fun_sym_UnamedVariableStar(tom_match2_1) ||  false ) { if ( true ) {
 return "_*"; } } } }

		throw new TomRuntimeException("Invalid Term");
  }
  
  /**
   * Shared Functions 
   */
  protected String extractType(TomSymbol symbol) {
    TomType type = getSymbolCodomain(symbol);
    return getTomType(type);
  }
  
  protected int findOriginTrackingLine(OptionList optionList) {
     if(optionList instanceof  tom.engine.adt.tomsignature.types.OptionList) { { tom.engine.adt.tomsignature.types.OptionList tom_match3_1=(( tom.engine.adt.tomsignature.types.OptionList)optionList); if (tom_is_fun_sym_concOption(tom_match3_1) ||  false ) { { tom.engine.adt.tomsignature.types.OptionList tom_match3_1_list1=tom_match3_1; { tom.engine.adt.tomsignature.types.OptionList tom_match3_1_begin1=tom_match3_1_list1; { tom.engine.adt.tomsignature.types.OptionList tom_match3_1_end1=tom_match3_1_list1; { while (!(tom_is_empty_concOption_OptionList(tom_match3_1_end1))) {tom_match3_1_list1=tom_match3_1_end1; { { tom.engine.adt.tomsignature.types.Option tom_match3_1_2=tom_get_head_concOption_OptionList(tom_match3_1_list1);tom_match3_1_list1=tom_get_tail_concOption_OptionList(tom_match3_1_list1); if (tom_is_fun_sym_OriginTracking(tom_match3_1_2) ||  false ) { { int  tom_match3_1_2_line=tom_get_slot_OriginTracking_line(tom_match3_1_2); if ( true ) {
 return tom_match3_1_2_line;  } } } }tom_match3_1_end1=tom_get_tail_concOption_OptionList(tom_match3_1_end1); } }tom_match3_1_list1=tom_match3_1_begin1; } } } } } } }

    return -1;
  }

  protected void ensureOriginTrackingLine(int line) {
    if(line < 0) {
      getLogger().log(Level.SEVERE,
                      TomMessage.findOTL.getMessage(),
                      getStreamManager().getInputFileName());
      //System.out.println("findOriginTrackingLine: not found ");
    }
  }

  /**
   * Message Functions
   */
  protected void messageError(int errorLine, TomMessage msg, Object[] msgArgs) {
    String structName = currentTomStructureOrgTrack.getAstName().getString();
    messageError(errorLine, structName, msg, msgArgs);
  }
  
  protected void messageError(int errorLine, String structInfo, TomMessage msg, Object[] msgArgs) {
    String fileName = currentTomStructureOrgTrack.getFileName().getString();
    int structDeclLine = currentTomStructureOrgTrack.getLine();
    getLogger().log(new PlatformLogRecord(Level.SEVERE, msg, msgArgs,fileName, errorLine, structDeclLine, structInfo));
  }
  
  protected void messageWarning(int errorLine, TomMessage msg, Object[] msgArgs) {
    String structName = currentTomStructureOrgTrack.getAstName().getString();
    messageWarning(errorLine, structName, msg, msgArgs);
  }
  
  protected void messageWarning(int errorLine, String structInfo, TomMessage msg, Object[] msgArgs) {
    String fileName = currentTomStructureOrgTrack.getFileName().getString();
    int structDeclLine = currentTomStructureOrgTrack.getLine();
    getLogger().log(new PlatformLogRecord(Level.WARNING,msg,msgArgs,fileName, errorLine,structDeclLine,structInfo));
  }
  
}  //Class TomChecker
