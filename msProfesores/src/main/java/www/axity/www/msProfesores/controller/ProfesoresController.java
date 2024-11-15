package www.axity.www.msProfesores.controller;

import feign.FeignException;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import www.axity.www.msProfesores.models.dao.Alumno;
import www.axity.www.msProfesores.models.dao.profesores.Profesor;
import www.axity.www.msProfesores.service.ProfesorService;

import java.util.*;

@RestController
public class ProfesoresController {

// =================== INICIO DE LA INYECCION DE DEPENDENCIAS ===============
    @Autowired
    private ProfesorService service;
// =================== FIN DE LA INYECCION DE DEPENDENCIAS ===============


// =================== INICIO METODO PARA LISTAR A LOS PROFESORES ====================
    @GetMapping("/listar") // http://localhost:9001
    public List<Profesor> listarProfesores () {
        return service.findAll();
    } // fin del metodo
// =================== FIN METODO PARA LISTAR A LOS PROFESORES =====================

// ================== INICIO DEL METODO PARA VER EL DETALLE DE PROFESORES ==============
    @GetMapping("/{id}") // http://localhost:9001/{id}
    public ResponseEntity<?> detalleProfesor(@PathVariable Long id){
        Optional<Profesor> optional = service.byId(id);
        if(optional.isPresent()){
            return ResponseEntity.ok(optional.get());
        } // fin del if
        return ResponseEntity.notFound().build();
    } // fin
// ================== FIN DEL METODO PARA VER EL DETALLE DE PROFESORES ==============

// ===================== INICIO DEL METODO PARA CREAR PROFESORES ==================
    @PostMapping // http://localhost:9001
    public ResponseEntity<?> crearProfesor (@Valid @RequestBody Profesor profesor, BindingResult result) {
        if(result.hasErrors()){
            Map<String, String> errores = erroresMap(result);
            return ResponseEntity.badRequest().body(errores);
        } // metodo para validar si hay errores
          return ResponseEntity.status(HttpStatus.CREATED).body(service.save(profesor));
    } // FIN
// ==================== FIN DEL METODO PARA CREAR PROFESORES ====================


// =================== INICIO DEL METODO PARA EDITAR PROFESORES ================
    @PutMapping("/{id}") // http://localhost:9001/{id}
    public ResponseEntity<?> editarProfesores (@Valid @RequestBody Profesor profesor, BindingResult result ,@PathVariable Long id) {
        Optional<Profesor> optional= service.byId(id);
        if (optional.isPresent()) {
            Profesor profesorData = optional.get();
            profesorData.setNombre(profesor.getNombre());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(profesorData));
        } // FIN DEL IF

        return ResponseEntity.notFound().build();
    }// FIN

// ===================== FIN DEL METODO PARA CREAR PROFESORES ==================


// ================== INICIO DEL METODO PARA ELIMINAR PROFESORES ===================
    @DeleteMapping("/{id}") // http://localhost:9001/{id}
    public ResponseEntity<?> eleminarProfesor (@PathVariable Long id) {
        Optional<Profesor> optional = service.byId(id);
        if(optional.isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } // FIN DEL IF
        return ResponseEntity.noContent().build();
    } // FIN
// ==================== FIN DEL METODO PARA ELIMINAR PROFESORES ===================


// ======================== INICIO METODO ASGINAR ALUMNO ==================================
    @PutMapping("/asignar-alumno/{alumnoId}") // http://localhost:9001/asignar-alumno/{alumnoId}
    public ResponseEntity<?> asignarAlumno (@RequestBody Alumno alumno, @PathVariable Long alumnoId){
        Optional<Alumno> optional;
        try {
           optional= service.asignarAlumno(alumno, alumnoId);
        } catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(Collections.singletonMap("mensaje", "ocurrio un error..."));
        }

        if(optional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optional.get());

        }

        return ResponseEntity.noContent().build();


    }// FIN

// ====================== FIN DEL METODO ASGINAR ALUMNO ========================


// ================== INICIO DEL METODO PARA CREAR UN ALUMNO =====================

    @PostMapping("/crear-alumno/{alumnoId}") // http://localhost:9001/crear-alumno/{alumnoId}
    public ResponseEntity<?> crearAlumno (@RequestBody Alumno alumno, @PathVariable Long alumnoId){
        Optional<Alumno> optional;
        try {
            optional= service.crearAlumno(alumno, alumnoId);
        } catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(Collections.singletonMap("mensaje", "ocurrio un error..."));
        }

        if(optional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optional.get());

        }

        return ResponseEntity.noContent().build();


    }// FIN
// ================== FIN DEL METODO PARA CREAR UN ALUMNO =====================


// ======================= INICIO DEL METODO PARA ELIMINAR UN ALUMNO =============
    @DeleteMapping("/eliminar-alumno/{alumnoId}") // http://localhost:9001/eliminra-alumno/{alumnoId}
    public ResponseEntity<?> eliminarAlumno (@RequestBody Alumno alumno, @PathVariable Long alumnoId){
        Optional<Alumno> optional;
        try {
            optional= service.desasignarAlumno(alumno, alumnoId);
        } catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(Collections.singletonMap("mensaje", "ocurrio un error..."));
        }

        if(optional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optional.get());

        }

        return ResponseEntity.noContent().build();


    }// FIN
// ====================== FIN DEL METODO PARA ELIMINAR UN ALUMNO ==================


// ====================================================================================

    @DeleteMapping("/eliminar-alumno-profesor/{id}") //  http://localhost:9001/eliminra-alumno.profesor/{alumnoId}
    public ResponseEntity<?> eliminarProfesorAlumno(@PathVariable Long id){
        service.eliminarProfesorAlumnoPorId(id);

        return ResponseEntity.noContent().build();
    }


// ============== INICIO DEL METODO ERRORES ==============================================
    /*
    Este metodo se aplica en los metodos de crearAlumno y metodo detalle para mapear errores
     */
    private static Map<String, String> erroresMap(BindingResult result) {
        Map<String, String > errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo tiene errores");
        });
        return errores;
    }
// ============= FIN DEL METODO DE ERRORES =============================================

}// FIN DE LA CLASE DE CONTROL
