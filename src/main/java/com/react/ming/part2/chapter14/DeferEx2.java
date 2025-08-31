package com.react.ming.part2.chapter14;

import java.time.Duration;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class DeferEx2 {

    public static void main(String[] args) throws InterruptedException {
        log.info("# start: {}", LocalDateTime.now());

        Mono.just("Hello")
            .delayElement(Duration.ofSeconds(3))
            .switchIfEmpty(Mono.fromCallable(() -> sayDefault()))
            .subscribe(data -> log.info("# onNext: {}", data));



    }

    private static String sayDefault() {
        log.info("# Say Hi");
        return "Hi";
    }
}
