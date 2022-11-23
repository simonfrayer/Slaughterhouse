package via.sdj3.slaughterhouse.RestServer.service;

import via.sdj3.slaughterhouse.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(long regNumber, List<Long> animalRegNumbers);
    List<Long> getAnimalsFromProduct(long productRegNumber);

}
