package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

public class StringUTF8ToBytes {

    /*pack.*/ static java.nio.charset.Charset CHARSET = java.nio.charset.Charset.forName("UTF-8");

    public static Function1<byte[], String> get() { return StringToBytes.get(CHARSET); }
}
