package com.react.ming.part2.chapter9;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.EmitFailureHandler;
import reactor.core.publisher.Sinks.One;

@Slf4j
public class SinksOneMain1 {
    public static void main(String[] args) {
        One<Object> sinkOne = Sinks.one();
        Mono<Object> mono = sinkOne.asMono();

        sinkOne.emitValue("Hello Reactor", EmitFailureHandler.FAIL_FAST);

        mono.subscribe(data -> log.info("# Subscriber1: " + data));
        mono.subscribe(data -> log.info("# Subscriber2: " + data));
    }
}
