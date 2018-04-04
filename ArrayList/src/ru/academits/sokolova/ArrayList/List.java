package ru.academits.sokolova.ArrayList;

import java.util.Collection;

public interface List<E> extends Iterable<E> {

    void add(E e);

    void add(int index, E e);

    void delete(int index);

    int getCapacity();

    int getLength();

    E get(int index);

    void set(int index, E e);

    boolean isEmpty();

    void increaseCapacity();

    void ensureCapacity(int newCapacity);

    void trimToSize();

}
