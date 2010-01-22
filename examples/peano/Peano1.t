/*
 * Copyright (c) 2004-2010, INPL, INRIA
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
 * 	- Neither the name of the INRIA nor the names of its
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

package peano;

import peano.peano.*;
import peano.peano.types.*;

public class Peano1 {
  %include { peano/Peano.tom }
  
  public static Nat plus(Nat t1, Nat t2) {
    %match(t2) {
      zero() -> { return `t1; }
      suc(y) -> { return `suc(plus(t1,y)); }
    }
    return null;
  }

  static Nat fib(Nat t) {    
    %match(t) {   
      zero()      -> { return `suc(zero()); }   
      suc(zero()) -> { return `suc(zero()); }   
      suc(suc(x)) -> { return `plus(fib(x),fib(suc(x))); }    
    }   
    return null;    
  }   

  public final static void main(String[] args) {    
    int n = 10;   
    Nat N = `zero();    
    for(int i=0 ; i<n ; i++) {    
      N = `suc(N);    
    }   

    Nat res = fib(N);   
    System.out.println("fib(" + n + ") =  " + res);   
  }   

}
