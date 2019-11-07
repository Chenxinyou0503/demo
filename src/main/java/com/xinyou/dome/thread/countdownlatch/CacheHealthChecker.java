package com.xinyou.dome.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/6/10 14:36
 * @Description:
 */
public class CacheHealthChecker extends BaseHealthChecker {
    public CacheHealthChecker(CountDownLatch countDownLatch) {
        super("cache serivce", countDownLatch);
    }

    @Override
    public void verifyService() {
        System.out.println("checking " + this.getServiceName());
        try {
            System.out.println("sleep 5s  " + this.getServiceName());
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + "is up!");
    }
}
