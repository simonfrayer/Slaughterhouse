package via.sdj3.slaughterhouse.RestServer.service;

import org.springframework.stereotype.Service;
import via.sdj3.slaughterhouse.model.Animal;
import via.sdj3.slaughterhouse.model.Product;

import java.util.List;

@Service
public class SlauhterhouseServiceImpl implements SlaughterhouseService
{

    @Override
    public Animal createAnimal(double weight, long registrationNumber, String origin) {
        return null;
    }

    @Override
    public Product createProduct(long regNumber, List<Long> animalRegNumbers) {
        return null;
    }

    @Override
    public List<Long> getAnimalsFromProduct(long productRegNumber) {
        return null;
    }

    @Override
    public List<Long> getProductsFromAnimal(long animalRegNumber) {
        return null;
    }
}
