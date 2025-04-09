package jl95.lang;

public class RefTest {

    @org.junit.Test
    public void test() {
        var r = new Ref<String>();
        org.junit.Assert.assertNull(r.get());
        r.set("abc");
        org.junit.Assert.assertEquals("abc", r.get());
        var r2 = new Ref<>("abc");
        org.junit.Assert.assertEquals(r.get(), r2.get());
        org.junit.Assert.assertEquals(r      , r2);
        var r3 = new Ref<>("def");
        org.junit.Assert.assertNotEquals(r.get(), r3.get());
        org.junit.Assert.assertNotEquals(r      , r3);
    }
    @org.junit.Test
    public void testNull() {
        var r = new Ref<Void>();
        System.out.println(r); // check if toString() does not blow up
    }
}
