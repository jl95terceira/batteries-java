package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

/**
 * A simple converter from {@link java.time.Instant} to String. 
 */
public class InstantToString {
    
    public static Function1<String, java.time.Instant> get() { return t -> t.toString(); }
}
