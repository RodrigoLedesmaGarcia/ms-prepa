package www.axity.www.msAlumos.repositoty;

import org.springframework.data.repository.CrudRepository;
import www.axity.www.msAlumos.models.dao.alumno.Alumno;

import java.util.Optional;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {

    Optional<Alumno> findByEmail(String email);
}
