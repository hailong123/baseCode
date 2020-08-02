package com.hl.list;

public abstract class AbstractList<E> implements List<E> {

    public int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    public void add(E element) {
        add(size, element);
    }

    public void rangeCheck(int index) {
        if (index >= size || index < 0) {
            outOfBounds(index);
        }
    }

    public void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            outOfBounds(index);
        }
    }

    public void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + "size:" + size);
    }

}
