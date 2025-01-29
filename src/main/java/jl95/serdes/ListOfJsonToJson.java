package jl95.serdes;

import jl95.lang.variadic.*;

public class ListOfJsonToJson {

    public static Function1<javax.json.JsonValue, Iterable<javax.json.JsonValue>> get() { return ListToJson.get(j -> j); }
}
