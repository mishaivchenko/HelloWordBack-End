package persistence.inDb;

import domain.Contact;
import org.springframework.jdbc.core.JdbcTemplate;
import persistence.ContactRepository;
import persistence.inDb.aspect.PersistenceAdvice;
import persistence.inDb.mapper.ContactMapper;

import javax.sql.DataSource;
import java.util.List;

public class ContactRepositoryInDb implements ContactRepository {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setFetchSize(1000);
    }


    @Override
    @PersistenceAdvice
    public List<Contact> findAll() {
        String SQL = "SELECT * FROM contacts";
        return jdbcTemplate.query(SQL, new ContactMapper());
    }
}
