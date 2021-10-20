package co.edu.utp.laboratorio.microprofile.ejemplo.test.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginSteps {
    private RequestSpecification requestSpecification;
    private Response response;
    private static final Logger logger = Logger.getLogger(LoginSteps.class.getName());

    @Given("Soy un usuario registrado del sistema usando credenciales validas")
    public void soyUnUsuarioRegistradoDelSistemaUsandoCredencialesValidas() {
        // TODO Usar Proveedor de datos (Usario) Obtener un usuario registrado
        // TODO Establecer el endPoint del servicio
        // TODO Configurar la invocación del servicio
        logger.info("Start - Given");
        RestAssured.baseURI = "http://localhost:8080/data";

        requestSpecification = given().contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"username\":\"nn\",\n" +
                        "    \"password\":\"1234\"\n" +
                        "}" );

    }

    @Given("Soy un usuario registrado del sistema usando credenciales no validas")
    public void soyUnUsuarioRegistradoDelSistemaUsandoCredencialesNoValidas() {
        logger.info("Start - Given");
        RestAssured.baseURI = "http://localhost:8080/data";

        requestSpecification = given().contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"username\":\"unusuario\",\n" +
                        "    \"password\":\"novalido\"\n" +
                        "}" );

    }

    @When("invoco el servicio de autenticación")
    public void invocoElServicioDeAutenticación() {
        logger.info("Start - When");
        // TODO invocaria el servicio obteniendo una respuesta
        response= requestSpecification.post("/login");
    }

    @Then("obtengo un status code {int}")
    public void obtengoUnStatusCode(int status) {
        logger.info("Start - Then with status"+status);
        //TODO Validar el código de respuesta
        response.then().statusCode(status);
    }

    @And("un token de autenticación")
    public void unTokenDeAutenticación() {
        logger.info("And");
        //TODO validar que en la respuesta se tenga un token
        String token = response.getHeader("Authorization");
        assertNotNull(token);
        logger.info(String.format("Token: %s",token));
    }

}
