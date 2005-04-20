 /*
  * Copyright (c) 2004-2005, INRIA
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


package minirho;

import aterm.*;
import aterm.pure.*;
import minirho.rho.rhoterm.*;
import minirho.rho.rhoterm.types.*;

import jjtraveler.reflective.VisitableVisitor;
import jjtraveler.VisitFailure;

import tom.library.strategy.mutraveler.reflective.AbstractVisitableVisitor;
import jjtraveler.Visitable;
import jjtraveler.reflective.VisitableVisitor;
import jjtraveler.VisitFailure;

 public class Rho {
     private rhotermFactory factory;

     public Rho(rhotermFactory factory) {
	 this.factory = factory;
     }
     public rhotermFactory getRhotermFactory() {
	 return factory;
     }


     %include { mutraveler.tom }

     
     %vas{
	 module rhoterm
	     imports 
	     public
	     sorts RTerm Constraint Subst ListConstraint ListSubst
	     abstract syntax
	     var(na:String) -> RTerm
	     const(na:String) -> RTerm
	     stk() -> RTerm
	     abs(lhs:RTerm,rhs:RTerm) -> RTerm
	     app(lhs:RTerm,rhs:RTerm) -> RTerm
	     struct(lhs:RTerm,rhs:RTerm) -> RTerm //structure is couple
	     appC(co:ListConstraint,term:RTerm) -> RTerm
	     appS(su:ListSubst,term:RTerm) -> RTerm

	     andC( Constraint* ) -> ListConstraint
	     andS( Subst* ) -> ListSubst
	     match(lhs:RTerm,rhs:RTerm) -> Constraint 
	     matchKO() -> Constraint
	     eq(var:RTerm,rhs:RTerm) -> Subst 

	     }  

     public class Sequence_abs extends AbstractVisitableVisitor {
	 protected final static int FIRST = 0;
	 protected final static int THEN = 1;
	 public Sequence_abs(VisitableVisitor first, VisitableVisitor then) {
	     init(first,then);
	 }
	 
	 public Sequence_abs(VisitableVisitor v1, VisitableVisitor v2, VisitableVisitor v3) {
	     init(v1,new Sequence_abs(v2, v3));
	 }
	 
	 public Visitable visit(Visitable any) throws VisitFailure {
	     RTerm v = (RTerm)getArgument(FIRST).visit(any);
	     %match(RTerm v){
		 abs[] -> {return v;}
		 _ -> {return getArgument(THEN).visit(v);}

	     }
		  
	 }
     }
     public class One_abs extends AbstractVisitableVisitor {
	 public One_abs(VisitableVisitor v) {
	     init(v);
	 }
	 
	 public Visitable visit(Visitable any) throws VisitFailure {
	     int childCount = any.getChildCount();
	     if (any instanceof RTerm) {
		 %match(RTerm any){
		     abs[] -> { throw new VisitFailure();}// return any;}
		 } 
	     }
	     for (int i = 0; i < childCount; i++) {
		 try {
		     return any.setChildAt(i,getArgument(0).visit(any.getChildAt(i)));
		 } catch(VisitFailure f) { }
	     }
	     throw new VisitFailure();
	 }
	 
     }
     
     %op VisitableVisitor One_abs(VisitableVisitor) {
	 fsym {}
	 is_fsym(t) { (t instanceof One_abs) }
	 make(v) { new One_abs((VisitableVisitor)v) }
     }

     %op VisitableVisitor Sequence_abs(VisitableVisitor, VisitableVisitor) {
	 fsym {}
	 is_fsym(t) { (t instanceof Sequence_abs) }
	 make(first,then) { new Sequence_abs((VisitableVisitor)first,(VisitableVisitor)then) }
     }
     
     %op VisitableVisitor Repeat_abs(VisitableVisitor) {
	 fsym {}
	 make(v) { `mu(MuVar("x"),Choice(Sequence_abs(v,MuVar("x")),Identity())) }
     }
     VisitableVisitor rules = new ReductionRules();
     VisitableVisitor print = new Print();
     VisitableVisitor myStrategy = `Repeat_abs(mu(MuVar("x"),Choice(rules,One_abs(MuVar("x")))));
     public final static void main(String[] args) {
	 Rho rhoEngine = new Rho(rhotermFactory.getInstance(new PureFactory(16)));
	 rhoEngine.run();
     }
     public void run(){
      	 RTerm subject;
	 String s;
	 System.out.println(" ******************************************************************\n RomCal: an implementation of the explicit rho-calculus in Tom\\n by Germain Faure and ...\n version 0.1. Devolp in few hours.Please use it with care \n ******************************************************************");
	 while(true){
	     
 	     System.out.print("RomCal>");
 	     s = Clavier.lireLigne();
 	     subject = factory.RTermFromString(s);
	     try{
		 System.out.println(myStrategy.visit(subject));
	     } catch (VisitFailure e) {
		 System.out.println("reduction failed on: " + subject);
	     }
	 }
     }
     
     public String test(String s){
	 RTerm subject = factory.RTermFromString(s);
	 try{
	     return "" + (myStrategy.visit(subject));
	 } catch (VisitFailure e) {
	     return ("reduction failed on: " + subject);
	 }
	 
     }
     public VisitableVisitor mu(VisitableVisitor var, VisitableVisitor v) {
	 return tom.library.strategy.mutraveler.MuVar.mu(var,v);
     }
     
     class Print extends rhotermVisitableFwd {
	 public Print() {
	     super(`Fail());
	 }
	 public RTerm visit_RTerm(RTerm arg) throws  VisitFailure { 
	     System.out.println("|-->>" + arg);
	     return arg;
	 }
     }
     class ReductionRules extends rhotermVisitableFwd {
	 public ReductionRules() {
	     super(`Fail());
	 }
	 public RTerm visit_RTerm(RTerm arg) throws  VisitFailure { 
	     %match(RTerm arg){
		 /*NORMALISATION FAIBLE*/
		 //		 		 abs[] -> {return arg;}

		 /*Compose */
		 appS(phi@andS(l*),appS(andS(L*),N)) -> {
		     ListSubst result = `mapS(((ListSubst)(L.reverse())),phi,andS());
		     return `appS(andS(l*,result*),N);}
		 //ATTENTION AU CAS n is 0
		 //ALPHA-CONV!!

 
		 /* Garbage collector */
		 appC((_*,matchKO(),_*),_) -> {return `stk();}
		 app(stk(),A) -> {return `stk();}
		 struct(A,stk()) -> {return `A;}
		 struct(stk(),A) -> {return `A;}
		 
		 
		 /*Rho*/
		 app(abs(P,M),N) -> {return `appC(andC(match(P,N)),M);}

		 /*Delta*/
		 app(struct(M1,M2),N) -> {return `struct(app(M1,N),app(M2,N));}

		 /*ToSubst*/
		 appC(andC(C*,match(X@var[],M),D*),N) -> {return `appC(andC(C*,D*),appS(andS(eq(X,M)),N));}
		 //PATTERNS LINEAIRES

		 /*Id*/ 
		 appC(andC(),M) -> {return `M;}

		 /*Replace*/
		 appS(andS(_*,eq(var(X),M),_*),var(X)) -> {return `M;}

		 /*Var*/
		 appS(_,Y@var[]) -> {return `Y;}
		 //cette regle est correcte sans condition de bord si
		 //on sait que on essaye toujours d'appliquer la regle
		 //Replace avant		

		 /*Const*/
		 appS(_,c@const[]) -> {return `c;}

		 /*Abs*/
		 appS(phi,abs(P,M)) -> {return `abs(P,appS(phi,M));}
		 //ALPHA-CONVERSION NECESSAIRE!


		 /*App*/
		 appS(phi,app(M,N)) -> {return `app(appS(phi,M),appS(phi,N));}

		 /*Struct*/
		 appS(phi,struct(M,N)) -> {return `struct(appS(phi,M),appS(phi,N));}

		 /*Constraint*/
		 appS(phi,appC(andC(L*),M)) -> {
		     ListConstraint result = `mapC(((ListConstraint)(L.reverse())),phi,andC());
		     return `appC(andC(result*),appS(phi,M));}
		 //ATTENTION AU CAS n is 0
		 //ALPHA-CONV!!


		 
		 /*ENCAPSULATIONS DES REGLES SUR LES CONTRAINTES */ 
		 appC((X*,match(f@const[],f),Y*),M) -> {return `appC(andC(X*,Y*),M);}
		 //si j'arrive dans la regle suivant c'est que les const sont diff

		 appC((X*,match(const[],const[]),Y*),M) -> {return `appC(andC(X*,matchKO(),Y*),M);}

		 l:appC((X*,m@match(app[],app[]),Y*),M)|appC((X*,m@match(app[],const[]),Y*),M)|appC((X*,m@match(const[],app[]),Y*),M) -> {
		     ListConstraint head_is_constant = `headIsConstant(m);
		     %match(ListConstraint head_is_constant){
			 (match[]) -> {
			     break l;
			 }
			 (matchKO()) -> {
			     return `appC(andC(X*,matchKO(),Y*),M);
			 }
		     }
		     ListConstraint result = `computeMatch(andC(m));
		     return `appC(andC(X*,result*,Y*),M);
		 }
		 appC((X*,match(struct(M1,M2),struct(N1,N2)),Y*),M) ->{
		     return `appC(andC(X*,match(M1,N1),match(M2,N2),Y*),M);}

	     }	

	     // return arg;
	          throw new VisitFailure();
	 }
// 	 public ListConstraint visit_ListConstraint(ListConstraint l) throws VisitFailure {
// 	     %match(ListConstraint l){
// 		 /*Decompose Struct */
// 		 (X*,match(struct(M1,M2),struct(N1,N2)),Y*) ->{
// 		     return `andC(X*,match(M1,N1),match(M2,N2),Y*);}

