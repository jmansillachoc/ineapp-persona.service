package pe.ineapp.ineapppersonaservice.Person.application.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.ineapp.ineapppersonaservice.Person.domain.entity.Person;
import pe.ineapp.ineapppersonaservice.Person.infraestructure.repository.PersonRepository;
import pe.ineapp.ineapppersonaservice.Person.infraestructure.request.UserRequest;
import pe.ineapp.ineapppersonaservice.Person.infraestructure.response.BasicResponse;
import pe.ineapp.ineapppersonaservice.Person.infraestructure.response.UserResponse;

import javax.transaction.Transactional;
import java.util.List;


@Log4j2 //Sirve apra escribir log en consola
@Service
public class PersonServiceImpl implements PersonService {


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
                    .basicResponse(BasicResponse.whenNoDataFound("User")).build();
        }

        return UserResponse.builder()
                .personList(personList)
                .basicResponse(BasicResponse.whenSuccess()).build();
    }
    @Override
    public UserResponse getByDni(String dni){

        try{
            Person person = personRepository.findByDni(dni);

            if(person != null){
                return UserResponse.builder()
                        .personList(List.of(person))
                        .basicResponse(BasicResponse.whenSuccess()).build();
            }else{
                return UserResponse.builder()
                        .personList(null)
                        .basicResponse(BasicResponse.whenNoDataFound("User")).build();
            }
        }catch (Exception e){
            return UserResponse.builder()
                    .personList(null)
                    .basicResponse(BasicResponse.whenError(e.getMessage())).build();
        }


    }


    @Transactional //Con esta etiqueta se guarda automaticamente y ahce la actualizacion
    @Override
    public BasicResponse updateUser(UserRequest request ,String dni){

        try{
            //Validar Existencia del usuario a modificar
            Person person = personRepository.findByDni(dni);

            if(person == null){
                return BasicResponse.whenNoDataFound("User con dni: "+dni);
            }else {
                //Validar que la clave sea correcta
                if (request.getPassword().equals(PASSWORD)) {

                    person.setName(request.getName() != null && !request.getName().isBlank() ? request.getName() : person.getName());
                    person.setLastname(request.getLastname() != null && !request.getLastname().isBlank() ? request.getLastname() : person.getLastname());
                    person.setEmail(request.getEmail() != null && !request.getEmail().isBlank() ? request.getEmail() : person.getEmail());
                    person.setDni(request.getDni() != null && !request.getDni().isBlank() ? request.getDni() : person.getDni());
                    person.setBirthDate(request.getBirthDate() != null ? request.getBirthDate() : person.getBirthDate());

                    //personRepository.updatePersonByDni(dni,personUpdate);
                    return BasicResponse.whenSuccess();
                } else {
                    return BasicResponse.whenPassNotMatch();
                }
            }
        }catch(Exception e){
                return BasicResponse.whenError(e.getMessage());
            }

    }


    public BasicResponse deleteUser(String dni){
        try{
            Person person = personRepository.findByDni(dni);

            if(person == null){
                return BasicResponse.whenNoDataFound("User con dni "+dni);
            }else{
                personRepository.delete(person);
                return BasicResponse.whenSuccess();
            }

        }catch (Exception e){
            return BasicResponse.whenError(e.getMessage());
        }
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
