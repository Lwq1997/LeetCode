package com.lwq;

import java.util.*;

public class ConsistentHashingWithVirtualNode {
    /**
     * 集群地址列表
     */
    private static String[] groups = {
            "192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
            "192.168.0.3:111", "192.168.0.4:111"
    };

    /**
     * 真实集群列表
     */
    private static List<String> realGroups = new LinkedList<>();

    /**
     * 虚拟节点映射关系《hash值，虚拟节点ip》
     */
    private static SortedMap<Integer, String> virtualNodes = new TreeMap<>();

    private static final int VIRTUAL_NODE_NUM = 1000;

    static {
        // 先添加真实节点列表
        realGroups.addAll(Arrays.asList(groups));

        // 将虚拟节点映射到Hash环上
        for (String realGroup: realGroups) {
            for (int i = 0; i < VIRTUAL_NODE_NUM; i++) {
                // 通过真实节点产生虚拟节点
                String virtualNodeName = getVirtualNodeName(realGroup, i);
                int hash = HashUtil.getHash(virtualNodeName);
                System.out.println("[" + virtualNodeName + "] launched @ " + hash);
                virtualNodes.put(hash, virtualNodeName);
            }
        }
    }

    private static String getVirtualNodeName(String realName, int num) {
        return realName + "&&VN" + String.valueOf(num);
    }

    private static String getRealNodeName(String virtualName) {
        return virtualName.split("&&")[0];
    }

    //请求来了判断落到哪个节点
    private static String getServer(String widgetKey) {
        int hash = HashUtil.getHash(widgetKey);
        // 只取出所有大于该hash值的部分而不必遍历整个Tree
        SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);
        String virtualNodeName;
        if (subMap == null || subMap.isEmpty()) {
            // hash值在最尾部，应该映射到第一个节点上
            virtualNodeName = virtualNodes.get(virtualNodes.firstKey());
        }else {
            virtualNodeName = subMap.get(subMap.firstKey());
        }
        return getRealNodeName(virtualNodeName);
    }

    public static void main(String[] args) {
        // 生成随机数进行测试
        //resMap<节点，请求被打到该节点的个数>
        Map<String, Integer> resMap = new HashMap<>();

        for (int i = 0; i < 100000; i++) {
            Integer widgetId = i;
            String group = getServer(widgetId.toString());
            if (resMap.containsKey(group)) {
                resMap.put(group, resMap.get(group) + 1);
            } else {
                resMap.put(group, 1);
            }
        }

        resMap.forEach(
                (k, v) -> {
                    //结果平均为20%
                    System.out.println("group " + k + ": " + v + "(" + v/100000.0D +"%)");
                }
        );
    }

}

class HashUtil {
    /**
     * 计算Hash值, 使用FNV1_32_HASH算法
     * @param str
     * @return
     */
    public static int getHash(String str) {
        final int p = 16777619;
        int hash = (int)2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash =( hash ^ str.charAt(i) ) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }
}