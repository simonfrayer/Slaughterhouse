package via.sdj3.slaughterhouse.repository;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import org.springframework.stereotype.Repository;
import via.sdj3.slaughterhouse.model.Animal;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

  @Override
  public List<Animal> getByOrigin(String origin) throws Exception
  {
    List<Animal> animals = new ArrayList<>();

    for (Map.Entry<Long, Animal> animal : animalMap.entrySet())
    {
      if (animal.getValue().getOrigin().equalsIgnoreCase(origin))
      {
        animals.add(animal.getValue());
      }
    }

    if (animals.isEmpty())
    {
      throw new Exception("No animal was found");
    }
    return animals;
  }

  @Override
  public Animal getByRegNumber(long regNumber) throws Exception {
    if (!animalMap.containsKey(regNumber))
      throw new Exception("Not registered");


    return animalMap.get(regNumber);
  }

  @Override
  public List<Animal> getAllFromDate(Timestamp date) {
    List<Animal> animalsToReturn = new ArrayList<>();
    String requiredDate = new SimpleDateFormat("yyyy.MM.dd").format(date);

    for (Animal animal:animalMap.values()
         ) {
      String animalDate = new SimpleDateFormat("yyyy.MM.dd").format(animal.getDate());

      if (animalDate.equals(requiredDate)){
        animalsToReturn.add(animal);
      }
    }
    return animalsToReturn;
  }

}
