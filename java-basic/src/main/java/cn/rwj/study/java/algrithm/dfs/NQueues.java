package cn.rwj.study.java.algrithm.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author rwj
 * @since 2024/8/24
 */
public class NQueues {

    public static void main(String[] args) {
        System.out.println(new NQueues().solveNQueues(8));
    }

    public int solveNQueues(int n) {
        int[][] board = new int[n][n];
        int x = 0, y = 0;
        List<String> list = new ArrayList<>(n);
        backTrack(n, x, y, board, list);
        return list.size();
    }

    public void prt(int[][] board) {
        for (int[] ints : board) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public void backTrack(int n, int x, int y, int[][] board, List<String> list) {
        if (x == n) list.add("*");
        if (x >= n || y >= n) return;

        if (valid(n, x, y, board)) {  // [x, y] 可用
            board[x][y] = 1;
            backTrack(n, x + 1, 0, board, list);
            board[x][y] = 0;
        }

        backTrack(n, x, y + 1, board, list);
    }

    public boolean valid(int n, int x, int y, int[][] board) {
        if (x >= n || y >= n) return false;

        int tmp = 0;
        while (tmp < n) {    // x轴
            if (board[x][tmp] == 1) return false;
            tmp++;
        }
        tmp = 0;

        while (tmp < n) {    // y轴
            if (board[tmp][y] == 1) return false;
            tmp++;
        }

        int tmpx = x, tmpy = y;
        while (tmpx >= 0 && tmpy >= 0) {      // x,y 西北方向
            if (board[tmpx][tmpy] == 1) return false;
            tmpx--;
            tmpy--;
        }
        tmpx = x;
        tmpy = y;

        while (tmpx >= 0 && tmpy < n) {      // x,y 东北方向
            if (board[tmpx][tmpy] == 1) return false;
            tmpx--;
            tmpy++;
        }
        tmpx = x;
        tmpy = y;

        while (tmpx < n && tmpy >= 0) {      // x,y 西南方向
            if (board[tmpx][tmpy] == 1) return false;
            tmpx++;
            tmpy--;
        }
        tmpx = x;
        tmpy = y;

        while (tmpx < n && tmpy < n) {      // x,y 东南方向
            if (board[tmpx][tmpy] == 1) return false;
            tmpx++;
            tmpy++;
        }

        return true;
    }

}
