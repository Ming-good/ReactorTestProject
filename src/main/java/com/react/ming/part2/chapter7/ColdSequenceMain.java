package com.react.ming.part2.chapter7;

import java.util.Arrays;
import reactor.core.publisher.Flux;

public class ColdSequenceMain {

    public static void main(String[] args) throws InterruptedException {
        Flux<String> coldFlux = Flux.fromIterable(Arrays.asList("KOREA", "JAPAN", "CHINEESE"))
                               .map(String::toLowerCase);
        coldFlux.subscribe(country -> System.out.println("# 구독자1:" + country));
        Thread.sleep(2000L);
        System.out.println("----------------------------------");
        coldFlux.subscribe(country -> System.out.println("# 구독자2: " + country));
    }
}
