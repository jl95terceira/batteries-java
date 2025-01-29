package jl95.serdes;

import jl95.lang.variadic.*;

/**
 * A simple converter from long to JSON. 
 */
public class LongToJson {
    
    public static Function1<javax.json.JsonValue, Long> get() { return javax.json.Json::createValue; }
}
