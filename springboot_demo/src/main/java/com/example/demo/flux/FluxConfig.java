package com.example.demo.flux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author TMW
 * @date 2020/7/21 15:46
 */
@Configuration
public class FluxConfig {

    @Bean
    public RouterFunction<ServerResponse> fluxRouteFunction(FluxHandler fluxHandler) {
        return route(GET("/flux").and(accept(MediaType.TEXT_PLAIN)), fluxHandler::index);
    }

}
