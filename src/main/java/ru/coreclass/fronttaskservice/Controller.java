package ru.coreclass.fronttaskservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @RequestMapping(value = "/")
    public String testMethod() {
        return "Test string";
    }
}
