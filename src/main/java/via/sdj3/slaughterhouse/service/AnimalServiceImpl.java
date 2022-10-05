package via.sdj3.slaughterhouse.service;

import via.sdj3.slaughterhouse.model.Animal;
import via.sdj3.slaughterhouse.repository.AnimalRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;

public class AnimalServiceImpl implements AnimalService
{
    private AnimalRepository database;

    public AnimalServiceImpl(AnimalRepository database) {
        this.database = database;
    }

    @Override
    public Animal registerAnimal( double weight, int registrationNumber, String origin) {
        Date date = new Date();
        Timestamp todayDate = new Timestamp(date.getTime());

        Animal createAnimal = new Animal(todayDate, weight, registrationNumber, origin);

        return database.registerAnimal(createAnimal);
    }
}
