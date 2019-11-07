package com.xinyou.dome.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/6/10 14:30
 * @Description:
 */
public class NetworkHealthChecker extends BaseHealthChecker {

    public NetworkHealthChecker(CountDownLatch countDownLatch) {
        super("Network service", countDownLatch);
    }

    @Override
    public void verifyService() {
        System.out.println("checking " + this.getServiceName());
        try {
            System.out.println("sleep 9s  " + this.getServiceName());
            Thread.sleep(9000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + "is up!");
    }
}
