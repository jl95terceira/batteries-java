package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

public class MapByStringOfStringToJson {

    public static Function1<javax.json.JsonValue, java.util.Map<String, String>> get() { return MapByStringToJson.get(javax.json.Json::createValue); }
}
