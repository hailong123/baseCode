package hl.Tree;

import hl.Tree.BBST;

import java.util.Comparator;

public class RBTree<E> extends BBST<E> {

    private static final boolean RED   = false;
    private static final boolean BLACK = true;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected Node createNode(Object elemenet, Node parent) {
        return new RBNode(elemenet, parent);
    }

    @Override
    protected void afterAdd(Node<E> node) {

        Node<E> parent = node.parent;

        //添加的是根节点
        if (parent == null) {
            black(node);
            return;
        }

        //如果父节点是黑色 则 直接返回
        if (isBlack(parent)) return;

        //叔父节点
        Node<E> uncle = parent.sibling();
        //祖父节点
        Node<E> grand = parent.parent;

        if (isRed(uncle)) { //叔父节点为红色
            black(parent);
            black(uncle);
            //祖父节点当做是新添加的节点
            red(grand);
            afterAdd(grand);
            return;
        }

        //叔父节点不是红色
        red(grand);
        if (parent.isLeftChild()) {//L
            if (node.isLeftChild()) { //LL
                black(parent);
            } else {//LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else {
            if (node.isLeftChild()) { //RL
                black(node);
                rotateRight(parent);
            } else {//RR
                black(node);
            }
            rotateLeft(grand);
        }
    }

    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {

        //如果删除的是红色直接返回不做处理
        if (isRed(node)) return;

        //用以取代node的子节点是红色
        if (isRed(replacement)) {
            black(replacement);
            return;
        }

        Node<E>parent = node.parent;
        //删除的是根节点
        if (parent == null) return;

        //删除的是黑色叶子节点
        boolean left = parent.left == null;

        Node<E> sibling = left ? parent.right : parent.left;

        if (left) {
            //被删除的节点在左边, 兄弟节点在左边
        } else {
            //被删除的节点在右边, 兄弟节点在右边
            if (isRed(sibling)) {
                //兄弟节点是红色
                /*
                * 兄弟节点变为黑色
                * 父节点变为红色
                * 进行旋转
                * 更换兄弟节点
                * */
                black(sibling);
                red(parent);
                rotateRight(parent);
                sibling = parent.left;
            }

            //兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                //兄弟节点没有一个红色子节点, 父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);

                black(parent);
                red(sibling);

                if (parentBlack) {
                    afterRemove(parent,null);
                }

            } else { //兄弟节点至少有一个是红色子节点, 向兄弟节点借元素
                //兄弟节点的左边是黑色 兄弟要先旋转
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                }

                //先染色 兄弟节点跟随父节点颜色
                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);

                rotateRight(parent);

            }
        }

    }

    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) return node;
        ((RBNode<E>)node).color = color;
        return node;
    }

    private Node<E> red(Node<E> node) {
       return color(node, RED);
    }

    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    private boolean isRed(Node<E> node) {
        return ((RBNode<E>)node).color == RED;
    }

    private boolean isBlack(Node<E> node) {
        return ((RBNode<E>)node).color == BLACK;
    }

    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>)node).color;
    }

    private static class RBNode<E> extends Node<E> {
        boolean color = RED;
        public RBNode(E element, Node<E> parent) {
            super(element,parent);
        }
    }

}
