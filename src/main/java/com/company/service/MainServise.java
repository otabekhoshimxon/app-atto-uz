package com.company.service;

import com.company.controller.AdminController;
import com.company.repository.AdminRepository;
import com.company.utils.ConsoleColors;
import com.company.utils.Scan;

import java.util.Objects;
import java.util.regex.Pattern;

public class MainServise {

    private ConsoleColors consoleColors;
    private AdminRepository adminRepository;
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


    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }

    public void setAdminRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void setConsoleColors(ConsoleColors consoleColors) {
        this.consoleColors = consoleColors;
    }


}
