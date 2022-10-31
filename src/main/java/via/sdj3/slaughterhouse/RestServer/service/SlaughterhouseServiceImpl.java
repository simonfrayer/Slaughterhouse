package via.sdj3.slaughterhouse.RestServer.service;

import org.springframework.stereotype.Service;
import via.sdj3.slaughterhouse.RestServer.gRPCCLient.ServerInterface;
import via.sdj3.slaughterhouse.model.Animal;
import via.sdj3.slaughterhouse.model.Product;
import via.sdj3.slaughterhouse.protobuf.AnimalRegistrationNumber;
import via.sdj3.slaughterhouse.protobuf.ProductRegNumber;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SlaughterhouseServiceImpl implements SlaughterhouseService
{

    private ServerInterface grpcServer;

    public SlaughterhouseServiceImpl(ServerInterface serverInterface)
    {
        this.grpcServer = serverInterface;
    }
    @Override
    public Animal createAnimal(double weight, long registrationNumber, String origin) {

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

    @Override
    public Product createProduct(long regNumber, List<Long> animalRegNumbers) {
        via.sdj3.slaughterhouse.protobuf.Product product = via.sdj3.slaughterhouse.protobuf.Product.newBuilder()
            .setRegistrationNumber(regNumber)
            .addAllAnimalRegNumber(animalRegNumbers).build();

        return grpcServer.createProduct(product);
    }

    @Override
    public List<Long> getAnimalsFromProduct(long productRegNumber) {
        ProductRegNumber regNumber = ProductRegNumber.newBuilder()
            .setRegistrationNumber(productRegNumber).build();

        return grpcServer.getAllAnimalsFromProduct(regNumber);
    }

    @Override
    public List<Long> getProductsFromAnimal(long animalRegNumber) {
        AnimalRegistrationNumber regNumber = AnimalRegistrationNumber.newBuilder()
            .setAnimalRegistrationNumber(animalRegNumber).build();

        return grpcServer.getProductsFromAnimal(regNumber);
    }
}
