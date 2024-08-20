package chapter06.exam03;

public class MutualExclusionExample {

    private int counter = 0;

    public synchronized void increment() {
        counter++;
        System.out.println("스레드=" + Thread.currentThread().getName() + ", counter=" + counter);
    }

    public static void main(String[] args) {
        MutualExclusionExample example = new MutualExclusionExample();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 500000; i++) {
                example.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 500000; i++) {
                example.increment();
            }
        });

        thread1.start();
        thread2.start();
    }
}
