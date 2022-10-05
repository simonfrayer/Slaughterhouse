package via.sdj3.slaughterhouse.service;

import org.springframework.stereotype.Service;
import via.sdj3.slaughterhouse.model.Animal;
import via.sdj3.slaughterhouse.repository.AnimalRepository;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService
{
    private AnimalRepository database;

    public AnimalServiceImpl(AnimalRepository database) {
        this.database = database;
    }

    @Override
    public Animal registerAnimal( double weight, long registrationNumber, String origin) {
        Date date = new Date();
        Timestamp todayDate = new Timestamp(date.getTime());

        Animal createAnimal = new Animal(todayDate, weight, registrationNumber, origin);

        return database.registerAnimal(createAnimal);
    }

    @Override public List<Animal> getAnimals()
    {
        return database.getAll();
    }

    @Override public Animal getByRegNumber(long regNumber)
    {
        return database.getByRegNumber(regNumber);
    }
}
