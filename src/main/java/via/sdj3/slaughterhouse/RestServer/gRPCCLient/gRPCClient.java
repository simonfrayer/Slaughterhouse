package via.sdj3.slaughterhouse.RestServer.gRPCCLient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;
import via.sdj3.slaughterhouse.protobuf.*;

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
        return null;
    }

    @Override public via.sdj3.slaughterhouse.model.Product createProduct(
        Product product)
    {
        Product responseProduct = stub.createProduct(product);

        return new via.sdj3.slaughterhouse.model.Product(responseProduct.getRegistrationNumber(), responseProduct.getAnimalRegNumberList());
    }

    @Override public List<Long> getAllAnimalsFromProduct(
        ProductRegNumber regNum)
    {
        return null;
    }

    @Override public List<Long> getProductsFromAnimal(
        AnimalRegistrationNumber regNum)
    {
        ProductsFromAnimal productRegNumbers = stub.getAllProductFromAnimal(regNum);

        return productRegNumbers.getProductRegistrationNumberList();
    }
}
