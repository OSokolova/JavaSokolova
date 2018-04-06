package ru.academits.sokolova.ArrayList;

import java.util.*;


public class ArrayList<E> implements List<E> {
    private E[] values;
    private int length;
    private int modCount = 0;

    public ArrayList(E[] values) {
        this.values = Arrays.copyOf(values, values.length * 2);
        this.length = values.length;
    }

    public ArrayList(int capacity) {

        //noinspection unchecked
        this.values = (E[]) new Object[capacity * 2];
        this.length = capacity;
    }

    @Override
    public boolean add(E e) {
        if (length >= values.length) {
            increaseCapacity();
        }
        values[length] = e;
        length++;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index != -1) {
            System.arraycopy(values, index + 1, values, index, length - index - 1);
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int startDest = length;
        ensureCapacity(length + c.size());
        System.arraycopy(c, 0, values, startDest, c.size());
        modCount++;
        return (c.size() != 0);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс вышел за границы списка");
        }
        ensureCapacity(length + c.size());
        System.arraycopy(values, index, values, index + c.size(), length - index);
        System.arraycopy(c, 0, values, index, c.size());
        length += c.size();
        modCount++;
        return (c.size() != 0);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o : c) {
            if (remove(o)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        for (E e : values) {
            if (!c.contains(e)) {
                remove(e);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        boolean modified = false;
        for (int i = 0; i < length; i++) {
            if (values[i] != null) {
                values[i] = null;
                modified = true;
            }
        }
        length = 0;
        if (modified) {
            modCount++;
        }
    }

    @Override
    public void add(int index, E e) {
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс вышел за границы списка");
        }
        ensureCapacity(length + 1);
        System.arraycopy(values, index, values, index + 1, length - index);
        values[index] = e;
        modCount++;
    }

    @Override
    public int indexOf(Object o) {

        for (int i = 0; i < length; i++) {
            if (Objects.equals(values[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = length - 1; i >= 0; i--) {
                if (values[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = length - 1; i >= 0; i--) {
                if (values[i].equals(o)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new listItr();
    }


    @Override
    public ListIterator<E> listIterator(int index) {
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Индекс вышел за границы списка");
        }
        return new listItr(index);
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
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
        modCount++;
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
        E oldE = values[index];
        values[index] = e;
        return oldE;
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
        return (indexOf(o) > -1);
    }


    private void increaseCapacity() {
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
    public Object[] toArray() {
        return Arrays.copyOf(values, length);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < length) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(values, length, a.getClass());
        }
        System.arraycopy(values, 0, a, 0, length);
        if (a.length > length) {
            a[length] = null;
        }
        return a;
    }

    private class listItr implements ListIterator<E> {
        private int cursor = -1;
        int expectedModCount = modCount;

        public listItr(int index) {
            super();
            this.cursor = index;
        }

        public listItr() {
            super();
        }

        @Override
        public boolean hasNext() {
            return cursor + 1 < length;
        }

        @Override
        public E next() {
            if (cursor >= length) {
                throw new NoSuchElementException("Список закончился");
            }
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Список был изменен");
            }
            cursor++;
            return values[cursor];
        }

        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        @Override
        public E previous() {
            if (cursor == 0) {
                throw new NoSuchElementException("Список закончился");
            }
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Список был изменен");
            }
            cursor--;
            return values[cursor];
        }

        @Override
        public int nextIndex() {
            if (cursor >= length) {
                throw new NoSuchElementException("Список закончился");
            }
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Список был изменен");
            }
            return cursor + 1;
        }

        @Override
        public int previousIndex() {
            if (cursor == 0) {
                throw new NoSuchElementException("Список закончился");
            }
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Список был изменен");
            }
            return cursor - 1;
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Список был изменен");
            }
            if (cursor >= length || cursor < 0) {
                throw new NoSuchElementException("Список закончился");
            }
            ArrayList.this.remove(cursor);
            expectedModCount = modCount;
        }

        @Override
        public void set(E e) {
            if (cursor >= length || cursor < 0) {
                throw new NoSuchElementException("Список закончился");
            }
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Список был изменен");
            }
            ArrayList.this.set(cursor, e);
        }

        @Override
        public void add(E e) {
            if (cursor >= length || cursor < 0) {
                throw new NoSuchElementException("Список закончился");
            }
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Список был изменен");
            }
            ArrayList.this.add(cursor, e);
            expectedModCount = modCount;
        }
    }

    private class ArrayListIterator implements Iterator<E> {
        int cursor = -1;
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor + 1 < length;
        }

        @Override
        public E next() {
            if (cursor >= length) {
                throw new NoSuchElementException("Список закончился");
            }
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Список был изменен");
            }
            cursor++;
            return values[cursor];
        }
    }
}
