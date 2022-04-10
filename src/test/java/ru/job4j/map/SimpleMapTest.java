package ru.job4j.map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMapTest {

    SimpleMap<Integer, String> table;

    @Before
    public void initData() {
        table = new SimpleMap<>();
        table.put(1, "Pavel");
        table.put(2, "Maksim");
        table.put(3, "Olga");
    }

    @Test
    public void whenPutElementAndTrue() {
        Assert.assertTrue(table.put(4, "Andrey"));
    }

    @Test
    public void whenPutElementAndFalse() {
        Assert.assertFalse(table.put(2, "Igor"));
    }

    @Test
    public void whenGetElement() {
        Assert.assertEquals("Pavel", table.get(1));
    }

    @Test
    public void whenGetElementAndNull() {
        Assert.assertNull(table.get(6));
    }

    @Test
    public void whenRemoveByKeyAndTrue() {
        Assert.assertTrue(table.remove(2));
    }

    @Test
    public void whenRemoveInvalidKeyAndFalse() {
        Assert.assertFalse(table.remove(4));
    }

    @Test
    public void whenNextIsOne() {
        Iterator<Integer> it = table.iterator();
        Assert.assertEquals(Integer.valueOf(1), it.next());
    }

    @Test
    public void whenHasNextTrue() {
        Iterator<Integer> it = table.iterator();
        Assert.assertTrue(it.hasNext());
    }

    @Test
    public void whenHasNextFalse() {
        Iterator<Integer> it = table.iterator();
        it.next();
        it.next();
        it.next();
        Assert.assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyListThenNextThrowException() {
        Iterator<Integer> it = table.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenPutAfterGetIteratorThenMustBeException() {
        Iterator<Integer> it = table.iterator();
        table.put(6, "Nasim");
        it.next();
    }
}