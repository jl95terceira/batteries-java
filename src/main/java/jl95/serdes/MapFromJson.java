package jl95.serdes;

import jl95.lang.variadic.*;

/**
 * A simple reverter from a JSON object to a map. 
 * The keys must be convertible to {@link java.lang.String}s. 
 */

public class MapFromJson {

    public static <K, V> Function1<java.util.Map<K, V>, javax.json.JsonValue> get(Function1<K, String>               key_fc,
                                                                                  Function1<V, javax.json.JsonValue> value_fc) { return b -> {
        
        javax.json.JsonObject b_jo = b.asJsonObject();
        java.util.Map<K, V>   m    = new java.util.HashMap<>(b_jo.size());
        for (java.util.Map.Entry<String, javax.json.JsonValue> e: b_jo.entrySet()) {
            
            m.put(key_fc.call(e.getKey()), value_fc.call(e.getValue()));
        }
        return m;
    }; }
}
