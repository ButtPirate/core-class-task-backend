package ru.coreclass.fronttaskservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<String> getFiles() {
        String sql = "SELECT filename FROM files";

        return jdbcTemplate.queryForList(sql, String.class);
    }

}
