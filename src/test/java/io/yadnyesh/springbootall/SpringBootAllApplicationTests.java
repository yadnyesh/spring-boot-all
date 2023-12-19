package io.yadnyesh.springbootall;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.relational.core.sql.In;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

import java.time.Duration;
import java.util.Arrays;
import java.util.Locale;

@SpringBootTest
class SpringBootAllApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	@Test
	public void test() {
		System.out.println("test started");
		Mono<String> monoPublisher = Mono.just("Testing");
		monoPublisher.subscribe(new CoreSubscriber<String>() {
			@Override
			public void onSubscribe(Subscription subscription) {
				System.out.println("Subscription Done Successfully" + subscription.toString());
				subscription.request(1);
			}

			@Override
			public void onNext(String data) {
				System.out.println("data : " + data);
			}

			@Override
			public void onError(Throwable throwable) {
				System.out.println("error : " + throwable.getMessage());
			}

			@Override
			public void onComplete() {
				System.out.println("Completed successfully");
			}
		});
	}

	@Test
	public void workingWithMono() {
		Mono<String> errorMono = Mono.error(new RuntimeException("User generated error!"));
		Mono<String> m1 = Mono.just("Learning coding with Yadnyesh");
		Mono<String> m2 = Mono.just("Subscribtion is required");
		Mono<Integer> m3 = Mono.just(170681);

		Mono<String> resultMono = m1.map(item -> item.toUpperCase(Locale.ROOT));
		resultMono.subscribe(System.out::println);

		Mono<String[]> resultFlatMapExample = m1.flatMap(valueM1 -> Mono.just(valueM1.split(" ")));
		resultFlatMapExample.subscribe(data -> {
			for (String s: data) {
				System.out.println(s);
			}
		});

		System.out.println("-------------------------------");

		Flux<String> stringFlux = m1.flatMapMany(valueM1 -> Flux.just(valueM1.split(" "))).log();
		stringFlux.subscribe(data -> {
			System.out.println(data);
		});
	}

	@Test
	public void anotherWorkingWithMono() throws InterruptedException {
		Mono<String> errorMono = Mono.error(new RuntimeException("User generated error!"));
		Mono<String> m1 = Mono.just("Learning coding with Yadnyesh");
		Mono<String> m2 = Mono.just("Subscribtion is required");
		Mono<Integer> m3 = Mono.just(170681);

		System.out.println(Thread.currentThread().getName());
		Flux<String> stringFlux = m1.concatWith(m2)
				.delayElements(Duration.ofMillis(2000))
				.log();
		stringFlux.subscribe(System.out::println);

		Thread.sleep(4400);
		System.out.println("------Terminated Main Thread---------");
	}

}
