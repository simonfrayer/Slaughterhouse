package via.sdj3.slaughterhouse.RestServer.gRPCCLient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.stereotype.Service;
import via.sdj3.slaughterhouse.model.Animal;
import via.sdj3.slaughterhouse.model.Product;
import via.sdj3.slaughterhouse.protobuf.*;

import java.util.ArrayList;
import java.util.List;

@GRpcService
public class gRPCClient implements ServerInterface{

    public static SlaughterhouseServerGrpc.SlaughterhouseServerBlockingStub stub;

    public gRPCClient()
    {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8060)
            .usePlaintext()
            .build();

        stub = SlaughterhouseServerGrpc.newBlockingStub(channel);
    }

    @Override public Animal createAnimal(AnimalToCreate animal)
    {
        via.sdj3.slaughterhouse.protobuf.Animal animalResponse = stub.createAnimal(animal);

        return new via.sdj3.slaughterhouse.model.Animal(animalResponse.getRegistrationNumber(),animalResponse.getDate(),
            animalResponse.getWeight(),animalResponse.getOrigin());
    }



    @Override public Product createProduct(ProductToCreate product)
    {
        via.sdj3.slaughterhouse.protobuf.Product productResponse = stub.createProduct(product);
        List<Long> regNumbers = new ArrayList<>();
        for (int i = 0; i < productResponse.getAnimalsCount(); i++)
        {
            regNumbers.add(productResponse.getAnimals(i).getAnimalRegistrationNumber());
        }
        return new via.sdj3.slaughterhouse.model.Product(productResponse.getRegistrationNumber(), regNumbers);

    }

    @Override public List<Animal> getAllAnimalsFromProduct(
        ProductRegNumber regNum)
    {
        AnimalsFromProduct response = stub.getAllAnimalsFromProduct(regNum);
        ArrayList<Animal> animals = new ArrayList<>();

        for (int i = 0; i < response.getAnimalsCount(); i++)
        {
            animals.add(new Animal(response.getAnimals(i).getRegistrationNumber(),response.getAnimals(i).getDate(),response.getAnimals(i).getWeight()
            ,response.getAnimals(i).getOrigin()));
        }

        return animals;
    }

    @Override public List<Product> getProductsFromAnimal(
        AnimalRegistrationNumber regNum)
    {
        ProductsFromAnimal response = stub.getAllProductFromAnimal(regNum);

        ArrayList<Product> products = new ArrayList<>();

        for (int i = 0; i < response.getProductsCount(); i++)
        {
            List<Long> regNumbers = new ArrayList<>();
            for (int j = 0; i < response.getProducts(i).getAnimalsCount(); i++)
            {
                regNumbers.add(response.getProducts(i).getAnimals(j).getAnimalRegistrationNumber());
            }
            products.add(new Product(response.getProducts(i).getRegistrationNumber(),regNumbers));

        }
        return products;
    }

    @Override public Animal getAnimalById(AnimalRegistrationNumber regNum)
    {
        via.sdj3.slaughterhouse.protobuf.Animal animalResponse = stub.getAnimalById(regNum);
        return new via.sdj3.slaughterhouse.model.Animal(animalResponse.getRegistrationNumber(),animalResponse.getDate(),
            animalResponse.getWeight(),animalResponse.getOrigin());
    }

    @Override public List<Animal> getAnimalsByOrigin(Origin origin)
    {
       AnimalsFromProduct response = stub.getAnimalsByOrigin(origin);

        ArrayList<Animal> animals = new ArrayList<>();

        for (int i = 0; i < response.getAnimalsCount(); i++)
        {
            animals.add(new Animal(response.getAnimals(i).getRegistrationNumber(),response.getAnimals(i).getDate(),response.getAnimals(i).getWeight()
                ,response.getAnimals(i).getOrigin()));
        }

        return animals;
    }

    @Override public List<Animal> getAnimalsByDate(Date date)
    {
        AnimalsFromProduct response = stub.getAnimalsByDate(date);

        ArrayList<Animal> animals = new ArrayList<>();

        for (int i = 0; i < response.getAnimalsCount(); i++)
        {
            animals.add(new Animal(response.getAnimals(i).getRegistrationNumber(),response.getAnimals(i).getDate(),response.getAnimals(i).getWeight()
                ,response.getAnimals(i).getOrigin()));
        }

        return animals;
    }
}
