import java.util.concurrent.*;

public class CompletableFuturee {
    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> future =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    }
                    return 42;
                });

        // chaîne de traitement
        CompletableFuture<String> result =
                future.thenApply(res -> "Result: " + res);

        System.out.println(result.get()); // bloque juste à la fin
    }
}
