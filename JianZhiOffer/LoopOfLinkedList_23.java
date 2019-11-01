public class LoopOfLinkedList_23 {
    static class LinkedList {
        int value;
        LinkedList next;

        LinkedList(int value) {
            this.value = value;
        }
    }

    public static LinkedList findLoopNode(LinkedList head) {
        if (head == null) {
            throw new RuntimeException("head is null!");
        }
        LinkedList p1 = head.next;
        LinkedList p2 = head;
        LinkedList loopNode = null;

        while (p1.next != null) {

            p1 = p1.next;
            if (p1.next != null) {
                p1 = p1.next;
            }
            p2 = p2.next;
//            System.out.println(p1.value + " " + p2.value);
            if (p1 == p2) {
                loopNode = p1;
                break;
            }
        }
        if (loopNode == null) {
            return null;
        }

        int count = 1;
        LinkedList tmpList=loopNode;
        while (loopNode.next != tmpList) {
            count++;
//            System.out.println(count);
            loopNode = loopNode.next;
        }

        p1 = p2 = head;

        while (count-- > 0) {
            p1 = p1.next;
        }
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;

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
        linkedList6.next = linkedList3;

        LinkedList linkedList = findLoopNode(linkedList1);
        System.out.println(linkedList.value);

    }

}
