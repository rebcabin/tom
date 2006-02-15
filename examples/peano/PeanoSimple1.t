/*
 * Copyright (c) 2004-2006, INRIA
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

import aterm.*;
import aterm.pure.*;

public class PeanoSimple1 {

  ATermFactory factory;
  AFun fzero, fsuc;
  ATerm tzero;

  public PeanoSimple1(ATermFactory factory) {
    this.factory = factory;
    fzero = factory.makeAFun("zero", 0, false);
    fsuc  = factory.makeAFun("suc" , 1, false);
    tzero = factory.makeAppl(fzero);
  }


  %typeterm term {
    implement { ATerm }
  }

  %op term zero() {
    is_fsym(t) { (((ATermAppl)t).getAFun())==fzero }
  }
  
  %op term suc(p:term) {
    is_fsym(t) { (((ATermAppl)t).getAFun())==fsuc }
    get_slot(p,t) { (((ATermAppl)t).getArgument(0)) }
  }

  %op term zoro(p:term) {
    is_fsym(t) { (((ATermAppl)t).getAFun())== factory.makeAFun("zoro" , 1, false) }
    get_slot(p,t) { (((ATermAppl)t).getArgument(0)) }
    make(p) {zoro(p)}
  }

  public ATerm zoro(ATerm t) {
    return suc(t);
  }

  public ATerm suc(ATerm t) {
    return factory.makeAppl(fsuc,t);
  }
  
  public ATerm plus(ATerm t1, ATerm t2) {
    %match(term t1, term t2) {
      x,zero() -> { return `x; }
      x,suc(y) -> { return suc(plus(`x,`y)); }
    }
    return null;
  }

  public void run(int n) {
    ATerm z = `zoro(tzero);
    %match(term z) {
      zoro(tadam) -> { System.out.println(`tadam); }
    }

    ATerm N = tzero;
    for(int i=0 ; i<n ; i++) {
      N = suc(N);
    }
    ATerm res = plus(N,N);
    System.out.println("plus(" + n + "," + n + ") = " + res);
  }

  public final static void main(String[] args) {
    PeanoSimple1 test = new PeanoSimple1(new PureFactory());
    test.run(10);
  }

}

