package cn.rwj.study.java.algrithm.dfs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rwj
 * @since 2024/8/23
 */
public class Sum {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 5, 1, 8};
        System.out.println(new Sum().sumKNums(nums, 5, 3));
    }

    public List<Integer> sumKNums(int nums[], int n, int k) {
        ArrayList<Integer> res = new ArrayList<>(k);

//        int[] usedIndex = new int[n];
//        sumKNums1(res, k, 0, nums, usedIndex, 0);

//        List<Integer> midList = Arrays.stream(nums).boxed().collect(Collectors.toList());
//        sumKNums2(n, midList, res, k, 0);

        sumKNums3(nums, res, 0, k, 0, 0);
        return res.stream()
                .distinct()
                .sorted(Comparator.comparingInt(o -> o))
                .collect(Collectors.toList());
    }

    private void sumKNums3(int nums[], List<Integer> path, int index, int k, int sum, int pathLen) {
        if (pathLen == k) {
            path.add(sum);
            return;
        }
        if (index == nums.length) {
            return;
        }
        sumKNums3(nums, path, index + 1, k, sum + nums[index], pathLen + 1);
        sumKNums3(nums, path, index + 1, k, sum, pathLen);
    }

    private void sumKNums2(int n, List<Integer> midList, List<Integer> res, int k, int sum) {
        if (n - midList.size() == k) {
            res.add(sum);
            return;
        }

        for (int i = midList.size() - 1; i >= 0; i--) {
            Integer tmp = midList.get(i);
            midList.remove(i);
            sumKNums2(n, midList, res, k, sum + tmp);
            midList.add(tmp);
        }
    }

    private void sumKNums1(ArrayList<Integer> res, int k, int sum, int[] nums, int[] usedIdx, int path) {
        if (path == k) {
            res.add(sum);
            return;
        }

        for (int i = 0; i < nums.length; ++i) {
            if (usedIdx[i] == 0) {
                usedIdx[i] = 1;
                sumKNums1(res, k, sum + nums[i], nums, usedIdx, path + 1);
                usedIdx[i] = 0;
            }
        }
    }

}
