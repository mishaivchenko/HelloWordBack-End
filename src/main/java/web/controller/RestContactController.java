package web.controller;

import domain.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.api.ContactService;

import java.util.List;

@RestController
@RequestMapping("hello")
public class RestContactController {

    private ContactService contactService;

    public RestContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("contacts")
    public ResponseEntity<List> contactList(@RequestParam String nameFilter) {
        List<Contact> contactList = contactService.getByRegExp(nameFilter);
        return contactList.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(contactList, HttpStatus.OK);
    }

}
