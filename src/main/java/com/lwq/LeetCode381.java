package com.lwq;

import java.util.*;

/**
 * 381. O(1) 时间插入、删除和获取随机元素 - 允许重复
 * 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
 * <p>
 * 注意: 允许出现重复元素。
 * <p>
 * insert(val)：向集合中插入元素 val。
 * remove(val)：当 val 存在时，从集合中移除一个 val。
 * getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
 * 示例:
 * <p>
 * // 初始化一个空的集合。
 * RandomizedCollection collection = new RandomizedCollection();
 * <p>
 * // 向集合中插入 1 。返回 true 表示集合不包含 1 。
 * collection.insert(1);
 * <p>
 * // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
 * collection.insert(1);
 * <p>
 * // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
 * collection.insert(2);
 * <p>
 * // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
 * collection.getRandom();
 * <p>
 * // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
 * collection.remove(1);
 * <p>
 * // getRandom 应有相同概率返回 1 和 2 。
 * collection.getRandom();
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1-duplicates-allowed
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode381 {
    /**
     * Initialize your data structure here.
     */
    // 最大索引
    int n ;//当前集合大小
    // key是值，value是这个值对应的set索引
    HashMap<Integer,Set<Integer>>map;
    // 存储值《list只能做到添加o1，不能查找o1，list也可以删除尾元素o1》
    ArrayList<Integer>list;
    // 随机数i
    Random random;
    /** Initialize your data structure here. */
    public void RandomizedCollection() {
        this.random = new Random();
        this.map = new HashMap();
        this.n = 0;
        this.list = new ArrayList<>();
    }
    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        Set<Integer> set = map.get(val);
        if (set == null) {
            set = new HashSet<Integer>();
        }
        // 添加索引
        set.add(n);
        // 添加值
        list.add(val);
        map.put(val, set);
        // 最大索引+1
        n++;
        // 需要看看是否之前有同样的值，但是总体还是插入成功了
        return set.size() == 1;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
        if (map.containsKey(val)) {
            // 拿到最后一个索引
            int lastIndex = n - 1;
            Set<Integer> curSet = map.get(val);
            Set<Integer> lastValSet = map.get(list.get(lastIndex));
            // 拿到当前值在list中的索引
            int curIndex = curSet.iterator().next();
            // 在list中替换当前索引和最后一个索引位置的值
            swap(list,curIndex,lastIndex);
            //将其在列表中删除
            list.remove(n-1);
            //curset中也删除
            curSet.remove(curIndex);
            if(curSet.size()==0)  {
                //在map中删除
                map.remove(val);
            }
            //修改最后一个值的索引，因为list中替换了顺序
            lastValSet.remove(n-1);
            lastValSet.add(curIndex);
            n--;
        } else {
            return false;
        }
        return true;
    }

    private void swap(List<Integer> list, int curIndex, int lastIndex) {
        Integer temp = list.get(lastIndex);
        list.set(lastIndex,list.get(curIndex));
        list.set(curIndex,temp);
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        return list.get(random.nextInt(n));
    }
}
