package com.expensetracker.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoApi {

    @GetMapping("/hello")
    public String greet() {
        return "Hello";
    }
}
