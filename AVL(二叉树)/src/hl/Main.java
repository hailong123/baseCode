package hl;

import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        test2();
    }

    public static void test2() {

        Integer data[] = new Integer[]{
                7,
                4,
                9,
                2,
                5,
                8,
                66
        };

        AVLTree<Integer> bst = new AVLTree<>();

        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        bst.levelOrderTraversal(bst.getRoot());
    }
}
