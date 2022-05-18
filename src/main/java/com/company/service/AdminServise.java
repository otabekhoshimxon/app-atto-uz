package com.company.service;

import com.company.entity.Card;
import com.company.enums.CardStatus;
import com.company.repository.CardRepository;
import com.company.utils.ConsoleColors;
import com.company.utils.CreditCardNumberGenerator;

import java.util.List;

public class AdminServise {
    private CreditCardNumberGenerator creditCardNumberGenerator;
    private CardRepository cardRepository;
    private ConsoleColors consoleColors;





    public void setCardRepository(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void setCreditCardNumberGenerator(CreditCardNumberGenerator creditCardNumberGenerator) {
        this.creditCardNumberGenerator = creditCardNumberGenerator;
    }

    public void setConsoleColors(ConsoleColors consoleColors) {
        this.consoleColors = consoleColors;
    }


}
