package jl95.serdes;

import jl95.lang.variadic.*;

public class StringUTF8FromBytes {

    /*pack.*/ static java.nio.charset.Charset CHARSET = java.nio.charset.Charset.forName("UTF-8");
    
    public static Function1<String, byte[]> get() { return StringFromBytes.get(CHARSET); }
}
