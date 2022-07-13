package co.com.sofka.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(POST("/api/usecase/pet"), handler::createPetPOSTUseCase)
                .and(route(GET("/api/pet"), handler::listarGETUseCase))
                .and(route(DELETE("/api/pet/{id}"), handler::deleteGETUseCase))
                .and(route(POST("/api/pet/{id}"), handler::updatePOSTUseCase));
//    .and(route(GET("/api/otherusercase/path"), handler::createPetPOSTUseCase))
//    .andRoute(GET("/api/usecase/path"), handler::createPetPOSTUseCase)
    }
}
