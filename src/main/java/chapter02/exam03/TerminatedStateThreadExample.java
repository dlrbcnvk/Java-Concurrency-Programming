package chapter02.exam03;

public class TerminatedStateThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("스레드 실행 중");
        });
        thread.start();
        // 메인 스레드는 thread 스레드가 종료될 때까지 대기한다.
        System.out.println(Thread.currentThread().getName() + " 스레드가 thread 스레드를 향해 join() 을 건다.");
        System.out.println("메인 스레드 상태: " + Thread.currentThread().getState());
        thread.join();
        System.out.println("메인 스레드 상태: " + Thread.currentThread().getState());
        System.out.println("스레드 상태: " + thread.getState());
        System.out.println("메인 스레드 상태: " + Thread.currentThread().getState());
    }

}
