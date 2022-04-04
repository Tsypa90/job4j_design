package ru.job4j.collection.list;

import ru.job4j.collection.ForwardLinked;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {
    private int modCount;
    private int size;
    private Node<E> first;
    private Node<E> last;
    private Node<E> node;

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null);
        if (first == null) {
            first = newNode;
        }
        last = newNode;
        node = first;
        node.next = last;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
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
               /*return index < size;*/
                return node != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                /*return get(index++);*/
                E value = node.item;
                node = node.next;
                return value;
            }
        };
    }
}
