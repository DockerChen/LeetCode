import java.util.LinkedList;
import java.util.*;

public class Solution1 {
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] res = new int[k];
        Arrays.sort(arr);
        for (int i = 0; i < k; i++) {
            res[i] = arr[i];
        }
        return res;

    }

    public int lastRemaining(int n, int m) {
        int pos = 0;
        for (int i = 2; i <= n; i++) {
            pos = (pos + m) % i;
        }
        return pos;

    }

    public int massage(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        //dp[i][0]表示第i-1天没有预约情况下的最大值
        //dp[i][1]表示第i-1天预约情况下的最大值
        int[][] dp = new int[n][n];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = Math.max(dp[i][1], dp[i - 1][0] + nums[i]);
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    //O(nlogn),排序
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        int res = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] <= A[i - 1]) {
                int pre = A[i];
                A[i] = A[i - 1] + 1;
                res += A[i] - pre;

            }
        }
        return res;

    }

    //O(n)，计数法
    public int minIncrementForUnique_1(int[] A) {
        int[] count = new int[40000];
        int max = 0;
        for (int num : A) {
            count[num]++;
            max = Math.max(max, num);
        }
        int res = 0;
        for (int i = 0; i < max; i++) {
            if (count[i] > 1) {
                res += count[i] - 1;
                count[i + 1] += count[i] - 1;
            }
        }
        if (count[max] > 1) {
            int d = count[max] - 1;
            res += (1 + d) * d / 2;
        }
        return res;

    }

    public boolean hasGroupsSizeX(int[] deck) {
        int[] count = new int[10000];
        for (int i = 0; i < deck.length; i++) {
            count[deck[i]]++;
        }
        int x = count[0];
        for (int i = 0; i < count.length; i++) {
            if (count[i] == 1) {
                return false;
            }
            if (count[i] >= 2) {
                x = gcd(x, count[i]);
                if (x == 1) {
                    return false;
                }
            }
        }
        return true;

    }

    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) {
            return false;
        }
        if (x == 0 || y == 0) {
            return z == 0 || x + y == z;
        }
        return z % gcd(x, y) == 0;

    }

    public boolean canMeasureWater_1(int x, int y, int z) {
        Set<Map.Entry<Integer, Integer>> set = new HashSet<>();
        Stack<Map.Entry<Integer, Integer>> stack = new Stack<>();
        AbstractMap.SimpleEntry<Integer, Integer> simpleEntry = new AbstractMap.SimpleEntry<>(0, 0);
        stack.push(simpleEntry);
        while (!stack.isEmpty()) {
            Map.Entry<Integer, Integer> entry = stack.pop();
            int remainX = entry.getKey();
            int remainY = entry.getValue();
            if (remainX == z || remainY == z || remainX + remainY == z) {
                return true;
            }
            if (set.contains(entry)) {
                continue;
            }
            set.add(entry);
            stack.push(new AbstractMap.SimpleEntry<>(x, remainY));
            stack.push(new AbstractMap.SimpleEntry<>(remainX, y));
            stack.push(new AbstractMap.SimpleEntry<>(0, remainY));
            stack.push(new AbstractMap.SimpleEntry<>(remainX, 0));
            stack.push(new AbstractMap.SimpleEntry<>(remainX - Math.min(remainX, y - remainY), remainY + Math.min(remainX, y - remainY)));
            stack.push(new AbstractMap.SimpleEntry<>(remainX + Math.min(x - remainX, remainY), remainY - Math.min(x - remainX, remainY)));

        }
        return false;
    }

    public int movingCount(int m, int n, int k) {
        int[][] arr = new int[m][n];
        int ans = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        ans++;
        arr[0][0] = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] point = queue.poll();
                int x = point[0];
                int y = point[1];
                //向下
                if (x + 1 < arr.length && arr[x + 1][y] == 0 && !helper(x + 1, y, k)) {
                    arr[x + 1][y] = 1;
                    ans++;
                    queue.add(new int[]{x + 1, y});
                }
                //向右
                if (y + 1 < arr[0].length && arr[x][y + 1] == 0 && !helper(x, y + 1, k)) {
                    arr[x][y + 1] = 1;
                    ans++;
                    queue.add(new int[]{x, y + 1});
                }

            }
        }
        return ans;

    }

    public int movingCountByBFS(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        int res = 0;
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0];
            int y = point[1];
            if (inArea(x, y, m, n) && !visited[x][y] && ((x % 10 + x / 10 + y % 10 + y / 10) <= k)) {
                visited[x][y] = true;
                res++;
                queue.add(new int[]{x + 1, y});
                queue.add(new int[]{x, y + 1});
            }

        }
        return res;

    }

    public boolean inArea(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public boolean helper(int x, int y, int k) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x = x / 10;
        }
        while (y != 0) {
            res += y % 10;
            y = y / 10;
        }
        return res > k;

    }

    public int movingCountByDFS(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return dfs(0, 0, m, n, k, visited);
    }

    public int dfs(int x, int y, int m, int n, int k, boolean[][] visited) {
        if (x >= m || y >= n || k < (x % 10 + x / 10 + y % 10 + y / 10) || visited[x][y]) {
            return 0;
        }
        visited[x][y] = true;
        return 1 + dfs(x + 1, y, m, n, k, visited) + dfs(x, y + 1, m, n, k, visited);

    }

    public void sortColors(int[] nums) {
        int zero = -1;
        int one = 0;
        int two = nums.length;
        while (one < two) {
            if (nums[one] == 0) {
                swap(nums, ++zero, one++);
            } else if (nums[one] == 2) {
                swap(nums, one, --two);
            } else {
                one++;
            }
        }

    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return cmp(root.left, root.right);
    }

    private boolean cmp(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        return cmp(left.left, right.right) && cmp(left.right, right.left);
    }

    public boolean isSymmetricByRecursive(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;

    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return getHeight(root) != -1;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        if (left == -1) {
            return -1;
        }
        int right = getHeight(root.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;

    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        TreeNode node = left;
        left = right;
        right = node;
        invertTree(left);
        invertTree(right);
        return root;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(nums[nums.length / 2]);
        root.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, nums.length / 2));
        root.right = sortedArrayToBST(Arrays.copyOfRange(nums, nums.length / 2 + 1, nums.length));
        return root;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;

    }

    long pre = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        return isValidBST(root.right);

    }

    public int numIslands(char[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    ++ans;
                }

            }
        }
        return ans;

    }

    public void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '2';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }

    public void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean isEdge = i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1;
                if (isEdge && board[i][j] == 'O') {
                    dfs1(board, i, j);
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }

    }

    public void dfs1(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O') {
            return;
        }
        board[i][j] = '#';
        dfs1(board, i + 1, j);
        dfs1(board, i - 1, j);
        dfs1(board, i, j + 1);
        dfs1(board, i, j - 1);

    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();

    }

}
