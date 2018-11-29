package com.kopylchak.commands.credit_commands;

import com.kopylchak.bank.BankManager;
import com.kopylchak.commands.Command;
import com.kopylchak.search.SearchEngine;
import com.kopylchak.bank.Bank;
import com.kopylchak.bank.Credit;
import com.kopylchak.bank.SortQualities;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeekCredit implements Command {
    private String regex = "\\s*[cC][rR][eE][dD][iI][tT]\\s+[sS][eE][eE][kK]\\s+(?<bankName>\\w+|\\*)\\s+(?<creditType>\\w+|\\*)\\s+" +
            "(?<isEarlyRepayment>\\w+|\\*)\\s+(?<isLineExtension>\\w+|\\*)\\s*\\s+" +
            "(?<range>((?<quality>\\w+)\\s+(?<minValue>\\d+\\.?\\d*)\\s+(?<maxValue>\\d+\\.?\\d*))|\\*)\\s*";

    public void ExecuteCommand(BankManager bankManager, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        SearchEngine seek = new SearchEngine(bankManager.getBankList());
        Map<Bank, List<Credit>> result = null;

        if (matcher.find()) {

            // пошук по банку
            try {
                if (!matcher.group(1).equals("*")) {
                    result = new LinkedHashMap<>();
                    result.put(bankManager.getBankByName(matcher.group("bankName")),
                            bankManager.getBankByName(matcher.group("bankName")).getCreditList());
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return;
            }

            // пошук по типу
            try {
                if (!matcher.group("creditType").equals("*")) {
                    if (result != null) {
                        result = conjunction(result, seek.getCreditsByType(matcher.group("creditType")));
                    } else
                        result = seek.getCreditsByType(matcher.group("creditType"));
                }
            } catch (Exception e){
                System.err.println(e.getMessage());
            }

            // пошук по достроковому погашенні
            if (!matcher.group("isEarlyRepayment").equals("*")) {
                if (result != null) {
                    result = conjunction(result, seek.getCreditsByEarlyRepayment(matcher.group("isEarlyRepayment").
                            equalsIgnoreCase("true")));
                } else
                    result = seek.getCreditsByEarlyRepayment(matcher.group("isEarlyRepayment").equalsIgnoreCase("true"));
            }

            // пошук по можливості продовжити лінію кредиту
            if (!matcher.group("isLineExtension").equals("*")) {
                if (result != null) {
                    result = conjunction(result, seek.getCreditsByLineExtension(matcher.group("isLineExtension").
                            equalsIgnoreCase("true")));
                } else
                    result = seek.getCreditsByLineExtension(matcher.group("isLineExtension").equalsIgnoreCase("true"));
            }

            // пошук по діапазону
            if (!matcher.group("range").equals("*")) {
                if (result == null) {
                    result = seek.getCreditsByQuality(SortQualities.getQualityByString(matcher.group("quality")),
                            Double.parseDouble(matcher.group("minValue")),
                            Double.parseDouble(matcher.group("maxValue")));
                } else {
                    result = conjunction(result, seek.getCreditsByQuality(SortQualities.getQualityByString(matcher.group(7)),
                            Double.parseDouble(matcher.group("minValue")),
                            Double.parseDouble(matcher.group("maxValue"))));
                }

            }

            if (result != null) {
                for (Bank bank : result.keySet()) {
                    for (Credit credit : result.get(bank))
                        System.out.println(bank.getName() + credit);
                }
            } else {
                System.out.println("No match");
            }
        }
    }

    public boolean matches(String str) {
        return str.matches(regex);
    }

    private Map<Bank, List<Credit>> conjunction(Map<Bank, List<Credit>> a, Map<Bank, List<Credit>> b) {
        Map<Bank, List<Credit>> result = new LinkedHashMap<>();

        for (Bank firstBank : a.keySet()) {
            for (Bank secondBank : b.keySet()) {
                if (firstBank == secondBank) {
                    result.put(firstBank, new ArrayList<>());
                    for (Credit firstCredit : a.get(firstBank)) {
                        for (Credit secondCredit : b.get(secondBank)) {
                            if (firstCredit == secondCredit)
                                result.get(firstBank).add(firstCredit);
                        }
                    }
                    break;
                }
            }
        }

        return result;
    }
}
