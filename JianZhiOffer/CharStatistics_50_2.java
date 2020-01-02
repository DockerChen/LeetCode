public class CharStatistics_50_2 {
    //occurrence,下标表示字符的ascii码，值表示字符出现的位置。
    public int[] occurrence = new int[256];
    //插入的字符个数
    public int index;

    //构造函数
    public CharStatistics_50_2() {
        index = 0;
        for (int i = 0; i < occurrence.length; i++) {
            occurrence[i] = -1;
        }

    }

    //插入
    public void insert(char ch) {


        if (occurrence[ch] == -1) {
            occurrence[ch] = index;
        } else {
            occurrence[ch] = -2;
        }
        index++;
    }

    //得到第一个只出现一次的字符
    public char firstAppearingOnce() {
        char ch = 0;
        int minIndex = Integer.MAX_VALUE;
        for (int i = 0; i < 256; i++) {
            if (occurrence[i] >= 0 && occurrence[i] < minIndex) {
                ch = (char) i;
                minIndex = occurrence[i];
            }

        }
        return ch;

    }


}
