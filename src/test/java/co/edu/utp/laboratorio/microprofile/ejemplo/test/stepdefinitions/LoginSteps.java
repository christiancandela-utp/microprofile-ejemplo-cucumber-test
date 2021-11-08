package co.edu.utp.laboratorio.microprofile.ejemplo.test.stepdefinitions;

import co.edu.utp.laboratorio.microprofile.ejemplo.test.dtos.ErrorResponseDTO;
import co.edu.utp.laboratorio.microprofile.ejemplo.test.dtos.LoginRequestDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginSteps {
    private LoginRequestDTO loginRequestDTO;
    private Response response;
    private static final Logger logger = Logger.getLogger(LoginSteps.class.getName());

    @Given("Soy un usuario registrado del sistema usando credenciales validas")
    public void soyUnUsuarioRegistradoDelSistemaUsandoCredencialesValidas() {
        // TODO Usar Proveedor de datos (Usario) Obtener un usuario registrado
        // TODO Establecer el endPoint del servicio
        // TODO Configurar la invocación del servicio
        logger.info("Start - Given");
        loginRequestDTO = LoginRequestDTO.of("nn","1234");

    }

    @Given("Soy un usuario registrado del sistema usando credenciales no validas")
    public void soyUnUsuarioRegistradoDelSistemaUsandoCredencialesNoValidas() {
        logger.info("Start - Given");
        loginRequestDTO = LoginRequestDTO.of("unusuairo","novalido");
    }

    @When("invoco el servicio de autenticación")
    public void invocoElServicioDeAutenticacion() {
        logger.info("Start - When");
        response= given().contentType(ContentType.JSON)
                .body( loginRequestDTO ).post("/login");
    }

    @Then("obtengo un status code {int}")
    public void obtengoUnStatusCode(int status) {
        logger.info("Start - Then with status"+status);
        //TODO Validar el código de respuesta
        response.then().statusCode(status);
        assertEquals(status, response.statusCode()  );
    }

    @And("el cual retorna un mensaje de error")
    public void elCualRetornaUnMensajeDeError() {
        logger.info("Start - And ");
        ErrorResponseDTO errorResponseDTO = response.body().as(ErrorResponseDTO.class);
        assertNotNull(errorResponseDTO);
        assertNotNull(errorResponseDTO.getError());
        logger.info("Error Message: "+errorResponseDTO.getError());
    }

    @And("un token de autenticación")
    public void unTokenDeAutenticacion() {
        logger.info("And");
        String token = response.getHeader("Authorization");
        assertNotNull(token);
        logger.info(String.format("Token: %s",token));
    }


}
