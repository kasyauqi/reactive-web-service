package com.example.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class GreetingHandler {

  private final MockLabClient mockLabClient;

  public GreetingHandler(MockLabClient mockLabClient) {
    this.mockLabClient = mockLabClient;
  }

  public Mono<ServerResponse> hello(ServerRequest request) {
    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
      .body(BodyInserters.fromValue(new Greeting("Hello, Spring!")));
  }

  public Mono<ServerResponse> slow(ServerRequest request) {
    return mockLabClient.getSlow5().flatMap(s -> ServerResponse.ok().build());
  }
}
