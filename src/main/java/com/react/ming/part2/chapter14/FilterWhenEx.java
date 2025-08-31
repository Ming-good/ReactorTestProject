package com.react.ming.part2.chapter14;

import com.react.ming.part2.chapter14.ex.SampleData;
import com.react.ming.part2.chapter14.ex.SampleData.CovidVaccine;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

@Slf4j
public class FilterWhenEx {

    public static void main(String[] args) throws InterruptedException {
        Map<CovidVaccine, Tuple2<CovidVaccine, Integer>> covidVaccines = SampleData.getCovidVaccines();
        Flux.fromIterable(SampleData.coronaVaccineNames)
            .filterWhen(vaccin -> Mono.just(covidVaccines.get(vaccin).getT2() >= 3_000_000)
                                      .publishOn(Schedulers.parallel()))
            .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(1000L);
    }
}
