package www.axity.www.msProfesores.models.dao.profesores;


import jakarta.persistence.*;

@Entity
@Table(name="profesores_alumnos")
public class ProfesoresAlumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "alumno_id", unique = true)
    private Long alumnoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Long alumnoId) {
        this.alumnoId = alumnoId;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(!(obj instanceof ProfesoresAlumno)){
            return false;
        }
        ProfesoresAlumno objeto = (ProfesoresAlumno) obj;
        return this.alumnoId != null &&
                this.alumnoId.equals(objeto.alumnoId);
    }
}
