package xquery.util; 

import jtom.runtime.xml.*;
import jtom.adt.tnode.*;
import jtom.adt.tnode.types.*;
import aterm.*;

import java.util.*;
import jtom.runtime.*;

public class TNodeTool {

  %include {TNode.tom}

  private XmlTools xtools = new XmlTools();

  private Factory getTNodeFactory() 
  {
	return xtools.getTNodeFactory();
  }

//   public Sequence operatorStar(TNode subject,final TNodeTester tester, final TNodeQualifier qualifier) 
//   {
// 	HashSequence seq = new HashSequence();
		
// 	TNodeList childList = ((TNode)node).getChildList(); 
// 	for (int i=0; i<childList.getLength(); i++) {
// 	  Sequence s = this.collectData02(childList.getTNodeAt(i));
// 	  seq.addAll(s);
// 	}
// 	return seq;
//   }


//   public Sequence operatorSlashSlash(TNode subject,final TNodeTester tester, final TNodeQualifier qualifier) 
//   {
// 	GenericTraversal traversal = new GenericTraversal();
// 	final HashSequence sequence=new HashSequence(); 
	
// 	Collect1 collect = new Collect1() { 
// 		public boolean apply(ATerm t) 
// 		{ 
// 		  try {
// 			if(t instanceof TNode) { 
// 			  if (tester.doTest(t)) {
// 				sequence.addAll(qualifier.qualify(t));
// 				return true;
// 			  }
// 			} 
// 			return true; 
// 		  }
// 		  catch (XQueryGeneralException e) {
// 			System.out.println("ERROR: xqueryGeneral exception");
// 			return false; 
// 		  }
// 		} // end apply 
// 	  }; // end new 
// 	traversal.genericCollect(subject, collect); 
	
	
//     return sequence;
//   }

  
  public Sequence distinctValues(Sequence seq, Comparator comparator, TNodeTester tester, TNodeQualifier qualifier)
	throws XQueryGeneralException
  {

	SequenceTool sequencetool = new SequenceTool (); 
	Sequence output = new Sequence(); 
	Enumeration enum = seq.elements(); 
	
	while (enum.hasMoreElements()) {
	  Object obj = (enum.nextElement());

	  if (tester.doTest(obj)) {
		if (!sequencetool.contain(output, obj, comparator)) {
		  output.add(obj);
		}
	  }
	}

	Sequence result =  new Sequence(); 
	enum = output.elements();
	while (enum.hasMoreElements()) {
	  TNode obj = (TNode)(enum.nextElement());
	  result.addAll(qualifier.qualify(obj));
	}
	return result;
  }

//   public Sequence operatorSlash(TNode subject,final TNodeTester tester,final TNodeQualifier qualifier) 
//   {
// 	try {
// 	//return null;
// 	  Sequence seq=new Sequence(); 
// 	  %match (TNode subject) {
// 		<_></_> -> {
// 		   if (tester.doTest(subject)) {
// 			 seq.addAll(qualifier.qualify(subject));
// 		   }
// 		 }
// 	  }
	  
// 	  return seq;
// 	}
// 	catch (XQueryGeneralException e) {
// 	  System.out.println("ERROR: xqueryGeneral exception");
// 	  return null; 
// 	}
//   }

  public void printResult(QueryRecordSet queryRecordSet, RecordPrinter printer) 
	throws XQueryGeneralException
  {
	Enumeration enum = queryRecordSet.elements(); 
	while (enum.hasMoreElements()) {
	  QueryRecord record = (QueryRecord)(enum.nextElement());
	  printer.print(record);
	  
	}
  }


  public boolean endWith(TNode node, String endwith) 
  {
	if (node.hasName()) {
	  String name=node.getName();
	  int location = name.lastIndexOf(endwith);
	  if (location==-1) {
		return false; 
	  }
	  else {
		if ((location + endwith.length()) == name.length()) {
		  return true;
		}
	  }
	  
	}
	return false;
  }


  public boolean contains(TNode node,  String str) 
  {	
	boolean result = false; 
	
	if (node.isTextNode()) {
	  String text = node.getData();
	  if (text.indexOf(str) != -1) {
		return true; 
	  }
	}

	else if (node.hasChildList()) {
	  TNodeList nodelist= node.getChildList();
	  int len = nodelist.getLength();
	  for(int i=0; i<len; i++) {
		TNode onenode = nodelist.getTNodeAt(i);
		if (this.contains(onenode, str)) 
		  return true; 
	  }
	}
	else {
	  return false;
	}

	return false;
  }


}
