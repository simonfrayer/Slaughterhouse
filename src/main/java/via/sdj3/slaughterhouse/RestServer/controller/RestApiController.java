package via.sdj3.slaughterhouse.RestServer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import via.sdj3.slaughterhouse.RestServer.service.AnimalService;
import via.sdj3.slaughterhouse.model.Animal;

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
  public ResponseEntity<Object> registerAnimal(@RequestParam long registerNumber, @RequestParam double weight, @RequestParam String origin)
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

  @GetMapping(value = "/animals/{regNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getAnimalById(@PathVariable("regNumber") Long regnumber)
  {
    try
    {
      Animal animal = service.getByRegNumber(regnumber);
      return new ResponseEntity<Object>(animal,HttpStatus.OK);
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
      return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "/animals/{year}/{month}/{day}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getAnimalById(@PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("day") int day)
  {
    try
    {
      List<Animal> animals = service.getAllFromDate(year, month, day);
      return new ResponseEntity<Object>(animals,HttpStatus.OK);
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
      return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping (value="/animals/origin")
  public ResponseEntity<Object> getAnimalsByOrigin(@RequestParam String origin)
  {
    try
    {
      System.out.println(origin);
      List<Animal> animals = service.getByOrigin(origin);

      return new ResponseEntity<Object>(animals,HttpStatus.OK);
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
      return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }
  }
}
