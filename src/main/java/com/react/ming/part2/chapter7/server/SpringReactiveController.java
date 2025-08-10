package com.react.ming.part2.chapter7.server;

import com.react.ming.part1.chapter3.nonBlocking.branchOffice.Book;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v/books")
public class SpringReactiveController {

    private Map<Long, Book> bookMap;

    @Autowired
    public SpringReactiveController(Map<Long, Book> bookMap) {
        this.bookMap = bookMap;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{book-id}")
    public Mono<Book> getBook(@PathVariable("book-id") long bookId) throws InterruptedException {
        Thread.sleep(500);
        Book book = bookMap.get(bookId);
        return Mono.just(book);
    }
}
