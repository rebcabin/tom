/*
  
    TOM - To One Matching Compiler
    
    Copyright (C) 2000-2003  LORIA (CNRST, INPL, INRIA, UHP, U-Nancy 2)
    Nancy, France.
    
    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.
    
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    
    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA
    
    Pierre-Etienne Moreau	e-mail: Pierre-Etienne.Moreau@loria.fr
    Julien Guyon

*/
 
package jtom.runtime.set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


import jtom.runtime.Collect1;
import jtom.runtime.GenericTraversal;
import jtom.runtime.set.jgtreeset.JGTreeSet;
import jtom.runtime.set.jgtreeset.SetFactory;
import aterm.*;

public abstract class ATermSet implements Collection {

    // The internal representation of Shared Sets are trees defined by Jean Goubault 
  protected JGTreeSet tree = null;
    // depth of tree allows to represent set with 2^depth elements
  protected int depth = 31;
    // Count how many elements are in the set
  protected int count = 0;
    // Modification counter to detect issues in iteration operation
  protected int modCount = 0;
  protected static GenericTraversal traversal = new GenericTraversal();

  static protected JGTreeSet emptyTree;
  static protected SetFactory factory = null;
  static protected int collisions = 0;
  static final protected int[] mask =
  { 1 << 0, 
    1 << 1,
    1 << 2,
    1 << 3,
    1 << 4,
    1 << 5,
    1 << 6,
    1 << 7,
    1 << 8,
    1 << 9,
    1 << 10,
    1 << 11,
    1 << 12,
    1 << 13,
    1 << 14,
    1 << 15,
    1 << 16,
    1 << 17,
    1 << 18,
    1 << 19,
    1 << 20,
    1 << 21,
    1 << 22,
    1 << 23,
    1 << 24,
    1 << 25,
    1 << 26,
    1 << 27,
    1 << 28,
    1 << 29,
    1 << 30,
    1 << 31
  };

  %include { jgtreeset/set.tom }

  public int hashCode() {
    return tree.getUniqueIdentifier();
  }
  
  protected SetFactory getSetFactory() { 
    return factory;
  }
  
  protected JGTreeSet makeEmptySet() {
    return emptyTree;
  }
  
  protected JGTreeSet makeBranch(JGTreeSet left, JGTreeSet right) {
    return getSetFactory().makeJGTreeSet_Branch(left, right);
  }
  
  protected JGTreeSet getTreeSet() {
    return tree;
  }

  public String toString() {
    return tree.toString();
  }
  
    // High level interface
  public boolean equals(ATermSet set) {
    return (tree == set.getTreeSet());
  }

  public void clear() {
    tree = makeEmptySet();
    count = 0;
  }
  
  public boolean isEmpty() {
    return tree.isEmptySet();
  }

  public boolean contains(Object o) {
    if (o instanceof ATerm)
      return containsATerm((ATerm)o);
    else
      throw new RuntimeException("Not an ATerm");
  }

  public boolean containsATerm(ATerm elt) {
    return contains(elt, tree, 0);
  }
  
  public boolean containsAll(Collection c) {
    Iterator it = c.iterator();
    Object o = null;
    while(it.hasNext()) {
      if (!contains((ATerm)it.next())) {
        return false;
      }
    }
    return true;    
  }
  
  public abstract Object[] toArray();
  
  public abstract Object[] toArray(Object[] o);
  
  public boolean add(Object o) {
    if (o instanceof ATerm)
      return addATerm((ATerm)o);
    else
      throw new RuntimeException("Not an ATerm");
  }
  
  private boolean addATerm(ATerm elt) {
    JGTreeSet before = tree;
    tree = override(elt, 1, tree, 0);
    modCount++;
    return tree == before;
  }
  
  public boolean addAll(Collection c) {
    JGTreeSet before = tree;
    Iterator it = c.iterator();
    while(it.hasNext()) {
      add(it.next());
    }
    return tree != before;
  }
  
  public boolean remove(Object o) {
    if (o instanceof ATerm)
      return removeATerm((ATerm)o);
    else
      throw new RuntimeException("Not an ATerm");
  }
  
  private boolean removeATerm(ATerm elt) {
    JGTreeSet c = tree;
    tree = remove(elt, tree, 0);
    modCount++;
    return tree == c;
  }

  public boolean removeAll(Collection c) {
    JGTreeSet before = tree;
    Iterator it = c.iterator();
    while(it.hasNext()) {
      remove(it.next());
    }
    modCount++;
    return tree ==before;
  }
  
  public boolean retainAll(Collection c) {
    return true;
  }

  public int size() {
    return size(tree);
  }
  
    // getHead return the first left inner element found
  public ATerm getHead() {
    return getHead(tree);
  }
  
  public String topRepartition() {
    return topRepartition(tree);
  }

    // Low interface using JGTreeSet classes
  protected boolean isEmpty(JGTreeSet set) {
    return set.isEmptySet();
  }
  
  protected boolean contains(ATerm elt, JGTreeSet t) {
    return contains(elt, t, 0);
  }
  protected JGTreeSet add(ATerm elt, JGTreeSet t) {
    return override(elt, 1, t, 0);
  }
  
