package chapter11;

import java.util.concurrent.*;

public class PrestartThreadsExample {
    public static void main(String[] args) {

        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 0L; // 스레드 유휴 시간 설정
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        int taskNum = 9;
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

//        executor.prestartCoreThread(); // 1개 미리 생성
//        executor.prestartAllCoreThreads(); // corePoolSize 만큼 미리 생성

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
