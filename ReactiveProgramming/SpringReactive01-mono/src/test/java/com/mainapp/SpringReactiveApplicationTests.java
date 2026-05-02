package com.mainapp;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

@SpringBootTest
class SpringReactiveApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test() {
        System.out.println("test started ...");
        
        //create mono
        Mono<String> m = Mono.just("testing");
        
        //consume mono
        m.subscribe(new CoreSubscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("Subscription done");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String data) {
                System.out.println("data: " + data);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Completed");
            }
        });
    }
}
