package chapter07.exam03;

public class VolatileExample {

    volatile boolean running = true;

    public void volatileTest() {
        new Thread(() -> {
            int count = 0;
            while (running) {
                // (스레드 sleep 하지않은경우) running 변수를 cpu 캐시에서 가져온다면 계속 캐시만 바라보게 됨. - 무한루프
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                count++;
            }
            System.out.println(Thread.currentThread().getName() + " 종료. count=" + count);
        }, "Thread-1").start();

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " 종료. 중");
            running = false;
        }, "Thread-2").start();
    }

    public static void main(String[] args) {
        new VolatileExample().volatileTest();
    }
}
