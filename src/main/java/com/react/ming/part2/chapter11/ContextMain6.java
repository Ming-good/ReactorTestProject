package com.react.ming.part2.chapter11;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

@Slf4j
public class ContextMain6 {
    public static final String HEADER_AUTH_TOKEN = "authToken";
    public static void main(String[] args) {
        postBoot(Mono.just(new Book("abc-123-123-123", "reactor", "ming")))
                .contextWrite(Context.of(HEADER_AUTH_TOKEN, "asdasdasd"))
                .subscribe(data -> log.info("# onNext: {}", data));
    }

    private static Mono<String> postBoot(Mono<Book> book) {
        return Mono.zip(book,
                           Mono.deferContextual(ctx ->
                                   Mono.just(ctx.get(HEADER_AUTH_TOKEN)))
                   )
                   .flatMap(tuple -> {
                       String rs =
                               "POST the book(" + tuple.getT1().getBookName() + "," + tuple.getT1().getAuthor() + ") with token: " + tuple.getT2();
                       return Mono.just(rs);
                   });
    }

}
