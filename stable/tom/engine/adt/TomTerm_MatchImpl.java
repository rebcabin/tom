package jtom.adt;

abstract public class TomTerm_MatchImpl
extends TomTerm
{
  static private aterm.ATerm pattern = null;

  protected aterm.ATerm getPattern() {
    return pattern;
  }
  private static int index_option = 0;
  private static int index_subjectList = 1;
  private static int index_patternList = 2;
  public shared.SharedObject duplicate() {
    TomTerm_Match clone = new TomTerm_Match();
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getTomSignatureFactory().makeTomTerm_Match(fun, i_args, annos);
  }
  static public void initializePattern()
  {
    pattern = getStaticFactory().parse("Match(<term>,<term>,<term>)");
  }

  static public TomTerm fromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(pattern);

    if (children != null) {
      TomTerm tmp = getStaticTomSignatureFactory().makeTomTerm_Match(Option.fromTerm( (aterm.ATerm) children.get(0)), TomTerm.fromTerm( (aterm.ATerm) children.get(1)), TomTerm.fromTerm( (aterm.ATerm) children.get(2)));
      tmp.setTerm(trm);
      return tmp;
    }
    else {
      return null;
    }
  }
  public boolean isMatch()
  {
    return true;
  }

  public boolean hasOption()
  {
    return true;
  }

  public boolean hasSubjectList()
  {
    return true;
  }

  public boolean hasPatternList()
  {
    return true;
  }

  public Option getOption()
  {
    return (Option) this.getArgument(index_option) ;
  }

  public TomTerm setOption(Option _option)
  {
    return (TomTerm) super.setArgument(_option, index_option);
  }

  public TomTerm getSubjectList()
  {
    return (TomTerm) this.getArgument(index_subjectList) ;
  }

  public TomTerm setSubjectList(TomTerm _subjectList)
  {
    return (TomTerm) super.setArgument(_subjectList, index_subjectList);
  }

  public TomTerm getPatternList()
  {
    return (TomTerm) this.getArgument(index_patternList) ;
  }

  public TomTerm setPatternList(TomTerm _patternList)
  {
    return (TomTerm) super.setArgument(_patternList, index_patternList);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof Option)) { 
          throw new RuntimeException("Argument 0 of a TomTerm_Match should have type Option");
        }
        break;
      case 1:
        if (! (arg instanceof TomTerm)) { 
          throw new RuntimeException("Argument 1 of a TomTerm_Match should have type TomTerm");
        }
        break;
      case 2:
        if (! (arg instanceof TomTerm)) { 
          throw new RuntimeException("Argument 2 of a TomTerm_Match should have type TomTerm");
        }
        break;
      default: throw new RuntimeException("TomTerm_Match does not have an argument at " + i );
    }
    return super.setArgument(arg, i);
  }
  protected int hashFunction() {
    int c = getArgument(1).hashCode() + (getAnnotations().hashCode()<<8);
    int a = 0x9e3779b9;
    int b = 0x9e3779b9;
    a += (getArgument(2).hashCode() << 16);
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
