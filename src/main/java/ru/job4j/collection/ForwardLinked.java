package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T deleteValue = head.value;
        Node<T> node = head.next;
        head.value = null;
        head.next = null;
        head = node;
        return deleteValue;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    public void add(T value) {
        Node<T> newNode = new Node<T>(value, null);
        if (head == null) {
            head = newNode;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = newNode;
    }

    public boolean revert() {
        boolean revert;
        if (head == null || head.next == null) {
            revert = false;
        } else {
            Node<T> prev = null;
            Node<T> node = head;
            while (node != null) {
                Node<T> next = node.next;
                node.next = prev;
                prev = node;
                node = next;
            }
            head = prev;
            revert = true;
        }
        return revert;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}