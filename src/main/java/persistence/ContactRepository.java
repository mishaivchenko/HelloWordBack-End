package persistence;

import domain.Contact;

import java.util.List;

public interface ContactRepository {

    List<Contact> findAll();

}
