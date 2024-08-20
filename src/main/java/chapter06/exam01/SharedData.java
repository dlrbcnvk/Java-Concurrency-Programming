package chapter06.exam01;

public class SharedData {

    private int sharedValue = 0;
    private Mutex mutex;

    public SharedData(Mutex mutex) {
        this.mutex = mutex;
    }

    public void sum() {
        try {
            mutex.acquired();
            // critical section
            for (int i = 0; i < 10000000; i++) {
                sharedValue++;
            }
        } finally {
            // release (finally 에서 수행해야 함. critical section 과정 중에서 오류가 발생할 수 있기 때문이다.)
            mutex.release();
        }
    }

    public int getSharedValue() {
        return sharedValue;
    }
}
