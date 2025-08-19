package com.react.ming.part2.chapter9;

import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.EmitFailureHandler;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class SinkMain2 {

    public static void main(String[] args) throws InterruptedException {
        Sinks.Many<String> unicastSink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<String> fluxView = unicastSink.asFlux();

        IntStream
                .range(1, 10)
                .forEach(n -> {
                    try {

                        new Thread(() -> {
                            unicastSink.emitNext(doTask(n), EmitFailureHandler.FAIL_FAST);
                            log.info("# emitted: {}", n);
                        }).start();
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

        fluxView
                .map(result -> result + " success!!")
                .doOnNext(n -> log.info("# map(): {}", n))
                .publishOn(Schedulers.parallel())
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(2000L);
    }

    private static String doTask(int num) {
        return "작업 " + num;
    }
}
