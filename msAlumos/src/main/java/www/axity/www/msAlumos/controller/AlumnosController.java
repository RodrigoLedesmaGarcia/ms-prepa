package www.axity.www.msAlumos.controller;

import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import www.axity.www.msAlumos.models.dao.alumno.Alumno;
import www.axity.www.msAlumos.service.AlumnoService;

import java.util.*;

@RestController("/home")
public class AlumnosController {

// ========== INICIO DE INYECCION DE DEPENDENCIAS ====================
    @Autowired
    private AlumnoService service;
// ========== FIN DE INYECCION DE DEPENDENCIAS ====================


// ================== INICIO DEL METODO LISTAR ALUMNOS ===============
    @GetMapping("/listar") // http://localhost:9000/listar
    public List<Alumno> listarAlumnos() {
        return service.findAll();
    } // FIN
// ================== FIN DEL METODO LISTAR ALUMNOS ===============

// ==================== INICO DEL METODO VER DETALLE DE LOS ALUMNOS ===========
    @GetMapping("/{id}") //http://localhost:9000/{id}
    public ResponseEntity<?> detalle (@PathVariable Long id) {
        Optional<Alumno> optional = service.byId(id);
        if(optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.notFound().build();
    } // FIN
// ==================== FIN DEL METODO VER DETALLE DE LOS ALUMNOS ===========


// ================= INICIO DE METODO DE CREAR ALUMNOS ========================
    @PostMapping //http://localhost:9000
    public  ResponseEntity<?>  crearAlumno(@Valid @RequestBody Alumno alumno, BindingResult result){
        if(service.GetByEmail(alumno.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", "ocurrio un error"));
        }

        if(result.hasErrors()){
            Map<String, String> errores = erroresMap(result);
            return ResponseEntity.badRequest().body(errores);
        } //metodo para validar si hay errores
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumno));
    } // FIN


// ================= FIN DE METODO DE CREAR ALUMNOS ========================


// ====================  METODO PARA EDITAR ALUMNOS ==========================
    @PutMapping("/{id}") //http://localhost:9000/{id}
    public ResponseEntity<?> editarAlumno (@Valid @RequestBody Alumno alumno, BindingResult result ,@PathVariable Long id) {
        if(result.hasErrors()){
            Map<String, String> errores = erroresMap(result);
            return ResponseEntity.badRequest().body(errores);
        } // metodo para validar si hay errores
        Optional<Alumno> optional = service.byId(id);
        if(optional.isPresent()){
            Alumno alumnoData = optional.get();
            if(!alumno.getEmail().equalsIgnoreCase(alumnoData.getEmail()) &&
                    service.GetByEmail(alumno.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje","ha ocurrido un error..."));
            }

            alumnoData.setNombre(alumno.getNombre());
            alumnoData.setEdad(alumno.getEdad());
            alumnoData.setEmail(alumno.getEmail());
            alumnoData.setTelefono(alumno.getTelefono());
            alumnoData.setTelefono(alumno.getDireccion());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoData));
        } // FIN DEL "IF"
        return ResponseEntity.notFound().build();
    }// FIN
// ====================  FIN PARA EDITAR ALUMNOS ==========================


// ====================== INICIO DEL METODO PARA ELIMINAR ALUMNOS ======================
    @DeleteMapping("/{id}") // http://localhost:9000/{id}
    public ResponseEntity<?> eliminarAlumno (@PathVariable Long id) {
        Optional<Alumno> optional = service.byId(id);
        if (optional.isPresent()){
            service.delete(id);
            return ResponseEntity.noContent().build();
        } //FIN DEL IF
        return ResponseEntity.notFound().build();
    } // FIN

// ====================== FIN DEL METODO PARA ELIMINAR ALUMNOS ======================


//====================================================================================
@GetMapping("/alumnos-por-profesores") // http://localhost:9000/alumnos-por-profesores
public ResponseEntity<?> alumnosPorProfesor(@RequestParam List<Long> ids){
        return ResponseEntity.ok(service.findAllByIds(ids));
}
// ====================================================================================


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

} // FIN DE "AlumnosController"
