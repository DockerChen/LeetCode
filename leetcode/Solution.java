import java.awt.geom.FlatteningPathIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {
    // linked-list-cycle
    // 快慢指针，快指针一次走2步，慢指针一次走1步
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }

        return false;

    }


    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = Integer.MIN_VALUE;
        while (left < right) {
            int h = Math.min(height[left], height[right]);
            max = Math.max(max, h * (right - left));
            if (height[left] < height[right])
                left++;
            else
                right--;

        }
        return max;

    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;

        }
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int curLength = 0;
        for (int i = 0; i < chars.length; i++) {
            if (!map.containsKey(chars[i])) {
                curLength++;
                maxLength = Math.max(maxLength, curLength);
                map.put(chars[i], i);
            } else {
                int d = i - map.get(chars[i]);
                if (d > curLength) {
                    curLength++;
                } else {
                    curLength = d;
                }
                maxLength = Math.max(maxLength, curLength);
                map.put(chars[i], i);
            }
        }


        return maxLength;

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //先合并两个数组，再求中位数，O(m+n)
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] nums = new int[len1 + len2];
        int i = 0, j = 0, k = 0;
        while (j < nums1.length && k < nums2.length) {
            nums[i++] = nums1[j] < nums2[k] ? nums1[j++] : nums2[k++];
        }
        while (j < nums1.length) {
            nums[i++] = nums1[j++];
        }
        while (k < nums2.length) {
            nums[i++] = nums2[k++];
        }


        if (nums.length % 2 == 1) {
            return nums[nums.length / 2];
        } else {
            return (nums[nums.length / 2 - 1] + nums[nums.length / 2]) / 2.0;
        }

    }

    //方法1：暴力法，时间复杂度O(n^3)
    public String longestPalindrome(String s) {

        if (s == null || s.length() == 0) {
            return s;
        }
        char[] chars = s.toCharArray();
        int max = 0;
        String res = null;
        for (int i = 0; i < chars.length; i++) {
            int j = chars.length - 1;
//            System.out.println(i + " " + j);
            while (i <= j) {
                if (chars[i] != chars[j]) {
                    j--;
                } else {
                    int left = i;
                    int right = j;
                    int flag = 1;
                    while (left < right) {
                        if (chars[left] != chars[right]) {
                            flag = 0;
                            break;
                        }
                        left++;
                        right--;
                    }
                    if (flag == 1) {
                        int cur = j - i + 1;
                        if (max < cur) {
                            max = cur;
                            res = s.substring(i, j + 1);
                        }
                        break;
                    } else {
                        j--;
                    }
                }
            }

        }

        return res;

    }

    //方法2：最长公共子串,时间复杂度O(n^2)
    public String longestPalindrome_1(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String origin = s;
        String reverse = new StringBuffer(s).reverse().toString();
        int len = s.length();
        int[][] arr = new int[len][len];
        int maxLen = 0;
        int maxEnd = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (origin.charAt(i) == reverse.charAt(j)) {
                    if (i == 0 || j == 0) {
                        arr[i][j] = 1;
                    } else {
                        arr[i][j] = arr[i - 1][j - 1] + 1;
                    }
                }
                if (arr[i][j] > maxLen) {
                    //判断首尾字符串是否相同，相同才更新最大长度
                    int before = len - j - 1;
                    if (before + arr[i][j] - 1 == i) {

                        maxLen = arr[i][j];
                        maxEnd = i;
                    }
                }
            }
        }

        return s.substring(maxEnd - maxLen + 1, maxEnd + 1);


    }

    // 方法三：中心扩散法，时间复杂度O(n^2)
    public String longestPalindrome_2(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandCenter(s, i, i);
            int len2 = expandCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);


    }

    private int expandCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    List<String> list = new ArrayList<>();


    //    动态规划，状态转移方程
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[][] dp = new int[s.length()][s.length()];
//        初始化
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 1;
        }

//        状态转移方程
        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < dp[i].length; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }

        return dp[0][s.length() - 1];


    }

    public int countSubstrings(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            ans += getCenterCounts(s, i, i) + getCenterCounts(s, i, i + 1);
        }
        return ans;


    }

    private int getCenterCounts(String s, int left, int right) {
        int ans = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
            ans++;
        }
        return ans;
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }
        int curRow = 0;
        boolean goingDown = false;
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }
            curRow += goingDown ? 1 : -1;
        }
        String res = "";
        for (StringBuilder ss : rows) {
            res += ss;
        }
        return res;


    }

    public int reverse(int x) {
        boolean isNegative = false;
        if (x < 0) {
            isNegative = true;
            x = -x;
        }
        int[] arr = new int[32];
        int remainder = 0;
        int i = 0;
        while (x != 0) {
            remainder = x % 10;
            arr[i++] = remainder;
            x = x / 10;
        }
        //如果可以用long类型来存储res的话，就很简单
        long res = 0;
        for (int j = 0; j < i; j++) {
            res += arr[j] * Math.pow(10, i - j - 1);
        }
        res = isNegative ? -res : res;
        if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) res;
    }

    //    弹出和推入数字 & 溢出前进行检查
    public int reverse_1(int x) {
        int res = 0;
        int pop = 0;
        while (x != 0) {
            pop = x % 10;
            x = x / 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;
            res = res * 10 + pop;
        }


        return res;


    }


    public int myAtoi(String str) {
        //去除字符串左右空格
        str = str.trim();
        if (str == null || str.length() == 0)
            return 0;

        char firstChar = str.charAt(0);
        //标志位，判断正负数
        int sign = 1;
        int start = 0;
        int res = 0;
        if (firstChar == '+') {
            sign = 1;
            start++;
        } else if (firstChar == '-') {
            sign = -1;
            start++;
        }


        for (int i = start; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return res * sign;
            }
            int pop = str.charAt(i) - '0';
            if (sign == 1) {
                if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > 7))
                    return Integer.MAX_VALUE;
            }
            if (sign == -1) {
                if (-res < Integer.MIN_VALUE / 10 || (-res == Integer.MIN_VALUE / 10 && -pop < -8))
                    return Integer.MIN_VALUE;
            }
            res = res * 10 + pop;
        }
        return res * sign;
    }

    //不转化成字符串
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int origin = x;
        int res = 0;
        int pop = 0;
        while (x != 0) {
            pop = x % 10;
            x = x / 10;

            res = res * 10 + pop;
        }


        return res == origin ? true : false;
    }

    //转化成字符串
    public boolean isPalindrome_1(int x) {
        char[] str = String.valueOf(x).toCharArray();
        boolean res = true;
        int left = 0;
        int right = str.length - 1;
        while (left < right) {
            if (str[left] != str[right]) {
                res = false;
                break;
            }
            left++;
            right--;
        }
        return res;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode2;

        Solution solution = new Solution();
//        System.out.println(solution.hasCycle(listNode1));
//        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
//        System.out.println(solution.longestPalindrome("babad"));
//        System.out.println(solution.longestPalindrome("cbbd"));
        System.out.println(solution.longestPalindromeSubseq("bbbab"));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(-12 % 10);


    }


}
