package co.edu.utp.laboratorio.microprofile.ejemplo.test.util;

import co.edu.utp.laboratorio.microprofile.ejemplo.test.dtos.PersonaDTO;
import co.edu.utp.laboratorio.microprofile.ejemplo.test.services.PersonService;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {
    private static final DataGenerator instance = new DataGenerator();
    private Faker faker;

    private DataGenerator() {
        faker = new Faker();
    }

    public static DataGenerator getInstance() {
        return instance;
    }

    public PersonaDTO generarPersonaNoRegistrada(){
        String dni;
        do{
            dni = faker.idNumber().valid();
        }while(PersonService.find(dni).statusCode() == 200);
        return PersonaDTO.of( dni,faker.name().name(), generarTelefonos() );
    }

    public List<String> generarTelefonos(){
        List<String> telefonos  = new ArrayList<>();
        int cantidad = faker.number().numberBetween(0,3);
        for (int i = 0; i < cantidad; i++) {
            telefonos.add(faker.phoneNumber().cellPhone());
        }
        return telefonos;
    }

    public PersonaDTO generarPersonaNoRegistradaIncompleta() {
        return PersonaDTO.of( null,faker.name().name(), generarTelefonos() );
    }
}
