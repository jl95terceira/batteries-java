package jl95.lang;

import static jl95.lang.SuperPowers.*;
import static org.junit.Assert.*;

import java.util.Iterator;

public class SuperPowersTest {

    @org.junit.Test public void testStrictList() {

        var s = function(() -> strict(List(1,2,3)));
        assertEquals   (s.apply(), s.apply());
        assertNotEquals(s.apply(), strict(List(4,5,6)));
        assertNotEquals(s.apply(), strict(List(1,2)));
        assertNotEquals(s.apply(), strict(List(1,3,2)));
        assertNotEquals(s.apply(), strict(List(2,3)));
        assertNotEquals(s.apply(), strict(List()));
        assertNotNull(s.toString());
    }
    @org.junit.Test public void testStrictSet() {

        var s = function(() -> strict(Set(1,2,3)));
        assertEquals   (s.apply(), s.apply());
        assertNotEquals(s.apply(), strict(Set(1,2)));
        assertNotEquals(s.apply(), strict(Set(2,3)));
        assertNotNull(s.toString());
    }
    @org.junit.Test public void testStrictMap() {

        var s = function(() -> strict(Map(tuple("a",1),tuple("b",2),tuple("c",3))));
        assertEquals   (s.apply(), s.apply());
        assertEquals   (s.apply(), strict(Map(tuple("a",1),tuple("c",3),tuple("b",2))));
        assertNotEquals(s.apply(), strict(Map(tuple("a",1),tuple("b",2),tuple("c",2))));
        assertNotEquals(s.apply(), strict(Map(tuple("a",1),tuple("a",2),tuple("c",3))));
        assertNotNull(s.toString());
    }
    @org.junit.Test public void testAny() {
        
        assertTrue (I(true,  true,  true,  true,  true) .any(x -> x)); /* all */
        assertFalse(I(false, false, false, false, false).any(x -> x)); /* none */
        assertTrue (I(true,  false, false, false, false).any(x -> x)); /* 1st */
        assertTrue (I(true,  false, false, false, true) .any(x -> x)); /* last */
        assertTrue (I(false, false, false, true , false).any(x -> x)); /* somewhere */
    }
    @org.junit.Test public void testAll() {
        
        assertFalse(I(false, false, false, false, false).all(x -> x)); /* none */
        assertTrue (I(true,  true,  true,  true,  true) .all(x -> x)); /* all */
        assertFalse(I(false, true,  true,  true,  true) .all(x -> x)); /* not 1st */
        assertFalse(I(true,  true,  true,  true,  false).all(x -> x)); /* not last */
        assertFalse(I(true,  true,  true,  false, true) .all(x -> x)); /* not somewhere */
    }
    @org.junit.Test public void testRange  () {

        java.util.List<Integer> list = new java.util.ArrayList<>();
        int n = 100;
        for (Integer i = 0; i < n; i++) {
            list.add(i);
        }
        java.util.Iterator<Integer> range_it = Iterable.range(n).iterator();
        for (Integer i: list) {
            assertEquals(i, range_it.next());
        }
        list.clear();
        int a = 42;
        for (Integer i = a; i < a+n; i++) {
            list.add(i);
        }
        range_it = Iterable.range(n).map(i -> a + i).iterator();
        for (Integer i: list) {
            assertEquals(i, range_it.next());
        }
    }
    @org.junit.Test public void testReduce () {
        
        assertEquals(Integer.valueOf(15),  Iterable.range(5).map(i -> 1 + i).reduce(0,  (a, b) -> a + b));
        assertEquals(Integer.valueOf(120), Iterable.range(5).map(i -> 1 + i).reduce(1,  (a, b) -> a * b));
        assertEquals("abc",                I("a", "b", "c").reduce("", (s, c) -> s + c));
        /* repeatable */
        Iterable<String> ii = I("a", "b", "c");
        for (int i = 0; i < 10; i++) {
            
            assertEquals("abc", ii.reduce("", (c, s) -> c+s));
        }
    }
    @org.junit.Test public void testApply  () {
        
        assertEquals(java.util.Arrays.asList("a", "b", "c"), I("a", "b", "c")
                .apply(new java.util.LinkedList<>(), (x, l) -> l.add(x)));
    }
    @org.junit.Test public void testMap    () {
        
        assertEquals(java.util.Arrays.asList(1, 2, 42), I("1", "2", "42")
                .map(s -> Integer.valueOf(s))
                .to(new java.util.LinkedList<>()));
        assertEquals(java.util.Arrays.asList("A", "B", "C"), I("a", "b", "c")
                .map(s -> s.toUpperCase())
                .to(new java.util.LinkedList<>()));
        /* repeatable */
        Iterable<String> ii = I("a", "b", "c").map(s -> s);
        for (int i = 0; i < 10; i++) {
            
            assertEquals(3, ii.toList().size());
        }
    }
    @org.junit.Test public void testFlatmap() {
        
        assertEquals(java.util.Arrays.asList("a", "b", "c", "d"), I(I("a", "b"), I("c", "d"))
                .flatmap(x -> x)
                .to(new java.util.LinkedList<>()));
        assertEquals(java.util.Arrays.asList("a", "b"), I(I("a", "b"), I())
                .flatmap(x -> x)
                .to(new java.util.LinkedList<>()));
        assertEquals(java.util.Arrays.asList("c", "d"), I(I(), I("c", "d"))
                .flatmap(x -> x)
                .to(new java.util.LinkedList<>()));
        assertEquals(java.util.Arrays.asList("e", "f"), I(I(), I(), I("e", "f"))
                .flatmap(x -> x)
                .to(new java.util.LinkedList<>()));
        assertEquals(java.util.Arrays.asList("a", "b", "e", "f"), I(I("a","b"), I(), I("e", "f"))
                .flatmap(x -> x)
                .to(new java.util.LinkedList<>()));
        /* repeatable */
        Iterable<String> ii = I(I("a", "b"), I("c", "d")).flatmap(ss -> ss);
        for (int i = 0; i < 10; i++) {
            
            assertEquals(4, ii.toList().size());
        }
    }
    @org.junit.Test public void testFilter () {
        
        assertEquals(java.util.Arrays.asList("a", "b", "c"), I("a", "b", "c")
                .filter(c -> true)
                .to(new java.util.LinkedList<>()));
        assertEquals(new java.util.HashSet<>(java.util.Arrays.asList("a", "b", "c")), I("a", "b", "c")
                .filter(c -> true)
                .to(new java.util.HashSet<>()));
        assertEquals(java.util.Arrays.asList("a", "b"), I("a", "b", "c")
                .filter(c -> !c.equals("c"))
                .to(new java.util.LinkedList<>()));
        assertEquals(java.util.Arrays.asList("a", "b", "e"), I("a", "b", "c", "d", "e")
                .filter(c -> !c.equals("c") && !c.equals("d"))
                .to(new java.util.LinkedList<>()));
        /* repeatable */
        Iterable<String> ii = I("a", "b", "c").filter(s -> true);
        for (int i = 0; i < 10; i++) {
            
            assertEquals(3, ii.toList().size());
        }
    }
    @org.junit.Test public void testEnumer () {
        
        assertEquals(List(
                tuple(0, "a"), tuple(1, "b"), tuple(2, "c")
        ),      I(       "a",           "b",           "c").enumerate
                 (0).toList());
        assertEquals(List(
                tuple(0, "a"), tuple(1, "b"), tuple(2, "c")
        ),      I(       "a",           "b",           "c").enumerate
                 ( ).toList());
        assertEquals(List(
                tuple(3, "d"), tuple(4, "e"), tuple(5, "f")
        ),      I(       "d",           "e",           "f").enumerate
                 (3).toList());
    }
    @org.junit.Test public void testGroup  () {
        
        assertEquals(
                
                Map(tuple("b", List("bcd", "bbb", "bcd")),
                    tuple("a", List("abc", "aaa"))),
                Iterable.group((String s) -> s.substring(0, 1), I(
                                    "abc", "aaa", 
                                    "bcd", "bbb", "bcd"))
        );
        assertEquals(
                
                Map(tuple(0, List("")),
                    tuple(1, List("a", "d")),
                    tuple(2, List("cc")),
                    tuple(3, List("bbb", "aaa"))),
                Iterable.group((String s) -> s.length(), I("a", "bbb", "cc", "", "aaa", "d"))
        );
    }
    @org.junit.Test public void testZip    () {
        
        assertEquals(
                
                List(List("foo","coiso","z"), 
                     List("bar","42",   "z")),
                Iterable.zip(I("foo",  "bar"),
                      I("coiso","42"), 
                      I("z",    "z")).toList()
        );
        assertEquals(
                
                List(List("foo","coiso","z"), 
                     List("bar", 42 ,   "z")),
                Iterable.zip(I("foo",  "bar"),
                      I("coiso", 42 ), 
                      I("z",    "z")).toList()
        );
    }
    @org.junit.Test public void testCycle  () {
        Iterator<String> it;
        it = I("a","b","c").cycle().iterator();
        for (String c: I("a", "b", "c",
                      "a", "b", "c",
                      "a", "b", "c",
                      "a", "b", "c")) {
            assertTrue(it.hasNext());
            assertEquals(c, it.next());
        }
        it = I("a").cycle().iterator();
        for (String c: I("a", "a", "a",
                      "a", "a", "a",
                      "a", "a", "a")) {
            assertTrue(it.hasNext());
            assertEquals(c, it.next());
        }
        for (int i=0; i < 10000; i++) {
            assertTrue(it.hasNext());
            it.next();
        }
    }
}
