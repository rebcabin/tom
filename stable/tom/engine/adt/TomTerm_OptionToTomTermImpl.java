package jtom.adt;

abstract public class TomTerm_OptionToTomTermImpl
extends TomTerm
{
  TomTerm_OptionToTomTermImpl(TomSignatureFactory factory) {
    super(factory);
  }
  private static int index_astOption = 0;
  public shared.SharedObject duplicate() {
    TomTerm_OptionToTomTerm clone = new TomTerm_OptionToTomTerm(factory);
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  public boolean equivalent(shared.SharedObject peer) {
    if (peer instanceof TomTerm_OptionToTomTerm) {
      return super.equivalent(peer);
    }
    return false;
  }
  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getTomSignatureFactory().makeTomTerm_OptionToTomTerm(fun, i_args, annos);
  }
  public aterm.ATerm toTerm() {
    if (term == null) {
      term = getTomSignatureFactory().toTerm(this);
    }
    return term;
  }

  public boolean isOptionToTomTerm()
  {
    return true;
  }

  public boolean hasAstOption()
  {
    return true;
  }

  public Option getAstOption()
  {
    return (Option) this.getArgument(index_astOption) ;
  }

  public TomTerm setAstOption(Option _astOption)
  {
    return (TomTerm) super.setArgument(_astOption, index_astOption);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof Option)) { 
          throw new RuntimeException("Argument 0 of a TomTerm_OptionToTomTerm should have type Option");
        }
        break;
      default: throw new RuntimeException("TomTerm_OptionToTomTerm does not have an argument at " + i );
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
