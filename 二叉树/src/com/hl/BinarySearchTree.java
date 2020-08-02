package com.hl;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<E> {
    private int size;
    private Node<E> root;
    private Comparator comparator;

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree() {
        this(null);
    }

    private static class Node<E> {
        E element;
        Node<E> left;   //左节点
        Node<E> right;  //右节点
        Node<E> parent; //父节点

        //节点内部类
        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent  = parent;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {

    }

    public Node<E> getRoot() {
        return root;
    }

    /**
     * 前序遍历
     * @param node
     */
    public void preorderTraversal(Node<E> node) {
        if (node == null) return;
        System.out.println(node.element);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    /**
     * 中序遍历
     * @param node
     */
    public void inorderTraversal(Node<E> node) {
        if (node == null) return;
        preorderTraversal(node.left);
        System.out.println(node.element);
        preorderTraversal(node.right);
    }

    /**
     * 后续遍历
     * @param node
     */
    public void postorder(Node<E> node) {
        if (node == null) return;
        preorderTraversal(node.left);
        preorderTraversal(node.right);
        System.out.println(node.element);
    }

    /**
     * 层序遍历
     * @param node
     */
    public void levelOrderTraversal(Node<E> node) {
        if (node == null) return;
        Queue<Node<E>>queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node<E> eNode = queue.poll();
            System.out.println(eNode.element);

            if (eNode.left != null) {
                queue.offer(eNode.left);
            }

            if (eNode.right != null) {
                queue.offer(eNode.right);
            }
        }
    }

    public static interface Visitor<E> {
        void visit(E element);
    }

    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            Node<E> eNode = queue.poll();
            visitor.visit(eNode.element);

            if (eNode.left != null) {
                queue.offer(eNode.left);
            }

            if (eNode.right != null) {
                queue.offer(eNode.right);
            }
        }
    }

    /**
     * 返回树的高度
     * @return
     */
    public int height() {
        return height2(root);
    }

    /**
     * 递归调用
     * @param node
     * @return
     */
    public int height1(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(height1(node.left),height1(node.right));
    }

    public int height2(Node<E> eNode) {
        if (eNode == null) return 0;

        int height       = 0;
        int levelElement = 1;

        Queue<Node> queue = new LinkedList();

        queue.offer(eNode);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levelElement --;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }

            if (levelElement == 0) {
                levelElement = queue.size();
                height ++;
            }
        }

        return height;
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
    }

    public void remove(E element) {

    }

    public boolean contains(E element) {
        return false;
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

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not null");
        }
    }

}
