package com.react.ming.part2.chapter14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class GetnerateEx1 {
    public static void main(String[] args) {
        Flux.generate(() -> 0, (state, sink) -> {
                sink.next("Data transform");
                if (state == 10) {
                    sink.complete();
                }
                return ++state;
            })
            .subscribe(data -> log.info("# onNext: {}", data));
    }
}
