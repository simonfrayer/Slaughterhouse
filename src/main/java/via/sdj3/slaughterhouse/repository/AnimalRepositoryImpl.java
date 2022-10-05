package via.sdj3.slaughterhouse.repository;

import org.springframework.stereotype.Repository;
import via.sdj3.slaughterhouse.model.Animal;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AnimalRepositoryImpl implements AnimalRepository
{

  private static final Map<Long, Animal> animalMap = new HashMap<>();

  @Override public Animal registerAnimal(Animal animal)
  {
    animalMap.put(animal.getRegistrationNumber(),animal);
    return animal;
  }

}
