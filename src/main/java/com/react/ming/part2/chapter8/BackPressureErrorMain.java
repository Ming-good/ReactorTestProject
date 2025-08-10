package com.react.ming.part2.chapter8;

import com.react.ming.part2.util.Log;
import java.time.Duration;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class BackPressureErrorMain {

    public static void main(String[] args) throws InterruptedException {
        Flux
                .interval(Duration.ofMillis(1L))
                .onBackpressureError()
                .doOnNext(data -> Log.info("# doOnNext: {}", data))
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
