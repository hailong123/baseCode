package 链表;

import java.util.Enumeration;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/palindrome-linked-list-lcci/
 */
public class _02_06_回文链表 {//1,2,3,2,1

    public boolean isPalindrome(ListNode head) {
        if(head == null){
            return true;
        }
        ListNode head1 = head;
        ListNode head2 = head;
        Stack<Integer> stack1 = new Stack<>();
        while(head1 != null && head2 != null){
            stack1.push(head1.val);
            head1 = head1.next;
            if(head2.next != null){
                head2 = head2.next.next;
            }else{
                head2 = null;
                stack1.pop();
            }
        }

        while(head1 != null){
            if(head1.val == stack1.peek()){
                stack1.pop();
            }
            head1 = head1.next;
        }
        return stack1.isEmpty();
    }
}
