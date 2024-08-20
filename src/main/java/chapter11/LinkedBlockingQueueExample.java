package chapter11;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueExample {
    public static void main(String[] args) {

        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);

        // 생산자 스레드
        Thread producer = new Thread(() -> {
           try {
               for (int i = 1; i <= 10; i++) {
                   System.out.println("Producing: " + i);
                   queue.put(i); // 데이터를 큐에 넣음 (큐가 가득 차면 블록됨)
                   Thread.sleep(1000); // 생산자 스레드는 1초마다 데이터를 생산
               }
           } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
           }
        });

        // 소비자 스레드
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    int data = queue.take();
                    System.out.println("Consuming: " + data);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
