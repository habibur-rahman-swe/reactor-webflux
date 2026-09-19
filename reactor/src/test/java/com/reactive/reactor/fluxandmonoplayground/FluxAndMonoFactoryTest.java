package com.reactive.reactor.fluxandmonoplayground;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


public class FluxAndMonoFactoryTest {

	List<String> names = Arrays.asList("A", "B", "C", "D", "E");
	
//	@Test
	public void fluxUsingIterable() {
		
		Flux<String> namesFlux = Flux.fromIterable(names).log();
		
		StepVerifier.create(namesFlux)
		.expectNext("A", "B", "C", "D", "E")
		.verifyComplete();
	}
	
//	@Test
	public void fluxUsingArray() {
		String[] names = new String[] {"A", "B", "C", "D", "E"};
		
		Flux<String> namesFlux = Flux.fromArray(names).log();
		
		StepVerifier.create(namesFlux).verifyComplete();
	}
	
//	@Test
	public void fluxUsingStreem() {
		Flux<String> namesFlux = Flux.fromStream(names.stream()).log();
		
		StepVerifier.create(namesFlux)
		.expectNext("A", "B", "C", "D", "E")
		.verifyComplete();
	}
	
//	@Test
	public void monoUsingJustOrEmpty() {
		Mono<String> mono = Mono.justOrEmpty(null);
		
		StepVerifier.create(mono)
		.verifyComplete();
	}
	
//	@Test
	public void monoUsingSupplier() {
		Supplier<String>stringSupplier = () -> "adam";
		
		Mono<String> stringMono = Mono.fromSupplier(stringSupplier);
		
		System.out.println(stringSupplier.get());
		
		StepVerifier.create(stringMono.log())
		.expectNext("adam")
		.verifyComplete();
	}
	
	@Test
	public void fluxUsingRange() {
		Flux<Integer> integerFlux = Flux.range(1,  5);
		
		StepVerifier.create(integerFlux)
		.expectNext(1, 2, 3, 4, 5)
		.verifyComplete();
	}
}
