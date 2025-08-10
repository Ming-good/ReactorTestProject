package com.react.ming.part2.chapter7;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Slf4j()
public class ColdSequenceHttpMain {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws InterruptedException {
        Logger rootLogger = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(Level.INFO);

        URI uri = UriComponentsBuilder.newInstance().scheme("http")
                                      .host("127.0.0.1")
                                      .port(7070)
                                      .path("/v/books/1")
                                      .build()
                                      .encode()
                                      .toUri();

        Mono<String> mono = getBook(uri);
        mono.subscribe(name -> log.info("# Book1 name: {}", name));
        Thread.sleep(1000);
        mono.subscribe(name -> log.info("# Book2 name: {}", name));
        Thread.sleep(3000);

    }

    private static Mono<String> getBook(URI uri) {
        return WebClient.create()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .map(res -> {
                    try {
                        HashMap<String, String> map = objectMapper.readValue(res, HashMap.class);
                        return map.get("name");
                    } catch (JsonProcessingException e) {
                        System.out.println("ERROR");
                    }
                    return "";
                });
    }
}
