package cn.rwj.study.java.algrithm.recursion;

/**
 * @author rwj
 * @since 2024/8/20
 */
public class Factorial2 {

    public int factorial(int n) {
        return cal(n);
    }

    public int cal(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;
        return n * cal(n - 1) % 7777777;
    }

}
