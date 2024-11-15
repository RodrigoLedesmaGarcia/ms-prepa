package www.axity.www.msProfesores.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.axity.www.msProfesores.client.UsuarioClientRest;
import www.axity.www.msProfesores.models.dao.Alumno;
import www.axity.www.msProfesores.models.dao.profesores.Profesor;
import www.axity.www.msProfesores.models.dao.profesores.ProfesoresAlumno;
import www.axity.www.msProfesores.repository.ProfesorRepository;

import java.util.List;
import java.util.Optional;
@Service
public class ProfesorServiceImpl implements ProfesorService{

// ============= INICIO DE LA INYECCION DE DEPENDENCIAS ===============
    @Autowired
    private ProfesorRepository repository;

    @Autowired
    private UsuarioClientRest clienteRest;
// ============= FIN DE LA INYECCION DE DEPENDENCIAS ===============


    @Override
    public List<Profesor> findAll() {
        return (List<Profesor>)repository.findAll();
    } // FIN DE LA IMPLEMENTACION DE LISTAR PROFESORES

    @Override
    public Optional<Profesor> byId(Long id) {
        return repository.findById(id);
    } // FIN DE LA IMPLEMENTACION DE BUSCAR PROFESORES

    @Override
    public Profesor save(Profesor profesor) {
        return repository.save(profesor);
    } // FIN DE LA IMPLEMENTACION DE GUARDAR PROFESORES

    @Override
    public void delete(Long id) {
           repository.deleteById(id);
    } // FIN DE LA IMPLEMENTACION DE ELIMINAR PROFESORES

    @Override
    public void eliminarProfesorAlumnoPorId(Long id) {
        repository.eliminarProfesorAlumnoPorId(id);
    } //Fin
//==========================================================================>


//==========================================================================>
    @Override
    public Optional<Alumno> asignarAlumno(Alumno alumno, Long alumnoId) {
        Optional<Profesor> optional = repository.findById(alumnoId);
        if(optional.isPresent()){
            Alumno alumnoMs = clienteRest.detalle(alumno.getId());

            Profesor profesor = optional.get();
            ProfesoresAlumno profesoresAlumno = new ProfesoresAlumno();
            profesoresAlumno.setAlumnoId(alumnoMs.getId());

            profesor.addProfesorAlumno(profesoresAlumno);
            repository.save(profesor);
            return Optional.of(alumnoMs);
        }

        return Optional.empty();
    } // Fin

    @Override
    public Optional<Alumno> crearAlumno(Alumno alumno, Long alumnoId) {
        Optional<Profesor> optional = repository.findById(alumnoId);
        if (optional.isPresent()) {
            Alumno alumnoNuevoMs = clienteRest.crearAlumno(alumno);

            Profesor profesor = optional.get();
            ProfesoresAlumno profesoresAlumno = new ProfesoresAlumno();
            profesoresAlumno.setAlumnoId(alumnoNuevoMs.getId());

            profesor.addProfesorAlumno(profesoresAlumno);
            repository.save(profesor);
            return Optional.of(alumnoNuevoMs);
        }

        return Optional.empty();
    } // FIN

    @Override
    public Optional<Alumno> desasignarAlumno(Alumno alumno, Long alumnoId) {

        Optional<Profesor> optional = repository.findById(alumnoId);
        if (optional.isPresent()) {
            Alumno alumnoEliminadoMs = clienteRest.crearAlumno(alumno);

            Profesor profesor = optional.get();
            ProfesoresAlumno profesoresAlumno = new ProfesoresAlumno();
            profesoresAlumno.setAlumnoId(alumnoEliminadoMs.getId());

            profesor.removeProfesorAlumno(profesoresAlumno);
            repository.save(profesor);
            return Optional.of(alumnoEliminadoMs);
        }

        return Optional.empty();

    }
// ============================================================================
    @Override
    public Optional<Profesor> porIdConAlumnos(Long id) {
        Optional<Profesor> optional = repository.findById(id);

        if(optional.isPresent()) {
            Profesor profesor = optional.get();
            if(!profesor.getProfesoresalumnos().isEmpty()) {
                List<Long> ids = profesor.getProfesoresalumnos().stream().map(ProfesoresAlumno::getAlumnoId).toList();

                List<Alumno> alumnos = clienteRest.alumnosPorProfesor(ids);
                profesor.setAlumnos(alumnos);
            }
            return Optional.of(profesor);

        }

        return Optional.empty();
    }
//==========================================================================>

} // FIN DE LA CLASE
