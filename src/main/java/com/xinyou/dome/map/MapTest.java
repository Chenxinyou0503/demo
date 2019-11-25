package com.xinyou.dome.map;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/11/21 21:23
 * @Description:
 */
public class MapTest {
    public static void main(String[] args) {
        List<String> synArrayList = Collections.synchronizedList(new ArrayList<String>());
        Set set = Collections.synchronizedSet(new HashSet<>());
        Map map = Collections.synchronizedMap(new HashMap<>());
        ConcurrentHashMap
    }
}
