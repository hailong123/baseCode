package hl;

import java.util.Comparator;

/**
 * AVL树
 * @param <E>
 */
public class AVLTree<E> extends BST<E> {

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
    protected void afterRemove(Node<E> node) {
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

    private void rotate(Node<E> root,
                        Node<E> a,Node<E> b,Node<E> c,
                        Node<E> d,
                        Node<E> e,Node<E> f,Node<E> g) {
        //更新根节点为 d
        if (root.isLeftChild()) {
            root.parent.left = d;
        } else if(root.isRightChild()) {
            root.parent.right = d;
        } else {
            root = d;
        }

        //a - b - c
        b.left   = a;

        if (a != null) {
            a.parent = b;
        }

        b.right  = c;

        if (c != null) {
            c.parent = b;
        }

        uploadHeight(b);

        //e - f - g
        f.left   = e;

        if (e != null) {
            e.parent = f;
        }

        f.right  = g;

        if (g != null) {
            g.parent = f;
        }

        uploadHeight(f);

        //b - d - f
        d.left   = b;
        d.right  = f;
        b.parent = d;
        f.parent = d;

        uploadHeight(d);
    }

    private void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child  = parent.left;
        grand.right    = child;
        parent.left    = grand;

        afterRotate(grand,parent,child);
    }

    private void rotateRight(Node<E> grand) {

        Node<E> parent = grand.left;
        Node<E> child  = parent.right;
        grand.left     = child;
        parent.right   = grand;

       afterRotate(grand,parent,child);
    }

    private void afterRotate(Node<E>grand, Node<E> parent, Node<E> child) {
        //更新父节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if(grand.isRightChild()) {
            grand.parent.right = parent;
        } else { //grand 是根节点
            root = parent;
        }

        //跟新child的父节点
        if (child != null) {
            child.parent = grand;
        }
        //更新grand的父节点
        grand.parent = parent;

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
