package web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import services.api.ContactService;
import web.aspect.LoggerControllerAspect;
import web.controller.RestContactController;

@Import(LoggerControllerAspect.class)
@Configuration
public class WebConfiguration {
    @Bean
    RestContactController restContactController(ContactService contactService) {
        return new RestContactController(contactService);
    }
}
