package tom.library.theory.internal;

import org.junit.contrib.theories.PotentialAssignment;
import org.junit.contrib.theories.internal.Assignments;
import org.junit.runners.model.Statement;

/**
 * Assigns test method's parameters with values generated from
 * a value supplier. All values are combined in every possible 
 * combination by this class.
 * 
 * @author nauval
 *
 */
public class AssignmentRunner {
	private TestObject testObject;
	private ExecutionHandler handler;

	public AssignmentRunner(TestObject testObject, ExecutionHandler handler) {
		this.testObject = testObject;
		this.handler = handler;
	}

	/**
	 * Assigns test method's parameters with values and then runs the 
	 * test methods after the assignment is complete.
	 * 
	 * @param parameterAssignment
	 * @throws Throwable
	 */
	public void runWithAssignment(Assignments parameterAssignment) throws Throwable {
		if (!parameterAssignment.isComplete()) {
			runWithIncompleteAssignment(parameterAssignment);
		} else {
			runWithCompleteAssignment(parameterAssignment);
		}
	}

	/**
	 * Retrieves potentials assignment values for a parameter and then assign them
	 * to the parameter.
	 * 
	 * @param incomplete
	 * @throws Throwable
	 */
	protected void runWithIncompleteAssignment(Assignments incomplete) throws Throwable {
		for (PotentialAssignment source : incomplete.potentialsForNextUnassigned()) {
			runWithAssignment(incomplete.assignNext(source));
		}
	}

	/**
	 * Runs a test after all its parameters has been assigned with values.
	 * The test is wrapped in a {@code Statement} that later on is evaluated
	 * by the method.
	 * @param complete
	 * @throws Throwable
	 */
	protected void runWithCompleteAssignment(final Assignments complete) throws Throwable {
		StatementBuilder builder = new StatementBuilder(testObject, handler);
		Statement statement = builder.buildStatementForCompleteAssignment(complete);
		statement.evaluate();
	}
}
