import java.awt.*;
import java.util.LinkedList;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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

    public void gameOfLife(int[][] board) {
        int[][] res = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                res[i][j] = board[i][j];
            }
        }

        for (int i = 0; i < board.length; i++) {
            int sum = 0;
            for (int j = 0; j < board[0].length; j++) {

                sum = state(i - 1, j - 1, board) + state(i - 1, j, board) + state(i - 1, j + 1, board)
                        + state(i, j - 1, board) + state(i, j + 1, board)
                        + state(i + 1, j - 1, board) + state(i + 1, j, board) + state(i + 1, j + 1, board);

                if (sum < 2) {
                    res[i][j] = 0;
                }

                if (sum == 3) {
                    res[i][j] = 1;
                }
                if (sum > 3) {
                    res[i][j] = 0;
                }

            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = res[i][j];
            }
        }

    }

    public int state(int row, int column, int[][] board) {
        if (row < 0 || row >= board.length) {
            return 0;
        }
        if (column < 0 || column >= board[0].length) {
            return 0;
        }

        return board[row][column];

    }

    /**
     * Definition for a binary tree node.
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        oneSideMax(root);
        return ans;
    }

    public int oneSideMax(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(0, oneSideMax(root.left));
        int right = Math.max(0, oneSideMax(root.right));
        ans = Math.max(ans, left + right + root.val);
        return Math.max(left, right) + root.val;
    }

    public boolean isVaildBST(TreeNode root) {
        return isVaildBST(root, null, null);
    }

    private boolean isVaildBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.val <= min.val) {
            return false;
        }
        if (max != null && root.val >= max.val) {
            return false;
        }
        return isVaildBST(root.left, min, root) && isVaildBST(root.right, root, max);
    }

    boolean isInBST(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        if (root.val == target) {
            return true;
        } else if (root.val > target) {
            return isInBST(root.left, target);
        } else {
            return isInBST(root.right, target);
        }
    }

    public int[] nextGreaterNumber(int[] nums) {
        Stack<Integer> stack = new Stack<Integer>();
        int[] res = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);

        }
        return res;

    }

    public int[] dailyTemperatures(int[] temp) {
        Stack<Integer> stack = new Stack<Integer>();
        int[] res = new int[temp.length];
        for (int i = temp.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temp[stack.peek()] <= temp[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);

        }
        return res;
    }

    class MonotonicQueue {
        private Deque<Integer> data;

        MonotonicQueue() {
            data = new LinkedList<>();
        }

        public void push(int num) {
            while (!data.isEmpty() && data.getLast() < num) {
                data.pollLast();
            }
            data.add(num);

        }

        public void pop(int num) {
            if (!data.isEmpty() && data.peek() == num) {
                data.poll();
            }
        }

        public int max() {
            return data.peek();
        }

    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[]{};
        }
        MonotonicQueue windows = new MonotonicQueue();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (i < k - 1) {
                windows.push(nums[i]);
            } else {
                windows.push(nums[i]);
                res[j++] = windows.max();
                windows.pop(nums[i - k + 1]);

            }
        }
        return res;

    }

    ListNode reverse(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;

    }

    ListNode successor = null;//后驱节点

    ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;

    }

    ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n);
        }
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;

    }

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return res;
    }

    private void backtrack(int[] nums, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
//            System.out.println(track);
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])) {
                continue;
            }
            track.add(nums[i]);
            backtrack(nums, track);
            track.removeLast();
        }

    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> needs = new HashMap<>();
        char[] source = s.toCharArray();
        char[] target = t.toCharArray();
        for (int i = 0; i < target.length; i++) {
            needs.put(target[i], needs.getOrDefault(target[i], 0) + 1);
        }
        System.out.println(needs);
        int left = 0;
        int right = 0;
        //s中匹配t中字符的个数
        int match = 0;
        int start = 0;
        int minLen = Integer.MAX_VALUE;
        List<String> res = new ArrayList<>();
        while (right < source.length) {
            char c = source[right];
            if (needs.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                //t中可能会有重复的字符串
                if (window.get(c) <= needs.get(c)) {
                    match++;
                }
            }
            right++;

            while (match == target.length) {
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }

                if (right - left == target.length) {
                    res.add(s.substring(left));
                }

                char c2 = source[left];
                if (needs.containsKey(c2)) {
                    window.put(c2, window.get(c2) - 1);
                    if (window.get(c2) < needs.get(c2)) {
                        match--;
                    }
                }
                left++;

            }

        }
        System.out.println(minLen);
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);

    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] source = s.toCharArray();
        char[] target = t.toCharArray();
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();

        for (int i = 0; i < source.length; i++) {
            sMap.put(source[i], sMap.getOrDefault(source[i], 0) + 1);
        }
        for (int i = 0; i < target.length; i++) {
            tMap.put(target[i], tMap.getOrDefault(target[i], 0) + 1);
        }

        for (int i = 0; i < source.length; i++) {
            if (!sMap.get(source[i]).equals(tMap.get(source[i]))) {

                return false;
            }

        }
        for (int i = 0; i < target.length; i++) {
            if (!sMap.get(source[i]).equals(tMap.get(source[i]))) {
                return false;
            }

        }

        return true;

    }

    public boolean isAnagram_1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();

        Arrays.sort(s1);
        Arrays.sort(s2);
        System.out.println(String.valueOf(s1) + " " + String.valueOf(s2));

        return Arrays.equals(s1, s2);
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String str = String.valueOf(chars);
            List<String> list = new ArrayList<>();
            if (map.containsKey(str)) {
                list = map.get(str);
                list.add(s);
            } else {
                list.add(s);
                map.put(str, list);
                stringList.add(str);
            }
        }

        for (String ss : stringList) {
            res.add(map.get(ss));
        }
        return res;

    }

    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            n = n & (n - 1);
        }
        return res;

    }

    LinkedList<Integer> res1 = new LinkedList<>();

    public List<Integer> pancakeSort(int[] A) {
        pancakeSort(A, A.length);
        return res1;
    }

    public void pancakeSort(int[] cakes, int n) {
        if (n == 1) {
            return;
        }
        int maxCake = 0;
        int maxCakeIndex = 0;
        for (int i = 0; i < n; i++) {
            if (cakes[i] > maxCake) {
                maxCakeIndex = i;
                maxCake = cakes[i];
            }
        }

        reverseCake(cakes, 0, maxCakeIndex);
        res1.add(maxCakeIndex + 1);
        reverseCake(cakes, 0, n - 1);
        res1.add(n);

        pancakeSort(cakes, n - 1);

    }

    private void reverseCake(int[] cakes, int i, int j) {
        while (i < j) {
            int tmp = cakes[i];
            cakes[i] = cakes[j];
            cakes[j] = tmp;
            i++;
            j--;
        }

    }

    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> preSum = new HashMap<>();
        //base case
        preSum.put(0, 1);
        int res = 0, sum_i = 0;
        for (int i = 0; i < n; i++) {
            //sum: 0,...,i
            sum_i += nums[i];
            //sum: 0,...,j
            int sum_j = sum_i - k;
            if (preSum.containsKey(sum_j)) {
                res += preSum.get(sum_j);
            }
            preSum.put(sum_i, preSum.getOrDefault(sum_i, 0) + 1);
        }

        return res;
    }

    private int capacity = 10;

    private int[][] ans_1 = new int[capacity][];

    //快慢指针
    public int[][] findContinuousSequence(int target) {

        int small = 1, big = 2;
        int sum;

        int t = 0, p = 0;

        // 其实取到 target / 2 就可以了
        while (small < big && small <= target / 2) {
            sum = ((small + big) * (big - small + 1)) / 2;
            if (sum < target) {
                big++;
            } else if (sum > target) {
                small++;
            } else {

                int[] subAns = new int[big - small + 1];

                for (int i = small; i <= big; i++) {
                    subAns[t++] = i;
                }

                t = 0;
                ans_1[p++] = subAns;

                if (p == capacity) {
                    enlargeCapacity();
                }

                small++;
            }
        }

        changeCapacity(p);

        return ans_1;
    }

    /**
     * 扩容
     */
    private void enlargeCapacity() {
        capacity += 10;
        ans_1 = Arrays.copyOf(ans_1, capacity);
    }

    /**
     * 删除数组多余空间
     */
    private void changeCapacity(int retCapacity) {
        ans_1 = Arrays.copyOf(ans_1, retCapacity);
    }

    public int maxSubArray(int[] nums) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (sum <= 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            max = Math.max(max, sum);
        }

        return max;

    }

    public String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        int[] res = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + res[p2];
                res[p2] = sum % 10;
                res[p1] += sum / 10;
            }
        }
        int i = 0;
        while (i < res.length && res[i] == 0) {
            i++;
        }
        String str = "";
        for (; i < res.length; i++) {
            str += String.valueOf(res[i]);
        }
        return str.length() == 0 ? "0" : str;

    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int orignColor = image[sr][sc];
        fill(image, sr, sc, orignColor, newColor);
        return image;

    }

    private void fill(int[][] image, int x, int y, int orignColor, int newColor) {
        if (!inArea(image, x, y)) {
            return;
        }
        if (image[x][y] != orignColor) {
            return;
        }
        if (image[x][y] == -1) {
            return;
        }
        image[x][y] = -1;
        fill(image, x - 1, y, orignColor, newColor);
        fill(image, x + 1, y, orignColor, newColor);
        fill(image, x, y - 1, orignColor, newColor);
        fill(image, x, y + 1, orignColor, newColor);
        image[x][y] = newColor;
    }

    private boolean inArea(int[][] image, int x, int y) {
        if (x < 0 || x >= image.length) {
            return false;
        }
        if (y < 0 || y >= image[0].length) {
            return false;
        }
        return true;
    }

    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        if (intervals == null || intervals.length == 0) {
            return res.toArray(new int[0][]);
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int i = 0;
        while (i < intervals.length) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            while (i < intervals.length - 1 && right >= intervals[i + 1][0]) {
                i++;
                right = Math.max(right, intervals[i][1]);
            }
            res.add(new int[]{left, right});
            i++;
        }

        return res.toArray(new int[0][]);

    }

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int i = 0, j = 0;
        int a1 = 0, a2 = 0, b1 = 0, b2 = 0;
        List<int[]> res = new ArrayList<>();
        while (i < A.length && j < B.length) {
            a1 = A[i][0];
            a2 = A[i][1];
            b1 = B[j][0];
            b2 = B[j][1];
            if (a2 >= b1 && a1 <= b2) {
                res.add(new int[]{Math.max(a1, b1), Math.min(a2, b2)});
            }
            if (a2 < b2) {
                i++;
            } else {
                j++;
            }
        }
        return res.toArray(new int[0][]);

    }

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        int n = envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0];
            }
        });
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = envelopes[i][1];
        }
        return lengthOfLIS(height);

    }

    //最长递增子序列（LIS），动态规划
    private int lengthOfLIS(int[] height) {
        int[] dp = new int[height.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < height.length; i++) {
            for (int j = 0; j < i; j++) {
                if (height[j] < height[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = dp[0];
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public void shuffle(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int rand = randInt(i, n - 1);
            swap(arr, arr[i], arr[rand]);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private int randInt(int min, int max) {
        Random random = new Random();
        int res = random.nextInt(max) % (max - min + 1) + min;
        return res;
    }

    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int rootCount = count(root, sum);
        int leftCount = pathSum(root.left, sum);
        int rightCount = pathSum(root.right, sum);
        return rootCount + leftCount + rightCount;

    }

    private int count(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }
        int isMe = (node.val == sum) ? 1 : 0;
        int left = count(node.left, sum - node.val);
        int right = count(node.right, sum - node.val);
        return isMe + left + right;
    }

    public void mergeSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    public void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (right - left) >> 1 + left;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);

    }

    private void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = right;
        while (p1 <= left && p2 <= right) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= left) {
            help[i++] = arr[p1++];
        }
        while (p2 <= right) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[left + j] = help[j];
        }

    }

    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }
        return count;

    }

    public boolean isLeapYear(int year) {

        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        }
        return false;
    }

    public class SpinLock {
        private AtomicReference<Thread> cas = new AtomicReference<Thread>();

        public void lock() {
            Thread current = Thread.currentThread();
            // 利用CAS
            while (!cas.compareAndSet(null, current)) {
                // DO nothing
            }
        }

        public void unlock() {
            Thread current = Thread.currentThread();
            cas.compareAndSet(current, null);
        }
    }

    //接雨水
    public int trap(int[] height) {
        int sum = 0;

        for (int i = 0; i < height.length; i++) {
            int left_max = 0;
            int right_max = 0;
            for (int j = 0; j <= i; j++) {
                left_max = Math.max(left_max, height[j]);
            }

            for (int j = i; j < height.length; j++) {
                right_max = Math.max(right_max, height[j]);
            }

            sum += Math.min(left_max, right_max) - height[i];

        }
        return sum;

    }

    public int trapByTwoPointer(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int sum = 0;
        int left_max = height[0];
        int right_max = height[height.length - 1];
        int left = 0;
        int right = height.length - 1;
        while (left <= right) {
            left_max = Math.max(left_max, height[left]);
            right_max = Math.max(right_max, height[right]);
            if (left_max < right_max) {
                sum += left_max - height[left];
                left++;
            } else {
                sum += right_max - height[right];
                right--;
            }
        }

        return sum;

    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int slow = 0;
        int fast = 1;
        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;

    }

    public ListNode removeLinkedListDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null) {
            if (slow.val != fast.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        slow.next = null;
        return head;

    }

    public String longestPalindrome_3(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = getPalindrome(s, i, i);
            String s2 = getPalindrome(s, i, i + 1);
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;

    }

    private String getPalindrome(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return s.substring(i + 1, j);
    }

    public ListNode reverse_1(ListNode head) {
        ListNode pre = null, cur = head, nxt = null;
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    public ListNode reverse(ListNode head, ListNode b) {
        ListNode pre = null, cur = head, nxt = null;
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode a, b;
        a = b = head;
        for (int i = 1; i <= k; i++) {
            if (b == null) {
                return head;
            }
            b = b.next;
        }
        ListNode newHead = reverse(a, b);
        a.next = reverseKGroup(b, k);
        return newHead;
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

        System.out.println(solution.permute(new int[]{1, 2, 3}));
        System.out.println(solution.minWindow("aa", "aa"));
        String s = "atbc";
        String t = "tcba";
        System.out.println(solution.isAnagram_1(s, t));
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(solution.groupAnagrams(strs));
        int[] arr = {3, 5, 2, -2, 4, 1};
        System.out.println(solution.subarraySum(arr, 8));
        System.out.println(solution.findContinuousSequence(9));
        System.out.println(solution.multiply("123", "45"));

        String s1 = new String("abc");// 堆内存的地址值
        String s2 = "abc";
        System.out.println(s1 == s2);// 输出 false,因为一个是堆内存，一个是常量池的内存，故两者是不同的。
        System.out.println(s1.equals(s2));// 输出 true
        for (int i = 2000; i <= 3000; i++) {
            if (solution.isLeapYear(i)) {
                System.out.print(i + " ");

            }

        }

    }

}
