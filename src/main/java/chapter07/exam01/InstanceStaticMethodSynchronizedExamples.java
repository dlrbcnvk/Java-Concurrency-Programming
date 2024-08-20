package chapter07.exam01;

public class InstanceStaticMethodSynchronizedExamples {

    private int instanceCount = 0;
    private static int staticCount = 0;

    public synchronized void incrementInstanceCount() {
        instanceCount++;
        System.out.println(Thread.currentThread().getName() + " 가 증가시켰습니다. 현재값=" + instanceCount);
    }

    public static synchronized void incrementStaticCount() {
        staticCount++;
        System.out.println(Thread.currentThread().getName() + " 가 증가시켰습니다. 현재값=" + staticCount);
    }


    public static void main(String[] args) {
        InstanceStaticMethodSynchronizedExamples example = new InstanceStaticMethodSynchronizedExamples();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                example.incrementInstanceCount();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                example.incrementInstanceCount();
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                InstanceStaticMethodSynchronizedExamples.incrementStaticCount();
            }
        });

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                InstanceStaticMethodSynchronizedExamples.incrementStaticCount();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("최종값 instance=" + example.instanceCount);
        System.out.println("최종값 static=" + staticCount);
    }
}
