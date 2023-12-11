package io.yadnyesh.springbootall;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

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

}
