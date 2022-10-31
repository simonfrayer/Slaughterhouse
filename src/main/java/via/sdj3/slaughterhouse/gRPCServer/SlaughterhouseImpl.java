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

  @Override public void getAllAnimalsFromProduct(ProductRegNumber request,
      StreamObserver<AnimalsFromProduct> responseObserver)
  {
    super.getAllAnimalsFromProduct(request, responseObserver);
  }

  @Override public void getAllProductFromAnimal(AnimalsFromProduct request,
      StreamObserver<ProductsFromAnimal> responseObserver)
  {
    super.getAllProductFromAnimal(request, responseObserver);
  }
}
