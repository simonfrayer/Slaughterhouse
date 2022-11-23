package via.sdj3.slaughterhouse.RestServer.service;

import via.sdj3.slaughterhouse.model.Animal;

import java.util.List;

public interface AnimalService {
    Animal createAnimal(double weight, long registrationNumber, String origin);
    List<Long> getProductsFromAnimal(long animalRegNumber);

}
