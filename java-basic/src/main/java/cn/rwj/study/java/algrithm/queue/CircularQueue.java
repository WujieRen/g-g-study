package cn.rwj.study.java.algrithm.queue;

import java.util.Arrays;

/**
 * @author rwj
 * @since 2024/8/20
 */
public class CircularQueue {

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(3);

        System.out.println("---------------------------");
        System.out.println(queue.isEmpty());

        System.out.println("---------------------------");
        System.out.println(queue.dequeue());;

        System.out.println("---------------------------");
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.prt();

        System.out.println("---------------------------");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.prt();
        System.out.println(queue.isEmpty());

        System.out.println("----------------插满-----------");
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.enqueue(3));
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.prt();
        System.out.println(queue.isEmpty());

        System.out.println("-------------dequeue四次--------------");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.prt();


        System.out.println("-------------deque所有--------------");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.prt();


    }

    public void prt() {
        System.out.println(String.format("%s,%s,%s", head, tail, Arrays.toString(ary)));
    }


    /* ---------------------------------------------------------------------- */

    int[] ary;
    int queueSize;
    int head;
    int tail;

    public CircularQueue(int queueSize) {
        this.ary = new int[queueSize+1];
        this.queueSize = queueSize+1;
        this.head = 0;
        this.tail = 0;
    }

    public boolean enqueue(int item) {
        if (tail + 1 % queueSize == head) return false;

        ary[tail] = item;
        tail++;
        if(tail == queueSize) tail = tail % queueSize;

        return true;
    }

    public int dequeue() {
        if(head == tail) return -1;

        int popVal = ary[head];
        head++;
        if(head == queueSize) head = head  % queueSize;

        return popVal;
    }

    public boolean isEmpty() {
        return head == tail;
    }

}
