package persistence.inDb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import persistence.inDb.ContactRepositoryInDb;

import javax.sql.DataSource;

@Configuration
public class InDbPersistenceConfiguration {
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")
                .addScript("test-data.sql")
                .build();
    }

    @Bean
    public ContactRepositoryInDb contactRepository() {
        ContactRepositoryInDb contactRepository = new ContactRepositoryInDb();
        contactRepository.setDataSource(dataSource());
        return contactRepository;
    }

}
