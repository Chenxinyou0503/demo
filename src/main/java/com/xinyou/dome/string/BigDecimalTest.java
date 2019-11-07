package com.xinyou.dome.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/7/17 15:28
 * @Description:
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "老虎机");
        map.put(2, "卤煮");
        map.put(3, "刀削面");
//        map.put(4, "沙县");
//        map.put(5, "庆丰");
//        map.put(6, "食堂鸡蛋盖饭");
//        map.put(7, "食堂拌面");
//        map.put(8, "食堂麻辣烫");
        int num = 0;
        for (int i = 0; i < 10000; i++) {
            Random random = new Random();
            int r = random.nextInt(8);
            num = num + r + 1;
        }
        int result = num % map.size()+1;
        System.out.println(result);
        System.out.println(map.get(result));
    }
}
