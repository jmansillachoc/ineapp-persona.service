package pe.ineapp.ineapppersonaservice.Person.application.service;

import pe.ineapp.ineapppersonaservice.Person.domain.entity.Person;
import pe.ineapp.ineapppersonaservice.Person.infraestructure.request.UserRequest;
import pe.ineapp.ineapppersonaservice.Person.infraestructure.response.BasicResponse;
import pe.ineapp.ineapppersonaservice.Person.infraestructure.response.UserResponse;

import java.util.List;

public interface PersonService {
    BasicResponse addUser(UserRequest request);

    public UserResponse getAll();
}
