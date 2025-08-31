package com.react.ming.part2.chapter14;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class UsingEx {

    public static void main(String[] args) {
        Path path = Paths.get("asda");
        Flux.using(() -> Files.lines(path), Flux::fromStream, Stream::close);
    }
}
