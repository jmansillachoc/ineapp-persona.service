package pe.ineapp.ineapppersonaservice.Person;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController //Gracias a esta anotacion podemos acceder a los metodos por URI
@RequestMapping("/person") //Esta es la ruta para acceder
public class PersonController {

    @GetMapping
    @RequestMapping("/getAll")
    public List<Person> getAll(){

        LocalDate date = LocalDate.of(1992, Month.JUNE, 25);

        Person person = Person.builder()
                .id(1L)
                .name("Joseph")
                .lastname("Mansilla")
                .dni("72721846")
                .email("jmansillac.isil@gmail.com")
                .birthDate(date).build();

        return List.of(person);
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


    //DELETE
}
