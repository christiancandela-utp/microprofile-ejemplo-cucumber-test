package co.edu.utp.laboratorio.microprofile.ejemplo.test.stepdefinitions;

import co.edu.utp.laboratorio.microprofile.ejemplo.test.dtos.PersonaDTO;
import co.edu.utp.laboratorio.microprofile.ejemplo.test.services.PersonService;
import co.edu.utp.laboratorio.microprofile.ejemplo.test.util.DataGenerator;
import co.edu.utp.laboratorio.microprofile.ejemplo.test.util.DataManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.Optional;

import static io.restassured.RestAssured.given;

public class PersonaSteps {
    private PersonaDTO personaDTO;
    @Given("Tengo el dni de una persona registrada")
    public void tengoElDniDeUnaPersonaRegistrada() {
        Optional<PersonaDTO> personaDTOOptional = DataGenerator.getInstance().generarPersonaRegistrada();
        Assertions.assertDoesNotThrow( ()->{
            personaDTO = personaDTOOptional.orElseThrow(  );
        },"No existen personas");
    }

    @When("invoco el servicio de obtener persona")
    public void invocoElServicioDeObtenerPersona() {
        DataManager.getInstance().setResponse(PersonService.find(personaDTO.getDni()));
    }

    @And("los datos de la persona asociados al dni dado")
    public void losDatosDeLaPersonaAsociadosAlDniDado() {
        PersonaDTO personaDTO = DataManager.getInstance().getResponse().as(PersonaDTO.class);
        Assertions.assertEquals(personaDTO.getDni(),personaDTO.getDni());
        Assertions.assertEquals(personaDTO.getNombre(),personaDTO.getNombre());
    }

    @Given("Tengo el dni de una persona no registrada")
    public void tengoElDniDeUnaPersonaNoRegistrada() {
        personaDTO = PersonaDTO.of("-1",null,null);
    }
}
