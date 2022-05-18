package com.company.repository;

import com.company.entity.Card;
import com.company.enums.CardStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
@Repository
public class TransferRepository {
    @Autowired
    private DbConnection dbConnection;




    public boolean transferMoney(Integer cardId, Integer terminalId, Double amountMoney) {

        Connection connection = dbConnection.getConnection();
        String sql="INSERT INTO transfer (card_id,terminal_id,created_date,amount)" +
                "values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,cardId);
            preparedStatement.setInt(2,terminalId);
            preparedStatement.setTimestamp(3, Timestamp.from(Instant.now()));
            preparedStatement.setDouble(4,amountMoney);
            boolean execute = preparedStatement.execute();
            connection.close();
            return execute;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

/*    public List<Transfer> getTransferList()
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
    */



}
