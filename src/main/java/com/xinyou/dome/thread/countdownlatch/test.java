package com.xinyou.dome.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/6/10 14:57
 * @Description:
 */
public class test {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(15);
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new FirstBatchWorker(latch, i));
            t.start();
        }
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new SecondBatchWorker(latch, i));
            t.start();
        }
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new ThreeBatchWorker(latch, i));
            t.start();
        }
        // 注意：这里是演示目的的逻辑，并不是推荐的协调方式
        latch.await();
        System.out.println("Wait for first batch finish");
        latch.countDown();
    }

    static class FirstBatchWorker implements Runnable {
        private CountDownLatch latch;
        private int anInt;

        public FirstBatchWorker(CountDownLatch latch, int anInt) {
            this.latch = latch;
            this.anInt = anInt;
        }

        @Override
        public void run() {
            System.out.println("First batch executed! anInt:" + anInt + ",latch:" + latch.getCount());
            latch.countDown();
        }
    }

    static class SecondBatchWorker implements Runnable {
        private CountDownLatch latch;
        private int anInt;

        public SecondBatchWorker(CountDownLatch latch, int anInt) {
            this.latch = latch;
            this.anInt = anInt;
        }

        @Override
        public void run() {
            try {
                latch.countDown();
                System.out.println("Second batch executed! anint :" + anInt + ",latch:" + latch.getCount());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreeBatchWorker implements Runnable {
        private CountDownLatch latch;
        private int anInt;

        public ThreeBatchWorker(CountDownLatch latch, int anInt) {
            this.latch = latch;
            this.anInt = anInt;
        }

        @Override
        public void run() {
            System.out.println("Three batch executed! anInt:" + anInt + ",latch:" + latch.getCount());
            latch.countDown();
        }
    }


}
