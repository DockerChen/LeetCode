public class CopyLinkedList_35 {
    public static class ComplexListNode {
        int value;
        ComplexListNode next;
        ComplexListNode random;

        public ComplexListNode(int value) {
            this.value = value;
        }
    }

    public static ComplexListNode copy(ComplexListNode head) {
        if (head == null) {
            return null;
        }

        //第一步,复制节点
        ComplexListNode cur = head;
        while (cur != null) {
            ComplexListNode copynode = new ComplexListNode(cur.value);
            copynode.next = cur.next;
            cur.next = copynode;
            cur = copynode.next;

        }
        //第二步，建立random连接
        cur = head;
        while (cur != null) {
            ComplexListNode copynode = cur.next;
            if (cur.random != null) {
                copynode.random = cur.random.next;
            }
            cur = copynode.next;
        }
        //第三步,拆分
        cur = head;
        ComplexListNode copyHead = head.next;
        while (cur.next != null) {
            ComplexListNode next = cur.next;
            cur.next = next.next;
            cur = next;

        }

        return copyHead;

    }

}
