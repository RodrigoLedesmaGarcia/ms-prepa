package www.axity.www.msAlumos.service;

import www.axity.www.msAlumos.models.dao.alumno.Alumno;

import java.util.List;
import java.util.Optional;

public interface AlumnoService {

 // ===================== INICIO DE CRUD DE ALUMOS ========================================
    List<Alumno> findAll(); // metodp para listar a los alumnos

    List<Alumno> findAllByIds(Iterable<Long> ids); // listar por ids

    Optional<Alumno> byId(Long id); // metodo para buscar a los alumnos

    Alumno save(Alumno alumno); //metodo para guardar a los alumnos

    void delete(Long id); // metodo para listar a los alumnos

    Optional<Alumno> GetByEmail(String email);
 // ===================== FIN DE CRUD DE ALUMOS ========================================

 // =======================================================================================





}// FIN DE LA INTERFACE.
