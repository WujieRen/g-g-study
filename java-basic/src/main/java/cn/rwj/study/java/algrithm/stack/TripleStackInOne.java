package cn.rwj.study.java.algrithm.stack;

/**
 * @author rwj
 * @since 2024/8/20
 */
public class TripleStackInOne {

    int[] ary;
    int stackSize;
    int[] aryTag = new int[3];

    public TripleStackInOne(int stackSize) {
        this.stackSize = stackSize;
        ary = new int[stackSize*3];
    }

    public boolean push(int stackID, int value) {
        int cnt = aryTag[stackID];
        if(cnt == stackSize) return false;

        ary[stackID * stackSize + cnt] = value; //入值
        aryTag[stackID]++;    // 记录stackID对应的数组的元素个数

        return true;
    }

    public int pop(int stackID) {
        int cnt = aryTag[stackID];
        if(cnt==0) return -1;

        int val = ary[stackID * stackSize + cnt - 1];
        aryTag[stackID]--;

        return val;
    }

    public int peek(int stackID) {
        int cnt = aryTag[stackID];
        if(cnt==0) return -1;

        int val = ary[stackID * stackSize + cnt - 1];

        return val;
    }

    public boolean isEmpty(int stackID) {
        return aryTag[stackID] == 0;
    }

}
