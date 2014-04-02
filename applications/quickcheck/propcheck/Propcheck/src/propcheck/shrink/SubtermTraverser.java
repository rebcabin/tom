package propcheck.shrink;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import tom.library.enumerator.Enumeration;

public class SubtermTraverser<E> {
	private final static String METHOD_GET_ENUMERATION = "getEnumeration";
	private final static String METHOD_GET_CHILDCOUNT = "getChildCount";
	private final static String METHOD_GET_CHILDAT = "getChildAt";
	
	private List<E> constants = null;
	
	protected SubtermTraverser(E rootTerm) {
		if (constants == null) {
			constants = new ArrayList<E>();
		}
		constants = getConstants(rootTerm);
	}
	
	public static <E> SubtermTraverser<E> make(E rootTerm) {
		return new SubtermTraverser<E>(rootTerm);
	}
	
	/**
	 * If the input term is a constant (constructor without parameters) then it will return empty list
	 * @param term
	 * @return
	 */
	public List<E> getConstants(E term) {
		List<E> csts = new ArrayList<E>();
		Enumeration<E> en = getEnumerationFromTerm(term);

		BigInteger card = en.parts().head().getCard();
		BigInteger bigIndex = BigInteger.ZERO; 

		while (card.compareTo(bigIndex) > 0) {
			csts.add(en.get(bigIndex));
			bigIndex = bigIndex.add(BigInteger.ONE);
		}
		
		// clear the list if the input term is a constant
		if (csts.contains(term)) {
			csts.clear();
		}
		return csts;
	}
	
	public List<E> getConstants() {
		return new ArrayList<E>(constants);
	}
	
	
	@SuppressWarnings("unchecked")
	public Enumeration<E> getEnumerationFromTerm(E term) {
		Enumeration<E> en = null;
		try {
			en = (Enumeration<E>) ShrinkTools.invokeStaticMethodFromSuperclass(term, METHOD_GET_ENUMERATION);
		} catch (ShrinkException e) {
			// TODO decide what to do for this exception
			e.printStackTrace();
		}
		return en;
	}
	
	public List<E> getSubterms(E term) {
		Class<?> sort = term.getClass().getSuperclass();
		List<E> subterms = new ArrayList<E>();
		int childCount = getChildCount(term);
		for (int i = 0; i < childCount; i++) {
			E c = getChildAt(term, i); 
			if (c.getClass().getSuperclass().equals(sort) && !isConstant(c)) {
				subterms.add(c);
			}
		}
		return subterms;
	}
	
	public E getChildAt(E term, int i) {
		Class<?>[] typeParams = {int.class};
		Object[] valueParams = {i};
		Object result = null;
		try {
			result = ShrinkTools.invokeMethod(term, METHOD_GET_CHILDAT, typeParams, valueParams);
		} catch (ShrinkException e) {
			// TODO decide what to do for this exception
			e.printStackTrace();
		}
		return cast(result);
	}
	
	public int getChildCount(E term) {
		int childcount = 0;
		try {
			childcount = (Integer) ShrinkTools.invokeMethod(term, METHOD_GET_CHILDCOUNT);
		} catch (ShrinkException e) {
			// TODO decide what to do for this exception
			childcount = 0;
		}
		return childcount;
	}
	
	public boolean isConstant(E term) {
		return constants.contains(term);
	}
	
	@SuppressWarnings("unchecked")
	public E cast(Object object) {
		return (E) object;
	}
}