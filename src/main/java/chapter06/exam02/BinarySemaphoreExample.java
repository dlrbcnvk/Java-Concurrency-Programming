package chapter06.exam02;

public class BinarySemaphoreExample {
    public static void main(String[] args) throws InterruptedException {

        SharedResource sharedResource = new SharedResource(new BinarySemaphore());

        Thread thread1 = new Thread(() -> {
            sharedResource.sum();
        });

        Thread thread2 = new Thread(() -> {
            sharedResource.sum();
        });

        long start = System.currentTimeMillis();

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("최종합계=" + sharedResource.getSharedValue());
        System.out.println("소요시간=" + (System.currentTimeMillis() - start) + "ms");
    }
}

class SharedResource {

    private int sharedValue = 0;
    private CommonSemaphore semaphore;

    public SharedResource(CommonSemaphore semaphore) {
        this.semaphore = semaphore;
    }

    public synchronized void sum() {
        try {
            semaphore.acquired();
            // critical section
            for (int i = 0; i < 10000000; i++) {
                sharedValue++;
            }
        } finally {
            // release (finally 에서 수행해야 함. critical section 과정 중에서 오류가 발생할 수 있기 때문이다.)
            semaphore.release();
        }
    }

    public int getSharedValue() {
        return sharedValue;
    }
}