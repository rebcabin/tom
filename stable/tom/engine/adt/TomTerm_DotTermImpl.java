package jtom.adt;

abstract public class TomTerm_DotTermImpl
extends TomTerm
{
  TomTerm_DotTermImpl(TomSignatureFactory factory) {
    super(factory);
  }
  private static int index_kid1 = 0;
  private static int index_kid2 = 1;
  public shared.SharedObject duplicate() {
    TomTerm_DotTerm clone = new TomTerm_DotTerm(factory);
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  public boolean equivalent(shared.SharedObject peer) {
    if (peer instanceof TomTerm_DotTerm) {
      return super.equivalent(peer);
    }
    return false;
  }
  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getTomSignatureFactory().makeTomTerm_DotTerm(fun, i_args, annos);
  }
  public aterm.ATerm toTerm() {
    if (term == null) {
      term = getTomSignatureFactory().toTerm(this);
    }
    return term;
  }

  public boolean isDotTerm()
  {
    return true;
  }

  public boolean hasKid1()
  {
    return true;
  }

  public boolean hasKid2()
  {
    return true;
  }

  public TomTerm getKid1()
  {
    return (TomTerm) this.getArgument(index_kid1) ;
  }

  public TomTerm setKid1(TomTerm _kid1)
  {
    return (TomTerm) super.setArgument(_kid1, index_kid1);
  }

  public TomTerm getKid2()
  {
    return (TomTerm) this.getArgument(index_kid2) ;
  }

  public TomTerm setKid2(TomTerm _kid2)
  {
    return (TomTerm) super.setArgument(_kid2, index_kid2);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof TomTerm)) { 
          throw new RuntimeException("Argument 0 of a TomTerm_DotTerm should have type TomTerm");
        }
        break;
      case 1:
        if (! (arg instanceof TomTerm)) { 
          throw new RuntimeException("Argument 1 of a TomTerm_DotTerm should have type TomTerm");
        }
        break;
      default: throw new RuntimeException("TomTerm_DotTerm does not have an argument at " + i );
    }
    return super.setArgument(arg, i);
  }
  protected int hashFunction() {
    int c = 0 + (getAnnotations().hashCode()<<8);
    int a = 0x9e3779b9;
    int b = (getAFun().hashCode()<<8);
    a += (getArgument(1).hashCode() << 8);
    a += (getArgument(0).hashCode() << 0);

    a -= b; a -= c; a ^= (c >> 13);
    b -= c; b -= a; b ^= (a << 8);
    c -= a; c -= b; c ^= (b >> 13);
    a -= b; a -= c; a ^= (c >> 12);
    b -= c; b -= a; b ^= (a << 16);
    c -= a; c -= b; c ^= (b >> 5);
    a -= b; a -= c; a ^= (c >> 3);
    b -= c; b -= a; b ^= (a << 10);
    c -= a; c -= b; c ^= (b >> 15);

    return c;
  }
}
