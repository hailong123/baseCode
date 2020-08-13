package 链表;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists-lcci/
 */
public class _02_07_链表相交 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tmp = headB;
        while (headA != null) {
            while (headB != null) {
                if (headA == headB) {
                    return headA;
                }
                headB = headB.next;
            }
            headA = headA.next;
            headB = tmp;
        }
        return null;
    }
}
