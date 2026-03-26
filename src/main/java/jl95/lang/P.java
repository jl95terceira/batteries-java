package jl95.lang;

import static jl95.lang.SuperPowers.I;

import jl95.lang.variadic.Function1;
import jl95.util.DataClass;

/**
 *
 * @param <T>
 * @deprecated Please use {@link Pointer}
 */
@Deprecated
public class P<T> extends Pointer<T> {

    public P(T value) {super(value);}
}
