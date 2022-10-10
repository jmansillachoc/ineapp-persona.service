package pe.ineapp.ineapppersonaservice.Person.infraestructure.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.ineapp.ineapppersonaservice.Person.application.service.PersonService;
import pe.ineapp.ineapppersonaservice.Person.infraestructure.request.UserRequest;
import pe.ineapp.ineapppersonaservice.Person.infraestructure.response.BasicResponse;
import pe.ineapp.ineapppersonaservice.Person.infraestructure.response.UserResponse;

@RestController //Gracias a esta anotacion podemos acceder a los metodos por URI
@RequestMapping("/person") //Esta es la ruta para acceder
public class PersonController {

    @Autowired
    private PersonService personService;


    @GetMapping
    @RequestMapping("/getall")
    public UserResponse getAll(){

        return personService.getAll();
    }

    @GetMapping
    @RequestMapping("/getbydni")
    public UserResponse getByDni(@RequestParam String dni){
        return personService.getByDni(dni);
    }

    //PUT
    @PutMapping
    @RequestMapping("/updateuser")
    public ResponseEntity<BasicResponse> updateUser(@RequestBody UserRequest request,
                                                    @RequestParam String dni){

               BasicResponse response = personService.updateUser(request,dni);
               return ResponseEntity.status(response.getCode()).body(response);
    }

    //POST
    @PostMapping
    @RequestMapping("/adduser")
    public ResponseEntity<BasicResponse> addUser(@RequestBody UserRequest request){
        BasicResponse response = personService.addUser(request);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    //DELETE
    @DeleteMapping
    @RequestMapping("/deleteuser")
    public ResponseEntity<BasicResponse> deleteUser(@RequestParam String dni){
        BasicResponse response = personService.deleteUser(dni);
        return ResponseEntity.status(response.getCode()).body(response);
    }

}
