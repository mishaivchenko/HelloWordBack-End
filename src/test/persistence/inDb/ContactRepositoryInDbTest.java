package persistence.inDb;

import domain.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import persistence.ContactRepository;
import persistence.inDb.config.InDbPersistenceConfiguration;

import java.util.List;

class ContactRepositoryInDbTest {
    private ContactRepository contactRepository;

    @BeforeEach
    void setUp() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(InDbPersistenceConfiguration.class);
        contactRepository = applicationContext.getBean("contactRepository", ContactRepositoryInDb.class);
    }

    @Test
    void TestMustReturnAllContactsFromDb() {
        //Given
        int expected = 15;
        //When
        List<Contact> contactList = contactRepository.findAll();
        //Then
        Assertions.assertEquals(expected, contactList.size());
    }

    @Test
    void TestMustReturnFirstElementOfListAndElementMustBeEqualMisha() {
        //Given
        String expactedName = "Misha";
        //When
        Contact contact = contactRepository.findAll().get(0);
        //Then
        Assertions.assertEquals(expactedName, contact.getName());
    }

    @Test
    void TestMustReturnLastElementOfListAndElementMustBeEqualNatasha() {
        //Given
        String expectedName = "Natasha";
        //When
        List<Contact> contactList = contactRepository.findAll();
        //Then
        Assertions.assertEquals(expectedName, contactList.get(contactList.size() - 1).getName());
    }
}
