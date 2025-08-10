package com.react.ming.part2.chapter5;

import java.util.stream.Collectors;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactorBasicOperatorMain {

    public static void main(String[] args) {
        System.out.println("=== just() ===");
        Flux.just("Hello", "Reactor")
            .map(String::toLowerCase)
            .collect(Collectors.joining(" "))
            .subscribe(System.out::println);
        Mono.just("Hello Money")
            .map(String::toLowerCase)
            .subscribe(System.out::println);

        System.out.println();

        System.out.println("=== empty() ===");
        Flux.empty().subscribe(none -> System.out.println("# 빈값"),
                error -> {
                    System.out.println("오류");
                },
                () -> System.out.println("Flux Emmit 성공"));
        Mono.empty().subscribe(none -> System.out.println("# Mono 빈값"),
                error -> {
                    System.out.println("MONO 오류");
                },
                () -> System.out.println("Mono Emmit 성공"));
    }
}
