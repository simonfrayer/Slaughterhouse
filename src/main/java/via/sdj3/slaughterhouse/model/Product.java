package via.sdj3.slaughterhouse.model;

import java.util.List;

public class Product {
    private long regNumber;
    private List<Long> animalRegNumbers;

    public Product(long regNumber, List<Long> animalRegNumbers) {
        this.regNumber = regNumber;
        this.animalRegNumbers = animalRegNumbers;
    }

    public long getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(long regNumber) {
        this.regNumber = regNumber;
    }

    public List<Long> getAnimalRegNumbers() {
        return animalRegNumbers;
    }

    public void setAnimalRegNumbers(List<Long> animalRegNumbers) {
        this.animalRegNumbers = animalRegNumbers;
    }
}
