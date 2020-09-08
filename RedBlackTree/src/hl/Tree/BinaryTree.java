package hl.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<E> {
    protected int size;
    protected Node<E> root;

    protected static class Node<E> {
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

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        //兄弟节点
        public Node<E> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }

            return null;
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
        Queue<Node<E>> queue = new LinkedList<>();
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

    protected Node<E> createNode(E elemenet, Node<E> parent) {
        return new Node(elemenet,parent);
    }

    /**
     * 查找前驱节点
     * @param node
     * @return
     */
    public Node<E> predecessor(Node<E> node) {
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

    public Node<E> successor(Node<E> node) {
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

    protected void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not null");
        }
    }

}
