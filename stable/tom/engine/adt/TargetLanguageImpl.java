package jtom.adt;

import aterm.*;
import java.io.InputStream;
import java.io.IOException;

abstract public class TargetLanguageImpl extends TomSignatureConstructor
{
  TargetLanguageImpl(TomSignatureFactory factory) {
     super(factory);
  }
  public boolean isEqual(TargetLanguage peer)
  {
    return term.isEqual(peer.toTerm());
  }
  public boolean isSortTargetLanguage()  {
    return true;
  }

  public boolean isTL()
  {
    return false;
  }

  public boolean isITL()
  {
    return false;
  }

  public boolean hasCode()
  {
    return false;
  }

  public boolean hasStart()
  {
    return false;
  }

  public boolean hasEnd()
  {
    return false;
  }

  public String getCode()
  {
     throw new RuntimeException("This TargetLanguage has no Code");
  }

  public TargetLanguage setCode(String _code)
  {
     throw new RuntimeException("This TargetLanguage has no Code");
  }

  public Position getStart()
  {
     throw new RuntimeException("This TargetLanguage has no Start");
  }

  public TargetLanguage setStart(Position _start)
  {
     throw new RuntimeException("This TargetLanguage has no Start");
  }

  public Position getEnd()
  {
     throw new RuntimeException("This TargetLanguage has no End");
  }

  public TargetLanguage setEnd(Position _end)
  {
     throw new RuntimeException("This TargetLanguage has no End");
  }

}

