package pe.ineapp.ineapppersonaservice.Person.application.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.ineapp.ineapppersonaservice.Person.domain.entity.Person;
import pe.ineapp.ineapppersonaservice.Person.infraestructure.repository.PersonRepository;
import pe.ineapp.ineapppersonaservice.Person.infraestructure.request.UserRequest;
import pe.ineapp.ineapppersonaservice.Person.infraestructure.response.BasicResponse;
import pe.ineapp.ineapppersonaservice.Person.infraestructure.response.UserResponse;

import java.util.List;


@Log4j2 //Sirve apra escribir log en consola
@Service
public class PersonServiceImplemetation implements PersonService {


    private static final String PASSWORD = "123456";

    @Autowired
    private PersonRepository personRepository;

    @Override
    public BasicResponse addUser(UserRequest request){
        //log.info("Este es el jeson: {}", person.toString());

        try{
            if(request.getPassword().equals(PASSWORD)){
                personRepository.save(this.builPersonFromRequest(request));
                return BasicResponse.whenSuccess();
            }else{
                return BasicResponse.whenPassNotMatch();
            }

        }catch (Exception e){
            log.error(e.getMessage());
            return BasicResponse.whenError(e.getMessage());
        }
    }

    @Override
    public UserResponse getAll(){
        List<Person> personList = personRepository.findAll();

        if (personList.isEmpty()){
            return UserResponse.builder()
                    .personList(null)
                    .basicResponse(BasicResponse.whenNoDataFound()).build();
        }

        return UserResponse.builder()
                .personList(personList)
                .basicResponse(BasicResponse.whenSuccess()).build();
    }
    public Person builPersonFromRequest(UserRequest request){

        return Person.builder()
                .name(request.getName())
                .lastname(request.getLastname())
                .dni(request.getDni())
                .email(request.getEmail())
                .birthDate(request.getBirthDate()).build();
    }
}
