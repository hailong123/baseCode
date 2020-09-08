package com.hl;

import com.hl.Base.AbstractList;
import com.hl.Circle.CircleLinkedList;
import com.hl.Circle.SingleCircleLinkedList;
import com.hl.DoubleList.LinkList;
import com.hl.Single.SingleLinkList;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

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

//        int [] arrayList = {22,33,4,5,6,7,77,87,565,434,3423,234,234,234,234,234,234,234234,23,423,42,342,3};
            int [] arrayList = {56,9,33,19,2,37,34};

            //冒泡排序
//        for (int y = arrayList.length; y > 0; y--) {
//            for (int x = 1; x < y; x++) {
//                if (arrayList[x] < arrayList[x - 1]) {
//                    int tmp = arrayList[x];
//                    arrayList[x] = arrayList[x-1];
//                    arrayList[x-1] = tmp;
//                }
//            }
//        }

        //快速排序
        int end = arrayList.length;
        for (int x = end; x >= 1; x--) {
            int index = 0;
            for (int y = 0; y < x; y++) {
                if (arrayList[index] < arrayList[y]) {
                    index = y;
                }
            }

            int tmp = arrayList[x - 1];
            arrayList[x - 1] = arrayList[index];
            arrayList[index] = tmp;
        }

        for (int i : arrayList) {
            System.out.println("_"+i);
        }
    }
}
