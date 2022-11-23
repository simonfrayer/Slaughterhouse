package via.sdj3.slaughterhouse.RestServer.service;

import via.sdj3.slaughterhouse.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(List<Long> animalRegNumbers);
    List<Product> getProductsFromAnimal(long animalRegNumber);

}
