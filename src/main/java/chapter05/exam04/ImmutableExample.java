package chapter05.exam04;

public class ImmutableExample implements Runnable {
    // 클래스 자체를 변경 불가능하도록 만들어서 동시성 문제를 원천적으로 차단하는 방법

    private ImmutablePerson person;

    public ImmutableExample(ImmutablePerson person) {
        this.person = person;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " - 이름: " + person.getName() + ", 나이: " + person.getAge());
    }

    public static void main(String[] args) {
        ImmutablePerson person = new ImmutablePerson("홍길동", 25);

        for (int i = 0; i < 10; i++) {
            new Thread(new ImmutableExample(person)).start();
        }
    }
}

final class ImmutablePerson {

    private final String name;
    private final int age;

    public ImmutablePerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}