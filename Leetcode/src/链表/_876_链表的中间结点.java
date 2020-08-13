package 链表;

/**
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 */
public class _876_链表的中间结点 {
    public ListNode middleNode(ListNode head) {

        ListNode fast = head;
        ListNode low  = head;

        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            low  = low.next;
        }

        return low;
    }
}
