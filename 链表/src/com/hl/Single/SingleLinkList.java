package com.hl.Single;

import com.hl.Base.AbstractList;

public class SingleLinkList<E> extends AbstractList<E> {

    private Node<E> first;

    @Override
    public void clear() {
        size  = 0;
        first = null;
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

        if (index == 0) {
            first  = new Node<E>(element, first);
        } else {
            Node<E> prev  = node(index - 1);
            prev.next     = new Node<E>(element, prev.next);
        }
        size ++;
    }

    @Override
    public E remove(int index) {

        Node<E> old = first;

        if (index == 0) {
            first = first.next;
        } else {

            Node<E> prev = node(index - 1);
            old          = prev.next;
            prev.next    = prev.next.next;
        }
        size --;
        return old.element;
    }

    @Override
    public int indexOf(E element) {

        Node tmp = first;

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
     * @param index
     * @return
     */
    private Node<E> node(int index) {

        rangeCheck(index);

        Node node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next    = next;
        }
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size = ").append(size).append(", [");
        Node<E> node = first;
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

}
