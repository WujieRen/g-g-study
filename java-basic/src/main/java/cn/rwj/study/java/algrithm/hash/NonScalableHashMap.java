package cn.rwj.study.java.algrithm.hash;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author rwj
 * @since 2024/8/21
 */
public class NonScalableHashMap {

    int size = 16;
    LinkedList<Integer[]>[] map;

    public static void main(String[] args) {
        NonScalableHashMap myHashMap = new NonScalableHashMap();
        myHashMap.put(1,1);
        myHashMap.put(17, 2);
        myHashMap.prt();

        System.out.println("----------------------------");

        myHashMap.put(1, 2);
        myHashMap.prt();
        System.out.println("----------------------------");

        myHashMap.put(33, 3);
        myHashMap.put(34, 1);
        myHashMap.put(16, 1);
        myHashMap.put(0, 2);
        myHashMap.prt();
        System.out.println("----------------------------");

        myHashMap.remove(17);
        myHashMap.remove(0);
        myHashMap.prt();
    }

    public void prt() {
        for (LinkedList<Integer[]> intsList : map) {
            System.out.print("【   ");
            if(intsList == null) {
                System.out.print(" NULL, ");
            }
            else {
                intsList.forEach(i -> System.out.print(Arrays.toString(i)));
            }
            System.out.println("   】");
        }
    }

    public NonScalableHashMap() {
        map = new LinkedList[size];
    }

    public int hash(int key) {
        return key & (size - 1);
    }


    public void put(int key, int value) {
        int idx = hash(key);
        if(map[idx] == null)  { // 该位置没插入过元素，新建一个list
            map[idx] = new LinkedList<>();
        }

        for (Integer[] ints : map[idx]) {
            if (ints[0] == key) {
                ints[1] = value;
                return; // 如果key存在，更新值后，结束
            }
        }

        Integer[] ints = new Integer[2];
        ints[0] = key;
        ints[1] = value;
        map[idx].add(ints);    // key 不存在，插入
    }

    public int get(int key) {
        int idx = hash(key);
        if(map[idx] == null)  return -1;
        for (Integer[] ints : map[idx]) {
            if(ints[0] == null) return -1;
            if(ints[0] == key) return ints[1];
        }
        return -1;
    }

    public void remove(int key) {
        int idx = hash(key);
        if(map[idx] == null)  return;
        map[idx].removeIf(i -> i[0] == key);
    }

}
