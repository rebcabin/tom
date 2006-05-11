package tom.library.strategy.mutraveler;

import tom.library.strategy.mutraveler.Position;
/**
 * A visitor that it iself visitable with a VisitorVisitor needs
 * to implement the MuStrategy interface. The visitor's arguments
 * should play the role of children.
 */

public abstract class AbstractMuStrategy implements MuStrategy {
  protected jjtraveler.reflective.VisitableVisitor[] visitors;
  private Position position;

  public void setPosition(Position pos) {
    this.position = pos;
  }

  public Position getPosition() {
    if(hasPosition()) {
      return position;
    } else {
      throw new RuntimeException("position not initialized");
    }
  }

  public boolean hasPosition() {
    return position!=null;
  }
  /*
  protected void initPosition(Position pos) {
    setPosition(pos);
    for(int i=0 ; i<getChildCount() ; i++) {
      ((AbstractMuStrategy)getChildAt(i)).initPosition(pos); 
    }
  }

  public MuStrategy init() {
    Position pos = new Position();
    initPosition(pos);
    return this;
  }
  */

  protected void initSubterm() {
    visitors = new jjtraveler.reflective.VisitableVisitor[] {};
  }
  protected void initSubterm(jjtraveler.reflective.VisitableVisitor v1) {
    visitors = new jjtraveler.reflective.VisitableVisitor[] {v1};
  }
  protected void initSubterm(jjtraveler.reflective.VisitableVisitor v1, jjtraveler.reflective.VisitableVisitor v2) {
    visitors = new jjtraveler.reflective.VisitableVisitor[] {v1,v2};
  }
  protected void initSubterm(jjtraveler.reflective.VisitableVisitor v1, jjtraveler.reflective.VisitableVisitor v2, jjtraveler.reflective.VisitableVisitor v3) {
    visitors = new jjtraveler.reflective.VisitableVisitor[] {v1,v2,v3};
  }

  public jjtraveler.reflective.VisitableVisitor getArgument(int i) {
    return visitors[i];
  }
  public void setArgument(int i, jjtraveler.reflective.VisitableVisitor child) {
    visitors[i]= child;
  }

  public int getChildCount() {
    return visitors.length;
  }

  public jjtraveler.Visitable getChildAt(int i) {
      return visitors[i];
  }
  
  public jjtraveler.Visitable setChildAt(int i, jjtraveler.Visitable child) {
    visitors[i]= (jjtraveler.reflective.VisitableVisitor) child;
    return this;
  }

}
