package jl95.serdes;

import jl95.lang.variadic.*;

/**
 * The reverter for {@link IntegerToJson}.
 */
public class IntegerFromJson {
    
    public static Function1<Integer, javax.json.JsonValue> get() { return b -> ((javax.json.JsonNumber) b).intValueExact(); }
}
