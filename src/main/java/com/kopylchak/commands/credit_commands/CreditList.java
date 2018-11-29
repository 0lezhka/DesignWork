package com.kopylchak.commands.credit_commands;

import com.kopylchak.bank.Credit;
import com.kopylchak.bank.BankManager;
import com.kopylchak.commands.Command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditList implements Command {
    private String regex = "\\s*[cC][rR][eE][dD][iI][tT]\\s+[lL][iI][sS][tT]\\s+(?<bankName>\\w+)\\s*";

    @Override
    public void ExecuteCommand(BankManager bankManager, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        if (matcher.find()) {
            for (Credit credit : bankManager.getBankByName(matcher.group("bankName")).getCreditList()) {
                System.out.println(credit);
            }
        }
    }

    @Override
    public boolean matches(String str) {
        return str.matches(regex);
    }
}
