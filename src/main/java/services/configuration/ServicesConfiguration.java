package services.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import persistence.ContactRepository;
import services.api.ContactService;
import services.impl.DefaultContactService;
import services.impl.aspect.ServiceAspect;

@Configuration
@Import(ServiceAspect.class)
public class ServicesConfiguration {

    @Bean
    public ContactService contactService(ContactRepository contactRepository) {
        return new DefaultContactService(contactRepository);
    }
}
