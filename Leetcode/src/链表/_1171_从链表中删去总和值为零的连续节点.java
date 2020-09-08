package 链表;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/
 */
public class _1171_从链表中删去总和值为零的连续节点 {
    public ListNode removeZeroSumSublists(ListNode head) {

        Stack<Integer> stack = new Stack<>();

        ListNode headNode  = head;

        ListNode stackNode = new ListNode(0);
        stackNode.next     = head;

        while (head != null) {
            while (stackNode.next != null && stackNode.next.next != null) {
                if ((head.val + stackNode.next.val == 0)) {
                    stack.push(stackNode.next.val);
                    stack.push(head.val);
                }
                stackNode.next = stackNode.next.next;
            }
            head           = head.next;
            stackNode.next = head;
        }

        ListNode node = new ListNode(0);

        node.next = headNode;

        ListNode tmp = node;

        while (tmp != null && tmp.next != null) {
            if (tmp.next.val == stack.peek()) {
                tmp.next = tmp.next.next;
                stack.pop();
            } else {
                tmp = tmp.next;
            }
        }

        return node.next;
    }
}
