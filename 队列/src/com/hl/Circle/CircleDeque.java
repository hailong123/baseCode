package com.hl.Circle;

public class CircleDeque<E> {

    private int size;
    private int front;//存储头部下标
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public CircleDeque() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 尾部入队
     * @param element
     */
    public void enQueueRear(E element) {
        ensureCapacity(size + 1);
        elements[index(size)] = element;
        size ++;
    }

    /**
     * 头部入队
     * @param element
     */
    public void enQueueFront(E element) {
        ensureCapacity(size + 1);
        front           = index(-1);
        elements[front] = element;
        size++;
    }

    /**
     * 头部出队
     * @return
     */
    public E deQueueFront() {

        E frontElement  = elements[front];
        elements[front] = null;
        front = index(1);
        size --;
        return frontElement;
    }

    /**
     * 尾部出队
     * @return
     */
    public E deQueueRear() {
        int rearIndex = index(size - 1);
        E realElement = elements[rearIndex];
        elements[rearIndex] = null;
        size --;
        return realElement;
    }

    /**
     * 获取头部
     * @return
     */
    public E front() {
        return elements[front];
    }

    /**
     * 获取尾部
     * @return
     */
    public E rear() {
        return elements[index(size - 1)];
    }

    /**
     * 转换下标 (如果是负数 则加上数组长度)
     * @param index 前提条件 
     * @return
     */
    private int index(int index) {
//        index += front;
//        if (index < 0) return index + elements.length;
//        return index % elements.length;
        index += front;
        if (index < 0) {
            return index + elements.length;
        }
        return index - (elements.length > index ? 0 : elements.length);
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
