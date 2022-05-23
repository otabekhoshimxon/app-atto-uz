package com.company.controller;

import com.company.entity.Card;
import com.company.entity.Transfer;
import com.company.repository.TransferRepository;
import com.company.service.AdminServise;
import com.company.service.CardService;
import com.company.service.TerminalServise;
import com.company.service.TransferService;
import com.company.utils.ConsoleColors;
import com.company.utils.Scan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AdminController  {
    @Autowired
     private ConsoleColors consoleColors;
    @Autowired
     private CardService cardService;
    @Autowired
     private TerminalServise terminalServise;
    @Autowired
     private TransferService transferService;




    public void start() {

        while (true)
        {
            showAdminMenu();

            consoleColors.print(ConsoleColors.BLUE,"ENTER ACTION ");
            int i = Scan.SCANNER_INT.nextInt();
            actionMenu(i);

        }



    }

    public void showAdminMenu()
    {
        consoleColors.printPlus(ConsoleColors.WHITE_BACKGROUND,"                                    ");
        consoleColors.print(ConsoleColors.GREEN," ********** ADMIN MENU *************");
        consoleColors.print(ConsoleColors.GREEN," 1.  ADD CARD                       ");
        consoleColors.print(ConsoleColors.GREEN," 2.  CARD LIST                      ");
        consoleColors.print(ConsoleColors.GREEN," 3.  CHANGE CARD STATUS             ");
        consoleColors.print(ConsoleColors.GREEN," 4.  CARD INFO                      ");
        consoleColors.print(ConsoleColors.GREEN," 5.  ADD TERMINAL                   ");
        consoleColors.print(ConsoleColors.GREEN," 6.  TERMINAL LIST                  ");
        consoleColors.print(ConsoleColors.GREEN," 7.  ADMIN CARD                     ");
        consoleColors.print(ConsoleColors.GREEN," 8.  TRANSFER LIST                  ");
        consoleColors.print(ConsoleColors.GREEN," 9.  TRANSFER LIST BY CARD          ");
        consoleColors.print(ConsoleColors.GREEN," 10. TRANSFER LIST BY TERMINAL NAME ");

    }

    public void actionMenu(int i)
    {
        switch (i)
        {
            case 1 -> {
               addCard();
                break;
            }
            case 2 -> {
               cardList();
                break;
            }
             case 3 -> {
               changeCardStatus();
                break;
            }
            case 5 -> {
               getTerminal();
                break;
            }
            case 6 -> {
               getTerminalList();
                break;
            }
            case 7 -> {
               getAdminCard();
                break;
            }
           case 8 -> {
               getTransferList();
                break;
            }
            case 9 -> {
               getTransferListByNumberCard();
                break;
            }
            case 10 -> {
               getTransferListByTerminalName();
                break;
            }




        }

    }

    private void getTransferListByTerminalName() {
     transferService.getTransferListByTerminalName();
    }

    private void getTransferListByNumberCard() {

        transferService.getTransferListByCardNumber();


    }

    private void getAdminCard() {
        cardService.showCardById(1);
    }

    private void getTransferList() {
        transferService.getAllTransfers();
    }

    private void getTerminalList() {
        terminalServise.getAllTerminals();

    }

    private void getTerminal() {

        consoleColors.print(ConsoleColors.BLUE,"ENTER TERMINAL NAME ");
        String name = Scan.SCANNER_STR.nextLine();

         consoleColors.print(ConsoleColors.BLUE,"ENTER TERMINAL ADDRESS ");
        String address = Scan.SCANNER_STR.nextLine();


        terminalServise.addTerminal(name,address);

    }

    private void changeCardStatus() {
        cardList();
        consoleColors.print(ConsoleColors.BLUE,"ENTER CARD NUMBER");
        String s = Scan.SCANNER_STR.nextLine();
        cardService.changeStatusCard(s);

    }


    private void addCard()
    {
        consoleColors.print(ConsoleColors.BLUE,"ENTER THE CARD BALANCE FILLMENT AMOUNT");
        String s = Scan.SCANNER_STR.nextLine();
        if (Double.parseDouble(s)<0)
        {
            addCard();
        }
        else {
            cardService.createCard(s);
        }

    }
    private void cardList() {
        cardService.showCards();
    }


}
