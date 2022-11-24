package via.sdj3.slaughterhouse.RestServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import via.sdj3.slaughterhouse.RestServer.service.AnimalService;
import via.sdj3.slaughterhouse.RestServer.service.ProductService;
import via.sdj3.slaughterhouse.model.Animal;
import via.sdj3.slaughterhouse.model.Product;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController
{
  private AnimalService animalService;
  private ProductService productService;

  public RestApiController(AnimalService animalService, ProductService productService)
  {
    this.animalService = animalService;
    this.productService = productService;
  }
  @PostMapping("/animals")
  public ResponseEntity<Object> createAnimal(@RequestParam double weight, @RequestParam String origin)
  {
    try
    {
      Animal animal = animalService.createAnimal(weight,origin);
      return new ResponseEntity<>(animal, HttpStatus.OK);
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/products")
  public ResponseEntity<Object> createProducts( @RequestParam List<Long> animals)
  {
    try
    {
      Product product = productService.createProduct(animals);
      return new ResponseEntity<>(product, HttpStatus.OK);
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/animals")
  public ResponseEntity<Object> getAnimalsFromProduct(@RequestParam Long regNumber)
  {
    try
    {
      List<Animal> animals = animalService.getAnimalsFromProduct(regNumber);
      return new ResponseEntity<>(animals,HttpStatus.OK);
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/products")
  public ResponseEntity<Object> getProductsFromAnimal(@RequestParam Long regNumber)
  {
    try
    {
      List<Product> products = productService.getProductsFromAnimal(regNumber);
      return new ResponseEntity<>(products,HttpStatus.OK);
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/animals/regNumber")
  ResponseEntity<Object> getAnimalById(@RequestHeader Long regNumber)
  {
    try
    {
      Animal animal = animalService.getAnimalById(regNumber);
      return new ResponseEntity<>(animal,HttpStatus.OK);
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/orgins")
  ResponseEntity<Object> getAnimalsByOrigin(@RequestParam String origin)
  {
    try
    {
      List<Animal> animals = animalService.getAnimalsByOrigin(origin);
      return new ResponseEntity<>(animals,HttpStatus.OK);
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/date")
  ResponseEntity<Object> getAnimalByDate(@RequestParam LocalDate date)
  {
    try
    {
      List<Animal> animals = animalService.getAnimalsByDate(date);
      return new ResponseEntity<>(animals,HttpStatus.OK);
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
