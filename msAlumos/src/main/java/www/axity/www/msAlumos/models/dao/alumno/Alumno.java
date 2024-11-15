package www.axity.www.msAlumos.models.dao.alumno;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "alumnos")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    @NotNull
    private Integer edad;

    @Column(unique = true)
    @Email
    @NotBlank
    private String email;

    @Column(unique = true)
    @NotBlank
    private String telefono;

    @NotBlank
    private String direccion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank String nombre) {
        this.nombre = nombre;
    }


    public @Email @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotBlank String email) {
        this.email = email;
    }

    public @NotBlank String getTelefono() {
        return telefono;
    }

    public void setTelefono(@NotBlank String telefono) {
        this.telefono = telefono;
    }

    public @NotBlank String getDireccion() {
        return direccion;
    }

    public void setDireccion(@NotBlank String direccion) {
        this.direccion = direccion;
    }

    public @NotNull Integer getEdad() {
        return edad;
    }

    public void setEdad(@NotNull Integer edad) {
        this.edad = edad;
    }
}
