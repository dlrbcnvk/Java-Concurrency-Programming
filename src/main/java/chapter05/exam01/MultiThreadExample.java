package chapter05.exam01;

public class MultiThreadExample {

    private static int sum = 0;

    private static final Object lock = new Object();

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            for (int i = 1; i < 500; i++) {
                synchronized (lock) {
                    sum += i;
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 501; i < 1000; i++) {
                synchronized (lock) {
                    sum += i;
                }
                try {
                    Thread.sleep(1);
                    // 하나의 스레드에서 예외가 터져도 전체 어플리케이션이 종료되는 일이 없도록 막을 수 있다.
//                    throw new RuntimeException("error");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("합계=" + sum);
        System.out.println("멀티스레드 처리 시간=" + (System.currentTimeMillis() - start) + "ms");
    }
}
