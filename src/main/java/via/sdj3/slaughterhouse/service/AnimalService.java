package via.sdj3.slaughterhouse.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import via.sdj3.slaughterhouse.model.Animal;

@Service
public interface AnimalService
{
    Animal registerAnimal( double weight, long registrationNumber, String origin);
}
