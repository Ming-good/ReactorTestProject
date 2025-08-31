package com.react.ming.part2.chapter14;

import com.react.ming.part2.chapter14.ex.CryptoCurrencyPriceEmitter;
import com.react.ming.part2.chapter14.ex.CryptoCurrencyPriceListener;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class CreateEx2 {

    public static void main(String[] args) throws InterruptedException {
        CryptoCurrencyPriceEmitter emitter = new CryptoCurrencyPriceEmitter();
        Flux.create(sink -> emitter.setListener(new CryptoCurrencyPriceListener() {
                @Override
                public void onPrice(List<Integer> priceList) {
                    priceList.stream()
                             .forEach(price -> {
                                 sink.next(price);
                             });
                }

                @Override
                public void onComplete() {
                    sink.complete();
                }
            }))
            .publishOn(Schedulers.parallel())
            .subscribe(
                    data -> log.info("# onNext: {}", data),
                    err -> {
                    },
                    () -> log.info("# onComplete")
            );

        Thread.sleep(3000L);
        emitter.flowInto();

        Thread.sleep(3000L);
        emitter.complete();
    }
}
