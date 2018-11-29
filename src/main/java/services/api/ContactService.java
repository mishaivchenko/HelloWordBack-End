package services.api;

import domain.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getAll();

    List<Contact> getByRegExp(String regExp);
}
