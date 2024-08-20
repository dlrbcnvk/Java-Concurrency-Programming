package chapter10.exam02;

import java.util.concurrent.Executor;

public class SyncExecutorExample {

    public static void main(String[] args) {
        Executor syncExecutor = new SyncExecutor();
        syncExecutor.execute(() -> {
            System.out.println("동기 작업 1 수행 중...");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("동기 작업 1 완료");
        });

        syncExecutor.execute(() -> {
            System.out.println("동기 작업 2 수행 중...");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("동기 작업 2 완료");
        });
    }

    static class SyncExecutor implements Executor {

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }
}
