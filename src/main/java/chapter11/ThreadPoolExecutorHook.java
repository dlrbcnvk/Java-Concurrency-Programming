package chapter11;

import java.util.concurrent.*;

public class ThreadPoolExecutorHook extends ThreadPoolExecutor {

    public ThreadPoolExecutorHook(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println("[" + t.getName() + "] 가 작업을 실행하려고 합니다.");
        super.beforeExecute(t, r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        if (t != null) {
            System.out.println("작업이 " + t.getMessage() + " 예외가 발생했습니다.");
        } else {
            System.out.println("작업이 성공적으로 완료되었습니다.");
        }
        super.afterExecute(r, t);
    }

    @Override
    protected void terminated() {
        System.out.println("스레드 풀이 종료되었습니다.");
        super.terminated();
    }

    public static void main(String[] args) {
        int corePoolSize = 2;
        int maxPoolSize = 2;
        long keepAliveTime = 0;
        int workQueueCapacity = 2;

        ThreadPoolExecutor executor = new ThreadPoolExecutorHook(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(workQueueCapacity),
                new ThreadPoolExecutor.AbortPolicy());

        submitTasks(executor);
        System.out.println("zzz");
    }

    private static void submitTasks(ThreadPoolExecutor executor) {
        for (int i = 1; i <= 4; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("[" + Thread.currentThread().getName() + "] Task " + taskId + " is running on thread");
            });
        }

//        executor.shutdown();
    }
}
