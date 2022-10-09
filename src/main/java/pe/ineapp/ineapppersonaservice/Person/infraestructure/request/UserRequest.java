package pe.ineapp.ineapppersonaservice.Person.infraestructure.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class UserRequest {

    private String name;
    private String lastname;
    private String dni;
    private String email;
    private LocalDate birthDate;
    private String password;
}
