package com.company.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Transfer {

        private Integer id;
        private String card_number;
        private String terminal_name;
        private Timestamp timestamp;
        private double amount;



}
