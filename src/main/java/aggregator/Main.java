package aggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import persistence.inDb.config.InDbPersistenceConfiguration;
import services.configuration.ServicesConfiguration;
import web.config.WebConfiguration;

@Import({InDbPersistenceConfiguration.class, ServicesConfiguration.class, WebConfiguration.class})
@SpringBootApplication
public class Main {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }


}
