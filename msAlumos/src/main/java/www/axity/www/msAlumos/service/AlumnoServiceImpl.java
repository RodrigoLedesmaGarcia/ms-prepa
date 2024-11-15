package www.axity.www.msAlumos.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.axity.www.msAlumos.client.ProfesorClientRest;
import www.axity.www.msAlumos.models.dao.alumno.Alumno;
import www.axity.www.msAlumos.repositoty.AlumnoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements AlumnoService {

// =========== INICIO DE INYECCION DE DEPENDECIAS ====================
    @Autowired
    private AlumnoRepository repository;

    @Autowired
    private ProfesorClientRest clientRest;
// =========== FIN DE INYECCION DE DEPENDECIAS ====================


// ============ INICIO DE CRUD DE ALUMOS ===============================
    @Override
    public List<Alumno> findAll() {
        return (List<Alumno>) repository.findAll();
    }// fin del metodo de listar alumnos

    @Override
    public List<Alumno> findAllByIds(Iterable<Long> ids) {
        return (List<Alumno>) repository.findAllById(ids);
    } // fin del metodo de listar por ids

    @Override
    public Optional<Alumno> byId(Long id) {
        return repository.findById(id);
    }// fin del metodo de buscar alumnos

    @Override
    public Alumno save(Alumno alumno) {
        return repository.save(alumno);
    } // fin del metodo de guardar alumnos

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
        clientRest.eliminarProfesorAlumno(id);
    } // fin del metodo de eliminar alumnos

    @Override
    public Optional<Alumno> GetByEmail(String email) {
        return repository.findByEmail(email);
    }

    // ============ FIN DE CRUD DE ALUMOS ===============================

} // FIN DE LA CLASE DE IMPLEMENTACION.
