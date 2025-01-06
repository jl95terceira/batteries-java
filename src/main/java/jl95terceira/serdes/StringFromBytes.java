package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

/**
 * A simple converter from string to bytes. 
 */
public class StringFromBytes {

    public static Function1<String, byte[]> get(java.nio.charset.Charset chs) { return b -> new String(b, chs); }
}
