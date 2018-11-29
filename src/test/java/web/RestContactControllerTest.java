package web;

import domain.Contact;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import persistence.ContactRepository;
import persistence.inDb.config.InDbPersistenceConfiguration;
import services.api.ContactService;
import services.configuration.ServicesConfiguration;
import web.config.WebConfiguration;
import web.controller.RestContactController;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SpringJUnitWebConfig(classes = {InDbPersistenceConfiguration.class, ServicesConfiguration.class, WebConfiguration.class})
public class RestContactControllerTest {
    @Mock
    private ContactService contactService;
    @Mock
    private ContactRepository contactRepository;
    @InjectMocks
    private RestContactController restContactController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldGetAllContacts() {
        //Given
        List<Contact> list = Arrays.asList(
                new Contact(1L, "Dima"),
                new Contact(2L, "Stephen"));

        //When
        Mockito.when(contactService.getByRegExp(":")).thenReturn(list);
        ResponseEntity<List> responseEntity = restContactController.contactList(":");
        //Then
        Assertions.assertEquals(Objects.requireNonNull(responseEntity.getBody()).size(), list.size());

    }

    @Test
    public void TestResponseShouldHaveStatusCode200Ok() {
        //Given
        List<Contact> list = Arrays.asList(
                new Contact(1L, "Dima"),
                new Contact(2L, "Stephen"));
        HttpStatus expectedHttpStatus = HttpStatus.OK;
        //When
        Mockito.when(contactService.getByRegExp(":")).thenReturn(list);
        ResponseEntity<List> responseEntity = restContactController.contactList(":");
        //Then
        Assertions.assertEquals(responseEntity.getStatusCode(), expectedHttpStatus);
    }


    @Test
    public void TestResponseShouldHaveStatus204NoContent() throws Exception {
        //Given
        List<Contact> list = Arrays.asList(
                new Contact(1L, "Dima"),
                new Contact(2L, "Stephen"));
        HttpStatus expectedHttpStatus = HttpStatus.NO_CONTENT;
        //When
        Mockito.when(contactRepository.findAll()).thenReturn(list);
        ResponseEntity<List> responseEntity = restContactController.contactList("/hello/contacts?nameFilter=^[^A].*$");
        //Then
        Assertions.assertEquals(responseEntity.getStatusCode(), expectedHttpStatus);
    }
}
