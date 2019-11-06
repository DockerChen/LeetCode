public class MergeSortedLinkedList_25 {
    public static LinkedList mergeSortedLinkedList(LinkedList head1, LinkedList head2) {

        LinkedList head = new LinkedList(-1);
        LinkedList cur = head;
        while (head1 != null && head2 != null) {
            if (head1.value <= head2.value) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        if (head1 != null) {
            cur.next = head1;
        }
        if (head2 != null) {
            cur.next = head2;
        }
        return head.next;

    }

    public static LinkedList mergeSortedLinkedListByRecursive(LinkedList head1, LinkedList head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        if (head1.value <= head2.value) {
            head1.next = mergeSortedLinkedListByRecursive(head1.next, head2);
            return head1;
        } else {
            head2.next = mergeSortedLinkedListByRecursive(head1, head2.next);
            return head2;
        }

    }
}
