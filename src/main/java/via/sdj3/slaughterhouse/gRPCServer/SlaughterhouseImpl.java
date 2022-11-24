package via.sdj3.slaughterhouse.gRPCServer;

import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import via.sdj3.slaughterhouse.protobuf.*;
import via.sdj3.slaughterhouse.repository.AnimalRepository;
import via.sdj3.slaughterhouse.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
@GRpcService
public class SlaughterhouseImpl extends SlaughterhouseServerGrpc.SlaughterhouseServerImplBase {

    private AnimalRepository animalRepository;

    private ProductRepository productRepository;

    @Autowired
    public SlaughterhouseImpl(AnimalRepository animalRepository, ProductRepository productRepository) {
        this.animalRepository = animalRepository;
        this.productRepository = productRepository;
    }


    @Override
    public void createAnimal(AnimalToCreate request, StreamObserver<Animal> responseObserver) {
        Animal animal = Animal.newBuilder()
                .setDate(request.getDate())
                .setWeight(request.getWeight())
                .setOrigin(request.getOrigin()).build();

        via.sdj3.slaughterhouse.model.Animal animalToStore = new via.sdj3.slaughterhouse.model.Animal();
        animalToStore.setDate(animal.getDate());
        animalToStore.setWeight(animalToStore.getWeight());
        animalToStore.setOrigin(animal.getOrigin());

        animalRepository.save(animalToStore);
        responseObserver.onNext(animal);
        responseObserver.onCompleted();
    }

    @Override
    public void createProduct(ProductToCreate request, StreamObserver<Product> responseObserver) {
        List<Long> animalRegNumbers = new ArrayList<>();

        for (var animal: request.getAnimalsList())
        {
            animalRegNumbers.add(animal.getAnimalRegistrationNumber());
        }

        via.sdj3.slaughterhouse.model.Product product = new via.sdj3.slaughterhouse.model.Product(animalRegNumbers);

        productRepository.save(product);


        Product productToSend = Product.newBuilder().setRegistrationNumber(1).addAllAnimals(request.getAnimalsList()).build();

        responseObserver.onNext(productToSend);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllAnimalsFromProduct(ProductRegNumber request, StreamObserver<AnimalsFromProduct> responseObserver) {
        via.sdj3.slaughterhouse.model.Product product = productRepository.getReferenceById(request.getRegistrationNumber());
        List<Animal> animals = new ArrayList<>();

        for (Long regNumber : product.getAnimalRegNumbers()
        ) {
            animals.add(Animal.newBuilder().setRegistrationNumber(regNumber).build());
        }


        AnimalsFromProduct animalsFromProduct = AnimalsFromProduct.newBuilder()
                .addAllAnimals((animals))
                .build();

        responseObserver.onNext(animalsFromProduct);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllProductFromAnimal(AnimalRegistrationNumber request, StreamObserver<ProductsFromAnimal> responseObserver) {
        via.sdj3.slaughterhouse.model.Animal animal = animalRepository.getReferenceById(request.getAnimalRegistrationNumber());
        List<via.sdj3.slaughterhouse.model.Product> allProducts = productRepository.findAll();
        List<Product> productsFromAnimalList = new ArrayList<>();

        for (via.sdj3.slaughterhouse.model.Product product : allProducts
        ) {
            if (product.getAnimalRegNumbers().contains(animal.getRegistrationNumber()))
                productsFromAnimalList.add(Product.newBuilder().setRegistrationNumber(product.getRegNumber()).build());
        }

        ProductsFromAnimal productsFromAnimal = ProductsFromAnimal.newBuilder()
                .addAllProducts((productsFromAnimalList))
                .build();

        responseObserver.onNext(productsFromAnimal);
        responseObserver.onCompleted();
    }

    @Override
    public void getAnimalById(AnimalRegistrationNumber request, StreamObserver<Animal> responseObserver) {
        via.sdj3.slaughterhouse.model.Animal animal = animalRepository.findById(request.getAnimalRegistrationNumber()).get();

        Animal animalToReturn = Animal.newBuilder()
                .setRegistrationNumber(animal.getRegistrationNumber())
                .setOrigin(animal.getOrigin())
                .setDate(animal.getDate())
                .setWeight(animal.getWeight())
                .build();

        responseObserver.onNext(animalToReturn);
        responseObserver.onCompleted();
    }

    @Override
    public void getAnimalsByOrigin(Origin request, StreamObserver<AnimalsFromProduct> responseObserver) {
        List<via.sdj3.slaughterhouse.model.Animal> animals = animalRepository.findAll();
        AnimalsFromProduct animalsFromProduct = null;

        for (via.sdj3.slaughterhouse.model.Animal animal : animals
        ) {
            if (animal.getOrigin().equals(request.getOrigin())) {
              Animal animalToAddToList = Animal.newBuilder()
                      .setRegistrationNumber(animal.getRegistrationNumber())
                      .setOrigin(animal.getOrigin())
                      .setDate(animal.getDate())
                      .setWeight(animal.getWeight())
                      .build();
                animalsFromProduct = AnimalsFromProduct.newBuilder()
                        .addAnimals(animalToAddToList)
                        .build();
            }
        }

        responseObserver.onNext(animalsFromProduct);
        responseObserver.onCompleted();
    }

    @Override
    public void getAnimalsByDate(Date request, StreamObserver<AnimalsFromProduct> responseObserver) {
        List<via.sdj3.slaughterhouse.model.Animal> animals = animalRepository.findAll();
        AnimalsFromProduct animalsFromProduct = null;
        for (via.sdj3.slaughterhouse.model.Animal animal : animals
        ) {
          Animal animalToAddToList = Animal.newBuilder()
                  .setRegistrationNumber(animal.getRegistrationNumber())
                  .setOrigin(animal.getOrigin())
                  .setDate(animal.getDate())
                  .setWeight(animal.getWeight())
                  .build();

            if (animal.getDate().equals(request.getLocaldate())) {
                animalsFromProduct = AnimalsFromProduct.newBuilder()
                        .addAnimals(animalToAddToList)
                        .build();
            }
        }


        responseObserver.onNext(animalsFromProduct);
        responseObserver.onCompleted();
    }


}
