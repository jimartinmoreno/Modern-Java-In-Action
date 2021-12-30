package modernjavainaction.chap15.threads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static modernjavainaction.chap15.threads.Functions.fo;
import static modernjavainaction.chap15.threads.Functions.go;

public class ExecutorServiceExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int x = 1337;

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> y = executorService.submit(() -> fo(x));
        Future<Integer> z = executorService.submit(() -> go(x));

        System.out.println(y.get() + z.get());
        executorService.shutdown();
    }

}
