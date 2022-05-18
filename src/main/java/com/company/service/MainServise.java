package com.company.service;

import com.company.controller.AdminController;
import com.company.repository.AdminRepository;
import com.company.utils.ConsoleColors;
import com.company.utils.Scan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Pattern;
@Service
public class MainServise {
    @Autowired
    private ConsoleColors consoleColors;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AdminController adminController;
    public void login() {
        consoleColors.print(ConsoleColors.GREEN_BOLD,"ENTER LOGIN ");
        String login =Scan.SCANNER_STR.nextLine();
        consoleColors.print(ConsoleColors.GREEN_BOLD,"ENTER PASSWORD ");
        String password =Scan.SCANNER_STR.nextLine();
        showLogin(login,password);

    }

    public void transfer() {


    }

    public void showLogin(String login,String password)
    {

        if (chechLogPass(login,password))
        {
            System.out.println("ok");
            return;
        }
        if (adminRepository.isAdmin(login, password) )
        {
            adminController.start();
        }


    }


    public boolean chechLogPass(String login,String password)
    {
        return Objects.isNull(login)&& Objects.isNull(password);
    }
    public void fillBalance() {


    }




}
