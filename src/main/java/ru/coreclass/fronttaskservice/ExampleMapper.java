package ru.coreclass.fronttaskservice;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExampleMapper implements RowMapper<ExampleDTO> {

    @Override
    public ExampleDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        ExampleDTO dto = new ExampleDTO();
        dto.setId(resultSet.getInt("id"));
        dto.setName(resultSet.getString("name"));
        dto.setValue(resultSet.getFloat("value"));

        return dto;
    }
}
