package com.hl;

import java.util.Comparator;

public class Main {

    private static class PersonComparator implements Comparator<Person> {

        @Override
        public int compare(Person e1, Person e2) {
            return e1.getAge() - e2.getAge();
        }
    }

    private static class PersonComparator2 implements Comparator<Person> {

        @Override
        public int compare(Person e1, Person e2) {
            return e2.getAge() - e1.getAge();
        }
    }

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

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

       // bst.levelOrderTraversal(bst.getRoot());

        //System.out.println("bst 高度为: " + bst.height());;

        bst.remove(7);

        bst.levelOrderTraversal(bst.getRoot());
    }

    public static void test1() {

//        bst.levelOrder(new BinarySearchTree.Visitor<Integer>() {
//            @Override
//            public void visit(Integer element) {
//                System.out.print("_" + element + "_ ");
//            }
//        });
//
//        BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new PersonComparator());
//        bst2.add(new Person(12));
//        bst2.add(new Person(15));
//
//        BinarySearchTree<Person> bst3 = new BinarySearchTree<>(new PersonComparator2());
//        bst3.add(new Person(12));
//        bst3.add(new Person(15));
    }

}
