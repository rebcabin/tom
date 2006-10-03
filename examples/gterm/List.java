/*
 * Copyright (c) 2004-2006, INRIA
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *  - Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  - Neither the name of the INRIA nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
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
package gterm;

public abstract class List extends AbstractType {

  public AbstractType accept(Visitor v) throws jjtraveler.VisitFailure {
    return v.visit_List(this);
  }

	public boolean isEmpty() {
		return false;
	}
  
	public boolean isCons() {
		return false;
	}
	
	public boolean isConsInt() {
		return false;
	}

	public Element getHead() {
    throw new UnsupportedOperationException("This List has no head");
	}

	public List getTail() {
    throw new UnsupportedOperationException("This List has no tail");
	}
	
	public int getHeadInt() {
    throw new UnsupportedOperationException("This List has no headint");
	}
	
  public static List fromTerm(aterm.ATerm trm) {
    List tmp;
    tmp = Empty.fromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    tmp = Cons.fromTerm(trm);
    if (tmp != null) {
      return tmp;
    }
    
		tmp = ConsInt.fromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    throw new IllegalArgumentException("This is not a List: " + trm);
  }
}
