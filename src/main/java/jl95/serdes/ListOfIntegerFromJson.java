package jl95.serdes;

import jl95.lang.variadic.*;

public class ListOfIntegerFromJson {
    
    public static Function1<java.util.List<Integer>, javax.json.JsonValue> get() { return ListFromJson.get(j -> ((javax.json.JsonNumber) j).intValueExact()); }
}
