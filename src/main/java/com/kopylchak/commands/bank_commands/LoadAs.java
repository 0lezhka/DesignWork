package com.kopylchak.commands.bank_commands;

import com.kopylchak.bank.BankManager;
import com.kopylchak.commands.Command;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoadAs implements Command {
    private String regex = "\\s*[bB][aA][nN][kK]\\s+[lL][oO][aA][dD]\\s+(?<fileName>\\w+)\\s*";

    @Override
    public void ExecuteCommand(BankManager bankManager, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        if (matcher.find()) {
            bankManager.loadBankFromFile(matcher.group("fileName"));
        }
    }

    @Override
    public boolean matches(String str) {
        return str.matches(regex);
    }
}
