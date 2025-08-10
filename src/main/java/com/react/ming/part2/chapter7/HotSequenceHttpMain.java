package com.react.ming.part2.chapter7;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.react.ming.part2.util.Log;
import java.net.URI;
import java.util.HashMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

public class HotSequenceHttpMain {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws InterruptedException {
        URI uri = UriComponentsBuilder.newInstance().scheme("http")
                                      .host("127.0.0.1")
                                      .port(7070)
                                      .path("/v/books/1")
                                      .build()
                                      .encode()
                                      .toUri();

        Mono<String> mono = getBook(uri).cache();
        mono.subscribe(name -> Log.info("# Book1 name: {}", name));
        Thread.sleep(1000);
        mono.subscribe(name -> Log.info("# Book2 name: {}", name));
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
