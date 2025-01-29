package jl95.serdes;

import jl95.lang.variadic.*;

/**
 * A simple converter from int to JSON. 
 */
public class IntegerToJson {
    
    public static Function1<javax.json.JsonValue, Integer> get() { return javax.json.Json::createValue; }
}
