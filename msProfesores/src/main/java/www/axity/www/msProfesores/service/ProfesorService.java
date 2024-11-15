package www.axity.www.msProfesores.service;

import www.axity.www.msProfesores.models.dao.Alumno;
import www.axity.www.msProfesores.models.dao.profesores.Profesor;

import java.util.List;
import java.util.Optional;

public interface ProfesorService {

// ==============  CRUD DE PROFESORES =================

    List<Profesor> findAll(); // listar

    Optional<Profesor> byId(Long id); // buscar por id

    Profesor save (Profesor profesor); // guardar

    void delete (Long id); // borrar

    void eliminarProfesorAlumnoPorId(Long id);

// ====================================================

//================ CRUD PARA PROFESORES ALUMNOS ===================

    Optional<Alumno> asignarAlumno(Alumno alumno, Long alumnoId); // asignar alumno

    Optional<Alumno> crearAlumno(Alumno alumno, Long alumnoId); // crear alumno

    Optional<Alumno> desasignarAlumno(Alumno alumno, Long alumnoId); // desasaignar alumno

    Optional<Profesor> porIdConAlumnos(Long id);

//=================================================================
}
