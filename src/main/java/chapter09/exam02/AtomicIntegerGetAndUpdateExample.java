package chapter09.exam02;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerGetAndUpdateExample {

    private static AtomicInteger accountBalance = new AtomicInteger(1000);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                int withdrawalAmount = 500;
                int updateBalance = accountBalance.getAndUpdate(balance -> {
                    if (balance >= withdrawalAmount) {
                        return balance - withdrawalAmount;
                    }  else {
                        return balance;
                    }
                });


            }).start();
        }
    }
}
