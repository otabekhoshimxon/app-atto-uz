package com.company.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
@Repository
public class AdminRepository {
    @Autowired
    private DbConnection dbConnection;


    public boolean isAdmin(String login, String password) {
        Connection connection = dbConnection.getConnection();
        String sql = "SELECT  * FROM admin where login='" + login + "' and password='" + password + "';";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return true;
            }
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
