package cn.rwj.study.java.mst;

import java.util.ArrayList;
import java.util.List;

/**
 * 详解：
 *      https://juejin.cn/post/6844904016170713096
 * @author rwj
 * @since 2023/11/11
 */
public class TestFinally {

    public static void main(String[] args) {
        System.out.println(t1());
        System.out.println(t4());

        System.out.println(t2());
        System.out.println(t3());
    }
    public static int t1() {
        int i = 0;
        try {
            i += 1;
            return i+=1;
        } finally {
            i += 1;
        }
    }

    public static int t4() {
        int i = 0;
        try {
            i += 1;
            return i+=1;
        } finally {
            i += 1;
//            return  i;
        }
    }

    public static List t2() {
        List<String> list = new ArrayList<>();
        try {
            list.add("a");
            return list;
        } finally {
            list.add("b");
        }
    }
    public static List t3() {
        List<String> list = new ArrayList<>();
        try {
            list.add("a");
            return list;
        } finally {
            list = new ArrayList<>();
            list.add("bbb");
            return list;
        }
    }

}
