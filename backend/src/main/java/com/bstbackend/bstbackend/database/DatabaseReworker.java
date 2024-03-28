package com.bstbackend.bstbackend.database;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class DatabaseReworker {
    private final JdbcTemplate jdbcTemplate;

    public DatabaseReworker(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    private void init() {
        List<DatabaseObject> list = getNamesAndIds();

        for (DatabaseObject databaseObject : list) {
            analyzeObject(databaseObject);
        }
    }

    private void analyzeObject(DatabaseObject databaseObject) {
        String sql = "INSERT INTO catalog_product (id, short_name) " +
                "VALUES (?, ?) " +
                "ON CONFLICT (id) DO UPDATE SET " +
                "short_name = EXCLUDED.short_name";
            jdbcTemplate.update(
                    sql,
                    databaseObject.getId(),
                    handleShortName(databaseObject.getName())

            );

        System.out.println("Set for id " + databaseObject.getId());
    }

    private String handleShortName(String name) {
        String[] args = name.split(",");

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < args.length; i++) {
            if(args[i].contains("цена"))
                continue;

            stringBuilder.append(args[i]);
            if(i < args.length - 2)
                stringBuilder.append(",");
        }

        return stringBuilder.toString();
    }


    private List<DatabaseObject> getNamesAndIds() {
        String sql = "SELECT id, name FROM catalog_product";

        return jdbcTemplate.query(sql, new DatabaseObjectRowMapper());
    }

    class DatabaseObjectRowMapper implements RowMapper<DatabaseObject> {
        @Override
        public DatabaseObject mapRow(ResultSet rs, int rowNum) throws SQLException {
            DatabaseObject entity = new DatabaseObject();
            entity.setId(rs.getLong("id"));
            entity.setName(rs.getString("name"));
            return entity;
        }
    }
}
