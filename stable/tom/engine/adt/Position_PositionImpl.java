package jtom.adt;

abstract public class Position_PositionImpl
extends Position
{
  Position_PositionImpl(TomSignatureFactory factory) {
    super(factory);
  }
  private static int index_line = 0;
  private static int index_column = 1;
  public shared.SharedObject duplicate() {
    Position_Position clone = new Position_Position(factory);
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  public boolean equivalent(shared.SharedObject peer) {
    if (peer instanceof Position_Position) {
      return super.equivalent(peer);
    }
    return false;
  }
  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getTomSignatureFactory().makePosition_Position(fun, i_args, annos);
  }
  public aterm.ATerm toTerm() {
    if (term == null) {
      term = getTomSignatureFactory().toTerm(this);
    }
    return term;
  }

  public boolean isPosition()
  {
    return true;
  }

  public boolean hasLine()
  {
    return true;
  }

  public boolean hasColumn()
  {
    return true;
  }

  public Integer getLine()
  {
   return new Integer(((aterm.ATermInt) this.getArgument(index_line)).getInt());
  }

  public Position setLine(Integer _line)
  {
    return (Position) super.setArgument(getFactory().makeInt(_line.intValue()), index_line);
  }

  public Integer getColumn()
  {
   return new Integer(((aterm.ATermInt) this.getArgument(index_column)).getInt());
  }

  public Position setColumn(Integer _column)
  {
    return (Position) super.setArgument(getFactory().makeInt(_column.intValue()), index_column);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof aterm.ATermInt)) { 
          throw new RuntimeException("Argument 0 of a Position_Position should have type int");
        }
        break;
      case 1:
        if (! (arg instanceof aterm.ATermInt)) { 
          throw new RuntimeException("Argument 1 of a Position_Position should have type int");
        }
        break;
      default: throw new RuntimeException("Position_Position does not have an argument at " + i );
    }
    return super.setArgument(arg, i);
  }
}
