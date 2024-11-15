package www.axity.www.msProfesores.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import www.axity.www.msProfesores.models.dao.profesores.Profesor;

public interface ProfesorRepository extends CrudRepository<Profesor, Long> {

    @Modifying
    @Query("delete from ProfesoresAlumno pa where pa.alumnoId=?1")
    void eliminarProfesorAlumnoPorId(Long id);
}
