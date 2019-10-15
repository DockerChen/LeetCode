import java.util.LinkedList;
import java.util.Queue;

public class TwoQueueToStack_9<T> {
    private Queue<T> queue1;
    private Queue<T> queue2;

    public TwoQueueToStack_9() {
        queue1 = new LinkedList<T>();
        queue2 = new LinkedList<T>();
    }

    public void push(T node) {
        queue1.add(node);

    }

    public T pop() {
        if (queue1.isEmpty() && queue2.isEmpty()) {
            throw new RuntimeException("the stack is empty!");
        }

        while (queue1.size() != 1) {
            queue2.add(queue1.poll());
        }
        T node = queue1.poll();
        Queue<T> queue = queue1;
        queue1 = queue2;
        queue2 = queue;
        return node;
    }

    public T peek() {
        if (queue1.isEmpty() && queue2.isEmpty()) {
            throw new RuntimeException("the stack is empty!");
        }
        T node = null;
        while (!queue1.isEmpty()) {
            node = queue1.poll();
            queue2.add(node);
        }

        Queue<T> queue = queue1;
        queue1 = queue2;
        queue2 = queue;

        return node;

    }

    public static void main(String[] args) {
        TwoQueueToStack_9 twoQueueToStack = new TwoQueueToStack_9();
        twoQueueToStack.push(1);
        twoQueueToStack.push(2);
        twoQueueToStack.push(3);
        System.out.println(twoQueueToStack.pop());
        twoQueueToStack.push(4);
        System.out.println(twoQueueToStack.peek());
        System.out.println(twoQueueToStack.pop());
        System.out.println(twoQueueToStack.pop());
        System.out.println(twoQueueToStack.pop());
        System.out.println(twoQueueToStack.pop());

    }

}
