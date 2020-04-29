import java.util.Stack;

public class ByteDance {
    //f(n)=f(n-1)+f(n-2),n>=3
    public int rectangleCover(int n) {
        if (n <= 2) {
            return n;
        }
        int res = 0, pre1 = 1, pre2 = 2;
        for (int i = 3; i <= n; i++) {
            res = pre1 + pre2;
            pre1 = 2;
            pre2 = res;

        }
        return res;
    }

    public int[] findMaxRightWithStack(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[arr.length];
        int i = 0;
        while (i < arr.length) {
            if (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                res[stack.pop()] = arr[i];
            } else {
                stack.push(i);
                i++;
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
