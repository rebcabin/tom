package propcheck.quickcheck;

import propcheck.assertion.NotTestedSkip;
import propcheck.property.Property;
import propcheck.property.Property2;
import propcheck.property.Property3;
import propcheck.shrink.PropcheckShink;
import propcheck.shrink.Shrink;
import propcheck.tools.SimpleLogger;

public class ShrinkRunner3<A, B, C> implements ShrinkRunner {

	private A rootTermA;
	private B rootTermB;
	private C rootTermC;
	private Property3<A, B, C> property;
	private int shrunkCount = 1;
	
	public ShrinkRunner3(A termA, B termB, C termC, Property3<A, B, C> property) {
		this.rootTermA = termA;
		this.rootTermB = termB;
		this.rootTermC = termC;
		this.property = property;
	}
	
	@Override
	public void run() {
		Shrink<A> shrinkerA = new PropcheckShink<A>(rootTermA);
		Shrink<B> shrinkerB = new PropcheckShink<B>(rootTermB);
		Shrink<C> shrinkerC = new PropcheckShink<C>(rootTermC);
		A inputA = rootTermA;
		B inputB = rootTermB;
		C inputC = rootTermC;
		
		// apply for inputA
		inputA = applyA(shrinkerA, inputA, inputB, inputC);
		
		// apply for inputB
		inputB = applyB(shrinkerB, inputA, inputB, inputC);
		
		// apply for inputB
		inputC = applyC(shrinkerC, inputA, inputB, inputC);
		
		print(shrunkCount, inputA, inputB, inputC);
	}

	private B applyB(Shrink<B> shrinkerB, A inputA, B inputB, C inputC) {
		while (shrinkerB.hasNextSubterm()) {
			inputB = shrinkerB.getNextshrinkedTerm();
			try {
				property.apply(inputA, inputB, inputC);
			} catch (NotTestedSkip skip) {
				// do nothing
			} catch (AssertionError error) {
				// assign shrinker to shrink the counter example
				shrinkerB.setCurrentTerm(inputB);
				shrunkCount ++;
			}
		}
		return inputB;
	}

	private A applyA(Shrink<A> shrinkerA, A inputA, B inputB, C inputC) {
		while (shrinkerA.hasNextSubterm()) {
			inputA = shrinkerA.getNextshrinkedTerm();
			try {
				property.apply(inputA, inputB, inputC);
			} catch (NotTestedSkip skip) {
				// do nothing
			} catch (AssertionError error) {
				// assign shrinker to shrink the counter example
				shrinkerA.setCurrentTerm(inputA);
				shrunkCount ++;
			}
		}
		return inputA;
	}
	
	private C applyC(Shrink<C> shrinkerC, A inputA, B inputB, C inputC) {
		while (shrinkerC.hasNextSubterm()) {
			inputC = shrinkerC.getNextshrinkedTerm();
			try {
				property.apply(inputA, inputB, inputC);
			} catch (NotTestedSkip skip) {
				// do nothing
			} catch (AssertionError error) {
				// assign shrinker to shrink the counter example
				shrinkerC.setCurrentTerm(inputC);
				shrunkCount ++;
			}
		}
		return inputC;
	}
	
	void print(int shrunk, A inputA, B inputB, C inputC) {
		String message = String.format("Shrunk %s times, counter example:\n%s\n%s\n%s", shrunk, inputA, inputB, inputC);
		SimpleLogger.log(message);
	}

}