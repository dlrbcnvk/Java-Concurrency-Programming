package chapter06.exam02;

public class CountingSemaphoreExample {
    public static void main(String[] args) throws InterruptedException {
        int permits = 10;
        CountingSemaphore semaphore = new CountingSemaphore(permits);
        SharedResource resource = new SharedResource(semaphore);

        int threadCount = 15;

        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                resource.sum();
            });
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        System.out.println("최종 값: " + resource.getSharedValue());
    }
}
