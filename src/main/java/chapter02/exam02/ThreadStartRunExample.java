package chapter02.exam02;

public class ThreadStartRunExample {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": 스레드 생성 중");
            }
        });

        thread.start();
    }
}
