package via.sdj3.slaughterhouse.RestServer.gRPCCLient;

import org.springframework.stereotype.Service;
import via.sdj3.slaughterhouse.model.Animal;
import via.sdj3.slaughterhouse.model.Product;
import via.sdj3.slaughterhouse.protobuf.AnimalRegistrationNumber;
import via.sdj3.slaughterhouse.protobuf.Date;
import via.sdj3.slaughterhouse.protobuf.Origin;
import via.sdj3.slaughterhouse.protobuf.ProductRegNumber;

import java.time.LocalDate;
import java.util.List;

public interface ServerInterface
{
  Animal createAnimal(via.sdj3.slaughterhouse.protobuf.AnimalToCreate animal);

  Product createProduct(via.sdj3.slaughterhouse.protobuf.ProductToCreate product);

  List<Animal> getAllAnimalsFromProduct(ProductRegNumber regNum);

  List<Product> getProductsFromAnimal(AnimalRegistrationNumber regNum);

  Animal getAnimalById(AnimalRegistrationNumber regNum);
  List<Animal> getAnimalsByOrigin(Origin origin);
  List<Animal> getAnimalsByDate(Date date);
}
