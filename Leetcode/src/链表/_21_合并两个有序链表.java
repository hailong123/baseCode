package 链表;

/**
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 */
public class _21_合并两个有序链表 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode dummyHead = new ListNode(0);

        ListNode exchange = dummyHead;

        while(l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                exchange.next = l1;
                exchange = exchange.next;
                l1 = l1.next;
            } else {
                exchange.next = l2;
                exchange = exchange.next;
                l2 = l2.next;
            }
        }

        if(l1 == null) {
            exchange.next = l2;
        } else {
            exchange.next = l1;
        }

        return dummyHead.next;
    }
}
