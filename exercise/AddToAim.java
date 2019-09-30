public class AddToAim {
    public static boolean addToAim(int[] arr, int aim) {
        return process(arr, aim, 0, 0);

    }

    public static boolean process(int[] arr, int aim, int res, int i) {
        if (res == aim) {
            return true;
        }
        //res!=aim
        if (i == arr.length) {
            return false;
        }

        return process(arr, aim, res + arr[i], i + 1) || process(arr, aim, res, i + 1);

    }

    public static boolean addToAimDP(int[] arr, int aim) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        if (aim > sum) {
            return false;
        }

        boolean[][] dp = new boolean[arr.length + 1][sum + 1];

        dp[arr.length][aim] = true;
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = sum; j >= 0; j--) {
                if (j + arr[i] > sum) {
                    dp[i][j] = dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j + arr[i]] || dp[i + 1][j];
                }
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(addToAim(new int[]{1, 2, 3, 4}, 11));
        System.out.println(addToAimDP(new int[]{1, 2, 3, 4}, 11));
        System.out.println(addToAimDP(new int[]{1, 2, 3, 4}, 5));
        System.out.println(addToAimDP(new int[]{5,4,1,3}, 2));

    }
}
