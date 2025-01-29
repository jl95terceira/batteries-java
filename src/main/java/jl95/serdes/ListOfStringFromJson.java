package jl95.serdes;

import jl95.lang.variadic.*;

public class ListOfStringFromJson {
    
    public static Function1<java.util.List<String>, javax.json.JsonValue> get() { return ListFromJson.get(j -> ((javax.json.JsonString) j).getString()); }
}
