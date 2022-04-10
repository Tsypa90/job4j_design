package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    public boolean isEmpty(K key) {
        return table[indexFor(hash(key.hashCode()))] == null;
    }

    @Override
    public boolean put(K key, V value) {
        if (count > (int) (capacity * LOAD_FACTOR)) {
            expand();
        }
        boolean result = isEmpty(key);
        if (isEmpty(key)) {
            table[indexFor(hash(key.hashCode()))] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash % (capacity - 1);
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] tempTable = new MapEntry[capacity];
        int index = 0;
        for (MapEntry<K, V> elementOne : table) {
            if (elementOne != null) {
                tempTable[index++] = elementOne;
                elementOne = null;
            }
        }
        count = 0;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> elementTwo : tempTable) {
            if (elementTwo != null) {
                put(elementTwo.key, elementTwo.value);
            }
        }
    }

    @Override
    public V get(K key) {
        return table[indexFor(hash(key.hashCode()))] != null ? table[indexFor(hash(key.hashCode()))].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        if (!isEmpty(key)) {
            table[indexFor(hash(key.hashCode()))] = null;
            modCount++;
            count--;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int index = 0;
            private int elements = 0;
            private int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return elements < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                K result = null;
                for (int  i = index; i < table.length; i++) {
                    index++;
                    if (table[i] != null) {
                        result = table[i].key;
                        elements++;
                        break;
                    }
                }
                return result;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
