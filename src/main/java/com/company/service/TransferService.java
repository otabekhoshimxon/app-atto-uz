package com.company.service;

import com.company.entity.Card;
import com.company.entity.Terminal;
import com.company.entity.Transfer;
import com.company.repository.CardRepository;
import com.company.repository.DbConnection;
import com.company.repository.TerminalRepository;
import com.company.repository.TransferRepository;
import com.company.utils.ConsoleColors;
import com.company.utils.Scan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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



        Card cardByNumber = cardRepository.getCardByNumber(cardNumber);
        Card adminCard = cardRepository.getCardById(1);


        Terminal terminalByName = terminalRepository.getTerminalByName(terminalName);

        if (cardByNumber.getBalance()>1400)
        {
            double v = 1400-cardByNumber.getBalance();
            boolean b = transferRepository.transferMoney(cardByNumber.getId(), terminalByName.getId(),v ,adminCard.getBalance());
            if (!b){
                consoleColors.print(ConsoleColors.GREEN,"SUCCESSFULLY TRANSFERED");
            }
        }
        else {
            consoleColors.print(ConsoleColors.RED,"NOT  TRANSFERED");

        }


    }
    public void getTransferListByTerminalName()
    {
        consoleColors.print(ConsoleColors.BLUE,"ENTER TERMINAL NAME ");
        String name = Scan.SCANNER_STR.nextLine();


        List<Transfer> transferList =transferRepository.getTransferList();
        for (Transfer transfer : transferList) {
            if (transfer.getTerminal_name().equals(name))
            {
                consoleColors.print(ConsoleColors.BLUE,transfer.getId()+ConsoleColors.YELLOW_BOLD+" | "+
                        transfer.getCard_number()+" | "+ConsoleColors.PURPLE+transfer.getTerminal_name()+" | "+ConsoleColors.YELLOW_BOLD+transfer.getTimestamp()+" | "+ConsoleColors.RED_BOLD+transfer.getAmount());
            }
      }

    }


    public void getAllTransfers() {
        List<Transfer> transferList =transferRepository.getTransferList();
        for (Transfer transfer : transferList) {
            consoleColors.print(ConsoleColors.BLUE,transfer.getId()+ConsoleColors.YELLOW_BOLD+" | "+
                    transfer.getCard_number()+" | "+ConsoleColors.PURPLE+transfer.getTerminal_name()+" | "+ConsoleColors.YELLOW_BOLD+transfer.getTimestamp()+" | "+ConsoleColors.RED_BOLD+transfer.getAmount());
        }

    }

    public void getTransferListByCardNumber() {
        consoleColors.print(ConsoleColors.BLUE,"ENTER CARD NUMBER ");
        String cardNumber = Scan.SCANNER_STR.nextLine();
        consoleColors.print(ConsoleColors.BLUE,"ENTER TERMINAL NAME ");
        String name = Scan.SCANNER_STR.nextLine();



        List<Transfer> transferList =transferRepository.getTransferList();
        for (Transfer transfer : transferList) {
            if (transfer.getCard_number().equals(cardNumber))
            {
                consoleColors.print(ConsoleColors.BLUE,transfer.getId()+ConsoleColors.YELLOW_BOLD+" | "+
                        transfer.getCard_number()+" | "+ConsoleColors.PURPLE+transfer.getTerminal_name()+" | "+ConsoleColors.YELLOW_BOLD+transfer.getTimestamp()+" | "+ConsoleColors.RED_BOLD+transfer.getAmount());
                break;
            }
        }

    }

    public void fillBalance() {
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
        consoleColors.print(ConsoleColors.BLUE, "ENTER AMOUNT MONEY ");
        String money = Scan.SCANNER_STR.nextLine();


        Card cardByNumber = cardRepository.getCardByNumber(cardNumber);
        Terminal terminalByName = terminalRepository.getTerminalByName(terminalName);
        if (Double.parseDouble(money)>0)
        {
            boolean b = transferRepository.fillBalance(cardByNumber, terminalByName, Double.parseDouble(cardByNumber.getBalance() + money));
            if (!b){
                consoleColors.print(ConsoleColors.GREEN,"SUCCESSFULLY TRANSFERED");
            }
        }
        else {
            consoleColors.print(ConsoleColors.RED,"NOT  TRANSFERED");

        }

    }
}
