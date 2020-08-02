package com.hl.Circle;

public class CircleQueue<E> {

    private int size;
    private int front;//存储下标
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public CircleQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enQueue(E element) {
        //进行扩容
        ensureCapacity(size + 1);

        elements[index(size)] = element;
        size ++;
    }

    /**
     * 保证要有capacity的容量
     * @param capacity
     */
    private void ensureCapacity(int capacity) {

        int oldCapacity = elements.length;

        if (oldCapacity >= capacity) return;

        int newCapacity = oldCapacity << 1;

        System.out.println("扩容前的容量"+ oldCapacity);
        System.out.println("扩容后的容量"+ newCapacity);

        E[] newElements = (E[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newElements[i] = elements[index(i)];
        }

        front    = 0;
        elements = newElements;
    }

    public E deQueue() {
        E frontElement  = elements[front];
        elements[front] = null;
        front = index(1);
        size --;
        return frontElement;
    }

    public E front() {
        return elements[front];
    }

    //进行索引映射
    private int index(int index) {
//        return (front + index) % elements.length;
        index += front;
        return index - (elements.length > index ? 0 : elements.length);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("capcacity =")
                .append(elements.length)
                .append(" size=")
                .append(size)
                .append(", [");

        for (int i = 0; i < elements.length; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(elements[i]);
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
