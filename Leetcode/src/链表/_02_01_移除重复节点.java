package 链表;

import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/remove-duplicate-node-lcci/
 */
public class _02_01_移除重复节点 {

    public ListNode removeDuplicateNodes(ListNode head) {

        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

        ListNode headNode = new ListNode(0);
        headNode.next     = head;

        ListNode node = headNode;

        while (node.next != null) {
            if (hashMap.get(node.next.val) != null) {
                node.next = node.next.next;
            } else {
                hashMap.put(node.next.val, node.next.val);
                node = node.next;
            }
        }
        return headNode.next;
    }
}
