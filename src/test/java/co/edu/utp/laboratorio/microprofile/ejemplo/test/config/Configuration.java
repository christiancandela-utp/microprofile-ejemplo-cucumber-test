package co.edu.utp.laboratorio.microprofile.ejemplo.test.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.ConfigFactory;

@Sources({ "system:properties",
        "system:env",
        "classpath:configuration.properties"})
public interface Configuration extends Config {
    @Key("url")
    @DefaultValue("http://localhost:8080/data/")
    String url();

    @Key("user.username")
    @DefaultValue("nn")
    String username();

    @Key("user.password")
    @DefaultValue("1234")
    String password();

    static Configuration get(){
        return ConfigFactory.create(Configuration.class);
    }


}
