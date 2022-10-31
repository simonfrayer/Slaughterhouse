package via.sdj3.slaughterhouse.RestServer.service;

import org.springframework.stereotype.Service;
import via.sdj3.slaughterhouse.model.Animal;
import via.sdj3.slaughterhouse.repository.AnimalRepository;

import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class AnimalServiceImpl
{
    private AnimalRepository database;

    public AnimalServiceImpl(AnimalRepository database) {
        this.database = database;
    }


    public void registerAnimal( double weight, long registrationNumber, String origin) {
        Date date = new Date();
        Timestamp todayDate = new Timestamp(date.getTime());

        Animal createAnimal = new Animal(todayDate, weight, registrationNumber, origin);

        //return database.registerAnimal(createAnimal);
    }

    public void getAnimals()
    {
       // return database.getAll();
        return ;
    }

    public void getByRegNumber(long regNumber)
    {
        try
        {
           // return database.getByRegNumber(regNumber);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public void getAllFromDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, day, 0, 0);
        Timestamp getDate = new Timestamp(c.getTime().getTime());

        //return database.getAllFromDate(getDate);
    }

  public void getByOrigin(String origin) throws Exception
    {
       // return database.getByOrigin(origin);
    }
}
