package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

/**
 * The reverter for {@link jl95terceira.data.c.LongToJsonValue}.
 */
public class LongFromJson {
    
    public static Function1<Long, javax.json.JsonValue> get() { return b -> ((javax.json.JsonNumber) b).longValueExact(); }
}
