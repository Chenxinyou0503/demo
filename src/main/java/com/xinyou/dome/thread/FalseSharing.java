package com.xinyou.dome.thread;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/1/19 9:59
 * @Description:
 */
public final class FalseSharing implements Runnable {
    public final static int NUM_THREADS = 4;
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;
    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];

    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    public FalseSharing(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public final static class VolatileLong {
        public volatile long value = 0L;
        public long p1, p2, p3, p4, p5, p6;
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));

        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }

    public static void main(String[] args) throws Exception {
        final long start = System.nanoTime();
        runTest();
        System.out.println("duration = " + (System.nanoTime() - start));
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
    }
}