package cn.rwj.study.java.algrithm.heap;

import java.util.PriorityQueue;

/**
 * @author rwj
 * @since 2024/8/22
 */
public class HeapSort {

    public void heapSort(int[] nums) {
        build(nums);
        sort(nums);
    }

    // 2、堆排序
    public void sort(int[] nums) {
        int n = nums.length - 1;
        while(n > 0) {
            swap(nums, 0, n); // 交换 0 和 n、n-1、n-2... 1
            n--;              // 下次交换的 n
            heapify(nums, n, 0);
        }
    }

    // 1、建堆
    public void build(int[] nums) {
        for(int i = nums.length / 2 - 1; i >= 0; --i) {
            heapify(nums, nums.length - 1, i);
        }
    }

    public void heapify(int[] nums, int n, int i) {
        int maxPos;
        while(true) {
            maxPos = i;
            int left = 2 * i + 1, right = 2 * i + 2;
            if(left <= n && nums[i] <= nums[left]) maxPos = left;
            if(right <= n && nums[maxPos] <= nums[right]) maxPos = right;
            if(maxPos == i) break;
            swap(nums, i, maxPos);
            i = maxPos; //这一行千万记得，不然会死循环
        }
    }

    public void swap(int[] nums, int i, int maxPos) {
        int tmp = nums[i];
        nums[i] = nums[maxPos];
        nums[maxPos] = tmp;
    }

}
