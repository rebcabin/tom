package gom;

import static org.junit.Assert.*;
import org.junit.Test;

import gom.testcollection.m.types.*;
import gom.testcollection.m.types.t.*;
import gom.testcollection.m.types.l.*;
import java.util.*;

public class TestCollection {

  %gom {
    module M
    imports int
    abstract syntax
      T = a() | b() | c()
        | f(T*)
        | g(T*)
      L = cons(T*)
        | consint(int*)

      g:AU() {}
  }

  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main(TestCollection.class.getName());
  }

  @Test
  public void test1() {
    f l = (f) `f(a(),b());
    assertTrue(l.contains(`a()));
    assertTrue(l.contains(`b()));
    Iterator<T> it = l.iterator();
    boolean a=false;
    boolean b=false;
    while(it.hasNext()) {
      T o = it.next();
      if(o==`a()) { a = true; }
      if(o==`b()) { b = true; }
    }
    assertTrue(a);
    assertTrue(b);
    for(T x:l) {
      assertTrue(l.contains(x));
    }

    Collection<T> col = l.getCollection();
    col.add(`c());
    Iterator<T> itc = col.iterator();
    boolean c = false;
    while(itc.hasNext()) {
      T o = itc.next();
      if(o==`c()) { c = true; }
    }
    assertTrue(c);

    for(T x:col) {
      assertTrue(col.contains(x));
    }
  }

  @Test
  public void test2() {
    cons l = (cons)`cons(a(),b());
    assertTrue(l.contains(`a()));
    assertTrue(l.contains(`b()));
    Iterator<T> it = l.iterator();
    boolean a=false;
    boolean b=false;
    while(it.hasNext()) {
      T o = it.next();
      if(o==`a()) { a = true; }
      if(o==`b()) { b = true; }
    }
    assertTrue(a);
    assertTrue(b);

    for(T x:l) {
      assertTrue(l.contains(x));
    }

    Collection<T> col = l.getCollection();
    col.add(`c());
    Iterator<T> itc = col.iterator();
    boolean c = false;
    while(itc.hasNext()) {
      T o = itc.next();
      if(o==`c()) { c = true; }
    }
    assertTrue(c);

    for(T x:col) {
      assertTrue(col.contains(x));
    }
  }

  @Test
  public void test3() {
    consint l = (consint) `consint(1,2);
    assertTrue(l.contains(1));
    assertTrue(l.contains(2));
    Iterator<Integer> it = l.iterator();
    boolean a=false;
    boolean b=false;
    while(it.hasNext()) {
      Integer o = it.next();
      if(o==1) { a = true; }
      if(o==2) { b = true; }
    }
    assertTrue(a);
    assertTrue(b);

    for(Integer x:l) {
      assertTrue(l.contains(x));

    }
    Collection<Integer> col = l.getCollectionconsint();
    col.add(3);
    Iterator<Integer> itc = col.iterator();
    boolean c = false;
    while(itc.hasNext()) {
      Integer o = itc.next();
      if(o==3) { c = true; }
    }
    assertTrue(c);

    for(Integer x:col) {
      assertTrue(col.contains(x));
    }
  }

  @Test
  public void test4() {
    f l = (f) `f(a(),b(),c());
    Object[] array = l.toArray();
    assertTrue(array[0]==`a());
    assertTrue(array[1]==`b());
    assertTrue(array[2]==`c());

    Collection<T> col = l.getCollection();
    Object[] colarray = col.toArray();
    assertTrue(colarray[0]==`a());
    assertTrue(colarray[1]==`b());
    assertTrue(colarray[2]==`c());

  }

  @Test
  public void test5() {
    g l = (g) `g(a(),b());
    assertTrue(l.contains(`a()));
    assertTrue(l.contains(`b()));
    Iterator<T> it = l.iterator();
    boolean a=false;
    boolean b=false;
    while(it.hasNext()) {
      T o = it.next();
      if(o==`a()) { a = true; }
      if(o==`b()) { b = true; }
    }
    assertTrue(a);
    assertTrue(b);
    for(T x:l) {
      assertTrue(l.contains(x));
    }

    Collection<T> col = l.getCollection();
    col.add(`c());
    Iterator<T> itc = col.iterator();
    boolean c = false;
    while(itc.hasNext()) {
      T o = itc.next();
      if(o==`c()) { c = true; }
    }
    assertTrue(c);

    for(T x:col) {
      assertTrue(col.contains(x));
    }
  }

  @Test
  public void test6() {
    g l = (g) `g(a(),b(),c());
    Object[] array = l.toArray();
    assertEquals(`a(),array[0]);
    assertEquals(`b(),array[1]);
    assertEquals(`c(),array[2]);

    Collection<T> col = l.getCollection();
    Object[] colarray = col.toArray();
    assertEquals(`a(),colarray[0]);
    assertEquals(`b(),colarray[1]);
    assertEquals(`c(),colarray[2]);

    T[] tcolarray = new T[3];
    T[] myarray = col.toArray(tcolarray);
    assertEquals(`a(),myarray[0]);
    assertEquals(`b(),myarray[1]);
    assertEquals(`c(),myarray[2]);

    g revl = (g)l.reverse();
    Object[] revarray = revl.toArray();
    assertEquals(`c(),revarray[0]);
    assertEquals(`b(),revarray[1]);
    assertEquals(`a(),revarray[2]);
  }
}
