package cn.rwj.study.java.algrithm.hash;

import lombok.val;

import java.util.Arrays;

/**
 * @author rwj
 * @since 2024/8/21
 */
public class ScalableHashMap {

    public static void main(String[] args) {
        ScalableHashMap hashMap = new ScalableHashMap();

        System.out.println("------------------");
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        hashMap.prt();

        System.out.println("------------------");
        System.out.println(hashMap.get(1));
        System.out.println(hashMap.get(3));
        hashMap.prt();

        System.out.println("------------------");
        hashMap.put(2, 1);
        System.out.println(hashMap.get(2));;
        hashMap.remove(2);
        System.out.println(hashMap.get(2));
        hashMap.prt();
    }

    public void prt() {
        for (int i = 0; i < node.length; i++) {
            System.out.print("【    ");
            if(node[i] == null) {
                System.out.print("  NULL");
            } else {
                Node tmp = node[i];
                while(tmp != null) {
                    System.out.printf("%d,%d |-|", tmp.key, tmp.value);
                    tmp = tmp.next;
                }
            }
            System.out.println("    】");
        }
    }

    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final int INITIAL_CAPACITY = 1 << 3;
    static final float LOAD_FACTOR = 0.75f;

    Node[] node;
    int size;
    int threshold;

    public ScalableHashMap() {
        node = new Node[INITIAL_CAPACITY];
        this.size = 0;
        this.threshold = (int) (node.length * LOAD_FACTOR);
    }

    public int hash(int key) {
        return key & (INITIAL_CAPACITY - 1);
    }

    private void resize() {
        if (node.length == MAXIMUM_CAPACITY) return;

        Node[] old = node;
        node = new Node[INITIAL_CAPACITY * 2];
        for (int i = 0; i < old.length; i++) {
            Node tmp = old[i];
            if (tmp == null) continue;

            int idx = hash(tmp.key);
            node[idx] = tmp;
            old[i] = null;
        }
    }

    public void put(int key, int value) {
        int idx = hash(key);
        /*Node head = node[idx];

        Node prev = head, cur = head;
        while (cur != null) {
            if (cur.key == key) {
                cur.value = value;   // 存在，更新值
                return;
            }
            prev = cur;
            cur = cur.next;
        }   // 这段代码可能已经改变了 prev 指向的节点，所以后面需要把prev 恢复到最开始 或者 使用 head 记录最开始的node[idx]

        Node newNode = new Node(key, value);   // 不存在，插入新节点
        if (head == null) {
            node[idx] = newNode;
        } else {
            head.next = newNode;
        }*/
        Node prev = node[idx], cur = prev;
        while (cur != null) {
            if (cur.key == key) {
                cur.value = value;   // 存在，更新值
                return;
            }
            prev = cur;
            cur = cur.next;
        }   // 这段代码可能已经改变了 prev 指向的节点，所以后面需要把prev 恢复到最开始 或者 使用 head 记录最开始的node[idx]

        Node newNode = new Node(key, value);   // 不存在，插入新节点
        if (node[idx] == null) {
            node[idx] = newNode;
        } else {
            node[idx].next = newNode;
        }

        if (size++ >= threshold) resize();  //扩容 & 数据迁移
    }

    public int get(int key) {
        int idx = hash(key);
        Node tmp = node[idx];
        if (tmp == null) return -1;

        while (tmp != null) {
            if (tmp.key == key) return tmp.value;
            tmp = tmp.next;
        }

        return -1;
    }

    public void remove(int key) {
        /*int idx = hash(key);
        Node tmp = node[idx];
        if (tmp == null) return;

        if (tmp.key == key) {
            node[idx] = node[idx].next;
            tmp.next = null;
            size--;
            return;
        }

        Node prev = tmp, cur = tmp.next;    // 如果第一次 cur ==null， 就会漏掉 tmp.val == key 这种情况，所以前面处理了一下，放后面处理也可以
        while (cur != null) {
            if (cur.key == key) {
                prev.next = cur.next;
                cur.next = null;
                size--;
                return;
            }
            prev = cur;
            cur = cur.next;
        }*/

        // 以下写法就把上面那两种情况整合在一起了
        int idx = hash(key);
        Node tmp = node[idx];
        if (tmp == null) return;

        Node prev = null, cur = node[idx];
        while (cur != null) {
            if (cur.key == key) {
                if(prev == null) {
                    node[idx] = cur.next;
                } else {
                    prev.next = cur.next;
                }
                size--;
                cur.next = null;
                return;
            }
            prev = cur;
            cur = cur.next;
        }
    }

    class Node {
        int key, value;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

}
