package co.id.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountRestController {
    @GetMapping("/api/v1/accounts")
    public String getAccount() {
        return "Here are the account details from the DB";
    }
}