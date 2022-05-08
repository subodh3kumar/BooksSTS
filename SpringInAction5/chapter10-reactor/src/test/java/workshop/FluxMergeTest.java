package workshop;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

@TestMethodOrder(OrderAnnotation.class)
public class FluxMergeTest {

	private static final Logger log = LoggerFactory.getLogger(FluxMergeTest.class);

	@Test
	@Order(1)
	@DisplayName("Flux.mergeWith() method")
	public void testMergeWith() {

		// TN - Tamil Nadu, BR - Bihar, KR - Karnataka
		Flux<String> stateFlux = Flux.just("TN", "BR", "KR")
				.delayElements(Duration.ofMillis(500));

		// CHN - Chennai, PAT - Patna, BLR - Bangalore
		Flux<String> capitalFlux = Flux.just("CHN", "PAT", "BLR")
				.delaySubscription(Duration.ofMillis(250))
				.delayElements(Duration.ofMillis(500));

		Flux<String> mergeFlux = stateFlux.mergeWith(capitalFlux);

		System.out.println();
		log.info("Flux.mergeWith() method...");
		mergeFlux.subscribe(data -> log.info(data));

		StepVerifier.create(mergeFlux)
			.expectNext("TN")
			.expectNext("CHN")
			.expectNext("BR")
			.expectNext("PAT")
			.expectNext("KR")
			.expectNext("BLR")
			.verifyComplete();
	}

	@Test
	@Order(2)
	@DisplayName("Flux.zip() method with Tuple")
	public void testFluxZipUsingTuple() {
		// TN - Tamil Nadu, BR - Bihar, KR - Karnataka
		Flux<String> stateFlux = Flux.just("TN", "BR", "KR");

		// CHN - Chennai, PAT - Patna, BLR - Bangalore
		Flux<String> capitalFlux = Flux.just("CHN", "PAT", "BLR");

		Flux<Tuple2<String, String>> zipFlux = Flux.zip(stateFlux, capitalFlux);

		System.out.println();
		log.info("Flux.zip() method with Tuple...");
		zipFlux.subscribe(data -> log.info(data.getT1() + "-" + data.getT2()));

		StepVerifier.create(zipFlux)
			.expectNextMatches(p -> p.getT1().equals("TN") && p.getT2().equals("CHN"))
			.expectNextMatches(p -> p.getT1().equals("BR") && p.getT2().equals("PAT"))
			.expectNextMatches(p -> p.getT1().equals("KR") && p.getT2().equals("BLR"))
			.verifyComplete();
	}

	@Test
	@Order(3)
	@DisplayName("Flux.zip() method without Tuple")
	public void testFluxZipWithoutTuple() {
		// TN - Tamil Nadu, BR - Bihar, KR - Karnataka
		Flux<String> stateFlux = Flux.just("TN", "BR", "KR");

		// CHN - Chennai, PAT - Patna, BLR - Bangalore
		Flux<String> capitalFlux = Flux.just("CHN", "PAT", "BLR");

		Flux<String> zipFlux = Flux.zip(stateFlux, capitalFlux, (state, capital) -> state + "-->" + capital);

		System.out.println();
		log.info("Flux.zip() method without Tuple...");
		zipFlux.subscribe(data -> log.info(data));

		StepVerifier.create(zipFlux)
			.expectNext("TN-->CHN")
			.expectNext("BR-->PAT")
			.expectNext("KR-->BLR")
			.verifyComplete();
	}

	@Test
	@Order(4)
	@DisplayName("Flux.first() method")
	public void testFluxFirst() {
		Flux<String> slowFlux = Flux.just("Tortoise", "Snail", "Sloth").delaySubscription(Duration.ofMillis(100));

		Flux<String> fastFlux = Flux.just("Hare", "Cheetah", "Squirrel");

		Flux<String> firstFlux = Flux.first(slowFlux, fastFlux);

		System.out.println();
		log.info("Flux.first() method...");
		firstFlux.subscribe(data -> log.info(data));

		StepVerifier.create(firstFlux)
			.expectNext("Hare")
			.expectNext("Cheetah")
			.expectNext("Squirrel")
			.verifyComplete();
	}
}
