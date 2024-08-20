package chapter08.exam02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockExample {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            boolean acquired = false;
            while (!acquired) {
                acquired = lock.tryLock();
                if (acquired) {
                    System.out.println("스레드 1이 락을 획득했습니다.");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        lock.unlock();
                        System.out.println("스레드 1이 락을 해제했습니다.");
                    }
                } else {
                    System.out.println("스레드 1이 락을 획득하지 못했습니다.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            boolean acquired = false;
            while (!acquired) {
                acquired = lock.tryLock();
                if (acquired) {
                    try {
                        System.out.println("스레드 2가 락을 획득했습니다.");
                    } finally {
                        lock.unlock();
                        System.out.println("스레드 2가 락을 해제했습니다.");
                    }
                } else {
                    System.out.println("스레드 2가 락을 획득하지 못했습니다.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
