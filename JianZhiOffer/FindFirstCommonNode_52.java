import java.util.HashSet;

public class FindFirstCommonNode_52 {
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 使用HashSet
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        HashSet<ListNode> hashSet = new HashSet<>();
        ListNode head1 = pHead1;
        ListNode head2 = pHead2;
        while (head1 != null) {
            hashSet.add(head1);
            head1 = head1.next;
        }

        while (head2 != null) {
            if (hashSet.contains(head2)) {
                return head2;
            }
            head2 = head2.next;
        }

        return null;
    }

    // 先遍历两个链表，获得链表的长度，让较长的链表头指针先移动，使得两个链表剩余的节点个数相同，之后，两个链表的头指针同时开始移动，
    // 出现第一个相同值的节点，即为第一个公共节点。
    public ListNode FindFirstCommonNode_2(ListNode pHead1, ListNode pHead2) {
        int len1 = 0, len2 = 0;
        ListNode head1 = pHead1;
        ListNode head2 = pHead2;
        while (head1 != null) {
            len1++;
            head1 = head1.next;
        }

        while (head2 != null) {
            len2++;
            head2 = head2.next;
        }

        int max = Math.max(len1, len2);
        int move1 = max - len1;
        int move2 = max - len2;
        head1 = pHead1;
        head2 = pHead2;
        //下面两个循环只会执行一个
        while (move2 != 0) {
            head1 = head1.next;
            move2--;
        }

        while (move1 != 0) {
            head2 = head2.next;
            move1--;
        }

        while (head1 != null && head2 != null) {
            if (head1.val == head2.val) {
                return head1;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return null;
    }


}
