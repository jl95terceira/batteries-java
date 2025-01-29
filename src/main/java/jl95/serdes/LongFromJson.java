package jl95.serdes;

import jl95.lang.variadic.*;

/**
 * The reverter for {@link jl95.serdes.LongToJson}.
 */
public class LongFromJson {
    
    public static Function1<Long, javax.json.JsonValue> get() { return b -> ((javax.json.JsonNumber) b).longValueExact(); }
}
