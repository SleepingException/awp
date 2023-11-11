public class Philosopher implements Runnable {
    private final int id;
    private final TimedSemaphore left;
    private final TimedSemaphore right;

    public Philosopher(int id, TimedSemaphore left, TimedSemaphore right) {
        this.id = id;
        this.left = left;
        this.right = right;
    }

    private void think() throws InterruptedException {
        System.out.println("Философ " + id + " размышляет");
        Thread.sleep((long) (Math.random() * 1000)); // Время размышлений
    }

    private void eat() throws InterruptedException {
        // Пытаемся захватить вилки
        left.acquire();
        right.acquire();

        System.out.println("Философ " + id + " ест");
        Thread.sleep((long) (Math.random() * 1000)); // Время еды

        // Освобождаем вилки
        left.release();
        right.release();
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                eat();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}