package co.id.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactRestController {
    @GetMapping("/api/v1/contacts")
    public String getContact() {
        return "Here are the contact details from the DB";
    }
}