package com.xinyou.dome.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/6/10 14:24
 * @Description:
 */
public abstract class BaseHealthChecker implements Runnable {
    private CountDownLatch countDownLatch;
    private String serviceName;
    private boolean serviceUp;

    public BaseHealthChecker(String serviceName, CountDownLatch countDownLatch) {
        super();
        this.countDownLatch = countDownLatch;
        this.serviceName = serviceName;
    }

    @Override
    public void run() {
        try {
            verifyService();
            serviceUp = true;
        } catch (Exception e) {
            e.printStackTrace();
            serviceUp = false;
        } finally {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }

    public String getServiceName() {
        return serviceName;
    }

    public boolean isServiceUp() {
        return serviceUp;
    }

    public abstract void verifyService();
}
