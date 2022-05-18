package com.company.service;

import com.company.entity.Card;
import com.company.entity.Terminal;
import com.company.repository.CardRepository;
import com.company.repository.DbConnection;
import com.company.repository.TerminalRepository;
import com.company.repository.TransferRepository;
import com.company.utils.ConsoleColors;
import com.company.utils.Scan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    @Autowired
    private ConsoleColors consoleColors;
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private  CardRepository cardRepository;
    @Autowired
    private TerminalRepository terminalRepository;



    public void transfer() {

        consoleColors.print(ConsoleColors.BLUE, "ENTER CARD NUMBER ");
        String cardNumber = Scan.SCANNER_STR.nextLine();

        consoleColors.print(ConsoleColors.BLUE, "ENTER TERMINAL NAME ");
        String terminalName = Scan.SCANNER_STR.nextLine();


        if (!cardRepository.hasCardNumber(cardNumber))
        {
            consoleColors.print(ConsoleColors.RED, "NOT FOUND CARD NUMBER ");
            return;
        }
        if (!terminalRepository.isAvailable(terminalName))
        {
            consoleColors.print(ConsoleColors.RED, "NOT FOUND TERMINAL NAME ");
            return;
        }
        consoleColors.print(ConsoleColors.BLUE,"ENTER THE CARD BALANCE FILLMENT AMOUNT");
        String amountMoney = Scan.SCANNER_STR.nextLine();


        if (Double.parseDouble(amountMoney)<0 ||amountMoney==null)
        {
            return;
        }
        Card cardByNumber = cardRepository.getCardByNumber(cardNumber);
        Terminal terminalByName = terminalRepository.getTerminalByName(terminalName);

        boolean b = transferRepository.transferMoney(cardByNumber.getId(), terminalByName.getId(), Double.parseDouble(amountMoney) + cardByNumber.getBalance());
        if (b){
            consoleColors.print(ConsoleColors.GREEN,"SUCCESFULLY TRANSFERED");
        }
        else {
            consoleColors.print(ConsoleColors.RED,"SUCCESFULLY TRANSFERED");

        }


    }


}
