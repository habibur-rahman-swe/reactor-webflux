package com.reactive.reactor.fluxandmonoplayground;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoFilterTest {

	List<String> names = Arrays.asList("Ad", "B", "AC", "D", "E");
	
	@Test
	public void filterTest() {
		Flux<String> namesFlux = Flux.fromIterable(names)
				.filter(s -> s.startsWith("A"))
				.log();
		
		StepVerifier.create(namesFlux).expectNext("Ad", "AC").verifyComplete();
	}
	
	@Test
	public void filterTestLength() {
		Flux<String> namesFlux = Flux.fromIterable(names)
				.filter(s -> s.length() > 1)
				.log();
		
		StepVerifier.create(namesFlux).expectNext("Ad", "AC").verifyComplete();
	}
}
