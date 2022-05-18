package com.company.service;

import com.company.entity.Card;
import com.company.enums.CardStatus;
import com.company.repository.CardRepository;
import com.company.utils.ConsoleColors;
import com.company.utils.CreditCardNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServise {
    @Autowired
    private CreditCardNumberGenerator creditCardNumberGenerator;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private ConsoleColors consoleColors;




}
