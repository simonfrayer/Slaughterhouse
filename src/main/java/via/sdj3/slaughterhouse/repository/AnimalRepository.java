package via.sdj3.slaughterhouse.repository;

import via.sdj3.slaughterhouse.protobuf.Animal;
import via.sdj3.slaughterhouse.protobuf.Product;

import java.sql.Timestamp;
import java.util.List;

public interface AnimalRepository
{
    Animal registerAnimal(Animal animal);
    List<Long> getAllAnimalRegNumberFromProduct(long productRegNum);

    Product registerProduct(Product product);

    List<Long> getAllProductRegNumFromAnimal (long animalRegNum);

}
