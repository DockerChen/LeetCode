public class ReverseLinkedList_24 {
    static class LinkedList {
        int value;
        LinkedList next;

        LinkedList(int value) {
            this.value = value;
        }

    }

    public static LinkedList reverse(LinkedList head) {
        LinkedList newList = new LinkedList(-1);
        while (head != null) {
            LinkedList next = head.next;
            head.next = newList.next;
            newList.next = head;
            head = next;
        }
        return newList.next;
    }

    public static LinkedList reverseByRecursive(LinkedList head){
        if(head==null||head.next==null){
            return head;
        }
        LinkedList next=head.next;
        head.next=null;
        LinkedList newHead=reverseByRecursive(next);
        next.next=head;
        return newHead;


    }


    public static void main(String[] args) {
        LinkedList linkedList1 = new LinkedList(1);
        LinkedList linkedList2 = new LinkedList(2);
        LinkedList linkedList3 = new LinkedList(3);
        LinkedList linkedList4 = new LinkedList(4);
        LinkedList linkedList5 = new LinkedList(5);
        LinkedList linkedList6 = new LinkedList(6);
        linkedList1.next = linkedList2;
        linkedList2.next = linkedList3;
        linkedList3.next = linkedList4;
        linkedList4.next = linkedList5;
        linkedList5.next = linkedList6;
        LinkedList reverseHead = reverse(linkedList1);
        while (reverseHead!=null){
            System.out.println(reverseHead.value);
            reverseHead=reverseHead.next;
        }
    }

}
