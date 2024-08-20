package chapter01.exam03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class IOBoundExample {
    public static void main(String[] args) {
        int numThreads = Runtime.getRuntime().availableProcessors() * 2;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) {
            executorService.submit(() -> {

                try {
                    // I/O가 집중되는 작업
                    for (int j = 0; j < 5; j++) {
                        Files.readAllLines(Path.of("/Users/joseong-gyu/Desktop/JavaPlayground/Java-Concurrency-Programming/src/main/java/chapter01/exam03/sample.txt"));
                        // I/O Bound 일 때 Context Switching 이 일어난다.
                        System.out.println("스레드: " + Thread.currentThread().getName() + ", " + j);
                    }

                    // 아주 빠른 cpu 연산
                    long result = 0;
                    for (long j = 0; j < 10; j++) {
                        result += j;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });
        }

        executorService.shutdown();
    }
}
