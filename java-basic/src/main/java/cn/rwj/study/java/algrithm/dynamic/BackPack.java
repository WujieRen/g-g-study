package cn.rwj.study.java.algrithm.dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rwj
 * @since 2024/8/27
 */
public class BackPack {

    public static void main(String[] args) {
//        int[] weights = new int[]{1, 7, 22, 3, 9, 1, 1, 1, 4};
//        System.out.println(new BackPack().solveKnapsack(21, 9, weights));
        int[] weights = new int[]{2};
        System.out.println(new BackPack().solveKnapsack(2, 1, weights));
    }

    public boolean solveKnapsack(int w, int n, int[] weights) {
//        List<Integer> list = Arrays.stream(weights).boxed().collect(Collectors.toList());
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            list.add(weights[i]);
        }
        return canBeFilled(list, w, 0);
    }

    public boolean canBeFilled(List<Integer> list, int w, int weight) {
        if(weight == w) return true;            //注意这两行的顺序：一旦能 == 就true，不管 list 是否为空
        if (list.isEmpty()) return false;       //注意这两行的顺序；如果这行放前面，有可能是 == 的，但是此时 list 是空了就返回false，此时结果就会出错

        for (int i = list.size() - 1; i >= 0; i--) {
            int tmp = list.remove(i);
            if(canBeFilled(list, w, weight + tmp)) return true;
            list.add(tmp);
        }

        return false;
    }

}
