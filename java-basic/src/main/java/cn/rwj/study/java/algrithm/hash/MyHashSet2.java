package cn.rwj.study.java.algrithm.hash;

import java.util.Arrays;

/**
 * @author rwj
 * @since 2024/8/21
 */
public class MyHashSet2 {

    public static void main(String[] args) {
        MyHashSet2 myHashSet2 = new MyHashSet2();
        myHashSet2.add(1);
        myHashSet2.add(2);
        myHashSet2.add(3);
        myHashSet2.prt();


        myHashSet2.add(4);
        myHashSet2.add(5);
        myHashSet2.add(6);
        myHashSet2.prt();


        myHashSet2.add(7);
        myHashSet2.add(8);
        myHashSet2.prt();
    }

    public void prt() {
        System.out.println(Arrays.toString(ary));
    }



    int size = 256;
    int[] ary = new int[size];

    public MyHashSet2() {
        for(int i = 0; i < size; i++) {
            ary[i] = -1;
        }
    }

    public int hash(int key) {
        return key & (size - 1);
    }

    public void add(int key) {
        int idx = hash(key);
        int flag = 1;
        while(flag <= size) {
            int tmp = ary[idx];

            if(tmp == key) return;
            if(tmp == -1) {
                ary[idx] = key;
                return;
            }
            idx = (idx + 1) % size;

            flag++;
        }
    }

    public boolean contains(int key) {
        int idx = hash(key);
        int flag = 1;
        while(flag <= size) {
            int tmp = ary[idx];

            if(tmp == key) return true;
            if(tmp == -1) return false;
            idx = (idx + 1) % size;

            flag++;
        }
        return false;
    }

}
