import aterm.*;
import aterm.pure.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class TestPeano {

  private ATermFactory factory;
  private AFun fzero, fsuc, fplus;
  public ATermAppl tzero;

  %typeterm term {
    implement { ATerm }
    is_sort(t) { t instanceof ATerm }
    equals(t1, t2)      { t1 == t2}
  }

  %typeterm appl {
    implement { ATermAppl }
    is_sort(t) { t instanceof ATermAppl }
    equals(t1, t2)      { t1 == t2}
  }

  %op term zero() {
    is_fsym(t) { ((ATermAppl)t).getAFun() == fzero }
    make() { factory.makeAppl(fzero) }
  }
  
  %op term suc(pred:term) {
    is_fsym(t) { ((ATermAppl)t).getAFun() == fsuc }
    get_slot(pred,t) { ((ATermAppl)t).getArgument(0)  }
    make(t) { factory.makeAppl(fsuc,t) }
  }

  %op term plus1(s1:term,s2:term) {
    make(t1,t2) { plus1(t1,t2) }
  }

  %op term plus2(s1:term,s2:term) {
    make(t1,t2) { plus2(t1,t2) }
  }

  %op term fib1(s1:term) {
    is_fsym(t) { ((ATermAppl)t).getName() == "fib1" }
    make(t) { fib1(t) }
  }

  %op term fib2(s1:term) {
    is_fsym(t) { ((ATermAppl)t).getName() == "fib2" }
    make(t) { fib2(t) }
  }

  %op term fib5(s1:term) {
    is_fsym(t) { ((ATermAppl)t).getName() == "fib5" }
    make(t) { fib2(t) }
  }
  
  %op appl term2appl(s1:term) {
    is_fsym(t) { ((ATermAppl)t).getName() == "term2appl" }
    get_slot(s1,t) { ((ATermAppl)t).getArgument(0) }
    make(t) { factory.makeAppl(factory.makeAFun("term2appl",1,false),t) }
  }

  %op term appl2term(s1:appl) {
    is_fsym(t) { ((ATermAppl)t).getName() == "appl2term" }
    get_slot(s1,t) { (ATermAppl) ((ATermAppl)t).getArgument(0) }
    make(t) { appl2term(t) }
  }

	public static void main(String[] args) {
    org.junit.runner.JUnitCore.main(TestPeano.class.getName());
	}

  public void setUp() {
    this.factory = new PureFactory(16);

    fzero = factory.makeAFun("zero", 0, false);
    fsuc  = factory.makeAFun("suc" , 1, false);
    fplus = factory.makeAFun("plus", 2, false);
    tzero = factory.makeAppl(fzero);
  }

  @Test
  public void testPlus1() {
    for(int i=0 ; i<100 ; i++) {
      ATerm N = int2peano(i);
      assertTrue("Testing plus1 with N ="+N+": ", 
								 peano2int(plus1(N,N)) == (i+i) );
    }
	}

  @Test
  public void testPlus2() {
    for(int i=0 ; i<100 ; i++) {
      ATerm N = int2peano(i);
      assertTrue("Testing plus2 with N ="+N+": ",  
								 peano2int(plus2(N,N)) == (i+i) );
    }
	}

  @Test
  public void testPlus3() {
    for(int i=0 ; i<100 ; i++) {
      ATerm N = int2peano(i);
      assertTrue("Testing plus3 with N ="+N+": ",  
								 peano2int(plus3(N,N)) == (i+i) );
    }
	}

  @Test
  public void testPlus4() {
    for(int i=0 ; i<100 ; i++) {
      ATerm N = int2peano(i);
      assertTrue("Testing plus4 with N ="+N+": ",  
								 peano2int(plus4(N,N)) == (i+i) );
    }
	}

  @Test
	public void testFib1() {
    for(int i=0 ; i<15 ; i++) {
      ATerm N = int2peano(i);
      assertTrue("Testing fib1 with N ="+N+": ",  
								 peano2int(fib1(N)) == fibint(i) );
    }
  }

  @Test
	public void testFib2() {
    for(int i=0 ; i<15 ; i++) {
      ATerm N = int2peano(i);
      assertTrue("Testing fib2 with N ="+N+": ",   
								 peano2int(fib2(N)) == fibint(i) );
    }
  }

  @Test
	public void testFib3() {
    for(int i=0 ; i<15 ; i++) {
      ATerm N = int2peano(i);
      assertTrue("Testing fib3 with N ="+N+": ",   
								 peano2int(fib3(N)) == fibint(i) );
    }
  }

  @Test
	public void testFib4() {
    for(int i=0 ; i<15 ; i++) {
      ATerm N = int2peano(i);
      assertTrue("Testing fib4 with N ="+N+": ",   
								 peano2int(fib4(N)) == fibint(i) );
    }
  }

  @Test
	public void testFib5() {
    for(int i=0 ; i<15 ; i++) {
      ATerm N = int2peano(i);
      assertTrue("Testing fib5 with N ="+N+": ",   
								 peano2int(fib5(N)) == fibint(i) );
    }
  }

  @Test
  public void testFib6() {
    ATerm N = `suc(suc(suc(suc(suc(suc(suc(suc(suc(suc(zero()))))))))));
    assertTrue("Testing fib5 with N =" + peano2int(N) + ": ",   
               peano2int(`fib5(N)) == fibint(peano2int(N)));
    assertTrue("Testing fib5 with N =" + peano2int(N)+1 + ": ",   
               peano2int(`fib5(suc(N))) == fibint(peano2int(N)+1));
  }
  

  public ATerm plus1(ATerm t1, ATerm t2) {
    %match(term t1, term t2) {
      x,(zero|zero)[]   -> { return `x; }
      x,suc(y) -> { return suc(plus1(`x,`y)); }
    }
    return null;
  }

  public ATerm plus2(ATerm t1, ATerm t2) {
    %match(term t1, term t2) {
      x,zero()   -> { return `x; }
      x,suc[pred=y] -> { return suc(plus2(`x,`y)); }
    }
    return null;
  }

  public ATerm plus3(ATerm t1, ATerm t2) {
    %match(term t1, term t2) {
      x,zero()   -> { return `x; }
      x@_,suc[pred=y@z] -> { return suc(plus3(`x,`y)); }
    }
    return null;
  }

    public ATerm plus4(ATerm t1, ATerm t2) {
    %match(term t1, term t2) {
      x,(zero|zero)[]   -> { return `x; }
      x,(suc|suc)[pred=y] -> { return suc(plus1(`x,`y)); }
    }
    return null;
  }
  
  %rule {
    fib1(zero())        -> suc(zero())
    fib1(suc(zero()))   -> suc(zero())
    fib1(suc(suc(x)))   -> plus1(fib1(x),fib1(suc(x)))
  }
  
  %rule {
    //    fib5(zero())      -> suc(appl2term(term2appl(x))) where x:= zero()
    fib5(zero())      -> suc(x) where x:= zero()
    fib5(x)           -> x      if x == suc(zero())
    fib5(suc(suc(x))) -> plus1(fib1(x),fib1(suc(x)))
  }
  
  %rule {
    fib2(zero())-> suc(zero())
    fib2(x@suc[pred=zero()]) -> suc(zero())
    fib2(suc(y@suc(x))) -> plus2(fib2(x),fib2(y))
  }

  %rule {
    appl2term(term2appl(x)) -> x
  }

  public ATerm fib3(ATerm t) {
    %match(term t) {
      zero()             -> { return `suc(zero()); }
      suc[pred=x@zero()] -> { return `suc(x); }
      suc(suc(x))      -> { return plus3(fib3(`x),fib3(suc(`x))); }
    }
    return null;
  }

  public ATerm fib4(ATerm t) {
    %match(term t) {
      zero()      -> { return `suc(zero()); }
      suc(zero()) -> { return `suc(zero()); }
      suc(suc(x)) -> { return plus3(fib4(`x),fib4(suc(`x))); }
    }
    return null;
  }

  public ATerm suc(ATerm t) {
    return (ATerm)factory.makeAppl(fsuc,t);
  }

  public int fibint(int n) {
    if(n<=1) {
      return 1;
    } else {
      return fibint(n-1)+fibint(n-2);
    }
  }

  @Test
	public void testInt2p() {
		assertEquals("int2peano should return sssss(tzero) for 5",
								 int2peano(5),`suc(suc(suc(suc(suc(tzero))))));
	}
  
  public ATerm int2peano(int n) {
    ATerm N = tzero;
    for(int i=0 ; i<n ; i++) {
      N = suc(N);
    }
    return N;
  }

  @Test
	public void testPeano2int() {
		assertEquals("peano2int should return 5 for sssss(tzero)",
								 peano2int(`suc(suc(suc(suc(suc(tzero)))))),5);
	}

  public int peano2int(ATerm N) {
    %match(term N) {
      zero() -> { return 0; }
      suc(x) -> {return 1+peano2int(`x); }
    }
    return 0;
  }
  
}

