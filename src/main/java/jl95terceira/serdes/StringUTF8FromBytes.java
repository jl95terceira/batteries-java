package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

public class StringUTF8FromBytes {

    /*pack.*/ static java.nio.charset.Charset CHARSET = java.nio.charset.Charset.forName("UTF-8");
    
    public static Function1<String, byte[]> get() { return StringFromBytes.get(CHARSET); }
}
