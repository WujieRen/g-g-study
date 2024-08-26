package cn.rwj.study.java.algrithm.recursion;

/**
 * 这种解法有点浪费内存了，因为计算过程中并不会出现重复使用 f(n)，也就没有 剪枝/优化 的空间
 * @author rwj
 * @since 2024/8/20
 */
public class Factorial1 {

    int[] mem;
    public int factorial(int n) {
        mem = new int[n+1];
        return cal(n);
    }

    public int cal(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;
        if(mem[n] != 0) return mem[n];

        mem[n] = n * cal(n - 1) % 7777777;

        return mem[n];
    }

}
