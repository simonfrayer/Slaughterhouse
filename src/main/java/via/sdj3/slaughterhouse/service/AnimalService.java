package via.sdj3.slaughterhouse.service;

import via.sdj3.slaughterhouse.model.Animal;

public interface AnimalService
{
    Animal registerAnimal( double weight, int registrationNumber, String origin);
}
