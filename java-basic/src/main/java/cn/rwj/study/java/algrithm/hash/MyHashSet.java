package cn.rwj.study.java.algrithm.hash;

import java.util.LinkedList;
import java.util.List;

/**
 * @author rwj
 * @since 2024/8/21
 */
public class MyHashSet {

    LinkedList<Integer>[] linkedLists = new LinkedList[16];
    int bucketSize = 16;

    public void add(int key) {
        int idx = key % bucketSize;
        LinkedList<Integer> tmp = linkedLists[idx];
        if(tmp != null) {   //如果不为空，说明该位置有值；需要遍历找到是否包含该值
            for (Integer i : tmp) {
                if(i == key) return;    // 包含的话，不重复插入
            }
        } else {
            linkedLists[idx] = new LinkedList<>();    // 为空的话，需要初始化一个 LinkedList
        }
        linkedLists[idx].add(key);  // 添加
    }


    public boolean contains(int key) {
        int idx = key % bucketSize;
        LinkedList<Integer> tmp = linkedLists[idx];
        if(tmp == null) return false;
        return tmp.contains(key);
    }

    void remove(int key) {
        int idx = key % bucketSize;
        LinkedList<Integer> tmp = linkedLists[idx];
        if(tmp == null) return;
        tmp.removeIf(i -> i == key);
    }

}
