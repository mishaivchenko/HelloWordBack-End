package persistence.inDb.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import persistence.inDb.ContactRepositoryInDb;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:config.properties")
public class InDbPersistenceConfiguration {

    @Autowired
    private Environment environment;

    @Autowired
    public InDbPersistenceConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public ContactRepositoryInDb contactRepository(DataSource dataSource) {
        ContactRepositoryInDb contactRepository = new ContactRepositoryInDb();
        contactRepository.setDataSource(dataSource());
        return contactRepository;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(environment.getProperty("driver"));
        basicDataSource.setUrl(environment.getProperty("url"));
        basicDataSource.setUsername(environment.getProperty("user"));
        basicDataSource.setPassword(environment.getProperty("password"));
        basicDataSource.setRemoveAbandoned(true);
        basicDataSource.setInitialSize(20);
        basicDataSource.setMaxActive(30);
        return basicDataSource;
    }
}
