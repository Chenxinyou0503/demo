package com.xinyou.dome.thread;

import java.util.concurrent.*;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2018/12/29 15:13
 * @Description:
 */
public class ThreadService {
    private static int corePoolSize = 2;
    private static int maxMumPoolSize = 4;
    private static long keepAliveTime = 60L;
    private static BlockingQueue<Runnable> workeQueue = new ArrayBlockingQueue<>(2);


    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxMumPoolSize, keepAliveTime,
                TimeUnit.SECONDS, workeQueue, new MyRejectedExecutionHandler());
        threadPoolExecutor.prestartAllCoreThreads();
        for (int i = 1; 1 <= 10; i++) {
            Tast tast = new Tast("test" + i);
            threadPoolExecutor.execute(tast);
        }
    }

    /**
     * 自定义阻塞队列
     * 当线程池满的时候，ThreadPoolExecutor 会调用RejectedExecutionHandler 来处理线程
     */
    public static class MyRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                executor.getQueue().put(r);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class Tast implements Runnable {
        private String name;

        public Tast(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.toString() + " is running ! ");
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Tast{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
