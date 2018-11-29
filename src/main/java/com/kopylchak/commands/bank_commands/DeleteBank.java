package com.kopylchak.commands.bank_commands;

import com.kopylchak.bank.Bank;
import com.kopylchak.bank.BankManager;
import com.kopylchak.commands.Command;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteBank implements Command {
    String regex = "\\s*[bB][aA][nN][kK]\\s+[dD][eE][lL][eE][tT][eE]\\s+(?<bankName>\\w+)$";

    @Override
    public void ExecuteCommand(BankManager bankManager, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        if (matcher.find()) {
            try {
                bankManager.deleteBank(matcher.group("bankName"));
                Logger.getLogger(Bank.class).info(" Bank deleted : " + matcher.group("bankName"));
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    public boolean matches(String str) {
        return str.matches(regex);
    }
}
