package com.kopylchak.commands.bank_commands;

import com.kopylchak.bank.BankManager;
import com.kopylchak.commands.Command;

public class Save implements Command {
    private String regex = "\\s*[bB][aA][nN][kK]\\s+[sS][aA][vV][eE]\\s*";

    @Override
    public void ExecuteCommand(BankManager bankManager, String str) {
        try {
            bankManager.writeBankInFile(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean matches(String str) {
        return str.matches(regex);
    }
}
