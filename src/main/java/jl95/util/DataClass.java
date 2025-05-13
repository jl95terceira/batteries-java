package jl95.util;

import static java.lang.String.format;

import java.util.Objects;
import jl95.lang.I;

/**
 * An abstract class to help implement data classes.
 * By returning the supposed attributes of the data object from the implementation of the abstract method ({@link #data()}), then the methods {@link #hashCode()}, {@link #equals(java.lang.Object)} and {@link #toString()} are implemented out-of-the-box so that
 * <ul>
 * <li>2 objects of different data classes are not equal.</li>
 * <li>2 objects of the same data class and with the same attributes are equal and have the same hash code.</li>
 * </ul>
 */
public abstract class DataClass {
    
    protected String dataRepr() { return String.join(", ", I.of (data())
                                                            .map(o -> format("%s", o))); }

    /**
     * define the data i.e the attributes of the data object
     * @return data
     */
    protected abstract Iterable<?> data();
    
    /**
     * (implemented automatically according to the return of {@link #data()})
     * @return hash code
     */
    @Override public int     hashCode() {
        
        return I.of    (this.data())
                .enumer()
                .reduce(1, (hc, t) -> hc + t.a1*Objects.hashCode(t.a2));
    }
    /**
     * (implemented automatically according to the return of {@link #data()})
     * @return whether equal
     */
    @Override public boolean equals  (Object obj) {
        
        if (this        == obj)           return true;
        if (obj         == null)          return false;
        if (getClass() != obj.getClass()) return false;
        final DataClass other = (DataClass) obj;
        return I.all(I.zip(this.data(), other.data())
                      .map(t -> Objects.equals(t.a1, t.a2)));
    }
    /**
     * (implemented automatically according to the return of {@link #data()})
     * @return string representation
     */
    @Override public String  toString() {
        
        return format("%s(%s)", getClass().getName(), dataRepr());
    }
}
