package co.com.sofka.mongo;

import co.com.sofka.model.pet.Pet;
import co.com.sofka.model.pet.gateways.PetRepository;
import co.com.sofka.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class MongoRepositoryAdapter extends AdapterOperations<Pet, PetDocument, String, MongoDBRepository>
        implements PetRepository {

    public MongoRepositoryAdapter(MongoDBRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Pet.class));
    }


    @Override
    public Mono<Void> delete(String id) {
//        repository.deleteById(id).;
//        var pet = repository.findById(id)
//                .flatMap(element -> {
//                    repository.deleteById(element.getId());
//                    return Mono.just(new Pet(element.getId(), element.getName(), element.getRaza()));
//                });
        return repository.deleteById(id);
    }

    @Override
    public Mono<Pet> update(String id, Pet pet) {
        pet.setId(id);

        return repository
                .save(new PetDocument(pet.getId(), pet.getName(), pet.getRaza()))
                .flatMap(element -> Mono.just(pet));
    }
}
