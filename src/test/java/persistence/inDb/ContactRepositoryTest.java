package persistence.inDb;

import domain.Contact;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import persistence.ContactRepository;
import persistence.inDb.config.InDbPersistenceConfiguration;

import java.util.List;

public class ContactRepositoryTest {
    private ContactRepository contactRepository;

    @Before
  public void setUp() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(InDbPersistenceConfiguration.class);
        contactRepository = applicationContext.getBean("contactRepository", ContactRepositoryInDb.class);
    }

    @Test
    public void TestMustReturnAllContactsFromDb() {
        //Given
        int expected = 15;
        //When
        List<Contact> contactList = contactRepository.findAll();
        //Then
        Assertions.assertEquals(expected, contactList.size());
    }

    @Test
    public void TestMustReturnFirstElementOfListAndElementMustBeEqualMisha() {
        //Given
        String expactedName = "Misha";
        //When
        Contact contact = contactRepository.findAll().get(0);
        //Then
        Assertions.assertEquals(expactedName, contact.getName());
    }

    @Test
    public void TestMustReturnLastElementOfListAndElementMustBeEqualNatasha() {
        //Given
        String expectedName = "Natasha";
        //When
        List<Contact> contactList = contactRepository.findAll();
        //Then
        Assertions.assertEquals(expectedName, contactList.get(contactList.size() - 1).getName());
    }
}
