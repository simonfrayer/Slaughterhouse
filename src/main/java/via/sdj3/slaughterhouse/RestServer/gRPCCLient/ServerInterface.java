package via.sdj3.slaughterhouse.RestServer.gRPCCLient;

import org.springframework.stereotype.Service;
import via.sdj3.slaughterhouse.model.Animal;
import via.sdj3.slaughterhouse.model.Product;
import via.sdj3.slaughterhouse.protobuf.AnimalRegistrationNumber;
import via.sdj3.slaughterhouse.protobuf.ProductRegNumber;

import java.util.List;

public interface ServerInterface
{
  Animal createAnimal(via.sdj3.slaughterhouse.protobuf.Animal animal);

  Product createProduct(via.sdj3.slaughterhouse.protobuf.Product product);

  List<Long> getAllAnimalsFromProduct(ProductRegNumber regNum);

  List<Long> getProductsFromAnimal(AnimalRegistrationNumber regNum);
}
