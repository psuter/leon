package leon.codegen.runtime;

import java.util.Arrays;

public final class Tuple implements Comparable<Tuple> {
  private final Object[] elements;

  // You may think that using varargs here would show less of the internals,
  // however the bytecode is exactly the same, so let's reflect the reality
  // instead.
  public Tuple(Object[] elements) {
    this.elements = Arrays.copyOf(elements, elements.length);
  }

  public final Object get(int index) {
    if(index < 0 || index >= this.elements.length) {
        throw new IllegalArgumentException("Invalid tuple index : " + index);
    }
    return this.elements[index];
  }

  public final int getArity() {
    return this.elements.length;
  }

  @Override
  public boolean equals(Object that) {
    if(that == this) return true;
    if(!(that instanceof Tuple)) return false;
    Tuple other = (Tuple)that;
    if(other.getArity() != this.getArity()) return false;
    for(int i = 0; i < this.getArity(); i++) {
        if(other.get(i) != this.get(i)) return false;
    }
    return true; 
  }

  @Override @SuppressWarnings("unchecked")
  public int compareTo(Tuple other) {
    int arityDiff = getArity() - other.getArity();
    if(arityDiff != 0) {
        return arityDiff;
    }
    for(int i = 0; i < getArity(); i++) {
        Comparable<Object> ce = (Comparable<Object>)get(i);
        int elemComp = ce.compareTo(other.get(i));
        if(elemComp != 0) {
          return elemComp;
        }
    }
    return 0;
  }
}
