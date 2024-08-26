package cn.rwj.study.java.algrithm.heap;

/**
 * @author rwj
 * @since 2024/8/22
 */
public class MyPriorityQueue {

    int[] nums;
    static final int INIT_CAPACITY = 8;
    int size;

    public MyPriorityQueue() {
        this.nums = new int[INIT_CAPACITY];
        this.size = 0;
    }

    public void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public void add(int value) {
        nums[size] = value;
        size++;
        if (size - 1 == 0) return;

        int curIdx = size - 1;
        while (curIdx > 0) {
            int parIdx = (curIdx - 1) / 2;
            if (nums[curIdx] >= nums[parIdx]) break;

            swap(nums, curIdx, parIdx);
            curIdx = parIdx;
        }

        int len = nums.length;
        if (len == size) {   // 扩容
            int[] tmp = new int[len * 2];
            System.arraycopy(nums, 0, tmp, 0, len);
            this.nums = tmp;
        }
    }

    public void heapify(int[] nums, int n, int i) {
        int minPos, left, right;
        while (true) {
            minPos = i;
            left = 2 * i + 1;
            right = 2 * i + 2;
            if (left <= n && nums[i] >= nums[left]) minPos = left;
            if (right <= n && nums[minPos] >= nums[right]) minPos = right;
            if (minPos == i) break;
            swap(nums, i, minPos);
            i = minPos;
        }
    }

    public int poll() {
        if (size == 0) return -1;
        int tmp = nums[0];
        nums[0] = nums[size - 1];
        size--;
        heapify(nums, size - 1, 0);
        return tmp;
    }

    public int peek() {
        if (size == 0) return -1;
        return nums[0];
    }

}
