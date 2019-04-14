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

    @Autowired
    StorageService storageService;

    @GetMapping("/api/table")
    public ResponseDTO table(@RequestParam(required = false) Integer id,
                                  @RequestParam(required = false) String name,
                                  @RequestParam(required = false) String order,
                                  @RequestParam Integer page,
                                  @RequestParam Integer pageSize,
                                  @RequestParam boolean withTotal) {
        List<ExampleDTO> result = exampleDAO.getDataForPage(id, name, order, page, pageSize);

        Integer pages = null;

        if (withTotal) {
            pages = exampleDAO.getTotalPages(id, name, pageSize);
        }

        return new ResponseDTO(result, pages);
    }

    @GetMapping("/api/files")
    public List<String> getFiles() {
        return storageService.getFiles();
    }



}
