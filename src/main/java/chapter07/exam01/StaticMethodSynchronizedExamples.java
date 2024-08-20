package chapter07.exam01;

public class StaticMethodSynchronizedExamples {

    private static int count = 0;

    public static synchronized void increment() {
        count++;
        System.out.println(Thread.currentThread().getName() + " 가 증가시켰습니다. 현재값=" + count);
    }

    public static synchronized void decrement() {
        count--;
        System.out.println(Thread.currentThread().getName() + " 가 감소시켰습니다. 현재값=" + count);
    }

    public static int getCount() {
        return count;
    }

    public static void main(String[] args) {
        StaticMethodSynchronizedExamples counter = new StaticMethodSynchronizedExamples();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                StaticMethodSynchronizedExamples.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                StaticMethodSynchronizedExamples.decrement();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("최종값=" + StaticMethodSynchronizedExamples.getCount());
    }
}
