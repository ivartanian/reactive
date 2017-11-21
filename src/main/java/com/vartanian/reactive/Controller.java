package com.vartanian.reactive;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class Controller {

    @GetMapping("/hello1")
    public String handle1() {
        return "Hello WebFlux";
    }

    @GetMapping("/hello2")
    public Mono<String> handle2() {
        return Mono.just("Hello WebFlux");
    }

}
