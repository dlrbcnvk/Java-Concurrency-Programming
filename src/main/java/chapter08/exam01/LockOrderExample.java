package chapter08.exam01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized 와 다르게, 락을 건 순서의 역순으로 해제할 필요 없음.
 */
public class LockOrderExample {

    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
           lock1.lock();
           try {
               System.out.println("스레드가 1번 락을 획득했습니다.");
               lock2.lock();
               try {
                   System.out.println("스레드가 2번 락을 획득했습니다.");
               } finally {
                   lock1.unlock();
                   System.out.println("스레드가 1번 락을 해제했습니다.");
               }
           } finally {
               lock2.unlock();
               System.out.println("스레드가 2번 락을 해제했습니다.");
           }
        }).start();
    }
}
