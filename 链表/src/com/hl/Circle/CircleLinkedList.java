package com.hl.Circle;

import com.hl.Base.AbstractList;

import java.util.Objects;

public class CircleLinkedList<E> extends AbstractList<E> {

    private Node<E> firstNode;
    private Node<E> lastNode;
    private Node<E> current;

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.next    = next;
            this.prev    = prev;
        }

        public Node(E element){
            this.element = element;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();

            if (prev != null) {
                stringBuilder.append(prev.element);
            }

            stringBuilder.append("_").append(element).append("_");

            if (next != null) {
                stringBuilder.append(next.element);
            }

            return stringBuilder.toString();
        }
    }

    public void reset() {
        current = firstNode;
    }

    public E next() {
        if (current == null) return null;
        current = current.next;
        return current.element;
    }

    public E remove() {

        if (current == null) return null;

        Node<E> next = current.next;
        E element    = remove(current);

        if (size == 0) {
            current = null;
        } else {
            current = next;
        }

        return element;
    }

    @Override
    public void clear() {
        size = 0;
        firstNode = null;
        lastNode  = null;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old        = node.element;
        node.element = element;
        return old;
    }

    @Override
    public void add(int index, E element) {

        rangeCheckForAdd(index);

        if (index == size) {// 最后面添加元素

            Node<E> oldLast = lastNode;

            lastNode = new Node<>(oldLast, element, firstNode);

            if (oldLast == null) {//这是链表添加的第一个元素
                firstNode = lastNode;
                firstNode.prev = firstNode;
                firstNode.next = firstNode;
            } else {
                oldLast.next   = lastNode;
                firstNode.prev = lastNode;
            }

        } else {

            Node<E> next = node(index);
            Node<E> prev = next.prev;

            Node<E> eNode = new Node<E>(prev, element, next);
            next.prev = eNode;
            prev.next = eNode;

            if (index == 0 || next == firstNode) { // index = 0
                firstNode      = eNode;
            }
        }

        size++;
    }

    private E remove(Node<E> node) {
        if (size == 1) {
            firstNode = null;
            lastNode  = null;
        } else {
            Node<E> prev = node.prev;
            Node<E> next = node.next;
            prev.next = next;
            next.prev = prev;

            if (node == firstNode) {//index = 0
                firstNode = next;
            }

            if (node == lastNode) {//index == size - 1
                lastNode = prev;
            }
        }

        size --;
        return node.element;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        return remove(node(index));
    }

    @Override
    public int indexOf(E element) {
        int index = 0;
        Node<E> cursorNode = firstNode;
        while(Objects.nonNull(cursorNode)){
            E find = cursorNode.element;
            if(find == element){
                return index;
            }
            if(Objects.nonNull(element) && find.equals(element)){
                return index;
            }
            cursorNode = cursorNode.next;
            index ++;
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 获取Index对应节点的对象
     * @param index
     * @return
     */
    private Node<E> node(int index) {

        rangeCheck(index);

        if (index < (size >> 1)) {
            Node<E> node = firstNode;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;

        } else {
            Node<E> node = lastNode;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size =").append(size).append(", [");
        Node<E> node = firstNode;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }

            stringBuilder.append(node);

            node = node.next;
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
