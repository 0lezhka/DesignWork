package com.kopylchak.commands.client_commands;

import com.kopylchak.bank.Client;
import com.kopylchak.bank.BankManager;
import com.kopylchak.bank.ClientData;
import com.kopylchak.commands.Command;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientList implements Command {
    private String regex = "\\s*[cC][lL][iI][eE][nN][tT]\\s+[lL][iI][sS][tT]\\s+(?<bankName>\\w+)";

    @Override
    public void ExecuteCommand(BankManager bankManager, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        if (matcher.find()) {
            Map<Client, ClientData> map = bankManager.getBankByName(matcher.group("bankName")).getClientMap();
            for (Client client : map.keySet()) {
                System.out.println(client + " " + map.get(client));
            }
        }
    }

    @Override
    public boolean matches(String str) {
        return str.matches(regex);
    }
}
