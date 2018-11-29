package com.kopylchak.commands.bank_commands;

import com.kopylchak.bank.Bank;
import com.kopylchak.bank.BankManager;
import com.kopylchak.commands.Command;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddBank implements Command {
    String regex = "\\s*[bB][aA][nN][kK]\\s+[aA][dD][dD]\\s+(?<bankName>\\w+)\\s*";

    @Override
    public void ExecuteCommand(BankManager bankManager, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        matcher.find();

        bankManager.addBank(matcher.group("bankName"));
        Logger.getLogger(Bank.class).info(" New bank : " + matcher.group("bankName"));
    }

    @Override
    public boolean matches(String str) {
        return str.matches(regex);
    }
}
