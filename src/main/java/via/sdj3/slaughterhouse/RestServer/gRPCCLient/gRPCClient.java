package via.sdj3.slaughterhouse.RestServer.gRPCCLient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;
import via.sdj3.slaughterhouse.protobuf.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class gRPCClient implements ServerInterface{

    private static SlaughterhouseServerGrpc.SlaughterhouseServerBlockingStub stub;

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8060)
                .usePlaintext()
                .build();

     stub = SlaughterhouseServerGrpc.newBlockingStub(channel);

    }

    @Override public via.sdj3.slaughterhouse.model.Animal createAnimal(
        Animal animal)
    {
        Animal animalResponse = stub.createAnimal(animal);

        via.sdj3.slaughterhouse.model.Animal animalToDisplay = new via.sdj3.slaughterhouse.model.Animal(animalResponse.getDate(),
            animalResponse.getWeight(),animalResponse.getRegistrationNumber(),animalResponse.getOrigin());

        return animalToDisplay;
    }

    @Override public via.sdj3.slaughterhouse.model.Product createProduct(
        Product product)
    {
        return null;
    }

    @Override public List<Long> getAllAnimalsFromProduct(
        ProductRegNumber regNum)
    {
        AnimalsFromProduct response = stub.getAllAnimalsFromProduct(regNum);
        ArrayList<Long> animalsRegNumbers = new ArrayList<>();

        for (int i = 0; i < response.getAnimalRegistrationNumberCount(); i++)
        {
            animalsRegNumbers.add(response.getAnimalRegistrationNumber(i));
        }

        return animalsRegNumbers;
    }

    @Override public List<Long> getProductsFromAnimal(
        AnimalRegistrationNumber regNum)
    {
        return null;
    }
}
