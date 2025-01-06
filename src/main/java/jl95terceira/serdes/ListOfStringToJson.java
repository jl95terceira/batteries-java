package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

public class ListOfStringToJson {

    public static Function1<javax.json.JsonValue, Iterable<String>> get() { return ListToJson.get(javax.json.Json::createValue); }
}
