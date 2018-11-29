package com.kopylchak.commands.bank_commands;

import com.kopylchak.bank.BankManager;
import com.kopylchak.commands.Command;

public class BankHelp implements Command {
    String regex = "\\s*[bB][aA][nN][kK]\\s+[hH][eE][lL][pP]\\s*";

    @Override
    public void ExecuteCommand(BankManager bankManager, String str) {
        System.out.println("Available commands :");
        System.out.println("Bank add [bankName]");
        System.out.println("Bank list");
        System.out.println("Bank delete [bankName]");
        System.out.println("Bank help");
        System.out.println("Bank Load");
        System.out.println("Bank Save\n");
    }

    @Override
    public boolean matches(String str) {
        return str.matches(regex);
    }
}
