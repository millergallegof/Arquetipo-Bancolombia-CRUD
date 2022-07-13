package co.com.sofka.api;

import co.com.sofka.model.pet.Pet;
import co.com.sofka.model.pet.gateways.PetRepository;
import co.com.sofka.usecase.createpet.CreatePetUseCase;
import co.com.sofka.usecase.listarpet.ListarPetUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;

@Component
@RequiredArgsConstructor
public class Handler {
    private final PetRepository petRepository;
    private final CreatePetUseCase createPetUseCase;

    public Mono<ServerResponse> createPetPOSTUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Pet.class)
                .flatMap(element -> createPetUseCase.createPet(element)) //es un flujo alterno-proceso de almacenamiento retorna otro flujo ya que el metodo del usecase guarda
                .flatMap(element -> ServerResponse.ok() //es el flujo que combierte la respuesta anterior a un body
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(petRepository.save(element), Pet.class));
    }

    public Mono<ServerResponse> listarGETUseCase(ServerRequest serverRequest) {
//        Flux<Pet> pets = petRepository.findAll();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(petRepository.findAll(), Pet.class);
    }

    public Mono<ServerResponse> deleteGETUseCase(ServerRequest serverRequest) {
//        el requestparat lo saco del serverRequest
        var id = serverRequest.pathVariable("id");

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON) // expone la respuesta en formato json depende dle mediatype
                .body(petRepository.delete(id), Pet.class);
    }

    public Mono<ServerResponse> updatePOSTUseCase(ServerRequest serverRequest) {
//        el requestparat lo saco del serverRequest
        var id = serverRequest.pathVariable("id");

        return serverRequest.bodyToMono(Pet.class)
                .flatMap(element -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(petRepository.update(id, element), Pet.class));
    }
}
