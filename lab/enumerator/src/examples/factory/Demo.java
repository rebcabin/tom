package examples.factory;

import tom.library.enumerator.Combinators;
import tom.library.enumerator.Enumeration;
import tom.library.enumerator.Finite;
import tom.library.enumerator.LazyList;

public class Demo {
	public static void main(String[] args) {

		// Integeres
		Enumeration<Integer> enumInt = Combinators.makeInteger();
		LazyList<Finite<Integer>> parts = enumInt.parts();
		for (int i = 0; i < 8 && !parts.isEmpty(); i++) {
			System.out.println(i + " --> " + parts.head());
			parts = parts.tail();
		}

		// Tree of Integeres
		Enumeration<Tree<Integer>> enumTree = TreeFactory.getEnumeration(enumInt);
		LazyList<Finite<Tree<Integer>>> partsTree = enumTree.parts();
		for (int i = 0; i < 8 && !partsTree.isEmpty(); i++) {
			System.out.println(i + " --> " + partsTree.head());
			partsTree = partsTree.tail();
		}

		// Colors
		Enumeration<Color> enumColor = ColorFactory.getEnumeration();
		LazyList<Finite<Color>> partsColor = enumColor.parts();
		for (int i = 0; i < 8 && !partsColor.isEmpty(); i++) {
			System.out.println(i + " --> " + partsColor.head());
			partsColor = partsColor.tail();
		}

		// Tree of Colors
		Enumeration<Tree<Color>> enumTreeC = TreeFactory.getEnumeration(enumColor);
		LazyList<Finite<Tree<Color>>> partsTreeC = enumTreeC.parts();
		for (int i = 0; i < 8 && !partsTreeC.isEmpty(); i++) {
			System.out.println(i + " --> " + partsTreeC.head());
			partsTreeC = partsTreeC.tail();
		}

	}
}
