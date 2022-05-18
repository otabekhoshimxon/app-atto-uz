package com.company.repository;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Repository
public class DbConnection {


    public   Connection getConnection()
    {
        String url="jdbc:postgresql://localhost:5432/app_atto_db";
        String username="postgres";
        String password="otabek";

        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            if (connection!=null)
            {
                return connection;
            }


        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return  null;
    }
}
