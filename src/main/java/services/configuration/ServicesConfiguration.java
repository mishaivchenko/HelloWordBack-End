package services.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import persistence.ContactRepository;
import services.api.ContactService;
import services.impl.DefaultContactService;

@Configuration
public class ServicesConfiguration {

    @Bean
    public ContactService contactService(ContactRepository contactRepository) {
        return new DefaultContactService(contactRepository);
    }
}
