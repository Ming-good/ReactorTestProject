package com.react.ming.part2.chapter6.server;

import com.react.ming.part1.chapter3.nonBlocking.branchOffice.Book;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringReactiveApplication.class, args);
    }

    @Bean("bookMap")
    public Map<Long, Book> bookMap() {
        Map<Long, Book> bookMap = new HashMap<>();
        for (long i = 1; i <= 2_000_000; i++) {
            bookMap.put(i, new Book(i, "IT Book" + i, 2000));
        }

        return bookMap;
    }
}
