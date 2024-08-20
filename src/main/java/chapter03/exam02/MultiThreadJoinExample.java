package chapter03.exam02;

public class MultiThreadJoinExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("스레드가 3초 동안 작동합니다.");
                Thread.sleep(3000);
                System.out.println("스레드 1 작동완료");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("스레드가 2초 동안 작동합니다.");
                Thread.sleep(2000);
                System.out.println("스레드 2 작동완료");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("메인 스레드가 계속 진행합니다.");
    }
}
