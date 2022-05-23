package com.company.repository;

import com.company.entity.Card;
import com.company.entity.Terminal;
import com.company.enums.CardStatus;
import com.company.service.TerminalServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Repository
public class TerminalRepository {
    @Autowired
    private JdbcTemplate connection;
    public boolean isAvailable(String name) {
        String sql = "SELECT  * FROM terminal where name='" + name + "';";
        Terminal terminal = connection.queryForObject(sql, new BeanPropertyRowMapper<>(Terminal.class));
        return Objects.nonNull(terminal);
    }


    public boolean createTerminal(String s, String address) {

        String sql="INSERT INTO terminal (name,address)" +
                "values (?,?);";
        PreparedStatementSetter  preparedStatementSetter=new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,s);
                ps.setString(2,address);

            }
        };
        int update = connection.update(sql, preparedStatementSetter);

        if (update != 0) {
            return true;

        }
        return false;

    }

    public List<Terminal> getTerminals() {

        String sql = "SELECT * FROM terminal ;";
        List<Terminal> query = connection.query(sql, new BeanPropertyRowMapper<>(Terminal.class));
        return query;
    }

    public Terminal getTerminalByName(String name) {
        String sql = "SELECT * FROM terminal where name='"+name+"';";
        Terminal terminal = connection.queryForObject(sql, new BeanPropertyRowMapper<>(Terminal.class));
        return terminal;
    }

}
