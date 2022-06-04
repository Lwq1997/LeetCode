package com.lwq.codecatalog.list;

/**
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
 * <p>
 * 在链表类中实现这些功能：
 * <p>
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 *  
 * <p>
 * 示例：
 * <p>
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
 * linkedList.get(1);            //返回2
 * linkedList.deleteAtIndex(1);  //现在链表是1-> 3
 * linkedList.get(1);            //返回3
 *  
 * <p>
 * 提示：
 * <p>
 * 所有val值都在 [1, 1000] 之内。
 * 操作次数将在  [1, 1000] 之内。
 * 请不要使用内置的 LinkedList 库。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/design-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode707 {

    public void main(String[] args) {
        MyLinkedList01 linkedList = new MyLinkedList01();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1, 2);   //链表变为1-> 2-> 3
        linkedList.get(1);            //返回2
        linkedList.deleteAtIndex(1);  //现在链表是1-> 3
        linkedList.get(1);            //返回3

        MyLinkedList02 linkedList02 = new MyLinkedList02();
        linkedList02.addAtHead(1);
        linkedList02.addAtTail(3);
        linkedList02.addAtIndex(1, 2);   //链表变为1-> 2-> 3
        linkedList02.get(1);            //返回2
        linkedList02.deleteAtIndex(1);  //现在链表是1-> 3
        linkedList02.get(1);            //返回3
    }

    //单链表实现
    static class MyLinkedList01 {
        int size; // 存储链表元素个数
        ListNode head; // 虚拟头节点

        public MyLinkedList01() {
            size = 0;
            head = new ListNode(0);
        }

        /**
         * 返回index处的节点值
         *
         * @param index
         * @return
         */
        public int get(int index) {
            if (index < 0 || index >= size) {
                return -1;
            }
            ListNode cur = head;
            for (int i = 0; i <= index; i++) {
                cur = cur.next;
            }
            return cur.val;
        }

        /**
         * 在头部添加节点
         *
         * @param val
         */
        public void addAtHead(int val) {
            addAtIndex(0, val);
        }

        /**
         * 在尾部添加节点
         *
         * @param val
         */
        public void addAtTail(int val) {
            addAtIndex(size, val);
        }


        // 在第 index 个节点之前插入一个新节点，例如index为0，那么新插入的节点为链表的新头节点。
        // 如果 index 等于链表的长度，则说明是新插入的节点为链表的尾结点
        // 如果 index 大于链表的长度，则返回空
        public void addAtIndex(int index, int val) {
            if (index > size) {
                return;
            }
            if (index <= 0) {
                index = 0;
            }
            size++;
            //找到要插入节点的前驱节点
            ListNode cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            ListNode newNode = new ListNode(val);
            ListNode next = cur.next;
            cur.next = newNode;
            newNode.next = next;
        }

        /**
         * 删除第index个节点
         *
         * @param index
         */
        public void deleteAtIndex(int index) {
            if (index >= size || index < 0) {
                return;
            }
            size--;
            //找到要删除节点的前驱节点
            ListNode cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
    }


    /**
     * 双链表
     */
    class MyLinkedList02 {
        class ListNode {
            int val;
            ListNode next, prev;

            ListNode(int x) {
                val = x;
            }
        }

        int size;
        ListNode head, tail;     //虚拟头尾节点

        public MyLinkedList02() {
            size = 0;
            head = new ListNode(0);
            tail = new ListNode(0);
            head.next = tail;
            tail.prev = head;
        }

        public int get(int index) {
            if (index < 0 || index >= size) {
                return -1;
            }
            ListNode cur = head;
            // 通过判断 index < (size - 1) / 2 来决定是从头结点还是尾节点遍历，提高效率
            if (index < (size - 1) / 2) {
                for (int i = 0; i <= index; i++) {
                    cur = cur.next;
                }
            } else {
                cur = tail;
                for (int i = 0; i <= size - index - 1; i++) {
                    cur = cur.prev;
                }
            }
            return cur.val;
        }

        public void addAtHead(int val) {
            ListNode cur = head;
            ListNode newNode = new ListNode(val);
            ListNode next = cur.next;
            //处理next和newNode之间的关系
            newNode.next = next;
            next.prev = newNode;
            //处理newNode和cur之间的关系
            cur.next = newNode;
            newNode.prev = cur;
            size++;
        }

        public void addAtTail(int val) {
            ListNode cur = tail;
            ListNode newNode = new ListNode(val);
            ListNode prev = cur.prev;
            newNode.next = tail;
            newNode.prev = prev;
            prev.next = newNode;
            tail.prev = newNode;
            size++;
        }

        public void addAtIndex(int index, int val) {
            if (index > size) {
                return;
            }
            if (index < 0) {
                index = 0;
            }
            ListNode cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            ListNode newNode = new ListNode(val);
            ListNode next = cur.next;
            newNode.next = next;
            next.prev = newNode;
            newNode.prev = cur;
            cur.next = newNode;
            size++;
        }

        public void deleteAtIndex(int index) {
            if (index >= size || index < 0) {
                return;
            }
            ListNode cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            cur.next.next.prev = cur;
            cur.next = cur.next.next;
            size--;
        }
    }

}
