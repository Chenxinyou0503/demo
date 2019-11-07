package com.xinyou.dome.thread.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/6/10 14:37
 * @Description:
 */
public class AplicationStart {
    private static List<BaseHealthChecker> list;
    private static CountDownLatch countDownLatch;

    private AplicationStart() {

    }

    private final static AplicationStart INSTANCE = new AplicationStart();

    public static AplicationStart getInstance() {
        return INSTANCE;
    }

    public static boolean checkService() throws Exception {
        countDownLatch = new CountDownLatch(3);
        list = new ArrayList<>();
        list.add(new NetworkHealthChecker(countDownLatch));
        list.add(new CacheHealthChecker(countDownLatch));
        list.add(new DataHealthChecker(countDownLatch));
        ScheduledExecutorService monitorExecutor = Executors.newScheduledThreadPool(1);
        for (BaseHealthChecker v : list) {
            monitorExecutor.execute(v);
        }
        countDownLatch.await();
        for (BaseHealthChecker v : list) {
            if (!v.isServiceUp()) {
                return false;
            }
        }
        return true;
    }
}
