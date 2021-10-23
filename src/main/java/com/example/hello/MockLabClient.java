package com.example.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.function.Function;
import java.util.function.Predicate;

@Component
public class MockLabClient {
    private final WebClient client;

    // Spring Boot auto-configures a `WebClient.Builder` instance with nice defaults and customizations.
    // We can use it to create a dedicated `WebClient` for our component.
    public MockLabClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("http://0gdzy.mocklab.io").build();
    }

    public Mono<String> getSlow5() {
        return this.client.get().uri("/slow5")
                .retrieve()
                .toBodilessEntity()
                .map(voidResponseEntity -> {
                    System.out.println("response from mocklab = " + voidResponseEntity.getStatusCode());
                    return "ok";
                });
    }

}
