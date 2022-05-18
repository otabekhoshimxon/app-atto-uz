package com.company.controller;

import com.company.service.TransferService;
import com.company.utils.Scan;
import com.company.service.MainServise;
import com.company.utils.ConsoleColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MainController extends Thread {
    @Autowired
    private ConsoleColors consoleColors;
    @Autowired
    private MainServise mainServise;
    @Autowired
    private TransferService transferService;


    @Override
    public void run() {

        while (true) {
            showMenu();
            consoleColors.print(ConsoleColors.BLUE, "ENTER ACTION ");
            int i = Scan.SCANNER_INT.nextInt();
            action(i);
        }

    }

    public void showMenu() {
        consoleColors.print(ConsoleColors.GREEN_BOLD, "     MENU       ");
        consoleColors.print(ConsoleColors.GREEN_BOLD, " 1.LOGIN        ");
        consoleColors.print(ConsoleColors.GREEN_BOLD, " 2.TRANSFER     ");
        consoleColors.print(ConsoleColors.GREEN_BOLD, " 3.FILL BALANCE ");
    }

    public void action(int i) {
        switch (i) {
            case 1 -> {
                getLoginPage();
                break;
            }
            case 2 -> {
                getTransferPage();
                break;
            }
            case 3 -> {
                getFillBalancePage();
                break;
            }

        }
    }


    public void getLoginPage() {
        mainServise.login();
    }

    public void getTransferPage() {
        transferService.transfer();
    }

    public void getFillBalancePage() {
        mainServise.fillBalance();
    }

}
