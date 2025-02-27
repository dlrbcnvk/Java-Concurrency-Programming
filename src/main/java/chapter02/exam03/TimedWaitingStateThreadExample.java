package chapter02.exam03;

public class TimedWaitingStateThreadExample {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }

        });
        thread.start();
        Thread.sleep(100);
        System.out.println("스레드 상태: " + thread.getState());
    }
}
