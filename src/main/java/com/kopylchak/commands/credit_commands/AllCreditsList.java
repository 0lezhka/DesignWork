package com.kopylchak.commands.credit_commands;

import com.kopylchak.bank.BankManager;
import com.kopylchak.bank.Bank;
import com.kopylchak.bank.Credit;
import com.kopylchak.commands.Command;

public class AllCreditsList implements Command {
    private String regex = "\\s*[cC][rR][eE][dD][iI][tT]\\s+[lL][iI][sS][tT]\\s*";

    @Override
    public void ExecuteCommand(BankManager bankManager, String str) {
        for(Bank bank : bankManager.getBankList()){
            for(Credit credit : bank.getCreditList()){
                System.out.println(bank.getName() + " : " + credit);
            }
        }
    }

    @Override
    public boolean matches(String str) {
        return str.matches(regex);
    }
}
