/*
 * Created on 19 janv. 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */


/**
 * @author bertrand.tavernier
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class PersonSort2 {
   
  private Document dom;

  %include{DOMTOM.tom}

 
  public static void main (String args[]) {
    PersonSort2 person = new PersonSort2();
    person.run("person.xml");
  }

  private void run(String filename){
    try {
      dom = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(filename);
      sort(dom.getDocumentElement());
	
      Transformer transform = TransformerFactory.newInstance().newTransformer();
      StreamResult result = new StreamResult(new File("Sorted.xml"));
      transform.transform(new DOMSource(dom), result);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
    
  private void sort(Node subject) {
    %match(TNode subject) {
      r @ <_>p1@<_ Age=a1></_> p2@<_ Age=a2></_></_> -> {
        if(a1.compareTo(a2) > 0) {
          r.replaceChild(p2.cloneNode(true),p1);
          r.replaceChild(p1,p2);
          sort(r);
          return;
        }	
      }
    }
  }
  	  
}