  protected JGTreeSet remove(ATerm elt, JGTreeSet t) {
    return remove(elt, t, 0);
  }
  
  protected abstract int size(JGTreeSet t);
 
      // getHead return the first left inner element found
  protected abstract ATerm getHead(JGTreeSet t);

  protected JGTreeSet getTail(JGTreeSet t) {
    return remove(getHead(t), t);
  }
  
  protected JGTreeSet union(JGTreeSet t1, JGTreeSet t2) {
    return union(t1, t2, 0);
  }

  protected JGTreeSet intersection(JGTreeSet t1, JGTreeSet t2) {
    JGTreeSet result = intersection(t1, t2, 0);
    return result;
      //return reworkJGTreeSet(result);
  }
  
  protected String topRepartition(JGTreeSet t) {
    %match(JGTreeSet t) {
      branch(l,r) -> { return "Left branch: "+size(l)+"\tright branch: "+size(r);}
      _ ->  {return "topRepartition: No a branch";}
    }
  }

  
  /* Simple binary operation skeleton
 private JGTreeSet f(JGTreeSet m1, JGTreeSet m2) {
   %match(JGTreeSet m1, JGTreeSet m2) {
      emptySet, x -> {
        return f2(m2);
      }
      x, emptySet -> {
        return f1(m1);
      }
      singleton(y) , x -> {
        return g2(y, m2);
      }
      x, singleton(y) -> {
        return g1(y, m1)
      }
      branch(l1, r1), branch(l2, r2) -> {
        return `branch(f(l1, l2, level+1), f(r1, r2, level+1));
      }
    }
  }*/

  protected abstract JGTreeSet reworkJGTreeSet(JGTreeSet t);
  
  protected abstract JGTreeSet union(JGTreeSet m1, JGTreeSet m2, int level);
  
  protected abstract JGTreeSet intersection(JGTreeSet m1, JGTreeSet m2, int level);
  
  protected abstract JGTreeSet restriction(JGTreeSet m1, JGTreeSet m2, int level);

  protected abstract JGTreeSet remove(ATerm elt, JGTreeSet t, int level);

  protected abstract boolean contains(ATerm elt, JGTreeSet t, int level);
    
  protected abstract JGTreeSet override(ATerm elt, int multiplicity, JGTreeSet t, int level);
  
  protected abstract JGTreeSet underride(ATerm elt, JGTreeSet t, int level);

  protected boolean isBitZero(ATerm elt, int position) {
    return ( (elt.getUniqueIdentifier() & mask[position]) == 0);
  }
  
  protected boolean isBitOne(ATerm elt, int position) {
    return ( (elt.getUniqueIdentifier() & mask[position]) > 0);
  }



     // Iterator interface
  public Iterator iterator() {
    if(tree.isEmptySet())
      return new EmptySetIterator();
    return new SetIterator();
  }
  
  protected static class EmptySetIterator implements Iterator {
    public boolean hasNext() {
      return false;
    }
    public Object next() {
      throw new NoSuchElementException();
    }
    public void remove() {
      throw new IllegalStateException();
    }
  } // Class EmptySetIterator
  
  protected class SetIterator implements Iterator {
    
    ATerm[] table = createTableFromTree(ATermSet.this.tree);
    int index = table.length;
    ATerm entry = null;
    ATerm lastReturned = null;
    
      /**
       * The modCount value that the iterator believes that the backing
       * List should have.  If this expectation is violated, the iterator
       * has detected concurrent modification.
       */
    private int expectedModCount = modCount;
    
    private ATerm[] createTableFromTree(JGTreeSet tree) {
      final Collection res = new ArrayList();
      Collect1 collect = new Collect1() {
          public boolean apply(ATerm t) {
            if(t instanceof JGTreeSet) {
              %match(JGTreeSet t) {
                emptySet -> {return false;}
                singleton(x) -> {
                  res.add(x);
                  return false;
                }
                pair[value=x] -> {
                  res.add(x);
                  return false;
                }
                _ -> {return true;}
              }
            } else {
              return true;
            }
          } // Apply
        }; //new
      
      ATermSet.traversal.genericCollect(tree, collect);
      ATerm[] result = new ATerm[res.size()];
      for(int i=0;i<res.size();i++) {
        result[i] = (ATerm) (((ArrayList)res).get(i));
      }
      return result;
    }

    public boolean hasNext() {
      ATerm e = entry;
      int i = index;
      ATerm t[] = table;
        /* Use locals for faster loop iteration */
      while (e == null && i > 0)
        e = t[--i];
      entry = e;
      index = i;
      return e != null;
    }
    
    public Object next() {
      if (modCount != expectedModCount)
        throw new ConcurrentModificationException();
      
      ATerm et = entry;
      int i = index;
      ATerm t[] = table;
      
        /* Use locals for faster loop iteration */
      while (et == null && i > 0) 
        et = t[--i];
      
      entry = et;
      index = i;
      if (et != null) {
        ATerm res = lastReturned = entry;
        entry = null;
        return res;
      }
      throw new NoSuchElementException();
    }
    
    public void remove() {
      throw new RuntimeException("not yet implemented!");
    }
  } // Class SetIterator
  
} // Class ATermSet
