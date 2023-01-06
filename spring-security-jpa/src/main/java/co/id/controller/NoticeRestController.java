package co.id.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeRestController {
    @GetMapping("/api/v1/notices")
    public String getNotice() {
        return "Here are the notice details from the DB";
    }
}