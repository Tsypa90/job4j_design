package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (out == null) {
            throw new NoSuchElementException();
        }
        T value = out.pop();
        in.push(value);
        return value;
    }

    public void push(T value) {
        out.push(value);
    }
}
