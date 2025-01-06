package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

/**
 * A simple converter from long to JSON. 
 */
public class LongToJson {
    
    public static Function1<javax.json.JsonValue, Long> get() { return javax.json.Json::createValue; }
}
