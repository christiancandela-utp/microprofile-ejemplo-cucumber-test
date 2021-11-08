package co.edu.utp.laboratorio.microprofile.ejemplo.test.stepdefinitions;

import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hook {
    @Before
    public void configurar(){
        RestAssured.baseURI = "http://localhost:8080/data";
    }
}
