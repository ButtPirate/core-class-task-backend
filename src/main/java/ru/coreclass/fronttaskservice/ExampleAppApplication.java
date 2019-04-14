package ru.coreclass.fronttaskservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class ExampleAppApplication {


    public static void main(String[] args) {
        SpringApplication.run(ExampleAppApplication.class, args);
    }

}
