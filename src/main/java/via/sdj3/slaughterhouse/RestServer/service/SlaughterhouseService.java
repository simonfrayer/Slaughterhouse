package via.sdj3.slaughterhouse.RestServer.service;

import via.sdj3.slaughterhouse.model.Animal;
import via.sdj3.slaughterhouse.model.Product;

import java.util.List;

public interface SlaughterhouseService
{
    Animal createAnimal(double weight, long registrationNumber, String origin);
    Product createProduct(long regNumber, List<Long> animalRegNumbers);

    List<Long> getAnimalsFromProduct(long productRegNumber);

    List<Long> getProductsFromAnimal(long animalRegNumber);
}
