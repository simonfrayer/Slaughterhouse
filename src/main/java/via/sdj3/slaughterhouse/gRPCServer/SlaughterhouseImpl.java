package via.sdj3.slaughterhouse.gRPCServer;

import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import via.sdj3.slaughterhouse.protobuf.*;
import via.sdj3.slaughterhouse.repository.AnimalRepository;
import via.sdj3.slaughterhouse.repository.ProductRepository;

@Service
public class SlaughterhouseImpl extends SlaughterhouseServerGrpc.SlaughterhouseServerImplBase
{


  private AnimalRepository animalRepository;

  private ProductRepository productRepository;



  public SlaughterhouseImpl()
  {
  }
  /*
  @Override public void createAnimal(Animal request,
      StreamObserver<Animal> responseObserver)
  {
    Animal animal = Animal.newBuilder()
        .setDate(request.getDate())
        .setWeight(request.getWeight())
        .setRegistrationNumber(request.getRegistrationNumber())
        .setOrigin(request.getOrigin()).build();

    animalRepository.save(animal);
    responseObserver.onNext(animal);
    responseObserver.onCompleted();
  }

  @Override public void createProduct(Product request,
      StreamObserver<Product> responseObserver)
  {
    Product product = Product.newBuilder()
        .setRegistrationNumber(request.getRegistrationNumber())
        .addAllAnimalRegNumber(request.getAnimalRegNumberList())
        .build();

    productRepository.save(product);
    responseObserver.onNext(product);
    responseObserver.onCompleted();
  }

  @Override public void getAllAnimalsFromProduct(ProductRegNumber request,
      StreamObserver<AnimalsFromProduct> responseObserver)
  {
    ProductRegNumber productRegNumber = ProductRegNumber.newBuilder()
            .setRegistrationNumber(request.getRegistrationNumber()).build();


    AnimalsFromProduct animalsFromProduct = AnimalsFromProduct.newBuilder()
                    .addAllAnimalRegistrationNumber((productRepository.findAll()))
            .build();

    responseObserver.onNext(animalsFromProduct);
    responseObserver.onCompleted();

  }

  @Override
  public void getAllProductFromAnimal(AnimalRegistrationNumber request, StreamObserver<ProductsFromAnimal> responseObserver) {
    AnimalRegistrationNumber animalRegistrationNumber = AnimalRegistrationNumber.newBuilder()
            .setAnimalRegistrationNumber(request.getAnimalRegistrationNumber())
            .build();

    ProductsFromAnimal productsFromAnimal = ProductsFromAnimal.newBuilder()
            .addAllProductRegistrationNumber((animalRepository.findAll()))
            .build();


    responseObserver.onNext(productsFromAnimal);
    responseObserver.onCompleted();
  }

*/
}
