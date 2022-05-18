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
public class CardService {
    @Autowired

    private CreditCardNumberGenerator creditCardNumberGenerator;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private ConsoleColors consoleColors;





    public void createCard(String amount) {

        String cardNumber = creditCardNumberGenerator.generate("8600", 16);
        if (!cardRepository.hasCardNumber(cardNumber))
        {
            consoleColors.print(ConsoleColors.RED_BOLD," This card number already taken ! ");
            return;
        }

        Card card=new Card();
        card.setCardNumber(cardNumber);
        card.setBalance(Double.parseDouble(amount));
        if (!cardRepository.addCard(card))
        {
            consoleColors.print(ConsoleColors.GREEN,"SUCCESFULLY CREATED CARD");
            return;
        }


    }


    public void showCards() {
        List<Card> cardList = cardRepository.getCardList();
        if (cardList==null)
        {
            consoleColors.print(ConsoleColors.RED_BOLD,"CARDS NOT FOUND!");
            return;

        }
        consoleColors.print(ConsoleColors.GREEN_BOLD," ID "+ConsoleColors.BLUE+" "+" CARD NUMBER "+ConsoleColors.YELLOW_BOLD+"        CARD BALANCE "+ConsoleColors.YELLOW_BOLD+"         CARD STATUS ");

        for (Card card : cardList) {


            consoleColors.print(ConsoleColors.GREEN_BOLD," "+card.getId()+"  "+ConsoleColors.BLUE+"  "+card.getCardNumber()+ConsoleColors.YELLOW_BOLD+"    "+card.getBalance()+((card.getCardStatus().equals(CardStatus.ACTIVE)) ?ConsoleColors.GREEN_BOLD+"                  "+card.getCardStatus():ConsoleColors.RED_BOLD+"                  "+card.getCardStatus()));
        }
    }

    public void changeStatusCard(String s) {
        boolean b = cardRepository.refreshCardStatus(s);

        if (b)
        {
            consoleColors.print(ConsoleColors.RED_BOLD,"CARD NOT FOUND ");
        }
        else {
            consoleColors.print(ConsoleColors.YELLOW_BOLD,"SUCCESSFULLY CHANGED STATUS CARD ");

        }
    }
    public void setCreditCardNumberGenerator(CreditCardNumberGenerator creditCardNumberGenerator) {
        this.creditCardNumberGenerator = creditCardNumberGenerator;
    }



}
