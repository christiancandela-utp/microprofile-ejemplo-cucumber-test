package co.edu.utp.laboratorio.microprofile.ejemplo.test.stepdefinitions;

import co.edu.utp.laboratorio.microprofile.ejemplo.test.dtos.ErrorResponseDTO;
import co.edu.utp.laboratorio.microprofile.ejemplo.test.util.DataManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ServicesCommonSteps {
    @Then("obtengo un status code {int}")
    public void obtengoUnStatusCode(Integer statusCode) {
        Response response = DataManager.getInstance().getResponse();

        System.out.println(response.body().prettyPrint());
        response.then().log().ifError().statusCode(statusCode);

    }

    @And("un mensaje de error")
    public void elCualRetornaUnMensajeDeError() {
        ErrorResponseDTO errorResponseDTO = DataManager.getInstance().getResponse().body().as(ErrorResponseDTO.class);
        assertNotNull(errorResponseDTO);
        assertNotNull(errorResponseDTO.getError());
    }
}
