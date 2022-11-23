package via.sdj3.slaughterhouse.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PRODUCTS", schema = "slaughterhouse")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long regNumber;
    @ElementCollection
    @CollectionTable(name = "productAnimal", schema = "slaughterhouse", joinColumns = @JoinColumn(name = "animalId"))
    private List<Long> animals;

    public Product(long regNumber, List<Long> animals) {
        this.regNumber = regNumber;
        this.animals = animals;
    }

    public Product(){}

    public long getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(long regNumber) {
        this.regNumber = regNumber;
    }

    public List<Long> getAnimalRegNumbers() {
        return animals;
    }

    public void setAnimalRegNumbers(List<Long> animalRegNumbers) {
        this.animals = animalRegNumbers;
    }
}
