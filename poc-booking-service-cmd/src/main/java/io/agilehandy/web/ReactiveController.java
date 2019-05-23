package io.agilehandy.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class ReactiveController {
    @GetMapping("/stocks/{stockCode}")
    Flux<String> retrieveStockPriceHardcoded
            (@PathVariable("stockCode") String stockCode) {
        return Flux.interval(Duration.ofSeconds(5))
                .map(l -> getCurrentDate() + " : "
                        + getRandomNumber(100, 125))
                .log();
    }
    private String getCurrentDate() {
        return (new Date()).toString();
    }
    private int getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
