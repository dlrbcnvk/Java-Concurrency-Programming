package chapter08.exam01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    private int count = 0;
    private Lock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockExample lockExample = new LockExample();
        long start = System.currentTimeMillis();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000000; i++) {
                lockExample.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000000; i++) {
                lockExample.increment();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Count=" + lockExample.count);
        System.out.println("Duration=" + (System.currentTimeMillis() - start) + "ms");
    }
}
