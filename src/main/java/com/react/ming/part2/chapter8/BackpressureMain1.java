package com.react.ming.part2.chapter8;

import com.react.ming.part2.util.Log;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BackpressureMain1 {

    public static void main(String[] args) {

        Flux.range(1, 5)
            .doOnRequest(data -> Log.info("# doOnRequest: {}", data))
            .subscribe(new BaseSubscriber<Integer>() {
                @Override
                protected void hookOnSubscribe(Subscription subscription) {
                    request(1);
                }

                @Override
                protected void hookOnNext(Integer value) {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    Log.info("# hookOnNext: {}", value);
                    request(2);
                }
            });
    }
}
