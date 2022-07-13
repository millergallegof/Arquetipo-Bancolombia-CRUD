package co.com.sofka.usecase.listarpet;

import co.com.sofka.model.pet.Pet;
import co.com.sofka.model.pet.gateways.PetRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ListarPetUseCase {
    private final PetRepository repository;

    public Flux<Pet> listarPet() {
        return repository.findAll();
    }
}
