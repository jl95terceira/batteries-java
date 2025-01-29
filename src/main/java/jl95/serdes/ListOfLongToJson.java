package jl95.serdes;

import jl95.lang.variadic.*;

public class ListOfLongToJson {

    public static Function1<javax.json.JsonValue, Iterable<Long>> get() { return ListToJson.get(javax.json.Json::createValue); }
}
