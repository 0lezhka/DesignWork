package com.kopylchak.commands.bank_commands;

import com.kopylchak.bank.Bank;
import com.kopylchak.bank.BankManager;
import com.kopylchak.commands.Command;
import org.apache.log4j.Logger;

public class Load implements Command {
    private String regex = "\\s*[bB][aA][nN][kK]\\s+[lL][oO][aA][dD]\\s*";

    @Override
    public void ExecuteCommand(BankManager bankManager, String str){
        bankManager.loadBankFromFile(null);
    }

    @Override
    public boolean matches(String str) {
        return str.matches(regex);
    }
}
