
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class ProducerAndConsumerPattern {


static class Producer implements Runnable {
    private BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> q) {
        this.queue = q;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Producing " + i);
                queue.put(i); // bloque si la queue est pleine
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

static class Consumer implements Runnable {
    private BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> q) {
        this.queue = q;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Integer value = queue.take(); // bloque si la queue est vide
                System.out.println("Consuming " + value);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue)).start();
    }
}

}
