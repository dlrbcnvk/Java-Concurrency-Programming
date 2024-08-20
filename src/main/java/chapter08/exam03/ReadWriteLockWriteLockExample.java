package chapter08.exam03;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockWriteLockExample {

    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        BankAccount account = new BankAccount(lock);

        // 읽기 스레드 - 잔액 조회
        new Thread(() -> {
            int balance = account.getBalance();
            System.out.println("[" + Thread.currentThread().getName() + "] 현재 잔액: " + balance);
        }).start();

        // 쓰기 스레드 - 잔액 조회
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int depositAmount = (int) (Math.random() * 1000);
                account.deposit(depositAmount);
                System.out.println("[" + Thread.currentThread().getName() + "] 입금액: " + depositAmount);
            }).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int withdrawAmount = (int) (Math.random() * 1000);
                account.withdraw(withdrawAmount);
            }).start();
        }

        // 읽기 스레드 - 잔액 조회
        new Thread(() -> {
            int balance = account.getBalance();
            System.out.println("[" + Thread.currentThread().getName() + "] 현재 잔액: " + balance);
        }).start();
    }
}
