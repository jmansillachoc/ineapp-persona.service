package pe.ineapp.ineapppersonaservice.Person.infraestructure.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.ineapp.ineapppersonaservice.Person.domain.entity.Person;
import pe.ineapp.ineapppersonaservice.Person.application.service.PersonService;
import pe.ineapp.ineapppersonaservice.Person.infraestructure.request.UserRequest;
import pe.ineapp.ineapppersonaservice.Person.infraestructure.response.BasicResponse;
import pe.ineapp.ineapppersonaservice.Person.infraestructure.response.UserResponse;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController //Gracias a esta anotacion podemos acceder a los metodos por URI
@RequestMapping("/person") //Esta es la ruta para acceder
public class PersonController {

    @Autowired
    private PersonService personService;


    @GetMapping
    @RequestMapping("/getAll")
    public UserResponse getAll(){

        return personService.getAll();
    }

    @GetMapping
    @RequestMapping("/getbyid")
    public Person getByid(){
        LocalDate date = LocalDate.of(1992, Month.JUNE, 25);

        Person person = Person.builder()
                .id(1L)
                .name("Joseph")
                .lastname("Mansilla")
                .dni("72721846")
                .email("jmansillac.isil@gmail.com")
                .birthDate(date).build();

        return person;
    }

    //PUT


    //POST
    @PostMapping
    @RequestMapping("/adduser")
    public ResponseEntity<BasicResponse> addUser(@RequestBody UserRequest request){
        BasicResponse response = personService.addUser(request);
        return ResponseEntity.status(response.getCode()).body(response);
    }



    //DELETE
}
