package 链表;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 */
public class _237_删除链表中的节点 {

    public void deleteNode(ListNode node) {
        node = node.next;
        node.next = node.next.next;
    }

}
