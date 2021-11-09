package co.edu.utp.laboratorio.microprofile.ejemplo.test.stepdefinitions;

import co.edu.utp.laboratorio.microprofile.ejemplo.test.dtos.PersonaDTO;
import co.edu.utp.laboratorio.microprofile.ejemplo.test.services.PersonService;
import co.edu.utp.laboratorio.microprofile.ejemplo.test.util.DataGenerator;
import co.edu.utp.laboratorio.microprofile.ejemplo.test.util.DataManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RegistrarPersonaSteps {
    private PersonaDTO personaDTO;

    @Given("Tengo los datos de una persona no registrada")
    public void tengoLosDatosDeUnaPersonaNoRegistrada() {
        personaDTO = DataGenerator.getInstance().generarPersonaNoRegistrada();
    }

    @When("invoco el servicio de registrar persona")
    public void invocoElServicioDeRegistrarPersona() {
        DataManager.getInstance().setResponse(PersonService.record(personaDTO));
    }

    @And("los datos de la persona enviados")
    public void losDatosDeLaPersonaEnviados() {
        PersonaDTO personaObtenida = DataManager.getInstance().getResponse().as(PersonaDTO.class);
        assertEquals( personaDTO , personaObtenida );
    }

    @Given("Tengo los datos incompletos de una persona no registrada")
    public void tengoLosDatosIncompletosDeUnaPersonaNoRegistrada() {
        personaDTO = DataGenerator.getInstance().generarPersonaNoRegistradaIncompleta();
    }

    @Given("Tengo los datos de una persona registrada")
    public void tengoLosDatosDeUnaPersonaRegistrada() {
        Optional<PersonaDTO> personaDTOOptional = DataGenerator.getInstance().generarPersonaRegistrada();
        Assertions.assertDoesNotThrow( ()->{
            personaDTO = personaDTOOptional.orElseThrow(  );
        },"No existen personas");
    }
}
