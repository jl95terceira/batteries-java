package jl95.lang;

import static jl95.lang.SuperPowers.*;

public class SuperPowersTest {

    @org.junit.Test public void test_any() {
        
        org.junit.Assert.assertTrue (I(true,  true,  true,  true,  true) .any(x -> x)); /* all */
        org.junit.Assert.assertFalse(I(false, false, false, false, false).any(x -> x)); /* none */
        org.junit.Assert.assertTrue (I(true,  false, false, false, false).any(x -> x)); /* 1st */
        org.junit.Assert.assertTrue (I(true,  false, false, false, true) .any(x -> x)); /* last */
        org.junit.Assert.assertTrue (I(false, false, false, true , false).any(x -> x)); /* somewhere */
    }
    @org.junit.Test public void test_all() {
        
        org.junit.Assert.assertFalse(I(false, false, false, false, false).all(x -> x)); /* none */
        org.junit.Assert.assertTrue (I(true,  true,  true,  true,  true) .all(x -> x)); /* all */
        org.junit.Assert.assertFalse(I(false, true,  true,  true,  true) .all(x -> x)); /* not 1st */
        org.junit.Assert.assertFalse(I(true,  true,  true,  true,  false).all(x -> x)); /* not last */
        org.junit.Assert.assertFalse(I(true,  true,  true,  false, true) .all(x -> x)); /* not somewhere */
    }
    @org.junit.Test public void test_range  () {
        
        java.util.List<Integer> list     = new java.util.ArrayList<>();
        int                     n        = 5;
        for (Integer i = 0; i < n; i++) { list.add(i); }
        java.util.Iterator<Integer> range_it = I.range(n).iterator();
        for (Integer i: list)           { org.junit.Assert.assertEquals(i, range_it.next()); }
            list.clear();
        int a        = 2;
        for (Integer i = a; i < a+n; i++) { list.add(i); }
            range_it = I.range(n).map(i -> a + i).iterator();
        for (Integer i: list)           { org.junit.Assert.assertEquals(i, range_it.next()); }
    }
    @org.junit.Test public void test_reduce () {
        
        org.junit.Assert.assertEquals(Integer.valueOf(15),  I.range(5).map(i -> 1 + i).reduce(0,  (a, b) -> a + b));
        org.junit.Assert.assertEquals(Integer.valueOf(120), I.range(5).map(i -> 1 + i).reduce(1,  (a, b) -> a * b));
        org.junit.Assert.assertEquals("abc",                I("a", "b", "c").reduce("", (s, c) -> s + c));
        /* repeatable */
        I<String> ii = I("a", "b", "c");
        for (int i = 0; i < 10; i++) {
            
            org.junit.Assert.assertEquals("abc", ii.reduce("", (c, s) -> c+s));
        }
    }
    @org.junit.Test public void test_apply  () {
        
        org.junit.Assert.assertEquals(java.util.Arrays.asList("a", "b", "c"), I("a", "b", "c")
                .apply(new java.util.LinkedList<>(), (x, l) -> l.add(x)));
    }
    @org.junit.Test public void test_map    () {
        
        org.junit.Assert.assertEquals(java.util.Arrays.asList(1, 2, 42), I("1", "2", "42")
                .map(s -> Integer.valueOf(s))
                .to(new java.util.LinkedList<>()));
        org.junit.Assert.assertEquals(java.util.Arrays.asList("A", "B", "C"), I("a", "b", "c")
                .map(s -> s.toUpperCase())
                .to(new java.util.LinkedList<>()));
        /* repeatable */
        I<String> ii = I("a", "b", "c").map(s -> s);
        for (int i = 0; i < 10; i++) {
            
            org.junit.Assert.assertEquals(3, ii.toList().size());
        }
    }
    @org.junit.Test public void test_flatmap() {
        
        org.junit.Assert.assertEquals(java.util.Arrays.asList("a", "b", "c", "d"), I(I("a", "b"), I("c", "d"))
                .flatmap(x -> x)
                .to(new java.util.LinkedList<>()));
        org.junit.Assert.assertEquals(java.util.Arrays.asList("a", "b"), I(I("a", "b"), I())
                .flatmap(x -> x)
                .to(new java.util.LinkedList<>()));
        org.junit.Assert.assertEquals(java.util.Arrays.asList("c", "d"), I(I(), I("c", "d"))
                .flatmap(x -> x)
                .to(new java.util.LinkedList<>()));
        org.junit.Assert.assertEquals(java.util.Arrays.asList("e", "f"), I(I(), I(), I("e", "f"))
                .flatmap(x -> x)
                .to(new java.util.LinkedList<>()));
        org.junit.Assert.assertEquals(java.util.Arrays.asList("a", "b", "e", "f"), I(I("a","b"), I(), I("e", "f"))
                .flatmap(x -> x)
                .to(new java.util.LinkedList<>()));
        /* repeatable */
        I<String> ii = I(I("a", "b"), I("c", "d")).flatmap(ss -> ss);
        for (int i = 0; i < 10; i++) {
            
            org.junit.Assert.assertEquals(4, ii.toList().size());
        }
    }
    @org.junit.Test public void test_filter () {
        
        org.junit.Assert.assertEquals(java.util.Arrays.asList("a", "b", "c"), I("a", "b", "c")
                .filter(c -> true)
                .to(new java.util.LinkedList<>()));
        org.junit.Assert.assertEquals(new java.util.HashSet<>(java.util.Arrays.asList("a", "b", "c")), I("a", "b", "c")
                .filter(c -> true)
                .to(new java.util.HashSet<>()));
        org.junit.Assert.assertEquals(java.util.Arrays.asList("a", "b"), I("a", "b", "c")
                .filter(c -> !c.equals("c"))
                .to(new java.util.LinkedList<>()));
        org.junit.Assert.assertEquals(java.util.Arrays.asList("a", "b", "e"), I("a", "b", "c", "d", "e")
                .filter(c -> !c.equals("c") && !c.equals("d"))
                .to(new java.util.LinkedList<>()));
        /* repeatable */
        I<String> ii = I("a", "b", "c").filter(s -> true);
        for (int i = 0; i < 10; i++) {
            
            org.junit.Assert.assertEquals(3, ii.toList().size());
        }
    }
    @org.junit.Test public void test_enumer () {
        
        org.junit.Assert.assertEquals(List(
                tuple(0, "a"), tuple(1, "b"), tuple(2, "c")
        ),      I(       "a",           "b",           "c").enumer
                 (0).toList());
        org.junit.Assert.assertEquals(List(
                tuple(0, "a"), tuple(1, "b"), tuple(2, "c")
        ),      I(       "a",           "b",           "c").enumer
                 ( ).toList());
        org.junit.Assert.assertEquals(List(
                tuple(3, "d"), tuple(4, "e"), tuple(5, "f")
        ),      I(       "d",           "e",           "f").enumer
                 (3).toList());
    }
    @org.junit.Test public void test_group  () {
        
        org.junit.Assert.assertEquals(
                
                Map(tuple("b", List("bcd", "bbb", "bcd")),
                    tuple("a", List("abc", "aaa"))),
                I.group((String s) -> s.substring(0, 1), I(
                                    "abc", "aaa", 
                                    "bcd", "bbb", "bcd"))
        );
        org.junit.Assert.assertEquals(
                
                Map(tuple(0, List("")),
                    tuple(1, List("a", "d")),
                    tuple(2, List("cc")),
                    tuple(3, List("bbb", "aaa"))),
                I.group((String s) -> s.length(), I("a", "bbb", "cc", "", "aaa", "d"))
        );
    }
    @org.junit.Test public void test_zip    () {
        
        org.junit.Assert.assertEquals(
                
                List(List("foo","coiso","z"), 
                     List("bar","42",   "z")),
                I.zip(I("foo",  "bar"), 
                      I("coiso","42"), 
                      I("z",    "z")).toList()
        );
        org.junit.Assert.assertEquals(
                
                List(List("foo","coiso","z"), 
                     List("bar", 42 ,   "z")),
                I.zip(I("foo",  "bar"), 
                      I("coiso", 42 ), 
                      I("z",    "z")).toList()
        );
    }
}
