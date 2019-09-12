import java.time.temporal.ValueRange;

public class TwoNumberSum_2 {

    //Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3, head = new ListNode(0);
        l3 = head;
        int res = 0, carry = 0;
        while (l1 != null && l2 != null) {
            res = l1.val + l2.val + carry;
            if (res >= 10) {
                res = res - 10;
                carry = 1;
            } else {
                carry = 0;
            }

            l3.next = new ListNode(res);
            l3 = l3.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        if (l1 == null && l2 == null && carry == 1) {
            l3.next = new ListNode(1);
        }


        while (l1 != null) {
            res = l1.val + carry;
            if (res >= 10) {
                res = res - 10;
                carry = 1;
            } else {
                carry = 0;
            }
            l3.next = new ListNode(res);
            l1 = l1.next;
            l3 = l3.next;

        }
        while (l2 != null) {
            res = l2.val + carry;
            if (res >= 10) {
                res = res - 10;
                carry = 1;
            } else {
                carry = 0;
            }
            l3.next = new ListNode(res);
            l2 = l2.next;
            l3 = l3.next;

        }

        if (carry == 1) {
            l3.next = new ListNode(1);
            l3 = l3.next;
        }


        return head.next;


    }

    public static ListNode addTwoNumbers_1(ListNode l1, ListNode l2) {
        ListNode l3, head = new ListNode(0);
        l3 = head;
        int res = 0, carry = 0;
        //注意空指针的判断
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            res = (x + y + carry) % 10;
            carry = (x + y + carry) / 10;
            l3.next = new ListNode(res);

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }

            l3 = l3.next;
        }
        if (carry == 1) {
            l3.next = new ListNode(carry);
            l3 = l3.next;
        }


        return head.next;


    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(0);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);

        ListNode l3 = addTwoNumbers_1(l1, l2);
        while (l3 != null) {
            System.out.print(l3.val + "->");
            l3 = l3.next;
        }
        System.out.println();



    }


}
