package com.lwq;


import java.util.HashMap;
import java.util.Map;

/**
 * 设计和构建一个“最近最少使用”缓存，该缓存会删除最近最少使用的项目。缓存应该从键映射到值(允许你插入和检索特定键对应的值)，并在初始化时指定最大容量。当缓存被填满时，它应该删除最近最少使用的项目。
 *
 * 它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 *
 * 示例:
 *
 * LRUCache cache = new LRUCache( 2  缓存容量  );
 *
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // 返回  1
 * cache.put(3, 3);    // 该操作会使得密钥 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4, 4);    // 该操作会使得密钥 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 */
public class LruCache02 {

    // 定义一个双向链表
    private class ListNode {
        int key;
        int value;
        ListNode pre;
        ListNode next;

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
            pre = null;
            next = null;
        }
    }

    // 定义一个int，用于代表容量
    private int capacity;

    // 定义一个map，用于查找是否包含有这个key
    private Map<Integer, ListNode> map;

    //定义两个哨兵节点head和tail
    private ListNode head;
    private ListNode tail;

    public LruCache02(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        // 如果map中没有就直接返回
        if (!map.containsKey(key)) {
            return -1;
        }
        ListNode node = map.get(key);

        // 在原来的位置上删除这个节点
        node.pre.next = node.next;
        node.next.pre = node.pre;

        // 把这个节点添加到末尾
        moveToTail(node);

        return node.value;
    }

    private void moveToTail(ListNode node) {
        ListNode temp = tail.pre;
        node.pre = temp;
        node.next = tail;
        temp.next = node;
        tail.pre = node;
    }

    public void put(int key, int value) {
        //直接调用这边的get方法，如果存在，它会在get内部被移动到尾巴，不用再移动一遍,直接修改值即可
        if (get(key) != -1) {
            map.get(key).value = value;
            return;
        }
        // 如果不存在，则新建一个
        ListNode node = new ListNode(key, value);
        map.put(key, node);
        moveToTail(node);

        // 判断是否超容量
        if (map.size() > capacity) {
            // 这里面的key是链表的key，也是map中的key
            map.remove(head.next.key);
            head.next = head.next.next;
            head.next.pre = head;
        }
    }


}
