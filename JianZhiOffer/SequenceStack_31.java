import java.util.Stack;

public class SequenceStack_31 {

    public static boolean judgeSequenceStack(int[] pushSequence, int[] popSequence) {

        //使用stack模拟栈的压入弹出操作
        Stack<Integer> stack = new Stack<>();

        int n = pushSequence.length;
        if (pushSequence == null || popSequence == null || n == 0) {
            return false;
        }

        //pushIndex：压栈序列的下标；popIndex：出栈序列的下标
        for (int pushIndex = 0, popIndex = 0; pushIndex < n; pushIndex++) {
            stack.push(pushSequence[pushIndex]);
            //栈顶元素和出栈序列下标的元素一样时，弹出栈顶元素，继续比较下一个出栈序列元素
            while (!stack.isEmpty() && popIndex < n && stack.peek() == popSequence[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }

        return stack.isEmpty();

    }

    public static void main(String[] args) {
        int[] pushSequence = {1, 2, 3, 4, 5};
        int[] popSequence1 = {4, 5, 3, 2, 1};
        int[] popSequence2 = {4, 3, 5, 1, 2};

        System.out.println(judgeSequenceStack(pushSequence, popSequence1));
        System.out.println(judgeSequenceStack(pushSequence, popSequence2));

    }

}
