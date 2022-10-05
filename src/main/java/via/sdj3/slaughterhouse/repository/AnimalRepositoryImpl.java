package via.sdj3.slaughterhouse.repository;

import org.springframework.stereotype.Repository;
import via.sdj3.slaughterhouse.model.Animal;

import java.sql.Timestamp;
import java.util.*;

@Repository
public class AnimalRepositoryImpl implements AnimalRepository
{

  private static final Map<Long, Animal> animalMap = new HashMap<>();

  public AnimalRepositoryImpl() {
    Animal animal = new Animal(new Timestamp(new Date().getTime()), 60, 1, "Farm1");

    animalMap.put(1L,animal);
  }

  @Override public Animal registerAnimal(Animal animal)
  {
    animalMap.put(animal.getRegistrationNumber(),animal);
    return animal;
  }

  @Override
  public List<Animal> getAll() {
    Collection<Animal> conversion = animalMap.values();
    List<Animal> animalList = new ArrayList<>();
    animalList.addAll(conversion);

    return animalList;
  }

}
