package via.sdj3.slaughterhouse.repository;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import org.springframework.stereotype.Repository;
import via.sdj3.slaughterhouse.protobuf.Animal;
import via.sdj3.slaughterhouse.protobuf.Product;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class AnimalRepositoryImpl implements AnimalRepository
{

  private static final Map<Long, Animal> animalMap = new HashMap<>();
  private static final Map<Long, Product> productMap = new HashMap<>();

  public AnimalRepositoryImpl() {
  }

  @Override public Animal registerAnimal(
      Animal animal)
  {
    animalMap.put(animal.getRegistrationNumber(),animal);
    return animal;
  }

  @Override public List<Long> getAllAnimalRegNumberFromProduct(
      long productRegNum)
  {
    ArrayList<Long> animalRegNum = new ArrayList<>();
    Product product = productMap.get(productRegNum);
    for (int i = 0; i < product.getAnimalRegNumberCount(); i++)
    {
      animalRegNum.add(product.getAnimalRegNumber(i));
    }
    return animalRegNum;
  }

  @Override public Product registerProduct(Product product)
  {
    productMap.put(product.getRegistrationNumber(), product);
    return product;
  }

  @Override public List<Long> getAllProductRegNumFromAnimal(long animalRegNum)
  {
    ArrayList<Long> productRegNum = new ArrayList<>();
    Collection<Product> products = productMap.values();

    for (Product product: products)
    {
      for (int i = 0; i < product.getAnimalRegNumberCount(); i++)
      {
        if (product.getAnimalRegNumber(i) == animalRegNum)
        {
          productRegNum.add(product.getRegistrationNumber());
        }
      }
    }
    return productRegNum;
  }
}
