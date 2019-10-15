public class Fibonacci_10 {
    public static int calFibonacciRecursive(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        return calFibonacciRecursive(n - 1) + calFibonacciRecursive(n - 2);
    }

    public static int calFibonacciNoRecursive(int n) {
        int[] res={0,1};
        if(n<2){
            return res[n];
        }
        int f1=0;
        int f2=1;
        int f=0;
        for (int i = 2; i <=n; i++) {
            f=f1+f2;
            f1=f2;
            f2=f;
        }

        return f;

    }

    public static void main(String[] args) {
        System.out.println(calFibonacciRecursive(10));
        System.out.println(calFibonacciNoRecursive(20));

    }
}
