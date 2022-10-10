package via.sdj3.slaughterhouse.repository;

import via.sdj3.slaughterhouse.model.Animal;

import java.sql.Timestamp;
import java.util.List;

public interface AnimalRepository
{
    Animal registerAnimal(Animal animal);
    List<Animal> getAll();
    Animal getByRegNumber(long regNumber) throws Exception;

    List<Animal> getAllFromDate(Timestamp date);
}
