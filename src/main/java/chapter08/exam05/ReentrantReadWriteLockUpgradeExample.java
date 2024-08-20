package chapter08.exam05;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockUpgradeExample {

    private static final ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        System.out.println("읽기 잠금 획득 시도...");
        lock.readLock().lock();
        System.out.println("읽기 잠금 획득");

        try {
            System.out.println("쓰기 잠금 획득 시도...");
            lock.writeLock().lock(); // 데드락 발생
            System.out.println();
            System.out.println("이 메시지는 출력되지 않습니다.");
        } finally {
            lock.readLock().unlock();
        }
    }
}
