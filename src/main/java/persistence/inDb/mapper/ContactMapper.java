package persistence.inDb.mapper;

import domain.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ContactMapper implements RowMapper<Contact> {

    @Override
    public Contact mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Contact(resultSet.getLong("id"), resultSet.getString("name"));
    }
}
