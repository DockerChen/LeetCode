public class NumberExponent_16 {
    public double Power(double base, int exponent) {
        //非法输入
        if (base == 0 && exponent < 0) {
            throw new RuntimeException("input number error!");
        }
        double res = 1;
        double tmp = exponent;
        if (exponent < 0) {
            exponent = -exponent;
        }
        //O(n)
        for (int i = 1; i <= exponent; i++) {
            res = res * base;
        }
        if (tmp < 0) {
            res = 1 / res;
        }

        return res;

    }

    public double PowerImprove(double base, int exponent) {
        //非法输入
        if (base == 0 && exponent < 0) {
            throw new RuntimeException("input number error!");
        }
        double res = 1;
        double tmp = exponent;
        if (exponent < 0) {
            exponent = -exponent;
        }

        if ((exponent >> 1) == 0) {
            //O(n/2)
            for (int i = 1; i <= exponent / 2; i++) {
                res = res * base;
            }
            res = res * res;
        } else {
            for (int i = 1; i <= (exponent - 1) / 2; i++) {
                res = res * base;
            }
            res = res * res * base;
        }

        if (tmp < 0) {
            res = 1 / res;
        }

        return res;

    }
}
