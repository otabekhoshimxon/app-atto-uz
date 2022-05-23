package com.company.repository;

import com.company.entity.Card;
import com.company.entity.Terminal;
import com.company.entity.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
@Repository
public class TransferRepository {
    @Autowired
    private JdbcTemplate connection;

    public boolean transferMoney(Integer cardId, Integer terminalId, Double amountMoney, double balance) {

        String sql="BEGIN;" +
                " INSERT INTO transfer (card_id,terminal_id,created_date,amount)" +
                "values (?,?,?,?);" +
                "UPDATE card set balance=? where id=?;" +
                "UPDATE card set balance=? where id=1;" +
                "COMMIT ;";
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,cardId);
                preparedStatement.setInt(2,terminalId);
                preparedStatement.setTimestamp(3, Timestamp.from(Instant.now()));
                preparedStatement.setDouble(4,amountMoney);
                preparedStatement.setDouble(5,amountMoney);
                preparedStatement.setInt(6,cardId);
                preparedStatement.setDouble(7,balance+1400.0);
            }
        };
        int update = connection.update(sql, preparedStatementSetter);

        if (update != 0) {
            return true;

        }
        return false;

    }



    public List<Transfer> getTransferList() {
        String sql="SELECT * from get_transfer_list";
        List<Transfer> query = connection.query(sql, new BeanPropertyRowMapper<>(Transfer.class));
        return query;
    }


    public boolean fillBalance(Card cardByNumber, Terminal terminal, Double money) {


        String sql="BEGIN;" +
                " INSERT INTO transfer (card_id,terminal_id,created_date,amount)" +
                "values (?,?,?,?);" +
                "UPDATE card set balance=? where id=?;" +
                ";" +
                "COMMIT ;";

        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,cardByNumber.getId());
                preparedStatement.setInt(2,terminal.getId());
                preparedStatement.setTimestamp(3, Timestamp.from(Instant.now()));
                preparedStatement.setDouble(4,money);
                preparedStatement.setDouble(5,money);
                preparedStatement.setInt(6,cardByNumber.getId());
            }
        };
        int update = connection.update(sql, preparedStatementSetter);

        if (update != 0) {
            return true;

        }
        return false;

        }



    }

