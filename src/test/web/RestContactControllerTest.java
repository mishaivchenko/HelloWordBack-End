package web;

import domain.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import persistence.ContactRepository;
import persistence.inDb.config.InDbPersistenceConfiguration;
import services.api.ContactService;
import services.configuration.ServicesConfiguration;
import web.config.WebConfiguration;
import web.controller.RestContactController;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(classes = {InDbPersistenceConfiguration.class, ServicesConfiguration.class, WebConfiguration.class})
class RestContactControllerTest {
    @Mock
    ContactService contactService;
    @Mock
    ContactRepository contactRepository;
    @InjectMocks
    RestContactController restContactController;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void shouldGetAllContacts() {
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
    void TestResponseShouldHaveStatusCode200Ok() {
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
    void TestResponseShouldHaveStatus400BadRequest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/hello/contacts"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void TestResponseShouldHaveStatus204NoContent() throws Exception {
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
