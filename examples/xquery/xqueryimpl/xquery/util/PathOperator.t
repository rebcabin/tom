package xquery.util; 

import jtom.runtime.xml.*;
import jtom.adt.tnode.*;
import jtom.adt.tnode.types.*;
import aterm.*;

import java.util.*;
import jtom.runtime.*;

public class PathOperator {
  %include {TNode.tom}

  private XmlTools xtools = new XmlTools();

  private Factory getTNodeFactory() 
  {
	return xtools.getTNodeFactory();
  }


  protected TNodeTester tester =null; 
  protected PathOperator nextOperator = null;

  public PathOperator(TNodeTester tester, PathOperator nextOperator)
  {
	this.tester =tester;
	this.nextOperator = nextOperator; 
  }

  protected boolean doTest(Object node) 
	throws XQueryGeneralException
  {
	return tester.doTest(node);
  }

  protected Sequence runNext(TNode node)
  {
	if (this.nextOperator ==null) {
	  HashSequence seq = new HashSequence();
	  seq.add(node);
	  return seq;
	}
	else {
	  HashSequence seq = new HashSequence();
	  %match (TNode node) {
		<_></_> -> {
		   TNodeList childList = ((TNode)node).getChildList(); 
		   for (int i=0; i<childList.getLength(); i++) {
			 Sequence s = this.nextOperator.run(childList.getTNodeAt(i));
			 seq.addAll(s);
		   }
		 }
	  }
	  return seq;
	}
  }
  

  public Sequence run(TNode subject) 
  {
	final HashSequence s=new HashSequence(); 

	try {
	  if (doTest(subject)) {
		s.addAll(runNext(subject));
	  } 
	}
	
	catch (XQueryGeneralException e) {
	  System.out.println("ERROR: xqueryGeneral exception");
	  return null; 
	}
	
	return s;
  }

}
