package cn.rwj.study.java.algrithm.recursion;

/**
 * @author rwj
 * @since 2024/8/20
 */
public class Fibonacci {

    int[] mem;

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        System.out.println(fibonacci.fibonacci(5000));
        ;

        Fibonacci f2 = new Fibonacci();
        System.out.println(f2.fibonacci(8000));
        ;

    }

    public int fibonacci(int n) {
        mem = new int[n + 1];
        long start = System.currentTimeMillis();
        int res = cal(mem, n);
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + "，结果：" + res);
        return res;
    }

    public int cal(int[] mem, int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        if (mem[n] != 0) return mem[n];
        int n1 = cal(mem, n - 1);
        mem[n - 1] = n1;
        int n2 = cal(mem, n - 2);
        mem[n - 2] = n2;
        if (mem[n - 1] > Integer.MAX_VALUE / 2 || mem[n - 2] > Integer.MAX_VALUE / 2) {
            System.out.println(n);
        }
        return mem[n - 1] + mem[n - 2];
    }

}
