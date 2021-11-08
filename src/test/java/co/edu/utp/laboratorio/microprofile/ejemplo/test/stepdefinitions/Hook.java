package co.edu.utp.laboratorio.microprofile.ejemplo.test.stepdefinitions;

import co.edu.utp.laboratorio.microprofile.ejemplo.test.config.Configuration;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hook {
    @Before
    public void configurar(){
        RestAssured.baseURI = Configuration.get().url();
    }
}
