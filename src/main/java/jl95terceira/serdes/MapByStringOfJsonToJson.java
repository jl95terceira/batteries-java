package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

public class MapByStringOfJsonToJson {

    public static Function1<javax.json.JsonValue, java.util.Map<String, javax.json.JsonValue>> get() { return MapByStringToJson.get(j -> j); }
}
