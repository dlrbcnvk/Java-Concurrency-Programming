package chapter11;

import java.util.concurrent.*;

public class ThreadPoolExecutorExample {
    public static void main(String[] args) {

        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 0L; // 대기하고 있는 스레드는 얼마동안 대기하다가 없앨 것인가.
//        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(4);
        int taskNum = 9;
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

        for (int i = 0; i < taskNum; i++) {
            final int taskId = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(500);
                    System.out.println("[" + Thread.currentThread().getName() + "] 가 태스크 " + taskId + "를 실행하고 있습니다.");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }

        executor.shutdown();
    }
}
