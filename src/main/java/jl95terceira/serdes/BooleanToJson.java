package jl95terceira.serdes;

import static jl95terceira.lang.stt.*;
import jl95terceira.lang.variadic.*;

/**
 * A simple converter from bool to JSON. 
 */
public class BooleanToJson {
    
    /*pack.*/ static final java.util.Map<Boolean,javax.json.JsonValue> _m = Map(tuple(true,  javax.json.JsonValue.TRUE), 
                                                                                tuple(false, javax.json.JsonValue.FALSE));

    public static Function1<javax.json.JsonValue, Boolean> get() { return _m::get; }
}
