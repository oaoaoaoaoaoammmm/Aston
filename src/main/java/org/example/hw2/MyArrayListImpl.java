package org.example.hw2;

import lombok.ToString;

import java.util.*;


@ToString
public class MyArrayListImpl<E> implements MyArrayList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public MyArrayListImpl() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayListImpl(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elements = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elements = new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public MyArrayListImpl(Collection<? extends E> c) {
        Object[] a = c.toArray();
        size = a.length;
        if (size != 0) {
            if (c.getClass() == ArrayList.class) {
                elements = a;
            } else {
                elements = Arrays.copyOf(a, size, Object[].class);
            }
        } else {
            elements = new Object[DEFAULT_CAPACITY];
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E element) {
        return add(size, element);
    }

    @Override
    public boolean add(int index, E element) {
        rangeCheckForAdd(index);
        int temp = size;
        if (index < size) size--;
        if (temp == elements.length) {
            elements = upSize((int) (elements.length * 1.5) + 1);
        }
        elements[index] = element;
        size++;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }
        c.forEach(this::add);
        return true;
    }

    @Override
    public void clear() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public E get(int index) {
        rangeCheckForAdd(index);
        @SuppressWarnings("unchecked")
        E element = (E) elements[index];
        return element;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public E remove(int index) {
        rangeCheckForAdd(index);
        @SuppressWarnings("unchecked")
        E element = (E) elements[index];
        elements[index] = null;
        shiftLeft(index);
        return element;
    }

    @Override
    public boolean remove(Object o) {
        int index = 0;
        found:
        {
            if (o == null) {
                for (; index < size; index++)
                    if (elements[index] == null)
                        break found;
            } else {
                for (; index < size; index++)
                    if (o.equals(elements[index]))
                        break found;
            }
            return false;
        }
        shiftLeft(index);
        return true;
    }

    private void shiftLeft(int index) {
        int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(elements, index + 1, elements, index, newSize - index);
        }
        size = newSize;
        elements[size] = null;
    }

    @Override
    public void sort(Comparator<? super E> comp) {
        if (size == 0 || size == 1) return;
        quickSort(comp, 0, size - 1);
    }

    private void quickSort(Comparator<? super E> comp, int low, int high) {
        if (low < high) {
            int partition = partition(comp, low, high);

            quickSort(comp, low, partition - 1);
            quickSort(comp, partition + 1, high);
        }
    }

    @SuppressWarnings("unchecked")
    private int partition(Comparator<? super E> comp, int low, int high) {
        E pivot = (E) elements[high];

        int j = low - 1;

        for (int i = low; i < high; i++) {
            E element = (E) elements[i];

            if (comp.compare(element, pivot) < 0) {
                j++;
                elements[i] = elements[j];
                elements[j] = element;
            }
        }

        Object temp = elements[j + 1];
        elements[j + 1] = elements[high];
        elements[high] = temp;

        return j + 1;
    }

    private Object[] upSize(int newCapacity) {
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, elements.length);

        return newArray;
    }

    private void rangeCheckForAdd(int index) {
        if (index > elements.length || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyArrayListImpl<?> that = (MyArrayListImpl<?>) o;
        return size == that.size && Arrays.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(elements);
        return result;
    }
}
