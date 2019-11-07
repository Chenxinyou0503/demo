package com.xinyou.dome.thread.countdownlatch;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/6/10 14:20
 * @Description:
 */
public class CountDownLatchDome {
    public static void main(String[] args) {
        boolean result = false;
        try {
            result = AplicationStart.checkService();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("External services validation completed !! Result was :: "+ result);
    }
}
