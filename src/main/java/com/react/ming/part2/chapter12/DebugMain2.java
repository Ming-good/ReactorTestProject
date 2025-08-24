package com.react.ming.part2.chapter12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class DebugMain2 {

    public static void main(String[] args) {
        Flux.just(2, 4, 6, 8)
            .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x / y)
            .map(num -> num + 2)
            .log()
            .subscribe(
                    data -> log.info("# onNext: {}", data),
                    err -> log.error("# onErr: {}", err)
            );
    }
}
