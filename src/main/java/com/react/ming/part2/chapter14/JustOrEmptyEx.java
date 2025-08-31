package com.react.ming.part2.chapter14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class JustOrEmptyEx {

    public static void main(String[] args) {
        // justOrEmpty()는 NullPointerException없이 onComplete signal만 전송한다.
        Mono.justOrEmpty(null)
            .subscribe(data -> {
                    },
                    er -> {
                    },
                    () -> log.info("# onComplete"));
    }
}
