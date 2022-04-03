package ru.job4j.collection.list;

import org.w3c.dom.Node;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {
    private E[] container;
    private int modCount;
    private int size;
    private E last;
    private E first;
    private E prev;
    private E next;

    public SimpleLinkedList() {
        this.container = (E[]) new Object[size];
    }

    private void setContainerLengths(int size) {
        if (container.length == 0) {
            container = Arrays.copyOf(container, 3);
        } else if (size == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    private void addNode(E value) {
        container[size] = value;
        if (size == 0) {
            first = container[size];
            last = container[size];
            prev = null;
            next = null;
        } else {
            last = container[container.length - 1];
            prev = container[size - 1];
            next = null;
        }
    }

    @Override
    public void add(E value) {
        setContainerLengths(size);
        addNode(value);
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public Iterator iterator() {
        return new Iterator<E>() {
            private int index = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }
}
