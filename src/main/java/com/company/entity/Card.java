package com.company.entity;

import com.company.enums.CardStatus;
import lombok.Data;

@Data
public class Card {

    private String id;
    private String cardNumber;
    private double balance;
    private CardStatus cardStatus=CardStatus.ACTIVE;


}
