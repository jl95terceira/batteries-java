package jl95terceira.serdes;

import jl95terceira.lang.variadic.*;

public class ListOfLongToJson {

    public static Function1<javax.json.JsonValue, Iterable<Long>> get() { return ListToJson.get(javax.json.Json::createValue); }
}
