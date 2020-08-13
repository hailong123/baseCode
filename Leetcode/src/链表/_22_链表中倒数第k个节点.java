package 链表;

/**
 * https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 */
public class _22_链表中倒数第k个节点 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        Integer index = length(head) - k;
        Integer tmp   = 0;
        while (head != null) {
            if (index == tmp) return head;
            head = head.next;
            tmp ++;
        }
        return null;
    }

    public static int length(ListNode node) {
        Integer tmp = 0;
        while (node != null) {
            tmp ++;
            node = node.next;
        }
        return tmp;
    }
}
