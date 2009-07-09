package mi3.congruence;

import tom.library.sl.*;

public class _StrategyList extends tom.library.sl.AbstractStrategyCombinator {

  protected mi3.mapping.IMapping mapping;

  public _StrategyList(mi3.mapping.IMapping mapping,Strategy s) {
    this.mapping = mapping;
    initSubterm(s);
  }

  /** 
   * Visits the subject any in a light way (without environment)  
   *
   * @param any the subject to visit
   * @throws VisitFailure if visitLight fails
   */
  public <T extends Visitable> T visitLight(T any) throws VisitFailure {
    return visitLight(any,mapping.getIntrospector());
  }

  /** 
   * Visit the subject any without managing any environment
   *
   * @param any the subject to visit
   * @param introspector the introspector
   * @return a Visitable
   * @throws VisitFailure if visitLight fails
   */ 
  public final <T> T visitLight(T any, Introspector introspector) throws VisitFailure {
    if(mapping instanceof mi3.mapping.IListMapping) {
      if(mapping.isInstanceOf(any)) {
        return mapLight((mi3.mapping.IListMapping<T,Object>)mapping, arguments[0], any, introspector);
      }
    }
    throw new tom.library.sl.VisitFailure();
  }

  private <C,D> C mapLight(mi3.mapping.IListMapping<C,D> mapping, Strategy s, C subject, Introspector introspector) throws VisitFailure {
    if(mapping.isEmpty(subject)) {
      return subject;
    } else {
      D head = s.visitLight(mapping.getHead(subject),introspector);
      C tail = mapLight(mapping, s, mapping.getTail(subject), introspector);
      return mapping.makeInsert(head,tail);
    }
  }

  /** 
   * Visits the subject any by providing the environment 
   *
   * @param any the subject to visit. 
   * @throws VisitFailure if visit fails
   */
  public <T extends Visitable> T visit(T any) throws VisitFailure {
    return visit(any,mapping.getIntrospector());
  }

  /**
   * Visit the current subject (found in the environment)
   * and place its result in the environment.
   * Sets the environment flag to Environment.FAILURE in case of failure
   *
   * @param introspector the introspector
   * @return 0 if success
   */
  public int visit(Introspector introspector) {
    //environment.setIntrospector(introspector);
    System.out.println("getSubject: " + environment.getSubject());
    System.out.println("strat: " + arguments[0]);
    System.out.println("instrospector: " + introspector);

    introspector.getChildCount(environment.getSubject());

    return new tom.library.sl.All(arguments[0]).visit(introspector);
  }
}
