package via.sdj3.slaughterhouse.RestServer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import via.sdj3.slaughterhouse.model.Animal;
import via.sdj3.slaughterhouse.model.Product;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController
{

  private SlaughterhouseService service;

  public RestApiController(SlaughterhouseService service)
  {
    this.service = service;
  }
  @PostMapping("/animals")
  public ResponseEntity<Object> createAnimal(@RequestParam long registerNumber, @RequestParam double weight, @RequestParam String origin)
  {
    try
    {
      Animal animal = service.createAnimal(weight,registerNumber,origin);
      return new ResponseEntity<Object>(animal, HttpStatus.OK);
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
      return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/products")
  public ResponseEntity<Object> createProducts(@RequestParam long registerNumber, @RequestParam List<Long> animalRegNUmbers)
  {
    try
    {
      Product product = service.createProduct(registerNumber,animalRegNUmbers);
      return new ResponseEntity<Object>(product, HttpStatus.OK);
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
      return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/animals")
  public ResponseEntity<Object> getAnimalsFromProduct(@RequestParam Long regNumber)
  {
    try
    {
      List<Long> animalsRegNumbers = service.getAnimalsFromProduct(regNumber);
      return new ResponseEntity<>(animalsRegNumbers,HttpStatus.OK);
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
      List<Long> productRegNumbers = service.getProductsFromAnimal(regNumber);
      return new ResponseEntity<>(productRegNumbers,HttpStatus.OK);
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
