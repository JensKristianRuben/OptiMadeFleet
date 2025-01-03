package com.example.optimadefleet.repository;

import com.example.optimadefleet.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoginRepository {
    @Autowired
    JdbcTemplate jdbctemplate;

    public List<Login> fetchAndFindLoginDetails(String user_name){
        String sqlUserName = "SELECT * FROM login WHERE user_name = ?";

        return jdbctemplate.query(sqlUserName, new BeanPropertyRowMapper<>(Login.class), user_name);
    }
}
