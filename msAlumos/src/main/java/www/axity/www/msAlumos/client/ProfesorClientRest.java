package www.axity.www.msAlumos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msProfesores", url="localhost:9001")
public interface ProfesorClientRest {

    @DeleteMapping("/eliminar-alumno/{id}")
    void  eliminarProfesorAlumno(@PathVariable Long id);

}
