package via.sdj3.slaughterhouse.controller;

import org.hibernate.criterion.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import via.sdj3.slaughterhouse.model.Animal;
import via.sdj3.slaughterhouse.service.AnimalService;

@RestController
@RequestMapping("/api/slaughterhouse")
public class RestApiController
{

  private AnimalService service;

  public RestApiController(AnimalService service)
  {
    this.service = service;
  }
  @PostMapping("/animals")
  public ResponseEntity<Object> registerAnimal(@RequestBody long registerNumber, double weight, String origin)
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
}
