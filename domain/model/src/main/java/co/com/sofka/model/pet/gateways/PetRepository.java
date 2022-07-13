package co.com.sofka.model.pet.gateways;

import co.com.sofka.model.pet.Pet;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PetRepository {
    public Mono<Pet> save(Pet pet);
    public Flux<Pet> findAll();
    public Mono<Void> delete(String id);

    Mono<Pet> update(String id, Pet pet);

}
