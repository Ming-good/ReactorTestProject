package com.react.ming.part2.chapter6;

import java.util.stream.Collectors;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxMain {

    public static void main(String[] args) {
        System.out.println("=== FLUX EX1 ===");
        Flux.just(6, 9, 13)
            .map(num -> num % 2)
            .subscribe(System.out::println);

        System.out.println();
        System.out.println("=== FLUX EX2 ===");
        Flux.fromArray(new Integer[]{3, 6, 7, 9})
            .filter(num -> num > 6)
            .map(num -> num * 2)
            .subscribe(System.out::println);

        System.out.println();
        System.out.println("=== FLUX EX3 concatWith() ===");
        Flux<String> flux = Mono.justOrEmpty("Steve")
                                .concatWith(Mono.justOrEmpty("Jobs"));
        flux.subscribe(System.out::println);

        System.out.println();
        System.out.println("=== FLUX EX4 ===");
        Flux.concat(
                    Flux.just("mercury", "Venus", "Earth"),
                    Flux.just("Mars", "Jupiter", "Saturn"),
                    Flux.just("Uranus", "Neptune", "Pluto")
            ).collect(Collectors.toList())
            .subscribe(System.out::println);
    }
}
