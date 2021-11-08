package co.edu.utp.laboratorio.microprofile.ejemplo.test.services;

import co.edu.utp.laboratorio.microprofile.ejemplo.test.dtos.LoginRequestDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginService {
    public static Response login(LoginRequestDTO loginRequestDTO){
        return given().contentType(ContentType.JSON)
                .body( loginRequestDTO ).post("/login");
    }
}
