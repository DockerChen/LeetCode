import java.util.Stack;

public class TwoStackToQueue_9<T> {
    private Stack<T> stackPush;
    private Stack<T> stackPop;

    public TwoStackToQueue_9() {
        stackPush = new Stack<T>();
        stackPop = new Stack<T>();
    }

    public void appendTail(T node) {
        stackPush.push(node);
    }

    public T deleteHead() {
        if (stackPush.isEmpty() && stackPop.isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        } else {
            if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push((stackPush.pop()));
                }

            }

            return stackPop.pop();
        }
    }
    public T getHead(){
        if (stackPush.isEmpty() && stackPop.isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        } else {
            if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push((stackPush.pop()));
                }

            }

            return stackPop.peek();
        }

    }

    public static void main(String[] args) {
        TwoStackToQueue_9 twoStackToQueue = new TwoStackToQueue_9();
        twoStackToQueue.appendTail(1);
        twoStackToQueue.appendTail(2);
        twoStackToQueue.appendTail(3);
        twoStackToQueue.appendTail(4);
        System.out.println(twoStackToQueue.deleteHead());
        System.out.println(twoStackToQueue.deleteHead());
        System.out.println(twoStackToQueue.deleteHead());
        twoStackToQueue.appendTail(5);
        System.out.println(twoStackToQueue.deleteHead());
        System.out.println(twoStackToQueue.getHead());
        System.out.println(twoStackToQueue.deleteHead());
        System.out.println(twoStackToQueue.deleteHead());

    }

}
