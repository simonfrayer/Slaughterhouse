package via.sdj3.slaughterhouse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import via.sdj3.slaughterhouse.model.Animal;
import via.sdj3.slaughterhouse.service.AnimalService;

@RestController
@RequestMapping("/")
public class AnimalController0
{

  private AnimalService service;

  public AnimalController0(AnimalService service)
  {
    this.service = service;
  }

  //First endpoint - @PostMapping
  @RequestMapping(value = "/animals", method = RequestMethod.POST)
  @ResponseBody
  public Animal registerAnimal(@RequestBody long registerNumber,@RequestBody double weight,@RequestBody String origin)
  {
    return service.registerAnimal(weight, registerNumber, origin);
  }
}
