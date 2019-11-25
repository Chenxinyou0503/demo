package com.xinyou.dome.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/11/11 15:08
 * @Description:
 */
public class ScheduledExecutorServiceTest {

    private static ScheduledExecutorService monitorExecutor = null;

    /**
     * 心跳启动延时，默认值
     */
    private static Long initialDelay = 1L;

    /**
     * 心跳周期，10S
     */
    private static Long delay = 1L;

    public static void main(String[] args) {
        try {
            monitorExecutor = Executors.newScheduledThreadPool(1);
            monitorExecutor.scheduleWithFixedDelay(() -> {
                System.out.println("这是测试");
            }, initialDelay, delay, TimeUnit.SECONDS);

            System.out.println("111");
            Thread.sleep(10000);
            System.out.println("休息结束，停止线程");
            monitorExecutor.shutdown();
            System.out.println("线程是否被关闭：" + monitorExecutor.isShutdown());

            monitorExecutor = Executors.newScheduledThreadPool(1);
            monitorExecutor.scheduleWithFixedDelay(() -> {
                System.out.println("这是测试222");
            }, initialDelay, delay, TimeUnit.SECONDS);

            while (1 == 1) {
                if (!monitorExecutor.isShutdown()) {
                    System.out.println("线程是否被关闭：" + monitorExecutor.isShutdown());
                }
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
