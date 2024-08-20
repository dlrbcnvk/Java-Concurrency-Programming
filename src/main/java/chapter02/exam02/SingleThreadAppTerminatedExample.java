package chapter02.exam02;

public class SingleThreadAppTerminatedExample {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            sum += i;
        }

        System.out.println("sum : " + sum);

        System.out.println(Thread.currentThread().getName() + " 스레드 종료");
    }
}
