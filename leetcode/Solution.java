import java.util.LinkedList;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/*
 * TODO:
 *  1. leetcode题解
 *  2. 每天刷一刷leetcode
 *  3. 努力是为了站在万人中央成为别人的光
 * */

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

/**
 * Definition for a binary tree node.
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
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

    List<Integer> orderList = new ArrayList<>();

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

    public int lengthOfLongestSubstring_1(String s) {
        if (s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        Map<Character, Integer> windows = new HashMap<>();
        int left = 0;
        int right = 0;
        int res = 0;
        //每次移动right，增大滑动窗口时更新res
        for (char aChar : chars) {
            windows.put(aChar, windows.getOrDefault(aChar, 0) + 1);
            right++;
            while (windows.get(aChar) > 1) {
                char c = chars[left];
                windows.put(c, windows.get(c) - 1);
                left++;
            }
            res = Math.max(res, right - left);

        }
        return res;
    }

    // 方法三：中心扩散法，时间复杂度O(n^2)
    public String longestPalindrome_2(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = expandCenter(s, i, i);
            String s2 = expandCenter(s, i, i + 1);
            res = s1.length() > res.length() ? s1 : res;
            res = s2.length() > res.length() ? s2 : res;

        }

        return res;

    }

    public String expandCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
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

    //方法2：最长公共子串,时间复杂度O(n^2)
    public String longestPalindrome_1(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String reverse = new StringBuffer(s).reverse().toString();
        int len = s.length();
        int[][] arr = new int[len][len];
        int maxLen = 0;
        int maxEnd = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (s.charAt(i) == reverse.charAt(j)) {
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
        StringBuilder res = new StringBuilder();
        for (StringBuilder ss : rows) {
            res.append(ss);
        }
        return res.toString();

    }

    public int reverse(int x) {
        boolean isNegative = false;
        if (x < 0) {
            isNegative = true;
            x = -x;
        }
        int[] arr = new int[32];
        int remainder;
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
        int pop;
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
        if (str.length() == 0)
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

    //不转化成字符串
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int origin = x;
        int res = 0;
        int pop;
        while (x != 0) {
            pop = x % 10;
            x = x / 10;

            res = res * 10 + pop;
        }

        return res == origin;
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

    public void gameOfLife(int[][] board) {
        int[][] res = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, res[i], 0, board[0].length);
        }

        for (int i = 0; i < board.length; i++) {
            int sum;
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
            System.arraycopy(res[i], 0, board[i], 0, board[0].length);
        }

    }

    public int[] nextGreaterNumber(int[] nums) {
        Stack<Integer> stack = new Stack<>();
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

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return res;
    }

    public int[] dailyTemperatures(int[] temp) {
        Stack<Integer> stack = new Stack<>();
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

    private void backtrack(int[] nums, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
//            System.out.println(track);
            res.add(new LinkedList<>(track));
            return;
        }
        for (int num : nums) {
            if (track.contains(num)) {
                continue;
            }
            track.add(num);
            backtrack(nums, track);
            track.removeLast();
        }

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

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] source = s.toCharArray();
        char[] target = t.toCharArray();
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();

        for (char c : source) {
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);
        }
        for (char c : target) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        for (char c : source) {
            if (!sMap.get(c).equals(tMap.get(c))) {

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

    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> preSum = new HashMap<>();
        //base case
        preSum.put(0, 1);
        int res = 0, sum_i = 0;
        for (int num : nums) {
            //sum: 0,...,i
            sum_i += num;
            //sum: 0,...,j
            int sum_j = sum_i - k;
            if (preSum.containsKey(sum_j)) {
                res += preSum.get(sum_j);
            }
            preSum.put(sum_i, preSum.getOrDefault(sum_i, 0) + 1);
        }

        return res;
    }

    public int maxSubArray(int[] nums) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (sum <= 0) {
                sum = num;
            } else {
                sum += num;
            }
            max = Math.max(max, sum);
        }

        return max;

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
        StringBuilder str = new StringBuilder();
        for (; i < res.length; i++) {
            str.append(res[i]);
        }
        return str.length() == 0 ? "0" : str.toString();

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

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int i = 0, j = 0;
        int a1, a2, b1, b2;
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

    private int randInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
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
        System.arraycopy(help, 0, arr, left, help.length);

    }

    public boolean isLeapYear(int year) {

        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
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

    public int removeDuplicates_1(int[] nums) {
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
        ListNode pre = null, cur = head, nxt;
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    public ListNode reverse(ListNode head, ListNode b) {
        ListNode pre = null, cur = head, nxt;
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

    public int missingNumber(int[] nums) {
        int n = nums.length;
        int res = 0;
        res ^= n;
        for (int i = 0; i < n; i++) {
            res ^= i ^ nums[i];
        }
        return res;
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin < 0)
                    continue;
                dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];

    }

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int x = coin; x < amount + 1; ++x) {
                dp[x] += dp[x - coin];
            }
        }
        return dp[amount];

    }

    //最长公共子序列
    public int longestCommonSubsequence(String text1, String text2) {
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        int len1 = chars1.length;
        int len2 = chars2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i < len1 + 1; i++) {
            for (int j = 1; j < len2 + 1; j++) {
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }

    //最长递增子序列（LIS），动态规划
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }

            }
        }
        int res = 0;
        for (int value : dp) {
            res = Math.max(res, value);
        }
        return res;

    }

    //最长递增子序列的个数
    public int findNumberOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        //dp[i] 表示以 nums[i] 结尾的最长递增子序列的长度
        int[] dp = new int[n];
        //count[i] 表示以 nums[i] 结尾的最长递增子序列的个数
        int[] count = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
//                    两者之间的关系：对于 i>j, nums[i] > nums[j] 的时候，如果 dp[ j]+1 > dp[i]，
//                    说明第一次找到 dp[ j]+1 长度且以 nums[ i]结尾的最长递增子序列，
//                    则 count[ i]=count[j]（以 nums[ i]结尾的最长递增子序列的组合方式就等于 nums[ j]目前的组合方式）;
//                    如果 dp[ j]+1 == dp[i] 说明这个长度的递增序列已找到过一次了，则 count[ i]+=count[j]
//                    （现有的组合方式个数加上 count[ j]的组合方式，即为总的组合方式个数）

                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        count[i] += count[j];

                    }

                }

            }
        }
        int max = 0;
        for (int value : dp) {
            max = Math.max(max, value);
        }
        int res = 0;
        for (int i = 0; i < count.length; i++) {
            res = max == dp[i] ? res + count[i] : res;
        }
        return res;

    }

    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        int sum;
        while (i < j) {
            sum = numbers[i] + numbers[j];
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {
                break;
            }
        }
        return new int[]{i + 1, j + 1};

    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String res = strs[0];
        for (int i = 0; i < strs.length - 1; i++) {
            int min = Math.min(res.length(), strs[i + 1].length());
            String tmp = "";
            for (int j = 0; j < min; j++) {

                if (res.charAt(j) == strs[i + 1].charAt(j)) {
                    tmp += strs[i].charAt(j);
                } else {
                    break;
                }
            }
            res = tmp;
            if (res.equals("")) {
                break;
            }
        }
        return res;

    }

    public String minWindow(String s, String t) {
        //need存储t中字符的出现次数
        Map<Character, Integer> need = new HashMap();
        //window存储滑动窗口的中字符的出现次数
        Map<Character, Integer> window = new HashMap();
        char[] source = s.toCharArray();
        char[] target = t.toCharArray();
        for (char value : target) {
            need.put(value, need.getOrDefault(value, 0) + 1);
        }
        //left，right表示滑动窗口的范围
        int left = 0;
        int right = 0;
        //滑动窗口匹配t中字符的个数
        int match = 0;
        //start表示最小覆盖子串开始的下标值
        int start = 0;
        //最小覆盖子串的长度
        int minLen = Integer.MAX_VALUE;

        while (right < source.length) {
            char c = source[right];
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                //t中可能有重复字符
                if (window.get(c) <= need.get(c)) {
                    match++;
                }
            }
            right++;
            while (match == target.length) {
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }
                char c2 = source[left];
                if (need.containsKey(c2)) {
                    window.put(c2, window.get(c2) - 1);
                    if (window.get(c2) < need.get(c2)) {
                        match--;
                    }
                }
                left++;
            }

        }

        if (minLen == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(start, start + minLen);

    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            if (nums[i] > 0) {
                return res;
            }
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }

            int l = i + 1, r = N - 1, target = -nums[i];
            while (l < r) {
                int sum = nums[l] + nums[r];
                if (sum == target) {

                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (l < r && nums[r - 1] == nums[r]) {
                        r--;
                    }
                    l++;
                    r--;

                } else if (sum < target) {
                    l++;
                } else {
                    r--;
                }

            }
        }
        return res;

    }

    //比较简单的逻辑判断，只需注意当前candies的数量是否足够分配给当前的小朋友
    public int[] distributeCandies(int candies, int num_people) {
        int[] ans = new int[num_people];
        int i = 0;
        int count = 1;
        while (candies > 0) {
            ans[i] += Math.min(candies, count);
            candies = candies - count;
            i++;
            count++;
            if (i == num_people) {
                i = 0;
            }

        }
        return ans;

    }

    public int orangesRotting(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshOrange = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    freshOrange++;
                } else if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        int round = 0;
        while (freshOrange > 0 && !queue.isEmpty()) {
            round++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int[] orange = queue.poll();
                assert orange != null;
                int r = orange[0];
                int c = orange[1];
                if (r - 1 >= 0 && grid[r - 1][c] == 1) {
                    grid[r - 1][c] = 2;
                    freshOrange--;
                    queue.add(new int[]{r - 1, c});
                }
                if (r + 1 < M && grid[r + 1][c] == 1) {
                    grid[r + 1][c] = 2;
                    freshOrange--;
                    queue.add(new int[]{r + 1, c});
                }
                if (c - 1 >= 0 && grid[r][c - 1] == 1) {
                    grid[r][c - 1] = 2;
                    freshOrange--;
                    queue.add(new int[]{r, c - 1});
                }
                if (c + 1 < N && grid[r][c + 1] == 1) {
                    grid[r][c + 1] = 2;
                    freshOrange--;
                    queue.add(new int[]{r, c + 1});
                }
            }
        }
        return freshOrange > 0 ? -1 : round;
    }

    public ListNode reverseListByRecursive(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode newHead = reverseListByRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public ListNode reverseListByIteration(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = null;
        ListNode nxt;
        while (head != null) {
            nxt = head.next;
            head.next = cur;
            cur = head;
            head = nxt;
        }
        return cur;

    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n);
        }
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;

    }

    ListNode successor = null;

    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode newHead = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return newHead;

    }

    public int[][] findContinuousSequence_1(int target) {
        List<int[]> res = new ArrayList<>();
        int i = 1;
        int j = 1;
        int sum = 0;
        while (i <= target / 2) {
            if (sum < target) {
                sum += j;
                j++;
            } else if (sum > target) {
                sum -= i;
                i++;
            } else {
                int[] arr = new int[j - i];
                for (int k = i; k < j; k++) {
                    arr[k - i] = k;
                }
                res.add(arr);
                sum -= i;
                i++;
            }
        }
        return res.toArray(new int[res.size()][]);

    }

    class MaxQueue {
        private Queue<Integer> dataQueue;
        private Deque<Integer> maxQueue;

        public MaxQueue() {
            dataQueue = new LinkedList<>();
            maxQueue = new LinkedList<>();

        }

        public int max_value() {

            return maxQueue.isEmpty() ? -1 : maxQueue.peek();

        }

        public void push_back(int value) {
            dataQueue.add(value);
            if (!maxQueue.isEmpty()) {
                while (!maxQueue.isEmpty() && value > maxQueue.getLast()) {
                    maxQueue.pollLast();
                }
            }
            maxQueue.addLast(value);

        }

        public int pop_front() {
            if (dataQueue.isEmpty()) {
                return -1;
            } else {

                int value = dataQueue.poll();
                if (value == maxQueue.peekFirst()) {
                    maxQueue.pollFirst();

                }
                return value;
            }

        }

    }

    public void merge(int[] A, int m, int[] B, int n) {
        int[] res = new int[m + n];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < m && j < n) {
            res[k++] = A[i] < B[j] ? A[i++] : B[j++];
        }
        while (i < m) {
            res[k++] = A[i++];
        }
        while (j < n) {
            res[k++] = B[j++];
        }
        System.arraycopy(res, 0, A, 0, res.length);

    }

    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
            res = Math.max(res, dp[i]);
        }
        return res;

    }

    public int longestCommonSubsequence_1(String text1, String text2) {
        if (text1 == null || text2 == null) {
            return 0;
        }
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];

    }

    public int longestCommonSubStr(String text1, String text2) {
        if (text1 == null || text2 == null) {
            return 0;
        }
        int res = 0;
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                res = Math.max(dp[i][j], res);
            }
        }
        return res;

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

    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        boolean res = false;
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                res = true;
                break;
            }
        }
        return res;

    }

    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int repeat = -1;
        for (int num : nums) {
            if (!set.add(num)) {
                repeat = num;
                break;
            }
        }
        return repeat;

    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                return true;
            }
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            // Find the successor of current element
            Integer s = set.ceiling(nums[i]);
            if (s != null && s <= nums[i] + t) return true;

            // Find the predecessor of current element
            Integer g = set.floor(nums[i]);
            if (g != null && nums[i] <= g + t) return true;

            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    int tree_ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        Depth(root);
        return tree_ans;

    }

    public int Depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = Depth(root.left);
        int rightHeight = Depth(root.right);
        tree_ans = Math.max(tree_ans, leftHeight + rightHeight);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        //先判断当前节点，如果不对，再看左子树，否则，看右子树
        return isSub(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);

    }

    private boolean isSub(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (head.val != root.val) {
            return false;
        }
        head = head.next;
        //如果值相同，继续看，左边和右边有一个满足即可
        return isSub(head, root.left) || isSub(head, root.right);

    }

    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2 == null;
        }
        //先判断当前节点，如果不对，再判断t1的左子树，否则，再判断t1的右子树
        return isSame(t1, t2) || checkSubTree(t1.left, t2) | checkSubTree(t1.right, t2);

    }

    private boolean isSame(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        //判断t1和t2的值是否相等，t1的左子树和t2的左子树是否相等，t1的右子树和t2的右子树是否相等
        return t1.val == t2.val && isSame(t1.left, t2.left) && isSame(t1.right, t2.right);
    }

    int tree_max = 0;

    public int longestZigZag(TreeNode root) {
        //搜索左孩子
        dfs(root.left, 0, 1);
        //搜索右孩子
        dfs(root.right, 1, 1);
        return tree_max;

    }

    public void dfs(TreeNode root, int dir, int dis) {
        if (root == null) {
            return;
        }
        tree_max = Math.max(tree_max, dis);
        //访问的是右孩子
        if (dir == 1) {
            //访问左孩子，dis+1
            dfs(root.left, 0, dis + 1);
            //访问右孩子，dis变为1，从该节点开始遍历
            dfs(root.right, 1, 1);
        } else {
            //访问的是左孩子
            //访问右孩子，dis+1
            dfs(root.right, 1, dis + 1);
            //访问左孩子，dis变为1，从该节点开始遍历
            dfs(root.left, 0, 1);
        }

    }

    public String gcdOfStrings(String str1, String str2) {
        //==,判断两个对象的地址是否相等，即判断两个对象是不是同一个对象，
        //基本数据类型比较的是值，引用数据类型比较的是内存地址
        //equals，String的equals方法是被重写的，比较的是对象的值
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        } else {
            int maxLen = gcd(str1.length(), str2.length());
            return str1.substring(0, maxLen);
        }
    }

    //GCD 算法 (Greatest Common Divisor) 即欧几里得算法 也就是学生时代 课本里学过的辗转相除法
    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public ListNode mergeKLists_1(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return Integer.compare(o1.val, o2.val);
            }
        });
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        for (ListNode listNode : lists) {
            if (listNode != null) {
                priorityQueue.add(listNode);
            }
        }
        while (!priorityQueue.isEmpty()) {
            head.next = priorityQueue.poll();
            head = head.next;
            if (head.next != null) {
                priorityQueue.add(head.next);
            }
        }
        return dummy.next;

    }

    class MyQueue {
        Stack<Integer> stack1;
        Stack<Integer> stack2;

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();

        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            stack1.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {

            while (stack1.size() != 1) {
                stack2.push(stack1.pop());
            }
            int num = stack1.pop();
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
            return num;

        }

        /**
         * Get the front element.
         */
        public int peek() {
            int num = 0;
            while (!stack1.isEmpty()) {
                num = stack1.pop();
                stack2.push(num);
            }

            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
            return num;

        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return stack1.isEmpty();

        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int len = lists.length;
        ListNode res = lists[0];
        for (int i = 0; i < len - 1; i++) {
            res = mergeList(res, lists[i + 1]);
        }
        return res;

    }

    private ListNode mergeList(ListNode list1, ListNode list2) {
        ListNode listNode = new ListNode(-1);
        ListNode head = listNode;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                head.next = list1;
                head = list1;
                list1 = list1.next;
            } else {
                head.next = list2;
                head = list2;
                list2 = list2.next;
            }
        }
        while (list1 != null) {
            head.next = list1;
            head = list1;
            list1 = list1.next;
        }
        while (list2 != null) {
            head.next = list2;
            head = list2;
            list2 = list2.next;
        }
        return listNode.next;

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        return root;

    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;

    }

    public boolean search_1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[left] < nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[left] == nums[mid]) {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                //去掉干扰项
                left++;
            }
        }
        return false;
    }

    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (nums[left] <= nums[right]) {
                return nums[left];
            }
            int mid = left + (right - left) / 2;
            if (nums[left] <= nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return -1;

    }

    public int findMin_1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (nums[left] < nums[right]) {
                return nums[left];
            }
            int mid = left + (right - left) / 2;
            if (nums[left] < nums[mid]) {
                left = mid + 1;
            } else if (nums[left] > nums[mid]) {
                right = mid;
            } else {
                int min = nums[left];
                for (int i = left; i <= right; i++) {
                    min = Math.min(min, nums[i]);
                }
                return min;
            }

        }
        return -1;

    }

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<>();
        }
        int sRow = 0;
        int dRow = matrix.length - 1;
        int sColumn = 0;
        int dColumn = matrix[0].length - 1;

        return printMatrix(matrix, sRow, dRow, sColumn, dColumn);

    }

    public List<Integer> printMatrix(int[][] matrix, int sRow, int dRow, int sColumn, int dColumn) {
        List<Integer> res = new ArrayList<>();
        while (sRow <= dRow && sColumn <= dColumn) {
            //单行
            if (sRow == dRow) {
                for (int i = sColumn; i <= dColumn; i++) {
                    res.add(matrix[sRow][i]);
                }
            }
            //单列
            else if (sColumn == dColumn) {
                for (int i = sRow; i <= dRow; i++) {
                    res.add(matrix[i][sColumn]);
                }
            } else {
                for (int i = sColumn; i <= dColumn; i++) {
                    res.add(matrix[sRow][i]);
                }
                for (int i = sRow + 1; i <= dRow; i++) {
                    res.add(matrix[i][dColumn]);
                }
                for (int i = dColumn - 1; i >= sColumn; i--) {
                    res.add(matrix[dRow][i]);
                }
                for (int i = dRow - 1; i >= sRow + 1; i--) {
                    res.add(matrix[i][sColumn]);
                }
            }
            sRow++;
            sColumn++;
            dRow--;
            dColumn--;

        }
        return res;
    }

    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        for (int value : A) {
            sum += value;
        }
        if (sum % 3 != 0) {
            return false;
        }
        int left = 0;
        int right = A.length - 1;
        int leftSum = A[left];
        int rightSum = A[right];
        while (left + 1 < right) {
            if (leftSum == sum / 3 && rightSum == sum / 3) {
                return true;
            }
            if (leftSum != sum / 3) {
                leftSum += A[++left];
            }
            if (rightSum != sum / 3) {
                rightSum += A[--right];

            }
        }
        return false;

    }

    public int uniquePaths(int m, int n) {
        int[][] arr = new int[n][m];
        arr[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int sum = 0;
                if (i - 1 >= 0) {
                    sum += arr[i - 1][j];
                }
                if (j - 1 >= 0) {
                    sum += arr[i][j - 1];
                }
                arr[i][j] = Math.max(sum, arr[i][j]);
            }
        }

        return arr[n - 1][m - 1];

    }

    public int maxAreaOfIsland(int[][] grid) {

        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, DFS(grid, i, j));
                }
            }

        }
        return maxArea;

    }

    private int DFS(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return 0;
        }
        if (grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = 2;

        return 1 + DFS(grid, i - 1, j) + DFS(grid, i + 1, j)
                + DFS(grid, i, j - 1) + DFS(grid, i, j + 1);

    }

    public String compressString(String S) {
        if (S.equals("") || S.length() == 0) {
            return S;
        }
        StringBuilder res = new StringBuilder();
        int len = S.length();
        int i = 0;
        while (i < len) {
            int j = i;
            while (j < len && S.charAt(j) == S.charAt(i)) {
                j++;
            }
            res.append(S.charAt(i));
            res.append(j - i);
            i = j;
        }
        return res.length() >= S.length() ? S : res.toString();

    }

    public int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> res = inOrder(root, new ArrayList<>());
        return res.get(k - 1);

    }

    public int findSecondMinimumValue(TreeNode root) {
        //没有最小节点
        if (root == null || (root.left == null && root.right == null))
            return -1;

        //找出候选数，默认就是子节点值，如果子节点值和root值相同，递归，在子树中寻找候选数
        int left = root.left.val;
        int right = root.right.val;
        if (left == root.val)
            left = findSecondMinimumValue(root.left);
        if (right == root.val)
            right = findSecondMinimumValue(root.right);

        //如果左右候选数都正常，返回较小值就可
        if (left != -1 && right != -1) {
            return Math.min(left, right);
        }
        //如果候选数有-1，说明整个子树中没有可供候选的数
        if (left != -1) {
            //左子树正常，答案就是左边的候选数
            return left;
        } else {
            //右子树正常，返回答案
            //或者右子树也没有候选数，返回-1，即right
            return right;
        }
    }

    public ArrayList<Integer> inOrder(TreeNode root, ArrayList<Integer> res) {
        if (root == null) {
            return res;
        }
        inOrder(root.left, res);
        res.add(root.val);
        inOrder(root.right, res);
        return res;

    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return orderList;
        }
        inorderTraversal(root.left);
        orderList.add(root.val);
        inorderTraversal(root.right);
        return orderList;
    }

    public class SpinLock {
        private AtomicReference<Thread> cas = new AtomicReference<>();

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

    class MyStack {
        Queue<Integer> queue1;
        Queue<Integer> queue2;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();

        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            queue1.add(x);
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            while (queue1.size() != 1) {
                queue2.add(queue1.poll());
            }
            int num = queue1.poll();
            Queue<Integer> queue;
            queue = queue1;
            queue1 = queue2;
            queue2 = queue;
            return num;
        }

        /**
         * Get the top element.
         */
        public int top() {
            int num = 0;
            while (!queue1.isEmpty()) {
                num = queue1.poll();
                queue2.add(num);
            }

            Queue<Integer> queue;
            queue = queue1;
            queue1 = queue2;
            queue2 = queue;
            return num;

        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return queue1.isEmpty();

        }
    }

    int minRes = Integer.MAX_VALUE;
    TreeNode pre;

    public int minDiffInBST(TreeNode root) {
        if (root == null) {
            return minRes;
        }

        minDiffInBST(root.left);
        if (pre != null) {
            minRes = Math.min(minRes, root.val - pre.val);
        }
        pre = root;
        minDiffInBST(root.right);
        return minRes;

    }

    public boolean exist(char[][] board, String word) {
        boolean res;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    res = DFSStr(board, i, j, word, 0);
                    if (res) {
                        return true;
                    }
                }
            }
        }
        return false;

    }

    private boolean DFSStr(char[][] board, int i, int j, String word, int index) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index)) {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }
        char tmp = board[i][j];
        board[i][j] = '/';
        boolean res = DFSStr(board, i - 1, j, word, index + 1) || DFSStr(board, i + 1, j, word, index + 1)
                || DFSStr(board, i, j - 1, word, index + 1) || DFSStr(board, i, j + 1, word, index + 1);
        board[i][j] = tmp;
        return res;

    }

    public int find(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == arr[mid]) {
                return mid;
            } else if (target < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        //至少有一个区间不相交
        int res = 1;
        int end = intervals[0][1];
        for (int i = 0; i < intervals.length; i++) {
            int start = intervals[i][0];
            if (start >= end) {
                end = intervals[i][1];
                res++;
            }
        }
        return intervals.length - res;

    }

    public boolean makesquare(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (len < 4 || sum % 4 != 0) {
            return false;
        }
        int sideLength = sum / 4;
        int[] data = new int[4];
        Arrays.sort(nums);
        reverse(nums);
        return helper(nums, data, 0, len, sideLength);

    }

    public boolean helper(int[] nums, int[] data, int index, int len, int sideLength) {
        if (index == len) {
            for (int i = 0; i < data.length; i++) {
                if (data[i] != sideLength) {
                    return false;
                }
            }
            return true;
        }

        for (int i = 0; i < data.length; i++) {

            if (data[i] + nums[index] > sideLength) {
                continue;
            }
            data[i] += nums[index];
            if (helper(nums, data, index + 1, len, sideLength)) {

                return true;
            }
            data[i] -= nums[index];

        }

        return false;

    }

    private void reverse(int[] nums) {
        int l = 0, r = nums.length - 1, t;
        while (l < r) {
            t = nums[r];
            nums[r--] = nums[l];
            nums[l++] = t;
        }
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        return makesquare(nums, k);

    }

    public boolean makesquare(int[] nums, int k) {
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (len < k || sum % k != 0) {
            return false;
        }
        int sideLength = sum / k;
        int[] data = new int[k];
        int[] visit = new int[len];
        return helper(nums, data, 0, len, sideLength);

    }

    public static void main(String[] args) {

    }

}

