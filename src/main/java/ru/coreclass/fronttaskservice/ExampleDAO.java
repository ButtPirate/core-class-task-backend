package ru.coreclass.fronttaskservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ExampleDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    List<ExampleDTO> find(Integer id, String name, String order) {
        String sql = this.buildSelectSQL(id, name, order);
        Map<String, Object> params = new HashMap<>();

        if (id != null) {
            params.put("id", id);
        }

        if (name != null) {
            params.put("name", name);
        }

        return namedParameterJdbcTemplate.query(sql, params, new ExampleMapper());

    }

    private String buildSelectSQL(Integer id, String name, String order) {
        StringBuilder builder = new StringBuilder();

        builder.append("SELECT * FROM test_data");

        boolean whereCounter = false;

        if (id != null) {
            builder.append(" WHERE id = :id");
            whereCounter = true;
        }

        if (name != null) {
            if (!whereCounter) {
                builder.append(" AND name = :name");
            } else {
                builder.append(" WHERE name = :name");
            }
        }

        if (order == null) {
            order = "id_desc";
        }

        String orderSQLString="";

        switch (order) {
            case "id_asc":
                orderSQLString = " ORDER BY id ASC";
                break;
            default:
            case "id_desc":
                orderSQLString = " ORDER BY id DESC";
                break;
            case "name_asc":
                orderSQLString = " ORDER BY name ASC";
                break;
            case "name_desc":
                orderSQLString = " ORDER BY name DESC";
                break;
            case "value_asc":
                orderSQLString = " ORDER BY value ASC";
                break;
            case "value_desc":
                orderSQLString = " ORDER BY value DESC";
                break;

        }

        builder.append(orderSQLString);
        return builder.toString();
    }


}
