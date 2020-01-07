public class StrToInt_67 {
    public int StrToInt(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        char[] chars = str.toCharArray();
        long res = 0;

        boolean isNegative = str.charAt(0) == '-';
        for (int i = 0; i < chars.length; i++) {
            if (i == 0 && (chars[i] == '+' || chars[i] == '-')) {
                continue;
            }
            if (chars[i] >= '0' && chars[i] <= '9') {
                res += Math.pow(10, chars.length - i - 1) * (chars[i] - '0');
            } else {
                return 0;
            }

        }

        res = isNegative ? -res : res;

        if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) {
            return 0;
        }

        return (int) res;

    }


    public static void main(String[] args) {
        StrToInt_67 test = new StrToInt_67();
        System.out.println(test.StrToInt("-2147483648"));
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MIN_VALUE);
    }
}
