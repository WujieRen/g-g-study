package cn.rwj.study.java.algrithm.queue;

import cn.rwj.study.java.algrithm.ListNode;

/**
 * @author rwj
 * @since 2024/8/20
 */
public class LinkedQueue {


    public static void main(String[] args) {
        LinkedQueue linkedQueue = new LinkedQueue();
        System.out.println(linkedQueue.isEmpty());

        System.out.println("---------------------------");
        int dequeue = linkedQueue.dequeue();
        System.out.println(dequeue);

        System.out.println("---------------------------");
        linkedQueue.enqueue(1);
        System.out.println(linkedQueue.isEmpty());
        linkedQueue.prt();

        System.out.println("---------------------------");
        linkedQueue.enqueue(2);
        linkedQueue.enqueue(3);
        linkedQueue.prt();

        System.out.println("---------------------------");
        System.out.println(linkedQueue.dequeue());


        System.out.println("---------------------------");
        System.out.println(linkedQueue.dequeue());
        System.out.println(linkedQueue.dequeue());
        System.out.println(linkedQueue.dequeue());

        System.out.println("---------------------------");
        System.out.println(linkedQueue.isEmpty());

    }

    public void prt() {
        ListNode tmp = head.next;
        while(tmp != null) {
            System.out.println(tmp.val);
            tmp = tmp.next;
        }
    }

    /* ---------------------------------------------------------------------- */

    ListNode head = new ListNode(-10, null);

    ListNode tail = head;

    public void enqueue(int value) {
        ListNode newNode = new ListNode(value, null);
        tail.next = newNode;
        tail = newNode;
    }

    public int dequeue() {
        if (tail == head || head.next == null) return -1;     //②-
//        if (tail == head) return -1;    // ①-

        int val = head.next.val;
        head.next = head.next.next;
//        if(head.next == null) tail = head;  // ①- 开始没考虑到这点

        return val;
    }

    public boolean isEmpty() {
        return tail == head  || head.next == null;    //②-
//        return tail == head;    // ①-
    }

}
