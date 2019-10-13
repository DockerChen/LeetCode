import java.util.Stack;

public class FromHeadtoTailPrintLinkedList_6 {
    static class ListNode {
        int key;
        ListNode next;

        public ListNode(int key) {
            this.key = key;
        }
    }

    public static void fromHeadtoTailPrintLinkedListByStack(ListNode head) {
        if (head == null) {
            return;
        }
        Stack<ListNode> stack = new Stack();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop().key + " ");
        }

    }

    public static void fromHeadtoTailPrintLinkedListByRecursion(ListNode head) {
        if (head == null) {
            return;
        }

        fromHeadtoTailPrintLinkedListByStack(head.next);
        System.out.print(head.key + " ");

    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        fromHeadtoTailPrintLinkedListByStack(head);
        System.out.println();
        fromHeadtoTailPrintLinkedListByRecursion(head);

    }

}
