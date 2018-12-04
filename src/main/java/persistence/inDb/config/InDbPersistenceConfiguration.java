package persistence.inDb.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import persistence.inDb.ContactRepositoryInDb;
import persistence.inDb.aspect.PersistenceAspect;

import javax.sql.DataSource;

@Import(PersistenceAspect.class)
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class InDbPersistenceConfiguration extends HikariConfig {


    @Bean
    public ContactRepositoryInDb contactRepository() {
        ContactRepositoryInDb contactRepository = new ContactRepositoryInDb();
        contactRepository.setDataSource(dataSource());
        return contactRepository;
    }


    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(this);
    }
}
