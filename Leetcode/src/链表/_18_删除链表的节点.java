package 链表;

public class _18_删除链表的节点 {
    public ListNode deleteNode(ListNode head, int val) {

        ListNode dummy = new ListNode(0);

        dummy.next   = head;
        ListNode cur = dummy;

        while(cur != null && cur.next != null){
            if (cur.next.val == val) {
                cur.next=cur.next.next;
            } else {
                cur=cur.next;
            }
        }
        return dummy.next;
    }
}
