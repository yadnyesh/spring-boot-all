package io.yadnyesh.springbootall;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.relational.core.sql.In;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

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

		Mono<String> learningReactiveWebMono = Mono.just("Learning Reactive Web");
		Mono.just("Second string");
		Mono<String> m1 = Mono.just(" Learning Reactive Web  ");
		Mono<String> m2 = Mono.just(" Subscribe to Youtube channel  ");
		Mono<Integer> m3 = Mono.just(170681);

		Mono<Tuple3<String, String, Integer>> combinedMono = Mono.zip(m1, m2, m3);

		combinedMono.subscribe(data -> {System.out.println(data.getT3());
		});


//		learningReactiveWebMono.subscribe(data -> {
//			System.out.println("data is : " + data);
//		});
//		errorMono.subscribe(System.out::println);
	}

}
