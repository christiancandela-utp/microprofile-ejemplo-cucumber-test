package co.edu.utp.laboratorio.microprofile.ejemplo.test.stepdefinitions;

import co.edu.utp.laboratorio.microprofile.ejemplo.test.dtos.PersonaDTO;
import co.edu.utp.laboratorio.microprofile.ejemplo.test.services.PersonService;
import co.edu.utp.laboratorio.microprofile.ejemplo.test.util.DataManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

public class PersonaSteps {
    private String dni;
    @Given("Tengo el dni de una persona registrada")
    public void tengoElDniDeUnaPersonaRegistrada() {
        dni = "1234";
    }

    @When("invoco el servicio de obtener persona")
    public void invocoElServicioDeObtenerPersona() {
        DataManager.getInstance().setResponse(PersonService.find(dni));
    }

    @And("los datos de la persona asociados al dni dado")
    public void losDatosDeLaPersonaAsociadosAlDniDado() {
        PersonaDTO personaDTO = DataManager.getInstance().getResponse().as(PersonaDTO.class);
        Assertions.assertEquals(dni,personaDTO.getDni());
        Assertions.assertEquals("Juan Perez",personaDTO.getNombre());
    }

    @Given("Tengo el dni de una persona no registrada")
    public void tengoElDniDeUnaPersonaNoRegistrada() {
        dni = "-1";
    }
}
