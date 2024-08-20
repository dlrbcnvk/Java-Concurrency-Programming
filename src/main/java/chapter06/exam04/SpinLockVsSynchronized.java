package chapter06.exam04;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpinLockVsSynchronized {

    private AtomicBoolean spinLock = new AtomicBoolean(false);

    private Object syncLock = new Object();

    private int count = 0;

    final static int THREAD_COUNT = 5;
    final int ITERATIONS = 10_000_000;

    public void useSpinLock() {
        while (!spinLock.compareAndSet(false, true));
    }
}
