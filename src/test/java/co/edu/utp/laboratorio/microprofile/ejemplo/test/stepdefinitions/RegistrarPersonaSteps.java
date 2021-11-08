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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RegistrarPersonaSteps {
    private PersonaDTO personaDTO;

    @And("Tengo los datos de una persona no registrada")
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

    @And("Tengo los datos incompletos de una persona no registrada")
    public void tengoLosDatosIncompletosDeUnaPersonaNoRegistrada() {
        personaDTO = DataGenerator.getInstance().generarPersonaNoRegistradaIncompleta();
    }

    @And("Tengo los datos de una persona registrada")
    public void tengoLosDatosDeUnaPersonaRegistrada() {
        PersonaDTO[] persons = PersonService.findAll().as(PersonaDTO[].class);
        assertNotEquals(0,persons.length);
        personaDTO = Arrays.stream(persons).findAny().get();
    }
}
