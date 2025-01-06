package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

/**
 * A simple converter from a map to a JSON object. 
 * The keys must be convertible to {@link java.lang.String}s. 
 */

public class MapToJson {

    public static <K, V> Function1<javax.json.JsonValue, java.util.Map<K, V>> get(Function1<String, K>               key_fc,
                                                                                  Function1<javax.json.JsonValue, V> value_fc) { return a -> {
    
        javax.json.JsonObjectBuilder jo = javax.json.Json.createObjectBuilder();
        for (java.util.Map.Entry<K, V> e: a.entrySet()) {
            
            jo.add(key_fc.call(e.getKey()), value_fc.call(e.getValue()));
        }
        return jo.build();
    }; }
}
