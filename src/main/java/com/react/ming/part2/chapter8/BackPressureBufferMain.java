package com.react.ming.part2.chapter8;

import com.react.ming.part2.util.Log;
import java.time.Duration;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class BackPressureBufferMain {

    public static void main(String[] args) throws InterruptedException {
        Flux
                .interval(Duration.ofMillis(300L))
                .doOnNext(data -> Log.info("# emitted by original Flux: {}", data))
                .onBackpressureBuffer(
                        2,
                        drop -> Log.info("** Overflow & Dropped: {} ***", drop),
                        BufferOverflowStrategy.DROP_LATEST
                )
                .doOnNext(data -> Log.info("[ # emitted by Buffer: {} ]", data))
                .publishOn(Schedulers.parallel(), false, 1)
                .subscribe(data -> {
                            try {
                                Thread.sleep(1000L);
                            } catch (InterruptedException e) {
                            }
                            Log.info("# onNext: {}", data);
                        },
                        error -> Log.info("# onError: {}", error)
                );
        Thread.sleep(3000L);

    }
}
