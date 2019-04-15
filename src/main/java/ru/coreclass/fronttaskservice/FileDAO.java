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
        String sql = "SELECT original_filename FROM files";

        return jdbcTemplate.queryForList(sql, String.class);
    }

    public void createFileRecord(String originalFilename, String savedFilename, Integer contentId) {
        String sql = "INSERT INTO files (timestamp, original_filename, saved_filename, content_id) VALUES (NOW(), ?, ?, ?)";

        jdbcTemplate.update(sql, originalFilename, savedFilename, contentId);
    }

    public Integer saveContent(String content) {
        String sql = "INSERT INTO content (content) VALUES (?)";

        return jdbcTemplate.update(sql, content);

    }
}
