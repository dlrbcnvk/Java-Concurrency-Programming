package chapter08.exam06;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean flag = false;

    public void awaiting() {
        lock.lock();
        try {
            while (!flag) {
                System.out.println("조건이 만족하지 못해 대기한다.");
                condition.await();
            }
            System.out.println("드디어 빠져나왔다.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void signaling() {
        lock.lock();
        try {
            flag = true;
            System.out.println("조건을 만족시키고 깨움");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionExample conditionExample = new ConditionExample();

        Thread thread1 = new Thread(() -> {
            try {
                conditionExample.awaiting();
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            conditionExample.signaling();
        });

        thread1.start();
        Thread.sleep(2000);
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
