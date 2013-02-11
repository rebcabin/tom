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

package poly;

import poly.poly.types.*;

public class PolySimple1 {

%include { poly/Poly.tom }

  public Term differentiate(Term p) {
    %match(Term p) {
      a()             -> { return `zero(); }
      X()             -> { return `one(); }
      plus(arg1,arg2) -> { return `plus(differentiate(arg1),differentiate(arg2)); }
      mult(arg1,arg2) -> { 
        Term res1 = `mult(arg1, differentiate(arg2));
        Term res2 = `mult(arg2, differentiate(arg1));
        return `plus(res1,res2);
      }
      _ -> { System.out.println("No match for: "+p); }
	    
    }
    return null;
  }
    
  public void run() {
    Term t = `mult(X(),plus(X(), a()));
    Term res = null;
	
    res = differentiate(t);
    System.out.println("Derivative form of " + t +" =\n\t" + res);
  }
    
  public final static void main(String[] args) {
    PolySimple1 test = new  PolySimple1();
    test.run();
  }
}
