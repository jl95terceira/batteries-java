package jl95terceira.serdes;

public class _Json {
    
    public static javax.json.JsonValue i = get();
    
    private static javax.json.JsonValue get() {
        
        javax.json.JsonObjectBuilder json      = javax.json.Json.createObjectBuilder();
            json.add("foo", 123);
            json.add("bar", "abc");
            json.add("The answer is 42.", true);
        javax.json.JsonArrayBuilder  a         = javax.json.Json.createArrayBuilder();
            a.add(123);
            a.add("abc");
            a.add(true);
            a.addNull();
        return json.build();
    }
    
    @org.junit.Test public void test() {
        
        org.junit.Assert.assertEquals(i, i);
    }
}
