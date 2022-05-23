package com.company.repository;

import com.company.entity.Card;
import com.company.enums.CardStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
@Repository
public class CardRepository {
    @Autowired
    private JdbcTemplate connection;
    public boolean hasCardNumber(String cardNumber) {

        String sql = "SELECT count(*) FROM card where card_number='"+cardNumber+"' ;";

        int update = connection.queryForObject(sql,Integer.class);

        if (update != 0) {
            return true;
        }
        return false;
    }

    public boolean addCard(Card card) {

        String sql = "INSERT INTO card (card_number,balance)" +
                "values (?,?);";

        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, card.getCardNumber());
                ps.setDouble(2, card.getBalance());
            }
        };
        int update = connection.update(sql, preparedStatementSetter);

        if (update != 0) {
            return true;

        }
        return false;


    }

    public List<Card> getCardList() {
        String sql = "SELECT * FROM card ;";
        List<Card> query = connection.query(sql, new BeanPropertyRowMapper<>(Card.class));
        return query;
    }


    public Card getCardByNumber(String number) {
        String sql = "SELECT * FROM card where card_number='" + number + "'  ;";
        Card card = connection.queryForObject(sql, new BeanPropertyRowMapper<>(Card.class));
        return card;
    }

    public boolean refreshCardStatus(String s) {
        Card cardByNumber = getCardByNumber(s);
        if (cardByNumber == null) {
            return false;
        }

        String sql = "UPDATE card set card_status=? where card_number=?;";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, String.valueOf(cardByNumber.getCardStatus().equals(CardStatus.ACTIVE) ? CardStatus.BLOCKED : CardStatus.ACTIVE));
                ps.setString(2, cardByNumber.getCardNumber());
            }
        };
        int update = connection.update(sql, preparedStatementSetter);

        if (update != 0) {
            return true;

        }
        return false;
    }

    public boolean fillBalanceCard(String cardNumber, double v) {

        Card cardByNumber = getCardByNumber(cardNumber);
        if (cardByNumber == null) {
            return false;
        }
        String sql = "UPDATE card set balance=? where card_number=?;";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setDouble(1, v);
                ps.setString(2, cardNumber);
            }
        };
        int update = connection.update(sql, preparedStatementSetter);

        if (update != 0) {
            return true;
        }
        return false;
    }

    public Card getCardById(int i) {
        String sql = "SELECT * FROM card where id='" + i + "'  ;";
        Card card = connection.queryForObject(sql, new BeanPropertyRowMapper<>(Card.class));
        return  card;
    }

}
