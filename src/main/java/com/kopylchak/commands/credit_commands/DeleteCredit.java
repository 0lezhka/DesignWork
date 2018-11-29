package com.kopylchak.commands.credit_commands;

import com.kopylchak.bank.BankManager;
import com.kopylchak.bank.Credit;
import com.kopylchak.commands.Command;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteCredit implements Command {
    private String regex = "\\s*[cC][rR][eE][dD][iI][tT]\\s+[dD][eE][lL][eE][tT][eE]\\s+(?<bankName>\\w+)\\s+(?<creditType>\\w+)";

    @Override
    public void ExecuteCommand(BankManager bankManager, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        try {
            if (matcher.find()) {
                bankManager.getBankByName(matcher.group("bankName")).deleteCredit(matcher.group("creditType"));
                Logger.getLogger(Credit.class).info(" Credit deleted : " + "[bank name : " +
                        matcher.group("bankName") + "; credit type : " + matcher.group("creditType") + "]");
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public boolean matches(String str) {
        return str.matches(regex);
    }
}
