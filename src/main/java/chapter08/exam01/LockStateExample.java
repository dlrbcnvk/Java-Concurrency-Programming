package chapter08.exam01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockStateExample {

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("스레드가 락을 1번 획득했습니다.");
                lock.lock();
                try {
                    System.out.println("스레드가 락을 2번 획득했습니다.");
                    lock.lock();
                    try {
                        System.out.println("스레드가 락을 3번 획득했습니다.");
                    } finally {
                        lock.unlock();
                        System.out.println("스레드가 락을 1번 해제했습니다.");
                    }
                } finally {
                    lock.unlock();
                    System.out.println("스레드가 락을 2번 해제했습니다.");
                }
            } finally {
                lock.unlock();
                System.out.println("스레드가 락을 3번 해제했습니다.");
            }
        });

        Thread thread2 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("스레드 2가 락을 획득했습니다.");
            } finally {
                lock.unlock();
                System.out.println("스레드 2가 락을 해제했습니다.");
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
