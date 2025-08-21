package com.react.ming.part2.chapter10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
@Slf4j
public class SchedulerTypeMain1 {

    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[]{1, 2, 3, 4, 5, 6,})
            .publishOn(Schedulers.parallel())
            .filter(data -> data > 3)
            .doOnNext(data -> log.info("# doOnnext filter: {}", data))
            .publishOn(Schedulers.immediate())
            .map(data -> data * 10)
            .doOnNext(data -> log.info("# doOnNext map: {}", data))
            .subscribe(data -> log.info("# onNext: {}", data));
        Thread.sleep(200L);
    }
}
