import java.util.*;
import java.util.stream.*;

public class ParallelStreamDemo {
    public static void main(String[] args) {
        List<Integer> numbers = IntStream.rangeClosed(1, 1000)
                                         .boxed()
                                         .toList();

        // Stream séquentiel
        int sumSequential = numbers.stream()
                                   .reduce(0, Integer::sum);

        // Stream parallèle
        int sumParallel = numbers.parallelStream()
                                 .reduce(0, Integer::sum);

        System.out.println("Sum sequential = " + sumSequential);
        System.out.println("Sum parallel   = " + sumParallel);
    }
}
