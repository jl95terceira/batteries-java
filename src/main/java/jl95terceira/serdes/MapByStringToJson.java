package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

public class MapByStringToJson {

    public static <V> Function1<javax.json.JsonValue, java.util.Map<String, V>> get(Function1<javax.json.JsonValue, V> value_fc) { return MapToJson.get(s -> s, value_fc); }
}
