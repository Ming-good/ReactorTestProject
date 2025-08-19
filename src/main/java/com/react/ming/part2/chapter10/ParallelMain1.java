package com.react.ming.part2.chapter10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ParallelMain1 {

    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19})
            .doOnSubscribe(subscription -> log.info("### publishOn() 사용시"))
            .publishOn(Schedulers.parallel())
            .doOnNext(data -> log.info("# onNext: {}", data))
            .doOnTerminate(() -> log.info("############\n"))
            .subscribe();

        Flux.fromArray(new Integer[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19})
            .doOnSubscribe(subscription -> log.info("### parallel() 사용시"))
            .parallel(4)
            .runOn(Schedulers.parallel())
            .subscribe(data -> log.info("# onNext: {}", data));

            Thread.sleep(200L);
    }
}
