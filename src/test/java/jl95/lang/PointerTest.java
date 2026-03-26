package jl95.lang;

public class PointerTest {

    @org.junit.Test
    public void test() {
        var r = new Pointer<String>(null);
        org.junit.Assert.assertNull(r.get());
        r.set("abc");
        org.junit.Assert.assertEquals("abc", r.get());
        var r2 = new Pointer<>("abc");
        org.junit.Assert.assertEquals(r.get(), r2.get());
        org.junit.Assert.assertEquals(r      , r2);
        var r3 = new Pointer<>("def");
        org.junit.Assert.assertNotEquals(r.get(), r3.get());
        org.junit.Assert.assertNotEquals(r      , r3);
    }
    @org.junit.Test
    public void testNull() {
        var r = new Pointer<Void>(null);
        System.out.println(r); // check if toString() does not blow up
    }
}
