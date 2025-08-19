package com.react.ming.part2.chapter9;

import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class SinkMain1 {

    public static void main(String[] args) throws InterruptedException {
        Flux.create((FluxSink<String> sink) -> {
                IntStream
                    .range(1, 10)
                    .forEach(n -> sink.next(n + "작업"));
        })
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(n -> log.info("# create(): {}", n))
                .publishOn(Schedulers.parallel())
                .map(result -> result + " success!")
                .doOnNext(n -> log.info("# map(): {}", n))
                .publishOn(Schedulers.parallel())
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(1000L);


    }
}
