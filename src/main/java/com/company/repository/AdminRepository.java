package com.company.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class AdminRepository {
    @Autowired
    private JdbcTemplate connection;
    public boolean isAdmin(String login, String password) {
        String sql = "SELECT  COUNT (*)FROM admin where login='" + login + "' and password='" + password + "';";
        Integer integer = connection.queryForObject(sql,Integer.class);
        if (integer!=0)
        {
            return true;
        }
        return false;
    }

}
