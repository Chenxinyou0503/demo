package com.xinyou.dome.recursive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/5/6 11:14
 * @Description:
 */
public class RecursiveTest {
    public Logger logger = LoggerFactory.getLogger(RecursiveTest.class);

    public int recursive(int i) {
        if (i < 1000) {
            i++;
            logger.info("增加：" + i);
            return recursive(i);
        } else {
            i--;
            logger.info("较少：" + i);
            return i;
        }
    }

    public static void main(String[] args) {
        RecursiveTest recursiveTest = new RecursiveTest();
        System.out.println(recursiveTest.recursive(1));
    }
}
