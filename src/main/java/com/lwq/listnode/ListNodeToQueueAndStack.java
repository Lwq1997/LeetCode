package com.lwq.listnode;

import com.lwq.DoubleNode;
import com.lwq.ListNode;

public class ListNodeToQueueAndStack {
    class Node<V> {
        V val;
        Node<V> next;

        public Node(V val) {
            this.val = val;
        }
    }

    class MyQueue<V> {
        Node<V> head;
        Node<V> tail;
        int size;

        public MyQueue() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void offer(V node) {
            Node<V> vNode = new Node<>(node);
            //直接插入到tail后面
            if (tail == null) {
                tail = vNode;
                head = vNode;
            } else {
                tail.next = vNode;
                tail = vNode;
            }
            size++;
        }

        public V pop() {
            V res = null;
            //直接删除head
            if (head != null) {
                res = head.val;
                head = head.next;
                size--;
            }
            if (head == null) {
                tail = null;
            }
            return res;
        }

        public V peek() {
            V res = null;
            //直接返回head
            if (head != null) {
                res = head.val;
            }
            return res;
        }
    }

    class MyStack<V> {
        private Node<V> head;
        private int size;

        public MyStack() {
            head = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void push(V node) {
            Node<V> vNode = new Node<>(node);
            if (head == null) {
                head = vNode;
            } else {
                vNode.next = head;
                head = vNode;
            }
            size++;
        }

        public V pop() {
            V res = null;
            //直接删除head
            if (head != null) {
                res = head.val;
                head = head.next;
                size--;
            }
            return res;
        }

        public V peek() {
            V res = null;
            //直接返回head
            if (head != null) {
                res = head.val;
            }
            return res;
        }
    }
}
