package web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import services.api.ContactService;
import web.controller.RestContactController;

@Configuration
public class WebConfiguration {
    @Bean
    RestContactController restContactController(ContactService contactService) {
        return new RestContactController(contactService);
    }
}
