package com.company.repository;

import java.sql.*;

public class AdminRepository {

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


    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
}
