package jl95terceira.lang;

import java.util.Objects;

/**
 * A simple class that holds a value.
 * This class is intended to work around Java's lack of support for keyword / named arguments.
 * 
 * The intented use is that
 * <ol>
 * <li>When we want a keyword argument, we create a class that extends this one.</li>
 * <li>We implement the new class' constructor to simply call this class' constructor i.e. it calls {@code super(T value)}.</li>
 * <li>We use the new class as an argument type <b>once at most</b>, in a function's signature.</li>
 * </ol>
 * 
 * Methods {@link #hashCode()} and {@link #equals(java.lang.Object)} are implemented so that, if the other object:
 <ul>
 <li>is of a different class or has a different value, it is not equal.</li>
 <li>is of the same class and has the same value, it is equal and its hash code is equal.</li>
 </ul>
 * @param <T> primitive type
 */
public class Value<T> {
    
    /**
     * primitive value
     */ 
    public final T value;
    
    /**
     * 
     * @param value initial value
     */
    public Value(T value) {this.value = value;}
    
    @Override public final boolean equals  (Object obj) {if (this == obj) {return true;}if (obj == null) {return false;}if (getClass() != obj.getClass()) {return false;}final Value<?> other = (Value<?>) obj;return Objects.equals(this.value, other.value);}
    @Override public final int     hashCode()           {int hash = 1;hash += Objects.hashCode(this.value);return hash;}
    @Override public final String  toString()           {return String.format("%s(%s)", this.getClass().getName(), this.value);}
}
