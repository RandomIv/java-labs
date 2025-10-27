package org.example;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static class SumTask extends RecursiveTask<Long> {
        private static final int THRESHOLD = 20;
        private final int[] array;
        private final int start;
        private final int end;

        SumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            int length = end - start;
            if (length <= THRESHOLD) {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
                return sum;
            } else {
                int mid = start + length / 2;
                SumTask leftTask = new SumTask(array, start, mid);
                SumTask rightTask = new SumTask(array, mid, end);

                leftTask.fork();
                long rightResult = rightTask.compute();
                long leftResult = leftTask.join();

                return leftResult + rightResult;
            }
        }
    }

    public static void main(String[] args) {
        int size = 1_000_000;
        int[] array = new int[size];

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(101);
        }

        long result;
        try (ForkJoinPool pool = new ForkJoinPool()) {
            SumTask task = new SumTask(array, 0, size);

            result = pool.invoke(task);
        }

        System.out.println("Sum: " + result);
    }
}