package com.react.ming.part2.chapter6;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

public class MonoRestMain {

    public static void main(String[] args) {
        URI uri = UriComponentsBuilder.newInstance().scheme("http")
                                       .host("127.0.0.1")
                                       .port(7070)
                                       .path("/v/books/1")
                                       .build()
                                       .encode()
                                       .toUri();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Mono.just(
                restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class)
        ).map(res -> {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                HashMap<String, String> map = objectMapper.readValue(res.getBody(), HashMap.class);
                return map.get("name");
            } catch (JsonProcessingException e) {
                System.out.println("ERROR");
            }
            return "";
        }).subscribe(data -> System.out.println("book name: " + data),
                error -> System.out.println(error),
                () -> System.out.println("# emitted onComplete"));

        System.out.println("TEST");
    }
}
