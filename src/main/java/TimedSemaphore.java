public class TimedSemaphore {
    private final int maxThreads;
    private int currentThreads;
    private final long timeLimit;
    private final Object lock = new Object();

    public TimedSemaphore(int maxThreads, long timeLimit) {
        this.maxThreads = maxThreads;
        this.timeLimit = timeLimit;
        this.currentThreads = 0;
    }

    public void acquire() throws InterruptedException {
        synchronized (lock) {
            long startTime = System.currentTimeMillis();
            while (currentThreads >= maxThreads) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                long timeLeft = timeLimit - elapsedTime;
                if (timeLeft <= 0) {
                    break;
                }
                lock.wait(timeLeft);
            }
            if (currentThreads < maxThreads) {
                currentThreads++;
            }
        }
    }

    public void release() {
        synchronized (lock) {
            currentThreads--;
            lock.notify();
        }
    }
}
