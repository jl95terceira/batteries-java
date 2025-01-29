package jl95.serdes;

import jl95.lang.variadic.*;

public class ListOfLongFromJson {
    
    public static Function1<java.util.List<Long>, javax.json.JsonValue> get() { return ListFromJson.get(j -> ((javax.json.JsonNumber) j).longValueExact()); }
}
