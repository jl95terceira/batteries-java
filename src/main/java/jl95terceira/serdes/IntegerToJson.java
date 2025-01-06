package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

/**
 * A simple converter from int to JSON. 
 */
public class IntegerToJson {
    
    public static Function1<javax.json.JsonValue, Integer> get() { return javax.json.Json::createValue; }
}
