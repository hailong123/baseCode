package 链表;

import com.sun.tools.javac.util.List;

/**
 * https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci/
 */
public class _02_返回倒数第k个节点 {

    public int kthToLast(ListNode head, int k) {

        Integer deep  = deep(head);

        Integer index = deep - k;
        Integer tmp   = 0;

        while (head != null) {
            if (tmp == index) {
                return head.val;
            } else  {
                tmp++;
            }

            head = head.next;
        }
        return 0;
    }

    public static int deep(ListNode head) {

        Integer deep = 0;
        ListNode tmp = head;

        while (tmp != null) {
            tmp = tmp.next;
            deep++;
        }
        return deep;
    }
}