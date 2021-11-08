package co.edu.utp.laboratorio.microprofile.ejemplo.test.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

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

}
