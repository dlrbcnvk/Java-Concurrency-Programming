package chapter05.exam02;

public class CpuNonSyncExample {

    private static int count = 0;
    private static final int ITERATIONS = 100000;

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                count++;
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                count++;
            }
        });

        long start = System.currentTimeMillis();
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("예상결과=" + (2 * ITERATIONS));
        System.out.println("실제결과=" + count);
        System.out.println("싱글스레드 처리 시간=" + (System.currentTimeMillis() - start) + "ms");
    }
}
