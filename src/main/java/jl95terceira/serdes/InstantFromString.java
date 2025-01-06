package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

/**
 * The counterpart to {@link InstantToString}.
 */
public class InstantFromString {
    
    public static Function1<java.time.Instant, String> get() { return java.time.Instant::parse; }
}
