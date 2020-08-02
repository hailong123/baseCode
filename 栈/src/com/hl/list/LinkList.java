package com.hl.list;

import java.util.Objects;

public class LinkList<E> extends AbstractList<E> {

    private Node<E> firstNode;
    private Node<E> lastNode;

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
        if (index == size) {
            Node<E> oldLast = lastNode;
            lastNode = new Node<>(oldLast,element,null);
            if (oldLast == null) {//这是链表添加的第一个元素
                firstNode    = lastNode;
            } else {
                oldLast.next = lastNode;
            }
        } else {
            Node<E> next  = node(index);
            Node<E> prev  = next.prev;
            Node<E> node  = new Node<E>(prev,element,next);
            next.prev     = node;

            if (prev == null) {
                firstNode = node;
            } else {
                prev.next = node;
            }
        }

        size ++;
    }

    @Override
    public E remove(int index) {

        if(index < 0 || index > size){
            throw  new RuntimeException("illegal index : {"+index+"}");
        }
        Node<E> target = firstNode;
        while(index > 0){
            target = target.next;
            index -= 1;
        }
        if(target == firstNode || target == lastNode){
            if(target == firstNode){
                firstNode = firstNode.next;
            }else{
                lastNode = lastNode.prev;
            }
        }else{
            target.prev = target.next;
        }
        size -= 1;
        return target.element;
//
//        Node<E> node = node(index);
//
//        if (node.prev == null) {
//            node.next.prev = node.prev;
//        } else {
//            firstNode = node.next;
//        }
//
//        if (node.next == null) {
//            lastNode = node.prev;
//        } else {
//            System.err.println(node.prev);
//            node.prev.next = node.next;
//        }

//        size --;
//        return node.element;
    }

    @Override
    public int indexOf(E element) {

//        Node tmp = firstNode;
//
//        if (element == null) {
//            for (int i = 0; i < size; i++) {
//                if (tmp.element == null) {
//                    return i;
//                }
//                tmp = tmp.next;
//            }
//        } else {
//            for (int i = 0; i < size; i++) {
//                if (tmp.element.equals(element)) {
//                    return i;
//                }
//                tmp = tmp.next;
//            }
//        }
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
