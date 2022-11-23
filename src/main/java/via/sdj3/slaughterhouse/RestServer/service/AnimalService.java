package via.sdj3.slaughterhouse.RestServer.service;

import via.sdj3.slaughterhouse.model.Animal;

import java.time.LocalDate;
import java.util.List;

public interface AnimalService {
    Animal createAnimal(double weight,String origin);
    List<Animal> getAnimalsFromProduct(long productRegNumber);
    Animal getAnimalById(long productRegNumber);
    List<Animal> getAnimalsByOrigin(String origin);
    List<Animal> getAnimalsByDate(LocalDate date);

}
