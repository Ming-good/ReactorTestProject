package com.react.ming.chapter13;

import reactor.core.publisher.Flux;

public class RecordTestEx {

    public static Flux<String> getCapitalizedCountry(Flux<String> source) {
        return source
                .map(country -> country.substring(0, 1).toUpperCase() + country.substring(1));
    }
}
