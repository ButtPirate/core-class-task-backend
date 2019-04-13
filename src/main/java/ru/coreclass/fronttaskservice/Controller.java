package ru.coreclass.fronttaskservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ExampleDAO exampleDAO;

    @GetMapping("/api/table")
    public List<ExampleDTO> testMethod(@RequestParam(required = false) Integer id,
                                       @RequestParam(required = false) String name,
                                       @RequestParam(required = false) String order) {
        return exampleDAO.find(id, name, order);
    }
}
