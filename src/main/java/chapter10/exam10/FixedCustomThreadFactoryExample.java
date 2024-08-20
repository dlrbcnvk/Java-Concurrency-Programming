package chapter10.exam10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class FixedCustomThreadFactoryExample {

    static class CustomThreadFactory implements ThreadFactory {

        private final String name;
        private int threadCount = 0;

        public CustomThreadFactory(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            threadCount++;
            String threadName = name + "-" + threadCount;
            return new Thread(r, threadName);
        }
    }

    public static void main(String[] args) {

        CustomThreadFactory customThreadFactory = new CustomThreadFactory("Custom");
        ExecutorService executorService = Executors.newFixedThreadPool(3, customThreadFactory);

        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("[" + Thread.currentThread().getName() + "]");
            });
        }

        executorService.shutdown();
    }
}
