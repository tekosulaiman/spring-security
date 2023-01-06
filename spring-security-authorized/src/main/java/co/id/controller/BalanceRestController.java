package co.id.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceRestController {
    @GetMapping("/api/v1/balances")
    public String getBalance() {
        return "Here are the balance details from the DB";
    }
}