// 		 /* Patterns lineaires, pas besoin de la regle Idem */

// 		 /* Decompose Algebriques n = 0 */
// 		 (X*,match(f@const[],f),Y*) -> {return `andC(X*,Y*);}

// 		 /* Decompose Algebriques n > 0 */
	
		
// 	    }
// 	    throw new VisitFailure();
// 	}

// 	 public RTerm visit_RTerm_Abs(RT arg) throws VisitFailure{
// 	     return arg;
// 	 }
    }
     private ListConstraint headIsConstant (Constraint l){
	 %match(Constraint l){
	     match(app(A1,B1),app(A2,B2)) ->{ 
		 return `headIsConstant(match(A1,A2));
	     }
	     match(const(f),const(f)) -> {
		 return `andC();
	     }
	     //si j'arrive dans le cas de la regle suivante alors les constantes sont forcement differentes
	     match(const[],const[])   -> {
		 return `andC(matchKO());
	     }
	     match(const[],app[]) | match(app[],const[]) -> {
		 return `andC(matchKO());
	     }
	     _ -> {return `andC(l);}
	 }

     }
     private ListConstraint computeMatch(ListConstraint l){
	 %match(ListConstraint l){
	     (match(app(f@const[],A),app(f,B))) -> {return `andC(match(A,B));}
	     (match(app(A1,B1),app(A2,B2))) -> {
		 ListConstraint result = `computeMatch(andC(match(A1,A2)));
		 return `andC(result*,match(B1,B2));}
	     _ -> {return `l;}
	 }
     }
     public ListConstraint mapC(ListConstraint list, ListSubst phi, ListConstraint result){
 	%match(ListConstraint list) {
 	    (match(P,M),_*) ->{
 		return `mapC(list.getTail(),phi,andC(match(P,appS(phi,M)),result*));}
 	    _ -> {return `result;}
 	}	
     }    
     public ListSubst mapS(ListSubst list, ListSubst phi, ListSubst result){
 	%match(ListSubst list) {
 	    (eq(X,M),_*) ->{
 		return `mapS(list.getTail(),phi,andS(eq(X,appS(phi,M)),result*));}
 	    _ -> {return `result;}
 	}	
     }    
     }
 
