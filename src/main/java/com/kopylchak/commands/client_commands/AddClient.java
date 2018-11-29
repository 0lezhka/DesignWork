package com.kopylchak.commands.client_commands;

import com.kopylchak.bank.Bank;
import com.kopylchak.bank.BankManager;
import com.kopylchak.bank.Client;
import com.kopylchak.commands.Command;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddClient implements Command {
    String regex = "\\s*[cC][lL][iI][eE][nN][tT]\\s+[aA][dD][dD]\\s+(?<bankName>\\w+)\\s+(?<clientName>\\w+)\\s*";

    @Override
    public void ExecuteCommand(BankManager bankManager, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        matcher.find();

        try {
            bankManager.getBankByName(matcher.group("bankName")).addClient(matcher.group("clientName"));
            Logger.getLogger(Client.class).info(" New client : " + "[bank : " + matcher.group("bankName") +
                    "; name : " + matcher.group("clientName") + "]");

        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public boolean matches(String str) {
        return str.matches(regex);
    }
}
