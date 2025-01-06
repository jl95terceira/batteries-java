package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

/**
 * The reverter for {@link jl95terceira.data.c.StringToJsonValue}.
 */
public class StringFromJson {
    
    public static Function1<String, javax.json.JsonValue> get() { return b -> ((javax.json.JsonString) b).getString(); }
}
