package jl95.lang;

import static java.lang.String.format;
import java.util.Iterator;
import jl95.lang.variadic.*;

/**
 * An abstract class to help implement data classes.
 * By returning the supposed attributes of the data object from the implementation of the abstract method ({@link #data()}), then the methods {@link #hashCode()}, {@link #equals(Object)} and {@link #toString()} are implemented out-of-the-box so that
 * <ul>
 * <li>2 objects of different data classes are not equal.</li>
 * <li>2 objects of the same data class and with the same attributes are equal and have the same hash code.</li>
 * </ul>
 */
public abstract class NamedDataClass extends DataClass {

    protected abstract Iterable<Tuple2<String, ?>> namedData();

    @Override protected Iterable<?> data() {
        var namedDataIterator = namedData().iterator();
        return () -> new Iterator<>() {

            @Override public boolean hasNext() { return namedDataIterator.hasNext(); }
            @Override public Object  next   () { return namedDataIterator.next   ().a2; }
        };
    }
    @Override protected String dataRepr() { return String.join(", ", I.of (namedData())
                                                         .map(o -> format("%s = %s", o.a1, o.a2))); }
}
