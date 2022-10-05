package via.sdj3.slaughterhouse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import via.sdj3.slaughterhouse.model.Animal;
import via.sdj3.slaughterhouse.service.AnimalService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController
{

  private AnimalService service;

  public RestApiController(AnimalService service)
  {
    this.service = service;
  }
  @PostMapping("/animals")
  public ResponseEntity<Object> registerAnimal(@RequestBody long registerNumber, @RequestBody double weight, @RequestBody String origin)
  {
    try
    {
      Animal animal = service.registerAnimal(weight,registerNumber,origin);
      return new ResponseEntity<Object>(animal, HttpStatus.OK);
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
      return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/animals")
  public ResponseEntity<Object> getAllAnimals()
  {
    try
    {
      List<Animal> animals = service.getAnimals();
      return new ResponseEntity<Object>(animals,HttpStatus.OK);
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
      return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }
  }
}
