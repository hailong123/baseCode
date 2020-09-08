package hl.Tree;

import java.util.Comparator;

/**
 * AVL树
 * @param <E>
 */
public class AVLTree<E> extends BBST<E> {

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        //找到最低的失衡节点
         while ((node = node.parent) != null) {
             //判断node是否平衡
             if (isBalance(node)) {
                //更新高度
                 uploadHeight(node);
             } else {
                 //调整平衡
                 reBalance(node);
                 //恢复平衡
                 break;
             }
         }
    }

    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        //找到最低的失衡节点
        while ((node = node.parent) != null) {
            //判断node是否平衡
            if (isBalance(node)) {
                //更新高度
                uploadHeight(node);
            } else {
                //调整平衡
                reBalance(node);
            }
        }
    }

    @Override
    protected Node createNode(Object elemenet, Node parent) {
        return new AVLNote(elemenet, parent);
    }

    /**
     * 恢复平衡
     * @param grand
     */
    private void reBalance2(Node<E> grand) {
        Node<E> parent = ((AVLNote<E>)grand).tallerChild();
        Node<E> node   = ((AVLNote<E>)parent).tallerChild();
        //判断是哪种树
        if (parent.isLeftChild()) {
            if (node.isLeftChild()) { //LL 右旋
                rotateRight(grand);
            } else  {//LR 先变为 RR  然后左旋
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            if (node.isLeftChild()) { //RL 先变为 LL 然后 左旋
                rotateRight(parent);
                rotateLeft(grand);
            } else { //RR 左旋
                rotateLeft(grand);
            }
        }
    }

    /**
     * 恢复平衡
     * @param grand
     */
    private void reBalance(Node<E> grand) {
        Node<E> parent = ((AVLNote<E>)grand).tallerChild();
        Node<E> node   = ((AVLNote<E>)parent).tallerChild();
        //判断是哪种树
        if (parent.isLeftChild()) {
            if (node.isLeftChild()) { //LL 右旋
                rotate(grand,node.left,node,node.right,parent,parent.right,grand,grand.right);
            } else  {//LR 先变为 RR  然后左旋
                rotate(grand,parent.left,parent,node.left,node,node.right,grand,grand.right);
            }
        } else {
            if (node.isLeftChild()) { //RL 先变为 LL 然后 左旋
                rotate(grand,grand.left,grand,node.left,node,node.right,parent,parent.right);
            } else { //RR 左旋
                rotate(grand,grand.left,grand,parent.left,parent,node.left,node,node.right);
            }
        }
    }

    @Override
    protected void rotate(Node<E> root, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
        super.rotate(root, a, b, c, d, e, f, g);
        uploadHeight(b);
        uploadHeight(f);
        uploadHeight(d);
    }

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);
        //跟新高度
        uploadHeight(grand);
        uploadHeight(parent);
    }

    private boolean isBalance(Node<E> node) {
        return Math.abs(((AVLNote<E>)node).balanceFactor()) <= 1;
    }

    private void uploadHeight(Node<E> node) {
       ((AVLNote<E>)node).updateHeight();
    }

    private static class AVLNote<E> extends Node<E> {
        int height = 1;
        public AVLNote(E element, Node<E> parent) {
            super(element, parent);
        }

        /**
         * 平衡因子
         * @return
         */
        public int balanceFactor() {
            int leftHeight  = left == null? 0 : ((AVLNote<E>)left).height;
            int rightHeight = right == null? 0 : ((AVLNote<E>)right).height;
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight  = left  == null? 0 : ((AVLNote<E>)left).height;
            int rightHeight = right == null? 0 : ((AVLNote<E>)right).height;
            height = 1 + Math.max(leftHeight,rightHeight);
        }

        /**
         *
         * @return
         */
        public Node<E> tallerChild() {
            int leftHeight  = left  == null? 0 : ((AVLNote<E>)left).height;
            int rightHeight = right == null? 0 : ((AVLNote<E>)right).height;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;
            return isLeftChild() ? left : right;
        }
    }
}
