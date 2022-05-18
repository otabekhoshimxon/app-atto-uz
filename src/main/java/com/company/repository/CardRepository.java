package com.company.repository;

import com.company.entity.Card;
import com.company.enums.CardStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
@Repository
public class CardRepository {
    @Autowired
    private  DbConnection dbConnection;














    public boolean hasCardNumber(String cardNumber) {
        Connection connection = dbConnection.getConnection();
        String sql="SELECT * FROM card where card_number=? ;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,cardNumber);
            boolean execute = preparedStatement.execute();
            connection.close();
            return execute;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean addCard(Card card) {
        Connection connection = dbConnection.getConnection();
        String sql="INSERT INTO card (card_number,balance)" +
                "values (?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,card.getCardNumber());
            preparedStatement.setDouble(2,card.getBalance());
            boolean execute = preparedStatement.execute();
            connection.close();
            return execute;



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<Card> getCardList()
    {
        Connection connection = dbConnection.getConnection();
        String sql="SELECT * FROM card ;";
        try {
            List<Card> cards=new LinkedList<>();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                String id = resultSet.getString("id");
                String card_number = resultSet.getString("card_number");
                double balance = resultSet.getDouble("balance");
                String card_status = resultSet.getString("card_status");
                Card card=new Card();
                card.setId(Integer.valueOf(id));
                card.setCardNumber(card_number);
                card.setBalance(balance);
                card.setCardStatus(CardStatus.valueOf(card_status));
                cards.add(card);


            }
            connection.close();
            return  cards;



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }   public Card getCardByNumber(String number)
    {
        Connection connection = dbConnection.getConnection();
        String sql="SELECT * FROM card where card_number='"+number+"'  ;";
        try {


            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next())
            {
                String id = resultSet.getString("id");
                String card_number = resultSet.getString("card_number");
                double balance = resultSet.getDouble("balance");
                String card_status = resultSet.getString("card_status");
                Card card=new Card();
                card.setId(Integer.valueOf(id));
                card.setCardNumber(card_number);
                card.setBalance(balance);
                card.setCardStatus(CardStatus.valueOf(card_status));
                return card;
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }

    public boolean refreshCardStatus(String s) {
        Card cardByNumber = getCardByNumber(s);
        if (cardByNumber==null)
        {
            return false;
        }

        Connection connection = dbConnection.getConnection();
        String sql="UPDATE card set card_status=? where card_number=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(cardByNumber.getCardStatus().equals(CardStatus.ACTIVE)?CardStatus.BLOCKED:CardStatus.ACTIVE));
            preparedStatement.setString(2,cardByNumber.getCardNumber());
            boolean execute = preparedStatement.execute();
            connection.close();
            return execute;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
