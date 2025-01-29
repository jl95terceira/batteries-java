package jl95.serdes;

import jl95.lang.variadic.*;
import jl95.lang.*;

/**
 * The reverter for {@link BooleanToJson}.
 */
public class BooleanFromJson {
    
    /*pack.*/ static final java.util.Map<javax.json.JsonValue,Boolean> _m = I.of   (BooleanToJson._m.entrySet())
                                                                           .toMap(e -> e.getValue(), e -> e.getKey());
    
    public static Function1<Boolean, javax.json.JsonValue> get() { return _m::get; }
}
