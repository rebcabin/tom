package jtom.adt;

abstract public class Option_OriginTrackingImpl
extends Option
{
  static private aterm.ATerm pattern = null;

  protected aterm.ATerm getPattern() {
    return pattern;
  }
  private static int index_astName = 0;
  private static int index_line = 1;
  public shared.SharedObject duplicate() {
    Option_OriginTracking clone = new Option_OriginTracking();
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getTomSignatureFactory().makeOption_OriginTracking(fun, i_args, annos);
  }
  static public void initializePattern()
  {
    pattern = getStaticFactory().parse("OriginTracking(<term>,<int>)");
  }

  static public Option fromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(pattern);

    if (children != null) {
      Option tmp = getStaticTomSignatureFactory().makeOption_OriginTracking(TomName.fromTerm( (aterm.ATerm) children.get(0)), (Integer) children.get(1));
      tmp.setTerm(trm);
      return tmp;
    }
    else {
      return null;
    }
  }
  public aterm.ATerm toTerm() {
    if(term == null) {
      java.util.List args = new java.util.LinkedList();
      args.add(((TomSignatureConstructor) getArgument(0)).toTerm());
      args.add(new Integer(((aterm.ATermInt) getArgument(1)).getInt()));
      setTerm(getFactory().make(getPattern(), args));
    }
    return term;
  }

  public boolean isOriginTracking()
  {
    return true;
  }

  public boolean hasAstName()
  {
    return true;
  }

  public boolean hasLine()
  {
    return true;
  }

  public TomName getAstName()
  {
    return (TomName) this.getArgument(index_astName) ;
  }

  public Option setAstName(TomName _astName)
  {
    return (Option) super.setArgument(_astName, index_astName);
  }

  public Integer getLine()
  {
   return new Integer(((aterm.ATermInt) this.getArgument(index_line)).getInt());
  }

  public Option setLine(Integer _line)
  {
    return (Option) super.setArgument(getFactory().makeInt(_line.intValue()), index_line);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof TomName)) { 
          throw new RuntimeException("Argument 0 of a Option_OriginTracking should have type TomName");
        }
        break;
      case 1:
        if (! (arg instanceof aterm.ATermInt)) { 
          throw new RuntimeException("Argument 1 of a Option_OriginTracking should have type int");
        }
        break;
      default: throw new RuntimeException("Option_OriginTracking does not have an argument at " + i );
    }
    return super.setArgument(arg, i);
  }
}
