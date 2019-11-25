package com.xinyou.dome.List;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/11/20 20:56
 * @Description:
 */
public class ListTest {
    public static void main(String[] args) {
        ArrayList list = new ArrayList<>();
        for (int i =0 ;i<20;i++){
            list.add("1");
            System.out.println(list.toArray().length);
        }


    }
}
