package com.xinyou.dome.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/6/10 14:33
 * @Description:
 */
public class DataHealthChecker extends BaseHealthChecker {
    public DataHealthChecker(CountDownLatch countDownLatch) {
        super("Data service", countDownLatch);
    }

    @Override
    public void verifyService() {
        System.out.println("checking " + this.getServiceName());
        try {
            System.out.println("sleep 7s  " + this.getServiceName());
            Thread.sleep(7000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + "is up!");
    }

}
