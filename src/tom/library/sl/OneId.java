/*
 *
 * Copyright (c) 2000-2007, Pierre-Etienne Moreau
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
 * 
 **/
package tom.library.sl;

/**
 * <code>T(t1,...,ti,...,tN).accept(OneId(v)) = T(t1,...,ti.accept(v),...,tN)</code>
 * if <code>ti</code> is the first child that is modified.
 * <p>
 * Basic visitor combinator with one visitor argument, that applies
 * this visitor to exactly one child. If no children are visited 
 * successfully, then OneId(v) fails.
 * <p>
 * Note that side-effects of failing visits to children are not
 * undone.
 */

public class OneId extends AbstractStrategy {
  public final static int ARG = 0;

  public OneId(Strategy v) {
    initSubterm(v);
  }

  public jjtraveler.Visitable visit(jjtraveler.Visitable any) throws  jjtraveler.VisitFailure {
    int childCount = any.getChildCount();
    for (int i = 0; i < childCount; i++) {
      jjtraveler.Visitable newSubterm = visitors[ARG].visit(any.getChildAt(i));
      if (newSubterm != any.getChildAt(i)) {
        return any.setChildAt(i,newSubterm);
      } 
    } 
    return any;
  }

  /**
   *  Visits the current subject (found in the environment)
   *  and place its result in the environment.
   *  Sets the environment flag to Environment.FAILURE in case of failure
   */
  public void visit() {
    int childCount = environment.getSubject().getChildCount();
    //Visitable originalsubject = environment.getSubject();
    for(int i = 0; i < childCount; i++) {
      environment.down(i+1);
      Visitable oldSubject = environment.getSubject();
      visitors[ARG].visit();
      Visitable newSubject = environment.getSubject();
      if(environment.getStatus() == Environment.SUCCESS && oldSubject!=newSubject) {
        environment.up();
        return;
      } else {
        environment.upLocal();
        /* Forget about the failure, to try another subterm */
        environment.setStatus(Environment.SUCCESS);
        //environment.setSubject(originalsubject);
      }
    }
  }
}
