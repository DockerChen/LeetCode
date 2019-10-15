package Test;

public class CalSum {
    public static int calSumRecursive(int n){
        if(n<=0){
            return 0;
        }
        return n+calSumRecursive(n-1);
    }

    public static int calSum(int n){
        int sum=0;
        for (int i = 1; i <= n; i++) {
            sum+=i;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(calSum(5000));
        System.out.println(calSumRecursive(5000));


    }
}
