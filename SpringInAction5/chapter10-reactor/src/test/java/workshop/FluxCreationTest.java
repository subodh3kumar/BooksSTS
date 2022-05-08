package workshop;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@TestMethodOrder(OrderAnnotation.class)
public class FluxCreationTest {

	private static final Logger log = LoggerFactory.getLogger(FluxCreationTest.class);

	@Test
	@Order(1)
	@DisplayName("Flux.just() method")
	public void testFluxJust() {
		Flux<String> fruitFlux = Flux.just("Apple", "Grape", "Orange", "Banana", "Mango");

		System.out.println();
		log.info("Flux.just() method...");
		fruitFlux.subscribe(data -> log.info(data));

		StepVerifier.create(fruitFlux)
			.expectNext("Apple")
			.expectNext("Grape")
			.expectNext("Orange")
			.expectNext("Banana")
			.expectNext("Mango")
			.verifyComplete();
	}

	@Test
	@Order(2)
	@DisplayName("Flux.fromArray() method")
	public void testFluxFromArray() {
		String[] fruits = new String[] { "Apple", "Grape", "Orange", "Banana", "Mango" };
		Flux<String> fruitFlux = Flux.fromArray(fruits);

		System.out.println();
		log.info("Flux.fromArray() method...");
		fruitFlux.subscribe(data -> log.info(data));

		StepVerifier.create(fruitFlux)
			.expectNext("Apple")
			.expectNext("Grape")
			.expectNext("Orange")
			.expectNext("Banana")
			.expectNext("Mango")
			.verifyComplete();
	}

	@Test
	@Order(3)
	@DisplayName("Flux.fromIterable() method")
	public void testFluxFromIterable() {
		List<String> fruits = new ArrayList<>();
		fruits.add("Apple");
		fruits.add("Grape");
		fruits.add("Orange");
		fruits.add("Banana");
		fruits.add("Mango");

		Flux<String> fruitFlux = Flux.fromIterable(fruits);

		System.out.println();
		log.info("Flux.fromIterable() method...");
		fruitFlux.subscribe(data -> log.info(data));

		StepVerifier.create(fruitFlux)
			.expectNext("Apple")
			.expectNext("Grape")
			.expectNext("Orange")
			.expectNext("Banana")
			.expectNext("Mango")
			.verifyComplete();
	}

	@Test
	@Order(4)
	@DisplayName("Flux.fromStream() method")
	public void testFluxFromStream() {
		Stream<String> fruits = Stream.of("Apple", "Grape", "Orange", "Banana", "Mango");
		Stream<String> fruits2 = Stream.of("Apple", "Grape", "Orange", "Banana", "Mango");

		Flux<String> fruitFlux = Flux.fromStream(fruits);
		Flux<String> fruitFlux2 = Flux.fromStream(fruits2);

		System.out.println();
		log.info("Flux.fromStream() method...");
		fruitFlux.subscribe(data -> log.info(data));

		StepVerifier.create(fruitFlux2)
			.expectNext("Apple")
			.expectNext("Grape")
			.expectNext("Orange")
			.expectNext("Banana")
			.expectNext("Mango")
			.verifyComplete();
	}

	@Test
	@Order(5)
	@DisplayName("Flux.interval() method")
	public void testFluxInterval() {
		Flux<Long> fluxInterval = Flux.interval(Duration.ofSeconds(1)).take(5);

		System.out.println();
		log.info("Flux.interval() method...");
		fluxInterval.subscribe(data -> log.info(Long.toString(data)));

		StepVerifier.create(fluxInterval)
			.expectNext(0L)
			.expectNext(1L)
			.expectNext(2L)
			.expectNext(3L)
			.expectNext(4L)
			.verifyComplete();
	}

	@Test
	@Order(6)
	@DisplayName("Flux.range() method")
	public void testFluxRange() {
		Flux<Integer> fluxRange = Flux.range(1, 5);

		System.out.println();
		log.info("Flux.range() method...");
		fluxRange.subscribe(data -> log.info(Integer.toString(data)));

		StepVerifier.create(fluxRange)
			.expectNext(1)
			.expectNext(2)
			.expectNext(3)
			.expectNext(4)
			.expectNext(5)
			.verifyComplete();
	}
}
