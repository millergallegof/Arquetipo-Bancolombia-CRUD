package co.com.sofka.model.pet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
//@NoArgsConstructor
//@AllArgsConstructor
public class Pet {
    private String id;
    private String name;
    private String raza;

    public Pet(String id, String name, String raza) {
        this.id = id;
        this.name = name;
        this.raza = raza;
    }

    public Pet() {
    }
}
