package pe.ineapp.ineapppersonaservice.Person.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.ineapp.ineapppersonaservice.Person.domain.entity.Person;

public interface PersonRepository extends JpaRepository<Person,Long> {

}
