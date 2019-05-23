package io.agilehandy.reactive;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ReactiveAPITests {
    @Test
    public void monoExample() throws InterruptedException {
        Mono<String> stubMonoWithADelay =
                Mono.just("Ranga").delayElement(Duration.ofSeconds(5));
        stubMonoWithADelay.subscribe(System.out::println);
        stubMonoWithADelay.subscribe(System.err::println);
        Thread.sleep(6000L);
    }
    @Test
    public void fluxExample() throws InterruptedException {
        Flux<String> stubFluxWithADelay =
                Flux.just("Ranga","haha");
        stubFluxWithADelay.subscribe(System.out::println);
        stubFluxWithADelay.subscribe(System.err::println);
        Thread.sleep(6000L);
    }
}
