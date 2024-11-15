package www.axity.www.msProfesores.client;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import www.axity.www.msProfesores.models.dao.Alumno;

import java.util.List;

@FeignClient(name = "msAlumos", url="localhost:9000")
public interface UsuarioClientRest {

    @GetMapping("/{id}")
    Alumno detalle (@PathVariable Long id);

    @PostMapping("/")
    Alumno crearAlumno(@RequestBody Alumno alumno);

    @GetMapping("/alumnos-por-profesores")
    List<Alumno> alumnosPorProfesor(@RequestParam Iterable<Long> ids);

}
