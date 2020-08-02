package com.hl.Circle;

import com.hl.Base.AbstractList;

public class SingleCircleLinkedList<E> extends AbstractList<E> {

    private Node<E> firstNode;

    @Override
    public void clear() {
        size = 0;
        firstNode = null;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    @Override
    public void add(int index, E element) {

        if (index == 0) {

           Node<E> first = new Node<E>(element, firstNode);

            //拿到最后一个节点
            Node<E> last = (size == 0)? first : node(size - 1);
            last.next    = first;
            firstNode    = first;

        } else {

            Node<E> eNode = new Node<E>(element, null);

            Node prev = node(index - 1);

            eNode.next = prev.next;

            prev.next = eNode;
        }

        size++;
    }

    @Override
    public E remove(int index) {
        Node<E> old = firstNode;

        if (index == 0) {
            if (size == 1) {
                firstNode = null;
            } else {
                //首先拿到最后一个节点
                Node<E> last = node(size - 1);
                firstNode    = firstNode.next;
                last.next    = firstNode;
            }
        } else {
            Node<E> prev = node(index - 1);
            old = prev.next;
            prev.next = prev.next.next;
        }
        size--;
        return old.element;
    }

    @Override
    public int indexOf(E element) {

        Node tmp = firstNode;

        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (tmp.element == null) {
                    return i;
                }
                tmp = tmp.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (tmp.element.equals(element)) {
                    return i;
                }
                tmp = tmp.next;
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    /**
     * 获取Index对应节点的对象
     *
     * @param index
     * @return
     */
    private Node<E> node(int index) {

        rangeCheck(index);

        Node next = firstNode;
        for (int i = 0; i < index; i++) {
            next = next.next;
        }
        return next;
    }

    @Override
    public String toString() {
       StringBuilder stringBuilder = new StringBuilder();
       stringBuilder.append("size = ").append(size).append(", [");
       Node<E> node = firstNode;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }

            stringBuilder.append(node.element);

            node = node.next;
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        public Node(E element) {
            this.element = element;
        }
    }

}
