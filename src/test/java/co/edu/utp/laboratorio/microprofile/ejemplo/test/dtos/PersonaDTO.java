package co.edu.utp.laboratorio.microprofile.ejemplo.test.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PersonaDTO {
    private final String dni;
    private final String nombre;
    private final List<String> telefonos;

    public PersonaDTO(String dni, String nombre) {
        this(dni,nombre,Collections.emptyList());
    }

    public PersonaDTO(String dni, String nombre, List<String> telefonos) {
        this.dni = dni;
        this.nombre = nombre;
        if( telefonos == null ){
            telefonos = Collections.emptyList();
        }
        this.telefonos = Collections.unmodifiableList( telefonos );
    }

    public String getDni() {
        return dni;
    }


    public String getNombre() {
        return nombre;
    }

    public List<String> getTelefonos() {
        return telefonos;
    }

    @JsonCreator
    public static PersonaDTO of(@JsonProperty("dni") String dni,
                                @JsonProperty("nombre") String nombre,
                                @JsonProperty("telefonos") List<String> telefonos){
        return new PersonaDTO(dni,nombre,telefonos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonaDTO)) return false;
        PersonaDTO that = (PersonaDTO) o;
        return Objects.equals(getDni(), that.getDni()) && Objects.equals(getNombre(), that.getNombre()) && Objects.equals(getTelefonos(), that.getTelefonos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDni(), getNombre(), getTelefonos());
    }
}
