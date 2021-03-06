package lemu2.kernel.evaluation;

import tom.library.sl.*;

import lemu2.kernel.proofterms.types.*;
import lemu2.kernel.proofterms.types.namelist.nameList;
import lemu2.kernel.proofterms.types.conamelist.conameList;
import lemu2.kernel.*;
import lemu2.util.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Collection;

public class Urban {

  %include { kernel/proofterms/proofterms.tom } 
  %include { sl.tom } 


  private static ProofTerm substName(ProofTerm pt, Name n1, CoName cn1, Prop prop, ProofTerm P) {
    %match(pt, Name n1) {
      ax(n,cn), n -> {
        return U.`reconame(P,cn1,cn);
      }
      falseL(n), n -> {
        return `cut(
            CutPrem1(cn1,prop,P),
            CutPrem2(n1,prop,falseL(n1)));
      }
      andL(AndLPrem1(x,px,y,py,M),n), n -> {
        return `cut(
            CutPrem1(cn1,prop,P),
            CutPrem2(n1,prop,andL(
                AndLPrem1(x,px,y,py,substName(M,n1,cn1,prop,P)),n1)));
      }
      orL(OrLPrem1(x,px,M1),OrLPrem2(y,py,M2),n), n -> {
        return `cut(
            CutPrem1(cn1,prop,P),
            CutPrem2(n1,prop,orL(
                OrLPrem1(x,px,substName(M1,n1,cn1,prop,P)),
                OrLPrem2(y,py,substName(M2,n1,cn1,prop,P)),n1)));
      }
      implyL(ImplyLPrem1(x,px,M1),ImplyLPrem2(a,pa,M2),n), n -> {
        return `cut(
            CutPrem1(cn1,prop,P),
            CutPrem2(n1,prop,implyL(
                ImplyLPrem1(x,px,substName(M1,n1,cn1,prop,P)),
                ImplyLPrem2(a,pa,substName(M2,n1,cn1,prop,P)),n1)));
      }
      forallL(ForallLPrem1(x,px,M),t,n), n -> {
        return `cut(
            CutPrem1(cn1,prop,P),
            CutPrem2(n1,prop,forallL(
                ForallLPrem1(x,px,substName(M,n1,cn1,prop,P)),t,n1))); 
      }
      existsL(ExistsLPrem1(x,px,fx,M),n), n -> {
        return `cut(
            CutPrem1(cn1,prop,P),
            CutPrem2(n1,prop,existsL(
                ExistsLPrem1(x,px,fx,substName(M,n1,cn1,prop,P)),n1))); 
      }
      foldL(id,FoldLPrem1(x,px,M),n), n -> {
        return `cut(
            CutPrem1(cn1,prop,P),
            CutPrem2(n1,prop,foldL(id,
                FoldLPrem1(x,px,substName(M,n1,cn1,prop,P)),n1)));
      }
    }
    %match(pt) { // if introduced name different from n1
      ax(n,cn) -> {
        return `ax(n,cn); 
      }
      cut(CutPrem1(a,pa,M1),CutPrem2(x,px,M2)) -> {
        return `cut(
            CutPrem1(a,pa,substName(M1,n1,cn1,prop,P)),
            CutPrem2(x,px,substName(M2,n1,cn1,prop,P)));
      }
      // left rules
      falseL(n) -> {
        return `falseL(n);
      }
      andL(AndLPrem1(x,px,y,py,M),n) -> {
        return `andL(
            AndLPrem1(x,px,y,py,substName(M,n1,cn1,prop,P)),n); 
      }
      orL(OrLPrem1(x,px,M1),OrLPrem2(y,py,M2),n) -> {
        return `orL(
            OrLPrem1(x,px,substName(M1,n1,cn1,prop,P)),
            OrLPrem2(y,py,substName(M2,n1,cn1,prop,P)),n);
      }
      implyL(ImplyLPrem1(x,px,M1),ImplyLPrem2(a,pa,M2),n) -> {
        return `implyL(
            ImplyLPrem1(x,px,substName(M1,n1,cn1,prop,P)),
            ImplyLPrem2(a,pa,substName(M2,n1,cn1,prop,P)),n);
      }
      forallL(ForallLPrem1(x,px,M),t,n) -> {
        return `forallL(
            ForallLPrem1(x,px,substName(M,n1,cn1,prop,P)),t,n);
      }
      existsL(ExistsLPrem1(x,px,fx,M),n) -> {
        return `existsL(
            ExistsLPrem1(x,px,fx,substName(M,n1,cn1,prop,P)),n);
      }
      foldL(id,FoldLPrem1(x,px,M),n) -> {
        return `foldL(id,
            FoldLPrem1(x,px,substName(M,n1,cn1,prop,P)),n);
      }
      // right rules
      orR(OrRPrem1(a,pa,b,pb,M),cn) -> {
        return `orR(
            OrRPrem1(a,pa,b,pb,substName(M,n1,cn1,prop,P)),cn);
      }
      andR(AndRPrem1(a,pa,M1),AndRPrem2(b,pb,M2),cn) -> {
        return `andR(
            AndRPrem1(a,pa,substName(M1,n1,cn1,prop,P)),
            AndRPrem2(b,pb,substName(M2,n1,cn1,prop,P)),cn);
      }
      implyR(ImplyRPrem1(x,px,a,pa,M),cn) -> {
        return `implyR(
            ImplyRPrem1(x,px,a,pa,substName(M,n1,cn1,prop,P)),cn);
      }
      existsR(ExistsRPrem1(a,pa,M),t,cn) -> {
        return `existsR(
            ExistsRPrem1(a,pa,substName(M,n1,cn1,prop,P)),t,cn);
      }
      forallR(ForallRPrem1(a,pa,fx,M),cn) -> {
        return `forallR(
            ForallRPrem1(a,pa,fx,substName(M,n1,cn1,prop,P)),cn);
      }
      foldR(id,FoldRPrem1(a,pa,M),cn) -> {
        return `foldR(id,
            FoldRPrem1(a,pa,substName(M,n1,cn1,prop,P)),cn);
      }
    }
    throw new RuntimeException("non exhaustive patterns");
  }

  private static ProofTerm substCoName(ProofTerm pt, CoName cn1, Name n1, Prop prop, ProofTerm P) {
    %match(pt, CoName cn1) {
      ax(n,cn), cn -> {
        return U.`rename(P,n1,n);
      }
      orR(OrRPrem1(a,pa,b,pb,M),cn), cn -> {
        return `cut(
            CutPrem1(cn1,prop,orR(
                OrRPrem1(a,pa,b,pb,substCoName(M,cn1,n1,prop,P)),cn1)),
            CutPrem2(n1,prop,P));
      }
      andR(AndRPrem1(a,pa,M1),AndRPrem2(b,pb,M2),cn), cn -> {
        return `cut(
            CutPrem1(cn1,prop,andR(
                AndRPrem1(a,pa,substCoName(M1,cn1,n1,prop,P)),
                AndRPrem2(b,pb,substCoName(M2,cn1,n1,prop,P)),cn1)),
            CutPrem2(n1,prop,P));
      }
      implyR(ImplyRPrem1(x,px,a,pa,M),cn), cn -> {
        return `cut(
            CutPrem1(cn1,prop,implyR(
                ImplyRPrem1(x,px,a,pa,substCoName(M,cn1,n1,prop,P)),cn1)),
            CutPrem2(n1,prop,P));
      }
      existsR(ExistsRPrem1(a,pa,M),t,cn), cn -> {
        return `cut(
            CutPrem1(cn1,prop,existsR(
                ExistsRPrem1(a,pa,substCoName(M,cn1,n1,prop,P)),t,cn1)),
            CutPrem2(n1,prop,P));
      }
      forallR(ForallRPrem1(a,pa,fx,M),cn), cn -> {
        return `forallR(
            ForallRPrem1(a,pa,fx,substCoName(M,cn1,n1,prop,P)),cn);
      }
      foldR(id,FoldRPrem1(a,pa,M),cn), cn -> {
        return `cut(
            CutPrem1(cn1,prop,foldR(id,
                FoldRPrem1(a,pa,substCoName(M,cn1,n1,prop,P)),cn1)),
            CutPrem2(n1,prop,P));
      }
    }
    %match(pt) { // if introduced coname different from cn1
      ax(n,cn) -> {
        return `ax(n,cn); 
      }
      cut(CutPrem1(a,pa,M1),CutPrem2(x,px,M2)) -> {
        return `cut(
            CutPrem1(a,pa,substCoName(M1,cn1,n1,prop,P)),
            CutPrem2(x,px,substCoName(M2,cn1,n1,prop,P)));
      }
      // left rules
      falseL(n) -> {
        return `falseL(n);
      }
      andL(AndLPrem1(x,px,y,py,M),n) -> {
        return `andL(
            AndLPrem1(x,px,y,py,substCoName(M,cn1,n1,prop,P)),n); 
      }
      orL(OrLPrem1(x,px,M1),OrLPrem2(y,py,M2),n) -> {
        return `orL(
            OrLPrem1(x,px,substCoName(M1,cn1,n1,prop,P)),
            OrLPrem2(y,py,substCoName(M2,cn1,n1,prop,P)),n);
      }
      implyL(ImplyLPrem1(x,px,M1),ImplyLPrem2(a,pa,M2),n) -> {
        return `implyL(
            ImplyLPrem1(x,px,substCoName(M1,cn1,n1,prop,P)),
            ImplyLPrem2(a,pa,substCoName(M2,cn1,n1,prop,P)),n);
      }
      forallL(ForallLPrem1(x,px,M),t,n) -> {
        return `forallL(
            ForallLPrem1(x,px,substCoName(M,cn1,n1,prop,P)),t,n);
      }
      existsL(ExistsLPrem1(x,px,fx,M),n) -> {
        return `existsL(
            ExistsLPrem1(x,px,fx,substCoName(M,cn1,n1,prop,P)),n);
      }
      foldL(id,FoldLPrem1(x,px,M),n) -> {
        return `foldL(id,
            FoldLPrem1(x,px,substCoName(M,cn1,n1,prop,P)),n);
      }
      // right rules
      orR(OrRPrem1(a,pa,b,pb,M),cn) -> {
        return `orR(
            OrRPrem1(a,pa,b,pb,substCoName(M,cn1,n1,prop,P)),cn);
      }
      andR(AndRPrem1(a,pa,M1),AndRPrem2(b,pb,M2),cn) -> {
        return `andR(
            AndRPrem1(a,pa,substCoName(M1,cn1,n1,prop,P)),
            AndRPrem2(b,pb,substCoName(M2,cn1,n1,prop,P)),cn);
      }
      implyR(ImplyRPrem1(x,px,a,pa,M),cn) -> {
        return `implyR(
            ImplyRPrem1(x,px,a,pa,substCoName(M,cn1,n1,prop,P)),cn);
      }
      existsR(ExistsRPrem1(a,pa,M),t,cn) -> {
        return `existsR(
            ExistsRPrem1(a,pa,substCoName(M,cn1,n1,prop,P)),t,cn);
      }
      forallR(ForallRPrem1(a,pa,fx,M),cn) -> {
        return `forallR(
            ForallRPrem1(a,pa,fx,substCoName(M,cn1,n1,prop,P)),cn);
      }
      foldR(id,FoldRPrem1(a,pa,M),cn) -> {
        return `foldR(id,
            FoldRPrem1(a,pa,substCoName(M,cn1,n1,prop,P)),cn);
      }
    }
    throw new RuntimeException("non exhaustive patterns");
  }

  %typeterm PTCollection { implement { java.util.Collection<ProofTerm> } }

  private static ProofTerm subst(Position w, ProofTerm orig, ProofTerm st) {
    try { return (ProofTerm) w.getReplace(st).visit(orig); }
    catch (VisitFailure e) { throw new RuntimeException("never happens"); }
  }

  %strategy RStep(c:PTCollection, last:ProofTerm) extends Identity() {
    visit ProofTerm {
      // commuting cut -- non determinism
      cut(CutPrem1(a,pa,M1),CutPrem2(x,px,M2)) -> {
        if (!U.`freshlyIntroducedCoName(M1,a)) { 
          c.add(subst(getPosition(),last,
                `substCoName(M1,a,x,pa,M2)));
        }
        if (!U.`freshlyIntroducedName(M2,x)) {
          c.add(subst(getPosition(),last,
                `substName(M2,x,a,px,M1)));
        }
      }
      // axiom cuts -- non determinism 
      cut(CutPrem1(a,_,M),CutPrem2(x,_,ax(x,b))) -> {
        if (U.`freshlyIntroducedCoName(M,a)) {
          c.add(subst(getPosition(),last,
                U.`reconame(M,a,b)));
        }
      }
      cut(CutPrem1(a,_,ax(y,a)),CutPrem2(x,_,M)) -> {
        if (U.`freshlyIntroducedName(M,x)) { 
          c.add(subst(getPosition(),last,
                U.`rename(M,x,y)));
        }
      }
      // top and bottom cuts
      cut(CutPrem1(a,_,trueR(a)),CutPrem2(x,_,M)) -> {
        if (U.`freshlyIntroducedName(M,x)) {
          c.add(subst(getPosition(),last,`M));
        }
      }
      cut(CutPrem1(a,_,M),CutPrem2(x,_,falseL(x))) -> {
        if (U.`freshlyIntroducedCoName(M,a)) {
          c.add(subst(getPosition(),last,`M));
        }
      }
      // => cut -- non determinism
      cut(
          CutPrem1(b,_,p1@implyR(ImplyRPrem1(x,px,a,pa,M),b)),
          CutPrem2(z,_,p2@implyL(ImplyLPrem1(y,py,P),ImplyLPrem2(c,pc,N),z))) -> {
        if (U.`freshlyIntroducedCoName(p1,b) && U.`freshlyIntroducedName(p2,z)) {
          c.add(subst(getPosition(),last,
                `cut(CutPrem1(a,pa,cut(CutPrem1(c,pc,N),CutPrem2(x,px,M))),CutPrem2(y,py,P))));
          c.add(subst(getPosition(),last,
                `cut(CutPrem1(c,pc,N),CutPrem2(x,px,cut(CutPrem1(a,pa,M),CutPrem2(y,py,P))))));
        }
      }
      // /\ cut -- non determinism
      cut(
          CutPrem1(a,_,p1@andR(AndRPrem1(b,pb,M1),AndRPrem2(c,pc,M2),a)),
          CutPrem2(x,_,p2@andL(AndLPrem1(y,py,z,pz,N),x))) -> {
        if (U.`freshlyIntroducedCoName(p1,a) && U.`freshlyIntroducedName(p2,x)) {
          c.add(subst(getPosition(),last,
                `cut(CutPrem1(b,pb,M1),CutPrem2(y,py,cut(CutPrem1(c,pc,M2),CutPrem2(z,pz,N))))));
          c.add(subst(getPosition(),last,
                `cut(CutPrem1(c,pc,M2),CutPrem2(z,pz,cut(CutPrem1(b,pb,M1),CutPrem2(y,py,N))))));
        }
      }
      // \/ cut -- non determinism
      cut(
          CutPrem1(a,_,p1@orR(OrRPrem1(b,pb,c,pc,M),a)),
          CutPrem2(x,_,p2@orL(OrLPrem1(y,py,N1),OrLPrem2(z,pz,N2),x))) -> {
        if (U.`freshlyIntroducedCoName(p1,a) && U.`freshlyIntroducedName(p2,x)) {
          c.add(subst(getPosition(),last,
                `cut(CutPrem1(b,pb,cut(CutPrem1(c,pc,M),CutPrem2(z,pz,N2))),CutPrem2(y,py,N1))));
          c.add(subst(getPosition(),last,
                `cut(CutPrem1(c,pc,cut(CutPrem1(b,pb,M),CutPrem2(y,py,N1))),CutPrem2(z,pz,N2))));
        }
      }
      // first-order cuts
      cut(
          CutPrem1(b,_,p1@existsR(ExistsRPrem1(a,pa,M),t,b)),
          CutPrem2(y,_,p2@existsL(ExistsLPrem1(x,px,fx,N),y))) -> {
        if (U.`freshlyIntroducedCoName(p1,b) && U.`freshlyIntroducedName(p2,y))
          c.add(subst(getPosition(),last,
                `cut(CutPrem1(a,pa,M),CutPrem2(x,U.substFoVar(px,fx,t),U.substFoVar(N,fx,t)))));

      }
      cut(
          CutPrem1(b,_,p1@forallR(ForallRPrem1(a,pa,fx,M),b)),
          CutPrem2(y,_,p2@forallL(ForallLPrem1(x,px,N),t,y))) -> {
        if (U.`freshlyIntroducedCoName(p1,b) && U.`freshlyIntroducedName(p2,y)) {
          c.add(subst(getPosition(),last,
                `cut(CutPrem1(a,U.substFoVar(pa,fx,t),U.substFoVar(M,fx,t)),CutPrem2(x,px,N))));
        }
      }
      // fold cuts
      cut(
          CutPrem1(a,_,p1@foldR(id,FoldRPrem1(b,pb,M),a)),
          CutPrem2(x,_,p2@foldL(id,FoldLPrem1(y,py,N),x))) -> {
        if (U.`freshlyIntroducedCoName(p1,a) && U.`freshlyIntroducedName(p2,x)) {
          c.add(subst(getPosition(),last,
                `cut(CutPrem1(b,pb,M),CutPrem2(y,py,N))));
        }
      }
    }
  }


  static class Tree {
    public ProofTerm pt;
    public Tree parent;
    public Tree(ProofTerm a, Tree b) { pt = a; parent = b;}
    public LinkedList<ProofTerm> chainback() {
      LinkedList<ProofTerm> res = new LinkedList<ProofTerm>();
      Tree t = this;
      do {
        res.add(t.pt);
        t = t.parent;
      } while (t != null);
      java.util.Collections.reverse(res);
      return res;
    }
    public LinkedList<ProofTerm> checkloop() {
      LinkedList<ProofTerm> res = new LinkedList<ProofTerm>();
      res.add(this.pt);
      Tree t = this.parent;
      while(t != null) {
        res.add(t.pt);
        if (t.pt.equals(this.pt)) {
          java.util.Collections.reverse(res);
          return res;
        } else {
          t = t.parent;
        }
      } 
      return null;
    }
  }

  public static LinkedList<Tree> step(Tree tree) {
    Collection<ProofTerm> tmp = new HashSet<ProofTerm>();
    try { `TopDown(RStep(tmp,tree.pt)).visit(tree.pt); }
    catch (VisitFailure e) { throw new RuntimeException("never happens"); }
    LinkedList<Tree> res = new LinkedList<Tree>();
    for (ProofTerm pt: tmp) {
      res.add(new Tree(pt,tree));
    }
    return res;
  }

  public static void smartAppend(LinkedList<Tree> l1, LinkedList<Tree> l2) {
l:  for(Tree t: l2) {
      for(Tree t1: l1) {
        if (t.pt.equals(t1.pt)) break l;
      }
      l1.add(t);
    }
  }

  public static LinkedList<Tree> step(LinkedList<Tree> trees) {
    LinkedList<Tree> res = new LinkedList<Tree>();
    for (Tree t: trees) {
      smartAppend(res,step(t));
      //res.addAll(step(t));
    }
    return res;
  }

  public static LinkedList<ProofTerm> check(ProofTerm orig, LinkedList<Tree> forest) {
    for(Tree t: forest) {
      if(t.pt.equals(orig)) return t.chainback();
    }
    return null;
  }

  public static LinkedList<ProofTerm> checkloop(LinkedList<Tree> forest) {
    for(Tree t: forest) {
      LinkedList<ProofTerm> l = t.checkloop();
      if(l != null) return l;
    }
    return null;
  }


  public static Collection<ProofTerm> reduce(ProofTerm pt) {

  /*

    LinkedList<Tree> forest = 
      new LinkedList<Tree>();
    forest.add(new Tree(pt,null));
    while(true) {
      System.out.println(forest.size() + " leaves");
      forest = step(forest);
      //LinkedList<ProofTerm> chain = check(pt,forest);
      LinkedList<ProofTerm> chain = checkloop(forest);
      if (chain != null) {
        System.out.println("found a loop of size " + chain.size());
        System.out.println("------------------");
        for (ProofTerm pt1:chain) {
          System.out.println(Pretty.lpretty(pt1.export()));
          System.out.println("------------------");
        }
        return null;
      }
    }
  }
  */

  Collection<ProofTerm> res = new HashSet<ProofTerm>();
  Collection<ProofTerm> swap;
  res.add(pt);
  Collection<ProofTerm> nf = new HashSet<ProofTerm>();
  Collection<ProofTerm> tmp = new HashSet<ProofTerm>();
  Collection<ProofTerm> tmpres = new HashSet<ProofTerm>();
  do {
    tmpres.clear();
    for(ProofTerm t : res) {
      tmp.clear();
      try { `TopDown(RStep(tmp,t)).visit(t); }
      catch (VisitFailure e) { throw new RuntimeException("never happens"); }
      if (tmp.isEmpty()) {
        boolean in_nf = false;
        for (ProofTerm nft: nf) {
          if (nft.equals(t)) {
            in_nf = true;
            break;
          }
        }
        if (!in_nf) {
          nf.add(t);
          System.out.println("normal forms so far: " + nf.size());      
        }
      }
      for(ProofTerm ptt: tmp) {
        boolean in_tmpres = false;
        for(ProofTerm pttr: tmpres) {
          if(ptt.equals(pttr)) {
            in_tmpres = true;
            break;
          }
        }
        if (!in_tmpres) tmpres.add(ptt);
      }
    }
    swap = tmpres;
    tmpres = res;
    res = swap;
    System.out.println("intermediate results: " + res.size());
    for (ProofTerm x: res) {
      if (x.equals(pt)) {
        System.out.println("loop !");
        return null;
      }
    }
  } while(res.size()>0);
  return nf;
}
}

