package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private boolean isEmpty(K key) {
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
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        newTable = table;
        table = new MapEntry[capacity];
        count = 0;
        for (MapEntry<K, V> value : newTable) {
            if (value != null) {
                put(value.key, value.value);
            }
        }
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key.hashCode()));
        return table[index] != null && (table[index].key.hashCode()
                == key.hashCode() && (Objects.equals(key, table[index].key)))
                ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = indexFor(hash(key.hashCode()));
        if (!isEmpty(key) && (table[index].key.hashCode() == key.hashCode()
                && (Objects.equals(key, table[index].key)))) {
            table[index] = null;
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
            private int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;
            return Objects.equals(key, mapEntry.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

}
