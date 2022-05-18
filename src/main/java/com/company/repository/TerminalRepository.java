package com.company.repository;

import com.company.entity.Card;
import com.company.entity.Terminal;
import com.company.enums.CardStatus;
import com.company.service.TerminalServise;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TerminalRepository {
    private DbConnection dbConnection;





    public boolean isAvailable(String name) {
        Connection connection = dbConnection.getConnection();
        String sql = "SELECT  * FROM terminal where name='" + name + "';";
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


    public boolean createTerminal(String s, String address) {
        Connection connection = dbConnection.getConnection();
        String sql="INSERT INTO terminal (name,address)" +
                "values (?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,s);
            preparedStatement.setString(2,address);
            boolean execute = preparedStatement.execute();
            connection.close();
            return execute;



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<Terminal> getTerminals() {
        Connection connection = dbConnection.getConnection();
        String sql = "SELECT * FROM terminal ;";
        try {
            List<Terminal> terminalList = new LinkedList<>();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                Terminal terminal = new Terminal();
                terminal.setName(name);
                terminal.setAddress(address);
                terminalList.add(terminal);


            }
            connection.close();
            return terminalList;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
}
