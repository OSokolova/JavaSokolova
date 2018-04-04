package ru.academits.sokolova.ArrayList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    private E[] values;
    private int length;

    public ArrayList(E[] values) {
        this.values = (E[]) new Object[0];
        this.length = values.length;
    }

    public ArrayList(int capacity) {
        this.values = (E[]) new Object[capacity];
        this.length = capacity;
    }

    @Override
    public void add(E e) {
        try {
            if (length >= values.length) {
                increaseCapacity();
            }
            values[length] = e;
            length++;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void add(int index, E e) {
        try {
            if (index >= length) {
                throw new IndexOutOfBoundsException("Индекс вышел за границы списка");
            }
            System.arraycopy(values, index, values, index + 1, length - index + 1);
            values[index] = e;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(int index) {
        try {
            if (index >= length) {
                throw new IndexOutOfBoundsException("Индекс вышел за границы списка");
            }
            if (index < length - 1) {
                System.arraycopy(values, index + 1, values, index, length - index - 1);
            }
            length--;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getCapacity() {
        return values.length;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public E get(int index) {
        if (index >= length) {
            throw new IndexOutOfBoundsException("Индекс вышел за границы списка");
        }
        return values[index];
    }

    @Override
    public void set(int index, E e) {
        if (index >= length) {
            throw new IndexOutOfBoundsException("Индекс вышел за границы списка");
        }
        values[index] = e;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public void increaseCapacity() {
        E[] old = values;
        values = (E[]) new Object[old.length * 2];
        System.arraycopy(old, 0, values, 0, old.length);
    }

    @Override
    public void ensureCapacity(int newCapacity) {
        if (values.length <= newCapacity) {
            E[] old = values;
            values = (E[]) new Object[newCapacity];
            System.arraycopy(old, 0, values, 0, old.length);
            length = newCapacity;
        }
    }

    @Override
    public void trimToSize() {
        if (length < values.length) {
            System.arraycopy(values, 0, values, 0, length);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator<>(values);
    }


}
