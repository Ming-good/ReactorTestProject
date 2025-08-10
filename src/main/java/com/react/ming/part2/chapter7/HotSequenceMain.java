package com.react.ming.part2.chapter7;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class HotSequenceMain {

    public static void main(String[] args) throws InterruptedException {
        String[] singers = {"가수 A", "가수 B", "가수 C", "가수 D", "가수 F"};
        log.info("# Begin concert:");
        Flux<String> concertFlux = Flux.fromArray(singers)
                                 .delayElements(Duration.ofSeconds(1))
                                 .share();
        concertFlux.subscribe(
                singer -> log.info("# 구독자1가 보고있습니다 " + singer + "의 노래를")
        );

        Thread.sleep(2500);

        concertFlux.subscribe(
                singer -> log.info("# 구독자2가 보고있습니다 " + singer + "의 노래를")
        );

        Thread.sleep(3000);
    }
}
