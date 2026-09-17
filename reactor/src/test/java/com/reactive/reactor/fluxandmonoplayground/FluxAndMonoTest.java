package com.reactive.reactor.fluxandmonoplayground;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;

public class FluxAndMonoTest {

	@Test
	public void fluxText() {
		Flux<String> stringFlux = Flux.just("A", "B", "C")
//				 .concatWith(Flux.error(new RuntimeException("Error!")))v
				.concatWith(Flux.just("After Error")).log();

		stringFlux.subscribe(System.out::println, (e) -> System.err.println(e), () -> System.out.println("Completed"));
	}

	public void fluxTestElement_withoutError() {
		Flux<String> stringFlux = Flux.just("A", "B", "C")
				.log();

	}
}
