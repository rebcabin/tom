package examples.adt.stack;

import examples.adt.stack.stack.types.*;

public class StackEvaluator {
	%include{ stack/Stack.tom }

	public static Stack createEmpty() {
		return `empty();
	}

	public static boolean isEmpty(Stack stack) {
		return stack.isempty();
	}

	public static int top(Stack stack) throws EmptyStackException {
		%match(stack) {
			push(x, _) -> { return `x; }
		} 
		throw new EmptyStackException();
	}

	public static Stack push(Stack stack, int element) {
		return `push(element, stack);
	}

	public static Stack pop(Stack stack)  throws EmptyStackException {
		%match(stack) {
			push(_, y) -> { return `y; }
		}
		throw new EmptyStackException();
	}

	public static int size(Stack stack) {
		%match(stack) {
			empty() -> { return 0; }
			push(_, y) -> { return 1 + `size(y); } 
		}
		throw new RuntimeException("bad term " + stack);
	}

}
