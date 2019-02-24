package com.zhgd.controller.controllerUtile;

import java.util.Map;
import java.util.TreeMap;

//排序类
public class MapSort {
    /**
     * 使用 Map按key进行排序
     * @param map
     * @return
     */
    public static Map<String, Integer> sortMapByKey(Map<String, Integer> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, Integer> sortMap = new TreeMap<String, Integer>(
                new MapKeyComparator());

        sortMap.putAll(map);

        return sortMap;
    }
}
