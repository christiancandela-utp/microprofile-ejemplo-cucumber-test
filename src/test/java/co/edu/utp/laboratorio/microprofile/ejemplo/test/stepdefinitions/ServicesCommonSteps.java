package co.edu.utp.laboratorio.microprofile.ejemplo.test.stepdefinitions;

import co.edu.utp.laboratorio.microprofile.ejemplo.test.config.Configuration;
import co.edu.utp.laboratorio.microprofile.ejemplo.test.dtos.ErrorResponseDTO;
import co.edu.utp.laboratorio.microprofile.ejemplo.test.dtos.LoginRequestDTO;
import co.edu.utp.laboratorio.microprofile.ejemplo.test.services.LoginService;
import co.edu.utp.laboratorio.microprofile.ejemplo.test.util.DataManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ServicesCommonSteps {
    @Then("obtengo un status code {int}")
    public void obtengoUnStatusCode(Integer statusCode) {
        Response response = DataManager.getInstance().getResponse();
        response.then().log().ifError().statusCode(statusCode);
    }

    @And("un mensaje de error")
    public void elCualRetornaUnMensajeDeError() {
        assertEquals(ContentType.JSON.toString(),DataManager.getInstance().getResponse().contentType());
        ErrorResponseDTO errorResponseDTO = DataManager.getInstance().getResponse().as(ErrorResponseDTO.class);
        assertNotNull(errorResponseDTO);
        assertNotNull(errorResponseDTO.getError());
    }

    @Given("Estoy autenticado")
    public void estoyAutenticado() {
        Response response = LoginService.login(
                LoginRequestDTO.of(Configuration.get().username(), Configuration.get().password())
        );
        assertEquals(200,response.statusCode());
        String token = response.getHeader("Authorization");
        assertNotNull(token);
        DataManager.getInstance().setToken(token);
    }

    @And("consistente con la estrutura esperada de {string}")
    public void consistenteConLaEstruturaEsperadaDe(String schema) {
        DataManager.getInstance().getResponse().then().body( matchesJsonSchemaInClasspath("schemas/"+schema+"-schema.json") );
    }
}
