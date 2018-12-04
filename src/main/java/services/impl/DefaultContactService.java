package services.impl;

import domain.Contact;
import persistence.ContactRepository;
import services.api.ContactService;
import services.impl.aspect.ServiceAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultContactService implements ContactService {

    private ContactRepository contactRepository;

    public DefaultContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    @Override
    @ServiceAdvice
    public List<Contact> getByRegExp(String regExp) {
        List<Contact> contactList = contactRepository.findAll();
        List<Contact> filteredList = new ArrayList<>();
        Pattern pattern = Pattern.compile(regExp);
        for (Contact c : contactList) {
            Matcher matcher = pattern.matcher(c.getName());
            if (!matcher.matches() && !regExp.equals(c.getName())) {
                filteredList.add(c);
            }
        }
        return filteredList;
    }
}
