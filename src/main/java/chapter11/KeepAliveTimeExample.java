package chapter11;

import java.util.concurrent.*;

public class KeepAliveTimeExample {
    public static void main(String[] args) throws InterruptedException {

        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 1L; // 대기하고 있는 스레드는 얼마동안 대기하다가 없앨 것인가.
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(2);
        int taskNum = 6;
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

        for (int i = 0; i < taskNum; i++) {
            final int taskId = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(2000);
                    System.out.println("[" + Thread.currentThread().getName() + "] 가 태스크 " + taskId + "를 실행하고 있습니다.");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        // keepAliveTime 이 지난 후에 corePoolSize 개수의 스레드들도 모두 종료시킨다.
        // 기본은 false -> 스레드들이 생성되어 queue task 를 수행하고 유효시간 지난 후, 스레드를 corePoolSize 만큼은 남겨두겠다는 것.
        executor.allowCoreThreadTimeOut(true);

        Thread.sleep(6000);
        executor.shutdown();
    }
}
