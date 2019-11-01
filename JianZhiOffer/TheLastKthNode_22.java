import java.util.List;

public class TheLastKthNode_22 {
    static class ListNode {
        int value;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    public static ListNode theLastKthNode(ListNode head, int k) {
        if(head==null){
            throw new RuntimeException("Error,head is null!");
        }
        if(k==0){
            throw new RuntimeException("Error,the value of k is 0!");
        }
        ListNode p1 = head;
        ListNode p2 = head;
        int i=0;
        while (i != (k - 1)) {
            if(p1.next==null){
                throw new RuntimeException("Error,the number of ListNode is less than k!");
            }
            p1 = p1.next;
            i++;
        }


        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p2;

    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;

        ListNode listNode=theLastKthNode(listNode1,8);
        System.out.println(listNode.value);

    }

}
