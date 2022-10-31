package via.sdj3.slaughterhouse.RestServer.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import via.sdj3.slaughterhouse.protobuf.*;

import java.util.ArrayList;
import java.util.List;

public class TestClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8060)
                .usePlaintext()
                .build();

        SlaughterhouseServerGrpc.SlaughterhouseServerBlockingStub stub
                = SlaughterhouseServerGrpc.newBlockingStub(channel);

        Animal animal1 = stub.createAnimal(Animal.newBuilder().setDate("22.22.2222").setRegistrationNumber(123).setWeight(10).setOrigin("Farm").build());
        Animal animal2 = stub.createAnimal(Animal.newBuilder().setDate("22.22.2222").setRegistrationNumber(124).setWeight(15).setOrigin("Farm").build());
        List<Long> animalRegNumbers = new ArrayList<>();
        animalRegNumbers
                .add(animal1.getRegistrationNumber());
        animalRegNumbers.add(animal2.getRegistrationNumber());
        Product product = stub.createProduct(Product.newBuilder().setRegistrationNumber(1).addAllAnimalRegNumber(animalRegNumbers).build());

        AnimalsFromProduct animalsFromProduct = stub.getAllAnimalsFromProduct(ProductRegNumber.newBuilder().setRegistrationNumber(product.getRegistrationNumber()).build());

        System.out.println(animalsFromProduct.getAnimalRegistrationNumberList());
    }
}
