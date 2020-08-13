package 链表;

/**
 * https://leetcode-cn.com/problems/delete-middle-node-lcci/
 */
public class _02_03_删除中间节点 {
    public void deleteNode(ListNode node) {
        node.val  = node.next.val;
        node.next = node.next.next;
    }
}
