package via.sdj3.slaughterhouse.RestServer.service;

import org.springframework.stereotype.Service;
import via.sdj3.slaughterhouse.RestServer.gRPCCLient.ServerInterface;
import via.sdj3.slaughterhouse.model.Product;
import via.sdj3.slaughterhouse.protobuf.ProductRegNumber;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private ServerInterface grpcServer;

    public ProductServiceImpl(ServerInterface serverInterface){
        this.grpcServer = serverInterface;
    }
    @Override public Product createProduct(List<Long> animalRegNumbers)
    {
        return null;
    }

    @Override public List<Product> getProductsFromAnimal(long animalRegNumber)
    {
        via.sdj3.slaughterhouse.protobuf.Product product = via.sdj3.slaughterhouse.protobuf.Product.newBuilder()
            .setRegistrationNumber(regNumber)
            .addAllAnimalRegNumber(animalRegNumbers).build();

        return grpcServer.createProduct(product);
    }
}
