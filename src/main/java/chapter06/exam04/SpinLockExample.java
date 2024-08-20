package chapter06.exam04;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpinLockExample {

    private AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        while (!lock.compareAndSet(false, true)) ;
    }

    public void unlock() {
        lock.set(false);
    }

    public static void main(String[] args) {
        SpinLockExample spinLock = new SpinLockExample();

        Runnable task = () -> {
            spinLock.lock();
            System.out.println(Thread.currentThread().getName() + " 가 락을 획득했습니다.");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println(Thread.currentThread().getName() + " 가 락을 해제합니다.");
                spinLock.unlock();
                System.out.println(Thread.currentThread().getName() + " 가 락을 해제했습니다.");
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();
    }
}
