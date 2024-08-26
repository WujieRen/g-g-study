package cn.rwj.study.java.algrithm.binarysearch;

/**
 * @author rwj
 * @since 2024/8/20
 */
public class Recursion {

    public static void main(String[] args) {

    }

    public int cal(int[] arr, int left, int right, int val) {
        if(left > right) return -1;
        int mid = (left + right) / 2;
        if(arr[mid] < val) return cal(arr, mid+1, right, val);
        if(arr[mid] > val) return cal(arr, left, mid-1, val);
        return mid;
    }

}
