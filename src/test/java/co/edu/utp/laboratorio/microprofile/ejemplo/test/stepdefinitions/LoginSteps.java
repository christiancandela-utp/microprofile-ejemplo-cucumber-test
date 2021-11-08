package co.edu.utp.laboratorio.microprofile.ejemplo.test.stepdefinitions;

import co.edu.utp.laboratorio.microprofile.ejemplo.test.config.Configuration;
import co.edu.utp.laboratorio.microprofile.ejemplo.test.dtos.LoginRequestDTO;
import co.edu.utp.laboratorio.microprofile.ejemplo.test.services.LoginService;
import co.edu.utp.laboratorio.microprofile.ejemplo.test.util.DataManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginSteps {
    private LoginRequestDTO loginRequestDTO;
    private static final Logger logger = Logger.getLogger(LoginSteps.class.getName());

    @Given("Soy un usuario registrado del sistema usando credenciales validas")
    public void soyUnUsuarioRegistradoDelSistemaUsandoCredencialesValidas() {
        loginRequestDTO = LoginRequestDTO.of(Configuration.get().username(), Configuration.get().password());
    }

    @Given("Soy un usuario registrado del sistema usando credenciales no validas")
    public void soyUnUsuarioRegistradoDelSistemaUsandoCredencialesNoValidas() {
        loginRequestDTO = LoginRequestDTO.of("unusuairo","novalido");
    }

    @When("invoco el servicio de autenticación")
    public void invocoElServicioDeAutenticacion() {
        DataManager.getInstance().setResponse(LoginService.login(loginRequestDTO));
    }

    @And("un token de autenticación")
    public void unTokenDeAutenticacion() {
        String token = DataManager.getInstance().getResponse().getHeader("Authorization");
        assertNotNull(token);
    }
}
