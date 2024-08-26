package cn.rwj.study.java.algrithm.test.TimeComplexity;

/**
 * @author rwj
 * @since 2024/8/16
 */
public class Test {

    // array表示一个长度为n的数组
    // 代码中的array.length就等于n
    static int n = 5;
    static int[] array = new int[n];
    static int count = 5;

    public static void main(String[] args) {
        insert(6);
    }

    public static void insert(int val) {
        if (count == array.length) {
            int sum = 0;
            for (int i = 0; i < array.length; ++i) {
                sum = sum + array[i];
            }
            array[0] = sum;
            count = 1;
        }

        array[count] = val;
        ++count;
    }

}
