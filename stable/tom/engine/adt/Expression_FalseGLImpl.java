package jtom.adt;

import aterm.*;

public class Expression_FalseGLImpl
extends Expression
{
  static private aterm.ATerm pattern = null;

  protected aterm.ATerm getPattern() {
    return pattern;
  }

  public shared.SharedObject duplicate() {
    Expression_FalseGL clone = new Expression_FalseGL();
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getTomSignatureFactory().makeExpression_FalseGL(fun, i_args, annos);
  }
  static public void initializePattern()
  {
    pattern = getStaticFactory().parse("FalseGL");
  }


  static public Expression fromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(pattern);

    if (children != null) {
      Expression tmp = getStaticTomSignatureFactory().makeExpression_FalseGL();
      tmp.setTerm(trm);
      return tmp;
    }
    else {
      return null;
    }
  }

  public boolean isFalseGL()
  {
    return true;
  }


  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
      throw new RuntimeException("Expression_FalseGL has no arguments");
  }

  protected int hashFunction() {
    int c = 0 + (getAnnotations().hashCode()<<8);
    int a = 0x9e3779b9;
    int b = 0x9e3779b9;

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
