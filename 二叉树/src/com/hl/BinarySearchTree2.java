package com.hl;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree2<E> extends BinaryTree {

    private Comparator<E> comparator;

    public BinarySearchTree2(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree2() {
        this(null);
    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    protected Node<E> node(E element) {

        Node<E> node = root;

        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) return node;
            if (cmp > 0) {
                node = node.right;
            } else  {
                node = node.left;
            }
        }

        return null;
    }

    public void add(E element) {
        elementNotNullCheck(element);
        //根节点的添加
        if (root == null) {
            root = new Node<>(element,null);
            size ++;
            return;
        }
        //添加不是第一个节点
        Node<E>node    = root;
        //找到父节点
        Node<E> parent = null;
        //保持方向
        int compare = 0;

        while (node != null) {
            compare = compare(element, node.element);
            parent      = node;
            if (compare > 0) {
                //右节点
                node = node.right;
            } else if(compare < 0) {
                //左节点
                node = node.left;
            } else {
                //相等直接返回
                node.element = element;
                return;
            }
        }

        //存入
        Node<E>insert = new Node(element,parent);
        if(compare > 0) {
            parent.right = insert;
        } else {
            parent.left = insert;
        }
        size++;
    }

    public void remove(E element) {
        remove(node(element));
    }

    /**
     * 删除指定节点
     * @param node
     */
    private void remove(Node<E> node) {
        if (node == null) return;

        if (node.hasTwoChildren()) {//度为2的节点
            //找到后继节点
            Node<E> s = successor(node);
            //用后继节点的值覆盖度为2的节点的值
            node.element = s.element;
            node = s;
        }

        //删除node节点(node 的度必然是1或者0)
        Node<E> replacement = node.left != null? node.left : node.right;

        if (replacement != null) {//Node 度为1的节点

            replacement.parent = node.parent;

            if (node.parent == null) {//node 是度为1的节点
                root = replacement;
            } else if (node == node.parent.left) { //更改parent left 和 right 的指向
                node.parent.left  = replacement;
            } else {
                node.parent.right = replacement;
            }

        } else if (node.parent == null){ //Node 是叶子节点并且是 根节点
            root = null;
        } else {//是叶子节点 但是不是根节点
            if (node == node.parent.left) {
                node.parent.left  = null;
            } else {
                node.parent.right = null;
            }
        }
        size --;
    }

    /**
     * 比较函数
     * @param e1
     * @param e2
     * @return (0 表示 e1 = e2  大于1 表示 e1 > e2  小于1 表示 e1 < e2)
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>)e1).compareTo(e2);
    }
}
