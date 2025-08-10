package com.react.ming.part1.chapter3.nonBlocking.headOffice;

import java.net.URI;
import java.time.LocalTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

/**
 * 본사 API 서버에 도서 정보를 요청하는 검색용 클라이언트 PC 역할을 한다.
 */
@Slf4j
@SpringBootApplication
public class SpringReactiveHeadOfficeApplication {
    private URI baseUri = UriComponentsBuilder.newInstance().scheme("http")
            .host("localhost")
            .port(8080)
            .path("/v1/books")
            .build()
            .encode()
            .toUri();

    public static void main(String[] args) {
        System.setProperty("reactor.netty.ioWorkerCount", "1");
        SpringApplication.run(SpringReactiveHeadOfficeApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return (String... args) -> {
            log.info("# 요청 시작 시간: {}", LocalTime.now());

            for (int i = 1; i <= 20; i++) {
                this.getBook(i).subscribe(
                        book -> {
                            log.info("{}: book name: {}", LocalTime.now(), book.getName());
                        }
                );
            }
            log.info("==== 작업 종료 ====");
        };
    }

    private Mono<Book> getBook(long bookId) {
        URI getBooksUri = UriComponentsBuilder.fromUri(baseUri)
                .path("/{book-id}")
                .build()
                .expand(bookId)
                .encode()
                .toUri(); // http://localhost:8080/v1/books/{book-id}

        return WebClient.create()
                        .get()
                        .uri(getBooksUri)
                        .retrieve()
                        .bodyToMono(Book.class);
    }

    @Bean
    public RestTemplateBuilder restTemplate() {
        return new RestTemplateBuilder();
    }
}
