package jl95.serdes;

import jl95.lang.variadic.*;

public class ListOfJsonFromJson {
    
    public static Function1<java.util.List<javax.json.JsonValue>, javax.json.JsonValue> get() { return ListFromJson.get(j -> j); }
}
