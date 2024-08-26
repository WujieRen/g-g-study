package cn.rwj.study.java.algrithm.greedy;

import java.util.*;

/**
 * @author rwj
 * @since 2024/8/23
 */
public class Greedy {

    public static void main(String[] args) {
        int[] s = new int[]{10, 7, 5, 4, 1};
        int[] g = new int[]{6, 8, 11, 3, 2};
//        System.out.println(new Candy().findMostChildren(null, g));

        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(4);
        list.add(3);
        list.add(1);
        list.add(6);
//        System.out.println(new Greedy().findMinWaitingTime(list, 5));

        List<List<Integer>> time = new ArrayList<>() {{
            add(new ArrayList<>() {{
                add(6);
                add(8);
            }});
            add(new ArrayList<>() {{
                add(2);
                add(4);
            }});
            add(new ArrayList<>() {{
                add(3);
                add(5);
            }});
            add(new ArrayList<>() {{
                add(1);
                add(5);
            }});
            add(new ArrayList<>() {{
                add(5);
                add(9);
            }});
            add(new ArrayList<>() {{
                add(8);
                add(10);
            }});
        }};
//        System.out.println(new Greedy().findMostIntervals(time, 6));
        System.out.println(new Greedy().findMostIntervals2(time, 6));
    }

    /**
     * 分糖果
     *
     * @param s
     * @param g
     * @return
     */
    public int findMostChildren(int[] s, int[] g) {
        if (s == null || s.length == 0) {
            return 0;
        }
        if (g == null || g.length == 0) {
            return 0;
        }

        Arrays.sort(s);
        Arrays.sort(g);

        int sl = s.length, gl = g.length;
        int sli = 0, gli = 0, res = 0;
        while (gli < gl) {
            int gln = g[gli];
            while (sli < sl) {
                if (s[sli++] >= gln) {
                    res++;
                    break;
                }
            }
            if (gli == 0 && res == 0) return res;
            gli++;
        }

        return res;
    }

    /**
     * 排队等待被一个窗口服务，返回整体等待最短时间
     *
     * @param t
     * @param n
     * @return
     */
    public int findMinWaitingTime(List<Integer> t, int n) {
        Collections.sort(t);
        System.out.println(t);
        int cur = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0) continue;
            cur += t.get(i - 1);
            sum += cur;
        }
        return sum;
    }

    /**
     * 排队等待被一个窗口服务，返回整体等待最短时间
     *
     * @param t
     * @param n
     * @return
     */
    public int findMinWaitingTime2(List<Integer> t, int n) {
        Collections.sort(t);

        int total = 0;
        for (int i = 1; i < n; ++i) {
            total += t.get(i - 1) * (n - i);    //这里 * (n-i) 相当于是倒着来计算每个人在当前的人被服务时，后面每一个人在这个人被服务时等待的时间
        }
        return total;
    }

    /**
     * 区间覆盖
     *
     * @param intervals
     * @param n
     * @return
     */

    int max = 1;

    public int findMostIntervals(List<List<Integer>> intervals, int n) {
        intervals.sort((o1, o2) -> {
            int first = o1.get(0) - o2.get(0);
            if (first == 0) return o1.get(2) - o2.get(2);
            return first;
        });
//        System.out.println(intervals);

        for (int i = 0; i < n; i++) {
            int path = 1;
            List<Integer> cur = intervals.get(i);
            test(n, intervals, cur, path, i + 1);
        }

        return max;
    }

    public void test(int n, List<List<Integer>> intervals, List<Integer> cur, int path, int nextIdx) {
        for (int j = nextIdx; j < n; ++j) {
            List<Integer> next = intervals.get(j);
            if (cur.get(1) <= next.get(0)) {
                int newPath = path + 1;
                if (newPath > max) max = newPath;
                test(n, intervals, next, newPath, nextIdx + 1);
            }  else {
                test(n, intervals, cur, path, nextIdx + 1);
            }
        }
    }

    public int findMostIntervals2(List<List<Integer>> intervals, int n) {
        intervals.sort((a, b) -> {
            if (Objects.equals(a.get(1), b.get(1))) {
                return a.get(0) - b.get(0);
            } else {
                return a.get(1) - b.get(1);
            }
        });
        System.out.println(intervals);

        int cnt = 1;
        int end = intervals.get(0).get(1);
        // System.out.printf("[%d, %d]\n", intervals.get(0).get(0), intervals.get(0).get(1));
        for (int i = 1; i < intervals.size(); ++i) {
            if (intervals.get(i).get(0) >= end) {
                // System.out.printf("[%d, %d]\n", intervals.get(i).get(0), intervals.get(i).get(1));
                cnt++;
                end = intervals.get(i).get(1);
            }
        }
        return cnt;
    }

}
