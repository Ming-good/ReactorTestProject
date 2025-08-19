package com.react.ming.part2.chapter9;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.EmitFailureHandler;
import reactor.core.publisher.Sinks.Many;

@Slf4j
public class SinksManyMain2 {

    public static void main(String[] args) {

        Many<Object> multicastSink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<Object> fluxView = multicastSink.asFlux();

        multicastSink.emitNext(1, EmitFailureHandler.FAIL_FAST);
        multicastSink.emitNext(2, EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> log.info("# Subscriber1: {}", data));
        fluxView.subscribe(data -> log.info("# Subscriber2: {}", data));

        multicastSink.emitNext(3, EmitFailureHandler.FAIL_FAST);
    }
}
