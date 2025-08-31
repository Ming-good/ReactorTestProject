package com.react.ming.part2.chapter14;

import com.react.ming.part1.chapter4.SampleData;
import java.util.List;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FromStreamEx {

    public static void main(String[] args) {
        Flux.fromStream(() -> List.of(1, 2, 3, 4, 5).stream())
            .filter(a -> a > 3)
            .subscribe(data -> log.info("{}", data));
    }
}
