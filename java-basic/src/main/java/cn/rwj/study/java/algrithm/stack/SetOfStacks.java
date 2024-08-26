package cn.rwj.study.java.algrithm.stack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author rwj
 * @since 2024/8/20
 */
public class SetOfStacks {

    public static void main(String[] args) {
        SetOfStacks setOfStacks = new SetOfStacks(2);
        System.out.println(setOfStacks.isEmpty());

        setOfStacks.push(1);
        setOfStacks.push(2);
        setOfStacks.prt();

        System.out.println("-------------------------");
        setOfStacks.push(3);
        setOfStacks.push(4);
        setOfStacks.push(5);
        setOfStacks.prt();

        System.out.println("---------------------------");
        setOfStacks.pop();
        setOfStacks.pop();
        setOfStacks.pop();
        setOfStacks.pop();
        setOfStacks.prt();

        System.out.println("---------------------------");
        setOfStacks.pop();
        setOfStacks.prt();

        System.out.println("---------------------------");
        setOfStacks.pop();
        setOfStacks.prt();
        setOfStacks.pop();
        setOfStacks.prt();
    }

    public void prt() {
        for (Stack<Integer> stack : this.stackList) {
            Integer[] res = stack.toArray(value -> new Integer[0]);
            System.out.println(Arrays.toString(res));
        }
    }

    /* ---------------------------------------------------------------------- */

    LinkedList<Stack<Integer>> stackList = new LinkedList<>();
    int sizePerStack;

    public SetOfStacks(int sizePerStack) {
        Stack<Integer> stack = new Stack<>();
        stackList.addLast(stack);
        this.sizePerStack = sizePerStack;
    }

    public void push(int value) {
        Stack<Integer> stack = stackList.getLast();
        if(stack.size() == this.sizePerStack) { //满了
            Stack<Integer> newStack = new Stack<>();
            stackList.addLast(newStack);
            stack = newStack;
        }
        stack.push(value);  // 无论满没满的，都需要push进去
    }

    public int pop() {
        Stack<Integer> stack = stackList.getLast();
        if(stack.isEmpty()) { // 该stack已空，移除
            if(stackList.size() == 1) return -1; // 最后一个stack的大小为0，说明所有都空，返回 -1
            stackList.removeLast(); // 如果 stackList 剩余不只一个 stack，就移除掉Uzi后一个
        }
        return stackList.getLast().pop();
    }

    public int peek() {
        Stack<Integer> stack = stackList.getLast();
        if(stack.isEmpty() && stackList.size() == 1) return -1;
        return stackList.getLast().pop();
    }

    public boolean isEmpty() {
        return stackList.getFirst().isEmpty();
    }

}
