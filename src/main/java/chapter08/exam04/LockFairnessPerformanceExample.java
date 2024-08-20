package chapter08.exam04;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockFairnessPerformanceExample {
    private static final int THREAD_COUNT = 20;
    private static final int ITERATIONS = 100_000;
    private static final Lock fairLock = new ReentrantLock(true);
    private static final Lock unfairLock = new ReentrantLock();

    public static void main(String[] args) {
        runTest("공정한 락", fairLock);
        runTest("불공정한 락", unfairLock);
    }

    private static void runTest(String lockType, Lock lock) {
        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < ITERATIONS; j++) {
                    lock.lock();
                    try {
                        // 자원에 대한 작업 수행
                    } finally {
                        lock.unlock();
                    }
                }
            });
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("[" + lockType + "] Duration=" + (endTime - startTime) + "ms");
    }
}
