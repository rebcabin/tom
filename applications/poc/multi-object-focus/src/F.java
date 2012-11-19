import lib.MOFException;
import lib.fun.Fun;

/**
 * Created with IntelliJ IDEA.
 * User: christophe
 * Date: 23/10/12
 * Time: 17:13
 * To change this template use File | Settings | File Templates.
 */
// Binary node
public class F extends Term {
    public String fsym ;
    public Term   left ;
    public Term   right;

    public F(String f, Term l, Term r) {
        fsym  = f;
        left  = l;
        right = r;
    }

    public String toString() {
        return fsym + "(" + left.toString() + " , " + right.toString() + ")";
    }

    public boolean equals(Object o) {
        if(o instanceof F) {
            F n = (F) o;
            return fsym.equals(n.fsym) && left.equals(n.left) && right.equals(n.right);
        }
        return false;
    }

    public <X>     X   accept(TermVisitor<X> v)                              throws MOFException { return v.visitF(this);       }
    public <Ans,Y> Ans acceptCPS(TermVisitorCPS<Ans,Y> v, Fun<Y,Ans> k) throws MOFException { return v.visitF_CPS(this, k); }
}