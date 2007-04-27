/*
 * Copyright (c) 2004-2007, INRIA
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *  - Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  - Neither the name of the INRIA nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package gom;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.*;

import gom.elist.types.*;
import jjtraveler.VisitFailure;
import tom.library.sl.Strategy;

public class TestMap extends TestCase {

  private static int cnt;
  %include { elist/Elist.tom }
  %include { sl.tom }
  %include { elist/_Elist.tom }
  %include { java/util/types/Collection.tom }
  %include { java/util/types/Map.tom }

  public void testMap() {
    Elist subject = `Cons(a(),Cons(b(),Cons(b(),Cons(c(),Empty()))));
    Collection abag = new HashSet();
    Collection bbag = new HashSet();
    Collection cbag = new HashSet();
    Strategy maps = `mu(MuVar("x"),Choice(_Cons(Log(abag,bbag,cbag),MuVar("x")),_Empty()));
    try {
    maps.visit(subject);
    } catch(VisitFailure e) {}
    assertEquals(1,abag.size());
    assertEquals(2,bbag.size());
    assertEquals(1,cbag.size());
  }

  %strategy Log(abag:Collection,bbag:Collection,cbag:Collection) extends `Identity() {
    visit E {
      a@a() -> { abag.add(new Integer(++cnt)); }
      b@b() -> { bbag.add(new Integer(++cnt)); }
      c@c() -> { cbag.add(new Integer(++cnt)); }
    }
  }

  public void testBuiltin() {
    Elist subject = 
      `Cons(a(),
        Cons(f(f(c(),1,f(b(),5,a())),4,f(a(),2,b())),
          Cons(b(),
            Cons(c(),
              Empty()))));
    Collection abag = new HashSet();
    Collection bbag = new HashSet();
    Collection cbag = new HashSet();
    Strategy maps = `mu(MuVar("x"),
        Choice(
          _Cons(BottomUp(Log(abag,bbag,cbag)),MuVar("x")),
          _Empty()
        ));
    try {
      maps.visit(subject);
    } catch(VisitFailure e) {}
    assertEquals(3,abag.size());
    assertEquals(3,bbag.size());
    assertEquals(2,cbag.size());
  }

  public void testCut() {
    E subject = `f(f(a(),2,b()),4,f(f(c(),2,a()),1,g(b(),5,f(a(),3,f(b(),6,a())))));
    Collection abag = new HashSet();
    Collection bbag = new HashSet();
    Collection cbag = new HashSet();
    /* cutbu is a custom bottomup, that do not go in the left part of an f */
    Strategy cutbu = `mu(MuVar("x"),
        Sequence(
          Choice(_f(Identity(),Fail(),MuVar("x")),All(MuVar("x"))),
          Log(abag,bbag,cbag)
          ));
    try {
    cutbu.visit(subject);
    } catch(VisitFailure e) {}
    assertEquals("count a",1,abag.size());
    assertEquals("count b",1,bbag.size());
    assertEquals("count c",0,cbag.size());
  }

  public void testMatch() {
    E subject = `f(f(a(),1,b()),2,f(c(),3,d()));
    Strategy match = `_f(_f(_a(),Fail(),_b()),Fail(),_f(_c(),Fail(),_d()));
    boolean state = false;
    try {
      match.visit(subject);
      state = true;
    } catch (jjtraveler.VisitFailure e) {
      fail("the match should not fail");
    }
    assertTrue("The strategie pattern should match",state);
  }

  public void testMatchFailure() {
    E subject = `f(f(a(),1,b()),2,f(c(),3,d()));
    Strategy match = `_f(_f(_a(),Fail(),_b()),Fail(),_f(_c(),Fail(),_c()));
    boolean state = true;
    try {
      match.visit(subject);
      fail("the match should fail");
    } catch (jjtraveler.VisitFailure e) {
      state = false;
    }
    assertFalse("The strategie pattern not have matched",state);
  }

  public void testReplace() {
    Elist subject = 
      `Cons(a(),
        Cons(f(f(c(),1,f(b(),5,a())),4,f(a(),2,b())),
          Cons(b(),
            Cons(c(),
              Empty()))));
    Strategy maps = `mu(MuVar("x"),
        Choice(
          _Cons(
            Try(
              _f(
                _f(Make_a(),Identity(),Identity()),
                Identity(),
                _f(Identity(),Identity(),Make_a())
              )),MuVar("x")),
          _Empty()
        ));
    try {
    subject = (Elist) maps.visit(subject);
    } catch(VisitFailure e) {}
    Collection abag = new HashSet();
    Collection bbag = new HashSet();
    Collection cbag = new HashSet();
    try {
      `BottomUp(Log(abag,bbag,cbag)).visit(subject);
    } catch(VisitFailure e) {}
    assertEquals(5,abag.size());
    assertEquals(2,bbag.size());
    assertEquals(1,cbag.size());
  }

  public void testRewrite() {
    Elist subject = 
      `Cons(a(),
        Cons(f(f(c(),1,f(b(),5,a())),4,f(a(),2,b())),
          Cons(b(),
            Cons(c(),
              Empty()))));
    /* encode the rule f(_,b) -> a */
    Strategy rule = `BottomUp(
        Try(
          Sequence(
            _f(Identity(),Identity(),_b()),
            Make_a())));
    try {
      subject = (Elist) rule.visit(subject);
    } catch(VisitFailure e) {}
    assertEquals(
        `Cons(a(),
          Cons(f(f(c(),1,f(b(),5,a())),4,a()),
            Cons(b(),
              Cons(c(),
                Empty())))),
        subject);
  }

  public void testInnermost() {
    E subject =
      `f(
        f(f(a(),0,a()),0,f(a(),1,a())),
        0,
        f(
          f(
            f(a(),1,a()),
            0,
            f(a(),1,f(f(a(),1,a()),0,f(a(),1,a())))
           ),
          0,
          f(f(a(),0,a()),0,f(a(),1,a()))
          )
        );
    /* encode the rule f(a,a) -> a */
    Strategy rule =
      `Sequence(
          _f(_a(),Identity(),_a()),
            Make_a()
            );
    try {
      subject = (E) `Innermost(rule).visit(subject);
    } catch(VisitFailure e) {}
    assertEquals(`a(),subject);
  }

  public void testMake_Strat() {
    Strategy builder = `Make_f(Make_a(),2,Make_a());
    try{
      assertEquals(`f(a(),2,a()),builder.visit(null));
      assertEquals(`f(a(),2,a()),builder.visit(`a()));
      assertEquals(`f(a(),2,a()),builder.visit(`f(a(),3,a())));
    } catch(VisitFailure e) { fail(); }
  }

  %strategy Assign(env:Map, name:String) extends Identity() {
    visit E {
      x -> { env.put(name,`x); }
    }
  }
  %strategy Get(env:Map, name:String) extends Identity() {
    visit E {
      _ -> { return (E) env.get(name); }
    }
  }

  public void testRule() {
    E subject =
      `f(
        f(f(a(),0,a()),0,f(a(),1,a())),
        0,
        f(
          f(
            f(a(),1,a()),
            0,
            f(a(),1,f(f(a(),1,a()),0,f(a(),1,a())))
           ),
          0,
          f(f(a(),0,a()),0,f(a(),1,a()))
          )
        );
    Map env = new HashMap();
    /* encode the rule f(x,a) -> x */
    Strategy rule =
      `Sequence(
         _f(Assign(env,"x"),Identity(),_a()),
         Get(env,"x")
       );
    try {
      subject = (E) `Innermost(rule).visit(subject);
    } catch(VisitFailure e) {}

    assertEquals(`a(),subject);
  }

  public void testVariadicMap() {
    VElist subject = `conc(a(),b(),b(),f(a(),0,b()),c());
    Collection abag = new HashSet();
    Collection bbag = new HashSet();
    Collection cbag = new HashSet();
    Strategy maps = `_conc(Log(abag,bbag,cbag));
    try {
      maps.visit(subject);
    } catch(VisitFailure e) {}
    assertEquals(1,abag.size());
    assertEquals(2,bbag.size());
    assertEquals(1,cbag.size());
  }

  public static void main(String[] args) {
    junit.textui.TestRunner.run(new TestSuite(TestMap.class));
  }
}
