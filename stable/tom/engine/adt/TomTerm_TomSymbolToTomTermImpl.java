package jtom.adt;

abstract public class TomTerm_TomSymbolToTomTermImpl
extends TomTerm
{
  TomTerm_TomSymbolToTomTermImpl(TomSignatureFactory factory) {
    super(factory);
  }
  private static int index_astSymbol = 0;
  public shared.SharedObject duplicate() {
    TomTerm_TomSymbolToTomTerm clone = new TomTerm_TomSymbolToTomTerm(factory);
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  public boolean equivalent(shared.SharedObject peer) {
    if (peer instanceof TomTerm_TomSymbolToTomTerm) {
      return super.equivalent(peer);
    }
    return false;
  }
  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getTomSignatureFactory().makeTomTerm_TomSymbolToTomTerm(fun, i_args, annos);
  }
  public aterm.ATerm toTerm() {
    if (term == null) {
      term = getTomSignatureFactory().toTerm(this);
    }
    return term;
  }

  public boolean isTomSymbolToTomTerm()
  {
    return true;
  }

  public boolean hasAstSymbol()
  {
    return true;
  }

  public TomSymbol getAstSymbol()
  {
    return (TomSymbol) this.getArgument(index_astSymbol) ;
  }

  public TomTerm setAstSymbol(TomSymbol _astSymbol)
  {
    return (TomTerm) super.setArgument(_astSymbol, index_astSymbol);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof TomSymbol)) { 
          throw new RuntimeException("Argument 0 of a TomTerm_TomSymbolToTomTerm should have type TomSymbol");
        }
        break;
      default: throw new RuntimeException("TomTerm_TomSymbolToTomTerm does not have an argument at " + i );
    }
    return super.setArgument(arg, i);
  }
  protected int hashFunction() {
    int c = 0 + (getAnnotations().hashCode()<<8);
    int a = 0x9e3779b9;
    int b = (getAFun().hashCode()<<8);
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
