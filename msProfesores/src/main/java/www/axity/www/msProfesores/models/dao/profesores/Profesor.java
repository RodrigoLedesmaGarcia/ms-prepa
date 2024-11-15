package www.axity.www.msProfesores.models.dao.profesores;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import www.axity.www.msProfesores.models.dao.Alumno;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profesores")
public class Profesor {

// ================ PROFESORES CRUD ========================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    public @NotBlank String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
// =================== FIN DE CRUD PROFESORES =========================


// ================= RELACION DE PROFESORES - ALUMNOS =================

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true )
    @JoinColumn(name = "profesor_id")
    private List<ProfesoresAlumno> profesoresalumnos;
//-------------------------------------------------------------------------

    public Profesor() {
        profesoresalumnos = new ArrayList<>();
        alumnos = new ArrayList<>();
    }

    public List<ProfesoresAlumno> getProfesoresalumnos() {
        return profesoresalumnos;
    }

    public void setProfesoresalumnos(List<ProfesoresAlumno> profesoresalumnos) {
        this.profesoresalumnos = profesoresalumnos;
    }
//-----------------------------------------------------------------------------

    public void addProfesorAlumno(ProfesoresAlumno profesoresAlumno){
        profesoresalumnos.add(profesoresAlumno);
    }

    public void removeProfesorAlumno(ProfesoresAlumno profesoresAlumno) {
        profesoresalumnos.remove(profesoresAlumno);
    }

// =============== FIN DE LA RELACION DE PROFESORES-ALUMNOS ============

    @Transient
    private List<Alumno> alumnos;

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
}
