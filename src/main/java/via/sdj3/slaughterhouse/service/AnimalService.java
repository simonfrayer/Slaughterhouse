package via.sdj3.slaughterhouse.service;

import via.sdj3.slaughterhouse.model.Animal;

import java.util.List;

public interface AnimalService
{
    Animal registerAnimal( double weight, long registrationNumber, String origin);
    List<Animal> getAnimals();
    Animal getByRegNumber(long regNumber);

    List<Animal> getAllFromDate(int year, int month, int day);
}
