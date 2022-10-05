package via.sdj3.slaughterhouse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import via.sdj3.slaughterhouse.model.Animal;
import via.sdj3.slaughterhouse.service.AnimalService;


@RestController
@RequestMapping("/")
public class RestApiController
{

  private AnimalService service;

  public RestApiController(AnimalService service)
  {
    this.service = service;
  }
  @PostMapping("/animals")
  @ResponseBody
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
