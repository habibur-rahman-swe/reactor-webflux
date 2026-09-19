package com.reactive.reactor.fluxandmonoplayground;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {

	@Test
	public void fluxText() {
		Flux<String> stringFlux = Flux.just("A", "B", "C")
//				 .concatWith(Flux.error(new RuntimeException("Error!")))v
				.concatWith(Flux.just("After Error")).log();

		stringFlux.subscribe(System.out::println, (e) -> System.err.println(e), () -> System.out.println("Completed"));
	}

	@Test
	public void fluxTestElement_withoutError() {
		Flux<String> stringFlux = Flux.just("A", "B", "C")
				.concatWith(Flux.error(new RuntimeException("Custum exception")))
				.log();
		
		StepVerifier.create(stringFlux)
		.expectNext("A")
		.expectNext("B")
		.expectNext("C")
		.expectError(RuntimeException.class)
		.verify();
	}
	
	@Test
	public void fluxTestElementsCount_withError() {
		Flux<String> stringFlux = Flux.just("A", "B", "C")
				.concatWith(Flux.error(new RuntimeException("Custum exception")))
				.log();
		
		StepVerifier.create(stringFlux)
		.expectNextCount(3)
		.expectError(RuntimeException.class)
		.verify();
	}
	
	@Test
	public void fluxTestElementsCount_withError1() {
		Flux<String> stringFlux = Flux.just("A", "B", "C")
				.concatWith(Flux.error(new RuntimeException("Custum exception")))
				.log();
		
		StepVerifier.create(stringFlux)
		.expectNext("A", "B", "C")
		.expectError(RuntimeException.class)
		.verify();
	}
	
	@Test
	public void monoTest() {
		Mono<String> stringMono = Mono.just("String");
		
		StepVerifier.create(stringMono.log())
		.expectNext("String")
		.verifyComplete();
	}
	
	@Test
	public void monoTest_error() {
		
		StepVerifier.create(Mono.just(new RuntimeException()).log())
		.expectError(RuntimeException.class)
		.verify();
	}
}
