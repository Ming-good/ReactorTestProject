package com.react.ming.part1.chapter3.blocking.headOffice;

import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping("/v1/books")
public class SpringMvcHeadOfficeController {

    private final RestTemplate restTemplate;
    URI baseuri = UriComponentsBuilder.newInstance().scheme("http")
                                      .host("localhost")
                                      .port(7070)
                                      .path("/v1/books")
                                      .build()
                                      .encode()
                                      .toUri();

    public SpringMvcHeadOfficeController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{book-id}")
    public ResponseEntity<Book> getBook(@PathVariable("book-id") long bookId) {
        URI getBookUri = UriComponentsBuilder.fromUri(baseuri)
                                      .path("/{book-id}")
                                      .build()
                                      .expand(bookId)
                                      .encode()
                                      .toUri();

        System.out.println("getBook : " +getBookUri);

        ResponseEntity<Book> reponse = restTemplate.getForEntity(getBookUri, Book.class);
        return ResponseEntity.ok(reponse.getBody());
    }
}
