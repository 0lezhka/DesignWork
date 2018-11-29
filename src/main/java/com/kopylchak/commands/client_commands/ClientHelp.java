package com.kopylchak.commands.client_commands;

import com.kopylchak.bank.BankManager;
import com.kopylchak.commands.Command;

public class ClientHelp implements Command {
    String regex = "\\s*[cC][lL][iI][eE][nN][tT]\\s+[hH][eE][lL][pP]\\s*";

    @Override
    public void ExecuteCommand(BankManager bankManager, String str) {
        System.out.println("Client add [bankName] [clientName]");
        System.out.println("Client list [bankName]");
        System.out.println("Client delete [bankName] [clientName]\n");
        System.out.println("Client help");
    }

    @Override
    public boolean matches(String str) {
        return str.matches(regex);
    }
}
