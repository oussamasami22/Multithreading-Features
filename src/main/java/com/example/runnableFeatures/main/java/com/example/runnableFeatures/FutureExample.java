import java.util.concurrent.*;

public class FutureExample {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<Integer> task = () -> {
            Thread.sleep(1000); // simulate long task
            return 42;
        };

        Future<Integer> future = executor.submit(task);

        System.out.println("Task submitted...");

        // bloque jusqu'au résultat
        Integer result = future.get();
        System.out.println("Result: " + result);

        executor.shutdown();
    }
}
