package chapter05.exam04;

public class ThreadSafeLocalVariableExample {

    // 스레드들이 공유할 수 있는 멤버변수의 값을 바꾼다면, 원하는 결과를 얻으리라 보장할 수 없다.
    // 동시성 문제.
    // thread-safe 하지 않다.
    // int localSum = 0;

    public void printNumbers(int plus) {
        // 지역변수, 매개변수로 정의된 변수. 각 스레드는 이 변수의 독립된 복사본을 가짐.
        int localSum = 0;

        for (int i = 0; i <= 5; i++) {
            localSum += i;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        localSum += plus;
        System.out.println(Thread.currentThread().getName() + " 스레드의 현재 합계=" + localSum);
    }

    public static void main(String[] args) {
        ThreadSafeLocalVariableExample example = new ThreadSafeLocalVariableExample();

        Thread thread1 = new Thread(() -> {
            example.printNumbers(50);
        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            example.printNumbers(40);
        }, "Thread-2");

        thread1.start();
        thread2.start();
    }
}
