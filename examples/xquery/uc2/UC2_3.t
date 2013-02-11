/*
 * Copyright (c) 2004-2013, Universite de Lorraine, Inria
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met: 
 * 	- Redistributions of source code must retain the above copyright
 * 	notice, this list of conditions and the following disclaimer.  
 * 	- Redistributions in binary form must reproduce the above copyright
 * 	notice, this list of conditions and the following disclaimer in the
 * 	documentation and/or other materials provided with the distribution.
 * 	- Neither the name of the Inria nor the names of its
 * 	contributors may be used to endorse or promote products derived from
 * 	this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package xquery.uc2;
// How many sections are in Book1, and how many figures?

// Solution in XQuery:

// <section_count>{ count(doc("book.xml")//section) }</section_count>, 
// <figure_count>{ count(doc("book.xml")//figure) }</figure_count> 

// Expected Result:

// <section_count>7</section_count>
// <figure_count>3</figure_count> 




import jtom.runtime.xml.*;
import jtom.adt.tnode.*;
import jtom.adt.tnode.types.*;
import aterm.*;

import java.util.*; 


public class UC2_3 
{

  %include {TNode.tom}
  private XmlTools xtools;

  private Factory getTNodeFactory() 
  {
	return xtools.getTNodeFactory();
  }

  public static void main(String args[]) 
  {
	UC2_3 uc1 = new UC2_3();
	uc1.run("book.xml");
  }

  private void run(String xmlfile1) 
  {
	xtools = new XmlTools();

	TNode xmldocument1 = (TNode)xtools.convertXMLToATerm(xmlfile1); 

	String result = executeQuery(xmldocument1.getDocElem());
	System.out.println(result);	
  }

  private String executeQuery(TNode book) 
  {
	String result = "<figure_count>";
	int figurecount=0; 
	%match (TNode book) {
	  <book>(_*, node, _*)</book> -> 
	   {
		 //		 xtools = new XmlTools();
		 figurecount = figurecount + countFigure(`node); 
		 //		 xtools.printXMLFromATerm(node);
	   }
	}
	result = result + figurecount + "</figure_count>\n";	


	result = result + "<section_count>";
	int sectioncount=0; 
	%match (TNode book) {
	  <book>(_*, node, _*)</book> -> 
	   {
		 //		 xtools = new XmlTools();
		 sectioncount = sectioncount + countSection(`node); 
		 //		 xtools.printXMLFromATerm(node);
	   }
	}
	result = result + sectioncount + "</section_count>\n";	

	return result;
  }


  private int countFigure(TNode node) 
  {
	int result =0; 
	%match (TNode node) {
	  <figure></figure> -> 
	   {
		 result++;
	   }
	   <section><figure></figure></section> -> {
		 result++;
	   }
	   <section><section>other</section></section> -> {
		 result = result + countFigure(`other);
	   }
	   _ -> {
		 result = result ;
	   }
	}
	//	System.out.println("deo hieu the nao");
	return result;
  }

  private int countSection(TNode node) 
  {
	int result =0; 
	%match (TNode node) {
	  <section></section> -> {
		 result++;
	   }
	   <section>nestedsection@<section>other*</section></section> -> {
		 result = result + countSection(`nestedsection);
	   }
	}
	//	System.out.println("deo hieu the nao");
	return result;
  }


  private String calculIndent(int indentlevel)
  {
	String indent = "";
	for (int i=0; i<indentlevel; i++) {
	  indent = indent + "  ";
	}
	return indent; 
  }

  private String createCascadeXML(String openClause, String data, String closeClause, int indentLevel)
  {
	String indent = calculIndent(indentLevel); 
	String xmlString = "";
	xmlString = openClause + "\n";
	xmlString = xmlString + "  " + data + "\n"; 
	xmlString = xmlString + closeClause;
	xmlString = indentXMLBlock(xmlString, indentLevel);
	return xmlString; 
  }

  private String indentXMLBlock(String xml, int indent) 
  {
	return xml;
  }

  private String createXML(String openClause, String data, String closeClause, int indentLevel) 
  {
	String indent = calculIndent(indentLevel); 
	String xmlString = "";
	xmlString = openClause;
	xmlString = xmlString + data; 
	xmlString = xmlString + closeClause ;
	xmlString = indentXMLBlock(xmlString, indentLevel);
	return xmlString; 
  }
}



