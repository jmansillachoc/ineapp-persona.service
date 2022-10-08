package pe.ineapp.ineapppersonaservice.Person;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


/*
@AllArgsConstructor //Creame el constructor con todos los argumentos.
@NoArgsConstructor //crea un constructor vacio con los argumentos declarados.
*/
@Builder //BUSCAR MAS INFORMACION SOBRE BUILDER - Esto
@Data
@Entity(name="Person")
@Table(
        name="tbl_person",
        uniqueConstraints = {
                @UniqueConstraint(name="person_email.unique", columnNames = "email"),
                @UniqueConstraint(name="person_dni.unique", columnNames = "dni"),
        }
)

public class Person {

    @SequenceGenerator(
            name="person_sequence",
            sequenceName = "person_sequence",
            allocationSize = 1
    )

    //Cada ves que se agrega un valor aumenta de 1 en 1
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "person_sequence"
    )
    @Id
    Long id;
    String name;
    String lastname;
    String dni;
    String email;
    LocalDate birthDate;

    //Contructor (Con Lombox ya no es necesario hacer esto)
    /*public Person(String nombre , String apellido){
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Person(){

    }*/

    //Getters and Setters (Con Lombox ya no es necesario hacer esto)

    /*public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }*/
}
