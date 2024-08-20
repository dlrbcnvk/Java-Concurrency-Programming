package chapter08.exam02;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 모니터링하는 용도로 사용하는 것을 권장
 * 실제 기능구현하는 용도로 사용한다면 위험하다.
 */
public class LockAPIExample {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
           lock.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        });

        Thread thread2 = new Thread(() -> {
            lock.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        });

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {

        }

        // Lock에 대한 정보 출력
        System.out.println("Hold Count: " + lock.getHoldCount());
        System.out.println("Is Held By Current Thread: " + lock.isHeldByCurrentThread());
        System.out.println("Has Queued Threads: " + lock.hasQueuedThreads());
        System.out.println("Has Queued Thread1: " + lock.hasQueuedThread(thread1));
        System.out.println("Has Queued Thread2: " + lock.hasQueuedThread(thread2));
        System.out.println("Queue Length:" + lock.getQueueLength());
        System.out.println("isFair:" + lock.isFair());
    }
}
