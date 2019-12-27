public class NumSequence_44 {
    public int getDigitAtIndex(int index) {
        if (index < 0) {
            return -1;
        }
        int digit = 1;
        while (true) {
            if (index < getDigitSum(digit)) {
                return digitAtIndex(index, digit);
            } else {
                index -= getDigitSum(digit);
                digit++;
            }
        }

    }

    //计算index的下标
    private int digitAtIndex(int index, int digit) {
        int begin = getBeginNumber(digit);
        int shiftNumber = index / digit;
        String number = (begin + shiftNumber) + "";
        int count = index % digit;
        return number.charAt(count) - '0';

    }

    //得到digit位开始的数字
    private int getBeginNumber(int digit) {
        if (digit == 1) {
            return 0;
        }
        return (int) Math.pow(10, digit - 1);
    }

    //得到digit位的数字位数的总和
    public int getDigitSum(int digit) {
        if (digit == 1) {
            return 10;
        } else {
            return digit * 9 * (int) (Math.pow(10, digit - 1));
        }
    }

    public static void main(String[] args) {
        NumSequence_44 numSequence_44 = new NumSequence_44();
        System.out.println(numSequence_44.getDigitAtIndex(999));
    }
}