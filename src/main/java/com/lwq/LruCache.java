package com.lwq;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache<K, V> {

    /**
     * 最大缓存大小
     */
    private int cacheSize;

    private LinkedHashMap<K, V> cacheMap;

    public LruCache(int cacheSize) {
        this.cacheSize = cacheSize;

        cacheMap = new LinkedHashMap(16, 0.75F, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                if (cacheSize + 1 == cacheMap.size()) {
                    return true;
                }
                return false;
            }
        };
    }

    public void put(K key, V value) {
        cacheMap.put(key, value);
    }

    public V get(K key) {
        return cacheMap.get(key);
    }

    public Collection<Map.Entry<K, V>> getAll() {
        return new ArrayList<Map.Entry<K, V>>(cacheMap.entrySet());
    }

    public static void main(String[] args) {
        LruCache<String, Integer> map = new LruCache<>(3);
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);

        for (Map.Entry<String, Integer> e : map.getAll()) {
            System.out.println(e.getKey() + "====>" + e.getValue());
        }
        System.out.println("\n");
        map.put("key4", 4);
        for (Map.Entry<String, Integer> e : map.getAll()) {
            System.out.println(e.getKey() + "====>" + e.getValue());
        }

    }

}
