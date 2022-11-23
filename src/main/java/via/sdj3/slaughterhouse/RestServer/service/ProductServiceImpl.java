package via.sdj3.slaughterhouse.RestServer.service;

import org.springframework.stereotype.Service;
import via.sdj3.slaughterhouse.RestServer.gRPCCLient.ServerInterface;
import via.sdj3.slaughterhouse.model.Product;
import via.sdj3.slaughterhouse.protobuf.AnimalRegistrationNumber;
import via.sdj3.slaughterhouse.protobuf.ProductRegNumber;
import via.sdj3.slaughterhouse.protobuf.ProductToCreate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private ServerInterface grpcServer;

    public ProductServiceImpl(ServerInterface serverInterface){
        this.grpcServer = serverInterface;
    }
    @Override public Product createProduct(List<Long> animalRegNumbers)
    {
        //ProductToCreate productToCreate = ProductToCreate.newBuilder().setAnimals(animalRegNumbers).build();
       // return grpcServer.createProduct(productToCreate);
        return new Product();
    }

    @Override public List<Product> getProductsFromAnimal(long animalRegNumber)
    {
        AnimalRegistrationNumber regNumber = AnimalRegistrationNumber.newBuilder().setAnimalRegistrationNumber(animalRegNumber).build();
        //return grpcServer.getProductsFromAnimal(AnimalRegistrationNumber);
        return new ArrayList<>();
    }
}
