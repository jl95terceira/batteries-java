package jl95.serdes;

public class MapToJsonValueTest {
    
    @org.junit.Test public void testStrings() {
    
        java.util.Map<String,String> map = new java.util.HashMap<>();
        map.put("foo",    "bar");
        map.put("answer", "42");
        org.junit.Assert.assertEquals(map, MapByStringOfStringFromJson.get().call
                                          (MapByStringOfStringToJson  .get().call(map)));
    }
    @org.junit.Test public void testInts() {
    
        java.util.Map<String,Integer> map = new java.util.HashMap<>();
        map.put("foo",    123);
        map.put("answer", 42);
        org.junit.Assert.assertEquals(map, MapByStringOfIntegerFromJson.get().call
                                          (MapByStringOfIntegerToJson  .get().call(map)));
    }
    @org.junit.Test public void testLongs() {
    
        java.util.Map<String, Long> map = new java.util.HashMap<>();
        map.put("foo",    123L);
        map.put("answer", 42L);
        org.junit.Assert.assertEquals(map, MapByStringOfLongFromJson.get().call
                                          (MapByStringOfLongToJson  .get().call(map)));
    }
}
