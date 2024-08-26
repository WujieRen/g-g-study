package cn.rwj.study.java.algrithm.dynamic;

/**
 * @author rwj
 * @since 2024/8/26
 */
public class MinPathSum {

    public static void main(String[] args) {
        int[][] grid = new int[4][4];
        grid[0] = new int[]{1, 3, 5, 9};
        grid[1] = new int[]{2, 1, 3, 4};
        grid[2] = new int[]{5, 2, 6, 7};
        grid[3] = new int[]{6, 8, 4, 3};
        System.out.println(new MinPathSum().minPathSum(grid, 4, 4));
    }

    int min = Integer.MAX_VALUE;

    public int minPathSum(int[][] grid, int n, int m) {
        int path = 0;
        test(grid, 4, 4, 0, 0, path);
        return min;
    }

    public void test(int[][] grid, int n, int m, int x, int y, int path) {
        if (x >= n || y >= m) return;

        if (x == n - 1 && y == m - 1) {
            min = Math.min(min, path + grid[x][y]);
            return;
        }

        //如果x、y都可以走，那就走 (x, y+1) 或者 (x+1,y) ，两种情况都需要走
        test(grid, n, m, x, y + 1, path + grid[x][y]);
        test(grid, n, m, x + 1, y, path + grid[x][y]);
    }


}
