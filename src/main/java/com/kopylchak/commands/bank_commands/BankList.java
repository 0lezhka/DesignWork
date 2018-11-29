package com.kopylchak.commands.bank_commands;

import com.kopylchak.bank.Bank;
import com.kopylchak.bank.BankManager;
import com.kopylchak.commands.Command;

public class BankList implements Command {
    private String regex = "\\s*[bB][aA][nN][kK]\\s+[lL][iI][sS][tT]\\s*";

    @Override
    public void ExecuteCommand(BankManager bankManager, String str) {
        for(Bank bank : bankManager.getBankList()){
            System.out.println(bank.getName());
        }
    }

    @Override
    public boolean matches(String str) {
        return str.matches(regex);
    }
}
