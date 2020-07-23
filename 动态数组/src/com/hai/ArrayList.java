package com.hai;

import java.util.Arrays;

public class ArrayList {

    /**
     * 元素的数量
     */
    private int size;

    /**
     * 所有的元素
     */
    private int[] elements;

    private static final int DEFAULT_CAPACITY  = 2;
    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList(int capaticy) {
        capaticy = (capaticy < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY: capaticy;
        elements = new int[capaticy];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 清除所有元素
     */
    public void clear() {
        size = 0;
    }

    /**
     * 元素的数据
     * @return 元素的大小
     */
    public int size() {
        return size;
    }


    /**
     * 数组是否为空
     * @return 是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 是否包含指定元素
     * @param element 指定元素
     * @return 是否包含
     */
    public boolean contains(int element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    /**
     * 添加元素到尾部
     * @param element 添加的元素
     */
    public void add(int element) {
        add(size,element);
    }

    /**
     * 获取Index位置的元素
     * @param index 索引
     * @return 返回索引的元素
     */
    public int get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    /**
     * 设置index位置的元素
     * @param index 索引
     * @param element 元素
     * @return 返回原来的元素
     */
    public int set(int index, int element) {

        rangeCheck(index);

        int old         = elements[index];
        elements[index] = element;

        return old;
    }

    /**
     * 在index位置插入一个元素
     * @param index 位置
     * @param element 元素
     */
    public void add(int index, int element) {

        rangeCheckForAdd(index);

        ensureCapacity(size + 1);

        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }

        elements[index] = element;
        size++;
    }

    /**
     * 删除Index位置的元素
     * @param index 索引
     * @return 所删除的元素
     */
    public int remove(int index) {

        rangeCheck(index);

        int old = elements[index];

        for (int i = index; i <= size - 1; i++) {
            elements[i] = elements[i+1];
        }

        size --;
        return old;
    }

    /**
     * 查看元素的索引
     * @param element 元素
     * @return 返回指定元素的索引
     */
    public int indexOf(int element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element) {
                return i;
            }
        }
        return ELEMENT_NOT_FOUND;
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

        int[] newElements = new int[newCapacity];

        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }

        elements = newElements;
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            outOfBounds(index);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            outOfBounds(index);
        }
    }

    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + "size:" + size);
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }
}
