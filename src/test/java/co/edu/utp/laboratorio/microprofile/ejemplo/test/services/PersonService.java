package co.edu.utp.laboratorio.microprofile.ejemplo.test.services;

import co.edu.utp.laboratorio.microprofile.ejemplo.test.dtos.PersonaDTO;
import co.edu.utp.laboratorio.microprofile.ejemplo.test.util.DataManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PersonService {
    public static Response find(String dni){
        return given().get("/persona/"+dni);
    }

    public static Response record(PersonaDTO personaDTO){
        return given().contentType(ContentType.JSON).header("Authorization", DataManager.getInstance().getToken()).body(personaDTO).post("/persona/");
    }

    public static Response findAll(){
        return given().get("/persona/");
    }
}
