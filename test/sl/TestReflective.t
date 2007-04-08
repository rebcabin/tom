/*
 * Copyright (c) 2004-2007, INRIA
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 * 	- Redistributions of source code must retain the above copyright
 * 	notice, this list of conditions and the following disclaimer.
 * 	- Redistributions in binary form must reproduce the above copyright
 * 	notice, this list of conditions and the following disclaimer in the
 * 	documentation and/or other materials provided with the distribution.
 * 	- Neither the name of the INRIA nor the names of its
 * 	contributors may be used to endorse or promote products derived from
 * 	this software without specific prior written permission.
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

package sl;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import tom.library.sl.Strategy;

public class TestReflective extends TestCase {

  %include { sl.tom }

  public static void main(String[] args) {
    junit.textui.TestRunner.run(new TestSuite(TestReflective.class));
  }

  public void testMatchId() {
    Strategy s = `Identity();
    %match(s) {
      Identity() -> { return; }
    }
    fail("Identity should have matched");
  }

  public void testMatchIdFail() {
    Strategy s = `Fail();
    %match(s) {
      Identity() -> { fail("Fail should not match"); }
    }
  }

  public void testMatchS1() {
    Strategy s = `S1();
    %match(s) {
      S1() -> { return; }
    }
    fail("S1 should have matched");
  }

  public void testMatchS1Fail() {
    Strategy s = `Fail();
    %match(s) {
      S1() -> { fail("Fail should not match"); }
    }
  }

  public void testMatchSequence1() {
    Strategy s = `Sequence(Identity(), Fail());
    %match(s) {
      Sequence(Identity(), Fail()) -> { return; }
    }
    fail("match should success");
  }

  public void testMatchSequence2() {
    Strategy s = `Sequence(Identity(), S1());
    %match(Strategy s) {
      Sequence(Identity(), Fail()) -> { fail("should not match"); }
    }
  }

  public void testMatchSequence3() {
    Strategy s = `Sequence(Identity(), S1());
    %match(s) {
      Sequence(Identity(), S1()) -> { return; }
    }
    fail("match should success");
  }

  public void testMatchSequence4() {
    Strategy s = `Sequence(Identity(), Fail());
    %match(s) {
      Sequence(Identity(), S1()) -> { fail("match should not success"); }
    }
  }

  public void testMatchSequenceVar() {
    Strategy s = `Sequence(Identity(), Fail());
    %match(s) {
      Sequence(s1, s2) -> { 
        %match(s1, s2) {
          Identity(), Fail() -> {
            return;
          }
        }
      }
    }
    fail("match should success");
  }

  public void testSequenceVarS1() {
    Strategy s = `Sequence(Identity(), S1());
    %match(s) {
      Sequence(s1, s2) -> {
        %match(s1, s2) {
          Identity(), S1() -> { return; }
        }
      }
    }
    fail("match should success");
  }

  public void testMatchS2() {
    Strategy s = `S2(0, "msg", Identity());
    %match(s) {
      S2(i, str, s1) -> {
        if (`i == 0) {
          if(`str.equals("msg")) {
            return;
          }
        }
      }
    }
    fail("match should success");
  }

  public void testMatchS2in() {
    Strategy s = `S2(1, "msg", Identity());
    %match(s) {
      S2(1, "msg", s1) -> {
        %match(s1) {
          Identity() -> {
            return;
          }
        }
      }
    }
    fail("match should success");
  }

  public void testMatchS2Fail() {
    Strategy s = `S2(1, "msg", S1());
    %match(s) {
      S2(1, "msg", Identity()) -> {
        fail("match should not succeed");
      }
    }
  }

  public void testMatchAll1() {
    Strategy s = `Identity();
    %match(s) {
      All(All(x)) -> { fail("no !"); }
      All(x) -> { fail("no !"); }
    }
  }

  public void testMatchAll2() {
    Strategy s = `All(Identity());
    %match(s) {
      All(All(x)) -> { fail("no !"); }
      All(x) -> { return; }
    }
    fail("no !");
  }

  public void testMatchAll3() {
    Strategy s = `All(All(Identity()));
    %match(s) {
      All(All(x)) -> { return; }
      All(x) -> { fail("no !"); }
    }
    fail("no !");
  }

  public void testS3Id() {
    Strategy s = (Strategy)`S3().fire(`Identity());
    %match(s) {
      Identity() -> { return; }
    }
    fail("should not be here");
  }

  public void testS3All() {
    Strategy s = (Strategy)`S3().fire(`All(Identity()));
    %match(s) {
      All[] -> { return; }
    }
    fail("should not be here");
  }

  public void testS3AllAll() {
    Strategy s = (Strategy)`S3().fire(`All(All(Identity())));
    %match(s) {
      All(All(Identity())) -> { fail("S3 did not rewrite s"); }
      All(Identity()) -> { return; }
    }
    fail("should not be here with "+s);
  }

  public void testS3Allllll() {
    Strategy s = (Strategy)`S3().fire(`All(All(All(All(Identity())))));
    %match(s) {
      All(Identity()) -> { fail("should not be here"); }
    }
  }

  public void testAlllllBU() {
    Strategy s = (Strategy)`BottomUp(S3()).fire(`All(All(All(All(Identity())))));
    %match(s) {
      All(Identity()) -> { return; }
    }
    fail("should not be here with "+s);
  }

  public void testcountAll1() {
    Strategy s = `All(S2(0, "", All(All(Identity()))));
    assertEquals("countAll should return 3 with "+s, 3, countAll(s));
  }

  public void testCountAll2() {
    Strategy s = `All(S2(0, "", All(All(Identity()))));
    assertEquals(
        "countAll should return 2 with `BottomUp(S3()).fire(`"+s+")", 2,
        countAll((Strategy)`BottomUp(S3()).fire(s)));
  }

  public void testcountAll3() {
    Strategy s = `All(S4(All(All(Identity())), 0, All(All(All(Identity())))));
    assertEquals("countAll should return 6 with `"+s, 6, countAll(s));
  }

  public void testcountAll4() {
    Strategy s = `All(S4(All(All(Identity())), 0, All(All(All(Identity())))));
    assertEquals(
        "countAll should return 3 with `BottomUp(S3()).fire("+s+")", 3,
        countAll((Strategy)`BottomUp(S3()).fire(s)));
  }

  public void testcountAll5() {
    Strategy s = `S4(Identity(), 0, Fail());
    assertEquals(
        "countAll should return 2 with `BottomUp(S5()).fire(`"+s+")", 2,
        countAll((Strategy)`BottomUp(S5()).fire(s)));
  }

  %strategy S1() extends `Identity() {
    visit Strategy {
      Identity() -> { return `Fail(); }
      Fail() -> { return `Identity(); }
    }
  }

  %strategy S2(i:int, str:String, s:Strategy) extends `Identity() {
    visit Strategy {
      Identity() -> { `Fail(); }
    }
  }

  %strategy S3() extends `Identity() {
    visit Strategy {
      All(All(x)) -> { return `All(x); }
    }
  }

  %strategy S4(s1:Strategy, i:int, s2:Strategy) extends `Fail() {
    visit Strategy {
      Identity() -> { return `s1; }
      Fail() -> { return `All(s2); }
    }
  }

  %strategy S5() extends `Identity() {
    visit Strategy {
      Fail() -> { return `All(Identity()); }
    }
  }

  // count the number of All nodes
  private static class Counter { public int count = 0; }
  %typeterm Counter {
    implement { Counter }
    is_sort(t) { t instanceof Counter }
  }
  public int countAll(Strategy s) {
    Counter c = new Counter();
    `TopDown(incAll(c)).fire(s);
    return c.count;
  }

  %strategy incAll(c:Counter) extends `Identity() {
    visit Strategy {
      All(_) -> { c.count++; }
    }
  }
}

