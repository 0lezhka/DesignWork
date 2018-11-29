package com.kopylchak.commands.client_commands;

import com.kopylchak.bank.Bank;
import com.kopylchak.bank.BankManager;
import com.kopylchak.bank.Client;
import com.kopylchak.commands.Command;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteClient implements Command {
    String regex = "\\s*[cC][lL][iI][eE][nN][tT]\\s+[dD][eE][lL][eE][tT][eE]\\s+(?<bankName>\\w+)\\s+(?<clientName>\\w+)\\s*";

    @Override
    public void ExecuteCommand(BankManager bankManager, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        if (matcher.find())
            bankManager.getBankByName(matcher.group("bankName")).deleteClient(matcher.group("clientName"));
            Logger.getLogger(Client.class).info(" Client deleted : " + "[bank : " + matcher.group("bankName") +
                    "; name : " + matcher.group("clientName") + "]");
    }

    @Override
    public boolean matches(String str) {
        return str.matches(regex);
    }
}
