/*
 *
 * Copyright (c) 2000-2007, Pierre-Etienne Moreau
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
 *
 **/
package tom.library.sl;
import java.util.Arrays;
import java.util.Vector;

/**
 * Object that represents an environment of a strategy
 * the position where the strategy is applied
 * a pointer to subterm
 * the root is stored in the first cell
 */

public final class Environment implements Cloneable {
  private static final int DEFAULT_LENGTH = 8;
  public static final int SUCCESS = 0;
  public static final int FAILURE = 1;
  public static final int IDENTITY = 2;
  /*
   * number of elements-1 in the arrays
   * the current position is int omega[current]
   * the current subterm is subterm[current]
   * */
  protected int current;
  protected int[] omega;
  protected Visitable[] subterm;
  protected int status = Environment.SUCCESS;

  public Environment() {
    this(DEFAULT_LENGTH);
  }

  private Environment(int length) {
    omega = new int[length+1];
    subterm = new Visitable[length+1];
    current = 0; // root is in subterm[0]
    omega[0]=0; // the first cell is not used
  }

  private void ensureLength(int minLength) {
    if(minLength > omega.length) {
      int max = Math.max(omega.length * 2, minLength);
      int[] newOmega = new int[max];
      Visitable[] newSubterm = new Visitable[max];
      System.arraycopy(omega, 0, newOmega, 0, omega.length);
      System.arraycopy(subterm, 0, newSubterm, 0, omega.length);
      omega = newOmega;
      subterm = newSubterm;
    }
  }

  public Object clone() throws CloneNotSupportedException {
    Environment clone = (Environment) super.clone();
    clone.omega = new int[omega.length];
    clone.subterm = new Visitable[omega.length];
    System.arraycopy(omega, 0, clone.omega, 0, omega.length);
    System.arraycopy(subterm, 0, clone.subterm, 0, omega.length);
    clone.current = current;
    return clone;
  }

  /**
   * Tests if two environments are equals
   */
  public boolean equals(Object o) {
    if (o instanceof Environment) {
      Environment p = (Environment)o;
      /* we need to check only the meaningful part of the omega and subterm arrays */
      if (current==p.current) {
        for(int i=0; i<current+1; i++) {
          if (omega[i]!=p.omega[i] || subterm[i]!=p.subterm[i]) {
            return false;
          }
        }
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  public int hashCode() {
    /* Hash only the interesting part of the array */
    int[] hashedOmega = new int[current+1];
    Visitable[] hashedSubterm = new Visitable[current+1];
    System.arraycopy(omega,0,hashedOmega,0,current+1);
    System.arraycopy(subterm,0,hashedSubterm,0,current+1);
    return (current+1) * Arrays.hashCode(hashedOmega) * Arrays.hashCode(hashedSubterm);
  }

  public int getStatus() { return status; } 

  public void setStatus(int s) { this.status = s; }

  /**
   * get the current root
   * @return the current root
   */
  public Visitable getRoot() {
    return subterm[0];
  }

  /**
   * set the current root
   */
  public void setRoot(Visitable root) {
    this.subterm[0] = root;
  }

  /**
   * get the current stack
   */
  public Vector<Visitable> getCurrentStack() {
    Vector<Visitable> v = new Vector<Visitable>();
    for (int i=0;i<depth();i++) {
      v.add(subterm[i]);
    }
    return v;
  }

  /**
   * get the term that corresponds to the current position
   * @return the current term
   */
  public Visitable getSubject() {
    return subterm[current];
  }

  /**
   * set the current term
   */
  public void setSubject(Visitable root) {
    //System.out.println("setsubject "+root);
    //System.out.println("in the env "+this);
    this.subterm[current] = root;
  }

  /**
   * get the current sub-position
   * @return the current sub-position
   */
  private int getSubOmega() {
    return omega[current];
  }

  /**
   * get the current position
   * @return the current position
   */
  public Position getPosition() {
    int[] reducedOmega = new int[depth()];
    System.arraycopy(omega,1,reducedOmega,0,depth());
    return new Position(reducedOmega);
  }



  /**
   * Get the depth of the position in the tree
   * @return depth on the position
   */
  public int depth() {
    return current;
  }

  /**
   * remove the last sub-position
   * Up and down are made public to allow to write compiled strategies by hand
   * This may be a BAD idea
   */
  public void up() {
    //System.out.println("before up: " + this);
    int childIndex = omega[current]-1;
    Visitable child = subterm[current];
    current--;
    subterm[current] = subterm[current].setChildAt(childIndex,child);
    //System.out.println("after up: " + this);
  }

  /**
   * package private
   * remove the last sub-position but does not update the subject
   */
  public void upLocal() {
    current--;
  }

  /**
   * add a sub-position n
   *
   * @param n sub-position number. 1 is the first possible sub-position
   */
  public void down(int n) {
    //System.out.println("before down: " + this);
    if(n>0) {
      Visitable child = subterm[current];
      current++;
      if(current == omega.length) {
        ensureLength(current+1);
      }
      omega[current] = n;
      subterm[current] = child.getChildAt(n-1);
    }
    //System.out.println("after down: " + this);
  }

  public void followPath(Path path) {
    Path normalizedPath = path.getCanonicalPath();
    int length = normalizedPath.length();
    for(int i=0;i<length;i++) {
      int head = normalizedPath.getHead();
      normalizedPath = normalizedPath.getTail();
      if(head>0){
        down(head);
        if(subterm[current] instanceof Path && !(normalizedPath.length()==0)) {
          // we do not want to follow the last reference
          followPath((Path)subterm[current]);
        }
      } else {
        //verify that getsubomega() = -head
        up();
      }
    }
  }

  public void followPathLocal(Path path) {
    Path normalizedPath = path.getCanonicalPath();
    int length = normalizedPath.length();
    for(int i=0;i<length;i++) {
      int head = normalizedPath.getHead();
      normalizedPath = normalizedPath.getTail();
      if(head>0){
        down(head);
        if (subterm[current] instanceof Path && !(normalizedPath.length()==0)) {
          // we do not want to follow the last reference
          followPath((Path)subterm[current]);
        }
      } else {
        //verify that getsubomega() = -head
        upLocal();
      }
    }
  }


  /**
   * Returns a <code>String</code> object representing the position.
   * The string representation consists of a list of elementary positions
   *
   * @return a string representation of this position
   */
  public String toString() {
    StringBuilder r = new StringBuilder("[");
    for(int i=0 ; i<current+1 ; i++) {
      r.append(omega[i]);
      if(i<current) {
        r.append(", ");
      }
    }
    r.append("]");

    r.append("\n[");

    for(int i=0 ; i<current+1 ; i++) {
      r.append(subterm[i]);
      if(i<current) {
        r.append(", ");
      }
    }
    r.append("]");
    return r.toString();
  }
}
