package com.company.service;

import com.company.entity.Card;
import com.company.entity.Terminal;
import com.company.enums.CardStatus;
import com.company.repository.TerminalRepository;
import com.company.utils.ConsoleColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminalServise {
    @Autowired
    private TerminalRepository terminalRepository;
    @Autowired
    private ConsoleColors consoleColors;

    public void addTerminal(String s,String address) {
        if (hasTerminal(s))
        {
            consoleColors.print(ConsoleColors.RED_BOLD,"THIS TERMINAL ALREADY REGISTERED ! ");
            return;
        }

        if ( terminalRepository.createTerminal(s,address))
        {
            consoleColors.print(ConsoleColors.RED_BOLD,"NEW TERMINAL SUCCESSFULLY  REGISTERED ! ");
            return;
        }




    }



    public boolean hasTerminal(String name)
    {
        return terminalRepository.isAvailable(name);


    }


    public void getAllTerminals() {

        List<Terminal> cardList = terminalRepository.getTerminals();
        if (cardList==null)
        {
            consoleColors.print(ConsoleColors.RED_BOLD,"TERMINALS NOT FOUND!");
            return;

        }
        consoleColors.printPlus(ConsoleColors.YELLOW_BOLD," NAME "+ConsoleColors.BLUE_BOLD+"          "+" ADDRESS ");

        for (Terminal terminal : cardList) {


            consoleColors.print(ConsoleColors.GREEN_BOLD," "+terminal.getName()+"  "+ConsoleColors.BLUE+"  "+terminal.getAddress());
        }
    }
}
