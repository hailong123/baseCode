package 链表;

/**
 * https://leetcode-cn.com/problems/convert-binary-number-in-a-linked-list-to-integer/
 */
public class _1290_二进制链表转整数 {

    public int getDecimalValue(ListNode head) {

        int sum = 0;

        while (head != null) {
            sum  = (head.val + 2*sum);
            head = head.next;
        }

        return sum;
    }
}
