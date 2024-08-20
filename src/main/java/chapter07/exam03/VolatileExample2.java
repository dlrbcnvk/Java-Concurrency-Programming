package chapter07.exam03;

public class VolatileExample2 {

    private volatile int counter = 0;

    // 쓰기 작업 가시성 보장
    public void increment() { counter++; }

    // 읽기 작업 가시성 보장
    public int getCounter() {
        return counter;
    }

    public static void main(String[] args) {
        VolatileExample2 example = new VolatileExample2();

        // 쓰기 스레드
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 10000000; i++) {
                example.increment();
            }
            System.out.println("쓰기 스레드가 쓰기 작업을 마쳤습니다.");
        });

        Runnable reader = () -> {
            int localValue = -1;
            while (localValue < 1000) {
                localValue = example.getCounter();
                System.out.println(Thread.currentThread().getName() + " 읽은 값: " + localValue);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        writer.start();
        for (int i = 0; i < 5; i++) {
            new Thread(reader).start();
        }
    }
}
