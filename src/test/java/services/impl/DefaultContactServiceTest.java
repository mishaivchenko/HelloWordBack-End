package services.impl;

import domain.Contact;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import persistence.inDb.config.InDbPersistenceConfiguration;
import services.api.ContactService;
import services.configuration.ServicesConfiguration;

import java.util.List;
import java.util.function.Predicate;

public class DefaultContactServiceTest {

    private ContactService contactService;

    @Before
    public void setUp() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                ServicesConfiguration.class,
                InDbPersistenceConfiguration.class);
        contactService = applicationContext.getBean("contactService", DefaultContactService.class);
    }

    @Test
    public void TestMustReturnAll() {
        //Given
        List list = contactService.getAll();
        //When
        List listByRegExp = contactService.getByRegExp(":");
        //Then
        Assertions.assertEquals(list.size(), listByRegExp.size());
    }

    @Test
    public void TestReturnedListShouldNotContainsMisha() {
        //Given
        Contact contact = new Contact(1L, "Misha");
        //When
        List list1 = contactService.getAll();
        List list = contactService.getByRegExp("Misha");
        //Then
        Assertions.assertTrue(list1.contains(contact));
        Assertions.assertFalse(list.contains(contact));
    }

    @Test
    public void TestMustReturnContactsWhatNamesNotBeginAtCharA() {
        //Given
        Predicate<Contact> p = e -> e.getName().startsWith("A");
        String pattern = "^A.*$";
        boolean expected = false;
        //When
        List<Contact> list = contactService.getByRegExp(pattern);
        boolean result = list.stream().anyMatch(p);
        //Then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void TestListMustNotContainsUserWithNameWhatEqualsToRegExp() {
        //Given
        String pattern = "^A.*$";
        Predicate<Contact> p = e -> e.getName().equals(pattern);
        boolean expected = false;
        //When
        List<Contact> list = contactService.getByRegExp(pattern);
        boolean result = list.stream().anyMatch(p);
        //Then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void TestMustReturnContactsWhatNamesNotContainsChars_aei() {
        //Given
        Predicate<Contact> p = e -> e.getName().contains("a") ||
                e.getName().contains("e") ||
                e.getName().contains("i");

        String pattern = "^.*[aei].*$";
        boolean expected = false;
        //When
        List<Contact> list = contactService.getByRegExp(pattern);
        boolean result = list.stream().anyMatch(p);
        //Then
        Assertions.assertEquals(expected, result);
    }
}
