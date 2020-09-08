package hl.Tree;

import java.util.Comparator;

public class BBST<E> extends BST<E> {
    public BBST() {
        this(null);
    }

    public BBST(Comparator<E> comparator) {
        super(comparator);
    }

    protected void rotate(Node<E> root,
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


        //e - f - g
        f.left   = e;

        if (e != null) {
            e.parent = f;
        }

        f.right  = g;

        if (g != null) {
            g.parent = f;
        }


        //b - d - f
        d.left   = b;
        d.right  = f;
        b.parent = d;
        f.parent = d;
    }

    protected void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child  = parent.left;
        grand.right    = child;
        parent.left    = grand;

        afterRotate(grand,parent,child);
    }

    protected void rotateRight(Node<E> grand) {

        Node<E> parent = grand.left;
        Node<E> child  = parent.right;
        grand.left     = child;
        parent.right   = grand;

        afterRotate(grand,parent,child);
    }

    protected void afterRotate(Node<E>grand, Node<E> parent, Node<E> child) {
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
    }
}
