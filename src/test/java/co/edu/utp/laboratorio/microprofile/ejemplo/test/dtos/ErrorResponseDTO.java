package co.edu.utp.laboratorio.microprofile.ejemplo.test.dtos;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ErrorResponseDTO {
    private final String error;

    public ErrorResponseDTO(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    @JsonCreator
    public static ErrorResponseDTO of(@JsonProperty("error") String error){
        return new ErrorResponseDTO(error);
    }

}
