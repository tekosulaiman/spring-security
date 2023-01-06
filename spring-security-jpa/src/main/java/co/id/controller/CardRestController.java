package co.id.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardRestController {
    @GetMapping("/api/v1/cards")
    public String getCard() {
        return "Here are the card details from the DB";
    }
}