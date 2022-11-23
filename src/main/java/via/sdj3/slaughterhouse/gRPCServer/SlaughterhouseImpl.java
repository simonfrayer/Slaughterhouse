package via.sdj3.slaughterhouse.gRPCServer;

import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import via.sdj3.slaughterhouse.protobuf.*;
import via.sdj3.slaughterhouse.repository.AnimalRepository;
import via.sdj3.slaughterhouse.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SlaughterhouseImpl extends SlaughterhouseServerGrpc.SlaughterhouseServerImplBase
{


  private AnimalRepository animalRepository;

  private ProductRepository productRepository;


  @Autowired
  public SlaughterhouseImpl(AnimalRepository animalRepository, ProductRepository productRepository)
  {
    this.animalRepository = animalRepository;
    this.productRepository = productRepository;
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

  @Override
  public void createAnimal(AnimalToCreate request, StreamObserver<Animal> responseObserver) {
    Animal animal = Animal.newBuilder()
            .setDate(request.getDate())
            .setWeight(request.getWeight())
            .setOrigin(request.getOrigin()).build();

    animalRepository.save(animal);
    responseObserver.onNext(animal);
    responseObserver.onCompleted();
  }

  @Override
  public void createProduct(ProductToCreate request, StreamObserver<Product> responseObserver) {
    List<Animal> animalIdList = new ArrayList<>();

    for (AnimalRegistrationNumber animalId:request.getAnimalsList()
         ) {
      animalIdList.add(animalRepository.getReferenceById(animalId.getAnimalRegistrationNumber()));
    }

    Product product = Product.newBuilder()
            .addAllAnimals(animalIdList)
            .build();

    productRepository.save(product);
    responseObserver.onNext(product);
    responseObserver.onCompleted();
  }

  @Override
  public void getAllAnimalsFromProduct(ProductRegNumber request, StreamObserver<AnimalsFromProduct> responseObserver) {
    Product product = productRepository.getReferenceById(request.getRegistrationNumber());

    AnimalsFromProduct animalsFromProduct = AnimalsFromProduct.newBuilder()
            .addAllAnimals((product.getAnimalsList()))
            .build();

    responseObserver.onNext(animalsFromProduct);
    responseObserver.onCompleted();
  }

  @Override
  public void getAllProductFromAnimal(AnimalRegistrationNumber request, StreamObserver<ProductsFromAnimal> responseObserver) {
    Animal animal = animalRepository.getReferenceById(request.getAnimalRegistrationNumber());
    List<Product> allProducts = productRepository.findAll();
    List<Product> productsFromAnimalList = new ArrayList<>();

    for (Product product:allProducts
         ) {
      if (product.getAnimalsList().contains(animal))
        productsFromAnimalList.add(product);
    }

    ProductsFromAnimal productsFromAnimal = ProductsFromAnimal.newBuilder()
            .addAllProducts((productsFromAnimalList))
            .build();


    responseObserver.onNext(productsFromAnimal);
    responseObserver.onCompleted();
  }

  @Override
  public void getAnimalById(AnimalRegistrationNumber request, StreamObserver<Animal> responseObserver) {
    Animal animalToReturn = animalRepository.findById(request.getAnimalRegistrationNumber()).get();

    responseObserver.onNext(animalToReturn);
    responseObserver.onCompleted();
  }

  @Override
  public void getAnimalsByOrigin(Origin request, StreamObserver<AnimalsFromProduct> responseObserver) {
    List<Animal> animals = animalRepository.findAll();
    List<Animal> animalsToReturn = new ArrayList<>();
    AnimalsFromProduct animalsFromProduct = null;
    for (Animal animal:animals
         ) {
      if (animal.getOrigin().equals(request.getOrigin())){
        animalsFromProduct = AnimalsFromProduct.newBuilder()
                .addAnimals(animal)
                .build();
      }
    }



    responseObserver.onNext(animalsFromProduct);
    responseObserver.onCompleted();
  }

  @Override
  public void getAnimalsByDate(Date request, StreamObserver<AnimalsFromProduct> responseObserver) {
    List<Animal> animals = animalRepository.findAll();
    List<Animal> animalsToReturn = new ArrayList<>();
    AnimalsFromProduct animalsFromProduct = null;
    for (Animal animal:animals
    ) {
      if (animal.getDate().equals(request.getLocaldate())){
        animalsFromProduct = AnimalsFromProduct.newBuilder()
                .addAnimals(animal)
                .build();
      }
    }



    responseObserver.onNext(animalsFromProduct);
    responseObserver.onCompleted();
  }
}
