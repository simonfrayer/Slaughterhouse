package via.sdj3.slaughterhouse.RestServer.service;

import org.springframework.stereotype.Service;
import via.sdj3.slaughterhouse.RestServer.gRPCCLient.ServerInterface;
import via.sdj3.slaughterhouse.model.Animal;
import via.sdj3.slaughterhouse.protobuf.AnimalRegistrationNumber;
import via.sdj3.slaughterhouse.protobuf.ProductRegNumber;
import via.sdj3.slaughterhouse.repository.AnimalRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService{

    private ServerInterface grpcServer;

    public AnimalServiceImpl(ServerInterface serverInterface) {
        this.grpcServer = serverInterface;
    }


    @Override public Animal createAnimal(double weight, String origin)
    {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MMM.yyyy");
        String formattedString = localDate.format(formatter);

        via.sdj3.slaughterhouse.protobuf.Animal animal = via.sdj3.slaughterhouse.protobuf.Animal.newBuilder()
            .setDate(formattedString)
            .setWeight(weight)
            .setRegistrationNumber(registrationNumber)
            .setOrigin(origin).build();

        return grpcServer.createAnimal(animal);
    }

    @Override public List<Animal> getAnimalsFromProduct(long productRegNumber)
    {
        return null;
    }

    @Override public Animal getAnimalById(long productRegNumber)
    {
        return null;
    }

    @Override public List<Animal> getAnimalsByOrigin(String origin)
    {
        return null;
    }

    @Override public List<Animal> getAnimalsByDate(LocalDate date)
    {
        return null;
    }
}
