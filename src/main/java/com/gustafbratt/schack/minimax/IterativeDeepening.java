package com.gustafbratt.schack.minimax;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.Drag;
import com.gustafbratt.schack.core.Farg;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class IterativeDeepening {


    public static Drag hittaBastaDrag(Brade brade, Farg farg, int timeoutSekunder) {
        ExecutorService executor = Executors.newFixedThreadPool(16);

        List<Future<Drag>> futures = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            int finalI = i;
            var f = executor.submit(() -> MinMax.hittaBastaDrag(brade, farg, finalI));
            futures.add(f);
        }
        System.out.println("Dags för sleep");
        try {
            Thread.sleep(timeoutSekunder * 1_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Färdig med sleep");
        Drag drag = null;
        for (var f : futures) {
            if (f.isDone()) {
                try {
                    drag = f.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                f.cancel(true);
            }
        }
        executor.shutdownNow();
        return drag;
    }
}
