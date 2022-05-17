package com.lwq.listnode;

public class ListNodeToDeQueue {
    class Node<V> {
        V val;
        Node<V> next;
        Node<V> last;

        public Node(V val) {
            this.val = val;
            next = null;
            last = null;
        }
    }

    class MyDeQueue<V> {
        Node<V> head;
        Node<V> tail;
        int size;

        public MyDeQueue() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void pushHead(V node) {
            Node<V> vNode = new Node<>(node);
            if (head == null) {
                tail = vNode;
                head = vNode;
            } else {
                vNode.next = head;
                head.last = vNode;
                head = vNode;
            }
            size++;
        }

        public void pushTail(V node) {
            Node<V> vNode = new Node<>(node);
            if (tail == null) {
                tail = vNode;
                head = vNode;
            } else {
                tail.next = vNode;
                vNode.last = tail;
                tail = vNode;
            }
            size++;
        }

        public V popHead() {
            V res = null;
            if (head == null) {
                return res;
            }
            size--;
            res = head.val;
            if (head != tail) {
                head = head.next;
                head.last = null;
            } else {
                head = null;
                tail = null;
            }
            return res;
        }

        public V popTail() {
            V res = null;
            if (tail == null) {
                return res;
            }
            size--;
            res = tail.val;
            if (head != tail) {
                tail = tail.last;
                tail.next = null;
            } else {
                head = null;
                tail = null;
            }
            return res;
        }


    }
}
