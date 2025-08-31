package com.react.ming.chapter13;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink.OverflowStrategy;

public class BackpressureEx {
    public static Flux<Integer> getnerateNumber() {
        return Flux.create(emmiter -> {
            for (int i = 1; i <= 100; i++) {
                emmiter.next(i);
            }
            emmiter.complete();
        }, OverflowStrategy.ERROR);
    }
}
