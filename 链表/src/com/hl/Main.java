package com.hl;

import com.hl.Base.AbstractList;
import com.hl.Circle.CircleLinkedList;
import com.hl.Circle.SingleCircleLinkedList;
import com.hl.DoubleList.LinkList;
import com.hl.Single.SingleLinkList;

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

    }


    public ListNode removeDuplicateNodes(ListNode head) {

        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

        ListNode headNode = new ListNode(0);
        headNode.next = head;

        ListNode node = headNode;
        Integer index = 0;

        while (node.next != null) {
            if (hashMap.get(index) != null) {
                node.next = node.next.next.next;
            } else {
                hashMap.put(index, node.next.val);
                node.next = node.next.next;
            }
            index++;
        }

        return headNode.next;
    }

}
