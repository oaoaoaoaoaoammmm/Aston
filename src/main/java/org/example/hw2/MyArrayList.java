package org.example.hw2;

import java.util.Collection;
import java.util.Comparator;

public interface MyArrayList<E> {

    int size();

    boolean add(E element);

    boolean add(int index, E element);

    boolean addAll(Collection<? extends E> c);

    void clear();

    E get(int index);

    boolean isEmpty();

    E remove(int index);

    boolean remove(Object o);

    void trimToSize();

    void sort(Comparator<? super E> c);

}
