package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

/**
 * A simple converter from string to bytes. 
 */
public class StringToBytes {

    public static Function1<byte[], String> get(java.nio.charset.Charset chs) { return a -> a.getBytes(chs); }
}
