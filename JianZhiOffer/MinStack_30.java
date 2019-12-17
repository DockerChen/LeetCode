import java.util.Stack;

public class MinStack_30 {
    Stack<Integer> dataStack;
    Stack<Integer> minStack;

    public MinStack_30() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int data) {
        dataStack.push(data);
        if (minStack.isEmpty()) {
            minStack.push(data);
        } else {
            int min = minStack.peek();
            if (data < min) {
                min = data;
            }
            minStack.push(min);
        }

    }

    public int pop() {
        int data = dataStack.pop();
        minStack.pop();
        return data;
    }

    public int min() {
        return minStack.peek();

    }

    public static void main(String[] args) {
        MinStack_30 minStack = new MinStack_30();
        minStack.push(3);
        System.out.println(minStack.min());
        minStack.push(4);
        System.out.println(minStack.min());
        minStack.push(2);
        System.out.println(minStack.min());
        minStack.push(1);
        System.out.println(minStack.min());
        minStack.pop();
        System.out.println(minStack.min());

    }

}