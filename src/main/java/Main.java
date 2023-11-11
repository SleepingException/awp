import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Задание 11.
 * Напишите класс семафор, впускающий в себя не более n тредов.
 * На основе wait-notify
 * C ограничением по времени: по прошествию определенного времени можно запускать новые потоки
 */
public class Main {
    private static final int NUM_PHILOSOPHERS = 5;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(NUM_PHILOSOPHERS);
    public static void main(String[] args) throws InterruptedException {

        List<TimedSemaphore> forks = new ArrayList<>();

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks.add(new TimedSemaphore(1, 100));
        }

        List<Philosopher> philosophers = new ArrayList<>();
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            TimedSemaphore leftFork = forks.get(i);
            TimedSemaphore rightFork = forks.get((i + 1) % NUM_PHILOSOPHERS);
            philosophers.add(new Philosopher(i, leftFork, rightFork));
        }

        philosophers.forEach(executorService::execute);
    }
}
