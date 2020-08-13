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

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
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
        size ++;
    }

    public void remove(E element) {
        remove(node(element));
    }

    public boolean contains(E element) {
        return node(element) != null;
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

    private Node<E> node(E element) {

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

    /**
     * 查找前驱节点
     * @param node
     * @return
     */
    private Node<E> predecessor(Node<E> node) {
        if (node == null) return null;

        Node<E> p = node.left;

        //前驱节点在左子树当中
        while (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        //从左父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        //node.parent == null
        //node == node.parent.right
        return node.parent;
    }

    private Node<E> successor(Node<E> node) {
        if (node == null) return null;

        Node<E> p = node.right;

        //后继节点在左子树当中
        while (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        //从左父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        //node.parent == null
        //node == node.parent.right
        return node.parent;
    }

}
