package com.react.ming.part2.chapter9;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.EmitFailureHandler;
import reactor.core.publisher.Sinks.Many;

@Slf4j
public class SinksManyMain3 {

    public static void main(String[] args) {
        Many<Object> replaySink = Sinks.many().replay().limit(2);
        Flux<Object> fluxView = replaySink.asFlux();

        replaySink.emitNext(1, EmitFailureHandler.FAIL_FAST);
        replaySink.emitNext(2, EmitFailureHandler.FAIL_FAST);
        replaySink.emitNext(3, EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> log.info("# Subscriber1: {}", data));

        replaySink.emitNext(4, EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> log.info("# subscriber2: {}", data));
    }
}
