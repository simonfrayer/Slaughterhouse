package via.sdj3.slaughterhouse.RestServer.service;

import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import via.sdj3.slaughterhouse.RestServer.gRPCCLient.ServerInterface;
import via.sdj3.slaughterhouse.gRPCServer.SlaughterhouseImpl;
import via.sdj3.slaughterhouse.model.Animal;
import via.sdj3.slaughterhouse.protobuf.AnimalRegistrationNumber;
import via.sdj3.slaughterhouse.protobuf.Date;
import via.sdj3.slaughterhouse.protobuf.Origin;
import via.sdj3.slaughterhouse.protobuf.ProductRegNumber;
import via.sdj3.slaughterhouse.repository.AnimalRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService{

    private ServerInterface grpcServer;

    public AnimalServiceImpl(ServerInterface grpcServer) {
        this.grpcServer=grpcServer;
    }


    @Override public Animal createAnimal(double weight, String origin)
    {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MMM.yyyy");
        String formattedString = localDate.format(formatter);

        via.sdj3.slaughterhouse.protobuf.AnimalToCreate animal = via.sdj3.slaughterhouse.protobuf.AnimalToCreate.newBuilder()
            .setDate(formattedString)
            .setWeight(weight)
            .setOrigin(origin).build();

        return grpcServer.createAnimal(animal);
    }

    @Override public List<Animal> getAnimalsFromProduct(long productRegNumber)
    {
        ProductRegNumber regNumber = ProductRegNumber.newBuilder().setRegistrationNumber(productRegNumber).build();

        return grpcServer.getAllAnimalsFromProduct(regNumber);
    }

    @Override public Animal getAnimalById(long productRegNumber)
    {
        AnimalRegistrationNumber regNumber = AnimalRegistrationNumber.newBuilder().setAnimalRegistrationNumber(productRegNumber).build();

        return grpcServer.getAnimalById(regNumber);
    }

    @Override public List<Animal> getAnimalsByOrigin(String origin)
    {
        Origin animalOrigin = Origin.newBuilder().setOrigin(origin).build();

        return grpcServer.getAnimalsByOrigin(animalOrigin);
    }

    @Override public List<Animal> getAnimalsByDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MMM.yyyy");
        String formattedString = date.format(formatter);

        Date dateToFind = Date.newBuilder().setLocaldate(formattedString).build();

        return grpcServer.getAnimalsByDate(dateToFind);
    }
}
