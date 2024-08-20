package chapter02.exam02;

public class MultiThreadAppTerminatedExample {

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new ThreadStackExample.MyRunnable(i));
            thread.start();
        }

        System.out.println(Thread.currentThread().getName() + " 스레드 종료");
    }

    static class MyRunnable implements Runnable {

        private final int threadId;

        public MyRunnable(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": 스레드 생성 중");
            firstMethod(threadId);
            System.out.println(Thread.currentThread().getName() + " 스레드 종료");
        }

        private void firstMethod(int threadId) {
            int localValue = threadId + 100;
            secondMethod(localValue);
        }

        private void secondMethod(int localValue) {
            System.out.println(Thread.currentThread().getName() + " : 스레드 ID : " + threadId + ", Value : " + localValue);
        }
    }
}
