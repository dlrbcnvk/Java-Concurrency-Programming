package chapter02.exam01;

public class LambdaThreadExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName() + ": 스레드 생성 중"));
        thread.start();
        System.out.println("zzzz");
    }
}
