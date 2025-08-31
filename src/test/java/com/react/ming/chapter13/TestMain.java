package com.react.ming.chapter13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.test.publisher.TestPublisher;
import reactor.test.publisher.TestPublisher.Violation;
import reactor.test.scheduler.VirtualTimeScheduler;

public class TestMain {

    @Test
    public void sayHelloTest() {
        StepVerifier
                .create(Mono.just("Hello"))
                .expectNext("Hello")
                .expectComplete()
                .verify();
    }

    @Test
    public void sayHelloTest2() {
        StepVerifier
                .create(GetneralTestExample.sayHello())
                .expectSubscription()
                .as("# expect subscription")
                .expectNext("Hi")
                .as("# expect Hi")
                .expectNext("Reactor")
                .as("# expect Reactor")
                .verifyComplete();
    }
    @Test
    public void divideByTwoTest() {
        Flux<Integer> source = Flux.just(2,4,6,8,10);
        StepVerifier
                .create(GetneralTestExample.divideByTwo(source))
                .expectSubscription()
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
//                .expectNext(1, 2, 3, 4)
                .expectError()
                .verify();
    }

    @Test
    public void takeNumberTest() {
        Flux<Integer> source = Flux.range(0, 1000);
        StepVerifier
                .create(GetneralTestExample.takeNumber(source, 500), StepVerifierOptions.create().scenarioName("Verify from 0 to 499"))
                .expectSubscription()
                .expectNext(0)
                .expectNext(1)
                .expectNextCount(497)
                .expectNext(499)
                .expectComplete()
                .verify();
    }

    @Test
    public void getCOVID19CountTest() {
        StepVerifier
                .withVirtualTime(() -> TimeBasedTestEx.getCOVID19Count(
                                Flux.interval(Duration.ofHours(1)).take(2)
                        )
                )
                .expectSubscription()
                .then(() -> VirtualTimeScheduler
                        .get()
                        .advanceTimeBy(Duration.ofHours(2))
                )
                .expectNextCount(22)
                .expectComplete()
                .verify();
    }

    @Test
    public void getCOVID19CountTest2() {
        StepVerifier
                .create(TimeBasedTestEx.getCOVID19Count(
                        Flux.interval(Duration.ofMinutes(1)).take(1)
                ))
                .expectSubscription()
                .expectNextCount(11)
                .expectComplete()
                .verify(Duration.ofSeconds(3));
    }

    @Test
    public void getVoteCountTest() throws InterruptedException {
        StepVerifier
                .withVirtualTime(() -> TimeBasedTestEx.getVoteCount(
                        Flux.interval(Duration.ofMinutes(2))
                ))
                .expectSubscription()
                .thenAwait(Duration.ofMinutes(8))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNextCount(5)
                .expectComplete()
                .verify();
    }

    @Test
    public void getnerateNumberTest() {
        StepVerifier
                .create(BackpressureEx.getnerateNumber(), 99L)
                .thenConsumeWhile(num -> num >= 1)
                .verifyComplete();
    }
    @Test
    public void getnerateNumberTest2() {
        StepVerifier
                .create(BackpressureEx.getnerateNumber(), 1)
                .thenConsumeWhile(num -> num >= 1)
                .expectError()
                .verifyThenAssertThat()
                .hasDroppedElements();
    }

    @Test
    public void getSecretMesgTest() {
        Mono<String> source = Mono.just("hello");
        StepVerifier
                .create(ContextEx
                        .getSecretMsg(source)
                        .contextWrite(context -> context.put("secretMessage", "Hello, Reactor"))
                        .contextWrite(context -> context.put("secretKey", "aGVsbG8="))
                )
                .expectSubscription()
                .expectAccessibleContext()
                .hasKey("secretKey")
                .hasKey("secretMessage")
                .then()
                .expectNext("Hello, Reactor")
                .expectComplete()
                .verify();
    }

    @Test
    public void getCityTest() {
        StepVerifier
                .create(RecordTestEx.getCapitalizedCountry(
                        Flux.just("korea", "england", "canada", "india")
                ))
                .expectSubscription()
                .recordWith(ArrayList::new)
                .thenConsumeWhile(country -> !country.isEmpty())
                .consumeRecordedWith(countiries -> {
                    assertEquals(
                            countiries
                                    .stream()
                                    .allMatch(country ->
                                            Character.isUpperCase(country.charAt(0))), true
                    );
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void getCountryTest() {
        StepVerifier
                .create(RecordTestEx.getCapitalizedCountry(
                        Flux.just("korea", "england", "canada", "india")
                ))
                .expectSubscription()
                .recordWith(ArrayList::new)
                .thenConsumeWhile(country -> !country.isEmpty())
                .expectRecordedMatches(coutries ->{
                    coutries
                            .stream()
                            .forEach(c -> System.out.println(c));

                            return coutries
                                    .stream()
                                    .allMatch(country ->
                                            Character.isUpperCase(country.charAt(0)));
                    }
                )
                .expectComplete()
                .verify();
    }

    @Test
    public void divideByTwoTest2() {
//        TestPublisher<Integer> source = TestPublisher.create();
        TestPublisher<Integer> source = TestPublisher.createNoncompliant(Violation.ALLOW_NULL);
        StepVerifier
                .create(GetneralTestExample.divideByTwo(source.flux()))
                .expectSubscription()
                .then(() -> {
                    getDataSource().stream()
                                   .forEach(data -> source.next(data));
                    source.complete();
                })
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify();
    }

    @Test
    public void test() throws InterruptedException {
        Flux.just(1, 2, 3)
            .flatMap(i -> Flux.just(i, i * 10)
                              .map(data -> {
                                  System.out.println(data);
                                  return data;
                              })
            ).subscribe();

        Thread.sleep(3000L);

    }

    private static List<Integer> getDataSource() {
        return Arrays.asList(2, 4, 6, 8, 10);
    }
}
