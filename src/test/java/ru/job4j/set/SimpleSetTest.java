package ru.job4j.set;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNullAndNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.add(null));
        assertTrue(set.contains(null));

    }

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenItHasNext() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        Iterator<Integer> it = set.iterator();
        assertTrue(it.hasNext());
    }

    @Test
    public void whenItNextOne() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        Assert.assertEquals(Integer.valueOf(1), it.next());
    }

}