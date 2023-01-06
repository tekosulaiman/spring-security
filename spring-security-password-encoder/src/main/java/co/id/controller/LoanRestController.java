package co.id.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanRestController {
    @GetMapping("/api/v1/loans")
    public String getLoan() {
        return "Here are the loan details from the DB";
    }
}