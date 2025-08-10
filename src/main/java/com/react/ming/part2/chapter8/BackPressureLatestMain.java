package com.react.ming.part2.chapter8;

import com.react.ming.part2.util.Log;
import java.time.Duration;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class BackPressureLatestMain {

    public static void main(String[] args) throws InterruptedException {
        Flux
                .interval(Duration.ofMillis(1L))
                .onBackpressureLatest()
                .publishOn(Schedulers.parallel())
                .subscribe(data -> {
                            try {
                                Thread.sleep(5L);
                            } catch (InterruptedException e) {
                            }
                            Log.info("# onNext: {}", data);
                        },
                        error -> Log.info("# onError: {}", error));
        Thread.sleep(2000L);

    }
}
