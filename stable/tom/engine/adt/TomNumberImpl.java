package jtom.adt;

import aterm.*;
import java.io.InputStream;
import java.io.IOException;

abstract public class TomNumberImpl extends TomSignatureConstructor
{
  protected TomNumberImpl(TomSignatureFactory factory) {
     super(factory);
  }
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  public boolean isEqual(TomNumber peer)
  {
    return term.isEqual(peer.toTerm());
  }
  public boolean isSortTomNumber()  {
    return true;
  }

  public boolean isMatchNumber()
  {
    return false;
  }

  public boolean isPatternNumber()
  {
    return false;
  }

  public boolean isListNumber()
  {
    return false;
  }

  public boolean isIndexNumber()
  {
    return false;
  }

  public boolean isBegin()
  {
    return false;
  }

  public boolean isEnd()
  {
    return false;
  }

  public boolean isNumber()
  {
    return false;
  }

  public boolean isAbsVar()
  {
    return false;
  }

  public boolean isRenamedVar()
  {
    return false;
  }

  public boolean isRuleVar()
  {
    return false;
  }

  public boolean hasNumber()
  {
    return false;
  }

  public boolean hasInteger()
  {
    return false;
  }

  public boolean hasAstName()
  {
    return false;
  }

  public TomNumber getNumber()
  {
     throw new RuntimeException("This TomNumber has no Number");
  }

  public TomNumber setNumber(TomNumber _number)
  {
     throw new RuntimeException("This TomNumber has no Number");
  }

  public int getInteger()
  {
     throw new RuntimeException("This TomNumber has no Integer");
  }

  public TomNumber setInteger(int _integer)
  {
     throw new RuntimeException("This TomNumber has no Integer");
  }

  public TomName getAstName()
  {
     throw new RuntimeException("This TomNumber has no AstName");
  }

  public TomNumber setAstName(TomName _astName)
  {
     throw new RuntimeException("This TomNumber has no AstName");
  }

}

