package 链表;

import java.util.List;

public class _203_移除链表元素 {
    public ListNode removeElements(ListNode head, int val) {

        ListNode tmp = new ListNode(0);

        tmp.next = head;

        ListNode curl = tmp;

        while (curl != null && curl.next != null) {
            if (curl.next.val == val) {
                curl.next = curl.next.next;
            } else {
                curl = curl.next;
            }
        }
        return tmp.next;
    }
}
