/*
*
1 . 给n个数字和一个目标数字t，判断有有多少组数字满足 a+b == t （a, b 是n个数字中的2个）。
2 . 将 数字、加减乘除、括号 组成的中缀表达式转成后缀表达式（计算量在前面，运算符在后面）。
3 . 实现一个正简单的匹配器，判断字符串s是否包含匹配模式p，匹配模式支持 “.” 和 “*” 。
* */

import java.util.Arrays;
import java.util.Stack;

public class AliyunInterview {
    public static void main(String[] args) {
        AliyunInterview result = new AliyunInterview();
        int[] arr = {1, 2, 3, 4, 5, 6};
//        System.out.println(result.getResult(arr, 7));
        System.out.println(result.isMatch("aabbc", "bd"));

    }

    //1 . 给n个数字和一个目标数字t，判断有有多少组数字满足 a+b == t （a, b 是n个数字中的2个）。
    public int getResult(int[] arr, int target) {
        if (arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);
        int res = 0;
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            int sum = arr[i] + arr[j];
            if (sum == target) {
                res++;
                i++;
                j--;
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return res;

    }

    //2 . 将 数字、加减乘除、括号 组成的中缀表达式转成后缀表达式（计算量在前面，运算符在后面）。
    public String getPostfixExpression(String str) {
        char[] chars = str.toCharArray();
        StringBuilder res = new StringBuilder();

        Stack<Character> numberStack = new Stack<>();
        Stack<Character> characterStack = new Stack<>();
        int num;
        for (int i = 0; i < chars.length; i++) {
            StringBuilder tmp = new StringBuilder();
            boolean flag = true;
            while (chars[i] >= '0' && chars[i] <= '9') {
                tmp.append(chars[i]);
                i++;
                if (i >= chars.length) {
                    flag = false;
                    break;
                }
            }
            num = Integer.parseInt(tmp.toString()) + '0';
            numberStack.push((char) num);
            if (!flag) {
                break;
            }
            if (chars[i] == '+' || chars[i] == '-' || chars[i] == '*' || chars[i] == '/') {
                if (characterStack.isEmpty()) {
                    characterStack.push(chars[i]);
                } else {
                    char chs = characterStack.peek();
                    if (chs == '*' || chs == '/') {
                        if (!numberStack.isEmpty()) {
                            res.append(numberStack.pop());
                        }
                        if (!numberStack.isEmpty()) {
                            res.append(numberStack.pop());
                        }
                        res.append(characterStack.pop());
                    }
                    characterStack.push(chars[i]);
                }
            }

        }

        res.append(numberStack.pop() + characterStack.pop());

        return res.toString();
    }

    //3 . 实现一个正简单的匹配器，判断字符串s是否包含匹配模式p，匹配模式支持 “.” 和 “*” 。
    public boolean isMatch(String s, String p) {

        if (s == null || p == null) {
            return false;
        }

        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();

        int strIndex = 0;
        int patternIndex = 0;
        boolean res = false;

        while (strIndex < str.length && patternIndex < pattern.length) {
            if (pattern[patternIndex] == str[strIndex] || pattern[patternIndex] == '.') {
                res = matchCore(str, strIndex, pattern, patternIndex);
                if (res) {
                    break;
                } else {
                    strIndex++;
                }
            } else {
                strIndex++;
            }
        }

        return res;
    }

    public boolean matchCore(char[] str, int strIndex, char[] pattern, int patternIndex) {
        if (patternIndex == pattern.length) {
            return true;
        }

        //模式第2个是*，且字符串第1个跟模式第1个匹配,分3种匹配模式；如不匹配，模式后移2位
        if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
            if ((strIndex != str.length && pattern[patternIndex] == str[strIndex])
                    || (strIndex != str.length && pattern[patternIndex] == '.')) {
                return matchCore(str, strIndex, pattern, patternIndex + 2)// 模式后移2，视为x*匹配0个字符
                        || matchCore(str, strIndex + 1, pattern, patternIndex + 2)// 视为模式匹配1个字符
                        || matchCore(str, strIndex + 1, pattern, patternIndex);// *匹配1个，再匹配str中的下一个
            } else {
                return matchCore(str, strIndex, pattern, patternIndex + 2);
            }
        }
        //模式第2个不是*，且字符串第1个跟模式第1个匹配，则都后移1位，否则直接返回false
        if ((strIndex != str.length && pattern[patternIndex] == str[strIndex])
                || (strIndex != str.length && pattern[patternIndex] == '.')) {
            return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
        }

        return false;
    }

}
