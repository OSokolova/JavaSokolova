package ru.academits.sokolova.ArrayList;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private E[] values;
    private int length;

    public ArrayList(E[] values) {
        this.values = Arrays.copyOf(values, values.length * 2);
        this.length = values.length;

    }

    public ArrayList(int capacity) {
        this.values = (E[]) new Object[capacity * 2];
        this.length = 0;
    }

    @Override
    public boolean add(E e) {
        if (length >= values.length) {
            increaseCapacity();
        }
        values[length] = e;
        length++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (contains(o)) {
            System.arraycopy(values, indexOf(o) + 1, values, indexOf(o), length - indexOf(o) - 1);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] array = c.toArray();
        if (values.length - length <= c.size()) {
            values = Arrays.copyOf(values, values.length + c.size() * 2);
            System.arraycopy(array, 0, values, length, c.size());
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            if (contains(o)) {
                remove(o);
            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int i = 0;
        for (Object o : c) {
            if (contains(o)) {
                add((E) o);
                i++;
            }
        }
        values = Arrays.copyOfRange(values, length, length + i);
        return false;
    }

    @Override
    public void clear() {
        values = Arrays.copyOf(values, 0);
    }

    @Override
    public void add(int index, E e) {
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс вышел за границы списка");
        }
        if (index == length) {
            increaseCapacity();
        }
        values[index] = e;
        length++;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; i++) {
            if (values[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = 0;
        for (int i = 0; i < length; i++) {
            if (values[i].equals(o)) {
                lastIndex = i;
            }
        }
        if (lastIndex == 0) {
            return -1;
        }
        return lastIndex;
    }

    @Override
    public E remove(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс вышел за границы списка");
        }
        E removed = values[index];
        if (index < length - 1) {
            System.arraycopy(values, index + 1, values, index, length - index - 1);
        }
        length--;
        return removed;
    }

    @Override
    public E get(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс вышел за границы списка");
        }
        return values[index];
    }

    @Override
    public E set(int index, E e) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс вышел за границы списка");
        }
        values[index] = e;
        return e;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (E e : values) {
            if (e.equals(o)) {
                return true;
            }
        }
        return false;
    }

    public void increaseCapacity() {
        E[] old = values;
        values = Arrays.copyOf(old, old.length * 2);
    }

    public void ensureCapacity(int newCapacity) {
        if (values.length < newCapacity) {
            values = Arrays.copyOf(values, newCapacity);
        }
    }

    public void trimToSize() {
        if (length < values.length) {
            values = Arrays.copyOf(values, length);
        }
    }

    @Override
    public Iterator<E> iterator() {
        trimToSize();
        return new ArrayIterator(values);
    }

    @Override
    public Object[] toArray() {
        return values;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) values;
    }
}
