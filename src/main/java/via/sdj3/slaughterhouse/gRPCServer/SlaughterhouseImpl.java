package via.sdj3.slaughterhouse.gRPCServer;

import io.grpc.stub.StreamObserver;
import via.sdj3.slaughterhouse.protobuf.*;
import via.sdj3.slaughterhouse.repository.AnimalRepository;

public class SlaughterhouseImpl extends SlaughterhouseServerGrpc.SlaughterhouseServerImplBase
{

  private AnimalRepository repository;

  public SlaughterhouseImpl(AnimalRepository repository)
  {
    this.repository = repository;
  }
  @Override public void createAnimal(Animal request,
      StreamObserver<Animal> responseObserver)
  {
    Animal animal = Animal.newBuilder()
        .setDate(request.getDate())
        .setWeight(request.getWeight())
        .setRegistrationNumber(request.getRegistrationNumber())
        .setOrigin(request.getOrigin()).build();

    repository.registerAnimal(animal);
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

    repository.registerProduct(product);
    responseObserver.onNext(product);
    responseObserver.onCompleted();
  }

  @Override public void getAllAnimalsFromProduct(ProductRegNumber request,
      StreamObserver<AnimalsFromProduct> responseObserver)
  {
    ProductRegNumber productRegNumber = ProductRegNumber.newBuilder()
            .setRegistrationNumber(request.getRegistrationNumber()).build();

    AnimalsFromProduct animalsFromProduct = AnimalsFromProduct.newBuilder()
                    .addAllAnimalRegistrationNumber(repository.getAllAnimalRegNumberFromProduct(productRegNumber.getRegistrationNumber()))
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
            .addAllProductRegistrationNumber(repository.getAllProductRegNumFromAnimal(animalRegistrationNumber.getAnimalRegistrationNumber()))
            .build();

    responseObserver.onNext(productsFromAnimal);
    responseObserver.onCompleted();
  }


}
