import java.util.concurrent.RecursiveTask;

public class ForkJoinPool {

    public class SumTask extends RecursiveTask<Integer>
{
    private int [] arr;
    private int start, end;
    private static final int THRESHOLD = 10; // seuil pour la division

    public SumTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= THRESHOLD) {
            // tâche simple, calcul direct
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            return sum;
        } else {
            // diviser la tâche
            int mid = (start + end) / 2;
            SumTask leftTask = new SumTask(arr, start, mid);
            SumTask rightTask = new SumTask(arr, mid, end);
            leftTask.fork(); // exécuter la tâche gauche de manière asynchrone
            int rightResult = rightTask.compute(); // calculer la tâche droite
            int leftResult = leftTask.join(); // attendre et obtenir le résultat de la tâche gauche
            return leftResult + rightResult;
        }
    }
}
public class ForkJoinDemo {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) array[i] = i + 1;

        SumTask task = pool.new SumTask(array, 0, array.length);
        int result = pool.invoke(task);
        System.out.println("Sum = " + result);
    }
}
public int invoke(ForkJoinPool.SumTask task) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'invoke'");
}
}
