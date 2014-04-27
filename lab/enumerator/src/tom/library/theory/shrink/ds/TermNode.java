package tom.library.theory.shrink.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tom.library.sl.Visitable;
import tom.library.theory.shrink.ds.zipper.Node;

public class TermNode implements Node{
	private List<TermNode> children;
	private Visitable term;
	private int index;
	
	public TermNode(Visitable term, int index) {
		initialize(term, index, new ArrayList<TermNode>());
	}
	
	public TermNode(Visitable term, int index, TermNode...children) {
		initialize(term, index, Arrays.asList(children));
	}

	protected void initialize(Visitable term, int index, List<TermNode> children) {
		this.term = term;
		this.index = index;
		this.children = children;
	}
	
	@Override
	public List<? extends Node> getChildren() {
		return children;
	}
	
	public void addChildren(List<TermNode> children) {
		this.children.addAll(children);
	}
	
	public void addChild(TermNode child) {
		children.add(child);
	}

	public Visitable getTerm() {
		return term;
	}

	public void setTerm(Visitable term) {
		this.term = term;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	
}
