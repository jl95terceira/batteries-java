package jl95.serdes;

import jl95.lang.variadic.*;

/**
 * The reverter for {@link jl95.serdes.StringToJson}.
 */
public class StringFromJson {
    
    public static Function1<String, javax.json.JsonValue> get() { return b -> ((javax.json.JsonString) b).getString(); }
}
