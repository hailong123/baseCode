package com.hl;

import com.hl.Base.AbstractList;
import com.hl.Circle.CircleLinkedList;
import com.hl.Circle.SingleCircleLinkedList;
import com.hl.DoubleList.LinkList;
import com.hl.Single.SingleLinkList;

public class Main {

    static void test(AbstractList<Integer> list) {
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);

//        list.add(0, 55);
//        list.add(2, 66);
//        list.add(list.size(), 77);
//
//        list.remove(0);
//        list.remove(2);
//        list.remove(list.size() - 1);

        System.out.println(list);
    }

    static void josephus() {
        CircleLinkedList<Integer> list = new CircleLinkedList<>();

        for (int i = 1; i <= 8; i++) {
            list.add(i);
        }

        list.reset();//指向头结点

        while (!list.isEmpty()) {
            list.next();
            list.next();
            System.out.println(list.remove());
        }

    }

    public static void main(String[] args) {
//        test(new ArrayList<>());
//        test(new LinkList<>());
//        test(new SingleCircleLinkedList<>());
//        test(new SingleLinkList<>());
//        test(new CircleLinkedList<>());
        josephus();
    }

}
