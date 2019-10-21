public class MatrixPath_12 {
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || str == null || rows <= 0 || cols <= 0) {
            return false;
        }

        //标记数组，用来记录该字符是否访问过
        boolean[][] mark = new boolean[rows][cols];
        char[][] chars = toArray(matrix, rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (process(chars, str, 0, mark, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    //将一维数组转换成二维数组
    public char[][] toArray(char[] matrix, int rows, int cols) {
        char[][] chars = new char[rows][cols];
        for (int i = 0, index = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                chars[i][j] = matrix[index++];
            }

        }
        return chars;

    }

    //递归函数
    public boolean process(char[][] chars, char[] str, int pathLength, boolean[][] mark, int row, int column) {
        //遍历的路径长度和字符串长度相等，说明，之前的字符都已经成功匹配，返回true
        if (pathLength == str.length) {
            return true;
        }
        //数组下标越界、字符不匹配、字符已经访问过，都返回false
        if (row < 0 || column < 0 || row >= chars.length || column >= chars[0].length
                || chars[row][column] != str[pathLength] || mark[row][column]) {
            return false;
        }
        //字符已访问，标记为true
        mark[row][column] = true;
        //递归遍历该字符傍边的字符，匹配成功，则路径长度加1
        if (process(chars, str, pathLength + 1, mark, row - 1, column) ||
                process(chars, str, pathLength + 1, mark, row + 1, column) ||
                process(chars, str, pathLength + 1, mark, row, column - 1) ||
                process(chars, str, pathLength + 1, mark, row, column + 1)) {
            return true;
        }
        //该字符旁边的字符都不匹配，则说明这条路不符合，还原，将字符的遍历标记设置为false
        mark[row][column] = false;
        return false;

    }

}
