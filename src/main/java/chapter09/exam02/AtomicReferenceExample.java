package chapter09.exam02;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceExample {

    public static void main(String[] args) {
        User user1 = new User("Alice", 25);
        User user2 = new User("Bob", 30);

        AtomicReference<User> atomicReference = new AtomicReference<>(user1);

    }
}

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
