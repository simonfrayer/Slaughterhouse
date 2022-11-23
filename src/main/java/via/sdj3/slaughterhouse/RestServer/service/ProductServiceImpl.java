package via.sdj3.slaughterhouse.RestServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    @Autowired
    public ProductServiceImpl(){
    }
    @Override public Product createProduct(List<Long> animalRegNumbers)
    {
        List<AnimalRegistrationNumber> list = new ArrayList<>();
        for (int i = 0; i < animalRegNumbers.size(); i++)
        {
            AnimalRegistrationNumber regNum = AnimalRegistrationNumber.newBuilder().setAnimalRegistrationNumber(animalRegNumbers.get(i)).build();
            list.add(regNum);
        }
        ProductToCreate productToCreate = ProductToCreate.newBuilder().addAllAnimals(list).build();
        return grpcServer.createProduct(productToCreate);
    }

    @Override public List<Product> getProductsFromAnimal(long animalRegNumber)
    {
        AnimalRegistrationNumber regNumber = AnimalRegistrationNumber.newBuilder().setAnimalRegistrationNumber(animalRegNumber).build();
        return grpcServer.getProductsFromAnimal(regNumber);
    }
}
