public class FirstNotRepeatingChar_50 {
    public int FirstNotRepeatingChar(String str) {
        //cnts，下标表示字符的ascii码，值表示字符出现的次数。
        int[] cnts = new int[256];
        for (int i = 0; i < str.length(); i++) {
            cnts[str.charAt(i)]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (cnts[str.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        System.out.println(new FirstNotRepeatingChar_50().FirstNotRepeatingChar("google"));
        System.out.println(new FirstNotRepeatingChar_50().FirstNotRepeatingChar("NXWtnzyoHoBhUJaPauJaAitLWNMlkKwDYbbigdMMaYfkVPhGZcrEwp"));
        char c=0;
        System.out.println(c);
    }
}
