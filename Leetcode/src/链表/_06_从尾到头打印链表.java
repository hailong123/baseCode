package 链表;

import java.util.ArrayList;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 */
public class _06_从尾到头打印链表 {
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack    = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();

        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }

        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }

        return list.stream().mapToInt(Integer::valueOf).toArray();
    }
}
