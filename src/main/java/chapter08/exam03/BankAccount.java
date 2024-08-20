package chapter08.exam03;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

public class BankAccount {
    private final ReadWriteLock lock;
    private Map<String, Integer> balance = new HashMap<>();

    public BankAccount(ReadWriteLock lock) {
        this.lock = lock;
        balance.put("account1", 0);
    }

    // read lock
    public int getBalance() {
        lock.readLock().lock();
        try {
            Thread.sleep(1000);
            return balance.get("account1");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.readLock().unlock();
        }
    }

    // write lock
    public void deposit(int amount) {
        lock.writeLock().lock();
        try {
            Thread.sleep(1000);
            Integer currentBalance = balance.get("account1");
            currentBalance += amount;
            balance.put("account1", currentBalance);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void withdraw(int amount) {
        lock.writeLock().lock();
        try {
            Integer currentBalance = balance.get("account1");
            if (currentBalance >= amount) {
                currentBalance -= amount;
                balance.put("account1", currentBalance);
                System.out.println("[" + Thread.currentThread().getName() + "] 출금 성공. 출금액:" + amount);
            } else {
                System.out.println("[" + Thread.currentThread().getName() + "] 출금 실패. 잔액 부족");
            }
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
