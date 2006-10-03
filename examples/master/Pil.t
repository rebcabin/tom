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
//package master;
//import master.pil.term.types.*;
import pil.term.types.*;

import java.util.*;

import jjtraveler.VisitFailure;
import jjtraveler.reflective.VisitableVisitor;

class Pil {
%include { mustrategy.tom }
%include { java/util/types/HashMap.tom }

  %gom {
    module Term
    imports
        int String
      
    abstract syntax
    Bool =
         | True()
         | False() 
         | Eq(e1:Expr, e2:Expr) 

    Expr = 
         | Var(name:String) 
         | Let(var:Expr, e:Expr, body:Expr) 
         | Seq(i1:Expr, i2:Expr)
         | If(cond:Bool, e1:Expr, e2:Expr) 
         | a()
         | b()
  }
  
  public final static void main(String[] args) {
    Expr p1 = `Let(Var("x"),a(), Let(Var("y"),b(),Var("x")));
    System.out.println("p1 = " + p1);
    
    System.out.println("renamed p1   = " + `BottomUp(RenameVar("x","z")).apply(p1));
    System.out.println("optimized p1 = " + `BottomUp(RemoveLet()).apply(p1));

  }

  %strategy RenameVar(n1:String,n2:String) extends Identity() {
    visit Expr {
      Var(n) -> { if(`n==n1) return `Var(n2); }
    }
  }

  %strategy RemoveLet() extends Identity() {
    visit Expr {
      Let(Var(n),expr,body) -> { 
        if(`body == `TopDown(RenameVar(n,"_"+n)).apply(`body)) {
	  return `body;
	}
      }
    }
  }


}
