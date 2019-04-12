package ru.coreclass.fronttaskservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableAutoConfiguration
@SpringBootApplication
public class FrontTaskServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontTaskServiceApplication.class, args);
    }

}
