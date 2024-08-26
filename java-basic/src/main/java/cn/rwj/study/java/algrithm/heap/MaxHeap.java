package cn.rwj.study.java.algrithm.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author rwj
 * @since 2024/8/22
 */
public class MaxHeap {

    public static void main(String[] args) {
        int[] nums = new int[]{5, 5, 4, 1, 4, 6, 9, 3};
        int kthSmallest = new MaxHeap().findKthSmallest(nums, 3);
        System.out.println(kthSmallest);
    }


    public int findKthSmallest(int[] nums, int k) {
        PriorityQueue<Integer> que = new PriorityQueue<>((a, b) -> a - b);
        for (int num : nums) {
            que.offer(num);     // 一定是先插入再删除
            /*if(que.size() > k) {    // = 时不能删除
                que.poll();
            }*/
        }
        System.out.println(Arrays.toString(que.toArray(new Integer[0])));
        return que.peek();
    }

}
