public class DeleteNode_18 {
    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }

    }

    //O(1)
    public static Node deleteNode(Node head, Node deleteNode) {
        //要删除节点的下一个节点不为空时，用下一个节点的值替代当前节点，然后将当前节点指向下一个节点的节点，O(1)
        if (deleteNode.next != null) {
            deleteNode.value = deleteNode.next.value;
            deleteNode.next = deleteNode.next.next;
        } else {
            //链表中只有一个节点
            if (head == deleteNode) {
                head = null;
            } else {
                //要删除节点的下一个节点为空，即链表中最后一个节点，O(n)
                Node cur = head;
                while (cur.next != deleteNode) {
                    cur = cur.next;
                }
                cur.next = null;
            }
        }

        return head;

    }

    public static void printNode(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.next = node2;
        node2.next = node3;
        Node head = deleteNode(node1, node3);

        printNode(head);

    }

}
