package com.kopylchak.commands.bank_commands;

import com.kopylchak.bank.BankManager;
import com.kopylchak.commands.Command;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SaveAs implements Command {
    private String regex = "\\s*[bB][aA][nN][kK]\\s+[sS][aA][vV][eE]\\s+(?<fileName>\\w+)\\s*";

    @Override
    public void ExecuteCommand(BankManager bankManager, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        if(matcher.find()) {
            try {
                bankManager.writeBankInFile(matcher.group("fileName"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean matches(String str) {
        return str.matches(regex);
    }
}
