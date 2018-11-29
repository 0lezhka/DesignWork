package com.kopylchak.commands.credit_commands;

import com.kopylchak.bank.BankManager;
import com.kopylchak.commands.Command;
import com.kopylchak.search.SearchEngine;
import com.kopylchak.bank.Bank;
import com.kopylchak.bank.Credit;
import com.kopylchak.bank.SortQualities;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BestCredits implements Command {
    String regex = "\\s*[cC][rR][eE][dD][iI][tT]\\s+[bB][eE][sS][tT]\\s+(?<quality>\\w+)\\s*";

    @Override
    public void ExecuteCommand(BankManager bankManager, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        if(matcher.find()) {
            Map<Bank, Credit> map = null;
            SearchEngine search = new SearchEngine(bankManager.getBankList());
            SortQualities sq = SortQualities.getQualityByString(matcher.group("quality"));

            try {
                map = search.getBestCreditsByQuality(sq);
                if (map == null)
                    throw new Exception("There are no credits");

                if(sq == null)
                    throw new Exception("Wrong quality");
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return;
            }

            for (Bank bank : map.keySet()) {
                System.out.println("[bank : " + bank.getName() + ", " + map.get(bank));
            }
        }
    }

    @Override
    public boolean matches(String str) {
        return str.matches(regex);
    }
}
